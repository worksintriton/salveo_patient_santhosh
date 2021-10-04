package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.petlover.DoctorClinicDetailsActivity;
import com.salveo.mysalveo.petlover.PetAppointment_Doctor_Date_Time_Activity;
import com.salveo.mysalveo.responsepojo.DoctorSearchResponse;

import java.util.List;


public class PetLoverNearByDoctorAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetLoverNearByDoctorAdapter";

    private Context context;

    private List<DoctorSearchResponse.DataBean> doctorDetailsResponseList;
    DoctorSearchResponse.DataBean currentItem;

    private int communication_type;
    private String searchString ;
    private String concatenatedSpcNames= "";

    public PetLoverNearByDoctorAdapter(Context context, List<DoctorSearchResponse.DataBean> doctorDetailsResponseList,int communication_type,String searchString) {
        this.doctorDetailsResponseList = doctorDetailsResponseList;
        this.context = context;
        this.communication_type = communication_type;
        this.searchString = searchString;

    }

    @SuppressLint("LogNotTimber")
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.w(TAG,"onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nearby_doctors, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

          currentItem = doctorDetailsResponseList.get(position);
          if(currentItem.getDoctor_name() != null && !currentItem.getDoctor_name().isEmpty()) {
              holder.txt_doctors_name.setText(currentItem.getDoctor_name());
          }
          else {

              holder.txt_doctors_name.setText("");
          }
        if(currentItem.getCity_name() != null&&!currentItem.getCity_name().isEmpty()) {
            holder.txt_place.setText(currentItem.getCity_name()+ " ");
        }else {

            holder.txt_place.setVisibility(View.GONE);
            holder.view.setVisibility(View.GONE);
        }
        if(currentItem.getDistance() != null&&!currentItem.getDistance().isEmpty()) {
            holder.txt_km.setText(currentItem.getDistance() +" km");
        }else {

            holder.txt_km.setText("");
        }
//          if(currentItem.getDoctor_exp() != 0) {
//              holder.txt_doctors_experience.setVisibility(View.VISIBLE);
//              holder.txt_doctors_experience.setText(currentItem.getDoctor_exp() +" Years Experience");
//          }else{
//              holder.txt_doctors_experience.setVisibility(View.GONE);
//              holder.txt_doctors_experience.setText("");
//          }

          /*if(currentItem.getSpecialization() != null && currentItem.getSpecialization().size()>0){
              List<DoctorSearchResponse.DataBean.SpecializationBean> specializationBeanList = currentItem.getSpecialization();
              for(int i=0;i<specializationBeanList.size();i++){
                  holder.txt_doctors_specialization.setText(specializationBeanList.get(i).getSpecialization());

              }
          }*/


        if(currentItem.getSpecialization() != null && currentItem.getSpecialization().size()>0){
            List<DoctorSearchResponse.DataBean.SpecializationBean> specializationBeanList = currentItem.getSpecialization();

           Log.w(TAG,"specializationBeanList "+new Gson().toJson(specializationBeanList));

           concatenatedSpcNames = "";
           
            for (int i = 0; i < specializationBeanList.size(); i++) {

                Log.w(TAG,"GetSpecialization() "+ specializationBeanList.get(i).getSpecialization());

                concatenatedSpcNames += specializationBeanList.get(i).getSpecialization();

                if (i < specializationBeanList.size() - 1) concatenatedSpcNames += ", ";
            }

            Log.w(TAG,"concatenatedSpcNames "+concatenatedSpcNames);

            holder.txt_doctors_specialization.setText(concatenatedSpcNames);

        }
        else {

            holder.txt_doctors_specialization.setText("");
        }
          if(doctorDetailsResponseList.get(position).getStar_count() != 0) {
              holder.txt_star_rating.setText(doctorDetailsResponseList.get(position).getStar_count() + "");
          }else {

              holder.txt_star_rating.setText("0");
          }
        if(doctorDetailsResponseList.get(position).getAmount() != 0) {
            holder.txt_price.setText("\u20B9 " +doctorDetailsResponseList.get(position).getAmount() + "");
        }else {

            holder.txt_price.setText("\u20B9 " +"0");
        }
          if(doctorDetailsResponseList.get(position).getClinic_name() != null) {
              holder.txt_doctors_clinicname.setVisibility(View.VISIBLE);
              holder.txt_doctors_clinicname.setText(doctorDetailsResponseList.get(position).getClinic_name());
          }else{
              holder.txt_doctors_clinicname.setVisibility(View.GONE);
          }
          /*if(doctorDetailsResponseList.get(position).getAmount() != 0) {
              holder.txt_review_count.setText("\u20B9"+doctorDetailsResponseList.get(position).getAmount());
          }else{
              holder.txt_review_count.setText("0");
          }*/
          if (currentItem.getDoctor_img() != null && !currentItem.getDoctor_img().isEmpty()) {
              Glide.with(context)
                    .load(currentItem.getThumbnail_image())
                    //.load(R.drawable.logo)
                    .into(holder.img_doctors_image);

        }else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_doctors_image);

        }

        Fade fade = new Fade();
        View decor = ((Activity) context).getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        ((Activity) context).getWindow().setEnterTransition(fade);
        ((Activity) context).getWindow().setExitTransition(fade);

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DoctorClinicDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("doctorid",doctorDetailsResponseList.get(position).getUser_id());
                intent.putExtra("doctorname",doctorDetailsResponseList.get(position).getDoctor_name());
                intent.putExtra("reviewcount",doctorDetailsResponseList.get(position).getReview_count());
                intent.putExtra("starcount",doctorDetailsResponseList.get(position).getStar_count());
                intent.putExtra("distance",doctorDetailsResponseList.get(position).getDistance());
                intent.putExtra("fromactivity", "PetCareFragment");
                intent.putExtra("communication_type", communication_type);
                intent.putExtra("searchString", searchString);
                Log.w(TAG,"doctorid :"+doctorDetailsResponseList.get(position).getUser_id());
                context.startActivity(intent);
                }




        });
        holder.btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PetAppointment_Doctor_Date_Time_Activity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("doctorid",doctorDetailsResponseList.get(position).getUser_id());
                intent.putExtra("doctorname",doctorDetailsResponseList.get(position).getDoctor_name());
                intent.putExtra("reviewcount",doctorDetailsResponseList.get(position).getReview_count());
                intent.putExtra("starcount",doctorDetailsResponseList.get(position).getStar_count());
                intent.putExtra("distance",doctorDetailsResponseList.get(position).getDistance());
                intent.putExtra("communicationtype",doctorDetailsResponseList.get(position).getCommunication_type());
                intent.putExtra("amount",doctorDetailsResponseList.get(position).getAmount());
                intent.putExtra("fromactivity", "PetCareFragment");
                intent.putExtra("fromto", "direct");
                Log.w(TAG,"doctorid :"+doctorDetailsResponseList.get(position).getUser_id());
                context.startActivity(intent);
                }




        });










    }












    @Override
    public int getItemCount() {
        return doctorDetailsResponseList.size();



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_doctors_name,txt_price,txt_doctors_specialization,txt_star_rating,txt_place,txt_km,txt_doctors_clinicname,txt_doctors_experience;
        public LinearLayout ll_root;
        public ImageView img_doctors_image;
        public Button btn_book;
        public View view;



        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_doctors_name = itemView.findViewById(R.id.txt_doctors_name);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_doctors_specialization = itemView.findViewById(R.id.txt_doctors_specialization);
            img_doctors_image = itemView.findViewById(R.id.img_doctors_image);
            ll_root = itemView.findViewById(R.id.ll_root);
            txt_star_rating = itemView.findViewById(R.id.txt_star_rating);
            txt_place = itemView.findViewById(R.id.txt_place);
            txt_km = itemView.findViewById(R.id.txt_dist);
            btn_book = itemView.findViewById(R.id.btn_book);
            view = itemView.findViewById(R.id.view9);
          txt_doctors_clinicname = itemView.findViewById(R.id.txt_doctors_clinicname);
           //txt_doctors_experience = itemView.findViewById(R.id.txt_doctors_experience);



        }




    }



}
