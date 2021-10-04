package com.salveo.mysalveo.doctor;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.DoctorPrescriptionsDetailsAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.PrescriptionCreateRequest;
import com.salveo.mysalveo.requestpojo.PrescriptionDetailsRequest;
import com.salveo.mysalveo.responsepojo.PrescriptionFetchResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorPrescriptionDetailsActivity extends AppCompatActivity {
    String TAG = "DoctorPrescriptionDetailsActivity";
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    PrescriptionCreateRequest.PrescriptionDataBean prescriptionData;

    SessionManager session;
    private String userid;
    private String appoinmentid,doctor_id;
    private String url;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_doctorname)
    TextView txt_doctorname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dr_specialization)
    TextView txt_dr_specialization;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_webname)
    TextView txt_webname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_phone)
    TextView txt_phone;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_applogo)
    ImageView img_applogo;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_owners_name)
    TextView txt_owners_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_name)
    TextView txt_pet_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_type)
    TextView txt_pet_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_breed)
    TextView txt_breed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_gender)
    TextView txt_gender;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_weight)
    TextView txt_weight;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_age)
    TextView txt_age;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_diagnosis)
    LinearLayout ll_diagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_diagnosis)
    TextView txt_diagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_sub_diagnosis)
    LinearLayout ll_sub_diagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_sub_diagnosis)
    TextView txt_sub_diagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_allergies)
    LinearLayout ll_allergies;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_allergies)
    TextView txt_allergies;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_doctor_comment)
    LinearLayout ll_doctor_comment;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_doctor_comment)
    TextView txt_doctor_comment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_prescriptiondetails)
    RecyclerView rv_prescriptiondetails;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_signature)
    ImageView img_signature;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_ftr_doctorname)
    TextView txt_ftr_doctorname;

    private String concatenatedSpcNames= "";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_prescno)
    TextView txt_prescno;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_doctorid)
    TextView txt_doctorid;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_clinic_name)
    TextView txt_clinic_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_clinic_loc)
    TextView txt_clinic_loc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_clinic_number)
    TextView txt_clinic_number;

    private List<PrescriptionFetchResponse.DataBean.PrescriptionDataBean> prescriptionDataList;
    private String pdfUrl;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_manual_prescription)
    LinearLayout ll_manual_prescription;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_uploadImage_prescription)
    LinearLayout ll_uploadImage_prescription;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_prescriptiondetails)
    ImageView img_prescriptiondetails;

    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_prescription_details);
        ButterKnife.bind(this);
        Log.w(TAG,"Oncreate");




        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();


        ll_doctor_comment.setVisibility(View.GONE);
        ll_allergies.setVisibility(View.GONE);

       /* ll_allergies.setVisibility(View.GONE);
        ll_diagnosis.setVisibility(View.GONE);
        ll_sub_diagnosis.setVisibility(View.GONE);
        ll_doctor_comment.setVisibility(View.GONE);
        txt_diagnosis.setVisibility(View.GONE);
        txt_sub_diagnosis.setVisibility(View.GONE);
        txt_doctor_comment.setVisibility(View.GONE);*/





        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appoinmentid = extras.getString("id");
            userid = extras.getString("userid");
            doctor_id = extras.getString("doctor_id");
            Log.w(TAG,"AppointID :"+" "+appoinmentid);
            Log.w(TAG,"userid :"+" "+userid);
            Log.w(TAG,"doctorid :"+" "+doctor_id);

        }

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.prescriptiondetails));

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        img_sos.setVisibility(View.GONE);
        img_notification.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
        img_profile.setVisibility(View.GONE);


        if(appoinmentid != null){
            prescriptionDetailsResponseCall();
        }











    }




    private void prescriptionDetailsResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PrescriptionFetchResponse> call = ApiService.prescriptionDetailsResponseCall(RestUtils.getContentType(),prescriptionDetailsRequest());
        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<PrescriptionFetchResponse>() {
            @SuppressLint({"SetJavaScriptEnabled", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<PrescriptionFetchResponse> call, @NonNull Response<PrescriptionFetchResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"PrescriptionCreateResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData()!=null) {


                            if (response.body().getData().getDoctorname() != null && !response.body().getData().getDoctorname().isEmpty()) {
                                txt_doctorname.setText(response.body().getData().getDoctorname());
                                txt_ftr_doctorname.setText(response.body().getData().getDoctorname());
                            }
                            else {
                                txt_doctorname.setText("");
                                txt_ftr_doctorname.setText("");
                            }

                            if (response.body().getData().getDoctor_speci() != null && response.body().getData().getDoctor_speci().size() > 0) {
                                List<PrescriptionFetchResponse.DataBean.DoctorSpeciBean> specializationBeanList = response.body().getData().getDoctor_speci();
                                for (int i = 0; i < specializationBeanList.size(); i++) {
                                    concatenatedSpcNames += specializationBeanList.get(i).getSpecialization();
                                    if (i < specializationBeanList.size() - 1)
                                        concatenatedSpcNames += ", ";
                                }

                                txt_dr_specialization.setText(concatenatedSpcNames);

                            }

                            if (response.body().getData().getWeb_name() != null && !response.body().getData().getWeb_name().isEmpty()) {
                                txt_webname.setText(response.body().getData().getWeb_name());
                            }
                            else {
                                txt_webname.setText("");
                            }

                            if (response.body().getData().getPhone_number() != null && !response.body().getData().getPhone_number().isEmpty()) {
                                txt_phone.setText("Phone: " + response.body().getData().getPhone_number());
                            }
                            else {
                                txt_phone.setText("");
                            }

                           /* if (response.body().getData().getApp_logo() != null && !response.body().getData().getApp_logo().isEmpty()) {
                                Glide.with(DoctorPrescriptionDetailsActivity.this)
                                        .load(response.body().getData().getApp_logo())
                                        .into(img_applogo);
                            }
                            else {
                                Glide.with(DoctorPrescriptionDetailsActivity.this)
                                        .load(APIClient.PROFILE_IMAGE_URL)
                                        .into(img_applogo);
                            }*/

                            if (response.body().getData().getDigital_sign() != null && !response.body().getData().getDigital_sign().isEmpty()) {
                                Glide.with(DoctorPrescriptionDetailsActivity.this)
                                        .load(response.body().getData().getDigital_sign())
                                        .into(img_signature);
                            }
                            else {
                                Glide.with(DoctorPrescriptionDetailsActivity.this)
                                        .load(APIClient.PROFILE_IMAGE_URL)
                                        .into(img_signature);
                            }

                            if(response.body().getData().getOwner_name() != null && !response.body().getData().getOwner_name().isEmpty()){
                                txt_owners_name.setText(response.body().getData().getOwner_name());
                            }else{
                                txt_owners_name.setText("");
                            }
                            if(response.body().getData().getName() != null && !response.body().getData().getName().isEmpty()){
                                txt_pet_name.setText(response.body().getData().getName());
                            }else{
                                txt_pet_name.setText("");
                            }
                            if(response.body().getData().getRelation() != null && !response.body().getData().getRelation() .isEmpty()){
                                txt_pet_type.setText(response.body().getData().getRelation());
                            }else{
                                txt_pet_type.setText("");
                            }if(response.body().getData().getHeight() != null && !response.body().getData().getHeight().isEmpty()){
                                txt_breed.setText(response.body().getData().getHeight());
                            }else{
                                txt_breed.setText("");
                            }if(response.body().getData().getGender() != null && !response.body().getData().getGender().isEmpty()){
                                txt_gender.setText(response.body().getData().getGender());
                            }else{
                                txt_gender.setText("");
                            }if(response.body().getData().getWeight()!=null&&!response.body().getData().getWeight().isEmpty()){
                                txt_weight.setText(""+response.body().getData().getWeight());
                            }else{
                                txt_weight.setText("");
                            }
                            if(response.body().getData().getAge() != 0){
                                txt_age.setText(""+response.body().getData().getAge());
                            }else{
                                txt_age.setText("");
                            }
                            if(response.body().getData().getDiagnosis() != null && !response.body().getData().getDiagnosis().isEmpty()){
                                txt_diagnosis.setText(response.body().getData().getDiagnosis());
                            }else{
                                txt_diagnosis.setText("");
                            }
                            if(response.body().getData().getSub_diagnosis() != null && !response.body().getData().getSub_diagnosis().isEmpty()){
                                txt_sub_diagnosis.setText(response.body().getData().getSub_diagnosis());
                            }else{
                                txt_sub_diagnosis.setText("");
                            }

                            if(response.body().getData().getDoctor_Comments() != null && !response.body().getData().getDoctor_Comments().isEmpty() ){
                                ll_doctor_comment.setVisibility(View.VISIBLE);
                                txt_doctor_comment.setText(response.body().getData().getDoctor_Comments());
                            }
                            else{
                                ll_doctor_comment.setVisibility(View.GONE);
                            }

                            if(response.body().getData().getAnymedicalinfo() != null && !response.body().getData().getAnymedicalinfo().isEmpty() ){

                                txt_prescno.setText("" +response.body().getData().getAnymedicalinfo());
                            }

                            else {

                                txt_prescno.setText("");
                            }

                            if(response.body().getData().getDoctor_id() != null && !response.body().getData().getDoctor_id().isEmpty() ){

                                txt_doctorid.setText(""+response.body().getData().getDoctor_id());
                            }

                            else {

                                txt_doctorid.setText("");
                            }


                            if(response.body().getData().getHealth_issue_title() != null && !response.body().getData().getHealth_issue_title().isEmpty() ){

                                txt_clinic_name.setText(response.body().getData().getHealth_issue_title());
                            }

                            else {

                                txt_clinic_name.setText("");
                            }
                       /*     if(response.body().getData().getClinic_no() != null && !response.body().getData().getClinic_no().isEmpty() ){

                                txt_clinic_number.setText(response.body().getData().getClinic_no());
                            }

                            else {

                                txt_clinic_number.setText("");
                            }

                            if(response.body().getData().getClinic_loc() != null && !response.body().getData().getClinic_loc().isEmpty() ){

                                txt_clinic_loc.setText(response.body().getData().getClinic_loc());
                            }

                            else {

                                txt_clinic_loc.setText("");
                            }*/


                            if(response.body().getData().getAllergies() != null && !response.body().getData().getAllergies().isEmpty() ){
                                ll_allergies.setVisibility(View.VISIBLE);
                                txt_allergies.setText(response.body().getData().getAllergies());
                            }
                            else{
                                ll_allergies.setVisibility(View.GONE);
                            }

                            if(response.body().getData().getPrescription_type()!=null&&!response.body().getData().getPrescription_type().isEmpty()){


                                if(response.body().getData().getPrescription_type().equals("PDF")){

                                    ll_manual_prescription.setVisibility(View.VISIBLE);

                                    ll_uploadImage_prescription.setVisibility(View.GONE);

                                    if(response.body().getData().getPrescription_data() != null){
                                        prescriptionDataList = response.body().getData().getPrescription_data();
                                        pdfUrl = response.body().getData().getPDF_format();

                                        if(prescriptionDataList.size()>0){
                                            rv_prescriptiondetails.setVisibility(View.VISIBLE);
                                            txt_no_records.setVisibility(View.GONE);
                                            setView();
                                        }else{
                                            rv_prescriptiondetails.setVisibility(View.GONE);
                                            txt_no_records.setVisibility(View.VISIBLE);

                                        }

                                    }

                                }

                                else {

                                    ll_manual_prescription.setVisibility(View.GONE);

                                    ll_uploadImage_prescription.setVisibility(View.VISIBLE);

                                    if(response.body().getData().getPrescription_img()!=null&&!response.body().getData().getPrescription_img().isEmpty()){

                                        Glide.with(DoctorPrescriptionDetailsActivity.this)
                                                .load(response.body().getData().getPrescription_img())
                                                .into(img_prescriptiondetails);

                                    }
                                    else {

                                        Glide.with(DoctorPrescriptionDetailsActivity.this)
                                                .load(APIClient.BANNER_IMAGE_URL)
                                                .into(img_prescriptiondetails);

                                    }


                                }
                            }

                            else {

                                ll_manual_prescription.setVisibility(View.GONE);

                                ll_uploadImage_prescription.setVisibility(View.GONE);

                            }

                        }


                    }
                    else{

                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<PrescriptionFetchResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"PrescriptionCreateResponseflr"+"--->" + t.getMessage());
            }
        });

    }

    private PrescriptionDetailsRequest prescriptionDetailsRequest() {
        /*
         * Appointment_ID
         */


        PrescriptionDetailsRequest prescriptionDetailsRequest = new PrescriptionDetailsRequest();
        prescriptionDetailsRequest.setAppointment_ID(appoinmentid);
        Log.w(TAG,"prescriptionDetailsRequest"+ "--->" + new Gson().toJson(prescriptionDetailsRequest));
        return prescriptionDetailsRequest;
    }

    public class WebViewController extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



    private void setView() {
        rv_prescriptiondetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_prescriptiondetails.setItemAnimator(new DefaultItemAnimator());
        DoctorPrescriptionsDetailsAdapter doctorPrescriptionsDetailsAdapter = new DoctorPrescriptionsDetailsAdapter(getApplicationContext(), prescriptionDataList);
        rv_prescriptiondetails.setAdapter(doctorPrescriptionsDetailsAdapter);

    }





}
