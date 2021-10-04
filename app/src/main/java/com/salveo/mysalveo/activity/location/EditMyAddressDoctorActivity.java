package com.salveo.mysalveo.activity.location;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.doctor.DoctorProfileScreenActivity;
import com.salveo.mysalveo.doctor.ManageAddressDoctorActivity;
import com.salveo.mysalveo.requestpojo.LocationUpdateRequest;
import com.salveo.mysalveo.responsepojo.LocationUpdateResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditMyAddressDoctorActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, View.OnClickListener {

    String TAG = "EditMyAddressDoctorActivity";



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cityname_title)
    TextView txt_cityname_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_address)
    TextView txt_address;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_change)
    Button btn_change;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_savethislocation)
    Button btn_savethislocation;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_pickname)
    EditText edt_pickname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cityname)
    TextView txt_cityname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pincode)
    TextView txt_pincode;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_location)
    TextView txt_location;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rglocationtype)
    RadioGroup rglocationtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.radioButton_Home)
    RadioButton radioButton_Home;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.radioButton_Work)
    RadioButton radioButton_Work;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.radioButton_Others)
    RadioButton radioButton_Others;



    String latlng = "";

    double latitude = 0, longtitude =0;

    Marker mCurrLocationMarker;

    String cityname = "", address = "";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.switchButton_default)
    SwitchCompat switchButton_default;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;


    String userid = "",state = "",country = "";



    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;


    String locationtype = "Home";
    private String pincode;
    private boolean defaultstatus = true;
    private String nickname;
    private String id;
    private String fromactivity;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_address);

        Log.w(TAG,"onCreate-->");

        ButterKnife.bind(this);



        SessionManager sessionManager = new SessionManager(EditMyAddressDoctorActivity.this);
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG,"userid--->"+userid);
        avi_indicator.setVisibility(View.GONE);
      
        btn_change.setOnClickListener(this);
        btn_savethislocation.setOnClickListener(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            latlng = String.valueOf(getIntent().getSerializableExtra("latlng"));
            Log.w(TAG,"latlng-->"+getIntent().getSerializableExtra("latlng"));
            if(latlng != null && !latlng.equalsIgnoreCase("null")) {

                String newString = latlng.replace("lat/lng:", "");
                Log.w(TAG, "latlng==" + newString);

                String latlngs = newString.trim().replaceAll("\\(", "").replaceAll("\\)", "").trim();
                Log.w(TAG, "latlngs==" + latlngs);

                String[] separated = latlngs.split(",");
                String lat = separated[0];
                String lon = separated[1];

                latitude = Double.parseDouble(lat);
                longtitude = Double.parseDouble(lon);


            }
            fromactivity = extras.getString("fromactivity");
            Log.w(TAG," fromactivity : "+fromactivity);
            id = extras.getString("id");
            userid = extras.getString("userid");
            cityname = extras.getString("cityname");
            state = extras.getString("state");
            country = extras.getString("country");
            address = extras.getString("address");
            pincode = extras.getString("pincode");
            nickname = extras.getString("nickname");
            locationtype = extras.getString("locationtype");
            defaultstatus = extras.getBoolean("defaultstatus");
            latitude = extras.getDouble("lat");
            longtitude = extras.getDouble("lon");
            Log.w(TAG," latitude : "+latitude+" longtitude : "+longtitude+" defaultstatus : "+defaultstatus);

            if(defaultstatus){
                switchButton_default.setChecked(true);
                switchButton_default.setClickable(false);
            }

            if(nickname != null){
                edt_pickname.setText(nickname);

            }if(pincode != null){
                txt_pincode.setText(pincode);

            }


            if(nickname != null && !nickname.isEmpty()){
                edt_pickname.setText(nickname);
            }



            txt_cityname.setText(cityname);
            txt_cityname_title.setText(cityname);
            if(address != null && !address.isEmpty()){
                txt_address.setText(address);
                txt_location.setText(address);

            }
            if(locationtype != null){
                if(locationtype.equalsIgnoreCase("Home")){
                    locationtype = "Home";
                    radioButton_Home.setChecked(true);

                }else if(locationtype.equalsIgnoreCase("Work")){
                    locationtype = "Work";
                    radioButton_Work.setChecked(true);
                }else if(locationtype.equalsIgnoreCase("Others")){
                    locationtype = "Others";
                    radioButton_Others.setChecked(true);

                }
            }



        }


        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.edit_address));

        img_back.setOnClickListener(this);
        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), DoctorProfileScreenActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("userid",userid);
                    intent.putExtra("nickname",nickname);
                    intent.putExtra("locationtype",locationtype);
                    intent.putExtra("defaultstatus",defaultstatus);
                    intent.putExtra("lat",latitude);
                    intent.putExtra("lon",longtitude);
                    intent.putExtra("pincode",pincode);
                    intent.putExtra("cityname",cityname);
                    intent.putExtra("address",address);
                    intent.putExtra("fromactivity",TAG);
                    startActivity(intent);
                    finish();

            }
        });



        rglocationtype.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = rglocationtype.getCheckedRadioButtonId();
            RadioButton radioButton = rglocationtype.findViewById(radioButtonID);
            locationtype = (String) radioButton.getText();
            Log.w(TAG,"selectedRadioButton" + locationtype);


        });

        switchButton_default.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                defaultstatus = isChecked;
                Log.w(TAG," defaultstatus : "+defaultstatus);
            }
        });





        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.clear();
        LatLng latLng = new LatLng(latitude,longtitude);

        //  mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
        MarkerOptions markerOptions = new MarkerOptions().position(Objects.requireNonNull(latLng)).title("");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));
        googleMap.addMarker(markerOptions);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_change:
               Intent intent = new Intent(getApplicationContext(),PickUpLocationEditDoctorActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("userid",userid);
                intent.putExtra("nickname",nickname);
                intent.putExtra("locationtype",locationtype);
                intent.putExtra("defaultstatus",defaultstatus);
                intent.putExtra("lat",latitude);
                intent.putExtra("lon",longtitude);
                intent.putExtra("fromactivity",TAG);
               startActivity(intent);
                break;
            case  R.id.btn_savethislocation:
                saveLocationValidator();
                break;



        }

    }



    public void saveLocationValidator() {
        boolean can_proceed = true;
        if (edt_pickname.getText().toString().trim().equals("")) {
             edt_pickname.setError("Please enter pick a nick Name for this location");
             edt_pickname.requestFocus();
            can_proceed = false;
        }

        if (can_proceed) {
            if (new ConnectionDetector(EditMyAddressDoctorActivity.this).isNetworkAvailable(EditMyAddressDoctorActivity.this)) {
                locationUpdateResponseCall();
                }


            }

        }

    public void locationUpdateResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LocationUpdateResponse> call = apiInterface.locationUpdateResponseCall(RestUtils.getContentType(),locationUpdateRequest());
        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<LocationUpdateResponse>() {
            @Override
            public void onResponse(@NotNull Call<LocationUpdateResponse> call, @NotNull Response<LocationUpdateResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG, "LocationUpdateResponse" + new Gson().toJson(response.body()));


                if (response.body() != null) {

                    if(response.body().getCode() == 200){
                        Intent i = new Intent(EditMyAddressDoctorActivity.this, ManageAddressDoctorActivity.class);
                        startActivity(i);

                    }
                    else{
                        if(response.body().getMessage() != null){
                            showErrorLoading(response.body().getMessage());

                        }
                }
                }

            }






            @Override
            public void onFailure(@NotNull Call<LocationUpdateResponse> call, @NotNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"LocationUpdateResponse flr"+t.getMessage());
            }
        });

    }
    private LocationUpdateRequest locationUpdateRequest() {
        /*
         * _id : 5fcf09c3928d5f5634501b35
         * user_id : 5fc61b82b750da703e48da78
         * location_state : asdfasdfasd
         * location_country : asdfasdfasd
         * location_city : asdfasdfasd
         * location_pin : asdfasdfasd
         * location_address : asdfasdfasd
         * location_lat : 18.90123
         * location_long : 12.09123
         * location_title : 23-10-1996 12:09 AM
         * location_nickname : 123
         * default_status : false
         * date_and_time : 23-10-1996 12:09 AM
         * __v : 0
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        Log.w(TAG,"AddLocationRequest--->"+"latitude"+latitude+" "+"longtitude :"+longtitude);


        LocationUpdateRequest locationUpdateRequest = new LocationUpdateRequest();
        locationUpdateRequest.set_id(id);
        locationUpdateRequest.setUser_id(userid);
        locationUpdateRequest.setLocation_state(state);
        locationUpdateRequest.setLocation_country(country);
        locationUpdateRequest.setLocation_city(cityname);
        locationUpdateRequest.setLocation_pin(pincode);
        locationUpdateRequest.setLocation_address(address);
        locationUpdateRequest.setLocation_lat(latitude);
        locationUpdateRequest.setLocation_long(longtitude);
        locationUpdateRequest.setLocation_title(locationtype);
        locationUpdateRequest.setLocation_nickname(edt_pickname.getText().toString());
        locationUpdateRequest.setDefault_status(defaultstatus);
        locationUpdateRequest.setDate_and_time(currentDateandTime);

        Log.w(TAG," locationUpdateRequest"+ new Gson().toJson(locationUpdateRequest));
        return locationUpdateRequest;
    }



    public void showErrorLoading(String errormesage){
        alertDialogBuilder = new AlertDialog.Builder(this);
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


}

