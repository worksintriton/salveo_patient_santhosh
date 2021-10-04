package com.salveo.mysalveo.activity.location;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.ShippingAddressListAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.OnDeleteShipAddrListener;
import com.salveo.mysalveo.interfaces.OnEditShipAddrListener;
import com.salveo.mysalveo.interfaces.OnSelectingShipIdListener;
import com.salveo.mysalveo.petlover.ShippingAddressActivity;
import com.salveo.mysalveo.requestpojo.LocationDeleteRequest;
import com.salveo.mysalveo.requestpojo.LocationListAddressRequest;
import com.salveo.mysalveo.requestpojo.LocationStatusChangeRequest;
import com.salveo.mysalveo.requestpojo.ShippingAddrMarkAsLastUsedRequest;
import com.salveo.mysalveo.requestpojo.ShippingAddrMarkAsLastUsedResponse;
import com.salveo.mysalveo.requestpojo.ShippingAddressDeleteRequest;
import com.salveo.mysalveo.requestpojo.ShippingAddressListingByUserIDRequest;
import com.salveo.mysalveo.responsepojo.CartDetailsResponse;
import com.salveo.mysalveo.responsepojo.LocationDeleteResponse;
import com.salveo.mysalveo.responsepojo.LocationListAddressResponse;
import com.salveo.mysalveo.responsepojo.LocationStatusChangeResponse;
import com.salveo.mysalveo.responsepojo.ShippingAddressDeleteResponse;
import com.salveo.mysalveo.responsepojo.ShippingAddressListingByUserIDResponse;
import com.salveo.mysalveo.serviceprovider.ShippingAddressSPActivity;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddressAddSPActivity extends AppCompatActivity implements View.OnClickListener, OnSelectingShipIdListener, OnEditShipAddrListener, OnDeleteShipAddrListener {

    private String TAG = "ShippingAddressAddSPActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_savedaddress)
    TextView txt_savedaddress;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_shipping_address)
    RecyclerView rv_shipping_address;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_add_address)
    LinearLayout ll_add_address;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_use_this_addreess)
    Button btn_use_this_addreess;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.footerView)
    LinearLayout footerView;

    String userid,fromactivity;


    Dialog dialog;

    String shippid;

    List<CartDetailsResponse.DataBean> Data = new ArrayList<>();

    private int prodouct_total;

    private int shipping_charge;

    private int discount_price;

    private int grand_total;

    private int prodcut_count;

    private int prodcut_item_count;
    private List<LocationListAddressResponse.DataBean> addressList;
    private List<ShippingAddressListingByUserIDResponse.DataBean> dataBeanList;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address_add);

        Log.w(TAG,"onCreate ");

        ButterKnife.bind(this);

        footerView.setVisibility(View.GONE);


        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.shipping_address));
        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);


        SessionManager session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        Log.w(TAG,"User ID:  "+userid);

        ll_add_address.setOnClickListener(this);

        img_back.setOnClickListener(this);

        btn_cancel.setOnClickListener(this);

        btn_use_this_addreess.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

            Log.w(TAG,"fromactivity :  "+ fromactivity +" : true-->");

            Data = (List<CartDetailsResponse.DataBean>) extras.getSerializable("data");

            prodouct_total = extras.getInt("product_total");

            shipping_charge = extras.getInt("shipping_charge");

            discount_price = extras.getInt("discount_price");

            grand_total = extras.getInt("grand_total");

            prodcut_count = extras.getInt("prodcut_count");

            prodcut_item_count = extras.getInt("prodcut_item_count");


        }

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            locationListAddressResponseCall();
        }



       /* if (new ConnectionDetector(ShippingAddressAddActivity.this).isNetworkAvailable(ShippingAddressAddActivity.this)) {

            shippingAddressresponseCall(userid);

        }*/

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.ll_add_address:
                gotoShippingaddressCreate();
                break;

            case R.id.btn_cancel:
                onBackPressed();
                break;

            case R.id.btn_use_this_addreess:
                gotoShippingAddressActivity();
                break;
        }

    }

    @SuppressLint("LogNotTimber")
    private void shippingAddressresponseCall(String userid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShippingAddressListingByUserIDResponse> call = apiInterface.list_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddressListingByUserIDRequest(userid));

        Log.w(TAG,"ShippingAddressListingByUserIDResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ShippingAddressListingByUserIDResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShippingAddressListingByUserIDResponse> call, @NonNull Response<ShippingAddressListingByUserIDResponse> response) {

                Log.w(TAG,"ShippingAddressListingByUserIDResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {

                    if(response.body().getCode() == 200){

                        if(response.body().getData()!=null&&!(response.body().getData().isEmpty()))
                        {
                            dataBeanList = response.body().getData();

                            if(dataBeanList.size()>0)
                            {

                                setView();

                            }

                        }

                            else {

                                showNoAddressAlert();

                                rv_shipping_address.setVisibility(View.GONE);

                                txt_no_records.setVisibility(View.VISIBLE);

                                avi_indicator.smoothToHide();

                            }

                        }

                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                        avi_indicator.smoothToHide();

                    }
                }

            @Override
            public void onFailure(@NonNull Call<ShippingAddressListingByUserIDResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddressFetchByUserIDResponse flr"+"--->" + t.getMessage());
            }
        });


    }

    @SuppressLint("LogNotTimber")
    private ShippingAddressListingByUserIDRequest shippingAddressListingByUserIDRequest(String userid) {


        /**
         * user_id : 6048589d0b3a487571a1c567
         */


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ShippingAddressListingByUserIDRequest shippingAddressListingByUserIDRequest = new ShippingAddressListingByUserIDRequest();
        shippingAddressListingByUserIDRequest.setUser_id(userid);


        Log.w(TAG,"shippingAddressFetchByUserIDRequest"+ "--->" + new Gson().toJson(shippingAddressListingByUserIDRequest));
        return shippingAddressListingByUserIDRequest;
    }

    private void markAsLastUsedResponseCall(String shipsid){

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShippingAddrMarkAsLastUsedResponse> call = apiInterface.mark_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddrMarkAsLastUsedRequest(shipsid));

        Log.w(TAG,"ShippingAddrMarkAsLastUsedResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ShippingAddrMarkAsLastUsedResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShippingAddrMarkAsLastUsedResponse> call, @NonNull Response<ShippingAddrMarkAsLastUsedResponse> response) {

                Log.w(TAG,"ShippingAddrMarkAsLastUsedResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        Intent intent = new Intent(ShippingAddressAddSPActivity.this, ShippingAddressActivity.class);

                        intent.putExtra("fromactivity",TAG);

                        intent.putExtra("data", (Serializable) Data);

                        intent.putExtra("product_total",prodouct_total);

                        intent.putExtra("shipping_charge",shipping_charge);

                        intent.putExtra("discount_price",discount_price);

                        intent.putExtra("grand_total",grand_total);

                        intent.putExtra("prodcut_count",prodcut_count);

                        intent.putExtra("prodcut_item_count",prodcut_item_count);

                        startActivity(intent);

                        finish();

                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                        avi_indicator.smoothToHide();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<ShippingAddrMarkAsLastUsedResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddrMarkAsLastUsedResponse flr"+"--->" + t.getMessage());
            }
        });


    }

    @SuppressLint("LogNotTimber")
    private ShippingAddrMarkAsLastUsedRequest shippingAddrMarkAsLastUsedRequest(String shipid) {

        /**
         * _id : 6058f4ebe748565ddb1fc515
         * user_id : 604081d12c2b43125f8cb840
         * user_address_stauts : Last Used
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ShippingAddrMarkAsLastUsedRequest shippingAddrMarkAsLastUsedRequest = new ShippingAddrMarkAsLastUsedRequest();
        shippingAddrMarkAsLastUsedRequest.set_id(shipid);
        shippingAddrMarkAsLastUsedRequest.setUser_id(userid);
        shippingAddrMarkAsLastUsedRequest.setUser_address_stauts("Last Used");


        Log.w(TAG,"shippingAddrMarkAsLastUsedRequest"+ "--->" + new Gson().toJson(shippingAddrMarkAsLastUsedRequest));
        return shippingAddrMarkAsLastUsedRequest;
    }




    @SuppressLint("LogNotTimber")
    private void locationStatusChangeResponseCall(String locationid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LocationStatusChangeResponse> call = apiInterface.locationStatusChangeResponseCall(RestUtils.getContentType(),locationStatusChangeRequest(locationid));

        Log.w(TAG,"url  :%s"+call.request().url().toString());

        call.enqueue(new Callback<LocationStatusChangeResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<LocationStatusChangeResponse> call, @NotNull Response<LocationStatusChangeResponse> response) {
                Log.w(TAG,"LocationStatusChangeResponse"+ "--->" + new Gson().toJson(response.body()));
                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(fromactivity != null && fromactivity.equalsIgnoreCase("AddMyAddressShippingSPActivity")){
                            Toasty.success(getApplicationContext(), "Default Location Changed Successfully", Toast.LENGTH_SHORT, true).show();
                            Intent intent = new Intent(ShippingAddressAddSPActivity.this, ShippingAddressSPActivity.class);
                            intent.putExtra("fromactivity",TAG);
                            intent.putExtra("data", (Serializable) Data);
                            intent.putExtra("product_total",prodouct_total);
                            intent.putExtra("shipping_charge",shipping_charge);
                            intent.putExtra("discount_price",discount_price);
                            intent.putExtra("grand_total",grand_total);
                            intent.putExtra("prodcut_count",prodcut_count);
                            intent.putExtra("prodcut_item_count",prodcut_item_count);
                            startActivity(intent);
                            finish();

                        }else{
                            Toasty.success(getApplicationContext(), "Default Location Changed Successfully", Toast.LENGTH_SHORT, true).show();
                            Intent intent = new Intent(ShippingAddressAddSPActivity.this, ShippingAddressActivity.class);
                            intent.putExtra("fromactivity",TAG);
                            intent.putExtra("data", (Serializable) Data);
                            intent.putExtra("product_total",prodouct_total);
                            intent.putExtra("shipping_charge",shipping_charge);
                            intent.putExtra("discount_price",discount_price);
                            intent.putExtra("grand_total",grand_total);
                            intent.putExtra("prodcut_count",prodcut_count);
                            intent.putExtra("prodcut_item_count",prodcut_item_count);
                            startActivity(intent);
                            finish();

                        }


                    }
                }



            }

            @Override
            public void onFailure(@NotNull Call<LocationStatusChangeResponse> call, @NotNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"LocationStatusChangeResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private LocationStatusChangeRequest locationStatusChangeRequest(String locationid) {

        /*
         * _id : 5fcf2d2a57c8326d894e4d7e
         * user_id : 5fc61b82b750da703e48da78
         * default_status : true
         */

        LocationStatusChangeRequest locationStatusChangeRequest = new LocationStatusChangeRequest();
        locationStatusChangeRequest.set_id(locationid);
        locationStatusChangeRequest.setUser_id(userid);
        locationStatusChangeRequest.setDefault_status(true);
        Log.w(TAG,"locationStatusChangeRequest"+ "--->" + new Gson().toJson(locationStatusChangeRequest));
        return locationStatusChangeRequest;
    }


    private void deleteshipAddrresponseCall(String shippingid) {

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShippingAddressDeleteResponse> call = apiInterface.delete_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddressDeleteRequest(shippingid));

        Log.w(TAG,"ShippingAddressDeleteResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ShippingAddressDeleteResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShippingAddressDeleteResponse> call, @NonNull Response<ShippingAddressDeleteResponse> response) {

                Log.w(TAG,"ShippingAddressDeleteResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        startActivity(getIntent());

                        finish();

                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                        avi_indicator.smoothToHide();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<ShippingAddressDeleteResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddressDeleteResponse flr"+"--->" + t.getMessage());
            }
        });


    }

    @SuppressLint("LogNotTimber")
    private ShippingAddressDeleteRequest shippingAddressDeleteRequest(String shipid) {


        /**
         * _id : 6057129e2e3b894a69767d40
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ShippingAddressDeleteRequest shippingAddressDeleteRequest = new ShippingAddressDeleteRequest();
        shippingAddressDeleteRequest.set_id(shipid);


        Log.w(TAG,"shippingAddressDeleteRequest"+ "--->" + new Gson().toJson(shippingAddressDeleteRequest));
        return shippingAddressDeleteRequest;
    }




    @SuppressLint("LogNotTimber")
    private void locationDeleteResponseCall(String locationid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();

        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LocationDeleteResponse> call = apiInterface.locationDeleteResponseCall(RestUtils.getContentType(),locationDeleteRequest(locationid));

        Log.w(TAG,"url  :%s"+call.request().url().toString());

        call.enqueue(new Callback<LocationDeleteResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<LocationDeleteResponse> call, @NotNull Response<LocationDeleteResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"LocationDeleteResponse"+ "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Toasty.success(getApplicationContext(), "Address Removed Successfully", Toast.LENGTH_SHORT, true).show();
                        finish();
                        overridePendingTransition( 0, 0);
                        startActivity(getIntent());
                        overridePendingTransition( 0, 0);

                    }
                }



            }

            @Override
            public void onFailure(@NotNull Call<LocationDeleteResponse> call, @NotNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"LocationDeleteResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private LocationDeleteRequest locationDeleteRequest(String locationid) {

        /*
          _id : 5f05d911f3090625a91f40c7
         */
        LocationDeleteRequest locationDeleteRequest = new LocationDeleteRequest();
        locationDeleteRequest.set_id(locationid);
        Log.w(TAG,"locationDeleteRequest"+ "--->" + new Gson().toJson(locationDeleteRequest));
        return locationDeleteRequest;
    }


    @SuppressLint("SetTextI18n")
    private void showNoAddressAlert() {

        try{

            dialog = new Dialog(ShippingAddressAddSPActivity.this);
            dialog.setContentView(R.layout.alert_cancel_layout);
            dialog.setCanceledOnTouchOutside(false);
            Button btn_ok = dialog.findViewById(R.id.btn_ok);
            Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
            TextView txt_msg = dialog.findViewById(R.id.txt_text_message);
            txt_msg.setText("No Address Found ! Please Add Some Address");
            txt_msg.setTextSize(12);
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    gotoShippingaddressCreate();

                    dialog.dismiss();

                }
            });
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();


        }
        catch(
                WindowManager.BadTokenException e){
            e.printStackTrace();
        }

    }

    @SuppressLint("SetTextI18n")
    private void showWaring(String shippingid) {

        try{

            dialog = new Dialog(ShippingAddressAddSPActivity.this);
            dialog.setContentView(R.layout.alert_cancel_layout);
            dialog.setCanceledOnTouchOutside(false);
            Button btn_ok = dialog.findViewById(R.id.btn_ok);
            Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
            TextView txt_msg = dialog.findViewById(R.id.txt_text_message);
            txt_msg.setText(R.string.deletemsg);
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (new ConnectionDetector(ShippingAddressAddSPActivity.this).isNetworkAvailable(ShippingAddressAddSPActivity.this)) {

                        deleteshipAddrresponseCall(shippingid);
                        locationDeleteResponseCall(shippingid);

                    }

                    dialog.dismiss();

                }
            });
            btn_cancel.setOnClickListener(view -> dialog.dismiss());

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();


        }
        catch(
                WindowManager.BadTokenException e){
            e.printStackTrace();
        }
    }


    private void setView() {
        rv_shipping_address.setLayoutManager(new LinearLayoutManager(ShippingAddressAddSPActivity.this));
        rv_shipping_address.setItemAnimator(new DefaultItemAnimator());
        ShippingAddressListAdapter shippingAddressListAdapter = new ShippingAddressListAdapter(ShippingAddressAddSPActivity.this,addressList,this,this,this);
        rv_shipping_address.setAdapter(shippingAddressListAdapter);


    }

    private void gotoShippingaddressCreate() {
        Intent intent = new Intent(ShippingAddressAddSPActivity.this, PickUpLocationAddNewAddressSPActivity.class);
        intent.putExtra("data", (Serializable) Data);
        intent.putExtra("product_total",prodouct_total);
        intent.putExtra("shipping_charge",shipping_charge);
        intent.putExtra("discount_price",discount_price);
        intent.putExtra("grand_total",grand_total);
        intent.putExtra("prodcut_count",prodcut_count);
        intent.putExtra("prodcut_item_count",prodcut_item_count);
        intent.putExtra("fromactivity",fromactivity);
        startActivity(intent);

    }

    private void gotoShippingAddressActivity() {

        if(shippid!=null&&!shippid.isEmpty()){

            //markAsLastUsedResponseCall(shippid);
            locationStatusChangeResponseCall(shippid);

        } else {
            if(fromactivity != null && fromactivity.equalsIgnoreCase("AddMyAddressShippingSPActivity")) {
                Intent intent = new Intent(ShippingAddressAddSPActivity.this, ShippingAddressSPActivity.class);
                intent.putExtra("fromactivity", TAG);
                intent.putExtra("data", (Serializable) Data);
                intent.putExtra("product_total", prodouct_total);
                intent.putExtra("shipping_charge", shipping_charge);
                intent.putExtra("discount_price", discount_price);
                intent.putExtra("grand_total", grand_total);
                intent.putExtra("prodcut_count", prodcut_count);
                intent.putExtra("prodcut_item_count", prodcut_item_count);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(ShippingAddressAddSPActivity.this, ShippingAddressActivity.class);
                intent.putExtra("fromactivity", TAG);
                intent.putExtra("data", (Serializable) Data);
                intent.putExtra("product_total", prodouct_total);
                intent.putExtra("shipping_charge", shipping_charge);
                intent.putExtra("discount_price", discount_price);
                intent.putExtra("grand_total", grand_total);
                intent.putExtra("prodcut_count", prodcut_count);
                intent.putExtra("prodcut_item_count", prodcut_item_count);
                startActivity(intent);
                finish();
            }

            //Toasty.warning(ShippingAddressAddActivity.this,"Plz choose shipping address ", Toasty.LENGTH_LONG).show();
        }


    }

    @Override
    public void OnEditShipAddr(int position) {

        Intent i = new Intent(getApplicationContext(), EditShippingAddresssActivity.class);
        i.putExtra("id",addressList.get(position).get_id());
        i.putExtra("userid",addressList.get(position).getUser_id());
        i.putExtra("cityname",addressList.get(position).getLocation_city());
        i.putExtra("state",addressList.get(position).getLocation_state());
        i.putExtra("country",addressList.get(position).getLocation_country());
        i.putExtra("address",addressList.get(position).getLocation_address());
        i.putExtra("pincode",addressList.get(position).getLocation_pin());
        i.putExtra("nickname",addressList.get(position).getLocation_nickname());
        i.putExtra("locationtype",addressList.get(position).getLocation_title());
        i.putExtra("defaultstatus",addressList.get(position).isDefault_status());
        Bundle b = new Bundle();
        b.putDouble("lat", addressList.get(position).getLocation_lat());
        b.putDouble("lon", addressList.get(position).getLocation_long());
        i.putExtras(b);
        i.putExtra("data", (Serializable) Data);
        i.putExtra("product_total",prodouct_total);
        i.putExtra("shipping_charge",shipping_charge);
        i.putExtra("discount_price",discount_price);
        i.putExtra("grand_total",grand_total);
        i.putExtra("prodcut_count",prodcut_count);
        i.putExtra("prodcut_item_count",prodcut_item_count);
        Log.w(TAG,"cityname-->"+addressList.get(position).getLocation_city());
        startActivity(i);






    }

    @Override
    public void onSelectShipID(String shipid) {

        shippid = shipid;
    }

    @Override
    public void OnDeleteShipAddr(String shipid) {
        showWaring(shipid);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("ShippingAddressSPActivity")){
            Intent i = new Intent(getApplicationContext(),ShippingAddressSPActivity.class);
            i.putExtra("data", (Serializable) Data);
            i.putExtra("product_total",prodouct_total);
            i.putExtra("shipping_charge",shipping_charge);
            i.putExtra("discount_price",discount_price);
            i.putExtra("grand_total",grand_total);
            i.putExtra("prodcut_count",prodcut_count);
            i.putExtra("prodcut_item_count",prodcut_item_count);
            i.putExtra("fromactivity",fromactivity);
            startActivity(i);
            finish();
        } else if(fromactivity != null && fromactivity.equalsIgnoreCase("AddMyAddressShippingSPActivity")){
            Intent i = new Intent(getApplicationContext(),AddMyAddressShippingSPActivity.class);
            i.putExtra("data", (Serializable) Data);
            i.putExtra("product_total",prodouct_total);
            i.putExtra("shipping_charge",shipping_charge);
            i.putExtra("discount_price",discount_price);
            i.putExtra("grand_total",grand_total);
            i.putExtra("prodcut_count",prodcut_count);
            i.putExtra("prodcut_item_count",prodcut_item_count);
            i.putExtra("fromactivity",fromactivity);
            startActivity(i);
            finish();
        }else{
            Intent i = new Intent(getApplicationContext(),ShippingAddressActivity.class);
            i.putExtra("data", (Serializable) Data);
            i.putExtra("product_total",prodouct_total);
            i.putExtra("shipping_charge",shipping_charge);
            i.putExtra("discount_price",discount_price);
            i.putExtra("grand_total",grand_total);
            i.putExtra("prodcut_count",prodcut_count);
            i.putExtra("prodcut_item_count",prodcut_item_count);
            i.putExtra("fromactivity",fromactivity);
            startActivity(i);
            finish();
        }

    }

    private void locationListAddressResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LocationListAddressResponse> call = apiInterface.locationListAddressResponseCall(RestUtils.getContentType(), locationListAddressRequest());
        Log.w(TAG,"locationListAddressResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<LocationListAddressResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<LocationListAddressResponse> call, @NonNull Response<LocationListAddressResponse> response) {

                Log.w(TAG,"locationListAddressResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getData() != null && response.body().getData().isEmpty()){
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No new address");
                            rv_shipping_address.setVisibility(View.GONE);
                            txt_savedaddress.setVisibility(View.GONE);
                            footerView.setVisibility(View.GONE);
                        }
                        else{
                            footerView.setVisibility(View.VISIBLE);
                            txt_no_records.setVisibility(View.GONE);
                            rv_shipping_address.setVisibility(View.VISIBLE);
                            txt_savedaddress.setVisibility(View.VISIBLE);
                            if(response.body().getData() != null) {
                                addressList = response.body().getData();
                            }
                            txt_savedaddress.setText(addressList.size()+" Saved Address");
                            setView();
                        }



                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<LocationListAddressResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"locationListAddressResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    private LocationListAddressRequest locationListAddressRequest() {
        LocationListAddressRequest locationListAddressRequest = new LocationListAddressRequest();
        locationListAddressRequest.setUser_id(userid);
        Log.w(TAG,"locationListAddressRequest"+ "--->" + new Gson().toJson(locationListAddressRequest));
        return locationListAddressRequest;
    }
}