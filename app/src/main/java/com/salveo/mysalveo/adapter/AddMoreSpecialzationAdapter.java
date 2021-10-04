package com.salveo.mysalveo.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.interfaces.AddMoreSpecialzationRemoveListener;
import com.salveo.mysalveo.requestpojo.ServiceProviderRegisterFormCreateRequest;

import java.util.List;

public class AddMoreSpecialzationAdapter extends RecyclerView.Adapter<AddMoreSpecialzationAdapter.AddExpViewHolder> {
    private static  String TAG =  "AddMoreSpecialzationAdapter";
    Context context;
    private List<ServiceProviderRegisterFormCreateRequest.BusSpecListBean> bus_specialzation_addmore_list;
    View view;
    private AddMoreSpecialzationRemoveListener addMoreSpecialzationRemoveListener;

    public AddMoreSpecialzationAdapter(Context context, List<ServiceProviderRegisterFormCreateRequest.BusSpecListBean> bus_specialzation_addmore_list, AddMoreSpecialzationRemoveListener addMoreSpecialzationRemoveListener) {
        this.context = context;
        this.bus_specialzation_addmore_list = bus_specialzation_addmore_list;
        this.addMoreSpecialzationRemoveListener = addMoreSpecialzationRemoveListener;

    }

    @NonNull
    @Override
    public AddExpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sp_add_more_service, parent, false);
        return new AddExpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddExpViewHolder holder, final int position) {
        if (bus_specialzation_addmore_list.get(position).getBus_spec_list()!= null) {
            holder.txt_servicename.setText(bus_specialzation_addmore_list.get(position).getBus_spec_list());
        }



        holder.img_close.setOnClickListener(view -> {
            addMoreSpecialzationRemoveListener.addMoreSpecialzationRemoveListener(position,bus_specialzation_addmore_list.get(position).getBus_spec_list());
            bus_specialzation_addmore_list.remove(position);
            Log.w(TAG,"Remove bus_specialzation_addmore_list : "+new Gson().toJson(bus_specialzation_addmore_list));
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return bus_specialzation_addmore_list.size();
    }

    public static class AddExpViewHolder extends RecyclerView.ViewHolder {
        TextView txt_servicename;
        ImageView img_close;
        public AddExpViewHolder(View itemView) {
            super(itemView);
            txt_servicename = itemView.findViewById(R.id.txt_servicename);
            img_close = itemView.findViewById(R.id.img_close);


        }
    }
}