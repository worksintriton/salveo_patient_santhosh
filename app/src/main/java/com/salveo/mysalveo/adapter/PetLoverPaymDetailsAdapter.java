package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.responsepojo.FetchPetloverPaymDetaResponse;

import java.util.List;


public class PetLoverPaymDetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetLoverPaymDetailsAdapter";

    private Context context;

    List<FetchPetloverPaymDetaResponse.DataBean> dataBeanList;

    FetchPetloverPaymDetaResponse.DataBean currentItem;

    int size;

    public PetLoverPaymDetailsAdapter(Context context,List<FetchPetloverPaymDetaResponse.DataBean> dataBeanList, int size) {
        this.dataBeanList = dataBeanList;
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vendor_report, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
          currentItem = dataBeanList.get(position);
          if(currentItem.getAppointment_UID() != null&&!currentItem.getAppointment_UID().isEmpty()){

              holder.txt_appointment_UID.setText(currentItem.getAppointment_UID());

          }

        if(currentItem.getAmount() != null&&!currentItem.getAmount().isEmpty()){

            holder.txt_amount.setText(" + INR " +currentItem.getAmount());

        }

        if(currentItem.getCommunication_type() != null&&!currentItem.getCommunication_type().isEmpty()){

            holder.txt_communication_type.setText(currentItem.getCommunication_type());

        }

        if(currentItem.getDisplay_date() != null&&!currentItem.getDisplay_date().isEmpty()){

            holder.txt_display_date.setText(currentItem.getDisplay_date());

        }



    }

    @Override
    public int getItemCount() {
        return Math.min(dataBeanList.size(), size);



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_appointment_UID,txt_amount,txt_communication_type,txt_display_date;

        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_appointment_UID = itemView.findViewById(R.id.txt_appointment_UID);

            txt_amount = itemView.findViewById(R.id.txt_amount);

            txt_communication_type = itemView.findViewById(R.id.txt_communication_type);

            txt_display_date = itemView.findViewById(R.id.txt_display_date);



        }




    }










}
