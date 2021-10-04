package com.salveo.mysalveo.vendor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.appUtils.NumericKeyBoardTransformationMethod;
import com.salveo.mysalveo.requestpojo.ProductEditRequest;
import com.salveo.mysalveo.responsepojo.VendorOrderUpdateResponse;
import com.salveo.mysalveo.utils.ConnectionDetector;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditManageProdcutsActivity extends AppCompatActivity {

    private String TAG = "EditManageProdcutsActivity";
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_product_title)
    EditText edt_product_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_product_price)
    EditText edt_product_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_product_thresould)
    EditText edt_product_thresould;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_product_descriptions)
    EditText edt_product_descriptions;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_update)
    Button btn_update;

    private String productid,producttitle,productthreshold,productdesc;
    private int productprice;
    private Dialog alertDialog;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_manage_prodcuts);
        ButterKnife.bind(this);

        edt_product_thresould.setTransformationMethod(new NumericKeyBoardTransformationMethod());


        avi_indicator.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            productid = extras.getString("productid");
            producttitle = extras.getString("producttitle");
            productprice = extras.getInt("productprice");
            productthreshold = extras.getString("productthreshold");
            productdesc = extras.getString("productdesc");

            if(producttitle != null){
                edt_product_title.setText(producttitle);
            }if(productprice != 0){
                edt_product_price.setText(productprice+"");
            }else{
                edt_product_price.setText("0");
            }
            if(productthreshold != null){
                edt_product_thresould.setText(productthreshold);
            }if(productdesc != null){
                edt_product_descriptions.setText(productdesc);
            }

            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addYourProductValidator();
                }
            });

            img_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });



        }

    }

    public void addYourProductValidator() {
        boolean can_proceed = true;



        if (edt_product_title.getText().toString().isEmpty() && edt_product_price.getText().toString().isEmpty() && edt_product_thresould.getText().toString().isEmpty() && edt_product_descriptions.getText().toString().isEmpty()) {
            Toasty.warning(getApplicationContext(), "Please enter the fields", Toast.LENGTH_SHORT, true).show();
            can_proceed = false;
        } else if (edt_product_title.getText().toString().trim().equals("")) {
            edt_product_title.setError("Please enter product title");
            edt_product_title.requestFocus();
            can_proceed = false;
        }
        else if (edt_product_price.getText().toString().trim().equals("")) {
            edt_product_price.setError("Please enter product price");
            edt_product_price.requestFocus();
            can_proceed = false;
        }else if (edt_product_thresould.getText().toString().trim().equals("")) {
            edt_product_thresould.setError("Please enter product thresould");
            edt_product_thresould.requestFocus();
            can_proceed = false;
        }else if (edt_product_descriptions.getText().toString().trim().equals("")) {
            edt_product_descriptions.setError("Please enter product descriptions");
            edt_product_descriptions.requestFocus();
            can_proceed = false;
        }


        if (can_proceed) {
            if (new ConnectionDetector(EditManageProdcutsActivity.this).isNetworkAvailable(EditManageProdcutsActivity.this)) {
                productEditResponseCall();
            }

        }





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void productEditResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderUpdateResponse> call = apiInterface.productEditResponseCall(RestUtils.getContentType(), productEditRequest());
        Log.w(TAG,"productEditResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorOrderUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Response<VendorOrderUpdateResponse> response) {

                Log.w(TAG,"productEditResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        startActivity(new Intent(getApplicationContext(),ManageProductsActivity.class));
                        finish();
                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<VendorOrderUpdateResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"productEditResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private ProductEditRequest productEditRequest() {

        /*
         * _id : 6034d66598fa826140f6a3a3
         * cost : 100
         * threshould : 100
         * product_name : Cat Food
         * product_discription : This is cat lunch.......
         */


        ProductEditRequest productEditRequest = new ProductEditRequest();
        productEditRequest.set_id(productid);
        productEditRequest.setCost(Integer.parseInt(edt_product_price.getText().toString()));
        productEditRequest.setThreshould(edt_product_thresould.getText().toString());
        productEditRequest.setProduct_name(edt_product_title.getText().toString());
        productEditRequest.setProduct_discription(edt_product_descriptions.getText().toString());
        Log.w(TAG,"productEditRequest"+ "--->" + new Gson().toJson(productEditRequest));
        return productEditRequest;
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
}