package com.salveo.mysalveo.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.adapter.ViewPagerPetloverDetailsAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.responsepojo.BreedTypeResponse;
import com.salveo.mysalveo.responsepojo.PetListResponse;
import com.salveo.mysalveo.responsepojo.PetTypeListResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetloverPetDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "PetloverPetDetailsActivity";

    private boolean vaccinatedstatus,defaultstatus;
    private String petid,userid,petimage,petname,pettype,petbreed,petgender,petcolor;
    private double petweight;

    private String petAgeandMonth = "";

    private String strPetType;
    private String strPetBreedType;
    private String strPetGenderType;

    private int year, month, day;
    String SelectedLastVaccinateddate = "";
    private static final int DATE_PICKER_ID = 0 ;
    private static final int PET_DATE_PICKER_ID = 1 ;

    private Dialog alertDialog;

    private List<PetTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    HashMap<String, String> hashMap_PetTypeid = new HashMap<>();
    private String petTypeId;
    private List<BreedTypeResponse.DataBean> breedTypedataBeanList;
    private String petdob;
    String SelectedPetDOB = "";


    private boolean pet_spayed;
    private boolean pet_purebred;
    private boolean pet_frnd_with_dog;
    private boolean pet_frnd_with_cat;
    private boolean pet_frnd_with_kit;
    private boolean pet_microchipped;
    private boolean pet_tick_free;
    private boolean pet_private_part;
    List<PetListResponse.DataBean.PetImgBean> petImgBeanList;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_petname)
    TextView txt_petname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pettype)
    TextView txt_pettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_petbreed)
    TextView txt_petbreed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_gender)
    TextView txt_pet_gender;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_age)
    TextView txt_pet_age;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_dob)
    TextView txt_pet_dob;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_color)
    TextView txt_pet_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_petbio)
    TextView txt_petbio;

    private String fromactivity;

    int currentPage = 0;
    // BottomSheetBehavior variable
    @SuppressWarnings("rawtypes")
    public BottomSheetBehavior bottomSheetBehavior;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_footer)
    View include_petlover_footer;


    private Dialog dialog;
    private static final int REQUEST_PHONE_CALL =1 ;
    private String sosPhonenumber;
    private String petbio;


    /* Petlover Bottom Navigation */
    /* Petlover Bottom Navigation */

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_care)
    RelativeLayout rl_care;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_care)
    TextView title_care;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_care)
    ImageView img_care;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_service)
    RelativeLayout rl_service;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_serv)
    TextView title_serv;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_serv)
    ImageView img_serv;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_shop)
    RelativeLayout rl_shop;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_shop)
    TextView title_shop;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_shop)
    ImageView img_shop;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_comn)
    RelativeLayout rl_comn;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_community)
    TextView title_community;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_community)
    ImageView img_community;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_homes)
    RelativeLayout rl_homes;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petlover_pet_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        Bundle args = intent.getBundleExtra("petimage");

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
        toolbar_title.setText(getResources().getString(R.string.pet_details));

        if (args != null && !args.isEmpty()) {

            petImgBeanList = (ArrayList<PetListResponse.DataBean.PetImgBean>) args.getSerializable("PETLIST");
        }

        avi_indicator.setVisibility(View.GONE);


        /*serv*/
        title_care.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_care.setImageResource(R.drawable.grey_care);
        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);
        title_serv.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
        img_serv.setImageResource(R.drawable.green_serv);

        rl_home.setOnClickListener(this);
        rl_care.setOnClickListener(this);
        rl_service.setOnClickListener(this);
        rl_shop.setOnClickListener(this);
        rl_comn.setOnClickListener(this);
        rl_homes.setOnClickListener(this);



        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);


        Log.w(TAG, petImgBeanList.toString());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            petid = extras.getString("id");
            userid = extras.getString("userid");
            // petimage = extras.getString("petimage");
            petname = extras.getString("petname");
            strPetType = extras.getString("pettype");
            strPetBreedType = extras.getString("petbreed");
            strPetGenderType = extras.getString("petgender");
            petcolor = extras.getString("petcolor");
            petweight = extras.getDouble("petweight");
            petAgeandMonth = extras.getString("petage");
            vaccinatedstatus = extras.getBoolean("vaccinatedstatus");
            SelectedLastVaccinateddate = extras.getString("vaccinateddate");
            defaultstatus = extras.getBoolean("defaultstatus");
            petdob = extras.getString("petdob");
            petbio = extras.getString("petbio");

            pet_spayed = extras.getBoolean("pet_spayed");
            pet_purebred = extras.getBoolean("pet_purebred");
            pet_frnd_with_dog = extras.getBoolean("pet_frnd_with_dog");
            pet_frnd_with_cat = extras.getBoolean("pet_frnd_with_cat");
            pet_frnd_with_kit = extras.getBoolean("pet_frnd_with_kit");
            pet_microchipped = extras.getBoolean("pet_microchipped");
            pet_tick_free = extras.getBoolean("pet_tick_free");
            pet_private_part = extras.getBoolean("pet_private_part");

            Log.w(TAG, "strPetType : " + strPetType + " strPetBreedType : " + strPetBreedType + " strPetGenderType : " + strPetGenderType);



        }

        if(petbio != null){
            txt_petbio.setText(petbio);
        }else {
            txt_petbio.setText("");
        }

        if(petImgBeanList!=null&&petImgBeanList.size()>0){

             viewpageData(petImgBeanList);
        }

        else {

            PetListResponse.DataBean.PetImgBean petImgBean = new PetListResponse.DataBean.PetImgBean();

            petImgBean.setPet_img(APIClient.BANNER_IMAGE_URL);

            petImgBeanList.add(petImgBean);

            viewpageData(petImgBeanList);
        }


        if(petname!=null&&!petname.isEmpty()){

            txt_petname.setText(petname);

        }

        else {

            txt_petname.setText("NA");
        }


        if(strPetType!=null&&!strPetType.isEmpty()){

            txt_pettype.setText(strPetType);

        }

        else {

            txt_pettype.setText("NA");

        }

        if(strPetBreedType!=null&&!strPetBreedType.isEmpty()){

            txt_petbreed.setText(strPetBreedType);

        }

        else {

            txt_petbreed.setText("NA");
        }

        if(strPetGenderType!=null&&!strPetGenderType.isEmpty()){

            txt_pet_gender.setText(strPetGenderType);

        }

        else {

            txt_pet_gender.setText("NA");

        }
        if(petAgeandMonth!=null&&!petAgeandMonth.isEmpty()){


            txt_pet_age.setText(petAgeandMonth);

        }

        else {

            txt_pet_age.setText("NA");
        }



        if(petdob!=null&&!petdob.isEmpty()){

            txt_pet_dob.setText(petdob);

        }

        else {

            txt_pet_dob.setText("NA");

        }

        if(petcolor!=null&&!petcolor.isEmpty()){

            txt_pet_color.setText(petcolor);

        }

        else {

            txt_pet_color.setText("NA");

        }

        img_back.setOnClickListener(this);
        img_sos.setOnClickListener(this);
        img_notification.setOnClickListener(this);
        img_cart.setOnClickListener(this);
        img_profile.setOnClickListener(this);

        setBottomSheet();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.img_notification:
                startActivity(new Intent(PetloverPetDetailsActivity.this, NotificationActivity.class));
                break;
            case R.id.img_profile:
                goto_Profile();
                break;

            case R.id.rl_homes:
                callDirections("1");
                break;

            case R.id.rl_home:
                callDirections("1");
                break;


            case R.id.rl_shop:
                callDirections("2");
                break;

            case R.id.rl_service:
                callDirections("3");
                break;

            case R.id.rl_care:
                callDirections("4");
                break;


            case R.id.rl_comn:
                callDirections("5");
                break;

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

            Intent intent = new Intent(getApplicationContext(), PetLoverProfileScreenActivity.class);
            startActivity(intent);
            finish();

    }

    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }

    private void goto_Profile() {
        Intent intent = new Intent(getApplicationContext(),PetLoverProfileScreenActivity.class);
        intent.putExtra("fromactivity",TAG);
        startActivity(intent);
    }





    /**
     * method to setup the bottomsheet
     */
    private void setBottomSheet() {

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayouts));

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

        bottomSheetBehavior.setHideable(false);

        bottomSheetBehavior.setFitToContents(false);

        bottomSheetBehavior.setHalfExpandedRatio(0.6f);


        // Capturing the callbacks for bottom sheet
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.w("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.w("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.w("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.w("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.w("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.w("Bottom Sheet Behaviour", "STATE_HALF_EXPANDED");
                        break;
                }


            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {


            }


        });
    }

    private void viewpageData(List<PetListResponse.DataBean.PetImgBean> doctorclinicdetailsResponseList) {

        ViewPager viewPager = findViewById(R.id.pager);

        TabLayout tabLayout = findViewById(R.id.tabDots);

        Timer timer;
        final long DELAY_MS = 600;//delay in milliseconds before task is to be executed
        final long PERIOD_MS = 3000;

        currentPage = 0;

        tabLayout.setupWithViewPager(viewPager, true);

        ViewPagerPetloverDetailsAdapter viewPagerPetloverDetailsAdapter = new ViewPagerPetloverDetailsAdapter(PetloverPetDetailsActivity.this, doctorclinicdetailsResponseList);
        viewPager.setAdapter(viewPagerPetloverDetailsAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == doctorclinicdetailsResponseList.size()) {
                currentPage = 0;
            }
            Log.w(TAG,"currentPage " + currentPage);

            viewPager.setCurrentItem(currentPage++, true);
        };

        timer = new Timer(); // This will create a new Thread

        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {

                Log.w(TAG,"update on " + Update);

                Log.w(TAG,"Delay_MS on " + DELAY_MS);

                Log.w(TAG,"Period_MS on " + PERIOD_MS);

                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

    }


    private void setMargins(RelativeLayout rl_layout, int i, int i1, int i2, int i3) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rl_layout.getLayoutParams();
        params.setMargins(i, i1, i2, i3);
        rl_layout.setLayoutParams(params);
    }


}