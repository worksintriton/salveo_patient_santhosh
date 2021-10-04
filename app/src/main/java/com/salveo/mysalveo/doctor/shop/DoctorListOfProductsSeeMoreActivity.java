package com.salveo.mysalveo.doctor.shop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.adapter.PetShopCategorySeeMoreAdapter;
import com.salveo.mysalveo.adapter.ProductsSearchAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.doctor.DoctorDashboardActivity;
import com.salveo.mysalveo.doctor.DoctorProfileScreenActivity;

import com.salveo.mysalveo.petlover.ProductFiltersActivity;
import com.salveo.mysalveo.requestpojo.FetctProductByCatRequest;
import com.salveo.mysalveo.requestpojo.NotificationCartCountRequest;
import com.salveo.mysalveo.requestpojo.ProductFiltersRequest;
import com.salveo.mysalveo.requestpojo.ProductSearchRequest;
import com.salveo.mysalveo.requestpojo.ProductSortByRequest;
import com.salveo.mysalveo.responsepojo.FetctProductByCatResponse;
import com.salveo.mysalveo.responsepojo.NotificationCartCountResponse;
import com.salveo.mysalveo.responsepojo.ProductSearchResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorListOfProductsSeeMoreActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "DoctorListOfProductsSeeMoreActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_today_deal)
    RecyclerView rv_today_deal;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_sort)
    RelativeLayout rl_sort;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_sort)
    EditText edt_sort;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_filters)
    RelativeLayout rl_filters;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_filter)
    EditText edt_filter;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_search)
    EditText edt_search;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_doctor_footer)
    View include_doctor_footer;


   /* @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_doctor_header)
    View include_doctor_header;*/




    private String cat_id;


    private boolean isLoading = false;
    private PetShopCategorySeeMoreAdapter petShopCategorySeeMoreAdapter;
    private GridLayoutManager gridLayoutManager;

    public static final int PAGE_START = 1;
    private int CURRENT_PAGE = PAGE_START;
    private List<FetctProductByCatResponse.DataBean> catListSeeMore;
    private List<FetctProductByCatResponse.DataBean> catListSeeMoreAll = new ArrayList<>();
    private Dialog dialog;

    RadioGroup rg_sortby;
    RadioButton rb_recent_products,rb_highest_discount,rb_best_sellers,rb_price_low_to_high,rb_price_high_to_low;
    private int recent = 0;
    private int high_discount = 0;
    private int best_sellers = 0;
    private int high_to_low = 0;
    private int low_to_high = 0;
    private String searchString = "";

    private String discount_value="";
    private String petTypeId = "";
    private String petBreedTypeId = "";
    private String strCategoryTypeId = "";
    private String fromactivity;


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

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_sos)
    ImageView img_sos;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_notification)
    ImageView img_notification;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_cart)
    ImageView img_cart;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count_badge)
    TextView txt_cart_count_badge;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_profile)
    ImageView img_profile;

    private String userid;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list_of_products);
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);
        img_sos.setVisibility(View.GONE);
        img_notification.setVisibility(View.GONE);
        txt_cart_count_badge.setVisibility(View.GONE);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
         userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG, "customerid-->" + userid);


        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            notificationandCartCountResponseCall();
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cat_id = extras.getString("cat_id");
            discount_value = extras.getString("discount_value");
            petTypeId = extras.getString("petTypeId");
            petBreedTypeId = extras.getString("petBreedTypeId");
            fromactivity = extras.getString("fromactivity");
            strCategoryTypeId = extras.getString("strCategoryTypeId");


        }
        /*ImageView img_back = include_doctor_header.findViewById(R.id.img_back);
        ImageView img_notification = include_doctor_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_doctor_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_doctor_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_doctor_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.shop));*/

        rv_today_deal.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this, 2);
        rv_today_deal.setLayoutManager(gridLayoutManager);
        rv_today_deal.setItemAnimator(new DefaultItemAnimator());

        if (fromactivity != null && fromactivity.equalsIgnoreCase("ProductFiltersActivity")) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                productFiltersResponseCall();
            }
        }else{
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                fetctProductByCatResponseCall();
            }
        }
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        initResultRecylerView();

        rl_filters.setOnClickListener(this);
        edt_filter.setOnClickListener(this);
        rl_sort.setVisibility(View.INVISIBLE);
        rl_sort.setOnClickListener(this);
        edt_sort.setOnClickListener(this);


        //bottom_navigation_view.getMenu().findItem(R.id.shop).setChecked(true);
        /*shop*/
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);
        title_shop.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
        img_shop.setImageResource(R.drawable.green_shop);

        rl_home.setOnClickListener(this);
        rl_shop.setOnClickListener(this);
        rl_comn.setOnClickListener(this);
        rl_homes.setOnClickListener(this);





        img_notification.setOnClickListener(this);
        img_cart.setOnClickListener(this);
        img_profile.setOnClickListener(this);
        edt_search.addTextChangedListener(new TextWatcher() {
            @SuppressLint("LogNotTimber")
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.w(TAG,"beforeTextChanged-->"+s.toString());
            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.w(TAG,"onTextChanged-->"+s.toString());
                searchString = s.toString();


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void afterTextChanged(Editable s) {
                Log.w(TAG,"afterTextChanged-->"+s.toString());
                searchString = s.toString();
                if(!searchString.isEmpty()){
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        productSearchResponseCall(searchString);
                    }
                }else{
                    searchString ="";
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        productSearchResponseCall(searchString);
                    }

                }

            }
        });

    }

    @SuppressLint("LogNotTimber")
    private void notificationandCartCountResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();

        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<NotificationCartCountResponse> call = apiInterface.notificationandCartCountResponseCall(RestUtils.getContentType(), notificationCartCountRequest());
        Log.w(TAG,"NotificationCartCountResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<NotificationCartCountResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<NotificationCartCountResponse> call, @NonNull Response<NotificationCartCountResponse> response) {

                Log.w(TAG,"NotificationCartCountResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200) {
                        if(response.body().getData()!=null){
                            int Notification_count = response.body().getData().getNotification_count();
                            int Product_count = response.body().getData().getProduct_count();
                            if(Product_count != 0){
                                txt_cart_count_badge.setVisibility(View.VISIBLE);
                                txt_cart_count_badge.setText(""+Product_count);
                            }else{
                                txt_cart_count_badge.setVisibility(View.GONE);
                            }

                        }
                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<NotificationCartCountResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"NotificationCartCountResponse flr"+"--->" + t.getMessage());
            }
        });


    }
    @SuppressLint("LogNotTimber")
    private NotificationCartCountRequest notificationCartCountRequest() {
        /*
         * user_id : 6048589d0b3a487571a1c567
         */

        NotificationCartCountRequest notificationCartCountRequest = new NotificationCartCountRequest();
        notificationCartCountRequest.setUser_id(userid);
        Log.w(TAG,"notificationCartCountRequest"+ "--->" + new Gson().toJson(notificationCartCountRequest));
        return notificationCartCountRequest;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        callDirections("2");
    }
    public void callDirections(String tag){
        Intent intent = new Intent(DoctorListOfProductsSeeMoreActivity.this, DoctorDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }

    @SuppressLint("LogNotTimber")
    public void fetctProductByCatResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<FetctProductByCatResponse> call = ApiService.fetctProductByCatResponseCall(RestUtils.getContentType(),fetctProductByCatRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetctProductByCatResponse>() {
            @Override
            public void onResponse(@NonNull Call<FetctProductByCatResponse> call, @NonNull Response<FetctProductByCatResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"ShopDashboardResponse" + new Gson().toJson(response.body()));

                        if(response.body().getData()!= null && response.body().getData().size()>0){
                            catListSeeMore = response.body().getData();


                            for(int i=0;i<catListSeeMore.size();i++) {
                                /*
                                 * _id : 60e5aabd5af36c5c3605bab4
                                 * product_img : http://54.212.108.156:3000/api/uploads/1625748054901.png
                                 * product_title : HUL Natural Shampoo for Puppy
                                 * product_price : 180
                                 * thumbnail_image : http://54.212.108.156:3000/api/uploads/1625748027413.png
                                 * product_discount : 10
                                 * product_discount_price : 0
                                 * product_fav : false
                                 * product_rating : 5
                                 * product_review : 0
                                 */
                                FetctProductByCatResponse.DataBean  dataBean = new FetctProductByCatResponse.DataBean();
                                dataBean.set_id(catListSeeMore.get(i).get_id());
                                dataBean.setProduct_img(catListSeeMore.get(i).getProduct_img());
                                dataBean.setProduct_title(catListSeeMore.get(i).getProduct_title());
                                dataBean.setProduct_price(catListSeeMore.get(i).getProduct_price());
                                dataBean.setThumbnail_image(catListSeeMore.get(i).getThumbnail_image());
                                dataBean.setProduct_discount(catListSeeMore.get(i).getProduct_discount());
                                dataBean.setProduct_discount_price(catListSeeMore.get(i).getProduct_discount_price());
                                dataBean.setProduct_fav(catListSeeMore.get(i).isProduct_fav());
                                dataBean.setProduct_rating(catListSeeMore.get(i).getProduct_rating());
                                dataBean.setProduct_review(catListSeeMore.get(i).getProduct_review());
                                catListSeeMoreAll.add(dataBean);


                            }
                            Log.w(TAG,"catListSeeMoreAll : "+new Gson().toJson(catListSeeMoreAll));
                            Log.w(TAG,"catListSeeMoreAll size : "+catListSeeMoreAll.size());



                            setView(catListSeeMoreAll);

                        }

                    }
                }

            }


            @Override
            public void onFailure(@NonNull Call<FetctProductByCatResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"FetctProductByCatResponse flr"+t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private FetctProductByCatRequest fetctProductByCatRequest() {
        /*
         * cat_id : 5fec14a5ea832e2e73c1fc79
         * skip_count : 6
         */

        FetctProductByCatRequest fetctProductByCatRequest = new FetctProductByCatRequest();
        fetctProductByCatRequest.setCat_id(cat_id);
        fetctProductByCatRequest.setSkip_count(CURRENT_PAGE);
        Log.w(TAG,"fetctProductByCatRequest"+ "--->" + new Gson().toJson(fetctProductByCatRequest));
        return fetctProductByCatRequest;
    }
    private void setView(List<FetctProductByCatResponse.DataBean> data) {
         petShopCategorySeeMoreAdapter = new PetShopCategorySeeMoreAdapter(getApplicationContext(), data,TAG,cat_id);
         rv_today_deal.setAdapter(petShopCategorySeeMoreAdapter);
         petShopCategorySeeMoreAdapter.notifyDataSetChanged();
         isLoading = false;



    }

    private void initResultRecylerView() {
        rv_today_deal.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == catListSeeMoreAll.size() - 1) {
                        //bottom of list!
                        CURRENT_PAGE += 1;

                        Log.w(TAG, "isLoading? " + isLoading + " currentPage " + CURRENT_PAGE);
                        isLoading = true;
                        fetctProductByCatResponseCall();
                    }
                }
            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_sort:
                showSortByAlert();
                break;

                case R.id.edt_sort:
                showSortByAlert();
                break;

            case R.id.rl_filters:
                gotoFilters();
                break;

            case R.id.edt_filter:
                gotoFilters();
                break;

                case R.id.rb_recent_products:
                    recent = 1;
                    high_discount = 0;
                    best_sellers = 0;
                    high_to_low = 0;
                    low_to_high = 0;
                break;

                case R.id.rb_highest_discount:
                    recent = 0;
                    high_discount = 1;
                    best_sellers = 0;
                    high_to_low = 0;
                    low_to_high = 0;
                break;

                case R.id.rb_best_sellers:
                    recent = 0;
                    high_discount = 0;
                    best_sellers = 0;
                    high_to_low = 0;
                    low_to_high = 0;
                break;

                case R.id.rb_price_high_to_low:
                    recent = 0;
                    high_discount = 0;
                    best_sellers = 0;
                    high_to_low = 1;
                    low_to_high = 0;
                break;

                case R.id.rb_price_low_to_high:
                    recent = 0;
                    high_discount = 0;
                    best_sellers = 0;
                    high_to_low = 0;
                    low_to_high = 1;
                break;


            case R.id.img_notification:
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                break;
            case R.id.img_cart:
                Intent i = new Intent(getApplicationContext(), DoctorCartActivity.class);
                i.putExtra("cat_id",cat_id);
                i.putExtra("fromactivity",TAG);
                startActivity(i);
                break;
            case R.id.img_profile:
                Intent intent = new Intent(getApplicationContext(), DoctorProfileScreenActivity.class);
                intent.putExtra("fromactivity",TAG);
                if(DoctorDashboardActivity.active_tag != null){
                    intent.putExtra("active_tag", DoctorDashboardActivity.active_tag);

                }
                startActivity(intent);
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

    private void showSortByAlert() {
        try {
            dialog = new Dialog(DoctorListOfProductsSeeMoreActivity.this);
            dialog.setContentView(R.layout.alert_sortby_layout);
            //dialog.setCanceledOnTouchOutside(false);
             rg_sortby = dialog.findViewById(R.id.rg_sortby);
             rb_recent_products = dialog.findViewById(R.id.rb_recent_products);
             rb_highest_discount = dialog.findViewById(R.id.rb_highest_discount);
             rb_best_sellers = dialog.findViewById(R.id.rb_best_sellers);
             rb_price_low_to_high = dialog.findViewById(R.id.rb_price_low_to_high);
             rb_price_high_to_low = dialog.findViewById(R.id.rb_price_high_to_low);


             rb_recent_products.setOnClickListener(this);
             rb_highest_discount.setOnClickListener(this);
             rb_best_sellers.setOnClickListener(this);
             rb_price_low_to_high.setOnClickListener(this);
             rb_price_high_to_low.setOnClickListener(this);

            Button btn_apply = dialog.findViewById(R.id.btn_apply);


            btn_apply.setOnClickListener(view -> {
                productSortByResponseCall();
                dialog.dismiss();


            });

            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }



    @SuppressLint("LogNotTimber")
    private void productSortByResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ProductSearchResponse> call = apiInterface.productSortByResponseCall(RestUtils.getContentType(), productSortByRequest());
        Log.w(TAG,"productSortByResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ProductSearchResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<ProductSearchResponse> call, @NonNull Response<ProductSearchResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"productSortByResponseCall" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {


                        if (response.body().getData() != null) {

                            if (response.body().getData() != null && response.body().getData().size()>0) {
                                rv_today_deal.setVisibility(View.VISIBLE);
                                txt_no_records.setVisibility(View.GONE);
                                setViewProducts(response.body().getData());
                            } else {
                                rv_today_deal.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No products available");

                            }

                        }



                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<ProductSearchResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("ProductSearchResponse flr", "--->" + t.getMessage());
            }
        });

    }
    private void setViewProducts(List<ProductSearchResponse.DataBean> productSearchResponseCall) {
        rv_today_deal.setLayoutManager(new GridLayoutManager(this, 2));
        rv_today_deal.setItemAnimator(new DefaultItemAnimator());
        ProductsSearchAdapter productsSearchAdapter = new ProductsSearchAdapter(getApplicationContext(), productSearchResponseCall,TAG,cat_id);
        rv_today_deal.setAdapter(productsSearchAdapter);
    }
    @SuppressLint("LogNotTimber")
    private ProductSortByRequest productSortByRequest() {
        /*
         * recent : 0
         * high_discount : 0
         * best_sellers : 0
         * high_to_low : 0
         * low_to_high : 1
         * cat_id :
         * today_deals : true
         */
        ProductSortByRequest productSortByRequest = new ProductSortByRequest();
        productSortByRequest.setRecent(recent);
        productSortByRequest.setHigh_discount(high_discount);
        productSortByRequest.setBest_sellers(best_sellers);
        productSortByRequest.setHigh_to_low(high_to_low);
        productSortByRequest.setLow_to_high(low_to_high);
        productSortByRequest.setCat_id(cat_id);
        productSortByRequest.setToday_deals(false);
        Log.w(TAG,"productSortByRequest"+ new Gson().toJson(productSortByRequest));
        return productSortByRequest;
    }


    @SuppressLint("LogNotTimber")
    private void productSearchResponseCall(String searchString) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ProductSearchResponse> call = apiInterface.todayDealSearchResponseCall(RestUtils.getContentType(), productSearchRequest(searchString));
        Log.w(TAG,"DoctorSearchResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ProductSearchResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<ProductSearchResponse> call, @NonNull Response<ProductSearchResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"DoctorSearchResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {


                        if (response.body().getData() != null) {
                            Log.w(TAG, "productSearchResponseCall Size" + response.body().getData().size());
                            if (response.body().getData() != null && response.body().getData().size()>0) {
                                rv_today_deal.setVisibility(View.VISIBLE);
                                txt_no_records.setVisibility(View.GONE);
                                setViewProducts(response.body().getData());
                            } else {
                                rv_today_deal.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No products available");

                            }

                        }



                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<ProductSearchResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("ProductSearchResponse flr", "--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private ProductSearchRequest productSearchRequest(String searchString) {
        /*
         * search_string :
         * cat_id
         */
        ProductSearchRequest productSearchRequest = new ProductSearchRequest();
        productSearchRequest.setSearch_string(searchString);
        productSearchRequest.setCat_id(cat_id);
        Log.w(TAG,"productSearchRequest"+ new Gson().toJson(productSearchRequest));
        return productSearchRequest;
    }

    private void gotoFilters() {
        Intent intent = new Intent(getApplicationContext(), ProductFiltersActivity.class);
        intent.putExtra("fromactivity",TAG);
        intent.putExtra("cat_id",cat_id);
        startActivity(intent);
    }

        @SuppressLint("LogNotTimber")
        private void productFiltersResponseCall() {
            avi_indicator.setVisibility(View.VISIBLE);
            avi_indicator.smoothToShow();
            RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
            Call<ProductSearchResponse> call = apiInterface.productFiltersResponseCall(RestUtils.getContentType(), productFiltersRequest());
            Log.w(TAG,"productFiltersResponseCall url  :%s"+" "+ call.request().url().toString());

            call.enqueue(new Callback<ProductSearchResponse>() {
                @SuppressLint({"LogNotTimber", "SetTextI18n"})
                @Override
                public void onResponse(@NonNull Call<ProductSearchResponse> call, @NonNull Response<ProductSearchResponse> response) {
                    avi_indicator.smoothToHide();
                    Log.w(TAG,"productFiltersResponseCall" + new Gson().toJson(response.body()));
                    if (response.body() != null) {
                        if (200 == response.body().getCode()) {


                            if (response.body().getData() != null) {
                                if (response.body().getData() != null && response.body().getData().size()>0) {
                                    rv_today_deal.setVisibility(View.VISIBLE);
                                    txt_no_records.setVisibility(View.GONE);
                                    setViewProducts(response.body().getData());
                                } else {
                                    rv_today_deal.setVisibility(View.GONE);
                                    txt_no_records.setVisibility(View.VISIBLE);
                                    txt_no_records.setText("No products available");

                                }

                            }



                        }

                    }


                }

                @SuppressLint("LongLogTag")
                @Override
                public void onFailure(@NonNull Call<ProductSearchResponse> call,@NonNull Throwable t) {
                    avi_indicator.smoothToHide();
                    Log.e("ProductSearchResponse flr", "--->" + t.getMessage());
                }
            });

        }
        @SuppressLint("LogNotTimber")
        private ProductFiltersRequest productFiltersRequest() {
            /*
             * pet_type : 602d1bf4562e0916bc9b3215
             * pet_breed :
             * discount_value :
             * cat_id :
             */

            ProductFiltersRequest productFiltersRequest = new ProductFiltersRequest();
            productFiltersRequest.setPet_type(petTypeId);
            productFiltersRequest.setPet_breed(petBreedTypeId);
            productFiltersRequest.setDiscount_value(discount_value);
            productFiltersRequest.setCat_id(strCategoryTypeId);
            Log.w(TAG,"productFiltersRequest"+ new Gson().toJson(productFiltersRequest));
            return productFiltersRequest;
        }




}