package com.salveo.mysalveo.petlover;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.appUtils.ApplicationData;
import com.salveo.mysalveo.appUtils.NumericKeyBoardTransformationMethod;
import com.salveo.mysalveo.requestpojo.EmailOTPRequest;
import com.salveo.mysalveo.responsepojo.EmailOTPResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetVerifyEmailOtpActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "PetVerifyEmailOtpActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_verify)
    Button btn_verify;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_otp)
    EditText edt_otp;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_resend)
    TextView txt_resend;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_timer_count)
    TextView txt_timer_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_getotp)
    TextView txt_getotp;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.llresendotp)
    LinearLayout llresendotp;


    private CountDownTimer timer;

    private ApplicationData applicationData;
    private String phonenumber;
    private int otp;
    Dialog alertDialog;
    private String autoOTP;

    private String userstatus;
    private String fromactivity;


    private boolean isOTPExpired ;
    private String userid;
    private String token = "";
    private String firstname,lastname,useremail;


    @SuppressLint({"SetTextI18n", "LongLogTag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        applicationData = (ApplicationData) getApplication();

        ButterKnife.bind(this);
        edt_otp.setTransformationMethod(new NumericKeyBoardTransformationMethod());

        
        avi_indicator.setVisibility(View.GONE);
        txt_getotp.setText("You will get a OTP via Email");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            otp = extras.getInt("otp");

            userid = extras.getString("userid");
            userstatus = extras.getString("userstatus");
            firstname = extras.getString("firstname");
            lastname = extras.getString("lastname");
            useremail = extras.getString("useremail");
            fromactivity = extras.getString("fromactivity");
            Log.w(TAG,"Bundle "+" phonenumber : "+phonenumber+" otp :"+otp+" userstatus : "+userstatus+ " userid : "+userid);
        }

        img_back.setOnClickListener(this);
        btn_verify.setOnClickListener(this);
        txt_resend.setOnClickListener(this);
        startTimer();


    }

    private void startTimer() {
        isOTPExpired = false;
          long timer_milliseconds = 120000;
          timer = new CountDownTimer(timer_milliseconds, 1000) {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onTick(long millisUntilFinished) {
                llresendotp.setVisibility(View.GONE);
                txt_timer_count.setVisibility(View.VISIBLE);

                applicationData.setTimer_milliseconds(millisUntilFinished);
                txt_timer_count.setText(getResources().getString(R.string.resendotp)+" " + String.format("%02d : %02d ",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }

            @Override
            public void onFinish() {
                isOTPExpired = true;
                txt_timer_count.setVisibility(View.GONE);
                llresendotp.setVisibility(View.VISIBLE);
                timer.cancel();
            }
        };
        timer.start();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_verify:
                verifyValidator();
                break;
            case R.id.img_back:
                onBackPressed();
                break;

                case R.id.txt_resend:
                    if (new ConnectionDetector(PetVerifyEmailOtpActivity.this).isNetworkAvailable(PetVerifyEmailOtpActivity.this)) {
                        if(useremail != null){
                            resendOtpResponseCall();
                        }

                    }
                break;


        }


    }
    public void verifyValidator() {
        boolean can_proceed = true;
        String enteredotp = edt_otp.getText().toString();
        String responseotp = String.valueOf(otp);
         if (edt_otp.getText().toString().trim().equals("")) {
             edt_otp.setError("Please enter your OTP");
             edt_otp.requestFocus();
            can_proceed = false;
        }else if(!responseotp.equalsIgnoreCase(enteredotp)){
             edt_otp.setError("Invalid OTP.");
             edt_otp.requestFocus();
            can_proceed = false;
        }else if(isOTPExpired){
             edt_otp.setError("Your otp is expired. please regenerate otp. ");
             edt_otp.requestFocus();
             can_proceed = false;
         }

         if (can_proceed) {
             Intent intent = new Intent(PetVerifyEmailOtpActivity.this, PetLoverEditProfileActivity.class);
             intent.putExtra("verified","verified");
             intent.putExtra("useremail",useremail);
             intent.putExtra("firstname",firstname);
             intent.putExtra("lastname",lastname);
             startActivity(intent);





        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(timer != null){
            timer.cancel();
            timer = null;

        }
        finish();
    }

    @SuppressLint("LongLogTag")
    private void resendOtpResponseCall() {
        llresendotp.setVisibility(View.GONE);
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<EmailOTPResponse> call = apiInterface.emailOTPResponseCall(RestUtils.getContentType(), emailOTPRequest());
        Log.w(TAG,"ResendOTPResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<EmailOTPResponse>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NonNull Call<EmailOTPResponse> call, @NonNull Response<EmailOTPResponse> response) {
                  avi_indicator.smoothToHide();
                Log.w(TAG,"ResendOTPResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        edt_otp.setText("");
                        startTimer();
                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                       if(response.body().getData().getOtp() != 0) {
                           otp = response.body().getData().getOtp();
                       }

                    } else {
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<EmailOTPResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("ResendOTPResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private EmailOTPRequest emailOTPRequest() {
        /*
         * user_email : mohammedimthi2395@gmail.com
         */
        EmailOTPRequest emailOTPRequest = new EmailOTPRequest();
        emailOTPRequest.setUser_email(useremail);
        Log.w(TAG,"EmailOTPRequest "+ new Gson().toJson(emailOTPRequest));
        return emailOTPRequest;
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

    @Override
    protected void onPause() {
        super.onPause();

    }



}