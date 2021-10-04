package com.salveo.mysalveo.doctor;

import android.Manifest;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.AddGovtIdPdfAdapter;
import com.salveo.mysalveo.adapter.AddPrescriptionsListAdapter;
import com.salveo.mysalveo.adapter.DiagnosiTypesListAdapter;
import com.salveo.mysalveo.adapter.SubDiagnosiTypesListAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.appUtils.FileUtil;
import com.salveo.mysalveo.appUtils.NumericKeyBoardTransformationMethod;
import com.salveo.mysalveo.interfaces.DiagnosisTypeListener;
import com.salveo.mysalveo.interfaces.SubDiagnosisTypeListener;
import com.salveo.mysalveo.requestpojo.AppoinmentCompleteRequest;
import com.salveo.mysalveo.requestpojo.DocBusInfoUploadRequest;
import com.salveo.mysalveo.requestpojo.PrescriptionCreateRequest;
import com.salveo.mysalveo.requestpojo.SubDiagnosisRequest;
import com.salveo.mysalveo.responsepojo.AppoinmentCompleteResponse;
import com.salveo.mysalveo.responsepojo.DiagnosisListResponse;
import com.salveo.mysalveo.responsepojo.FileUploadResponse;
import com.salveo.mysalveo.responsepojo.PrescriptionCreateResponse;
import com.salveo.mysalveo.responsepojo.SubDiagnosisListResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescriptionActivity extends AppCompatActivity implements DiagnosisTypeListener, SubDiagnosisTypeListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_tabletname)
    EditText et_tabletname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_quanity)
    EditText et_quanity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_consumption)
    EditText et_consumption;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.etdoctorcomments)
    EditText etdoctorcomments;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.chx_m)
    CheckBox chx_m;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.chx_a)
    CheckBox chx_a;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.chx_n)
    CheckBox chx_n;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.add)
    Button add;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_headername)
    LinearLayout ll_headername;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.container)
    LinearLayout container;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnsubmit)
    Button btnSubmit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.back_rela)
    RelativeLayout back_rela;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_diagnosis)
    LinearLayout ll_diagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_subdiagnosis)
    LinearLayout ll_subdiagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_manual_prescription)
    LinearLayout ll_manual_prescription;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_uploadImage)
    LinearLayout ll_uploadImage;

    //      @SuppressLint("NonConstantResourceId")
//      @BindView(R.id.sprdiagnosistype)
//      Spinner sprdiagnosistype;
//
//      @SuppressLint("NonConstantResourceId")
//      @BindView(R.id.rl_sub_diagnosis)
//      RelativeLayout rl_sub_diagnosis;
//
//      @SuppressLint("NonConstantResourceId")
//      @BindView(R.id.sprsub_diagnosis)
//      Spinner sprsub_diagnosis;
//
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_diagnosis)
    TextView txt_diagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_subdiagnosis)
    TextView txt_subdiagnosis;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rgprescription_method)
    RadioGroup rgprescription_method;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_prescriptiondetails)
    RecyclerView rv_prescriptiondetails;

    String TAG = "PrescriptionActivity";

    private String selectedRadioButton = "Manual";

    View addView;


    Button btn_done,btn_done1;

    ArrayList<FileUploadResponse>  govtIdPicResponse = new ArrayList<>();


    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    PrescriptionCreateRequest.PrescriptionDataBean prescriptionData;
    List<PrescriptionCreateRequest.PrescriptionDataBean> prescriptionDataList = new ArrayList<>();

    SessionManager session;

    private String Doctor_Name = "";
    private String Doctor_Image = "";
    private String Doctor_ID = "";
    private String Treatment_Done_by = "";
    private String Patient_Name = "";
    private String Patient_Image = "";
    private String Patient_ID = "";
    private String Family_ID = "";
    private String Family_Name = "";
    private String Family_Image = "";
    private String Date = "";
    private String Doctor_Email_id = "";
    private String Patient_Email_id = "";
    private String userid;
    private String appoinmentid;
    private String paymentmethod;
    private List<DiagnosisListResponse.DataBean> diagnosisList;

    MultipartBody.Part govIdPart;

    HashMap<String, String> hashMap_diagnosis_id = new HashMap<>();
    private List<SubDiagnosisListResponse.DataBean> subDiagnosisList;
    private String DiagnosisType;
    private String DiagnosisTypeId;
    private String SubDiagnosisType;

    private static final int SELECT_GOVTID_CAMERA = 1007;

    private static final int REQUEST_READ_GOVT_ID_PDF_PERMISSION = 788;

    private static final int REQUEST_GOVTID_CAMERA_PERMISSION_CODE = 792;

    private static final int SELECT_GOVTID_PICTURE = 1008;

    private static final int SELECT_GOVTID_PDF = 1003;

    private static final int REQUEST_READ_GOVTID_PIC_PERMISSION = 793;

    PrescriptionCreateRequest.PrescriptionDataBean.ConsumptionBean consumptionBean;

    DiagnosiTypesListAdapter diagnosiTypesListAdapter;

    AddGovtIdPdfAdapter addGovtIdPdfAdapter;

    SubDiagnosiTypesListAdapter subDiagnosiTypesListAdapter;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtx_uploadImage)
    EditText edtx_uploadImage;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtx_service_charge_amount)
    EditText edtx_service_charge_amount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_lbl_serviceamout)
    TextView txt_lbl_serviceamout;


    String currentDateandTime;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rcylr_uploadImage)
    RecyclerView rcylr_uploadImage;

    private final List<DocBusInfoUploadRequest.GovtIdPicBean> govtIdPicBeans = new ArrayList<>();

    int i =0;

    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        ButterKnife.bind(this);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        Doctor_Name = user.get(SessionManager.KEY_FIRST_NAME);
        Doctor_ID = user.get(SessionManager.KEY_ID);

        avi_indicator.setVisibility(View.GONE);

        txt_subdiagnosis.setVisibility(View.GONE);

        txt_lbl_serviceamout.setVisibility(View.GONE);
        edtx_service_charge_amount.setVisibility(View.GONE);



        /* *************** Get Current Date and Time ************************ */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        currentDateandTime = sdf.format(new Date());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appoinmentid = extras.getString("id");
            userid = extras.getString("patient_id");
            paymentmethod = extras.getString("paymentmethod");
            Log.w(TAG,"appoinmentid :"+" "+appoinmentid+" paymentmethod  : "+paymentmethod);
            Log.w(TAG,"userid :"+" "+userid);

        }

        if(paymentmethod != null && paymentmethod.equalsIgnoreCase("Cash")){
            txt_lbl_serviceamout.setVisibility(View.VISIBLE);
            edtx_service_charge_amount.setVisibility(View.VISIBLE);

        } else{
            txt_lbl_serviceamout.setVisibility(View.GONE);
            edtx_service_charge_amount.setVisibility(View.GONE);

        }

        edtx_uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseGovIDPdf();
            }
        });

        if (new ConnectionDetector(PrescriptionActivity.this).isNetworkAvailable(PrescriptionActivity.this)) {
            diagnosisListResponseCall();
        }

        back_rela.setOnClickListener(v -> onBackPressed());

//        sprdiagnosistype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @SuppressLint("LogNotTimber")
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
//                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
//                DiagnosisType = sprdiagnosistype.getSelectedItem().toString();
//                DiagnosisTypeId = hashMap_diagnosis_id.get(DiagnosisType);
//                subDiagnosisListResponseCall(DiagnosisTypeId);
//                Log.w(TAG, "DiagnosisTypeId : " + DiagnosisTypeId + " DiagnosisType :" + DiagnosisType);
//
//                if(DiagnosisType != null && !DiagnosisType.equalsIgnoreCase("Diagnosis Type")){
//                    rl_sub_diagnosis.setVisibility(View.VISIBLE);
//                }else{
//                    rl_sub_diagnosis.setVisibility(View.GONE);
//                }
//
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//        sprsub_diagnosis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
//                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
//                SubDiagnosisType = sprsub_diagnosis.getSelectedItem().toString();
//                Log.w(TAG, "SubDiagnosisType :" + SubDiagnosisType);
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//
//            }
//        });

        ll_diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDiagnosisListType();

            }
        });

        ll_subdiagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSubDiagnosisListType();

            }
        });
        et_quanity.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        et_consumption.setTransformationMethod(new NumericKeyBoardTransformationMethod());




        add.setOnClickListener(new View.OnClickListener(){

            @SuppressLint({"InflateParams", "SetTextI18n"})
            @Override
            public void onClick(View arg0) {


                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                addView = layoutInflater.inflate(R.layout.row, null);
                final LinearLayout parent_linear_layout = addView.findViewById(R.id.parent_linear_layout);
                final TextView tvtabletname = addView.findViewById(R.id.tv_tabletname);
                tvtabletname.setText(et_tabletname.getText().toString());
                final TextView tvquantity = addView.findViewById(R.id.tv_quanity);
                tvquantity.setText(et_quanity.getText().toString());
                // final TextView tvconsumption = addView.findViewById(R.id.tv_consumption);
                // tvconsumption.setText(et_consumption.getText().toString());
                final Button buttonRemove = addView.findViewById(R.id.remove);
                buttonRemove.setText("Remove");


                final CheckBox chx_mg = addView.findViewById(R.id.chx_m);
                final CheckBox chx_an = addView.findViewById(R.id.chx_a);
                final CheckBox chx_ng = addView.findViewById(R.id.chx_n);

                chx_mg.setChecked(chx_m.isChecked());
                chx_an.setChecked(chx_a.isChecked());
                chx_ng.setChecked(chx_n.isChecked());




                consumptionBean =  new PrescriptionCreateRequest.PrescriptionDataBean.ConsumptionBean();

                if(chx_m.isChecked()){
                    consumptionBean.setMorning(chx_m.isChecked());
                }else{
                    consumptionBean.setMorning(false);
                }if(chx_a.isChecked()){
                    consumptionBean.setEvening(chx_a.isChecked());
                }else{
                    consumptionBean.setEvening(false);
                }if(chx_n.isChecked()){
                    consumptionBean.setNight(chx_n.isChecked());
                }else{
                    consumptionBean.setNight(false);
                }



                Log.w(TAG,"Consumptions checked : m "+chx_m.isChecked()+" a "+chx_a.isChecked()+" n "+chx_n.isChecked());

                buttonRemove.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        if (addView != null) {
                            //((LinearLayout) addView.getParent()).removeView(addView);
                            ViewGroup parent = (ViewGroup) addView.getParent();
                            if (parent != null) {
                                parent.removeView(addView);
                            }
                        }


                    }});

                parent_linear_layout.setVisibility(View.GONE);


                if(!et_tabletname.getText().toString().isEmpty() && !et_quanity.getText().toString().isEmpty() && chx_m.isChecked() || chx_a.isChecked() || chx_n.isChecked()){
                    Log.w(TAG,"prescriptionDataList  : tablet name "+et_tabletname.getText().toString()+" qty : "+et_quanity.getText().toString());
                    prescriptionData  = new PrescriptionCreateRequest.PrescriptionDataBean();
                    prescriptionData.setTablet_name(et_tabletname.getText().toString());
                    prescriptionData.setQuantity(et_quanity.getText().toString());
                    prescriptionData.setConsumption(consumptionBean);
                    prescriptionDataList.add(prescriptionData);

                    Log.w(TAG,"prescriptionDataList add : "+new Gson().toJson(prescriptionDataList));
                    ll_headername.setVisibility(View.VISIBLE);
                    // container.addView(addView, 0);
                    setView();
                    clearField();


                }
                else{
                    showErrorLoading("Please fill all the fields");
                    //ll_headername.setVisibility(View.GONE);
                }

            }});

        LayoutTransition transition = new LayoutTransition();
        container.setLayoutTransition(transition);


        btnSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {


                // prescriptionDataList.clear();

                String showallPrompt = "";

                int childCount = container.getChildCount();
                showallPrompt += "childCount: " + childCount + "\n\n";

                   /* for(int c=0; c<childCount; c++){
                        prescriptionData  = new PrescriptionCreateRequest.PrescriptionDataBean();
                        View childView = container.getChildAt(c);
                        TextView childTabletName = childView.findViewById(R.id.tv_tabletname);
                        String childTextTabletName = (String)(childTabletName.getText());

                        TextView childQuantity = childView.findViewById(R.id.tv_quanity);
                        String childTexQuantity = (String)(childQuantity.getText());

                        TextView childConsumption = childView.findViewById(R.id.tv_consumption);
                        String childTextConsumption = (String)(childConsumption.getText());



                        showallPrompt += c + ": " + childTextTabletName + "\n"+
                                c + ": " + childTexQuantity+"\n"+
                                c + ": " + childTextConsumption +"\n" ;

                        prescriptionData.setTablet_name(childTextTabletName);
                        prescriptionData.setQuantity(childTexQuantity);
                        prescriptionData.setConsumption(consumptionBean);
                        prescriptionDataList.add(prescriptionData);

                    }*/


                //  Toast.makeText(PrescriptionExActivity.this, showallPrompt, Toast.LENGTH_LONG).show();

                Log.w(TAG,"showallPrompt-->"+showallPrompt);

                if(validSelectDiagnosisType()){
                    if(validdSubDiagnosisType()){
                        if(etdoctorcomments.getText().toString().isEmpty()){
                            etdoctorcomments.setError("Please enter the comments ");
                            etdoctorcomments.requestFocus();
                        }
                        else if(paymentmethod != null && paymentmethod.equalsIgnoreCase("Cash")){
                            if(edtx_service_charge_amount.getText().toString().isEmpty()){
                                edtx_service_charge_amount.setError("Please enter the service amount ");
                                edtx_service_charge_amount.requestFocus();
                            }else{
                                if(prescriptionDataList.isEmpty()&&selectedRadioButton.equalsIgnoreCase("Manual")){
                                    showErrorLoading("Please fill the prescription fields");
                                }
                                else if(govtIdPicBeans.isEmpty()&&selectedRadioButton.equalsIgnoreCase("Upload Image")){
                                    showErrorLoading("Please Upload Prescription Image");
                                }
                                else{

                                    if (new ConnectionDetector(PrescriptionActivity.this).isNetworkAvailable(PrescriptionActivity.this)) {
                                        if(Treatment_Done_by.equalsIgnoreCase("Self")){
                                            Family_ID = "";
                                            Family_Name = "";

                                        }else{
                                            Family_Name = Family_Name;
                                            Family_ID = Family_ID;
                                        }

                                        String image = "";

                                        if(govtIdPicBeans!=null&&govtIdPicBeans.size()>0){

                                            image = govtIdPicBeans.get(0).getGovt_id_pic();
                                        }

                                        Log.w(TAG,"prescriptionDataList : "+new Gson().toJson(prescriptionDataList));

                                        Intent intent = new Intent(getApplicationContext(),PrescriptionDetailsActivity.class);
                                        intent.putExtra("Doctor_ID",Doctor_ID);
                                        intent.putExtra("Doctor_Comments",etdoctorcomments.getText().toString().trim());
                                        intent.putExtra("prescriptionDataList", (Serializable) prescriptionDataList);
                                        intent.putExtra("Treatment_Done_by", Treatment_Done_by);
                                        intent.putExtra("id", appoinmentid);
                                        intent.putExtra("image",image);
                                        intent.putExtra("selectedRadioButton",selectedRadioButton);
                                        intent.putExtra("userid", userid);
                                        intent.putExtra("DiagnosisType", DiagnosisType);
                                        intent.putExtra("SubDiagnosisType", SubDiagnosisType);
                                        intent.putExtra("Doctor_ID", Doctor_ID);
                                        intent.putExtra("Treatment_Done_by", Treatment_Done_by);
                                        intent.putExtra("paymentmethod", paymentmethod);
                                        intent.putExtra("servicecost", edtx_service_charge_amount.getText().toString());
                                        startActivity(intent);
                                        //prescriptionCreateRequestCall();
                                    }
                                }
                            }
                        }
                        else if(prescriptionDataList.isEmpty()&&selectedRadioButton.equalsIgnoreCase("Manual")){
                            showErrorLoading("Please fill the prescription fields");
                        }
                        else if(govtIdPicBeans.isEmpty()&&selectedRadioButton.equalsIgnoreCase("Upload Image")){
                            showErrorLoading("Please Upload Prescription Image");
                        }
                        else{

                            if (new ConnectionDetector(PrescriptionActivity.this).isNetworkAvailable(PrescriptionActivity.this)) {
                                if(Treatment_Done_by.equalsIgnoreCase("Self")){
                                    Family_ID = "";
                                    Family_Name = "";

                                }else{
                                    Family_Name = Family_Name;
                                    Family_ID = Family_ID;
                                }

                                String image = "";

                                if(govtIdPicBeans!=null&&govtIdPicBeans.size()>0){

                                    image = govtIdPicBeans.get(0).getGovt_id_pic();
                                }

                                Log.w(TAG,"prescriptionDataList : "+new Gson().toJson(prescriptionDataList));

                                Intent intent = new Intent(getApplicationContext(),PrescriptionDetailsActivity.class);
                                intent.putExtra("Doctor_ID",Doctor_ID);
                                intent.putExtra("Doctor_Comments",etdoctorcomments.getText().toString().trim());
                                intent.putExtra("prescriptionDataList", (Serializable) prescriptionDataList);
                                intent.putExtra("Treatment_Done_by", Treatment_Done_by);
                                intent.putExtra("id", appoinmentid);
                                intent.putExtra("image",image);
                                intent.putExtra("selectedRadioButton",selectedRadioButton);
                                intent.putExtra("userid", userid);
                                intent.putExtra("DiagnosisType", DiagnosisType);
                                intent.putExtra("SubDiagnosisType", SubDiagnosisType);
                                intent.putExtra("Doctor_ID", Doctor_ID);
                                intent.putExtra("Treatment_Done_by", Treatment_Done_by);
                                intent.putExtra("paymentmethod", paymentmethod);
                                intent.putExtra("servicecost", edtx_service_charge_amount.getText().toString());
                                startActivity(intent);
                                //prescriptionCreateRequestCall();
                            }
                        }
                    }

                }



            }});

        rgprescription_method.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = rgprescription_method.getCheckedRadioButtonId();
            RadioButton radioButton = rgprescription_method.findViewById(radioButtonID);
            selectedRadioButton = (String) radioButton.getText();
            Log.w(TAG,"selectedRadioButton" + selectedRadioButton);
            if(selectedRadioButton.equalsIgnoreCase("Manual")){
                ll_manual_prescription.setVisibility(View.VISIBLE);
                ll_uploadImage.setVisibility(View.GONE);
                govtIdPicBeans.clear();
            }
            else{
                ll_manual_prescription.setVisibility(View.GONE);
                ll_uploadImage.setVisibility(View.VISIBLE);
                try{
                    if(i==0){
                        if(addView !=null){
                            ((LinearLayout)addView.getParent()).removeView(addView);
                        }

                        clearField();
                        if(prescriptionDataList !=null && prescriptionDataList.size()>0){
                            prescriptionDataList.clear();
                        }
                        i = i+1;
                    }
                }catch (Exception e){
                    Log.e(TAG, "exception", e);
                    Log.e(TAG, "Exception: "+Log.getStackTraceString(e));
                    Log.e(TAG, Log.getStackTraceString(new Exception()));


                }



            }

        });

    }

    public void clearField(){
        et_tabletname.setText("");
        et_quanity.setText("");
        et_consumption.setText("");
        et_tabletname.requestFocus();
        chx_m.setChecked(false);
        chx_a.setChecked(false);
        chx_n.setChecked(false);

    }


    @SuppressLint("LogNotTimber")
    private void prescriptionCreateRequestCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PrescriptionCreateResponse> call = ApiService.prescriptionCreateRequestCall(RestUtils.getContentType(),prescriptionCreateRequest());
        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<PrescriptionCreateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<PrescriptionCreateResponse> call, @NonNull Response<PrescriptionCreateResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"PrescriptionCreateResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        appoinmentCompleteResponseCall();

                    }
                    else{

                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<PrescriptionCreateResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"PrescriptionCreateResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private PrescriptionCreateRequest prescriptionCreateRequest() {
        /*
         * doctor_id : 5ef3472a4b9bd73eb1cff539
         * Date : 23-10-2020 12:00 AM
         * Doctor_Comments : test
         * PDF_format :
         * Prescription_type : Image / PDF
         * Prescription_img : http://mysalveo.com/api/public/prescriptions/231afd32-6d68-4288-a8e5-1c599833c0e8.pdf
         * Doctor_ID : 5ef2c092c006bb0ed174c771
         * Prescription_data : [{"Quantity":"3","Tablet_name":"dolo","consumption":"twice"}]
         * Treatment_Done_by : Self
         * Appointment_ID
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        PrescriptionCreateRequest prescriptionCreateRequest = new PrescriptionCreateRequest();
        prescriptionCreateRequest.setDoctor_id(Doctor_ID);
        prescriptionCreateRequest.setDate(currentDateandTime);
        prescriptionCreateRequest.setDoctor_Comments(etdoctorcomments.getText().toString().trim());
        prescriptionCreateRequest.setPDF_format("");
        prescriptionCreateRequest.setPrescription_type("PDF");
        prescriptionCreateRequest.setPrescription_img("");
        prescriptionCreateRequest.setUser_id(userid);
        Log.w(TAG, "Doctor_ID" + userid);
        prescriptionCreateRequest.setPrescription_data(prescriptionDataList);
        prescriptionCreateRequest.setTreatment_Done_by(Treatment_Done_by);
        prescriptionCreateRequest.setAppointment_ID(appoinmentid);
        Log.w(TAG,"prescriptionCreateRequest"+ "--->" + new Gson().toJson(prescriptionCreateRequest));
        return prescriptionCreateRequest;
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
        Toasty.warning(getApplicationContext(), "This action is disabled in this screen..", Toast.LENGTH_SHORT, true).show();
    }


    private void appoinmentCompleteResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppoinmentCompleteResponse> call = apiInterface.appoinmentCompleteResponseCall(RestUtils.getContentType(), appoinmentCompleteRequest());
        Log.w(TAG,"AppoinmentCompleteResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppoinmentCompleteResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppoinmentCompleteResponse> call, @NonNull Response<AppoinmentCompleteResponse> response) {

                Log.w(TAG,"AppoinmentCompleteResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        startActivity(new Intent(PrescriptionActivity.this, DoctorDashboardActivity.class));





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
    private AppoinmentCompleteRequest appoinmentCompleteRequest() {
        /*
         * _id : 5fc639ea72fc42044bfa1683
         * completed_at : 23-10-2000 10 : 00 AM
         * appoinment_status : Completed
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        AppoinmentCompleteRequest appoinmentCompleteRequest = new AppoinmentCompleteRequest();
        appoinmentCompleteRequest.set_id(appoinmentid);
        appoinmentCompleteRequest.setCompleted_at(currentDateandTime);
        appoinmentCompleteRequest.setAppoinment_status("Completed");
        Log.w(TAG,"appoinmentCompleteRequest"+ "--->" + new Gson().toJson(appoinmentCompleteRequest));
        return appoinmentCompleteRequest;
    }



    @SuppressLint("LogNotTimber")
    public void diagnosisListResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<DiagnosisListResponse> call = apiInterface.diagnosisListResponseCall(RestUtils.getContentType());
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<DiagnosisListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<DiagnosisListResponse> call, @NonNull Response<DiagnosisListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        Log.w(TAG, "diagnosisListResponseCall" + new Gson().toJson(response.body()));

                        if(response.body().getData() != null) {
                            diagnosisList = response.body().getData();
                        }
                        if (diagnosisList != null && diagnosisList.size() > 0) {
                            //  setDiagnosisType(diagnosisList);
                        }
                    }


                }


            }


            @Override
            public void onFailure(@NonNull Call<DiagnosisListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "DiagnosisListResponse flr" + t.getMessage());
            }
        });

    }
    //    private void setDiagnosisType(List<DiagnosisListResponse.DataBean> diagnosisList) {
//        ArrayList<String> diagnosistypeArrayList = new ArrayList<>();
//        diagnosistypeArrayList.add("Diagnosis Type");
//        for (int i = 0; i < diagnosisList.size(); i++) {
//
//            String diagnosisType = diagnosisList.get(i).getDiagnosis();
//            hashMap_diagnosis_id.put(diagnosisList.get(i).getDiagnosis(), diagnosisList.get(i).get_id());
//
//            Log.w(TAG, "diagnosisType-->" + diagnosisType);
//            diagnosistypeArrayList.add(diagnosisType);
//
//            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(PrescriptionActivity.this, R.layout.spinner_item, diagnosistypeArrayList);
//            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
//            sprdiagnosistype.setAdapter(spinnerArrayAdapter);
//
//
//
//        }
//    }
    @SuppressLint("LogNotTimber")
    private void subDiagnosisListResponseCall(String diagnosis_id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SubDiagnosisListResponse> call = ApiService.subDiagnosisListResponseCall(RestUtils.getContentType(), subDiagnosisRequest(diagnosis_id));
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<SubDiagnosisListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SubDiagnosisListResponse> call, @NonNull Response<SubDiagnosisListResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "BreedTypeResponse" + "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null) {
                            subDiagnosisList = response.body().getData();
                            if (subDiagnosisList != null && subDiagnosisList.size() > 0) {


                            }else{
                                ll_subdiagnosis.setVisibility(View.GONE);
                            }
                        }

                    }

                }


            }


            @Override
            public void onFailure(@NonNull Call<SubDiagnosisListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG, "SubDiagnosisListResponse flr" + "--->" + t.getMessage());
            }
        });

    }

//    private void setSubDiagnosisType(List<SubDiagnosisListResponse.DataBean> subDiagnosisList) {
//        ArrayList<String> subDiagnosisArrayList = new ArrayList<>();
//        subDiagnosisArrayList.add("SubDiagnosis Type");
//        for (int i = 0; i < subDiagnosisList.size(); i++) {
//
//            String SubDiagnosisType = subDiagnosisList.get(i).getSub_diagnosis();
//
//            Log.w(TAG, "SubDiagnosisType-->" + SubDiagnosisType);
//            subDiagnosisArrayList.add(SubDiagnosisType);
//
//            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(PrescriptionActivity.this, R.layout.spinner_item, subDiagnosisArrayList);
//            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
//            sprsub_diagnosis.setAdapter(spinnerArrayAdapter);
//
//
//        }
//    }

    @SuppressLint("LogNotTimber")
    private SubDiagnosisRequest subDiagnosisRequest(String diagnosis_id) {
        SubDiagnosisRequest subDiagnosisRequest = new SubDiagnosisRequest();
        subDiagnosisRequest.setDiagnosis_id(diagnosis_id);
        Log.w(TAG, "subDiagnosisRequest" + "--->" + new Gson().toJson(subDiagnosisRequest));
        return subDiagnosisRequest;
    }

    public boolean validSelectDiagnosisType() {
        if (DiagnosisType != null && DiagnosisType.equalsIgnoreCase("Diagnosis Type")) {
            final AlertDialog alertDialog = new AlertDialog.Builder(PrescriptionActivity.this).create();
            alertDialog.setMessage(getString(R.string.err_msg_type_of_diagnosis));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    (dialog, which) -> alertDialog.cancel());
            alertDialog.show();

            return false;
        }

        return true;
    }

    public boolean validdSubDiagnosisType() {
        if (SubDiagnosisType != null && SubDiagnosisType.equalsIgnoreCase("SubDiagnosis Type")) {
            final AlertDialog alertDialog = new AlertDialog.Builder(PrescriptionActivity.this).create();
            alertDialog.setMessage(getString(R.string.err_msg_type_of_sub_diagnosis));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    (dialog, which) -> alertDialog.cancel());
            alertDialog.show();

            return false;
        }

        return true;
    }

    private void showDiagnosisListType() {

        try {

            Dialog dialog = new Dialog(PrescriptionActivity.this);
            dialog.setContentView(R.layout.alert_diagnosis_layout);
            dialog.setCanceledOnTouchOutside(false);

            ImageView img_close = dialog.findViewById(R.id.img_close);
            btn_done = dialog.findViewById(R.id.btn_done);
            LinearLayout ll_diagnosistype = dialog.findViewById(R.id.ll_diagnosistype);
            RecyclerView rv_diagnosistype = dialog.findViewById(R.id.rv_diagnosistype);
            TextView tv_norecords = dialog.findViewById(R.id.tv_norecords);
            EditText edt_search_diagnosistype = dialog.findViewById(R.id.edt_search_diagnosistype);

            btn_done.setVisibility(View.GONE);
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            if(diagnosisList != null && diagnosisList.size()>0){
                rv_diagnosistype.setVisibility(View.VISIBLE);
                tv_norecords.setVisibility(View.GONE);
                setView(rv_diagnosistype,diagnosisList);

            }else{
                rv_diagnosistype.setVisibility(View.GONE);
                tv_norecords.setVisibility(View.VISIBLE);
                tv_norecords.setText("No Diagnosis Type Found");
            }


            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });

            btn_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    txt_diagnosis.setText(DiagnosisType);

                    txt_subdiagnosis.setVisibility(View.GONE);

                    for (int i=0;i<diagnosisList.size();i++){
                        diagnosisList.get(i).setSelected(false);
                    }

                    diagnosiTypesListAdapter.notifyDataSetChanged();

                    subDiagnosisListResponseCall(DiagnosisTypeId);

                    dialog.dismiss();

                }
            });


            edt_search_diagnosistype.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @SuppressLint("LogNotTimber")
                @Override
                public void afterTextChanged(Editable editable) {
                    //after the change calling the method and passing the search input
                    filter(editable.toString());
                    Log.w(TAG,"afterTextChanged : "+editable.toString());
                }
            });



            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }

    private void setView(RecyclerView rv_diagnosistype, List<DiagnosisListResponse.DataBean> diagnosisList) {

        rv_diagnosistype.setNestedScrollingEnabled(true);
        rv_diagnosistype.setLayoutManager(new LinearLayoutManager(this));
        rv_diagnosistype.setItemAnimator(new DefaultItemAnimator());
        diagnosiTypesListAdapter = new DiagnosiTypesListAdapter(getApplicationContext(), diagnosisList,this);
        rv_diagnosistype.setAdapter(diagnosiTypesListAdapter);

    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        List<DiagnosisListResponse.DataBean> dataBeanList = new ArrayList<>();


        //looping through existing elements
        for (DiagnosisListResponse.DataBean s : diagnosisList) {
            //if the existing elements contains the search input
            if (s.getDiagnosis().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                dataBeanList.add(s);
            }
        }

        Log.w(TAG, "dataBeanList" + new Gson().toJson(dataBeanList));
        //calling a method of the adapter class and passing the filtered list
        diagnosiTypesListAdapter.filterList(dataBeanList);
    }


    @Override
    public void diagnosisTypeSelectListener(String id, String diagnosis) {

        Log.w(TAG, "Diagnosis ID " + id + "Diagnosis Type "+diagnosis);

        DiagnosisTypeId = id;

        DiagnosisType = diagnosis;

        btn_done.setVisibility(View.VISIBLE);


    }

    //Sub Diagnosis

    private void showSubDiagnosisListType() {

        try {

            Dialog dialog = new Dialog(PrescriptionActivity.this);
            dialog.setContentView(R.layout.alert_subdiagnosis_layout);
            dialog.setCanceledOnTouchOutside(false);

            ImageView img_close = dialog.findViewById(R.id.img_close);
            btn_done1 = dialog.findViewById(R.id.btn_done1);
            LinearLayout ll_diagnosistype = dialog.findViewById(R.id.ll_diagnosistype);
            RecyclerView rv_diagnosistype = dialog.findViewById(R.id.rv_diagnosistype);
            TextView tv_norecords = dialog.findViewById(R.id.tv_norecords);
            EditText edt_search_diagnosistype = dialog.findViewById(R.id.edt_search_diagnosistype);

            btn_done1.setVisibility(View.GONE);
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            btn_done1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    txt_subdiagnosis.setVisibility(View.VISIBLE);

                    txt_subdiagnosis.setText(SubDiagnosisType);

                    for (int i=0;i<subDiagnosisList.size();i++){
                        subDiagnosisList.get(i).setSelected(false);
                    }

                    subDiagnosiTypesListAdapter.notifyDataSetChanged();


                    dialog.dismiss();


                }
            });

            if(subDiagnosisList != null && subDiagnosisList.size()>0){
                rv_diagnosistype.setVisibility(View.VISIBLE);
                tv_norecords.setVisibility(View.GONE);
                setSubDiagnosisView(rv_diagnosistype,subDiagnosisList);

            }else{
                rv_diagnosistype.setVisibility(View.GONE);
                tv_norecords.setVisibility(View.VISIBLE);
                tv_norecords.setText("No Sub Diagnosis Found");
            }


            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });


            edt_search_diagnosistype.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @SuppressLint("LogNotTimber")
                @Override
                public void afterTextChanged(Editable editable) {
                    //after the change calling the method and passing the search input
                    filterSubDiagnosis(editable.toString());
                    Log.w(TAG,"afterTextChanged : "+editable.toString());
                }
            });



            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }

    private void setSubDiagnosisView(RecyclerView rv_diagnosistype, List<SubDiagnosisListResponse.DataBean> diagnosisList) {

        rv_diagnosistype.setNestedScrollingEnabled(true);
        rv_diagnosistype.setLayoutManager(new LinearLayoutManager(this));
        rv_diagnosistype.setItemAnimator(new DefaultItemAnimator());
        subDiagnosiTypesListAdapter = new SubDiagnosiTypesListAdapter(getApplicationContext(), diagnosisList,this);
        rv_diagnosistype.setAdapter(subDiagnosiTypesListAdapter);

    }

    private void filterSubDiagnosis(String text) {
        //new array list that will hold the filtered data
        List<SubDiagnosisListResponse.DataBean> dataBeanList = new ArrayList<>();


        //looping through existing elements
        for (SubDiagnosisListResponse.DataBean s : subDiagnosisList) {
            //if the existing elements contains the search input
            if (s.getSub_diagnosis().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                dataBeanList.add(s);
            }
        }

        Log.w(TAG, "dataBeanList" + new Gson().toJson(dataBeanList));
        //calling a method of the adapter class and passing the filtered list
        subDiagnosiTypesListAdapter.filterList(dataBeanList);
    }


    @Override
    public void subdiagnosisTypeSelectListener(String id, String diagnosis) {

        Log.w(TAG, "SubDiagnosis ID " + id + "SubDiagnosis Type "+diagnosis);

        SubDiagnosisType = diagnosis;

        btn_done1.setVisibility(View.VISIBLE);

    }

    private void chooseGovIDPdf() {

        if(govtIdPicBeans.size()>=1){

            Toasty.warning(getApplicationContext(), "Sorry you can't Add more than 1", Toast.LENGTH_SHORT).show();

        }

        else {

            final CharSequence[] items = {"Take Photo", "Pick from Gallery","Cancel"};
            //AlertDialog.Builder alert=new AlertDialog.Builder(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(PrescriptionActivity.this);
            builder.setTitle("Choose option");
            builder.setItems(items, (dialog, item) -> {
                if (items[item].equals("Take Photo"))
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(PrescriptionActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_GOVTID_CAMERA_PERMISSION_CODE);
                    }
                    else
                    {


                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        startActivityForResult(intent, SELECT_GOVTID_CAMERA);
                    }

                }

                else if (items[item].equals("Pick from Gallery"))
                {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_GOVTID_PIC_PERMISSION);
                    }

                    else{

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_GOVTID_PICTURE);


                    }
                }


                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            });
            builder.show();

        }


    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_READ_GOVT_ID_PDF_PERMISSION) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), SELECT_GOVTID_PDF);

            } else {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Permisson Required")
                        .setContentText("Plz Allow Permissions for choosing Pdf Files ")
                        .setConfirmText("Ok")
                        .setConfirmClickListener(sDialog -> {

                            sDialog.dismissWithAnimation();

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_GOVT_ID_PDF_PERMISSION);
                            }


                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();

                            }
                        })
                        .show();

            }

        }


        else if (requestCode == REQUEST_READ_GOVTID_PIC_PERMISSION) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_GOVTID_PICTURE);

            } else {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Permisson Required")
                        .setContentText("Plz Allow Permissions for choosing Images from Gallery ")
                        .setConfirmText("Ok")
                        .setConfirmClickListener(sDialog -> {

                            sDialog.dismissWithAnimation();

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_GOVTID_PIC_PERMISSION);
                            }


                        })
                        .setCancelButton("Cancel", SweetAlertDialog::dismissWithAnimation)
                        .show();

            }

        }

        else if (requestCode == REQUEST_GOVTID_CAMERA_PERMISSION_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //    intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                startActivityForResult(intent, SELECT_GOVTID_CAMERA);

            } else {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Permisson Required")
                        .setContentText("Plz Allow Camera for taking picture")
                        .setConfirmText("Ok")
                        .setConfirmClickListener(sDialog -> {

                            sDialog.dismissWithAnimation();

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_GOVTID_CAMERA_PERMISSION_CODE);
                            }


                        })
                        .setCancelButton("Cancel", SweetAlertDialog::dismissWithAnimation)
                        .show();

            }

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_GOVTID_CAMERA)
        {
            Bitmap photo = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");

            File file = new File(getFilesDir(), "Petfolio1" + ".jpg");

            OutputStream os;
            try {
                os = new FileOutputStream(file);
                if (photo != null) {
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, os);
                }
                os.flush();
                os.close();
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
            }

            RequestBody requestFile = RequestBody.create(MediaType.parse("image*/"), file);

            govIdPart = MultipartBody.Part.createFormData("sampleFile",  Doctor_ID+currentDateandTime+file.getName(), requestFile);

            uploadGovtIDPdf();

        }

        else if(requestCode == SELECT_GOVTID_PICTURE){

            try {
                if (resultCode == Activity.RESULT_OK) {

                    Log.w("VALUEEEEEEE1111", " " + data);

                    Uri selectedImageUri = data.getData();

                    Log.w("selectedImageUri", " " + selectedImageUri);

                    String filename = null;
                    if (selectedImageUri != null) {
                        filename = getFileName(selectedImageUri);
                    }

                    Log.w("filename", " " + filename);

                    String filePath = FileUtil.getPath(PrescriptionActivity.this,selectedImageUri);

                    assert filePath != null;

                    File file = new File(filePath); // initialize file here

                    long length = file.length() / 1024; // Size in KB

                    Log.w("filesize", " " + length);

                    govIdPart = MultipartBody.Part.createFormData("sampleFile", Doctor_ID+currentDateandTime+file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

                    uploadGovtIDPdf();


                }
            } catch (Exception e) {

                Log.w("Exception", " " + e);
            }

        }

        else if(requestCode== SELECT_GOVTID_PDF){

            try {
                if (resultCode == Activity.RESULT_OK)
                {

                    Log.w("URI", " " + data);

                    Uri selectedFileUri = data.getData();

                    Log.w("selectedFileUri", " " + selectedFileUri);

                    String filename = getFileName(selectedFileUri);

                    Log.w("filename", " " + filename);

                    String filePath = FileUtil.getPath(PrescriptionActivity.this,selectedFileUri);

                    assert filePath != null;

                    File file = new File(filePath); // initialize file here

                    long length = file.length() / 1024; // Size in KB

                    Log.w("filesize", " " + length);

//                    if(length>200){
//
//                        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                                .setTitleText("File Size")
//                                .setContentText("Plz choose file size less than 200 kb ")
//                                .setConfirmText("Ok")
//                                .show();
//                    }
//
//                    else{

                    govIdPart = MultipartBody.Part.createFormData("sampleFile", Doctor_ID + currentDateandTime + file.getName(), RequestBody.create(MediaType.parse("pdf/*"), file));

                    uploadGovtIDPdf();
                    //}

                }
            } catch (Exception e) {

                Log.w("Exception", " " + e);
            }
        }

    }

    @SuppressLint("LogNotTimber")
    private void uploadGovtIDPdf() {

        avi_indicator.show();

        RestApiInterface apiInterface = APIClient.getImageClient().create(RestApiInterface.class);

        //RestApiInterface ApiService = RetrofitClient.getApiService();

        Call<FileUploadResponse> call = apiInterface.getImageStroeResponse(govIdPart);

//        Call<ImageFileUploadResponse> call = apiInterface.getImageStroeResponse(getProfileImagePicMultipart());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FileUploadResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FileUploadResponse> call, @NonNull Response<FileUploadResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"PdfFileLink"+ "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {


                        DocBusInfoUploadRequest.GovtIdPicBean govtIdPicBean = new DocBusInfoUploadRequest.GovtIdPicBean (response.body().getData());

                        govtIdPicBeans.add(govtIdPicBean);

                        if(govtIdPicBeans!=null&&govtIdPicBeans.size()>0){

                            addGovtIdPdfAdapter = new AddGovtIdPdfAdapter(getApplicationContext(), govtIdPicBeans);

                            rcylr_uploadImage.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                            rcylr_uploadImage.setAdapter(addGovtIdPdfAdapter);

                        }


                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<FileUploadResponse> call, @NonNull Throwable t) {
                // avi_indicator.smoothToHide();
                Log.w(TAG,"ServerUrlImagePath"+ "On failure working"+ t.getMessage());
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void setView() {
        rv_prescriptiondetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_prescriptiondetails.setItemAnimator(new DefaultItemAnimator());
        AddPrescriptionsListAdapter addPrescriptionsListAdapter = new AddPrescriptionsListAdapter(getApplicationContext(), prescriptionDataList);
        rv_prescriptiondetails.setAdapter(addPrescriptionsListAdapter);

    }

}
