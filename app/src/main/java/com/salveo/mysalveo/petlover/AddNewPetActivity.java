package com.salveo.mysalveo.petlover;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.adapter.PetBreedTypesListAdapter;
import com.salveo.mysalveo.adapter.PetTypesListAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.interfaces.PetBreedTypeSelectListener;
import com.salveo.mysalveo.interfaces.PetTypeSelectListener;
import com.salveo.mysalveo.requestpojo.AddYourPetRequest;
import com.salveo.mysalveo.requestpojo.BreedTypeRequest;
import com.salveo.mysalveo.responsepojo.AddYourPetResponse;
import com.salveo.mysalveo.responsepojo.BreedTypeResponse;
import com.salveo.mysalveo.responsepojo.PetTypeListResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewPetActivity extends AppCompatActivity implements PetTypeSelectListener, View.OnClickListener, PetBreedTypeSelectListener {

    private static final String TAG = "AddNewPetActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_petname)
    EditText edt_petname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_petdob)
    RelativeLayout rl_petdob;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_petdob)
    TextView txt_petdob;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_petbio)
    EditText edt_petbio;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_pettypeandbreed)
    LinearLayout ll_pettypeandbreed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_save_continue)
    LinearLayout ll_save_continue;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ivmale)
    ImageView ivmale;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ivfemale)
    ImageView ivfemale;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ivothers)
    ImageView ivothers;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_petandbreedtype)
    TextView txt_petandbreedtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_petcolor)
    EditText edt_petcolor;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_petweight)
    EditText edt_petweight;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rgvaccinated)
    RadioGroup rgvaccinated;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlpetlastvaccinatedagedate)
    RelativeLayout rlpetlastvaccinatedagedate;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.llpetlastvaccinatedagedate)
    LinearLayout llpetlastvaccinatedagedate;

    RecyclerView rv_usertype;
    TextView tv_norecords;
    EditText edt_search_breedtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_petlastvaccinatedage)
    TextView txt_petlastvaccinatedage;

    RecyclerView rv_breedtype;
    TextView tv_breednorecords;
    TextView txt_header;
    LinearLayout ll_pettype,ll_breedtype;
    Button btn_done;
    PetBreedTypesListAdapter petBreedTypesListAdapter;


    private String petType = "";
    private String petTypeId;
    private List<PetTypeListResponse.DataBean.UsertypedataBean> petTypedataBeanList;
    private Dialog alertDialog;
    private List<BreedTypeResponse.DataBean> breedTypedataBeanList;

    String gender = "";
    private int year, month, day;
    String SelectedLastVaccinateddate = "";
    private static final int DATE_PICKER_ID = 0 ;
    private static final int PET_DATE_PICKER_ID = 1 ;

    private String SelectedPetDOB;
    private String petAgeandMonth = "";
    private String selectedRadioButton = "Yes";
    Boolean isvaccinated = true;
    private String userid;
    private String PetBreedType = "";

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


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_pet_details);
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);

        edt_petweight.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,2)});


        SessionManager sessionManager = new SessionManager(AddNewPetActivity.this);
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
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
            SP_ava_Date = extras.getString("SP_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            distance = extras.getInt("distance");
            Log.w(TAG,"spid : "+spid +" catid : "+catid+" from : "+from+" serviceamount : "+serviceamount+" servicetime : "+servicetime+" SP_ava_Date : "+SP_ava_Date+" selectedTimeSlot : "+selectedTimeSlot);

            Log.w(TAG,"fromactivity : "+fromactivity+" from : "+from);

        }

        img_back.setOnClickListener(this);
        ivmale.setOnClickListener(this);
        ivfemale.setOnClickListener(this);
        ivothers.setOnClickListener(this);
        ll_save_continue.setOnClickListener(this);





        if (new ConnectionDetector(AddNewPetActivity.this).isNetworkAvailable(AddNewPetActivity.this)) {
            petTypeListResponseCall();
        }

        ll_pettypeandbreed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupPetType();
            }
        });

        rl_petdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPetDOB();
            }
        });

        rgvaccinated.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonID = rgvaccinated.getCheckedRadioButtonId();
            RadioButton radioButton = rgvaccinated.findViewById(radioButtonID);
            selectedRadioButton = (String) radioButton.getText();
            Log.w(TAG,"selectedRadioButton" + selectedRadioButton);
            if(selectedRadioButton.equalsIgnoreCase("Yes")){
                isvaccinated = true;
                rlpetlastvaccinatedagedate.setVisibility(View.VISIBLE);
                llpetlastvaccinatedagedate.setVisibility(View.VISIBLE);
            }else{
                isvaccinated = false;
                rlpetlastvaccinatedagedate.setVisibility(View.GONE);
                llpetlastvaccinatedagedate.setVisibility(View.GONE);

            }

        });
        rlpetlastvaccinatedagedate.setOnClickListener(v -> SelectDate());




    }

    private void SelectDate() {

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        showDialog(DATE_PICKER_ID);

    }
    private void SelectPetDOB() {

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        showDialog(PET_DATE_PICKER_ID);

    }
    @SuppressLint("LogNotTimber")
    @Override
    protected Dialog onCreateDialog(int id) {
        Log.w(TAG,"onCreateDialog id : "+id);
        if (id == DATE_PICKER_ID) {
            // open datepicker dialog.
            // set date picker for current date
            // add pickerListener listner to date picker
            // return new DatePickerDialog(this, pickerListener, year, month,day);
            DatePickerDialog dialog = new DatePickerDialog(this, pickerListener, year, month, day);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            return dialog;
        }else if (id == PET_DATE_PICKER_ID) {
            // open datepicker dialog.
            // set date picker for current date
            // add pickerListener listner to date picker
            // return new DatePickerDialog(this, pickerListener, year, month,day);
            DatePickerDialog dialog = new DatePickerDialog(this, petdobpickerListener, year, month, day);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            return dialog;
        }
        return null;
    }
    private final DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @SuppressLint("LogNotTimber")
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;



            String strdayOfMonth;
            String strMonth;
            int month1 =(month + 1);
            if(day == 9 || day <9){
                strdayOfMonth = "0"+day;
                Log.w(TAG,"Selected dayOfMonth-->"+strdayOfMonth);
            }else{
                strdayOfMonth = String.valueOf(day);
            }

            if(month1 == 9 || month1 <9){
                strMonth = "0"+month1;
                Log.w(TAG,"Selected month1-->"+strMonth);
            }else{
                strMonth = String.valueOf(month1);
            }

            SelectedLastVaccinateddate = strdayOfMonth + "-" + strMonth + "-" + year;

            // Show selected date
            txt_petlastvaccinatedage.setText(SelectedLastVaccinateddate);

        }
    };
    private final DatePickerDialog.OnDateSetListener petdobpickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @SuppressLint("LogNotTimber")
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            String strdayOfMonth;
            String strMonth;
            int month1 =(month + 1);
            if(day == 9 || day <9){
                strdayOfMonth = "0"+day;
                Log.w(TAG,"Selected dayOfMonth-->"+strdayOfMonth);
            }else{
                strdayOfMonth = String.valueOf(day);
            }

            if(month1 == 9 || month1 <9){
                strMonth = "0"+month1;
                Log.w(TAG,"Selected month1-->"+strMonth);
            }else{
                strMonth = String.valueOf(month1);
            }

            getAge(year,month1,day);

            SelectedPetDOB = strdayOfMonth + "-" + strMonth + "-" + year;

            // Show selected date
            txt_petdob.setText(SelectedPetDOB);

        }
    };


    @SuppressLint("LogNotTimber")
    private void getAge(int year, int month, int day){
        Log.w(TAG,"getAge : year "+year+" month : "+ month+" day : "+day);
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        Log.w(TAG,"age : "+age+" todayyear : "+today.get(Calendar.YEAR)+" dobyear : "+ dob.get(Calendar.YEAR));


        int months = dob.get(Calendar.MONTH) - today.get(Calendar.MONTH);
        int currentmonths = (today.get(Calendar.MONTH))+1;
        Log.w(TAG,"dob months: "+dob.get(Calendar.MONTH)+" currentmonths : "+ currentmonths);

        Log.w(TAG," todayyear : "+today.get(Calendar.YEAR)+" dobyear : "+ dob.get(Calendar.YEAR));

        Log.w(TAG,"Conditions : "+(today.get(Calendar.YEAR) < dob.get(Calendar.YEAR)));
        if(today.get(Calendar.YEAR) < dob.get(Calendar.YEAR)){
            age--;
        }

        Log.w(TAG,"age: "+age+" monthsInt : "+ months);
        String ageS = Integer.toString(age);
        String monthsS = Integer.toString(months);

        Log.w(TAG,"ageS: "+ageS+" months : "+monthsS);

        if(age != 0){

            petAgeandMonth = ageS+" years "+monthsS+" months";
        }else{
            petAgeandMonth = monthsS+" months";

        }



        Log.w(TAG,"ageS: "+ageS+" months : "+monthsS);

    }



    private void showPopupPetType() {
        try {

            Dialog dialog = new Dialog(AddNewPetActivity.this);
            dialog.setContentView(R.layout.alert_pettype_layout);
            dialog.setCanceledOnTouchOutside(false);

            ImageView img_close = dialog.findViewById(R.id.img_close);
             txt_header = dialog.findViewById(R.id.txt_header);
             btn_done = dialog.findViewById(R.id.btn_done);
             ll_pettype = dialog.findViewById(R.id.ll_pettype);
             ll_breedtype = dialog.findViewById(R.id.ll_breedtype);
             rv_breedtype = dialog.findViewById(R.id.rv_breedtype);
             tv_breednorecords = dialog.findViewById(R.id.tv_breednorecords);
             rv_usertype = dialog.findViewById(R.id.rv_usertype);
             tv_norecords = dialog.findViewById(R.id.tv_norecords);
             edt_search_breedtype = dialog.findViewById(R.id.edt_search_breedtype);

            ll_pettype.setVisibility(View.VISIBLE);
            ll_breedtype.setVisibility(View.GONE);
            btn_done.setVisibility(View.GONE);
            btn_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            if(petTypedataBeanList != null && petTypedataBeanList.size()>0){
                rv_usertype.setVisibility(View.VISIBLE);
                tv_norecords.setVisibility(View.GONE);
                setView(petTypedataBeanList);

            }else{
                rv_usertype.setVisibility(View.GONE);
                tv_norecords.setVisibility(View.VISIBLE);
                tv_norecords.setText("No pet types");
            }



            txt_header.setText("Select Type");

            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });


            edt_search_breedtype.addTextChangedListener(new TextWatcher() {
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

    private void filter(String text) {
        //new array list that will hold the filtered data
        List<BreedTypeResponse.DataBean> breedTypedataBeanListFiltered = new ArrayList<>();


        //looping through existing elements
        for (BreedTypeResponse.DataBean s : breedTypedataBeanList) {
            //if the existing elements contains the search input
            if (s.getPet_breed().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                breedTypedataBeanListFiltered.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        petBreedTypesListAdapter.filterList(breedTypedataBeanListFiltered);
    }



    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddNewPetActivity.this);
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.ivmale:
                gender = "male";
                selectMaleImage();
                break;
            case R.id.ivfemale:
                gender = "female";
                selectFemaleImage();
                break;
            case R.id.ivothers:
                gender = "others";
                selectOthersImage();
                break;

            case R.id.ll_save_continue:
                addPetDetailsValidator();
                break;

        }
    }

    private void gotoSignup() {
        Intent intent = new Intent(AddNewPetActivity.this, AddNewPetActivity.class);
        intent.putExtra("UserType",petType);
        intent.putExtra("petTypeId",petTypeId);

        startActivity(intent);
        finish();
    }

    private void selectMaleImage() {
        ivmale.setBackgroundResource(R.drawable.male_color);
        ivfemale.setBackgroundResource(R.drawable.female);
        ivothers.setBackgroundResource(R.drawable.others);

    }
    private void selectFemaleImage() {
        ivmale.setBackgroundResource(R.drawable.male);
        ivfemale.setBackgroundResource(R.drawable.female_color);
        ivothers.setBackgroundResource(R.drawable.others);
    }
    private void selectOthersImage() {
        ivmale.setBackgroundResource(R.drawable.male);
        ivfemale.setBackgroundResource(R.drawable.female);
        ivothers.setBackgroundResource(R.drawable.others_color);
    }

    @SuppressLint("LogNotTimber")
    public void addPetDetailsValidator() {
        boolean can_proceed = true;
        String petandbreedtypes = txt_petandbreedtype.getText().toString();

        Log.w(TAG,"petandbreedtypes : "+petandbreedtypes+" petType : "+petType+" PetBreedType : "+PetBreedType);

        if (petandbreedtypes != null && petandbreedtypes.isEmpty()) {
            showErrorLoading("Please select pet types and breed types");
            can_proceed = false;
        } else if (petType.isEmpty()) {
            showErrorLoading("Please select pet types");
            can_proceed = false;
        }else if (PetBreedType.isEmpty()) {
            showErrorLoading("Please select breed types");
            can_proceed = false;
        } else if (edt_petname.getText().toString().trim().equals("")) {
            edt_petname.setError("Please enter pet name");
            edt_petname.requestFocus();
            can_proceed = false;
        } /*else if (txt_petdob.getText().toString().trim().equals("")) {
           showErrorLoading("Please select date of birth");
           can_proceed = false;
        }*/




        if (can_proceed) {
            if (new ConnectionDetector(AddNewPetActivity.this).isNetworkAvailable(AddNewPetActivity.this)) {
                addYourPetResponseCall();

            }

        }

    }

    @SuppressLint("LogNotTimber")
    private void addYourPetResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddYourPetResponse> call = apiInterface.addYourPetResponseCall(RestUtils.getContentType(), addYourPetRequest());
        Log.w(TAG,"AddYourPetResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AddYourPetResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AddYourPetResponse> call, @NonNull Response<AddYourPetResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"AddYourPetResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        if(response.body().getData() != null) {
                            if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
                                Intent intent = new Intent(getApplicationContext(),PetOtherInformationsActivity.class);
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
                                intent.putExtra("petid", response.body().getData().get_id());
                                intent.putExtra("petId", response.body().getData().get_id());
                                startActivity(intent);
                            }
                             else {
                                Intent intent = new Intent(AddNewPetActivity.this, PetOtherInformationsActivity.class);
                                intent.putExtra("petid", response.body().getData().get_id());
                                intent.putExtra("doctorid", doctorid);
                                intent.putExtra("fromactivity", TAG);
                                intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
                                intent.putExtra("selectedTimeSlot", selectedTimeSlot);
                                intent.putExtra("amount", amount);
                                intent.putExtra("communicationtype", communicationtype);
                                intent.putExtra("fromto", TAG);
                                intent.putExtra("petId", response.body().getData().get_id());
                                startActivity(intent);
                            }


                        }

                    } else {
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<AddYourPetResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("AddYourPetResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private AddYourPetRequest addYourPetRequest() {
        /*
         * user_id : 5fb36ca169f71e30a0ffd3f7
         * pet_img :
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

        double petweight = 0;
        if(edt_petweight.getText().toString() != null && !edt_petweight.getText().toString().isEmpty()){
            petweight = Double.parseDouble(edt_petweight.getText().toString());
        }

        AddYourPetRequest addYourPetRequest = new AddYourPetRequest();
        addYourPetRequest.setUser_id(userid);
        addYourPetRequest.setPet_name(edt_petname.getText().toString());
        addYourPetRequest.setPet_type(petType);
        addYourPetRequest.setPet_breed(PetBreedType);
        addYourPetRequest.setPet_gender(gender);
        addYourPetRequest.setPet_color(edt_petcolor.getText().toString());
        addYourPetRequest.setPet_weight(petweight);
        addYourPetRequest.setPet_age(petAgeandMonth);
        addYourPetRequest.setPet_dob(txt_petdob.getText().toString());
        addYourPetRequest.setVaccinated(isvaccinated);
        addYourPetRequest.setLast_vaccination_date(SelectedLastVaccinateddate);
        addYourPetRequest.setDefault_status(true);
        addYourPetRequest.setDate_and_time(currentDateandTime);
        addYourPetRequest.setMobile_type("Android");
        addYourPetRequest.setPetbio(edt_petbio.getText().toString());
        Log.w(TAG,"addYourPetRequest"+ new Gson().toJson(addYourPetRequest));
        return addYourPetRequest;
    }

    public boolean vaildSelectGender() {

        if(gender.isEmpty()){
            final AlertDialog alertDialog = new AlertDialog.Builder(AddNewPetActivity.this).create();
            alertDialog.setMessage(getString(R.string.err_msg_type_of_gender));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    (dialog, which) -> alertDialog.cancel());
            alertDialog.show();

            return false;
        }

        return true;
    }



    @SuppressLint("LogNotTimber")
    public void petTypeListResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<PetTypeListResponse> call = apiInterface.petTypeListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<PetTypeListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<PetTypeListResponse> call, @NonNull Response<PetTypeListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"PetTypeListResponse" + new Gson().toJson(response.body()));
                        if(response.body().getData().getUsertypedata() != null) {
                            petTypedataBeanList = response.body().getData().getUsertypedata();
                            Log.w(TAG,"petTypedataBeanList size" + petTypedataBeanList.size());
                        }

                    }



                }








            }


            @Override
            public void onFailure(@NonNull Call<PetTypeListResponse> call,@NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"PetTypeListResponse flr"+t.getMessage());
            }
        });

    }


    @SuppressLint("LogNotTimber")
    @Override
    public void userTypeSelectListener(String pettitle, String petid) {
        petType = pettitle;
        petTypeId = petid;
        Log.w(TAG,"userTypeSelectListener : "+" petType : "+petType+" petTypeId : "+petTypeId);

        if(petTypeId != null){
            txt_petandbreedtype.setText(petType);
            ll_pettype.setVisibility(View.GONE);
            ll_breedtype.setVisibility(View.VISIBLE);
            txt_header.setText("Select Breed");
            breedTypeResponseByPetIdCall(petTypeId);
        }



    }

    @SuppressLint("LogNotTimber")
    private void breedTypeResponseByPetIdCall(String petTypeId) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<BreedTypeResponse> call = ApiService.breedTypeResponseByPetIdCall(RestUtils.getContentType(),breedTypeRequest(petTypeId));
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<BreedTypeResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<BreedTypeResponse> call, @NonNull Response<BreedTypeResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "BreedTypeResponse" + "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null) {
                            breedTypedataBeanList = response.body().getData();
                        }
                        if(breedTypedataBeanList != null && breedTypedataBeanList.size()>0){
                            setBreedTypeView(breedTypedataBeanList);
                            rv_breedtype.setVisibility(View.VISIBLE);
                            tv_breednorecords.setVisibility(View.GONE);
                        }else{
                            rv_breedtype.setVisibility(View.GONE);
                            tv_breednorecords.setVisibility(View.VISIBLE);
                            tv_breednorecords.setText("No breed type");
                        }

                    }

                }

            }



            @Override
            public void onFailure(@NonNull Call<BreedTypeResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"BreedTypeResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    private BreedTypeRequest breedTypeRequest(String petTypeId) {
        BreedTypeRequest breedTypeRequest = new BreedTypeRequest();
        breedTypeRequest.setPet_type_id(petTypeId);
        Log.w(TAG,"breedTypeRequest"+ "--->" + new Gson().toJson(breedTypeRequest));
        return breedTypeRequest;
    }


    @SuppressLint("LogNotTimber")
    private void setView(List<PetTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList) {
        rv_usertype.setLayoutManager(new GridLayoutManager(this, 2));
        rv_usertype.setItemAnimator(new DefaultItemAnimator());
        PetTypesListAdapter petTypesListAdapter = new PetTypesListAdapter(getApplicationContext(), usertypedataBeanList,this);
        rv_usertype.setAdapter(petTypesListAdapter);
    }

    @SuppressLint("LogNotTimber")
    private void setBreedTypeView(List<BreedTypeResponse.DataBean> breedTypedataBeanList) {
        rv_breedtype.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_breedtype.setItemAnimator(new DefaultItemAnimator());
         petBreedTypesListAdapter = new PetBreedTypesListAdapter(getApplicationContext(), breedTypedataBeanList,this);
        rv_breedtype.setAdapter(petBreedTypesListAdapter);
    }

    @SuppressLint({"LogNotTimber", "SetTextI18n"})
    @Override
    public void petBreedTypeSelectListener(String petbreedtitle, String petbreedid) {
        PetBreedType = petbreedtitle;
        Log.w(TAG,"petBreedTypeSelectListener : "+"petbreedtitle : "+petbreedtitle+"petbreedid : "+petbreedid);
        btn_done.setVisibility(View.VISIBLE);
        txt_petandbreedtype.setText(petType+", "+petbreedtitle);


    }
}
