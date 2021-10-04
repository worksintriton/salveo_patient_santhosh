package com.salveo.mysalveo.petlover;

import android.annotation.SuppressLint;

import android.app.Dialog;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.adapter.PetServiceMyCalendarAvailableAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.OnItemSelectedTime;
import com.salveo.mysalveo.requestpojo.PetDoctorAvailableTimeRequest;

import com.salveo.mysalveo.responsepojo.SPAvailableTimeResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.razorpay.Checkout;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PetServiceAppointment_Doctor_Date_Time_Activity extends AppCompatActivity implements OnItemSelectedTime, View.OnClickListener {

    String TAG = "PetServiceAppointment_Doctor_Date_Time_Activity";

    private Button btn_bookappointment;
    private CheckBox chat, video;







   // CalendarView calendar;

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;


    RadioButton radioButton1,radioButton2;

    RecyclerView rv_doctoravailabeslottime;

    RelativeLayout sub_layer1;



    private String SP_ava_Date = "";
    private String selectedTimeSlot = "";







    View view;
    TextView tvlblavailabletime,tvlbldoctoravailable;





    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.datePickerTimeline)
    DatePickerTimeline datePickerTimeline ;



    private String spid,catid,from;
    private String spuserid;
    private String selectedServiceTitle,serviceprovidingcompanyname;

    private String servicetime;
    private int serviceamount;
    private List<SPAvailableTimeResponse.DataBean> spDateAvailabilityResponseList;
    private List<SPAvailableTimeResponse.DataBean.TimesBean> timesBeanList;

    private static final int REQUEST_PHONE_CALL =1 ;
    private String sosPhonenumber;
    private Dialog dialog;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;
    private int distance;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petappointment_sp_date_time);
        Log.w(TAG,"onCreateView");

        ButterKnife.bind(this);

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.appointment));

        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);

        avi_indicator.setVisibility(View.GONE);

        /*Razorpay init*/
        Checkout.preload(getApplicationContext());



        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        String userid = user.get(SessionManager.KEY_ID);


        Log.w(TAG,"userid :"+ userid);
        rv_doctoravailabeslottime = findViewById(R.id.rv_doctoravailabeslottime);



        img_sos.setOnClickListener(this);
        img_notification.setOnClickListener(this);
        img_cart.setOnClickListener(this);
        img_profile.setOnClickListener(this);





        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            spid = extras.getString("spid");
            catid = extras.getString("catid");
            from = extras.getString("from");
            spuserid = extras.getString("spuserid");
            selectedServiceTitle = extras.getString("selectedServiceTitle");
            serviceprovidingcompanyname = extras.getString("serviceprovidingcompanyname");
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
            distance = extras.getInt("distance");
            Log.w(TAG,"spid : "+spid +" catid : "+catid+" from : "+from);
            Log.w(TAG,"distance : "+distance);
        }


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);

        if (new ConnectionDetector(PetServiceAppointment_Doctor_Date_Time_Activity.this).isNetworkAvailable(PetServiceAppointment_Doctor_Date_Time_Activity.this)) {

            spAvailableTimeResponseCall(formattedDate);
        }


      /*  calendar = findViewById(R.id.calender);
        calendar.setMinDate(System.currentTimeMillis() - 1000);*/

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);

        btn_bookappointment = findViewById(R.id.btn_bookappointment);


        chat = findViewById(R.id.chat);
        video = findViewById(R.id.video);
        view = findViewById(R.id.view);
        tvlblavailabletime = findViewById(R.id.tvlblavailabletime);
        tvlbldoctoravailable = findViewById(R.id.tvlbldoctoravailable);





        sub_layer1 = findViewById(R.id.sub_layer1);
        sub_layer1.setVisibility(View.GONE);

        btn_bookappointment.setOnClickListener(v -> {
            if(selectedTimeSlot != null && !selectedTimeSlot.isEmpty()){

                if (new ConnectionDetector(PetServiceAppointment_Doctor_Date_Time_Activity.this).isNetworkAvailable(PetServiceAppointment_Doctor_Date_Time_Activity.this)) {
                   // appointmentCheckResponseCall();

                    gotoServiceBookAppoinment();
                }
            }else{
                showErrorLoading("Please select time slot ");

            }

        });






        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        // Set a Start date (Default, 1 Jan 1970)
        datePickerTimeline.setInitialDate(year, month, date);

        datePickerTimeline.setDateTextColor(getResources().getColor(R.color.new_gree_color));
        //datePickerTimeline.setDayTextColor(Color.parseColor("#009675"));
        datePickerTimeline.setDayTextColor(getResources().getColor(R.color.new_gree_color));
        datePickerTimeline.setMonthTextColor(getResources().getColor(R.color.new_gree_color));
      // Set a date Selected Listener
        datePickerTimeline.setOnDateSelectedListener(new OnDateSelectedListener() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onDateSelected(int year, int month, int dayOfMonth, int dayOfWeek) {
                // Do Something

                selectedTimeSlot = "";
                String strdayOfMonth;
                String strMonth;
                int month1 =(month + 1);
                if(dayOfMonth == 9 || dayOfMonth <9){
                    strdayOfMonth = "0"+dayOfMonth;
                    Log.w(TAG,"Selected dayOfMonth-->"+strdayOfMonth);
                }else{
                    strdayOfMonth = String.valueOf(dayOfMonth);
                }

                if(month1 == 9 || month1 <9){
                    strMonth = "0"+month1;
                    Log.w(TAG,"Selected month1-->"+strMonth);
                }else{
                    strMonth = String.valueOf(month1);
                }

                String Date = strdayOfMonth + "-" + strMonth + "-" + year;
                Log.w(TAG,"Selected Date-->"+Date);

                if (new ConnectionDetector(PetServiceAppointment_Doctor_Date_Time_Activity.this).isNetworkAvailable(PetServiceAppointment_Doctor_Date_Time_Activity.this)) {
                    spAvailableTimeResponseCall(Date);
                }


            }

            @Override
            public void onDisabledDateSelected(int year, int month, int day, int dayOfWeek, boolean isDisabled) {
                // Do Something
            }
        });

      /*// Disable date
        Date[] dates = {Calendar.getInstance().getTime()};
        datePickerTimeline.deactivateDates(dates);*/




        img_back.setOnClickListener(v -> onBackPressed());




    }









    @SuppressLint("LogNotTimber")
    private void spAvailableTimeResponseCall(String Date) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SPAvailableTimeResponse> call = ApiService.spAvailableTimeResponseCall(RestUtils.getContentType(),petDoctorAvailableTimeRequest(Date));

        Log.w(TAG,"spAvailableTimeResponseCall url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SPAvailableTimeResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SPAvailableTimeResponse> call, @NonNull Response<SPAvailableTimeResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"spAvailableTimeResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getData() != null) {
                            spDateAvailabilityResponseList = response.body().getData();
                        }
                        if(response.body().getData().get(0).getTimes() != null) {
                            timesBeanList = response.body().getData().get(0).getTimes();
                        }
                        Log.w(TAG,"Size"+spDateAvailabilityResponseList.size());
                        if(!response.body().getData().isEmpty()){
                           /* String SP_name = response.body().getData().get(0).getSp_name();
                            String SP_email_id = response.body().getData().get(0).getSp_email_id();*/
                            SP_ava_Date = response.body().getData().get(0).getSp_ava_Date();

                            Log.w(TAG,"doctorDateAvailabilityResponseCall SP_ava_Date: "+SP_ava_Date);
                            sub_layer1.setVisibility(View.VISIBLE);
                            btn_bookappointment.setVisibility(View.VISIBLE);

                            if(spDateAvailabilityResponseList != null && spDateAvailabilityResponseList.size()>0) {
                                setViewAvlDays();

                            }

                            chat.setChecked(false);
                            video.setChecked(false);
                            chat.setVisibility(View.GONE);
                            video.setVisibility(View.GONE);
                            view.setVisibility(View.GONE);
                            tvlblavailabletime.setVisibility(View.VISIBLE);

                            String  doctorChatAvailable = response.body().getData().get(0).getComm_type_chat();
                            String doctorVideoAvailable = response.body().getData().get(0).getComm_type_video();



                            if (doctorChatAvailable.equalsIgnoreCase("Yes")) {
                                chat.setVisibility(View.VISIBLE);
                                chat.setChecked(true);
                                chat.setClickable(false);

                            }
                            if (doctorVideoAvailable.equalsIgnoreCase("Yes")) {
                                video.setVisibility(View.VISIBLE);
                                video.setChecked(true);
                                video.setClickable(false);
                            }
                            if(doctorChatAvailable.equalsIgnoreCase("Yes") && doctorVideoAvailable.equalsIgnoreCase("Yes")){
                                chat.setChecked(false);
                                video.setChecked(false);
                                chat.setClickable(true);
                                video.setClickable(true);
                                view.setVisibility(View.VISIBLE);


                            }

                        }


                    }
                    else{
                        sub_layer1.setVisibility(View.GONE);
                        btn_bookappointment.setVisibility(View.GONE);
                        tvlblavailabletime.setVisibility(View.GONE);
                        tvlbldoctoravailable.setVisibility(View.GONE);
                        rv_doctoravailabeslottime.setVisibility(View.GONE);
                        showErrorLoading(response.body().getMessage());



                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<SPAvailableTimeResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"spAvailableTimeResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private PetDoctorAvailableTimeRequest petDoctorAvailableTimeRequest(String Date) {

        /*
         * Date : 31-11-2020
         * user_id : 1234567890
         * cur_date : 31-11-2020
         * cur_time : 01:00 AM
         * current_time
         */
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sf.format(new Date());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime24hrs = simpleDateFormat.format(new Date());
        String currenttime = currentDateandTime24hrs.substring(currentDateandTime24hrs.indexOf(' ') + 1);
        String currentdate =  currentDateandTime24hrs.substring(0, currentDateandTime24hrs.indexOf(' '));

        PetDoctorAvailableTimeRequest petDoctorAvailableTimeRequest = new PetDoctorAvailableTimeRequest();
        petDoctorAvailableTimeRequest.setUser_id(spuserid);
        petDoctorAvailableTimeRequest.setDate(Date);
        petDoctorAvailableTimeRequest.setCurrent_time(currentDateandTime);
        petDoctorAvailableTimeRequest.setCur_time(currenttime);
        petDoctorAvailableTimeRequest.setCur_date(currentdate);
        Log.w(TAG,"spAvailableTimeResponseRequest"+ "--->" + new Gson().toJson(petDoctorAvailableTimeRequest));
        return petDoctorAvailableTimeRequest;
    }
    private void setViewAvlDays() {
        rv_doctoravailabeslottime.setVisibility(View.VISIBLE);
        rv_doctoravailabeslottime.setLayoutManager(new GridLayoutManager(this, 4));
        rv_doctoravailabeslottime.setItemAnimator(new DefaultItemAnimator());
        PetServiceMyCalendarAvailableAdapter petServiceMyCalendarAvailableAdapter = new PetServiceMyCalendarAvailableAdapter(getApplicationContext(), timesBeanList, rv_doctoravailabeslottime, this);
        rv_doctoravailabeslottime.setAdapter(petServiceMyCalendarAvailableAdapter);







    }




    public void showErrorLoading(String errormesage){
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        try {
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        catch (WindowManager.BadTokenException e) {
            //use a log message
        }

    }
    public void hideLoading(){
        try {
            alertDialog.dismiss();
        }catch (Exception ignored){

        }
    }


    @SuppressLint("LogNotTimber")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),Service_Details_Activity.class);
        intent.putExtra("spid",spid);
        intent.putExtra("catid",catid);
        intent.putExtra("from",from);
        intent.putExtra("spuserid",spuserid);
        intent.putExtra("selectedServiceTitle",selectedServiceTitle);
        intent.putExtra("serviceamount",serviceamount);
        intent.putExtra("servicetime",servicetime);
        intent.putExtra("distance",distance);
        startActivity(intent);
        finish();
        Log.w(TAG,"distance : "+distance);
    }




    @SuppressLint("LogNotTimber")
    @Override
    public void onItemSelectedTime(String selectedTime) {
        Log.w(TAG,"onItemSelectedTime : "+selectedTime);
        selectedTimeSlot = selectedTime;

    }



    @SuppressLint("LogNotTimber")
    private void gotoServiceBookAppoinment(){
      //  Intent intent = new Intent(PetServiceAppointment_Doctor_Date_Time_Activity.this,ServiceBookAppointmentActivity.class);
        Intent intent = new Intent(PetServiceAppointment_Doctor_Date_Time_Activity.this,SelectYourPetActivity.class);
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
        intent.putExtra("serviceprovidingcompanyname",serviceprovidingcompanyname);
        intent.putExtra("fromactivity",TAG);
        Log.w(TAG,"gotoServiceBookAppoinment : "+"SP_ava_Date : "+SP_ava_Date);
        startActivity(intent);


    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.img_notification:
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                break;
            case R.id.img_cart:
                break;
            case R.id.img_profile:
                Intent intent = new Intent(getApplicationContext(),PetLoverProfileScreenActivity.class);
                intent.putExtra("fromactivity",TAG);
                intent.putExtra("spid",spid);
                intent.putExtra("catid",catid);
                intent.putExtra("from",from);
                intent.putExtra("spuserid",spuserid);
                intent.putExtra("selectedServiceTitle",selectedServiceTitle);
                intent.putExtra("serviceamount",serviceamount);
                intent.putExtra("servicetime",servicetime);
                intent.putExtra("distance",distance);
                startActivity(intent);
                break;
        }
    }




}
