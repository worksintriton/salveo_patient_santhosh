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
import com.salveo.mysalveo.interfaces.DoctorPetHandledTypeCheckedListener;
import com.salveo.mysalveo.responsepojo.DoctorDetailsByUserIdResponse;
import com.salveo.mysalveo.responsepojo.DropDownListResponse;

import java.util.List;


public class PetsHandledListEditAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "PetsHandledListEditAdapter";
    private Context context;
    private List<DropDownListResponse.DataBean.PetHandleBean> petHandleBeanList;
    List<DoctorDetailsByUserIdResponse.DataBean.PetHandledBean> pethandleListEdit;
    DropDownListResponse.DataBean.PetHandleBean currentItem;
    private DoctorPetHandledTypeCheckedListener doctorPetHandledTypeCheckedListener;


    public PetsHandledListEditAdapter(Context context, List<DropDownListResponse.DataBean.PetHandleBean> pettypedataBeanList, List<DoctorDetailsByUserIdResponse.DataBean.PetHandledBean> pethandleListEdit,DoctorPetHandledTypeCheckedListener doctorPetHandledTypeCheckedListener) {
        this.petHandleBeanList = pettypedataBeanList;
        this.pethandleListEdit = pethandleListEdit;
        this.context = context;
        this.doctorPetHandledTypeCheckedListener = doctorPetHandledTypeCheckedListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pets_type_list, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("LongLogTag")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = petHandleBeanList.get(position);
        holder.txt_spectypes.setText(currentItem.getPet_handle());


        for(int i=0;i<pethandleListEdit.size();i++){
            if(null!=pethandleListEdit && null!=currentItem.getPet_handle() && pethandleListEdit.get(i).getPet_handled().equalsIgnoreCase(currentItem.getPet_handle().trim())){
                holder.chx_spectypes.setChecked(true);
                Log.w(TAG,"petedit");

            }

        }


        holder.chx_spectypes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String chspecialization = petHandleBeanList.get(position).getPet_handle();

                if(isChecked){
                    if (holder.chx_spectypes.isChecked()) {
                        doctorPetHandledTypeCheckedListener.onItemPetCheck(position,chspecialization);
                    }

                }else{

                    doctorPetHandledTypeCheckedListener.onItemPetUnCheck(position,chspecialization);

                }

            }
        });



    }
    @Override
    public int getItemCount() {
        return petHandleBeanList.size();
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

            txt_spectypes = itemView.findViewById(R.id.pet_name);

            chx_spectypes = itemView.findViewById(R.id.checkbox_pet_type);


        }

    }

}
