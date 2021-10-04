package com.salveo.mysalveo.fragmentserviceprovider.myappointments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.SPNewAppointmentAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;

import com.salveo.mysalveo.interfaces.OnAppointmentCancel;
import com.salveo.mysalveo.interfaces.OnAppointmentComplete;
import com.salveo.mysalveo.requestpojo.AppoinmentCancelledRequest;
import com.salveo.mysalveo.requestpojo.AppoinmentCompleteRequest;
import com.salveo.mysalveo.requestpojo.SPAppointmentRequest;
import com.salveo.mysalveo.requestpojo.SPNotificationSendRequest;
import com.salveo.mysalveo.responsepojo.AppoinmentCancelledResponse;
import com.salveo.mysalveo.responsepojo.AppoinmentCompleteResponse;
import com.salveo.mysalveo.responsepojo.NotificationSendResponse;
import com.salveo.mysalveo.responsepojo.SPAppointmentResponse;
import com.salveo.mysalveo.serviceprovider.ServiceProviderDashboardActivity;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentSPNewAppointment extends Fragment implements OnAppointmentCancel, OnAppointmentComplete, View.OnClickListener {
    private String TAG = "FragmentSPNewAppointment";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_newappointment)
    RecyclerView rv_newappointment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_load_more)
    Button btn_load_more;


    SessionManager session;
    String type = "",username = "",userid = "";
    private SharedPreferences preferences;
    private Context mContext;
    private List<SPAppointmentResponse.DataBean> newAppointmentResponseList;
    private Dialog dialog;


    public FragmentSPNewAppointment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView");

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view = inflater.inflate(R.layout.fragment_sp_new_appointment, container, false);

        ButterKnife.bind(this, view);
        mContext = getActivity();

        avi_indicator.setVisibility(View.GONE);
        btn_load_more.setVisibility(View.GONE);
        btn_load_more.setOnClickListener(this);

        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        username = user.get(SessionManager.KEY_FIRST_NAME);

        Log.w(TAG,"userid"+userid +"username :"+username);


        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
            spNewAppointmentResponseCall();
        }

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            //your method here
                            if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                                spNewAppointmentResponseCall();
                            }

                        } catch (Exception e) {
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 30000);//you can put 30000(30 secs)


        return view;
    }



    @SuppressLint("LogNotTimber")
    private void spNewAppointmentResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SPAppointmentResponse> call = ApiService.spNewAppointmentResponseCall(RestUtils.getContentType(),spAppointmentRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SPAppointmentResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<SPAppointmentResponse> call, @NonNull Response<SPAppointmentResponse> response) {
               avi_indicator.smoothToHide();
                Log.w(TAG,"spNewAppointmentResponseCall"+ "--->" + new Gson().toJson(response.body()));


               if (response.body() != null) {

                   if(200 == response.body().getCode()){
                       if(response.body().getData() != null && response.body().getData().size()>0){
                           newAppointmentResponseList = response.body().getData();
                           Log.w(TAG,"Size"+newAppointmentResponseList.size());
                           Log.w(TAG,"spNewAppointmentResponseCall : "+new Gson().toJson(newAppointmentResponseList));
                               txt_no_records.setVisibility(View.GONE);
                               rv_newappointment.setVisibility(View.VISIBLE);
                               if(newAppointmentResponseList.size()>3){
                                   Log.w(TAG,"size init --> "+newAppointmentResponseList.size());
                                   btn_load_more.setVisibility(View.VISIBLE);
                               }else{
                                   btn_load_more.setVisibility(View.GONE);
                               }
                               setView();

                       }else{
                           rv_newappointment.setVisibility(View.GONE);
                           btn_load_more.setVisibility(View.GONE);
                           txt_no_records.setVisibility(View.VISIBLE);
                           txt_no_records.setText(getResources().getString(R.string.no_new_appointments_sp));


                       }


                   }



                }
            }

            @Override
            public void onFailure(@NonNull Call<SPAppointmentResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"spNewAppointmentResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private SPAppointmentRequest spAppointmentRequest() {
        SPAppointmentRequest spAppointmentRequest = new SPAppointmentRequest();
        spAppointmentRequest.setSp_id(userid);
        Log.w(TAG,"spAppointmentRequest"+ "--->" + new Gson().toJson(spAppointmentRequest));
        return spAppointmentRequest;
    }
    private void setView() {
        rv_newappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_newappointment.setItemAnimator(new DefaultItemAnimator());
        int size = 3;
        Log.w(TAG,"size set --> "+size);
        SPNewAppointmentAdapter spNewAppointmentAdapter = new SPNewAppointmentAdapter(getContext(), newAppointmentResponseList, rv_newappointment,size,this,this);
        rv_newappointment.setAdapter(spNewAppointmentAdapter);

    }
    private void setViewLoadMore() {
        rv_newappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_newappointment.setItemAnimator(new DefaultItemAnimator());
        int size = newAppointmentResponseList.size();
        Log.w(TAG,"size onclick --> "+size);
        SPNewAppointmentAdapter spNewAppointmentAdapter = new SPNewAppointmentAdapter(getContext(), newAppointmentResponseList, rv_newappointment,size,this,this);
        rv_newappointment.setAdapter(spNewAppointmentAdapter);

    }


    @Override
    public void onAppointmentCancel(String id,String appointmenttype,String userid, String doctorid,String appointmentid,String spid) {
        if(id != null){
            Log.w(TAG,"userid : "+userid+" spid :"+doctorid+"appointmentid : "+appointmentid);
            showStatusAlert(id,appointmenttype,userid,doctorid,appointmentid,spid);
        }
    }

    private void showStatusAlert(String id,String appointmenttype,String userid, String doctorid,String appointmentid,String spid ) {

        try {

            dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = (TextView)dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.cancelappointment);
            Button dialogButtonApprove = (Button) dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = (Button) dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    appoinmentCancelledResponseCall(id,appointmenttype,userid,doctorid,appointmentid,spid);


                }
            });
            dialogButtonRejected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toasty.info(context, "Rejected Successfully", Toast.LENGTH_SHORT, true).show();
                    dialog.dismiss();




                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    private void appoinmentCancelledResponseCall(String id,String appointmenttype,String userid, String doctorid,String appointmentid,String spid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppoinmentCancelledResponse> call = apiInterface.spappoinmentCancelledResponseCall(RestUtils.getContentType(), appoinmentCancelledRequest(id));
        Log.w(TAG,"appoinmentCancelledResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppoinmentCancelledResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Response<AppoinmentCancelledResponse> response) {

                Log.w(TAG,"appoinmentCancelledResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        spnotificationSendResponseCall(id,appointmenttype,userid,doctorid,appointmentid,spid);





                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"appoinmentCancelledResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    private AppoinmentCancelledRequest appoinmentCancelledRequest(String id) {

        /*
         * _id : 5fc639ea72fc42044bfa1683
         * missed_at : 23-10-2000 10 : 00 AM
         * doc_feedback : One Emergenecy work i am cancelling this appointment
         * appoinment_status : Missed
         */


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        AppoinmentCancelledRequest appoinmentCancelledRequest = new AppoinmentCancelledRequest();
        appoinmentCancelledRequest.set_id(id);
        appoinmentCancelledRequest.setMissed_at(currentDateandTime);
        appoinmentCancelledRequest.setDoc_feedback("");
        appoinmentCancelledRequest.setAppoinment_status("Missed");
        appoinmentCancelledRequest.setAppoint_patient_st("Doctor Cancelled appointment");
        Log.w(TAG,"appoinmentCancelledRequest"+ "--->" + new Gson().toJson(appoinmentCancelledRequest));
        return appoinmentCancelledRequest;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_load_more:
                setViewLoadMore();
                break;
        }
    }

    @Override
    public void onAppointmentComplete(String id) {

        showStatusAlertCompleteAppointment(id);

    }
    private void showStatusAlertCompleteAppointment(String id) {

        try {

            dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = (TextView)dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.completeappointment);
            Button dialogButtonApprove = (Button) dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = (Button) dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    appoinmentCompleteResponseCall(id);


                }
            });
            dialogButtonRejected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toasty.info(context, "Rejected Successfully", Toast.LENGTH_SHORT, true).show();
                    dialog.dismiss();




                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    private void appoinmentCompleteResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppoinmentCompleteResponse> call = apiInterface.spappoinmentCompleteResponseCall(RestUtils.getContentType(), appoinmentCompleteRequest(id));
        Log.w(TAG,"AppoinmentCompleteResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppoinmentCompleteResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppoinmentCompleteResponse> call, @NonNull Response<AppoinmentCompleteResponse> response) {

                Log.w(TAG,"AppoinmentCompleteResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        startActivity(new Intent(mContext, ServiceProviderDashboardActivity.class));
                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<AppoinmentCompleteResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"AppoinmentCompleteResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private AppoinmentCompleteRequest appoinmentCompleteRequest(String id) {
        /*
         * _id : 5fc639ea72fc42044bfa1683
         * completed_at : 23-10-2000 10 : 00 AM
         * appoinment_status : Completed
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        AppoinmentCompleteRequest appoinmentCompleteRequest = new AppoinmentCompleteRequest();
        appoinmentCompleteRequest.set_id(id);
        appoinmentCompleteRequest.setCompleted_at(currentDateandTime);
        appoinmentCompleteRequest.setAppoinment_status("Completed");
        Log.w(TAG,"appoinmentCompleteRequest"+ "--->" + new Gson().toJson(appoinmentCompleteRequest));
        return appoinmentCompleteRequest;
    }

    @SuppressLint("LogNotTimber")
    private void spnotificationSendResponseCall(String id, String appointmenttype, String userid, String doctorid, String appointmentid, String spid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<NotificationSendResponse> call = ApiService.spnotificationSendResponseCall(RestUtils.getContentType(),spNotificationSendRequest(id,appointmenttype,userid,doctorid,appointmentid,spid));

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<NotificationSendResponse>() {
            @Override
            public void onResponse(@NonNull Call<NotificationSendResponse> call, @NonNull Response<NotificationSendResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"notificationSendResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        startActivity(new Intent(mContext, ServiceProviderDashboardActivity.class));

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
    private SPNotificationSendRequest spNotificationSendRequest(String id,String appointmenttype,String userid, String doctorid,String appointmentid,String spid) {

        /*
         * status : Payment Failed
         * date : 23-10-2020 11:00 AM
         * appointment_UID : PET-2923029239123
         * user_id : 601b8ac3204c595ee52582f2
         * sp_id : 601ba9c6270cbe79fd900183
         */
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime = simpleDateFormat.format(new Date());



        SPNotificationSendRequest spNotificationSendRequest = new SPNotificationSendRequest();
        spNotificationSendRequest.setStatus("Doctor Appointment Cancelled");
        spNotificationSendRequest.setDate(currentDateandTime);
        spNotificationSendRequest.setAppointment_UID(appointmentid);
        spNotificationSendRequest.setUser_id(userid);
        spNotificationSendRequest.setSp_id(doctorid);


        Log.w(TAG,"spNotificationSendRequest"+ "--->" + new Gson().toJson(spNotificationSendRequest));
        return spNotificationSendRequest;
    }

}