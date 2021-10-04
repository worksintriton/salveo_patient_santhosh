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
import com.salveo.mysalveo.responsepojo.SPServiceListResponse;
import com.salveo.mysalveo.responsepojo.ServiceProviderRegisterFormCreateResponse;

import java.util.List;


public class SPServiceListEditAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "SPServiceListEditAdapter";
    private Context mcontext;
    private List<SPServiceListResponse.DataBean.ServiceListBean> spServiceList;
    private List<ServiceProviderRegisterFormCreateResponse.DataBean.BusServiceListBean> spServiceListEdit;
    SPServiceListResponse.DataBean.ServiceListBean currentItem;
    private SPServiceCheckedListener spServiceCheckedListener;

    private String chservice;
    private String strTimeslot;
    private boolean isChbxChecked;
    private int amount = 0;
    private boolean isValueAdded = false;



    public SPServiceListEditAdapter(Context context, List<SPServiceListResponse.DataBean.ServiceListBean> spServiceList,List<ServiceProviderRegisterFormCreateResponse.DataBean.BusServiceListBean> spServiceListEdit, SPServiceCheckedListener spServiceCheckedListener,String strTimeslot) {
        this.spServiceList = spServiceList;
        this.spServiceListEdit = spServiceListEdit;
        this.mcontext = context;
        this.spServiceCheckedListener = spServiceCheckedListener;
        this.strTimeslot = strTimeslot;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_edit_sp_service_list, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = spServiceList.get(position);
        holder.txt_servicename.setText(currentItem.getService_list());
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

                }
                else{
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
