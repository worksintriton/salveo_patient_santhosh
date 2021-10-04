package com.salveo.mysalveo.fragmentserviceprovider.myappointments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.SPCompletedAppointmentAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.SPAppointmentRequest;
import com.salveo.mysalveo.responsepojo.SPAppointmentResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentSPCompletedAppointment extends Fragment implements View.OnClickListener {
    private String TAG = "FragmentSPCompletedAppointment";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_completedappointment)
    RecyclerView rv_completedappointment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_load_more)
    Button btn_load_more;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_filter)
    Button btn_filter;




    SessionManager session;
    String type = "",username = "",userid = "";
    private SharedPreferences preferences;
    private Context mContext;
    private List<SPAppointmentResponse.DataBean> completedAppointmentResponseList;


    public FragmentSPCompletedAppointment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView");

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view = inflater.inflate(R.layout.fragment_sp_completed_appointment, container, false);

        ButterKnife.bind(this, view);
        mContext = getActivity();

        avi_indicator.setVisibility(View.GONE);
        btn_load_more.setVisibility(View.GONE);
        btn_filter.setVisibility(View.GONE);

        btn_load_more.setOnClickListener(this);

        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);
        username = user.get(SessionManager.KEY_FIRST_NAME);

        Log.w(TAG,"userid"+userid +"username :"+username);

      

        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
            spCompletedAppointmentResponseCall();
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
                                spCompletedAppointmentResponseCall();
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
    private void spCompletedAppointmentResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SPAppointmentResponse> call = ApiService.spCompletedAppointmentResponseCall(RestUtils.getContentType(),spAppointmentRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SPAppointmentResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<SPAppointmentResponse> call, @NonNull Response<SPAppointmentResponse> response) {
               avi_indicator.smoothToHide();
                Log.w(TAG,"spCompletedAppointmentResponseCall"+ "--->" + new Gson().toJson(response.body()));


               if (response.body() != null) {

                   if(200 == response.body().getCode()){
                       if(response.body().getData() != null && response.body().getData().size()>0){
                           completedAppointmentResponseList = response.body().getData();
                           Log.w(TAG,"Size"+completedAppointmentResponseList.size());
                           Log.w(TAG,"spCompletedAppointmentResponseCall : "+new Gson().toJson(completedAppointmentResponseList));
                              txt_no_records.setVisibility(View.GONE);
                               rv_completedappointment.setVisibility(View.VISIBLE);
                               Log.w(TAG,"Size : "+completedAppointmentResponseList.size());
                               if(completedAppointmentResponseList.size() > 3){
                                   btn_load_more.setVisibility(View.VISIBLE);
                               }else{
                                   btn_load_more.setVisibility(View.GONE);

                               }
                               setView();
                           }else{
                               rv_completedappointment.setVisibility(View.GONE);
                               btn_load_more.setVisibility(View.GONE);
                               btn_filter.setVisibility(View.GONE);
                               txt_no_records.setVisibility(View.VISIBLE);
                               txt_no_records.setText(getResources().getString(R.string.no_completed_appointments_sp));


                       }


                   }



                }
            }

            @Override
            public void onFailure(@NonNull Call<SPAppointmentResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"SPAppointmentResponse flr"+"--->" + t.getMessage());
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
        rv_completedappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_completedappointment.setItemAnimator(new DefaultItemAnimator());
        int size = 3;
        SPCompletedAppointmentAdapter spCompletedAppointmentAdapter = new SPCompletedAppointmentAdapter(getContext(), completedAppointmentResponseList, rv_completedappointment,size);
        rv_completedappointment.setAdapter(spCompletedAppointmentAdapter);

    }
    private void setViewLoadMore() {
        rv_completedappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_completedappointment.setItemAnimator(new DefaultItemAnimator());
        int size = completedAppointmentResponseList.size();
        SPCompletedAppointmentAdapter spCompletedAppointmentAdapter = new SPCompletedAppointmentAdapter(getContext(), completedAppointmentResponseList, rv_completedappointment,size);
        rv_completedappointment.setAdapter(spCompletedAppointmentAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_load_more:
                setViewLoadMore();
                break;
        }
    }
}