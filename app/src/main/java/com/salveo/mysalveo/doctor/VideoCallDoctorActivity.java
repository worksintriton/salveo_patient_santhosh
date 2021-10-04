package com.salveo.mysalveo.doctor;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.modules.core.PermissionListener;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.AppoinmentCloseRequest;
import com.salveo.mysalveo.requestpojo.PetNoShowRequest;
import com.salveo.mysalveo.responsepojo.AppointmentsUpdateResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetActivityInterface;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetViewListener;
import org.jitsi.meet.sdk.log.JitsiMeetLogger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoCallDoctorActivity extends AppCompatActivity implements JitsiMeetActivityInterface, JitsiMeetViewListener, View.OnClickListener {

    private String TAG = "VideoCallDoctorActivity";


    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    SessionManager session;
    String id = "",type = "";
    String appointmentid = "";

    String Bookingdate = "", Bookingtime = "";

    String doctorname ="",doctorimage = "",doctoremailid = "",doctorid ="",patientname = "",patientimage ="",patientemailid = "", patientid = "",Ailmentdetails = "", Documentsattached = "";

    String Bookingfor ="", Familyid = "",Familyname = "";


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_closeconversation)
    Button btn_closeconversation;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_petownernoshow)
    Button btn_petownernoshow;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_doctor);
        ButterKnife.bind(this);

        avi_indicator.setVisibility(View.INVISIBLE);


        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        type = user.get(SessionManager.KEY_TYPE);
        id = user.get(SessionManager.KEY_ID);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appointmentid = extras.getString("id");
            doctorname = extras.getString("doctorname");
            doctorimage = extras.getString("doctorimage");
            doctoremailid = extras.getString("doctoremailid");
            doctorid = extras.getString("doctorid");
            Log.w(TAG , "Doctor_Id " +doctorid);
            patientname = extras.getString("patientname");
            patientimage = extras.getString("patientimage");
            patientemailid = extras.getString("patientemailid");
            patientid = extras.getString("patientid");
            Log.w(TAG , "patientid " +patientid);
            Bookingfor = extras.getString("Bookingfor");
            Familyid = extras.getString("Familyid");
            Familyname = extras.getString("Familyname");
            Ailmentdetails = extras.getString("Ailmentdetails");
            Documentsattached = extras.getString("Documentsattached");
            Bookingdate = extras.getString("Bookingdate");
            Bookingtime = extras.getString("Bookingtime");
            Log.w(TAG,"Bookingfor :"+Bookingfor+"Familyid :"+Familyid+"Familyname :"+Familyname);

        }

        img_back.setOnClickListener(this);
        btn_closeconversation.setOnClickListener(this);
        btn_petownernoshow.setOnClickListener(this);


        // Initialize default options for Jitsi Meet conferences.
        URL serverURL;
        try {
            serverURL = new URL("https://meet.jit.si");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid server URL!");
        }
        JitsiMeetConferenceOptions defaultOptions
                = new JitsiMeetConferenceOptions.Builder()
                .setServerURL(serverURL)
                .setWelcomePageEnabled(false)
                .build();
        JitsiMeet.setDefaultConferenceOptions(defaultOptions);


        JitsiMeetConferenceOptions options
                = new JitsiMeetConferenceOptions.Builder()
                .setRoom(appointmentid)
                .build();
        // Launch the new activity with the given options. The launch() method takes care
        // of creating the required Intent and passing the options.
        JitsiMeetActivity.launch(this, options);


    }

    @Override
    public void requestPermissions(String[] strings, int i, PermissionListener permissionListener) {

    }

    @Override
    public void onConferenceJoined(Map<String, Object> data) {
        JitsiMeetLogger.i("Conference joined: " + data);
        // Launch the service for the ongoing notification.
        // JitsiMeetOngoingConferenceService.launch(this);
    }

    @Override
    public void onConferenceTerminated(Map<String, Object> data) {
        JitsiMeetLogger.i("Conference terminated: " + data);

    }

    @Override
    public void onConferenceWillJoin(Map<String, Object> data) {
        JitsiMeetLogger.i("Conference will join: " + data);
    }

    @Override
    public void onBackPressed() {
       // Toasty.warning(getApplicationContext(),R.string.onbackpressedmsg,Toasty.LENGTH_SHORT).show();
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), DoctorDashboardActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();



    }


    private void appoinmentCloseConversationResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppointmentsUpdateResponse> call = apiInterface.appoinmentcloseconversationResponseCall(RestUtils.getContentType(), appoinmentCloseRequest());
        Log.w(TAG,"appoinmentCloseConversationResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppointmentsUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Response<AppointmentsUpdateResponse> response) {

                Log.w(TAG,"appoinmentCloseConversationResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                       // closeAppointmentNotifyResponseCall();
                        Intent intent = new Intent(getApplicationContext(), PrescriptionActivity.class);
                        intent.putExtra("id",appointmentid);
                        intent.putExtra("patient_id",patientid);
                        startActivity(intent);
                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"appoinmentCloseConversationResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    private AppoinmentCloseRequest appoinmentCloseRequest() {
        /*
         * _id : 5fc639ea72fc42044bfa1683
         * start_appointment_status :
         * appoinment_status : Completed
         */
        AppoinmentCloseRequest appoinmentCloseRequest = new AppoinmentCloseRequest();
        appoinmentCloseRequest.set_id(appointmentid);
        appoinmentCloseRequest.setStart_appointment_status("Completed");
        appoinmentCloseRequest.setAppoinment_status("Completed");
        Log.w(TAG,"appoinmentCloseRequest"+ "--->" + new Gson().toJson(appoinmentCloseRequest));
        return appoinmentCloseRequest;


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

    private void confirmCloseConversationDialog(){
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(VideoCallDoctorActivity.this);
        alertDialogBuilder.setMessage("Are you sure you want to close the conversation?.Please proceed to fill the prescription");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        if (new ConnectionDetector(VideoCallDoctorActivity.this).isNetworkAvailable(VideoCallDoctorActivity.this)) {
                            appoinmentCloseConversationResponseCall();

                        }


                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogBuilder.setCancelable(true);
            }
        });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    private void confirmPatientNoShowDialog(){

        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(VideoCallDoctorActivity.this);
        alertDialogBuilder.setMessage("Are you sure you want to close the conversation?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        if (new ConnectionDetector(VideoCallDoctorActivity.this).isNetworkAvailable(VideoCallDoctorActivity.this)) {
                            petNoShowResponseCall();
                        }


                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogBuilder.setCancelable(true);
            }
        });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void petNoShowResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppointmentsUpdateResponse> call = apiInterface.appoinmentpetnoResponseCall(RestUtils.getContentType(), petNoShowRequest());
        Log.w(TAG,"petNoShowResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppointmentsUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Response<AppointmentsUpdateResponse> response) {

                Log.w(TAG,"petNoShowResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        // closeAppointmentNotifyResponseCall();
                        Intent intent = new Intent(getApplicationContext(), DoctorDashboardActivity.class);
                        startActivity(intent);
                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"petNoShowResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    private PetNoShowRequest petNoShowRequest() {
        /**
         * _id : 5fc639ea72fc42044bfa1683
         * appoint_patient_st :no show
         * appoinment_status : Missed
         */
        PetNoShowRequest petNoShowRequest = new PetNoShowRequest();
        petNoShowRequest.set_id(appointmentid);
        petNoShowRequest.setAppoint_patient_st("Petowner Not Available");
        petNoShowRequest.setAppoinment_status("Missed");
        Log.w(TAG,"petNoShowRequest"+ "--->" + new Gson().toJson(petNoShowRequest));
        return petNoShowRequest;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
                case R.id.btn_closeconversation:
                    confirmCloseConversationDialog();
                break;
                case R.id.btn_petownernoshow:
                    confirmPatientNoShowDialog();
                    break;
        }







    }
}
