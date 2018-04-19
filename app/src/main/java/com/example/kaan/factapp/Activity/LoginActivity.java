package com.example.kaan.factapp.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.kaan.factapp.Connection.RestControllerFactory;
import com.example.kaan.factapp.Helper.NavigationHelper;
import com.example.kaan.factapp.Model.Request.LoginRequest;
import com.example.kaan.factapp.Model.Response.LoginResponse;
import com.example.kaan.factapp.R;
import com.example.kaan.factapp.databinding.ActivityLoginBinding;
import com.example.kaan.factapp.databinding.ToolbarBinding;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kaan.factapp.FactApp.getClientPreferences;

public class LoginActivity extends BaseActivity implements View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private ActivityLoginBinding binding;
    public LoginRequest loginRequest;


    @Override
    protected void onCreateFinished(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        init();


    }

    private void init() {
        binding.btnLogin.setOnClickListener(this);
        if (getClientPreferences().getUser() != null) {
            NavigationHelper.getInstance().startChoosingActivityDirect(LoginActivity.this);
            showToastMessage("Kayıtlı kullanıcıyla giriş yapıldı.");
            finish();
        }

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

            binding.slider.addSlider(textSliderView);
        }
        binding.slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        binding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        binding.slider.setCustomAnimation(new DescriptionAnimation());
        binding.slider.setDuration(4000);
        binding.slider.addOnPageChangeListener(this);
    }

    @Override
    protected ToolbarBinding getToolBarBinding() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                loginAPI();
                break;
        }
    }

    public void loginAPI() {
        showLoadingIndicator();
        RestControllerFactory.getInstance().getRegisterFactory().loginAPI(binding.etMailLogin.getText().toString(), binding.etPasswordLogin.getText().toString()).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()&& response.body()!=null&& response.body().isLoginSuccess()){
                    login(response);
                }else if (response.isSuccessful() && response.body() != null && !response.body().isLoginSuccess()) {
                    showToastMessage(response.body().getResponse());
                } else {
                    showToastMessage("Giriş Başarısız.");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                hideLoadingIndicator();
                Toast.makeText(LoginActivity.this, "Birşeyler Ters Gitti...", Toast.LENGTH_LONG).show();

            }
        });
    }
    private void login(Response<LoginResponse> response) {
        hideLoadingIndicator();
        getClientPreferences().setLoggedIn(true);
        getClientPreferences().setUser(response.body());
        getClientPreferences().setUserNameAndPassword(binding.etMailLogin.getText().toString().replace("999999", ""), binding.etPasswordLogin.getText().toString());

        NavigationHelper.getInstance().startChoosingActivityDirect(LoginActivity.this,binding.etMailLogin.getText().toString());
        finish();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
