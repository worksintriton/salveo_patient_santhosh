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
import com.salveo.mysalveo.doctor.DoctorPrescriptionDetailsActivity;
import com.salveo.mysalveo.responsepojo.DoctorAppointmentsResponse;

import java.util.List;


public class DoctorCompletedAppointmentAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  String TAG = "DoctorCompletedAppointmentAdapter";
    private List<DoctorAppointmentsResponse.DataBean> completedAppointmentResponseList;
    private Context context;
    private int size;

    DoctorAppointmentsResponse.DataBean currentItem;
    private List<DoctorAppointmentsResponse.DataBean.PetIdBean.PetImgBean> petImgBeanList;
    private String petImagePath;


    public DoctorCompletedAppointmentAdapter(Context context, List<DoctorAppointmentsResponse.DataBean> completedAppointmentResponseList, RecyclerView inbox_list, int size) {
        this.completedAppointmentResponseList = completedAppointmentResponseList;
        this.context = context;
        this.size = size;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor_completed_appointment, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        if(completedAppointmentResponseList.get(position).getPet_id() != null){
            if(completedAppointmentResponseList.get(position).getPet_id().getPet_name() != null){
                holder.txt_petname.setText(completedAppointmentResponseList.get(position).getPet_id().getPet_name());}
            if(completedAppointmentResponseList.get(position).getPet_id().getPet_type() != null){
                holder.txt_pettype.setText(completedAppointmentResponseList.get(position).getPet_id().getPet_type());}
            petImgBeanList = completedAppointmentResponseList.get(position).getPet_id().getPet_img();

        }

        if(completedAppointmentResponseList.get(position).getCompleted_at() != null){
        holder.txt_completed_date.setText("Completed on:"+" "+completedAppointmentResponseList.get(position).getCompleted_at());}
        if(completedAppointmentResponseList.get(position).getAppointment_types() != null){
            holder.txt_type.setText(completedAppointmentResponseList.get(position).getAppointment_types());
        }
        if(completedAppointmentResponseList.get(position).getAmount() != null){
            holder.txt_service_cost.setText("\u20B9 "+completedAppointmentResponseList.get(position).getAmount());
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

        if(completedAppointmentResponseList.get(position).getAppointment_types() != null && completedAppointmentResponseList.get(position).getAppointment_types().equalsIgnoreCase("Emergency")){
            holder.img_emergency_appointment.setVisibility(View.VISIBLE);
        }else{
            holder.img_emergency_appointment.setVisibility(View.GONE);

        }

        holder.btn__prescriptiondetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(completedAppointmentResponseList.get(position).get_id() != null) {
                    Intent i = new Intent(context, DoctorPrescriptionDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("id", completedAppointmentResponseList.get(position).get_id());
                    i.putExtra("doctor_id", completedAppointmentResponseList.get(position).getDoctor_id().get_id());
                    i.putExtra("userid", completedAppointmentResponseList.get(position).getUser_id().get_id());
                    context.startActivity(i);
                }

            }
        });


        holder.ll_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DoctorAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("appointment_id",completedAppointmentResponseList.get(position).get_id());
                i.putExtra("bookedat",completedAppointmentResponseList.get(position).getBooking_date_time());
                i.putExtra("from",TAG);
                context.startActivity(i);

            }
        });
    }


    @Override
    public int getItemCount() {
        return Math.min(completedAppointmentResponseList.size(), size);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_petname,txt_pettype,txt_type,txt_service_cost,txt_completed_date;
        public ImageView img_pet_imge,img_emergency_appointment;
        public Button btn_cancel,btn_complete,btn__prescriptiondetails;
        LinearLayout ll_new;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            txt_petname = itemView.findViewById(R.id.txt_petname);
            txt_pettype = itemView.findViewById(R.id.txt_pettype);
            txt_type = itemView.findViewById(R.id.txt_type);
            txt_service_cost = itemView.findViewById(R.id.txt_service_cost);
            txt_completed_date = itemView.findViewById(R.id.txt_completed_date);
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            btn_complete = itemView.findViewById(R.id.btn_complete);
            //img_prescriptiondetails = itemView.findViewById(R.id.img_prescriptiondetails);
            ll_new = itemView.findViewById(R.id.ll_new);
            btn__prescriptiondetails = itemView.findViewById(R.id.btn_prescriptiondetails);
            img_emergency_appointment = itemView.findViewById(R.id.img_emergency_appointment);
            img_emergency_appointment.setVisibility(View.GONE);

        }




    }








}
