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
import com.salveo.mysalveo.requestpojo.CouponCodeCheckRequest;
import com.salveo.mysalveo.requestpojo.NotificationSendRequest;
import com.salveo.mysalveo.requestpojo.SPCreateAppointmentRequest;
import com.salveo.mysalveo.responsepojo.CouponCodeCheckResponse;
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

public class PetLoverServiceChoosePaymentMethodActivity extends AppCompatActivity implements PaymentResultListener {

    public static String TAG = "PetLoverServiceChoosePaymentMethodActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_servicename)
    TextView txt_servicename;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_business_name)
    TextView txt_business_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_name)
    TextView txt_pet_name;


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

    ArrayList<SPCreateAppointmentRequest> SPCreateAppointmentRequestList = new ArrayList<>();

    private String sp_id;
    private String booking_date;
    private String booking_time;
    private String booking_date_time;
    private String user_id;
    private String pet_id;
    private String additional_info;
    private String sp_feedback;
    private String sp_rate;
    private String user_feedback;
    private String user_rate;
    private String display_date;
    private String server_date_time;
    private String payment_id;
    private String payment_method;
    private String service_name;
    private String service_amount;
    private String service_time;
    private String completed_at;
    private String missed_at;
    private String mobile_type;
    private List<SPCreateAppointmentRequest.SpAttchedBean> sp_attched;
    private String date_and_time;
    private String health_issue_title;

    private String Coupon_code = "";
    private String Coupon_status = "Not Applied";
    private int Original_price = 0;
    private int Discount_price = 0;
    private int Total_price = 0;


    private String doctorname;
    private String clinicname;
    private String petname;

    private String doctorid;

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
    private String userid;
    private Dialog dialog;
    private Dialog alertDialog;


    private String spid;
    private String catid;
    private String from;
    private String spuserid;
    private String selectedServiceTitle;
    private String serviceprovidingcompanyname;
    private int serviceamount;
    private String servicetime;
    private String SP_ava_Date;
    private int distance;

    @SuppressLint({"LogNotTimber", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petlover_service_choose_payment_method);
        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");

        SessionManager sessionManager = new SessionManager(getApplicationContext());
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

            Log.w(TAG, "petId : " + petId);
            doctorname = extras.getString("doctorname");
            clinicname = extras.getString("clinicname");
            petname = extras.getString("petname");
            Log.w(TAG,"Bundle "+" doctorname : "+doctorname+" clinicname : "+clinicname+"petname : "+petname);


            /*PetServiceAppointment_Doctor_Date_Time_Activity*/
            fromactivity = extras.getString("fromactivity");
            spid = extras.getString("spid");
            catid = extras.getString("catid");
            from = extras.getString("from");
            spuserid = extras.getString("spuserid");
            selectedServiceTitle = extras.getString("selectedServiceTitle");
            serviceprovidingcompanyname = extras.getString("serviceprovidingcompanyname");
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
            SP_ava_Date = extras.getString("SP_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            petId = extras.getString("petId");
            distance = extras.getInt("distance");
            Log.w(TAG,"spid : "+spid +" catid : "+catid+" from : "+from+" serviceamount : "+serviceamount+" servicetime : "+servicetime+" SP_ava_Date : "+SP_ava_Date+" selectedTimeSlot : "+selectedTimeSlot);
            Total_price = serviceamount;
            Log.w(TAG,"fromactivity : "+fromactivity+" from : "+from+" petId : "+petId);
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
             //   edt_coupon.setText("");
                txt_total_amount.setText("\u20B9 "+serviceamount);
                Total_price = serviceamount;
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
                //edt_coupon.setText("");
                txt_total_amount.setText("\u20B9 "+serviceamount);
                Total_price = serviceamount;
                Coupon_status = "Not Applied";
                Coupon_code = "";
                Original_price = 0;
                Discount_price = 0;

                // ll_totalamount.setVisibility(View.GONE);
            }

        });



      /*  SPCreateAppointmentRequestList = (ArrayList<SPCreateAppointmentRequest>) getIntent().getSerializableExtra("SPCreateAppointmentRequestList");
        Log.w(TAG,"SPCreateAppointmentRequestList : "+new Gson().toJson(SPCreateAppointmentRequestList));

        if(SPCreateAppointmentRequestList != null && SPCreateAppointmentRequestList.size()>0) {
            for (int i = 0; i < SPCreateAppointmentRequestList.size(); i++) {

                *//*
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
                 * sp_business_info : []
                 * health_issue_title
                 * original_price : 100
                 * discount_price : 10
                 * total_price : 90
                 * coupon_status : String,
                 *  coupon_code : String,
                 *//*
                sp_id = SPCreateAppointmentRequestList.get(i).getSp_id();
                booking_date = SPCreateAppointmentRequestList.get(i).getBooking_date();
                booking_time = SPCreateAppointmentRequestList.get(i).getBooking_time();
                booking_date_time = SPCreateAppointmentRequestList.get(i).getBooking_date_time();
                user_id = SPCreateAppointmentRequestList.get(i).getUser_id();
                pet_id = SPCreateAppointmentRequestList.get(i).getPet_id();
                additional_info = SPCreateAppointmentRequestList.get(i).getAdditional_info();
                sp_attched = SPCreateAppointmentRequestList.get(i).getSp_attched();
                sp_feedback = SPCreateAppointmentRequestList.get(i).getSp_feedback();
                sp_rate = SPCreateAppointmentRequestList.get(i).getSp_rate();
                user_feedback = SPCreateAppointmentRequestList.get(i).getUser_feedback();
                user_rate = SPCreateAppointmentRequestList.get(i).getUser_rate();
                display_date = SPCreateAppointmentRequestList.get(i).getDisplay_date();
                server_date_time = SPCreateAppointmentRequestList.get(i).getServer_date_time();
                service_name = SPCreateAppointmentRequestList.get(i).getService_name();
                service_amount = SPCreateAppointmentRequestList.get(i).getService_amount();
                service_time = SPCreateAppointmentRequestList.get(i).getService_time();
                completed_at = SPCreateAppointmentRequestList.get(i).getCompleted_at();
                missed_at = SPCreateAppointmentRequestList.get(i).getMissed_at();
                health_issue_title = SPCreateAppointmentRequestList.get(i).getHealth_issue_title();

            }


        }

*/
        if(selectedServiceTitle != null){
            txt_servicename.setText(selectedServiceTitle);
        }
        if(serviceprovidingcompanyname != null){
            txt_business_name.setText(serviceprovidingcompanyname);
        }
        if(petname != null){
            txt_pet_name.setText(petname);
        }
        if(serviceamount != 0){
            txt_cost.setText("\u20B9 "+serviceamount);
            txt_total_amount.setText("\u20B9 "+serviceamount);
            Total_price = serviceamount;
        }
        if(SP_ava_Date != null){
            txt_booked_date.setText(SP_ava_Date+" "+selectedTimeSlot);
        }


      /*  btn_apply_coupon.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btn_apply_coupon.getText().toString().equalsIgnoreCase("Remove")){
                    edt_coupon.setText("");
                    btn_apply_coupon.setText("Apply");
                    viewapply.setVisibility(View.GONE);
                    ll_cost.setVisibility(View.GONE);
                    ll_discount.setVisibility(View.GONE);
                    txt_total_amount.setText("\u20B9 "+serviceamount);
                    Coupon_status = "Not Applied";
                    Coupon_code = "";
                    Original_price = 0;
                    Discount_price = 0;
                    Total_price = serviceamount;

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
                   // spCreateAppointmentRequest();

                }
                else {
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        spCreateAppointmentResponseCall();
                        //spCreateAppointmentRequest();

                    }

                }
            }else{

                if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                    spCreateAppointmentResponseCall();
                    //spCreateAppointmentRequest();

                }
            }






        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),SelectYourPetActivity.class);
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
        /*
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
        couponCodeCheckRequest.setTotal_amount(serviceamount);
        couponCodeCheckRequest.setCoupon_type("2");
        couponCodeCheckRequest.setUser_id(userid);
    //    couponCodeCheckRequest.setCode(edt_coupon.getText().toString());
        Log.w(TAG,"couponCodeCheckRequest"+ "--->" + new Gson().toJson(couponCodeCheckRequest));
        return couponCodeCheckRequest;
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        //totalamount = amount;

      /*  Double d = new Double(amount);
        int amout = d.intValue();*/


        Integer totalamout = Total_price*100;

        try {
            JSONObject options = new JSONObject();

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

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Payment_id = razorpayPaymentID;

            Log.w(TAG, "Payment Successful: " + razorpayPaymentID);
            Toasty.success(getApplicationContext(), "Payment Successful. View your booking details in upcoming appointments.", Toast.LENGTH_SHORT, true).show();


            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                spCreateAppointmentResponseCall();
                //spCreateAppointmentRequest();

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
         * sp_business_info : []
         * health_issue_title
         * original_price : 100
         * discount_price : 10
         * total_price : 90
         * coupon_status : String,
         *  coupon_code : String,
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
        spCreateAppointmentRequest.setOriginal_price(Original_price);
        spCreateAppointmentRequest.setDiscount_price(Discount_price);
        spCreateAppointmentRequest.setTotal_price(Total_price);
        spCreateAppointmentRequest.setCoupon_status(Coupon_status);
        spCreateAppointmentRequest.setCoupon_code(Coupon_code);
        Log.w(TAG,"spCreateAppointmentRequest"+ "--->" + new Gson().toJson(spCreateAppointmentRequest));
        return spCreateAppointmentRequest;
    }


    @SuppressLint("LogNotTimber")
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

            dialog = new Dialog(PetLoverServiceChoosePaymentMethodActivity.this);
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