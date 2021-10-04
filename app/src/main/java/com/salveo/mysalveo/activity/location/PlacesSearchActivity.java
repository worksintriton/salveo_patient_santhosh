package com.salveo.mysalveo.activity.location;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.PlacesResultsAdapter;
import com.salveo.mysalveo.api.API;
import com.salveo.mysalveo.interfaces.PlacesNameListener;
import com.salveo.mysalveo.responsepojo.AddressResultsResponse;
import com.salveo.mysalveo.responsepojo.CartDetailsResponse;
import com.salveo.mysalveo.responsepojo.PlacesResultsResponse;
import com.salveo.mysalveo.serviceprovider.PickUpLocationSPActivity;
import com.wang.avi.AVLoadingIndicatorView;


import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacesSearchActivity extends AppCompatActivity implements PlacesNameListener {

    String TAG = "PlacesSearchActivity";


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_placessearch)
    EditText edtPlacesSearch;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.imgBack)
    ImageView imgBack;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_close)
    ImageView img_close;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_placesresults)
    RecyclerView rv_placesresults;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_norecords)
    TextView tv_norecords;

    private Context mContext;

    private List<PlacesResultsResponse.PredictionsBean> predictionsBeanList;

    String placesresults = "";
    private String fromactivity;

    private String id,userid,locationnickname,LocationType;
    private boolean defaultstatus;

    List<CartDetailsResponse.DataBean> Data = new ArrayList<>();
    private int prodouct_total;
    private int shipping_charge;
    private int discount_price;
    private int grand_total;
    private int prodcut_count;
    private int prodcut_item_count;

    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_search);
        Log.w(TAG,"onCreate--->");


        ButterKnife.bind(this);
        mContext = PlacesSearchActivity.this;


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");

            Log.w(TAG,"fromactivity if : "+fromactivity);

            id = extras.getString("id");
            userid = extras.getString("userid");
            locationnickname = extras.getString("nickname");
            LocationType = extras.getString("locationtype");
            defaultstatus = extras.getBoolean("defaultstatus");

            Data = (List<CartDetailsResponse.DataBean>) extras.getSerializable("data");
            prodouct_total = extras.getInt("product_total");
            shipping_charge = extras.getInt("shipping_charge");
            discount_price = extras.getInt("discount_price");
            grand_total = extras.getInt("grand_total");
            prodcut_count = extras.getInt("prodcut_count");
            prodcut_item_count = extras.getInt("prodcut_item_count");

        }


        edtPlacesSearch.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.showSoftInput(edtPlacesSearch, InputMethodManager.SHOW_IMPLICIT);

        avi_indicator.setVisibility(View.GONE);
        img_close.setVisibility(View.GONE);
        imgBack.setOnClickListener(v -> onBackPressed());




        img_close.setOnClickListener(v -> {

            if(placesresults != null && !placesresults.isEmpty()){
                edtPlacesSearch.setText("");
                img_close.setVisibility(View.GONE);
            }

        });



        edtPlacesSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Log.w(TAG,"beforeTextChanged-->"+s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.w(TAG,"onTextChanged-->"+s.toString());
                placesresults = s.toString();
                placesSearchResponseCall(s.toString());
                if(placesresults != null && !placesresults.isEmpty()){
                    img_close.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.w(TAG,"afterTextChanged-->"+s.toString());
                //  placesSearchResponseCall(s.toString());

            }
        });



    }

    private void placesSearchResponseCall(String places) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API service = retrofit.create(API.class);

        String key = API.MAP_KEY;
        service.getCityResults(places, key).enqueue(new Callback<PlacesResultsResponse>() {
            @Override
            public void onResponse(@NotNull Call<PlacesResultsResponse> call, @NotNull Response<PlacesResultsResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"url  :%s"+ call.request().url().toString());


                Log.w(TAG,"placesSearchResponseCall" + new Gson().toJson(response.body()));


                assert response.body() != null;
                predictionsBeanList = response.body().getPredictions();
                if (predictionsBeanList.size() > 0 ) {
                    rv_placesresults.setVisibility(View.VISIBLE);
                    tv_norecords.setVisibility(View.GONE);
                    setViewPlacesResulsts();
                }else{
                    rv_placesresults.setVisibility(View.GONE);
                    tv_norecords.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(@NotNull Call<PlacesResultsResponse> call, @NotNull Throwable t) {
                avi_indicator.smoothToHide();
                t.printStackTrace();
            }
        });
    }

    private void setViewPlacesResulsts() {
        rv_placesresults.setLayoutManager(new LinearLayoutManager(this));
        rv_placesresults.setItemAnimator(new DefaultItemAnimator());
        PlacesResultsAdapter placesResultsAdapter = new PlacesResultsAdapter(mContext, predictionsBeanList,PlacesSearchActivity.this,fromactivity);
        rv_placesresults.setAdapter(placesResultsAdapter);

          }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("LogNotTimber")
    @Override
    public void selectedPlacesName(String PlacesName,String selectedPlaceName,String fromactivity1) {
        addressResponseCall(PlacesName,selectedPlaceName);
        fromactivity = fromactivity1;
        Log.w(TAG,"selectedPlacesName : "+fromactivity);
    }

    private void addressResponseCall(String PlacesName, String selectedPlaceName) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API service = retrofit.create(API.class);

        String key = API.MAP_KEY;
        service.getaddressResults(PlacesName, key).enqueue(new Callback<AddressResultsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<AddressResultsResponse> call, @NotNull Response<AddressResultsResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"url  :%s"+ call.request().url().toString());


                Log.w(TAG,"addressResponseCall" + new Gson().toJson(response.body()));


                assert response.body() != null;
                List<AddressResultsResponse.ResultsBean> resultsBeanList = response.body().getResults();
                if (resultsBeanList.size() > 0 ) {
                    double lat = resultsBeanList.get(0).getGeometry().getLocation().getLat();
                    double lon = resultsBeanList.get(0).getGeometry().getLocation().getLng();

                    Log.w(TAG,"addressResponseCall lat-->"+lat+" lon : "+lon);
                    Log.w(TAG,"addressResponseCall cityname-->"+selectedPlaceName+" fromactivity : "+fromactivity);

                    if(fromactivity != null && fromactivity.equalsIgnoreCase("PickUpLocationDenyActivity")){
                        Log.w(TAG,"if-->"+fromactivity);

                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationDenyActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        startActivity(i);
                    }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PickUpLocationEditActivity")){
                        Log.w(TAG,"else if-->"+fromactivity);

                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationEditActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);

                        i.putExtra("id",id);
                        i.putExtra("userid",userid);
                        i.putExtra("nickname",locationnickname);
                        i.putExtra("locationtype",LocationType);
                        i.putExtra("defaultstatus",defaultstatus);
                        startActivity(i);
                    }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PickUpLocationActivity")){
                        Log.w(TAG,"else if-->"+fromactivity);

                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        startActivity(i);
                    }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PickUpLocationDoctorActivity")){
                        Log.w(TAG,"else if-->"+fromactivity);
                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationDoctorActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        startActivity(i);
                    }

                    else if(fromactivity != null && fromactivity.equalsIgnoreCase("ShippingAddressSPActivity")){
                        Log.w(TAG,"else if-->"+fromactivity);
                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationSPActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        startActivity(i);
                    } else if(fromactivity != null && fromactivity.equalsIgnoreCase("PickUpLocationSPActivity")){
                        Log.w(TAG,"else if-->"+fromactivity);
                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationSPActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        startActivity(i);
                    }


                    else if(fromactivity != null && fromactivity.equalsIgnoreCase("PickUpLocationAddNewAddressActivity")){
                        Log.w(TAG,"else if-->"+fromactivity);
                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationAddNewAddressActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        i.putExtra("id",id);
                        i.putExtra("userid",userid);
                        i.putExtra("nickname",locationnickname);
                        i.putExtra("data", (Serializable) Data);
                        i.putExtra("product_total",prodouct_total);
                        i.putExtra("shipping_charge",shipping_charge);
                        i.putExtra("discount_price",discount_price);
                        i.putExtra("grand_total",grand_total);
                        i.putExtra("prodcut_count",prodcut_count);
                        i.putExtra("prodcut_item_count",prodcut_item_count);
                        i.putExtra("fromactivity",fromactivity);
                        startActivity(i);
                    }
                    else if(fromactivity != null && fromactivity.equalsIgnoreCase("PickUpLocationAddNewAddressSPActivity")){
                        Log.w(TAG,"else if-->"+fromactivity);
                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationAddNewAddressSPActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        i.putExtra("id",id);
                        i.putExtra("userid",userid);
                        i.putExtra("nickname",locationnickname);
                        i.putExtra("data", (Serializable) Data);
                        i.putExtra("product_total",prodouct_total);
                        i.putExtra("shipping_charge",shipping_charge);
                        i.putExtra("discount_price",discount_price);
                        i.putExtra("grand_total",grand_total);
                        i.putExtra("prodcut_count",prodcut_count);
                        i.putExtra("prodcut_item_count",prodcut_item_count);
                        i.putExtra("fromactivity",fromactivity);
                        startActivity(i);
                    }
                    else if(fromactivity != null && fromactivity.equalsIgnoreCase("DoctorCartActivity")){
                        Log.w(TAG,"else if-->"+fromactivity);

                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationAddNewAddressActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        i.putExtra("id",id);
                        i.putExtra("userid",userid);
                        i.putExtra("nickname",locationnickname);
                        i.putExtra("data", (Serializable) Data);
                        i.putExtra("product_total",prodouct_total);
                        i.putExtra("shipping_charge",shipping_charge);
                        i.putExtra("discount_price",discount_price);
                        i.putExtra("grand_total",grand_total);
                        i.putExtra("prodcut_count",prodcut_count);
                        i.putExtra("prodcut_item_count",prodcut_item_count);
                        i.putExtra("fromactivity",fromactivity);
                        startActivity(i);
                    }
                    else if(fromactivity != null && fromactivity.equalsIgnoreCase("SetLocationDoctorNewActivity")){
                        Log.w(TAG,"else if-->"+fromactivity);
                        Intent i = new Intent(PlacesSearchActivity.this, SetLocationDoctorNewActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        startActivity(i);
                    }
                    else if(fromactivity != null && fromactivity.equalsIgnoreCase("SetLocationDoctorOldActivity")) {
                        Log.w(TAG,"else if-->"+fromactivity);
                        Intent i = new Intent(PlacesSearchActivity.this, SetLocationDoctorOldActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        startActivity(i);
                    }
                    else if(fromactivity != null && fromactivity.equalsIgnoreCase("SetLocationSPNewActivity")){
                        Log.w(TAG,"else if-->"+fromactivity);
                        Intent i = new Intent(PlacesSearchActivity.this, SetLocationSPNewActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        startActivity(i);
                    }
                    else if(fromactivity != null && fromactivity.equalsIgnoreCase("SetLocationSPOldActivity")) {
                        Log.w(TAG,"else if-->"+fromactivity);
                        Intent i = new Intent(PlacesSearchActivity.this, SetLocationSPOldActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        startActivity(i);
                    }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PickUpLocationShippingAddressEditActivity")) {
                        Log.w(TAG,"else if-->"+fromactivity);
                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationShippingAddressEditActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        i.putExtra("id",id);
                        i.putExtra("userid",userid);
                        i.putExtra("nickname",locationnickname);

                        i.putExtra("data", (Serializable) Data);
                        i.putExtra("product_total",prodouct_total);
                        i.putExtra("shipping_charge",shipping_charge);
                        i.putExtra("discount_price",discount_price);
                        i.putExtra("grand_total",grand_total);
                        i.putExtra("prodcut_count",prodcut_count);
                        i.putExtra("prodcut_item_count",prodcut_item_count);
                        startActivity(i);
                    }
                    else{
                        Log.w(TAG,"else -->"+"PickUpLocationAllowActivity");

                        Intent i = new Intent(PlacesSearchActivity.this, PickUpLocationAllowActivity.class);
                        i.putExtra("cityname",selectedPlaceName);
                        i.putExtra("placesearchactivity","placesearchactivity");
                        Bundle b = new Bundle();
                        b.putDouble("lat", lat);
                        b.putDouble("lon", lon);
                        i.putExtras(b);
                        startActivity(i);
                    }


                }


            }

            @Override
            public void onFailure(@NotNull Call<AddressResultsResponse> call, @NotNull Throwable t) {
                avi_indicator.smoothToHide();
                t.printStackTrace();
            }
        });
    }
}