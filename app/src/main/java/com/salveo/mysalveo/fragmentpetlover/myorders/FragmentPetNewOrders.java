package com.salveo.mysalveo.fragmentpetlover.myorders;

import android.annotation.SuppressLint;
import android.content.Context;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.PetVendorNewOrdersAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;

import com.salveo.mysalveo.requestpojo.PetVendorOrderRequest;

import com.salveo.mysalveo.responsepojo.PetVendorOrderResponse;
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


public class FragmentPetNewOrders extends Fragment implements  View.OnClickListener {
    private final String TAG = "FragmentPetNewOrders";

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


    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;


    SessionManager session;
    String userid = "";
    private List<PetVendorOrderResponse.DataBean> newOrderResponseList;
    Context mContext;


    public FragmentPetNewOrders() {

    }

    @SuppressLint("LogNotTimber")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView");

        View view = inflater.inflate(R.layout.fragment_pet_new_appointment, container, false);

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
        Log.w(TAG," userid : "+userid);

      

        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
            get_order_details_user_id_ResponseCall();
        }

        refresh_layout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                            get_order_details_user_id_ResponseCall();

                        }

                    }
                }
        );


        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    try {
                        //your method here
                        get_order_details_user_id_ResponseCall();
                    }catch (Exception ignored) {
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 30000);//you can put 30000(30 secs)

        return view;
    }



    @SuppressLint("LogNotTimber")
    private void get_order_details_user_id_ResponseCall() {
       /* avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        mShimmerViewContainer.startShimmerAnimation();

        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PetVendorOrderResponse> call = ApiService.get_order_details_user_id_ResponseCall(RestUtils.getContentType(),petVendorOrderRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<PetVendorOrderResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<PetVendorOrderResponse> call, @NonNull Response<PetVendorOrderResponse> response) {
             /*  avi_indicator.smoothToHide();*/
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                refresh_layout.setRefreshing(false);
                Log.w(TAG,"PetVendorOrderResponse"+ "--->" + new Gson().toJson(response.body()));


               if (response.body() != null) {

                   if(200 == response.body().getCode()) {
                       if (response.body().getData() != null ) {
                           newOrderResponseList = response.body().getData();
                           Log.w(TAG, "Size" + newOrderResponseList.size());
                           Log.w(TAG, "newOrderResponseList : " + new Gson().toJson(newOrderResponseList));
                           if (response.body().getData().isEmpty()) {
                               txt_no_records.setVisibility(View.VISIBLE);
                               txt_no_records.setText("No new orders");
                               rv_newappointment.setVisibility(View.GONE);
                               btn_load_more.setVisibility(View.GONE);
                           } else {
                               txt_no_records.setVisibility(View.GONE);
                               rv_newappointment.setVisibility(View.VISIBLE);
                               if (newOrderResponseList.size() > 3) {
                                   btn_load_more.setVisibility(View.VISIBLE);
                               } else {
                                   btn_load_more.setVisibility(View.GONE);
                               }
                               setView();
                           }

                       }
                   }



                }
            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<PetVendorOrderResponse> call, @NonNull Throwable t) {
             /*   avi_indicator.smoothToHide();*/
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);

                Log.w(TAG,"PetVendorOrderResponse"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private PetVendorOrderRequest petVendorOrderRequest() {
        /*
         * user_id : 603e27792c2b43125f8cb802
         * order_status : New
         */
        PetVendorOrderRequest petVendorOrderRequest = new PetVendorOrderRequest();
        petVendorOrderRequest.setUser_id(userid);
        petVendorOrderRequest.setOrder_status("New");
        Log.w(TAG,"petVendorOrderRequest"+ "--->" + new Gson().toJson(petVendorOrderRequest));
        return petVendorOrderRequest;
    }
    private void setView() {
        rv_newappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_newappointment.setItemAnimator(new DefaultItemAnimator());
        int size = 3;
        PetVendorNewOrdersAdapter petVendorNewOrdersAdapter = new PetVendorNewOrdersAdapter(mContext, newOrderResponseList,size);
        rv_newappointment.setAdapter(petVendorNewOrdersAdapter);

    }
    private void setViewLoadMore() {
        rv_newappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_newappointment.setItemAnimator(new DefaultItemAnimator());
        int size = newOrderResponseList.size();
        PetVendorNewOrdersAdapter petVendorNewOrdersAdapter = new PetVendorNewOrdersAdapter(mContext, newOrderResponseList,size);
        rv_newappointment.setAdapter(petVendorNewOrdersAdapter);

    }




    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_load_more) {
            setViewLoadMore();
        }
    }
}