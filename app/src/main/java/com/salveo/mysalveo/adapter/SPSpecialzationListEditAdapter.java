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

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.interfaces.SPSpecialzationChckedListener;
import com.salveo.mysalveo.responsepojo.SPServiceListResponse;
import com.salveo.mysalveo.responsepojo.ServiceProviderRegisterFormCreateResponse;

import java.util.List;


public class SPSpecialzationListEditAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "SPSpecialzationListEditAdapter";
    private Context mcontext;
    private List<SPServiceListResponse.DataBean.SpecializationBean> spSpecialzationList;
    SPServiceListResponse.DataBean.SpecializationBean currentItem;
    private SPSpecialzationChckedListener spSpecialzationChckedListener;
    private List<ServiceProviderRegisterFormCreateResponse.DataBean.BusSpecListBean> spSpecialzationListEdit;


    public SPSpecialzationListEditAdapter(Context context, List<SPServiceListResponse.DataBean.SpecializationBean> spSpecialzationList,List<ServiceProviderRegisterFormCreateResponse.DataBean.BusSpecListBean> spSpecialzationListEdit, SPSpecialzationChckedListener spSpecialzationChckedListener) {
        this.spSpecialzationList = spSpecialzationList;
        this.spSpecialzationListEdit = spSpecialzationListEdit;
        this.mcontext = context;
        this.spSpecialzationChckedListener = spSpecialzationChckedListener;
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

    @SuppressLint("LogNotTimber")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = spSpecialzationList.get(position);
        if(currentItem.getSpecialization() != null){
            holder.txt_spectypes.setText(currentItem.getSpecialization());

        }

        if(spSpecialzationListEdit != null && spSpecialzationListEdit.size()>0) {

            for (int i = 0; i < spSpecialzationListEdit.size(); i++) {
                if (null != currentItem.getSpecialization() && spSpecialzationListEdit.get(i).getBus_spec_list() != null && spSpecialzationListEdit.get(i).getBus_spec_list().equalsIgnoreCase(currentItem.getSpecialization().trim())) {
                    holder.chx_spectypes.setChecked(true);
                    Log.w(TAG, "ServiceEdit");

                }

            }
        }


        holder.chx_spectypes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String chspecialization = spSpecialzationList.get(position).getSpecialization();

                if(isChecked){
                    if (holder.chx_spectypes.isChecked()) {
                        spSpecialzationChckedListener.onItemSPSpecialzationCheck(position,chspecialization);
                    }

                }else{

                    spSpecialzationChckedListener.onItemSPSpecialzationUnCheck(position,chspecialization);

                }

            }
        });


    }
    @Override
    public int getItemCount() {
        return spSpecialzationList.size();
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
