package com.salveo.mysalveo.petlover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.BreedTypeRequest;
import com.salveo.mysalveo.responsepojo.BreedTypeResponse;
import com.salveo.mysalveo.responsepojo.PetTypeListResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFiltersActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ProductFiltersActivity" ;

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
    @BindView(R.id.rg_pettype)
    RadioGroup rg_pettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_petbreedtype)
    RadioGroup rg_petbreedtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_clear)
    Button btn_clear;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_apply)
    Button btn_apply;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_discount)
    RadioGroup rg_discount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_discount30)
    RadioButton rb_discount30;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_discount20)
    RadioButton rb_discount20;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_discount10)
    RadioButton rb_discount10;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_discount10below)
    RadioButton rb_discount10below;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_pettype)
    LinearLayout ll_pettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_petbreedtype)
    LinearLayout ll_petbreedtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_discount)
    LinearLayout ll_discount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_category)
    LinearLayout ll_category;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_pettype)
    ImageView img_pettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_petbreedtype)
    ImageView img_petbreedtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_discount)
    ImageView img_discount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_category)
    ImageView img_category;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_categorytype)
    RadioGroup rg_categorytype;


    private String strPetType = "";
    private String petTypeId = "";
    private String strPetBreedType = "";
    private String petBreedTypeId = "";
    private String strCategoryType = "";
    private String strCategoryTypeId = "";

    private List<PetTypeListResponse.DataBean.UsertypedataBean> petTypeList;
    private final HashMap<String, String> hashMap_PetTypeid = new HashMap<>();
    private final HashMap<String, String> hashMap_CategoryTypeid = new HashMap<>();
    private final HashMap<String, String> hashMap_PetBreedTypeid = new HashMap<>();
    private String discount_value = "";

    private String fromactivity;
    private String cat_id;
    private boolean flag;



    @SuppressLint({"LogNotTimber", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_filters);
        ButterKnife.bind(this);

        img_back.setOnClickListener(this);
        btn_apply.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        rb_discount30.setOnClickListener(this);
        rb_discount20.setOnClickListener(this);
        rb_discount10.setOnClickListener(this);
        rb_discount10below.setOnClickListener(this);


        rg_pettype.setVisibility(View.GONE);
        rg_petbreedtype.setVisibility(View.GONE);
        rg_discount.setVisibility(View.GONE);
        rg_categorytype.setVisibility(View.GONE);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");
            cat_id = extras.getString("cat_id");
            Log.w(TAG,"fromactivity : "+fromactivity);

        }

        if (new ConnectionDetector(ProductFiltersActivity.this).isNetworkAvailable(ProductFiltersActivity.this)) {
            petTypeListResponseCall();

        }
        rg_pettype.setOnCheckedChangeListener((group, checkedId) -> {
            if(rg_pettype != null) {
                int radioButtonID = rg_pettype.getCheckedRadioButtonId();
                RadioButton radioButton = rg_pettype.findViewById(radioButtonID);
                if (radioButton != null) {
                    strPetType = (String) radioButton.getText();

                    petTypeId = hashMap_PetTypeid.get(strPetType);

                    Log.w(TAG, "selected pettype : " + strPetType+" petTypeId : "+petTypeId);

                    if(petTypeId != null && !petTypeId.isEmpty()) {
                        rg_petbreedtype.setVisibility(View.VISIBLE);
                        breedTypeResponseByPetIdCall(petTypeId);
                    }
                }
            }



        });
        rg_petbreedtype.setOnCheckedChangeListener((group, checkedId) -> {
            if(rg_petbreedtype != null) {
                int radioButtonID = rg_petbreedtype.getCheckedRadioButtonId();
                RadioButton radioButton = rg_petbreedtype.findViewById(radioButtonID);
                if (radioButton != null) {
                    strPetBreedType = (String) radioButton.getText();
                    petBreedTypeId = hashMap_PetBreedTypeid.get(strPetBreedType);
                    Log.w(TAG, "selected strPetBreedType : " + strPetBreedType+" petBreedTypeId : "+petBreedTypeId);
                }
            }



        });
        rg_categorytype.setOnCheckedChangeListener((group, checkedId) -> {
            if(rg_categorytype != null) {
                int radioButtonID = rg_pettype.getCheckedRadioButtonId();
                RadioButton radioButton = rg_pettype.findViewById(radioButtonID);
                if (radioButton != null) {
                    strCategoryType = (String) radioButton.getText();
                    strCategoryTypeId = hashMap_CategoryTypeid.get(strCategoryType);
                    Log.w(TAG, "selected strCategoryType : " + strCategoryType+" strCategoryTypeId : "+strCategoryTypeId);


                }
            }



        });

        ll_pettype.setOnClickListener(v -> {
            Log.w(TAG,"flag : "+flag);

            if (!flag) {
                rg_pettype.setVisibility(View.VISIBLE);
                img_pettype.setImageResource(R.drawable.ic_down);
                flag=true;
            }
            else {
                rg_pettype.setVisibility(View.GONE);
                img_pettype.setImageResource(R.drawable.ic_up);
                flag=false;
            }
        });
        ll_petbreedtype.setOnClickListener(v -> {
            Log.w(TAG,"flag : "+flag);

            if (!flag) {
                rg_petbreedtype.setVisibility(View.VISIBLE);
                img_petbreedtype.setImageResource(R.drawable.ic_down);
                flag=true;
            }
            else {
                rg_petbreedtype.setVisibility(View.GONE);
                img_petbreedtype.setImageResource(R.drawable.ic_up);
                flag=false;
            }
        });
        ll_discount.setOnClickListener(v -> {
            Log.w(TAG,"flag : "+flag);

            if (!flag) {
                rg_discount.setVisibility(View.VISIBLE);
                img_discount.setImageResource(R.drawable.ic_down);
                flag=true;
            }
            else {
                rg_discount.setVisibility(View.GONE);
                img_discount.setImageResource(R.drawable.ic_up);
                flag=false;
            }
        });
        ll_category.setOnClickListener(v -> {
            Log.w(TAG,"flag : "+flag);

            if (!flag) {
                rg_categorytype.setVisibility(View.VISIBLE);
                img_category.setImageResource(R.drawable.ic_down);
                flag=true;
            }
            else {
                rg_categorytype.setVisibility(View.GONE);
                img_category.setImageResource(R.drawable.ic_up);
                flag=false;
            }
        });



    }
    @SuppressLint("LogNotTimber")
    public void petTypeListResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<PetTypeListResponse> call = apiInterface.petTypeListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<PetTypeListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<PetTypeListResponse> call, @NonNull Response<PetTypeListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"petTypeListResponseCall" + new Gson().toJson(response.body()));
                        if(response.body().getData().getUsertypedata() != null) {
                            petTypeList = response.body().getData().getUsertypedata();
                        }

                        if(response.body().getData().getProduct_categories() != null && response.body().getData().getProduct_categories().size()>0){
                            setCategoryTypeList(response.body().getData().getProduct_categories());
                        }


                        Log.w(TAG,"petTypeList : "+new Gson().toJson(petTypeList));
                        if(petTypeList != null && petTypeList.size()>0){
                            txt_no_records.setVisibility(View.GONE);
                            setPetTypeList(petTypeList);
                        }else{
                            rg_pettype.setVisibility(View.GONE);
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No pet types found");

                        }

                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<PetTypeListResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"petTypeListResponseCall flr"+t.getMessage());
            }
        });

    }
    private void setCategoryTypeList(List<PetTypeListResponse.DataBean.ProductCategoriesBean> product_categories) {
        for(int i = 0; i<product_categories.size(); i++){
            RadioButton rb = new RadioButton(getApplicationContext());
            rb.setText(product_categories.get(i).getProduct_cate());
            rg_categorytype.addView(rb);
            hashMap_CategoryTypeid.put(product_categories.get(i).getProduct_cate(), product_categories.get(i).get_id());
        }
    }
    private void setPetTypeList(List<PetTypeListResponse.DataBean.UsertypedataBean> petTypeList) {
        for(int i = 0; i<petTypeList.size(); i++){
            RadioButton rb = new RadioButton(getApplicationContext());
            rb.setText(this.petTypeList.get(i).getPet_type_title());
            rg_pettype.addView(rb);
            hashMap_PetTypeid.put(petTypeList.get(i).getPet_type_title(), petTypeList.get(i).get_id());
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
                if(fromactivity != null && fromactivity.equalsIgnoreCase("PetShopTodayDealsSeeMoreActivity")){
                    gotoPetShopTodayDealsSeeMoreActivity();
                }
                else if(fromactivity != null && fromactivity.equalsIgnoreCase("ListOfProductsSeeMoreActivity")){
                    gotoListOfProductsSeeMoreActivity();
                }
                break;

            case R.id.btn_clear:
                clearRadioChecked();
                rg_pettype.clearCheck();
                rg_petbreedtype.clearCheck();
                rg_petbreedtype.removeAllViews();
                rg_petbreedtype.setVisibility(View.GONE);
                strPetType = "";
                strPetBreedType ="";
                petBreedTypeId = "";
                petTypeId = "";
                discount_value ="";
                break;

            case R.id.rb_discount30:
                discount_value = "3";
                rb_discount30.setChecked(true);
                rb_discount30.setBackgroundResource(R.drawable.checked);
                rb_discount20.setBackgroundResource(R.drawable.uncheck);
                rb_discount10.setBackgroundResource(R.drawable.uncheck);
                rb_discount10below.setBackgroundResource(R.drawable.uncheck);
                break;

                case R.id.rb_discount20:
                    discount_value = "2";
                    rb_discount20.setChecked(true);
                    rb_discount30.setBackgroundResource(R.drawable.uncheck);
                    rb_discount20.setBackgroundResource(R.drawable.checked);
                    rb_discount10.setBackgroundResource(R.drawable.uncheck);
                    rb_discount10below.setBackgroundResource(R.drawable.uncheck);

                    break;

                case R.id.rb_discount10:
                    discount_value = "1";
                    rb_discount10.setChecked(true);
                    rb_discount30.setBackgroundResource(R.drawable.uncheck);
                    rb_discount20.setBackgroundResource(R.drawable.uncheck);
                    rb_discount10.setBackgroundResource(R.drawable.checked);
                    rb_discount10below.setBackgroundResource(R.drawable.uncheck);
                break;

                case R.id.rb_discount10below:
                    discount_value = "0";
                    rb_discount10below.setChecked(true);
                    rb_discount30.setBackgroundResource(R.drawable.uncheck);
                    rb_discount20.setBackgroundResource(R.drawable.uncheck);
                    rb_discount10.setBackgroundResource(R.drawable.uncheck);
                    rb_discount10below.setBackgroundResource(R.drawable.checked);
                break;




         }

    }

    @SuppressLint("LogNotTimber")
    private void gotoPetShopTodayDealsSeeMoreActivity() {
        Log.w(TAG,"petTypeId : "+petTypeId+" petBreedTypeId : "+petBreedTypeId+" discount_value : "+discount_value);
        Intent intent = new Intent(getApplicationContext(), PetShopTodayDealsSeeMoreActivity.class);
        intent.putExtra("tag","2");
        intent.putExtra("petTypeId",petTypeId);
        intent.putExtra("petBreedTypeId",petBreedTypeId);
        intent.putExtra("discount_value",discount_value);
        intent.putExtra("strCategoryTypeId",strCategoryTypeId);
        intent.putExtra("fromactivity",TAG);
        startActivity(intent);
    }
    @SuppressLint("LogNotTimber")
    private void gotoListOfProductsSeeMoreActivity() {
        Log.w(TAG,"petTypeId : "+petTypeId+" petBreedTypeId : "+petBreedTypeId+" discount_value : "+discount_value);
        Intent intent = new Intent(getApplicationContext(), ListOfProductsSeeMoreActivity.class);
        intent.putExtra("tag","2");
        intent.putExtra("petTypeId",petTypeId);
        intent.putExtra("petBreedTypeId",petBreedTypeId);
        intent.putExtra("discount_value",discount_value);
        intent.putExtra("fromactivity",TAG);
        intent.putExtra("cat_id",cat_id);
        intent.putExtra("strCategoryTypeId",strCategoryTypeId);
        startActivity(intent);
    }

    public void clearRadioChecked() {
        rb_discount30.setChecked(false);
        rb_discount20.setChecked(false);
        rb_discount10.setChecked(false);
        rb_discount10below.setChecked(false);

        rb_discount30.setBackgroundResource(R.drawable.uncheck);
        rb_discount20.setBackgroundResource(R.drawable.uncheck);
        rb_discount10.setBackgroundResource(R.drawable.uncheck);
        rb_discount10below.setBackgroundResource(R.drawable.uncheck);

    }


    @SuppressLint("LogNotTimber")
    private void breedTypeResponseByPetIdCall(String petTypeId) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<BreedTypeResponse> call = ApiService.breedTypeResponseByPetIdCall(RestUtils.getContentType(),breedTypeRequest(petTypeId));
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<BreedTypeResponse>() {
            @Override
            public void onResponse(@NonNull Call<BreedTypeResponse> call, @NonNull Response<BreedTypeResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "BreedTypeResponse" + "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if (200 == response.body().getCode()) {

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            rg_petbreedtype.removeAllViews();
                            setBreedType(response.body().getData());
                        }

                    }

                }

            }



            @Override
            public void onFailure(@NonNull Call<BreedTypeResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"BreedTypeResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    private void setBreedType(List<BreedTypeResponse.DataBean> breedTypedataBeanList) {
        for(int i = 0; i<breedTypedataBeanList.size(); i++){
            RadioButton rb = new RadioButton(getApplicationContext());
            rb.setText(breedTypedataBeanList.get(i).getPet_breed());
            rg_petbreedtype.addView(rb);
            hashMap_PetBreedTypeid.put(breedTypedataBeanList.get(i).getPet_breed(), breedTypedataBeanList.get(i).getPet_type_id());

        }

    }
    @SuppressLint("LogNotTimber")
    private BreedTypeRequest breedTypeRequest(String petTypeId) {
        BreedTypeRequest breedTypeRequest = new BreedTypeRequest();
        breedTypeRequest.setPet_type_id(petTypeId);
        Log.w(TAG,"breedTypeRequest"+ "--->" + new Gson().toJson(breedTypeRequest));
        return breedTypeRequest;
    }


}