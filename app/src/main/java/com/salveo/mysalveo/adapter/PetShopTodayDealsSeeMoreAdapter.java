package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.doctor.shop.DoctorProductDetailsActivity;
import com.salveo.mysalveo.petlover.ProductDetailsActivity;
import com.salveo.mysalveo.responsepojo.TodayDealMoreResponse;
import com.salveo.mysalveo.serviceprovider.shop.SPProductDetailsActivity;

import java.util.List;


public class PetShopTodayDealsSeeMoreAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetShopTodayDealsSeeMoreAdapter";
    private Context context;

    List<TodayDealMoreResponse.DataBean> data;

    TodayDealMoreResponse.DataBean currentItem;
    private String fromactivity;
    private String tag;

    public PetShopTodayDealsSeeMoreAdapter(Context context, List<TodayDealMoreResponse.DataBean> data,String fromactivity, String tag) {
        this.context = context;
        this.data = data;
        this.fromactivity = fromactivity;
        this.tag=tag;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shop_todaysdeal_seemore, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = data.get(position);
        Log.w(TAG,"data : "+new Gson().toJson(data));

        if(currentItem.getProduct_title() != null) {
            holder.txt_products_title.setText(currentItem.getProduct_title());
        }
        if(currentItem.getProduct_price() != 0){
            holder.txt_products_price.setText("\u20B9 "+currentItem.getProduct_price());
        }else{
            holder.txt_products_price.setText("\u20B9 "+0);
        }

        if(currentItem.getProduct_discount_price() != 0){
            holder.txt_product_discount_price.setVisibility(View.VISIBLE);
            holder.txt_product_discount_price.setText("\u20B9 "+currentItem.getProduct_discount_price());
            holder.txt_product_discount_price.setPaintFlags(holder.txt_product_discount_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.txt_product_discount_price.setText("\u20B9 "+0);
            holder.txt_product_discount_price.setVisibility(View.GONE);
        }




        if(currentItem.isProduct_fav()){
            holder.img_like.setVisibility(View.VISIBLE);
            holder.img_dislike.setVisibility(View.GONE);
        }
        else{
            holder.img_dislike.setVisibility(View.VISIBLE);
            holder.img_like.setVisibility(View.GONE);
        }
        Log.w(TAG,"discount : "+data.get(position).getProduct_discount());


        if(currentItem.getProduct_discount() != 0){
            holder.txt_products_offer.setVisibility(View.VISIBLE);
            holder.txt_products_offer.setText(currentItem.getProduct_discount()+" % off");
        }
        else{
            holder.txt_products_offer.setVisibility(View.GONE);

        }


        Log.w(TAG,"getThumbnail_image : "+currentItem.getThumbnail_image());

        if (currentItem.getThumbnail_image() != null && !currentItem.getThumbnail_image().isEmpty()) {
            Glide.with(context)
                    .load(currentItem.getThumbnail_image())
                    .into(holder.img_products_image);

        }
        else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_products_image);

        }

        if(currentItem.getProduct_rating() != 0){
            holder.txt_star_rating.setText(currentItem.getProduct_rating()+"");
        }
        else{
            holder.txt_star_rating.setText("0");
        }
        if(currentItem.getProduct_review() != 0){
            holder.txt_review_count.setText(currentItem.getProduct_review()+"");
        }
        else{
            holder.txt_review_count.setText("0");
        }
        holder.ll_root.setOnClickListener(v -> {
            if(fromactivity != null && fromactivity.equalsIgnoreCase("DoctorShopTodayDealsSeeMoreActivity")){
                Intent intent = new Intent(context, DoctorProductDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("productid",currentItem.get_id());
                intent.putExtra("fromactivity",fromactivity);
                intent.putExtra("tag",tag);
                context.startActivity(intent);
            } else if(fromactivity != null && fromactivity.equalsIgnoreCase("SPShopTodayDealsSeeMoreActivity")){
                Intent intent = new Intent(context, SPProductDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("productid",currentItem.get_id());
                intent.putExtra("fromactivity",fromactivity);
                intent.putExtra("tag",tag);
                context.startActivity(intent);
            }else{
                Intent intent = new Intent(context, ProductDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("productid",currentItem.get_id());
                intent.putExtra("fromactivity",fromactivity);
                intent.putExtra("tag",tag);
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_products_title,txt_products_price,txt_products_offer,txt_star_rating,txt_review_count,txt_product_discount_price;
        public ImageView img_products_image,img_like,img_dislike;
        LinearLayout ll_root;


        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_products_title = itemView.findViewById(R.id.txt_products_title);
            txt_products_price = itemView.findViewById(R.id.txt_products_price);
            txt_products_offer = itemView.findViewById(R.id.txt_products_offer);
            txt_star_rating = itemView.findViewById(R.id.txt_star_rating);
            txt_review_count = itemView.findViewById(R.id.txt_review_count);
            ll_root = itemView.findViewById(R.id.ll_root);
            img_products_image = itemView.findViewById(R.id.img_products_image);
            img_like = itemView.findViewById(R.id.img_like);
            img_dislike = itemView.findViewById(R.id.img_dislike);
            txt_product_discount_price = itemView.findViewById(R.id.txt_product_discount_price);
            txt_review_count.setVisibility(View.GONE);

        }




    }


}
