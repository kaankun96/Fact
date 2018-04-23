package com.example.kaan.factapp.Helper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.kaan.factapp.Activity.ChoosingActivity;
import com.example.kaan.factapp.Activity.LoginActivity;
import com.example.kaan.factapp.Activity.MapsActivity;
import com.example.kaan.factapp.Activity.ReportActivity;
import com.example.kaan.factapp.Activity.ShareActivity;
import com.example.kaan.factapp.Activity.SignActivity;
import com.example.kaan.factapp.Activity.TotalEventActivity;
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
    public void startSignActivityDirect(Context context) {
        Intent intent = new Intent(context, SignActivity.class);
        startActivity(context, intent);
    }
    public void startShareActivityDirect(Context context,String incidentName){
        Intent intent = new Intent(context, ShareActivity.class);
        intent.putExtra("incidentName",incidentName);
        startActivity(context, intent);
    }
    public void startLoginActivityDirect(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(context, intent);
    }
    public void startChoosingActivityDirect(Context context) {
        Intent intent = new Intent(context, ChoosingActivity.class);
        startActivity(context, intent);
    }
    public void startChoosingActivityDirect(Context context,String userEmail) {
        Intent intent = new Intent(context, ChoosingActivity.class);
        intent.putExtra("userEmail",userEmail);
        startActivity(context, intent);
    }
    public void startReportActivityDirect(Context context){
        Intent intent = new Intent(context, ReportActivity.class);
        startActivity(context, intent);
    }
    public void startTotalEventActivityDirect(Context context){
        Intent intent = new Intent(context, TotalEventActivity.class);
        startActivity(context, intent);
    }
    public void startMapsActivityDirect(Context context){
        Intent intent = new Intent(context, MapsActivity.class);
        startActivity(context, intent);
    }
}
