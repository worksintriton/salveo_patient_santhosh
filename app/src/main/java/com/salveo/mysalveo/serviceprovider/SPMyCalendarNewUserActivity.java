package com.salveo.mysalveo.serviceprovider;

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
import com.salveo.mysalveo.adapter.SPMyCalendarAvailableNewUserAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.OnItemClickSpecialization;
import com.salveo.mysalveo.requestpojo.SPMyCalendarAvlDaysRequest;
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

public class SPMyCalendarNewUserActivity extends AppCompatActivity implements OnItemClickSpecialization {

    private static final String TAG = "SPMyCalendarNewUserActivity" ;
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


        if (new ConnectionDetector(SPMyCalendarNewUserActivity.this).isNetworkAvailable(SPMyCalendarNewUserActivity.this)) {
            spMyCalendarAvlDaysResponseCall();
        }
       btn_next = findViewById(R.id.btn_next);
        btn_next.setVisibility(View.GONE);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                Log.w(TAG,"dateList-->"+new Gson().toJson(dateList));
                if(dateList != null && dateList.size()>0){
                    Intent intent = new Intent(SPMyCalendarNewUserActivity.this, SPMyCalendarTimeActivity.class);
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
        txtAddHoliday.setVisibility(View.GONE);

        }

    @SuppressLint("LongLogTag")
    private void spMyCalendarAvlDaysResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<DoctorMyCalendarAvlDaysResponse> call = ApiService.spMyCalendarAvlDaysResponseCall(RestUtils.getContentType(),spMyCalendarAvlDaysRequest());
        Log.w(TAG,"url  :%s"+" "+call.request().url().toString());

        call.enqueue(new Callback<DoctorMyCalendarAvlDaysResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<DoctorMyCalendarAvlDaysResponse> call, @NonNull Response<DoctorMyCalendarAvlDaysResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"spMyCalendarAvlDaysResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getData() != null) {
                            dataBeanList = response.body().getData();
                            for (int i = 0; i < dataBeanList.size(); i++) {
                                boolean isStatus = dataBeanList.get(i).isStatus();
                                if (isStatus) {
                                    btn_next.setVisibility(View.GONE);
                                } else {
                                    btn_next.setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                            if (dataBeanList.size() > 0) {
                                setViewAvlDays();
                            }
                        }

                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<DoctorMyCalendarAvlDaysResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"spMyCalendarAvlDaysResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private SPMyCalendarAvlDaysRequest spMyCalendarAvlDaysRequest() {
        /*
         * sp_name : imthi
         * types : 1
         * user_id : 5fe18d0bf9ed795ad2f594941
         */
        SPMyCalendarAvlDaysRequest spMyCalendarAvlDaysRequest = new SPMyCalendarAvlDaysRequest();
        spMyCalendarAvlDaysRequest.setUser_id(userid);
        spMyCalendarAvlDaysRequest.setSp_name(doctorname);
        spMyCalendarAvlDaysRequest.setTypes(1);
        Log.w(TAG,"spMyCalendarAvlDaysRequest"+ "--->" + new Gson().toJson(spMyCalendarAvlDaysRequest));
        return spMyCalendarAvlDaysRequest;
    }

    private void setViewAvlDays() {
        rv_doctor_mycalendar_avldays.setLayoutManager(new LinearLayoutManager(this));
        rv_doctor_mycalendar_avldays.setItemAnimator(new DefaultItemAnimator());
        SPMyCalendarAvailableNewUserAdapter spMyCalendarAvailableNewUserAdapter = new SPMyCalendarAvailableNewUserAdapter(getApplicationContext(), dataBeanList, rv_doctor_mycalendar_avldays, this);
        rv_doctor_mycalendar_avldays.setAdapter(spMyCalendarAvailableNewUserAdapter);

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
        Toasty.warning(getApplicationContext(), "This action is disabled in this screen..", Toast.LENGTH_SHORT, true).show();

       /* super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), DoctorDashboardActivity.class));
        finish();*/
    }
}