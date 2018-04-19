package com.example.kaan.factapp.Activity;


import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.kaan.factapp.Connection.RestControllerFactory;
import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.Model.Response.RegisterResponse;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivitySignBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignActivity extends BaseActivity implements View.OnClickListener {

    private ActivitySignBinding binding;
    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    private String email, password, name, lastName, phone, birthDate, gender;

    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign);

        init();
    }

    private void init() {
        binding.etBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
                // TODO Auto-generated method stub
                new DatePickerDialog(SignActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        binding.btnCreateAccountWelcome.setOnClickListener(this);

    }

    @Override
    protected ToolbarBinding getToolBarBinding() {
        return null;

    }

    public void showDatePicker() {


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        binding.etBirthDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreateAccountWelcome:
                signToDatabase();
                break;

        }
    }

    public void signToDatabase() {


        email = binding.etMail.getText().toString();
        password = binding.etPassword.getText().toString();
        name = binding.etName.getText().toString();
        lastName = binding.etLastName.getText().toString();
        phone = binding.etPhone.getText().toString();
        birthDate = binding.etBirthDate.getText().toString();
        gender = binding.etGender.getText().toString();

        showLoadingIndicator();
        RestControllerFactory.getInstance().getRegisterFactory().registerAPI(email, password, name, lastName, phone, birthDate, gender).enqueue
                (new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus() == 200) {
                                hideLoadingIndicator();
                                Toast.makeText(SignActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                                NavigationHelper.getInstance().startLoginActivityDirect(SignActivity.this);

                            }
                        } else {
                            Toast.makeText(SignActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        hideLoadingIndicator();
                        Toast.makeText(SignActivity.this, "Bağlantı Hatası veya Var Olan Email Girişi", Toast.LENGTH_SHORT).show();
                        Log.e("", t.toString());
                    }
                });


    }
}
