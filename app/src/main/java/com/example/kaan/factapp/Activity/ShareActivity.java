package com.example.kaan.factapp.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.io.File;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
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
    byte[] dataImage, dataImage2, dataImage3;
    String incidentName;
    Date currentTime = Calendar.getInstance().getTime();
    private Uri uri;
    File file, file2, file3;
    private static final int READ_REQUEST_CODE = 300;

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
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 0);
                break;
            case R.id.ivAddPhoto2:
                Intent intent2 = new Intent();
                intent2.setType("image/*");
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent2, "Select Image"), 1);
                break;
            case R.id.ivAddPhoto3:
                Intent intent3 = new Intent();
                intent3.setType("image/*");
                intent3.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent3, "Select Image"), 2);
                break;
            case R.id.btnIncidentShare:
                uploadToAPI();
                break;
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                android.net.Uri uri = data.getData();
                String filePath = getRealPathFromURIPath(uri, ShareActivity.this);
                file = new File(filePath);
                binding.ivAddPhoto1.setImageURI(uri);
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }

           /* Bundle extras = data.getExtras();
            thumbnail = (Bitmap) extras.get("data");
            binding.ivAddPhoto1.setImageBitmap(thumbnail);
            binding.ivAddPhoto1.setDrawingCacheEnabled(true);
            binding.ivAddPhoto1.buildDrawingCache();
            Bitmap bitmap = binding.ivAddPhoto1.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            dataImage = baos.toByteArray();*/

        } else if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                android.net.Uri uri = data.getData();
                String filePath2 = getRealPathFromURIPath(uri, ShareActivity.this);
                file2 = new File(filePath2);
                binding.ivAddPhoto2.setImageURI(uri);
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }


            /*Bundle extras = data.getExtras();
            thumbnail = (Bitmap) extras.get("data");
            binding.ivAddPhoto2.setImageBitmap(thumbnail);
            binding.ivAddPhoto2.setDrawingCacheEnabled(true);
            binding.ivAddPhoto2.buildDrawingCache();
            Bitmap bitmap = binding.ivAddPhoto2.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            dataImage2 = baos.toByteArray();*/
        } else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                android.net.Uri uri = data.getData();
                String filePath3 = getRealPathFromURIPath(uri, ShareActivity.this);
                file3 = new File(filePath3);
                binding.ivAddPhoto3.setImageURI(uri);
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            /*Bundle extras = data.getExtras();
            thumbnail = (Bitmap) extras.get("data");
            binding.ivAddPhoto3.setImageBitmap(thumbnail);
            binding.ivAddPhoto3.setDrawingCacheEnabled(true);
            binding.ivAddPhoto3.buildDrawingCache();
            Bitmap bitmap = binding.ivAddPhoto3.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            dataImage3 = baos.toByteArray();*/
        }
    }


    public void uploadToAPI() {
        /*uploadRequest = new UploadRequest();
        uploadRequest.setDateTimes(String.valueOf(currentTime));
        uploadRequest.setName(incidentName);
        uploadRequest.setUrl(String.valueOf(dataImage));
        uploadRequest.setUrl2(String.valueOf(dataImage2));
        uploadRequest.setUrl3(String.valueOf(dataImage3));
        uploadRequest.setLocation(location);
        uploadRequest.setUserEmail(getClientPreferences().getUser().getEmail());
        uploadRequest.setUserName(getClientPreferences().getUser().getName());
        uploadRequest.setUserLastName(getClientPreferences().getUser().getLastName());
        uploadRequest.setResultIncident("NO");*/
        showLoadingIndicator();

        RequestBody currentTimeBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(currentTime));
        RequestBody incidentNameBody = RequestBody.create(MediaType.parse("text/plain"), incidentName);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", "image", incidentNameBody);
        MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", "image2", incidentNameBody);
        MultipartBody.Part image3 = MultipartBody.Part.createFormData("image3", "image3", incidentNameBody);

        RequestBody locationBody = RequestBody.create(MediaType.parse("text/plain"), location);
        RequestBody userEmailBody = RequestBody.create(MediaType.parse("text/plain"), getClientPreferences().getUser().getEmail());
        RequestBody userNameBody = RequestBody.create(MediaType.parse("text/plain"), getClientPreferences().getUser().getName());
        RequestBody userLastNameBody = RequestBody.create(MediaType.parse("text/plain"), getClientPreferences().getUser().getLastName());
        RequestBody resultIncidentBody = RequestBody.create(MediaType.parse("text/plain"), "NO");

        RestControllerFactory.getInstance().getRegisterFactory().uploadAPI(currentTimeBody, incidentNameBody, image, image2, image3, locationBody, userEmailBody, userNameBody, userLastNameBody, resultIncidentBody).enqueue(new Callback<UploadResponse>() {
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
                Toast.makeText(ShareActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("", t.toString());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, ShareActivity.this);
    }


    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(0);
        }
    }

    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (uri != null) {
            String filePath = getRealPathFromURIPath(uri, ShareActivity.this);
            file = new File(filePath);
        }
    }
}
