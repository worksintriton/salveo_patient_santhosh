package com.salveo.mysalveo.doctor.shop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.RelatedProductsAdapter;
import com.salveo.mysalveo.adapter.VendorBusinessGalleryListAdapter;
import com.salveo.mysalveo.adapter.ViewPagerProductDetailsAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.doctor.DoctorDashboardActivity;

import com.salveo.mysalveo.doctor.DoctorProfileScreenActivity;
import com.salveo.mysalveo.requestpojo.CartAddProductRequest;
import com.salveo.mysalveo.requestpojo.DoctorProductFavListCreateRequest;
import com.salveo.mysalveo.requestpojo.FetchByIdRequest;
import com.salveo.mysalveo.requestpojo.NotificationCartCountRequest;
import com.salveo.mysalveo.responsepojo.FetchProductByIdResponse;
import com.salveo.mysalveo.responsepojo.NotificationCartCountResponse;
import com.salveo.mysalveo.responsepojo.SuccessResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private  String TAG = "DoctorProductDetailsActivity";

    String prod_type ="";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_products_title)
    TextView txt_products_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_products_price)
    TextView txt_products_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_product_discount_price)
    TextView txt_product_discount_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_discount)
    TextView txt_discount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_products_quantity)
    TextView txt_products_quantity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_product_desc)
    TextView txt_product_desc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_relatedproducts)
    RecyclerView rv_relatedproducts;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count)
    TextView txt_cart_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_remove_product)
    ImageView img_remove_product;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_add_product)
    ImageView img_add_product;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_add_to_cart)
    LinearLayout ll_add_to_cart;

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.txt_view_details)
//    TextView txt_view_details;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_label)
    TextView txt_cart_label;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img1)
    ImageView hand_img1;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img2)
    ImageView hand_img2;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img3)
    ImageView hand_img3;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img4)
    ImageView hand_img4;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img5)
    ImageView hand_img5;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_discount)
    RelativeLayout rl_discount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_prod_type)
    TextView txt_prod_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_prod_desc_label)
    TextView txt_prod_desc_label;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_relat_prod)
    RelativeLayout rl_relat_prod;


    Dialog dialog;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;


    private String userid;
    private String productid;
    private int product_cart_counts = 1;
    private String threshould;
    private String fromactivity;
    private String cat_id;
    private int productqty;
    private String tag;

    String business_name, vendor_name, bussiness_reg, business_location;

    List<FetchProductByIdResponse.VendorDetailsBean.BussinessGalleryBean> bussinessGalleryBeans;

    BottomSheetBehavior bottomSheetBehavior;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_fav)
    ImageView img_fav;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_cart)
    ImageView img_cart;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count_badge)
    TextView txt_cart_count_badge;


    @SuppressLint({"LogNotTimber", "SetTextI18n", "LongLogTag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_product_details);
        Log.w(TAG,"onCreate -->");
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);
        txt_cart_count_badge.setVisibility(View.GONE);



        rl_back.setOnClickListener(v -> onBackPressed());
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productid = extras.getString("productid");
            cat_id = extras.getString("cat_id");
            fromactivity = extras.getString("fromactivity");
            tag = extras.getString("tag");
        }
        if(userid != null && productid != null){
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                fetch_product_by_id_ResponseCall();
            }
        }
        txt_cart_count.setText("1");
        img_remove_product.setOnClickListener(v -> {
            Log.w(TAG,"img_remove_product setOnClickListener : product_cart_counts "+product_cart_counts);
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                if(product_cart_counts != 1) {
                    //remove_product_ResponseCall();
                    product_cart_counts--;
                    txt_cart_count.setText(product_cart_counts+"");
                    if(product_cart_counts == 1){
                        txt_cart_label.setText("Add to cart");
                    }else{
                        txt_cart_label.setText("Go to cart");
                    }

                }else{
                    txt_cart_label.setText("Add to cart");
                }
            }

        });
        img_add_product.setOnClickListener(v -> {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

                if(threshould != null){
                    productqty = Integer.parseInt(threshould);
                    int cartcount = Integer.parseInt(txt_cart_count.getText().toString());
                    Log.w(TAG," productqty : "+productqty+" cartcount : "+cartcount);
                    if(cartcount == productqty || cartcount >productqty){
                        Toasty.warning(getApplicationContext(), "You can buy only up to "+productqty+" quantity of this product", Toast.LENGTH_SHORT, true).show();
                    }else{
                       /* if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                            add_product_ResponseCall();
                        }*/
                        Log.w(TAG," productqty : "+productqty+" product_cart_counts : "+product_cart_counts);


                        product_cart_counts++;
                        if(threshould != null){
                             productqty = Integer.parseInt(threshould);
                            if(product_cart_counts > productqty){
                                Toasty.warning(getApplicationContext(), "You can buy only up to "+productqty+" quantity of this product", Toast.LENGTH_SHORT, true).show();
                            }else{
                                if(product_cart_counts != 1){
                                    txt_cart_count.setText(product_cart_counts+"");
                                    txt_cart_label.setText("Go to cart");
                                }else{
                                    txt_cart_label.setText("Add to cart");
                                }

                            }
                        }






                    }
                }



            }

        });
        ll_add_to_cart.setOnClickListener(v -> {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                cart_add_product_ResponseCall();
            }
        });

//        txt_view_details.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                showVendorDetails();
//
//            }
//        });


        viewPager.setVisibility(View.GONE);

        tabLayout.setVisibility(View.GONE);

        hand_img1.setVisibility(View.GONE);

        hand_img2.setVisibility(View.GONE);

        hand_img3.setVisibility(View.GONE);

        hand_img4.setVisibility(View.GONE);

        hand_img5.setVisibility(View.GONE);

        txt_products_title.setVisibility(View.GONE);

        txt_prod_type.setVisibility(View.GONE);

        txt_products_price.setVisibility(View.GONE);
        txt_product_discount_price.setVisibility(View.GONE);

        rl_discount.setVisibility(View.GONE);

        txt_products_quantity.setVisibility(View.GONE);

        txt_prod_desc_label.setVisibility(View.GONE);

   //     txt_view_details.setVisibility(View.GONE);

        txt_product_desc.setVisibility(View.GONE);

        rl_relat_prod.setVisibility(View.GONE);


    }

    private void showVendorDetails() {

        try {

            dialog = new Dialog(DoctorProductDetailsActivity.this);

            dialog.setContentView(R.layout.vendor_details_view_popup_layout);

            TextView txt_business_name = dialog.findViewById(R.id.txt_business_name);

            TextView txt_vendor_name = dialog.findViewById(R.id.txt_vendor_name);

            TextView txt_bussiness_reg = dialog.findViewById(R.id.txt_bussiness_reg);

            TextView txt_business_location = dialog.findViewById(R.id.txt_business_location);

            Button btn_confirm = dialog.findViewById(R.id.btn_confirm);

            RecyclerView rv_businessimg =  dialog.findViewById(R.id.rv_businessimg);

            TextView txt_no_records = dialog.findViewById(R.id.txt_no_records);
            ImageView img_close = dialog.findViewById(R.id.img_close);
            img_close.setOnClickListener(v -> dialog.dismiss());
            btn_confirm.setOnClickListener(v -> dialog.dismiss());

            if(business_name != null && !business_name.isEmpty() ){

                txt_business_name.setText(business_name);
            }

            if(vendor_name != null && !vendor_name.isEmpty() ){

                txt_vendor_name.setText(vendor_name);
            }

            if(bussiness_reg != null && !bussiness_reg.isEmpty() ){

                txt_bussiness_reg.setText(bussiness_reg);
            }

            if(bottomSheetBehavior != null && !business_location.isEmpty() ){

                txt_business_location.setText(business_location);
            }

            if(bussinessGalleryBeans != null && bussinessGalleryBeans.size()>0 ){

                rv_businessimg.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                rv_businessimg.setItemAnimator(new DefaultItemAnimator());
                VendorBusinessGalleryListAdapter businessGalleryListAdapter = new VendorBusinessGalleryListAdapter(getApplicationContext(), bussinessGalleryBeans);
                rv_businessimg.setAdapter(businessGalleryListAdapter);


            }


            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }


    }


    /**
     * method to setup the bottomsheet
     */
    private void setBottomSheet() {

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayoutDR));

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

        bottomSheetBehavior.setHideable(false);

        bottomSheetBehavior.setFitToContents(false);

        bottomSheetBehavior.setHalfExpandedRatio(0.7f);


        // Capturing the callbacks for bottom sheet
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.w("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.w("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.w("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.w("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.w("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.w("Bottom Sheet Behaviour", "STATE_HALF_EXPANDED");
                        break;
                }


            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {


            }


        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("SearchDoctorActivity")){
            Intent intent = new Intent(DoctorProductDetailsActivity.this, SearchDoctorActivity.class);
            startActivity(intent);
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("DoctorListOfProductsSeeMoreActivity")){
            Intent intent = new Intent(DoctorProductDetailsActivity.this, DoctorListOfProductsSeeMoreActivity.class);
            intent.putExtra("cat_id",cat_id);
            startActivity(intent);
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("DoctorShopTodayDealsSeeMoreActivity")){
            Intent intent = new Intent(DoctorProductDetailsActivity.this, DoctorShopTodayDealsSeeMoreActivity.class);
            startActivity(intent);
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("Cart_Adapter")){
            Intent intent = new Intent(DoctorProductDetailsActivity.this, DoctorCartActivity.class);
            startActivity(intent);
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PetLoverShopNewAdapter")){
            callDirections("1");
        }else {
            callDirections("2");
        }
    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    public void fetch_product_by_id_ResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchProductByIdResponse> call = ApiService.fetch_product_by_id_ResponseCall(RestUtils.getContentType(),fetchByIdRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetchProductByIdResponse>() {
            @SuppressLint({"LogNotTimber", "LongLogTag"})
            @Override
            public void onResponse(@NonNull Call<FetchProductByIdResponse> call, @NonNull Response<FetchProductByIdResponse> response) {
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(200 == response.body().getCode()){

                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                            notificationandCartCountResponseCall();
                        }

                        Log.w(TAG,"FetchProductByIdResponse" + new Gson().toJson(response.body()));
                        if(response.body().getProduct_details() != null){
                            String product_title = response.body().getProduct_details().getProduct_title();
                            int product_review = response.body().getProduct_details().getProduct_review();
                            double product_rating = response.body().getProduct_details().getProduct_rating();
                            int product_price = response.body().getProduct_details().getProduct_price();
                            int product_discount_price = response.body().getProduct_details().getProduct_discount_price();
                            if(product_discount_price != 0 ){
                                txt_product_discount_price.setVisibility(View.VISIBLE);
                                txt_product_discount_price.setPaintFlags(txt_product_discount_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                txt_product_discount_price.setText("INR "+product_discount_price);

                            }else{
                                txt_product_discount_price.setVisibility(View.GONE);
                                txt_product_discount_price.setText("INR "+0);
                            }
                            int product_discount = response.body().getProduct_details().getProduct_discount();
                            String  product_discription = response.body().getProduct_details().getProduct_discription();
                            int product_cart_count = response.body().getProduct_details().getProduct_cart_count();
                            threshould = response.body().getProduct_details().getThreshould();
                            prod_type = response.body().getProduct_details().getCat_id().getProduct_cate();

                            if( response.body().getVendor_details() != null){
                                if(response.body().getProduct_details().getThreshould() != null){
                                    threshould = response.body().getProduct_details().getThreshould();
                                }
                                if(response.body().getProduct_details().getCat_id().getProduct_cate() != null){
                                    prod_type = response.body().getProduct_details().getCat_id().getProduct_cate();
                                }

                                if(response.body().getVendor_details().getBussiness_name() != null) {
                                    business_name = response.body().getVendor_details().getBussiness_name();
                                }
                                if(response.body().getVendor_details().getUser_name() != null){
                                    vendor_name = response.body().getVendor_details().getUser_name();
                                }
                                if( response.body().getVendor_details().getBusiness_reg() != null){
                                    bussiness_reg = response.body().getVendor_details().getBusiness_reg();
                                }
                                if(response.body().getVendor_details().getBussiness_loc() != null) {
                                    business_location = response.body().getVendor_details().getBussiness_loc();
                                }
                                if( response.body().getVendor_details().getBussiness_gallery() != null) {
                                    bussinessGalleryBeans = response.body().getVendor_details().getBussiness_gallery();

                                }
                            }

                            viewPager.setVisibility(View.VISIBLE);

                            tabLayout.setVisibility(View.VISIBLE);

                            hand_img1.setVisibility(View.VISIBLE);

                            hand_img2.setVisibility(View.VISIBLE);

                            hand_img3.setVisibility(View.VISIBLE);

                            hand_img4.setVisibility(View.VISIBLE);

                            hand_img5.setVisibility(View.VISIBLE);

                            txt_products_title.setVisibility(View.VISIBLE);

                            txt_prod_type.setVisibility(View.VISIBLE);

                            txt_products_price.setVisibility(View.VISIBLE);

                            rl_discount.setVisibility(View.VISIBLE);


                            txt_prod_desc_label.setVisibility(View.VISIBLE);

               //             txt_view_details.setVisibility(View.VISIBLE);

                            txt_product_desc.setVisibility(View.VISIBLE);

                            rl_relat_prod.setVisibility(View.VISIBLE);

                            setBottomSheet();

                            img_fav.setOnClickListener(DoctorProductDetailsActivity.this);
                            img_cart.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getApplicationContext(), DoctorCartActivity.class);
                                    intent.putExtra("productid",productid);
                                    intent.putExtra("cat_id",cat_id);
                                    intent.putExtra("fromactivity",fromactivity);
                                    intent.putExtra("fromto",TAG);
                                    intent.putExtra("tag",tag);
                                    startActivity(intent);
                                }
                            });


                            if(response.body().getProduct_details().getProduct_img() != null && response.body().getProduct_details().getProduct_img().size()>0){
                                viewpageData(response.body().getProduct_details().getProduct_img());
                            }
                             if(response.body().getProduct_details().getProduct_related() != null && response.body().getProduct_details().getProduct_related().size()>0){
                                 setView(response.body().getProduct_details().getProduct_related());

                            }

                             if(response.body().getProduct_details().isProduct_fav()){
                                 img_fav.setBackgroundResource(R.drawable.ic_fav);
                             }else{
                                 img_fav.setBackgroundResource(R.drawable.heart_gray);
                             }
                             setUIData(product_title,prod_type,product_review,product_rating,product_price,product_discount,product_discription,product_cart_count,threshould);




                        }
                    }
                }
            }


            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<FetchProductByIdResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"FetchProductByIdResponse flr"+t.getMessage());
            }
        });

    }

    private void setView(List<FetchProductByIdResponse.ProductDetailsBean.ProductRelatedBean> product_related) {
        rv_relatedproducts.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_relatedproducts.setItemAnimator(new DefaultItemAnimator());
        RelatedProductsAdapter relatedProductsAdapter = new RelatedProductsAdapter(getApplicationContext(), product_related, prod_type,true,TAG);
        rv_relatedproducts.setAdapter(relatedProductsAdapter);

    }

    private void viewpageData(List<String> product_img) {
        tabLayout.setupWithViewPager(viewPager, true);

        ViewPagerProductDetailsAdapter viewPagerProductDetailsAdapter = new ViewPagerProductDetailsAdapter(getApplicationContext(), product_img);
        viewPager.setAdapter(viewPagerProductDetailsAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == product_img.size()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, false);
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    @SuppressLint("SetTextI18n")
    private void setUIData(String product_title, String prod_type, int product_review, double product_rating, int product_price, int product_discount, String product_discription, int product_cart_count, String threshould) {

        //product_cart_counts = product_cart_count;

        if(product_title != null && !product_title.isEmpty()){
            txt_products_title.setText(product_title);
        }

          if(prod_type != null && !prod_type.isEmpty()){
            txt_prod_type.setText(prod_type);
        }

        if(product_rating != 0 ){

            if(product_rating == 1){
                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img2.setBackgroundResource(R.drawable.ic_logo_graycolor);
                hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
                hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
            } else if(product_rating == 2){
                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
                hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
            }else if(product_rating == 3){
                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
            }else if(product_rating == 4){
                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
            } else if(product_rating == 5){
                hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
                hand_img5.setBackgroundResource(R.drawable.ic_logo_color);
            }


        }
        if(product_price != 0 ){
            txt_products_price.setText("\u20B9 "+product_price);

        }else{
            txt_products_price.setText("\u20B9 "+0);
        }
        if(product_discount != 0 ){
            rl_discount.setVisibility(View.VISIBLE);
            txt_discount.setText(product_discount+" % off");
        }else{
            rl_discount.setVisibility(View.GONE);
        }
        if(threshould != null && !threshould.isEmpty() ){
            if(threshould.equalsIgnoreCase("0")){
                txt_products_quantity.setVisibility(View.VISIBLE);
                txt_products_quantity.setText("Out Of Stock");
                txt_products_quantity.setTextColor(ContextCompat.getColor(DoctorProductDetailsActivity.this, R.color.vermillion));
                img_add_product.setVisibility(View.GONE);
                txt_cart_count.setVisibility(View.GONE);
                img_remove_product.setVisibility(View.GONE);
                ll_add_to_cart.setVisibility(View.GONE);
            }else{
                txt_products_quantity.setVisibility(View.GONE);
                img_add_product.setVisibility(View.VISIBLE);
                txt_cart_count.setVisibility(View.VISIBLE);
                img_remove_product.setVisibility(View.VISIBLE);
                ll_add_to_cart.setVisibility(View.VISIBLE);
               // txt_products_quantity.setText("Prodcut Quantity : "+threshould);
               // txt_products_quantity.setTextColor(ContextCompat.getColor(DoctorProductDetailsActivity.this, R.color.black));

            }


        }
        if(product_discription != null && !product_discription.isEmpty()){
            txt_product_desc.setText(product_discription);
        }
       /* if(product_cart_count != 0){
            txt_cart_count.setText(product_cart_count+"");
        }*/
    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private FetchByIdRequest fetchByIdRequest() {
        /*
         * user_id : 603e27792c2b43125f8cb802
         * product_id : 6034d6a5888af7628e7e17d4
         */
        FetchByIdRequest fetchByIdRequest = new FetchByIdRequest();
        fetchByIdRequest.setUser_id(userid);
        fetchByIdRequest.setProduct_id(productid);
        Log.w(TAG,"fetchByIdRequest"+ "--->" + new Gson().toJson(fetchByIdRequest));
        return fetchByIdRequest;
    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private CartAddProductRequest cartAddProductRequest() {
        /*
         * user_id : 603e27792c2b43125f8cb802
         * product_id : 602e4940f62e8d2089fba978
         * count : 3
         */
        CartAddProductRequest cartAddProductRequest = new CartAddProductRequest();
        cartAddProductRequest.setUser_id(userid);
        cartAddProductRequest.setProduct_id(productid);
        cartAddProductRequest.setCount(Integer.parseInt(txt_cart_count.getText().toString()));
        Log.w(TAG,"cartAddProductRequest"+ "--->" + new Gson().toJson(cartAddProductRequest));
        return cartAddProductRequest;
    }
    public void callDirections(String tag){
        Intent intent = new Intent(DoctorProductDetailsActivity.this, DoctorDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    public void remove_product_ResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.remove_product_ResponseCall(RestUtils.getContentType(),fetchByIdRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n", "LongLogTag"})
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"Remove SuccessResponse" + new Gson().toJson(response.body()));
                        product_cart_counts--;
                        txt_cart_count.setText(product_cart_counts+"");
                    }
                }
            }


            @SuppressLint({"LogNotTimber", "LongLogTag"})
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"Remove SuccessResponse flr"+t.getMessage());
            }
        });

    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    public void cart_add_product_ResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.cart_add_product_ResponseCall(RestUtils.getContentType(),cartAddProductRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        if(userid != null && productid != null){
                            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                                fetch_product_by_id_ResponseCall();
                            }
                        }
                     /*   Intent intent = new Intent(getApplicationContext(),DoctorCartActivity.class);
                        intent.putExtra("productid",productid);
                        intent.putExtra("fromactivity",TAG);
                        startActivity(intent);
                        finish();*/
                    }
                }
            }


            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"Remove SuccessResponse flr"+t.getMessage());
            }
        });

    }


    @SuppressLint({"LogNotTimber", "LongLogTag"})
    public void add_product_ResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.add_product_ResponseCall(RestUtils.getContentType(),fetchByIdRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n", "LongLogTag"})
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"Add SuccessResponse" + new Gson().toJson(response.body()));
                        product_cart_counts++;
                        if(threshould != null){
                            int productqty = Integer.parseInt(threshould);
                            if(product_cart_counts > productqty){
                                Toasty.warning(getApplicationContext(), "You can buy only up to "+productqty+" quantity of this product", Toast.LENGTH_SHORT, true).show();
                            }else{
                                if(product_cart_counts != 0){
                                    txt_cart_count.setText(product_cart_counts+"");
                                }

                            }
                        }






                    }
                }
            }


            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"Add SuccessResponse flr"+t.getMessage());
            }
        });

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
                case R.id.img_cart:
                    Intent i = new Intent(getApplicationContext(), DoctorCartActivity.class);
                    i.putExtra("productid",productid);
                    i.putExtra("cat_id",cat_id);
                    i.putExtra("fromactivity",TAG);
                    startActivity(i);
                    break;
                case R.id.img_profile:
                    Intent intent = new Intent(getApplicationContext(), DoctorProfileScreenActivity.class);
                    intent.putExtra("fromactivity",TAG);
                    if(DoctorDashboardActivity.active_tag != null){
                        intent.putExtra("active_tag",DoctorDashboardActivity.active_tag);

                    }
                    startActivity(intent);
                break;

            case R.id.img_fav:
                if (new ConnectionDetector(DoctorProductDetailsActivity.this).isNetworkAvailable(DoctorProductDetailsActivity.this)) {
                    doctorProductFavListCreateResponseCall();
                }
                break;

        }

    }


    @SuppressLint("LogNotTimber")
    private void notificationandCartCountResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();

        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<NotificationCartCountResponse> call = apiInterface.notificationandCartCountResponseCall(RestUtils.getContentType(), notificationCartCountRequest());
        Log.w(TAG,"NotificationCartCountResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<NotificationCartCountResponse>() {
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

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void doctorProductFavListCreateResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = apiInterface.doctorProductFavListCreateResponseCall(RestUtils.getContentType(),doctorProductFavListCreateRequest());

        Log.w(TAG,"url  :%s"+call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<SuccessResponse> call, @NotNull Response<SuccessResponse> response) {
                Log.w(TAG,"SuccessResponse"+ "--->" + new Gson().toJson(response.body()));
                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Toasty.success(getApplicationContext(),""+response.body().getMessage(),Toasty.LENGTH_SHORT).show();

                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                            fetch_product_by_id_ResponseCall();
                        }


                    }
                }



            }

            @Override
            public void onFailure(@NotNull Call<SuccessResponse> call, @NotNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"SuccessResponse"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private DoctorProductFavListCreateRequest doctorProductFavListCreateRequest() {

        /*
         * user_id : 6087d8626163803091258a5d
         * product_id : 60731580a959860485828fc5
         */

        DoctorProductFavListCreateRequest doctorProductFavListCreateRequest = new DoctorProductFavListCreateRequest();
        doctorProductFavListCreateRequest.setUser_id(userid);
        doctorProductFavListCreateRequest.setProduct_id(productid);
        Log.w(TAG,"doctorProductFavListCreateRequest"+ "--->" + new Gson().toJson(doctorProductFavListCreateRequest));
        return doctorProductFavListCreateRequest;
    }

}