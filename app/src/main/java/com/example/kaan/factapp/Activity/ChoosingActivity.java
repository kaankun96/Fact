package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.kaan.factapp.Activity.BaseActivity;
import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivityChoosingBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

public class ChoosingActivity extends BaseActivity implements View.OnClickListener {

    private ActivityChoosingBinding binding;

    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choosing);
        init();

    }

    private void init() {
        binding.contentChoosing.btnAccident.setOnClickListener(this);
        binding.contentChoosing.btnMurder.setOnClickListener(this);
        binding.contentChoosing.btnRoadWork.setOnClickListener(this);
        binding.contentChoosing.btnRobbery.setOnClickListener(this);
    }


    @Override
    protected ToolbarBinding getToolBarBinding() {
        binding.contentChoosing.toolbar.imgBtnToolbarMenu.setVisibility(View.VISIBLE);
        return binding.contentChoosing.toolbar;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAccident:
                NavigationHelper.getInstance().startChoosingToShareActivityDirect(ChoosingActivity.this);
                break;
            case R.id.btnMurder:
                NavigationHelper.getInstance().startChoosingToShareActivityDirect(ChoosingActivity.this);
                break;
            case R.id.btnRoadWork:
                NavigationHelper.getInstance().startChoosingToShareActivityDirect(ChoosingActivity.this);
                break;
            case R.id.btnRobbery:
                NavigationHelper.getInstance().startChoosingToShareActivityDirect(ChoosingActivity.this);
                break;


        }
    }
}
