package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Bundle;

import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.Model.Response.LoginResponse;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivitySplashBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

import static com.example.kaan.factapp.FactApp.getClientPreferences;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;


    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        setStatusBarTranslucent(true);
//alt +enter yapıyım mı kanka

       if (getClientPreferences() != null){
            if (getClientPreferences().getUser() == null){

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            NavigationHelper.getInstance().startWelcomeActivityDirect(SplashActivity.this);
                            finish();
                        }
                    }, 1500);

            }else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NavigationHelper.getInstance().startChoosingActivityDirect(SplashActivity.this,getClientPreferences().getUser().getEmail());
                        finish();
                    }
                }, 1500);
            }
        }
    }


    @Override
    protected ToolbarBinding getToolBarBinding() {
        return binding.toolbar;
    }
}
