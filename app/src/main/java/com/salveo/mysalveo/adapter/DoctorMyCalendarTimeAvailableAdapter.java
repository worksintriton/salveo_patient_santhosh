package com.salveo.mysalveo.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.doctor.DoctorMyCalendarTimeActivity;
import com.salveo.mysalveo.interfaces.OnItemClickSpecialization;
import com.salveo.mysalveo.responsepojo.DoctorMyCalendarAvlTimesResponse;

import java.util.List;


public class DoctorMyCalendarTimeAvailableAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_LOADING = 5;
    private  String TAG = "DoctorMyCalendarTimeAvailableAdapter";
    private List<DoctorMyCalendarAvlTimesResponse.DataBean> dataBeanList = null;
    private Context context;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    DoctorMyCalendarAvlTimesResponse.DataBean currentItem;
    String strMSgdaystime;
    String strMsg;

    private Boolean isPoll = false;
    String formatedDate,formatedStartDate = "";
    Dialog dialog;
    private OnItemClickSpecialization mCallback;



    public DoctorMyCalendarTimeAvailableAdapter(Context context, List<DoctorMyCalendarAvlTimesResponse.DataBean> dataBeanList, RecyclerView inbox_list, DoctorMyCalendarTimeActivity doctorMyCalendarTimeActivity) {
        this.dataBeanList = dataBeanList;
        this.context = context;
        this.mCallback = (OnItemClickSpecialization)doctorMyCalendarTimeActivity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor_mycalendar_available_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = dataBeanList.get(position);
        if(dataBeanList != null && dataBeanList.size()>0) {
            for (int i = 0; i < dataBeanList.size(); i++) {

                if (dataBeanList.get(position).getTime() != null) {
                    holder.txt_days.setText(dataBeanList.get(position).getTime());
                }

            }
        }
        if(dataBeanList.get(position).isStatus()){
            holder.ch_days.setChecked(true);

        }else{
            holder.img_edit.setVisibility(View.GONE);

        }



        holder.ch_days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String chspecialization = dataBeanList.get(position).getTime();

                if(isChecked){
                    if (holder.ch_days.isChecked()) {
                        mCallback.onItemCheckSpecialization(chspecialization);
                    }

                }else{

                    mCallback.onItemUncheckSpecialization(chspecialization);

                }

            }
        });

/*
        holder.llsymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DoctorListActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                Bundle b = new Bundle();
                b.putString("Array",symptomsGetlistResponseList.get(position).getSpecializations().toString());
                i.putExtras(b);
                Log.w(TAG,"Array-->"+b);
                context.startActivity(i);

            }
        });
*/




    }









    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_days;
        public LinearLayout ll_days;
        public CheckBox ch_days;
        public ImageView img_edit;



        public ViewHolderOne(View itemView) {
            super(itemView);

            ch_days = itemView.findViewById(R.id.ch_days);
            txt_days = itemView.findViewById(R.id.txt_days);
            ll_days = itemView.findViewById(R.id.ll_days);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_edit.setVisibility(View.GONE);











        }




    }














}
