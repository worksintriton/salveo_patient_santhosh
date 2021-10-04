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
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorAppointmentDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "DoctorAppointmentDetailsActivity";


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_user)
    ImageView img_user;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_usrname)
    TextView txt_usrname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_serv_name)
    TextView txt_serv_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_serv_cost)
    TextView txt_serv_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_complete)
    Button btn_complete;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_prescriptiondetails)
    Button btn_prescriptiondetails;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_petimg)
    ImageView img_petimg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_name)
    TextView txt_pet_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_type)
    TextView txt_pet_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_breed)
    TextView txt_breed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_gender)
    TextView txt_gender;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_color)
    TextView txt_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_weight)
    TextView txt_weight;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_age)
    TextView txt_age;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_vaccinated)
    TextView txt_vaccinated;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_date)
    TextView txt_order_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_booking_id)
    TextView txt_booking_id;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_payment_method)
    TextView txt_payment_method;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_cost)
    TextView txt_order_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_address)
    TextView txt_address;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_videocall)
    ImageView img_videocall;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_petlastvacinateddate)
    LinearLayout ll_petlastvacinateddate;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_petlastvaccinatedage)
    TextView txt_petlastvaccinatedage;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_appointment_date)
    TextView txt_appointment_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_doctor_footer)
    View include_doctor_footer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_home_address)
    LinearLayout ll_home_address;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_home_address)
    TextView txt_home_address;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.scrollablContent)
    ScrollView scrollablContent;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_visit_type)
    TextView txt_visit_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_doctor_header)
    View include_doctor_header;


    String appointment_id;



    String appoinment_status;

    String start_appointment_status;

    String userid, allergies,problem_info,pet_name,pet_type,doctorid;

    private Dialog dialog;


    private String bookedat;
    private boolean isVaildDate;

    private String from;
    private List<PetNewAppointmentDetailsResponse.DataBean.PetIdBean.PetImgBean> pet_image;
    private String petAgeandMonth;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_emergency_appointment)
    ImageView img_emergency_appointment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_allergies)
    LinearLayout ll_allergies;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_allergies)
    TextView txt_allergies;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_comments)
    LinearLayout ll_comments;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_comments)
    TextView txt_comments;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_diagnosis)
    LinearLayout ll_diagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_diagnosis)
    TextView txt_diagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_sub_diagnosis)
    LinearLayout ll_sub_diagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_sub_diagnosis)
    TextView txt_sub_diagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_doctor_comment)
    LinearLayout ll_doctor_comment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_doctor_comment)
    TextView txt_doctor_comment;

    String breed,gender,colour,weight,pet_dob ;


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


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment_details);

        ButterKnife.bind(this);
        scrollablContent.setVisibility(View.GONE);
        img_emergency_appointment.setVisibility(View.GONE);

        avi_indicator.setVisibility(View.GONE);
        txt_serv_name.setVisibility(View.GONE);
        ll_home_address.setVisibility(View.GONE);

        ll_allergies.setVisibility(View.GONE);
        ll_comments.setVisibility(View.GONE);

        ll_diagnosis.setVisibility(View.GONE);
        ll_sub_diagnosis.setVisibility(View.GONE);
        ll_doctor_comment.setVisibility(View.GONE);
        txt_diagnosis.setVisibility(View.GONE);
        txt_sub_diagnosis.setVisibility(View.GONE);
        txt_doctor_comment.setVisibility(View.GONE);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appointment_id = extras.getString("appointment_id");
            bookedat = extras.getString("bookedat");
            from = extras.getString("from");
            Log.w(TAG,"from : "+from);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        if(bookedat != null){
            compareDatesandTime(currentDateandTime,bookedat);
        }


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

        ImageView img_back = include_doctor_header.findViewById(R.id.img_back);
        ImageView img_notification = include_doctor_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_doctor_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_doctor_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_doctor_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.appointment));

        img_notification.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
        img_profile.setVisibility(View.GONE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });






        btn_prescriptiondetails.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DoctorPrescriptionDetailsActivity.class);
            intent.putExtra("id",appointment_id);
            startActivity(intent);
        });





        

        if (new ConnectionDetector(DoctorAppointmentDetailsActivity.this).isNetworkAvailable(DoctorAppointmentDetailsActivity.this)) {
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
                        scrollablContent.setVisibility(View.VISIBLE);


                        String vaccinated, addr = null, usrname = null;
                        if (response.body().getData() != null) {

                            if(response.body().getData().getAppointment_types() != null && response.body().getData().getAppointment_types().equalsIgnoreCase("Emergency")){
                                img_emergency_appointment.setVisibility(View.VISIBLE);
                            }else{
                                img_emergency_appointment.setVisibility(View.GONE);

                            }

                            if(response.body().getData().getVisit_type() != null && !response.body().getData().getVisit_type().isEmpty() ){
                                txt_visit_type.setText(response.body().getData().getVisit_type());
                            }else{
                                txt_visit_type.setText(response.body().getData().getCommunication_type());
                            }

                            if(from != null && from.equalsIgnoreCase("DoctorNewAppointmentAdapter")){
                                btn_prescriptiondetails.setVisibility(View.GONE);
                                btn_cancel.setVisibility(View.VISIBLE);
                                btn_complete.setVisibility(View.VISIBLE);
                                if(isVaildDate){
                                    btn_cancel.setVisibility(View.VISIBLE);
                                }else{
                                    btn_cancel.setVisibility(View.GONE);
                                }

                                if(response.body().getData().getCommunication_type() != null && response.body().getData().getCommunication_type().equalsIgnoreCase("Online")){
                                    img_videocall.setVisibility(View.VISIBLE);
                                }else{
                                    img_videocall.setVisibility(View.GONE);
                                }

                            }
                            else if(from != null && from.equalsIgnoreCase("DoctorCompletedAppointmentAdapter")){
                                btn_prescriptiondetails.setVisibility(View.VISIBLE);
                                btn_cancel.setVisibility(View.GONE);
                                btn_complete.setVisibility(View.GONE);
                                img_videocall.setVisibility(View.GONE);

                            }
                            else{
                                btn_prescriptiondetails.setVisibility(View.GONE);
                                btn_cancel.setVisibility(View.GONE);
                                btn_complete.setVisibility(View.GONE);
                                img_videocall.setVisibility(View.GONE);

                            }



                            String usr_image = null;
                          //  String usr_image = response.body().getData().getDoctor_id().getProfile_img();
                            if(response.body().getData().getPet_id() != null){
                                 pet_name = response.body().getData().getPet_id().getPet_name();
                                pet_image = response.body().getData().getPet_id().getPet_img();
                                 pet_type = response.body().getData().getPet_id().getPet_type();

                                 breed = response.body().getData().getPet_id().getPet_breed();

                                 gender = response.body().getData().getPet_id().getPet_gender();

                                 colour = response.body().getData().getPet_id().getPet_color();

                                 weight = String.valueOf(response.body().getData().getPet_id().getPet_weight());

                                 pet_dob = response.body().getData().getPet_id().getPet_dob();

                            }





                            if(pet_dob != null){
                                String[] separated = pet_dob.split("-");
                                String day = separated[0];
                                String month = separated[1];
                                String year = separated[2];
                                Log.w(TAG,"day : "+day+" month: "+month+" year : "+year);

                                getAge(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
                            }


                            if(response.body().getData().getBooking_date_time() != null){
                                txt_appointment_date.setText(response.body().getData().getBooking_date_time());
                            }

                            if (response.body().getData().getPet_id().isVaccinated()) {
                                vaccinated = "Yes";
                                ll_petlastvacinateddate.setVisibility(View.VISIBLE);
                                if (response.body().getData().getPet_id().getLast_vaccination_date() != null && !response.body().getData().getPet_id().getLast_vaccination_date().isEmpty()) {
                                    txt_petlastvaccinatedage.setText(response.body().getData().getPet_id().getLast_vaccination_date());
                                }
                            }
                            else {
                                ll_petlastvacinateddate.setVisibility(View.GONE);
                                vaccinated = "No";
                            }

                            String order_date = response.body().getData().getDate_and_time();

                            String orderid = response.body().getData().getAppointment_UID();

                            String payment_method = response.body().getData().getPayment_method();

                            String order_cost = response.body().getData().getAmount();

                            List<PetNewAppointmentDetailsResponse.DataBean.DocBusinessInfoBean> Address = response.body().getData().getDoc_business_info();

                            for (int i = 0; i < Address.size(); i++) {

                                addr = Address.get(i).getClinic_loc();

                                usrname = Address.get(i).getDr_name();
                                usr_image = Address.get(i).getThumbnail_image();
                            }
                            appoinment_status = response.body().getData().getAppoinment_status();
                            start_appointment_status = response.body().getData().getStart_appointment_status();
                            setView(usrname, usr_image, pet_name, pet_type, breed

                                    , gender, colour, weight, order_date, orderid, payment_method, order_cost, vaccinated, addr);

                            if(response.body().getData().getVisit_type() != null &&response.body().getData().getVisit_type().equalsIgnoreCase("Home")){
                                ll_home_address.setVisibility(View.VISIBLE);
                                if(response.body().getAddress() != null){
                                    if(response.body().getAddress().getLocation_address() != null){
                                        txt_home_address.setText(response.body().getAddress().getLocation_address());
                                    }
                                }
                            }
                            else{
                                ll_home_address.setVisibility(View.GONE);
                            }

                            if(response.body().getData().getAllergies() != null && !response.body().getData().getAllergies().isEmpty() ){
                                ll_allergies.setVisibility(View.VISIBLE);
                                txt_allergies.setText(response.body().getData().getAllergies());
                            }
                            else{
                                ll_allergies.setVisibility(View.GONE);
                            }
                            if(response.body().getData().getProblem_info() != null && !response.body().getData().getProblem_info().isEmpty() ){
                                ll_comments.setVisibility(View.VISIBLE);
                                txt_comments.setText(response.body().getData().getProblem_info());
                            }
                            else{
                                ll_comments.setVisibility(View.GONE);
                            }


                            if(from != null && from.equalsIgnoreCase("DoctorCompletedAppointmentAdapter")){
                                if(response.body().getData().getDiagnosis() != null && !response.body().getData().getDiagnosis().isEmpty()){
                                    ll_diagnosis.setVisibility(View.VISIBLE);
                                    txt_diagnosis.setVisibility(View.VISIBLE);
                                    txt_diagnosis.setText(response.body().getData().getDiagnosis());

                                }
                                if(response.body().getData().getSub_diagnosis() != null && !response.body().getData().getSub_diagnosis().isEmpty()){
                                    ll_sub_diagnosis.setVisibility(View.VISIBLE);
                                    txt_sub_diagnosis.setVisibility(View.VISIBLE);
                                    txt_sub_diagnosis.setText(response.body().getData().getSub_diagnosis());
                                }
                                if(response.body().getData().getDoctor_comment() != null && !response.body().getData().getDoctor_comment().isEmpty()){
                                    ll_doctor_comment.setVisibility(View.VISIBLE);
                                    txt_doctor_comment.setVisibility(View.VISIBLE);
                                    txt_doctor_comment.setText(response.body().getData().getDoctor_comment());

                                }






                            }
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

    @SuppressLint({"SetTextI18n", "LongLogTag", "LogNotTimber"})
    private void setView(String usrname, String usr_image, String pet_name, String pet_type, String breed, String gender, String colour, String weight, String order_date, String orderid, String payment_method, String order_cost, String vaccinated, String addr) {
        if(usr_image != null && !usr_image.isEmpty()){
            Glide.with(DoctorAppointmentDetailsActivity.this)
                    .load(usr_image)
                    .into(img_user);

        }else{
            Glide.with(DoctorAppointmentDetailsActivity.this)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(img_user);
        }


        if(usrname!= null && !usrname.isEmpty()){
            txt_usrname.setText(usrname);
        }




        if(pet_image != null && pet_image.size()>0){
            String petimage = null;
            for(int i=0;i<pet_image.size();i++){
                petimage = pet_image.get(i).getPet_img();
            }

            Glide.with(DoctorAppointmentDetailsActivity.this)
                    .load(petimage)
                    .into(img_petimg);
        }else{
            Glide.with(DoctorAppointmentDetailsActivity.this)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(img_petimg);
        }


        if(pet_name != null && !pet_name.isEmpty()){
            txt_pet_name.setText(pet_name);
        }

        if(pet_type != null && !pet_type.isEmpty()){

            txt_pet_type.setText(pet_type);
        }

        if(breed != null && !breed.isEmpty()){

            txt_breed.setText(breed);
        }

        if(gender != null && !gender.isEmpty()){

            txt_gender.setText(gender);
        }

        if(colour != null && !colour.isEmpty()){

            txt_color.setText(colour);
        }

        if(weight != null && !weight.isEmpty()){

            txt_weight.setText(weight);
        }

        if(petAgeandMonth != null && !petAgeandMonth.isEmpty()){

            txt_age.setText(petAgeandMonth);
        }

        if(vaccinated != null && !vaccinated.isEmpty()){
            txt_vaccinated.setText(vaccinated);
        }

        if(order_date != null && !order_date.isEmpty()){

            txt_order_date.setText(order_date);
        }

        if(orderid != null && !orderid.isEmpty()){

            txt_booking_id.setText(orderid);
        }

        if(payment_method != null && !payment_method.isEmpty()) {

            txt_payment_method.setText(payment_method);

        }

        if(order_cost != null && !order_cost.isEmpty()){
            txt_order_cost.setText("\u20B9 "+order_cost);
            txt_serv_cost.setText("\u20B9 "+order_cost);
        }

        if(addr != null && !addr.isEmpty()){

            txt_address.setText(addr);
        }



        btn_complete.setOnClickListener(v -> {
            Intent i = new Intent(DoctorAppointmentDetailsActivity.this, PrescriptionActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("id", appointment_id);
            i.putExtra("petname", pet_name);
            i.putExtra("pettype", pet_type);
            i.putExtra("userid", userid );
            i.putExtra("allergies", allergies);
            i.putExtra("probleminfo", problem_info );
            i.putExtra("doctorid",doctorid);
            Log.w(TAG, "ID-->" + appointment_id);
            startActivity(i);

        });

    img_videocall.setOnClickListener(v -> {

        Log.w(TAG, "Start_appointment_status : " + start_appointment_status);
        if (start_appointment_status != null && start_appointment_status.equalsIgnoreCase("Not Started")) {
            doctorStartAppointmentResponseCall(appointment_id);
        } else {
            Intent i = new Intent(DoctorAppointmentDetailsActivity.this, VideoCallDoctorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("id", appointment_id);
            i.putExtra("petname", pet_name);
            i.putExtra("pettype", pet_type);
            i.putExtra("userid", userid );
            i.putExtra("allergies", allergies);
            i.putExtra("probleminfo", problem_info );
            Log.w(TAG, "ID-->" + appointment_id);
            startActivity(i);


        }
    });
       btn_cancel.setOnClickListener(v -> showStatusAlert(appointment_id));

    }

    @SuppressLint("SetTextI18n")
    private void showStatusAlert(String id) {
        try {
            dialog = new Dialog(DoctorAppointmentDetailsActivity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = (TextView)dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.cancelappointment);
            Button dialogButtonApprove = (Button) dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = (Button) dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(view -> {
                dialog.dismiss();
                appoinmentCancelledResponseCall(id);
               });
            dialogButtonRejected.setOnClickListener(view -> dialog.dismiss());
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }


    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void doctorStartAppointmentResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppointmentsUpdateResponse> call = apiInterface.doctorStartAppointmentResponseCall(RestUtils.getContentType(), doctorStartAppointmentRequest(id));
        Log.w(TAG,"startAppointmentResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppointmentsUpdateResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Response<AppointmentsUpdateResponse> response) {

                Log.w(TAG,"startAppointmentResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Intent i = new Intent(DoctorAppointmentDetailsActivity.this, VideoCallDoctorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"startAppointmentResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
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

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void appoinmentCancelledResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppoinmentCancelledResponse> call = apiInterface.appoinmentCancelledResponseCall(RestUtils.getContentType(), appoinmentCancelledRequest(id));
        Log.w(TAG,"appoinmentCancelledResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppoinmentCancelledResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Response<AppoinmentCancelledResponse> response) {

                Log.w(TAG,"appoinmentCancelledResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        startActivity(new Intent(DoctorAppointmentDetailsActivity.this, DoctorDashboardActivity.class));

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

    @SuppressLint({"LongLogTag", "LogNotTimber"})
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

            Date currentDate = formatter.parse(currentDateandTime);

            Date responseDate = formatter.parse(bookingDateandTime);

            Log.w(TAG,"compareDatesandTime--->"+"responseDate :"+responseDate+" "+"currentDate :"+currentDate);

            if (currentDate != null) {
                if (responseDate != null) {
                    if (currentDate.compareTo(responseDate)<0 || responseDate.compareTo(currentDate) == 0)
                    {
                        Log.w(TAG,"date is equal");
                        isVaildDate = true;

                    }else{
                        Log.w(TAG,"date is not equal");
                        isVaildDate = false;
                    }
                }
            }


        }catch (ParseException e1){
            e1.printStackTrace();
        }
    }

    @SuppressLint("LogNotTimber")
    private void getAge(int year, int month, int day){
        Log.w(TAG,"getAge : year "+year+" month : "+ month+" day : "+day);
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        Log.w(TAG,"age : "+age+" todayyear : "+today.get(Calendar.YEAR)+" dobyear : "+ dob.get(Calendar.YEAR));


        int months = dob.get(Calendar.MONTH) - today.get(Calendar.MONTH);
        int currentmonths = (today.get(Calendar.MONTH))+1;
        Log.w(TAG,"dob months: "+dob.get(Calendar.MONTH)+" currentmonths : "+ currentmonths);

        Log.w(TAG," todayyear : "+today.get(Calendar.YEAR)+" dobyear : "+ dob.get(Calendar.YEAR));

        Log.w(TAG,"Conditions : "+(today.get(Calendar.YEAR) < dob.get(Calendar.YEAR)));
        if(today.get(Calendar.YEAR) < dob.get(Calendar.YEAR)){
            age--;
        }

        Log.w(TAG,"age: "+age+" monthsInt : "+ months);
        String ageS = Integer.toString(age);
        String monthsS = Integer.toString(months);

        Log.w(TAG,"ageS: "+ageS+" months : "+monthsS);

        if(age != 0){
            petAgeandMonth = ageS+" years "+monthsS+" months";
        }else{
            petAgeandMonth = monthsS+" months";

        }



        Log.w(TAG,"ageS: "+ageS+" months : "+monthsS);

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