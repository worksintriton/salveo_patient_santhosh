package com.salveo.mysalveo.petlover;


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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import com.salveo.mysalveo.adapter.DoctorClinicPetsHandledListAdapter;
import com.salveo.mysalveo.adapter.DoctorClinicSpecTypesListAdapter;
import com.salveo.mysalveo.adapter.ViewPagerClinicDetailsAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.DoctorDetailsRequest;
import com.salveo.mysalveo.requestpojo.DoctorFavCreateRequest;
import com.salveo.mysalveo.responsepojo.DoctorDetailsResponse;
import com.salveo.mysalveo.responsepojo.SuccessResponse;
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

public class DoctorClinicDetailsActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback
{

    // BottomSheetBehavior variable
    @SuppressWarnings("rawtypes")
    public BottomSheetBehavior bottomSheetBehavior;

    private static final String TAG = "DoctorClinicDetailsActivity";

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
    @BindView(R.id.txt_clinicname)
    TextView txt_clinicname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_drname)
    TextView txt_drname;

    private SupportMapFragment mapFragment;
    private double latitude;
    private double longitude;
    private GoogleMap mMap;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_place)
    TextView txt_place;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_distance)
    TextView txt_distance;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_about_vet_desc)
    TextView txt_dr_desc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dr_experience)
    TextView txt_dr_experience;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dr_consultationfees)
    TextView txt_dr_consultationfees;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_book_now)
    LinearLayout ll_book_now;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_fav)
    ImageView img_fav;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_speclist)
    RecyclerView rv_speclist;

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.rv_pet_hanldle)
//    RecyclerView rv_pet_hanldle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.toolbar_header)
    Toolbar toolbar_header;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomSheetLayouts)
    NestedScrollView bottomSheetLayouts;

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
    @BindView(R.id.txt_about_vet_label)
    TextView txt_about_vet_label;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_spec_label)
    TextView txt_spec_label;

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.txt_pet_hanldle)
//    TextView txt_pet_hanldle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_map)
    LinearLayout ll_map;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_location_label)
    TextView txt_location_label;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.footerView)
    LinearLayout footerView;



    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;

    private String doctorid;
    private List<DoctorDetailsResponse.DataBean.ClinicPicBean> doctorclinicdetailsResponseList;
    private String doctorname;
    private Integer reviewcount;
    private Integer starcount;
    private String clinicname;
    private String distance;
    private String ClinicLocationname;
    private String fromactivity;
    private String searchString;
    private int amount;
    private String communicationtype;
    private int Doctor_exp;

    List<DoctorDetailsResponse.DataBean.SpecializationBean> specializationBeanList;

    List<DoctorDetailsResponse.DataBean.PetHandledBean> petHandledBeanList;

    DoctorClinicSpecTypesListAdapter doctorClinicSpecTypesListAdapter;

    DoctorClinicPetsHandledListAdapter doctorClinicPetsHandledListAdapter;

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
    @BindView(R.id.txt_seemore_spec)
    TextView txt_seemore_spec;

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.txt_seemore_petshandle)
//    TextView txt_seemore_petshandle;


    private int communication_type;
    private String userid;

    private String location;

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

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_clinic_details);
        ButterKnife.bind(this);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);


        avi_indicator.setVisibility(View.GONE);
        txt_seemore_spec.setVisibility(View.GONE);
       // txt_seemore_petshandle.setVisibility(View.GONE);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            doctorid = extras.getString("doctorid");
            doctorname = extras.getString("doctorname");
            distance = extras.getString("distance");
            fromactivity = extras.getString("fromactivity");
            communication_type = extras.getInt("communication_type");
            searchString = extras.getString("searchString");
            Log.w(TAG,"Bundle "+" doctorid : "+doctorid+ "fromactivity : "+fromactivity);
        }

        if(distance!=null&&!distance.isEmpty()){

            APIClient.DISTANCE = distance;
        }

        /*petcare*/
        title_care.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
        img_care.setImageResource(R.drawable.green_care);
        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);
        title_serv.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_serv.setImageResource(R.drawable.grey_servc);

        rl_home.setOnClickListener(this);
        rl_care.setOnClickListener(this);
        rl_service.setOnClickListener(this);
        rl_shop.setOnClickListener(this);
        rl_comn.setOnClickListener(this);
        rl_homes.setOnClickListener(this);

        ll_book_now.setOnClickListener(this);

        rl_back.setOnClickListener(this);

        avi_indicator.setVisibility(View.VISIBLE);

        viewPager.setVisibility(View.GONE);

        tabLayout.setVisibility(View.GONE);

        hand_img1.setVisibility(View.GONE);

        hand_img2.setVisibility(View.GONE);

        hand_img3.setVisibility(View.GONE);

        hand_img4.setVisibility(View.GONE);

        hand_img5.setVisibility(View.GONE);

        txt_clinicname.setVisibility(View.GONE);

        txt_drname.setVisibility(View.GONE);

        ll_root1.setVisibility(View.GONE);

        ll_root2.setVisibility(View.GONE);

        ll_root3.setVisibility(View.GONE);

        txt_about_vet_label.setVisibility(View.GONE);

        txt_dr_desc.setVisibility(View.GONE);

        txt_spec_label.setVisibility(View.GONE);

        rv_speclist.setVisibility(View.GONE);

   //     txt_pet_hanldle.setVisibility(View.GONE);

   //     rv_pet_hanldle.setVisibility(View.GONE);

        txt_location_label.setVisibility(View.GONE);

        txt_place.setVisibility(View.GONE);

        ll_map.setVisibility(View.GONE);


//
//        bottomSheetLayouts.setVisibility(View.GONE);


        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            if(doctorid != null){
                doctorDetailsResponseCall();
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
//        txt_seemore_petshandle.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View view) {
//                if(txt_seemore_petshandle.getText().toString() != null && txt_seemore_petshandle.getText().toString().equalsIgnoreCase("See more...")){
//                    txt_seemore_petshandle.setText("Hide");
//                    int size =specializationBeanList.size();
//                    setPetHandle(petHandledBeanList,size);
//                }else{
//                    txt_seemore_petshandle.setText("See more...");
//                    int size = 4;
//                    setPetHandle(petHandledBeanList,size);
//
//                }
//
//            }
//        });



//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        assert mapFragment != null;
//        mapFragment.getMapAsync(DoctorClinicDetailsActivity.this);
//
//
//
//        setBottomSheet();


        img_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                    if(doctorid != null){
                        createDoctorFavListResponseCall();
                    }

                }
            }
        });





    }


    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void createDoctorFavListResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.createDoctorFavListResponseCall(RestUtils.getContentType(),doctorFavCreateRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SuccessResponse Fav"+ "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    if(response.body().getCode() ==  200){
                        Toasty.success(getApplicationContext(),""+response.body().getMessage(),Toasty.LENGTH_SHORT).show();

                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                            if(doctorid != null){
                                doctorDetailsResponseCall();
                            }

                        }
                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"SuccessResponse fav flr"+"--->" + t.getMessage());
            }
        });

    }



    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private DoctorFavCreateRequest doctorFavCreateRequest() {
        /*
         * user_id : 603e262e2c2b43125f8cb801
         * doctor_id : 603e31a02c2b43125f8cb806
         */
        DoctorFavCreateRequest doctorFavCreateRequest = new DoctorFavCreateRequest();
        doctorFavCreateRequest.setUser_id(userid);
        doctorFavCreateRequest.setDoctor_id(doctorid);
        Log.w(TAG,"doctorFavCreateRequest"+ "--->" + new Gson().toJson(doctorFavCreateRequest));
        return doctorFavCreateRequest;
    }



    /**
     * method to setup the bottomsheet
     */
    private void setBottomSheet() {

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayouts));

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

        bottomSheetBehavior.setHideable(false);

        bottomSheetBehavior.setFitToContents(false);

        bottomSheetBehavior.setHalfExpandedRatio(0.7f);


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


    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void doctorDetailsResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<DoctorDetailsResponse> call = ApiService.doctorDetailsResponseCall(RestUtils.getContentType(),doctorDetailsRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<DoctorDetailsResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<DoctorDetailsResponse> call, @NonNull Response<DoctorDetailsResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"DoctorDetailsResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        if(response.body().getData().getClinic_pic() != null) {
                            doctorclinicdetailsResponseList = response.body().getData().getClinic_pic();
                        }
                        if(response.body().getData() != null) {
                            clinicname = response.body().getData().getClinic_name();
                            doctorname = response.body().getData().getDr_name();
                            reviewcount = response.body().getData().getReview_count();
                            starcount = response.body().getData().getStar_count();
                            amount = response.body().getData().getAmount();
                            Log.w(TAG, "amount : " + amount);
                            communicationtype = response.body().getData().getCommunication_type();
                            ClinicLocationname = response.body().getData().getClinic_loc();
                            Doctor_exp = response.body().getData().getDoctor_exp();

                            viewPager.setVisibility(View.VISIBLE);

                            tabLayout.setVisibility(View.VISIBLE);

                            hand_img1.setVisibility(View.VISIBLE);

                            hand_img2.setVisibility(View.VISIBLE);

                            hand_img3.setVisibility(View.VISIBLE);

                            hand_img4.setVisibility(View.VISIBLE);

                            hand_img5.setVisibility(View.VISIBLE);

                            txt_clinicname.setVisibility(View.VISIBLE);

                            txt_drname.setVisibility(View.VISIBLE);

                            ll_root1.setVisibility(View.VISIBLE);

                            ll_root2.setVisibility(View.VISIBLE);

                            ll_root3.setVisibility(View.VISIBLE);

                            txt_about_vet_label.setVisibility(View.VISIBLE);

                            txt_dr_desc.setVisibility(View.VISIBLE);

                            txt_spec_label.setVisibility(View.VISIBLE);

                            rv_speclist.setVisibility(View.VISIBLE);

//                            txt_pet_hanldle.setVisibility(View.VISIBLE);
//
//                            rv_pet_hanldle.setVisibility(View.VISIBLE);

                            txt_location_label.setVisibility(View.VISIBLE);

                            txt_place.setVisibility(View.VISIBLE);

                            ll_map.setVisibility(View.VISIBLE);


                            setBottomSheet();

//                            bottomSheetLayouts.setVisibility(View.VISIBLE);

                         //   setBottomSheet();


                            if(response.body().getData().getAmount() != 0){
                                txt_dr_consultationfees.setText("INR "+response.body().getData().getAmount());
                            }


                            avi_indicator.setVisibility(View.GONE);

//                            toolbar_header.setVisibility(View.VISIBLE);

                          /////////  scrollView.setVisibility(View.VISIBLE);

                          //  footerView.setVisibility(View.GONE);


                            if(response.body().getData().isFav()){
                                img_fav.setBackgroundResource(R.drawable.ic_fav);
                            }else{
                                img_fav.setBackgroundResource(R.drawable.heart_gray);
                            }
                        }

                        if(Doctor_exp != 0) {
                            Log.w(TAG,"Doctor_exp : "+Doctor_exp);
                            txt_dr_experience.setText(Doctor_exp+" Years");
                        }





                        if(clinicname != null){
                            txt_clinicname.setText(clinicname);
                        }
                        if(doctorname != null){
                            txt_drname.setText("DR "+doctorname);

                        }


                        if(starcount != null ){

                            if(starcount == 1){
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            } else if(starcount == 2){
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            }else if(starcount == 3){
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            }else if(starcount == 4){
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            } else if(starcount == 5){
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_color);
                            }

                        }
                        if(ClinicLocationname != null ){
                            txt_place.setText(ClinicLocationname+"");

                            location = response.body().getData().getClinic_loc();

                            latitude = response.body().getData().getClinic_lat();

                            longitude = response.body().getData().getClinic_long();

                            Log.w(TAG,"latitude"+ latitude );

                            Log.w(TAG,"longitude"+ longitude );

                            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.map);

                            assert mapFragment != null;

                            mapFragment.getMapAsync(DoctorClinicDetailsActivity.this);


                        }
                        if(distance != null && ClinicLocationname != null){
                            txt_distance.setText(""+distance);
                        }
                        else if(APIClient.DISTANCE != null && ClinicLocationname != null){
                            txt_distance.setText(""+APIClient.DISTANCE);
                        }


                        Log.w(TAG,"Size"+doctorclinicdetailsResponseList.size());
                        Log.w(TAG,"doctorclinicdetailsResponseList : "+new Gson().toJson(doctorclinicdetailsResponseList));

                        if(doctorclinicdetailsResponseList != null && doctorclinicdetailsResponseList.size()>0){

                           // cl_banner.setVisibility(View.VISIBLE);

                            for (int i = 0; i < doctorclinicdetailsResponseList.size(); i++) {
                                doctorclinicdetailsResponseList.get(i).getClinic_pic();
                                Log.w(TAG, "RES" + ", " +  doctorclinicdetailsResponseList.get(i).getClinic_pic());
                            }


                            viewpageData(doctorclinicdetailsResponseList);

                        }

                        Log.w(TAG," Descri : "+response.body().getData().getDescri());

                        if(response.body().getData().getDescri() != null){
                            txt_dr_desc.setText(response.body().getData().getDescri());

                        }


                        if(response.body().getData().getSpecialization() != null&&response.body().getData().getSpecialization().size()>0){

                           // specializationBeanList = new ArrayList<>();

                            specializationBeanList=response.body().getData().getSpecialization();

                            Log.w(TAG,"SpecilaziationList : "+new Gson().toJson(specializationBeanList));

                            setSpecList(specializationBeanList,4);



                        }

                        if(response.body().getData().getPet_handled() != null&&response.body().getData().getPet_handled().size()>0){

                            // specializationBeanList = new ArrayList<>();

                            petHandledBeanList=response.body().getData().getPet_handled();

                            Log.w(TAG,"petHandledBeanList : "+new Gson().toJson(petHandledBeanList));

                            setPetHandle(petHandledBeanList, 4);



                        }


                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<DoctorDetailsResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"DoctorDetailsResponse flr"+"--->" + t.getMessage());
            }
        });

    }



    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private DoctorDetailsRequest doctorDetailsRequest() {
        /*
         * user_id : 603e262e2c2b43125f8cb801
         * doctor_id : 603e31a02c2b43125f8cb806
         */
        DoctorDetailsRequest doctorDetailsRequest = new DoctorDetailsRequest();
        doctorDetailsRequest.setUser_id(userid);
        doctorDetailsRequest.setDoctor_id(doctorid);
        Log.w(TAG,"doctorDetailsRequest"+ "--->" + new Gson().toJson(doctorDetailsRequest));
        return doctorDetailsRequest;
    }
    private void viewpageData(List<DoctorDetailsResponse.DataBean.ClinicPicBean> doctorclinicdetailsResponseList) {
        tabLayout.setupWithViewPager(viewPager, true);

        ViewPagerClinicDetailsAdapter viewPagerClinicDetailsAdapter = new ViewPagerClinicDetailsAdapter(getApplicationContext(), doctorclinicdetailsResponseList);
        viewPager.setAdapter(viewPagerClinicDetailsAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == doctorclinicdetailsResponseList.size()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, false);
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(fromactivity != null && fromactivity.equalsIgnoreCase("PetCareFragment")){
            callDirections("4");
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PetLoverDoctorNewFavAdapter")){
            Intent intent = new Intent(DoctorClinicDetailsActivity.this,PetloverFavListActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(DoctorClinicDetailsActivity.this,PetLoverDashboardActivity.class);
            startActivity(intent);

        }



    }

    public void callDirections(String tag){
        Intent intent = new Intent(DoctorClinicDetailsActivity.this,PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        intent.putExtra("communication_type",communication_type);
        intent.putExtra("searchString",searchString);
        intent.putExtra("doctorid",doctorid);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_book_now:
                goto_PetAppointment_Doctor_Date_Time_Activity();
                break;
            case R.id.rl_back:
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

    private  void goto_PetAppointment_Doctor_Date_Time_Activity(){
        Intent intent = new Intent(DoctorClinicDetailsActivity.this,PetAppointment_Doctor_Date_Time_Activity.class);
        intent.putExtra("doctorid",doctorid);
        intent.putExtra("fromactivity",fromactivity);
        intent.putExtra("amount",amount);
        intent.putExtra("communicationtype",communicationtype);
        intent.putExtra("distance",distance);
        intent.putExtra("doctorname",doctorname);
        intent.putExtra("clinicname",clinicname);
        startActivity(intent);
    }

    private void setSpecList(List<DoctorDetailsResponse.DataBean.SpecializationBean> specializationBeanList,int size) {

        int spanCount = 2; // 3 columns
        int spacing = 0; // 50px
        boolean includeEdge = true;
        rv_speclist.setLayoutManager(new GridLayoutManager(this, 2));
        rv_speclist.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        rv_speclist.setItemAnimator(new DefaultItemAnimator());
        doctorClinicSpecTypesListAdapter = new DoctorClinicSpecTypesListAdapter(DoctorClinicDetailsActivity.this, specializationBeanList,size);
        rv_speclist.setAdapter(doctorClinicSpecTypesListAdapter);

        if(specializationBeanList.size()>4){
            txt_seemore_spec.setVisibility(View.VISIBLE);
        }else {
            txt_seemore_spec.setVisibility(View.GONE);
        }


    }


   private void setPetHandle(List<DoctorDetailsResponse.DataBean.PetHandledBean> petHandledBeanList, int size) {
//        int spanCount = 2; // 3 columns
//        int spacing = 0; // 50px
//        boolean includeEdge = true;
//        rv_pet_hanldle.setLayoutManager(new GridLayoutManager(this, 2));
//        rv_speclist.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
//        rv_pet_hanldle.setItemAnimator(new DefaultItemAnimator());
//        doctorClinicPetsHandledListAdapter = new DoctorClinicPetsHandledListAdapter(DoctorClinicDetailsActivity.this, petHandledBeanList,size);
//        rv_pet_hanldle.setAdapter(doctorClinicPetsHandledListAdapter);
//
//        if(petHandledBeanList.size()>4){
//            txt_seemore_petshandle.setVisibility(View.VISIBLE);
//        }else {
//            txt_seemore_petshandle.setVisibility(View.GONE);
//        }
//
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

}