package com.salveo.mysalveo.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.adapter.AddImageListAdapter;
import com.salveo.mysalveo.adapter.ManageAddressListVisitAdapter;
import com.salveo.mysalveo.adapter.ViewPagerPetlistAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.appUtils.FileUtil;
import com.salveo.mysalveo.interfaces.LocationDefaultListener;
import com.salveo.mysalveo.requestpojo.AddYourPetRequest;
import com.salveo.mysalveo.requestpojo.BreedTypeRequest;
import com.salveo.mysalveo.requestpojo.DocBusInfoUploadRequest;
import com.salveo.mysalveo.requestpojo.LocationListAddressRequest;
import com.salveo.mysalveo.requestpojo.NotificationSendRequest;
import com.salveo.mysalveo.requestpojo.PetAppointmentCreateRequest;
import com.salveo.mysalveo.requestpojo.PetDetailsRequest;
import com.salveo.mysalveo.responsepojo.AddYourPetResponse;
import com.salveo.mysalveo.responsepojo.BreedTypeResponse;
import com.salveo.mysalveo.responsepojo.FileUploadResponse;
import com.salveo.mysalveo.responsepojo.LocationListAddressResponse;
import com.salveo.mysalveo.responsepojo.NotificationSendResponse;
import com.salveo.mysalveo.responsepojo.PetAppointmentCreateResponse;
import com.salveo.mysalveo.responsepojo.PetDetailsResponse;
import com.salveo.mysalveo.responsepojo.PetTypeListResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAppointmentActivity extends AppCompatActivity implements PaymentResultListener, LocationDefaultListener {

    private static final String TAG = "BookAppointmentActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spr_selectyourpettype)
    Spinner spr_selectyourpettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sprpettype)
    Spinner sprpettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sprpetbreed)
    Spinner sprpetbreed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_save_continue)
    LinearLayout btn_continue;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cdvw)
    CardView cv_pet_img;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pettype)
    TextView txt_pettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_petbreed)
    TextView txt_petbreed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_petname)
    EditText edt_petname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_petbreed)
    RelativeLayout rl_petbreed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_pettype)
    RelativeLayout rl_pettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_or)
    TextView txt_or;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_pet_pics)
    RelativeLayout rl_pet_pics;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_upload_pet_images)
    RecyclerView rv_upload_pet_images;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_pet_imge)
    ImageView img_pet_imge;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_lbl_uploadpet)
    TextView txt_lbl_uploadpet;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_appointmenttype)
    RadioGroup rg_appointmenttype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_allergies)
    EditText edt_allergies;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_comment)
    EditText edt_comment;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_communicationtype)
    RadioGroup rg_communicationtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.radioButton_online)
    RadioButton radioButton_online;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.radioButton_visit)
    RadioButton radioButton_visit;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_visit_group)
    LinearLayout ll_visit_group;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_visit_group)
    RadioGroup rg_visit_group;

    private List<PetTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList;
    private String strPetType;
    private String strPetBreedType;
    private String userid = "";
    private String strSelectyourPetType;

    HashMap<String, String> hashMap_PetTypeid = new HashMap<>();
    private String petTypeId;
    private List<PetDetailsResponse.DataBean> petDetailsResponseByUserIdList;
    private List<BreedTypeResponse.DataBean> breedTypedataBeanList;
    private String petName;
    private String petType;
    private String petBreed;

    private final List<DocBusInfoUploadRequest.ClinicPicBean> clinicPicBeans = new ArrayList<>();

    private static final int REQUEST_CLINIC_CAMERA_PERMISSION_CODE = 785;
    private static final int SELECT_CLINIC_CAMERA = 1000;
    private static final int REQUEST_READ_CLINIC_PIC_PERMISSION = 786;
    private static final int SELECT_CLINIC_PICTURE = 1001;

    MultipartBody.Part filePart;
    String currentDateandTime;
    private String uploadimagepath = "";
    Dialog alertDialog;
    private boolean isSelectYourPet;
    private String selectedAppointmentType = "Normal";
    private String selectedVisitType = "";
    private String petId;
    private String doctorid;
    private String fromactivity;
    private String fromto;
    private String Payment_id = "";

    private String Doctor_ava_Date = "";
    private String selectedTimeSlot = "";

    private int amount;
    private String communicationtype = "";

    HashMap<String, String> hashMap_selectyourpet = new HashMap<>();
    private String selectedCommunicationtype;
    private List<PetDetailsResponse.DataBean.PetImgBean> petimage;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;
    private Dialog dialog;

    TextView txt_no_records;
    TextView txt_savedaddress;
    RecyclerView rv_manage_address;
    Button btn_use_this_addreess;
    private List<LocationListAddressResponse.DataBean> addressList;

    String locationid = "";
    private boolean isVisit;
    private String health_issue_title;

    private String doctorname;
    private String clinicname;
    private String petname;
    private String Problem_info = "";
    private String Allergies = "";

    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        ButterKnife.bind(this);
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
            health_issue_title = extras.getString("health_issue_title");
            Log.w(TAG,"Bundle "+" doctorid : "+doctorid+" selectedTimeSlot : "+selectedTimeSlot+"communicationtype : "+communicationtype+" amount : "+amount+" fromactivity : "+fromactivity);

            Log.w(TAG, "petId : " + petId);

            doctorname = extras.getString("doctorname");
            clinicname = extras.getString("clinicname");
            petname = extras.getString("petname");

        }

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.appointment));

        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
        img_notification.setVisibility(View.GONE);
        img_profile.setVisibility(View.GONE);

        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PetLoverProfileScreenActivity.class);
                intent.putExtra("doctorid",doctorid);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);
            }
        });

        txt_pettype.setVisibility(View.GONE);
        txt_petbreed.setVisibility(View.GONE);
        cv_pet_img.setVisibility(View.GONE);
        rv_upload_pet_images.setVisibility(View.GONE);
        img_pet_imge.setVisibility(View.VISIBLE);
        rl_petbreed.setVisibility(View.GONE);

        ll_visit_group.setVisibility(View.GONE);
        radioButton_online.setVisibility(View.GONE);
        radioButton_visit.setVisibility(View.GONE);

        if(communicationtype != null){
            if(communicationtype.equalsIgnoreCase("Online Or Visit")){
                radioButton_online.setVisibility(View.VISIBLE);
                radioButton_visit.setVisibility(View.VISIBLE);
                radioButton_online.setChecked(true);
                selectedCommunicationtype = "Online";

            }else if(communicationtype.equalsIgnoreCase("Online")){
                radioButton_online.setVisibility(View.VISIBLE);
                radioButton_online.setChecked(true);
                radioButton_online.setClickable(false);
                selectedCommunicationtype = "Online";

            }else if(communicationtype.equalsIgnoreCase("Visit")){
                isVisit = true;
                radioButton_visit.setVisibility(View.VISIBLE);
                radioButton_visit.setChecked(true);
                radioButton_visit.setClickable(false);
                selectedCommunicationtype = "Visit";
                ll_visit_group.setVisibility(View.VISIBLE);
            }
        }

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        currentDateandTime = sdf.format(new Date());

        if (userid != null) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                petDetailsResponseByUserIdCall();
            }

        }

        img_back.setOnClickListener(v -> onBackPressed());

        spr_selectyourpettype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
                strSelectyourPetType = spr_selectyourpettype.getSelectedItem().toString();
                String selectedpetid = hashMap_selectyourpet.get(strSelectyourPetType);

                Log.w(TAG, "strPetType :" + strSelectyourPetType+" selectedpetid : "+selectedpetid);
                if (!strSelectyourPetType.equalsIgnoreCase("Select Your Pet")) {
                    isSelectYourPet = true;
                    txt_or.setVisibility(View.GONE);
                    txt_pettype.setVisibility(View.VISIBLE);
                    txt_petbreed.setVisibility(View.VISIBLE);
                    cv_pet_img.setVisibility(View.VISIBLE);
                    edt_petname.setVisibility(View.GONE);
                    edt_petname.setEnabled(false);
                    edt_petname.setInputType(InputType.TYPE_NULL);
                    if(petDetailsResponseByUserIdList != null && petDetailsResponseByUserIdList.size()>0) {
                        for(int i = 0;i<petDetailsResponseByUserIdList.size();i++) {
                            if(selectedpetid != null && selectedpetid.equalsIgnoreCase(petDetailsResponseByUserIdList.get(i).get_id())) {
                                petName = petDetailsResponseByUserIdList.get(i).getPet_name();
                                petType = petDetailsResponseByUserIdList.get(i).getPet_type();
                                petBreed = petDetailsResponseByUserIdList.get(i).getPet_breed();
                                petId = petDetailsResponseByUserIdList.get(i).get_id();
                                petimage = petDetailsResponseByUserIdList.get(i).getPet_img();
                                if(petimage!=null&&petimage.size()>0){

                                    img_pet_imge.setVisibility(View.GONE);

                                    viewpageData(petimage);
                                }

                                else {

                                    img_pet_imge.setVisibility(View.VISIBLE);

                                }

                                Log.w(TAG, "for petType-->" + petType + "  petName : "+petName+" petId : "+petId+" petimage : "+petimage);

                            }
                        }
                    }



                    edt_petname.setText(petName);
                    txt_pettype.setText(petType);
                    txt_petbreed.setText(petBreed);


                    rl_pettype.setVisibility(View.GONE);
                    rl_petbreed.setVisibility(View.GONE);
                    rv_upload_pet_images.setVisibility(View.GONE);
                    txt_lbl_uploadpet.setVisibility(View.GONE);
                    rl_pet_pics.setVisibility(View.GONE);

                }
                else {
                    isSelectYourPet = false;
                    txt_or.setVisibility(View.VISIBLE);
                    edt_petname.setVisibility(View.VISIBLE);

                    txt_pettype.setVisibility(View.GONE);
                    txt_petbreed.setVisibility(View.GONE);
                    cv_pet_img.setVisibility(View.GONE);
                    img_pet_imge.setVisibility(View.VISIBLE);
                    edt_petname.setText("");
                    edt_petname.setEnabled(true);
                    edt_petname.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

                    rl_pettype.setVisibility(View.VISIBLE);

                    rv_upload_pet_images.setVisibility(View.VISIBLE);
                    txt_lbl_uploadpet.setVisibility(View.VISIBLE);
                    rl_pet_pics.setVisibility(View.VISIBLE);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        sprpettype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
                strPetType = sprpettype.getSelectedItem().toString();
                petTypeId = hashMap_PetTypeid.get(strPetType);
                breedTypeResponseByPetIdCall(petTypeId);
                Log.w(TAG, "petTypeId : " + petTypeId + " strPetType :" + strPetType);

                if(strPetType != null && !strPetType.equalsIgnoreCase("Pet Type")){
                    rl_petbreed.setVisibility(View.VISIBLE);
                }else{
                    rl_petbreed.setVisibility(View.GONE);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        sprpetbreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
                strPetBreedType = sprpetbreed.getSelectedItem().toString();
                Log.w(TAG, "strPetBreedType :" + strPetBreedType);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        btn_continue.setOnClickListener(v -> {
            Log.w(TAG,"btn_continue selectedCommunicationtype : "+selectedCommunicationtype+" selectedVisitType : "+selectedVisitType);



            if (isVisit && selectedVisitType != null && selectedVisitType.isEmpty()) {
                showErrorLoading("Please select visit type");
            }
            else{
                if(amount != 0){
                    //  startPayment();
                    petAppointmentCreateRequest();

                }
                else {
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        //petAppointmentCreateResponseCall();
                        petAppointmentCreateRequest();
                    }

                }
            }



        });

        rl_pet_pics.setOnClickListener(v -> choosePetImage());

        rg_appointmenttype.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = rg_appointmenttype.getCheckedRadioButtonId();
            RadioButton radioButton = rg_appointmenttype.findViewById(radioButtonID);
            selectedAppointmentType = (String) radioButton.getText();
            Log.w(TAG, "selectedAppointmentType : " + selectedAppointmentType);
            communicationtype = selectedAppointmentType;


        });

        rg_communicationtype.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = rg_communicationtype.getCheckedRadioButtonId();
            RadioButton radioButton = rg_communicationtype.findViewById(radioButtonID);
            selectedCommunicationtype = (String) radioButton.getText();
            Log.w(TAG,"selectedCommunicationtype " + selectedCommunicationtype);
            if(selectedCommunicationtype != null && selectedCommunicationtype.equalsIgnoreCase("Visit")) {
                ll_visit_group.setVisibility(View.VISIBLE);
                isVisit = true;
            }else{
                ll_visit_group.setVisibility(View.GONE);
                isVisit = false;
            }


        });
        rg_visit_group.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = rg_visit_group.getCheckedRadioButtonId();
            RadioButton radioButton = rg_visit_group.findViewById(radioButtonID);
            selectedVisitType = (String) radioButton.getText();
            Log.w(TAG,"selectedVisitType : " + selectedVisitType);
            if(selectedVisitType != null && selectedVisitType.equalsIgnoreCase("Home")){
                selectedVisitType = "Home Visit";
                showManageAddressAlert();
                btn_use_this_addreess.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Log.w(TAG," locationid : "+locationid+" selectedVisitType : "+selectedVisitType);
                    }
                });

            }else{
                selectedVisitType = "Clinic Visit";
            }


        });



    }

    private void viewpageData(List<PetDetailsResponse.DataBean.PetImgBean> petImgBeanList) {
        tabLayout.setupWithViewPager(viewPager, true);

        ViewPagerPetlistAdapter viewPagerPetlistAdapter = new ViewPagerPetlistAdapter(getApplicationContext(), petImgBeanList);
        viewPager.setAdapter(viewPagerPetlistAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update =  new Runnable() {
            public void run() {
                if (currentPage == petImgBeanList.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, false);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

    }

    @SuppressLint("LogNotTimber")
    public void petTypeListResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<PetTypeListResponse> call = apiInterface.petTypeListResponseCall(RestUtils.getContentType());
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<PetTypeListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<PetTypeListResponse> call, @NonNull Response<PetTypeListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        Log.w(TAG, "PetTypeListResponse" + new Gson().toJson(response.body()));

                        if(response.body().getData().getUsertypedata() != null) {
                            usertypedataBeanList = response.body().getData().getUsertypedata();
                        }
                        if (usertypedataBeanList != null && usertypedataBeanList.size() > 0) {
                            setPetType(usertypedataBeanList);
                        }
                    }


                }


            }


            @Override
            public void onFailure(@NonNull Call<PetTypeListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "PetTypeListResponse flr" + t.getMessage());
            }
        });

    }
    private void setPetType(List<PetTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList) {
        ArrayList<String> pettypeArrayList = new ArrayList<>();
        pettypeArrayList.add("Pet Type");
        for (int i = 0; i < usertypedataBeanList.size(); i++) {

            String petType = usertypedataBeanList.get(i).getPet_type_title();
            hashMap_PetTypeid.put(usertypedataBeanList.get(i).getPet_type_title(), usertypedataBeanList.get(i).get_id());

            Log.w(TAG, "petType-->" + petType);
            pettypeArrayList.add(petType);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(BookAppointmentActivity.this, R.layout.spinner_item, pettypeArrayList);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
            sprpettype.setAdapter(spinnerArrayAdapter);



        }
    }
    @SuppressLint("LogNotTimber")
    private void breedTypeResponseByPetIdCall(String petTypeId) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<BreedTypeResponse> call = ApiService.breedTypeResponseByPetIdCall(RestUtils.getContentType(), breedTypeRequest(petTypeId));
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<BreedTypeResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<BreedTypeResponse> call, @NonNull Response<BreedTypeResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "BreedTypeResponse" + "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null) {
                            breedTypedataBeanList = response.body().getData();
                            if (breedTypedataBeanList != null && breedTypedataBeanList.size() > 0) {
                                setBreedType(breedTypedataBeanList);
                            }
                        }

                    }

                }


            }


            @Override
            public void onFailure(@NonNull Call<BreedTypeResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG, "BreedTypeResponse flr" + "--->" + t.getMessage());
            }
        });

    }

    private void setBreedType(List<BreedTypeResponse.DataBean> breedTypedataBeanList) {
        ArrayList<String> pettypeArrayList = new ArrayList<>();
        pettypeArrayList.add("Pet Breed");
        for (int i = 0; i < breedTypedataBeanList.size(); i++) {

            String petType = breedTypedataBeanList.get(i).getPet_breed();

            Log.w(TAG, "petType-->" + petType);
            pettypeArrayList.add(petType);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(BookAppointmentActivity.this, R.layout.spinner_item, pettypeArrayList);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
            sprpetbreed.setAdapter(spinnerArrayAdapter);


        }
    }

    private BreedTypeRequest breedTypeRequest(String petTypeId) {
        BreedTypeRequest breedTypeRequest = new BreedTypeRequest();
        breedTypeRequest.setPet_type_id(petTypeId);
        Log.w(TAG, "breedTypeRequest" + "--->" + new Gson().toJson(breedTypeRequest));
        return breedTypeRequest;
    }


    @SuppressLint("LogNotTimber")
    private void petDetailsResponseByUserIdCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PetDetailsResponse> call = ApiService.petDetailsResponseByUserIdCall(RestUtils.getContentType(), petDetailsRequest());
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<PetDetailsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<PetDetailsResponse> call, @NonNull Response<PetDetailsResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "PetDetailsResponse" + "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

                            petTypeListResponseCall();
                        }
                        if(response.body().getData() != null) {

                            petDetailsResponseByUserIdList = response.body().getData();
                        }
                        if (petDetailsResponseByUserIdList != null && petDetailsResponseByUserIdList.size() > 0) {
                            setSelectYourPetType(petDetailsResponseByUserIdList);
                        }

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<PetDetailsResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG, "PetDetailsResponse flr" + "--->" + t.getMessage());
            }
        });

    }

    private PetDetailsRequest petDetailsRequest() {
        PetDetailsRequest petDetailsRequest = new PetDetailsRequest();
        petDetailsRequest.setUser_id(userid);
        Log.w(TAG, "petDetailsRequest" + "--->" + new Gson().toJson(petDetailsRequest));
        return petDetailsRequest;
    }

    private void setSelectYourPetType(List<PetDetailsResponse.DataBean> petDetailsResponseByUserIdList) {
        ArrayList<String> pettypeArrayList = new ArrayList<>();
        pettypeArrayList.add("Select Your Pet");
        for (int i = 0; i < petDetailsResponseByUserIdList.size(); i++) {
            hashMap_selectyourpet.put(petDetailsResponseByUserIdList.get(i).getPet_name(), petDetailsResponseByUserIdList.get(i).get_id());
            String petName = petDetailsResponseByUserIdList.get(i).getPet_name();
            Log.w(TAG, "petName-->" + petName);
            pettypeArrayList.add(petName);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(BookAppointmentActivity.this, R.layout.spinner_item, pettypeArrayList);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
            spr_selectyourpettype.setAdapter(spinnerArrayAdapter);


        }
    }

    public boolean validdSelectYourPetType() {
        if (strSelectyourPetType.equalsIgnoreCase("Select Your Pet")) {
            final AlertDialog alertDialog = new AlertDialog.Builder(BookAppointmentActivity.this).create();
            alertDialog.setMessage(getString(R.string.err_msg_type_of_pettype));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    (dialog, which) -> alertDialog.cancel());
            alertDialog.show();

            return false;
        }

        return true;
    }

    public boolean validdSelectPetType() {
        if (strPetType != null && strPetType.equalsIgnoreCase("Pet Type")) {
            final AlertDialog alertDialog = new AlertDialog.Builder(BookAppointmentActivity.this).create();
            alertDialog.setMessage(getString(R.string.err_msg_type_of_pettype));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    (dialog, which) -> alertDialog.cancel());
            alertDialog.show();

            return false;
        }

        return true;
    }

    public boolean validdSelectPetBreedType() {
        if (strPetBreedType != null && strPetBreedType.equalsIgnoreCase("Pet Breed")) {
            final AlertDialog alertDialog = new AlertDialog.Builder(BookAppointmentActivity.this).create();
            alertDialog.setMessage(getString(R.string.err_msg_type_of_petbreedtype));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    (dialog, which) -> alertDialog.cancel());
            alertDialog.show();

            return false;
        }

        return true;
    }


    private void choosePetImage() {

        if (clinicPicBeans.size() >= 1) {

            Toasty.warning(getApplicationContext(), "Sorry you can't Add more than 1", Toast.LENGTH_SHORT).show();

        } else {
            final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
            //AlertDialog.Builder alert=new AlertDialog.Builder(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(BookAppointmentActivity.this);
            builder.setTitle("Choose option");
            builder.setItems(items, (dialog, item) -> {
                if (items[item].equals("Take Photo")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(BookAppointmentActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CLINIC_CAMERA_PERMISSION_CODE);
                    } else {


                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        startActivityForResult(intent, SELECT_CLINIC_CAMERA);
                    }

                } else if (items[item].equals("Choose from Library")) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(BookAppointmentActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_CLINIC_PIC_PERMISSION);
                    } else {

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_CLINIC_PICTURE);


                    }
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            });
            builder.show();

        }

    }

    @SuppressLint("LogNotTimber")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //	Toast.makeText(getActivity(),"kk",Toast.LENGTH_SHORT).show();
        if (requestCode == SELECT_CLINIC_PICTURE || requestCode == SELECT_CLINIC_CAMERA) {

            if (requestCode == SELECT_CLINIC_CAMERA) {
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

                filePart = MultipartBody.Part.createFormData("sampleFile", userid+file.getName().trim(), requestFile);

                uploadPetImage();

            } else {

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

                        String filePath = FileUtil.getPath(BookAppointmentActivity.this, selectedImageUri);

                        assert filePath != null;

                        File file = new File(filePath); // initialize file here
                        if(file != null) {
                            long length = file.length() / 1024; // Size in KB
                            Log.w("filesize", " " + length);
                        }


                        filePart = MultipartBody.Part.createFormData("sampleFile", userid+currentDateandTime+file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
                        uploadPetImage();


                    }
                } catch (Exception e) {

                    Log.w("Exception", " " + e);
                }

            }

        }


    }

    private void uploadPetImage() {

        avi_indicator.show();

        RestApiInterface apiInterface = APIClient.getImageClient().create(RestApiInterface.class);


        Call<FileUploadResponse> call = apiInterface.getImageStroeResponse(filePart);


        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FileUploadResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FileUploadResponse> call, @NonNull Response<FileUploadResponse> response) {
                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        Log.w(TAG, "Profpic" + "--->" + new Gson().toJson(response.body()));

                        DocBusInfoUploadRequest.ClinicPicBean clinicPicBean = new DocBusInfoUploadRequest.ClinicPicBean(response.body().getData().trim());
                        clinicPicBeans.add(clinicPicBean);
                        uploadimagepath = response.body().getData();
                        if (uploadimagepath != null) {
                            img_pet_imge.setVisibility(View.GONE);
                            setView();
                        }


                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<FileUploadResponse> call, @NonNull Throwable t) {
                // avi_indicator.smoothToHide();
                Log.w(TAG, "ServerUrlImagePath" + "On failure working" + t.getMessage());
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setView() {
        rv_upload_pet_images.setVisibility(View.VISIBLE);
        rv_upload_pet_images.setLayoutManager(new LinearLayoutManager(this));
        rv_upload_pet_images.setItemAnimator(new DefaultItemAnimator());
        AddImageListAdapter addImageListAdapter = new AddImageListAdapter(getApplicationContext(), clinicPicBeans);
        rv_upload_pet_images.setAdapter(addImageListAdapter);
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


    @SuppressLint("LogNotTimber")
    private void addYourPetResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddYourPetResponse> call = apiInterface.addYourPetResponseCall(RestUtils.getContentType(), addYourPetRequest());
        Log.w(TAG, "AddYourPetResponse url  :%s" + " " + call.request().url().toString());

        call.enqueue(new Callback<AddYourPetResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AddYourPetResponse> call, @NonNull Response<AddYourPetResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "AddYourPetResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        petId = response.body().getData().get_id();

                        if(amount != 0){
                            startPayment();
                        }else {
                            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                                //petAppointmentCreateResponseCall();
                                petAppointmentCreateRequest();
                            }

                        }
                    } else {
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<AddYourPetResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("AddYourPetResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private AddYourPetRequest addYourPetRequest() {
        /*
         * user_id : 5fb36ca169f71e30a0ffd3f7
         * pet_img : http://mysalveo.com/api/uploads/images.jpeg
         * pet_name : POP
         * pet_type : Dog
         * pet_breed : breed 1
         * pet_gender : Male
         * pet_color : white
         * pet_weight : 120
         * pet_age : 20
         * vaccinated : true
         * last_vaccination_date : 23-10-1996
         * default_status : true
         * date_and_time : 23-10-1996 12:09 AM
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());



        AddYourPetRequest addYourPetRequest = new AddYourPetRequest();
        addYourPetRequest.setUser_id(userid);
        /*if(uploadimagepath != null && !uploadimagepath.isEmpty()){
            addYourPetRequest.setPet_img(uploadimagepath);
        }else{
            addYourPetRequest.setPet_img(APIClient.PROFILE_IMAGE_URL);

        }*/
        addYourPetRequest.setPet_name(edt_petname.getText().toString());
        addYourPetRequest.setPet_type(strPetType);
        addYourPetRequest.setPet_breed(strPetBreedType);
        addYourPetRequest.setPet_gender("");
        addYourPetRequest.setPet_color("");
        addYourPetRequest.setPet_weight(0);
        addYourPetRequest.setPet_age("");
        addYourPetRequest.setVaccinated(false);
        addYourPetRequest.setLast_vaccination_date("");
        addYourPetRequest.setDefault_status(true);
        addYourPetRequest.setDate_and_time(currentDateandTime);
        addYourPetRequest.setMobile_type("Android");
        Log.w(TAG, "addYourPetRequest" + new Gson().toJson(addYourPetRequest));
        return addYourPetRequest;
    }

    public void showErrorLoading(String errormesage) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void hideLoading() {
        try {
            alertDialog.dismiss();
        } catch (Exception ignored) {

        }
    }


    public boolean bookAppointmentValidator() {
        boolean can_proceed = true;


        if (edt_petname.getText().toString().trim().equals("")) {
            edt_petname.setError("Please enter pet name");
            edt_petname.requestFocus();
            can_proceed = false;
        }


        return can_proceed;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Intent intent = new Intent(getApplicationContext(),PetAppointment_Doctor_Date_Time_Activity.class);
        Intent intent = new Intent(getApplicationContext(),ConsultationIssuesActivity.class);
        intent.putExtra("doctorid",doctorid);
        intent.putExtra("communicationtype",communicationtype);
        intent.putExtra("fromactivity",fromactivity);
        intent.putExtra("fromto",fromto);
        intent.putExtra("doctorname", doctorname);
        intent.putExtra("clinicname", clinicname);
        intent.putExtra("petname", petname);
        startActivity(intent);


    }


    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        //totalamount = amount;

      /*  Double d = new Double(amount);
        int amout = d.intValue();*/


        Integer totalamout = amount*100;

        try {
            JSONObject options = new JSONObject();
          options.put("name", "Salveo Health Care LLP");
            options.put("description", userid);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", totalamout);


            co.open(activity, options);
        } catch (Exception e) {
            Log.w(TAG,"Error in payment: " + e.getMessage());

            e.printStackTrace();
        }
    }
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Payment_id = razorpayPaymentID;

            Log.w(TAG, "Payment Successful: " + razorpayPaymentID);
            Toasty.success(getApplicationContext(), "Payment Successful. View your booking details in upcoming appointments.", Toast.LENGTH_SHORT, true).show();


            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                petAppointmentCreateResponseCall();

            }




        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentSuccess", e);
        }
    }
    @SuppressLint("LogNotTimber")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                notificationSendResponseCall();
            }
            Log.w(TAG,  "Payment failed: " + code + " " + response);
            Toasty.error(getApplicationContext(), "Payment failed. Please try again with another payment method..", Toast.LENGTH_SHORT, true).show();

        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentError", e);
        }
    }


    @SuppressLint("LogNotTimber")
    private void petAppointmentCreateResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PetAppointmentCreateResponse> call = ApiService.petAppointmentCreateResponseCall(RestUtils.getContentType(),petAppointmentCreateRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<PetAppointmentCreateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<PetAppointmentCreateResponse> call, @NonNull Response<PetAppointmentCreateResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"PetDoctorAvailableTimeResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getMessage() != null){
                            showPaymentSuccessalert(response.body().getMessage());
                            // showSuceessLoading(response.body().getMessage());

                        }



                    }
                    else{
                        if(response.body().getMessage() != null){
                            showErrorLoading(response.body().getMessage());

                        }


                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<PetAppointmentCreateResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"PetDoctorAvailableTimeResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private PetAppointmentCreateRequest petAppointmentCreateRequest() {

        /*
         * doctor_id : 5fb62a1924583828f10f8731
         * booking_date : 19/11/2020
         * booking_time : 12:22 pm
         * booking_date_time : 19/11/2020 12:22 pm
         * communication_type :
         * video_id : http://vidoe.com
         * user_id : 5fb6162a211fce241eaf53a9
         * pet_id : 5fb38ea334f6014ea9013d30
         * problem_info : problem info
         * doc_attched : [{"file":"http://google.pdf"}]
         * doc_feedback : doc feedback
         * doc_rate : 5
         * user_feedback : user feedback
         * user_rate : 4.5
         * display_date : 19/11/2020 01:00 PM
         * server_date_time : 09/12/2020 03:00 PM
         * payment_id : 1234567890
         * payment_method : Card
         * appointment_types : Normal
         * allergies : this is
         * amount : 400
         * location_id,
         * visit_type
         */
        List<PetAppointmentCreateRequest.DocAttchedBean> doc_attched = new ArrayList<>();


        @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        if(Doctor_ava_Date != null && !Doctor_ava_Date.isEmpty()){
            try {
                date = inputFormat.parse(Doctor_ava_Date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String outputDateStr = outputFormat.format(date);
        String outputTimeStr = null;

        @SuppressLint("SimpleDateFormat") SimpleDateFormat h_mm_a   = new SimpleDateFormat("hh:mm aa");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat hh_mm_ss = new SimpleDateFormat("HH:mm:ss");

        if(selectedTimeSlot != null && !selectedTimeSlot.isEmpty()){
            try {
                Date d1 = h_mm_a.parse(selectedTimeSlot);
                outputTimeStr =hh_mm_ss.format(d1);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Problem_info = edt_comment.getText().toString();
        Allergies = edt_allergies.getText().toString();

        String displaydateandtime = outputDateStr+" "+outputTimeStr;
        PetAppointmentCreateRequest petAppointmentCreateRequest = new PetAppointmentCreateRequest();
        petAppointmentCreateRequest.setDoctor_id(doctorid);
        petAppointmentCreateRequest.setBooking_date(Doctor_ava_Date);
        petAppointmentCreateRequest.setBooking_time(selectedTimeSlot);
        petAppointmentCreateRequest.setBooking_date_time(Doctor_ava_Date+" "+selectedTimeSlot);
        petAppointmentCreateRequest.setCommunication_type(selectedCommunicationtype);
        petAppointmentCreateRequest.setVideo_id("");
        petAppointmentCreateRequest.setUser_id(userid);
        petAppointmentCreateRequest.setPatient_id(petId);
        petAppointmentCreateRequest.setProblem_info(Problem_info);
        petAppointmentCreateRequest.setDoc_attched(doc_attched);
        petAppointmentCreateRequest.setDoc_feedback("");
        petAppointmentCreateRequest.setDoc_rate(0);
        petAppointmentCreateRequest.setUser_feedback("");
        petAppointmentCreateRequest.setUser_rate(0);
        petAppointmentCreateRequest.setDisplay_date(displaydateandtime);
        petAppointmentCreateRequest.setServer_date_time("");
        petAppointmentCreateRequest.setPayment_id(Payment_id);
        petAppointmentCreateRequest.setPayment_method("Online");
        petAppointmentCreateRequest.setAppointment_types(selectedAppointmentType);
        petAppointmentCreateRequest.setAllergies(Allergies);
        petAppointmentCreateRequest.setAmount(amount);
        petAppointmentCreateRequest.setMobile_type("Android");
        petAppointmentCreateRequest.setService_name("");
        petAppointmentCreateRequest.setService_amount("");
        petAppointmentCreateRequest.setDate_and_time(currentDateandTime);
        petAppointmentCreateRequest.setVisit_type(selectedVisitType);
        petAppointmentCreateRequest.setLocation_id(locationid);
        petAppointmentCreateRequest.setHealth_issue_title(health_issue_title);

        ArrayList<PetAppointmentCreateRequest> PetAppointmentCreateRequestList = new ArrayList<>();
        PetAppointmentCreateRequestList.add(petAppointmentCreateRequest);
        Log.w(TAG,"petAppointmentCreateRequest"+ "--->" + new Gson().toJson(petAppointmentCreateRequest));
        Intent intent = new Intent(getApplicationContext(),PetLoverDoctorChoosePaymentMethodActivity.class);
        intent.putExtra("PetAppointmentCreateRequestList",PetAppointmentCreateRequestList);
        intent.putExtra("doctorname", doctorname);
        intent.putExtra("clinicname", clinicname);
        intent.putExtra("petname", petname);
        intent.putExtra("doctorid", doctorid);
        intent.putExtra("fromactivity", fromactivity);
        intent.putExtra("fromto", fromto);
        intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
        intent.putExtra("selectedTimeSlot", selectedTimeSlot);
        intent.putExtra("amount", amount);
        intent.putExtra("communicationtype", communicationtype);
        intent.putExtra("selectedVisitType", selectedVisitType);
        intent.putExtra("petId", petId);
        intent.putExtra("health_issue_title", health_issue_title);
        intent.putExtra("selectedCommunicationtype", selectedCommunicationtype);
        startActivity(intent);



        return petAppointmentCreateRequest;

    }
    public void showSuceessLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoadingSuccess());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });
    }
    public void hideLoadingSuccess() {
        try {
            Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
            startActivity(intent);
            finish();
            alertDialog.dismiss();

        } catch (Exception ignored) {

        }
    }


    private void showPaymentSuccessalert(String message) {
        try {

            dialog = new Dialog(BookAppointmentActivity.this);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_appointment_payment_success_layout);
            TextView txt_success_msg = dialog.findViewById(R.id.txt_success_msg);
            txt_success_msg.setText(message);
            Button btn_ok = dialog.findViewById(R.id.btn_ok);

            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
                    startActivity(intent);
                    finish();



                }
            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }


    private void notificationSendResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<NotificationSendResponse> call = ApiService.notificationSendResponseCall(RestUtils.getContentType(),notificationSendRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<NotificationSendResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<NotificationSendResponse> call, @NonNull Response<NotificationSendResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"notificationSendResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){




                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<NotificationSendResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"NotificationSendResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private NotificationSendRequest notificationSendRequest() {

        /**
         * status : Payment Failed
         * date : 23-10-2020 11:00 AM
         * appointment_UID :
         * user_id : 601b8ac3204c595ee52582f2
         * doctor_id :
         */

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime = simpleDateFormat.format(new Date());
        NotificationSendRequest notificationSendRequest = new NotificationSendRequest();
        notificationSendRequest.setStatus("Payment Failed");
        notificationSendRequest.setDate(currentDateandTime);
        notificationSendRequest.setAppointment_UID("");
        notificationSendRequest.setUser_id(userid);
        notificationSendRequest.setDoctor_id(doctorid);
        Log.w(TAG,"notificationSendRequest"+ "--->" + new Gson().toJson(notificationSendRequest));
        return notificationSendRequest;
    }


    private void showManageAddressAlert() {
        try {

            dialog = new Dialog(BookAppointmentActivity.this);
            dialog.setContentView(R.layout.alert_manage_addresses_layout);
            dialog.setCancelable(false);
            txt_no_records = dialog.findViewById(R.id.txt_no_records);
            txt_savedaddress = dialog.findViewById(R.id.txt_savedaddress);
            rv_manage_address = dialog.findViewById(R.id.rv_manage_address);
            btn_use_this_addreess = dialog.findViewById(R.id.btn_use_this_addreess);
            txt_no_records.setVisibility(View.GONE);
            txt_savedaddress.setVisibility(View.GONE);
            btn_use_this_addreess.setVisibility(View.GONE);
            rv_manage_address.setVisibility(View.GONE);

            if (new ConnectionDetector(BookAppointmentActivity.this).isNetworkAvailable(BookAppointmentActivity.this)) {
                locationListAddressResponseCall();
            }


            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }

    @SuppressLint("LogNotTimber")
    private void locationListAddressResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LocationListAddressResponse> call = apiInterface.locationListAddressResponseCall(RestUtils.getContentType(), locationListAddressRequest());
        Log.w(TAG,"locationListAddressResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<LocationListAddressResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<LocationListAddressResponse> call, @NonNull Response<LocationListAddressResponse> response) {

                Log.w(TAG,"locationListAddressResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getData() != null && response.body().getData().isEmpty()){
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No new address");
                            rv_manage_address.setVisibility(View.GONE);
                            txt_savedaddress.setVisibility(View.GONE);
                        }
                        else{
                            btn_use_this_addreess.setVisibility(View.VISIBLE);
                            txt_no_records.setVisibility(View.GONE);
                            rv_manage_address.setVisibility(View.VISIBLE);
                            txt_savedaddress.setVisibility(View.VISIBLE);
                            if(response.body().getData() != null) {
                                addressList = response.body().getData();
                            }
                            txt_savedaddress.setText(addressList.size()+" Saved Address");
                            setViewManageAddress();
                        }



                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<LocationListAddressResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"locationListAddressResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private LocationListAddressRequest locationListAddressRequest() {
        LocationListAddressRequest locationListAddressRequest = new LocationListAddressRequest();
        locationListAddressRequest.setUser_id(userid);
        Log.w(TAG,"locationListAddressRequest"+ "--->" + new Gson().toJson(locationListAddressRequest));
        return locationListAddressRequest;
    }
    private void setViewManageAddress() {
        rv_manage_address.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_manage_address.setItemAnimator(new DefaultItemAnimator());
        ManageAddressListVisitAdapter manageAddressListVisitAdapter = new ManageAddressListVisitAdapter(getApplicationContext(), addressList,this,TAG);
        rv_manage_address.setAdapter(manageAddressListVisitAdapter);

    }
    @SuppressLint("LogNotTimber")
    @Override
    public void locationDefaultListener(boolean status, String location_id, String userid) {
        locationid = location_id;
        Log.w(TAG,"locationDefaultListener : "+"status : "+status+" locationid : "+locationid+" userid : "+userid);
    }

}