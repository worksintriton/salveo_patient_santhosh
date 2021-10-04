package com.salveo.mysalveo.petlover;

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
import android.widget.CheckBox;
import android.widget.ImageView;
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
import com.salveo.mysalveo.adapter.PetMyCalendarAvailableAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.OnItemSelectedTime;
import com.salveo.mysalveo.requestpojo.AppointmentCheckRequest;
import com.salveo.mysalveo.requestpojo.PetDoctorAvailableTimeRequest;
import com.salveo.mysalveo.requestpojo.RescheduleAppointmentRequest;
import com.salveo.mysalveo.responsepojo.AppointmentCheckResponse;
import com.salveo.mysalveo.responsepojo.PetDoctorAvailableTimeResponse;
import com.salveo.mysalveo.responsepojo.SuccessResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PetAppointment_Doctor_Date_Time_Activity extends AppCompatActivity implements OnItemSelectedTime {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_bookappointment)
    Button btn_bookappointment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.chat)
    CheckBox chat;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.video)
    CheckBox video;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_doctoravailabeslottime)
    RecyclerView rv_doctoravailabeslottime;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sub_layer1)
    RelativeLayout sub_layer1;


    String TAG = "PetAppointment_Doctor_Date_Time_Activity";

   // CalendarView calendar;

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    private String Doctor_ava_Date = "";
    private String selectedTimeSlot = "";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view)
    View view;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tvlblavailabletime)
    TextView tvlblavailabletime;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tvlbldoctoravailable)
    TextView tvlbldoctoravailable;





    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.datePickerTimeline)
    DatePickerTimeline datePickerTimeline ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    private List<PetDoctorAvailableTimeResponse.DataBean> doctorDateAvailabilityResponseList;
    private List<PetDoctorAvailableTimeResponse.DataBean.TimesBean> timesBeanList;
    private String doctorid;
    private String fromactivity;
    private String fromto;
    private int amount;
    private String communicationtype;
    private String bookingdateandtime;
    private Dialog dialog;
    private String id;
    private String distance;
    private String currentDateandTime;
    private String currenttime;
    private String currentdate;
    private String doctorname;
    private String clinicname;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petappointment_doctor_date_time);
        Log.w(TAG,"onCreateView");

        ButterKnife.bind(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            doctorid = extras.getString("doctorid");
            fromactivity = extras.getString("fromactivity");
            fromto = extras.getString("fromto");
            doctorname = extras.getString("doctorname");
            clinicname = extras.getString("clinicname");
            Log.w(TAG,"doctorid : "+doctorid+"fromactivity : "+fromactivity+ " fromto : "+fromto+" clinicname : "+clinicname);




            String petid = extras.getString("petid");
            String allergies = extras.getString("allergies");
            String probleminfo = extras.getString("probleminfo");
            doctorid = extras.getString("doctorid");
            String selectedAppointmentType = extras.getString("selectedAppointmentType");

            amount = extras.getInt("amount");
            distance = extras.getString("distance");
            Log.w(TAG,"amount : "+amount);
            communicationtype = extras.getString("communicationtype");
            bookingdateandtime = extras.getString("bookingdateandtime");

            Log.w(TAG,"petid-->"+ petid + "allergies : "+ allergies +"  probleminfo : "+ probleminfo +" selectedAppointmentType : "+ selectedAppointmentType +" communicationtype : "+communicationtype);

        }

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


        img_notification.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), NotificationActivity.class)));
        img_profile.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PetLoverProfileScreenActivity.class);
            intent.putExtra("doctorid",doctorid);
            intent.putExtra("fromactivity",TAG);
            startActivity(intent);
        });





        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        String userid = user.get(SessionManager.KEY_ID);


        Log.w(TAG,"userid :"+ userid);


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDateandTime = sf.format(new Date());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime24hrs = simpleDateFormat.format(new Date());
        currenttime = currentDateandTime24hrs.substring(currentDateandTime24hrs.indexOf(' ') + 1);
        currentdate =  currentDateandTime24hrs.substring(0, currentDateandTime24hrs.indexOf(' '));

        if(fromactivity != null && fromactivity.equalsIgnoreCase("PetAppointmentDetailsActivity")){
            currentDateandTime = sf.format(new Date(System.currentTimeMillis() + 3600000));
            Log.w(TAG,"currentDateandTime : "+currentDateandTime);

            String newcurrentDateandTime12hrs = simpleDateFormat.format(new Date(System.currentTimeMillis() + 3600000));
            Log.w(TAG,"newcurrentDateandTime12hrs : "+newcurrentDateandTime12hrs);
            currenttime = newcurrentDateandTime12hrs.substring(currentDateandTime24hrs.indexOf(' ') + 1);
            currentdate =  newcurrentDateandTime12hrs.substring(0, currentDateandTime24hrs.indexOf(' '));

            if (new ConnectionDetector(PetAppointment_Doctor_Date_Time_Activity.this).isNetworkAvailable(PetAppointment_Doctor_Date_Time_Activity.this)) {
                petDoctorAvailableTimeResponseCall(formattedDate,currentDateandTime,currenttime,currentdate);
            }


        }
        else{
            if (new ConnectionDetector(PetAppointment_Doctor_Date_Time_Activity.this).isNetworkAvailable(PetAppointment_Doctor_Date_Time_Activity.this)) {
                petDoctorAvailableTimeResponseCall(formattedDate,currentDateandTime,currenttime,currentdate);
            }
        }




        btn_bookappointment.setOnClickListener(v -> {
            Log.w(TAG,"btn_bookappointment selectedTimeSlot : "+selectedTimeSlot);
           if(selectedTimeSlot != null && !selectedTimeSlot.isEmpty()){

                if (new ConnectionDetector(PetAppointment_Doctor_Date_Time_Activity.this).isNetworkAvailable(PetAppointment_Doctor_Date_Time_Activity.this)) {
                    appointmentCheckResponseCall();
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

                if (new ConnectionDetector(PetAppointment_Doctor_Date_Time_Activity.this).isNetworkAvailable(PetAppointment_Doctor_Date_Time_Activity.this)) {
                    petDoctorAvailableTimeResponseCall(formattedDate, currentDateandTime, currenttime, Date);
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
    private void petDoctorAvailableTimeResponseCall(String formattedDate, String currentDateandTime, String currenttime, String Date) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PetDoctorAvailableTimeResponse> call = ApiService.petDoctorAvailableTimeResponseCall(RestUtils.getContentType(),petDoctorAvailableTimeRequest(Date));

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<PetDoctorAvailableTimeResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<PetDoctorAvailableTimeResponse> call, @NonNull Response<PetDoctorAvailableTimeResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"PetDoctorAvailableTimeResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getData() != null){
                            doctorDateAvailabilityResponseList = response.body().getData();

                        }
                        if (response.body().getData() != null && response.body().getData().get(0).getTimes() != null) {
                            timesBeanList = response.body().getData().get(0).getTimes();
                        }
                        Log.w(TAG,"Size"+doctorDateAvailabilityResponseList.size());
                        if(response.body().getData() != null && !response.body().getData().isEmpty()){

                            if(response.body().getData().get(0).getDoctor_ava_Date() != null){
                                Doctor_ava_Date = response.body().getData().get(0).getDoctor_ava_Date();
                            }

                            sub_layer1.setVisibility(View.VISIBLE);
                            btn_bookappointment.setVisibility(View.VISIBLE);

                            if(doctorDateAvailabilityResponseList.size()>0) {


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
                        showErrorLoading(response.body().getMessage());
                        rv_doctoravailabeslottime.setVisibility(View.GONE);
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<PetDoctorAvailableTimeResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"PetDoctorAvailableTimeResponseflr"+"--->" + t.getMessage());
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

        PetDoctorAvailableTimeRequest petDoctorAvailableTimeRequest = new PetDoctorAvailableTimeRequest();
        petDoctorAvailableTimeRequest.setUser_id(doctorid);
        petDoctorAvailableTimeRequest.setDate(Date);
        petDoctorAvailableTimeRequest.setCurrent_time(currentDateandTime);
        petDoctorAvailableTimeRequest.setCur_time(currenttime);
        petDoctorAvailableTimeRequest.setCur_date(currentdate);
        Log.w(TAG,"petDoctorAvailableTimeRequest"+ "--->" + new Gson().toJson(petDoctorAvailableTimeRequest));
        return petDoctorAvailableTimeRequest;
    }


    private void setViewAvlDays() {
        rv_doctoravailabeslottime.setVisibility(View.VISIBLE);
        rv_doctoravailabeslottime.setLayoutManager(new GridLayoutManager(this, 4));
        rv_doctoravailabeslottime.setItemAnimator(new DefaultItemAnimator());
        PetMyCalendarAvailableAdapter petMyCalendarAvailableAdapter = new PetMyCalendarAvailableAdapter(getApplicationContext(), timesBeanList, rv_doctoravailabeslottime, this);
        rv_doctoravailabeslottime.setAdapter(petMyCalendarAvailableAdapter);







    }

    public void showErrorLoading(String errormesage){
        alertDialogBuilder = new AlertDialog.Builder(this);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(fromto != null && fromto.equalsIgnoreCase("direct")){
            callDirections("4");
        } else if(fromactivity != null && fromactivity.equalsIgnoreCase("PetCareFragment")){
            Intent intent = new Intent(getApplicationContext(),DoctorClinicDetailsActivity.class);
            intent.putExtra("doctorid",doctorid);
            intent.putExtra("fromactivity",fromactivity);
            intent.putExtra("distance",distance);
            startActivity(intent);

        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PetAppointmentDetailsActivity")){
            Intent intent = new Intent(getApplicationContext(),PetMyappointmentsActivity.class);
            intent.putExtra("doctorid",doctorid);
            intent.putExtra("fromactivity",fromactivity);
            intent.putExtra("distance",distance);
            startActivity(intent);

        }else{
            Intent intent = new Intent(getApplicationContext(), DoctorClinicDetailsActivity.class);
            intent.putExtra("doctorid",doctorid);
            intent.putExtra("distance",distance);
            startActivity(intent);
            finish();

        }


    }
    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(),PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }



    @SuppressLint("LogNotTimber")
    @Override
    public void onItemSelectedTime(String selectedTime) {
        Log.w(TAG,"onItemSelectedTime : "+selectedTime);
        selectedTimeSlot = selectedTime;

    }


    @SuppressLint("LogNotTimber")
    private void appointmentCheckResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<AppointmentCheckResponse> call = ApiService.appointmentCheckResponseCall(RestUtils.getContentType(),appointmentCheckRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<AppointmentCheckResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AppointmentCheckResponse> call, @NonNull Response<AppointmentCheckResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"appointmentCheckResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(fromactivity != null && fromactivity.equalsIgnoreCase("PetAppointmentDetailsActivity")){
                            rescheduleResponseCall();
                        }else {

                           // Intent intent = new Intent(PetAppointment_Doctor_Date_Time_Activity.this, BookAppointmentActivity.class);
                            Intent intent = new Intent(PetAppointment_Doctor_Date_Time_Activity.this, ConsultationActivity.class);
                            intent.putExtra("doctorid", doctorid);
                            intent.putExtra("fromactivity", fromactivity);
                            intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
                            intent.putExtra("selectedTimeSlot", selectedTimeSlot);
                            intent.putExtra("amount", amount);
                            intent.putExtra("communicationtype", communicationtype);
                            intent.putExtra("fromto", fromto);
                            intent.putExtra("distance",distance);
                            intent.putExtra("doctorname",doctorname);
                            intent.putExtra("clinicname",clinicname);
                            startActivity(intent);
                            Log.w(TAG, "communicationtype : " + communicationtype);
                        }



                    }else{
                        showErrorLoading(response.body().getMessage());
                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<AppointmentCheckResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"AppointmentCheckResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private AppointmentCheckRequest appointmentCheckRequest() {
        /*
         * Booking_Date : 02-12-2020
         * Booking_Time : 09:00 AM
         * doctor_id : 5fc4eb2c913fec4204e4b15d
         */

        AppointmentCheckRequest appointmentCheckRequest = new AppointmentCheckRequest();
        appointmentCheckRequest.setDoctor_id(doctorid);
        appointmentCheckRequest.setBooking_Date(Doctor_ava_Date);
        appointmentCheckRequest.setBooking_Time(selectedTimeSlot);
        Log.w(TAG,"appointmentCheckRequest"+ "--->" + new Gson().toJson(appointmentCheckRequest));
        return appointmentCheckRequest;
    }



    @SuppressLint("LogNotTimber")
    private void rescheduleResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.rescheduleResponseCall(RestUtils.getContentType(),rescheduleAppointmentRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"appointmentCheckResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        showRescheduleAppointmentSuccessalert();


                    }else{
                        showErrorLoading(response.body().getMessage());
                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"AppointmentCheckResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private RescheduleAppointmentRequest rescheduleAppointmentRequest() {
        /*
         * _id : 604090e72c2b43125f8cb84e
         * already_booked_date : 03-05-2021 03:30 AM
         * reschedule_date : 05-03-2021 11:00 AM
         * booking_date : 05-03-2021
         * booking_time : 11:00 AM
         */

        RescheduleAppointmentRequest  rescheduleAppointmentRequest = new RescheduleAppointmentRequest();
        rescheduleAppointmentRequest.set_id(id);
        rescheduleAppointmentRequest.setAlready_booked_date(bookingdateandtime);
        rescheduleAppointmentRequest.setReschedule_date(Doctor_ava_Date+" "+selectedTimeSlot);
        rescheduleAppointmentRequest.setBooking_date(Doctor_ava_Date);
        rescheduleAppointmentRequest.setBooking_time(selectedTimeSlot);
        Log.w(TAG,"rescheduleAppointmentRequest"+ "--->" + new Gson().toJson(rescheduleAppointmentRequest));
        return rescheduleAppointmentRequest;
    }


    private void showRescheduleAppointmentSuccessalert() {
        try {

            dialog = new Dialog(PetAppointment_Doctor_Date_Time_Activity.this);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_reschedule_appointment_layout);
            Button btn_view = dialog.findViewById(R.id.btn_view);

            btn_view.setOnClickListener(view -> {
                dialog.dismiss();
                Intent intent = new Intent(PetAppointment_Doctor_Date_Time_Activity.this, PetMyappointmentsActivity.class);
                startActivity(intent);
                finish();


            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }




}
