package com.example.kaan.factapp.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.kaan.factapp.FactApp;
import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.Utils.ClientPreferences;
import com.example.kaan.factapp.databinding.ToolbarBinding;

import static com.example.kaan.factapp.FactApp.getClientPreferences;


public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected ToolbarBinding toolbarBinding;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateFinished(savedInstanceState);

        constructorActionBar();

        // Uygulama dik konuma hizalansın diye.
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void constructorActionBar() {
        toolbarBinding = getToolBarBinding();
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
            if (toolbarBinding.imgBtnToolbarExit != null) {
                toolbarBinding.imgBtnToolbarExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alertDialogError = new AlertDialog.Builder(BaseActivity.this);
                        alertDialogError.setTitle("DİKKAT");
                        alertDialogError.setIcon(R.drawable.ic_green_exit);
                        alertDialogError.setMessage("Kullanıcı çıkışı yapmak istiyor musunuz?");
                        alertDialogError.setIcon(R.drawable.ic_error_red);
                        alertDialogError.setCancelable(false);
                        alertDialogError.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getClientPreferences().setUser(null);
                                NavigationHelper.getInstance().startLoginActivityDirect(BaseActivity.this);
                                dialog.dismiss();
                                finish();
                            }
                        });
                        alertDialogError.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alertDialog = alertDialogError.create();
                        alertDialog.show();
                    }
                });
            }

        }
    }


    public void showLoadingIndicator() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, R.style.MyTheme);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progressbar_drawable));
            progressDialog.show();
        }
    }

    public void hideLoadingIndicator() {

        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
                progressDialog = null;
            } catch (Exception e) {
            }
        }
    }

    protected abstract void onCreateFinished(Bundle savedInstanceState);

    protected abstract ToolbarBinding getToolBarBinding();


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_share_incident:
                NavigationHelper.getInstance().startChoosingActivityDirect(BaseActivity.this);

                break;
            case R.id.nav_report_incident:
                NavigationHelper.getInstance().startReportActivityDirect(BaseActivity.this);

                break;
            case R.id.nav_near_incident:

                break;
            case R.id.nav_all_incident:
                NavigationHelper.getInstance().startTotalEventActivityDirect(BaseActivity.this);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showToastMessage(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }


    /*public ClientPreferences getClientPreferences() {
        return FactApp.getInstance().getClientPreferences();
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
