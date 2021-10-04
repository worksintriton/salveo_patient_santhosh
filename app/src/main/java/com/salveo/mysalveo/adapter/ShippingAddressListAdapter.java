package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;

import com.salveo.mysalveo.interfaces.OnDeleteShipAddrListener;
import com.salveo.mysalveo.interfaces.OnEditShipAddrListener;
import com.salveo.mysalveo.interfaces.OnSelectingShipIdListener;
import com.salveo.mysalveo.responsepojo.LocationListAddressResponse;


import java.util.List;

import es.dmoral.toasty.Toasty;


public class ShippingAddressListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "ShippingAddressListAdapter";

    private List<LocationListAddressResponse.DataBean> addressList;


    private Context context;

    LocationListAddressResponse.DataBean currentItem;



    String first_name, last_name, state, landmark, pincode;

    private int lastSelectedPosition = -1;
    private boolean isSelected = true;

    OnSelectingShipIdListener onSelectingShipIdListener;

    OnEditShipAddrListener onEditShipAddrListener;

    OnDeleteShipAddrListener onDeleteShipAddrListener;


    public ShippingAddressListAdapter(Context context, List<LocationListAddressResponse.DataBean> addressList,OnSelectingShipIdListener onSelectingShipIdListener,OnEditShipAddrListener onEditShipAddrListener,OnDeleteShipAddrListener onDeleteShipAddrListener) {
        this.context = context;
        this.addressList = addressList;
        this.onSelectingShipIdListener = onSelectingShipIdListener;
        this.onEditShipAddrListener = onEditShipAddrListener;
        this.onDeleteShipAddrListener = onDeleteShipAddrListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shipping_address, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {


        currentItem = addressList.get(position);

        if(addressList.get(position).getLocation_nickname()!=null&&!addressList.get(position).getLocation_nickname().isEmpty()){

            first_name = addressList.get(position).getLocation_nickname();

        }


        if(first_name!=null){
            holder.txt_username.setText(first_name);
        }




        if(addressList.get(position).getLocation_title()!=null && !addressList.get(position).getLocation_title().isEmpty()){

            holder.txt_addrs_type.setText(addressList.get(position).getLocation_title());

        }

        if(addressList.get(position).getDate_and_time()!=null && !addressList.get(position).getDate_and_time().isEmpty()){

            holder.txt_date.setText(addressList.get(position).getDate_and_time());

        }

        if(addressList.get(position).getLocation_city()!=null && !addressList.get(position).getLocation_city().isEmpty()){

            holder.txt_user_city.setText(addressList.get(position).getLocation_city());

        }
        if(addressList.get(position).getLocation_state()!=null && !addressList.get(position).getLocation_state().isEmpty()){

            state = addressList.get(position).getLocation_state();

        }

        if(addressList.get(position).getLocation_address()!=null && !addressList.get(position).getLocation_address().isEmpty()){

            holder.txt_street.setText(addressList.get(position).getLocation_address());

        }

        if(addressList.get(position).getLocation_nickname()!=null && !addressList.get(position).getLocation_nickname().isEmpty()){

           landmark = addressList.get(position).getLocation_nickname();

        }

        if(addressList.get(position).getLocation_pin()!=null && !addressList.get(position).getLocation_pin().isEmpty()){

           pincode = addressList.get(position).getLocation_pin();

        }


        /*if(landmark!=null&&pincode!=null&&state!=null){

            holder.txt_dist_pincode_state.setText(landmark + " " + " "+ state +" "+ pincode);
        }*/
        Log.w(TAG,"lastSelectedPosition : "+lastSelectedPosition+" position : "+position);

       //since only one radio button is allowed to be selected,
        // this condition un-checks previous selections

        holder.rb_choose_addr_list.setChecked(lastSelectedPosition == position);

        if(isSelected) {
            if (addressList.get(position).isDefault_status()) {
                holder.rb_choose_addr_list.setChecked(addressList.get(position).isDefault_status());
            }
        }

        holder.rb_choose_addr_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected = false;
               // newOrderResponseList.get(position).setUser_address_type("Last Used");
                lastSelectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
                onSelectingShipIdListener.onSelectShipID(addressList.get(position).get_id());
            }
        });




        holder.ll_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*

                Intent i = new Intent(context, EditShippingAddresssActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id",addressList.get(position).get_id());
                i.putExtra("userid",addressList.get(position).getUser_id());
                i.putExtra("cityname",addressList.get(position).getLocation_city());
                i.putExtra("state",addressList.get(position).getLocation_state());
                i.putExtra("country",addressList.get(position).getLocation_country());
                i.putExtra("address",addressList.get(position).getLocation_address());
                i.putExtra("pincode",addressList.get(position).getLocation_pin());
                i.putExtra("nickname",addressList.get(position).getLocation_nickname());
                i.putExtra("locationtype",addressList.get(position).getLocation_title());
                i.putExtra("defaultstatus",addressList.get(position).isDefault_status());
                Bundle b = new Bundle();
                b.putDouble("lat", addressList.get(position).getLocation_lat());
                b.putDouble("lon", addressList.get(position).getLocation_long());
                i.putExtras(b);
                Log.w(TAG,"cityname-->"+addressList.get(position).getLocation_city());
                context.startActivity(i);
*/



                onEditShipAddrListener.OnEditShipAddr(position);
            }
        });



        holder.ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!addressList.get(position).isDefault_status()){
                    onDeleteShipAddrListener.OnDeleteShipAddr(addressList.get(position).get_id());
                }else{
                    Toasty.warning(context, "Default location cannot be deleted.", Toast.LENGTH_SHORT, true).show();

                }


            }
        });

    }


    @Override
    public int getItemCount() {
        return addressList.size();

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView txt_username, txt_phnum, txt_user_city, txt_street, txt_dist_pincode_state, txt_date, txt_addrs_type;

        LinearLayout ll_edit,ll_delete;

        RadioButton rb_choose_addr_list;


        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_username = itemView.findViewById(R.id.txt_username);


            txt_phnum = itemView.findViewById(R.id.txt_phnum);


            txt_user_city = itemView.findViewById(R.id.txt_user_city);


            txt_street = itemView.findViewById(R.id.txt_street);


            txt_dist_pincode_state = itemView.findViewById(R.id.txt_dist_pincode_state);


            txt_date = itemView.findViewById(R.id.txt_date);
            txt_date.setVisibility(View.GONE);


            txt_addrs_type = itemView.findViewById(R.id.txt_addrs_type);


            ll_edit = itemView.findViewById(R.id.ll_edit);


            ll_delete = itemView.findViewById(R.id.ll_delete);


            rb_choose_addr_list = itemView.findViewById(R.id.rb_choose_addr_list);

        }




    }

}
