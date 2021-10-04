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

public class DoctorAddExpAdapter extends RecyclerView.Adapter<DoctorAddExpAdapter.AddExpViewHolder> {
    Context context;
    List<DoctorDetailsByUserIdResponse.DataBean.ExperienceDetailsBean> experience_detailsList;
    View view;
    List<DoctorBusinessInfoUpdateRequest.ExperienceDetailsBean> experience_details;

    public DoctorAddExpAdapter(Context context, List<DoctorDetailsByUserIdResponse.DataBean.ExperienceDetailsBean> experience_detailsList,List<DoctorBusinessInfoUpdateRequest.ExperienceDetailsBean> experience_details) {
        this.context = context;
        this.experience_detailsList = experience_detailsList;
        this.experience_details = experience_details;

    }

    @NonNull
    @Override
    public AddExpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_experience_details_model, parent, false);
        return new AddExpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddExpViewHolder holder, final int position) {
        final DoctorDetailsByUserIdResponse.DataBean.ExperienceDetailsBean experienceDetailsBean = experience_detailsList.get(position);
        if (experienceDetailsBean.getCompany()!= null) {
            holder.cmpnynm.setText(experienceDetailsBean.getCompany());
        }

        if (experienceDetailsBean.getFrom()!= null) {
            holder.tot_exp.setText(experienceDetailsBean.getFrom() + " to " + experienceDetailsBean.getTo() );
        }

        holder.removeImg.setOnClickListener(view -> {
            experience_detailsList.remove(position);
            experience_details.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return experience_detailsList.size();
    }

    public static class AddExpViewHolder extends RecyclerView.ViewHolder {
        TextView cmpnynm,tot_exp;
        ImageView removeImg;
        public AddExpViewHolder(View itemView) {
            super(itemView);
            cmpnynm = itemView.findViewById(R.id.cmpy_name);
            removeImg = itemView.findViewById(R.id.close);
            tot_exp = itemView.findViewById(R.id.total_expr);

        }
    }
}