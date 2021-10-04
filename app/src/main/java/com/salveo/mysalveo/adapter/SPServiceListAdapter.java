package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.interfaces.SPServiceCheckedListener;
import com.salveo.mysalveo.requestpojo.ServiceProviderRegisterFormCreateRequest;
import com.salveo.mysalveo.responsepojo.SPServiceListResponse;

import java.util.ArrayList;
import java.util.List;



public class SPServiceListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "SPServiceListAdapter";
    private Context mcontext;
    private List<SPServiceListResponse.DataBean.ServiceListBean> spServiceList;
    SPServiceListResponse.DataBean.ServiceListBean currentItem;
    private SPServiceCheckedListener spServiceCheckedListener;
    private List<SPServiceListResponse.DataBean.TimeBean> spTimeList;
    private String strTimeslot;
    private boolean isChbxChecked;
    private String chservice;
    private int amount=0;
    List<ServiceProviderRegisterFormCreateRequest.BusServiceListBean> bus_service_list = new ArrayList<>();

    private boolean isValueAdded = false;




    public SPServiceListAdapter(Context context,List<SPServiceListResponse.DataBean.ServiceListBean> spServiceList,  SPServiceCheckedListener spServiceCheckedListener,List<SPServiceListResponse.DataBean.TimeBean> spTimeList, List<ServiceProviderRegisterFormCreateRequest.BusServiceListBean> bus_service_list,String strTimeslot) {
        this.spServiceList = spServiceList;
        this.mcontext = context;
        this.spServiceCheckedListener = spServiceCheckedListener;
        this.spTimeList = spTimeList;
        this.bus_service_list = bus_service_list;
        this.strTimeslot = strTimeslot;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sp_service_list, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = spServiceList.get(position);
        if(currentItem.getService_list() != null ) {
            holder.txt_servicename.setText(currentItem.getService_list());
        }
        if(strTimeslot != null){
            holder.txt_timeslottype.setText(strTimeslot);
        }
        Log.w(TAG,"spServiceList : "+new Gson().toJson(spServiceList));
        if(spServiceList != null && spServiceList.size() > 0) {
                if (spServiceList.get(position).getAmount() != 0) {
                    holder.txt_amount.setText(spServiceList.get(position).getAmount() + "");
                }if (spServiceList.get(position).getTime_slots() != null) {
                    holder.txt_timeslottype.setText(spServiceList.get(position).getTime_slots());
                }
                if(spServiceList.get(position).isChbxChecked()){
                    holder.checkbox_service_type.setChecked(true);
                }else{
                    holder.checkbox_service_type.setChecked(false);
                }
                isValueAdded = spServiceList.get(position).isValueAdded();
                isChbxChecked = spServiceList.get(position).isChbxChecked();

        }

        holder.checkbox_service_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.w(TAG,"onCheckedChanged spServiceList : "+new Gson().toJson(spServiceList));

                chservice = spServiceList.get(position).getService_list();
                spServiceList.get(position).setBus_service_list(chservice);
                spServiceList.get(position).setTime_slots(strTimeslot);
                spServiceList.get(position).setAmount(amount);

                 isChbxChecked =  isChecked;
                 isValueAdded = spServiceList.get(position).isValueAdded();

                Log.w(TAG,"setOnCheckedChangeListener : "+" isChbxChecked : "+isChbxChecked+" isValueAdded : "+isValueAdded);

                if(isChecked && !isValueAdded){
                    if (holder.checkbox_service_type.isChecked()) {
                        spServiceCheckedListener.onItemSPServiceCheck(position,chservice,isChbxChecked);
                        holder.txt_timeslottype.setText(spServiceList.get(position).getTime_slots());

                    }

                }else{
                    spServiceCheckedListener.onItemSPServiceUnCheck(position,chservice,isChbxChecked);
                    if(strTimeslot != null){
                        holder.txt_timeslottype.setText(strTimeslot);
                    }
                    holder.txt_amount.setText("");

                }

            }
        });







    }
    @Override
    public int getItemCount() {
        return spServiceList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public TextView txt_servicename;
        public CheckBox checkbox_service_type;
        public TextView txt_amount,txt_timeslottype;


        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_servicename = itemView.findViewById(R.id.txt_servicename);
            checkbox_service_type = itemView.findViewById(R.id.checkbox_service_type);
            txt_timeslottype = itemView.findViewById(R.id.txt_timeslottype);
            txt_amount = itemView.findViewById(R.id.txt_amount);


        }

    }





}
