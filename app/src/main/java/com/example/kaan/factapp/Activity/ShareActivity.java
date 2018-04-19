package com.example.kaan.factapp.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.kaan.factapp.Connection.RestControllerFactory;
import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.Model.Request.UploadRequest;
import com.example.kaan.factapp.Model.Response.UploadResponse;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.Utils.GPSTracker;
import com.example.kaan.factapp.databinding.ActivityShareBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

import java.io.ByteArrayOutputStream;

import java.util.Date;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kaan.factapp.FactApp.getClientPreferences;

public class ShareActivity extends BaseActivity implements View.OnClickListener {
    GPSTracker gps;
    private ActivityShareBinding binding;
    private UploadRequest uploadRequest;
    public String url, url2, url3;
    public String location;
    Bitmap thumbnail;
    byte[] dataImage,dataImage2,dataImage3;
    String incidentName;
    Date currentTime = Calendar.getInstance().getTime();
    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_share);
        takeLocation();
        init();

    }

    private void init() {
        binding.ivAddPhoto1.setOnClickListener(this);
        binding.ivAddPhoto2.setOnClickListener(this);
        binding.ivAddPhoto3.setOnClickListener(this);
        binding.btnIncidentShare.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        incidentName = extras.getString("incidentName");
    }

    @Override
    protected ToolbarBinding getToolBarBinding() {
        binding.toolbar.imgBtnToolbarMenu.setVisibility(View.GONE);
        binding.toolbar.imgBtnToolbarBack.setVisibility(View.VISIBLE);
        return binding.toolbar;
    }

    public void takeLocation() {

        gps = new GPSTracker(ShareActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            location = String.valueOf(latitude + " " + longitude);
            binding.etCoordinate.setText(latitude + " " + longitude);
            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivAddPhoto1:
                Intent takePicture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //takePicture.putExtra(MediaStore.EXTRA_OUTPUT, takePicture);
                startActivityForResult(takePicture,0);
                break;
            case R.id.ivAddPhoto2:
                Intent takePicture2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //takePicture.putExtra(MediaStore.EXTRA_OUTPUT, takePicture);
                startActivityForResult(takePicture2,1);
                break;
            case R.id.ivAddPhoto3:
                Intent takePicture3=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //takePicture.putExtra(MediaStore.EXTRA_OUTPUT, takePicture);
                startActivityForResult(takePicture3,2);
                break;
            case R.id.btnIncidentShare:
                uploadToAPI(dataImage,dataImage2,dataImage3);
                break;
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            thumbnail= (Bitmap) extras.get("data");
            binding.ivAddPhoto1.setImageBitmap(thumbnail);
            binding.ivAddPhoto1.setDrawingCacheEnabled(true);
            binding.ivAddPhoto1.buildDrawingCache();
            Bitmap bitmap=binding.ivAddPhoto1.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            dataImage = baos.toByteArray();
        } else if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            thumbnail= (Bitmap) extras.get("data");
            binding.ivAddPhoto2.setImageBitmap(thumbnail);
            binding.ivAddPhoto2.setDrawingCacheEnabled(true);
            binding.ivAddPhoto2.buildDrawingCache();
            Bitmap bitmap=binding.ivAddPhoto2.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            dataImage2 = baos.toByteArray();
        } else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            thumbnail= (Bitmap) extras.get("data");
            binding.ivAddPhoto3.setImageBitmap(thumbnail);
            binding.ivAddPhoto3.setDrawingCacheEnabled(true);
            binding.ivAddPhoto3.buildDrawingCache();
            Bitmap bitmap=binding.ivAddPhoto3.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            dataImage3 = baos.toByteArray();
        }
    }



    public void uploadToAPI(byte[] dataImage,byte[] dataImage2,byte[] dataImage3) {
        url = String.valueOf(dataImage);
        url2 = String.valueOf(dataImage2);
        url3 = String.valueOf(dataImage3);
        showLoadingIndicator();
        RestControllerFactory.getInstance().getRegisterFactory().uploadAPI(String.valueOf(currentTime), url, url2, url3, incidentName, location, getClientPreferences().getUser().getEmail(), getClientPreferences().getUser().getName(), getClientPreferences().getUser().getLastName(), "NO").enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        hideLoadingIndicator();
                        String message = response.body().getMessage();
                        Toast.makeText(ShareActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        NavigationHelper.getInstance().startLoginActivityDirect(ShareActivity.this);
                        Log.i("Kaan", response.body().getMessage());

                    }
                } else {
                    hideLoadingIndicator();
                    Toast.makeText(ShareActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                hideLoadingIndicator();
                Toast.makeText(ShareActivity.this, "Bağlantı Hatası veya Var Olan Email Girişi", Toast.LENGTH_SHORT).show();
                Log.e("", t.toString());
            }
        });
    }


}
