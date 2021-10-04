package com.salveo.mysalveo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.requestpojo.DoctorBusinessInfoUpdateRequest;
import com.salveo.mysalveo.responsepojo.DoctorDetailsByUserIdResponse;

import java.util.List;

public class DoctorAddEducAdapter extends RecyclerView.Adapter<DoctorAddEducAdapter.AddEduViewHolder> {
    Context context;
    List<DoctorDetailsByUserIdResponse.DataBean.EducationDetailsBean> education_detailsList;
    View view;
    private List<DoctorBusinessInfoUpdateRequest.EducationDetailsBean> education_details;

    public DoctorAddEducAdapter(Context context, List<DoctorDetailsByUserIdResponse.DataBean.EducationDetailsBean> education_detailsList,List<DoctorBusinessInfoUpdateRequest.EducationDetailsBean> education_details) {
        this.context = context;
        this.education_detailsList = education_detailsList;
        this.education_details = education_details;

    }

    @NonNull
    @Override
    public AddEduViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_education_details_model, parent, false);
        return new AddEduViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddEduViewHolder holder, final int position) {
        final DoctorDetailsByUserIdResponse.DataBean.EducationDetailsBean educationDetailsBean = education_detailsList.get(position);
        if (educationDetailsBean.getEducation()!= null) {
            holder.eduName.setText(educationDetailsBean.getEducation());
        }

        if (educationDetailsBean.getYear()!= null) {
            holder.eduyr.setText(educationDetailsBean.getYear());
        }

        holder.removeImg.setOnClickListener(view -> {
            education_detailsList.remove(position);
            education_details.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return education_detailsList.size();
    }

    public static class AddEduViewHolder extends RecyclerView.ViewHolder {
        TextView eduName,eduyr;
        ImageView removeImg;
        public AddEduViewHolder(View itemView) {
            super(itemView);
            eduName = itemView.findViewById(R.id.educ_name);
            removeImg = itemView.findViewById(R.id.close);
            eduyr = itemView.findViewById(R.id.edu_yr);
        }
    }
}