package com.salveo.mysalveo.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.requestpojo.VendorRegisterFormCreateRequest;

import java.util.List;

public class VendorAddServiceImageListAdapter extends RecyclerView.Adapter<VendorAddServiceImageListAdapter.AddImageListHolder> {
    private String TAG = "VendorAddServiceImageListAdapter";
    Context context;
    List<VendorRegisterFormCreateRequest.BussinessGalleryBean> bus_service_gall_list;
    View view;

    public VendorAddServiceImageListAdapter(Context context,   List<VendorRegisterFormCreateRequest.BussinessGalleryBean> bus_service_gall_list) {
        this.context = context;
        this.bus_service_gall_list = bus_service_gall_list;


    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_images_upload, parent, false);

        return new AddImageListHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final VendorRegisterFormCreateRequest.BussinessGalleryBean busServiceGallBean = bus_service_gall_list.get(position);

        Log.w(TAG,"ImagePic : "+busServiceGallBean.getBussiness_gallery());

        if (busServiceGallBean.getBussiness_gallery()!= null) {

            Glide.with(context)
                    .load(busServiceGallBean.getBussiness_gallery())
                    .into(holder.certificate_pics_1);

        }

        holder.removeImg.setOnClickListener(view -> {
            bus_service_gall_list.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return bus_service_gall_list.size();
    }

    public static class AddImageListHolder extends RecyclerView.ViewHolder {
        ImageView removeImg,certificate_pics_1;
        public AddImageListHolder(View itemView) {
            super(itemView);
            certificate_pics_1 = itemView.findViewById(R.id.certificate_pics_1);
            removeImg = itemView.findViewById(R.id.close);
        }
    }


}