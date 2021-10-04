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
import com.salveo.mysalveo.interfaces.AddMoreServiceRemoveListener;
import com.salveo.mysalveo.requestpojo.ServiceProviderRegisterFormCreateRequest;

import java.util.List;

public class AddMoreServiceAdapter extends RecyclerView.Adapter<AddMoreServiceAdapter.AddExpViewHolder> {
    private static  String TAG =  "AddMoreServiceAdapter";
    Context context;
    private List<ServiceProviderRegisterFormCreateRequest.BusServiceListBean> bus_service_list;
    View view;
    private AddMoreServiceRemoveListener addMoreServiceRemoveListener;

    public AddMoreServiceAdapter(Context context, List<ServiceProviderRegisterFormCreateRequest.BusServiceListBean> bus_service_list,AddMoreServiceRemoveListener addMoreServiceRemoveListener) {
        this.context = context;
        this.bus_service_list = bus_service_list;
        this.addMoreServiceRemoveListener = addMoreServiceRemoveListener;

    }

    @NonNull
    @Override
    public AddExpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sp_add_more_service, parent, false);
        return new AddExpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddExpViewHolder holder, final int position) {
        if (bus_service_list.get(position).getBus_service_list()!= null) {
            holder.txt_servicename.setText(bus_service_list.get(position).getBus_service_list());
        }



        holder.img_close.setOnClickListener(view -> {
            addMoreServiceRemoveListener.addMoreServiceRemoveListener(position,bus_service_list.get(position).getBus_service_list());
            bus_service_list.remove(position);
            Log.w(TAG,"Remove bus_service_list : "+new Gson().toJson(bus_service_list));
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return bus_service_list.size();
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