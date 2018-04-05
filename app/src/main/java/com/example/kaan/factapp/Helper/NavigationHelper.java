package com.example.kaan.factapp.Helper;

import android.content.Context;
import android.content.Intent;

import com.example.kaan.factapp.Activity.LoginActivity;
import com.example.kaan.factapp.Activity.ShareActivity;
import com.example.kaan.factapp.Activity.SignActivity;
import com.example.kaan.factapp.Activity.WelcomeActivity;

public class NavigationHelper {

    private static final NavigationHelper ourInstance = new NavigationHelper();

    public static NavigationHelper getInstance() {
        return ourInstance;
    }

    private NavigationHelper() {
    }

    private void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public void startWelcomeActivityDirect(Context context) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        startActivity(context, intent);
    }
    public void startWelcomeToSignActivityDirect(Context context) {
        Intent intent = new Intent(context, SignActivity.class);
        startActivity(context, intent);
    }
    public void startWelcomeToLoginActivityDirect(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(context, intent);
    }
    public void startChoosingToShareActivityDirect(Context context){
        Intent intent = new Intent(context, ShareActivity.class);
        startActivity(context, intent);
    }

}
