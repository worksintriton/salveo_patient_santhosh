package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.interfaces.LocationDefaultListener;
import com.salveo.mysalveo.responsepojo.LocationListAddressResponse;

import java.util.List;


public class ManageAddressListVisitAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "ManageAddressListVisitAdapter";
    private List<LocationListAddressResponse.DataBean> locationListResponseList  = null;
    private Context context;

    LocationListAddressResponse.DataBean currentItem;




    public static String id = "";

    private LocationDefaultListener locationDefaultListener;
    String fromactivity;

    private int lastSelectedPosition = -1;
    private boolean isSelected = true;



    public ManageAddressListVisitAdapter(Context context, List<LocationListAddressResponse.DataBean> locationListResponseList, LocationDefaultListener locationDefaultListener, String fromactivity) {
        this.context = context;
        this.locationListResponseList = locationListResponseList;
        this.locationDefaultListener = locationDefaultListener;
        this.fromactivity = fromactivity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_manage_address_list_visit, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = locationListResponseList.get(position);
        if(locationListResponseList.get(position).getLocation_title() != null) {
            holder.txt_location_title.setText(locationListResponseList.get(position).getLocation_title());
        }
        if(locationListResponseList.get(position).getLocation_nickname() != null) {
            holder.txt_location_nickname.setText(locationListResponseList.get(position).getLocation_nickname());
        }
        if(locationListResponseList.get(position).getLocation_address() != null) {
            holder.txt_address.setText(locationListResponseList.get(position).getLocation_address());
        }






        if(locationListResponseList.get(position).isDefault_status()){
            locationDefaultListener.locationDefaultListener(locationListResponseList.get(position).isDefault_status(),locationListResponseList.get(position).get_id(),locationListResponseList.get(position).getUser_id());

        }

        holder.rb_choose_addr_list.setChecked(lastSelectedPosition == position);

        if(isSelected) {
            if (locationListResponseList.get(position).isDefault_status()) {
                holder.rb_choose_addr_list.setChecked(locationListResponseList.get(position).isDefault_status());
            }
        }

        holder.rb_choose_addr_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected = false;
                lastSelectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
                locationDefaultListener.locationDefaultListener(locationListResponseList.get(position).isDefault_status(),locationListResponseList.get(position).get_id(),locationListResponseList.get(position).getUser_id());
            }
        });









    }









    @Override
    public int getItemCount() {
        return locationListResponseList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_location_title,txt_location_nickname,txt_address;
        public RelativeLayout rl_root;
        RadioButton rb_choose_addr_list;




        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_location_title = itemView.findViewById(R.id.txt_location_title);
            txt_location_nickname = itemView.findViewById(R.id.txt_location_nickname);
            txt_address = itemView.findViewById(R.id.txt_address);

            rl_root = itemView.findViewById(R.id.rl_root);
            rb_choose_addr_list = itemView.findViewById(R.id.rb_choose_addr_list);



        }




    }







}
