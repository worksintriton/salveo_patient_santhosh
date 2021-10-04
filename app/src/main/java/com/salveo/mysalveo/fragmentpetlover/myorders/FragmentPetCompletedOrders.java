package com.salveo.mysalveo.fragmentpetlover.myorders;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.RatingBar;
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
import com.salveo.mysalveo.adapter.PetVendorCompletedOrdersAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;

import com.salveo.mysalveo.interfaces.AddReviewListener;
import com.salveo.mysalveo.requestpojo.AddShopReviewRequest;
import com.salveo.mysalveo.requestpojo.PetVendorOrderRequest;

import com.salveo.mysalveo.responsepojo.AddReviewResponse;
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


public class FragmentPetCompletedOrders extends Fragment implements View.OnClickListener, AddReviewListener {
    private final String TAG = "FragmentPetCompletedOrders";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_completedappointment)
    RecyclerView rv_completedappointment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_load_more)
    Button btn_load_more;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_filter)
    Button btn_filter;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;




    SessionManager session;
    String doctorid = "";

    private String userid;
    Dialog alertDialog;

    private List<PetVendorOrderResponse.DataBean> newOrderResponseList;
    Context mContext;
    private String userrate;


    public FragmentPetCompletedOrders() {

    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView");

        View view = inflater.inflate(R.layout.fragment_pet_completed_appointment, container, false);

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
        Log.w(TAG," userid : "+userid);

        String patientname = user.get(SessionManager.KEY_FIRST_NAME);

        Log.w(TAG,"Doctorid"+doctorid +"patientname :"+patientname);



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





    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_load_more) {
            setViewLoadMore();
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


    @SuppressLint({"LogNotTimber", "LongLogTag"})
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
                refresh_layout.setRefreshing(false);
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.w(TAG,"PetVendorOrderResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {

                    if(200 == response.body().getCode()) {
                        if (response.body().getData() != null ) {
                            newOrderResponseList = response.body().getData();
                            Log.w(TAG, "Size" + newOrderResponseList.size());
                            Log.w(TAG, "newOrderResponseList : " + new Gson().toJson(newOrderResponseList));
                            if (response.body().getData().isEmpty()) {
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No completed orders");
                                rv_completedappointment.setVisibility(View.GONE);
                                btn_load_more.setVisibility(View.GONE);
                            } else {
                                txt_no_records.setVisibility(View.GONE);
                                rv_completedappointment.setVisibility(View.VISIBLE);
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
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private PetVendorOrderRequest petVendorOrderRequest() {
        /*
         * user_id : 603e27792c2b43125f8cb802
         * order_status : New
         */
        PetVendorOrderRequest petVendorOrderRequest = new PetVendorOrderRequest();
        petVendorOrderRequest.setUser_id(userid);
        petVendorOrderRequest.setOrder_status("Complete");
        Log.w(TAG,"petVendorOrderRequest"+ "--->" + new Gson().toJson(petVendorOrderRequest));
        return petVendorOrderRequest;
    }
    private void setView() {
        rv_completedappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_completedappointment.setItemAnimator(new DefaultItemAnimator());
        int size = 3;
        PetVendorCompletedOrdersAdapter petVendorCompletedOrdersAdapter = new PetVendorCompletedOrdersAdapter(mContext, newOrderResponseList,size,this);
        rv_completedappointment.setAdapter(petVendorCompletedOrdersAdapter);

    }
    private void setViewLoadMore() {
        rv_completedappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_completedappointment.setItemAnimator(new DefaultItemAnimator());
        int size = newOrderResponseList.size();
        PetVendorCompletedOrdersAdapter petVendorCompletedOrdersAdapter = new PetVendorCompletedOrdersAdapter(mContext, newOrderResponseList,size,this);
        rv_completedappointment.setAdapter(petVendorCompletedOrdersAdapter);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void addReviewListener(String id, String userrate, String userfeedback, String appointment_for) {
        Log.w(TAG,"addReviewListener : "+"id : "+id+" userrate : "+userrate+" userfeedback : "+userfeedback+" appointment_for : "+appointment_for);
        showAddReview(id,appointment_for);
    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void showAddReview(String id,String appointment_for) {
        try {
            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.addreview_popup_layout);
            dialog.setCancelable(true);
            RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
            EditText edt_addreview = dialog.findViewById(R.id.edt_addreview);
            Button btn_addreview = dialog.findViewById(R.id.btn_addreview);

            ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
                userrate = String.valueOf(rating);
                Log.w(TAG,"onRatingChanged userrate : "+userrate);
            });

            btn_addreview.setOnClickListener(view -> {
                if(userrate != null){
                    dialog.dismiss();
                    if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                            addReviewResponseCall(id, edt_addreview.getText().toString(), userrate);
                    }
                }else{
                    showErrorLoading("Please choose a star.");
                }


            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void addReviewResponseCall(String id, String userfeedback, String userrate) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddReviewResponse> call = apiInterface.shopaddReviewResponseCall(RestUtils.getContentType(), addShopReviewRequest(id,userfeedback,userrate));
        Log.w(TAG,"addReviewResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AddReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddReviewResponse> call, @NonNull Response<AddReviewResponse> response) {

                Log.w(TAG,"AddReviewResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        showAddReviewSuccess();



                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<AddReviewResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"AddReviewResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private AddShopReviewRequest addShopReviewRequest(String id, String userfeedback, String userrate) {

        /**
         * order_id : 5fd30a701978e618628c966c
         * user_feedback :
         * user_rate : 0
         */

        AddShopReviewRequest addShopReviewRequest = new AddShopReviewRequest();
        addShopReviewRequest.setOrder_id(id);
        if(userfeedback != null){
            addShopReviewRequest.setUser_feedback(userfeedback);

        }else{
            addShopReviewRequest.setUser_feedback("");

        }if(userrate != null){
            addShopReviewRequest.setUser_rate(userrate);

        }else{
            addShopReviewRequest.setUser_rate("");

        }
        Log.w(TAG,"addShopReviewRequest"+ "--->" + new Gson().toJson(addShopReviewRequest));
        return addShopReviewRequest;
    }

    private void showAddReviewSuccess() {
        try {

            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.addreview_review_success_layout);
            dialog.setCancelable(false);

            Button btn_back = dialog.findViewById(R.id.btn_back);


            btn_back.setOnClickListener(view -> {
                dialog.dismiss();
                if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                    get_order_details_user_id_ResponseCall();
                }


            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }

}