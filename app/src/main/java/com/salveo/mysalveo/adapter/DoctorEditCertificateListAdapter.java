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
import com.salveo.mysalveo.requestpojo.DoctorBusinessInfoUpdateRequest;
import com.salveo.mysalveo.responsepojo.DoctorDetailsByUserIdResponse;

import java.util.List;

public class DoctorEditCertificateListAdapter extends RecyclerView.Adapter<DoctorEditCertificateListAdapter.AddImageListHolder> {
    private String TAG = "DoctorEditClinicImageListAdapter";
    Context context;
    private List<DoctorDetailsByUserIdResponse.DataBean.CertificatePicBean> certificatePicBeansEdit;
    private List<DoctorBusinessInfoUpdateRequest.CertificatePicBean> certificate_pic;
    String extension;

    View view;

    public DoctorEditCertificateListAdapter(Context context, List<DoctorDetailsByUserIdResponse.DataBean.CertificatePicBean> certificatePicBeansEdit, List<DoctorBusinessInfoUpdateRequest.CertificatePicBean> certificate_pic) {
        this.context = context;
        this.certificatePicBeansEdit = certificatePicBeansEdit;
        this.certificate_pic = certificate_pic;


    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pdf_upload, parent, false);

        return new AddImageListHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final DoctorDetailsByUserIdResponse.DataBean.CertificatePicBean certificatePicBean = certificatePicBeansEdit.get(position);

        Log.w(TAG,"ImagePic : "+certificatePicBean.getCertificate_pic());

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
                        .into(holder.pdf_file);

            }
        } else {

            holder.pdf_file.setImageResource(R.drawable.pdf_icon);
        }


        holder.removeImg.setOnClickListener(view -> {
            certificatePicBeansEdit.remove(position);
            certificate_pic.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return certificatePicBeansEdit.size();
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