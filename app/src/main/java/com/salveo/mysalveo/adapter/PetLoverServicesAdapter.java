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
import com.salveo.mysalveo.petlover.SelectedServiceActivity;
import com.salveo.mysalveo.responsepojo.PetLoverDashboardResponse;


import java.util.List;



public class PetLoverServicesAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetLoverServicesAdapter";

    private Context context;





    List<PetLoverDashboardResponse.DataBean.DashboarddataBean.ServiceDetailsBean> serviceDetailsResponseList;
    PetLoverDashboardResponse.DataBean.DashboarddataBean.ServiceDetailsBean currentItem;


    int size;

    public PetLoverServicesAdapter(Context context,  List<PetLoverDashboardResponse.DataBean.DashboarddataBean.ServiceDetailsBean> serviceDetailsResponseList, RecyclerView inbox_list, int size) {
        this.serviceDetailsResponseList = serviceDetailsResponseList;
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petlover_services, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
          currentItem = serviceDetailsResponseList.get(position);
          if(currentItem.getService_title() != null) {
              holder.txt_petlover_servicesname.setText(currentItem.getService_title());
          }
          if (currentItem.getService_icon() != null && !currentItem.getService_icon().isEmpty()) {

            Glide.with(context)
                    .load(currentItem.getService_icon())
                    //.load(R.drawable.logo)
                    .into(holder.cv_serviceimage);

           }
          else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.cv_serviceimage);

        }

         /* if(currentItem.getBackground_color() != null) {
              int color = Color.parseColor(currentItem.getBackground_color());
              Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.layout_bg_service);
              Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
              DrawableCompat.setTint(wrappedDrawable, color);
              holder.ll_root.setBackgroundResource(R.drawable.layout_bg_service);


          }*/


        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceDetailsResponseList.get(position).get_id() != null) {
                    Intent intent = new Intent(context, SelectedServiceActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("catid", serviceDetailsResponseList.get(position).get_id());
                    context.startActivity(intent);
                }
                }




        });


    }












    @Override
    public int getItemCount() {
        return Math.min(serviceDetailsResponseList.size(), size);



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_petlover_servicesname;
        public LinearLayout ll_root;
        public ImageView cv_serviceimage;




        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_petlover_servicesname = itemView.findViewById(R.id.txt_petlover_servicesname);
            cv_serviceimage = itemView.findViewById(R.id.cv_serviceimage);
            ll_root = itemView.findViewById(R.id.ll_root);



        }




    }










}
