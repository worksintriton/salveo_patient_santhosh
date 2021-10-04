package com.salveo.mysalveo.adapter;

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

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.interfaces.DoctorSpecialzationChckedListener;

import com.salveo.mysalveo.responsepojo.DoctorDetailsByUserIdResponse;
import com.salveo.mysalveo.responsepojo.DropDownListResponse;


import java.util.List;


public class DoctorSpecialzationListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "DoctorSpecialzationListAdapter";
    private Context mcontext;
    DropDownListResponse.DataBean.SpecialzationBean currentItem;
    private DoctorSpecialzationChckedListener doctorSpecialzationChckedListener;


    List<DropDownListResponse.DataBean.SpecialzationBean> petSpecilaziationList;
    List<DoctorDetailsByUserIdResponse.DataBean.SpecializationBean> specialzationListEdit;

    public DoctorSpecialzationListAdapter(Context context, List<DropDownListResponse.DataBean.SpecialzationBean> petSpecilaziationList, List<DoctorDetailsByUserIdResponse.DataBean.SpecializationBean> specialzationListEdit, DoctorSpecialzationChckedListener doctorSpecialzationChckedListener) {
        this.petSpecilaziationList = petSpecilaziationList;
        this.specialzationListEdit = specialzationListEdit;
        this.mcontext = context;
        this.doctorSpecialzationChckedListener = doctorSpecialzationChckedListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_specialization_list, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = petSpecilaziationList.get(position);
        holder.txt_spectypes.setText(currentItem.getSpecialzation());


        for(int i=0;i<specialzationListEdit.size();i++){
            if(null!=specialzationListEdit && null!=currentItem.getSpecialzation() && specialzationListEdit.get(i).getSpecialization().equalsIgnoreCase(currentItem.getSpecialzation().trim())){
                holder.chx_spectypes.setChecked(true);
                Log.w(TAG,"ServiceEdit");

            }

        }


        holder.chx_spectypes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String chspecialization = petSpecilaziationList.get(position).getSpecialzation();

                if(isChecked){
                    if (holder.chx_spectypes.isChecked()) {
                        doctorSpecialzationChckedListener.onItemDrSpecialzationCheck(position,chspecialization);
                    }

                }else{

                    doctorSpecialzationChckedListener.onItemDrSpecialzationUnCheck(position,chspecialization);

                }

            }
        });


    }
    @Override
    public int getItemCount() {
        return petSpecilaziationList.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public TextView txt_spectypes;
        public CheckBox chx_spectypes;


        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_spectypes = itemView.findViewById(R.id.spec_name);
            chx_spectypes = itemView.findViewById(R.id.checkbox_specialization_type);


        }

    }

}
