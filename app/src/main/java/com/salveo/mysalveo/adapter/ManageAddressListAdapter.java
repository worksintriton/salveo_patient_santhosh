package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;

import android.content.Context;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.location.EditMyAddressActivity;
import com.salveo.mysalveo.activity.location.EditMyAddressDoctorActivity;
import com.salveo.mysalveo.interfaces.LocationDefaultListener;
import com.salveo.mysalveo.interfaces.LocationDeleteListener;
import com.salveo.mysalveo.responsepojo.LocationListAddressResponse;
import com.salveo.mysalveo.serviceprovider.EditMyAddressSPActivity;


import java.util.List;




public class ManageAddressListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "ManageAddressListAdapter";
    private List<LocationListAddressResponse.DataBean> locationListResponseList  = null;
    private Context context;

    LocationListAddressResponse.DataBean currentItem;




    public static String id = "";

    private LocationDeleteListener locationDeleteListener;
    private LocationDefaultListener locationDefaultListener;
    String fromactivity;



    public ManageAddressListAdapter(Context context, List<LocationListAddressResponse.DataBean> locationListResponseList,LocationDeleteListener locationDeleteListener,LocationDefaultListener locationDefaultListener,String fromactivity) {
        this.context = context;
        this.locationListResponseList = locationListResponseList;
        this.locationDeleteListener = locationDeleteListener;
        this.locationDefaultListener = locationDefaultListener;
        this.fromactivity = fromactivity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_address_list, parent, false);
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

        holder.img_settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(context, v);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String titleName = String.valueOf(item.getTitle());
                        if(titleName != null && titleName.equalsIgnoreCase("Edit")){
                            if(fromactivity != null && fromactivity.equalsIgnoreCase("ManageAddressDoctorActivity")){
                                Intent i = new Intent(context, EditMyAddressDoctorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtra("id",locationListResponseList.get(position).get_id());
                                i.putExtra("userid",locationListResponseList.get(position).getUser_id());
                                i.putExtra("cityname",locationListResponseList.get(position).getLocation_city());
                                i.putExtra("state",locationListResponseList.get(position).getLocation_state());
                                i.putExtra("country",locationListResponseList.get(position).getLocation_country());
                                i.putExtra("address",locationListResponseList.get(position).getLocation_address());
                                i.putExtra("pincode",locationListResponseList.get(position).getLocation_pin());
                                i.putExtra("nickname",locationListResponseList.get(position).getLocation_nickname());
                                i.putExtra("locationtype",locationListResponseList.get(position).getLocation_title());
                                i.putExtra("defaultstatus",locationListResponseList.get(position).isDefault_status());
                                i.putExtra("fromactivity",fromactivity);
                                Bundle b = new Bundle();
                                b.putDouble("lat", locationListResponseList.get(position).getLocation_lat());
                                b.putDouble("lon", locationListResponseList.get(position).getLocation_long());
                                i.putExtras(b);
                                Log.w(TAG,"cityname-->"+locationListResponseList.get(position).getLocation_city());
                                context.startActivity(i);

                            }

                            else   if(fromactivity != null && fromactivity.equalsIgnoreCase("ManageAddressSPActivity")) {
                                Intent i = new Intent(context, EditMyAddressSPActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtra("id", locationListResponseList.get(position).get_id());
                                i.putExtra("userid", locationListResponseList.get(position).getUser_id());
                                i.putExtra("cityname", locationListResponseList.get(position).getLocation_city());
                                i.putExtra("state", locationListResponseList.get(position).getLocation_state());
                                i.putExtra("country", locationListResponseList.get(position).getLocation_country());
                                i.putExtra("address", locationListResponseList.get(position).getLocation_address());
                                i.putExtra("pincode", locationListResponseList.get(position).getLocation_pin());
                                i.putExtra("nickname", locationListResponseList.get(position).getLocation_nickname());
                                i.putExtra("locationtype", locationListResponseList.get(position).getLocation_title());
                                i.putExtra("defaultstatus", locationListResponseList.get(position).isDefault_status());
                                i.putExtra("fromactivity", fromactivity);
                                Bundle b = new Bundle();
                                b.putDouble("lat", locationListResponseList.get(position).getLocation_lat());
                                b.putDouble("lon", locationListResponseList.get(position).getLocation_long());
                                i.putExtras(b);
                                Log.w(TAG, "cityname-->" + locationListResponseList.get(position).getLocation_city());
                                context.startActivity(i);
                            }
                            else{
                                Intent i = new Intent(context, EditMyAddressActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtra("id",locationListResponseList.get(position).get_id());
                                i.putExtra("userid",locationListResponseList.get(position).getUser_id());
                                i.putExtra("cityname",locationListResponseList.get(position).getLocation_city());
                                i.putExtra("state",locationListResponseList.get(position).getLocation_state());
                                i.putExtra("country",locationListResponseList.get(position).getLocation_country());
                                i.putExtra("address",locationListResponseList.get(position).getLocation_address());
                                i.putExtra("pincode",locationListResponseList.get(position).getLocation_pin());
                                i.putExtra("nickname",locationListResponseList.get(position).getLocation_nickname());
                                i.putExtra("locationtype",locationListResponseList.get(position).getLocation_title());
                                i.putExtra("defaultstatus",locationListResponseList.get(position).isDefault_status());
                                i.putExtra("fromactivity",fromactivity);
                                Bundle b = new Bundle();
                                b.putDouble("lat", locationListResponseList.get(position).getLocation_lat());
                                b.putDouble("lon", locationListResponseList.get(position).getLocation_long());
                                i.putExtras(b);
                                Log.w(TAG,"cityname-->"+locationListResponseList.get(position).getLocation_city());
                                context.startActivity(i);

                            }


                        } else if(titleName != null && titleName.equalsIgnoreCase("Delete")){
                            locationDeleteListener.locationDeleteListener(locationListResponseList.get(position).isDefault_status(),locationListResponseList.get(position).get_id());

                        }
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });

        holder.rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationDefaultListener.locationDefaultListener(locationListResponseList.get(position).isDefault_status(),locationListResponseList.get(position).get_id(),locationListResponseList.get(position).getUser_id());

            }
        });



        if(locationListResponseList.get(position).isDefault_status()){
            holder.iv_default_location.setVisibility(View.GONE);
            holder.txt_default.setVisibility(View.VISIBLE);
            }else{
                holder.iv_default_location.setVisibility(View.GONE);
                holder.txt_default.setVisibility(View.GONE);
            }






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
        public TextView txt_location_title,txt_location_nickname,txt_address,txt_default;
        public ImageView img_settings,iv_default_location;
        public RelativeLayout rl_root;




        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_location_title = itemView.findViewById(R.id.txt_location_title);
            txt_location_nickname = itemView.findViewById(R.id.txt_location_nickname);
            txt_address = itemView.findViewById(R.id.txt_address);
            img_settings = itemView.findViewById(R.id.img_settings);
            iv_default_location = itemView.findViewById(R.id.iv_default_location);
            rl_root = itemView.findViewById(R.id.rl_root);
            txt_default = itemView.findViewById(R.id.txt_default);



        }




    }







}
