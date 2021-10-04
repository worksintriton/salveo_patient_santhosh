package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.interfaces.PetDeleteListener;

import com.salveo.mysalveo.responsepojo.PetListResponse;

import java.util.List;


public class ManagePetListMedicalHistoryAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<PetListResponse.DataBean> petListResponseList;

    private final Context context;

    PetListResponse.DataBean currentItem;

    PetDeleteListener petDeleteListener;


    public static String id = "";
    private List<PetListResponse.DataBean.PetImgBean> petImgBeanList;


    public ManagePetListMedicalHistoryAdapter(Context context, List<PetListResponse.DataBean> petListResponseList, PetDeleteListener petDeleteListener) {
        this.petListResponseList = petListResponseList;
        this.context = context;
        this.petDeleteListener = petDeleteListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petlist_medical_history, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = petListResponseList.get(position);
        if (petListResponseList.get(position).getPet_name() != null) {
            holder.txt_pet_name.setText(petListResponseList.get(position).getPet_name());
        }

        if (petListResponseList.size() > 0) {
            String TAG = "ManagePetListAdapter";
            Log.w(TAG,"petListResponseList : "+new Gson().toJson(petListResponseList));


            petImgBeanList =   petListResponseList.get(position).getPet_img();

            String petImagePath = null;


            Log.w(TAG,"petImgBeanList : "+new Gson().toJson(petImgBeanList));

            if (petImgBeanList != null && petImgBeanList.size() > 0) {

                for(int j=0;j<petImgBeanList.size();j++) {
                    petImagePath = petImgBeanList.get(j).getPet_img();

                }
            }




            Log.w(TAG,"petImagePath : "+petImagePath);

            if (petImagePath != null && !petImagePath.isEmpty()) {
                Glide.with(context)
                        .load(petImagePath)
                        .into(holder.img_pet_imge);
            }else {
                Glide.with(context)
                        .load(APIClient.PROFILE_IMAGE_URL)
                        .into(holder.img_pet_imge);

            }


        }

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petDeleteListener.petDeleteListener(petListResponseList.get(position).isDefault_status(),petListResponseList.get(position).get_id());
            }
        });








    }









    @Override
    public int getItemCount() {
        return petListResponseList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_pet_name;
        public ImageView img_pet_imge;
        public LinearLayout ll_root;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            txt_pet_name = itemView.findViewById(R.id.txt_pet_name);
            ll_root = itemView.findViewById(R.id.ll_root);



        }




    }







}
