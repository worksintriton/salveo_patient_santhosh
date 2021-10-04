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
import com.salveo.mysalveo.responsepojo.VendorOrderListResponse;
import com.salveo.mysalveo.vendor.VendorOrderDetailsNewActivity;

import java.util.List;


public class VendorOrdersAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "VendorOrdersAdapter";

    List<VendorOrderListResponse.DataBean> orderResponseListAll;
    private Context context;
    VendorOrderListResponse.DataBean currentItem;
    String fromactivity;
   
    public VendorOrdersAdapter(Context context, List<VendorOrderListResponse.DataBean> orderResponseListAll, String fromactivity) {
        this.context = context;
        this.orderResponseListAll = orderResponseListAll;
        this.fromactivity = fromactivity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vendor_orders, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = orderResponseListAll.get(position);

        if(orderResponseListAll.get(position).getV_order_id()!=null&&!(orderResponseListAll.get(position).getV_order_id().isEmpty())){
            holder.txt_orderid.setText(orderResponseListAll.get(position).getV_order_id());
        }

        if(orderResponseListAll.get(position).getV_order_text()!=null&&!(orderResponseListAll.get(position).getV_order_text().isEmpty())){
            holder.txt_producttitle.setText(orderResponseListAll.get(position).getV_order_text());
        }
        if(orderResponseListAll.get(position).getV_order_status()!=null&&!(orderResponseListAll.get(position).getV_order_status().isEmpty())){
            holder.btn_update_status.setText(orderResponseListAll.get(position).getV_order_status());
        }

        if(orderResponseListAll.get(position).getV_order_image()!=null&&!(orderResponseListAll.get(position).getV_order_image().isEmpty())){
            Glide.with(context)
                    .load(orderResponseListAll.get(position).getV_order_image())
                    .into(holder.img_pet_imge);

        } else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_pet_imge);

        }
        if(orderResponseListAll.get(position).getV_order_price() != 0 && orderResponseListAll.get(position).getV_order_product_count() != 0) {
            if(orderResponseListAll.get(position).getV_order_product_count() == 1){
                holder.txt_service_cost.setText("\u20B9 " + orderResponseListAll.get(position).getV_order_price() + " (" + orderResponseListAll.get(position).getV_order_product_count() + " product )");
            }else{
                holder.txt_service_cost.setText("\u20B9 " + orderResponseListAll.get(position).getV_order_price() + " (" + orderResponseListAll.get(position).getV_order_product_count() + " products )");

            }
        }
        else{
            if(orderResponseListAll.get(position).getV_order_product_count() == 1){
                holder.txt_service_cost.setText("\u20B9 " + 0 + " (" + orderResponseListAll.get(position).getV_order_product_count() + " item )");
            }else{
                holder.txt_service_cost.setText("\u20B9 " + 0 + " (" + orderResponseListAll.get(position).getV_order_product_count() + " items )");

            }

        }

        Log.w(TAG,"fromactivity  : "+fromactivity);

        if(fromactivity != null && fromactivity.equalsIgnoreCase("FragementNewOrders")){
            if (orderResponseListAll.get(position).getV_order_booked_on() != null) {
                holder.txt_bookedon.setText("Booked on:" + " " + orderResponseListAll.get(position).getV_order_booked_on());

            }
        }
         if(fromactivity != null && fromactivity.equalsIgnoreCase("FragmentCompletedOrders")){
            if (orderResponseListAll.get(position).getV_completed_date() != null) {
                holder.txt_bookedon.setText("Delivered on:" + " " + orderResponseListAll.get(position).getV_completed_date());

            }

        }
         if(fromactivity != null && fromactivity.equalsIgnoreCase("FragmentCancelledOrders")){
            if (orderResponseListAll.get(position).getV_cancelled_date() != null) {
                holder.txt_bookedon.setText("Cancelled on:" + " " + orderResponseListAll.get(position).getV_cancelled_date());

            }
        }

        holder.txt_order_details.setOnClickListener(v -> {
                Intent i = new Intent(context, VendorOrderDetailsNewActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("_id",orderResponseListAll.get(position).getV_order_id());
                i.putExtra("fromactivity",fromactivity);
                context.startActivity(i);

        });
         holder.ll_root.setOnClickListener(v -> {
                Intent i = new Intent(context, VendorOrderDetailsNewActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("_id",orderResponseListAll.get(position).getV_order_id());
                i.putExtra("fromactivity",fromactivity);
                context.startActivity(i);

        });
        holder.btn_update_status.setOnClickListener(v -> {
             /*   Intent i = new Intent(context, VendorUpdateOrderStatusActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("order_id",orderResponseListAll.get(position).get_id());
                i.putExtra("fromactivity",TAG);
                context.startActivity(i);
*/
        });






    }


    @Override
    public int getItemCount() {
        return orderResponseListAll.size();

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_orderid,txt_producttitle,txt_service_cost,txt_bookedon,txt_order_details;
        public ImageView img_pet_imge;
        public LinearLayout ll_root;
        public Button btn_update_status;

        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            txt_orderid = itemView.findViewById(R.id.txt_orderid);
            txt_producttitle = itemView.findViewById(R.id.txt_producttitle);
            txt_service_cost = itemView.findViewById(R.id.txt_service_cost);
            txt_bookedon = itemView.findViewById(R.id.txt_bookedon);
            ll_root = itemView.findViewById(R.id.ll_root);
            txt_order_details = itemView.findViewById(R.id.txt_order_details);
            btn_update_status = itemView.findViewById(R.id.btn_update_status);

        }

    }

}
