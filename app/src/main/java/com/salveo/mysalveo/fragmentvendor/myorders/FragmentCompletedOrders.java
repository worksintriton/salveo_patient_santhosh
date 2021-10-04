package com.salveo.mysalveo.fragmentvendor.myorders;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
import com.salveo.mysalveo.adapter.VendorOrdersAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.VendorGetsOrderIdRequest;
import com.salveo.mysalveo.requestpojo.VendorOrderListRequest;
import com.salveo.mysalveo.responsepojo.VendorGetsOrderIDResponse;
import com.salveo.mysalveo.responsepojo.VendorOrderListResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentCompletedOrders extends Fragment  {
    private final String TAG = "FragmentCompletedOrders";

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
    @BindView(R.id.btn_filter)
    Button btn_filter;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    Dialog alertDialog;


    SessionManager session;
    String username = "";
    String userid = "";
    private Context mContext;

    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;

    private List<VendorOrderListResponse.DataBean> orderResponseList;
    private final List<VendorOrderListResponse.DataBean> orderResponseListAll = new ArrayList<>();


    VendorOrdersAdapter vendorOrdersAdapter;
    private LinearLayoutManager linearLayoutManager;
    public static final int PAGE_START = 1;
    private int CURRENT_PAGE = PAGE_START;
    private boolean isLoading = true;
    private int pastVisibleItem,visibleItemCount,totalItemCount,previousTotal =0;
    private final int viewThreshold = 5;

    public FragmentCompletedOrders() {

    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView");

        View view = inflater.inflate(R.layout.fragment_vendor_completed_orders, container, false);

        ButterKnife.bind(this, view);
        mContext = getActivity();

        includelayout = view.findViewById(R.id.includelayout);
        mShimmerViewContainer = includelayout.findViewById(R.id.shimmer_layout);

        avi_indicator.setVisibility(View.GONE);
        btn_filter.setVisibility(View.GONE);


        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);
        username = user.get(SessionManager.KEY_FIRST_NAME);

        Log.w(TAG,"userid"+userid +"username :"+username);

        if (APIClient.VENDOR_ID.isEmpty()) {
            if (new ConnectionDetector(getActivity()).isNetworkAvailable(mContext)) {
                getVendorOrderIDResponseCall(userid);

            }
        }

        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_completedappointment.setHasFixedSize(true);
        rv_completedappointment.setItemAnimator(new DefaultItemAnimator());
        rv_completedappointment.setLayoutManager(linearLayoutManager);
        orderResponseListAll.clear();

        if (new ConnectionDetector(getActivity()).isNetworkAvailable(mContext)) {
            CURRENT_PAGE = 1;
            if (APIClient.VENDOR_ID != null && !APIClient.VENDOR_ID.isEmpty()) {
                vendorCompleteOrderResponseCall(APIClient.VENDOR_ID);
            }

        }

        refresh_layout.setOnRefreshListener(() -> {
            if (new ConnectionDetector(getActivity()).isNetworkAvailable(mContext)) {
                CURRENT_PAGE = 1;
                previousTotal = 0;
                orderResponseListAll.clear();
                if (APIClient.VENDOR_ID != null && !APIClient.VENDOR_ID.isEmpty()) {
                    vendorCompleteOrderResponseCall(APIClient.VENDOR_ID);
                }


            }

        });

        initResultRecylerView();



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
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NonNull Call<VendorGetsOrderIDResponse> call, @NonNull Response<VendorGetsOrderIDResponse> response) {

                Log.w(TAG,"getVendorOrderIDResponseCall"+ "--->" + new Gson().toJson(response.body()));

               // avi_indicator.smoothToHide();

                refresh_layout.setRefreshing(false);

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData()!=null){

                            if(response.body().getData().get_id()!=null&&!(response.body().getData().get_id().isEmpty())){
                                APIClient.VENDOR_ID = response.body().getData().get_id();
                                vendorCompleteOrderResponseCall(response.body().getData().get_id());

                            }


                        }


                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<VendorGetsOrderIDResponse> call, @NonNull Throwable t) {

                /*avi_indicator.smoothToHide();*/

                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.w(TAG,"getVendorOrderIDResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private VendorGetsOrderIdRequest vendorGetsOrderIdRequest(String userid) {

        VendorGetsOrderIdRequest vendorGetsOrderIdRequest = new VendorGetsOrderIdRequest();

        vendorGetsOrderIdRequest.setUser_id(userid);

        Log.w(TAG,"vendorGetsOrderIdRequest"+ "--->" + new Gson().toJson(vendorGetsOrderIdRequest));
        //  Toasty.success(getApplicationContext(),"fbTokenUpdateRequest : "+new Gson().toJson(fbTokenUpdateRequest), Toast.LENGTH_SHORT, true).show();

        return vendorGetsOrderIdRequest;
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





    @SuppressLint("LogNotTimber")
    private void vendorCompleteOrderResponseCall(String id) {
       /* avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderListResponse> call = ApiService.get_grouped_order_by_vendor_ResponseCall(RestUtils.getContentType(),vendorOrderListRequest(id));
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<VendorOrderListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<VendorOrderListResponse> call, @NonNull Response<VendorOrderListResponse> response) {
                // avi_indicator.smoothToHide();

                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                refresh_layout.setRefreshing(false);
                Log.w(TAG,"VendorNewOrderResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {

                    if(200 == response.body().getCode()){
                        orderResponseList = response.body().getData();

                        for(int i=0;i<orderResponseList.size();i++) {
                            /*
                             * v_order_id : ORDER-1618830809052
                             * v_user_id : 603e27792c2b43125f8cb802
                             * v_shipping_address : 60797c16a20ca32d2668a30c
                             * v_payment_id : pay_H0gNJn2CM7xjO1
                             * v_vendor_id : 604866a50b3a487571a1c568
                             * v_order_product_count : 1
                             * v_order_price : 960
                             * v_order_image : http://54.212.108.156:3000/api/uploads/1615541391131.jpeg
                             * v_order_booked_on : 19-04-2021 12:05 PM
                             * v_order_status : New
                             * v_order_text : Food Products
                             */
                            VendorOrderListResponse.DataBean  dataBean = new VendorOrderListResponse.DataBean();
                            dataBean.setV_order_id(orderResponseList.get(i).getV_order_id());
                            dataBean.setV_user_id(orderResponseList.get(i).getV_user_id());
                            dataBean.setV_shipping_address(orderResponseList.get(i).getV_shipping_address());
                            dataBean.setV_payment_id(orderResponseList.get(i).getV_payment_id());
                            dataBean.setV_vendor_id(orderResponseList.get(i).getV_vendor_id());
                            dataBean.setV_order_product_count(orderResponseList.get(i).getV_order_product_count());
                            dataBean.setV_order_price(orderResponseList.get(i).getV_order_price());
                            dataBean.setV_order_image(orderResponseList.get(i).getV_order_image());
                            dataBean.setV_order_booked_on(orderResponseList.get(i).getV_order_booked_on());
                            dataBean.setV_order_status(orderResponseList.get(i).getV_order_status());
                            dataBean.setV_order_text(orderResponseList.get(i).getV_order_text());
                            dataBean.setV_cancelled_date(orderResponseList.get(i).getV_cancelled_date());
                            dataBean.setV_completed_date(orderResponseList.get(i).getV_completed_date());
                            dataBean.setV_user_feedback(orderResponseList.get(i).getV_user_feedback());
                            dataBean.setV_user_rate(orderResponseList.get(i).getV_user_rate());
                            orderResponseListAll.add(dataBean);


                        }

                        Log.w(TAG,"Size"+orderResponseListAll.size());
                        Log.w(TAG,"orderResponseListAll : "+new Gson().toJson(orderResponseListAll));
                        if(orderResponseList.size() > 0){
                            txt_no_records.setVisibility(View.GONE);
                            rv_completedappointment.setVisibility(View.VISIBLE);
                            setView(orderResponseListAll);

                        }
                        else{
                            if (CURRENT_PAGE == 1) {
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No completed orders");
                            } else {
                                rv_completedappointment.setVisibility(View.VISIBLE);
                                setView(orderResponseListAll);
                            }

                        }
                    }



                }
            }

            @Override
            public void onFailure(@NonNull Call<VendorOrderListResponse> call, @NonNull Throwable t) {
                // avi_indicator.smoothToHide();

                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);

                Log.w(TAG,"VendorOrderResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private VendorOrderListRequest vendorOrderListRequest(String id) {
        /*
         * vendor_id : 604866a50b3a487571a1c568
         * order_status : Complete
         * skip_count : 1
         */

        VendorOrderListRequest vendorOrderListRequest = new VendorOrderListRequest();
        vendorOrderListRequest.setVendor_id(id);
        vendorOrderListRequest.setOrder_status("Complete");
        vendorOrderListRequest.setSkip_count(CURRENT_PAGE);
        Log.w(TAG,"vendorNewOrderRequest"+ "--->" + new Gson().toJson(vendorOrderListRequest));
        return vendorOrderListRequest;
    }
    private void setView(List<VendorOrderListResponse.DataBean> orderResponseListAll) {
        vendorOrdersAdapter = new VendorOrdersAdapter(getContext(), orderResponseListAll,TAG);
        rv_completedappointment.setAdapter(vendorOrdersAdapter);
        isLoading = true;
    }

    private void initResultRecylerView() {
        rv_completedappointment.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previousTotal) {

                            isLoading = false;
                            previousTotal = totalItemCount;
                        }
                    }

                    if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItem+viewThreshold)) {
                        CURRENT_PAGE = CURRENT_PAGE + 1;
                        if (APIClient.VENDOR_ID != null && !APIClient.VENDOR_ID.isEmpty()) {
                            vendorCompleteOrderResponseCall(APIClient.VENDOR_ID);
                        }
                        isLoading = true;
                    }
                }

            }
        });
    }






}