package com.example.kaan.factapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.kaan.factapp.R;

public class PrefConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefFile), Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.prefStatus), status);
        editor.commit();
    }

    public boolean readLoginStatus() {
        return sharedPreferences.getBoolean(context.getString(R.string.prefStatus), false);
    }

    public void writeName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.prefFile), name);
        editor.commit();
    }

    public String readName() {
        return sharedPreferences.getString(context.getString(R.string.prefFile), "User");
    }
    public void displayToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}

