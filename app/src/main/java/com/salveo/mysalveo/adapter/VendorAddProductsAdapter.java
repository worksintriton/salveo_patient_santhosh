package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.salveo.mysalveo.responsepojo.FetctProductByCatDetailsResponse;
import com.salveo.mysalveo.vendor.VendorCreateProductsActivity;

import java.util.List;


public class VendorAddProductsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "VendorAddProductsAdapter";
    private Context context;

    List<FetctProductByCatDetailsResponse.DataBean> fetctProductByCatDetailsList;
    FetctProductByCatDetailsResponse.DataBean currentItem;
    private String fromactivity;
    private String tag;

    public VendorAddProductsAdapter(Context context,  List<FetctProductByCatDetailsResponse.DataBean> fetctProductByCatDetailsList, String fromactivity, String tag) {
        this.context = context;
        this.fetctProductByCatDetailsList = fetctProductByCatDetailsList;
        this.fromactivity = fromactivity;
        this.tag=tag;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vendor_add_products, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = fetctProductByCatDetailsList.get(position);
        if(fetctProductByCatDetailsList.get(position).getProduct_title() != null) {
            holder.txt_products_title.setText(fetctProductByCatDetailsList.get(position).getProduct_title());
        }

        if (fetctProductByCatDetailsList.get(position).getProduct_img() != null && !fetctProductByCatDetailsList.get(position).getProduct_img().isEmpty()) {
            Glide.with(context)
                    .load(fetctProductByCatDetailsList.get(position).getProduct_img())
                    .into(holder.img_products_image);

        }
        else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_products_image);

        }

        if(fetctProductByCatDetailsList.get(position).isStatus()){
            holder.rl_add.setVisibility(View.GONE);
        }else{
            holder.rl_add.setVisibility(View.VISIBLE);
        }


        holder.rl_add.setOnClickListener(v -> {

            Intent i = new Intent(context, VendorCreateProductsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("productid",fetctProductByCatDetailsList.get(position).get_id());
            i.putExtra("producttitle",fetctProductByCatDetailsList.get(position).getProduct_title());
            i.putExtra("productdesc",fetctProductByCatDetailsList.get(position).getProduct_discription());
            i.putExtra("productimage",fetctProductByCatDetailsList.get(position).getProduct_img());
            context.startActivity(i);

        });
    }

    @Override
    public int getItemCount() {
        return fetctProductByCatDetailsList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_products_title,txt_products_price,txt_products_offer,txt_star_rating,txt_review_count;
        public ImageView img_products_image,img_like,img_dislike;
        LinearLayout ll_root;
        RelativeLayout rl_add;


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
            rl_add = itemView.findViewById(R.id.rl_add);
            txt_review_count.setVisibility(View.GONE);

        }




    }


}
