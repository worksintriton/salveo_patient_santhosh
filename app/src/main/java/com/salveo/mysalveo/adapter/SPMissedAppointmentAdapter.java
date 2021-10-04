package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.responsepojo.SPAppointmentResponse;
import com.salveo.mysalveo.serviceprovider.SPAppointmentDetailsActivity;

import java.util.List;


public class SPMissedAppointmentAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "SPMissedAppointmentAdapter";
    private List<SPAppointmentResponse.DataBean> missedAppointmentResponseList;
    private Context context;
    private int size;

    SPAppointmentResponse.DataBean currentItem;


    public SPMissedAppointmentAdapter(Context context, List<SPAppointmentResponse.DataBean> missedAppointmentResponseList, RecyclerView inbox_list, int size) {
        this.missedAppointmentResponseList = missedAppointmentResponseList;
        this.context = context;
        this.size = size;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor_missed_appointment, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        Log.w(TAG,"Pet name-->"+missedAppointmentResponseList.get(position).getPet_id().getPet_name());

        currentItem = missedAppointmentResponseList.get(position);
        if(missedAppointmentResponseList.get(position).getPet_id().getPet_name() != null) {
            holder.txt_petname.setText(missedAppointmentResponseList.get(position).getPet_id().getPet_name());
        }
        if(missedAppointmentResponseList.get(position).getPet_id().getPet_type() != null) {
            holder.txt_pettype.setText(missedAppointmentResponseList.get(position).getPet_id().getPet_type());
        }
        if(missedAppointmentResponseList.get(position).getMissed_at() != null) {
            holder.txt_missed_date.setText("Missed on:" + " " + missedAppointmentResponseList.get(position).getMissed_at());
        }

        holder.txt_lbl_type.setText("Service Name");
        if(missedAppointmentResponseList.get(position).getService_name() != null){
            holder.txt_type.setText(missedAppointmentResponseList.get(position).getService_name());
        }
        if(missedAppointmentResponseList.get(position).getService_amount() != null){
            holder.txt_service_cost.setText("\u20B9 "+missedAppointmentResponseList.get(position).getService_amount());
        }
        if (missedAppointmentResponseList.get(position).getPet_id().getPet_img() != null && !missedAppointmentResponseList.get(position).getPet_id().getPet_img().isEmpty()) {

            Glide.with(context)
                    .load(missedAppointmentResponseList.get(position).getPet_id().getPet_img())
                    .into(holder.img_pet_imge);

        }
        else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_pet_imge);

        }
        holder.ll_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SPAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("appointment_id",missedAppointmentResponseList.get(position).get_id());
                i.putExtra("fromactivity",TAG);
                context.startActivity(i);

            }
        });



    }


    @Override
    public int getItemCount() {
        return Math.min(missedAppointmentResponseList.size(), size);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_petname,txt_pettype,txt_type,txt_service_cost,txt_missed_date,txt_lbl_type;
        public ImageView img_pet_imge,img_emergency_appointment;
        public Button btn_cancel,btn_complete;
        public LinearLayout ll_new;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            txt_petname = itemView.findViewById(R.id.txt_petname);
            txt_pettype = itemView.findViewById(R.id.txt_pettype);
            txt_lbl_type = itemView.findViewById(R.id.txt_lbl_type);
            txt_type = itemView.findViewById(R.id.txt_type);
            txt_service_cost = itemView.findViewById(R.id.txt_service_cost);
            txt_missed_date = itemView.findViewById(R.id.txt_missed_date);
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            btn_complete = itemView.findViewById(R.id.btn_complete);
            ll_new = itemView.findViewById(R.id.ll_new);

            img_emergency_appointment = itemView.findViewById(R.id.img_emergency_appointment);
            img_emergency_appointment.setVisibility(View.GONE);




        }




    }








}
