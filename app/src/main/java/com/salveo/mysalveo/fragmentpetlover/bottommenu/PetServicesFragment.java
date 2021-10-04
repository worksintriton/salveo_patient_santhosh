package com.salveo.mysalveo.fragmentpetlover.bottommenu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;

import com.salveo.mysalveo.adapter.PetServicesAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;

import com.salveo.mysalveo.requestpojo.ServiceCatRequest;
import com.salveo.mysalveo.responsepojo.DoctorSearchResponse;
import com.salveo.mysalveo.responsepojo.ServiceCatResponse;
import com.salveo.mysalveo.service.GPSTracker;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PetServicesFragment extends Fragment implements Serializable, View.OnClickListener {


    private String TAG = "PetServicesFragment";



    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;





    String token = "";
    String type ="";
    String name = "",patientid = "";

    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    double latitude, longitude;

    private Handler handler = new Handler();
    Runnable runnable;
    private TextView headertitle;






    Dialog dialog;
    private String userid;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_popular_services)
    RecyclerView rv_popular_services;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;


    private String AddressLine;
    private SharedPreferences preferences;
    private Context mContext;

    private static final int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private GoogleApiClient googleApiClient;
    Location mLastLocation;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private SupportMapFragment mapFragment;
    private List<DoctorSearchResponse.DataBean> doctorDetailsResponseList;
    private Dialog alertDialog;
    private String searchString = "";

    private int reviewcount;
    private String fromactivity,specialization;
    private List<ServiceCatResponse.DataBean> serviceCatList;


    public PetServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView-->");
        View view = inflater.inflate(R.layout.fragment_pet_services, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ButterKnife.bind(this, view);
        mContext = getActivity();

        gpsTracker = new GPSTracker(mContext);


        avi_indicator.setVisibility(View.GONE);
        includelayout = view.findViewById(R.id.includelayout);
        mShimmerViewContainer = includelayout.findViewById(R.id.shimmer_layout);



            if(getArguments() != null){
            fromactivity = getArguments().getString("fromactivity");
            reviewcount = getArguments().getInt("reviewcount");
            specialization = getArguments().getString("specialization");
            Log.w(TAG,"fromactivity : "+fromactivity+" reviewcount : "+reviewcount+" specialization : "+specialization);

            }





        SessionManager sessionManager = new SessionManager(mContext);
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG,"customerid-->"+userid);




        if (new ConnectionDetector(mContext).isNetworkAvailable(mContext)) {
            ServiceCatResponseCall();
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }










    @Override
    public void onClick(View v) {
        switch (v.getId()){



        }

    }


    @SuppressLint("LogNotTimber")
    private void ServiceCatResponseCall() {
      /*  avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        includelayout.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ServiceCatResponse> call = apiInterface.ServiceCatResponseCall(RestUtils.getContentType(), serviceCatRequest());
        Log.w(TAG,"ServiceCatResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ServiceCatResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ServiceCatResponse> call, @NonNull Response<ServiceCatResponse> response) {
                //avi_indicator.smoothToHide();
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.w(TAG,"ServiceCatResponseCall" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {


                        if (response.body().getData() != null) {
                            serviceCatList = response.body().getData();
                            if(serviceCatList != null && serviceCatList.size()>0){
                                txt_no_records.setVisibility(View.GONE);
                                rv_popular_services.setVisibility(View.VISIBLE);
                                setViewPetServices(serviceCatList);
                            }else{
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No pet service");
                                rv_popular_services.setVisibility(View.GONE);


                            }



                        }



                    }
                    else {
                        showErrorLoading(response.body().getMessage());
                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<ServiceCatResponse> call,@NonNull Throwable t) {
               // avi_indicator.smoothToHide();
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.e(" ServiceCatResponse flr", "--->" + t.getMessage());
            }
        });

    }

    @SuppressLint("LogNotTimber")
    private ServiceCatRequest serviceCatRequest() {
        /*
         * user_id : 5fd778437aa4cc1c6a1e5632
         */
        ServiceCatRequest serviceCatRequest = new ServiceCatRequest();
        serviceCatRequest.setUser_id(userid);
        Log.w(TAG,"serviceCatRequest"+ new Gson().toJson(serviceCatRequest));
        return serviceCatRequest;
    }

    private void setViewPetServices(List<ServiceCatResponse.DataBean> serviceCatList) {
        // Setting the layout as Staggered Grid for vertical orientation
        LinearLayoutManager staggeredGridLayoutManager = new LinearLayoutManager(getContext());
        rv_popular_services.setLayoutManager(staggeredGridLayoutManager);

        PetServicesAdapter petServicesAdapter = new PetServicesAdapter(mContext, serviceCatList);
        rv_popular_services.setAdapter(petServicesAdapter);
    }




    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
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

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {


                }
            } else {
                Toast.makeText(mContext, "permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }



}
