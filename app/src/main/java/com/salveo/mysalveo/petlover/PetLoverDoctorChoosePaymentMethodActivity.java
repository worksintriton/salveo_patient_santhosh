package com.salveo.mysalveo.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.fragmentpetlover.bottommenu.PetHomeNewFragment;
import com.salveo.mysalveo.requestpojo.CouponCodeCheckRequest;
import com.salveo.mysalveo.requestpojo.NotificationSendRequest;
import com.salveo.mysalveo.requestpojo.PetAppointmentCreateRequest;
import com.salveo.mysalveo.responsepojo.CouponCodeCheckResponse;
import com.salveo.mysalveo.responsepojo.NotificationSendResponse;
import com.salveo.mysalveo.responsepojo.PetAppointmentCreateResponse;
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

public class PetLoverDoctorChoosePaymentMethodActivity extends AppCompatActivity implements PaymentResultListener {

    public static String TAG = "PetLoverDoctorChoosePaymentMethodActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_clinicname)
    TextView txt_clinicname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_doctorname)
    TextView txt_doctorname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_name)
    TextView txt_pet_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_appointment_type)
    TextView txt_appointment_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cost)
    TextView txt_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_booked_date)
    TextView txt_booked_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdGroupPayment)
    RadioGroup rdGroupPayment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_content_amount)
    LinearLayout ll_content_amount;

/*
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_coupon)
    EditText edt_coupon;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_apply_coupon)
    Button btn_apply_coupon;
*/

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_serv_cost)
    TextView txt_serv_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_discount_amount)
    TextView txt_discount_amount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_amount)
    TextView txt_total_amount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.viewapply)
    View viewapply;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.viewTotalamout)
    View viewTotalamout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_cost)
    LinearLayout ll_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_discount)
    LinearLayout ll_discount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_totalamount)
    LinearLayout ll_totalamount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_bookappointment)
    Button btn_bookappointment;

    ArrayList<PetAppointmentCreateRequest> PetAppointmentCreateRequestList = new ArrayList<>();
    private String Doctor_id;
    private String Booking_date;
    private List<PetAppointmentCreateRequest.DocAttchedBean> Doc_attched;
    private String Booking_time;
    private String Booking_date_time;
    private String Communication_type;
    private String User_id;
    private String Pet_id;
    private String Problem_info;
    private String Display_date;
    private String Appointment_types;
    private String Allergies;
    private int Amount;
    private String Date_and_time;
    private String Visit_type;
    private String Location_id;
    private String Health_issue_title;

    private String doctorname;
    private String clinicname;
    private String petname;

    private String doctorid;

    private String selectedAppointmentType = "Normal";
    private String selectedPaymentMethod = "Online";
    private String selectedVisitType = "";
    private String petId;
    private String fromactivity;
    private String fromto;
    private String Payment_id = "";

    private String Doctor_ava_Date = "";
    private String selectedTimeSlot = "";

    private int amount;
    private String communicationtype = "";
    private String health_issue_title;
    private String userid;
    private Dialog dialog;
    private Dialog alertDialog;
    private String Coupon_code = "";
    private String Coupon_status = "Not Applied";
    private int Original_price = 0;
    private int Discount_price = 0;
    private int Total_price = 0;
    private String selectedCommunicationtype;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdonline)
    RadioButton rdonline;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdcod)
    RadioButton rdcod;
    private SessionManager sessionManager;

    @SuppressLint({"LogNotTimber", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petlover_doctor_choose_payment_method);
        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.appointment));

        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
        img_notification.setVisibility(View.GONE);
        img_profile.setVisibility(View.GONE);
        avi_indicator.setVisibility(View.GONE);

        viewapply.setVisibility(View.GONE);
        //viewTotalamout.setVisibility(View.GONE);
        ll_cost.setVisibility(View.GONE);
        ll_discount.setVisibility(View.GONE);
       // ll_totalamount.setVisibility(View.GONE);


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {


            doctorid = extras.getString("doctorid");
            fromactivity = extras.getString("fromactivity");
            fromto = extras.getString("fromto");
            Doctor_ava_Date = extras.getString("Doctor_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            amount = extras.getInt("amount");
            Total_price = amount;
            Log.w(TAG,"amount : "+amount);
            communicationtype = extras.getString("communicationtype");
            selectedVisitType = extras.getString("selectedVisitType");
            petId = extras.getString("petId");
            health_issue_title = extras.getString("health_issue_title");
            Log.w(TAG,"Bundle "+" doctorid : "+doctorid+" selectedTimeSlot : "+selectedTimeSlot+"communicationtype : "+communicationtype+" amount : "+amount+" fromactivity : "+fromactivity);

            Log.w(TAG, "petId : " + petId);

            doctorname = extras.getString("doctorname");
            clinicname = extras.getString("clinicname");
            petname = extras.getString("petname");


            Log.w(TAG,"Bundle "+" doctorname : "+doctorname+" clinicname : "+clinicname+"petname : "+petname);
        }

        if(selectedCommunicationtype != null && selectedCommunicationtype.equalsIgnoreCase("Online")){
            rdcod.setVisibility(View.GONE);
        }else{
            rdcod.setVisibility(View.VISIBLE);
        }



        rdGroupPayment.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = rdGroupPayment.getCheckedRadioButtonId();
            RadioButton radioButton = rdGroupPayment.findViewById(radioButtonID);
            selectedPaymentMethod = (String) radioButton.getText();
            Log.w(TAG, "selectedPaymentMethod : " + selectedPaymentMethod);

            if(selectedPaymentMethod != null && selectedPaymentMethod.equalsIgnoreCase("Cash")){
                ll_content_amount.setVisibility(View.VISIBLE);
                viewapply.setVisibility(View.GONE);
               // viewTotalamout.setVisibility(View.GONE);
                ll_cost.setVisibility(View.GONE);
                ll_discount.setVisibility(View.GONE);
           //     edt_coupon.setText("");
                txt_total_amount.setText("\u20B9 "+Amount);
                Total_price = Amount;
                Coupon_status = "Not Applied";
                Coupon_code = "";
                Original_price = 0;
                Discount_price = 0;

               // ll_totalamount.setVisibility(View.GONE);
            }else{
                ll_content_amount.setVisibility(View.VISIBLE);
                viewapply.setVisibility(View.GONE);
               // viewTotalamout.setVisibility(View.GONE);
                ll_cost.setVisibility(View.GONE);
                ll_discount.setVisibility(View.GONE);
             //   edt_coupon.setText("");
                txt_total_amount.setText("\u20B9 "+Amount);
                Total_price = Amount;
                Coupon_status = "Not Applied";
                Coupon_code = "";
                Original_price = 0;
                Discount_price = 0;

               // ll_totalamount.setVisibility(View.GONE);
            }

        });



        PetAppointmentCreateRequestList = (ArrayList<PetAppointmentCreateRequest>) getIntent().getSerializableExtra("PetAppointmentCreateRequestList");
        Log.w(TAG,"PetAppointmentCreateRequestList : "+new Gson().toJson(PetAppointmentCreateRequestList));

        if(PetAppointmentCreateRequestList != null && PetAppointmentCreateRequestList.size()>0) {
            for (int i = 0; i < PetAppointmentCreateRequestList.size(); i++) {
                Doctor_id =  PetAppointmentCreateRequestList.get(i).getDoctor_id();
                Booking_date =  PetAppointmentCreateRequestList.get(i).getBooking_date();
                Booking_time =  PetAppointmentCreateRequestList.get(i).getBooking_time();
                Booking_date_time =  PetAppointmentCreateRequestList.get(i).getBooking_date_time();
                Communication_type =  PetAppointmentCreateRequestList.get(i).getCommunication_type();
                User_id =  PetAppointmentCreateRequestList.get(i).getUser_id();
                Pet_id =  PetAppointmentCreateRequestList.get(i).getPatient_id();
                Problem_info =  PetAppointmentCreateRequestList.get(i).getProblem_info();
                Doc_attched =  PetAppointmentCreateRequestList.get(i).getDoc_attched();
                Display_date =  PetAppointmentCreateRequestList.get(i).getDisplay_date();
                Appointment_types =  PetAppointmentCreateRequestList.get(i).getAppointment_types();
                Allergies =  PetAppointmentCreateRequestList.get(i).getAllergies();
                Amount =  PetAppointmentCreateRequestList.get(i).getAmount();
                Date_and_time =  PetAppointmentCreateRequestList.get(i).getDate_and_time();
                Visit_type =  PetAppointmentCreateRequestList.get(i).getVisit_type();
                Location_id =  PetAppointmentCreateRequestList.get(i).getLocation_id();
                Health_issue_title =  PetAppointmentCreateRequestList.get(i).getHealth_issue_title();
            }
            Log.w(TAG,"doctorid : "+Doctor_id);


        }


        if(doctorname != null){
            txt_doctorname.setText(doctorname);
        }
        if(clinicname != null){
            txt_clinicname.setText(clinicname);
        }
        if(petname != null){
            txt_pet_name.setText(petname);
        }
        if(Amount != 0){
            txt_cost.setText("\u20B9 "+Amount);
            txt_total_amount.setText("\u20B9 "+Amount);
            Total_price = Amount;
        }
        if(Booking_date_time != null){
            txt_booked_date.setText(Booking_date_time);
        }
        if(Appointment_types != null){
            txt_appointment_type.setText(Appointment_types);
        }

     /*   btn_apply_coupon.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btn_apply_coupon.getText().toString().equalsIgnoreCase("Remove")){
                    edt_coupon.setText("");
                    btn_apply_coupon.setText("Apply");
                    viewapply.setVisibility(View.GONE);
                    ll_cost.setVisibility(View.GONE);
                    ll_discount.setVisibility(View.GONE);
                    txt_total_amount.setText("\u20B9 "+Amount);
                    Coupon_status = "Not Applied";
                    Coupon_code = "";
                    Original_price = 0;
                    Discount_price = 0;
                    Total_price = Amount;

                }else if(btn_apply_coupon.getText().toString().equalsIgnoreCase("Apply")){
                    if(edt_coupon.getText().toString().isEmpty()){
                        edt_coupon.setError("Please enter the coupon code");
                        edt_coupon.requestFocus();
                    }else {
                        Coupon_status = "Applied";
                        Coupon_code = edt_coupon.getText().toString();
                        CouponCodeCheckResponseCall();
                    }


                }


            }
        });*/

        btn_bookappointment.setOnClickListener(v -> {

            Log.w(TAG,"btn_bookappointment amount : "+amount+"selectedPaymentMethod : "+selectedPaymentMethod);

            if(selectedPaymentMethod != null && selectedPaymentMethod.equalsIgnoreCase("Online")){
                if(Total_price != 0){
                   startPayment();

                }
                else {
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        petAppointmentCreateResponseCall();

                    }

                }
            }else{

                if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                       petAppointmentCreateResponseCall();

                }
            }






        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),BookAppointmentActivity.class);
        intent.putExtra("doctorid",doctorid);
        intent.putExtra("fromactivity",fromactivity);
        intent.putExtra("fromto",fromto);
        intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
        intent.putExtra("selectedTimeSlot", selectedTimeSlot);
        intent.putExtra("amount", amount);
        intent.putExtra("communicationtype", communicationtype);
        intent.putExtra("selectedVisitType", selectedVisitType);
        intent.putExtra("petId", petId);
        intent.putExtra("health_issue_title", health_issue_title);
        intent.putExtra("doctorname", doctorname);
        intent.putExtra("clinicname", clinicname);
        intent.putExtra("petname", petname);
        startActivity(intent);
    }


    @SuppressLint("LogNotTimber")
    private void CouponCodeCheckResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<CouponCodeCheckResponse> call = ApiService.CouponCodeCheckResponseCall(RestUtils.getContentType(),couponCodeCheckRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<CouponCodeCheckResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<CouponCodeCheckResponse> call, @NonNull Response<CouponCodeCheckResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"notificationMarkResponseCall SuccessResponse"+ "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                      //  btn_apply_coupon.setText("Remove");
                        viewapply.setVisibility(View.VISIBLE);
                        ll_cost.setVisibility(View.VISIBLE);
                        ll_discount.setVisibility(View.VISIBLE);
                        viewTotalamout.setVisibility(View.VISIBLE);
                        ll_totalamount.setVisibility(View.VISIBLE);

                        if(response.body().getData().getDiscount_price() != 0){
                            Discount_price = response.body().getData().getDiscount_price();
                            txt_discount_amount.setText("\u20B9 "+response.body().getData().getDiscount_price());
                        }else{
                            txt_discount_amount.setText("\u20B9 "+0);
                        }
                        if(response.body().getData().getOriginal_price() != 0){
                            Original_price = response.body().getData().getOriginal_price();
                            txt_serv_cost.setText("\u20B9 "+response.body().getData().getOriginal_price());

                        }else{
                            txt_serv_cost.setText("\u20B9 "+0);

                        }

                        if(response.body().getData().getTotal_price() != 0){
                            Total_price = response.body().getData().getTotal_price();
                            txt_total_amount.setText("\u20B9 "+response.body().getData().getTotal_price());

                        }else{
                            txt_total_amount.setText("\u20B9 "+0);
                        }


                    }else{
                        showErrorLoading(response.body().getMessage());
                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<CouponCodeCheckResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"CouponCodeCheckResponse SuccessResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private CouponCodeCheckRequest couponCodeCheckRequest() {
        /**
         * current_date : 10/09/2021
         * total_amount : 10000
         * coupon_type : 2
         * user_id :
         * code : TRITON123
         */

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        String currentDate = sdf.format(new Date());

        CouponCodeCheckRequest couponCodeCheckRequest = new CouponCodeCheckRequest();
        couponCodeCheckRequest.setCurrent_date(currentDate);
        couponCodeCheckRequest.setTotal_amount(Amount);
        couponCodeCheckRequest.setCoupon_type("1");
        couponCodeCheckRequest.setUser_id(User_id);
       // couponCodeCheckRequest.setCode(edt_coupon.getText().toString());
        Log.w(TAG,"couponCodeCheckRequest"+ "--->" + new Gson().toJson(couponCodeCheckRequest));
        return couponCodeCheckRequest;
    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
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




        double percentage = 0;
        double percentageamount = 0;
        double totalamout = 0 ;
        double grandtotal =0;

        try{
            percentage = Double.parseDouble(PetHomeNewFragment.percentage);
            Log.w(TAG,"percentage : "+percentage);
            percentageamount = (Total_price*(percentage/100));
            Log.w(TAG,"percentageamount : "+percentageamount);
            totalamout = Total_price+percentageamount;
            Log.w(TAG,"totalamout : "+totalamout);
            grandtotal = totalamout * 100;
            Log.w(TAG,"grandtotal : "+grandtotal);
            Total_price = (int) totalamout;
            Log.w(TAG,"Total_price : "+Total_price);


        }catch(NumberFormatException ignored){
        }

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Salveo Health Care LLP");
            options.put("description", userid);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", grandtotal);


            co.open(activity, options);
        } catch (Exception e) {
            Log.w(TAG,"Error in payment: " + e.getMessage());

            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Payment_id = razorpayPaymentID;

            Log.w(TAG, "Payment Successful: " + razorpayPaymentID);
            Toasty.success(getApplicationContext(), "Payment Successful. View your booking details in upcoming appointments.", Toast.LENGTH_SHORT, true).show();


            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                petAppointmentCreateResponseCall();

            }




        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentSuccess", e);
        }
    }
    @SuppressLint("LogNotTimber")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                notificationSendResponseCall();
            }
            Log.w(TAG,  "Payment failed: " + code + " " + response);
            Toasty.error(getApplicationContext(), "Payment failed. Please try again with another payment method..", Toast.LENGTH_SHORT, true).show();

        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentError", e);
        }
    }


    @SuppressLint("LogNotTimber")
    private void petAppointmentCreateResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PetAppointmentCreateResponse> call = ApiService.petAppointmentCreateResponseCall(RestUtils.getContentType(),petAppointmentCreateRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<PetAppointmentCreateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<PetAppointmentCreateResponse> call, @NonNull Response<PetAppointmentCreateResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"PetDoctorAvailableTimeResponse"+ "--->" + new Gson().toJson(response.body()));


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

            @Override
            public void onFailure(@NonNull Call<PetAppointmentCreateResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"PetDoctorAvailableTimeResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private PetAppointmentCreateRequest petAppointmentCreateRequest() {

        /*
         * doctor_id : 5fb62a1924583828f10f8731
         * booking_date : 19/11/2020
         * booking_time : 12:22 pm
         * booking_date_time : 19/11/2020 12:22 pm
         * communication_type :
         * video_id : http://vidoe.com
         * user_id : 5fb6162a211fce241eaf53a9
         * pet_id : 5fb38ea334f6014ea9013d30
         * problem_info : problem info
         * doc_attched : [{"file":"http://google.pdf"}]
         * doc_feedback : doc feedback
         * doc_rate : 5
         * user_feedback : user feedback
         * user_rate : 4.5
         * display_date : 19/11/2020 01:00 PM
         * server_date_time : 09/12/2020 03:00 PM
         * payment_id : 1234567890
         * payment_method : Card
         * appointment_types : Normal
         * allergies : this is
         * amount : 400
         * location_id,
         * visit_type
         * original_price : 100
         * discount_price : 10
         * total_price : 90
         * coupon_status : String,
         *  coupon_code : String,
         *  */
        List<PetAppointmentCreateRequest.DocAttchedBean> doc_attched = new ArrayList<>();


        @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = inputFormat.parse(Doctor_ava_Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        String outputTimeStr = null;

        @SuppressLint("SimpleDateFormat") SimpleDateFormat h_mm_a   = new SimpleDateFormat("hh:mm aa");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat hh_mm_ss = new SimpleDateFormat("HH:mm:ss");

        try {
            Date d1 = h_mm_a.parse(selectedTimeSlot);
            outputTimeStr =hh_mm_ss.format(d1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String displaydateandtime = outputDateStr+" "+outputTimeStr;

        if(userid != null){
            userid = userid;
        }else{
            userid = "";
        }

        PetAppointmentCreateRequest petAppointmentCreateRequest = new PetAppointmentCreateRequest();
        petAppointmentCreateRequest.setDoctor_id(doctorid);
        petAppointmentCreateRequest.setBooking_date(Doctor_ava_Date);
        petAppointmentCreateRequest.setBooking_time(selectedTimeSlot);
        petAppointmentCreateRequest.setBooking_date_time(Doctor_ava_Date+" "+selectedTimeSlot);
        petAppointmentCreateRequest.setCommunication_type(Communication_type);
        petAppointmentCreateRequest.setVideo_id("");
        petAppointmentCreateRequest.setUser_id(userid);
        petAppointmentCreateRequest.setPatient_id(petId);
        petAppointmentCreateRequest.setProblem_info(Problem_info);
        petAppointmentCreateRequest.setDoc_attched(doc_attched);
        petAppointmentCreateRequest.setDoc_feedback("");
        petAppointmentCreateRequest.setDoc_rate(0);
        petAppointmentCreateRequest.setUser_feedback("");
        petAppointmentCreateRequest.setUser_rate(0);
        petAppointmentCreateRequest.setDisplay_date(displaydateandtime);
        petAppointmentCreateRequest.setServer_date_time("");
        petAppointmentCreateRequest.setPayment_id(Payment_id);
        petAppointmentCreateRequest.setPayment_method(selectedPaymentMethod);
        petAppointmentCreateRequest.setAppointment_types(selectedAppointmentType);
        petAppointmentCreateRequest.setAllergies(Allergies);
        petAppointmentCreateRequest.setAmount(amount);
        petAppointmentCreateRequest.setMobile_type("Android");
        petAppointmentCreateRequest.setService_name("");
        petAppointmentCreateRequest.setService_amount("");
        petAppointmentCreateRequest.setDate_and_time(Date_and_time);
        petAppointmentCreateRequest.setVisit_type(selectedVisitType);
        petAppointmentCreateRequest.setLocation_id(Location_id);
        petAppointmentCreateRequest.setHealth_issue_title(health_issue_title);
        petAppointmentCreateRequest.setOriginal_price(Original_price);
        petAppointmentCreateRequest.setDiscount_price(Discount_price);
        petAppointmentCreateRequest.setTotal_price(Total_price);
        petAppointmentCreateRequest.setCoupon_status(Coupon_status);
        petAppointmentCreateRequest.setCoupon_code(Coupon_code);
        Log.w(TAG,"petAppointmentCreateRequest : "+new Gson().toJson(petAppointmentCreateRequest));

        return petAppointmentCreateRequest;

    }

    private void notificationSendResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<NotificationSendResponse> call = ApiService.notificationSendResponseCall(RestUtils.getContentType(),notificationSendRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());
        call.enqueue(new Callback<NotificationSendResponse>() {
            @SuppressLint("LogNotTimber")
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
    @SuppressLint("LogNotTimber")
    private NotificationSendRequest notificationSendRequest() {

        /*
         * status : Payment Failed
         * date : 23-10-2020 11:00 AM
         * appointment_UID :
         * user_id : 601b8ac3204c595ee52582f2
         * doctor_id :
         */

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime = simpleDateFormat.format(new Date());
        NotificationSendRequest notificationSendRequest = new NotificationSendRequest();
        notificationSendRequest.setStatus("Payment Failed");
        notificationSendRequest.setDate(currentDateandTime);
        notificationSendRequest.setAppointment_UID("");
        notificationSendRequest.setUser_id(userid);
        notificationSendRequest.setDoctor_id(doctorid);
        Log.w(TAG,"notificationSendRequest"+ "--->" + new Gson().toJson(notificationSendRequest));
        return notificationSendRequest;
    }

    private void showPaymentSuccessalert(String message) {
        try {

            dialog = new Dialog(PetLoverDoctorChoosePaymentMethodActivity.this);
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


}