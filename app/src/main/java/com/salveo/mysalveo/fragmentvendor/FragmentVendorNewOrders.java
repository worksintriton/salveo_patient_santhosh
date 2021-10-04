package com.salveo.mysalveo.fragmentvendor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
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
import com.salveo.mysalveo.adapter.VendorNewOrdersAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;

import com.salveo.mysalveo.requestpojo.VendorGetsOrderIdRequest;
import com.salveo.mysalveo.requestpojo.VendorNewOrderRequest;

import com.salveo.mysalveo.responsepojo.VendorGetsOrderIDResponse;
import com.salveo.mysalveo.responsepojo.VendorNewOrderResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentVendorNewOrders extends Fragment implements View.OnClickListener {
    private String TAG = "FragmentVendorNewOrders";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_newappointment)
    RecyclerView rv_newappointment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_load_more)
    Button btn_load_more;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;


    SessionManager session;
    String type = "",username = "",userid = "";
    private SharedPreferences preferences;
    private Context mContext;
    private Dialog dialog;
    private List<VendorNewOrderResponse.DataBean> newOrderResponseList;
    Dialog alertDialog;

    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;

    public FragmentVendorNewOrders() {

    }

    @SuppressLint("LogNotTimber")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView");

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view = inflater.inflate(R.layout.fragment_vendor_new_orders, container, false);

        ButterKnife.bind(this, view);
        mContext = getActivity();

        includelayout = view.findViewById(R.id.includelayout);
        mShimmerViewContainer = includelayout.findViewById(R.id.shimmer_layout);

        avi_indicator.setVisibility(View.GONE);
        btn_load_more.setVisibility(View.GONE);
        btn_load_more.setOnClickListener(this);

        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        username = user.get(SessionManager.KEY_FIRST_NAME);

        Log.w(TAG,"userid"+userid +"username :"+username);


        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {

           // vendorNewOrderResponseCall();

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
                        if (APIClient.VENDOR_ID != null && !APIClient.VENDOR_ID.isEmpty()) {
                            vendorNewOrderResponseCall(APIClient.VENDOR_ID);
                        }

                    }catch (Exception ignored) {
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
                            if(APIClient.VENDOR_ID != null && !APIClient.VENDOR_ID.isEmpty()) {
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
     /*   avi_indicator.setVisibility(View.VISIBLE);
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

               // avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData()!=null){

                            if(response.body().getData().get_id()!=null&&!(response.body().getData().get_id().isEmpty())){
                                APIClient.VENDOR_ID = response.body().getData().get_id();
                                Log.w(TAG,"VENDOR_ID : "+response.body().getData().get_id());
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

             /*   avi_indicator.smoothToHide();*/
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
       /* avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorNewOrderResponse> call = ApiService.get_order_details_vendordid_ResponseCall(RestUtils.getContentType(),vendorNewOrderRequest(id));
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<VendorNewOrderResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<VendorNewOrderResponse> call, @NonNull Response<VendorNewOrderResponse> response) {
              // avi_indicator.smoothToHide();

                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                refresh_layout.setRefreshing(false);
                Log.w(TAG,"VendorNewOrderResponse"+ "--->" + new Gson().toJson(response.body()));


               if (response.body() != null) {

                   if(200 == response.body().getCode()){
                       newOrderResponseList = response.body().getData();
                       Log.w(TAG,"Size"+newOrderResponseList.size());
                       Log.w(TAG,"newOrderResponseList : "+new Gson().toJson(newOrderResponseList));
                       if(response.body().getData().isEmpty()){
                           txt_no_records.setVisibility(View.VISIBLE);
                           txt_no_records.setText("No new orders");
                           rv_newappointment.setVisibility(View.GONE);
                           btn_load_more.setVisibility(View.GONE);
                       }
                       else{
                           txt_no_records.setVisibility(View.GONE);
                           rv_newappointment.setVisibility(View.VISIBLE);
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

            @Override
            public void onFailure(@NonNull Call<VendorNewOrderResponse> call, @NonNull Throwable t) {
               // avi_indicator.smoothToHide();

                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);

                Log.w(TAG,"VendorOrderResponse flr"+"--->" + t.getMessage());
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
        vendorNewOrderRequest.setOrder_status("New");
        Log.w(TAG,"vendorNewOrderRequest"+ "--->" + new Gson().toJson(vendorNewOrderRequest));
        return vendorNewOrderRequest;
    }
    private void setView() {
        rv_newappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_newappointment.setItemAnimator(new DefaultItemAnimator());
        int size = 3;
        VendorNewOrdersAdapter vendorNewOrdersAdapter = new VendorNewOrdersAdapter(getContext(), newOrderResponseList,size);
        rv_newappointment.setAdapter(vendorNewOrdersAdapter);

    }
    private void setViewLoadMore() {
        rv_newappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_newappointment.setItemAnimator(new DefaultItemAnimator());
        int size = newOrderResponseList.size();
        VendorNewOrdersAdapter vendorNewOrdersAdapter = new VendorNewOrdersAdapter(getContext(), newOrderResponseList,size);
        rv_newappointment.setAdapter(vendorNewOrdersAdapter);

    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_load_more) {
            setViewLoadMore();
        }
    }

}