package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ListView;

import com.example.kaan.factapp.Adapter.TotalEventAdapter;
import com.example.kaan.factapp.Connection.RestControllerFactory;
import com.example.kaan.factapp.Dialog.DetailsDialog;
import com.example.kaan.factapp.Model.TotalEventModel;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ToolbarBinding;
import com.example.kaan.factapp.databinding.ActivityTotalEventBinding;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TotalEventActivity extends BaseActivity {

    private ActivityTotalEventBinding binding;
    private ArrayList<TotalEventModel> totalEventModels;

    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_total_event);
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
        showLoadingIndicator();
        RestControllerFactory.getInstance().getRegisterFactory().listAPI().enqueue(new Callback<ArrayList<TotalEventModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TotalEventModel>> call, Response<ArrayList<TotalEventModel>> response) {
                if (response.isSuccessful()) {
                    hideLoadingIndicator();
                    totalEventModels = response.body();

                    TotalEventAdapter adapter = new TotalEventAdapter(TotalEventActivity.this, totalEventModels);
                    binding.rvReportAll.setAdapter(adapter);
                    binding.rvReportAll.setLayoutManager(new LinearLayoutManager(TotalEventActivity.this, LinearLayoutManager.VERTICAL, false));

                    if (totalEventModels.size() == 0) {
                        binding.rvReportAll.setVisibility(View.GONE);
                        binding.tvInfo.setVisibility(View.GONE);
                    } else {
                        binding.rvReportAll.setVisibility(View.VISIBLE);
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
