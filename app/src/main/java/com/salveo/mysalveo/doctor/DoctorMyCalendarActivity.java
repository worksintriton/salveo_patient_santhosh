package com.salveo.mysalveo.doctor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.DoctorMyCalendarAvailableAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.OnItemClickSpecialization;
import com.salveo.mysalveo.requestpojo.DoctorMyCalendarAvlDaysRequest;
import com.salveo.mysalveo.responsepojo.DoctorMyCalendarAvlDaysResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorMyCalendarActivity extends AppCompatActivity implements OnItemClickSpecialization {

    private static final String TAG = "DoctorMyCalendarActivity" ;
    RecyclerView rv_doctor_mycalendar_avldays;
    private SharedPreferences preferences;

    private List<DoctorMyCalendarAvlDaysResponse.DataBean> dataBeanList = null;

    private ArrayList<String> dateList = new ArrayList<>();
    private SessionManager session;
    String doctorname = "",doctoremailid = "";
    Button btn_next;
    private String userid;
    AVLoadingIndicatorView avi_indicator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_my_calendar);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        rv_doctor_mycalendar_avldays = findViewById(R.id.rv_doctor_mycalendar_avldays);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        doctorname = user.get(SessionManager.KEY_FIRST_NAME);
        doctoremailid = user.get(SessionManager.KEY_EMAIL_ID);
        userid = user.get(SessionManager.KEY_ID);

        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);


        if (new ConnectionDetector(DoctorMyCalendarActivity.this).isNetworkAvailable(DoctorMyCalendarActivity.this)) {
            doctorMyCalendarAvlDaysResponseCall();
        }
         btn_next = findViewById(R.id.btn_next);
        btn_next.setVisibility(View.GONE);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                Log.w(TAG,"dateList-->"+new Gson().toJson(dateList));
                if(dateList != null && dateList.size()>0){
                    Intent intent = new Intent(DoctorMyCalendarActivity.this,DoctorMyCalendarTimeActivity.class);
                    intent.putExtra("dateList",dateList);
                    startActivity(intent);
                }else{
                    Toasty.warning(getApplicationContext(), "Please select any one day", Toast.LENGTH_SHORT, true).show();

                }

            }
        });

        RelativeLayout back_rela = findViewById(R.id.back_rela);
        back_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView txtAddHoliday = findViewById(R.id.txtAddHoliday);
        txtAddHoliday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorMyCalendarActivity.this,Doctor_Holiday_Activity.class));
            }
        });
        }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void doctorMyCalendarAvlDaysResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<DoctorMyCalendarAvlDaysResponse> call = ApiService.doctorMyCalendarAvlDaysResponseCall(RestUtils.getContentType(),doctorMyCalendarAvlDaysRequest());
        Log.w(TAG,"url  :%s"+" "+call.request().url().toString());

        call.enqueue(new Callback<DoctorMyCalendarAvlDaysResponse>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NonNull Call<DoctorMyCalendarAvlDaysResponse> call, @NonNull Response<DoctorMyCalendarAvlDaysResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"DoctorMyCalendarAvlDaysResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getData() != null ) {
                            dataBeanList = response.body().getData();
                        }

                        if(dataBeanList != null && dataBeanList.size()>0) {
                            for (int i = 0; i < dataBeanList.size(); i++) {
                                boolean isStatus = dataBeanList.get(i).isStatus();
                                if (isStatus) {
                                    btn_next.setVisibility(View.GONE);
                                } else {
                                    btn_next.setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                            setViewAvlDays();
                        }



                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<DoctorMyCalendarAvlDaysResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"DoctorMyCalendarAvlDaysResponse"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LongLogTag")
    private DoctorMyCalendarAvlDaysRequest doctorMyCalendarAvlDaysRequest() {

        /*
         * user_id : 1234567890
         * Doctor_name : mohammed6
         * types : 1
         */
        DoctorMyCalendarAvlDaysRequest doctorMyCalendarAvlDaysRequest = new DoctorMyCalendarAvlDaysRequest();
        doctorMyCalendarAvlDaysRequest.setUser_id(userid);
        doctorMyCalendarAvlDaysRequest.setDoctor_name(doctorname);
        doctorMyCalendarAvlDaysRequest.setTypes(1);
        Log.w(TAG,"doctorMyCalendarAvlDaysRequest"+ "--->" + new Gson().toJson(doctorMyCalendarAvlDaysRequest));
        return doctorMyCalendarAvlDaysRequest;
    }

    private void setViewAvlDays() {
        rv_doctor_mycalendar_avldays.setLayoutManager(new LinearLayoutManager(this));
        rv_doctor_mycalendar_avldays.setItemAnimator(new DefaultItemAnimator());
        DoctorMyCalendarAvailableAdapter doctorMyCalendarAvailableAdapter = new DoctorMyCalendarAvailableAdapter(getApplicationContext(), dataBeanList, rv_doctor_mycalendar_avldays, DoctorMyCalendarActivity.this);
        rv_doctor_mycalendar_avldays.setAdapter(doctorMyCalendarAvailableAdapter);

   }

    @SuppressLint("LongLogTag")
    @Override
    public void onItemCheckSpecialization(String item) {
        dateList.add(item);
        Log.w(TAG,"onItemCheckSpecialization : "+item);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onItemUncheckSpecialization(String item) {
        Log.w(TAG,"onItemUncheckSpecialization : "+item);
        dateList.remove(item);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), DoctorDashboardActivity.class));
        finish();
    }
}