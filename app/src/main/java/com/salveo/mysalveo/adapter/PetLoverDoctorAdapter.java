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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.petlover.DoctorClinicDetailsActivity;
import com.salveo.mysalveo.responsepojo.PetLoverDashboardResponse;


import java.util.List;


public class PetLoverDoctorAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetLoverDoctorAdapter";

    private Context context;





    List<PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean> doctorDetailsResponseList;
    PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean currentItem;




    int size;



    public PetLoverDoctorAdapter(Context context,  List<PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean> doctorDetailsResponseList, RecyclerView inbox_list,int size) {
        this.doctorDetailsResponseList = doctorDetailsResponseList;
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petloverdoctor, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {





          currentItem = doctorDetailsResponseList.get(position);
          if(currentItem.getDoctor_name() != null){
              holder.txt_doctors_name.setText(currentItem.getDoctor_name());
          }
          List<PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean.SpecializationBean> specializationBeanList = currentItem.getSpecialization();

          for(int i=0;i<specializationBeanList.size();i++){
              if(specializationBeanList.get(i).getSpecialization() !=  null) {
                  holder.txt_doctors_specialization.setText(specializationBeanList.get(i).getSpecialization());
              }

          }
          if(doctorDetailsResponseList.get(position).getStar_count() != 0) {
              holder.txt_star_rating.setText(doctorDetailsResponseList.get(position).getStar_count() + "");
          }
          if(doctorDetailsResponseList.get(position).getReview_count() != 0) {
              holder.txt_review_count.setText(doctorDetailsResponseList.get(position).getReview_count() + "");
          }
          if (currentItem.getDoctor_img() != null && !currentItem.getDoctor_img().isEmpty()) {

            Glide.with(context)
                    .load(currentItem.getDoctor_img())
                    .into(holder.img_doctors_image);

        }
          else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_doctors_image);

        }

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DoctorClinicDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("doctorid",doctorDetailsResponseList.get(position).get_id());
                intent.putExtra("doctorname",doctorDetailsResponseList.get(position).getDoctor_name());
                intent.putExtra("reviewcount",doctorDetailsResponseList.get(position).getReview_count());
                intent.putExtra("starcount",doctorDetailsResponseList.get(position).getStar_count());
                intent.putExtra("distance",doctorDetailsResponseList.get(position).getDistance());
                intent.putExtra("fromactivity",TAG);
                Log.w(TAG,"doctorid :"+doctorDetailsResponseList.get(position).get_id());
                context.startActivity(intent);
                }




        });










    }












    @Override
    public int getItemCount() {
        return Math.min(doctorDetailsResponseList.size(), size);



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_doctors_name,txt_doctors_specialization,txt_star_rating,txt_review_count;
        public LinearLayout ll_root;
        public ImageView img_doctors_image;




        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_doctors_name = itemView.findViewById(R.id.txt_doctors_name);
            txt_doctors_specialization = itemView.findViewById(R.id.txt_doctors_specialization);
            img_doctors_image = itemView.findViewById(R.id.img_doctors_image);
            ll_root = itemView.findViewById(R.id.ll_root);
            txt_star_rating = itemView.findViewById(R.id.txt_star_rating);
            txt_review_count = itemView.findViewById(R.id.txt_review_count);
            txt_review_count.setVisibility(View.GONE);



        }




    }










}
