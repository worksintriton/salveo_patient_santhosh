package com.salveo.mysalveo.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.interfaces.OnItemSelectedTime;
import com.salveo.mysalveo.petlover.PetAppointment_Doctor_Date_Time_Activity;
import com.salveo.mysalveo.responsepojo.PetDoctorAvailableTimeResponse;


import java.util.List;

import es.dmoral.toasty.Toasty;


public class PetMyCalendarAvailableAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_LOADING = 5;
    private  String TAG = "PetMyCalendarAvailableAdapter";

    private List<PetDoctorAvailableTimeResponse.DataBean.TimesBean> timesBeanList;



    private Context context;

    PetDoctorAvailableTimeResponse.DataBean currentItem;
    String strMSgdaystime;
    String strMsg;

    private Boolean isPoll = false;
    String formatedDate,formatedStartDate = "";
    Dialog dialog;
    private OnItemSelectedTime mCallback;
    private int selectedPosition =-1;


    public PetMyCalendarAvailableAdapter(Context context,List<PetDoctorAvailableTimeResponse.DataBean.TimesBean> timesBeanList, RecyclerView inbox_list, PetAppointment_Doctor_Date_Time_Activity petAppointment_doctor_date_time_activity) {
        this.timesBeanList = timesBeanList;
        this.context = context;
        this.mCallback = (OnItemSelectedTime)petAppointment_doctor_date_time_activity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pet_mycalendar_available_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    private void initLayoutOne(ViewHolderOne holder, final int position) {

       // currentItem = dataBeanList.get(position);
        for (int i = 0; i < timesBeanList.size(); i++) {
            holder.txt_days.setText(timesBeanList.get(position).getTime());
            Log.w(TAG,"Times : "+timesBeanList.get(position).getTime());

        }

            holder.txt_days.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(timesBeanList.get(position).isBook_status()) {
                        mCallback.onItemSelectedTime(timesBeanList.get(position).getTime());
                        selectedPosition = position;
                        notifyDataSetChanged();
                    }else{
                        Toasty.warning(context, "Slot Not Available", Toast.LENGTH_SHORT, true).show();

                    }


                }
            });





        if(selectedPosition==position){
            holder.txt_days.setBackgroundResource(R.drawable.button_blue_rounded_corner);
            holder.txt_days.setTextColor(ContextCompat.getColor(context, R.color.white));
        } else{
            holder.txt_days.setBackgroundResource(R.drawable.button_rounded_corner);
            holder.txt_days.setTextColor(ContextCompat.getColor(context, R.color.black));

        }



        if(!timesBeanList.get(position).isBook_status()){
            holder.txt_days.setBackgroundResource(R.drawable.button_gray_rounded_corner);

        }






    }









    @Override
    public int getItemCount() {
        return timesBeanList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_days;




        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_days = itemView.findViewById(R.id.txt_days);












        }




    }














}
