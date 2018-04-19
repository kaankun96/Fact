package com.example.kaan.factapp.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.kaan.factapp.Adapter.TotalEventAdapter;
import com.example.kaan.factapp.Connection.RestControllerFactory;
import com.example.kaan.factapp.Model.LocationModel;
import com.example.kaan.factapp.Model.TotalEventModel;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.Utils.GPSTracker;
import com.example.kaan.factapp.databinding.ActivityMapsBinding;
import com.example.kaan.factapp.databinding.ActivityShareBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback,View.OnClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    GPSTracker gps;
    public String location;
    private ArrayList<TotalEventModel> totalEventModels;
    private LocationModel locationModel;

    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_maps);
        takeLocation();
        init();

    }

    private void init() {
       getIncidentList();
    }
    private void getIncidentList() {
        showLoadingIndicator();
        locationModel.setLocation(location);
        RestControllerFactory.getInstance().getRegisterFactory().closeListAPI(locationModel).enqueue(new Callback<ArrayList<TotalEventModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TotalEventModel>> call, Response<ArrayList<TotalEventModel>> response) {
                if (response.isSuccessful()) {
                    hideLoadingIndicator();
                    totalEventModels = response.body();
                    TotalEventAdapter adapter = new TotalEventAdapter(MapsActivity.this, totalEventModels);
                    binding.rvReportAll.setAdapter(adapter);
                    binding.rvReportAll.setLayoutManager(new LinearLayoutManager(MapsActivity.this, LinearLayoutManager.VERTICAL, false));

                    if (totalEventModels.size() == 0) {
                        binding.rvReportAll.setVisibility(View.GONE);
                        //binding.tvInfo.setVisibility(View.GONE);
                    } else {
                        binding.rvReportAll.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TotalEventModel>> call, Throwable t) {
                hideLoadingIndicator();
            }
        });
    }

    @Override
    protected ToolbarBinding getToolBarBinding() {
        binding.toolbar.imgBtnToolbarMenu.setVisibility(View.GONE);
        binding.toolbar.imgBtnToolbarExit.setVisibility(View.GONE);
        binding.toolbar.imgBtnToolbarBack.setVisibility(View.VISIBLE);
        return binding.toolbar;
    }

    public void takeLocation() {

        gps = new GPSTracker(MapsActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            location = String.valueOf(latitude + " " + longitude);
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

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
