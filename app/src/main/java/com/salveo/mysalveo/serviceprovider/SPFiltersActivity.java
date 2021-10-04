package com.salveo.mysalveo.serviceprovider;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.petlover.SelectedServiceActivity;
import com.salveo.mysalveo.responsepojo.SPFilterPriceListResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SPFiltersActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SPFiltersActivity" ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

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



    private String selectedprice;
    private int reviewcount;
    private List<SPFilterPriceListResponse.DataBean> spFilterPriceList;
    private int Count_value_start;
    private int Count_value_end;
    private int distance;
    private String catid;



    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_filters);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            distance = extras.getInt("distance");
            catid = extras.getString("catid");
            selectedprice = extras.getString("selectedprice");
            reviewcount = extras.getInt("reviewcount");
            Log.w(TAG,"Bundle : "+" selectedprice : "+selectedprice+" reviewcount : "+reviewcount);

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

        img_back.setOnClickListener(this);
        btn_apply.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        rb_four_star.setOnClickListener(this);
        rb_three_star.setOnClickListener(this);
        rb_two_star.setOnClickListener(this);
        rb_one_star.setOnClickListener(this);



        if (new ConnectionDetector(SPFiltersActivity.this).isNetworkAvailable(SPFiltersActivity.this)) {
            SPFilterPriceListResponseCall();

        }

        rg_specialization.setOnCheckedChangeListener((group, checkedId) -> {
            if(rg_specialization != null) {
                int radioButtonID = rg_specialization.getCheckedRadioButtonId();
                RadioButton radioButton = rg_specialization.findViewById(radioButtonID);
                if (radioButton != null) {
                    selectedprice = (String) radioButton.getText();
                    Log.w(TAG, "selectedprice : " + selectedprice);
                }
            }



            if(spFilterPriceList != null && spFilterPriceList.size()>0){
               for(int i =0; i<spFilterPriceList.size();i++){
                   if (selectedprice != null && selectedprice.equalsIgnoreCase(spFilterPriceList.get(i).getDisplay_text())) {
                       Count_value_start = spFilterPriceList.get(i).getCount_value_start();
                       Count_value_end = spFilterPriceList.get(i).getCount_value_end();
                   }
                   }
               Log.w(TAG,"Count_value_start : "+Count_value_start+" Count_value_end : "+Count_value_end);


            }


        });



    }
    @SuppressLint("LogNotTimber")
    public void SPFilterPriceListResponseCall(){

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SPFilterPriceListResponse> call = apiInterface.SPFilterPriceListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SPFilterPriceListResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<SPFilterPriceListResponse> call, @NonNull Response<SPFilterPriceListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"DropDownListResponse" + new Gson().toJson(response.body()));

                        if(response.body().getData() != null) {
                            spFilterPriceList = response.body().getData();
                        }


                        Log.w(TAG,"spFilterPriceList : "+new Gson().toJson(spFilterPriceList));
                        if(spFilterPriceList != null && spFilterPriceList.size()>0){
                            rg_specialization.setVisibility(View.VISIBLE);
                            txt_no_records.setVisibility(View.GONE);
                            setPriceList(spFilterPriceList);
                        }else{
                            rg_specialization.setVisibility(View.GONE);
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No specialization found");

                        }

                    }

                }

            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SPFilterPriceListResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPFilterPriceListResponse flr"+t.getMessage());
            }
        });

    }

    private void setPriceList(List<SPFilterPriceListResponse.DataBean> spFilterPriceList) {
        for(int i=0; i<spFilterPriceList.size();i++){
            RadioButton rb = new RadioButton(getApplicationContext());
            rb.setText(spFilterPriceList.get(i).getDisplay_text());
            rg_specialization.addView(rb);

            if(selectedprice != null && !selectedprice.isEmpty()){
                if(selectedprice.equalsIgnoreCase(spFilterPriceList.get(i).getDisplay_text())) {
                    ((RadioButton) rg_specialization.getChildAt(i)).setChecked(true);
                }
            }

        }



    }

    @SuppressLint("LogNotTimber")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), SelectedServiceActivity.class);
        intent.putExtra("distance",distance);
        intent.putExtra("catid",catid);
        intent.putExtra("selectedprice",selectedprice);
        intent.putExtra("reviewcount",reviewcount);
        startActivity(intent);
        Log.w(TAG,"selectedprice : "+selectedprice);
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
                //  gotoPetCareClear();
                clearRadioChecked();
                rg_specialization.clearCheck();
                reviewcount = 0;
                selectedprice = "";
                Count_value_start = 0;
                Count_value_end = 0;

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
        Intent intent = new Intent(getApplicationContext(), SelectedServiceActivity.class);
        intent.putExtra("tag","3");
        intent.putExtra("reviewcount",reviewcount);
        intent.putExtra("selectedprice",selectedprice);
        intent.putExtra("Count_value_start",Count_value_start);
        intent.putExtra("Count_value_end",Count_value_end);
        intent.putExtra("distance",distance);
        intent.putExtra("catid",catid);
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