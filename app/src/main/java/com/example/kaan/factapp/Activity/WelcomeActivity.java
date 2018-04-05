package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivityWelcomeBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        init();
    }

    private void init() {
        binding.btnCreateAccount.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);
    }


    @Override
    protected ToolbarBinding getToolBarBinding() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreateAccount:
                NavigationHelper.getInstance().startWelcomeToSignActivityDirect(WelcomeActivity.this);
                Log.i("KAAN", "GEÇTİİİ");
                break;
            case R.id.btnLogin:
                NavigationHelper.getInstance().startWelcomeToLoginActivityDirect(WelcomeActivity.this);
                break;
        }
    }
}
