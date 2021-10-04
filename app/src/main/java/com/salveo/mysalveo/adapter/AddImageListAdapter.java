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
import com.salveo.mysalveo.requestpojo.DocBusInfoUploadRequest;

import java.util.List;

public class AddImageListAdapter extends RecyclerView.Adapter<AddImageListAdapter.AddImageListHolder> {
    private String TAG = "AddImageListAdapter";
    Context context;
    List<DocBusInfoUploadRequest.ClinicPicBean> ClinicPicBeans;
    View view;

    public AddImageListAdapter(Context context,List<DocBusInfoUploadRequest.ClinicPicBean> ClinicPicBeans) {
        this.context = context;
        this.ClinicPicBeans = ClinicPicBeans;


    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_images_upload, parent, false);

        return new AddImageListHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final DocBusInfoUploadRequest.ClinicPicBean ClinicPicBean = ClinicPicBeans.get(position);

        Log.w(TAG,"ImagePic : "+ClinicPicBean.getClinic_pic());

        if (ClinicPicBean.getClinic_pic()!= null) {
            Glide.with(context)
                    .load(ClinicPicBean.getClinic_pic())
                    .into(holder.certificate_pics_1);

        }

        holder.removeImg.setOnClickListener(view -> {
            ClinicPicBeans.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return ClinicPicBeans.size();
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