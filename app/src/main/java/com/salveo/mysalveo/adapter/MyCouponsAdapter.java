package com.salveo.mysalveo.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.responsepojo.CouponCodeListResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MyCouponsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "MyCouponsAdapter";
    private Context context;

    CouponCodeListResponse.DataBean currentItem;
    private List<CouponCodeListResponse.DataBean> couponcoderesponseList;





    public MyCouponsAdapter(Context context, List<CouponCodeListResponse.DataBean> couponcoderesponseList) {
        this.context = context;
        this.couponcoderesponseList = couponcoderesponseList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mycoupons_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

  @SuppressLint({"LogNotTimber", "SetTextI18n"})
  private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = couponcoderesponseList.get(position);


        if(currentItem.getTitle() != null && !currentItem.getTitle().isEmpty() ) {
            holder.txt_title.setVisibility(View.VISIBLE);
            holder.txt_title.setText(currentItem.getTitle());
        }else{
            holder.txt_title.setVisibility(View.INVISIBLE);
        }
        if(currentItem.getDescri() != null){
            holder.txt_desc.setText(currentItem.getDescri());

        }
      Log.w(TAG,"getExpired_date : "+currentItem.getCoupon_code());

      if(currentItem.getCoupon_code() != null) {
            holder.txt_coupon_code.setText(currentItem.getCoupon_code());
        }
        Log.w(TAG,"getExpired_date : "+currentItem.getExpired_date());




        if(currentItem.getExpired_date() != null && !currentItem.getExpired_date().isEmpty()) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
            Date d = null;
            try {
                d = sdf.parse(currentItem.getExpired_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String formattedDate = null;
            if (d != null) {
                formattedDate = output.format(d);
            }
            Log.w(TAG,"formattedDate : "+formattedDate);
            holder.txt_expired.setVisibility(View.VISIBLE);
            holder.txt_expired.setText("Expires on:"+formattedDate);
        }else{
            holder.txt_expired.setVisibility(View.INVISIBLE);
        }

        if(currentItem.getUsed_status() != null && currentItem.getUsed_status().equalsIgnoreCase("Used")){
            holder.txt_expired.setVisibility(View.VISIBLE);
            holder.txt_expired.setText(currentItem.getUsed_status());
            holder.txt_expired.setTextColor(Color.parseColor("#2896c2"));
        }else{
            holder.txt_expired.setVisibility(View.INVISIBLE);
        }




      if (currentItem.getCoupon_img() != null && !currentItem.getCoupon_img().isEmpty()) {
          Glide.with(context)
                  .load(currentItem.getCoupon_img())
                 // .load(R.drawable.app_logo)
                  .into(holder.img_notify_imge);
          Log.w(TAG,"getCoupon_img : "+currentItem.getCoupon_img());
      } else{
          Glide.with(context)
                  .load(APIClient.PROFILE_IMAGE_URL)
                  .into(holder.img_notify_imge);

      }



        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }




        });


   }

   @Override
    public int getItemCount() {
        
        return couponcoderesponseList.size();
    }
   

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_title,txt_desc,txt_coupon_code,txt_expired;
        public LinearLayout ll_root;
        public ImageView img_notify_imge;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.txt_title);
            txt_desc = itemView.findViewById(R.id.txt_desc);
            txt_coupon_code = itemView.findViewById(R.id.txt_coupon_code);
            txt_expired = itemView.findViewById(R.id.txt_expired);
            img_notify_imge = itemView.findViewById(R.id.img_notify_imge);
            ll_root = itemView.findViewById(R.id.ll_root);

        }

    }

}
