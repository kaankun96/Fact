package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivityWelcomeBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

import java.util.HashMap;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        init();
    }

    private void init() {
        binding.btnCreateAccount.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("","KAAN");
        url_maps.put("", "KAAN2");
        url_maps.put("", "KAAN3");
        url_maps.put("", "KAAN4");


        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            binding.tvSlider.addSlider(textSliderView);

        }
        binding.tvSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        binding.tvSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        binding.tvSlider.setCustomAnimation(new DescriptionAnimation());
        binding.tvSlider.setDuration(4000);
        binding.tvSlider.addOnPageChangeListener(this);
    }


    @Override
    protected ToolbarBinding getToolBarBinding() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreateAccount:
                NavigationHelper.getInstance().startSignActivityDirect(WelcomeActivity.this);
                break;
            case R.id.btnLogin:
                NavigationHelper.getInstance().startLoginActivityDirect(WelcomeActivity.this);
                break;
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
