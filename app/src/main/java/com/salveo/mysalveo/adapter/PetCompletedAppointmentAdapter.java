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
import com.salveo.mysalveo.doctor.DoctorPrescriptionDetailsActivity;
import com.salveo.mysalveo.interfaces.AddReviewListener;
import com.salveo.mysalveo.petlover.PetAppointmentDetailsActivity;
import com.salveo.mysalveo.responsepojo.PetAppointmentResponse;

import java.util.List;


public class PetCompletedAppointmentAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "PetCompletedAppointmentAdapter";
    private final List<PetAppointmentResponse.DataBean> completedAppointmentResponseList;
    private final Context context;

    int size;
    private final AddReviewListener addReviewListener;


    public PetCompletedAppointmentAdapter(Context context, List<PetAppointmentResponse.DataBean> completedAppointmentResponseList, int size, AddReviewListener addReviewListener) {
        this.completedAppointmentResponseList = completedAppointmentResponseList;
        this.context = context;
        this.size = size;
        this.addReviewListener = addReviewListener;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petcompleted_appointment, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber", "LongLogTag"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        Log.w(TAG,"Pet name-->"+completedAppointmentResponseList.get(position).getPet_name());
        if(completedAppointmentResponseList.get(position).getPet_name() != null){
            holder.txt_pettype.setText(completedAppointmentResponseList.get(position).getPet_name());

        }
        if(completedAppointmentResponseList.get(position).getCompleted_at() != null) {
            holder.txt_completed_date.setText("Completed on:" + " " + completedAppointmentResponseList.get(position).getCompleted_at());

        }

        if(completedAppointmentResponseList.get(position).getAppointment_for() != null && completedAppointmentResponseList.get(position).getAppointment_for().equalsIgnoreCase("Doctor") ){
            holder.txt_type.setText(completedAppointmentResponseList.get(position).getAppointment_for());
            if(completedAppointmentResponseList.get(position).getClinic_name() != null) {
                holder.txt_petname.setText(completedAppointmentResponseList.get(position).getClinic_name());
            }
            holder.txt_lbl_doctorname.setText("Doctor Name");
            if(completedAppointmentResponseList.get(position).getDoctor_name() != null) {
                holder.txt_doctorname.setText(completedAppointmentResponseList.get(position).getDoctor_name());
            }


        }else if(completedAppointmentResponseList.get(position).getAppointment_for() != null && completedAppointmentResponseList.get(position).getAppointment_for().equalsIgnoreCase("SP") ){
            holder.txt_type.setText(completedAppointmentResponseList.get(position).getAppointment_for());
            if(completedAppointmentResponseList.get(position).getService_provider_name() != null) {
                holder.txt_petname.setText(completedAppointmentResponseList.get(position).getService_provider_name());
            }
            holder.txt_lbl_doctorname.setText("Service Name");
            if(completedAppointmentResponseList.get(position).getService_name() != null) {
                holder.txt_doctorname.setText(completedAppointmentResponseList.get(position).getService_name());
            }
            holder.btn__prescriptiondetails.setVisibility(View.GONE);


        }
        if(completedAppointmentResponseList.get(position).getCost() != null){
            holder.txt_service_cost.setText("\u20B9 "+completedAppointmentResponseList.get(position).getCost());
        }


        Log.w(TAG,"userrate: "+completedAppointmentResponseList.get(position).getUser_rate());

        if(completedAppointmentResponseList.get(position).getUser_rate() != null && completedAppointmentResponseList.get(position).getUser_rate().equalsIgnoreCase("0")){
            holder.btn_add_review.setVisibility(View.VISIBLE);
        }else{
            holder.btn_add_review.setVisibility(View.GONE);

        }

        holder.btn_add_review.setOnClickListener(v -> addReviewListener.addReviewListener(completedAppointmentResponseList.get(position).get_id(),completedAppointmentResponseList.get(position).getUser_rate(),completedAppointmentResponseList.get(position).getUser_feedback(),completedAppointmentResponseList.get(position).getAppointment_for()));

        holder.btn__prescriptiondetails.setOnClickListener(v -> {
            if(completedAppointmentResponseList.get(position).get_id() != null) {
                Intent i = new Intent(context, DoctorPrescriptionDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id", completedAppointmentResponseList.get(position).get_id());
                context.startActivity(i);
            }

        });

        if (completedAppointmentResponseList.get(position).getPhoto() != null && !completedAppointmentResponseList.get(position).getPhoto().isEmpty()) {

            Glide.with(context)
                    .load(completedAppointmentResponseList.get(position).getPhoto())
                    .into(holder.img_pet_imge);

        }
        else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_pet_imge);

        }

        if(completedAppointmentResponseList.get(position).getAppointment_type() != null && completedAppointmentResponseList.get(position).getAppointment_type().equalsIgnoreCase("Emergency")){
            holder.img_emergency_appointment.setVisibility(View.VISIBLE);
        }else{
            holder.img_emergency_appointment.setVisibility(View.GONE);

        }




        holder.ll_new.setOnClickListener(v -> {

            Intent i = new Intent(context, PetAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("appointment_id",completedAppointmentResponseList.get(position).get_id());
            i.putExtra("bookedat",completedAppointmentResponseList.get(position).getBooked_at());
            i.putExtra("startappointmentstatus",completedAppointmentResponseList.get(position).getStart_appointment_status());
            i.putExtra("appointmentfor",completedAppointmentResponseList.get(position).getAppointment_for());
            i.putExtra("userrate",completedAppointmentResponseList.get(position).getUser_rate());
            i.putExtra("userfeedback", completedAppointmentResponseList.get(position).getUser_feedback());
            i.putExtra("from",TAG);
            context.startActivity(i);

           /* if(completedAppointmentResponseList.get(position).getAppointment_for() != null && completedAppointmentResponseList.get(position).getAppointment_for().equalsIgnoreCase("Doctor") ) {
                Intent i = new Intent(context, PetCompletedAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("appointment_id",completedAppointmentResponseList.get(position).get_id());
                context.startActivity(i);
            }else if(completedAppointmentResponseList.get(position).getAppointment_for() != null && completedAppointmentResponseList.get(position).getAppointment_for().equalsIgnoreCase("SP") ) {
                Intent i = new Intent(context, PetSPNewAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("appointment_id",completedAppointmentResponseList.get(position).get_id());
                i.putExtra("fromactivity",TAG);
                context.startActivity(i);
            }*/
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
        public TextView txt_petname,txt_pettype,txt_type,txt_completed_date,txt_service_cost,txt_lbl_doctorname,txt_doctorname;
        public ImageView img_pet_imge,img_emergency_appointment;
        public Button btn_cancel,btn_complete,btn_add_review,btn__prescriptiondetails;
        LinearLayout ll_new;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            txt_petname = itemView.findViewById(R.id.txt_petname);
            txt_pettype = itemView.findViewById(R.id.txt_pettype);
            txt_type = itemView.findViewById(R.id.txt_type);
            txt_completed_date = itemView.findViewById(R.id.txt_completed_date);
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            btn_complete = itemView.findViewById(R.id.btn_complete);
            txt_service_cost = itemView.findViewById(R.id.txt_service_cost);
            btn_add_review = itemView.findViewById(R.id.btn_add_review);
            ll_new = itemView.findViewById(R.id.ll_new);
            btn__prescriptiondetails = itemView.findViewById(R.id.btn_prescriptiondetails);
            txt_lbl_doctorname = itemView.findViewById(R.id.txt_lbl_doctorname);
            txt_doctorname = itemView.findViewById(R.id.txt_doctorname);
            img_emergency_appointment = itemView.findViewById(R.id.img_emergency_appointment);
            img_emergency_appointment.setVisibility(View.GONE);



        }




    }








}
