package com.salveo.mysalveo.petlover;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.PetLoverCancelOrderRequest;
import com.salveo.mysalveo.requestpojo.PetLoverCancelSingleOrderRequest;
import com.salveo.mysalveo.requestpojo.UpdateStatusCancelRequest;
import com.salveo.mysalveo.responsepojo.DropDownListResponse;
import com.salveo.mysalveo.responsepojo.SuccessResponse;
import com.salveo.mysalveo.responsepojo.VendorOrderUpdateResponse;
import com.salveo.mysalveo.responsepojo.VendorReasonListResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetVendorCancelOrderActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "PetVendorCancelOrderActivity" ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spr_reason)
    Spinner spr_reason;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_cancel_order)
    Button btn_cancel_order;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_comment)
    EditText edt_comment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_footer)
    View include_petlover_footer;

    BottomNavigationView bottom_navigation_view;

    private List<DropDownListResponse.DataBean.SpecialzationBean> petSpecilaziationList;
    private String _id;
    HashMap<String, String> hashMap_ReasonTypeid = new HashMap<>();
    private String strSelectedReason = "";
    private Dialog dialog;

    String User_cancell_info = "";
    private String cancelorder;

    ArrayList<Integer> product_idList;
    private int product_id;
    private String orderid;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    /* Petlover Bottom Navigation */

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




    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_vendor_cancel_order);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _id = extras.getString("_id");
            orderid = extras.getString("orderid");
            product_id = extras.getInt("product_id");
            cancelorder = extras.getString("cancelorder");
            Log.w(TAG,"_id : "+_id);
            product_idList = getIntent().getIntegerArrayListExtra("product_idList");
            Log.w(TAG,"product_idList : "+ new Gson().toJson(product_idList)+" cancelorder :"+cancelorder);

        }


        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.cancel_order));

        img_back.setOnClickListener(this);

        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
        edt_comment.setVisibility(View.GONE);

        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PetLoverProfileScreenActivity.class);
                intent.putExtra("_id",_id);
                intent.putExtra("orderid",orderid);
                intent.putExtra("product_id",product_id);
                intent.putExtra("cancelorder",cancelorder);
                intent.putExtra("product_idList",product_idList);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);
            }
        });

//        bottom_navigation_view = include_petlover_footer.findViewById(R.id.bottom_navigation_view);
//        bottom_navigation_view.setItemIconTintList(null);
//        bottom_navigation_view.setOnNavigationItemSelectedListener(this);
//        bottom_navigation_view.getMenu().findItem(R.id.shop).setChecked(true);

        /*shop*/
        title_care.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_care.setImageResource(R.drawable.grey_care);
        title_serv.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_serv.setImageResource(R.drawable.grey_servc);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);
        title_shop.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
        img_shop.setImageResource(R.drawable.green_shop);


        rl_home.setOnClickListener(this);

        rl_care.setOnClickListener(this);

        rl_service.setOnClickListener(this);

        rl_shop.setOnClickListener(this);

        rl_comn.setOnClickListener(this);


        rl_homes.setOnClickListener(this);




        if (new ConnectionDetector(PetVendorCancelOrderActivity.this).isNetworkAvailable(PetVendorCancelOrderActivity.this)) {
            vendorReasonListResponseCall();

        }

        spr_reason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
                strSelectedReason = spr_reason.getSelectedItem().toString();
                Log.w(TAG,"strSelectedReason : "+strSelectedReason);

                if(strSelectedReason != null){
                    if(strSelectedReason.equalsIgnoreCase("Other")){
                        edt_comment.setVisibility(View.VISIBLE);
                    }else{
                        edt_comment.setVisibility(View.GONE);
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        btn_cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCancelAlert();
            }
        });





    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @SuppressLint({"LogNotTimber", "LongLogTag"})
    public void vendorReasonListResponseCall(){

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorReasonListResponse> call = apiInterface.vendorReasonListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<VendorReasonListResponse>() {
            @SuppressLint({"SetTextI18n", "LongLogTag"})
            @Override
            public void onResponse(@NonNull Call<VendorReasonListResponse> call, @NonNull Response<VendorReasonListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"DropDownListResponse" + new Gson().toJson(response.body()));
                        if(response.body().getData().getCancel_status() != null &&response.body().getData().getCancel_status().size()>0 ) {
                            setReasonList(response.body().getData().getCancel_status());
                        }




                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<VendorReasonListResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"VendorReasonListResponse flr"+t.getMessage());
            }
        });

    }
    @SuppressLint("LongLogTag")
    private void setReasonList(List<VendorReasonListResponse.DataBean.CancelStatusBean> cancel_status) {
        ArrayList<String> pettypeArrayList = new ArrayList<>();
       // pettypeArrayList.add("Select Pet Type");
        for (int i = 0; i < cancel_status.size(); i++) {
            String petType = cancel_status.get(i).getTitle();
            Log.w(TAG,"petType-->"+petType);
            pettypeArrayList.add(petType);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(PetVendorCancelOrderActivity.this, R.layout.spinner_item, pettypeArrayList);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
            spr_reason.setAdapter(spinnerArrayAdapter);


        }
    }
    private void showCancelAlert() {

        try {

             dialog = new Dialog(PetVendorCancelOrderActivity.this);
            dialog.setContentView(R.layout.alert_cancel_layout);
            dialog.setCanceledOnTouchOutside(false);
            Button btn_ok = dialog.findViewById(R.id.btn_ok);
            Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (new ConnectionDetector(PetVendorCancelOrderActivity.this).isNetworkAvailable(PetVendorCancelOrderActivity.this)) {
                        if(cancelorder != null && cancelorder.equalsIgnoreCase("bulk")){
                            petlover_update_order_cancel_ResponseCall(product_idList);
                        }else{
                            petlover_update_order_cancel_single_ResponseCall();
                        }


                    }






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


    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void update_status_cancelResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = apiInterface.update_status_cancelResponseCall(RestUtils.getContentType(), updateStatusCancelRequest());
        Log.w(TAG,"vendorOrderDetailsResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {

                Log.w(TAG,"update_status_cancelResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        dialog.dismiss();
                        startActivity(new Intent(PetVendorCancelOrderActivity.this,PetMyOrdrersActivity.class));
                        finish();


                    }

                }


            }

            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"update_status_cancelResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private UpdateStatusCancelRequest updateStatusCancelRequest() {
        /*
         * _id : 604f4386a358454d3208f685
         * activity_id : 4
         * activity_title : Order Cancelled
         * activity_date : 11-03-2021 03:07 PM
         * order_status : Cancelled
         * user_cancell_info : I have order wrongly
         * user_cancell_date : 11-03-2021 03:07 PM
         */


        if(strSelectedReason.equalsIgnoreCase("Other")){
            edt_comment.setVisibility(View.VISIBLE);
            User_cancell_info = edt_comment.getText().toString();
        }else{
            User_cancell_info = strSelectedReason;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        UpdateStatusCancelRequest updateStatusCancelRequest = new UpdateStatusCancelRequest();
        updateStatusCancelRequest.set_id(_id);
        updateStatusCancelRequest.setActivity_id(4);
        updateStatusCancelRequest.setActivity_title("Order Cancelled");
        updateStatusCancelRequest.setActivity_date(currentDateandTime);
        updateStatusCancelRequest.setOrder_status("Cancelled");
        updateStatusCancelRequest.setUser_cancell_info(User_cancell_info);
        updateStatusCancelRequest.setUser_cancell_date(currentDateandTime);

        Log.w(TAG,"updateStatusCancelRequest"+ "--->" + new Gson().toJson(updateStatusCancelRequest));

        return updateStatusCancelRequest;
    }


    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void petlover_update_order_cancel_ResponseCall(List<Integer> product_id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderUpdateResponse> call = apiInterface.petlover_update_order_cancel_ResponseCall(RestUtils.getContentType(), petLoverCancelOrderRequest(product_id));
        Log.w(TAG,"petlover_update_order_cancel_ResponseCall url  :%s"+" "+ call.request().url().toString());
        call.enqueue(new Callback<VendorOrderUpdateResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Response<VendorOrderUpdateResponse> response) {
                Log.w(TAG,"petlover_update_order_cancel_ResponseCall"+ "--->" + new Gson().toJson(response.body()));
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        dialog.dismiss();
                        Intent intent = new Intent(PetVendorCancelOrderActivity.this,PetLoverVendorOrderDetailsActivity.class);
                        intent.putExtra("_id",orderid);
                        startActivity(intent);
                        finish();


                    }

                }


            }

            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onFailure(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"vendor_update_order_confirm_ResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private PetLoverCancelOrderRequest petLoverCancelOrderRequest(List<Integer> product_id) {
        /*
         * order_id : ORDER-1618919599393
         * product_id : [0,1,2]
         * date : 20-04-2021 12:47 PM
         * reject_reason :
         */

        if(strSelectedReason.equalsIgnoreCase("Other")){
            edt_comment.setVisibility(View.VISIBLE);
            User_cancell_info = edt_comment.getText().toString();
        }else{
            User_cancell_info = strSelectedReason;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        PetLoverCancelOrderRequest petLoverCancelOrderRequest = new PetLoverCancelOrderRequest();
        petLoverCancelOrderRequest.setOrder_id(orderid);
        petLoverCancelOrderRequest.setProduct_id(product_id);
        petLoverCancelOrderRequest.setDate(currentDateandTime);
        petLoverCancelOrderRequest.setReject_reason(User_cancell_info);
        Log.w(TAG,"petLoverCancelOrderRequest"+ "--->" + new Gson().toJson(petLoverCancelOrderRequest));
        return petLoverCancelOrderRequest;
    }
   @SuppressLint({"LongLogTag", "LogNotTimber"})

    private void petlover_update_order_cancel_single_ResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderUpdateResponse> call = apiInterface.petlover_update_order_cancel_single_ResponseCall(RestUtils.getContentType(), petLoverCancelSingleOrderRequest());
        Log.w(TAG,"vendor_update_order_confirm_ResponseCall url  :%s"+" "+ call.request().url().toString());
        call.enqueue(new Callback<VendorOrderUpdateResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Response<VendorOrderUpdateResponse> response) {
                Log.w(TAG,"vendorOrderDetailsResponseCall"+ "--->" + new Gson().toJson(response.body()));
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        dialog.dismiss();
                        Intent intent = new Intent(PetVendorCancelOrderActivity.this,PetLoverVendorOrderDetailsActivity.class);
                        intent.putExtra("_id",orderid);
                        startActivity(intent);
                        finish();


                    }

                }


            }

            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onFailure(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"vendor_update_order_confirm_ResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private PetLoverCancelSingleOrderRequest petLoverCancelSingleOrderRequest() {
        /*
         * order_id : ORDER-1618919599393
         * product_id : 0
         * date : 20-04-2021 12:47 PM
         * reject_reason :
         */

        if(strSelectedReason.equalsIgnoreCase("Other")){
            edt_comment.setVisibility(View.VISIBLE);
            User_cancell_info = edt_comment.getText().toString();
        }else{
            User_cancell_info = strSelectedReason;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        PetLoverCancelSingleOrderRequest petLoverCancelSingleOrderRequest = new PetLoverCancelSingleOrderRequest();
        petLoverCancelSingleOrderRequest.setOrder_id(orderid);
        petLoverCancelSingleOrderRequest.setProduct_id(product_id);
        petLoverCancelSingleOrderRequest.setDate(currentDateandTime);
        petLoverCancelSingleOrderRequest.setReject_reason(User_cancell_info);
        Log.w(TAG,"petLoverCancelSingleOrderRequest"+ "--->" + new Gson().toJson(petLoverCancelSingleOrderRequest));
        return petLoverCancelSingleOrderRequest;
    }


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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

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

            case R.id.img_back:
                onBackPressed();
                break;
        }
    }


    private void setMargins(RelativeLayout rl_layout, int i, int i1, int i2, int i3) {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rl_layout.getLayoutParams();
        params.setMargins(i, i1, i2, i3);
        rl_layout.setLayoutParams(params);
    }

    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }
}