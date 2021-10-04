package com.salveo.mysalveo.serviceprovider;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.SPHolidayListAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.OnItemDeleteHoliday;
import com.salveo.mysalveo.requestpojo.CreateHolidayRequest;
import com.salveo.mysalveo.requestpojo.HolidayDeleteRequest;
import com.salveo.mysalveo.requestpojo.HolidayListRequest;
import com.salveo.mysalveo.responsepojo.CreateHolidayResponse;
import com.salveo.mysalveo.responsepojo.HolidayDeleteResponse;
import com.salveo.mysalveo.responsepojo.HolidayListResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SP_Holiday_Activity extends AppCompatActivity implements OnItemDeleteHoliday {


    private String TAG = "SP_Holiday_Activity";



    private ImageView backarrow;

    AVLoadingIndicatorView avi_indicator;





    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;






    String doctorname = "",doctoremailid = "";
    SessionManager session;

    String SelectedDate = "";
    TextView tvdob;
    private int year, month, day;
    RelativeLayout rldob;
    String SelectedDob = "";
    private static final int DATE_PICKER_ID = 0 ;


    TextView tvNorecords;
    RecyclerView rv_listofholidays;
    private SharedPreferences preferences;

    HolidayListResponse holidayListResponse;
    private List<HolidayListResponse.DataBean> holidayListResponseList = null;
    private Dialog dialog;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_holiday);
        Log.w(TAG,"onCreateView");

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);


        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        doctorname = user.get(SessionManager.KEY_FIRST_NAME);
        doctoremailid = user.get(SessionManager.KEY_EMAIL_ID);
        userid = user.get(SessionManager.KEY_ID);


        Log.w(TAG,"doctorname :"+doctorname+" "+"doctoremailid :"+doctoremailid);

        rv_listofholidays = findViewById(R.id.rv_listofholidays);
        tvNorecords = findViewById(R.id.tvNoRecords);

        tvdob = findViewById(R.id.tvdob);
        rldob = findViewById(R.id.rldob);
        rldob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate();
            }


        });

        backarrow = findViewById(R.id.backarrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        if (new ConnectionDetector(SP_Holiday_Activity.this).isNetworkAvailable(SP_Holiday_Activity.this)) {
            holidayListResponseCall();
        }


    }

    @SuppressLint("LogNotTimber")
    private void holidayListResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<HolidayListResponse> call = ApiService.spHolidayListResponseCall(RestUtils.getContentType(),holidayListRequest());
        Log.w(TAG,"url  :%s"+call.request().url().toString());

        call.enqueue(new Callback<HolidayListResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<HolidayListResponse> call, @NonNull Response<HolidayListResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"HolidayListResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                            Log.w(TAG,"PatientGetpaylistResponse" + "--->" + new Gson().toJson(response.body()));
                           if(response.body().getData() != null){
                               holidayListResponseList = response.body().getData();
                               Log.w(TAG,"Size%s"+ holidayListResponseList.size());
                               if(response.body().getData().isEmpty()){
                                   tvNorecords.setVisibility(View.VISIBLE);
                                   tvNorecords.setText("No holidays");
                                   rv_listofholidays.setVisibility(View.GONE);
                               }else{
                                   tvNorecords.setVisibility(View.GONE);
                                   rv_listofholidays.setVisibility(View.VISIBLE);
                                   setView();
                               }

                           }




                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<HolidayListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToShow();

                Log.w(TAG,"HolidayListResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    private HolidayListRequest holidayListRequest() {
        HolidayListRequest holidayListRequest = new HolidayListRequest();
        holidayListRequest.setUser_id(userid);
        Log.w(TAG,"holidayListRequest"+ "--->" + new Gson().toJson(holidayListRequest));
        return holidayListRequest;
    }
    private void setView() {
        rv_listofholidays.setLayoutManager(new LinearLayoutManager(this));
        rv_listofholidays.setItemAnimator(new DefaultItemAnimator());
        SPHolidayListAdapter  spHolidayListAdapter = new SPHolidayListAdapter(getApplicationContext(), holidayListResponseList, rv_listofholidays,this);
        rv_listofholidays.setAdapter(spHolidayListAdapter);

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
        startActivity(new Intent(getApplicationContext(), SPMyCalendarActivity.class));
        finish();
    }


    @SuppressLint("LogNotTimber")
    private void createHolidayResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<CreateHolidayResponse> call = ApiService.spCreateHolidayResponseCall(RestUtils.getContentType(),createHolidayRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<CreateHolidayResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<CreateHolidayResponse> call, @NonNull Response<CreateHolidayResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"CreateHolidayResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if (new ConnectionDetector(SP_Holiday_Activity.this).isNetworkAvailable(SP_Holiday_Activity.this)) {
                            holidayListResponseCall();
                        }

                    }else{
                        showErrorLoading(response.body().getMessage());
                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<CreateHolidayResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"DoctorTimeCheckResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    private CreateHolidayRequest createHolidayRequest() {

        /*
         * user_id : 1234567890
         * Date : 23-10-2020
         */
        CreateHolidayRequest createHolidayRequest = new CreateHolidayRequest();
        createHolidayRequest.setDate(tvdob.getText().toString());
        createHolidayRequest.setUser_id(userid);
        Log.w(TAG,"createHolidayRequest"+ "--->" + new Gson().toJson(createHolidayRequest));
        return createHolidayRequest;
    }


    private void SelectDate() {

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        showDialog(DATE_PICKER_ID);

    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                // return new DatePickerDialog(this, pickerListener, year, month,day);
                DatePickerDialog dialog = new DatePickerDialog(this, pickerListener, year, month, day);
               // dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                return dialog;
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;



            String strdayOfMonth = "";
            String strMonth = "";
            int month1 =(month + 1);
            if(day == 9 || day <9){
                strdayOfMonth = "0"+day;
                Log.w(TAG,"Selected dayOfMonth-->"+strdayOfMonth);
            }else{
                strdayOfMonth = String.valueOf(day);
            }

            if(month1 == 9 || month1 <9){
                strMonth = "0"+month1;
                Log.w(TAG,"Selected month1-->"+strMonth);
            }else{
                strMonth = String.valueOf(month1);
            }

            SelectedDob = strdayOfMonth + "-" + strMonth + "-" + year;

            // Show selected date
            tvdob.setText(SelectedDob);

            if (new ConnectionDetector(SP_Holiday_Activity.this).isNetworkAvailable(SP_Holiday_Activity.this)) {
                createHolidayResponseCall();
            }

        }
    };

    @Override
    public void onItemdeletedate(String itemdateid) {
        Log.w(TAG," onItemdeletedate : "+itemdateid);
        showStatusAlert(itemdateid);

    }

    private void showStatusAlert(String dateid) {

        try {

            dialog = new Dialog(SP_Holiday_Activity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.deleteholidaymsg);
            Button dialogButtonApprove = dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    final ProgressDialog dialog = new ProgressDialog(view.getContext());
                    dialog.setMessage("Please wait.....");
                    dialog.show();
                    holidayDeleteResponseCall(dialog,dateid);


                }
            });
            dialogButtonRejected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toasty.info(context, "Rejected Successfully", Toast.LENGTH_SHORT, true).show();
                    dialog.dismiss();




                }
            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    @SuppressLint("LogNotTimber")
    private void holidayDeleteResponseCall(final ProgressDialog dialog, String dateid) {
        dialog.show();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<HolidayDeleteResponse> call = apiInterface.spHolidayDeleteResponseCall(RestUtils.getContentType(),holidayDeleteRequest(dateid));

        Log.w(TAG,"url  :%s"+call.request().url().toString());

        call.enqueue(new Callback<HolidayDeleteResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NotNull Call<HolidayDeleteResponse> call, @NotNull Response<HolidayDeleteResponse> response) {
                dialog.dismiss();
                Log.w(TAG,"HolidayDeleteResponse"+ "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        if (new ConnectionDetector(SP_Holiday_Activity.this).isNetworkAvailable(SP_Holiday_Activity.this)) {
                            holidayListResponseCall();
                        }

                    }
                }



            }

            @Override
            public void onFailure(@NotNull Call<HolidayDeleteResponse> call, @NotNull Throwable t) {
                dialog.dismiss();

                Log.w(TAG,"HolidayDeleteResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    private HolidayDeleteRequest holidayDeleteRequest(String dateid) {
        /*
         * _id : 5f88587983b6d9067d05e1dd
         */
        HolidayDeleteRequest holidayDeleteRequest = new HolidayDeleteRequest();
        holidayDeleteRequest.set_id(dateid);
        Log.w(TAG,"holidayDeleteRequest"+ "--->" + new Gson().toJson(holidayDeleteRequest));
        return holidayDeleteRequest;
    }

}
