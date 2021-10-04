package com.salveo.mysalveo.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.appUtils.NumericKeyBoardTransformationMethod;
import com.salveo.mysalveo.requestpojo.ShippingAddressCreateRequest;
import com.salveo.mysalveo.responsepojo.CartDetailsResponse;
import com.salveo.mysalveo.responsepojo.ShippingAddressCreateResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

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

public class ShippingAddressCreateActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "ShippingAddressCreateActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_firstname)
    TextInputEditText edt_firstname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_lastname)
    TextInputEditText edt_lastname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_flat_house_build_no)
    EditText edt_flat_house_build_no;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_street_addr)
    TextInputEditText edt_street_addr;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_landmark)
    TextInputEditText edt_landmark;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_pincode)
    TextInputEditText edt_pincode;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_city)
    TextInputEditText edt_city;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_state)
    TextInputEditText edt_state;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_phoneno)
    TextInputEditText edt_phoneno;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_altphoneno)
    TextInputEditText edt_altphoneno;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_addr_type)
    RadioGroup rg_addr_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_home)
    RadioButton rb_home;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_office)
    RadioButton rb_office;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_clearfields)
    TextView txt_clearfields;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_cancel)
    LinearLayout ll_cancel;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_create_addreess)
    LinearLayout ll_create_addreess;

    String firstname , lastname, flat_house_build_no, street_addr, landmark, pincode, city, state,phoneno,altphoneno, addr_type = "Home",userid,fromactivity;

    List<CartDetailsResponse.DataBean> Data = new ArrayList<>();

    private int prodouct_total;

    private int shipping_charge;

    private int discount_price;

    private int grand_total;

    private int prodcut_count;

    private int prodcut_item_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shipping_address_create);

        Log.w(TAG,"onCreate ");

        ButterKnife.bind(this);

        edt_phoneno.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        edt_altphoneno.setTransformationMethod(new NumericKeyBoardTransformationMethod());


        SessionManager session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        Log.w(TAG,"User ID:  "+userid);

        txt_clearfields.setOnClickListener(this);

        img_back.setOnClickListener(this);

        ll_cancel.setOnClickListener(this);

        ll_create_addreess.setOnClickListener(this);

        //btn_create_addreess.setOnClickListener(this);

        //rb_home.setChecked(true);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

            Data = (List<CartDetailsResponse.DataBean>) extras.getSerializable("data");

            prodouct_total = extras.getInt("product_total");

            shipping_charge = extras.getInt("shipping_charge");

            discount_price = extras.getInt("discount_price");

            grand_total = extras.getInt("grand_total");

            prodcut_count = extras.getInt("prodcut_count");

            prodcut_item_count = extras.getInt("prodcut_item_count");


        }


        rg_addr_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

                Log.w(TAG, "Checked_id" + checkedId);

                if (checkedId == R.id.rb_home) {
                    //some code

                    addr_type = "Home";

                } else if (checkedId == R.id.rb_office) {
                    //some code

                    addr_type = "Office";
                }
            }
        });


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.txt_clearfields:
                gotoClearFields();
                break;

            case R.id.ll_cancel:
                gotoPreviousActivity();
                break;

            case R.id.ll_create_addreess:
                checkValidation();
                break;

        }

    }

    private void gotoPreviousActivity() {

        Intent intent = new Intent(ShippingAddressCreateActivity.this,ShippingAddressAddActivity.class);

        intent.putExtra("data", (Serializable) Data);

        intent.putExtra("product_total",prodouct_total);

        intent.putExtra("shipping_charge",shipping_charge);

        intent.putExtra("discount_price",discount_price);

        intent.putExtra("grand_total",grand_total);

        intent.putExtra("prodcut_count",prodcut_count);

        intent.putExtra("prodcut_item_count",prodcut_item_count);

        startActivity(intent);


    }

    private void gotoClearFields() {

        edt_firstname.setText("");

        edt_lastname.setText("");

        edt_flat_house_build_no.setText("");

        edt_street_addr.setText("");

        edt_state.setText("");

        edt_street_addr.setText("");

        edt_city.setText("");

        edt_pincode.setText("");

        edt_landmark.setText("");

        edt_altphoneno.setText("");

        rb_home.setChecked(true);
    }

    private void checkValidation() {

            firstname = edt_firstname.getText().toString().trim();
            lastname = edt_lastname.getText().toString().trim();
            flat_house_build_no = edt_flat_house_build_no.getText().toString().trim();
            street_addr = edt_street_addr.getText().toString().trim();
            landmark = edt_landmark.getText().toString().trim();
            pincode = edt_pincode.getText().toString().trim();
            city = edt_city.getText().toString().trim();
            state = edt_state.getText().toString().trim();
            phoneno = edt_phoneno.getText().toString().trim();
            altphoneno = edt_altphoneno.getText().toString().trim();

        int moblength = edt_phoneno.getText().toString().trim().length();
        int mobaltlength = edt_altphoneno.getText().toString().trim().length();
        int pincodelength = edt_pincode.getText().toString().trim().length();
            boolean can_proceed = true;
            if(firstname != null && firstname.isEmpty()){
                edt_firstname.setError("Please fill the first name");
                edt_firstname.requestFocus();
                //Toasty.warning(getApplicationContext(), "Please Enter the First Name", Toast.LENGTH_SHORT).show();
                can_proceed = false;

            }

          /*  else if(lastname != null && lastname.isEmpty()){
                edt_lastname.setError("Please fill the last name");
                edt_lastname.requestFocus();
                //Toasty.warning(getApplicationContext(), "Please Enter the Last Name", Toast.LENGTH_SHORT).show();
                can_proceed = false;

            }*/


            else if(flat_house_build_no != null && flat_house_build_no.isEmpty()){
                edt_flat_house_build_no.setError("Please fill the flat No");
                edt_flat_house_build_no.requestFocus();
                // Toasty.warning(getApplicationContext(), "Please Enter the flat No", Toast.LENGTH_SHORT).show();
                can_proceed = false;

            }

            else if(street_addr != null && street_addr.isEmpty()){
                edt_street_addr.setError("Please fill the Street Address");
                edt_street_addr.requestFocus();
                //Toasty.warning(getApplicationContext(), "Please Enter the Street Address", Toast.LENGTH_SHORT).show();
                can_proceed = false;

            }

            else if(pincode !=null && pincode.isEmpty()){
                edt_pincode.setError("Please fill the Pincode");
                edt_pincode.requestFocus();
                //Toasty.warning(getApplicationContext(), "Please Enter the Pincode", Toast.LENGTH_SHORT).show();
                can_proceed = false;
            }else if (pincodelength <= 5) {
                edt_pincode.setError("Please enter valid pin code");
                edt_pincode.requestFocus();
                can_proceed = false;
            }

            else if(city != null &&city.isEmpty()){
                edt_city.setError("Please fill the city");
                edt_city.requestFocus();
                //Toasty.warning(getApplicationContext(), "Please Enter the City", Toast.LENGTH_SHORT).show();
                can_proceed = false;

            }

            else if(state != null && state.isEmpty()){
                edt_state.setError("Please fill the state");
                edt_state.requestFocus();
                //Toasty.warning(getApplicationContext(), "Please Enter the State", Toast.LENGTH_SHORT).show();
                can_proceed = false;

            }

            else if(phoneno != null && phoneno.isEmpty()){
                edt_phoneno.setError("Please fill the Phone No");
                edt_phoneno.requestFocus();
                //Toasty.warning(getApplicationContext(), "Please Enter the Phone No", Toast.LENGTH_SHORT).show();
                can_proceed = false;

            }else if (moblength <= 9) {
                edt_phoneno.setError("Please enter valid mobile number");
                edt_phoneno.requestFocus();
                can_proceed = false;
            }else if (altphoneno != null && !altphoneno.isEmpty() && mobaltlength <= 9) {
                edt_altphoneno.setError("Please enter valid mobile number");
                edt_altphoneno.requestFocus();
                can_proceed = false;
            }


        if(landmark !=  null && landmark.isEmpty()){
            landmark = "";

        }

        if(altphoneno != null && altphoneno.isEmpty()){
            altphoneno = "";

        }

        if(can_proceed){
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                createAddressResponseCall();

            }

            }


        }

    private void createAddressResponseCall() {

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShippingAddressCreateResponse> call = apiInterface.creates_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddressCreateRequest());

        Log.w(TAG,"ShippingAddressCreateResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ShippingAddressCreateResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShippingAddressCreateResponse> call, @NonNull Response<ShippingAddressCreateResponse> response) {

                Log.w(TAG,"ShippingAddressCreateResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        Intent intent = new Intent(ShippingAddressCreateActivity.this,ShippingAddressAddActivity.class);

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

                        Toasty.warning(ShippingAddressCreateActivity.this,"Server Issue",Toasty.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<ShippingAddressCreateResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddressCreateResponse flr"+"--->" + t.getMessage());
            }
        });




    }

    @SuppressLint("LogNotTimber")
    private ShippingAddressCreateRequest shippingAddressCreateRequest() {

        /**
         * user_id : 6048589d0b3a487571a1c567
         * user_first_name : Mohammed
         * user_last_name : imthiyas
         * user_flat_no : no 23
         * user_stree : 203rd Street,Muthamil nage, chennai - 600119
         * user_landmark : Near Temple
         * user_picocode : 600119
         * user_state : Tamil Nadu
         * user_mobile : +919181823011
         * user_alter_mobile : +919181823011
         * user_address_stauts :
         * user_address_type : Home
         * user_display_date : 22-03-2021
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ShippingAddressCreateRequest shippingAddressCreateRequest = new ShippingAddressCreateRequest();
        shippingAddressCreateRequest.setUser_id(userid);
        shippingAddressCreateRequest.setUser_first_name(firstname);
        shippingAddressCreateRequest.setUser_last_name(lastname);
        shippingAddressCreateRequest.setUser_flat_no(flat_house_build_no);
        shippingAddressCreateRequest.setUser_stree(street_addr);
        shippingAddressCreateRequest.setUser_landmark(landmark);
        shippingAddressCreateRequest.setUser_picocode(pincode);
        shippingAddressCreateRequest.setUser_state(state);
        shippingAddressCreateRequest.setUser_mobile(phoneno);
        shippingAddressCreateRequest.setUser_alter_mobile(altphoneno);
        shippingAddressCreateRequest.setUser_address_stauts("");
        shippingAddressCreateRequest.setUser_address_type(addr_type);
        shippingAddressCreateRequest.setUser_city(city);
        shippingAddressCreateRequest.setUser_display_date(currentDateandTime);


        Log.w(TAG,"shippingAddressCreateRequest"+ "--->" + new Gson().toJson(shippingAddressCreateRequest));
        return shippingAddressCreateRequest;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}