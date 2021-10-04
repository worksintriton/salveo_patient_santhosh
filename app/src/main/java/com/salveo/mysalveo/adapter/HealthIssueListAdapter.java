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
import com.salveo.mysalveo.interfaces.MyPetsSelectListener;
import com.salveo.mysalveo.responsepojo.HealthIssuesListResponse;

import java.util.List;


public class HealthIssueListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HealthIssuesListResponse.DataBean> healthissueList;

    private final Context context;

    HealthIssuesListResponse.DataBean currentItem;

    MyPetsSelectListener myPetsSelectListener;


    public static String id = "";
    private List<HealthIssuesListResponse.DataBean> petImgBeanList;
    private String TAG = "HealthIssueListAdapter";


    public HealthIssueListAdapter(Context context, List<HealthIssuesListResponse.DataBean> healthissueList, MyPetsSelectListener myPetsSelectListener) {
        this.context = context;
        this.healthissueList = healthissueList;
        this.myPetsSelectListener = myPetsSelectListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_health_issue_list, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = healthissueList.get(position);
        if (healthissueList.get(position).getHealth_issue_title() != null) {
            holder.txt_health_issue_name.setText(healthissueList.get(position).getHealth_issue_title());
        }


        Log.w(TAG,"ImagePAth : "+healthissueList.get(position).getHealth_issue_img());

        if (healthissueList.get(position).getHealth_issue_img() != null && !healthissueList.get(position).getHealth_issue_img().isEmpty()) {
                Glide.with(context)
                        .load(healthissueList.get(position).getHealth_issue_img())
                        .into(holder.img_health_issue);
            }
        else {
                Glide.with(context)
                        .load(APIClient.PROFILE_IMAGE_URL)
                        .into(holder.img_health_issue);

            }

        holder.ll_pet_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<healthissueList.size();i++){
                    healthissueList.get(i).setSelected(false);

                }
                healthissueList.get(position).setSelected(true);
                notifyDataSetChanged();
                if(healthissueList.get(position).getHealth_issue_title() != null ){
                    myPetsSelectListener.myPetsSelectListener(healthissueList.get(position).getHealth_issue_title(),"");

                }
            }
        });





        if(healthissueList.get(position).isSelected()) {
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
        return healthissueList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_health_issue_name;
        public ImageView img_health_issue;
        public LinearLayout ll_pet_profile;
        public CheckBox chx_usertypes;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_health_issue = itemView.findViewById(R.id.img_health_issue);
            txt_health_issue_name = itemView.findViewById(R.id.txt_health_issue_name);
            ll_pet_profile = itemView.findViewById(R.id.ll_pet_profile);
            chx_usertypes = itemView.findViewById(R.id.chx_usertypes);
            chx_usertypes.setClickable(false);


        }




    }







}
