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
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.interfaces.OnAcceptsReturnOrder;
import com.salveo.mysalveo.interfaces.OnRejectsReturnOrder;
import com.salveo.mysalveo.responsepojo.VendorNewOrderResponse;
import com.salveo.mysalveo.vendor.VendorOrderDetailsActivity;
import com.salveo.mysalveo.vendor.VendorUpdateOrderStatusActivity;

import java.util.List;


public class VendorCancelledOrdersAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "VendorCancelledOrdersAdapter";
    private List<VendorNewOrderResponse.DataBean> newOrderResponseList;

    private Context context;

    VendorNewOrderResponse.DataBean currentItem;


    private int size;

    private OnAcceptsReturnOrder onAcceptsReturnOrder;

    private OnRejectsReturnOrder onRejectsReturnOrder;

    public VendorCancelledOrdersAdapter(Context context, List<VendorNewOrderResponse.DataBean> newOrderResponseList, int size, OnAcceptsReturnOrder onAcceptsReturnOrder) {
        this.newOrderResponseList = newOrderResponseList;
        this.context = context;
        this.size = size;
        this.onAcceptsReturnOrder = onAcceptsReturnOrder;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vendor_cancelled_order, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {


        currentItem = newOrderResponseList.get(position);


        if(newOrderResponseList.get(position).getOrder_id()!=null&&!(newOrderResponseList.get(position).getOrder_id().isEmpty())){

            holder.txt_orderid.setText(newOrderResponseList.get(position).getOrder_id());

        }

        if(newOrderResponseList.get(position).getProduct_name()!=null&&!(newOrderResponseList.get(position).getProduct_name().isEmpty())){

            holder.txt_producttitle.setText(newOrderResponseList.get(position).getProduct_name());

        }

        Log.w(TAG,"image_url "+newOrderResponseList.get(position).getProdcut_image());

        if(newOrderResponseList.get(position).getProdcut_image()!=null&&!(newOrderResponseList.get(position).getProdcut_image().isEmpty())){

            Glide.with(context)
                    .load(newOrderResponseList.get(position).getProdcut_image())
                    .into(holder.img_pet_imge);

        }
        else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_pet_imge);

        }


        if(newOrderResponseList.get(position).getProduct_price() != 0 && newOrderResponseList.get(position).getProduct_quantity() != 0) {
            if(newOrderResponseList.get(position).getProduct_quantity() == 1){
                holder.txt_service_cost.setText("\u20B9 " + newOrderResponseList.get(position).getProduct_price() + " (" + newOrderResponseList.get(position).getProduct_quantity() + " item )");
            }else{
                holder.txt_service_cost.setText("\u20B9 " + newOrderResponseList.get(position).getProduct_price() + " (" + newOrderResponseList.get(position).getProduct_quantity() + " items )");

            }
        }
        else{
            if(newOrderResponseList.get(position).getProduct_quantity() == 1){
                holder.txt_service_cost.setText("\u20B9 " + 0 + " (" + newOrderResponseList.get(position).getProduct_quantity() + " item )");
            }else{
                holder.txt_service_cost.setText("\u20B9 " + 0 + " (" + newOrderResponseList.get(position).getProduct_quantity() + " items )");

            }

        }


        if(newOrderResponseList.get(position).getVendor_cancell_date() != null && !(newOrderResponseList.get(position).getVendor_cancell_date().isEmpty())){
            holder.txt_cancelledon.setText("Cancelled on:"+" "+newOrderResponseList.get(position).getVendor_cancell_date());

        } else if(newOrderResponseList.get(position).getUser_return_date() != null&&!(newOrderResponseList.get(position).getUser_return_date().isEmpty())){
            holder.txt_cancelledon.setText("Returned on:"+" "+newOrderResponseList.get(position).getUser_return_date());

        }


        holder.txt_order_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VendorOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("_id",newOrderResponseList.get(position).get_id());
                i.putExtra("fromactivity",TAG);
                context.startActivity(i);

            }
        });

        holder.txt_track_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VendorUpdateOrderStatusActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("order_id",newOrderResponseList.get(position).get_id());
                i.putExtra("fromactivity",TAG);

                context.startActivity(i);

            }
        });



        holder.ll_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   /* Intent i = new Intent(context, SPAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("appointment_id",newAppointmentResponseList.get(position).get_id());
                    i.putExtra("bookedat",newAppointmentResponseList.get(position).getBooking_date_time());
                    i.putExtra("fromactivity",TAG);
                    context.startActivity(i);*/

            }
        });



        if(newOrderResponseList.get(position).getUser_return_info().equals("")&&newOrderResponseList.get(position).getVendor_accept_cancel().equals("")){
        }

        else if(!(newOrderResponseList.get(position).getUser_return_info().equals(""))&&newOrderResponseList.get(position).getVendor_accept_cancel().equals("")){
            holder.ll_btn.setVisibility(View.GONE);
        }
        else if(!(newOrderResponseList.get(position).getUser_return_info().equals(""))&&!(newOrderResponseList.get(position).getVendor_accept_cancel().equals(""))){
            holder.txt_return_accept.setVisibility(View.VISIBLE);


        }

        else if(!(newOrderResponseList.get(position).getUser_return_info().equals(""))&&!(newOrderResponseList.get(position).getVendor_cancell_info().equals(""))){
            //order return cancel - text
            holder.txt_return_cancel.setVisibility(View.VISIBLE);


        }


        holder.btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAcceptsReturnOrder.string(newOrderResponseList.get(position).get_id());

            }
        });

        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }


    @Override
    public int getItemCount() {
        return Math.min(newOrderResponseList.size(), size);

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_orderid,txt_producttitle,txt_service_cost,txt_cancelledon,txt_order_details,txt_track_order;
        public ImageView img_pet_imge;
        public LinearLayout ll_new,ll_btn;
        Button btn_accept,btn_reject;
        TextView txt_return_accept, txt_return_cancel;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            txt_orderid = itemView.findViewById(R.id.txt_orderid);
            txt_producttitle = itemView.findViewById(R.id.txt_producttitle);
            txt_service_cost = itemView.findViewById(R.id.txt_service_cost);
            txt_cancelledon = itemView.findViewById(R.id.txt_cancelledon);
            ll_new = itemView.findViewById(R.id.ll_new);
            txt_order_details = itemView.findViewById(R.id.txt_order_details);
            txt_track_order = itemView.findViewById(R.id.txt_track_order);
            ll_btn = itemView.findViewById(R.id.ll_btn);
            btn_accept = itemView.findViewById(R.id.btn_accept);
            btn_reject = itemView.findViewById(R.id.btn_reject);
            txt_return_accept = itemView.findViewById(R.id.txt_return_accept);
            txt_return_cancel = itemView.findViewById(R.id.txt_return_cancel);



        }




    }

}
