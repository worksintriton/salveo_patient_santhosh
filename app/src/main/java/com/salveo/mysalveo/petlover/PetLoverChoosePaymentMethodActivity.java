package com.salveo.mysalveo.petlover;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.salveo.mysalveo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetLoverChoosePaymentMethodActivity extends AppCompatActivity {

    public static String TAG = "PetLoverChoosePaymentMethodActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petlover_choose_payment_method);
        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.choose__payment_method));

        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
    }
}