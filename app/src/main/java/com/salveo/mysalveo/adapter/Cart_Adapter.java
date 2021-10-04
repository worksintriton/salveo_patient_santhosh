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

import com.salveo.mysalveo.interfaces.AddandRemoveProductListener;
import com.salveo.mysalveo.petlover.ProductDetailsActivity;
import com.salveo.mysalveo.responsepojo.CartDetailsResponse;

import java.util.List;



public class Cart_Adapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "Cart_Adapter";
    List<CartDetailsResponse.DataBean> data;
    private Context context;
    private String petImagePath;
    private List<String> petImgBeanList;
    private AddandRemoveProductListener addandRemoveProductListener;


    public Cart_Adapter(Context context, List<CartDetailsResponse.DataBean> data,AddandRemoveProductListener addandRemoveProductListener) {
        this.context = context;
        this.data = data;
        this.addandRemoveProductListener = addandRemoveProductListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cart, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        petImgBeanList = data.get(position).getProduct_id().getProduct_img();

        if (data.get(position).getProduct_id().getProduct_name() != null) {
            holder.txt_products_title.setText(data.get(position).getProduct_id().getProduct_name());
        }
        if (data.get(position).getProduct_id().getDiscount_amount() != 0) {
            holder.txt_original_amount.setVisibility(View.VISIBLE);
            holder.txt_original_amount.setPaintFlags(holder.txt_original_amount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txt_original_amount.setText("\u20B9 " + data.get(position).getProduct_id().getDiscount_amount());

        }else{
            holder.txt_original_amount.setVisibility(View.GONE);
        }
        if (data.get(position).getProduct_id().getCost() != 0) {
            holder.txt_discount_amount.setText("\u20B9 " + data.get(position).getProduct_id().getCost());
        }
        Log.w(TAG,"Discount-->"+data.get(position).getProduct_id().getDiscount());
        if (data.get(position).getProduct_id().getDiscount() != 0) {
            holder.txt_discount.setVisibility(View.VISIBLE);
            holder.txt_discount.setText(data.get(position).getProduct_id().getDiscount() + " % off");
        }else {
            holder.txt_discount.setVisibility(View.GONE);
        }

        if (data.get(position).getProduct_count() != 0) {
            holder.txt_cart_count.setText(data.get(position).getProduct_count()+"");
        }

        if (petImgBeanList != null && petImgBeanList.size() > 0) {
            for (int j = 0; j < petImgBeanList.size(); j++) {
                petImagePath = petImgBeanList.get(0);

            }
        }
        if (data.get(position).getProduct_id().getThumbnail_image() != null && !data.get(position).getProduct_id().getThumbnail_image().isEmpty()) {
            Glide.with(context)
                    .load(data.get(position).getProduct_id().getThumbnail_image())
                    .into(holder.img_products_image);
        }
        else {
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_products_image);

        }

        holder.img_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addandRemoveProductListener.addandRemoveProductListener(data.get(position).getProduct_id().get_id(),"add",data.get(position).getProduct_id().getThreshould(),data.get(position).getProduct_count());


            }
        });
        holder.img_remove_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getProduct_count() != 0) {
                    addandRemoveProductListener.addandRemoveProductListener(data.get(position).getProduct_id().get_id(),"remove",data.get(position).getProduct_id().getThreshould(),data.get(position).getProduct_count());
                }

            }
        });

        if(holder.txt_cart_count.getText().toString() != null && holder.txt_cart_count.getText().toString().equalsIgnoreCase("1")){
            holder.img_remove_product.setBackgroundResource(R.drawable.ic_baseline_delete_24);
        }else{
            holder.img_remove_product.setBackgroundResource(R.drawable.ic_cart_minus);
        }


        if(data.get(position).getProduct_count() == 1){
            Log.w(TAG,"Product Count  conditions : "+data.get(position).getProduct_count());
            holder.img_remove_product.setBackgroundResource(R.drawable.ic_baseline_delete_24);
        }
        else{
            holder.img_remove_product.setBackgroundResource(R.drawable.ic_cart_minus);
        }

        holder.ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getProduct_count() != 0) {
                    addandRemoveProductListener.addandRemoveProductListener(data.get(position).getProduct_id().get_id(),"singleproductremove",data.get(position).getProduct_id().getThreshould(),data.get(position).getProduct_count());
                }

            }
        });
        holder.rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("productid",data.get(position).getProduct_id().get_id());
                intent.putExtra("fromactivity",TAG);
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
        public TextView txt_products_title,txt_discount_amount,txt_original_amount,txt_discount,txt_cart_count;
        public ImageView img_products_image,img_remove_product,img_add_product;
        public LinearLayout ll_delete;
        public RelativeLayout rl_root;

        public ViewHolderOne(View itemView) {
            super(itemView);
            img_products_image = itemView.findViewById(R.id.img_products_image);
            txt_products_title = itemView.findViewById(R.id.txt_products_title);
            txt_discount_amount = itemView.findViewById(R.id.txt_discount_amount);
            txt_original_amount = itemView.findViewById(R.id.txt_original_amount);
            txt_discount = itemView.findViewById(R.id.txt_discount);
            txt_cart_count = itemView.findViewById(R.id.txt_cart_count);
            img_remove_product = itemView.findViewById(R.id.img_remove_product);
            img_add_product = itemView.findViewById(R.id.img_add_product);
            ll_delete = itemView.findViewById(R.id.ll_delete);
            rl_root = itemView.findViewById(R.id.rl_root);




           }




    }


}
