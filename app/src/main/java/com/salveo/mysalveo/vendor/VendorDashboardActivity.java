package com.salveo.mysalveo.vendor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.fragmentvendor.FragmentVendorDashboard;
import com.salveo.mysalveo.fragmentvendor.VendorCommunityFragment;
import com.salveo.mysalveo.requestpojo.ShippingAddressFetchByUserIDRequest;
import com.salveo.mysalveo.responsepojo.ShippingAddressFetchByUserIDResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VendorDashboardActivity  extends VendorNavigationDrawer implements Serializable{

    private String TAG = "VendorDashboardActivity";


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_vendor_footer)
    View include_vendor_footer;

    /* Bottom Navigation */

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;

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


    final Fragment fragmentVendorDashboard = new FragmentVendorDashboard();

    final Fragment fragmentVendorCommunity = new VendorCommunityFragment();

    public static String active_tag = "1";


    Fragment active = fragmentVendorDashboard;
    String tag;

    String fromactivity;
    private int reviewcount;
    private String specialization;

    private static final int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private GoogleApiClient googleApiClient;
    Location mLastLocation;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private double latitude;
    private double longitude;
    public static String cityName;
    private Dialog dialog;
    private String userid;

    public static String orders;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_dashboard);
        ButterKnife.bind(this);
        Log.w(TAG,"onCreate-->");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            orders = bundle.getString("orders");
            Log.w(TAG,"orders : "+orders);
        }

//        bottom_navigation_view = include_vendor_footer.findViewById(R.id.bottom_navigation_view);
//        bottom_navigation_view.setItemIconTintList(null);
//        bottom_navigation_view.getMenu().findItem(R.id.home).setChecked(true);
//        bottom_navigation_view.setOnNavigationItemSelectedListener(this);

        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop_selector);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);


        avi_indicator.setVisibility(View.GONE);

        rl_home.setOnClickListener(this);

        rl_shop.setOnClickListener(this);

        rl_comn.setOnClickListener(this);


        rl_homes.setOnClickListener(this);


        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

      /*  if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            shippingAddressresponseCall();
        }*/

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            fromactivity = extras.getString("fromactivity");
            reviewcount = extras.getInt("reviewcount");
            specialization = extras.getString("specialization");


        }

        tag = getIntent().getStringExtra("tag");
        Log.w(TAG," tag : "+tag);
        if(tag != null){
            if(tag.equalsIgnoreCase("1")){
                active = fragmentVendorDashboard;
//                bottom_navigation_view.getMenu().findItem(R.id.home).setChecked(true);
                title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
                img_shop.setImageResource(R.drawable.grey_shop_selector);
                title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
                img_community.setImageResource(R.drawable.grey_community);
                loadFragment(new FragmentVendorDashboard());
            }else if(tag.equalsIgnoreCase("2")){
               // active = doctorShopFragment;
//                bottom_navigation_view.getMenu().findItem(R.id.feeds).setChecked(true);
                title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
                img_community.setImageResource(R.drawable.grey_community);
                title_shop.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
                img_shop.setImageResource(R.drawable.green_shop_selector);
                gotoManageProducts();
               // loadFragment(new DoctorShopFragment());
            } else if(tag.equalsIgnoreCase("3")){
//                showComingSoonAlert();
                active = fragmentVendorCommunity;
                title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
                img_shop.setImageResource(R.drawable.grey_shop_selector);
                title_community.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
                img_community.setImageResource(R.drawable.green_comm);
                loadFragment(new VendorCommunityFragment());
//                bottom_navigation_view.getMenu().findItem(R.id.community).setChecked(true);

            }
        }
        else{

            //bottom_navigation_view.getMenu().findItem(R.id.home).setChecked(true);
            title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
            img_shop.setImageResource(R.drawable.grey_shop_selector);
            title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
            img_community.setImageResource(R.drawable.grey_community);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_schedule, active, active_tag);
            transaction.commitNowAllowingStateLoss();
        }


    }



    private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        if(fromactivity != null){
            Log.w(TAG,"fromactivity loadFragment : "+fromactivity);

            if(fromactivity.equalsIgnoreCase("FiltersActivity")) {
                bundle.putString("fromactivity", fromactivity);
                bundle.putString("specialization", specialization);
                bundle.putInt("reviewcount", reviewcount);
                // set Fragmentclass Arguments
                fragment.setArguments(bundle);
                Log.w(TAG,"fromactivity : "+fromactivity);
                // load fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_schedule, fragment);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        }else {

            // set Fragmentclass Arguments
            fragment.setArguments(bundle);

            // load fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_schedule, fragment);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {
        Log.w(TAG,"tag : "+tag);
//        if (bottom_navigation_view.getSelectedItemId() == R.id.home) {
            showExitAppAlert();
          /*  new android.app.AlertDialog.Builder(PetLoverDashboardActivity.this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, id) -> PetLoverDashboardActivity.this.finishAffinity())
                    .setNegativeButton("No", null)
                    .show();*/
//        }
//        else if(tag != null ){
//            Log.w(TAG,"Else IF--->"+"fromactivity : "+fromactivity);
//            if(fromactivity != null){
//
//
//            }else{
//                bottom_navigation_view.setSelectedItemId(R.id.home);
//                // load fragment
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_schedule,new FragmentVendorDashboard());
//                transaction.commitNowAllowingStateLoss();
//            }
//
//
//        }
//        else{
//            bottom_navigation_view.setSelectedItemId(R.id.home);
//            // load fragment
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.frame_schedule,new FragmentVendorDashboard());
//            transaction.commitNowAllowingStateLoss();
//        }

    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_schedule,fragment);
        transaction.commitNowAllowingStateLoss();
    }

//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.home:
//                active_tag = "1";
//                replaceFragment(new FragmentVendorDashboard());
//                break;
//            case R.id.feeds:
//                gotoManageProducts();
//                active_tag = "2";
//                break;
//
//            case R.id.community:
//                showComingSoonAlert();
//                active_tag = "3";
//                break;
//
//            default:
//                return  false;
//        }
//        return true;
//    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.rl_homes:
                active_tag = "1";
                title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
                img_shop.setImageResource(R.drawable.grey_shop_selector);
                title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
                img_community.setImageResource(R.drawable.grey_community);
                replaceFragment(new FragmentVendorDashboard());
                break;

            case R.id.rl_home:
                active_tag = "1";
                title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
                img_shop.setImageResource(R.drawable.grey_shop_selector);
                title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
                img_community.setImageResource(R.drawable.grey_community);
                replaceFragment(new FragmentVendorDashboard());
                break;

            case R.id.rl_shop:
                active_tag = "2";
                title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
                img_community.setImageResource(R.drawable.grey_community);
                title_shop.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
                img_shop.setImageResource(R.drawable.green_shop_selector);
                gotoManageProducts();
                break;

            case R.id.rl_comn:
                active_tag = "3";
                title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
                img_shop.setImageResource(R.drawable.grey_shop);
                title_community.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
                img_community.setImageResource(R.drawable.green_comm);
                replaceFragment(new VendorCommunityFragment());
                break;
        }

    }




    private void showExitAppAlert() {
        try {

            dialog = new Dialog(VendorDashboardActivity.this);
            dialog.setContentView(R.layout.alert_exit_layout);
            Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
            Button btn_exit = dialog.findViewById(R.id.btn_exit);

            btn_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    VendorDashboardActivity.this.finishAffinity();
                }
            });
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }

    private void showComingSoonAlert() {

        try {

            Dialog dialog = new Dialog(VendorDashboardActivity.this);
            dialog.setContentView(R.layout.alert_comingsoon_layout);
            dialog.setCanceledOnTouchOutside(false);

            ImageView img_close = dialog.findViewById(R.id.img_close);
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }



    @SuppressLint("LogNotTimber")
    private void shippingAddressresponseCall() {
        /* avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShippingAddressFetchByUserIDResponse> call = apiInterface.fetch_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddressFetchByUserIDRequest());
        Log.w(TAG,"ShippingAddressFetchByUserIDResponse url  :%s"+" "+ call.request().url().toString());
        call.enqueue(new Callback<ShippingAddressFetchByUserIDResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShippingAddressFetchByUserIDResponse> call, @NonNull Response<ShippingAddressFetchByUserIDResponse> response) {
                Log.w(TAG,"ShippingAddressFetchByUserIDResponse"+ "--->" + new Gson().toJson(response.body()));
                //  avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(response.body().getCode() == 200) {
                        if(response.body().getData()!=null){
                            ShippingAddressFetchByUserIDResponse.DataBean dataBeanList = response.body().getData();

                            if(dataBeanList!=null) {
                                if(dataBeanList.isDefault_status()){
                                    Log.w(TAG,"true-->");
                                    String city = dataBeanList.getLocation_city();

                                }


                            }

                        }
                    }



                }




            }

            @Override
            public void onFailure(@NonNull Call<ShippingAddressFetchByUserIDResponse> call, @NonNull Throwable t) {

                //  avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddressFetchByUserIDResponse flr"+"--->" + t.getMessage());
            }
        });


    }
    @SuppressLint("LogNotTimber")
    private ShippingAddressFetchByUserIDRequest shippingAddressFetchByUserIDRequest() {
        /*
         * user_id : 6048589d0b3a487571a1c567
         */

        ShippingAddressFetchByUserIDRequest shippingAddressFetchByUserIDRequest = new ShippingAddressFetchByUserIDRequest();
        shippingAddressFetchByUserIDRequest.setUser_id(userid);

        Log.w(TAG,"shippingAddressFetchByUserIDRequest"+ "--->" + new Gson().toJson(shippingAddressFetchByUserIDRequest));
        return shippingAddressFetchByUserIDRequest;
    }








}