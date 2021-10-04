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
import com.salveo.mysalveo.petlover.DoctorClinicDetailsActivity;
import com.salveo.mysalveo.responsepojo.PetLoverDashboardResponse;

import java.util.List;


public class PetLoverDoctorNewAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetLoverDoctorNewAdapter";

    private Context context;





    List<PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean> doctorDetailsResponseList;
    PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean currentItem;




    int size;



    public PetLoverDoctorNewAdapter(Context context, List<PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean> doctorDetailsResponseList, RecyclerView inbox_list, int size) {
        this.doctorDetailsResponseList = doctorDetailsResponseList;
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vets_card, parent, false);
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
          } if(currentItem.getClinic_name() != null){
              holder.txt_clinicname.setText(currentItem.getClinic_name());
          }

          Log.w(TAG,"Rating : "+currentItem.getStar_count());

          if(currentItem.getStar_count() == 1){
              holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
          } else if(currentItem.getStar_count() == 2){
              holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
          }else if(currentItem.getStar_count() == 3){
              holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
              holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
          }else if(currentItem.getStar_count() == 4){
              holder.hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
              holder.hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
          } else if(currentItem.getStar_count() == 5){
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
                      .load(R.drawable.heart_gray)
                      .into(holder.img_fav);
          }

          List<PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean.SpecializationBean> specializationBeanList = currentItem.getSpecialization();

          for(int i=0;i<specializationBeanList.size();i++){
              if(specializationBeanList.get(i).getSpecialization() !=  null) {
                //  holder.txt_doctors_specialization.setText(specializationBeanList.get(i).getSpecialization());
              }

          }
         /* if(doctorDetailsResponseList.get(position).getStar_count() != 0) {
              holder.txt_star_rating.setText(doctorDetailsResponseList.get(position).getStar_count() + "");
          }
          if(doctorDetailsResponseList.get(position).getReview_count() != 0) {
              holder.txt_review_count.setText(doctorDetailsResponseList.get(position).getReview_count() + "");
          }*/

        Log.w(TAG,"Doctor Thumbnail_image : "+currentItem.getThumbnail_image());

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

                //Opens the AppDetailActivity showing the selected App Card
                //Log.d("Debugtext","Card with position " + getAdapterPosition() + " was touched.");
//                Intent intent = new Intent(context, DoctorClinicDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        context, holder.img_doctors_image, holder.img_doctors_image.getTransitionName());
//                ActivityCompat.startActivity(context, intent, options.toBundle());

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
