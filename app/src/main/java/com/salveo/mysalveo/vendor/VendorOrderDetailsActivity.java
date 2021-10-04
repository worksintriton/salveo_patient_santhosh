package com.salveo.mysalveo.vendor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.VendorOrderDetailsRequest;
import com.salveo.mysalveo.responsepojo.VendorOrderDetailsResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorOrderDetailsActivity extends AppCompatActivity implements View.OnClickListener{


    private final String TAG = "VendorOrderDetailsActivity";
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
    @BindView(R.id.scrollablContent)
    ScrollView scrollablContent;

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

    private String _id;
    private String fromactivity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_vendor_footer)
    View include_vendor_footer;

    BottomNavigationView bottom_navigation_view;


    @SuppressLint({"SetTextI18n", "LogNotTimber", "LongLogTag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_order_details);

        ButterKnife.bind(this);

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

        img_back.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _id = extras.getString("_id");
             fromactivity = extras.getString("fromactivity");
            Log.w(TAG,"_id : "+_id+" fromactivity : "+ fromactivity);




        }

        if (new ConnectionDetector(VendorOrderDetailsActivity.this).isNetworkAvailable(VendorOrderDetailsActivity.this)) {
            vendorOrderDetailsResponseCall();

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void vendorOrderDetailsResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderDetailsResponse> call = apiInterface.vendorOrderDetailsResponseCall(RestUtils.getContentType(), vendorOrderDetailsRequest());
        Log.w(TAG,"vendorOrderDetailsResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorOrderDetailsResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<VendorOrderDetailsResponse> call, @NonNull Response<VendorOrderDetailsResponse> response) {

                Log.w(TAG,"vendorOrderDetailsResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        scrollablContent.setVisibility(View.VISIBLE);

                        if(response.body().getData()!=null){

                            if(response.body().getData().getProduct_name()!=null&&!(response.body().getData().getProduct_name().isEmpty())){
                                txt_product_title.setText(response.body().getData().getProduct_name());
                            }
                            if(response.body().getData().getProduct_price()!=0){
                                txt_products_price.setText("\u20B9 "+response.body().getData().getProduct_price());
                            }
                            if(response.body().getData().getDate_of_booking_display()!=null){
                                txt_order_date.setText(response.body().getData().getDate_of_booking_display());
                            } if(response.body().getData().getOrder_id()!=null){
                                txt_booking_id.setText(response.body().getData().getOrder_id());
                            } if(response.body().getData().getGrand_total() !=0){
                                txt_total_order_cost.setText("\u20B9 "+response.body().getData().getGrand_total());
                            }if(response.body().getData().getProduct_quantity() !=0){
                                txt_quantity.setText(""+response.body().getData().getProduct_quantity());
                            }
                            if(response.body().getData().getProduct_quantity() !=0){
                                txt_quantity.setText(""+response.body().getData().getProduct_quantity());
                            }if(response.body().getData().getShipping_details_id() !=null){
                                txt_shipping_address_name.setText(response.body().getData().getShipping_details_id().getUser_first_name()+" "+response.body().getData().getShipping_details_id().getUser_last_name());
                                txt_shipping_address_street.setText(response.body().getData().getShipping_details_id().getUser_flat_no()+" ,"+response.body().getData().getShipping_details_id().getUser_stree()+", ");
                                txt_shipping_address_city.setText(response.body().getData().getShipping_details_id().getUser_city());
                                txt_shipping_address_state_pincode.setText(response.body().getData().getShipping_details_id().getUser_state()+" - "+response.body().getData().getShipping_details_id().getUser_picocode());
                                if(response.body().getData().getShipping_details_id().getUser_mobile() != null && !response.body().getData().getShipping_details_id().getUser_mobile().isEmpty()) {
                                    txt_shipping_address_phone.setText("Phone : " + response.body().getData().getShipping_details_id().getUser_mobile());
                                }
                                if(response.body().getData().getShipping_details_id().getUser_landmark() != null && !response.body().getData().getShipping_details_id().getUser_landmark().isEmpty()) {
                                    txt_shipping_address_landmark.setText("Landmark : " + response.body().getData().getShipping_details_id().getUser_landmark());
                                }

                            }

                            if (response.body().getData().getProdcut_image() != null && !response.body().getData().getProdcut_image().isEmpty()) {
                                Glide.with(getApplicationContext())
                                        .load(response.body().getData().getProdcut_image())
                                        .into(img_products_image);
                            }
                            else{
                                Glide.with(getApplicationContext())
                                        .load(APIClient.PROFILE_IMAGE_URL)
                                        .into(img_products_image);

                            }

                            if(fromactivity != null && fromactivity.equalsIgnoreCase("PetVendorNewOrdersAdapter")){
                                txt_order_status.setText("Booked on");
                                img_order_status.setImageResource(R.drawable.completed);
                                if(response.body().getData().getDate_of_booking() != null){
                                    txt_delivered_date.setText(response.body().getData().getDate_of_booking());
                                }
                            }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PetVendorCompletedOrdersAdapter")){
                                txt_order_status.setText("Delivered on");
                                img_order_status.setImageResource(R.drawable.completed);
                                if(response.body().getData().getVendor_complete_date() != null){
                                    txt_delivered_date.setText(response.body().getData().getVendor_complete_date());
                                }
                            }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PetVendorCancelledOrdersAdapter")){
                                txt_order_status.setText("Cancelled on");
                                img_order_status.setImageResource(R.drawable.ic_baseline_cancel_24);
                                if(response.body().getData().getVendor_cancell_date() != null && !response.body().getData().getVendor_cancell_date().isEmpty()){
                                    txt_delivered_date.setText(response.body().getData().getVendor_cancell_date());
                                }else if(response.body().getData().getUser_cancell_date() != null && !response.body().getData().getUser_cancell_date().isEmpty()){
                                    txt_delivered_date.setText(response.body().getData().getUser_cancell_date());
                                }

                            }






                        }


                    }

                }


            }

            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onFailure(@NonNull Call<VendorOrderDetailsResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"VendorOrderDetailsResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private VendorOrderDetailsRequest vendorOrderDetailsRequest() {
        VendorOrderDetailsRequest vendorOrderDetailsRequest = new VendorOrderDetailsRequest();
        vendorOrderDetailsRequest.set_id(_id);
        Log.w(TAG,"vendorOrderDetailsRequest"+ "--->" + new Gson().toJson(vendorOrderDetailsRequest));
        return vendorOrderDetailsRequest;
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