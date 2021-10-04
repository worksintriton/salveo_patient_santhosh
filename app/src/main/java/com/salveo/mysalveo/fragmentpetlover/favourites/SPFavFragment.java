package com.salveo.mysalveo.fragmentpetlover.favourites;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.PetLoverSPNewFavAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.FetchPetloverFavListRequest;
import com.salveo.mysalveo.responsepojo.FetchPetloverSPFavListResponse;
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

public class SPFavFragment extends Fragment implements View.OnClickListener {

    private String TAG = "SPFavFragment";


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_spfavlist)
    RecyclerView rv_spfavlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_load_more)
    Button btn_load_more;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    SessionManager session;
    String userid = "";
    private Context mContext;

    List<FetchPetloverSPFavListResponse.DataBean> dataBeanList;
    private Dialog alertDialog;

    public SPFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.w(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_s_p_fav, container, false);

        ButterKnife.bind(this, view);
        mContext = getActivity();


        avi_indicator.setVisibility(View.GONE);
        btn_load_more.setVisibility(View.GONE);

        btn_load_more.setOnClickListener(this);


        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

     //   userid = "603e262e2c2b43125f8cb801";

        Log.w(TAG," userid : "+userid);



        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
            spfavlistResponseCall();
        }

        refresh_layout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        refresh_layout.setEnabled(false);
                        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                            spfavlistResponseCall();

                        }

                    }
                }
        );

        return view;
    }

    @SuppressLint("LogNotTimber")
    private void spfavlistResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchPetloverSPFavListResponse> call = apiInterface.petloverspfavlistResponseCall(RestUtils.getContentType(), fetchPetloverFavListRequest());
        Log.w(TAG,"FetchPetloverSPFavListResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<FetchPetloverSPFavListResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<FetchPetloverSPFavListResponse> call, @NonNull Response<FetchPetloverSPFavListResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"FetchPetloverSPFavListResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {


                        if (response.body().getData() != null) {

                            dataBeanList = response.body().getData();
                            Log.w(TAG, "dataBeanList Size" + dataBeanList.size());
                            if (dataBeanList != null && dataBeanList.size()>0) {
                                Log.w(TAG, "if loop :true");
                                rv_spfavlist.setVisibility(View.VISIBLE);
                                txt_no_records.setVisibility(View.GONE);
                                setViewSPFavList(dataBeanList);
                            } else {
                                rv_spfavlist.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No favourites available");

                            }

                        }



                    }
                    else {
                        showErrorLoading(response.body().getMessage());
                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<FetchPetloverSPFavListResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("FetchPetloverSPFavListResponse", "--->" + t.getMessage());
            }
        });

    }
    private void setViewSPFavList(List<FetchPetloverSPFavListResponse.DataBean> spFavList) {

        Log.w(TAG, "adapter method :true");

        rv_spfavlist.setLayoutManager(new GridLayoutManager(mContext,2));
        rv_spfavlist.setItemAnimator(new DefaultItemAnimator());
        PetLoverSPNewFavAdapter petLoverSPNewFavAdapter = new PetLoverSPNewFavAdapter(mContext, spFavList,4);
        rv_spfavlist.setAdapter(petLoverSPNewFavAdapter);
    }
    private FetchPetloverFavListRequest fetchPetloverFavListRequest() {
        /*
         *  "user_id": "603e262e2c2b43125f8cb801
         */


        FetchPetloverFavListRequest fetchPetloverFavListRequest = new FetchPetloverFavListRequest();
        fetchPetloverFavListRequest.setUser_id(userid);
        Log.w(TAG,"fetchPetloverFavListRequest"+ new Gson().toJson(fetchPetloverFavListRequest));
        return fetchPetloverFavListRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
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
    public void onClick(View v) {

        if (v.getId() == R.id.btn_load_more) {
            setViewLoadMore();
        }

    }

    private void setViewLoadMore() {

        rv_spfavlist.setLayoutManager(new GridLayoutManager(mContext,2));
        rv_spfavlist.setItemAnimator(new DefaultItemAnimator());
        int size = dataBeanList.size();
        PetLoverSPNewFavAdapter petLoverSPNewFavAdapter = new PetLoverSPNewFavAdapter(mContext, dataBeanList,size);
        rv_spfavlist.setAdapter(petLoverSPNewFavAdapter);
    }
}