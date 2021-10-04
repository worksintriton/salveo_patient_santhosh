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


public class DoctorClinicPetsHandledListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "DoctorClinicPetsHandledListAdapter";
    private Context context;
    private List<DoctorDetailsResponse.DataBean.PetHandledBean> petHandledBeanList;
    DoctorDetailsResponse.DataBean.PetHandledBean currentItem;
    private int size;


    public DoctorClinicPetsHandledListAdapter(Context context, List<DoctorDetailsResponse.DataBean.PetHandledBean> pettypedataBeanList,int size) {
        this.context = context;
        this.petHandledBeanList = pettypedataBeanList;
        this.size = size;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sp_petlist, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sp_speclist, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = petHandledBeanList.get(position);

        if(currentItem.getPet_handled()!=null&&!currentItem.getPet_handled().isEmpty()){

            holder.txt_petname.setText(currentItem.getPet_handled());
        }





    }
    @Override
    public int getItemCount()
    {
        //return petHandledBeanList.size();
        return Math.min(petHandledBeanList.size(), size);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public TextView txt_petname;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_petname = itemView.findViewById(R.id.txt_specname);



        }

    }

}
