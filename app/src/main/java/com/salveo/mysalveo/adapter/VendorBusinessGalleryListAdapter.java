package com.salveo.mysalveo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.responsepojo.FetchProductByIdResponse;

import java.util.List;

public class VendorBusinessGalleryListAdapter extends RecyclerView.Adapter<VendorBusinessGalleryListAdapter.AddImageListHolder> {
    private String TAG = "VendorBusinessGalleryListAdapter";
    Context context;
    List<FetchProductByIdResponse.VendorDetailsBean.BussinessGalleryBean> bussinessGalleryBeans;
    View view;

    public VendorBusinessGalleryListAdapter(Context context, List<FetchProductByIdResponse.VendorDetailsBean.BussinessGalleryBean> bussinessGalleryBeans) {
        this.context = context;
        this.bussinessGalleryBeans = bussinessGalleryBeans;


    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_business_gallery_card, parent, false);

        return new AddImageListHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final FetchProductByIdResponse.VendorDetailsBean.BussinessGalleryBean bussinessGalleryBean = bussinessGalleryBeans.get(position);

        /*Log.w(TAG,"ImagePic : "+ bussinessGalleryBean.getBussiness_gallery());

        if (bussinessGalleryBean.getBussiness_gallery()!= null) {
            Glide.with(context)
                    .load(bussinessGalleryBean.getBussiness_gallery())
                    .into(holder.img_products_image);

        }
        else {

            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_products_image);
        }*/


    }

    @Override
    public int getItemCount() {
        return bussinessGalleryBeans.size();
    }

    public static class AddImageListHolder extends RecyclerView.ViewHolder {
        ImageView img_products_image;
        public AddImageListHolder(View itemView) {
            super(itemView);
            img_products_image = itemView.findViewById(R.id.img_products_image);
        }
    }


}