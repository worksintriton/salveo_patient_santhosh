package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.petlover.BookAppointmentActivity;
import com.salveo.mysalveo.petlover.DoctorClinicDetailsActivity;
import com.salveo.mysalveo.responsepojo.FilterDoctorResponse;

import java.util.List;


public class PetLoverDoctorFilterAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetLoverDoctorFilterAdapter";

    private Context context;







    private List<FilterDoctorResponse.DataBean> doctorFilterDetailsResponseList;
    FilterDoctorResponse.DataBean currentItem;
    public String concatenatedSpcNames;





    public PetLoverDoctorFilterAdapter(Context context, List<FilterDoctorResponse.DataBean> doctorFilterDetailsResponseList) {
        this.doctorFilterDetailsResponseList = doctorFilterDetailsResponseList;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nearby_doctors, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

          currentItem = doctorFilterDetailsResponseList.get(position);
        if(currentItem.getDoctor_name() != null && !currentItem.getDoctor_name().isEmpty()) {
            holder.txt_doctors_name.setText(currentItem.getDoctor_name());
        }
        else {

            holder.txt_doctors_name.setText("");
        }
        if(currentItem.getCity_name() != null&&!currentItem.getCity_name().isEmpty()) {
            holder.txt_place.setText(currentItem.getCity_name());
        }else {

            holder.txt_place.setVisibility(View.GONE);
            holder.view.setVisibility(View.GONE);
        }
        if(currentItem.getDistance() != null&&!currentItem.getDistance().isEmpty()) {
            holder.txt_km.setText(currentItem.getDistance() +" km");
        }else {

            holder.txt_km.setText("");
        }

        if(doctorFilterDetailsResponseList.get(position).getAmount() != 0) {
            holder.txt_price.setText("\u20B9 " +doctorFilterDetailsResponseList.get(position).getAmount() + "");
        }else {

            holder.txt_price.setText("\u20B9 " +"0");
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
                List<FilterDoctorResponse.DataBean.SpecializationBean> specializationBeanList = currentItem.getSpecialization();

                concatenatedSpcNames = "";
            for (int i = 0; i < specializationBeanList.size(); i++) {
                concatenatedSpcNames += specializationBeanList.get(i).getSpecialization();
                if (i < specializationBeanList.size() - 1) concatenatedSpcNames += ", ";
            }
            holder.txt_doctors_specialization.setText(concatenatedSpcNames);

        }
        else {

            holder.txt_doctors_specialization.setText("");
        }
        if(doctorFilterDetailsResponseList.get(position).getStar_count() != 0) {
            holder.txt_star_rating.setText(doctorFilterDetailsResponseList.get(position).getStar_count() + "");
        }else {

            holder.txt_star_rating.setText("");
        }
//          if(doctorDetailsResponseList.get(position).getClinic_name() != null) {
//              holder.txt_doctors_clinicname.setVisibility(View.VISIBLE);
//              holder.txt_doctors_clinicname.setText(doctorDetailsResponseList.get(position).getClinic_name());
//          }else{
//              holder.txt_doctors_clinicname.setVisibility(View.GONE);
//          }
//          if(doctorDetailsResponseList.get(position).getAmount() != 0) {
//              holder.txt_review_count.setText("\u20B9"+doctorDetailsResponseList.get(position).getAmount());
//          }else{
//              holder.txt_review_count.setText("0");
//          }
        if (currentItem.getDoctor_img() != null && !currentItem.getDoctor_img().isEmpty()) {

            Glide.with(context)
                    .load(currentItem.getDoctor_img())
                    //.load(R.drawable.logo)
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

                Intent intent = new Intent(context, DoctorClinicDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("doctorid",doctorFilterDetailsResponseList.get(position).getUser_id());
                intent.putExtra("doctorname",doctorFilterDetailsResponseList.get(position).getDoctor_name());
                intent.putExtra("reviewcount",doctorFilterDetailsResponseList.get(position).getReview_count());
                intent.putExtra("starcount",doctorFilterDetailsResponseList.get(position).getStar_count());
                intent.putExtra("distance",doctorFilterDetailsResponseList.get(position).getDistance());
                intent.putExtra("fromactivity", "PetCareFragment");
                Log.w(TAG,"doctorid :"+doctorFilterDetailsResponseList.get(position).getUser_id());
                context.startActivity(intent);
                }




        });
        holder.btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, BookAppointmentActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("doctorid",doctorFilterDetailsResponseList.get(position).getUser_id());
                intent.putExtra("doctorname",doctorFilterDetailsResponseList.get(position).getDoctor_name());
                intent.putExtra("reviewcount",doctorFilterDetailsResponseList.get(position).getReview_count());
                intent.putExtra("starcount",doctorFilterDetailsResponseList.get(position).getStar_count());
                intent.putExtra("distance",doctorFilterDetailsResponseList.get(position).getDistance());
                intent.putExtra("communicationtype",doctorFilterDetailsResponseList.get(position).getCommunication_type());
                intent.putExtra("fromto", "direct");
                Log.w(TAG,"doctorid :"+doctorFilterDetailsResponseList.get(position).getUser_id());
                context.startActivity(intent);
                }




        });










    }












    @Override
    public int getItemCount() {
        return doctorFilterDetailsResponseList.size();



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_doctors_name,txt_doctors_specialization,txt_price,txt_star_rating,txt_review_count,txt_place,txt_km;
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
            txt_review_count = itemView.findViewById(R.id.txt_review_count);
            txt_place = itemView.findViewById(R.id.txt_place);
            txt_km = itemView.findViewById(R.id.txt_dist);
            btn_book = itemView.findViewById(R.id.btn_book);
            view = itemView.findViewById(R.id.view9);
            txt_review_count.setVisibility(View.GONE);


        }




    }

}
