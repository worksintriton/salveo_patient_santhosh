package com.salveo.mysalveo.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.appUtils.NumericKeyBoardTransformationMethod;
import com.salveo.mysalveo.requestpojo.SOSUpdateRequest;
import com.salveo.mysalveo.responsepojo.SuccessResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSoSActivity extends AppCompatActivity {

    private String TAG = "EditSoSActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_contact_name)
    EditText edt_contact_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_contact_number)
    EditText edt_contact_number;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_save)
    Button btn_save;

    private int id;
    private String name,phone;

    private Dialog alertDialog;
    private String userid;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sos);
        ButterKnife.bind(this);

        edt_contact_number.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        avi_indicator.setVisibility(View.GONE);

        SessionManager   session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            id = extras.getInt("id");
            name = extras.getString("name");
            phone = extras.getString("phone");

            if(name != null){
                edt_contact_name.setText(name);
            }
            if(phone != null){
                edt_contact_number.setText(phone);
            }
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addYourContactValidator();
                }
            });

            img_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });



        }

    }

    public void addYourContactValidator() {
        boolean can_proceed = true;



        if (edt_contact_name.getText().toString().isEmpty() && edt_contact_number.getText().toString().isEmpty() ) {
            Toasty.warning(getApplicationContext(), "Please enter the fields", Toast.LENGTH_SHORT, true).show();
            can_proceed = false;
        } else if (edt_contact_name.getText().toString().trim().equals("")) {
            edt_contact_name.setError("Please enter contact name");
            edt_contact_name.requestFocus();
            can_proceed = false;
        }
        else if (edt_contact_number.getText().toString().trim().equals("")) {
            edt_contact_number.setError("Please enter phone number");
            edt_contact_number.requestFocus();
            can_proceed = false;
        }


        if (can_proceed) {
            if (new ConnectionDetector(EditSoSActivity.this).isNetworkAvailable(EditSoSActivity.this)) {
                SOSUpdateResponseCall();
            }

        }





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void SOSUpdateResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = apiInterface.SOSUpdateResponseCall(RestUtils.getContentType(), sosUpdateRequest());
        Log.w(TAG,"productEditResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {

                Log.w(TAG,"productEditResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        startActivity(new Intent(getApplicationContext(), SoSActivity.class));
                        finish();
                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"SuccessResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private SOSUpdateRequest sosUpdateRequest() {

        /*
         * user_id : 60e466f0e87f127291325203
         * id : 3
         * name : Imthi
         * phone : 98989989898
         */

        SOSUpdateRequest sosUpdateRequest = new SOSUpdateRequest();
        sosUpdateRequest.setUser_id(userid);
        sosUpdateRequest.setId(id);
        sosUpdateRequest.setName(edt_contact_name.getText().toString());
        sosUpdateRequest.setPhone(edt_contact_number.getText().toString());
        Log.w(TAG,"sosUpdateRequest"+ "--->" + new Gson().toJson(sosUpdateRequest));
        return sosUpdateRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
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
}