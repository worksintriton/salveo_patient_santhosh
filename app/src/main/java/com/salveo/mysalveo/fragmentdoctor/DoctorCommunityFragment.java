package com.salveo.mysalveo.fragmentdoctor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.responsepojo.CommunityTextResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorCommunityFragment extends Fragment{


    private String TAG = "DoctorCommunityFragment";

    View view;

    public DoctorCommunityFragment() {
        // Required empty public constructor
    }


    @SuppressLint("LogNotTimber")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(TAG,"onCreate-->");



    }

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_community_text)
    TextView txt_community_text;

    private Context mContext;

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView-->");

        view = inflater.inflate(R.layout.fragment_doctor_community, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();


        if (new ConnectionDetector(mContext).isNetworkAvailable(mContext)) {
            getcommunitytextResponseCall();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @SuppressLint("LogNotTimber")
    public void getcommunitytextResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<CommunityTextResponse> call = apiInterface.getcommunitytextResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<CommunityTextResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<CommunityTextResponse> call, @NonNull Response<CommunityTextResponse> response) {
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"CommunityTextResponse" + new Gson().toJson(response.body()));
                        if(response.body().getData() != null) {
                            txt_community_text.setText(response.body().getData());
                        }

                    }



                }

            }
            @Override
            public void onFailure(@NonNull Call<CommunityTextResponse> call,@NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"CommunityTextResponse flr"+t.getMessage());
            }
        });

    }

}
