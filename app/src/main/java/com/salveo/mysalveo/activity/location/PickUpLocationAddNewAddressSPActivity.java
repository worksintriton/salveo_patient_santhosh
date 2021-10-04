package com.salveo.mysalveo.activity.location;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.API;
import com.salveo.mysalveo.responsepojo.CartDetailsResponse;
import com.salveo.mysalveo.responsepojo.GetAddressResultResponse;
import com.salveo.mysalveo.service.GPSTracker;
import com.salveo.mysalveo.serviceprovider.ShippingAddressSPActivity;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PickUpLocationAddNewAddressSPActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    String TAG = "PickUpLocationAddNewAddressSPActivity";

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private GoogleMap mMap;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_setpickuppoint)
    Button btn_setpickuppoint;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView imgBack;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.imgLocationPinUp)
    ImageView imgLocationPinUp;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_placessearch)
    RelativeLayout rl_placessearch;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_searchlocationaddress)
    TextView tv_searchlocationaddress;


    private double latitude = 0, longitude = 0;

    String strlatlng = "";


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;



    String CityName, AddressLine ,PostalCode;




    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback locationCallback;

    private String fromactivity;
    private String placesearchactivity;

    List<CartDetailsResponse.DataBean> Data = new ArrayList<>();
    private int prodouct_total;
    private int shipping_charge;
    private int discount_price;
    private int grand_total;
    private int prodcut_count;
    private int prodcut_item_count;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_location_allow);

        ButterKnife.bind(this);

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.choose_location));
        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);



        imgLocationPinUp.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");
            placesearchactivity = extras.getString("placesearchactivity");
            Log.w(TAG,"fromactivity if : "+fromactivity+" placesearchactivity : "+placesearchactivity);

            Data = (List<CartDetailsResponse.DataBean>) extras.getSerializable("data");
            prodouct_total = extras.getInt("product_total");
            shipping_charge = extras.getInt("shipping_charge");
            discount_price = extras.getInt("discount_price");
            grand_total = extras.getInt("grand_total");
            prodcut_count = extras.getInt("prodcut_count");
            prodcut_item_count = extras.getInt("prodcut_item_count");

        }else{
            fromactivity  = TAG;
            Log.w(TAG,"fromactivity else: "+fromactivity);

        }




        rl_placessearch.setOnClickListener(v -> {
            Intent intent = new Intent(PickUpLocationAddNewAddressSPActivity.this, PlacesSearchActivity.class);
            intent.putExtra("data", (Serializable) Data);
            intent.putExtra("product_total",prodouct_total);
            intent.putExtra("shipping_charge",shipping_charge);
            intent.putExtra("discount_price",discount_price);
            intent.putExtra("grand_total",grand_total);
            intent.putExtra("prodcut_count",prodcut_count);
            intent.putExtra("prodcut_item_count",prodcut_item_count);
            intent.putExtra("fromactivity",TAG);
           /* intent.putExtra("id",id);
            intent.putExtra("userid",userid);*/
            startActivity(intent);
        });

        avi_indicator.setVisibility(View.GONE);
        imgBack.setOnClickListener(v -> onBackPressed());




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        if (extras != null) {}else{
            checkLocation();
        }








        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(this);


        btn_setpickuppoint.setOnClickListener(v -> {
            if(CityName != null){
                   Intent intent = new Intent(PickUpLocationAddNewAddressSPActivity.this,AddMyAddressShippingSPActivity.class);
                    intent.putExtra("latlng",strlatlng);
                    intent.putExtra("cityname",CityName);
                    intent.putExtra("address",AddressLine);
                    intent.putExtra("PostalCode",PostalCode);
                    intent.putExtra("fromactivity",fromactivity);
                    intent.putExtra("data", (Serializable) Data);
                    intent.putExtra("product_total",prodouct_total);
                    intent.putExtra("shipping_charge",shipping_charge);
                    intent.putExtra("discount_price",discount_price);
                    intent.putExtra("grand_total",grand_total);
                    intent.putExtra("prodcut_count",prodcut_count);
                    intent.putExtra("prodcut_item_count",prodcut_item_count);
                    startActivity(intent);
            }else{
                Toasty.warning(PickUpLocationAddNewAddressSPActivity.this,"Please select citynmae",Toasty.LENGTH_SHORT).show();
            }


        });





    } //end of oncreate




    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;




        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().isMyLocationButtonEnabled();
        mMap.getUiSettings().isZoomControlsEnabled();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(PickUpLocationAddNewAddressSPActivity.this);
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {

                if (locationResult.getLastLocation() != null) {

                    latitude = locationResult.getLastLocation().getLatitude();
                    longitude = locationResult.getLastLocation().getLongitude();



                    LatLng sourcePoint = new LatLng(latitude, longitude);
                    googleMap.addMarker(new MarkerOptions().position(sourcePoint).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
                    mFusedLocationClient.removeLocationUpdates(locationCallback);
                }
            }
        };
        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());



        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        mMap.setOnMapClickListener(latLng -> {
            Log.w(TAG,"setOnMapClickListener latLng---->"+latLng);
            strlatlng  = String.valueOf(latLng);


            String newString = strlatlng.replace("lat/lng:", "");
            Log.w(TAG,"setOnMapClickListener latlng=="+newString);

            String latlngs = newString.trim().replaceAll("\\(", "").replaceAll("\\)","").trim();
            Log.w(TAG,"setOnMapClickListener latlngs=="+latlngs);

            String[] separated = latlngs.split(",");
            String lat = separated[0];
            String lon = separated[1];

            latitude = Double.parseDouble(lat);
            longitude = Double.parseDouble(lon);



            Log.w(TAG,"setOnMapClickListener latlong :"+latitude+" "+longitude);

            //  getAddress(latitude,longitude);

            if(latitude != 0 && longitude != 0){
                latLng = new LatLng(latitude,longitude);
                getAddressResultResponse(latLng);

            }



            mMap.clear();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
            MarkerOptions markerOptions1 = new MarkerOptions().position(Objects.requireNonNull(latLng)).title("");
            markerOptions1.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));
            mMap.addMarker(markerOptions1);

        });


    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    mLocationRequest, this);
        }

        mGoogleApiClient.connect();
        setUpDragListners();
    }

    private void setUpDragListners() {
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        GPSTracker gps = new GPSTracker(PickUpLocationAddNewAddressSPActivity.this);

        gps.canGetLocation();
        LatLng curentpoint = new LatLng(gps.getLatitude(), gps.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(curentpoint).zoom(15f).tilt(30).build();
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));




        mMap.setOnCameraMoveListener(() -> {
            mMap.clear();
            imgLocationPinUp.setVisibility(View.VISIBLE);


        });
        mMap.setOnCameraIdleListener(() -> {
            if(placesearchactivity != null && placesearchactivity.equalsIgnoreCase("placesearchactivity")){

            }else {
                // mMap.clear();
                imgLocationPinUp.setVisibility(View.GONE);
                double CameraLat = mMap.getCameraPosition().target.latitude;
                double CameraLong = mMap.getCameraPosition().target.longitude;
                Log.w(TAG, "setOnCameraIdleListener--->" + "CameraLat :" + CameraLat + " " + "CameraLong :" + CameraLong);
                getChangeLocationBackground(CameraLat, CameraLong);
            }

        });


    }
    private void getChangeLocationBackground(double latitude, double longitude) {
        Log.w(TAG,"getChangeLocationBackground--->"+"latitude :"+latitude+" "+"longitude :"+longitude);

        LatLng latLng = new LatLng(latitude, longitude);
        strlatlng = String.valueOf(latLng);
        if(latitude != 0 && longitude != 0){
            getAddressResultResponse(latLng);

        }

        mMap.clear();
        MarkerOptions markerOptions = new MarkerOptions().position(mMap.getCameraPosition().target)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));
        mMap.addMarker(markerOptions);





    }

    @Override
    public void onConnectionSuspended(int i) {
    }
    @Override
    public void onLocationChanged(Location location) {
        Log.w(TAG,"onLocationChanged-->");

        mLastLocation = location;
        if (mCurrLocationMarker != null) { mCurrLocationMarker.remove(); }
        //Showing Current Location Marker on Map
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        Log.w(TAG,"onLocationChanged latLng--->"+latLng);

        strlatlng  = String.valueOf(latLng);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));


        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        String provider = locationManager.getBestProvider(new Criteria(), true);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //CityName = extras.getString("cityname");
            String placesearchactivity = extras.getString("placesearchactivity");
            fromactivity = extras.getString("fromactivity");
            double lat = extras.getDouble("lat");
            double lon = extras.getDouble("lon");
            if(lat != 0 && lon != 0){
                latitude = lat;
                longitude = lon;
                Log.w(TAG,"BundleData for search places :"+"lat :"+lat+" "+"lon :"+lon);

                latLng = new LatLng(lat, lon);
            }



            if(placesearchactivity != null && placesearchactivity.equalsIgnoreCase("placesearchactivity")){
                if(latitude != 0 && longitude != 0){
                    latLng = new LatLng(latitude,longitude);
                   // Log.w(TAG,"onLocationChanged BundleData if-->"+"Call getAddressResultResponse");
                    //Log.w(TAG,"onLocationChanged BundleData for searched places :"+"lat :"+lat+" "+"lon :"+lon);
                    strlatlng = String.valueOf(latLng);
                    //Log.w(TAG,"onLocationChanged BundleData"+strlatlng);
                    getAddressResultResponse(latLng);
                }
            }else {
                Log.w(TAG,"onLocationChanged BundleData else");
                mMap.clear();
                //  mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
                markerOptions = new MarkerOptions().position(Objects.requireNonNull(latLng)).title(CityName);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));
                mMap.addMarker(markerOptions);

                assert provider != null;
                Location locations = locationManager.getLastKnownLocation(provider);
                List<String> providerList = locationManager.getAllProviders();
                if (null != locations && providerList.size() > 0) {
                    double longitude = locations.getLongitude();
                    double latitude = locations.getLatitude();


                    if (latitude != 0 && longitude != 0) {
                        latLng = new LatLng(latitude, longitude);
                        Log.w(TAG, "onLocationChanged-->" + "Call getAddressResultResponse");
                        getAddressResultResponse(latLng);

                    }
                }
            }
        }

        //   markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        /*mCurrLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));*/

        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }
    @Override
    public void onConnectionFailed(@NotNull ConnectionResult connectionResult) {
    }
    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String @NotNull [] permissions, @NotNull int @NotNull [] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    if (mGoogleApiClient == null) {
                        buildGoogleApiClient();
                    }
                    mMap.setMyLocationEnabled(true);
                }
            } else {
                Toast.makeText(this, "permission denied",
                        Toast.LENGTH_LONG).show();
            }
        }
    }


    private void checkLocation(){
        try{
            LocationManager lm = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            boolean gps_enabled = false;
            boolean network_enabled = false;

            try {
                if (lm != null) {
                    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                }
            } catch(Exception ignored) {}

            try {
                if (lm != null) {
                    network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                }
            } catch(Exception ignored) {}

            if(!gps_enabled && !network_enabled) {

                if (lm != null && !lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    showSettingsAlert();
                }

            }else{
                getLatandLong();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void getLatandLong(){
        try{
            if (ContextCompat.checkSelfPermission(PickUpLocationAddNewAddressSPActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(PickUpLocationAddNewAddressSPActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(PickUpLocationAddNewAddressSPActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            }
            else {
                GPSTracker gps = new GPSTracker(PickUpLocationAddNewAddressSPActivity.this);

                // Check if GPS enabled
                if (gps.canGetLocation()) {
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    Log.w(TAG,"getLatandLong--->"+"latitude" + " " + latitude+"longitude" + " " + longitude);

                   /* String country = gps.getCountryName(MapsActivity.this);
                    String city = gps.getLocality(MapsActivity.this);
                    String postalCode = gps.getPostalCode(MapsActivity.this);
                    String addressLine = gps.getAddressLine(MapsActivity.this);
                    Log.w(TAG,"country : "+country+" "+"city : "+" "+city+"postalCode : "+" "+postalCode+" "+"addressLine :"+" "+addressLine);*/

                    //  Toasty.warning(getApplicationContext(), "latitude :"+latitude+"longitude :"+longitude+"address :"+addressLine, Toast.LENGTH_SHORT, true).show();


                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PickUpLocationAddNewAddressSPActivity.this);

        // Setting DialogHelp Title
        alertDialog.setTitle("GPS is settings");

        // Setting DialogHelp Message
        alertDialog
                .setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings",
                (dialog, which) -> {
                    Intent intent = new Intent(
                            Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel",
                (dialog, which) -> dialog.cancel());

        // Showing Alert Message
        alertDialog.show();
    }






    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =  new Intent(PickUpLocationAddNewAddressSPActivity.this, ShippingAddressSPActivity.class);
        intent.putExtra("data", (Serializable) Data);
        intent.putExtra("product_total",prodouct_total);
        intent.putExtra("shipping_charge",shipping_charge);
        intent.putExtra("discount_price",discount_price);
        intent.putExtra("grand_total",grand_total);
        intent.putExtra("prodcut_count",prodcut_count);
        intent.putExtra("prodcut_item_count",prodcut_item_count);
        intent.putExtra("fromactivity",TAG);
        startActivity(intent);
        finish();


    }

    private void getAddressResultResponse(LatLng latLng) {
       // Log.w(TAG,"GetAddressResultResponse-->"+latLng);
        //avi_indicator.setVisibility(View.VISIBLE);
        // avi_indicator.smoothToShow();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API service = retrofit.create(API.class);
        String strlatlng = String.valueOf(latLng);
      //  Log.w(TAG,"getAddressResultResponse strlatlng-->"+strlatlng);
        String newString = strlatlng.replace("lat/lng:", "");
       // Log.w(TAG,"getAddressResultResponse latlng=="+newString);

        String latlngs = newString.trim().replaceAll("\\(", "").replaceAll("\\)","").trim();
       //Log.w(TAG,"getAddressResultResponse latlngs=="+latlngs);



        String key = API.MAP_KEY;
        service.getAddressResultResponseCall(latlngs, key).enqueue(new Callback<GetAddressResultResponse>() {
            @Override
            public void onResponse(@NotNull Call<GetAddressResultResponse> call, @NotNull Response<GetAddressResultResponse> response) {
                //avi_indicator.smoothToHide();
                Log.w(TAG,"url  :%s"+ call.request().url().toString());


                Log.w(TAG,"GetAddressResultResponse" + new Gson().toJson(response.body()));


                if(response.body() != null) {
                    String currentplacename = null;
                    String compundcode = null;

                    if(response.body().getPlus_code().getCompound_code() != null){
                        compundcode = response.body().getPlus_code().getCompound_code();
                    }
                    if(compundcode != null) {
                        String[] separated = compundcode.split(",");
                        String placesname = separated[0];
                        String[] splitData = placesname.split("\\s", 2);
                        String code = splitData[0];
                        currentplacename = splitData[1];
                        Log.w(TAG, "code-->" + code + "currentplacename : " + currentplacename);
                    }


                    String localityName = null;
                    String cityName = null;
                    String sublocalityName = null;
                    String postalCode;


                    List<GetAddressResultResponse.ResultsBean> getAddressResultResponseList;
                    getAddressResultResponseList = response.body().getResults();
                    if (getAddressResultResponseList.size() > 0) {
                        AddressLine = getAddressResultResponseList.get(0).getFormatted_address();
                        Log.w(TAG, "FormateedAddress-->" + AddressLine);

                    }
                    List<GetAddressResultResponse.ResultsBean.AddressComponentsBean> addressComponentsBeanList = response.body().getResults().get(0).getAddress_components();
                    if(addressComponentsBeanList != null) {
                        if (addressComponentsBeanList.size() > 0) {
                            for (int i = 0; i < addressComponentsBeanList.size(); i++) {
                                //Log.w(TAG, "addressComponentsBeanList size : " + addressComponentsBeanList.size());

                                for (int j = 0; j < addressComponentsBeanList.get(i).getTypes().size(); j++) {
                                    //Log.w(TAG, "getTypes size : " + addressComponentsBeanList.get(i).getTypes().size());

                                   // Log.w(TAG, "TYPES-->" + addressComponentsBeanList.get(i).getTypes());
                                    List<String> typesList = addressComponentsBeanList.get(i).getTypes();

                                    if (typesList.contains("postal_code")) {
                                        postalCode = addressComponentsBeanList.get(i).getShort_name();
                                        PostalCode = postalCode;
                                        Log.w(TAG, "Postal Short name ---->" + postalCode);

                                    }
                                    if (typesList.contains("locality")) {
                                        CityName = addressComponentsBeanList.get(i).getLong_name();
                                        localityName = addressComponentsBeanList.get(i).getShort_name();
                                        Log.w(TAG, "Locality Short name ---->" + localityName);
                                        Log.w(TAG, "Locality City  short name ---->" + cityName);


                                    }

                                    if (typesList.contains("administrative_area_level_2")) {
                                        cityName = addressComponentsBeanList.get(i).getShort_name();
                                        //  CityName = cityName;
                                        Log.w(TAG, "City  short name ---->" + cityName);

                                    }
                                    if (typesList.contains("sublocality_level_1")) {
                                        sublocalityName = addressComponentsBeanList.get(i).getShort_name();
                                        Log.w(TAG, "sublocality_level_1  short name ---->" + cityName);

                                    }

                                }

                            }
                            if (AddressLine != null) {
                                tv_searchlocationaddress.setText(AddressLine);
                            } else if (sublocalityName != null) {
                                tv_searchlocationaddress.setText(sublocalityName);
                            } else if (localityName != null) {
                                tv_searchlocationaddress.setText(localityName);
                            } else if (currentplacename != null) {
                                tv_searchlocationaddress.setText(currentplacename);

                            } else {
                                if (cityName != null && !cityName.isEmpty()) {
                                    tv_searchlocationaddress.setText(cityName);

                                }
                            }


                        }
                    }
                }


            }

            @Override
            public void onFailure(@NotNull Call<GetAddressResultResponse> call, @NotNull Throwable t) {
                //avi_indicator.smoothToHide();
                t.printStackTrace();
            }
        });
    }

}