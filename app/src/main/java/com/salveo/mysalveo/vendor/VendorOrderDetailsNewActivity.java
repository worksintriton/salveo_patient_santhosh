package com.salveo.mysalveo.vendor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.ProductDetailsVendorAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.OnItemCheckConfirmStatus;
import com.salveo.mysalveo.interfaces.OnItemCheckDispatchStatus;
import com.salveo.mysalveo.interfaces.OnItemCheckRejectStatus;
import com.salveo.mysalveo.requestpojo.VendorOrderDetailsListRequest;
import com.salveo.mysalveo.requestpojo.VendorOrderUpdateDispatchRequest;
import com.salveo.mysalveo.requestpojo.VendorOrderUpdateRejectRequest;
import com.salveo.mysalveo.requestpojo.VendorOrderUpdateRequest;
import com.salveo.mysalveo.responsepojo.PetLoverVendorOrderDetailsResponse;
import com.salveo.mysalveo.responsepojo.VendorOrderUpdateResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorOrderDetailsNewActivity extends AppCompatActivity implements View.OnClickListener, OnItemCheckConfirmStatus, OnItemCheckRejectStatus, OnItemCheckDispatchStatus {

    private static final String TAG = "VendorOrderDetailsNewActivity" ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_products_image)
    ImageView img_products_image;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_product_title)
    TextView txt_product_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_products_price)
    TextView txt_products_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_status)
    TextView txt_order_status;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_delivered_date)
    TextView txt_delivered_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_date)
    TextView txt_order_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_booking_id)
    TextView txt_booking_id;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_payment_method)
    TextView txt_payment_method;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_order_cost)
    TextView txt_total_order_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_quantity)
    TextView txt_quantity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_name)
    TextView txt_shipping_address_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_street)
    TextView txt_shipping_address_street;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_city)
    TextView txt_shipping_address_city;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_state_pincode)
    TextView txt_shipping_address_state_pincode;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_phone)
    TextView txt_shipping_address_phone;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_landmark)
    TextView txt_shipping_address_landmark;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_order_status)
    ImageView img_order_status;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_expand_arrow)
    ImageView img_expand_arrow;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_orderdetails)
    LinearLayout ll_orderdetails;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_expand_arrow_shipping)
    ImageView img_expand_arrow_shipping;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_shippingaddress)
    LinearLayout ll_shippingaddress;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_productdetails)
    RecyclerView rv_productdetails;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_expand_arrow_productdetails)
    ImageView img_expand_arrow_productdetails;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_productdetails)
    LinearLayout ll_productdetails;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_products)
    TextView txt_no_products;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_confirmall)
    TextView txt_confirmall;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.scrollablContent)
    ScrollView scrollablContent;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_update_status)
    Button btn_update_status;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_vendor_footer)
    View include_vendor_footer;

    BottomNavigationView bottom_navigation_view;



    private String _id;
    private String orderid;
    private String fromactivity;

    private  boolean button1IsVisible = true;
    private  boolean ShippingIsVisible = true;
    private  boolean productsIsVisible = true;

    private List<Integer> product_id = new ArrayList<>();
    private List<Integer> product_id_confirmList = new ArrayList<>();
    private List<Integer> product_id_rejectList = new ArrayList<>();
    private List<Integer> product_id_dispatchList = new ArrayList<>();
    private List<PetLoverVendorOrderDetailsResponse.DataBean.ProductDetailsBean> productdetailslist;
    private Dialog alertDialog;

    private boolean isConfirmaAll = false;
    private boolean isConfirmaAndReject = false;
    private boolean isConfirmaAndRejectAndDispatch = false;
    private boolean isConfirmaAndDispatch = false;
    private boolean isRejectAndDispatch = false;

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

    @SuppressLint({"LogNotTimber", "LongLogTag", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_order_details_new);
        ButterKnife.bind(this);
        img_back.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _id = extras.getString("_id");
            orderid = _id;
            fromactivity = extras.getString("fromactivity");
            Log.w(TAG,"_id : "+_id+" fromactivity : "+ fromactivity);



        }
     scrollablContent.setVisibility(View.GONE);

//        bottom_navigation_view = include_vendor_footer.findViewById(R.id.bottom_navigation_view);
//        bottom_navigation_view.setItemIconTintList(null);
//        bottom_navigation_view.getMenu().findItem(R.id.home).setChecked(true);
//        bottom_navigation_view.setOnNavigationItemSelectedListener(this);

        /*home*/

        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop_selector);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);

        rl_home.setOnClickListener(this);

        rl_shop.setOnClickListener(this);

        rl_comn.setOnClickListener(this);


        rl_homes.setOnClickListener(this);


        if (new ConnectionDetector(VendorOrderDetailsNewActivity.this).isNetworkAvailable(VendorOrderDetailsNewActivity.this)) {
         if (APIClient.VENDOR_ID != null && !APIClient.VENDOR_ID.isEmpty()) {
            vendorOrderDetailsResponseCall(APIClient.VENDOR_ID);
         }
     }

        ll_orderdetails.setVisibility(View.GONE);
        ll_shippingaddress.setVisibility(View.GONE);

        img_expand_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.w(TAG, "button1IsVisible : "+button1IsVisible);

                if(button1IsVisible) {
                    ll_orderdetails.setVisibility(View.VISIBLE);
                    img_expand_arrow.setImageResource(R.drawable.ic_up);
                    button1IsVisible = false;
                }
                else {
                    ll_orderdetails.setVisibility(View.GONE);
                    img_expand_arrow.setImageResource(R.drawable.ic_down);
                    button1IsVisible = true;

                }


            }
        });
        img_expand_arrow_shipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.w(TAG, "ShippingIsVisible : "+ShippingIsVisible);

                if(ShippingIsVisible) {
                    ll_shippingaddress.setVisibility(View.VISIBLE);
                    img_expand_arrow_shipping.setImageResource(R.drawable.ic_up);
                    ShippingIsVisible = false;
                } else {
                    ll_shippingaddress.setVisibility(View.GONE);
                    img_expand_arrow_shipping.setImageResource(R.drawable.ic_down);
                    ShippingIsVisible = true;

                }


            }
        });
        img_expand_arrow_productdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.w(TAG, "productsIsVisible : "+productsIsVisible);

                if(productsIsVisible) {
                    ll_productdetails.setVisibility(View.GONE);
                    img_expand_arrow_productdetails.setImageResource(R.drawable.ic_down);
                    productsIsVisible = false;
                } else {
                    ll_productdetails.setVisibility(View.VISIBLE);
                    img_expand_arrow_productdetails.setImageResource(R.drawable.ic_up);
                    productsIsVisible = true;

                }


            }
        });
        txt_confirmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_confirmall.getText().toString().equalsIgnoreCase("Confirm All")){
                    txt_confirmall.setText("Clear All");
                    btn_update_status.setVisibility(View.VISIBLE);
                    if(productdetailslist != null && productdetailslist.size()>0){
                        setView(productdetailslist,true);
                    }
                    isConfirmaAll = true;
                }else{
                    txt_confirmall.setText("Confirm All");
                    btn_update_status.setVisibility(View.GONE);
                    setView(productdetailslist,false);
                    isConfirmaAll = false;
                }

            }
        });
        btn_update_status.setVisibility(View.GONE);

        btn_update_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w(TAG,"product_id list : "+ new Gson().toJson(product_id));
                if(isConfirmaAll){
                    vendor_update_order_confirm_ResponseCall(product_id);
                }
                else if(product_id_confirmList != null && product_id_confirmList.size()>0 && product_id_rejectList != null &&  product_id_rejectList.size()>0){
                    isConfirmaAndReject = true;
                    if(product_id_dispatchList != null &&  product_id_dispatchList.size()>0){
                        isConfirmaAndRejectAndDispatch = true;
                    }
                    vendor_update_order_confirm_ResponseCall(product_id_confirmList);
                }else if(product_id_rejectList != null &&  product_id_rejectList.size()>0 && product_id_dispatchList != null &&  product_id_dispatchList.size()>0){
                    isRejectAndDispatch = true;
                    addCancelReason(product_id_rejectList);
                }else if(product_id_confirmList != null &&  product_id_confirmList.size()>0 && product_id_dispatchList != null &&  product_id_dispatchList.size()>0){
                    isConfirmaAndDispatch = true;
                    vendor_update_order_confirm_ResponseCall(product_id_confirmList);
                }
                else if(product_id_confirmList != null && product_id_confirmList.size()>0){
                    vendor_update_order_confirm_ResponseCall(product_id_confirmList);
                }else if(product_id_rejectList != null &&  product_id_rejectList.size()>0){
                    addCancelReason(product_id_rejectList);

                } else if(product_id_dispatchList != null &&  product_id_dispatchList.size()>0){
                    addDispatch(product_id_dispatchList);

                }
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("FragementNewOrders")) {
            startActivity(new Intent(getApplicationContext(), VendorDashboardActivity.class));
            finish();
        }else {
            finish();
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.img_back:

                onBackPressed();
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

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void vendorOrderDetailsResponseCall(String vendorId) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<PetLoverVendorOrderDetailsResponse> call = apiInterface.get_product_list_by_vendor_ResponseCall(RestUtils.getContentType(), vendorOrderDetailsListRequest(vendorId));
        Log.w(TAG,"vendorOrderDetailsResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<PetLoverVendorOrderDetailsResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<PetLoverVendorOrderDetailsResponse> call, @NonNull Response<PetLoverVendorOrderDetailsResponse> response) {

                Log.w(TAG,"vendorOrderDetailsResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        scrollablContent.setVisibility(View.VISIBLE);

                        if(response.body().getData()!=null){


                            if(response.body().getData().getOrder_details().getOrder_text() !=null && !response.body().getData().getOrder_details().getOrder_text().isEmpty()){
                                txt_product_title.setText(response.body().getData().getOrder_details().getOrder_text());
                            }
                            if(response.body().getData().getOrder_details().getOrder_price()!=0){
                                txt_products_price.setText("\u20B9 "+response.body().getData().getOrder_details().getOrder_price());
                            }

                            if (response.body().getData().getOrder_details().getOrder_price() != 0 && response.body().getData().getOrder_details().getOrder_product() != 0) {
                                if (response.body().getData().getOrder_details().getOrder_product() == 1) {
                                    txt_products_price.setText("\u20B9 " + response.body().getData().getOrder_details().getOrder_price() + " (" + response.body().getData().getOrder_details().getOrder_product() + " product )");
                                } else {
                                    txt_products_price.setText("\u20B9 " + response.body().getData().getOrder_details().getOrder_price() + " (" + response.body().getData().getOrder_details().getOrder_product() + " products )");

                                }
                            }
                            else { if (response.body().getData().getOrder_details().getOrder_product() == 1) {
                                txt_products_price.setText("\u20B9 " + 0 + " (" + response.body().getData().getOrder_details().getOrder_product() + " product )");
                            } else {
                                txt_products_price.setText("\u20B9 " + 0 + " (" + response.body().getData().getOrder_details().getOrder_product() + " products )"); } }






                            if(response.body().getData().getOrder_details().getOrder_booked()!=null){
                                txt_order_date.setText(response.body().getData().getOrder_details().getOrder_booked());
                            }
                            if(response.body().getData().getOrder_details().getOrder_id()!=null){
                                txt_booking_id.setText(response.body().getData().getOrder_details().getOrder_id());

                            }
                            if(response.body().getData().getOrder_details().getOrder_price() !=0){
                                txt_total_order_cost.setText("\u20B9 "+response.body().getData().getOrder_details().getOrder_price());
                            }
                            if(response.body().getData().getOrder_details().getOrder_product() !=0){
                                txt_quantity.setText(""+response.body().getData().getOrder_details().getOrder_product());
                            }

                            if(response.body().getData().getShipping_address() !=null){
                                txt_shipping_address_name.setText(response.body().getData().getShipping_address().getUser_first_name()+" "+response.body().getData().getShipping_address().getUser_last_name());
                                txt_shipping_address_street.setText(response.body().getData().getShipping_address().getUser_flat_no()+" ,"+response.body().getData().getShipping_address().getUser_stree()+", ");
                                txt_shipping_address_city.setText(response.body().getData().getShipping_address().getUser_city());
                                txt_shipping_address_state_pincode.setText(response.body().getData().getShipping_address().getUser_state()+" - "+response.body().getData().getShipping_address().getUser_picocode());
                                if(response.body().getData().getShipping_address().getUser_mobile() != null && !response.body().getData().getShipping_address().getUser_mobile().isEmpty()) {
                                    txt_shipping_address_phone.setText("Phone : " + response.body().getData().getShipping_address().getUser_mobile());
                                }
                                if(response.body().getData().getShipping_address().getUser_landmark() != null && !response.body().getData().getShipping_address().getUser_landmark().isEmpty()) {
                                    txt_shipping_address_landmark.setText("Landmark : " + response.body().getData().getShipping_address().getUser_landmark());
                                }

                            }

                            if (response.body().getData().getOrder_details().getOrder_image() != null && !response.body().getData().getOrder_details().getOrder_image().isEmpty()) {
                                Glide.with(getApplicationContext())
                                        .load(response.body().getData().getOrder_details().getOrder_image())
                                        .into(img_products_image);
                            }
                            else{
                                Glide.with(getApplicationContext())
                                        .load(APIClient.PROFILE_IMAGE_URL)
                                        .into(img_products_image);

                            }

                            if(fromactivity != null && fromactivity.equalsIgnoreCase("FragementNewOrders")){
                                txt_order_status.setText("Booked on");
                                img_order_status.setImageResource(R.drawable.completed);
                                if(response.body().getData().getOrder_details().getOrder_booked() != null){
                                    txt_delivered_date.setText(response.body().getData().getOrder_details().getOrder_booked());
                                }
                            }
                            else if(fromactivity != null && fromactivity.equalsIgnoreCase("FragmentCompletedOrders")){
                                txt_order_status.setText("Delivered on");
                                img_order_status.setImageResource(R.drawable.completed);
                                if(response.body().getData().getOrder_details().getOrder_completed() != null){
                                    txt_delivered_date.setText(response.body().getData().getOrder_details().getOrder_completed());
                                }
                            }
                            else if(fromactivity != null && fromactivity.equalsIgnoreCase("FragmentCancelledOrders")) {
                                txt_order_status.setText("Cancelled on");
                                img_order_status.setImageResource(R.drawable.ic_baseline_cancel_24);
                                if (response.body().getData().getOrder_details().getOrder_cancelled() != null && !response.body().getData().getOrder_details().getOrder_cancelled().isEmpty()) {
                                    txt_delivered_date.setText(response.body().getData().getOrder_details().getOrder_cancelled());
                                }


                            }

                            if(response.body().getData().getProduct_details() != null && response.body().getData().getProduct_details().size()>0){
                                rv_productdetails.setVisibility(View.VISIBLE);
                                txt_no_products.setVisibility(View.GONE);
                                productdetailslist = response.body().getData().getProduct_details();
                                Log.w(TAG,"ProductsDetails size : "+productdetailslist.size()+" productdetails "+new Gson().toJson(productdetailslist));
                                for(int i=0; i<productdetailslist.size();i++){
                                    int productid = response.body().getData().getProduct_details().get(i).getProduct_id();
                                    String productstatus =  response.body().getData().getProduct_details().get(i).getProduct_stauts();
                                    product_id.add(productid);
                                    if(productstatus != null){
                                        if(fromactivity != null && fromactivity.equalsIgnoreCase("FragmentCancelledOrders")) {
                                            txt_confirmall.setVisibility(View.GONE);
                                        }else{
                                            if(productstatus.equalsIgnoreCase("Order Booked")){
                                                txt_confirmall.setVisibility(View.VISIBLE);
                                            }else{
                                                txt_confirmall.setVisibility(View.GONE);
                                            }
                                        }


                                    }
                                }
                                Log.w(TAG,"product_id List : "+new Gson().toJson(product_id));
                                setView(response.body().getData().getProduct_details(),false);

                            }else{
                                rv_productdetails.setVisibility(View.GONE);
                                txt_no_products.setVisibility(View.VISIBLE);
                                txt_no_products.setText("No products found");
                            }






                        }


                    }

                }


            }

            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onFailure(@NonNull Call<PetLoverVendorOrderDetailsResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"VendorOrderDetailsResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private VendorOrderDetailsListRequest vendorOrderDetailsListRequest(String vendorId) {
        VendorOrderDetailsListRequest vendorOrderDetailsListRequest = new VendorOrderDetailsListRequest();
        vendorOrderDetailsListRequest.setOrder_id(_id);
        vendorOrderDetailsListRequest.setVendor_id(vendorId);
        Log.w(TAG,"vendorOrderDetailsListRequest"+ "--->" + new Gson().toJson(vendorOrderDetailsListRequest));
        return vendorOrderDetailsListRequest;
    }


    private void setView(List<PetLoverVendorOrderDetailsResponse.DataBean.ProductDetailsBean> product_details,boolean status) {
        rv_productdetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_productdetails.setItemAnimator(new DefaultItemAnimator());
        ProductDetailsVendorAdapter productDetailsVendorAdapter = new ProductDetailsVendorAdapter(getApplicationContext(),product_details,orderid,this,this,this,status,fromactivity);
        rv_productdetails.setAdapter(productDetailsVendorAdapter);

    }


    @SuppressLint({"LongLogTag", "LogNotTimber"})
    @Override
    public void onItemCheckConfirmStatus(int productid) {
        product_id_confirmList.add(productid);
        Log.w(TAG,"onItemCheckConfirmStatus product_id_confirmList : "+new Gson().toJson(product_id_confirmList)+" product_id_rejectList : "+new Gson().toJson(product_id_rejectList));
        if(product_id_confirmList != null && product_id_confirmList.size()>0){
            btn_update_status.setVisibility(View.VISIBLE);
        }else{
            btn_update_status.setVisibility(View.GONE);
        }
        if(product_id_rejectList != null && product_id_rejectList.size()>0 || product_id_confirmList != null && product_id_confirmList.size()>0){
            btn_update_status.setVisibility(View.VISIBLE);
        }else{
            btn_update_status.setVisibility(View.GONE);
        }

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    @Override
    public void onItemUncheckConfirmStatus(int productid) {
        if(product_id_confirmList != null){
                for (int i = 0; i < product_id_confirmList.size(); i++)
                    if (product_id_confirmList.get(i).equals(productid)) {
                        product_id_confirmList.remove(i);
                        Log.w(TAG, "onItemUncheckConfirmStatus after removing list if" + new Gson().toJson(product_id_confirmList));

                    }

            }
        if(product_id_rejectList != null && product_id_rejectList.size()>0 || product_id_confirmList != null && product_id_confirmList.size()>0){
            btn_update_status.setVisibility(View.VISIBLE);
        }
        else{
            btn_update_status.setVisibility(View.GONE);
        }



       }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    public void onItemCheckRejectStatus(int productid) {
        product_id_rejectList.add(productid);
        Log.w(TAG,"onItemCheckRejectStatus product_id_rejectList : "+new Gson().toJson(product_id_rejectList));
        if(product_id_rejectList != null && product_id_rejectList.size()>0 || product_id_confirmList != null && product_id_confirmList.size()>0){
            btn_update_status.setVisibility(View.VISIBLE);
        }else{
            btn_update_status.setVisibility(View.GONE);
        }

    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    public void onItemUncheckRejectStatus(int productid) {
        if(product_id_rejectList != null){
            for (int i = 0; i < product_id_rejectList.size(); i++)
                if (product_id_rejectList.get(i).equals(productid)) {
                    product_id_rejectList.remove(i);
                    Log.w(TAG, "onItemUncheckConfirmStatus after removing list if" + new Gson().toJson(product_id_rejectList));

                }

        }
        if(product_id_rejectList != null && product_id_rejectList.size()>0 || product_id_confirmList != null && product_id_confirmList.size()>0){
            btn_update_status.setVisibility(View.VISIBLE);
        }else{
            btn_update_status.setVisibility(View.GONE);
        }


    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    public void onItemCheckDispatchStatus(int productid) {
        product_id_dispatchList.add(productid);
        Log.w(TAG,"onItemCheckDispatchStatus product_id_dispatchList : "+new Gson().toJson(product_id_dispatchList));
        if(product_id_dispatchList != null && product_id_dispatchList.size()>0){
            btn_update_status.setVisibility(View.VISIBLE);
        }else{
            btn_update_status.setVisibility(View.GONE);
        }

    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    public void onItemUncheckDispatchStatus(int productid) {
        Log.w(TAG,"onItemUncheckDispatchStatus : "+productid);
        if(product_id_dispatchList != null){
            for (int i = 0; i < product_id_dispatchList.size(); i++)
                if (product_id_dispatchList.get(i).equals(productid)) {
                    product_id_dispatchList.remove(i);
                    Log.w(TAG, "onItemUncheckDispatchStatus after removing list if" + new Gson().toJson(product_id_dispatchList));

                }

        }
        if(product_id_dispatchList != null && product_id_dispatchList.size()>0){
            btn_update_status.setVisibility(View.VISIBLE);
        }else{
            btn_update_status.setVisibility(View.GONE);
        }


    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void addCancelReason(List<Integer> product_id_rejectList) {
        try {

            Dialog dialog = new Dialog(VendorOrderDetailsNewActivity.this);
            dialog.setContentView(R.layout.add_trackid_popup);
            dialog.setCancelable(false);
            EditText edt_addtrackid = dialog.findViewById(R.id.edt_addtrackid);
            Button btn_addtrackid = dialog.findViewById(R.id.btn_addtrackid);
            edt_addtrackid.setHint("Please enter the reason...");

            btn_addtrackid.setOnClickListener(view -> {
                if(!edt_addtrackid.getText().toString().isEmpty()){
                    dialog.dismiss();
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        vendor_update_order_reject_ResponseCall(product_id_rejectList,edt_addtrackid.getText().toString());

                    }
                }else{
                    showErrorLoading("Please enter the reason");
                }


            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }

      

    }
    private void addDispatch(List<Integer> product_id_dispatchList) {
        try {
            Dialog dialog = new Dialog(VendorOrderDetailsNewActivity.this);
            dialog.setContentView(R.layout.add_trackid_popup);
            dialog.setCancelable(false);
            EditText edt_addtrackid = dialog.findViewById(R.id.edt_addtrackid);
            Button btn_addtrackid = dialog.findViewById(R.id.btn_addtrackid);
            edt_addtrackid.setHint("Please enter the track id...");

            btn_addtrackid.setOnClickListener(view -> {
                if(edt_addtrackid.getText().toString() != null &&  !edt_addtrackid.getText().toString().isEmpty()){
                    dialog.dismiss();
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        vendor_update_order_dispatch_ResponseCall(product_id_dispatchList,edt_addtrackid.getText().toString());

                    }
                }else{
                    showErrorLoading("Please enter the track id");
                }


            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }



    }



    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VendorOrderDetailsNewActivity.this);
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


    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void vendor_update_order_confirm_ResponseCall(List<Integer> product_id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderUpdateResponse> call = apiInterface.vendor_update_order_confirm_ResponseCall(RestUtils.getContentType(), vendorOrderUpdateRequest(product_id));
        Log.w(TAG,"vendor_update_order_confirm_ResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorOrderUpdateResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Response<VendorOrderUpdateResponse> response) {
                Log.w(TAG,"vendorOrderDetailsResponseCall"+ "--->" + new Gson().toJson(response.body()));
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(isConfirmaAndReject){
                            addCancelReason(product_id_rejectList);
                        }else if(isConfirmaAndDispatch){
                            addDispatch(product_id_dispatchList);
                        }else{
                            finish();
                            startActivity(getIntent());
                        }



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
    private VendorOrderUpdateRequest vendorOrderUpdateRequest(List<Integer> product_id) {
        /*
         * order_id : ORDER-1618919599393
         * product_id : [0,1,2]
         * date : 20-04-2021 12:47 PM
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        VendorOrderUpdateRequest vendorOrderUpdateRequest = new VendorOrderUpdateRequest();
        vendorOrderUpdateRequest.setOrder_id(_id);
        vendorOrderUpdateRequest.setProduct_id(product_id);
        vendorOrderUpdateRequest.setDate(currentDateandTime);
        vendorOrderUpdateRequest.setVendor_id(APIClient.VENDOR_ID);
        Log.w(TAG,"vendorOrderUpdateRequest"+ "--->" + new Gson().toJson(vendorOrderUpdateRequest));
        return vendorOrderUpdateRequest;
    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void vendor_update_order_reject_ResponseCall(List<Integer> product_id, String rejectReason) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderUpdateResponse> call = apiInterface.vendor_update_order_reject_ResponseCall(RestUtils.getContentType(), vendorOrderUpdateRejectRequest(product_id,rejectReason));
        Log.w(TAG,"vendor_update_order_confirm_ResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorOrderUpdateResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Response<VendorOrderUpdateResponse> response) {
                Log.w(TAG,"vendorOrderDetailsResponseCall"+ "--->" + new Gson().toJson(response.body()));
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(isConfirmaAndRejectAndDispatch){
                            addDispatch(product_id_dispatchList);
                        }else if(isRejectAndDispatch){
                            addDispatch(product_id_dispatchList);
                        }else {
                            finish();
                            startActivity(getIntent());
                        }

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
    private VendorOrderUpdateRejectRequest vendorOrderUpdateRejectRequest(List<Integer> product_id, String rejectReason) {
        /*
         * order_id : ORDER-1618919599393
         * product_id : [0]
         * date : 20-04-2021 12:47 PM
         * reject_reason : we are not having the product currently
         * vendor_id
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        VendorOrderUpdateRejectRequest vendorOrderUpdateRejectRequest = new VendorOrderUpdateRejectRequest();
        vendorOrderUpdateRejectRequest.setOrder_id(_id);
        vendorOrderUpdateRejectRequest.setProduct_id(product_id);
        vendorOrderUpdateRejectRequest.setDate(currentDateandTime);
        vendorOrderUpdateRejectRequest.setReject_reason(rejectReason);
        vendorOrderUpdateRejectRequest.setVendor_id(APIClient.VENDOR_ID);
        Log.w(TAG,"vendorOrderUpdateRejectRequest"+ "--->" + new Gson().toJson(vendorOrderUpdateRejectRequest));
        return vendorOrderUpdateRejectRequest;
    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void vendor_update_order_dispatch_ResponseCall(List<Integer> product_id_dispatchList, String trackid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderUpdateResponse> call = apiInterface.vendor_update_order_dispatch_ResponseCall(RestUtils.getContentType(), vendorOrderUpdateDispatchRequest(product_id_dispatchList,trackid));
        Log.w(TAG,"vendor_update_order_confirm_ResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorOrderUpdateResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Response<VendorOrderUpdateResponse> response) {
                Log.w(TAG,"vendorOrderDetailsResponseCall"+ "--->" + new Gson().toJson(response.body()));
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        finish();
                        startActivity(getIntent());

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
    private VendorOrderUpdateDispatchRequest vendorOrderUpdateDispatchRequest(List<Integer> product_id_dispatchList, String trackid) {
        /*
         * order_id : ORDER-1618919599393
         * product_id : [0]
         * date : 20-04-2021 12:47 PM
         * track_id : 1234 tracid
         * vendor_id
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        VendorOrderUpdateDispatchRequest vendorOrderUpdateDispatchRequest = new VendorOrderUpdateDispatchRequest();
        vendorOrderUpdateDispatchRequest.setOrder_id(_id);
        vendorOrderUpdateDispatchRequest.setProduct_id(product_id_dispatchList);
        vendorOrderUpdateDispatchRequest.setDate(currentDateandTime);
        vendorOrderUpdateDispatchRequest.setTrack_id(trackid);
        vendorOrderUpdateDispatchRequest.setVendor_id(APIClient.VENDOR_ID);
        Log.w(TAG,"vendorOrderUpdateDispatchRequest"+ "--->" + new Gson().toJson(vendorOrderUpdateDispatchRequest));
        return vendorOrderUpdateDispatchRequest;
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