package com.salveo.mysalveo.petlover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.modules.core.PermissionListener;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.LoginActivity;
import com.salveo.mysalveo.sessionmanager.SessionManager;


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


public class VideoCallPetLoverActivity extends AppCompatActivity implements JitsiMeetActivityInterface, JitsiMeetViewListener {
    private String TAG = "VideoCallPetLoverActivity";
    SessionManager session;
    String logintype = "",id = "",type = "";
    String appointmentid;
    String doctorname ="",doctorimage = "",doctoremailid = "",doctorid ="",patientname = "",patientimage ="",patientemailid = "", patientid = "";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_closeconversation)
    Button btn_closeconversation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_petlover);
        ButterKnife.bind(this);

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
            patientname = extras.getString("patientname");
            patientimage = extras.getString("patientimage");
            patientemailid = extras.getString("patientemailid");
            patientid = extras.getString("patientid");
            Log.w(TAG,"appointmentid :"+" "+appointmentid);

        }


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        btn_closeconversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





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
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    @Override
    public void onConferenceWillJoin(Map<String, Object> data) {
        JitsiMeetLogger.i("Conference will join: " + data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //Toasty.warning(getApplicationContext(),R.string.onbackpressedmsg, Toasty.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }







}
