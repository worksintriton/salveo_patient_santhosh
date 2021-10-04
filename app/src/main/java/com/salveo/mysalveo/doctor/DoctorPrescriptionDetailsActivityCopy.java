package com.salveo.mysalveo.doctor;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorPrescriptionDetailsActivityCopy extends AppCompatActivity implements DownloadFile.Listener{
    EditText etdoctorcomments;
    String TAG = "DoctorPrescriptionDetailsActivity";
    AVLoadingIndicatorView avi_indicator;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    PrescriptionCreateRequest.PrescriptionDataBean prescriptionData;

    SessionManager session;


    private String userid;
    private String appoinmentid,doctor_id;

    RecyclerView rv_prescriptiondetails;
    TextView  txt_no_records;
    WebView webView;

    private List<PrescriptionFetchResponse.DataBean.PrescriptionDataBean> prescriptionDataList;
    private String pdfUrl;

    private RemotePDFViewPager remotePDFViewPager;

    private PDFPagerAdapter pdfPagerAdapter;

    private String url;

    private ProgressBar progressBar;

    private LinearLayout pdfLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_prescription_details);
        ButterKnife.bind(this);
        Log.w(TAG,"Oncreate");

        //set the Visibility of the progressbar to visible
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //initialize the pdfLayout
        pdfLayout = findViewById(R.id.pdf_layout);


        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();


        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);
        rv_prescriptiondetails = findViewById(R.id.rv_prescriptiondetails);
        txt_no_records = findViewById(R.id.txt_no_records);



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








        etdoctorcomments = findViewById(R.id.etdoctorcomments);
        etdoctorcomments.setEnabled(false);


    }




    private void prescriptionDetailsResponseCall() {
      avi_indicator.setVisibility(View.VISIBLE);
  //      avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PrescriptionFetchResponse> call = ApiService.prescriptionDetailsResponseCall(RestUtils.getContentType(),prescriptionDetailsRequest());
        Log.w(TAG,"url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<PrescriptionFetchResponse>() {
            @SuppressLint({"SetJavaScriptEnabled", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<PrescriptionFetchResponse> call, @NonNull Response<PrescriptionFetchResponse> response) {
//                avi_indicator.smoothToHide();
                Log.w(TAG,"PrescriptionCreateResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData()!=null){

                            if(response.body().getData().getDoctor_Comments() != null) {
                                etdoctorcomments.setText(response.body().getData().getDoctor_Comments());
                            }
                            if(response.body().getData().getPrescription_data() != null){
                                prescriptionDataList = response.body().getData().getPrescription_data();
                                pdfUrl = response.body().getData().getPDF_format();

                          /*if(prescriptionDataList.size()>0){
                              rv_prescriptiondetails.setVisibility(View.VISIBLE);
                              txt_no_records.setVisibility(View.GONE);
                              setView();
                          }else{
                              rv_prescriptiondetails.setVisibility(View.GONE);
                              txt_no_records.setVisibility(View.VISIBLE);

                          }*/

                                try
                                {
                                    Log.w(TAG,"pdfUrl : "+pdfUrl);
                                    if(pdfUrl != null) {
//                                  webView.requestFocus();
//                                  webView.getSettings().setJavaScriptEnabled(true);
//
//                                  final String googleDocs = "https://docs.google.com/viewer?url=";
//
//                                  String url = googleDocs + pdfUrl;
//                                  webView.loadUrl(pdfUrl);
//                                  webView.setWebViewClient(new WebViewClient() {
//                                      @Override
//                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                                          view.loadUrl(url);
//                                          return true;
//                                      }
//                                  });
//                                  webView.setWebChromeClient(new WebChromeClient() {
//                                      public void onProgressChanged(WebView view, int progress) {
//                                          if (progress < 100) {
//
//                                          }
//                                          if (progress == 100) {
//
//                                          }
//                                      }
//                                  });

                                        //initialize the url variable
                                        url = pdfUrl;

                                        setPdfUrl(url);

                                    }




                             /* Intent intentUrl = new Intent(Intent.ACTION_VIEW);
                              intentUrl.setDataAndType(Uri.parse(pdfUrl), "application/pdf");
                              intentUrl.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                              startActivity(intentUrl);*/
                                }
                                catch (Exception e)
                                {
                                    //Toast.makeText(DoctorPrescriptionDetailsActivity.this, "No PDF Viewer Installed", Toast.LENGTH_LONG).show();
                                }
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
 //               avi_indicator.smoothToHide();

                Log.w(TAG,"PrescriptionCreateResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    private void setPdfUrl(String pdfurl) {

        //Create a RemotePDFViewPager object
        remotePDFViewPager = new RemotePDFViewPager(DoctorPrescriptionDetailsActivityCopy.this, pdfurl, this);

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

    @Override
    public void onSuccess(String url, String destinationPath) {

        // That's the positive case. PDF Download went fine
        pdfPagerAdapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(pdfPagerAdapter);
        updateLayout();
        progressBar.setVisibility(View.GONE);
    }

    private void updateLayout() {

        pdfLayout.addView(remotePDFViewPager,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onFailure(Exception e) {
        // This will be called if download fails
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        // You will get download progress here
        // Always on UI Thread so feel free to update your views here
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (pdfPagerAdapter != null) {
            pdfPagerAdapter.close();
        }
    }
}
