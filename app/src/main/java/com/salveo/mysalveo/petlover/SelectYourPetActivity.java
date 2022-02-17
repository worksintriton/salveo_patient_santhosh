package com.salveo.mysalveo.petlover;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.MyFamilyListAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.MyPetsSelectListener;
import com.salveo.mysalveo.requestpojo.FamilyMemberListRequest;
import com.salveo.mysalveo.requestpojo.SPCreateAppointmentRequest;
import com.salveo.mysalveo.requestpojo.SPNotificationSendRequest;
import com.salveo.mysalveo.responsepojo.FamilyMemberListResponse;
import com.salveo.mysalveo.responsepojo.NotificationSendResponse;
import com.salveo.mysalveo.responsepojo.SPCreateAppointmentResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
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

public class SelectYourPetActivity extends AppCompatActivity implements View.OnClickListener, MyPetsSelectListener, PaymentResultListener {

    private static final String TAG = "SelectYourPetActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_family)
    RecyclerView rv_family;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_name)
    TextView txt_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_save_continue)
    LinearLayout ll_save_continue;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_add_new_pet)
    LinearLayout ll_add_new_pet;

    private String userid;
    private List<FamilyMemberListResponse.DataBean> dataBeanList;


    private String selectedAppointmentType = "Normal";
    private String selectedVisitType = "";
    private String petId;
    private String doctorid;
    private String fromactivity;
    private String fromto;
    private String Payment_id = "";
    private String Doctor_ava_Date = "";
    private String selectedTimeSlot = "";
    private int amount;
    private String communicationtype = "";


    private String spid,catid,from;
    private String spuserid;
    private String selectedServiceTitle,serviceprovidingcompanyname;
    private String petcolor;
    private double petweight;
    private String servicetime;
    private int serviceamount;
    private String petage;
    private int distance;
    private String SP_ava_Date,petname;
    private Dialog alertDialog;
    private Dialog dialog;
    private SessionManager sessionManager;


    @SuppressLint({"LogNotTimber", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_your_pet);
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);
        ll_save_continue.setVisibility(View.GONE);


        sessionManager = new SessionManager(SelectYourPetActivity.this);
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        String name = user.get(SessionManager.KEY_FIRST_NAME);

        txt_name.setText("Hey "+name+", ");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            doctorid = extras.getString("doctorid");
            fromactivity = extras.getString("fromactivity");
            fromto = extras.getString("fromto");
            Doctor_ava_Date = extras.getString("Doctor_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            serviceprovidingcompanyname = extras.getString("serviceprovidingcompanyname");
            amount = extras.getInt("amount");
            Log.w(TAG,"amount : "+amount);
            communicationtype = extras.getString("communicationtype");
            Log.w(TAG,"Bundle "+" doctorid : "+doctorid+" selectedTimeSlot : "+selectedTimeSlot+"communicationtype : "+communicationtype+" amount : "+amount);


            /*PetServiceAppointment_Doctor_Date_Time_Activity*/
            fromactivity = extras.getString("fromactivity");
            spid = extras.getString("spid");
            catid = extras.getString("catid");
            from = extras.getString("from");
            spuserid = extras.getString("spuserid");
            selectedServiceTitle = extras.getString("selectedServiceTitle");
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
            SP_ava_Date = extras.getString("SP_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            distance = extras.getInt("distance");
            Log.w(TAG,"spid : "+spid +" catid : "+catid+" from : "+from+" serviceamount : "+serviceamount+" servicetime : "+servicetime+" SP_ava_Date : "+SP_ava_Date+" selectedTimeSlot : "+selectedTimeSlot);

            Log.w(TAG,"fromactivity : "+fromactivity+" from : "+from);

        }
        img_back.setOnClickListener(this);
        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            familymembersListResponseCall();
        }

        ll_save_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spCreateAppointmentRequest1();

                /*if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
                    Intent intent = new Intent(getApplicationContext(),ServiceBookAppointmentActivity.class);
                    intent.putExtra("spid",spid);
                    intent.putExtra("catid",catid);
                    intent.putExtra("from",from);
                    intent.putExtra("spuserid",spuserid);
                    intent.putExtra("selectedServiceTitle",selectedServiceTitle);
                    intent.putExtra("serviceamount",serviceamount);
                    intent.putExtra("servicetime",servicetime);
                    intent.putExtra("SP_ava_Date",SP_ava_Date);
                    intent.putExtra("selectedTimeSlot",selectedTimeSlot);
                    intent.putExtra("distance",distance);
                    intent.putExtra("fromactivity",fromactivity);
                    intent.putExtra("petId", petId);
                    startActivity(intent);
                    Log.w(TAG, "petId : " + petId);
                }
                else {
                    Intent intent = new Intent(SelectYourPetActivity.this, ConsultationIssuesActivity.class);
                    intent.putExtra("doctorid", doctorid);
                    intent.putExtra("fromactivity", fromactivity);
                    intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
                    intent.putExtra("selectedTimeSlot", selectedTimeSlot);
                    intent.putExtra("amount", amount);
                    intent.putExtra("communicationtype", communicationtype);
                    intent.putExtra("fromto", fromto);
                    intent.putExtra("petId", petId);
                    startActivity(intent);
                    Log.w(TAG, "petId : " + petId);
                }*/
            }
        });

        ll_add_new_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
                    Intent intent = new Intent(getApplicationContext(),AddNewFamilyMembersActivity.class);
                    intent.putExtra("spid",spid);
                    intent.putExtra("catid",catid);
                    intent.putExtra("from",from);
                    intent.putExtra("spuserid",spuserid);
                    intent.putExtra("selectedServiceTitle",selectedServiceTitle);
                    intent.putExtra("serviceamount",serviceamount);
                    intent.putExtra("servicetime",servicetime);
                    intent.putExtra("SP_ava_Date",SP_ava_Date);
                    intent.putExtra("selectedTimeSlot",selectedTimeSlot);
                    intent.putExtra("serviceprovidingcompanyname",serviceprovidingcompanyname);
                    intent.putExtra("petname",petname);
                    intent.putExtra("distance",distance);
                    intent.putExtra("fromactivity",fromactivity);
                    intent.putExtra("TAGG",TAG);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(SelectYourPetActivity.this, AddNewFamilyMembersActivity.class);
                    intent.putExtra("doctorid", doctorid);
                    intent.putExtra("fromactivity", TAG);
                    intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
                    intent.putExtra("selectedTimeSlot", selectedTimeSlot);
                    intent.putExtra("amount", amount);
                    intent.putExtra("communicationtype", communicationtype);
                    intent.putExtra("fromto", TAG);
                    intent.putExtra("petId", petId);
                    intent.putExtra("TAGG",TAG);
                    intent.putExtra("serviceprovidingcompanyname",serviceprovidingcompanyname);
                    intent.putExtra("petname",petname);
                    startActivity(intent);
                    Log.w(TAG, "communicationtype : " + communicationtype);
                }

            }
        });




    }

    private SPCreateAppointmentRequest spCreateAppointmentRequest1() {
        /*
         * sp_id : 5ff7ef9b1c72093650a13a10
         * booking_date : 23/10/2020
         * booking_time : 12:00 AM
         * booking_date_time : 23/10/2020 12:00 AM
         * user_id : 5fd841a67aa4cc1c6a1e5636
         * pet_id : 5fdc46be1e5d8b0eb31c3699
         * additional_info : this if is for the comments
         * sp_attched : []
         * sp_feedback :
         * sp_rate :
         * user_feedback :
         * user_rate :
         * display_date : 23/10/2020 10:10 AM
         * server_date_time : 23/10/2020 10:10 AM
         * payment_id : 12345
         * payment_method : Card
         * service_name : Grooming
         * service_amount : 200
         * service_time : 15 mins
         * completed_at :
         * missed_at :
         * mobile_type : Admin
         */



        @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = inputFormat.parse(SP_ava_Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        String outputTimeStr = null;

        @SuppressLint("SimpleDateFormat") SimpleDateFormat h_mm_a   = new SimpleDateFormat("hh:mm aa");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat hh_mm_ss = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        try {
            Date d1 = h_mm_a.parse(selectedTimeSlot);
            outputTimeStr =hh_mm_ss.format(d1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String displaydateandtime = outputDateStr+" "+outputTimeStr;

        List<SPCreateAppointmentRequest.SpAttchedBean> sp_attched = new ArrayList<>();


        SPCreateAppointmentRequest spCreateAppointmentRequest = new SPCreateAppointmentRequest();
        spCreateAppointmentRequest.setSp_id(spuserid);
        spCreateAppointmentRequest.setBooking_date(SP_ava_Date);
        spCreateAppointmentRequest.setBooking_time(selectedTimeSlot);
        spCreateAppointmentRequest.setBooking_date_time(SP_ava_Date+" "+selectedTimeSlot);
        spCreateAppointmentRequest.setUser_id(userid);
        spCreateAppointmentRequest.setPatient_id(petId);
        spCreateAppointmentRequest.setAdditional_info("");
        spCreateAppointmentRequest.setSp_attched(sp_attched);
        spCreateAppointmentRequest.setSp_feedback("");
        spCreateAppointmentRequest.setSp_rate("");
        spCreateAppointmentRequest.setUser_feedback("");
        spCreateAppointmentRequest.setUser_rate("0");
        spCreateAppointmentRequest.setDisplay_date(displaydateandtime);
        spCreateAppointmentRequest.setServer_date_time("");
        spCreateAppointmentRequest.setPayment_id(Payment_id);
        spCreateAppointmentRequest.setPayment_method("Online");
        spCreateAppointmentRequest.setService_name(selectedServiceTitle);
        spCreateAppointmentRequest.setService_amount(String.valueOf(serviceamount));
        spCreateAppointmentRequest.setService_time(servicetime);
        spCreateAppointmentRequest.setCompleted_at("");
        spCreateAppointmentRequest.setMissed_at("");
        spCreateAppointmentRequest.setMobile_type("Android");
        spCreateAppointmentRequest.setDate_and_time(currentDateandTime);
        spCreateAppointmentRequest.setHealth_issue_title("");
        Log.w(TAG,"spCreateAppointmentRequest"+ "--->" + new Gson().toJson(spCreateAppointmentRequest));

        /*ArrayList<SPCreateAppointmentRequest> SPCreateAppointmentRequestList = new ArrayList<>();
        SPCreateAppointmentRequestList.add(spCreateAppointmentRequest);*/


        Intent intent = new Intent(getApplicationContext(),PetLoverServiceChoosePaymentMethodActivity.class);
        //  intent.putExtra("SPCreateAppointmentRequestList",SPCreateAppointmentRequestList);
        intent.putExtra("spid",spid);
        intent.putExtra("catid",catid);
        intent.putExtra("from",from);
        intent.putExtra("spuserid",spuserid);
        intent.putExtra("selectedServiceTitle",selectedServiceTitle);
        intent.putExtra("serviceprovidingcompanyname",serviceprovidingcompanyname);
        intent.putExtra("serviceamount",serviceamount);
        intent.putExtra("servicetime",servicetime);
        intent.putExtra("SP_ava_Date",SP_ava_Date);
        intent.putExtra("selectedTimeSlot",selectedTimeSlot);
        intent.putExtra("distance",distance);
        intent.putExtra("fromactivity",fromactivity);
        intent.putExtra("petId", petId);
        intent.putExtra("petname",petname);
        startActivity(intent);
        Log.w(TAG, "petId : " + petId);
        return spCreateAppointmentRequest;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
            Intent intent = new Intent(getApplicationContext(),PetServiceAppointment_Doctor_Date_Time_Activity.class);
            intent.putExtra("spid",spid);
            intent.putExtra("catid",catid);
            intent.putExtra("from",from);
            intent.putExtra("spuserid",spuserid);
            intent.putExtra("selectedServiceTitle",selectedServiceTitle);
            intent.putExtra("serviceamount",serviceamount);
            intent.putExtra("servicetime",servicetime);
            intent.putExtra("SP_ava_Date",SP_ava_Date);
            intent.putExtra("selectedTimeSlot",selectedTimeSlot);
            intent.putExtra("distance",distance);
            intent.putExtra("fromactivity",TAG);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(),PetServiceAppointment_Doctor_Date_Time_Activity.class);
            intent.putExtra("spid",spid);
            intent.putExtra("catid",catid);
            intent.putExtra("from",from);
            intent.putExtra("spuserid",spuserid);
            intent.putExtra("selectedServiceTitle",selectedServiceTitle);
            intent.putExtra("serviceamount",serviceamount);
            intent.putExtra("servicetime",servicetime);
            intent.putExtra("SP_ava_Date",SP_ava_Date);
            intent.putExtra("selectedTimeSlot",selectedTimeSlot);
            intent.putExtra("distance",distance);
            intent.putExtra("fromactivity",TAG);
            startActivity(intent);
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;


        }
    }

    private void familymembersListResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FamilyMemberListResponse> call = apiInterface.familymembersListResponseCall(RestUtils.getContentType(),FamilyMemberListRequest());
        Log.w(TAG,"FamilyMemberListResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<FamilyMemberListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<FamilyMemberListResponse> call, @NonNull Response<FamilyMemberListResponse> response) {

                Log.w(TAG,"FamilyMemberListResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            txt_no_records.setVisibility(View.GONE);
                            rv_family.setVisibility(View.VISIBLE);
                            dataBeanList = response.body().getData();
                            setView();

                        }
                        else{
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText(getResources().getString(R.string.no_new_fam_memb));
                            rv_family.setVisibility(View.GONE);
                        }



                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<FamilyMemberListResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"FamilyMemberListResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    private FamilyMemberListRequest FamilyMemberListRequest() {
        FamilyMemberListRequest FamilyMemberListRequest = new FamilyMemberListRequest();
        FamilyMemberListRequest.setUser_id(userid);
        Log.w(TAG,"FamilyMemberListRequest"+ "--->" + new Gson().toJson(FamilyMemberListRequest));
        return FamilyMemberListRequest;
    }
    private void setView() {
        rv_family.setLayoutManager(new GridLayoutManager(this,2));

        //  rv_family.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_family.setItemAnimator(new DefaultItemAnimator());
        MyFamilyListAdapter myPetsListAdapter = new MyFamilyListAdapter(getApplicationContext(), dataBeanList, this);
        rv_family.setAdapter(myPetsListAdapter);

    }


    @SuppressLint("LogNotTimber")
    @Override
    public void myPetsSelectListener(String petid, String name) {
        Log.w(TAG,"myPetsSelectListener : petid "+petid);
        if(petid != null){
            petId = petid;
            petname = name;
            ll_save_continue.setVisibility(View.VISIBLE);
        }else{
            ll_save_continue.setVisibility(View.GONE);
        }

    }



    @SuppressLint("LongLogTag")
    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();
        HashMap<String, String> sessionRazorpayDetails = sessionManager.getRazorpayDetails();
        String rzpayapikey = sessionRazorpayDetails.get(SessionManager.KEY_RAZORPAY_APIKEY);
        Log.w(TAG,"startPayment rzpayapikey : " + rzpayapikey);
        // set your id as below
        co.setKeyID(rzpayapikey);

        //totalamount = amount;

      /*  Double d = new Double(amount);
        int amout = d.intValue();*/


        Integer totalamout = serviceamount*100;

        try {
            JSONObject options = new JSONObject();
          options.put("name", "Salveo Health Care LLP");
            options.put("description", userid);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", totalamout);


            co.open(activity, options);
        } catch (Exception e) {
            Log.w(TAG,"Error in payment: " + e.getMessage());

            e.printStackTrace();
        }
    }
    @SuppressLint("LongLogTag")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Payment_id = razorpayPaymentID;

            Log.w(TAG, "Payment Successful: " + razorpayPaymentID);
            Toasty.success(getApplicationContext(), "Payment Successful. View your booking details in upcoming appointments.", Toast.LENGTH_SHORT, true).show();


            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                spCreateAppointmentResponseCall();

            }




        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentSuccess", e);
        }
    }
    @SuppressLint("LongLogTag")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            if(new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                spnotificationSendResponseCall();
            }
            Log.w(TAG,  "Payment failed: " + code + " " + response);
            Toasty.error(getApplicationContext(), "Payment failed. Please try again with another payment method..", Toast.LENGTH_SHORT, true).show();

        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentError", e);
        }
    }


    @SuppressLint("LongLogTag")
    private void spCreateAppointmentResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SPCreateAppointmentResponse> call = ApiService.SPCreateAppointmentResponseCall(RestUtils.getContentType(),spCreateAppointmentRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SPCreateAppointmentResponse>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NonNull Call<SPCreateAppointmentResponse> call, @NonNull Response<SPCreateAppointmentResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPCreateAppointmentResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getMessage() != null){
                            showPaymentSuccessalert(response.body().getMessage());

                            // showSuceessLoading(response.body().getMessage());
                        }
                    }
                    else{
                        if(response.body().getMessage() != null){
                            showErrorLoading(response.body().getMessage());

                        }


                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<SPCreateAppointmentResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"SPCreateAppointmentResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private SPCreateAppointmentRequest spCreateAppointmentRequest() {
        /*
         * sp_id : 5ff7ef9b1c72093650a13a10
         * booking_date : 23/10/2020
         * booking_time : 12:00 AM
         * booking_date_time : 23/10/2020 12:00 AM
         * user_id : 5fd841a67aa4cc1c6a1e5636
         * pet_id : 5fdc46be1e5d8b0eb31c3699
         * additional_info : this if is for the comments
         * sp_attched : []
         * sp_feedback :
         * sp_rate :
         * user_feedback :
         * user_rate :
         * display_date : 23/10/2020 10:10 AM
         * server_date_time : 23/10/2020 10:10 AM
         * payment_id : 12345
         * payment_method : Card
         * service_name : Grooming
         * service_amount : 200
         * service_time : 15 mins
         * completed_at :
         * missed_at :
         * mobile_type : Admin
         */



        @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = inputFormat.parse(SP_ava_Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        String outputTimeStr = null;

        @SuppressLint("SimpleDateFormat") SimpleDateFormat h_mm_a   = new SimpleDateFormat("hh:mm aa");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat hh_mm_ss = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        try {
            Date d1 = h_mm_a.parse(selectedTimeSlot);
            outputTimeStr =hh_mm_ss.format(d1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String displaydateandtime = outputDateStr+" "+outputTimeStr;

        List<SPCreateAppointmentRequest.SpAttchedBean> sp_attched = new ArrayList<>();


        SPCreateAppointmentRequest spCreateAppointmentRequest = new SPCreateAppointmentRequest();
        spCreateAppointmentRequest.setSp_id(spuserid);
        spCreateAppointmentRequest.setBooking_date(SP_ava_Date);
        spCreateAppointmentRequest.setBooking_time(selectedTimeSlot);
        spCreateAppointmentRequest.setBooking_date_time(SP_ava_Date+" "+selectedTimeSlot);
        spCreateAppointmentRequest.setUser_id(userid);
        spCreateAppointmentRequest.setPatient_id(petId);
        spCreateAppointmentRequest.setAdditional_info("");
        spCreateAppointmentRequest.setSp_attched(sp_attched);
        spCreateAppointmentRequest.setSp_feedback("");
        spCreateAppointmentRequest.setSp_rate("");
        spCreateAppointmentRequest.setUser_feedback("");
        spCreateAppointmentRequest.setUser_rate("0");
        spCreateAppointmentRequest.setDisplay_date(displaydateandtime);
        spCreateAppointmentRequest.setServer_date_time("");
        spCreateAppointmentRequest.setPayment_id(Payment_id);
        spCreateAppointmentRequest.setPayment_method("Online");
        spCreateAppointmentRequest.setService_name(selectedServiceTitle);
        spCreateAppointmentRequest.setService_amount(String.valueOf(serviceamount));
        spCreateAppointmentRequest.setService_time(servicetime);
        spCreateAppointmentRequest.setCompleted_at("");
        spCreateAppointmentRequest.setMissed_at("");
        spCreateAppointmentRequest.setMobile_type("Android");
        spCreateAppointmentRequest.setDate_and_time(currentDateandTime);
        spCreateAppointmentRequest.setHealth_issue_title("");
        Log.w(TAG,"spCreateAppointmentRequest"+ "--->" + new Gson().toJson(spCreateAppointmentRequest));
        return spCreateAppointmentRequest;
    }


    public void showErrorLoading(String errormesage) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void hideLoading() {
        try {
            alertDialog.dismiss();
        } catch (Exception ignored) {

        }
    }


    public void showSuceessLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoadingSuccess());



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });
    }
    private void showPaymentSuccessalert(String message) {
        try {

            dialog = new Dialog(SelectYourPetActivity.this);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_appointment_payment_success_layout);
            TextView txt_success_msg = dialog.findViewById(R.id.txt_success_msg);
            txt_success_msg.setText(message);
            Button btn_ok = dialog.findViewById(R.id.btn_ok);

            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
                    startActivity(intent);
                    finish();



                }
            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }

    public void hideLoadingSuccess() {
        try {
            Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
            startActivity(intent);
            finish();
            alertDialog.dismiss();

        } catch (Exception ignored) {

        }
    }

    @SuppressLint("LongLogTag")
    private void spnotificationSendResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<NotificationSendResponse> call = ApiService.spnotificationSendResponseCall(RestUtils.getContentType(),spNotificationSendRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<NotificationSendResponse>() {
            @Override
            public void onResponse(@NonNull Call<NotificationSendResponse> call, @NonNull Response<NotificationSendResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"notificationSendResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<NotificationSendResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"NotificationSendResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LongLogTag")
    private SPNotificationSendRequest spNotificationSendRequest() {

        /**
         * status : Payment Failed
         * date : 23-10-2020 11:00 AM
         * appointment_UID :
         * user_id : 601b8ac3204c595ee52582f2
         * doctor_id :
         */

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime = simpleDateFormat.format(new Date());

        SPNotificationSendRequest spNotificationSendRequest = new SPNotificationSendRequest();
        spNotificationSendRequest.setStatus("Payment Failed");
        spNotificationSendRequest.setDate(currentDateandTime);
        spNotificationSendRequest.setAppointment_UID("");
        spNotificationSendRequest.setUser_id(userid);
        spNotificationSendRequest.setSp_id(spid);


        Log.w(TAG,"spNotificationSendRequest"+ "--->" + new Gson().toJson(spNotificationSendRequest));
        return spNotificationSendRequest;
    }
}