package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivitySplashBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;


    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        setStatusBarTranslucent(true);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NavigationHelper.getInstance().startWelcomeActivityDirect(SplashActivity.this);
                finish();
            }
        }, 1500);
    }


    @Override
    protected ToolbarBinding getToolBarBinding() {
        return binding.toolbar;
    }
}
