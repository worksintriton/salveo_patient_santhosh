package com.salveo.mysalveo.fragmentpetlover.bottommenu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.location.PickUpLocationAllowActivity;
import com.salveo.mysalveo.activity.location.PickUpLocationDenyActivity;
import com.salveo.mysalveo.adapter.PetLoverDashboardPubbyLoveAdapter;
import com.salveo.mysalveo.adapter.PetLoverDoctorNewAdapter;
import com.salveo.mysalveo.adapter.PetLoverServicesAdapter;
import com.salveo.mysalveo.adapter.PetLoverShopNewAdapter;
import com.salveo.mysalveo.adapter.ViewPagerDashboardAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.petlover.PetLoverDashboardActivity;
import com.salveo.mysalveo.requestpojo.PetLoverDashboardRequest;
import com.salveo.mysalveo.responsepojo.PetLoverDashboardResponse;
import com.salveo.mysalveo.service.GPSTracker;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PetHomeNewFragment extends Fragment implements Serializable,
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener,
        View.OnClickListener  {

    private String TAG = "PetHomeNewFragment";
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;

    double latitude, longitude;

    private String userid;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvdoctors)
    RecyclerView rvdoctors;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_doctors)
    TextView txt_doctors;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvservice)
    RecyclerView rvservice;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_services)
    TextView txt_services;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_seemore_services)
    TextView txt_seemore_services;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_seemore_vets)
    TextView txt_seemore_vets;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_products)
    TextView txt_products;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_seemore_products)
    TextView txt_seemore_products;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvproducts)
    RecyclerView rvproducts;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_doctor_norecord)
    TextView txt_doctor_norecord;

/*
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_puppy_love)
    TextView txt_puppy_love;*/

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_seemore_puppy_love)
    TextView txt_seemore_puppy_love;

   /* @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvpuppy_love)
    RecyclerView rvpuppy_love;*/

 /*   @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_puppy_love)
    TextView txt_no_puppy_love;*/

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.headerView)
    LinearLayout headerView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_content_shop)
    LinearLayout ll_content_shop;

    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;

    private List<PetLoverDashboardResponse.DataBean.DashboarddataBean.BannerDetailsBean> listHomeBannerResponse;
    private List<PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean> doctorDetailsResponseList;
    private List<PetLoverDashboardResponse.DataBean.DashboarddataBean.ServiceDetailsBean> serviceDetailsResponseList;
    private List<PetLoverDashboardResponse.DataBean.DashboarddataBean.ProductsDetailsBean> productDetailsResponseList;
    private List<PetLoverDashboardResponse.DataBean.DashboarddataBean.PuppyProductsDetailsBean> puppyProductsDetailsBeanList;
    private Dialog alertDialog;

    private String AddressLine;
    private Context mContext;

    private static final int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private GoogleApiClient googleApiClient;
    Location mLastLocation;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private SupportMapFragment mapFragment;
    private String title;
    private String doctornotavlmsg;
    private List<PetLoverDashboardResponse.DataBean.DashboarddataBean.MiddleBannerDetailsBean> middleBannerDetailsBeanList;

    private Activity mActivity;


    public PetHomeNewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView-->");
        View view = inflater.inflate(R.layout.fragment_home_pet_new, container, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ButterKnife.bind(this, view);
        mContext = getActivity();

        GPSTracker gpsTracker = new GPSTracker(mContext);

        // SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(this);
        }
        // R.id.map is a FrameLayout, not a Fragment
        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();

        googleApiConnected();


        avi_indicator.setVisibility(View.GONE);

        headerView.setVisibility(View.GONE);
        ll_content_shop.setVisibility(View.GONE);

        includelayout = view.findViewById(R.id.includelayout);
        mShimmerViewContainer = includelayout.findViewById(R.id.shimmer_layout);

        txt_doctors.setVisibility(View.GONE);
        txt_services.setVisibility(View.GONE);
        txt_seemore_services.setVisibility(View.GONE);
        txt_seemore_vets.setVisibility(View.GONE);

        txt_products.setVisibility(View.GONE);
        txt_seemore_products.setVisibility(View.GONE);
      /*  txt_puppy_love.setVisibility(View.GONE);*/
        txt_seemore_puppy_love.setVisibility(View.GONE);

        SessionManager sessionManager = new SessionManager(mContext);
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG,"customerid-->"+userid);
        txt_seemore_services.setOnClickListener(this);
        txt_seemore_products.setOnClickListener(this);
        txt_seemore_puppy_love.setOnClickListener(this);
        txt_seemore_vets.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }










    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.txt_seemore_services:
               /* if(serviceDetailsResponseList != null && serviceDetailsResponseList.size()>0){
                    Log.w(TAG,"SERVICE SIZE :"+serviceDetailsResponseList.size());
                    rvservice.setVisibility(View.VISIBLE);
                    txt_services.setVisibility(View.VISIBLE);
                    setViewServicesSeeMore();
                }
                else{
                    rvservice.setVisibility(View.GONE);
                    txt_services.setVisibility(View.GONE);

                }*/
                callDirections("3");
                break;

            case R.id.txt_seemore_products:
               /* if(productDetailsResponseList.size()>0){
                    rvproducts.setVisibility(View.VISIBLE);
                    txt_products.setVisibility(View.VISIBLE);
                    setViewProductsSeeMore();
                }
                else{
                    rvproducts.setVisibility(View.GONE);
                    txt_products.setVisibility(View.GONE);

                }*/
                callDirections("2");
                break;
            case R.id.txt_seemore_puppy_love:
               /* if(puppyProductsDetailsBeanList.size()>0){
                    rvpuppy_love.setVisibility(View.GONE);
                    txt_puppy_love.setVisibility(View.GONE);
                    setViewPuppyLoveSeeMore();
                }
                else{
                    rvpuppy_love.setVisibility(View.GONE);
                    txt_puppy_love.setVisibility(View.GONE);

                }*/
                break;

            case R.id.txt_seemore_vets:
                callDirections("4");
                break;


        }

    }

    public void callDirections(String tag){
        Intent intent = new Intent(mContext, PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }



    @SuppressLint("LogNotTimber")
    private void petLoverDashboardResponseCall(int doctor, int service, int product) {
       /* avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        includelayout.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<PetLoverDashboardResponse> call = apiInterface.petLoverDashboardResponseCall(RestUtils.getContentType(), petLoverDashboardRequest(doctor,service,product));
        Log.w(TAG,"PetLoverDashboardResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<PetLoverDashboardResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<PetLoverDashboardResponse> call, @NonNull Response<PetLoverDashboardResponse> response) {
                //avi_indicator.smoothToHide();
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.w(TAG,"PetLoverDashboardResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        headerView.setVisibility(View.VISIBLE);
                        ll_content_shop.setVisibility(View.VISIBLE);

                        if (response.body().getData().getDashboarddata().getBanner_details() != null && response.body().getData().getDashboarddata().getBanner_details().size()>0) {
                            listHomeBannerResponse = response.body().getData().getDashboarddata().getBanner_details();
                            for (int i = 0; i < listHomeBannerResponse.size(); i++) {
                                listHomeBannerResponse.get(i).getImg_path();
                                Log.w(TAG, "RES" + " " + listHomeBannerResponse.get(i).getImg_path());
                            }
                            viewpageData(listHomeBannerResponse);

                        }
                        if (response.body().getData().getDashboarddata().getDoctor_details() != null) {
                            doctorDetailsResponseList = response.body().getData().getDashboarddata().getDoctor_details();
                            Log.w(TAG, "doctorDetailsResponseList Size" + doctorDetailsResponseList.size());
                            if (doctorDetailsResponseList != null && doctorDetailsResponseList.size()>0) {
                                rvdoctors.setVisibility(View.VISIBLE);
                                txt_doctor_norecord.setVisibility(View.GONE);
                                txt_doctors.setVisibility(View.VISIBLE);
                                txt_seemore_vets.setVisibility(View.VISIBLE);
                                setViewDoctors(doctorDetailsResponseList);


                            }
                            else {
                                rvdoctors.setVisibility(View.GONE);
                                if(response.body().getData().getMessages() != null){
                                    for(int i= 0;i< response.body().getData().getMessages().size();i++){
                                        title =  response.body().getData().getMessages().get(i).getTitle();
                                        if(title != null && title.equalsIgnoreCase("Doctor")){
                                            doctornotavlmsg =  response.body().getData().getMessages().get(i).getMessage();
                                            break;

                                        }

                                    }


                                    showAlertDoctorNotAvlLoading(doctornotavlmsg);
                                }



                            }

                        }
                        if (response.body().getData().getDashboarddata().getService_details() != null) {
                            serviceDetailsResponseList = response.body().getData().getDashboarddata().getService_details();
                            Log.w(TAG, "serviceDetailsResponseList Size" + serviceDetailsResponseList.size());
                            if (serviceDetailsResponseList != null && serviceDetailsResponseList.size()>0) {
                                txt_seemore_services.setVisibility(View.VISIBLE);
                                rvservice.setVisibility(View.VISIBLE);
                                txt_services.setVisibility(View.VISIBLE);
                                setViewServices(serviceDetailsResponseList);
                            } else {
                                rvservice.setVisibility(View.GONE);
                                txt_services.setVisibility(View.GONE);
                                txt_seemore_services.setVisibility(View.GONE);

                            }

                        }
                        if (response.body().getData().getDashboarddata().getProducts_details() != null) {
                            productDetailsResponseList = response.body().getData().getDashboarddata().getProducts_details();
                            Log.w(TAG, "productDetailsResponseList Size" + productDetailsResponseList.size());
                            if (productDetailsResponseList != null && productDetailsResponseList.size()>0) {
                                rvproducts.setVisibility(View.VISIBLE);
                                txt_products.setVisibility(View.VISIBLE);
                                txt_seemore_products.setVisibility(View.VISIBLE);
                                setViewProducts(productDetailsResponseList);
                            } else {
                                rvproducts.setVisibility(View.GONE);
                                txt_products.setVisibility(View.GONE);
                                txt_seemore_products.setVisibility(View.GONE);

                            }

                        }
                        /*if (response.body().getData().getDashboarddata().getMiddle_Banner_details() != null) {
                            middleBannerDetailsBeanList = response.body().getData().getDashboarddata().getMiddle_Banner_details();
                            Log.w(TAG, "middleBannerDetailsBeanList Size" + middleBannerDetailsBeanList.size());
                            if (middleBannerDetailsBeanList != null && middleBannerDetailsBeanList.size()>0) {
                                rvpuppy_love.setVisibility(View.VISIBLE);
                                txt_puppy_love.setVisibility(View.GONE);
                                txt_seemore_puppy_love.setVisibility(View.GONE);
                                txt_no_puppy_love.setVisibility(View.GONE);
                                setViewPuppyLove(middleBannerDetailsBeanList);
                            } else {
                                rvpuppy_love.setVisibility(View.GONE);
                                txt_puppy_love.setVisibility(View.GONE);
                                txt_seemore_puppy_love.setVisibility(View.GONE);

                               *//* txt_no_puppy_love.setVisibility(View.VISIBLE);
                                txt_no_puppy_love.setText("No Puppies found");*//*

                            }

                        }*/
                        if(response.body().getData().getLocationDetails() != null && response.body().getData().getLocationDetails().isEmpty()){
                                showLocationAlert();

                        }


                    }
                    else {
                        showErrorLoading(response.body().getMessage());
                    }

                }


            }

            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onFailure(@NonNull Call<PetLoverDashboardResponse> call,@NonNull Throwable t) {
               // avi_indicator.smoothToHide();
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.e("PetLoverDashboardResponseflr", "--->" + t.getMessage());
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private PetLoverDashboardRequest petLoverDashboardRequest(int doctor, int service, int product) {
        /*
         * Doctor : 0
         * Product : 0
         * address : Unnamed Road, Tamil Nadu 621006, India
         * lat : 11.055715
         * lon : 78.632249
         * service : 0
         * user_id : 5fd227ac80791a71361baad3
         * user_type : 1
         */


        PetLoverDashboardRequest petLoverDashboardRequest = new PetLoverDashboardRequest();
        petLoverDashboardRequest.setUser_id(userid);
        petLoverDashboardRequest.setLat(latitude);
        petLoverDashboardRequest.setLongX(longitude);
        petLoverDashboardRequest.setUser_type(1);
        petLoverDashboardRequest.setAddress(AddressLine);
        petLoverDashboardRequest.setDoctor(doctor);
        petLoverDashboardRequest.setProduct(product);
        petLoverDashboardRequest.setService(service);


        Log.w(TAG,"petLoverDashboardRequest"+ new Gson().toJson(petLoverDashboardRequest));
        return petLoverDashboardRequest;
    }


    private void setViewProducts(List<PetLoverDashboardResponse.DataBean.DashboarddataBean.ProductsDetailsBean> productDetailsResponseList) {
        rvproducts.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvproducts.setMotionEventSplittingEnabled(false);
        int size =3;
        rvproducts.setItemAnimator(new DefaultItemAnimator());
        PetLoverShopNewAdapter petLoverProductsAdapter = new PetLoverShopNewAdapter(mContext, productDetailsResponseList, rvproducts, size);
        rvproducts.setAdapter(petLoverProductsAdapter);
    }
    private void setViewProductsSeeMore() {
        rvproducts.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvproducts.setMotionEventSplittingEnabled(false);
        int size = productDetailsResponseList.size();
        rvproducts.setItemAnimator(new DefaultItemAnimator());
        PetLoverShopNewAdapter petLoverProductsAdapter = new PetLoverShopNewAdapter(mContext, productDetailsResponseList, rvproducts, size);
        rvproducts.setAdapter(petLoverProductsAdapter);
    }
/*    private void setViewPuppyLove(List<PetLoverDashboardResponse.DataBean.DashboarddataBean.MiddleBannerDetailsBean> middleBannerDetailsBeanList) {
        rvpuppy_love.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvpuppy_love.setMotionEventSplittingEnabled(false);
        int size =3;
        rvpuppy_love.setItemAnimator(new DefaultItemAnimator());
        PetLoverDashboardPubbyLoveAdapter petLoverDashboardPubbyLoveAdapter = new PetLoverDashboardPubbyLoveAdapter(mContext, middleBannerDetailsBeanList, rvpuppy_love, size);
        rvpuppy_love.setAdapter(petLoverDashboardPubbyLoveAdapter);
    }
    private void setViewPuppyLoveSeeMore() {
        rvpuppy_love.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvpuppy_love.setMotionEventSplittingEnabled(false);
        int size =puppyProductsDetailsBeanList.size();
        rvpuppy_love.setItemAnimator(new DefaultItemAnimator());
        PetLoverDashboardPubbyLoveAdapter petLoverDashboardPubbyLoveAdapter = new PetLoverDashboardPubbyLoveAdapter(mContext, middleBannerDetailsBeanList, rvpuppy_love, size);
        rvpuppy_love.setAdapter(petLoverDashboardPubbyLoveAdapter);
    }*/
    private void setViewServices(List<PetLoverDashboardResponse.DataBean.DashboarddataBean.ServiceDetailsBean> serviceDetailsResponseList) {
        rvservice.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvservice.setMotionEventSplittingEnabled(false);
        int size = 4;
        rvservice.setItemAnimator(new DefaultItemAnimator());
        PetLoverServicesAdapter petLoverServicesAdapter = new PetLoverServicesAdapter(mContext, serviceDetailsResponseList, rvservice, size);
        rvservice.setAdapter(petLoverServicesAdapter);
    }
    private void setViewServicesSeeMore() {
        rvservice.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvservice.setMotionEventSplittingEnabled(false);
        int size = serviceDetailsResponseList.size();
        rvservice.setItemAnimator(new DefaultItemAnimator());
        PetLoverServicesAdapter petLoverServicesAdapter = new PetLoverServicesAdapter(mContext, serviceDetailsResponseList, rvservice, size);
        rvservice.setAdapter(petLoverServicesAdapter);
        petLoverServicesAdapter.notifyDataSetChanged();
    }
    private void setViewDoctors(List<PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean> doctorDetailsResponseList) {
        rvdoctors.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvdoctors.setMotionEventSplittingEnabled(false);
        int size =3;
        rvdoctors.setItemAnimator(new DefaultItemAnimator());
        PetLoverDoctorNewAdapter petLoverDoctorAdapter = new PetLoverDoctorNewAdapter(mContext, doctorDetailsResponseList, rvdoctors, size);
        rvdoctors.setAdapter(petLoverDoctorAdapter);
    }
    private void setViewDoctorsSeeMore() {
        rvdoctors.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvdoctors.setMotionEventSplittingEnabled(false);
        int size = doctorDetailsResponseList.size();
        rvdoctors.setItemAnimator(new DefaultItemAnimator());
        PetLoverDoctorNewAdapter petLoverDoctorAdapter = new PetLoverDoctorNewAdapter(mContext, doctorDetailsResponseList, rvdoctors, size);
        rvdoctors.setAdapter(petLoverDoctorAdapter);
        petLoverDoctorAdapter.notifyDataSetChanged();

    }
    private void viewpageData(List<PetLoverDashboardResponse.DataBean.DashboarddataBean.BannerDetailsBean> listHomeBannerResponse) {
        tabLayout.setupWithViewPager(viewPager, true);

        ViewPagerDashboardAdapter viewPagerDashboardAdapter = new ViewPagerDashboardAdapter(mContext, listHomeBannerResponse);
        viewPager.setAdapter(viewPagerDashboardAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update =  new Runnable() {
            public void run() {
                if (currentPage == listHomeBannerResponse.size()) {
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



    private void showLocationAlert() {

        try {

            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.alert_location_allow_deny_layout);
            dialog.setCanceledOnTouchOutside(false);
            Button btn_allow = dialog.findViewById(R.id.btn_allow);
            Button btn_deny = dialog.findViewById(R.id.btn_deny);
            btn_deny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    startActivity(new Intent(mContext, PickUpLocationDenyActivity.class));

                    // showLocationDenyAlert();


                }
            });
            btn_allow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(mContext, PickUpLocationAllowActivity.class));
                    dialog.dismiss();

                }
            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    private void showLocationDenyAlert() {

        try {

            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.alert_location_deny_layout);
            dialog.setCanceledOnTouchOutside(false);

            ImageView img_close = dialog.findViewById(R.id.img_close);
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(mContext, PickUpLocationDenyActivity.class));
                    dialog.dismiss();

                }
            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());




        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void hideLoading(){
        try {
            alertDialog.dismiss();
        }catch (Exception ignored){

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String @NotNull [] permissions, @NotNull int @NotNull [] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {


                }
            } else {
                Toast.makeText(mContext, "permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void getLatandLong() {
        try {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {
                GPSTracker gps = new GPSTracker(mContext);
                // Check if GPS enabled
                if (gps.canGetLocation()) {
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    if(latitude != 0 && longitude != 0){
                        getCompleteAddressString(latitude,longitude);
                        Log.w(TAG, "getLatandLong--->" + "latitude" + " " + latitude + "longitude" + " " + longitude);

                        if (new ConnectionDetector(mContext).isNetworkAvailable(mContext)) {
                            petLoverDashboardResponseCall(0,0,0);
                        }
                    }




                }
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressLint("LongLogTag")
    private void getCompleteAddressString(double LATITUDE, double LONGITUDE) {

        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                String cityName = addresses.get(0).getAddressLine(0);
                Log.w(TAG,"getCompleteAddressString cityName : "+cityName);

                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                AddressLine = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
    }
    private void googleApiConnected() {

        googleApiClient = new GoogleApiClient.Builder(Objects.requireNonNull(mContext)).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).
                addApi(LocationServices.API).build();
        googleApiClient.connect();

    }
    private void checkLocation() {
        try {
            LocationManager lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            boolean gps_enabled = false;
            boolean network_enabled = false;

            try {
                gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            } catch (Exception ignored) {
            }

            try {
                network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            } catch (Exception ignored) {
            }

            if (!gps_enabled && !network_enabled) {

                if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getMyLocation();
                }

            } else {
                getLatandLong();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        latitude = mLastLocation.getLatitude();
        longitude = mLastLocation.getLongitude();

        Log.w(TAG,"onLocationChanged : "+" latitude : "+latitude+ " longitude : "+longitude);






    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        permissionChecking();
    }
    @Override
    public void onConnectionSuspended(int i) {

    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @SuppressLint("LongLogTag")
    @Override
    public void onMapReady(GoogleMap googleMap) {


    }
    private void permissionChecking() {
        if (mContext != null) {
            if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) &&
                    (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 5);

            } else {

                checkLocation();
            }
        }
    }
    public void getMyLocation() {
        if (googleApiClient != null) {

            if (googleApiClient.isConnected()) {
                if(mContext != null){
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                        return;
                    }

                }

                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                LocationRequest locationRequest = new LocationRequest();
                locationRequest.setInterval(2000);
                locationRequest.setFastestInterval(2000);
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
                builder.setAlwaysShow(true);
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
                PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
                result.setResultCallback(result1 -> {
                    Status status = result1.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied.
                            // You can initialize location requests here.
                            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);







                            Handler handler = new Handler();
                            int delay = 1000; //milliseconds

                            handler.postDelayed(new Runnable() {
                                @SuppressLint({"LongLogTag", "LogNotTimber"})
                                public void run() {
                                    //do something
                                    Log.w(TAG, "getMyLocation-->");
                                    if(mContext != null) {
                                        if(latitude != 0 && longitude != 0) {
                                            getCompleteAddressString(latitude,longitude);
                                            if (new ConnectionDetector(mContext).isNetworkAvailable(mContext)) {
                                                petLoverDashboardResponseCall(0, 0, 0);
                                            }
                                        }
                                    }
                                }
                            }, delay);


                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                status.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS_GPS);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                });
            }


        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_GPS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        getMyLocation();
                        break;
                }
                break;
        }
    }

    public void showAlertDoctorNotAvlLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoadingDoctornotavl());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void hideLoadingDoctornotavl() {
        try {
            if(latitude != 0 && longitude != 0) {
                if (new ConnectionDetector(mContext).isNetworkAvailable(mContext)) {
                    petLoverDashboardResponseCall(1, 0, 0);
                }
            }

            alertDialog.dismiss();

        } catch (Exception ignored) {

        }
    }



}
