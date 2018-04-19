package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivityChoosingBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

import java.util.HashMap;

public class ChoosingActivity extends BaseActivity implements View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private ActivityChoosingBinding binding;
    public String userEmail;

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
        binding.navView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        userEmail = extras.getString("userEmail");

        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Yol Çalışması", "http://d.yuksekovahaber.com.tr/news/224694.jpg");
        url_maps.put("Kaza", "http://ajansurfa.com/Images/Thumbs/News/38204-diyarbakir-sanliurfa-yolunda-kaza-cok-sayida-yarali-var-700x0.jpeg");
        url_maps.put("Hırsızlık", "http://i.hurimg.com/i/hurriyet/75/590x332/59cdef9fd3806c1b8cf3e6f3.jpg");
        url_maps.put("Cinayet", "http://www.demokratbakis.net/images/haberler/2017/10/magusa-da-cinayet.jpg");

        /*HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal", R.drawable.hannibal);
        file_maps.put("Big Bang Theory", R.drawable.bigbang);
        file_maps.put("House of Cards", R.drawable.house);
        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);*/

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            binding.contentChoosing.slider.addSlider(textSliderView);
        }
        binding.contentChoosing.slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        binding.contentChoosing.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        binding.contentChoosing.slider.setCustomAnimation(new DescriptionAnimation());
        binding.contentChoosing.slider.setDuration(4000);
        binding.contentChoosing.slider.addOnPageChangeListener(this);
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
                NavigationHelper.getInstance().startShareActivityDirect(ChoosingActivity.this,"YOL");
                break;
            case R.id.btnMurder:
                NavigationHelper.getInstance().startShareActivityDirect(ChoosingActivity.this,"KAZA");
                break;
            case R.id.btnRoadWork:
                NavigationHelper.getInstance().startShareActivityDirect(ChoosingActivity.this,"HIRSIZLIK");
                break;
            case R.id.btnRobbery:
                NavigationHelper.getInstance().startShareActivityDirect(ChoosingActivity.this,"CİNAYET");
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
