package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.salveo.mysalveo.petlover.Service_Details_Activity;
import com.salveo.mysalveo.responsepojo.FetchPetloverSPFavListResponse;

import java.util.List;


public class PetLoverSPNewFavAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetLoverSPNewFavAdapter";

    private Context context;

    List<FetchPetloverSPFavListResponse.DataBean> dataBeanList;
    FetchPetloverSPFavListResponse.DataBean currentItem;
    int size;

    public PetLoverSPNewFavAdapter(Context context, List<FetchPetloverSPFavListResponse.DataBean> dataBeanList, int size) {
        this.dataBeanList = dataBeanList;
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_spfav_card, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
          currentItem = dataBeanList.get(position);
          if(currentItem.getUser_name() != null){
              holder.txt_doctors_name.setText(currentItem.getUser_name());
          }

          if(currentItem.getService_provider_name() != null){
              holder.txt_clinicname.setText(currentItem.getService_provider_name());
          }

          Log.w(TAG,"Rating : "+currentItem.getRating_count());

          if(currentItem.getRating_count() == 1){
              holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
          } else if(currentItem.getRating_count() == 2){
              holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
          }else if(currentItem.getRating_count() == 3){
              holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
          }else if(currentItem.getRating_count() == 4){
              holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
          } else if(currentItem.getRating_count() == 5){
              holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_color);
          }

       if(currentItem.isFav()){
              Glide.with(context)
                     .load(R.drawable.ic_fav)
                     .into(holder.img_fav);
        }else{
             Glide.with(context)
                     .load(R.drawable.heart_gray).into(holder.img_fav);
          }


        if (currentItem.getThumbnail_image() != null && !currentItem.getThumbnail_image().isEmpty()) {
            Glide.with(context)
                    .load(currentItem.getThumbnail_image())
                    .into(holder.img_doctors_image);

        }
        else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_doctors_image);

        }
        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, Service_Details_Activity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("spid",dataBeanList.get(position).get_id());
                i.putExtra("catid",dataBeanList.get(position).getCat_id());
                i.putExtra("from",TAG);
                i.putExtra("distance",dataBeanList.get(position).getDistance());
                i.putExtra("reviewcount",dataBeanList.get(position).getComments_count());
                i.putExtra("Count_value_start",0);
                i.putExtra("Count_value_end",0);
                context.startActivity(i);
                }

        });
    }

    @Override
    public int getItemCount() {
        return Math.min(dataBeanList.size(), size);



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_doctors_name,txt_clinicname;
        public ImageView img_doctors_image,img_fav;
        public ImageView hand_img1,hand_img2,hand_img3,hand_img4,hand_img5;
        public LinearLayout ll_root;
        public RelativeLayout rl_doctor;




        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_doctors_name = itemView.findViewById(R.id.txt_doctors_name);
            txt_clinicname = itemView.findViewById(R.id.txt_clinicname);
            rl_doctor = itemView.findViewById(R.id.rl_doctor);
            ll_root = itemView.findViewById(R.id.ll_root);
            img_doctors_image = itemView.findViewById(R.id.img_doctors_image);
            img_fav = itemView.findViewById(R.id.img_fav);
            hand_img1 = itemView.findViewById(R.id.hand_img1);
            hand_img2 = itemView.findViewById(R.id.hand_img2);
            hand_img3 = itemView.findViewById(R.id.hand_img3);
            hand_img4 = itemView.findViewById(R.id.hand_img4);
            hand_img5 = itemView.findViewById(R.id.hand_img5);



        }




    }










}
