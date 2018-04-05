package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivityLoginBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

public class LoginActivity extends BaseActivity  implements View.OnClickListener{

    private ActivityLoginBinding binding;


    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        init();
    }

    private void init() {
        binding.btnLogin.setOnClickListener(this);
    }

    @Override
    protected ToolbarBinding getToolBarBinding() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:

                break;
        }
    }
}
