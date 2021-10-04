package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.requestpojo.PrescriptionCreateRequest;

import java.util.List;


public class PrescriptionsDetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PrescriptionsDetailsAdapter";
    private Context mcontext;

    PrescriptionCreateRequest.PrescriptionDataBean currentItem;

    List<PrescriptionCreateRequest.PrescriptionDataBean> prescriptionDataList;



    public PrescriptionsDetailsAdapter(Context context,  List<PrescriptionCreateRequest.PrescriptionDataBean> prescriptionDataList) {
        this.prescriptionDataList = prescriptionDataList;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor_prescriptions_details_list, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
            currentItem = prescriptionDataList.get(position);
            holder.txt_medicine.setText(prescriptionDataList.get(position).getTablet_name());
            holder.txt_noofdays.setText(prescriptionDataList.get(position).getQuantity());

            if(currentItem.getConsumption().isMorning()){
                holder.chx_m.setChecked(true);
            }
            if(currentItem.getConsumption().isEvening()){
                holder.chx_a.setChecked(true);
            }
            if(currentItem.getConsumption().isNight()){
                holder.chx_n.setChecked(true);
            }
           // holder.txt_consumptionperday.setText(prescriptionDataList.get(position).getConsumption());













    }
    @Override
    public int getItemCount() {
        return prescriptionDataList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public TextView txt_medicine,txt_noofdays,txt_consumptionperday;
        CheckBox chx_m,chx_a,chx_n;



        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_medicine = itemView.findViewById(R.id.txt_medicine);
            txt_noofdays = itemView.findViewById(R.id.txt_noofdays);
            txt_consumptionperday = itemView.findViewById(R.id.txt_consumptionperday);
            chx_m = itemView.findViewById(R.id.chx_m);
            chx_a = itemView.findViewById(R.id.chx_a);
            chx_n = itemView.findViewById(R.id.chx_n);



        }


    }

}
