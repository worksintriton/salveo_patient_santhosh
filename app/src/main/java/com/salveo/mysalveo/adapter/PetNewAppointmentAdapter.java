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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.interfaces.OnAppointmentCancel;
import com.salveo.mysalveo.petlover.PetAppointmentDetailsActivity;
import com.salveo.mysalveo.petlover.VideoCallPetLoverActivity;
import com.salveo.mysalveo.responsepojo.PetAppointmentResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;


public class PetNewAppointmentAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetNewAppointmentAdapter";
    private List<PetAppointmentResponse.DataBean> newAppointmentResponseList;
    private Context context;

    PetAppointmentResponse.DataBean currentItem;

    private OnAppointmentCancel onAppointmentCancel;

    private int size;
    private String communicationtype;
    private boolean isVaildDate;

    public PetNewAppointmentAdapter(Context context, List<PetAppointmentResponse.DataBean> newAppointmentResponseList, RecyclerView inbox_list,int size,OnAppointmentCancel onAppointmentCancel) {
        this.newAppointmentResponseList = newAppointmentResponseList;
        this.context = context;
        this.size = size;
        this.onAppointmentCancel = onAppointmentCancel;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pet_new_appointment, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        Log.w(TAG,"Pet name-->"+newAppointmentResponseList.get(position).getPet_name());

        currentItem = newAppointmentResponseList.get(position);
        communicationtype = newAppointmentResponseList.get(position).getCommunication_type();
       Log.w(TAG,"Communicationtype : "+ newAppointmentResponseList.get(position).getCommunication_type());
       if(newAppointmentResponseList.get(position).getPet_name() != null) {
           holder.txt_petname.setText(newAppointmentResponseList.get(position).getPet_name());
       }
        if(communicationtype != null){
            if(communicationtype.equalsIgnoreCase("Online")){
                holder.img_videocall.setVisibility(View.VISIBLE);
            }else{
                holder.img_videocall.setVisibility(View.GONE);
            }
        }

        if(newAppointmentResponseList.get(position).getAppointment_for() != null && newAppointmentResponseList.get(position).getAppointment_for().equalsIgnoreCase("Doctor") ){
            holder.txt_type.setText(newAppointmentResponseList.get(position).getAppointment_for());
            if(newAppointmentResponseList.get(position).getClinic_name() != null) {
                holder.txt_clinicname.setText(newAppointmentResponseList.get(position).getClinic_name());
            }
            holder.txt_lbl_doctorname.setText("Doctor Name");
            if(newAppointmentResponseList.get(position).getDoctor_name() != null) {
                holder.txt_doctorname.setText(newAppointmentResponseList.get(position).getDoctor_name());
            }
            if(newAppointmentResponseList.get(position).getStart_appointment_status() != null && !newAppointmentResponseList.get(position).getStart_appointment_status().equalsIgnoreCase("Not Started")) {
                holder.btn_cancel.setVisibility(View.GONE);
            }else{
                holder.btn_cancel.setVisibility(View.VISIBLE);
            }

        }
        else if(newAppointmentResponseList.get(position).getAppointment_for() != null && newAppointmentResponseList.get(position).getAppointment_for().equalsIgnoreCase("SP") ){
            holder.txt_type.setText(newAppointmentResponseList.get(position).getAppointment_for());
            if(newAppointmentResponseList.get(position).getService_provider_name() != null) {
                holder.txt_clinicname.setText(newAppointmentResponseList.get(position).getService_provider_name());
            }
            holder.img_videocall.setVisibility(View.GONE);
            holder.img_emergency_appointment.setVisibility(View.GONE);
            holder.txt_lbl_doctorname.setText("Service Name");
            if(newAppointmentResponseList.get(position).getService_name() != null) {
                holder.txt_doctorname.setText(newAppointmentResponseList.get(position).getService_name());
            }



        }

            if(newAppointmentResponseList.get(position).getCost() != null){
            holder.txt_service_cost.setText("\u20B9 "+newAppointmentResponseList.get(position).getCost());
            }

        if(newAppointmentResponseList.get(position).getBooked_at() != null){
            holder.txt_bookedon.setText("Booked for :"+" "+newAppointmentResponseList.get(position).getBooked_at());

        }




        if(newAppointmentResponseList.get(position).getAppointment_type() != null && newAppointmentResponseList.get(position).getAppointment_type().equalsIgnoreCase("Emergency")){
            holder.img_emergency_appointment.setVisibility(View.VISIBLE);
        }else{
            holder.img_emergency_appointment.setVisibility(View.GONE);

        }

        if (newAppointmentResponseList.get(position).getPhoto() != null && !newAppointmentResponseList.get(position).getPhoto().isEmpty()) {

                Glide.with(context)
                        .load(newAppointmentResponseList.get(position).getPhoto())
                        .into(holder.img_clinic_imge);

            }
        else{
                Glide.with(context)
                        .load(APIClient.PROFILE_IMAGE_URL)
                        .into(holder.img_clinic_imge);

            }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        String bookingDateandTime = newAppointmentResponseList.get(position).getBooked_at();
        compareDatesandTime(currentDateandTime,bookingDateandTime);

        if(isVaildDate){
            holder.btn_cancel.setVisibility(View.VISIBLE);
        }else{
            holder.btn_cancel.setVisibility(View.GONE);
        }




            holder.btn_cancel.setOnClickListener(v -> {
                onAppointmentCancel.onAppointmentCancel(newAppointmentResponseList.get(position).get_id(), newAppointmentResponseList.get(position).getAppointment_for(), newAppointmentResponseList.get(position).getUser_id(), newAppointmentResponseList.get(position).getDoctor_id(), newAppointmentResponseList.get(position).getBooking_Id(), newAppointmentResponseList.get(position).getSp_id());
            });

        if(newAppointmentResponseList.get(position).getStart_appointment_status() != null && newAppointmentResponseList.get(position).getStart_appointment_status().equalsIgnoreCase("Not Started")){
            holder.img_videocall.setBackgroundResource(R.drawable.video_camera_gray);
        }else{
            holder.img_videocall.setBackgroundResource(R.drawable.video_camera_green);
        }


            holder.img_videocall.setOnClickListener(v -> {
                Log.w(TAG,"Start_appointment_status : "+newAppointmentResponseList.get(position).getStart_appointment_status());
                if(newAppointmentResponseList.get(position).getStart_appointment_status() != null && newAppointmentResponseList.get(position).getStart_appointment_status().equalsIgnoreCase("Not Started")){
                    Toasty.warning(context,"Doctor is yet to start the Appointment. Please wait for the doctor to initiate the Appointment", Toast.LENGTH_SHORT, true).show();
                }else {
                    Intent i = new Intent(context, VideoCallPetLoverActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("id", newAppointmentResponseList.get(position).get_id());
                    Log.w(TAG, "ID-->" + newAppointmentResponseList.get(position).get_id());
                    context.startActivity(i);
                }


            });



        holder.ll_new.setOnClickListener(v -> {

            Intent i = new Intent(context, PetAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("appointment_id",newAppointmentResponseList.get(position).get_id());
            i.putExtra("bookedat",newAppointmentResponseList.get(position).getBooked_at());
            i.putExtra("startappointmentstatus",newAppointmentResponseList.get(position).getStart_appointment_status());
            i.putExtra("appointmentfor",newAppointmentResponseList.get(position).getAppointment_for());
            i.putExtra("from",TAG);
            context.startActivity(i);

            /*if(newAppointmentResponseList.get(position).getAppointment_for() != null && newAppointmentResponseList.get(position).getAppointment_for().equalsIgnoreCase("Doctor") ) {
                Intent i = new Intent(context, PetNewAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("appointment_id",newAppointmentResponseList.get(position).get_id());
                i.putExtra("bookedat",newAppointmentResponseList.get(position).getBooked_at());
                i.putExtra("startappointmentstatus",newAppointmentResponseList.get(position).getStart_appointment_status());
                context.startActivity(i);
            }else if(newAppointmentResponseList.get(position).getAppointment_for() != null && newAppointmentResponseList.get(position).getAppointment_for().equalsIgnoreCase("SP") ) {
                Intent i = new Intent(context, PetSPNewAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("appointment_id",newAppointmentResponseList.get(position).get_id());
                i.putExtra("bookedat",newAppointmentResponseList.get(position).getBooked_at());
                i.putExtra("fromactivity",TAG);
                context.startActivity(i);
            }*/
        });


    }

    @Override
    public int getItemCount() {
        return Math.min(newAppointmentResponseList.size(), size);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_clinicname,txt_petname,txt_type,txt_service_cost,txt_bookedon,txt_lbl_doctorname,txt_doctorname;
        public ImageView img_clinic_imge,img_emergency_appointment,img_videocall;
        public Button btn_cancel;
        LinearLayout ll_new;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_clinic_imge = itemView.findViewById(R.id.img_clinic_imge);
            txt_clinicname = itemView.findViewById(R.id.txt_clinicname);
            txt_petname = itemView.findViewById(R.id.txt_petname);
            txt_type = itemView.findViewById(R.id.txt_type);
            txt_bookedon = itemView.findViewById(R.id.txt_bookedon);
            txt_service_cost = itemView.findViewById(R.id.txt_service_cost);
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            img_emergency_appointment = itemView.findViewById(R.id.img_emergency_appointment);
            img_emergency_appointment.setVisibility(View.GONE);
            img_videocall = itemView.findViewById(R.id.img_videocall);
            ll_new = itemView.findViewById(R.id.ll_new);
            txt_lbl_doctorname = itemView.findViewById(R.id.txt_lbl_doctorname);
            txt_doctorname = itemView.findViewById(R.id.txt_doctorname);


        }




    }

    @SuppressLint("LogNotTimber")
    private void compareDatesandTime(String currentDateandTime, String bookingDateandTime) {
        try{

            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");

            Date currentDate = formatter.parse(currentDateandTime);

            Date responseDate = formatter.parse(bookingDateandTime);

            Log.w(TAG,"compareDatesandTime--->"+"responseDate :"+responseDate+" "+"currentDate :"+currentDate);

            if (currentDate.compareTo(responseDate)<0 || responseDate.compareTo(currentDate) == 0)
            {
                Log.w(TAG,"date is equal");
                isVaildDate = true;

            }else{
                Log.w(TAG,"date is not equal");
                isVaildDate = false;
            }



        }catch (ParseException e1){
            e1.printStackTrace();
        }
    }








}
