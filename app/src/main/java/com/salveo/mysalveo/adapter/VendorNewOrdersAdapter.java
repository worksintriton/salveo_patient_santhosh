package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

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
import com.salveo.mysalveo.responsepojo.VendorNewOrderResponse;
import com.salveo.mysalveo.vendor.VendorOrderDetailsActivity;
import com.salveo.mysalveo.vendor.VendorUpdateOrderStatusActivity;

import java.util.List;


public class VendorNewOrdersAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "VendorNewOrdersAdapter";
    private List<VendorNewOrderResponse.DataBean> newOrderResponseList;
    private Context context;
    VendorNewOrderResponse.DataBean currentItem;
    private int size;
    public VendorNewOrdersAdapter(Context context, List<VendorNewOrderResponse.DataBean> newOrderResponseList, int size) {
        this.context = context;
        this.newOrderResponseList = newOrderResponseList;
        this.size = size;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vendor_new_order, parent, false);
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

        if(newOrderResponseList.get(position).getDate_of_booking() != null&&!(newOrderResponseList.get(position).getDate_of_booking().isEmpty())){
            holder.txt_bookedon.setText("Booked on:"+" "+newOrderResponseList.get(position).getDate_of_booking());

        }

        holder.txt_order_details.setOnClickListener(v -> {
                Intent i = new Intent(context, VendorOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("_id",newOrderResponseList.get(position).get_id());
                i.putExtra("fromactivity",TAG);
                context.startActivity(i);

        });
        holder.btn_update_status.setOnClickListener(v -> {
                Intent i = new Intent(context, VendorUpdateOrderStatusActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("order_id",newOrderResponseList.get(position).get_id());
                i.putExtra("fromactivity",TAG);
                context.startActivity(i);

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
        public TextView txt_orderid,txt_producttitle,txt_service_cost,txt_bookedon,txt_order_details;
        public ImageView img_pet_imge;
        public LinearLayout ll_new;
        public Button btn_update_status;

        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            txt_orderid = itemView.findViewById(R.id.txt_orderid);
            txt_producttitle = itemView.findViewById(R.id.txt_producttitle);
            txt_service_cost = itemView.findViewById(R.id.txt_service_cost);
            txt_bookedon = itemView.findViewById(R.id.txt_bookedon);
            ll_new = itemView.findViewById(R.id.ll_new);
            txt_order_details = itemView.findViewById(R.id.txt_order_details);
            btn_update_status = itemView.findViewById(R.id.btn_update_status);

        }

    }

}
