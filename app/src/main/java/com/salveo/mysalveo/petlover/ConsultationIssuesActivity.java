package com.salveo.mysalveo.petlover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.HealthIssueListAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.MyPetsSelectListener;
import com.salveo.mysalveo.requestpojo.PetListRequest;
import com.salveo.mysalveo.responsepojo.HealthIssuesListResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultationIssuesActivity extends AppCompatActivity implements View.OnClickListener, MyPetsSelectListener {

    private String TAG = "ConsultationIssuesActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_pet)
    RecyclerView rv_pet;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_save_continue)
    LinearLayout ll_save_continue;

    private String userid;


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
    private List<HealthIssuesListResponse.DataBean> healthissueList;


    private String spid,catid,from;
    private String spuserid;
    private String selectedServiceTitle;
    private String petcolor;
    private double petweight;
    private String servicetime;
    private int serviceamount;
    private String petage;
    private int distance;
    private String SP_ava_Date;
    private String health_issue_title;
    private String doctorname;
    private String clinicname;
    private String petname;


    @SuppressLint({"LogNotTimber", "SetTextI18n", "LongLogTag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_issues);
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);
        ll_save_continue.setVisibility(View.GONE);


        SessionManager sessionManager = new SessionManager(ConsultationIssuesActivity.this);
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        String name = user.get(SessionManager.KEY_FIRST_NAME);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            doctorid = extras.getString("doctorid");
            fromactivity = extras.getString("fromactivity");
            fromto = extras.getString("fromto");
            Doctor_ava_Date = extras.getString("Doctor_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            amount = extras.getInt("amount");
            Log.w(TAG,"amount : "+amount);
            communicationtype = extras.getString("communicationtype");
            petId = extras.getString("petId");
            Log.w(TAG,"Bundle "+" doctorid : "+doctorid+" selectedTimeSlot : "+selectedTimeSlot+"communicationtype : "+communicationtype+" amount : "+amount+" fromactivity : "+fromactivity);

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
            doctorname = extras.getString("doctorname");
            clinicname = extras.getString("clinicname");
            petname = extras.getString("petname");
            Log.w(TAG,"spid : "+spid +" catid : "+catid+" from : "+from+" serviceamount : "+serviceamount+" servicetime : "+servicetime+" SP_ava_Date : "+SP_ava_Date+" selectedTimeSlot : "+selectedTimeSlot);

            Log.w(TAG, "petId : " + petId);

        }
        img_back.setOnClickListener(this);
        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            healthissueListResponseCall();
        }

        ll_save_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
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
                    intent.putExtra("health_issue_title",health_issue_title);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(ConsultationIssuesActivity.this, BookAppointmentActivity.class);
                    intent.putExtra("doctorid", doctorid);
                    intent.putExtra("fromactivity", fromactivity);
                    intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
                    intent.putExtra("selectedTimeSlot", selectedTimeSlot);
                    intent.putExtra("amount", amount);
                    intent.putExtra("communicationtype", communicationtype);
                    intent.putExtra("fromto", fromto);
                    intent.putExtra("petId", petId);
                    intent.putExtra("health_issue_title", health_issue_title);
                    intent.putExtra("doctorname", doctorname);
                    intent.putExtra("clinicname", clinicname);
                    intent.putExtra("petname", petname);
                    startActivity(intent);
                    Log.w(TAG, "communicationtype : " + communicationtype);
                }
            }
        });




    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
            Intent intent = new Intent(getApplicationContext(),ConsultationActivity.class);
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
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(ConsultationIssuesActivity.this, ConsultationActivity.class);
            intent.putExtra("doctorid", doctorid);
            intent.putExtra("fromactivity", fromactivity);
            intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
            intent.putExtra("selectedTimeSlot", selectedTimeSlot);
            intent.putExtra("amount", amount);
            intent.putExtra("communicationtype", communicationtype);
            intent.putExtra("fromto", fromto);
            intent.putExtra("petId", petId);
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

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    public void healthissueListResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<HealthIssuesListResponse> call = apiInterface.healthissueListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<HealthIssuesListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<HealthIssuesListResponse> call, @NonNull Response<HealthIssuesListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"PetTypeListResponse" + new Gson().toJson(response.body()));
                         healthissueList = response.body().getData();
                        if(healthissueList != null && healthissueList.size()>0){
                            setView();
                        }
                    }



                }








            }


            @Override
            public void onFailure(@NonNull Call<HealthIssuesListResponse> call,@NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"HealthIssuesListResponse flr"+t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private PetListRequest petListRequest() {
        PetListRequest petListRequest = new PetListRequest();
        petListRequest.setUser_id(userid);
        Log.w(TAG,"petListRequest"+ "--->" + new Gson().toJson(petListRequest));
        return petListRequest;
    }
    private void setView() {
        rv_pet.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_pet.setItemAnimator(new DefaultItemAnimator());
        HealthIssueListAdapter healthIssueListAdapter = new HealthIssueListAdapter(getApplicationContext(), healthissueList, this);
        rv_pet.setAdapter(healthIssueListAdapter);

    }


    @SuppressLint("LogNotTimber")
    @Override
    public void myPetsSelectListener(String Health_issue_title, String name) {
        Log.w(TAG,"myPetsSelectListener : Health_issue_title "+Health_issue_title);
        if(Health_issue_title != null){
           // petId = petid;
             health_issue_title = Health_issue_title;
            ll_save_continue.setVisibility(View.VISIBLE);
        }else{
            ll_save_continue.setVisibility(View.GONE);
        }

    }
}