package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.interfaces.MyPetsSelectListener;

import com.salveo.mysalveo.responsepojo.PetListResponse;

import java.util.List;


public class MyPetsListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<PetListResponse.DataBean> petListResponseList;

    private final Context context;

    PetListResponse.DataBean currentItem;

    MyPetsSelectListener myPetsSelectListener;


    public static String id = "";
    private List<PetListResponse.DataBean.PetImgBean> petImgBeanList;
    private String TAG = "MyPetsListAdapter";


    public MyPetsListAdapter(Context context, List<PetListResponse.DataBean> petListResponseList,  MyPetsSelectListener myPetsSelectListener) {
        this.petListResponseList = petListResponseList;
        this.context = context;
        this.myPetsSelectListener = myPetsSelectListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_pets_list, parent, false);
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

        if(petListResponseList.size() > 0) {
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
        holder.ll_pet_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<petListResponseList.size();i++){
                    petListResponseList.get(i).setSelected(false);

                }
                petListResponseList.get(position).setSelected(true);
                notifyDataSetChanged();
                if(petListResponseList.get(position).get_id() != null ){
                    myPetsSelectListener.myPetsSelectListener(petListResponseList.get(position).get_id(), petListResponseList.get(position).getPet_name());

                }
            }
        });
        if (petListResponseList.get(position).isSelected()) {
            Log.w(TAG, "IF isSelected--->");
            holder.ll_pet_profile.setBackgroundResource(R.drawable.user_type_bgm);
            holder.chx_usertypes.setVisibility(View.VISIBLE);
            holder.chx_usertypes.setChecked(true);
            return;

        }
        else {
            Log.w(TAG, "ELSE isSelected--->");
            holder.ll_pet_profile.setBackgroundResource(R.drawable.user_bgm_trans);
            holder.chx_usertypes.setVisibility(View.INVISIBLE);
            holder.chx_usertypes.setChecked(false);

        }



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
        public LinearLayout ll_pet_profile;
        public CheckBox chx_usertypes;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            txt_pet_name = itemView.findViewById(R.id.txt_pet_name);
            ll_pet_profile = itemView.findViewById(R.id.ll_pet_profile);
            chx_usertypes = itemView.findViewById(R.id.chx_usertypes);
            chx_usertypes.setClickable(false);


        }




    }







}
