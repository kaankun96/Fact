package com.example.kaan.factapp.Helper;

import android.content.Context;
import android.content.Intent;

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

}
