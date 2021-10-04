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

public class AddCertPdfAdapter extends RecyclerView.Adapter<AddCertPdfAdapter.AddImageListHolder> {
    Context context;
    List<DocBusInfoUploadRequest.CertificatePicBean > certificatePicBeans;
    View view;
    String extension;

    public AddCertPdfAdapter(Context context,List<DocBusInfoUploadRequest.CertificatePicBean> certificatePicBean) {
        this.context = context;
        this.certificatePicBeans = certificatePicBean;

    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pdf_upload, parent, false);
        return new AddImageListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final DocBusInfoUploadRequest.CertificatePicBean certificatePicBean = certificatePicBeans.get(position);

        if (certificatePicBean.getCertificate_pic()!= null) {

            String uri = certificatePicBean.getCertificate_pic();
            if(uri.contains(".")) {
                extension = uri.substring(uri.lastIndexOf("."));

                Log.w("extension",extension);
            }

        }

        if(extension != null && !extension.isEmpty()) {
            if (extension.equals(".png") || extension.equals(".jpg") || (extension.equals(".jpeg"))) {
                Glide.with(context)
                        .load(certificatePicBean.getCertificate_pic())
                        .into(holder.certificate_pics_1);

            }
        } else {

            holder.certificate_pics_1.setImageResource(R.drawable.pdf_icon);
        }

        holder.removeImg.setOnClickListener(view -> {
            certificatePicBeans.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return certificatePicBeans.size();
    }

    public static class AddImageListHolder extends RecyclerView.ViewHolder {
        ImageView removeImg,certificate_pics_1;
        public AddImageListHolder(View itemView) {
            super(itemView);
            certificate_pics_1 = itemView.findViewById(R.id.pdf_file);
            removeImg = itemView.findViewById(R.id.close);
        }
    }


}