package com.salveo.mysalveo.fragmentdoctor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.doctor.DoctorBusinessInfoActivity;
import com.salveo.mysalveo.doctor.DoctorDashboardActivity;
import com.salveo.mysalveo.doctor.DoctorMyCalendarNewUserActivity;
import com.salveo.mysalveo.fragmentdoctor.myappointments.FragmentDoctorCompletedAppointment;
import com.salveo.mysalveo.fragmentdoctor.myappointments.FragmentDoctorMissedAppointment;
import com.salveo.mysalveo.fragmentdoctor.myappointments.FragmentDoctorNewAppointment;
import com.salveo.mysalveo.requestpojo.DoctorCheckStatusRequest;
import com.salveo.mysalveo.responsepojo.DoctorCheckStatusResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentDoctorDashboard extends Fragment  {

    private   String TAG = "FragmentDoctorDashboard";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tablayout)
    TabLayout tablayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private SharedPreferences preferences;
    private Context mContext;
    private String userid;
    private boolean isDoctorStatus = false;
    private boolean isProfileUpdatedClose;

    SessionManager session;

    FragmentManager  childFragMang;

    private int someIndex = 0;

    public FragmentDoctorDashboard() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_dashboard, container, false);

        childFragMang= getChildFragmentManager();


        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ButterKnife.bind(this, view);
        mContext = getActivity();
        avi_indicator.setVisibility(View.GONE);

        session = new SessionManager(mContext);
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG,"userid : "+userid);



        if(userid != null){
            if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                doctorCheckStatusResponseCall();
            }
        }





        return view;


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(childFragMang,3);
        adapter.addFragment(new FragmentDoctorNewAppointment(), "New");
        adapter.addFragment(new FragmentDoctorCompletedAppointment(), "Completed");
        adapter.addFragment(new FragmentDoctorMissedAppointment(), "Missed");
        viewPager.setAdapter(adapter);
    }




    static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager,int number) {
            super(manager,number);
        }

        @Override
        public @NotNull Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



    @SuppressLint("LogNotTimber")
    private void doctorCheckStatusResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<DoctorCheckStatusResponse> call = apiInterface.doctorCheckStatusResponseCall(RestUtils.getContentType(), doctorCheckStatusRequest());
        Log.w(TAG,"doctorCheckStatusResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<DoctorCheckStatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<DoctorCheckStatusResponse> call, @NonNull Response<DoctorCheckStatusResponse> response) {

                Log.w(TAG,"doctorCheckStatusResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getData() != null){
                            if(!response.body().getData().isProfile_status()){
                                Intent intent = new Intent(mContext, DoctorBusinessInfoActivity.class);
                                intent.putExtra("fromactivity",TAG);
                                startActivity(intent);
                            }
                            else if(!response.body().getData().isCalender_status()){
                                Intent intent = new Intent(mContext, DoctorMyCalendarNewUserActivity.class);
                                intent.putExtra("fromactivity",TAG);
                                startActivity(intent);
                            }
                            else{
                                String profileVerificationStatus = response.body().getData().getProfile_verification_status();
                                if( profileVerificationStatus != null && profileVerificationStatus.equalsIgnoreCase("Not verified")){
                                    showProfileStatus(response.body().getMessage());

                                }else if( profileVerificationStatus != null && profileVerificationStatus.equalsIgnoreCase("profile updated")){
                                    if(!session.isProfileUpdate()){
                                        showProfileUpdateStatus(response.body().getMessage());

                                    }

                                }else{
                                    isDoctorStatus = true;
                                    Log.w(TAG,"isDoctorStatus else : "+isDoctorStatus);

                                    if(isDoctorStatus){
                                        setupViewPager(viewPager);
                                        if(DoctorDashboardActivity.appintments != null && DoctorDashboardActivity.appintments.equalsIgnoreCase("New")){
                                            someIndex = 0;
                                        }
                                        else if(DoctorDashboardActivity.appintments != null && DoctorDashboardActivity.appintments.equalsIgnoreCase("Completed")){
                                            someIndex = 1;
                                        }
                                        else if(DoctorDashboardActivity.appintments != null && DoctorDashboardActivity.appintments.equalsIgnoreCase("Missed")){
                                            someIndex = 2;
                                        }

                                        tablayout.setupWithViewPager(viewPager);
                                        TabLayout.Tab tab = tablayout.getTabAt(someIndex);
                                        tab.select();

                                    }

                                }


                            }

                        }

                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<DoctorCheckStatusResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"doctorCheckStatusResponseCall"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private DoctorCheckStatusRequest doctorCheckStatusRequest() {
        DoctorCheckStatusRequest doctorCheckStatusRequest = new DoctorCheckStatusRequest();
        doctorCheckStatusRequest.setUser_id(userid);
        Log.w(TAG,"doctorCheckStatusRequest"+ "--->" + new Gson().toJson(doctorCheckStatusRequest));
        return doctorCheckStatusRequest;
    }
    @SuppressLint("SetTextI18n")
    private void showProfileStatus(String message) {

        try {

            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.alert_no_internet_layout);
            dialog.setCancelable(false);
            Button dialogButton = dialog.findViewById(R.id.btnDialogOk);
            dialogButton.setText("Refresh");
            TextView tvInternetNotConnected = dialog.findViewById(R.id.tvInternetNotConnected);
            tvInternetNotConnected.setText(message);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                        doctorCheckStatusResponseCall();
                    }
                    dialog.dismiss();

                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    private void showProfileUpdateStatus(String message) {

        try {

            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.alert_profile_update_layout);
            dialog.setCancelable(false);
            Button dialogButton = dialog.findViewById(R.id.btnDialogOk);
            dialogButton.setText("Refresh");
            TextView tvInternetNotConnected = dialog.findViewById(R.id.tvInternetNotConnected);
            tvInternetNotConnected.setText(message);
            ImageView img_close = dialog.findViewById(R.id.img_close);

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                        doctorCheckStatusResponseCall();
                    }
                    dialog.dismiss();

                }
            });

            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    session.setIsProfileUpdate(true);
                    isProfileUpdatedClose = true;
                    dialog.dismiss();

                }
            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }


}