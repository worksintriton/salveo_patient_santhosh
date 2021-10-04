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
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.interfaces.PetTypeSelectListener;
import com.salveo.mysalveo.responsepojo.PetTypeListResponse;

import java.util.List;


public class PetTypesListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetTypesListAdapter";
    private Context context;
    private PetTypeSelectListener petTypeSelectListener;
    PetTypeListResponse.DataBean.UsertypedataBean currentItem;
    List<PetTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList;




    public PetTypesListAdapter(Context context, List<PetTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList, PetTypeSelectListener petTypeSelectListener) {
        this.context = context;
        this.usertypedataBeanList = usertypedataBeanList;
        this.petTypeSelectListener = petTypeSelectListener;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pettypes_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("LogNotTimber")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = usertypedataBeanList.get(position);

        if(currentItem.getPet_type_title() != null){
            holder.txt_usertypes.setText(currentItem.getPet_type_title());

        }
        if (currentItem.getPet_type_img() != null && !currentItem.getPet_type_img().isEmpty()) {
            Glide.with(context)
                        .load(currentItem.getPet_type_img())
                        .into(holder.img_userimages);

            }
        else{
                Glide.with(context)
                        .load(APIClient.PROFILE_IMAGE_URL)
                        .into(holder.img_userimages);

            }

        holder.ll_usertypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<usertypedataBeanList.size();i++){
                    usertypedataBeanList.get(i).setSelected(false);

                }
                usertypedataBeanList.get(position).setSelected(true);
                notifyDataSetChanged();
                if(usertypedataBeanList.get(position).getPet_type_title() != null && usertypedataBeanList.get(position).get_id() != null){
                    petTypeSelectListener.userTypeSelectListener(usertypedataBeanList.get(position).getPet_type_title(),usertypedataBeanList.get(position).get_id());

                }
            }
        });



        if (usertypedataBeanList.get(position).isSelected()) {
            Log.w(TAG, "IF isSelected--->");
            holder.ll_usertypes.setBackgroundResource(R.drawable.user_type_bgm);
            holder.chx_usertypes.setVisibility(View.VISIBLE);
            holder.chx_usertypes.setChecked(true);
            return;

        } else {
            Log.w(TAG, "ELSE isSelected--->");
            holder.ll_usertypes.setBackgroundResource(R.drawable.user_bgm_trans);
            holder.chx_usertypes.setVisibility(View.INVISIBLE);
            holder.chx_usertypes.setChecked(false);

        }



    }
    @Override
    public int getItemCount() {
        return usertypedataBeanList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_usertypes;
        public ImageView img_userimages;
        public LinearLayout ll_usertypes;
        public CheckBox chx_usertypes;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_usertypes = itemView.findViewById(R.id.txt_usertypes);
            img_userimages = itemView.findViewById(R.id.img_userimages);
            ll_usertypes = itemView.findViewById(R.id.ll_usertypes);
            chx_usertypes = itemView.findViewById(R.id.chx_usertypes);
            chx_usertypes.setClickable(false);
            chx_usertypes.setVisibility(View.INVISIBLE);










        }




    }







}
