package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.interfaces.ProductsFavListener;
import com.salveo.mysalveo.responsepojo.DoctorProductFavListResponse;

import java.util.List;


public class DoctorProductsFavAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "DoctorProductsFavAdapter";
    private Context context;

    private List<DoctorProductFavListResponse.DataBean> productsList;


    DoctorProductFavListResponse.DataBean currentItem;
    String fromactivity;
    ProductsFavListener productsFavListener;

    public DoctorProductsFavAdapter(Context context, List<DoctorProductFavListResponse.DataBean> productsList, String fromactivity, ProductsFavListener productsFavListener) {
        this.context = context;
        this.productsList = productsList;
        this.fromactivity = fromactivity;
        this.productsFavListener = productsFavListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_products_fav_doctor, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = productsList.get(position);
        holder.txt_products_title.setText(productsList.get(position).getProduct_title());
        if(productsList.get(position).getProduct_price() != 0){
            holder.txt_products_price.setText("\u20B9 "+productsList.get(position).getProduct_price());
        }else{
            holder.txt_products_price.setText("\u20B9 "+0);
        }

        if(productsList.get(position).isProduct_fav()){
            holder.img_like.setVisibility(View.VISIBLE);
            holder.img_dislike.setVisibility(View.GONE);
        }
        else{
            holder.img_dislike.setVisibility(View.VISIBLE);
            holder.img_like.setVisibility(View.GONE);
        }

        Log.w(TAG,"discount : "+productsList.get(position).getProduct_discount());


        if(productsList.get(position).getProduct_discount() != 0){
            holder.txt_products_offer.setVisibility(View.VISIBLE);
            holder.txt_products_offer.setText(productsList.get(position).getProduct_discount()+" % off");
        }else{
            holder.txt_products_offer.setVisibility(View.GONE);

        }

        if (productsList.get(position).getProduct_img() != null && !productsList.get(position).getProduct_img().isEmpty()) {
            Glide.with(context)
                        .load(productsList.get(position).getProduct_img())
                        .into(holder.img_products_image);

            }
           else{
                Glide.with(context)
                        .load(APIClient.PROFILE_IMAGE_URL)
                        .into(holder.img_products_image);

            }

        if(currentItem.getProduct_rating() != 0){
            holder.txt_star_rating.setText(currentItem.getProduct_rating()+"");
        }else{
            holder.txt_star_rating.setText("0");
        }
        if(currentItem.getProduct_review() != 0){
            holder.txt_review_count.setText(currentItem.getProduct_review()+"");
        }else{
            holder.txt_review_count.setText("0");
        }

        holder.img_settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(context, v);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_products_fav_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String titleName = String.valueOf(item.getTitle());
                        if(titleName.equalsIgnoreCase("Remove")){
                            if(productsList.get(position).isProduct_fav()){
                                productsFavListener.productsFavListener(productsList.get(position).isProduct_fav(),productsList.get(position).get_id());
                            }


                        }
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });







    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_products_title,txt_products_price,txt_products_offer,txt_star_rating,txt_review_count;
        public ImageView img_products_image,img_like,img_dislike,img_settings;
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
            img_settings = itemView.findViewById(R.id.img_settings);
            txt_review_count.setVisibility(View.GONE);



        }

    }


}
