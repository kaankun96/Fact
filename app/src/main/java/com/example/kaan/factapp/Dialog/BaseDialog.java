package com.example.kaan.factapp.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.kaan.factapp.R;

public abstract class BaseDialog extends Dialog {
    private ProgressDialog progressDialog;

    public BaseDialog(Context context) {
        super(context);
    }

    protected abstract int getContentViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        setContentView(getContentViewId());
        onCreateFinished(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void showAlertDialogErrorMessage(String message) {
        AlertDialog.Builder alertDialogError = new AlertDialog.Builder(getContext());
        alertDialogError.setTitle("DİKKAT");
        alertDialogError.setMessage(message);
        alertDialogError.setIcon(R.drawable.ic_error_red);
        alertDialogError.setCancelable(false);
        alertDialogError.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogError.create();
        alertDialog.show();
    }

    public void showAlertDialogSuccessMessage(String message) {
        AlertDialog.Builder alertDialogError = new AlertDialog.Builder(getContext());
        alertDialogError.setTitle("Başarılı");
        alertDialogError.setMessage(message);
        alertDialogError.setIcon(R.drawable.ic_check_green);
        alertDialogError.setCancelable(false);
        alertDialogError.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogError.create();
        alertDialog.show();
    }


    protected abstract void onCreateFinished(Bundle savedInstance);


    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showLoadingIndicator() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext(), R.style.MyTheme);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminateDrawable(getContext().getResources().getDrawable(R.drawable.progressbar_drawable));
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
}
