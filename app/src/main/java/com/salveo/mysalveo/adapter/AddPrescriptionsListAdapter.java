package com.salveo.mysalveo.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.requestpojo.PrescriptionCreateRequest;

import java.util.ArrayList;
import java.util.List;

public class AddPrescriptionsListAdapter extends RecyclerView.Adapter<AddPrescriptionsListAdapter.AddImageListHolder> {
    Context context;
    List<PrescriptionCreateRequest.PrescriptionDataBean> prescriptionDataList = new ArrayList<>();
    View view;
    String extension;
    private String TAG = "AddPrescriptionsListAdapter";

    public AddPrescriptionsListAdapter(Context context, List<PrescriptionCreateRequest.PrescriptionDataBean> prescriptionDataList) {
        this.context = context;
        this.prescriptionDataList = prescriptionDataList;

    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_prescription_row, parent, false);
        return new AddImageListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final PrescriptionCreateRequest.PrescriptionDataBean  currentItem = prescriptionDataList.get(position);

        Log.w(TAG,"prescriptionDataList : "+new Gson().toJson(prescriptionDataList));
        holder.tv_tabletname.setText(prescriptionDataList.get(position).getTablet_name());
        holder.tv_quanity.setText(prescriptionDataList.get(position).getQuantity());

        if(currentItem.getConsumption().isMorning()){
            holder.chx_m.setChecked(true);
        }
        if(currentItem.getConsumption().isEvening()){
            holder.chx_a.setChecked(true);
        }
        if(currentItem.getConsumption().isNight()){
            holder.chx_n.setChecked(true);
        }

        holder.removeImg.setOnClickListener(view -> {
            prescriptionDataList.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return prescriptionDataList.size();
    }

    public static class AddImageListHolder extends RecyclerView.ViewHolder {
        TextView tv_tabletname,tv_quanity;
        CheckBox chx_m,chx_a,chx_n;
        ImageView removeImg;
        public AddImageListHolder(View itemView) {
            super(itemView);
            tv_tabletname = itemView.findViewById(R.id.tv_tabletname);
            tv_quanity = itemView.findViewById(R.id.tv_quanity);
            chx_m = itemView.findViewById(R.id.chx_m);
            chx_a = itemView.findViewById(R.id.chx_a);
            chx_n = itemView.findViewById(R.id.chx_n);
            removeImg = itemView.findViewById(R.id.removeImg);
        }
    }


}