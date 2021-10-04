package com.salveo.mysalveo.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.requestpojo.DocBusInfoUploadRequest;

import java.util.List;

public class EditVendorPhotoIDPdfAdapter extends RecyclerView.Adapter<EditVendorPhotoIDPdfAdapter.AddImageListHolder> {
    private static final String TAG = "EditVendorPhotoIDPdfAdapter";
    Context context;
    List< DocBusInfoUploadRequest.PhotoIdPicBean> photoIdPicBeans;
    View view;

    public EditVendorPhotoIDPdfAdapter(Context context, List<DocBusInfoUploadRequest.PhotoIdPicBean> photoIdPicBeans) {
        this.context = context;
        this.photoIdPicBeans = photoIdPicBeans;

    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pdf_upload, parent, false);
        return new AddImageListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final DocBusInfoUploadRequest.PhotoIdPicBean photoIdPicBean = photoIdPicBeans.get(position);
        if (photoIdPicBean.getPhoto_id_pic()!= null) {
            Log.w(TAG,"photoIdPicBean.getPhoto_id_pic() : "+photoIdPicBean.getPhoto_id_pic());
           /* holder.pdf_file.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(R.drawable.pdf_icon)
                    .into(holder.pdf_file);*/


        }else{
           // holder.pdf_file.setVisibility(View.GONE);
        }

        holder.removeImg.setOnClickListener(view -> {
            photoIdPicBeans.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return photoIdPicBeans.size();
    }

    public static class AddImageListHolder extends RecyclerView.ViewHolder {
        ImageView removeImg,pdf_file;
        public AddImageListHolder(View itemView) {
            super(itemView);
            pdf_file = itemView.findViewById(R.id.pdf_file);
            removeImg = itemView.findViewById(R.id.close);
        }
    }


}