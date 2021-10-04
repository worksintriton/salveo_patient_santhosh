package com.salveo.mysalveo.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.NotificationActivity;
import com.salveo.mysalveo.adapter.PetLoverPaymDetailsAdapter;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.requestpojo.FetchPetloverPaymDetaRequest;

import com.salveo.mysalveo.responsepojo.FetchPetloverPaymDetaResponse;
import com.salveo.mysalveo.sessionmanager.SessionManager;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;
import com.wwdablu.soumya.simplypdf.DocumentInfo;
import com.wwdablu.soumya.simplypdf.SimplyPdf;
import com.wwdablu.soumya.simplypdf.SimplyPdfDocument;
import com.wwdablu.soumya.simplypdf.composers.TableComposer;
import com.wwdablu.soumya.simplypdf.composers.TextComposer;
import com.wwdablu.soumya.simplypdf.composers.models.TableProperties;
import com.wwdablu.soumya.simplypdf.composers.models.TextProperties;
import com.wwdablu.soumya.simplypdf.composers.models.cell.Cell;
import com.wwdablu.soumya.simplypdf.composers.models.cell.TextCell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetloverPaymentDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "PetloverPaymentDetailsActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_norecords)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_recenttransc)
    RecyclerView rv_recenttransc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    SessionManager session;
    String userid = "";
    private Context mContext;

    List<FetchPetloverPaymDetaResponse.DataBean> dataBeanList;
    private Dialog alertDialog;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_footer)
    View include_petlover_footer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_download)
    ImageView img_download;


    private String active_tag = "1";


    String tag;

    String fromactivity;
    private Dialog dialog;

    private static final int REQUEST_PHONE_CALL =1 ;
    private String sosPhonenumber;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    private TextComposer textComposer;
    private TableComposer tableComposer;
    private SimplyPdfDocument simplyPdfDocument;


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




    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petlover_payment_details);

        ButterKnife.bind(this);

        avi_indicator.setVisibility(View.GONE);

        session = new SessionManager(this);
        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        //userid = "603e27792c2b43125f8cb802";

        Log.w(TAG," userid : "+userid);





        if (new ConnectionDetector(PetloverPaymentDetailsActivity.this).isNetworkAvailable(PetloverPaymentDetailsActivity.this)) {
            fetchpaymntdetailsResponseCall();
        }

        img_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simplyPdfDocument = SimplyPdf.with(PetloverPaymentDetailsActivity.this, new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/test.pdf"))
                        .colorMode(DocumentInfo.ColorMode.COLOR)
                        .paperSize(PrintAttributes.MediaSize.ISO_A4)
                        .margin(DocumentInfo.Margins.DEFAULT)
                        .paperOrientation(DocumentInfo.Orientation.PORTRAIT)
                        .build();

                textComposer = new TextComposer(simplyPdfDocument);

                tableComposer = new TableComposer(simplyPdfDocument);

                testTextComposed();
            }
        });

        refresh_layout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refresh_layout.setEnabled(false);
                        if (new ConnectionDetector(PetloverPaymentDetailsActivity.this).isNetworkAvailable(PetloverPaymentDetailsActivity.this)) {
                            fetchpaymntdetailsResponseCall();
                        }

                    }
                }
        );

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText("Payment Details");

        img_back.setOnClickListener(v -> onBackPressed());

        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
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
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);
            }
        });



        /*home*/
        title_care.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_care.setImageResource(R.drawable.grey_care);
        title_serv.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_serv.setImageResource(R.drawable.grey_servc);
        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);


        rl_home.setOnClickListener(this);
        rl_care.setOnClickListener(this);
        rl_service.setOnClickListener(this);
        rl_shop.setOnClickListener(this);
        rl_comn.setOnClickListener(this);
        rl_homes.setOnClickListener(this);


    }


    private void testTextComposed() {

        TextProperties textProperties = new TextProperties();
        textProperties.textSize = 24;
        textProperties.alignment = Layout.Alignment.ALIGN_CENTER;

        int w_50_cent = simplyPdfDocument.pageWidth() / 2;

        TableProperties colProperties = new TableProperties();
        colProperties.borderWidth = 1;
        colProperties.borderColor = "#000000";
        tableComposer.setProperties(colProperties);

        List<List<Cell>> composedList = new ArrayList<>();
        ArrayList<Cell> rowList = new ArrayList<>();

        //1st row
        rowList.add(new TextCell("Likes", textProperties, w_50_cent));
        rowList.add(new TextCell("Dislikes", textProperties, w_50_cent));
        composedList.add(rowList);

        textProperties = new TextProperties();
        textProperties.textSize = 24;
        textProperties.alignment = Layout.Alignment.ALIGN_CENTER;
        textProperties.alignment = Layout.Alignment.ALIGN_NORMAL;
        textProperties.bulletSymbol = "•";
        textProperties.isBullet = true;

        //2nd row
        rowList = new ArrayList<>();
        Cell cell = new TextCell("Apple", textProperties, w_50_cent);
        rowList.add(cell);
        rowList.add(new TextCell("Guava", textProperties, w_50_cent));
        composedList.add(rowList);

        //3rd row
        rowList = new ArrayList<>();
        rowList.add(new TextCell("Banana", textProperties, w_50_cent));
        rowList.add(new TextCell("Coconut", textProperties, w_50_cent));
        composedList.add(rowList);

        //4th row
        rowList = new ArrayList<>();
        rowList.add(new TextCell("Mango", textProperties, w_50_cent));
        composedList.add(rowList);
        tableComposer.draw(composedList);

        simplyPdfDocument.insertEmptySpace(25);
        textProperties.isBullet = false;

        //new table
        composedList.clear();
        rowList = new ArrayList<>();
        rowList.add(new TextCell("Small Left Text", textProperties, w_50_cent));
        rowList.add(new TextCell("This is a big text on the right column which will be multiple lines.",
                textProperties, w_50_cent));
        composedList.add(rowList);
        tableComposer.draw(composedList);

        simplyPdfDocument.insertEmptySpace(25);

        //new table
        composedList.clear();
        rowList = new ArrayList<>();

        cell = new TextCell(
                "This is a big text a a the right column which will be multiple lines.", textProperties, w_50_cent);
        cell.setHorizontalPadding(25);
        cell.setVerticalPadding(50);
        rowList.add(cell);

        cell = new TextCell("Small right text", textProperties, w_50_cent);
        cell.setHorizontalPadding(25);
        cell.setVerticalPadding(50);
        rowList.add(cell);
        composedList.add(rowList);
        tableComposer.draw(composedList);

        try {
            simplyPdfDocument.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("LongLogTag")
    private void fetchpaymntdetailsResponseCall() {

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchPetloverPaymDetaResponse> call = apiInterface.fetchpetlvrpaymdetaillistResponseCall(RestUtils.getContentType(), fetchPetloverPaymDetaRequest());
        Log.w(TAG,"FetchPetloverPaymDetaResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<FetchPetloverPaymDetaResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<FetchPetloverPaymDetaResponse> call, @NonNull Response<FetchPetloverPaymDetaResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPFavCreateResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {
                        if (response.body().getData() != null&&!response.body().getData().isEmpty()) {

                            dataBeanList = response.body().getData();

                            if(dataBeanList.size()>0){

                                setViewPaymtDetails(dataBeanList);
                            }
                            else {

                                txt_no_records.setText("No Payments Found");

                                rv_recenttransc.setVisibility(View.GONE);
                            }
                        }
                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<FetchPetloverPaymDetaResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"FetchPetloverPaymDetaResponse flr"+ t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setViewPaymtDetails(List<FetchPetloverPaymDetaResponse.DataBean> dataBeanList) {

        rv_recenttransc.setLayoutManager(new LinearLayoutManager(PetloverPaymentDetailsActivity.this));
        rv_recenttransc.setItemAnimator(new DefaultItemAnimator());
        PetLoverPaymDetailsAdapter petLoverDoctorNewFavAdapter = new PetLoverPaymDetailsAdapter(PetloverPaymentDetailsActivity.this, dataBeanList,dataBeanList.size());
        rv_recenttransc.setAdapter(petLoverDoctorNewFavAdapter);
    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private FetchPetloverPaymDetaRequest fetchPetloverPaymDetaRequest() {

        /**
         * user_id : 603e27792c2b43125f8cb802
         */

        FetchPetloverPaymDetaRequest fetchPetloverPaymDetaRequest = new FetchPetloverPaymDetaRequest();
        fetchPetloverPaymDetaRequest.setUser_id(userid);

        Log.w(TAG,"fetchPetloverPaymDetaRequest "+ new Gson().toJson(fetchPetloverPaymDetaRequest));
        return fetchPetloverPaymDetaRequest;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(PetloverPaymentDetailsActivity.this, PetLoverDashboardActivity.class);
        startActivity(i);
        finish();
    }


    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.img_notification:
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                break;
            case R.id.img_cart:
                break;
            case R.id.img_profile:
                Intent intent = new Intent(getApplicationContext(),PetLoverProfileScreenActivity.class);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);
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


}