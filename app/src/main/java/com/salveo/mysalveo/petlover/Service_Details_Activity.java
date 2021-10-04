package com.salveo.mysalveo.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;

import com.salveo.mysalveo.adapter.SPDetails_SpecTypesListAdapter;
import com.salveo.mysalveo.adapter.ViewPagerSPDetailsGalleryAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.SPDetailsRequest;
import com.salveo.mysalveo.requestpojo.SPFavCreateRequest;

import com.salveo.mysalveo.responsepojo.SPDetailsRepsonse;
import com.salveo.mysalveo.responsepojo.SPFavCreateResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.GridSpacingItemDecoration;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Service_Details_Activity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private String TAG = "Service_Details_Activity";

    // BottomSheetBehavior variable
    @SuppressWarnings("rawtypes")
    public BottomSheetBehavior bottomSheetBehavior;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_sp_companyname)
    TextView txt_sp_companyname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_spname)
    TextView txt_sp_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_place)
    TextView txt_place;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_distance)
    TextView txt_distance;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_book_now)
    LinearLayout ll_book_now;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_speclist)
    RecyclerView rv_speclist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dr_consultationfees)
    TextView txt_dr_consultationfees;


    //    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.txt_sp_spname)
//    TextView txt_sp_spname;
//
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.txt_serv_name)
//    TextView txt_serv_name;
//
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_selected_servicesname)
    TextView txt_selected_servicesname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_selectedserviceimage)
    ImageView img_selectedserviceimage;

    SPDetails_SpecTypesListAdapter spDetails_specTypesListAdapter;

    private String active_tag;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;




    private String userid;
    private String spid,catid;
    private List<SPDetailsRepsonse.DataBean.BusServiceGallBean> spServiceGalleryResponseList;
    private String from;
    private String spuserid;
    private String selectedServiceTitle;
    private String servicetime;
    private int serviceamount;


    private String serviceprovidingcompanyname = "";
    private String spprovidername = "";
    private int ratingcount;

    private String location;

    private SupportMapFragment mapFragment;
    private double latitude;
    private double longitude;
    private GoogleMap mMap;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img1)
    ImageView hand_img1;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img2)
    ImageView hand_img2;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img3)
    ImageView hand_img3;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img4)
    ImageView hand_img4;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img5)
    ImageView hand_img5;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_fav)
    ImageView img_fav;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_root1)
    LinearLayout ll_root1;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_root2)
    LinearLayout ll_root2;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_root3)
    LinearLayout ll_root3;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_aboutsp_label)
    TextView txt_aboutsp_label;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_spec_label)
    TextView txt_spec_label;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_hanldle)
    TextView txt_pet_hanldle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_popular_serv)
    LinearLayout ll_popular_serv;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_map)
    LinearLayout ll_map;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_location_label)
    TextView txt_location_label;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dr_desc)
    TextView txt_dr_desc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_seemore_spec)
    TextView txt_seemore_spec;


    List<SPDetailsRepsonse.DataBean.BusSpecListBean> specializationBeanList;

    String serv_name,selectedServiceImagepath;


    private String distance;
    private int reviewcount;
    private int Count_value_start;
    private int Count_value_end;


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
        setContentView(R.layout.activity_service_details);
        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");



        txt_seemore_spec.setVisibility(View.GONE);
        avi_indicator.setVisibility(View.GONE);
        ll_book_now.setVisibility(View.GONE);

        rl_back.setOnClickListener(this);
        ll_book_now.setOnClickListener(this);


        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            spid = extras.getString("spid");
            catid = extras.getString("catid");
            from = extras.getString("from");
            distance = extras.getString("distance");
            reviewcount = extras.getInt("reviewcount");
            Count_value_start = extras.getInt("Count_value_start");
            Count_value_end = extras.getInt("Count_value_end");

        }

        /*serv*/
        title_care.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_care.setImageResource(R.drawable.grey_care);
        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);
        title_serv.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
        img_serv.setImageResource(R.drawable.green_serv);

        rl_home.setOnClickListener(this);
        rl_care.setOnClickListener(this);
        rl_service.setOnClickListener(this);
        rl_shop.setOnClickListener(this);
        rl_comn.setOnClickListener(this);
        rl_homes.setOnClickListener(this);



        Log.w(TAG," userid : "+userid+ " spid : "+spid+" catid : "+catid+" from : "+from+" distance : "+distance);

        if(distance!=null&&!distance.isEmpty()){

            APIClient.SP_DISTANCE = distance;
        }
        if(spid != null && userid != null) {
            if (new ConnectionDetector(Service_Details_Activity.this).isNetworkAvailable(Service_Details_Activity.this)) {
                SPDetailsRepsonseCall();
            }
        }

        txt_seemore_spec.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(txt_seemore_spec.getText().toString() != null && txt_seemore_spec.getText().toString().equalsIgnoreCase("See more...")){
                    txt_seemore_spec.setText("Hide");
                    int size =specializationBeanList.size();
                    setSpecList(specializationBeanList,size);
                }else{
                    txt_seemore_spec.setText("See more...");
                    int size = 4;
                    setSpecList(specializationBeanList,size);

                }

            }
        });


        viewPager.setVisibility(View.GONE);

        tabLayout.setVisibility(View.GONE);

        hand_img1.setVisibility(View.GONE);

        hand_img2.setVisibility(View.GONE);

        hand_img3.setVisibility(View.GONE);

        hand_img4.setVisibility(View.GONE);

        hand_img5.setVisibility(View.GONE);

        txt_sp_companyname.setVisibility(View.GONE);

        txt_sp_name.setVisibility(View.GONE);

        ll_root1.setVisibility(View.GONE);

        ll_root3.setVisibility(View.GONE);

        txt_aboutsp_label.setVisibility(View.GONE);

        txt_dr_desc.setVisibility(View.GONE);

        txt_spec_label.setVisibility(View.GONE);

        rv_speclist.setVisibility(View.GONE);

        txt_pet_hanldle.setVisibility(View.GONE);

        ll_popular_serv.setVisibility(View.GONE);

        txt_location_label.setVisibility(View.GONE);

        txt_place.setVisibility(View.GONE);

        ll_map.setVisibility(View.GONE);

//        setBottomSheet();
//
//
//        if (mapFragment == null) {
//            mapFragment = SupportMapFragment.newInstance();
//            mapFragment.getMapAsync(this);
//        }
//
//        img_fav.setOnClickListener(this);
    }


    /**
     * method to setup the bottomsheet
     */
    private void setBottomSheet() {

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayoutsp));

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

        bottomSheetBehavior.setHideable(false);

        bottomSheetBehavior.setFitToContents(false);

        bottomSheetBehavior.setHalfExpandedRatio(0.8f);


        // Capturing the callbacks for bottom sheet
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.w("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.w("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.w("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.w("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.w("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.w("Bottom Sheet Behaviour", "STATE_HALF_EXPANDED");
                        break;
                }


            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {


            }


        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
                onBackPressed();
                break;
            case R.id.ll_book_now:
                gotoSPAvailableTimeActivity();
                break;
            case R.id.img_fav:
                if (new ConnectionDetector(Service_Details_Activity.this).isNetworkAvailable(Service_Details_Activity.this)) {
                    favResponseCall();
                }
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

    private void favResponseCall() {

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SPFavCreateResponse> call = apiInterface.createspfavlistResponseCall(RestUtils.getContentType(), spFavCreateRequest());
        Log.w(TAG,"SPFavCreateResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SPFavCreateResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<SPFavCreateResponse> call, @NonNull Response<SPFavCreateResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPFavCreateResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {
                        Toasty.success(getApplicationContext(),""+response.body().getMessage(),Toasty.LENGTH_SHORT).show();
                        if(spid != null && userid != null) {
                            if (new ConnectionDetector(Service_Details_Activity.this).isNetworkAvailable(Service_Details_Activity.this)) {
                                SPDetailsRepsonseCall();
                            }
                        }

                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<SPFavCreateResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPFavCreateResponse flr"+ t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("LogNotTimber")
    private SPFavCreateRequest spFavCreateRequest() {
        /*
         * user_id : 5fd778437aa4cc1c6a1e5632
         * sp_id : 5fe1e675094d0471dabf9295
         * cata_id : 5fe185d61996f651f5133693
         */


        SPFavCreateRequest spFavCreateRequest = new SPFavCreateRequest();
        spFavCreateRequest.setUser_id(userid);
        spFavCreateRequest.setSp_id(spid);
        spFavCreateRequest.setCat_id(catid);
        Log.w(TAG,"spFavCreateRequest "+ new Gson().toJson(spFavCreateRequest));
        return spFavCreateRequest;
    }


    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }
    private void gotoSPAvailableTimeActivity() {
        Intent intent = new Intent(getApplicationContext(),PetServiceAppointment_Doctor_Date_Time_Activity.class);
        intent.putExtra("spid",spid);
        intent.putExtra("catid",catid);
        intent.putExtra("from",from);
        intent.putExtra("spuserid",spuserid);
        intent.putExtra("selectedServiceTitle",selectedServiceTitle);
        intent.putExtra("serviceprovidingcompanyname",serviceprovidingcompanyname);
        intent.putExtra("serviceamount",serviceamount);
        intent.putExtra("servicetime",servicetime);
        intent.putExtra("distance",distance);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(from != null && from.equalsIgnoreCase("PetLoverSPNewFavAdapter")){
            Intent intent = new Intent(getApplicationContext(),PetloverFavListActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(getApplicationContext(),SelectedServiceActivity.class);
            intent.putExtra("spid",spid);
            intent.putExtra("catid",catid);
            intent.putExtra("from",from);
            intent.putExtra("distance",distance);
            intent.putExtra("reviewcount",reviewcount);
            intent.putExtra("Count_value_start",Count_value_start);
            intent.putExtra("Count_value_end",Count_value_end);
            startActivity(intent);
            finish();
        }


    }
    @SuppressLint("LogNotTimber")
    private void SPDetailsRepsonseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SPDetailsRepsonse> call = apiInterface.SPDetailsRepsonseCall(RestUtils.getContentType(), spDetailsRequest());
        Log.w(TAG,"SPDetailsRepsonseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SPDetailsRepsonse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<SPDetailsRepsonse> call, @NonNull Response<SPDetailsRepsonse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPDetailsRepsonse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        viewPager.setVisibility(View.VISIBLE);
                        ll_book_now.setVisibility(View.VISIBLE);

                        tabLayout.setVisibility(View.VISIBLE);

                        hand_img1.setVisibility(View.VISIBLE);

                        hand_img2.setVisibility(View.VISIBLE);

                        hand_img3.setVisibility(View.VISIBLE);

                        hand_img4.setVisibility(View.VISIBLE);

                        hand_img5.setVisibility(View.VISIBLE);

                        txt_sp_companyname.setVisibility(View.VISIBLE);

                        txt_sp_name.setVisibility(View.VISIBLE);

                        ll_root1.setVisibility(View.VISIBLE);

                        ll_root3.setVisibility(View.VISIBLE);



                        txt_spec_label.setVisibility(View.VISIBLE);

                        rv_speclist.setVisibility(View.VISIBLE);

                        txt_pet_hanldle.setVisibility(View.VISIBLE);

                        ll_popular_serv.setVisibility(View.VISIBLE);

                        txt_location_label.setVisibility(View.VISIBLE);

                        txt_place.setVisibility(View.VISIBLE);

                        ll_map.setVisibility(View.VISIBLE);

                        setBottomSheet();

                        img_fav.setOnClickListener(Service_Details_Activity.this);

                        if(response.body().getData().isFav()){
                            img_fav.setBackgroundResource(R.drawable.ic_fav);
                        }else{
                            img_fav.setBackgroundResource(R.drawable.heart_gray);
                        }

                        if(response.body().getData().getBus_service_gall() != null) {
                            spServiceGalleryResponseList = response.body().getData().getBus_service_gall();
                        }
                        if(response.body().getData().getBussiness_name() != null) {
                            serviceprovidingcompanyname = response.body().getData().getBussiness_name();
                        }
                        if(response.body().getData().getBus_user_name() != null) {
                            spprovidername = response.body().getData().getBus_user_name();
                        }
                        if(response.body().getData().getRating() != 0) {
                            ratingcount = response.body().getData().getRating();
                        }

//                        if(response.body().getData().getComments() != 0) {
//                             comments = response.body().getData().getComments();
//                        }
//                        if(response.body().getData().getDistance() != 0) {
//                             distance = response.body().getData().getDistance();
//                             txt_distance.setText(distance+"");
//
//                        }else{
//                            distance = 0;
//                            txt_distance.setText(0+"");
//                        }
                        if( response.body().getData().getSp_loc() != null) {
                            location = response.body().getData().getSp_loc();

                            latitude = response.body().getData().getSp_lat();

                            longitude = response.body().getData().getSp_long();

                            Log.w(TAG,"latitude"+ latitude );

                            Log.w(TAG,"longitude"+ longitude );



                            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.map);


                            if (mapFragment != null) {
                                mapFragment.getMapAsync(Service_Details_Activity.this);
                            }

                            // mapFragment.getMapAsync(Service_Details_Activity.this);

                        }
                        if(response.body().getDetails().getImage_path() != null) {
                            selectedServiceImagepath = response.body().getDetails().getImage_path();
                        }



                        if(response.body().getDetails().getTitle() != null) {
                            selectedServiceTitle = response.body().getDetails().getTitle();
                        }
                        if(response.body().getDetails().getAmount() != 0) {
                            serviceamount = response.body().getDetails().getAmount();

                            txt_dr_consultationfees.setText("INR "+serviceamount);


                        }

                        if(response.body().getDetails().getTitle() != null) {

                            serv_name = response.body().getDetails().getTitle();
                        }

                        if(serv_name != null && !serv_name.isEmpty()){
                            txt_selected_servicesname.setText(serv_name);

                        }

                        if(selectedServiceImagepath != null && !selectedServiceImagepath.isEmpty()){
                            Glide.with(Service_Details_Activity.this)
                                    .load(selectedServiceImagepath)
                                    .into(img_selectedserviceimage);

                        }

                        else {

                            img_selectedserviceimage.setImageResource(R.drawable.services);
                        }



                        if(response.body().getDetails().getTime() != null) {
                            servicetime = response.body().getDetails().getTime();
                        }

                        if(response.body().getDetails().getTime() != null) {
                            servicetime = response.body().getDetails().getTime();
                        }
                        if(response.body().getData().getUser_id() != null) {
                            spuserid = response.body().getData().getUser_id();
                        }

                        if(serviceprovidingcompanyname != null && !serviceprovidingcompanyname.isEmpty()){
                            txt_sp_companyname.setText(serviceprovidingcompanyname);
                        }
                        if(spprovidername != null && !spprovidername.isEmpty()){
                            txt_sp_name.setText(spprovidername);
                        }
                        if(ratingcount != 0 ) {

                            if(ratingcount == 1){
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            } else if(ratingcount == 2){
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            }else if(ratingcount == 3){
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            }else if(ratingcount == 4){
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            } else if(ratingcount == 5){
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_color);
                            }


                        }else{
                            hand_img1.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            hand_img2.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                        }
                        if(location != null && !location.isEmpty()){
                            txt_place.setText(location);
                        }

                        if(distance != null&&!distance.isEmpty()){

                            txt_distance.setText(distance+" KM Away");

                        }
                        else if(APIClient.SP_DISTANCE != null&&!APIClient.SP_DISTANCE.isEmpty()){

                            txt_distance.setText(APIClient.SP_DISTANCE+" KM Away");

                        }


                        if(spServiceGalleryResponseList != null && spServiceGalleryResponseList.size()>0){

                            for (int i = 0; i < spServiceGalleryResponseList.size(); i++) {
                                spServiceGalleryResponseList.get(i).getBus_service_gall();
                                Log.w(TAG, "RES" + ", " +  spServiceGalleryResponseList.get(i).getBus_service_gall());
                            }


                            viewpageData(spServiceGalleryResponseList);

                        }

                        if(response.body().getData().getBus_spec_list() != null&&response.body().getData().getBus_spec_list().size()>0){

                            // specializationBeanList = new ArrayList<>();

                            specializationBeanList=response.body().getData().getBus_spec_list();

                            Log.w(TAG,"SpecilaziationList : "+new Gson().toJson(specializationBeanList));
                            Log.w(TAG,"SpecilaziationList size: "+specializationBeanList.size());

                            setSpecList(specializationBeanList,4);



                        }





                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<SPDetailsRepsonse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPDetailsRepsonse flr"+ t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void viewpageData(List<SPDetailsRepsonse.DataBean.BusServiceGallBean> spServiceGalleryResponseList) {
        tabLayout.setupWithViewPager(viewPager, true);

        ViewPagerSPDetailsGalleryAdapter viewPagerSPDetailsGalleryAdapter = new ViewPagerSPDetailsGalleryAdapter(getApplicationContext(), spServiceGalleryResponseList);
        viewPager.setAdapter(viewPagerSPDetailsGalleryAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update =  new Runnable() {
            public void run() {
                if (currentPage == spServiceGalleryResponseList.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, false);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

    }
    @SuppressLint("LogNotTimber")
    private SPDetailsRequest spDetailsRequest() {
        /*
         * user_id : 5fd778437aa4cc1c6a1e5632
         * sp_id : 5fe1e675094d0471dabf9295
         * cata_id : 5fe185d61996f651f5133693
         */


        SPDetailsRequest spDetailsRequest = new SPDetailsRequest();
        spDetailsRequest.setUser_id(userid);
        spDetailsRequest.setSp_id(spid);
        spDetailsRequest.setCata_id(catid);
        Log.w(TAG,"spSpecificServiceDetailsRequest "+ new Gson().toJson(spDetailsRequest));
        return spDetailsRequest;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device.
     * This method will only be triggered once the user has installed
     Google Play services and returned to the app.
     */

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        Log.w(TAG,"Map latitude"+ latitude );

        Log.w(TAG,"Map longitude"+ longitude );

        if(latitude!=0&&longitude!=0){

            LatLng currentLocation = new LatLng(latitude, longitude);

            mMap.addMarker(new
                    MarkerOptions().position(currentLocation));

            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
            // Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                    Log.w(TAG,"mMap onclick : "+"latitude : "+latitude+" longitude : "+longitude+" location : "+location);
                    String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + location + ")";
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);
                }
            });


        }

    }



    private void setSpecList(List<SPDetailsRepsonse.DataBean.BusSpecListBean> specializationBeanList, int size) {

        int spanCount = 2; // 3 columns
        int spacing = 0; // 50px
        boolean includeEdge = true;
        rv_speclist.setLayoutManager(new GridLayoutManager(this, 2));
        rv_speclist.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        rv_speclist.setItemAnimator(new DefaultItemAnimator());
        spDetails_specTypesListAdapter = new SPDetails_SpecTypesListAdapter(Service_Details_Activity.this, specializationBeanList,size);
        rv_speclist.setAdapter(spDetails_specTypesListAdapter);

    }

}