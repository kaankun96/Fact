package com.example.kaan.factapp.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaan.factapp.Connection.RestControllerFactory;
import com.example.kaan.factapp.Model.Response.UpdateIncidentModel;
import com.example.kaan.factapp.Model.TotalEventModel;
import com.example.kaan.factapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsDialog extends BaseDialog {
    TotalEventModel totalEventModel;
    ImageView ivPhoto;
    TextView tvIncidentName, tvIncidentLocation, tvIncidentInformers, tvIncidentDate;
    Button btnConfirm;

    public DetailsDialog(Context context) {
        super(context);
    }


    public DetailsDialog(Context context, TotalEventModel totalEventModel) {
        super(context);
        this.totalEventModel = totalEventModel;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.details_dialog;
    }

    @Override
    protected void onCreateFinished(Bundle savedInstance) {

        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        Picasso.with(getContext()).load(totalEventModel.getUrl()).into(ivPhoto);


        tvIncidentName = (TextView) findViewById(R.id.tvIncidentName);
        tvIncidentName.setText(totalEventModel.getName());

        tvIncidentLocation = (TextView) findViewById(R.id.tvIncidentLocation);
        tvIncidentLocation.setText(totalEventModel.getLocation());

        tvIncidentInformers = (TextView) findViewById(R.id.tvIncidentInformers);
        tvIncidentInformers.setText(totalEventModel.getUserName() + " " + totalEventModel.getUserLastName());

        tvIncidentDate = (TextView) findViewById(R.id.tvIncidentDate);
        tvIncidentDate.setText(totalEventModel.getDateTimes());

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResult();
            }
        });
    }

    public void updateResult() {
        showLoadingIndicator();
        RestControllerFactory.getInstance().getRegisterFactory().updateListAPI(totalEventModel.getId(), "YES").enqueue(new Callback<UpdateIncidentModel>() {
            @Override
            public void onResponse(Call<UpdateIncidentModel> call, Response<UpdateIncidentModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hideLoadingIndicator();
                    dismiss();
                }
            }

            @Override
            public void onFailure(Call<UpdateIncidentModel> call, Throwable t) {
                hideLoadingIndicator();
                dismiss();
            }
        });
    }
}
