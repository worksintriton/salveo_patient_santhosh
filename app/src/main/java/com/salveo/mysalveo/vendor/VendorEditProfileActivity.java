package com.salveo.mysalveo.vendor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.EmailOTPRequest;
import com.salveo.mysalveo.requestpojo.ProfileUpdateRequest;
import com.salveo.mysalveo.responsepojo.EmailOTPResponse;
import com.salveo.mysalveo.responsepojo.ProfileUpdateResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorEditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "VendorEditProfileActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView  img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_firstname)
    EditText edt_firstname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_lastname)
    EditText edt_lastname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_email)
    EditText edt_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_verify_email)
    Button btn_verify_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_phone)
    TextView txt_phone;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_save_changes)
    Button btn_save_changes;

    private String firstname,lastname,useremail,phonenumber,userid,usertype,userstatus;
    private Dialog alertDialog;
    private String profileimage;

    private String verifyemailstatus;
    private boolean user_email_verification;
    private String verified = "notverified";
    private String refcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_lover_edit_profile);
        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");

        avi_indicator.setVisibility(View.GONE);

        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        firstname = user.get(SessionManager.KEY_FIRST_NAME);
        lastname = user.get(SessionManager.KEY_LAST_NAME);
        useremail = user.get(SessionManager.KEY_EMAIL_ID);
        phonenumber = user.get(SessionManager.KEY_MOBILE);
        userid = user.get(SessionManager.KEY_ID);
        usertype = user.get(SessionManager.KEY_TYPE);
        userstatus = user.get(SessionManager.KEY_PROFILE_STATUS);
        profileimage = user.get(SessionManager.KEY_PROFILE_IMAGE);
        verifyemailstatus = user.get(SessionManager.KEY_VERIFY_EMAIL_STATUS);
        refcode = user.get(SessionManager.KEY_REF_CODE);


        Log.w(TAG,"verifyemailstatus : "+verifyemailstatus);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            verified = extras.getString("verified");

        }

        if(firstname != null){
            edt_firstname.setText(firstname);
        } if(lastname != null){
            edt_lastname.setText(lastname);
        } if(useremail != null){
            edt_email.setText(useremail);
        } if(phonenumber != null){
            txt_phone.setText(phonenumber);
        }

        if(verifyemailstatus != null  && verifyemailstatus.equalsIgnoreCase("true") || verified != null && verified.equalsIgnoreCase("verified")){
            btn_verify_email.setText("Verified email");
            user_email_verification = true;
            btn_verify_email.setEnabled(false);

        }
        else{
            user_email_verification = false;
            btn_verify_email.setEnabled(true);
        }
        edt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString();
                Log.w(TAG,"afterTextChanged email : "+email+" useremail : "+useremail);

                if(!email.equalsIgnoreCase(useremail)){
                    btn_verify_email.setText("Verify email");
                    user_email_verification = false;
                    btn_verify_email.setEnabled(true);
                }else{
                    btn_verify_email.setText("Verified email");
                    user_email_verification = true;
                    btn_verify_email.setEnabled(false);
                }


            }
        });

        btn_save_changes.setOnClickListener(this);
        img_back.setOnClickListener(this);
        btn_verify_email.setOnClickListener(this);





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("LogNotTimber")
    private void profileUpdateResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ProfileUpdateResponse> call = apiInterface.profileUpdateResponseCall(RestUtils.getContentType(), profileUpdateRequest());
        Log.w(TAG,"profileUpdateResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ProfileUpdateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ProfileUpdateResponse> call, @NonNull Response<ProfileUpdateResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"profileUpdateResponseCall" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                       Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        if(response.body().getData() != null) {
                            if (response.body().getData().isUser_email_verification()) {
                                verifyemailstatus = "true";
                            }
                        }

                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        sessionManager.setIsLogin(true);
                        sessionManager.createLoginSession(
                                userid,
                                edt_firstname.getText().toString(),
                                edt_lastname.getText().toString(),
                                edt_email.getText().toString(),
                                txt_phone.getText().toString(),
                                String.valueOf(usertype),
                                userstatus,
                                profileimage,
                                verifyemailstatus,
                                refcode

                        );
                        Intent intent = new Intent(getApplicationContext(), VendorDashboardActivity.class);
                        startActivity(intent);
                        finish();


                    } else {
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<ProfileUpdateResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private ProfileUpdateRequest profileUpdateRequest() {
        /*
         * user_id : 5fd778437aa4cc1c6a1e5632
         * first_name : Sam
         * last_name : san
         * user_email : santhoshvsk94@gmail.com
         * user_email_verification
         */


        ProfileUpdateRequest profileUpdateRequest = new ProfileUpdateRequest();
        profileUpdateRequest.setFirst_name(edt_firstname.getText().toString().trim());
        profileUpdateRequest.setLast_name(edt_lastname.getText().toString().trim());
        profileUpdateRequest.setUser_email(edt_email.getText().toString());
        profileUpdateRequest.setUser_id(userid);
        profileUpdateRequest.setUser_email_verification(user_email_verification);
        Log.w(TAG,"profileUpdateRequest "+ new Gson().toJson(profileUpdateRequest));
        return profileUpdateRequest;
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

    public void updateProfileValidator() {
        boolean can_proceed = true;
        int firstnamelength = edt_firstname.getText().toString().trim().length();
        int lastnamelength = edt_firstname.getText().toString().trim().length();
        String emailAddress = edt_email.getText().toString().trim();
        String emailPattern = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";


        if (Objects.requireNonNull(edt_firstname.getText()).toString().trim().equals("") && Objects.requireNonNull(edt_lastname.getText()).toString().trim().equals("")){
            Toasty.warning(getApplicationContext(), "Please enter the fields", Toast.LENGTH_SHORT, true).show();
            can_proceed = false;
        } else if (edt_firstname.getText().toString().trim().equals("")) {
            edt_firstname.setError("Please enter first name");
            edt_firstname.requestFocus();
            can_proceed = false;
        }else if (firstnamelength > 25) {
            edt_firstname.setError("The maximum length for an first name is 25 characters.");
            edt_firstname.requestFocus();
            can_proceed = false;
        }
        else if (edt_lastname.getText().toString().trim().equals("")) {
            edt_lastname.setError("Please enter last name");
            edt_lastname.requestFocus();
            can_proceed = false;
        }
        else if (lastnamelength > 25) {
            edt_lastname.setError("The maximum length for an last name is 25 characters.");
            edt_lastname.requestFocus();
            can_proceed = false;
        }
        else if(!emailAddress.matches(emailPattern)){
            edt_email.setError("Please enter correct E_mail address");
            edt_email.requestFocus();
            can_proceed = false;
        }




        if (can_proceed) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                profileUpdateResponseCall();
            }

        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_save_changes:
                updateProfileValidator();
                break;

            case R.id.btn_verify_email:
                ValidEmailValidator();
                break;
        }
    }

    public void ValidEmailValidator() {
        boolean can_proceed = true;
        String emailAddress = edt_email.getText().toString().trim();
        String emailPattern = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";

        if (edt_email.getText().toString().trim().equals("")) {
            edt_email.setError("Please enter the email");
            edt_email.requestFocus();
            can_proceed = false;
        }else if(!emailAddress.matches(emailPattern)){
            edt_email.setError("Please enter correct Email address");
            edt_email.requestFocus();
            can_proceed = false;
        }

        if (can_proceed) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                emailOTPResponseCall(emailAddress);
            }
        }

    }
    private void emailOTPResponseCall(String emailid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<EmailOTPResponse> call = apiInterface.emailOTPResponseCall(RestUtils.getContentType(), emailOTPRequest(emailid));
        Log.w(TAG,"EmailOTPResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<EmailOTPResponse>() {
            @Override
            public void onResponse(@NonNull Call<EmailOTPResponse> call, @NonNull Response<EmailOTPResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"EmailOTPResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {
                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        if(response.body().getData() != null) {
                            Intent intent = new Intent(getApplicationContext(), VendorVerifyEmailOtpActivity.class);
                            intent.putExtra("useremail", response.body().getData().getEmail_id());
                            intent.putExtra("otp", response.body().getData().getOtp());
                            intent.putExtra("firstname", edt_firstname.getText().toString());
                            intent.putExtra("lastname", edt_lastname.getText().toString());
                            startActivity(intent);
                        }


                    } else {
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<EmailOTPResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("Email OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private EmailOTPRequest emailOTPRequest(String emailid) {
        /*
         * user_email : mohammedimthi2395@gmail.com
         */
        EmailOTPRequest emailOTPRequest = new EmailOTPRequest();
        emailOTPRequest.setUser_email(emailid);
        Log.w(TAG,"EmailOTPRequest "+ new Gson().toJson(emailOTPRequest));
        return emailOTPRequest;
    }
}