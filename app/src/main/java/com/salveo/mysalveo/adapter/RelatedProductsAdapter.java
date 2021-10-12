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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.doctor.shop.DoctorProductDetailsActivity;
import com.salveo.mysalveo.petlover.ProductDetailsActivity;
import com.salveo.mysalveo.responsepojo.FetchProductByIdResponse;
import com.salveo.mysalveo.serviceprovider.shop.SPProductDetailsActivity;

import java.util.List;

public class RelatedProductsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "RelatedProductsAdapter";
    private Context context;

    List<FetchProductByIdResponse.ProductDetailsBean.ProductRelatedBean> product_related;
    FetchProductByIdResponse.ProductDetailsBean.ProductRelatedBean currentItem;
    String prod_type;
    boolean check;
    String fromactivity;


    public RelatedProductsAdapter(Context context, List<FetchProductByIdResponse.ProductDetailsBean.ProductRelatedBean> product_related, String prod_type, boolean b,String fromactivity) {
        this.product_related = product_related;
        this.context = context;
        this.prod_type=prod_type;
        this.check=b;
        this.fromactivity=fromactivity;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shop_card, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = product_related.get(position);

        Log.w(TAG,"rating : "+ currentItem.getProduct_rating());
        if(currentItem.getProduct_rating() == 1){
            holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_graycolor);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
        } else if(currentItem.getProduct_rating() == 2){
            holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
        }else if(currentItem.getProduct_rating() == 3){
            holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
        }else if(currentItem.getProduct_rating() == 4){
            holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
        } else if(currentItem.getProduct_rating() == 5){
            holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_color);
        }



        if(currentItem.getProduct_title() != null){
            holder.txt_products_title.setText(currentItem.getProduct_title());
        }

        if(check){


        }

        else {

            if(prod_type != null&&!prod_type.isEmpty()){
                holder.txt_category_title.setText(prod_type);
            }
        }

        if(currentItem.getProduct_price() != 0){
            holder.txt_products_price.setText("INR "+currentItem.getProduct_price());
        }else{
            holder.txt_products_price.setText("INR 0");
        }

        if(currentItem.getProduct_discount_price() != 0){
            holder.txt_product_discount_price.setVisibility(View.VISIBLE);
            holder.txt_product_discount_price.setText("INR "+currentItem.getProduct_discount_price());
            holder.txt_product_discount_price.setPaintFlags(holder.txt_product_discount_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.txt_product_discount_price.setText("INR "+0);
            holder.txt_product_discount_price.setVisibility(View.GONE);
        }

        if(currentItem.getProduct_discount() != 0){
            holder.txt_products_offer.setVisibility(View.VISIBLE);
            holder.txt_products_offer.setText(currentItem.getProduct_discount()+" % off");
        }else{
            holder.txt_products_offer.setVisibility(View.INVISIBLE);

        }


        if(currentItem.isProduct_fav()){
            Glide.with(context)
                    .load(R.drawable.ic_fav)
                    .into(holder.img_fav);
        }else{
            Glide.with(context)
                    .load(R.drawable.heart_gray)
                    .into(holder.img_fav);
        }

        Log.w(TAG,"Thumbnail_image"+currentItem.getThumbnail_image());

        if (currentItem.getThumbnail_image() != null && !currentItem.getThumbnail_image().isEmpty()) {
            Glide.with(context)
                    .load(currentItem.getThumbnail_image())
                    .into(holder.img_products_image);

        } else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_products_image);

        }
        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fromactivity != null){
                    if(fromactivity.equalsIgnoreCase("DoctorProductDetailsActivity")){
                        Intent intent = new Intent(context, DoctorProductDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("productid",product_related.get(position).get_id());
                        intent.putExtra("fromactivity",TAG);
                        context.startActivity(intent);
                    } else if(fromactivity.equalsIgnoreCase("ProductDetailsActivity")){
                        Intent intent = new Intent(context, ProductDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("productid",product_related.get(position).get_id());
                        intent.putExtra("fromactivity",TAG);
                        context.startActivity(intent);
                    } else if(fromactivity.equalsIgnoreCase("SPProductDetailsActivity")){
                        Intent intent = new Intent(context, SPProductDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("productid",product_related.get(position).get_id());
                        intent.putExtra("fromactivity",TAG);
                        context.startActivity(intent);
                    }
                }

            }

        });



    }

    @Override
    public int getItemCount() {
        return product_related.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public TextView txt_products_title,txt_category_title,txt_products_price,txt_product_discount_price,txt_products_offer;
        public ImageView img_products_image,img_fav;
        public ImageView hand_img1,hand_img2,hand_img3,hand_img4,hand_img5;
        public LinearLayout ll_root;
        public RelativeLayout rl_shop;



        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_products_title = itemView.findViewById(R.id.txt_products_title);
            txt_category_title = itemView.findViewById(R.id.txt_category_title);
            rl_shop = itemView.findViewById(R.id.rl_shop);
            ll_root = itemView.findViewById(R.id.ll_root);
            img_products_image = itemView.findViewById(R.id.img_products_image);
            img_fav = itemView.findViewById(R.id.img_fav);
            txt_products_price = itemView.findViewById(R.id.txt_products_price);
            hand_img1 = itemView.findViewById(R.id.hand_img1);
            hand_img2 = itemView.findViewById(R.id.hand_img2);
            hand_img3 = itemView.findViewById(R.id.hand_img3);
            hand_img4 = itemView.findViewById(R.id.hand_img4);
            hand_img5 = itemView.findViewById(R.id.hand_img5);
            txt_product_discount_price = itemView.findViewById(R.id.txt_product_discount_price);
            txt_products_offer = itemView.findViewById(R.id.txt_products_offer);

        }




    }


}



