package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.salveo.mysalveo.doctor.DoctorAppointmentDetailsActivity;
import com.salveo.mysalveo.responsepojo.DoctorAppointmentsResponse;

import java.util.List;


public class DoctorMissedAppointmentAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "DoctorMissedAppointmentAdapter";
    private List<DoctorAppointmentsResponse.DataBean> missedAppointmentResponseList;
    private Context context;
    private int size;

    DoctorAppointmentsResponse.DataBean currentItem;
    private List<DoctorAppointmentsResponse.DataBean.PetIdBean.PetImgBean> petImgBeanList;
    private String petImagePath;


    public DoctorMissedAppointmentAdapter(Context context, List<DoctorAppointmentsResponse.DataBean> missedAppointmentResponseList, RecyclerView inbox_list,int size) {
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


        currentItem = missedAppointmentResponseList.get(position);

        if(missedAppointmentResponseList.get(position).getPet_id() != null){
            if(missedAppointmentResponseList.get(position).getPet_id().getPet_name() != null){
                holder.txt_petname.setText(missedAppointmentResponseList.get(position).getPet_id().getPet_name());}
            if(missedAppointmentResponseList.get(position).getPet_id().getPet_type() != null) {
                holder.txt_pettype.setText(missedAppointmentResponseList.get(position).getPet_id().getPet_type());
            }
            petImgBeanList = missedAppointmentResponseList.get(position).getPet_id().getPet_img();

        }

        if(missedAppointmentResponseList.get(position).getMissed_at() != null ){
        holder.txt_missed_date.setText("Missed on:"+" "+missedAppointmentResponseList.get(position).getMissed_at());}

        if(missedAppointmentResponseList.get(position).getAppointment_types() != null){
            holder.txt_type.setText(missedAppointmentResponseList.get(position).getAppointment_types());
        }
        if(missedAppointmentResponseList.get(position).getAmount() != null){
            holder.txt_service_cost.setText("\u20B9 "+missedAppointmentResponseList.get(position).getAmount());
        }


        if (petImgBeanList != null && petImgBeanList.size() > 0) {
            for(int j=0;j<petImgBeanList.size();j++) {
                petImagePath = petImgBeanList.get(j).getPet_img();

            }
        }

        if (petImagePath != null && !petImagePath.isEmpty()) {
            Glide.with(context)
                    .load(petImagePath)
                    .into(holder.img_pet_imge);
        } else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_pet_imge);

        }

        if(missedAppointmentResponseList.get(position).getAppointment_types() != null && missedAppointmentResponseList.get(position).getAppointment_types().equalsIgnoreCase("Emergency")){
            holder.img_emergency_appointment.setVisibility(View.VISIBLE);
        }else{
            holder.img_emergency_appointment.setVisibility(View.GONE);

        }


        if(missedAppointmentResponseList.get(position).getAppoint_patient_st() != null && missedAppointmentResponseList.get(position).getAppoint_patient_st().equalsIgnoreCase("Patient Appointment Cancelled")){
            holder.ll_appointmentstatus.setVisibility(View.VISIBLE);
            holder.txt_appointment_status.setText("Not available");
        }
        else if(missedAppointmentResponseList.get(position).getAppoint_patient_st() != null && missedAppointmentResponseList.get(position).getAppoint_patient_st().equalsIgnoreCase("Doctor Cancelled appointment")){
            holder.ll_appointmentstatus.setVisibility(View.VISIBLE);
            holder.txt_appointment_status.setText("Not available");
        }
        else if(missedAppointmentResponseList.get(position).getAppoint_patient_st() != null && missedAppointmentResponseList.get(position).getAppoint_patient_st().equalsIgnoreCase("Doctor missed appointment")){
            holder.ll_appointmentstatus.setVisibility(View.VISIBLE);
            holder.txt_appointment_status.setText("Not available");
        }
        else if(missedAppointmentResponseList.get(position).getAppoint_patient_st() != null && missedAppointmentResponseList.get(position).getAppoint_patient_st().equalsIgnoreCase("Patient Not Available")){
            holder.ll_appointmentstatus.setVisibility(View.VISIBLE);
            holder.txt_appointment_status.setText("No show");
        }

        if(missedAppointmentResponseList.get(position).getAppoinment_status() != null && missedAppointmentResponseList.get(position).getAppoinment_status().equalsIgnoreCase("no show")){
            holder.ll_appointmentstatus.setVisibility(View.VISIBLE);
            holder.txt_appointment_status.setText("No show");
        }

        holder.ll_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DoctorAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("appointment_id",missedAppointmentResponseList.get(position).get_id());
                i.putExtra("bookedat",missedAppointmentResponseList.get(position).getBooking_date_time());
                i.putExtra("from",TAG);
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
        public TextView txt_petname,txt_pettype,txt_type,txt_service_cost,txt_missed_date,txt_appointment_status;
        public ImageView img_pet_imge,img_emergency_appointment;
        public Button btn_cancel,btn_complete;
        public LinearLayout ll_appointmentstatus;
        LinearLayout ll_new;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            txt_petname = itemView.findViewById(R.id.txt_petname);
            txt_pettype = itemView.findViewById(R.id.txt_pettype);
            txt_type = itemView.findViewById(R.id.txt_type);
            txt_service_cost = itemView.findViewById(R.id.txt_service_cost);
            txt_missed_date = itemView.findViewById(R.id.txt_missed_date);
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            btn_complete = itemView.findViewById(R.id.btn_complete);
            txt_appointment_status = itemView.findViewById(R.id.txt_appointment_status);
            ll_appointmentstatus = itemView.findViewById(R.id.ll_appointmentstatus);
            ll_appointmentstatus.setVisibility(View.GONE);
            ll_new = itemView.findViewById(R.id.ll_new);
            img_emergency_appointment = itemView.findViewById(R.id.img_emergency_appointment);
            img_emergency_appointment.setVisibility(View.GONE);


        }




    }








}
