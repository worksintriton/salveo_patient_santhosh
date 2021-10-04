package com.salveo.mysalveo.fragmentvendor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.VendorCancelledOrdersAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.OnAcceptsReturnOrder;
import com.salveo.mysalveo.requestpojo.VendorAcceptReturnOrderRequest;
import com.salveo.mysalveo.requestpojo.VendorGetsOrderIdRequest;
import com.salveo.mysalveo.requestpojo.VendorNewOrderRequest;
import com.salveo.mysalveo.responsepojo.VendorAcceptsReturnOrderResponse;
import com.salveo.mysalveo.responsepojo.VendorGetsOrderIDResponse;
import com.salveo.mysalveo.responsepojo.VendorNewOrderResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.salveo.mysalveo.vendor.VendorDashboardActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentVendorCancelledOrders extends Fragment implements View.OnClickListener, OnAcceptsReturnOrder {
    private String TAG = "FragmentVendorCancelledOrders";


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_missedappointment)
    RecyclerView rv_missedappointment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_load_more)
    Button btn_load_more;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_filter)
    Button btn_filter;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    Dialog alertDialog;


    SessionManager session;
    String type = "",username = "",userid = "";
    private SharedPreferences preferences;
    private Context mContext;
    private List<VendorNewOrderResponse.DataBean> newOrderResponseList;


    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;


    public FragmentVendorCancelledOrders() {

    }

    @SuppressLint("LogNotTimber")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView");

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view = inflater.inflate(R.layout.fragment_vendor_cancelled_orders, container, false);

        ButterKnife.bind(this, view);
        mContext = getActivity();

        includelayout = view.findViewById(R.id.includelayout);
        mShimmerViewContainer = includelayout.findViewById(R.id.shimmer_layout);

        avi_indicator.setVisibility(View.GONE);
        btn_load_more.setVisibility(View.GONE);
        btn_filter.setVisibility(View.GONE);

        btn_load_more.setOnClickListener(this);

        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        username = user.get(SessionManager.KEY_FIRST_NAME);

        Log.w(TAG,"userid"+userid +"username :"+username);

      

        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
            getVendorOrderIDResponseCall(userid);
        }

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    try {
                        //your method here
                            if(APIClient.VENDOR_ID != null && !APIClient.VENDOR_ID.isEmpty()) {
                                vendorNewOrderResponseCall(APIClient.VENDOR_ID);
                            }


                    } catch (Exception ignored) {
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 30000);//you can put 30000(30 secs)

        refresh_layout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                            if (APIClient.VENDOR_ID != null && !APIClient.VENDOR_ID.isEmpty()) {
                                vendorNewOrderResponseCall(APIClient.VENDOR_ID);
                            }
                        }
                    }
                }
        );


        return view;
    }


    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void getVendorOrderIDResponseCall(String userid) {
       /* avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        mShimmerViewContainer.startShimmerAnimation();

        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorGetsOrderIDResponse> call = apiInterface.vendor_gets_orderbyId_ResponseCall(RestUtils.getContentType(), vendorGetsOrderIdRequest(userid));
        Log.w(TAG,"getVendorOrderIDResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorGetsOrderIDResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<VendorGetsOrderIDResponse> call, @NonNull Response<VendorGetsOrderIDResponse> response) {

                Log.w(TAG,"getVendorOrderIDResponseCall"+ "--->" + new Gson().toJson(response.body()));

                //avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData()!=null){

                            if(response.body().getData().get_id()!=null&&!(response.body().getData().get_id().isEmpty())){
                                APIClient.VENDOR_ID = response.body().getData().get_id();
                                vendorNewOrderResponseCall(response.body().getData().get_id());

                            }


                        }


                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onFailure(@NonNull Call<VendorGetsOrderIDResponse> call, @NonNull Throwable t) {

              /*  avi_indicator.smoothToHide();*/
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.w(TAG,"getVendorOrderIDResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint("LogNotTimber")
    private VendorGetsOrderIdRequest vendorGetsOrderIdRequest(String userid) {

        VendorGetsOrderIdRequest vendorGetsOrderIdRequest = new VendorGetsOrderIdRequest();

        vendorGetsOrderIdRequest.setUser_id(userid);

        Log.w(TAG,"vendorGetsOrderIdRequest"+ "--->" + new Gson().toJson(vendorGetsOrderIdRequest));
        //  Toasty.success(getApplicationContext(),"fbTokenUpdateRequest : "+new Gson().toJson(fbTokenUpdateRequest), Toast.LENGTH_SHORT, true).show();

        return vendorGetsOrderIdRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
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



    @SuppressLint("LogNotTimber")
    private void vendorNewOrderResponseCall(String id) {
        /*avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorNewOrderResponse> call = ApiService.get_order_details_vendordid_ResponseCall(RestUtils.getContentType(),vendorNewOrderRequest(id));
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<VendorNewOrderResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<VendorNewOrderResponse> call, @NonNull Response<VendorNewOrderResponse> response) {
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                refresh_layout.setRefreshing(false);
                //avi_indicator.smoothToHide();
                Log.w(TAG,"VendorCancelledOrderResponse : "+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {

                    if(200 == response.body().getCode()){
                        newOrderResponseList = response.body().getData();
                        Log.w(TAG,"Size"+newOrderResponseList.size());
                        Log.w(TAG,"VendorCancelledOrderResponse : "+new Gson().toJson(newOrderResponseList));
                        if(response.body().getData().isEmpty()){
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No Cancelled orders");
                            rv_missedappointment.setVisibility(View.GONE);
                            btn_load_more.setVisibility(View.GONE);
                        }
                        else{
                            txt_no_records.setVisibility(View.GONE);
                            rv_missedappointment.setVisibility(View.VISIBLE);
                            if(newOrderResponseList.size()>3){
                                btn_load_more.setVisibility(View.VISIBLE);
                            }else{
                                btn_load_more.setVisibility(View.GONE);
                            }
                            setView();
                        }

                    }



                }
            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<VendorNewOrderResponse> call, @NonNull Throwable t) {
             /*   avi_indicator.smoothToHide();*/

                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.w(TAG,"VendorCancelledOrderResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private VendorNewOrderRequest vendorNewOrderRequest(String id) {
        /*
         * vendor_id : 604866a50b3a487571a1c568
         * order_status : New
         */

        VendorNewOrderRequest vendorNewOrderRequest = new VendorNewOrderRequest();
        vendorNewOrderRequest.setVendor_id(id);
        vendorNewOrderRequest.setOrder_status("Cancelled");
        Log.w(TAG,"VendorCancelledOrderRequest : "+ "--->" + new Gson().toJson(vendorNewOrderRequest));
        return vendorNewOrderRequest;
    }

    private void setView() {
        rv_missedappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_missedappointment.setItemAnimator(new DefaultItemAnimator());
        int size = 3;
        VendorCancelledOrdersAdapter vendorCancelledOrdersAdapter = new VendorCancelledOrdersAdapter(getContext(), newOrderResponseList,size,this);
        rv_missedappointment.setAdapter(vendorCancelledOrdersAdapter);

    }
    private void setViewLoadMore() {
        rv_missedappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_missedappointment.setItemAnimator(new DefaultItemAnimator());
        int size = newOrderResponseList.size();
        VendorCancelledOrdersAdapter vendorCancelledOrdersAdapter = new VendorCancelledOrdersAdapter(getContext(), newOrderResponseList,size,this);
        rv_missedappointment.setAdapter(vendorCancelledOrdersAdapter);

    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_load_more) {
            setViewLoadMore();
        }
    }

    @Override
    public void string(String order_id) {

        VendorAcceptReturnOrderIDResponseCall(order_id);
    }


    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void VendorAcceptReturnOrderIDResponseCall(String order_id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorAcceptsReturnOrderResponse> call = apiInterface.update_status_vendor_accept_returnResponseCall(RestUtils.getContentType(), vendorAcceptReturnOrderRequest(order_id) );
        Log.w(TAG,"VendorAcceptsReturnOrderResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorAcceptsReturnOrderResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<VendorAcceptsReturnOrderResponse> call, @NonNull Response<VendorAcceptsReturnOrderResponse> response) {

                Log.w(TAG,"VendorAcceptsReturnOrderResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData()!=null){


                            startActivity(new Intent(getContext(), VendorDashboardActivity.class));

                        }


                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onFailure(@NonNull Call<VendorAcceptsReturnOrderResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"VendorAcceptsReturnOrderResponse flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint("LogNotTimber")
    private VendorAcceptReturnOrderRequest vendorAcceptReturnOrderRequest(String order_id) {

        /*
         * _id : 6053b5e0d7570364e4d28c98
         * activity_id : 6
         * activity_title : Vendor Accept Return
         * activity_date : 11-03-2021 03:07 PM
         * vendor_accept_cancel : Ok I will accept the Return
         * vendor_accept_cancel_date : 11-03-2021 03:07 PM
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());



        VendorAcceptReturnOrderRequest vendorAcceptReturnOrderRequest = new VendorAcceptReturnOrderRequest();

        vendorAcceptReturnOrderRequest.set_id(order_id);

        vendorAcceptReturnOrderRequest.setActivity_id(6);

        vendorAcceptReturnOrderRequest.setActivity_title("Vendor Accept Return");

        vendorAcceptReturnOrderRequest.setActivity_date(currentDateandTime);

        vendorAcceptReturnOrderRequest.setVendor_accept_cancel("Ok I will accept the Return");

        vendorAcceptReturnOrderRequest.setVendor_accept_cancel_date(currentDateandTime);

        Log.w(TAG,"vendorAcceptReturnOrderRequest"+ "--->" + new Gson().toJson(vendorAcceptReturnOrderRequest));
        //  Toasty.success(getApplicationContext(),"fbTokenUpdateRequest : "+new Gson().toJson(fbTokenUpdateRequest), Toast.LENGTH_SHORT, true).show();

        return vendorAcceptReturnOrderRequest;
    }

}