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

public class DoctorEditClinicImageListAdapter extends RecyclerView.Adapter<DoctorEditClinicImageListAdapter.AddImageListHolder> {
    private String TAG = "DoctorEditClinicImageListAdapter";
    Context context;
    private List<DoctorDetailsByUserIdResponse.DataBean.ClinicPicBean> clinicPicBeansListEdit;
    private List<DoctorBusinessInfoUpdateRequest.ClinicPicBean> clinic_pic;

    View view;

    public DoctorEditClinicImageListAdapter(Context context, List<DoctorDetailsByUserIdResponse.DataBean.ClinicPicBean> clinicPicBeansListEdit,  List<DoctorBusinessInfoUpdateRequest.ClinicPicBean> clinic_pic) {
        this.context = context;
        this.clinicPicBeansListEdit = clinicPicBeansListEdit;
        this.clinic_pic = clinic_pic;


    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_images_upload, parent, false);

        return new AddImageListHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final DoctorDetailsByUserIdResponse.DataBean.ClinicPicBean clinicPicBean = clinicPicBeansListEdit.get(position);

        Log.w(TAG,"ImagePic : "+clinicPicBean.getClinic_pic());

        if (clinicPicBean.getClinic_pic()!= null) {

            Glide.with(context)
                    .load(clinicPicBean.getClinic_pic())
                    .into(holder.certificate_pics_1);

        }

        holder.removeImg.setOnClickListener(view -> {
            clinicPicBeansListEdit.remove(position);
            clinic_pic.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return clinicPicBeansListEdit.size();
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