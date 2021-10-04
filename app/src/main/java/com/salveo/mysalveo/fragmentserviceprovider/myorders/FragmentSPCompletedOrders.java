package com.salveo.mysalveo.fragmentserviceprovider.myorders;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.salveo.mysalveo.adapter.PetLoverVendorOrdersAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.AddandReviewListener;
import com.salveo.mysalveo.requestpojo.PetLoverMyOrdersReviewandUpdateRequest;
import com.salveo.mysalveo.requestpojo.PetLoverVendorOrderListRequest;
import com.salveo.mysalveo.responsepojo.PetLoverVendorOrderListResponse;
import com.salveo.mysalveo.responsepojo.VendorOrderUpdateResponse;
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


public class FragmentSPCompletedOrders extends Fragment implements AddandReviewListener {
    private final String TAG = "FragmentSPCompletedOrders";

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

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_load_more)
    Button btn_load_more;

    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;




    SessionManager session;
    String doctorid = "";

    private String userid;
    Dialog alertDialog;

    Context mContext;
    private int userrate;

    PetLoverVendorOrdersAdapter petLoverVendorOrdersAdapter;
    private List<PetLoverVendorOrderListResponse.DataBean> orderResponseList;
    private final List<PetLoverVendorOrderListResponse.DataBean> orderResponseListAll = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    public static final int PAGE_START = 1;
    private int CURRENT_PAGE = PAGE_START;
    private boolean isLoading = true;
    private int pastVisibleItem,visibleItemCount,totalItemCount,previousTotal =0;
    private final int viewThreshold = 5;



    public FragmentSPCompletedOrders() {

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
        btn_filter.setVisibility(View.GONE);
        btn_load_more.setVisibility(View.GONE);





        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG," userid : "+userid);

        String patientname = user.get(SessionManager.KEY_FIRST_NAME);

        Log.w(TAG,"Doctorid"+doctorid +"patientname :"+patientname);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_completedappointment.setHasFixedSize(true);
        rv_completedappointment.setItemAnimator(new DefaultItemAnimator());
        rv_completedappointment.setLayoutManager(linearLayoutManager);
        orderResponseListAll.clear();

        if (new ConnectionDetector(getActivity()).isNetworkAvailable(mContext)) {
            CURRENT_PAGE = 1;
            get_grouped_order_by_petlover_ResponseCall();
        }

        refresh_layout.setOnRefreshListener(() -> {
            if (new ConnectionDetector(getActivity()).isNetworkAvailable(mContext)) {
                CURRENT_PAGE = 1;
                previousTotal = 0;
                orderResponseListAll.clear();
                get_grouped_order_by_petlover_ResponseCall();

            }

        });

        initResultRecylerView();

        return view;
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
    private void showAddReview(String id) {
        try {
            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.addreview_popup_layout);
            dialog.setCancelable(true);
            RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
            EditText edt_addreview = dialog.findViewById(R.id.edt_addreview);
            Button btn_addreview = dialog.findViewById(R.id.btn_addreview);

            ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
                userrate = (int) rating;
                Log.w(TAG,"onRatingChanged userrate : "+ this.userrate);
            });

            btn_addreview.setOnClickListener(view -> {
                if(userrate != 0){
                    dialog.dismiss();
                    if (new ConnectionDetector(getActivity()).isNetworkAvailable(mContext)) {
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
    private void addReviewResponseCall(String id, String userfeedback, int userrate) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderUpdateResponse> call = apiInterface.petlover_myorders_reviewandupdate_ResponseCall(RestUtils.getContentType(), petLoverMyOrdersReviewandUpdateRequest(id,userfeedback,userrate));
        Log.w(TAG,"addReviewResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorOrderUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Response<VendorOrderUpdateResponse> response) {

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
            public void onFailure(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"AddReviewResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private PetLoverMyOrdersReviewandUpdateRequest petLoverMyOrdersReviewandUpdateRequest(String id, String userfeedback, int userrate) {

        /*
         * order_id : 5fd30a701978e618628c966c
         * user_feedback :
         * user_rate : 0
         */

        PetLoverMyOrdersReviewandUpdateRequest petLoverMyOrdersReviewandUpdateRequest = new PetLoverMyOrdersReviewandUpdateRequest();
        petLoverMyOrdersReviewandUpdateRequest.setOrder_id(id);
        if(userfeedback != null){
            petLoverMyOrdersReviewandUpdateRequest.setUser_feedback(userfeedback);

        }else{
            petLoverMyOrdersReviewandUpdateRequest.setUser_feedback("");

        }
        petLoverMyOrdersReviewandUpdateRequest.setUser_rate(userrate);
        Log.w(TAG,"addShopReviewRequest"+ "--->" + new Gson().toJson(petLoverMyOrdersReviewandUpdateRequest));
        return petLoverMyOrdersReviewandUpdateRequest;
    }

    private void showAddReviewSuccess() {
        try {
            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.addreview_review_success_layout);
            dialog.setCancelable(false);
            Button btn_back = dialog.findViewById(R.id.btn_back);
            btn_back.setOnClickListener(view -> {
                dialog.dismiss();
                if (new ConnectionDetector(getActivity()).isNetworkAvailable(mContext)) {
                    CURRENT_PAGE = 1;
                    previousTotal = 0;
                    orderResponseListAll.clear();
                    get_grouped_order_by_petlover_ResponseCall();
                }


            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }


    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void get_grouped_order_by_petlover_ResponseCall() {
       /* avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        mShimmerViewContainer.startShimmerAnimation();

        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PetLoverVendorOrderListResponse> call = ApiService.get_grouped_order_by_petlover_ResponseCall(RestUtils.getContentType(),petLoverVendorOrderListRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<PetLoverVendorOrderListResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<PetLoverVendorOrderListResponse> call, @NonNull Response<PetLoverVendorOrderListResponse> response) {
                /*  avi_indicator.smoothToHide();*/
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                refresh_layout.setRefreshing(false);

                Log.w(TAG,"PetVendorOrderResponse"+ "--->" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if(200 == response.body().getCode()){


                        orderResponseList = response.body().getData();
                        Log.w(TAG,"orderResponseList Size : "+orderResponseList.size());

                        for(int i=0;i<orderResponseList.size();i++) {
                            /*
                             * p_order_id : ORDER-1618833408047
                             * p_user_id : 603e27792c2b43125f8cb802
                             * p_shipping_address : 60797c16a20ca32d2668a30c
                             * p_payment_id : pay_H0gNJn2CM7xjO1
                             * p_vendor_id : 604866a50b3a487571a1c568
                             * p_order_product_count : 2
                             * p_order_price : 7200
                             * p_order_image : http://54.212.108.156:3000/api/uploads/1615541391131.jpeg
                             * p_order_booked_on : 19-04-2021 12:05 PM
                             * p_order_status : New
                             * p_order_text : Food Products
                             */

                            PetLoverVendorOrderListResponse.DataBean  dataBean = new PetLoverVendorOrderListResponse.DataBean();
                            dataBean.setP_order_id(orderResponseList.get(i).getP_order_id());
                            dataBean.setP_user_id(orderResponseList.get(i).getP_user_id());
                            dataBean.setP_shipping_address(orderResponseList.get(i).getP_shipping_address());
                            dataBean.setP_payment_id(orderResponseList.get(i).getP_payment_id());
                            dataBean.setP_vendor_id(orderResponseList.get(i).getP_vendor_id());
                            dataBean.setP_order_product_count(orderResponseList.get(i).getP_order_product_count());
                            dataBean.setP_order_price(orderResponseList.get(i).getP_order_price());
                            dataBean.setP_order_image(orderResponseList.get(i).getP_order_image());
                            dataBean.setP_order_booked_on(orderResponseList.get(i).getP_order_booked_on());
                            dataBean.setP_order_status(orderResponseList.get(i).getP_order_status());
                            dataBean.setP_order_text(orderResponseList.get(i).getP_order_text());
                            dataBean.setP_cancelled_date(orderResponseList.get(i).getP_cancelled_date());
                            dataBean.setP_completed_date(orderResponseList.get(i).getP_completed_date());
                            dataBean.setP_user_feedback(orderResponseList.get(i).getP_user_feedback());
                            dataBean.setP_user_rate(orderResponseList.get(i).getP_user_rate());
                            orderResponseListAll.add(dataBean);


                        }

                        Log.w(TAG,"orderResponseListAll size : "+orderResponseListAll.size());
                        // Log.w(TAG,"orderResponseListAll : "+new Gson().toJson(orderResponseListAll));
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

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<PetLoverVendorOrderListResponse> call, @NonNull Throwable t) {
                /*   avi_indicator.smoothToHide();*/
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);

                Log.w(TAG,"PetVendorOrderResponse"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private PetLoverVendorOrderListRequest petLoverVendorOrderListRequest() {
        /*
         * petlover_id : 603e27792c2b43125f8cb802
         * status : New
         * skip_count : 1
         */
        PetLoverVendorOrderListRequest petLoverVendorOrderListRequest = new PetLoverVendorOrderListRequest();
        petLoverVendorOrderListRequest.setPetlover_id(userid);
        petLoverVendorOrderListRequest.setStatus("Complete");
        petLoverVendorOrderListRequest.setSkip_count(CURRENT_PAGE);
        Log.w(TAG,"petLoverVendorOrderListRequest"+ "--->" + new Gson().toJson(petLoverVendorOrderListRequest));
        return petLoverVendorOrderListRequest;
    }
    private void setView(List<PetLoverVendorOrderListResponse.DataBean> orderResponseListAll) {
        petLoverVendorOrdersAdapter = new PetLoverVendorOrdersAdapter(getContext(), orderResponseListAll, TAG, this);
        rv_completedappointment.setAdapter(petLoverVendorOrdersAdapter);
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
                        get_grouped_order_by_petlover_ResponseCall();
                        isLoading = true;
                    }
                }

            }
        });
    }

    @Override
    public void addReviewListener(String id, int userrate, String userfeedback) {
        showAddReview(id);
    }
}