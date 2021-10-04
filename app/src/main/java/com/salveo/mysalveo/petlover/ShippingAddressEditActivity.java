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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.ShippingAddressEditRequest;
import com.salveo.mysalveo.responsepojo.CartDetailsResponse;
import com.salveo.mysalveo.responsepojo.ShippingAddressEditResponse;
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
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddressEditActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "ShippingAddressEditActivity";

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
    @BindView(R.id.txt_cancel)
    TextView txt_cancel;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_create_addreess)
    LinearLayout ll_create_addreess;

    String firstname, lastname, flat_house_build_no, street_addr, landmark, pincode, city, state, phoneno, altphoneno, addr_type = "Home";

    String userid, name, phonum, states, street, landmark_pincode, address_type, date, shipid, fromactivity;

    String first_name,last_name,flat_no,landmarks,pincodes,alt_phonum,address_status;

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

        setContentView(R.layout.activity_shipping_address_edit);

        Log.w(TAG, "onCreate ");

        ButterKnife.bind(this);

        SessionManager session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        Log.w(TAG, "User ID:  " + userid);

        img_back.setOnClickListener(this);

        ll_cancel.setOnClickListener(this);
        txt_cancel.setOnClickListener(this);

        ll_create_addreess.setOnClickListener(this);

        rg_addr_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

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

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

               fromactivity = extras.getString("fromactivity");

                shipid = extras.getString("shipid");

                first_name = extras.getString("first_name");

                last_name = extras.getString("last_name");

                name = first_name + " " + last_name;

                phonum = extras.getString("phonum");

                alt_phonum = extras.getString("alt_phonum");

                flat_no = extras.getString("flat_no");

                states = extras.getString("state");

                street = extras.getString("street");

                landmarks = extras.getString("landmark");

                pincodes  = extras.getString("pincode");

                city  = extras.getString("city");

                landmark_pincode = landmark +" , "+ pincode;

                //address_type = "Home";

                address_type = extras.getString("address_type");

                //date = "14/02/2021";

                //date = extras.getString("date");

                address_status = extras.getString("address_status");

                Data = (List<CartDetailsResponse.DataBean>) extras.getSerializable("data");

                prodouct_total = extras.getInt("product_total");

                shipping_charge = extras.getInt("shipping_charge");

                discount_price = extras.getInt("discount_price");

                grand_total = extras.getInt("grand_total");

                prodcut_count = extras.getInt("prodcut_count");

                prodcut_item_count = extras.getInt("prodcut_item_count");

                setView();

                Log.w(TAG,"fromactivity : "+fromactivity);


        }

    }

    private void setView() {

        if(first_name!=null&&!first_name.isEmpty()){

            edt_firstname.setText(first_name);
        }

        if(last_name!=null&&!last_name.isEmpty()){

            edt_lastname.setText(last_name);
        }

        if(flat_no!=null&&!flat_no.isEmpty()){

            edt_flat_house_build_no.setText(flat_no);
        }

        if(street!=null&&!street.isEmpty()){

            edt_street_addr.setText(street);
        }

        if(landmarks!=null&&!landmarks.isEmpty()){

            edt_landmark.setText(landmarks);
        }

        if(pincodes!=null&&!pincodes.isEmpty()){

            edt_pincode.setText(pincodes);
        }


        if(city!=null&&!city.isEmpty()){

            edt_city.setText(city);
        }

        if(states!=null&&!states.isEmpty()){

            edt_state.setText(states);

        }

        if(phonum!=null&&!phonum.isEmpty()){

            edt_phoneno.setText(phonum);

        }

        if(alt_phonum!=null&&!alt_phonum.isEmpty()){

            edt_altphoneno.setText(alt_phonum );

        }

        if(address_type!=null&&!address_type.isEmpty()){

            if(address_type.equals("Home")){

                rb_home.setChecked(true);
            }

            else {

                rb_office.setChecked(true);
            }

        }





    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_back:
               // onBackPressed();
                gotoPreviousActivity();
                break;

            case R.id.ll_cancel:
                gotoPreviousActivity();
                break;

                case R.id.txt_cancel:
                    gotoPreviousActivity();
                break;

            case R.id.ll_create_addreess:
                checkValidation();
                break;

        }
    }

    private void gotoPreviousActivity() {

        if(fromactivity.equals("ShippingAddressActivity")){

            Intent intent = new Intent(ShippingAddressEditActivity.this,ShippingAddressActivity.class);

            intent.putExtra("fromactivity",TAG);

            intent.putExtra("data", (Serializable) Data);

            intent.putExtra("product_total",prodouct_total);

            intent.putExtra("shipping_charge",shipping_charge);

            intent.putExtra("discount_price",discount_price);

            intent.putExtra("grand_total",grand_total);

            intent.putExtra("prodcut_count",prodcut_count);

            intent.putExtra("prodcut_item_count",prodcut_item_count);

            intent.putExtra("fromactivity", TAG);

            startActivity(intent);

        }

        else {

            Intent intent = new Intent(ShippingAddressEditActivity.this,ShippingAddressAddActivity.class);

            intent.putExtra("fromactivity",TAG);

            intent.putExtra("data", (Serializable) Data);

            intent.putExtra("product_total",prodouct_total);

            intent.putExtra("shipping_charge",shipping_charge);

            intent.putExtra("discount_price",discount_price);

            intent.putExtra("grand_total",grand_total);

            intent.putExtra("prodcut_count",prodcut_count);

            intent.putExtra("prodcut_item_count",prodcut_item_count);

            startActivity(intent);

        }



    }

    private void checkValidation() {

        firstname = Objects.requireNonNull(edt_firstname.getText()).toString().trim();

        lastname = Objects.requireNonNull(edt_lastname.getText()).toString().trim();

        flat_house_build_no = Objects.requireNonNull(edt_flat_house_build_no.getText()).toString().trim();

        street_addr = Objects.requireNonNull(edt_street_addr.getText()).toString().trim();

        landmark = Objects.requireNonNull(edt_landmark.getText()).toString().trim();

        pincode = Objects.requireNonNull(edt_pincode.getText()).toString().trim();

        city = Objects.requireNonNull(edt_city.getText()).toString().trim();

        state = Objects.requireNonNull(edt_state.getText()).toString().trim();

        phoneno = Objects.requireNonNull(edt_phoneno.getText()).toString().trim();

        altphoneno = Objects.requireNonNull(edt_altphoneno.getText()).toString().trim();

        boolean can_proceed = true;

        if(firstname.isEmpty()){

            edt_firstname.setError("Please fill the first name");

            edt_firstname.requestFocus();

            Toasty.warning(getApplicationContext(), "Please Enter the First Name", Toast.LENGTH_SHORT).show();

            can_proceed = false;

        }

        else if(lastname.isEmpty()){

            edt_lastname.setError("Please fill the last name");

            edt_lastname.requestFocus();

            Toasty.warning(getApplicationContext(), "Please Enter the Last Name", Toast.LENGTH_SHORT).show();

            can_proceed = false;

        }


        else if(flat_house_build_no.isEmpty()){

            edt_flat_house_build_no.setError("Please fill the flat No");

            edt_flat_house_build_no.requestFocus();

            Toasty.warning(getApplicationContext(), "Please Enter the flat No", Toast.LENGTH_SHORT).show();

            can_proceed = false;

        }

        else if(street_addr.isEmpty()){

            edt_street_addr.setError("Please fill the Street Address");

            edt_street_addr.requestFocus();

            Toasty.warning(getApplicationContext(), "Please Enter the Street Address", Toast.LENGTH_SHORT).show();

            can_proceed = false;

        }

        else if(pincode.isEmpty()){

            edt_pincode.setError("Please fill the Pincode");

            edt_pincode.requestFocus();

            Toasty.warning(getApplicationContext(), "Please Enter the Pincode", Toast.LENGTH_SHORT).show();

            can_proceed = false;

        }

        else if(city.isEmpty()){

            edt_city.setError("Please fill the city");

            edt_city.requestFocus();

            Toasty.warning(getApplicationContext(), "Please Enter the City", Toast.LENGTH_SHORT).show();

            can_proceed = false;

        }

        else if(state.isEmpty()){

            edt_state.setError("Please fill the state");

            edt_state.requestFocus();

            Toasty.warning(getApplicationContext(), "Please Enter the State", Toast.LENGTH_SHORT).show();

            can_proceed = false;

        }

        else if(phoneno.isEmpty()){

            edt_phoneno.setError("Please fill the Phone No");

            edt_phoneno.requestFocus();

            Toasty.warning(getApplicationContext(), "Please Enter the Phone No", Toast.LENGTH_SHORT).show();

            can_proceed = false;

        }

        if(landmark.isEmpty()){

            landmark = "";

        }

        if(altphoneno.isEmpty()){

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
        Call<ShippingAddressEditResponse> call = apiInterface.edit_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddressEditRequest());

        Log.w(TAG,"ShippingAddressEditResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ShippingAddressEditResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShippingAddressEditResponse> call, @NonNull Response<ShippingAddressEditResponse> response) {

                Log.w(TAG,"ShippingAddressEditResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        gotoPreviousActivity();

                        finish();

                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                        avi_indicator.smoothToHide();

                        Toasty.warning(ShippingAddressEditActivity.this,"Server Issue",Toasty.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<ShippingAddressEditResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddressCreateResponse flr"+"--->" + t.getMessage());
            }
        });




    }

    @SuppressLint("LogNotTimber")
    private ShippingAddressEditRequest shippingAddressEditRequest() {

        /**
         * _id : 60582973cd2b5432d800f3eb
         * user_id : 6048589d0b3a487571a1c567
         * user_first_name : Santhosh
         * user_last_name : Kuamar
         * user_flat_no : no 23
         * user_stree : 203rd Street,Muthamil nage, chennai - 600119
         * user_landmark : Near Temple
         * user_picocode : 600119
         * user_state : Tamil Nadu
         * user_mobile : +919181823011
         * user_alter_mobile : +919181823011
         * user_address_stauts : Last Used
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ShippingAddressEditRequest shippingAddressEditRequest = new ShippingAddressEditRequest();
        shippingAddressEditRequest.set_id(shipid);
        shippingAddressEditRequest.setUser_id(userid);
        shippingAddressEditRequest.setUser_first_name(firstname);
        shippingAddressEditRequest.setUser_last_name(lastname);
        shippingAddressEditRequest.setUser_flat_no(flat_house_build_no);
        shippingAddressEditRequest.setUser_stree(street_addr);
        shippingAddressEditRequest.setUser_landmark(landmark);
        shippingAddressEditRequest.setUser_picocode(pincode);
        shippingAddressEditRequest.setUser_state(state);
        shippingAddressEditRequest.setUser_mobile(phoneno);
        shippingAddressEditRequest.setUser_alter_mobile(altphoneno);
        shippingAddressEditRequest.setUser_address_stauts(address_status);
        shippingAddressEditRequest.setUser_address_type(addr_type);
        shippingAddressEditRequest.setUser_city(city);


        Log.w(TAG,"shippingAddressEditRequest"+ "--->" + new Gson().toJson(shippingAddressEditRequest));
        return shippingAddressEditRequest;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoPreviousActivity();
    }
}