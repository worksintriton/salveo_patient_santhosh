package com.salveo.mysalveo.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.LoginActivity;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.PetUpdateOtherInformationRequest;
import com.salveo.mysalveo.responsepojo.PetAddImageResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetOtherInformationsActivity extends AppCompatActivity {

    private  String TAG = "PetOtherInformationsActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_skip)
    TextView txt_skip;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_continue)
    TextView btn_continue;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_spayed)
    RadioGroup rg_spayed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_purebreed)
    RadioGroup rg_purebreed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_friendlywithdogs)
    RadioGroup rg_friendlywithdogs;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_friendlywithcats)
    RadioGroup rg_friendlywithcats;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_friendlywithkids)
    RadioGroup rg_friendlywithkids;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_microchipped)
    RadioGroup rg_microchipped;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_tickfree)
    RadioGroup rg_tickfree;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rg_allowscleanprivate)
    RadioGroup rg_allowscleanprivate;



    private String petid;
    private String fromactivity;

    private boolean pet_spayed = false;
    private boolean pet_purebred = false;
    private boolean pet_frnd_with_dog = false;
    private boolean pet_frnd_with_cat = false;
    private boolean pet_frnd_with_kit = false;
    private boolean pet_microchipped = false;
    private boolean pet_tick_free = false;
    private boolean pet_private_part = false;

    private String selectedAppointmentType = "Normal";
    private String selectedVisitType = "";
    private String petId;
    private String doctorid;
    private String fromto;
    private String Payment_id = "";
    private String Doctor_ava_Date = "";
    private String selectedTimeSlot = "";
    private int amount;
    private String communicationtype = "";


    private String spid,catid,from;
    private String spuserid;
    private String selectedServiceTitle;
    private String petcolor;
    private double petweight;
    private String servicetime;
    private int serviceamount;
    private String petage;
    private int distance;
    private String SP_ava_Date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_other_informations);
        ButterKnife.bind(this);

        avi_indicator.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            petid = extras.getString("petid");
            doctorid = extras.getString("doctorid");
            fromactivity = extras.getString("fromactivity");
            fromto = extras.getString("fromto");
            Doctor_ava_Date = extras.getString("Doctor_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            amount = extras.getInt("amount");
            communicationtype = extras.getString("communicationtype");
            petId = extras.getString("petId");
            Log.w(TAG,"Bundle "+" doctorid : "+doctorid+" selectedTimeSlot : "+selectedTimeSlot+"communicationtype : "+communicationtype+" amount : "+amount+" fromactivity : "+fromactivity+" fromto : "+fromto);


            /*PetServiceAppointment_Doctor_Date_Time_Activity*/
            fromactivity = extras.getString("fromactivity");
            spid = extras.getString("spid");
            catid = extras.getString("catid");
            from = extras.getString("from");
            spuserid = extras.getString("spuserid");
            selectedServiceTitle = extras.getString("selectedServiceTitle");
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
            SP_ava_Date = extras.getString("SP_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            distance = extras.getInt("distance");
            Log.w(TAG,"spid : "+spid +" catid : "+catid+" from : "+from+" serviceamount : "+serviceamount+" servicetime : "+servicetime+" SP_ava_Date : "+SP_ava_Date+" selectedTimeSlot : "+selectedTimeSlot);

            Log.w(TAG,"fromactivity : "+fromactivity+" from : "+from);

        }

        txt_skip.setOnClickListener(v -> {
            if(fromactivity != null && fromactivity.equalsIgnoreCase("BasicPetDetailsActivity")) {
                Intent intent = new Intent(getApplicationContext(), AddYourPetImageOlduserActivity.class);
                intent.putExtra("petid", petid);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);
            }else if(fromactivity != null && fromactivity.equalsIgnoreCase("BasicPetDetailsNewActivity")) {
                Intent intent = new Intent(getApplicationContext(), RegisterYourPetActivity.class);
                intent.putExtra("petid", petid);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);
            } else if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
                Intent intent = new Intent(getApplicationContext(),AddYourPetImageOlduserActivity.class);
                intent.putExtra("spid",spid);
                intent.putExtra("catid",catid);
                intent.putExtra("from",from);
                intent.putExtra("spuserid",spuserid);
                intent.putExtra("selectedServiceTitle",selectedServiceTitle);
                intent.putExtra("serviceamount",serviceamount);
                intent.putExtra("servicetime",servicetime);
                intent.putExtra("SP_ava_Date",SP_ava_Date);
                intent.putExtra("selectedTimeSlot",selectedTimeSlot);
                intent.putExtra("distance",distance);
                intent.putExtra("fromactivity",fromactivity);
                intent.putExtra("petid", petid);
                startActivity(intent);
            }
            else if(fromactivity != null && fromactivity.equalsIgnoreCase("AddNewPetActivity")) {
                Intent intent = new Intent(PetOtherInformationsActivity.this, AddYourPetImageOlduserActivity.class);
                intent.putExtra("doctorid", doctorid);
                intent.putExtra("fromactivity", fromactivity);
                intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
                intent.putExtra("selectedTimeSlot", selectedTimeSlot);
                intent.putExtra("amount", amount);
                intent.putExtra("communicationtype", communicationtype);
                intent.putExtra("fromto", TAG);
                intent.putExtra("petId",petId);
                intent.putExtra("petid",petid);
                startActivity(intent);

            }else{
                Intent intent = new Intent(getApplicationContext(), RegisterYourPetActivity.class);
                intent.putExtra("petid", petid);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);
            }
        });

        btn_continue.setOnClickListener(v -> {
            if (new ConnectionDetector(PetOtherInformationsActivity.this).isNetworkAvailable(PetOtherInformationsActivity.this)) {
                petUpdateOtherInformationResponseCall();
            }
        });

        img_back.setOnClickListener(v -> onBackPressed());

        rg_spayed.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(radioButtonID);
            int position = group.indexOfChild(radioButton);
            pet_spayed = position == 0;


        });
        rg_purebreed.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(radioButtonID);
            int position = group.indexOfChild(radioButton);
            pet_purebred = position == 0;



        });
        rg_friendlywithdogs.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(radioButtonID);
            int position = group.indexOfChild(radioButton);
            pet_frnd_with_dog = position == 0;

        });
        rg_friendlywithcats.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(radioButtonID);
            int position = group.indexOfChild(radioButton);
            pet_frnd_with_cat = position == 0;

        });
        rg_friendlywithkids.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(radioButtonID);
            int position = group.indexOfChild(radioButton);
            pet_frnd_with_kit = position == 0;

        });
        rg_microchipped.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(radioButtonID);
            int position = group.indexOfChild(radioButton);
            pet_microchipped = position == 0;

        });
        rg_tickfree.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(radioButtonID);
            int position = group.indexOfChild(radioButton);
            pet_tick_free = position == 0;

        });
        rg_allowscleanprivate.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(radioButtonID);
            int position = group.indexOfChild(radioButton);
            pet_private_part= position == 0;

        });



    }


    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void petUpdateOtherInformationResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<PetAddImageResponse> call = apiInterface.petUpdateOtherInformationResponseCall(RestUtils.getContentType(), petUpdateOtherInformationRequest());
        Log.w(TAG,"petUpdateOtherInformationResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<PetAddImageResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<PetAddImageResponse> call, @NonNull Response<PetAddImageResponse> response) {

                Log.w(TAG,"petUpdateOtherInformationResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getData().get_id() != null) {
                        if(fromactivity != null && fromactivity.equalsIgnoreCase("BasicPetDetailsActivity")) {
                            Intent intent = new Intent(getApplicationContext(), AddYourPetImageOlduserActivity.class);
                            intent.putExtra("petid", response.body().getData().get_id());
                            intent.putExtra("fromactivity",TAG);
                            startActivity(intent);
                        }
                        else if(fromactivity != null && fromactivity.equalsIgnoreCase("AddNewPetActivity")) {
                            Intent intent = new Intent(PetOtherInformationsActivity.this, AddYourPetImageOlduserActivity.class);
                            intent.putExtra("doctorid", doctorid);
                            intent.putExtra("fromactivity", fromactivity);
                            intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
                            intent.putExtra("selectedTimeSlot", selectedTimeSlot);
                            intent.putExtra("amount", amount);
                            intent.putExtra("communicationtype", communicationtype);
                            intent.putExtra("fromto", TAG);
                            intent.putExtra("petId",petId);
                            intent.putExtra("petid", response.body().getData().get_id());
                            startActivity(intent);

                        }
                        else if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
                            Intent intent = new Intent(getApplicationContext(),AddYourPetImageOlduserActivity.class);
                            intent.putExtra("spid",spid);
                            intent.putExtra("catid",catid);
                            intent.putExtra("from",from);
                            intent.putExtra("spuserid",spuserid);
                            intent.putExtra("selectedServiceTitle",selectedServiceTitle);
                            intent.putExtra("serviceamount",serviceamount);
                            intent.putExtra("servicetime",servicetime);
                            intent.putExtra("SP_ava_Date",SP_ava_Date);
                            intent.putExtra("selectedTimeSlot",selectedTimeSlot);
                            intent.putExtra("distance",distance);
                            intent.putExtra("fromactivity",fromactivity);
                            intent.putExtra("petId",petId);
                            intent.putExtra("petid", response.body().getData().get_id());
                            startActivity(intent);
                        }
                        else if(fromactivity != null && fromactivity.equalsIgnoreCase("BasicPetDetailsNewActivity")) {
                            Intent intent = new Intent(getApplicationContext(), RegisterYourPetActivity.class);
                            intent.putExtra("petid", petid);
                            intent.putExtra("fromactivity",TAG);
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), RegisterYourPetActivity.class);
                            intent.putExtra("petid", response.body().getData().get_id());
                            intent.putExtra("fromactivity",TAG);
                            startActivity(intent);
                        }



                    }


                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<PetAddImageResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"petUpdateOtherInformationResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private PetUpdateOtherInformationRequest petUpdateOtherInformationRequest() {
        /*
         * _id : 603e098e2c2b43125f8cb7f8
         * pet_spayed : false
         * pet_purebred : true
         * pet_frnd_with_dog : false
         * pet_frnd_with_cat : false
         * pet_frnd_with_kit : false
         * pet_microchipped : false
         * pet_tick_free : false
         * pet_private_part : false
         */


        PetUpdateOtherInformationRequest petUpdateOtherInformationRequest = new PetUpdateOtherInformationRequest();
        petUpdateOtherInformationRequest.set_id(petid);
        petUpdateOtherInformationRequest.setPet_spayed(pet_spayed);
        petUpdateOtherInformationRequest.setPet_purebred(pet_purebred);
        petUpdateOtherInformationRequest.setPet_frnd_with_dog(pet_frnd_with_dog);
        petUpdateOtherInformationRequest.setPet_frnd_with_cat(pet_frnd_with_cat);
        petUpdateOtherInformationRequest.setPet_frnd_with_kit(pet_frnd_with_kit);
        petUpdateOtherInformationRequest.setPet_microchipped(pet_microchipped);
        petUpdateOtherInformationRequest.setPet_tick_free(pet_tick_free);
        petUpdateOtherInformationRequest.setPet_private_part(pet_private_part);
        Log.w(TAG,"petUpdateOtherInformationRequest"+ "--->" + new Gson().toJson(petUpdateOtherInformationRequest));
        return petUpdateOtherInformationRequest;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("BasicPetDetailsActivity")) {
            startActivity(new Intent(getApplicationContext(), PetLoverProfileScreenActivity.class));
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("AddNewPetActivity")) {
            Intent intent = new Intent(PetOtherInformationsActivity.this, ConsultationActivity.class);
            intent.putExtra("doctorid", doctorid);
            intent.putExtra("fromactivity", TAG);
            intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
            intent.putExtra("selectedTimeSlot", selectedTimeSlot);
            intent.putExtra("amount", amount);
            intent.putExtra("communicationtype", communicationtype);
            intent.putExtra("fromto", TAG);
            intent.putExtra("petId", petId);
            startActivity(intent);
        }
        if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
            Intent intent = new Intent(getApplicationContext(),ConsultationActivity.class);
            intent.putExtra("spid",spid);
            intent.putExtra("catid",catid);
            intent.putExtra("from",from);
            intent.putExtra("spuserid",spuserid);
            intent.putExtra("selectedServiceTitle",selectedServiceTitle);
            intent.putExtra("serviceamount",serviceamount);
            intent.putExtra("servicetime",servicetime);
            intent.putExtra("SP_ava_Date",SP_ava_Date);
            intent.putExtra("selectedTimeSlot",selectedTimeSlot);
            intent.putExtra("distance",distance);
            intent.putExtra("fromactivity",fromactivity);
            intent.putExtra("petId",petId);
            intent.putExtra("petid", petid);
            startActivity(intent);
        }else{
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

    }
}