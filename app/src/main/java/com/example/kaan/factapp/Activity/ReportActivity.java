package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.kaan.factapp.Adapter.TotalEventAdapter;
import com.example.kaan.factapp.Connection.RestControllerFactory;
import com.example.kaan.factapp.Model.Request.IncidentAllModel;
import com.example.kaan.factapp.Model.TotalEventModel;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivityReportBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kaan.factapp.FactApp.getClientPreferences;

public class ReportActivity extends BaseActivity {

    private ActivityReportBinding binding;
    private ArrayList<TotalEventModel> totalEventModels;
    private IncidentAllModel allModel;

    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_report);
        init();

    }

    @Override
    protected ToolbarBinding getToolBarBinding() {
        binding.toolbar.imgBtnToolbarMenu.setVisibility(View.GONE);
        binding.toolbar.imgBtnToolbarExit.setVisibility(View.GONE);
        binding.toolbar.imgBtnToolbarBack.setVisibility(View.VISIBLE);
        return binding.toolbar;
    }

    private void init() {
        getIncidentList();

    }

    private void getIncidentList() {
        allModel = new IncidentAllModel();
        allModel.setUserEmail(getClientPreferences().getUser().getEmail());
        showLoadingIndicator();
        RestControllerFactory.getInstance().getRegisterFactory().myListAPI(allModel).enqueue(new Callback<ArrayList<TotalEventModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TotalEventModel>> call, Response<ArrayList<TotalEventModel>> response) {
                if (response.isSuccessful()) {
                    hideLoadingIndicator();
                    totalEventModels = response.body();

                    TotalEventAdapter adapter = new TotalEventAdapter(ReportActivity.this, totalEventModels);
                    binding.rvReport.setAdapter(adapter);
                    binding.rvReport.setLayoutManager(new LinearLayoutManager(ReportActivity.this, LinearLayoutManager.VERTICAL, false));

                    if (totalEventModels.size() == 0) {
                        binding.rvReport.setVisibility(View.GONE);
                        binding.tvInfo.setVisibility(View.GONE);
                    } else {
                        binding.rvReport.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TotalEventModel>> call, Throwable t) {
                hideLoadingIndicator();
            }
        });
    }
}
