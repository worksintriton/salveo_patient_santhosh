package com.salveo.mysalveo.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.ProductsSearchAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.fragmentpetlover.bottommenu.PetCareFragment;
import com.salveo.mysalveo.fragmentpetlover.bottommenu.PetHomeFragment;
import com.salveo.mysalveo.fragmentpetlover.bottommenu.PetServicesFragment;
import com.salveo.mysalveo.fragmentpetlover.bottommenu.VendorShopFragment;
import com.salveo.mysalveo.requestpojo.ProductSearchRequest;
import com.salveo.mysalveo.responsepojo.ProductSearchResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "SearchActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_relatedproducts)
    RecyclerView rv_relatedproducts;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_search)
    EditText edt_search;


    private String searchString = "";
    private List<ProductSearchResponse.DataBean> productSearchResponseCall;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_footer)
    View include_petlover_footer;


    final Fragment petHomeFragment = new PetHomeFragment();
    final Fragment petCareFragment = new PetCareFragment();
    final Fragment petServicesFragment = new PetServicesFragment();
    final Fragment vendorShopFragment = new VendorShopFragment();

    public static String active_tag = "1";


    Fragment active = petHomeFragment;
    String tag;
    private String fromactivity;

    /* Petlover Bottom Navigation */
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_care)
    RelativeLayout rl_care;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_care)
    TextView title_care;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_care)
    ImageView img_care;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_service)
    RelativeLayout rl_service;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_serv)
    TextView title_serv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_serv)
    ImageView img_serv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_shop)
    RelativeLayout rl_shop;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_shop)
    TextView title_shop;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_shop)
    ImageView img_shop;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_comn)
    RelativeLayout rl_comn;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_community)
    TextView title_community;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_community)
    ImageView img_community;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_homes)
    RelativeLayout rl_homes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        img_back.setOnClickListener(this);
        avi_indicator.setVisibility(View.GONE);





        /*home*/
        title_care.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_care.setImageResource(R.drawable.grey_care);
        title_serv.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_serv.setImageResource(R.drawable.grey_servc);
        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);


        rl_home.setOnClickListener(this);
        rl_care.setOnClickListener(this);
        rl_service.setOnClickListener(this);
        rl_shop.setOnClickListener(this);
        rl_comn.setOnClickListener(this);
        rl_homes.setOnClickListener(this);

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            productSearchResponseCall(searchString);
        }

        edt_search.addTextChangedListener(new TextWatcher() {
            @SuppressLint("LogNotTimber")
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.w(TAG,"beforeTextChanged-->"+s.toString());
            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.w(TAG,"onTextChanged-->"+s.toString());
                searchString = s.toString();


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void afterTextChanged(Editable s) {
                Log.w(TAG,"afterTextChanged-->"+s.toString());
                searchString = s.toString();
                if(!searchString.isEmpty()){
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        productSearchResponseCall(searchString);
                    }


                }else{
                    searchString ="";
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        productSearchResponseCall(searchString);
                    }

                }

            }
        });



    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.rl_homes:
                callDirections("1");
                break;
            case R.id.rl_home:
                callDirections("1");
                break;
            case R.id.rl_shop:
                callDirections("2");
                break;
            case R.id.rl_service:
                callDirections("3");
                break;
            case R.id.rl_care:
                callDirections("4");
                break;
            case R.id.rl_comn:
                callDirections("5");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        callDirections("2");
    }

    @SuppressLint("LogNotTimber")
    private void productSearchResponseCall(String searchString) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ProductSearchResponse> call = apiInterface.productSearchResponseCall(RestUtils.getContentType(), productSearchRequest(searchString));
        Log.w(TAG,"DoctorSearchResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ProductSearchResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<ProductSearchResponse> call, @NonNull Response<ProductSearchResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"DoctorSearchResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {


                        if (response.body().getData() != null) {
                            productSearchResponseCall = response.body().getData();
                            Log.w(TAG, "productSearchResponseCall Size" + productSearchResponseCall.size());
                            if (productSearchResponseCall != null && productSearchResponseCall.size()>0) {
                                rv_relatedproducts.setVisibility(View.VISIBLE);
                                txt_no_records.setVisibility(View.GONE);
                                setViewProducts(productSearchResponseCall);
                            } else {
                                rv_relatedproducts.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No products available");

                            }

                        }



                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<ProductSearchResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("ProductSearchResponse flr", "--->" + t.getMessage());
            }
        });

    }
    private void setViewProducts(List<ProductSearchResponse.DataBean> productSearchResponseCall) {
        rv_relatedproducts.setLayoutManager(new GridLayoutManager(this, 2));
        rv_relatedproducts.setItemAnimator(new DefaultItemAnimator());
        ProductsSearchAdapter productsSearchAdapter = new ProductsSearchAdapter(getApplicationContext(), productSearchResponseCall,TAG,"");
        rv_relatedproducts.setAdapter(productsSearchAdapter);
    }
    private ProductSearchRequest productSearchRequest(String searchString) {
        /*
         * search_string :
        */
        ProductSearchRequest productSearchRequest = new ProductSearchRequest();
        productSearchRequest.setSearch_string(searchString);
        Log.w(TAG,"productSearchRequest"+ new Gson().toJson(productSearchRequest));
        return productSearchRequest;
    }

    public void callDirections(String tag){
        Intent intent = new Intent(SearchActivity.this,PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }


    private void setMargins(RelativeLayout rl_layout, int i, int i1, int i2, int i3) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rl_layout.getLayoutParams();
        params.setMargins(i, i1, i2, i3);
        rl_layout.setLayoutParams(params);
    }
}