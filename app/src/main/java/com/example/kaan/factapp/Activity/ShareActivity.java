package com.example.kaan.factapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kaan.factapp.R;
import com.example.kaan.factapp.Utils.GPSTracker;
import com.example.kaan.factapp.databinding.ActivityShareBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

public class ShareActivity extends BaseActivity implements View.OnClickListener {
    GPSTracker gps;
    private ActivityShareBinding binding;

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
    }

    @Override
    protected ToolbarBinding getToolBarBinding() {
        return binding.toolbar;
    }

    public void takeLocation() {

        gps = new GPSTracker(ShareActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

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
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
                break;
            case R.id.ivAddPhoto2:
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent2, 1);
                break;
            case R.id.ivAddPhoto3:
                Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent3, 2);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            binding.ivAddPhoto1.setImageBitmap(bitmap);
        } else if (requestCode == 1) {
            Bitmap bitmap2 = (Bitmap) data.getExtras().get("data");
            binding.ivAddPhoto2.setImageBitmap(bitmap2);
        } else if (requestCode == 2) {
            Bitmap bitmap3 = (Bitmap) data.getExtras().get("data");
            binding.ivAddPhoto3.setImageBitmap(bitmap3);
        }
    }
}
