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
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.api.RestApiInterface;
import com.salveo.mysalveo.doctor.DoctorAppointmentDetailsActivity;
import com.salveo.mysalveo.doctor.PrescriptionActivity;
import com.salveo.mysalveo.doctor.VideoCallDoctorActivity;
import com.salveo.mysalveo.interfaces.OnAppointmentCancel;
import com.salveo.mysalveo.interfaces.StartAppointmentListener;
import com.salveo.mysalveo.requestpojo.DoctorStartAppointmentRequest;
import com.salveo.mysalveo.responsepojo.AppointmentsUpdateResponse;
import com.salveo.mysalveo.responsepojo.DoctorAppointmentsResponse;
import com.salveo.mysalveo.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorNewAppointmentAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "DoctorNewAppointmentAdapter";
    private final List<DoctorAppointmentsResponse.DataBean> newAppointmentResponseList;
    private Context context;

    DoctorAppointmentsResponse.DataBean currentItem;

    private OnAppointmentCancel onAppointmentCancel;
    private StartAppointmentListener startAppointmentListener;
    private int size;
    private String communicationtype;
    AVLoadingIndicatorView avi_indicator;
    private boolean isVaildDate;
    private List<DoctorAppointmentsResponse.DataBean.PetIdBean.PetImgBean> petImgBeanList;
    private String petImagePath;


    public DoctorNewAppointmentAdapter(Context context, List<DoctorAppointmentsResponse.DataBean> newAppointmentResponseList, RecyclerView inbox_list, int size, OnAppointmentCancel onAppointmentCancel, AVLoadingIndicatorView avi_indicator) {
        this.newAppointmentResponseList = newAppointmentResponseList;
        this.context = context;
        this.size = size;
        this.onAppointmentCancel = onAppointmentCancel;
        this.avi_indicator = avi_indicator;



    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor_new_appointment, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {


        currentItem = newAppointmentResponseList.get(position);
        if(newAppointmentResponseList.get(position).getCommunication_type() != null ) {
            communicationtype = newAppointmentResponseList.get(position).getCommunication_type();
        }

        if(newAppointmentResponseList.get(position).getPet_id() != null){
            if(newAppointmentResponseList.get(position).getPet_id().getPet_name() != null) {
                holder.txt_petname.setText(newAppointmentResponseList.get(position).getPet_id().getPet_name());
            }
            if(newAppointmentResponseList.get(position).getPet_id().getPet_type() != null) {
                holder.txt_pettype.setText(newAppointmentResponseList.get(position).getPet_id().getPet_type());
            }
            petImgBeanList = newAppointmentResponseList.get(position).getPet_id().getPet_img();
        }


        if(newAppointmentResponseList.get(position).getAppointment_types() != null){
            holder.txt_type.setText(newAppointmentResponseList.get(position).getAppointment_types());
        }
        if(newAppointmentResponseList.get(position).getAmount() != null){
            holder.txt_service_cost.setText("\u20B9 "+newAppointmentResponseList.get(position).getAmount());
        }

        if(newAppointmentResponseList.get(position).getBooking_date_time() != null){
            holder.txt_bookedon.setText("Booked for :"+" "+newAppointmentResponseList.get(position).getBooking_date_time());

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


        
        if(newAppointmentResponseList.get(position).getAppointment_types() != null && newAppointmentResponseList.get(position).getAppointment_types().equalsIgnoreCase("Emergency")){
            holder.img_emergency_appointment.setVisibility(View.VISIBLE);
        }else{
            holder.img_emergency_appointment.setVisibility(View.GONE);

        }

        if(communicationtype != null){
            if(communicationtype.equalsIgnoreCase("Online")){
                holder.img_videocall.setVisibility(View.VISIBLE);
            }else{
                holder.img_videocall.setVisibility(View.GONE);
            }
        }




        holder.btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PrescriptionActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(newAppointmentResponseList.get(position).getPet_id() != null){
                    i.putExtra("petname",newAppointmentResponseList.get(position).getPet_id().getPet_name());
                    i.putExtra("pettype",newAppointmentResponseList.get(position).getPet_id().getPet_type());
                }

                i.putExtra("id",newAppointmentResponseList.get(position).get_id());
                i.putExtra("userid",newAppointmentResponseList.get(position).getUser_id().get_id());
                i.putExtra("allergies",newAppointmentResponseList.get(position).getAllergies());
                i.putExtra("probleminfo",newAppointmentResponseList.get(position).getProblem_info());
                i.putExtra("doctorid",newAppointmentResponseList.get(position).getDoctor_id().get_id());
                context.startActivity(i);

            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        String bookingDateandTime = newAppointmentResponseList.get(position).getBooking_date_time();
        compareDatesandTime(currentDateandTime,bookingDateandTime);

        if(isVaildDate){
            holder.btn_cancel.setVisibility(View.VISIBLE);
        }else{
            holder.btn_cancel.setVisibility(View.GONE);
        }

        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAppointmentCancel.onAppointmentCancel(newAppointmentResponseList.get(position).get_id(),newAppointmentResponseList.get(position).getAppointment_types(),newAppointmentResponseList.get(position).getUser_id().get_id(),newAppointmentResponseList.get(position).getDoctor_id().get_id(),newAppointmentResponseList.get(position).getAppointment_UID(),"");

            }
        });

        holder.img_videocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(TAG,"Start_appointment_status : "+newAppointmentResponseList.get(position).getStart_appointment_status());
                if(newAppointmentResponseList.get(position).getStart_appointment_status() != null && newAppointmentResponseList.get(position).getStart_appointment_status().equalsIgnoreCase("Not Started")){
                    doctorStartAppointmentResponseCall(newAppointmentResponseList.get(position).get_id(),position);
                }else {
                    Intent i = new Intent(context, VideoCallDoctorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("id", newAppointmentResponseList.get(position).get_id());
                    i.putExtra("petname", newAppointmentResponseList.get(position).getPet_id().getPet_name());
                    i.putExtra("pettype", newAppointmentResponseList.get(position).getPet_id().getPet_type());
                    i.putExtra("userid", newAppointmentResponseList.get(position).getUser_id().get_id());
                    Log.w(TAG, " User_ID "+newAppointmentResponseList.get(position).getUser_id().get_id());
                    i.putExtra("allergies", newAppointmentResponseList.get(position).getAllergies());
                    i.putExtra("probleminfo", newAppointmentResponseList.get(position).getProblem_info());
                    Log.w(TAG, "ID-->" + newAppointmentResponseList.get(position).get_id());
                    context.startActivity(i);
                }


            }
        });



            holder.ll_new.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DoctorAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("appointment_id",newAppointmentResponseList.get(position).get_id());
                    i.putExtra("bookedat",newAppointmentResponseList.get(position).getBooking_date_time());
                    i.putExtra("from",TAG);
                    context.startActivity(i);

                }
            });





    }

        /*if(pastAppointmentResponseList.get(position).getCommunication_Chat().equalsIgnoreCase("True")){
            holder.ivmessgaechat.setVisibility(View.VISIBLE);
        }else{
            holder.ivmessgaechat.setVisibility(View.GONE);
        }
*/
    @Override
    public int getItemCount() {
        return Math.min(newAppointmentResponseList.size(), size);

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_petname,txt_pettype,txt_type,txt_service_cost,txt_bookedon;
        public ImageView img_pet_imge,img_emergency_appointment,img_videocall;
        public Button btn_cancel,btn_complete;
        public LinearLayout ll_new;



        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            txt_petname = itemView.findViewById(R.id.txt_petname);
            txt_pettype = itemView.findViewById(R.id.txt_pettype);
            txt_type = itemView.findViewById(R.id.txt_type);
            txt_service_cost = itemView.findViewById(R.id.txt_service_cost);
            txt_bookedon = itemView.findViewById(R.id.txt_bookedon);
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            btn_complete = itemView.findViewById(R.id.btn_complete);
            ll_new = itemView.findViewById(R.id.ll_new);
            img_emergency_appointment = itemView.findViewById(R.id.img_emergency_appointment);
            img_emergency_appointment.setVisibility(View.GONE);
            img_videocall = itemView.findViewById(R.id.img_videocall);
            ll_new = itemView.findViewById(R.id.ll_new);



        }




    }

    private void doctorStartAppointmentResponseCall(String id, int position) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppointmentsUpdateResponse> call = apiInterface.doctorStartAppointmentResponseCall(RestUtils.getContentType(), doctorStartAppointmentRequest(id));
        Log.w(TAG,"startAppointmentResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppointmentsUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Response<AppointmentsUpdateResponse> response) {

                Log.w(TAG,"startAppointmentResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Intent i = new Intent(context, VideoCallDoctorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("id", newAppointmentResponseList.get(position).get_id());
                        i.putExtra("petname", newAppointmentResponseList.get(position).getPet_id().getPet_name());
                        i.putExtra("pettype", newAppointmentResponseList.get(position).getPet_id().getPet_type());
                        i.putExtra("userid", newAppointmentResponseList.get(position).getUser_id().get_id());
                        i.putExtra("allergies", newAppointmentResponseList.get(position).getAllergies());
                        i.putExtra("probleminfo", newAppointmentResponseList.get(position).getProblem_info());
                        Log.w(TAG, "ID-->" + newAppointmentResponseList.get(position).get_id());
                        context.startActivity(i);

                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"startAppointmentResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    private DoctorStartAppointmentRequest doctorStartAppointmentRequest(String id) {
        /*
         * _id : 5fc639ea72fc42044bfa1683
         * appoinment_status : In-Progress
         */
        DoctorStartAppointmentRequest doctorStartAppointmentRequest = new DoctorStartAppointmentRequest();
        doctorStartAppointmentRequest.set_id(id);
        doctorStartAppointmentRequest.setStart_appointment_status("In-Progress");
        Log.w(TAG,"doctorStartAppointmentRequest"+ "--->" + new Gson().toJson(doctorStartAppointmentRequest));
        return doctorStartAppointmentRequest;
    }

    @SuppressLint("LogNotTimber")
    private void compareDatesandTime(String currentDateandTime, String bookingDateandTime) {
        try{

            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");

            String str1 = currentDateandTime;
            Date currentDate = formatter.parse(str1);

            String str2 = bookingDateandTime;
            Date responseDate = formatter.parse(str2);

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
