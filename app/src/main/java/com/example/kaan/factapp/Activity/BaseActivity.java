package com.example.kaan.factapp.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ToolbarBinding;


public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected ToolbarBinding toolbarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setStatusBarTranslucent(false);
        onCreateFinished(savedInstanceState);
        constructorActionBar();

        // Uygulama dik konuma hizalansın diye.
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }
    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    private void constructorActionBar() {
        toolbarBinding=getToolBarBinding();
        if (toolbarBinding != null) {
            if (toolbarBinding.imgBtnToolbarBack != null) {
                toolbarBinding.imgBtnToolbarBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }

            if (toolbarBinding.imgBtnToolbarMenu != null) {
                toolbarBinding.imgBtnToolbarMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            if (drawer.isDrawerVisible(GravityCompat.START)) {
                                drawer.closeDrawer(GravityCompat.START);
                            } else {
                                drawer.openDrawer(GravityCompat.START);
                            }
                        } catch (Exception e) {

                        }
                    }
                });
            }

        }

    }
    protected abstract void onCreateFinished(Bundle savedInstanceState);

    protected abstract ToolbarBinding getToolBarBinding();

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share_incident) {

        } else if (id == R.id.nav_report_incident) {

        } else if (id == R.id.nav_report_incident) {

        } else if (id == R.id.nav_all_incident) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
