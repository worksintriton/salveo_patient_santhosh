package com.salveo.mysalveo.fragmentpetlover.myordersnew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
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
import com.salveo.mysalveo.adapter.PetLoverVendorOrdersAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.AddandReviewListener;
import com.salveo.mysalveo.requestpojo.PetLoverVendorOrderListRequest;
import com.salveo.mysalveo.responsepojo.PetLoverVendorOrderListResponse;
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


public class FragmentPetLoverNewOrders extends Fragment implements AddandReviewListener {
    private final String TAG = "FragmentPetLoverNewOrders";

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
    Context mContext;

    PetLoverVendorOrdersAdapter petLoverVendorOrdersAdapter;
    private List<PetLoverVendorOrderListResponse.DataBean> orderResponseList;
    private final List<PetLoverVendorOrderListResponse.DataBean> orderResponseListAll = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;
    public static final int PAGE_START = 1;
    private int CURRENT_PAGE = PAGE_START;
    private boolean isLoading = true;
    private int pastVisibleItem,visibleItemCount,totalItemCount,previousTotal =0;
    private final int viewThreshold = 5;

    public FragmentPetLoverNewOrders() {

    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
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


        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);



        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_newappointment.setHasFixedSize(true);
        rv_newappointment.setItemAnimator(new DefaultItemAnimator());
        rv_newappointment.setLayoutManager(linearLayoutManager);
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

        /*final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    try {
                        //your method here
                        CURRENT_PAGE = 1;
                        previousTotal = 0;
                        orderResponseListAll.clear();
                        get_grouped_order_by_petlover_ResponseCall();
                    }catch (Exception ignored) {
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 60000);//you can put 30000(30 secs)*/



        return view;
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
                            * p_cancelled_date :
                            * p_completed_date :
                            * p_user_feedback :
                            * p_user_rate : 0
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
                           rv_newappointment.setVisibility(View.VISIBLE);
                           setView(orderResponseListAll);

                       }
                       else{
                           if (CURRENT_PAGE == 1) {
                               txt_no_records.setVisibility(View.VISIBLE);
                               txt_no_records.setText("No new orders");
                           } else {
                               rv_newappointment.setVisibility(View.VISIBLE);
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
        petLoverVendorOrderListRequest.setStatus("New");
        petLoverVendorOrderListRequest.setSkip_count(CURRENT_PAGE);
        Log.w(TAG,"petLoverVendorOrderListRequest"+ "--->" + new Gson().toJson(petLoverVendorOrderListRequest));
        return petLoverVendorOrderListRequest;
    }

    private void setView(List<PetLoverVendorOrderListResponse.DataBean> orderResponseListAll) {
        petLoverVendorOrdersAdapter = new PetLoverVendorOrdersAdapter(getContext(), orderResponseListAll, TAG, this);
        rv_newappointment.setAdapter(petLoverVendorOrdersAdapter);
        isLoading = true;
    }
    private void initResultRecylerView() {
        rv_newappointment.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    }
}