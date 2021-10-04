package com.salveo.mysalveo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.responsepojo.DoctorDetailsResponse;

import java.util.List;


public class DoctorClinicSpecTypesListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "DoctorClinicSpecTypesListAdapter";
    Context mcontext;
    List<DoctorDetailsResponse.DataBean.SpecializationBean> specializationBeanList;
    DoctorDetailsResponse.DataBean.SpecializationBean currentItem;
    private  int size;


    public DoctorClinicSpecTypesListAdapter(Context context, List<DoctorDetailsResponse.DataBean.SpecializationBean> specializationBeanList, int size) {
        this.mcontext = context;
        this.specializationBeanList = specializationBeanList;
        this.size = size;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sp_speclist, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = specializationBeanList.get(position);

        if(currentItem.getSpecialization()!=null&&!currentItem.getSpecialization().isEmpty()){

            holder.txt_spectypes.setText(currentItem.getSpecialization());
        }


    }
    @Override
    public int getItemCount() {
        //return specializationBeanList.size();
        return Math.min(specializationBeanList.size(), size);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public TextView txt_spectypes;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_spectypes = itemView.findViewById(R.id.txt_specname);


        }

    }

}
