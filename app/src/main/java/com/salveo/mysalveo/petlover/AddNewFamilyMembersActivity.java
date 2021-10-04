package com.salveo.mysalveo.petlover;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.AddFamilyImageListAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.appUtils.FileUtil;
import com.salveo.mysalveo.requestpojo.FamilyMemberCreateRequest;
import com.salveo.mysalveo.responsepojo.FamilyMemberCreateResponse;
import com.salveo.mysalveo.responsepojo.FileUploadResponse;
import com.salveo.mysalveo.responsepojo.GetFamilyMemberResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AddNewFamilyMembersActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AdurFamMemOldActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_skip)
    TextView txt_skip;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sprrelationtype)
    Spinner sprrelationtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_continue)
    Button btn_continue;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_name)
    EditText edt_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_age)
    EditText edt_age;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_height)
    EditText edt_height;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_weight)
    EditText edt_weight;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtx_any_medical_information)
    EditText edtx_any_medical_information;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cdvw_uploadImage)
    CardView cdvw_uploadImage;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rggender)
    RadioGroup rggender;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_uploaded_images)
    RecyclerView rv_uploaded_images;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_uploadimage)
    ImageView img_uploadimage;

    List<FamilyMemberCreateRequest.PicBean> picBeanList = new ArrayList<>();

    List<GetFamilyMemberResponse.DataBean> getfamilymemberslist;

    Dialog alertDialog;

    String userid,strrelationtype="";

    int sprflag = 0;

    private String selectedRadioButton = "Male";

    public final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 1;
    private static final String CAMERA_PERMISSION = CAMERA ;
    private static final String READ_EXTERNAL_STORAGE_PERMISSION = READ_EXTERNAL_STORAGE;
    private static final String WRITE_EXTERNAL_STORAGE_PERMISSION = WRITE_EXTERNAL_STORAGE;





    private String ServerUrlImagePath;

    private static final int REQUEST_READ_CLINIC_PIC_PERMISSION = 786;
    private static final int REQUEST_CLINIC_CAMERA_PERMISSION_CODE = 785 ;




    private static final int SELECT_CLINIC_CAMERA = 1000 ;

    private static final int SELECT_CLINIC_PICTURE = 1001 ;
    private MultipartBody.Part filePart;

    private String petId,tagg;
    private String doctorid;
    private String fromactivity;
    private String fromto;
    private String Payment_id = "";
    private String Doctor_ava_Date = "";
    private String selectedTimeSlot = "";
    private int amount;
    private String communicationtype = "";


    private String spid,catid,from;
    private String spuserid;
    private String selectedServiceTitle;
    private String petcolor,petname,serviceprovidingcompanyname;
    private double petweight;
    private String servicetime;
    private int serviceamount;
    private String petage,doctorname,clinicname;
    private int distance;
    private String SP_ava_Date;



    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_your_family_members);
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);

        txt_skip.setOnClickListener(this);

        btn_continue.setOnClickListener(this);

        img_uploadimage.setOnClickListener(this);

        SessionManager sessionManager = new SessionManager(AddNewFamilyMembersActivity.this);
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            doctorid = extras.getString("doctorid");
            fromactivity = extras.getString("fromactivity");
            fromto = extras.getString("fromto");
            Doctor_ava_Date = extras.getString("Doctor_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            amount = extras.getInt("amount");
            Log.w(TAG,"amount : "+amount);
            communicationtype = extras.getString("communicationtype");
            petId = extras.getString("petId");
            Log.w(TAG,"Bundle "+" doctorid : "+doctorid+" selectedTimeSlot : "+selectedTimeSlot+"communicationtype : "+communicationtype+" amount : "+amount+" fromactivity : "+fromactivity+" fromto : "+fromto);

            Log.w(TAG,"fromactivity : "+fromactivity);

            /*PetServiceAppointment_Doctor_Date_Time_Activity*/
            fromactivity = extras.getString("fromactivity");
            spid = extras.getString("spid");
            catid = extras.getString("catid");
            from = extras.getString("from");
            spuserid = extras.getString("spuserid");
            selectedServiceTitle = extras.getString("selectedServiceTitle");
            serviceprovidingcompanyname = extras.getString("serviceprovidingcompanyname");
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
            SP_ava_Date = extras.getString("SP_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");

            doctorname = extras.getString("doctorname");

            clinicname = extras.getString("clinicname");

            tagg = extras.getString("TAGG");

            distance = extras.getInt("distance");
            Log.w(TAG,"spid : "+spid +" catid : "+catid+" from : "+from+" serviceamount : "+serviceamount+" servicetime : "+servicetime+" SP_ava_Date : "+SP_ava_Date+" selectedTimeSlot : "+selectedTimeSlot);

            Log.w(TAG,"fromactivity : "+fromactivity+" from : "+from);

        }

        if (new ConnectionDetector(AddNewFamilyMembersActivity.this).isNetworkAvailable(AddNewFamilyMembersActivity.this)) {
            getfamilymembersListResponseCall();

        }

        txt_skip.setVisibility(View.GONE);


        sprrelationtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {

                if(++sprflag > 1) {

                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
                    strrelationtype = sprrelationtype.getSelectedItem().toString();

                    Log.w(TAG,"strrelationtype:"+strrelationtype);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        rggender.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = rggender.getCheckedRadioButtonId();
            RadioButton radioButton = rggender.findViewById(radioButtonID);
            selectedRadioButton = (String) radioButton.getText();
            Log.w(TAG,"selectedRadioButton" + selectedRadioButton);

        });



    }

    public void addYourfamilyValidator() {
        boolean can_proceed = true;

        int namelength = edt_name.getText().toString().trim().length();
        int weightlength = edt_weight.getText().toString().trim().length();
        int heightlength = edt_height.getText().toString().trim().length();

        if (edt_name.getText().toString() != null && edt_name.getText().toString().trim().equals("") && edt_weight.getText().toString()!= null && edt_weight.getText().toString().trim().equals("")
                && edt_height.getText().toString() != null && edt_height.getText().toString().trim().equals("")
                && edt_age.getText().toString() != null && edt_age.getText().toString().trim().equals("")) {
            Toasty.warning(getApplicationContext(), "Please enter the fields", Toast.LENGTH_SHORT, true).show();
            can_proceed = false;
        } else if (edt_name.getText().toString().trim().equals("")) {
            edt_name.setError("Please enter name");
            edt_name.requestFocus();
            can_proceed = false;
        }else if (namelength > 25) {
            edt_name.setError("The maximum length for anname is 25 characters.");
            edt_name.requestFocus();
            can_proceed = false;
        }
        else if (edt_weight.getText().toString().trim().equals("")) {
            edt_weight.setError("Please enter weight");
            edt_weight.requestFocus();
            can_proceed = false;
        }
        else if (weightlength > 5) {
            edt_weight.setError("The maximum length for an weight is 5 characters.");
            edt_weight.requestFocus();
            can_proceed = false;
        }
        else if (edt_height.getText().toString().trim().equals("")) {
            edt_height.setError("Please enter height");
            edt_height.requestFocus();
            can_proceed = false;
        }
        else if (heightlength > 5) {
            edt_height.setError("The maximum length for an height is 5 characters.");
            edt_height.requestFocus();
            can_proceed = false;
        }
        else if (edtx_any_medical_information.getText().toString().trim().equals("")) {
            edtx_any_medical_information.setError("Please enter medical info");
            edtx_any_medical_information.requestFocus();
            can_proceed = false;
        }
        else if (strrelationtype.trim().equals("")) {
            showErrorLoading("Please select relation type");
            can_proceed = false;
        }
        else if (picBeanList.size()==0) {
            showErrorLoading("Please upload one image of your family member");
            can_proceed = false;
        }

        if (can_proceed) {
            if (new ConnectionDetector(AddNewFamilyMembersActivity.this).isNetworkAvailable(AddNewFamilyMembersActivity.this)) {

                familymemberxreateResponseCall();
            }

        }

    }



    @SuppressLint("LogNotTimber")
    public void getfamilymembersListResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<GetFamilyMemberResponse> call = apiInterface.getfamilymembersListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<GetFamilyMemberResponse>() {
            @SuppressLint({"LogNotTimber", "LongLogTag"})
            @Override
            public void onResponse(@NonNull Call<GetFamilyMemberResponse> call, @NonNull Response<GetFamilyMemberResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"GetFamilyMemberResponse" + new Gson().toJson(response.body()));

                        if(response.body().getData() != null){
                            getfamilymemberslist = response.body().getData();
                        }


                        if(getfamilymemberslist != null && getfamilymemberslist.size()>0){
                            setView(getfamilymemberslist);
                        }
                    }



                }








            }


            @Override
            public void onFailure(@NonNull Call<GetFamilyMemberResponse> call,@NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"GetFamilyMemberResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LogNotTimber")
    private void setView(List<GetFamilyMemberResponse.DataBean> getfamilymemberslist) {

        ArrayList<String> familymemberstypeArrayList = new ArrayList<>();
        familymemberstypeArrayList.add("Select Relation Type");
        for (int i = 0; i < getfamilymemberslist.size(); i++) {

            String relationType = getfamilymemberslist.get(i).getName();
            Log.w(TAG, "relationType-->" + relationType);
            familymemberstypeArrayList.add(relationType);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(AddNewFamilyMembersActivity.this, R.layout.spinner_item, familymemberstypeArrayList);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
            sprrelationtype.setAdapter(spinnerArrayAdapter);

        }

    }

    @SuppressLint("LogNotTimber")
    private void familymemberxreateResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FamilyMemberCreateResponse> call = apiInterface.familymembercreateResponseCall(RestUtils.getContentType(),FamilyMemberCreateRequest());
        Log.w(TAG,"FamilyMemberCreateResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<FamilyMemberCreateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FamilyMemberCreateResponse> call, @NonNull Response<FamilyMemberCreateResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"FamilyMemberCreateResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {
                        if(response.body().getData().get_id() != null){

                            Toasty.success(AddNewFamilyMembersActivity.this," "+response.body().getMessage(), Toasty.LENGTH_LONG).show();

                            callDirections();
                        }

                    } else {
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<FamilyMemberCreateResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private FamilyMemberCreateRequest FamilyMemberCreateRequest() {

        /*
         * user_id : 123123123
         * relation : Father
         * name : Mohammed imthiyas
         * gender : Male
         * age : 23
         * height : 12.2
         * weight : 122
         * anymedicalinfo : this is not good for health
         * pic : [{"image":"http://Google.com"}]
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());


        FamilyMemberCreateRequest FamilyMemberCreateRequest = new FamilyMemberCreateRequest();
        FamilyMemberCreateRequest.setUser_id(userid);
        FamilyMemberCreateRequest.setRelation(strrelationtype);
        FamilyMemberCreateRequest.setName(edt_name.getText().toString().trim());
        FamilyMemberCreateRequest.setGender(selectedRadioButton);
        FamilyMemberCreateRequest.setAge(Integer.parseInt(edt_age.getText().toString().trim()));
        FamilyMemberCreateRequest.setHeight(edt_height.getText().toString().trim());
        FamilyMemberCreateRequest.setWeight(edt_weight.getText().toString().trim());
        FamilyMemberCreateRequest.setAnymedicalinfo(edtx_any_medical_information.getText().toString().trim());
        FamilyMemberCreateRequest.setPic(picBeanList);
        Log.w(TAG,"FamilyMemberCreateRequest "+ new Gson().toJson(FamilyMemberCreateRequest));
        return FamilyMemberCreateRequest;
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

    private void gotoPetloverDashboard() {
        Intent intent = new Intent(AddNewFamilyMembersActivity.this,PetLoverDashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        callDirections();
    }

    public void callDirections(){

        if(tagg!=null){

            if(tagg.equals("SelectYourPetActivity")){

                Intent intent = new Intent(getApplicationContext(),SelectYourPetActivity.class);
                //  intent.putExtra("SPCreateAppointmentRequestList",SPCreateAppointmentRequestList);
                intent.putExtra("spid",spid);
                intent.putExtra("catid",catid);
                intent.putExtra("from",from);
                intent.putExtra("spuserid",spuserid);
                intent.putExtra("selectedServiceTitle",selectedServiceTitle);
                intent.putExtra("serviceprovidingcompanyname",serviceprovidingcompanyname);
                intent.putExtra("serviceamount",serviceamount);
                intent.putExtra("servicetime",servicetime);
                intent.putExtra("SP_ava_Date",SP_ava_Date);
                intent.putExtra("selectedTimeSlot",selectedTimeSlot);
                intent.putExtra("distance",distance);
                intent.putExtra("fromactivity",fromactivity);
                intent.putExtra("petId", petId);
                intent.putExtra("petname",petname);
                startActivity(intent);
            }

            else {

                Intent intent = new Intent(getApplicationContext(), ConsultationActivity.class);
                intent.putExtra("doctorid", doctorid);
                intent.putExtra("fromactivity", TAG);
                intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
                intent.putExtra("selectedTimeSlot", selectedTimeSlot);
                intent.putExtra("amount", amount);
                intent.putExtra("communicationtype", communicationtype);
                intent.putExtra("fromto", TAG);
                intent.putExtra("doctorname", doctorname);
                intent.putExtra("clinicname", clinicname);
                intent.putExtra("petname", petname);
                intent.putExtra("petId", petId);
                startActivity(intent);
            }
        }


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.txt_skip:
                callDirections();
                break;
            case R.id.btn_continue:
                addYourfamilyValidator();
                break;
            case R.id.img_uploadimage:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkMultiplePermissions(REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS, AddNewFamilyMembersActivity.this);
                }else{
                    choosePetImage();

                }
                break;

        }

    }

    private void choosePetImage() {


        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        //AlertDialog.Builder alert=new AlertDialog.Builder(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(AddNewFamilyMembersActivity.this);
        builder.setTitle("Choose option");
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Take Photo"))
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(AddNewFamilyMembersActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CLINIC_CAMERA_PERMISSION_CODE);
                }
                else
                {


                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(intent, SELECT_CLINIC_CAMERA);
                }

            }

            else if (items[item].equals("Choose from Library"))
            {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(AddNewFamilyMembersActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_CLINIC_PIC_PERMISSION);
                }

                else{

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_CLINIC_PICTURE);


                }
            }

            else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //	Toast.makeText(getActivity(),"kk",Toast.LENGTH_SHORT).show();
        if(requestCode== SELECT_CLINIC_PICTURE || requestCode == SELECT_CLINIC_CAMERA)
        {

            if(requestCode == SELECT_CLINIC_CAMERA)
            {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                File file = new File(getFilesDir(), "Petfolio1" + ".jpg");

                OutputStream os;
                try {
                    os = new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    os.flush();
                    os.close();
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());

                RequestBody requestFile = RequestBody.create(MediaType.parse("image*/"), file);

                filePart = MultipartBody.Part.createFormData("sampleFile",  userid+currentDateandTime+file.getName(), requestFile);

                uploadPetImage();

            }

            else{

                try {
                    if (resultCode == Activity.RESULT_OK)
                    {

                        Log.w("VALUEEEEEEE1111", " " + data);

                        Uri selectedImageUri = data.getData();

                        Log.w("selectedImageUri", " " + selectedImageUri);

                        String filename = getFileName(selectedImageUri);

                        Log.w("filename", " " + filename);

                        String filePath = FileUtil.getPath(AddNewFamilyMembersActivity.this,selectedImageUri);

                        assert filePath != null;

                        File file = new File(filePath); // initialize file here

                        long length = file.length() / 1024; // Size in KB

                        Log.w("filesize", " " + length);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
                        String currentDateandTime = sdf.format(new Date());

                        filePart = MultipartBody.Part.createFormData("sampleFile", userid+currentDateandTime+file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

                        uploadPetImage();


                    }
                } catch (Exception e) {

                    Log.w("Exception", " " + e);
                }

            }

        }



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





    private void uploadPetImage() {

        avi_indicator.show();

        RestApiInterface apiInterface = APIClient.getImageClient().create(RestApiInterface.class);


        Call<FileUploadResponse> call = apiInterface.getImageStroeResponse(filePart);


        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FileUploadResponse>() {
            @Override
            public void onResponse(@NonNull Call<FileUploadResponse> call, @NonNull Response<FileUploadResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"Profpic"+ "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    if (200 == response.body().getCode()) {

                        ServerUrlImagePath = response.body().getData();
                        btn_continue.setVisibility(View.VISIBLE);

                        Log.w(TAG, "ServerUrlImagePath " + ServerUrlImagePath);

                        if( response.body().getData() != null)
                        {

                            if(picBeanList.size()>=1){

                                Toasty.warning(AddNewFamilyMembersActivity.this,"Sorry You can't Upload more than 1", Toasty.LENGTH_LONG).show();

                            }

                            else
                            {
                                if(ServerUrlImagePath != null&&!ServerUrlImagePath.isEmpty())
                                {
                                    picBeanList.add(new FamilyMemberCreateRequest.PicBean(ServerUrlImagePath));

                                }
                                else
                                {
                                    picBeanList.add(new FamilyMemberCreateRequest.PicBean(APIClient.IMAGE_BASE_URL));

                                }

                                setView();

                            }



                        }

                        else
                        {
                            Toasty.warning(AddNewFamilyMembersActivity.this,"Failed to Upload", Toasty.LENGTH_LONG).show();

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

    private void setView() {

        rv_uploaded_images.setHasFixedSize(true);

        rv_uploaded_images.setNestedScrollingEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(AddNewFamilyMembersActivity.this, LinearLayoutManager.HORIZONTAL, false);

        rv_uploaded_images.setLayoutManager(layoutManager);

        AddFamilyImageListAdapter addFamilyImageListAdapter = new AddFamilyImageListAdapter(this, picBeanList);

        rv_uploaded_images.setAdapter(addFamilyImageListAdapter);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_CLINIC_PIC_PERMISSION) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_CLINIC_PICTURE);

            } else {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Permisson Required")
                        .setContentText("Plz Allow Permissions for choosing Images from Gallery ")
                        .setConfirmText("Ok")
                        .setConfirmClickListener(sDialog -> {

                            sDialog.dismissWithAnimation();

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, REQUEST_READ_CLINIC_PIC_PERMISSION);
                            }


                        })
                        .setCancelButton("Cancel", sDialog -> {
                            sDialog.dismissWithAnimation();

                            showWarning(REQUEST_READ_CLINIC_PIC_PERMISSION);
                        })
                        .show();

            }

        } else if (requestCode == REQUEST_CLINIC_CAMERA_PERMISSION_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, SELECT_CLINIC_CAMERA);

            } else {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Permisson Required")
                        .setContentText("Plz Allow Camera for taking picture")
                        .setConfirmText("Ok")
                        .setConfirmClickListener(sDialog -> {

                            sDialog.dismissWithAnimation();

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{CAMERA}, REQUEST_CLINIC_CAMERA_PERMISSION_CODE);
                            }


                        })
                        .setCancelButton("Cancel", sDialog -> {
                            sDialog.dismissWithAnimation();

                            showWarning(REQUEST_CLINIC_CAMERA_PERMISSION_CODE);
                        })
                        .show();

            }

        }
    }

    //check for camera and storage access permissions
    @TargetApi(Build.VERSION_CODES.M)
    private void checkMultiplePermissions(int permissionCode, Context context) {

        String[] PERMISSIONS = {CAMERA_PERMISSION, READ_EXTERNAL_STORAGE_PERMISSION, WRITE_EXTERNAL_STORAGE_PERMISSION};
        if (!hasPermissions(context, PERMISSIONS)) {
            ActivityCompat.requestPermissions((Activity) context, PERMISSIONS, permissionCode);
        } else {
            choosePetImage();
            // Open your camera here.
        }
    }
    private boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWarning(int REQUEST_PERMISSION_CODE) {

        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Sorry!!")
                .setContentText("You Can't proceed further unless you allow permission")
                .setConfirmText("Ok")
                .setConfirmClickListener(sDialog -> {

                    sDialog.dismissWithAnimation();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                    }


                })
                .setCancelButton("Cancel", SweetAlertDialog::dismissWithAnimation)
                .show();
    }

}