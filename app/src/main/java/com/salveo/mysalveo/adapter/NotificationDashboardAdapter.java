package com.salveo.mysalveo.adapter;


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
import com.salveo.mysalveo.responsepojo.NotificationGetlistResponse;

import java.util.List;


public class NotificationDashboardAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
   
    private  String TAG = "NotificationDashboardAdapter";
    private Context context;
 
    NotificationGetlistResponse.DataBean currentItem;
  private List<NotificationGetlistResponse.DataBean> notificationGetlistResponseList;
   

    private int currentSelectedPosition = RecyclerView.NO_POSITION;



    public NotificationDashboardAdapter(Context context, List<NotificationGetlistResponse.DataBean> notificationGetlistResponseList) {
        this.notificationGetlistResponseList = notificationGetlistResponseList;
        this.context = context;

       

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notifications_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

  private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = notificationGetlistResponseList.get(position);
        if(currentItem.getNotify_title() != null) {
            holder.txt_notification_title.setText(currentItem.getNotify_title());
        }
        if(currentItem.getNotify_descri() != null){
            holder.txt_message.setText(currentItem.getNotify_descri());

        }
        if(currentItem.getNotify_descri() != null) {
            holder.txt_message_details.setText(currentItem.getNotify_descri());
        }
        if(currentItem.getDate_and_time() != null) {
            holder.txt_date.setText(currentItem.getDate_and_time());
        }
        if (currentItem.getNotify_img() != null && !currentItem.getNotify_img().isEmpty()) {

            Glide.with(context)
                    .load(currentItem.getNotify_img())
                    //.load(R.drawable.logo)
                    .into(holder.img_notify_imge);

        }
        else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_notify_imge);

        }
        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSelectedPosition = position;
                notifyDataSetChanged();




            }




        });

      if (currentSelectedPosition == position) {
          holder.txt_message_details.setVisibility(View.VISIBLE);
          holder.txt_message.setVisibility(View.GONE);


      } else {
          holder.txt_message.setVisibility(View.VISIBLE);
          holder.txt_message_details.setVisibility(View.GONE);
      }

   }

   @Override
    public int getItemCount() {
        
        return notificationGetlistResponseList.size();
    }
   

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_message,txt_date,txt_notification_title,txt_message_details;
        public LinearLayout ll_root;
        public ImageView img_notify_imge;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_notification_title = itemView.findViewById(R.id.txt_notification_title);
            txt_message = itemView.findViewById(R.id.txt_message);
            txt_message_details = itemView.findViewById(R.id.txt_message_details);
            txt_date = itemView.findViewById(R.id.txt_date);
            img_notify_imge = itemView.findViewById(R.id.img_notify_imge);
            ll_root = itemView.findViewById(R.id.ll_root);
            txt_message_details.setVisibility(View.GONE);

        }

    }

}
