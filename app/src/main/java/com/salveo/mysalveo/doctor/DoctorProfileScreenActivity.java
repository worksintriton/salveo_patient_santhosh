package com.salveo.mysalveo.doctor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.LoginActivity;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.activity.location.AddMyAddressDoctorActivity;
import com.salveo.mysalveo.activity.location.EditMyAddressDoctorActivity;

import com.salveo.mysalveo.activity.location.PickUpLocationDoctorActivity;
import com.salveo.mysalveo.activity.location.PickUpLocationEditDoctorActivity;
import com.salveo.mysalveo.adapter.ViewPagerDoctorClinicDetailsAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.petlover.AddYourPetOldUserActivity;

import com.salveo.mysalveo.requestpojo.DoctorDetailsByUserIdRequest;

import com.salveo.mysalveo.responsepojo.DoctorDetailsByUserIdResponse;

import com.salveo.mysalveo.responsepojo.PetListResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;


import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorProfileScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private  String TAG = "DoctorProfileScreenActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_profile)
    ImageView img_profile;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_edit_image)
    TextView txt_edit_image;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_usrname)
    TextView txt_usrname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_mail)
    TextView txt_mail;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_phn_num)
    TextView txt_phn_num;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_edit_profile)
    TextView txt_edit_profile;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_manage_address)
    TextView txt_manage_address;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_change_password)
    TextView txt_change_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_logout)
    TextView txt_logout;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_edit_doc_business_info)
    TextView txt_edit_doc_business_info;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dr_specialization)
    TextView txt_dr_specialization;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pets_handled)
    TextView txt_pets_handled;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_clinic_address)
    TextView txt_clinic_address;

   @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_notification)
    ImageView img_notification;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_doctor_footer)
    View include_doctor_footer;






    private SessionManager session;
    String name,emailid,phoneNo,userid;
    private List<PetListResponse.DataBean> petList;
    private Dialog dialog;
    private List<DoctorDetailsByUserIdResponse.DataBean.ClinicPicBean> doctorclinicdetailsResponseList;
    private String clinicname,doctorname;
    private String concatenatedSpcNames = "";
    private String concatenatedPetHandled = "";

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;
    private String profileimage;
    private String fromactivity;

    String locationtype;
    private String pincode,cityname,address,nickname;
    private boolean defaultstatus;
    private String id;
    double latitude, longtitude;
    private String latlng;
    private String PostalCode;

    /* Bottom Navigation */

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_service)
    RelativeLayout rl_service;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_shop)
    RelativeLayout rl_shop;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_shop)
    TextView title_shop;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_clinicname)
    TextView txt_clinicname;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile_screen);
        ButterKnife.bind(this);

        Log.w(TAG,"onCreate : ");
        avi_indicator.setVisibility(View.GONE);


        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        name = user.get(SessionManager.KEY_FIRST_NAME);
        emailid = user.get(SessionManager.KEY_EMAIL_ID);
        phoneNo = user.get(SessionManager.KEY_MOBILE);
        userid = user.get(SessionManager.KEY_ID);
        profileimage = user.get(SessionManager.KEY_PROFILE_IMAGE);

        Log.w(TAG,"session profileimage : "+profileimage);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");


            id = extras.getString("id");
            locationtype = extras.getString("locationtype");
            defaultstatus = extras.getBoolean("defaultstatus");
            latitude = extras.getDouble("lat");
            longtitude = extras.getDouble("lon");
            pincode = extras.getString("pincode");
            PostalCode = extras.getString("PostalCode");
            cityname = extras.getString("cityname");
            address = extras.getString("address");
            nickname = extras.getString("nickname");
            latlng = extras.getString("latlng");


        }

        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });




        txt_usrname.setText(name);
        txt_mail.setText(emailid);
        txt_phn_num.setText(phoneNo);


        if(profileimage != null && !profileimage.isEmpty()){
            Glide.with(DoctorProfileScreenActivity.this)
                    .load(profileimage)
                    .into(img_profile);
        }else{
            Glide.with(DoctorProfileScreenActivity.this)
                    .load(R.drawable.upload)
                    .into(img_profile);

        }






        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            doctorDetailsByUserIdResponseCall();
        }


        img_back.setOnClickListener(this);
        txt_manage_address.setOnClickListener(this);
        txt_change_password.setOnClickListener(this);
        txt_logout.setOnClickListener(this);
        txt_edit_profile.setOnClickListener(this);
        txt_edit_image.setOnClickListener(this);
        txt_edit_doc_business_info.setOnClickListener(this);

        //bottom_navigation_view.getMenu().findItem(R.id.home).setChecked(true);
        /*home*/

        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);

        rl_home.setOnClickListener(this);
        rl_shop.setOnClickListener(this);
        rl_comn.setOnClickListener(this);
        rl_homes.setOnClickListener(this);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("DoctorNavigationDrawer")){
            startActivity(new Intent(getApplicationContext(), DoctorDashboardActivity.class));
            finish();
        }if(fromactivity != null && fromactivity.equalsIgnoreCase("ManageAddressDoctorActivity")){
            startActivity(new Intent(getApplicationContext(), ManageAddressDoctorActivity.class));
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PickUpLocationDoctorActivity")){
            startActivity(new Intent(getApplicationContext(), PickUpLocationDoctorActivity.class));
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("DoctorMyOrdrersActivity")){
            startActivity(new Intent(getApplicationContext(), DoctorMyOrdrersActivity.class));
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("EditMyAddressDoctorActivity")){
            Intent intent = new Intent(getApplicationContext(), EditMyAddressDoctorActivity.class);
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
            startActivity(intent);
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("AddMyAddressDoctorActivity")){
            Intent intent = new Intent(getApplicationContext(), AddMyAddressDoctorActivity.class);
            intent.putExtra("latlng",latlng);
            intent.putExtra("cityname",cityname);
            intent.putExtra("address",address);
            intent.putExtra("PostalCode",PostalCode);
            intent.putExtra("userid",userid);
            intent.putExtra("nickname",nickname);
            startActivity(intent);
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PickUpLocationEditDoctorActivity")){
            Intent intent = new Intent(getApplicationContext(), PickUpLocationEditDoctorActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("userid",userid);
            intent.putExtra("nickname",nickname);
            intent.putExtra("locationtype",locationtype);
            intent.putExtra("defaultstatus",defaultstatus);
            intent.putExtra("lat",latitude);
            intent.putExtra("lon",longtitude);
            startActivity(intent);
            finish();
        }
        else{
            startActivity(new Intent(getApplicationContext(), DoctorDashboardActivity.class));
            finish();
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
                case R.id.txt_edit_profile:
                startActivity(new Intent(getApplicationContext(), DoctorEditProfileActivity.class));
                break;
                case R.id.txt_edit_image:
                startActivity(new Intent(getApplicationContext(), EditDoctorProfileImageActivity.class));
                break;
                case R.id.txt_edit_doc_business_info:
                startActivity(new Intent(getApplicationContext(), EditDoctorBusinessInfoActivity.class));
                break;
                case R.id.txt_manage_address:
                    gotoManageAddress();
                break;
                case R.id.txt_change_password:
                break;
                case R.id.txt_logout:
                    showLogOutAppAlert();
                    //confirmLogoutDialog();
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

    private void gotoAddYourPet() {
        startActivity(new Intent(getApplicationContext(), AddYourPetOldUserActivity.class));
    }

    private void gotoManageAddress() {
        startActivity(new Intent(DoctorProfileScreenActivity.this, ManageAddressDoctorActivity.class));
    }

    private void confirmLogoutDialog(){
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(DoctorProfileScreenActivity.this);
        alertDialogBuilder.setMessage("Are you sure want to logout?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {

                        gotoLogout();


                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogBuilder.setCancelable(true);
            }
        });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    private void gotoLogout() {
        session.logoutUser();
        session.setIsLogin(false);
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();



    }


    @SuppressLint("LogNotTimber")
    private void doctorDetailsByUserIdResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<DoctorDetailsByUserIdResponse> call = ApiService.doctorDetailsByUserIdResponseCall(RestUtils.getContentType(),doctorDetailsByUserIdRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<DoctorDetailsByUserIdResponse>() {
            @Override
            public void onResponse(@NonNull Call<DoctorDetailsByUserIdResponse> call, @NonNull Response<DoctorDetailsByUserIdResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"DoctorDetailsByUserIdResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(200 == response.body().getCode()){

                        if(response.body().getData() != null) {
                            if (response.body().getData().getClinic_name() != null) {
                                txt_clinicname.setText(response.body().getData().getClinic_name());
                            }else{
                                txt_clinicname.setText("");
                            }
                            if (response.body().getData().getClinic_pic() != null) {
                                doctorclinicdetailsResponseList = response.body().getData().getClinic_pic();
                                Log.w(TAG, "Size" + doctorclinicdetailsResponseList.size());
                                Log.w(TAG, "doctorclinicdetailsResponseList : " + new Gson().toJson(doctorclinicdetailsResponseList));
                            }
                            if (response.body().getData().getClinic_name() != null) {
                                clinicname = response.body().getData().getClinic_name();
                            }
                            if (response.body().getData().getDr_name() != null) {
                                doctorname = response.body().getData().getDr_name();
                            }
                            if (response.body().getData().getClinic_loc() != null) {
                                txt_clinic_address.setText(response.body().getData().getClinic_loc());
                            }
                            if (doctorclinicdetailsResponseList != null && doctorclinicdetailsResponseList.size() > 0) {

                                for (int i = 0; i < doctorclinicdetailsResponseList.size(); i++) {
                                    doctorclinicdetailsResponseList.get(i).getClinic_pic();
                                    Log.w(TAG, "RES" + ", " + doctorclinicdetailsResponseList.get(i).getClinic_pic());
                                }


                                viewpageData(doctorclinicdetailsResponseList);

                            }
                            if (response.body().getData().getSpecialization() != null) {
                                for (int i = 0; i < response.body().getData().getSpecialization().size(); i++) {
                                    concatenatedSpcNames += response.body().getData().getSpecialization().get(i).getSpecialization();
                                    if (i < response.body().getData().getSpecialization().size() - 1)
                                        concatenatedSpcNames += ", ";
                                }
                                txt_dr_specialization.setText(concatenatedSpcNames);

                            }
                            if (response.body().getData().getPet_handled() != null) {
                                for (int i = 0; i < response.body().getData().getPet_handled().size(); i++) {
                                    concatenatedPetHandled += response.body().getData().getPet_handled().get(i).getPet_handled();
                                    if (i < response.body().getData().getPet_handled().size() - 1)
                                        concatenatedPetHandled += ", ";
                                }
                                txt_pets_handled.setText(concatenatedPetHandled);

                            }
                        }

                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<DoctorDetailsByUserIdResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"DoctorDetailsResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private DoctorDetailsByUserIdRequest doctorDetailsByUserIdRequest() {
        DoctorDetailsByUserIdRequest doctorDetailsByUserIdRequest = new DoctorDetailsByUserIdRequest();
        doctorDetailsByUserIdRequest.setUser_id(userid);
        Log.w(TAG,"doctorDetailsByUserIdRequest"+ "--->" + new Gson().toJson(doctorDetailsByUserIdRequest));
        return doctorDetailsByUserIdRequest;
    }
    private void viewpageData(List<DoctorDetailsByUserIdResponse.DataBean.ClinicPicBean> doctorclinicdetailsResponseList) {
        tabLayout.setupWithViewPager(viewPager, true);

        ViewPagerDoctorClinicDetailsAdapter viewPagerClinicDetailsAdapter = new ViewPagerDoctorClinicDetailsAdapter(getApplicationContext(), doctorclinicdetailsResponseList);
        viewPager.setAdapter(viewPagerClinicDetailsAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update =  new Runnable() {
            public void run() {
                if (currentPage == DoctorProfileScreenActivity.this.doctorclinicdetailsResponseList.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, false);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

    }


    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), DoctorDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }

    private void showLogOutAppAlert() {
        try {

            dialog = new Dialog(DoctorProfileScreenActivity.this);
            dialog.setContentView(R.layout.alert_logout_layout);
            Button btn_no = dialog.findViewById(R.id.btn_no);
            Button btn_yes = dialog.findViewById(R.id.btn_yes);

            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    gotoLogout();

                }
            });
            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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