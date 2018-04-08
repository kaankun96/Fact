package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kaan.factapp.Connection.RestControllerFactory;
import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.Model.Request.LoginRequest;
import com.example.kaan.factapp.Model.Response.LoginResponse;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivityLoginBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    public LoginRequest loginRequest;


    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
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
        switch (v.getId()) {
            case R.id.btnLogin:
                loginAPI();
                break;
        }
    }

    public void loginAPI() {
        RestControllerFactory.getInstance().getRegisterFactory().loginAPI(binding.etMailLogin.getText().toString(), binding.etPasswordLogin.getText().toString()).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                switch (response.code()) {
                    case 200:
                        Toast.makeText(LoginActivity.this, response.body().getResponse(), Toast.LENGTH_LONG).show();
                        NavigationHelper.getInstance().startLoginToChoosingActivityDirect(LoginActivity.this);
                        break;

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Birşeyler Ters Gitti...", Toast.LENGTH_LONG).show();

            }
        });
    }
}
