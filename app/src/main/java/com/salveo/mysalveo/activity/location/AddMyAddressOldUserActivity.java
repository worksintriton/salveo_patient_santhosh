package com.salveo.mysalveo.activity.location;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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
import com.salveo.mysalveo.petlover.PetLoverProfileScreenActivity;
import com.salveo.mysalveo.requestpojo.LocationAddRequest;
import com.salveo.mysalveo.responsepojo.LocationAddResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddMyAddressOldUserActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, View.OnClickListener {


    String TAG = "AddMyAddressOldUserActivity";



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
    @BindView(R.id.edt_cityname)
    EditText edt_cityname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_pincode)
    EditText edt_pincode;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_location)
    EditText edt_location;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_pickname)
    EditText edt_pickname;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rglocationtype)
    RadioGroup rglocationtype;



    String latlng = "";

    double latitude = 0, longtitude =0;

    Marker mCurrLocationMarker;

    String CityName = "", AddressLine = "";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.switchButton_default)
    SwitchCompat switchButton_default;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;


    String userid = "",locationnickname,state = "",country = "",postalcode = "",street;

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;


    String LocationType = "Home";
    private boolean defaultstatus = false;
    private String PostalCode;
    private String fromactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_address);

        Log.w(TAG,"onCreate-->");

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            latlng = String.valueOf(getIntent().getSerializableExtra("latlng"));
            Log.w(TAG,"latlng-->"+getIntent().getSerializableExtra("latlng"));

            String newString = latlng.replace("lat/lng:", "");
            Log.w(TAG,"latlng=="+newString);

            String latlngs = newString.trim().replaceAll("\\(", "").replaceAll("\\)","").trim();
            Log.w(TAG,"latlngs=="+latlngs);

            String[] separated = latlngs.split(",");
            String lat = separated[0];
            String lon = separated[1];

            latitude = Double.parseDouble(lat);
            longtitude = Double.parseDouble(lon);

            getAddress(latitude,longtitude);

            Log.w(TAG,"lat"+lat+" "+"lon :"+lon);
            Log.w(TAG,"latitude"+latitude+" "+"longtitude :"+longtitude);

            CityName = extras.getString("cityname");
            AddressLine = extras.getString("address");
            PostalCode = extras.getString("PostalCode");


            userid = extras.getString("userid");
            locationnickname = extras.getString("nickname");
            fromactivity = extras.getString("fromactivity");

            Log.w(TAG,"fromactivity : "+fromactivity);

            edt_cityname.setText(CityName);
            txt_cityname_title.setText(CityName);
            txt_address.setText(AddressLine);
            edt_pincode.setText(PostalCode);





        }

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.pickup_location));

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
                Intent intent = new Intent(new Intent(getApplicationContext(), PetLoverProfileScreenActivity.class));
                intent.putExtra("latlng",latlng);
                intent.putExtra("CityName",CityName);
                intent.putExtra("AddressLine",AddressLine);
                intent.putExtra("PostalCode",PostalCode);
                intent.putExtra("userid",userid);
                intent.putExtra("nickname",locationnickname);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);
            }
        });

        avi_indicator.setVisibility(View.GONE);
        img_back.setOnClickListener(this);
        btn_change.setOnClickListener(this);
        btn_savethislocation.setOnClickListener(this);

        edt_cityname.setEnabled(false);
        edt_pincode.setEnabled(false);
        edt_location.setEnabled(false);






        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG," userid : "+userid);

        rglocationtype.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = rglocationtype.getCheckedRadioButtonId();
            RadioButton radioButton = rglocationtype.findViewById(radioButtonID);
            LocationType = (String) radioButton.getText();
            Log.w(TAG,"selectedRadioButton" + LocationType);
            if (LocationType != null && LocationType.equalsIgnoreCase("new")) {

            }


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
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.btn_change:
                onBackPressed();
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
            if (new ConnectionDetector(AddMyAddressOldUserActivity.this).isNetworkAvailable(AddMyAddressOldUserActivity.this)) {
                locationAddResponseCall();
            }


            }

        }



    public void locationAddResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LocationAddResponse> call = apiInterface.locationAddResponseCall(RestUtils.getContentType(),locationAddRequest());
        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<LocationAddResponse>() {
            @Override
            public void onResponse(@NotNull Call<LocationAddResponse> call, @NotNull Response<LocationAddResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG, "AddLocationResponse" + new Gson().toJson(response.body()));


                if (response.body() != null) {

                    if(response.body().getCode() == 200){
                        Intent i = new Intent(AddMyAddressOldUserActivity.this, ManageAddressActivity.class);
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
            public void onFailure(@NotNull Call<LocationAddResponse> call, @NotNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"AddLocationResponseflr"+t.getMessage());
            }
        });

    }
    private LocationAddRequest locationAddRequest() {
        /*
         * user_id : 5fb36ca169f71e30a0ffd3f7
         * location_state : asdfasdfasd
         * location_country : asdfasdfasd
         * location_city : asdfasdfasd
         * location_pin : asdfasdfasd
         * location_address : asdfasdfasd
         * location_lat : 18.90123
         * location_long : 12.09123
         * location_title : 23-10-1996 12:09 AM
         * location_nickname : 123
         * default_status : true
         * date_and_time : 23-10-1996 12:09 AM
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        Log.w(TAG,"AddLocationRequest--->"+"latitude"+latitude+" "+"longtitude :"+longtitude);


        LocationAddRequest locationAddRequest = new LocationAddRequest();
        locationAddRequest.setUser_id(userid);
        locationAddRequest.setLocation_state(state);
        locationAddRequest.setLocation_country(country);
        locationAddRequest.setLocation_city(CityName);
        locationAddRequest.setLocation_pin(postalcode);
        locationAddRequest.setLocation_address(AddressLine);
        locationAddRequest.setLocation_lat(latitude);
        locationAddRequest.setLocation_long(longtitude);
        locationAddRequest.setLocation_title(LocationType);
        locationAddRequest.setLocation_nickname(edt_pickname.getText().toString());
        locationAddRequest.setDefault_status(defaultstatus);
        locationAddRequest.setDate_and_time(currentDateandTime);
        locationAddRequest.setMobile_type("Android");

        Log.w(TAG," locationAddRequest"+ new Gson().toJson(locationAddRequest));
        return locationAddRequest;
    }

    private void getAddress(double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (null != listAddresses && listAddresses.size() > 0) {
                Address address = listAddresses.get(0);
                result.append(address.getLocality()).append("\n");
                result.append(address.getCountryName());
                Log.w(TAG,"getAddress-->"+result.toString());

                 state = listAddresses.get(0).getAdminArea();
                 country = listAddresses.get(0).getCountryName();
                 String subLocality = listAddresses.get(0).getSubLocality();
                 postalcode = listAddresses.get(0).getPostalCode();
                AddressLine = listAddresses.get(0).getAddressLine(0);
                CityName = listAddresses.get(0).getLocality();


                // Thoroughfare seems to be the street name without numbers
                 street = address.getThoroughfare();

                 if(street != null){
                     edt_location.setText(street);
                 }else if(subLocality != null ){
                     edt_location.setText(subLocality);
                 }


                Log.w(TAG,"AddressLine :"+AddressLine+"CityName :"+CityName+"street :"+street);

                Log.w(TAG,"state :"+state+" "+"country :"+country+"subLocality :"+subLocality+"postalcode :"+postalcode);
            }
        } catch (IOException e) {
            Log.e("tag", Objects.requireNonNull(e.getMessage()));
        }

        result.toString();
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

