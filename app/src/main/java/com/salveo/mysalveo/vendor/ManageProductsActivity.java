package com.salveo.mysalveo.vendor;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.adapter.ManageProductsListAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.ManageProductsDealsListener;
import com.salveo.mysalveo.interfaces.OnItemCheckProduct;
import com.salveo.mysalveo.requestpojo.ApplyMultiProdDiscountRequest;
import com.salveo.mysalveo.requestpojo.ApplySingleDiscountCalRequest;
import com.salveo.mysalveo.requestpojo.ApplySingleDiscountRequest;
import com.salveo.mysalveo.requestpojo.ManageProductsListRequest;
import com.salveo.mysalveo.requestpojo.TodayDealsClearRequest;
import com.salveo.mysalveo.responsepojo.ApplyMultiProdDiscountResponse;
import com.salveo.mysalveo.responsepojo.ApplySingleDiscountCalResponse;
import com.salveo.mysalveo.responsepojo.ApplySingleDiscountResponse;
import com.salveo.mysalveo.responsepojo.ManageProductsListResponse;
import com.salveo.mysalveo.responsepojo.VendorOrderUpdateResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageProductsActivity extends AppCompatActivity implements View.OnClickListener, OnItemCheckProduct, ManageProductsDealsListener{

    private String TAG = "ManageProductsActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_manage_productlist)
    RecyclerView rv_manage_productlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_apply)
    LinearLayout ll_apply;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_discard)
    LinearLayout ll_discard;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_applydeal)
    TextView txt_applydeal;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.add_deal_fab)
    FloatingActionButton add_deal_fab;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab_add_deal)
    FloatingActionButton fab_add_deal;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab_discard_deal)
    FloatingActionButton fab_discard_deal;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_add_deal)
    TextView txt_add_deal;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_discard_deal)
    TextView txt_discard_deal;


   @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_add_deal)
    LinearLayout ll_add_deal;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_vendor_footer)
    View include_vendor_footer;

    /* Bottom Navigation */

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;

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






    // to check whether sub FAB buttons are visible or not.
    Boolean isAllFabsVisible;
    Boolean isAddDealFabsVisible;

    private List<ManageProductsListResponse.DataBean> manageProductsListResponseList;

    boolean showCheckbox = false;
    private Dialog dialog;

    int productcount = 0;
    String productid, productname;
    int productprice;
    private int productdiscount;

    TextView txt_discount_price,txt_cost;
    TextView txt_deal_start_date,txt_deal_expriy_date;
    private int discountamount =0;
    private boolean discountstatus = false;
    private String searchString = "";
    private String getfromdate = "";
    private String gettodate = "";

     boolean isvaliddate = false;
    AlertDialog alertDialog;

    List<String> _id = new ArrayList<>();
    private boolean isValidProductPrice = true;

    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_products);
        Log.w(TAG,"onCreate ");
        ButterKnife.bind(this);

        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        String userid = user.get(SessionManager.KEY_ID);
        ll_discard.setVisibility(View.INVISIBLE);

        ll_add_deal.setVisibility(View.GONE);
        txt_add_deal.setVisibility(View.GONE);
        txt_discard_deal.setVisibility(View.GONE);
        fab_add_deal.setVisibility(View.GONE);
        fab_discard_deal.setVisibility(View.GONE);

//        bottom_navigation_view = include_vendor_footer.findViewById(R.id.bottom_navigation_view);
//        bottom_navigation_view.setItemIconTintList(null);
//        bottom_navigation_view.getMenu().findItem(R.id.feeds).setChecked(true);
//        bottom_navigation_view.setOnNavigationItemSelectedListener(this);

        /*shop*/

        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);
        title_shop.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
        img_shop.setImageResource(R.drawable.green_shop_selector);

        rl_home.setOnClickListener(this);

        rl_shop.setOnClickListener(this);

        rl_comn.setOnClickListener(this);


        rl_homes.setOnClickListener(this);

        ImageView img_notification = findViewById(R.id.img_notification);
        ImageView img_profile = findViewById(R.id.img_profile);
        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));

            }
        });
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VendorProfileScreenActivity.class);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);

            }
        });


        img_back.setOnClickListener(v -> onBackPressed());

        if (new ConnectionDetector(ManageProductsActivity.this).isNetworkAvailable(ManageProductsActivity.this)) {
            getlist_from_vendor_id_ResponseCall();
        }

        refresh_layout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                              getlist_from_vendor_id_ResponseCall();

                        }

                    }
                }
        );


    /*    final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    try {
                        //your method here
                        getlist_from_vendor_id_ResponseCall();
                    }catch (Exception ignored) {
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 30000);//you can put 30000(30 secs)*/

        ll_apply.setOnClickListener(this);
        ll_discard.setOnClickListener(this);
        txt_applydeal.setOnClickListener(this);



        isAllFabsVisible = false;
        isAddDealFabsVisible = false;
        add_deal_fab.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint({"UseCompatLoadingForDrawables", "ObsoleteSdkInt"})
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {
                            fab_add_deal.show();
                            fab_discard_deal.show();
                            txt_add_deal.setVisibility(View.VISIBLE);
                            txt_discard_deal.setVisibility(View.VISIBLE);
                            isAllFabsVisible = true;
                            add_deal_fab.setImageResource(R.drawable.ic_baseline_close_white24);

                        } else {
                            fab_add_deal.hide();
                            fab_discard_deal.hide();
                            txt_add_deal.setVisibility(View.GONE);
                            txt_discard_deal.setVisibility(View.GONE);
                            isAllFabsVisible = false;
                            add_deal_fab.setImageResource(R.drawable.ic_baseline_add_24);
                            showCheckbox = false;
                            setView();

                        }
                    }
                });

        fab_add_deal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAddDealFabsVisible) {
                            showCheckbox = true;
                            setView();
                          /*  fab_add_deal.show();
                            fab_discard_deal.show();
                            txt_add_deal.setVisibility(View.VISIBLE);
                            txt_discard_deal.setVisibility(View.VISIBLE);*/
                            isAddDealFabsVisible = true;
                          //  add_deal_fab.setImageResource(R.drawable.ic_baseline_close_white24);

                        } else {
                          /*  fab_add_deal.hide();
                            fab_discard_deal.hide();
                            txt_add_deal.setVisibility(View.GONE);
                            txt_discard_deal.setVisibility(View.GONE);*/
                            isAddDealFabsVisible = false;
                            if(productcount != 0){
                                showProductDealAlert();
                            }else{
                                showErrorLoading("Please select the product");
                            }
                           // add_deal_fab.setImageResource(R.drawable.ic_baseline_add_24);

                        }
                    }
                });
        fab_discard_deal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showCheckbox = false;
                        setView();
                        fab_add_deal.hide();
                        fab_discard_deal.hide();
                        txt_add_deal.setVisibility(View.GONE);
                        txt_discard_deal.setVisibility(View.GONE);
                        isAllFabsVisible = false;
                        add_deal_fab.setImageResource(R.drawable.ic_baseline_add_24);
                    }
                });


    }
    @SuppressLint("LogNotTimber")
    private void getlist_from_vendor_id_ResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<ManageProductsListResponse> call = ApiService.getlist_from_vendor_id_ResponseCall(RestUtils.getContentType(),manageProductsListRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ManageProductsListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ManageProductsListResponse> call, @NonNull Response<ManageProductsListResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"ManageProductsListResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        refresh_layout.setRefreshing(false);

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            manageProductsListResponseList = response.body().getData();
                            txt_no_records.setVisibility(View.GONE);
                            rv_manage_productlist.setVisibility(View.VISIBLE);
                            ll_add_deal.setVisibility(View.VISIBLE);
                            setView();
                        }else{
                            rv_manage_productlist.setVisibility(View.GONE);
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No products found");
                            ll_add_deal.setVisibility(View.GONE);

                        }


                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<ManageProductsListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"ManageProductsListResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private ManageProductsListRequest manageProductsListRequest() {
        /*
         * vendor_id : 6048589d0b3a487571a1c567
         * search_string : CAT
         */
        ManageProductsListRequest manageProductsListRequest = new ManageProductsListRequest();
        manageProductsListRequest.setVendor_id(APIClient.VENDOR_ID);
        manageProductsListRequest.setSearch_string(searchString);
        Log.w(TAG,"manageProductsListRequest"+ "--->" + new Gson().toJson(manageProductsListRequest));
        return manageProductsListRequest;
    }
    private void setView() {
        rv_manage_productlist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_manage_productlist.setItemAnimator(new DefaultItemAnimator());
        ManageProductsListAdapter manageProductsListAdapter = new ManageProductsListAdapter(getApplicationContext(), manageProductsListResponseList,showCheckbox,this,this);
        rv_manage_productlist.setAdapter(manageProductsListAdapter);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),VendorDashboardActivity.class));
        finish();
    }
    @SuppressLint({"NonConstantResourceId", "SetTextI18n", "LogNotTimber"})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_discard:
                showCheckbox = false;
                ll_discard.setVisibility(View.INVISIBLE);
                txt_applydeal.setText("Apply Deal");
                setView();
                break;

            case R.id.ll_apply:
                Log.w(TAG,"Apply Deal Clicked");
                showCheckbox = true;
                ll_discard.setVisibility(View.VISIBLE);
                txt_applydeal.setText("Submit");
                setView();
                break;

            case R.id.txt_applydeal:
                Log.w(TAG,"txt_applydeal clicked"+txt_applydeal.getText().toString());
                if(txt_applydeal.getText().toString().equalsIgnoreCase("Submit")){
                    if(productcount != 0){
                        showProductDealAlert();
                    }else{
                        showErrorLoading("Please select the product");
                    }

                }else{
                    showCheckbox = true;
                    ll_discard.setVisibility(View.VISIBLE);
                    txt_applydeal.setText("Submit");
                    setView();
                }
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
    @SuppressLint("LogNotTimber")
    @Override
    public void onItemCheckProduct(int count, String product_id, String product_name, int product_price) {
        Log.w(TAG,"onItemCheckProduct : count "+count);
        productcount = count;
        productid = product_id;
        productname = product_name;
        productprice = product_price;
        if(product_id != null) {
            _id.add(product_id);
            Log.w(TAG, "after Add list if" + new Gson().toJson(_id));
        }





    }

    @SuppressLint("LogNotTimber")
    @Override
    public void onItemUnCheckProduct(int count, String product_id, String product_name, int product_price) {
        Log.w(TAG,"onItemUnCheckProduct : count "+count);
        productcount = count;
        productid = product_id;
        productname = product_name;
        productprice = product_price;
        if(product_id != null){
            if(_id != null){
                for (int i = 0; i < _id.size(); i++)
                    if (_id.get(i).equalsIgnoreCase(product_id)) {
                        _id.remove(i);
                        Log.w(TAG, "after removing list if" + new Gson().toJson(_id));

                    }

            }



        }

    }

    private void showProductDealAlert() {
        try {
            dialog = new Dialog(ManageProductsActivity.this);
            dialog.setContentView(R.layout.add_todays_deal_popup);
            dialog.setCanceledOnTouchOutside(true);
            EditText edt_discount_per_unit = dialog.findViewById(R.id.edt_discount_per_unit);
            EditText edt_deal_price = dialog.findViewById(R.id.edt_deal_price);
            txt_deal_start_date = dialog.findViewById(R.id.txt_deal_start_date);
            txt_deal_expriy_date = dialog.findViewById(R.id.txt_deal_expriy_date);
            txt_discount_price  = dialog.findViewById(R.id.txt_discount_price);
            txt_cost  = dialog.findViewById(R.id.txt_cost);
            Button btn_apply_discount  = dialog.findViewById(R.id.btn_apply_discount);
            LinearLayout ll_content  = dialog.findViewById(R.id.ll_content);
            ll_content.setVisibility(View.GONE);


            edt_discount_per_unit.addTextChangedListener(new TextWatcher() {
                @SuppressLint("LogNotTimber")
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.w(TAG,"beforeTextChanged : "+s.toString());

                }

                @SuppressLint("LogNotTimber")
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.w(TAG,"onTextChanged : "+s.toString());
                }

                @SuppressLint("LogNotTimber")
                @Override
                public void afterTextChanged(Editable s) {
                    Log.w(TAG,"afterTextChanged : "+s.toString());

                    if(s.toString() != null && !s.toString().isEmpty()){
                        if(edt_deal_price.getText().toString() != null && !edt_deal_price.getText().toString().isEmpty()){
                            edt_deal_price.setText("");
                        }
                        discountamount = 0;
                        productdiscount = Integer.parseInt(s.toString());
                        discountstatus = true;
                        Log.w(TAG,"productcount : "+productcount+"edt_discount_per_unit afterTextChanged "+s.toString());
                        if(productcount ==1){
                            if(productdiscount != 0) {
                                ll_content.setVisibility(View.VISIBLE);
                                cal_discount_single_ResponseCall();
                            }
                        }

                    }else{
                        ll_content.setVisibility(View.GONE);

                    }





                }
            });
            edt_deal_price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @SuppressLint("LogNotTimber")
                @Override
                public void afterTextChanged(Editable s) {

                    if(s.toString() != null && !s.toString().isEmpty()){
                        if(edt_discount_per_unit.getText().toString() != null && !edt_discount_per_unit.getText().toString().isEmpty()){
                            edt_discount_per_unit.setText("");
                        }
                        productdiscount = 0;
                        discountamount = Integer.parseInt(s.toString());
                        discountstatus = false;
                        Log.w(TAG,"productcount : "+productcount+"edt_discount_per_unit afterTextChanged "+s.toString());
                        if(productcount ==1){
                            if(discountamount != 0) {
                                if(productprice>discountamount) {
                                    isValidProductPrice = true;
                                    ll_content.setVisibility(View.VISIBLE);
                                    cal_discount_single_ResponseCall();
                                }else{
                                    isValidProductPrice = false;
                                    showErrorLoading("Please enter the amount below the product price");
                                }
                            }
                        }else{
                            ll_content.setVisibility(View.GONE);
                        }

                    }




                }
            });

            btn_apply_discount.setOnClickListener(v -> {

                if(getfromdate.isEmpty()){
                    showErrorLoading("Please select deal start date");
                }else if(gettodate.isEmpty()){
                    showErrorLoading("Please select deal end date");
                }else {
                    CheckDates(getfromdate,gettodate);
                    if(isvaliddate){
                        if(productcount != 0){
                        if(productcount == 1){
                            if(isValidProductPrice) {
                                apply_sing_dis_ResponseCall();
                            }else{
                                showErrorLoading("Please enter the amount below the product price");
                            }
                        }else{
                            apply_multi_dis_ResponseCall();
                        }
                        }
                    }else{
                        showErrorLoading("Please select valid deal date");
                    }
                }




            });

            txt_deal_start_date.setOnClickListener(v -> getStartDate());
            txt_deal_expriy_date.setOnClickListener(v -> getEndDate());


            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }


    @SuppressLint("LogNotTimber")
    private void apply_sing_dis_ResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ApplySingleDiscountResponse> call = apiInterface.apply_sing_dis_ResponseCall(RestUtils.getContentType(), applySingleDiscountRequest());
        Log.w(TAG,"ApplySingleDiscountResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ApplySingleDiscountResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<ApplySingleDiscountResponse> call, @NonNull Response<ApplySingleDiscountResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"ApplySingleDiscountResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        dialog.dismiss();
                        ll_discard.setVisibility(View.INVISIBLE);
                        txt_applydeal.setText("Apply Deal");
                        showCheckbox = false;
                        fab_add_deal.hide();
                        fab_discard_deal.hide();
                        txt_add_deal.setVisibility(View.GONE);
                        txt_discard_deal.setVisibility(View.GONE);
                        isAllFabsVisible = false;
                        add_deal_fab.setImageResource(R.drawable.ic_baseline_add_24);
                        getlist_from_vendor_id_ResponseCall();




                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<ApplySingleDiscountResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("ApplySingleDiscountResponse flr", "--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private ApplySingleDiscountRequest applySingleDiscountRequest() {
        /*
         * _id : 605328895e35b95a5cf804e4
         * discount_status : false
         * discount_amount : 2
         * discount : 0
         * "discount_start_date" : "23-03-2021",
         * "discount_end_date" : "24-02-2021"
         */

        ApplySingleDiscountRequest applySingleDiscountRequest = new ApplySingleDiscountRequest();
        applySingleDiscountRequest.set_id(productid);
        applySingleDiscountRequest.setDiscount_status(discountstatus);
        applySingleDiscountRequest.setDiscount_amount(discountamount);
        applySingleDiscountRequest.setDiscount(productdiscount);
        applySingleDiscountRequest.setDiscount_start_date(getfromdate);
        applySingleDiscountRequest.setDiscount_end_date(gettodate);
        Log.w(TAG,"applySingleDiscountRequest"+ new Gson().toJson(applySingleDiscountRequest));
        return applySingleDiscountRequest;
    }

    @SuppressLint("LogNotTimber")
    private void apply_multi_dis_ResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ApplyMultiProdDiscountResponse> call = apiInterface.apply_multi_dis_ResponseCall(RestUtils.getContentType(), applyMultiProdDiscountRequest());
        Log.w(TAG,"ApplyMultiProdDiscountResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ApplyMultiProdDiscountResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<ApplyMultiProdDiscountResponse> call, @NonNull Response<ApplyMultiProdDiscountResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"ApplySingleDiscountResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        dialog.dismiss();
                        ll_discard.setVisibility(View.INVISIBLE);
                        txt_applydeal.setText("Apply Deal");
                        showCheckbox = false;
                        fab_add_deal.hide();
                        fab_discard_deal.hide();
                        txt_add_deal.setVisibility(View.GONE);
                        txt_discard_deal.setVisibility(View.GONE);
                        isAllFabsVisible = false;
                        add_deal_fab.setImageResource(R.drawable.ic_baseline_add_24);
                        getlist_from_vendor_id_ResponseCall();


                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<ApplyMultiProdDiscountResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("ApplyMultiProdDiscountResponse flr", "--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private ApplyMultiProdDiscountRequest applyMultiProdDiscountRequest() {
        /*
         * _id : 605328895e35b95a5cf804e4
         * discount_status : false
         * discount_amount : 2
         * discount : 0
         * "discount_start_date" : "23-03-2021",
         * "discount_end_date" : "24-02-2021"
         */


        ApplyMultiProdDiscountRequest applyMultiProdDiscountRequest = new ApplyMultiProdDiscountRequest();
        applyMultiProdDiscountRequest.set_id(_id);
        applyMultiProdDiscountRequest.setDiscount_status(discountstatus);
        applyMultiProdDiscountRequest.setDiscount_amount(discountamount);
        applyMultiProdDiscountRequest.setDiscount(productdiscount);
        applyMultiProdDiscountRequest.setDiscount_start_date(getfromdate);
        applyMultiProdDiscountRequest.setDiscount_end_date(gettodate);
        Log.w(TAG,"applyMultiProdDiscountRequest"+ new Gson().toJson(applyMultiProdDiscountRequest));
        return applyMultiProdDiscountRequest;
    }

     @SuppressLint("LogNotTimber")
    private void cal_discount_single_ResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ApplySingleDiscountCalResponse> call = apiInterface.cal_discount_single_ResponseCall(RestUtils.getContentType(), applySingleDiscountCalRequest());
        Log.w(TAG,"ApplySingleDiscountCalResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ApplySingleDiscountCalResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<ApplySingleDiscountCalResponse> call, @NonNull Response<ApplySingleDiscountCalResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"ApplySingleDiscountCalResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {


                        if (response.body().getData() != null) {
                            if(discountstatus){
                                txt_discount_price.setText(response.body().getData().getDiscount()+" %");
                            }else{
                                txt_discount_price.setText("\u20B9 "+response.body().getData().getDiscount_amount());
                            }
                            txt_cost.setText("\u20B9 " +response.body().getData().getCost());

                        }



                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<ApplySingleDiscountCalResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("ApplySingleDiscountCalResponse flr", "--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private ApplySingleDiscountCalRequest applySingleDiscountCalRequest() {
        /*
         * _id : 605328895e35b95a5cf804e4
         * discount_status : false
         * discount_amount : 2
         * discount : 0
         */
        ApplySingleDiscountCalRequest applySingleDiscountCalRequest = new ApplySingleDiscountCalRequest();
        applySingleDiscountCalRequest.set_id(productid);
        applySingleDiscountCalRequest.setDiscount_status(discountstatus);
        applySingleDiscountCalRequest.setDiscount_amount(discountamount);
        applySingleDiscountCalRequest.setDiscount(productdiscount);
        Log.w(TAG,"applySingleDiscountCalRequest"+ new Gson().toJson(applySingleDiscountCalRequest));
        return applySingleDiscountCalRequest;
    }
    private void getStartDate() {

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {

                    String nd = "" + dayOfMonth;
                    String nm;
                    if ( dayOfMonth < 10 ){
                        nd = "0"+ dayOfMonth;
                    }


                    if ( (monthOfYear + 1) < 10){
                        nm = "0"+ ( monthOfYear +1 ) ;
                    }else {
                        nm = ""+ ( monthOfYear + 1) ;
                    }
                    getfromdate = nd+"-"+nm+"-"+year;
                    txt_deal_start_date.setText(getfromdate);



                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);

        datePickerDialog.show();
    }
    private void getEndDate() {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    String nd = "" + dayOfMonth;
                    String nm;
                    if ( dayOfMonth < 10 ){
                        nd = "0"+ dayOfMonth;
                    }

                    if ( (monthOfYear + 1) < 10){
                        nm = "0"+ ( monthOfYear +1 ) ;
                    }else {
                        nm = ""+ ( monthOfYear + 1) ;
                    }
                    gettodate = nd+"-"+nm+"-"+year;
                    txt_deal_expriy_date.setText(gettodate);


                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);

        datePickerDialog.show();
    }
    @SuppressLint("LogNotTimber")
    public void CheckDates(String d1, String d2){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dfDate  = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if(d1 != null && d2 != null){
                if(Objects.requireNonNull(dfDate.parse(d1)).before(dfDate.parse(d2)))
                {
                    isvaliddate = true;//If start date is before end date
                    Log.w(TAG,"before "+isvaliddate);
                } else {
                    isvaliddate = false; //If start date is after the end date
                    Log.w(TAG,"else "+isvaliddate);
                }

            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    @SuppressLint("LogNotTimber")
    @Override
    public void manageProductsDealsListener(boolean status, String productid) {
        Log.w(TAG,"status : "+status+" productid : "+productid);
        if(status){
            clearTodayDealsResponseCall(status,productid);
        }

    }


    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void clearTodayDealsResponseCall(boolean status, String productid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderUpdateResponse> call = apiInterface.clearTodayDealsResponseCall(RestUtils.getContentType(), todayDealsClearRequest(status,productid));
        Log.w(TAG,"clearTodayDealsResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorOrderUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Response<VendorOrderUpdateResponse> response) {

                Log.w(TAG,"clearTodayDealsResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        showClearDealsSuccess(response.body().getMessage());



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
                Log.w(TAG,"clearTodayDealsResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private TodayDealsClearRequest todayDealsClearRequest(boolean status, String productid) {

        /*
         * _id : 602e11404775fa0735d7bf40
         * status : false
         */

        TodayDealsClearRequest todayDealsClearRequest = new TodayDealsClearRequest();
        todayDealsClearRequest.set_id(productid);
        todayDealsClearRequest.setStatus(false);
        Log.w(TAG,"todayDealsClearRequest"+ "--->" + new Gson().toJson(todayDealsClearRequest));
        return todayDealsClearRequest;
    }

    private void showClearDealsSuccess(String message) {
        try {
            Dialog dialog = new Dialog(ManageProductsActivity.this);
            dialog.setContentView(R.layout.addreview_review_success_layout);
            dialog.setCancelable(false);
            Button btn_back = dialog.findViewById(R.id.btn_back);
            TextView txt_success = dialog.findViewById(R.id.txt_success);
            txt_success.setText(message);
            btn_back.setOnClickListener(view -> {
                dialog.dismiss();
                if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                    getlist_from_vendor_id_ResponseCall();
                }


            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }


//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.home:
//                callDirections("1");
//                break;
//            case R.id.feeds:
//                callDirections("2");
//                break;
//
//            case R.id.community:
//                callDirections("3");
//                break;
//
//            default:
//                return  false;
//        }
//
//        return false;
//    }

    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), VendorDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }
}