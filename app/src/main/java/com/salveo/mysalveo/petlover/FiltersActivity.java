package com.salveo.mysalveo.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.responsepojo.DropDownListResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltersActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FiltersActivity" ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;
    private List<DropDownListResponse.DataBean.SpecialzationBean> petSpecilaziationList;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_specialization)
    RadioGroup rg_specialization;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_clear)
    Button btn_clear;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_apply)
    Button btn_apply;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_review)
    RadioGroup rg_review;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_four_star)
    RadioButton rb_four_star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_three_star)
    RadioButton rb_three_star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_two_star)
    RadioButton rb_two_star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_one_star)
    RadioButton rb_one_star;



    private String specialization;
    private int reviewcount;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        ButterKnife.bind(this);

        img_back.setOnClickListener(this);
        btn_apply.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        rb_four_star.setOnClickListener(this);
        rb_three_star.setOnClickListener(this);
        rb_two_star.setOnClickListener(this);
        rb_one_star.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            specialization = extras.getString("specialization");
            reviewcount = extras.getInt("reviewcount");
            Log.w(TAG,"Bundle : "+" specialization : "+specialization+" reviewcount : "+reviewcount);

            if(reviewcount != 0){
                if (reviewcount == 1) {
                    rb_one_star.setChecked(true);
                }else if (reviewcount == 2) {
                    rb_two_star.setChecked(true);
                }else if (reviewcount == 3) {
                    rb_three_star.setChecked(true);
                }else if (reviewcount == 4) {
                    rb_four_star.setChecked(true);
                }

            }

        }



        if (new ConnectionDetector(FiltersActivity.this).isNetworkAvailable(FiltersActivity.this)) {
            dropDownListResponseCall();

        }

        rg_specialization.setOnCheckedChangeListener((group, checkedId) -> {
            if(rg_specialization != null) {
                int radioButtonID = rg_specialization.getCheckedRadioButtonId();
                RadioButton radioButton = rg_specialization.findViewById(radioButtonID);
                if (radioButton != null) {
                    specialization = (String) radioButton.getText();
                    Log.w(TAG, "selectedspecialization : " + specialization);
                }
            }



        });


    }
    @SuppressLint("LogNotTimber")
    public void dropDownListResponseCall(){

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<DropDownListResponse> call = apiInterface.dropDownListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<DropDownListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<DropDownListResponse> call, @NonNull Response<DropDownListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"DropDownListResponse" + new Gson().toJson(response.body()));
                        if(response.body().getData().getSpecialzation() != null) {
                            petSpecilaziationList = response.body().getData().getSpecialzation();
                        }


                        Log.w(TAG,"petSpecilaziationList : "+new Gson().toJson(petSpecilaziationList));
                        if(petSpecilaziationList != null && petSpecilaziationList.size()>0){
                            rg_specialization.setVisibility(View.VISIBLE);
                            txt_no_records.setVisibility(View.GONE);
                            setSpecList(petSpecilaziationList);
                        }else{
                            rg_specialization.setVisibility(View.GONE);
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No specialization found");

                        }

                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<DropDownListResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"DropDownListResponse flr"+t.getMessage());
            }
        });

    }

    private void setSpecList(List<DropDownListResponse.DataBean.SpecialzationBean> petSpecilaziationList) {
        for(int i=0; i<petSpecilaziationList.size();i++){
            RadioButton rb = new RadioButton(getApplicationContext());
            rb.setText(petSpecilaziationList.get(i).getSpecialzation());
            rg_specialization.addView(rb);

            if(specialization != null && !specialization.isEmpty()){
                if(specialization.equalsIgnoreCase(petSpecilaziationList.get(i).getSpecialzation())) {
                    ((RadioButton) rg_specialization.getChildAt(i)).setChecked(true);
                }
            }

        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;

            case  R.id.btn_apply:
                gotoPetCare();
                break;

            case R.id.btn_clear:
                clearRadioChecked();
                rg_specialization.clearCheck();
                specialization = "";
                reviewcount = 0;
                break;

            case R.id.rb_four_star:
                reviewcount = 4;
                clearRadioChecked();
                rb_four_star.setChecked(true);
                break;

                case R.id.rb_three_star:
                    reviewcount = 3;
                    clearRadioChecked();
                    rb_three_star.setChecked(true);
                break;

                case R.id.rb_two_star:
                    reviewcount = 2;
                    clearRadioChecked();
                    rb_two_star.setChecked(true);
                break;

                case R.id.rb_one_star:
                    reviewcount = 1;
                    clearRadioChecked();
                    rb_one_star.setChecked(true);
                break;

        }

    }

    private void gotoPetCare() {
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag","4");
        intent.putExtra("reviewcount",reviewcount);
        intent.putExtra("specialization",specialization);
        intent.putExtra("fromactivity",TAG);
        startActivity(intent);
    }

    public void clearRadioChecked() {
        rb_four_star.setChecked(false);
        rb_three_star.setChecked(false);
        rb_two_star.setChecked(false);
        rb_one_star.setChecked(false);
    }


}