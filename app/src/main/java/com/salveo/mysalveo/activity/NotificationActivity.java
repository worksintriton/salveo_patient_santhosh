package com.salveo.mysalveo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.NotificationDashboardAdapter;

import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.doctor.DoctorDashboardActivity;
import com.salveo.mysalveo.petlover.PetLoverDashboardActivity;
import com.salveo.mysalveo.requestpojo.NotificationGetlistRequest;
import com.salveo.mysalveo.requestpojo.NotificationsMarkRequest;
import com.salveo.mysalveo.responsepojo.NotificationGetlistResponse;
import com.salveo.mysalveo.responsepojo.SuccessResponse;
import com.salveo.mysalveo.serviceprovider.ServiceProviderDashboardActivity;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.salveo.mysalveo.vendor.VendorDashboardActivity;
import com.wang.avi.AVLoadingIndicatorView;


import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationActivity extends AppCompatActivity {


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tvNoRecords)
    TextView tvNorecords;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvnotifiaction)
    RecyclerView rvnotifiaction;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;





    private String TAG = "NotificationActivity";

    SessionManager session;
    String type = "",name = "",userid = "";
    private List<NotificationGetlistResponse.DataBean> notificationGetlistResponseList;
    private String fromactivity;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaton);
        Log.w(TAG,"onCreate-->");
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            fromactivity = extras.getString("fromactivity");
            Log.w(TAG,"Bundle "+" fromactivity : "+fromactivity);



        }



        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        type = user.get(SessionManager.KEY_TYPE);
        name = user.get(SessionManager.KEY_FIRST_NAME);
        userid = user.get(SessionManager.KEY_ID);


        Log.w(TAG,"session--->"+"type :"+type+" "+"name :"+" "+name);


        img_back.setOnClickListener(v -> onBackPressed());


        if (new ConnectionDetector(NotificationActivity.this).isNetworkAvailable(NotificationActivity.this)) {
            notificationGetlistResponseCall();
        }

        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (new ConnectionDetector(NotificationActivity.this).isNetworkAvailable(NotificationActivity.this)) {
                    notificationGetlistResponseCall();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @SuppressLint("LogNotTimber")
    private void notificationMarkResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.notificationMarkResponseCall(RestUtils.getContentType(),notificationsMarkRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"notificationMarkResponseCall SuccessResponse"+ "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"notificationMarkResponseCall SuccessResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private NotificationsMarkRequest notificationsMarkRequest() {
        /*
         * user_id : 5ee3666a5dfb34019b13c3a2
         */
        NotificationsMarkRequest notificationsMarkRequest = new NotificationsMarkRequest();
        notificationsMarkRequest.setUser_id(userid);
        Log.w(TAG,"notificationsMarkRequest"+ "--->" + new Gson().toJson(notificationsMarkRequest));
        return notificationsMarkRequest;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("PetLoverNavigationDrawerNew")){
            startActivity(new Intent(getApplicationContext(), PetLoverDashboardActivity.class));
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("DoctorNavigationDrawer")){
            startActivity(new Intent(getApplicationContext(), DoctorDashboardActivity.class));
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("VendorNavigationDrawer")){
            startActivity(new Intent(getApplicationContext(), VendorDashboardActivity.class));
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("ServiceProviderNavigationDrawer")){
            startActivity(new Intent(getApplicationContext(), ServiceProviderDashboardActivity.class));
            finish();
        }else{
            finish();
        }

    }
    private void setView() {
        rvnotifiaction.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvnotifiaction.setItemAnimator(new DefaultItemAnimator());
        NotificationDashboardAdapter notificationDashboardAdapter = new NotificationDashboardAdapter(getApplicationContext(), notificationGetlistResponseList);
        rvnotifiaction.setAdapter(notificationDashboardAdapter);

    }

    @SuppressLint("LogNotTimber")
    private void notificationGetlistResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<NotificationGetlistResponse> call = ApiService.notificationGetlistResponseCall(RestUtils.getContentType(),notificationGetlistRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<NotificationGetlistResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<NotificationGetlistResponse> call, @NonNull Response<NotificationGetlistResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"NotificationGetlistResponse"+ "--->" + new Gson().toJson(response.body()));
                refresh_layout.setRefreshing(false);

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        notificationMarkResponseCall();

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            notificationGetlistResponseList = response.body().getData();
                            tvNorecords.setVisibility(View.GONE);
                            rvnotifiaction.setVisibility(View.VISIBLE);
                            setView();
                        }else{
                            rvnotifiaction.setVisibility(View.GONE);
                            tvNorecords.setVisibility(View.VISIBLE);
                            tvNorecords.setText("No Notifications Available");

                        }


                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<NotificationGetlistResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"NotificationGetlistResponse"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private NotificationGetlistRequest notificationGetlistRequest() {
        /*
         * user_id : 5ee3666a5dfb34019b13c3a2
         */
        NotificationGetlistRequest notificationGetlistRequest = new NotificationGetlistRequest();
        notificationGetlistRequest.setUser_id(userid);
        Log.w(TAG,"notificationGetlistRequest"+ "--->" + new Gson().toJson(notificationGetlistRequest));
        return notificationGetlistRequest;
    }
}
