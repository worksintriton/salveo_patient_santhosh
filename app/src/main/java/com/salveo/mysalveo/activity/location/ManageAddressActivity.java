package com.salveo.mysalveo.activity.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.adapter.ManageAddressListAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.LocationDefaultListener;
import com.salveo.mysalveo.interfaces.LocationDeleteListener;
import com.salveo.mysalveo.petlover.PetLoverDashboardActivity;
import com.salveo.mysalveo.petlover.PetLoverProfileScreenActivity;
import com.salveo.mysalveo.requestpojo.LocationDeleteRequest;
import com.salveo.mysalveo.requestpojo.LocationListAddressRequest;
import com.salveo.mysalveo.requestpojo.LocationStatusChangeRequest;
import com.salveo.mysalveo.responsepojo.LocationDeleteResponse;
import com.salveo.mysalveo.responsepojo.LocationListAddressResponse;
import com.salveo.mysalveo.responsepojo.LocationStatusChangeResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageAddressActivity extends AppCompatActivity implements View.OnClickListener, LocationDeleteListener, LocationDefaultListener{

    private static final String TAG = "ManageAddressActivity";



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_adddress_list)
    RecyclerView rv_adddress_list;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_add_newaddress)
    LinearLayout ll_add_newaddress;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_savedaddress)
    TextView txt_savedaddress;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_footer)
    View include_petlover_footer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

//    BottomNavigationView bottom_navigation_view;

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


    private String userid;
    private List<LocationListAddressResponse.DataBean> addressList;
    private Dialog dialog;
    Dialog alertDialog;
    private String fromactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);
        ButterKnife.bind(this);


        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.manage_address));

        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);

        avi_indicator.setVisibility(View.GONE);


        img_notification.setOnClickListener(this);
        img_profile.setOnClickListener(this);

        Log.w(TAG,"onCreate : ");
        img_back.setOnClickListener(this);
        ll_add_newaddress.setOnClickListener(this);

//        bottom_navigation_view.getMenu().findItem(R.id.home).setChecked(true);

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




        SessionManager  session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
           locationListAddressResponseCall();
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");
        }




    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
                case R.id.ll_add_newaddress:
                gotoAddNewAddress();
                break;
                case R.id.img_profile:
                    Intent  intent = new Intent(getApplicationContext(),PetLoverProfileScreenActivity.class);
                    intent.putExtra("fromactivity",TAG);
                    startActivity(intent);
                break;
                case R.id.img_notification:
                    Intent  intent1 = new Intent(getApplicationContext(), NotificationActivity.class);
                    intent1.putExtra("fromactivity",TAG);
                    startActivity(intent1);
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

    private void gotoAddNewAddress() {
        Intent intent = new Intent(getApplicationContext(),PickUpLocationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("PetLoverNavigationDrawerNew")){
            startActivity(new Intent(getApplicationContext(), PetLoverDashboardActivity.class));
            finish();
        }else {
            startActivity(new Intent(getApplicationContext(), PetLoverProfileScreenActivity.class));
            finish();
        }
    }


    private void locationListAddressResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LocationListAddressResponse> call = apiInterface.locationListAddressResponseCall(RestUtils.getContentType(), locationListAddressRequest());
        Log.w(TAG,"locationListAddressResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<LocationListAddressResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<LocationListAddressResponse> call, @NonNull Response<LocationListAddressResponse> response) {

                Log.w(TAG,"locationListAddressResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getData() != null && response.body().getData().isEmpty()){
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No new address");
                            rv_adddress_list.setVisibility(View.GONE);
                            txt_savedaddress.setVisibility(View.GONE);
                        }
                        else{
                            txt_no_records.setVisibility(View.GONE);
                            rv_adddress_list.setVisibility(View.VISIBLE);
                            txt_savedaddress.setVisibility(View.VISIBLE);
                            if(response.body().getData() != null) {
                                addressList = response.body().getData();
                            }
                            txt_savedaddress.setText(addressList.size()+" Saved Address");
                            setView();
                        }



                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<LocationListAddressResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"locationListAddressResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    private LocationListAddressRequest locationListAddressRequest() {
        LocationListAddressRequest locationListAddressRequest = new LocationListAddressRequest();
        locationListAddressRequest.setUser_id(userid);
        Log.w(TAG,"locationListAddressRequest"+ "--->" + new Gson().toJson(locationListAddressRequest));
        return locationListAddressRequest;
    }
    private void setView() {
        rv_adddress_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_adddress_list.setItemAnimator(new DefaultItemAnimator());
        ManageAddressListAdapter manageAddressListAdapter = new ManageAddressListAdapter(getApplicationContext(), addressList,this,this,TAG);
        rv_adddress_list.setAdapter(manageAddressListAdapter);

    }


    @Override
    public void locationDeleteListener(boolean status, String locationid) {
        Log.w(TAG,"locationDeleteListener : "+" status : "+status+" locationid : "+locationid);

        if(!status){
            showStatusAlert(locationid);
        }else{
            Toasty.warning(getApplicationContext(), "Default location cannot be deleted.", Toast.LENGTH_SHORT, true).show();


        }
    }

    @SuppressLint("SetTextI18n")
    private void showStatusAlert(final String locationid) {

        try {
            dialog = new Dialog(ManageAddressActivity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.deletemsg);
            Button dialogButtonApprove = dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(view -> {
                dialog.dismiss();

                locationDeleteResponseCall(locationid);


            });
            dialogButtonRejected.setOnClickListener(view -> dialog.dismiss());
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    @SuppressLint("LogNotTimber")
    private void locationDeleteResponseCall(String locationid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();

        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LocationDeleteResponse> call = apiInterface.locationDeleteResponseCall(RestUtils.getContentType(),locationDeleteRequest(locationid));

        Log.w(TAG,"url  :%s"+call.request().url().toString());

        call.enqueue(new Callback<LocationDeleteResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<LocationDeleteResponse> call, @NotNull Response<LocationDeleteResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"LocationDeleteResponse"+ "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Toasty.success(getApplicationContext(), "Address Removed Successfully", Toast.LENGTH_SHORT, true).show();
                        finish();
                        overridePendingTransition( 0, 0);
                        startActivity(getIntent());
                        overridePendingTransition( 0, 0);

                    }
                }



            }

            @Override
            public void onFailure(@NotNull Call<LocationDeleteResponse> call, @NotNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"LocationDeleteResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private LocationDeleteRequest locationDeleteRequest(String locationid) {

        /*
          _id : 5f05d911f3090625a91f40c7
         */
        LocationDeleteRequest locationDeleteRequest = new LocationDeleteRequest();
        locationDeleteRequest.set_id(locationid);
        Log.w(TAG,"locationDeleteRequest"+ "--->" + new Gson().toJson(locationDeleteRequest));
        return locationDeleteRequest;
    }

    @Override
    public void locationDefaultListener(boolean status, String locationid,String userid) {
        showLocationStatusChangeAlert(locationid,userid);
    }

    @SuppressLint("SetTextI18n")
    private void showLocationStatusChangeAlert(String locationid, String userid) {

        try {

            dialog = new Dialog(ManageAddressActivity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.locationstatuschange);
            Button dialogButtonApprove = dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(view -> {
                dialog.dismiss();

                locationStatusChangeResponseCall(locationid,userid);


            });
            dialogButtonRejected.setOnClickListener(view -> {
                // Toasty.info(context, "Rejected Successfully", Toast.LENGTH_SHORT, true).show();
                dialog.dismiss();




            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    @SuppressLint("LogNotTimber")
    private void locationStatusChangeResponseCall(String locationid, String userid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LocationStatusChangeResponse> call = apiInterface.locationStatusChangeResponseCall(RestUtils.getContentType(),locationStatusChangeRequest(locationid,userid));

        Log.w(TAG,"url  :%s"+call.request().url().toString());

        call.enqueue(new Callback<LocationStatusChangeResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<LocationStatusChangeResponse> call, @NotNull Response<LocationStatusChangeResponse> response) {
                Log.w(TAG,"LocationStatusChangeResponse"+ "--->" + new Gson().toJson(response.body()));
                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Toasty.success(getApplicationContext(), "Default Location Changed Successfully", Toast.LENGTH_SHORT, true).show();
                        finish();
                        overridePendingTransition( 0, 0);
                        startActivity(getIntent());
                        overridePendingTransition( 0, 0);


                    }
                }



            }

            @Override
            public void onFailure(@NotNull Call<LocationStatusChangeResponse> call, @NotNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"LocationStatusChangeResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private LocationStatusChangeRequest locationStatusChangeRequest(String locationid, String userid) {

        /*
         * _id : 5fcf2d2a57c8326d894e4d7e
         * user_id : 5fc61b82b750da703e48da78
         * default_status : true
         */

        LocationStatusChangeRequest locationStatusChangeRequest = new LocationStatusChangeRequest();
        locationStatusChangeRequest.set_id(locationid);
        locationStatusChangeRequest.setUser_id(userid);
        locationStatusChangeRequest.setDefault_status(true);
        Log.w(TAG,"locationStatusChangeRequest"+ "--->" + new Gson().toJson(locationStatusChangeRequest));
        return locationStatusChangeRequest;
    }



    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
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
//
//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.home:
//                callDirections("1");
//                break;
//            case R.id.shop:
//                callDirections("2");
//                break;
//            case R.id.services:
//                callDirections("3");
//                break;
//            case R.id.care:
//                callDirections("4");
//                break;
//            case R.id.community:
//                callDirections("5");
//                break;
//
//            default:
//                return  false;
//        }
//        return true;
//    }
    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
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