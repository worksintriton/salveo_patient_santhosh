package com.salveo.mysalveo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.appUtils.NumericKeyBoardTransformationMethod;
import com.salveo.mysalveo.requestpojo.LoginRequest;
import com.salveo.mysalveo.responsepojo.LoginResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "LoginActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_loginheader)
    ImageView img_loginheader;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_emailorphone)
    EditText edt_emailorphone;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_signup)
    TextView txt_signup;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_verifyotp)
    Button btn_verifyotp;

    Dialog alertDialog;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    String[] permissionString = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECEIVE_SMS,
            "check"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        edt_emailorphone.setTransformationMethod(new NumericKeyBoardTransformationMethod());


        avi_indicator.setVisibility(View.GONE);

        txt_signup.setOnClickListener(this);
        btn_verifyotp.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_signup:
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                break;
            case R.id.btn_verifyotp:
                verifyValidator();
                break;
        }

    }

    public void verifyValidator() {
        boolean can_proceed = true;
        if (edt_emailorphone.getText().toString().trim().equals("")) {
            edt_emailorphone.setError("Please enter your phone number");
            edt_emailorphone.requestFocus();
            can_proceed = false;
        }




        if (can_proceed) {
            insertmappermission();

        }

    }


    @SuppressLint("LogNotTimber")
    private void loginResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LoginResponse> call = apiInterface.loginResponseCall(RestUtils.getContentType(), loginRequest());
        Log.w(TAG,"ResendOTPResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<LoginResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"ResendOTPResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {


                        if(response.body().getData().getUser_details() != null) {

                            if(response.body().getData().getUser_details().getUser_type()==1){

                                Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                                Intent intent = new Intent(LoginActivity.this, VerifyOtpActivity.class);
                                intent.putExtra("phonemumber", response.body().getData().getUser_details().getUser_phone());
                                intent.putExtra("otp", response.body().getData().getUser_details().getOtp());
                                intent.putExtra("userstatus", response.body().getData().getUser_details().getUser_status());
                                intent.putExtra("usertype", response.body().getData().getUser_details().getUser_type());
                                intent.putExtra("userid", response.body().getData().getUser_details().get_id());

                                intent.putExtra("firstname", response.body().getData().getUser_details().getFirst_name());
                                intent.putExtra("lastname", response.body().getData().getUser_details().getLast_name());
                                intent.putExtra("useremail", response.body().getData().getUser_details().getUser_email());
                                intent.putExtra("fromactivity", TAG);
                                startActivity(intent);
                            }
                            else {
                                showErrorLoading("No Account Found");

                            }
                        }

                    }
                    else {
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("LoginResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private LoginRequest loginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUser_phone(edt_emailorphone.getText().toString());
        Log.w(TAG,"loginRequest"+ new Gson().toJson(loginRequest));
        return loginRequest;
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

    private void insertmappermission() {

        int haslocationpermission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            haslocationpermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) | checkSelfPermission(Manifest.permission.RECEIVE_SMS);

            if (haslocationpermission != PackageManager.PERMISSION_GRANTED) {

                if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) | !shouldShowRequestPermissionRationale(Manifest.permission.RECEIVE_SMS)) {

                    requestPermissions(permissionString,
                            REQUEST_CODE_ASK_PERMISSIONS);

                    return;
                }
                requestPermissions(permissionString,
                        REQUEST_CODE_ASK_PERMISSIONS);
            }else{
                if (new ConnectionDetector(LoginActivity.this).isNetworkAvailable(LoginActivity.this)) {

                    loginResponseCall();
                }

            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               /* startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();*/
                // Permission Granted
                 if (new ConnectionDetector(LoginActivity.this).isNetworkAvailable(LoginActivity.this)) {

                    loginResponseCall();
                }




            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onBackPressed() {
        new android.app.AlertDialog.Builder(LoginActivity.this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> LoginActivity.this.finishAffinity())
                .setNegativeButton("No", null)
                .show();
    }
}