package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.interfaces.PrescriptionListener;
import com.salveo.mysalveo.responsepojo.MedicalHistoryResponse;

import java.util.List;


public class MedicalHistoryListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "MedicalHistoryListAdapter";
    private final Context context;
    MedicalHistoryResponse.DataBean currentItem;
    List<MedicalHistoryResponse.DataBean> data;
    public static String id = "";
    private int currentSelectedPosition = RecyclerView.NO_POSITION;

    int count = 0;

    PrescriptionListener prescriptionListener;

    public MedicalHistoryListAdapter(Context context,List<MedicalHistoryResponse.DataBean> data, PrescriptionListener prescriptionListener) {
        this.context = context;
        this.data = data;
        this.prescriptionListener = prescriptionListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_medical_history_list_parent_view, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber", "LongLogTag"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = data.get(position);
        if(data.get(position).getVet_name() != null) {
            holder.txt_veterinarian_name.setText(data.get(position).getVet_name());
        }
        if(data.get(position).getPet_name() != null) {
            holder.txt_pet_name.setText(data.get(position).getPet_name());
        } if(data.get(position).getAppointment_date() != null) {
            holder.txt_date.setText(data.get(position).getAppointment_date());
        }
        if(data.get(position).getAllergies() != null){
            holder.txt_allergies.setText(": "+data.get(position).getAllergies());
        } if(data.get(position).isVacination()){
            holder.txt_vaccination.setText(": Yes");
        }else{
            holder.txt_vaccination.setText(": No");
        }
        if(data.get(position).getPrescrip_type() != null){
            holder.txt_prescription.setText(": "+data.get(position).getPrescrip_type());
        }if(data.get(position).getCommunication_type() != null){
            holder.txt_communicationtype.setText(": "+data.get(position).getCommunication_type());
        }
        if (data.get(position).getVet_image() != null && !data.get(position).getVet_image().isEmpty()) {
            Glide.with(context)
                    .load(data.get(position).getVet_image())
                    .into(holder.img_medical_image);
        }
        else{
            Glide.with(context)
                    .load(APIClient.BANNER_IMAGE_URL)
                    .into(holder.img_medical_image);

        }
        holder.img_expand_arrow.setOnClickListener(v -> {
            currentSelectedPosition = position;
            notifyDataSetChanged();



        });
        if (currentSelectedPosition == position) {
            holder.include_medical_history_childview.setVisibility(View.VISIBLE);
            holder.img_expand_arrow.setImageResource(R.drawable.ic_up);
        }
        else {
            holder.include_medical_history_childview.setVisibility(View.GONE);
            holder.img_expand_arrow.setImageResource(R.drawable.ic_down);
        }

        holder.ll_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prescriptionListener.prescriptionListener(data.get(position).getAppointement_id());
            }
        });



    }









    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_veterinarian_name,txt_pet_name,txt_date;
        public ImageView img_medical_image,img_expand_arrow;
        public View include_medical_history_childview;
        public TextView txt_allergies,txt_vaccination,txt_communicationtype,txt_prescription;
        public LinearLayout ll_download;

        public ViewHolderOne(View itemView) {
            super(itemView);
            img_medical_image = itemView.findViewById(R.id.img_medical_image);
            txt_veterinarian_name = itemView.findViewById(R.id.txt_veterinarian_name);
            txt_pet_name = itemView.findViewById(R.id.txt_pet_name);
            img_expand_arrow = itemView.findViewById(R.id.img_expand_arrow);
            txt_date = itemView.findViewById(R.id.txt_date);

            include_medical_history_childview = itemView.findViewById(R.id.include_medical_history_childview);
            include_medical_history_childview.setVisibility(View.GONE);
            txt_allergies = include_medical_history_childview.findViewById(R.id.txt_allergies);
            txt_vaccination = include_medical_history_childview.findViewById(R.id.txt_vaccination);
            txt_communicationtype = include_medical_history_childview.findViewById(R.id.txt_communicationtype);
            txt_prescription = include_medical_history_childview.findViewById(R.id.txt_prescription);
            ll_download = include_medical_history_childview.findViewById(R.id.ll_download);




        }

    }







}
