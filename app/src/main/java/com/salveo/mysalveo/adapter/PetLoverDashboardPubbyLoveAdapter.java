package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.responsepojo.PetLoverDashboardResponse;

import java.util.List;


public class PetLoverDashboardPubbyLoveAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetLoverDashboardPubbyLoveAdapter";
    private Context context;
    List<PetLoverDashboardResponse.DataBean.DashboarddataBean.MiddleBannerDetailsBean> middleBannerDetailsBeanList;
    PetLoverDashboardResponse.DataBean.DashboarddataBean.MiddleBannerDetailsBean currentItem;

    int size;
    private RecyclerView recyclerView;



    public PetLoverDashboardPubbyLoveAdapter(Context context,  List<PetLoverDashboardResponse.DataBean.DashboarddataBean.MiddleBannerDetailsBean> middleBannerDetailsBeanList, RecyclerView recyclerView, int size) {
        this.context = context;
        this.middleBannerDetailsBeanList = middleBannerDetailsBeanList;
        this.size = size;
        this.recyclerView = recyclerView;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petlover_dashboardproducts, parent, false);

        // recyclerView is your passed view.
        int width = recyclerView.getWidth();
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = (int)(width * 0.8);
        view.setLayoutParams(params);

        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

          currentItem = middleBannerDetailsBeanList.get(position);
          Log.w(TAG,"getImg_path :"+currentItem.getImg_path());

          if (currentItem.getImg_path() != null && !currentItem.getImg_path().isEmpty()) {

            Glide.with(context)
                    .load(currentItem.getImg_path())
                    //.load(R.drawable.logo)
                    .into(holder.img_products_image);

        }
          else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_products_image);

        }

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                }




        });










    }












    @Override
    public int getItemCount() {
        return Math.min(middleBannerDetailsBeanList.size(), size);



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public LinearLayout ll_root;
        public ImageView img_products_image;




        public ViewHolderOne(View itemView) {
            super(itemView);

            img_products_image = itemView.findViewById(R.id.img_products_image);
            ll_root = itemView.findViewById(R.id.ll_root);




        }




    }










}
