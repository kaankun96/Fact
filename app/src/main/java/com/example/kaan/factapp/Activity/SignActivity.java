package com.example.kaan.factapp.Activity;


import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivitySignBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SignActivity extends BaseActivity implements View.OnClickListener {

    private ActivitySignBinding binding;
    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

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

                break;

        }
    }

    public void signToFirebase() {
        String email = binding.etMail.getText().toString();
        String password = binding.etPassword.getText().toString();
        String name = binding.etName.getText().toString();
        String lastName = binding.etLastName.getText().toString();
        String phone = binding.etPhone.getText().toString();
        String birthDate = binding.etBirthDate.getText().toString();
        String gender = binding.etGender.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Lütfen Mailinizi Giriniz", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Lütfen  Parolanızı Giriniz", Toast.LENGTH_SHORT).show();
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Lütfen  Parolanızı 6 Haneli Giriniz", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Lütfen  İsminizi Giriniz", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(getApplicationContext(), "Lütfen  Soyisminizi Giriniz", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplicationContext(), "Lütfen  Telefon Numaranızı Giriniz", Toast.LENGTH_SHORT).show();
        }
        if (phone.length() < 11 || phone.length() > 11) {
            Toast.makeText(getApplicationContext(), "Lütfen  Telefon Numaranızı Doğru Giriniz", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(birthDate)) {
            Toast.makeText(getApplicationContext(), "Lütfen  Doğum Tarihinizi Giriniz", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(gender)) {
            Toast.makeText(getApplicationContext(), "Lütfen  Cinsiyetinizi Giriniz", Toast.LENGTH_SHORT).show();
        }

    }
}
