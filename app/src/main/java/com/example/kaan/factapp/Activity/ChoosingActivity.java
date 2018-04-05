package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.kaan.factapp.Activity.BaseActivity;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivityChoosingBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

public class ChoosingActivity extends BaseActivity {

    private ActivityChoosingBinding binding;

    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choosing);


    }

    @Override
    protected ToolbarBinding getToolBarBinding() {
        binding.contentChoosing.toolbar.imgBtnToolbarMenu.setVisibility(View.VISIBLE);
        return binding.contentChoosing.toolbar;
    }
}
