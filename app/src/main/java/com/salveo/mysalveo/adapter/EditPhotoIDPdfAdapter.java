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

public class EditPhotoIDPdfAdapter extends RecyclerView.Adapter<EditPhotoIDPdfAdapter.AddImageListHolder> {
    private static final String TAG = "EditPhotoIDPdfAdapter";
    Context context;
    List< DocBusInfoUploadRequest.PhotoIdPicBean> photoIdPicBeans;
    View view;
    String extension;

    public EditPhotoIDPdfAdapter(Context context, List<DocBusInfoUploadRequest.PhotoIdPicBean> photoIdPicBeans) {
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

            String uri = photoIdPicBean.getPhoto_id_pic();
            if(uri.contains(".")) {
                extension = uri.substring(uri.lastIndexOf("."));

                Log.w("extension",extension);
            }

        }

        if(extension != null && !extension.isEmpty()){
            if (extension.equals(".png")||extension.equals(".jpg")||(extension.equals(".jpeg"))) {
                Glide.with(context)
                        .load(photoIdPicBean.getPhoto_id_pic())
                        .into(holder.pdf_file);

            }
        } else {

            holder.pdf_file.setImageResource(R.drawable.pdf_icon);
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