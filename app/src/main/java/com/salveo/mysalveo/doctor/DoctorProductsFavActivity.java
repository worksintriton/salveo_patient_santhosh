package com.salveo.mysalveo.doctor;

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
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.adapter.DoctorProductsFavAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;

import com.salveo.mysalveo.interfaces.ProductsFavListener;
import com.salveo.mysalveo.requestpojo.DoctorProductFavListCreateRequest;
import com.salveo.mysalveo.requestpojo.DoctorProductFavListRequest;

import com.salveo.mysalveo.responsepojo.DoctorProductFavListResponse;

import com.salveo.mysalveo.responsepojo.SuccessResponse;
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

public class DoctorProductsFavActivity extends AppCompatActivity implements View.OnClickListener, ProductsFavListener {

    private String TAG = "DoctorShopFavActivity";



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_product_list)
    RecyclerView rv_product_list;




    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_doctor_footer)
    View include_petlover_footer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;



    private String userid;
    private Dialog dialog;
    Dialog alertDialog;
    private String fromactivity;
    private List<DoctorProductFavListResponse.DataBean> productsList;

    /* Bottom Navigation */

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_service)
    RelativeLayout rl_service;

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

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_products_fav);
        ButterKnife.bind(this);


        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.favourites));

        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);

        avi_indicator.setVisibility(View.GONE);


        img_notification.setOnClickListener(this);
        img_profile.setOnClickListener(this);

        Log.w(TAG,"onCreate : ");
        img_back.setOnClickListener(this);

       // bottom_navigation_view.getMenu().findItem(R.id.home).setChecked(true);
        /*home*/

        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);

        rl_home.setOnClickListener(this);
        rl_shop.setOnClickListener(this);
        rl_comn.setOnClickListener(this);
        rl_homes.setOnClickListener(this);






        SessionManager  session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            doctorProductFavListResponseCall();
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

                case R.id.img_profile:
                    Intent  intent = new Intent(getApplicationContext(),DoctorProfileScreenActivity.class);
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

            case R.id.rl_comn:
                callDirections("3");
                break;

        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("DoctorNavigationDrawer")){
            startActivity(new Intent(getApplicationContext(), DoctorDashboardActivity.class));
            finish();
        }else {
            startActivity(new Intent(getApplicationContext(), DoctorDashboardActivity.class));
            finish();
        }
    }


    @SuppressLint("LogNotTimber")
    private void doctorProductFavListResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<DoctorProductFavListResponse> call = apiInterface.doctorProductFavListResponseCall(RestUtils.getContentType(), doctorProductFavListRequest());
        Log.w(TAG,"DoctorProductFavListResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<DoctorProductFavListResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<DoctorProductFavListResponse> call, @NonNull Response<DoctorProductFavListResponse> response) {

                Log.w(TAG,"DoctorProductFavListResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getData() != null && response.body().getData().isEmpty()){
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No products found");
                            rv_product_list.setVisibility(View.GONE);

                        }
                        else{
                            txt_no_records.setVisibility(View.GONE);
                            rv_product_list.setVisibility(View.VISIBLE);
                            if(response.body().getData() != null) {
                                productsList = response.body().getData();
                            }
                            setView();
                        }



                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<DoctorProductFavListResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"DoctorProductFavListResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    private DoctorProductFavListRequest doctorProductFavListRequest() {
        DoctorProductFavListRequest doctorProductFavListRequest = new DoctorProductFavListRequest();
        doctorProductFavListRequest.setUser_id(userid);
        Log.w(TAG,"doctorProductFavListRequest"+ "--->" + new Gson().toJson(doctorProductFavListRequest));
        return doctorProductFavListRequest;
    }
    private void setView() {
       GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
       rv_product_list.setLayoutManager(gridLayoutManager);
        rv_product_list.setItemAnimator(new DefaultItemAnimator());
        DoctorProductsFavAdapter doctorProductsFavAdapter = new DoctorProductsFavAdapter(getApplicationContext(), productsList,TAG,this);
        rv_product_list.setAdapter(doctorProductsFavAdapter);

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


    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), DoctorDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }

    @Override
    public void productsFavListener(boolean status, String productid) {
        Log.w(TAG,"productsFavListener : "+"status : "+status+" productid : "+productid);
        showProductFavStatusChangeAlert(productid);

    }

    @SuppressLint("SetTextI18n")
    private void showProductFavStatusChangeAlert(String productid) {
        try {
            dialog = new Dialog(DoctorProductsFavActivity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.removeproductfavmsg);
            Button dialogButtonApprove = dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(view -> {
                dialog.dismiss();

                doctorProductFavListCreateResponseCall(productid);


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
    private void doctorProductFavListCreateResponseCall(String productid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = apiInterface.doctorProductFavListCreateResponseCall(RestUtils.getContentType(),doctorProductFavListCreateRequest(productid));

        Log.w(TAG,"url  :%s"+call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<SuccessResponse> call, @NotNull Response<SuccessResponse> response) {
                Log.w(TAG,"SuccessResponse"+ "--->" + new Gson().toJson(response.body()));
                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Toasty.success(getApplicationContext(),""+response.body().getMessage(),Toasty.LENGTH_SHORT).show();

                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                            doctorProductFavListResponseCall();
                        }


                    }
                }



            }

            @Override
            public void onFailure(@NotNull Call<SuccessResponse> call, @NotNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"SuccessResponse"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private DoctorProductFavListCreateRequest doctorProductFavListCreateRequest(String productid) {

        /*
         * user_id : 6087d8626163803091258a5d
         * product_id : 60731580a959860485828fc5
         */

        DoctorProductFavListCreateRequest doctorProductFavListCreateRequest = new DoctorProductFavListCreateRequest();
        doctorProductFavListCreateRequest.setUser_id(userid);
        doctorProductFavListCreateRequest.setProduct_id(productid);
        Log.w(TAG,"doctorProductFavListCreateRequest"+ "--->" + new Gson().toJson(doctorProductFavListCreateRequest));
        return doctorProductFavListCreateRequest;
    }


}