package com.salveo.mysalveo.doctor;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.petlover.PetMyappointmentsActivity;
import com.salveo.mysalveo.requestpojo.AppoinmentCancelledRequest;
import com.salveo.mysalveo.requestpojo.DoctorStartAppointmentRequest;
import com.salveo.mysalveo.requestpojo.PetNewAppointmentDetailsRequest;
import com.salveo.mysalveo.responsepojo.AppoinmentCancelledResponse;
import com.salveo.mysalveo.responsepojo.AppointmentsUpdateResponse;
import com.salveo.mysalveo.responsepojo.PetNewAppointmentDetailsResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorNewAppointmentDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DoctorNewAppointmentDetailsActivity";


    AVLoadingIndicatorView avi_indicator;


    ImageView img_back;


    ImageView img_user;


    TextView txt_usrname;


    TextView txt_serv_name;


    TextView txt_serv_cost;


    Button btn_cancel;

    Button btn_complete;

    ImageView img_petimg;


    TextView txt_pet_name;


    TextView txt_pet_type;


    TextView txt_breed;


    TextView txt_gender;


    TextView txt_color;

    TextView txt_weight;

    TextView txt_age;

    TextView txt_vaccinated;

    TextView txt_order_date;

    TextView txt_order_id;

    TextView txt_payment_method;

    TextView txt_order_cost;

    TextView txt_address;

    String appointment_id;

    ImageView img_videocall;

    String appoinment_status;

    String start_appointment_status;

    String userid, allergies,problem_info,pet_name,pet_type,doctorid;

    private Dialog dialog;

    LinearLayout ll_petlastvacinateddate;
    TextView txt_petlastvaccinatedage;
    private String bookedat;
    private boolean isVaildDate;

    TextView txt_appointment_date;
    private List<PetNewAppointmentDetailsResponse.DataBean.PetIdBean.PetImgBean> pet_image;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_doctor_footer)
    View include_doctor_footer;

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
        setContentView(R.layout.activity_doctor_new_appointment_details);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appointment_id = extras.getString("appointment_id");
            bookedat = extras.getString("bookedat");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        if(bookedat != null){
            compareDatesandTime(currentDateandTime,bookedat);
        }
        btn_cancel=findViewById(R.id.btn_cancel);

        if(isVaildDate){
            btn_cancel.setVisibility(View.VISIBLE);
        }else{
            btn_cancel.setVisibility(View.GONE);
        }


        avi_indicator=findViewById(R.id.avi_indicator);
        txt_appointment_date =findViewById(R.id.txt_appointment_date);


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



        img_back=findViewById(R.id.img_back);


        img_user =findViewById(R.id.img_user);


        txt_usrname =findViewById(R.id.txt_usrname);


        txt_serv_name=findViewById(R.id.txt_serv_name);
        txt_serv_name.setVisibility(View.GONE);

        txt_serv_cost=findViewById(R.id.txt_serv_cost);




        img_petimg=findViewById(R.id.img_petimg);


        txt_pet_name=findViewById(R.id.txt_pet_name);


        txt_pet_type=findViewById(R.id.txt_pet_type);


        txt_breed=findViewById(R.id.txt_breed);


        txt_gender=findViewById(R.id.txt_gender);


        txt_color=findViewById(R.id.txt_color);


        txt_weight=findViewById(R.id.txt_weight);


        txt_age=findViewById(R.id.txt_age);


        txt_vaccinated =findViewById(R.id.txt_vaccinated);

        ll_petlastvacinateddate = findViewById(R.id.ll_petlastvacinateddate);
        ll_petlastvacinateddate.setVisibility(View.GONE);
        txt_petlastvaccinatedage = findViewById(R.id.txt_petlastvaccinatedage);


        txt_order_date=findViewById(R.id.txt_order_date);


        txt_order_id =findViewById(R.id.txt_order);


        txt_payment_method =findViewById(R.id.txt_payment_method);


        txt_order_cost=findViewById(R.id.txt_order_cost);


        txt_address=findViewById(R.id.txt_address);

        img_videocall=findViewById(R.id.img_videocall);

        btn_complete = findViewById(R.id.btn_complete);

        

        if (new ConnectionDetector(DoctorNewAppointmentDetailsActivity.this).isNetworkAvailable(DoctorNewAppointmentDetailsActivity.this)) {
            petNewAppointmentResponseCall();
        }

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void petNewAppointmentResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PetNewAppointmentDetailsResponse> call = ApiService.petNewAppointDetailResponseCall(RestUtils.getContentType(), petNewAppointmentDetailsRequest());
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<PetNewAppointmentDetailsResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<PetNewAppointmentDetailsResponse> call, @NonNull Response<PetNewAppointmentDetailsResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "PetNewAppointmentDetailsResponse" + "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        String vaccinated, addr = null, usrname = null;


                        if (response.body().getData() != null) {

                            String usr_image = response.body().getData().getDoctor_id().getProfile_img();

                            String servname = response.body().getData().getService_name();

                            String servcost = response.body().getData().getService_amount();

                            String pet_name = response.body().getData().getPet_id().getPet_name();

                            userid = response.body().getData().getUser_id().get_id();

                            Log.w(TAG, " User_ID "+userid);

                            pet_image = response.body().getData().getPet_id().getPet_img();

                            String pet_type = response.body().getData().getPet_id().getPet_type();

                            String breed = response.body().getData().getPet_id().getPet_breed();

                            String gender = response.body().getData().getPet_id().getPet_gender();

                            String colour = response.body().getData().getPet_id().getPet_color();

                            String weight = String.valueOf(response.body().getData().getPet_id().getPet_weight());

                            String age = String.valueOf(response.body().getData().getPet_id().getPet_age());

                            if(response.body().getData().getBooking_date_time() != null){
                                txt_appointment_date.setText(response.body().getData().getBooking_date_time());
                            }

                            if (response.body().getData().getPet_id().isVaccinated()) {
                                vaccinated = "Yes";
                                ll_petlastvacinateddate.setVisibility(View.VISIBLE);
                                if (response.body().getData().getPet_id().getLast_vaccination_date() != null && !response.body().getData().getPet_id().getLast_vaccination_date().isEmpty()) {
                                    txt_petlastvaccinatedage.setText(": "+response.body().getData().getPet_id().getLast_vaccination_date());
                                }

                            } else {
                                ll_petlastvacinateddate.setVisibility(View.GONE);
                                vaccinated = "No";
                            }

                            String order_date = response.body().getData().getBooking_date();

                            String orderid = response.body().getData().getAppointment_UID();

                            String payment_method = response.body().getData().getPayment_method();

                            String order_cost = response.body().getData().getAmount();

                            List<PetNewAppointmentDetailsResponse.DataBean.DocBusinessInfoBean> Address = response.body().getData().getDoc_business_info();

                            for (int i = 0; i < Address.size(); i++) {

                                addr = Address.get(i).getClinic_loc();

                                usrname = Address.get(i).getDr_name();
                            }

                            appoinment_status = response.body().getData().getAppoinment_status();

                            start_appointment_status = response.body().getData().getStart_appointment_status();

                            setView(usrname, usr_image, servname, servcost, pet_name,pet_image, pet_type, breed

                                    , gender, colour, weight, age, order_date, orderid, payment_method, order_cost, vaccinated, addr);
                        }
                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<PetNewAppointmentDetailsResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG, "PetNewAppointmentDetailsResponse" + "--->" + t.getMessage());
            }
        });

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private PetNewAppointmentDetailsRequest petNewAppointmentDetailsRequest() {

        PetNewAppointmentDetailsRequest petNewAppointmentDetailsRequest = new PetNewAppointmentDetailsRequest();
        petNewAppointmentDetailsRequest.setApppointment_id(appointment_id);
        Log.w(TAG, "petNewAppointmentDetailsRequest" + "--->" + new Gson().toJson(petNewAppointmentDetailsRequest));
        return petNewAppointmentDetailsRequest;
    }

    @SuppressLint("SetTextI18n")
    private void setView(String usrname, String usr_image, String servname, String servcost, String pet_name, List<PetNewAppointmentDetailsResponse.DataBean.PetIdBean.PetImgBean> pet_image, String pet_type, String breed, String gender, String colour, String weight, String age, String order_date, String orderid, String payment_method, String order_cost, String vaccinated, String addr) {


        if(usr_image != null && !usr_image.isEmpty()){
            Glide.with(DoctorNewAppointmentDetailsActivity.this)
                    .load(usr_image)
                    .into(img_user);

        }else{
            Glide.with(DoctorNewAppointmentDetailsActivity.this)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(img_user);
        }


        if(usrname!= null && !usrname.isEmpty()){
            txt_usrname.setText(usrname);
        }




        if(pet_image != null && !pet_image.isEmpty()){

            Glide.with(DoctorNewAppointmentDetailsActivity.this)
                    .load(pet_image)
                    .into(img_petimg);


        }else{
            Glide.with(DoctorNewAppointmentDetailsActivity.this)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(img_user);
        }


        if(pet_name != null && !pet_name.isEmpty()){
            txt_pet_name.setText(": "+pet_name);
        }

        if(pet_type != null && !pet_type.isEmpty()){

            txt_pet_type.setText(": "+pet_type);
        }

        if(breed != null && !breed.isEmpty()){

            txt_breed.setText(": "+breed);
        }

        if(gender != null && !gender.isEmpty()){

            txt_gender.setText(": "+gender);
        }

        if(colour != null && !colour.isEmpty()){

            txt_color.setText(": "+colour);
        }

        if(weight != null && !weight.isEmpty()){

            txt_weight.setText(": "+weight);
        }

        if(age != null && !age.isEmpty()){

            txt_age.setText(": "+age);
        }

        if(vaccinated != null && !vaccinated.isEmpty()){
            txt_vaccinated.setText(": "+vaccinated);
        }

        if(order_date != null && !order_date.isEmpty()){

            txt_order_date.setText(": "+order_date);
        }

        if(orderid != null && !orderid.isEmpty()){

            txt_order_id.setText(": "+orderid);
        }

        if(payment_method != null && !payment_method.isEmpty()) {

            txt_payment_method.setText(": "+payment_method);

        }

        if(order_cost != null && !order_cost.isEmpty()){
            txt_order_cost.setText(": "+"\u20B9 "+order_cost);
            txt_serv_cost.setText("\u20B9 "+order_cost);
        }

        if(addr != null && !addr.isEmpty()){

            txt_address.setText(addr);
        }


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        btn_complete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorNewAppointmentDetailsActivity.this, PrescriptionActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id", appointment_id);
                i.putExtra("petname", pet_name);
                i.putExtra("pettype", pet_type);
                i.putExtra("userid", userid );
                Log.w(TAG, " User_ID "+userid);
                i.putExtra("allergies", allergies);
                i.putExtra("probleminfo", problem_info );
                i.putExtra("doctorid",doctorid);
                Log.w(TAG, "ID-->" + appointment_id);
                startActivity(i);

            }
        });

    img_videocall.setOnClickListener(new View.OnClickListener() {
         @SuppressLint("LongLogTag")
         @Override
         public void onClick(View v) {

             Log.w(TAG, "Start_appointment_status : " + appoinment_status);
             if (appoinment_status != null && appoinment_status.equalsIgnoreCase("Not Started")) {
                 doctorStartAppointmentResponseCall(appointment_id);
             } else {
                 Intent i = new Intent(DoctorNewAppointmentDetailsActivity.this, VideoCallDoctorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 i.putExtra("id", appointment_id);
                 i.putExtra("petname", pet_name);
                 i.putExtra("pettype", pet_type);
                 i.putExtra("userid", userid );
                 Log.w(TAG, " User_ID "+userid);
                 i.putExtra("allergies", allergies);
                 i.putExtra("probleminfo", problem_info );
                 Log.w(TAG, "ID-->" + appointment_id);
                 startActivity(i);


             }
         }
     });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showStatusAlert(appointment_id,"Doctor");
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void showStatusAlert(String id, String appointmenttype) {
        try {
            dialog = new Dialog(DoctorNewAppointmentDetailsActivity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = (TextView)dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.cancelappointment);
            Button dialogButtonApprove = (Button) dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = (Button) dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    //if(appointmenttype != null && appointmenttype.equalsIgnoreCase("Doctor")){
                    appoinmentCancelledResponseCall(id);
                    // } else if(appointmenttype != null && appointmenttype.equalsIgnoreCase("SP")){
                    //spappoinmentCancelledResponseCall(id);
                    //}



                }
            });
            dialogButtonRejected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toasty.info(context, "Rejected Successfully", Toast.LENGTH_SHORT, true).show();
                    dialog.dismiss();




                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }


    }

    @SuppressLint("LongLogTag")
    private void doctorStartAppointmentResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppointmentsUpdateResponse> call = apiInterface.doctorStartAppointmentResponseCall(RestUtils.getContentType(), doctorStartAppointmentRequest(id));
        Log.w(TAG,"startAppointmentResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppointmentsUpdateResponse>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Response<AppointmentsUpdateResponse> response) {

                Log.w(TAG,"startAppointmentResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Intent i = new Intent(DoctorNewAppointmentDetailsActivity.this, VideoCallDoctorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("id", appointment_id);
                        i.putExtra("petname", pet_name);
                        i.putExtra("pettype", pet_type);
                        i.putExtra("userid", userid );
                        i.putExtra("allergies", allergies);
                        i.putExtra("probleminfo", problem_info );
                        Log.w(TAG, "ID-->" + appointment_id);
                        startActivity(i);

                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"startAppointmentResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private DoctorStartAppointmentRequest doctorStartAppointmentRequest(String id) {
        /*
         * _id : 5fc639ea72fc42044bfa1683
         * appoinment_status : In-Progress
         */
        DoctorStartAppointmentRequest doctorStartAppointmentRequest = new DoctorStartAppointmentRequest();
        doctorStartAppointmentRequest.set_id(id);
        doctorStartAppointmentRequest.setStart_appointment_status("In-Progress");
        Log.w(TAG,"doctorStartAppointmentRequest"+ "--->" + new Gson().toJson(doctorStartAppointmentRequest));
        return doctorStartAppointmentRequest;
    }

    @SuppressLint("LongLogTag")
    private void appoinmentCancelledResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppoinmentCancelledResponse> call = apiInterface.appoinmentCancelledResponseCall(RestUtils.getContentType(), appoinmentCancelledRequest(id));
        Log.w(TAG,"appoinmentCancelledResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppoinmentCancelledResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Response<AppoinmentCancelledResponse> response) {

                Log.w(TAG,"appoinmentCancelledResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        startActivity(new Intent(DoctorNewAppointmentDetailsActivity.this, PetMyappointmentsActivity.class));





                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"appoinmentCancelledResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private AppoinmentCancelledRequest appoinmentCancelledRequest(String id) {

        /*
         * _id : 5fc639ea72fc42044bfa1683
         * missed_at : 23-10-2000 10 : 00 AM
         * doc_feedback : One Emergenecy work i am cancelling this appointment
         * appoinment_status : Missed
         */


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        AppoinmentCancelledRequest appoinmentCancelledRequest = new AppoinmentCancelledRequest();
        appoinmentCancelledRequest.set_id(id);
        appoinmentCancelledRequest.setMissed_at(currentDateandTime);
        appoinmentCancelledRequest.setDoc_feedback("");
        appoinmentCancelledRequest.setAppoint_patient_st("Doctor Cancelled appointment");
        appoinmentCancelledRequest.setAppoinment_status("Missed");
        Log.w(TAG,"appoinmentCancelledRequest"+ "--->" + new Gson().toJson(appoinmentCancelledRequest));
        return appoinmentCancelledRequest;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void compareDatesandTime(String currentDateandTime, String bookingDateandTime) {
        try{

            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");

            String str1 = currentDateandTime;
            Date currentDate = formatter.parse(str1);

            String str2 = bookingDateandTime;
            Date responseDate = formatter.parse(str2);

            Log.w(TAG,"compareDatesandTime--->"+"responseDate :"+responseDate+" "+"currentDate :"+currentDate);

            if (currentDate.compareTo(responseDate)<0 || responseDate.compareTo(currentDate) == 0)
            {
                Log.w(TAG,"date is equal");
                isVaildDate = true;

            }else{
                Log.w(TAG,"date is not equal");
                isVaildDate = false;
            }



        }catch (ParseException e1){
            e1.printStackTrace();
        }
    }


    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), DoctorDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

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
}