package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.interfaces.OnItemCheckConfirmStatus;
import com.salveo.mysalveo.interfaces.OnItemCheckDispatchStatus;
import com.salveo.mysalveo.interfaces.OnItemCheckRejectStatus;
import com.salveo.mysalveo.petlover.PetVendorCancelOrderActivity;
import com.salveo.mysalveo.responsepojo.PetLoverVendorOrderDetailsResponse;
import com.salveo.mysalveo.vendor.VendorTrackOrderActivity;

import java.util.List;


public class ProductDetailsVendorAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "ProductDetailsVendorAdapter";

    List<PetLoverVendorOrderDetailsResponse.DataBean.ProductDetailsBean> product_details;
    private final Context context;
    PetLoverVendorOrderDetailsResponse.DataBean.ProductDetailsBean currentItem;
    String orderid;

    OnItemCheckConfirmStatus onItemCheckConfirmStatus;
    OnItemCheckRejectStatus onItemCheckRejectStatus;
    OnItemCheckDispatchStatus onItemCheckDispatchStatus;
    private boolean isCheckedReject;
    private boolean isCheckedConfirm;
    boolean status;
    private String fromactivity;


    public ProductDetailsVendorAdapter(Context context, List<PetLoverVendorOrderDetailsResponse.DataBean.ProductDetailsBean> product_details, String orderid, OnItemCheckConfirmStatus onItemCheckConfirmStatus,OnItemCheckRejectStatus onItemCheckRejectStatus, OnItemCheckDispatchStatus onItemCheckDispatchStatus, boolean status,String fromactivity) {
        this.context = context;
        this.product_details = product_details;
        this.orderid = orderid;
        this.onItemCheckConfirmStatus = onItemCheckConfirmStatus;
        this.onItemCheckRejectStatus = onItemCheckRejectStatus;
        this.onItemCheckDispatchStatus = onItemCheckDispatchStatus;
        this.status = status;
        this.fromactivity = fromactivity;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_details_vendor, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LongLogTag", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = product_details.get(position);
        if (orderid != null) {
            holder.txt_orderid.setText(orderid);
        }



        if(product_details.get(position).getProduct_stauts() != null ){
            Log.w(TAG,"Product_stauts : "+product_details.get(position).getProduct_stauts());
           if( product_details.get(position).getProduct_stauts().equalsIgnoreCase("Order Booked")){
               holder.ll_confirm_reject.setVisibility(View.VISIBLE);
               holder.ll_dispatch.setVisibility(View.GONE);
               holder.txt_product_status.setVisibility(View.GONE);
           }
           else if(product_details.get(position).getProduct_stauts().equalsIgnoreCase("Order Accept")){
               holder.ll_confirm_reject.setVisibility(View.GONE);
               holder.ll_dispatch.setVisibility(View.VISIBLE);
               holder.txt_product_status.setVisibility(View.GONE);
           }
           else if(product_details.get(position).getProduct_stauts().equalsIgnoreCase("Vendor cancelled")){
               holder.ll_confirm_reject.setVisibility(View.GONE);
               holder.ll_dispatch.setVisibility(View.GONE);
               holder.txt_product_status.setVisibility(View.VISIBLE);
               holder.txt_product_status.setText(product_details.get(position).getProduct_stauts());
           }else if(product_details.get(position).getProduct_stauts().equalsIgnoreCase("Order Dispatch")){
               holder.ll_confirm_reject.setVisibility(View.GONE);
               holder.ll_dispatch.setVisibility(View.GONE);
               holder.txt_product_status.setVisibility(View.VISIBLE);
               holder.txt_product_status.setTextColor(context.getResources().getColor(R.color.new_green_btn));
               holder.txt_product_status.setText(product_details.get(position).getProduct_stauts());
           }else if(product_details.get(position).getProduct_stauts().equalsIgnoreCase("Order Cancelled")){
               holder.ll_confirm_reject.setVisibility(View.GONE);
               holder.ll_dispatch.setVisibility(View.GONE);
               holder.txt_product_status.setVisibility(View.VISIBLE);
               holder.txt_product_status.setText(product_details.get(position).getProduct_stauts());
            }



        }

        Log.w(TAG,"fromactivity : "+fromactivity);
        if(fromactivity != null && fromactivity.equalsIgnoreCase("FragmentCancelledOrders")) {
            holder.ll_confirm_reject.setVisibility(View.GONE);
            holder.ll_dispatch.setVisibility(View.GONE);
        }

        if (product_details.get(position).getProduct_name() != null) {
            holder.txt_producttitle.setText(product_details.get(position).getProduct_name());
        }
        if (product_details.get(position).getProduct_price() != 0 && product_details.get(position).getProduct_count() != 0) {
            if (product_details.get(position).getProduct_count() == 1) {
                holder.txt_products_price.setText("\u20B9 " + product_details.get(position).getProduct_price() + " (" + product_details.get(position).getProduct_count() + " item )");
            } else {
                holder.txt_products_price.setText("\u20B9 " + product_details.get(position).getProduct_price() + " (" + product_details.get(position).getProduct_count() + " items )");

            }
        }
        else { if (product_details.get(position).getProduct_count() == 1) {
                holder.txt_products_price.setText("\u20B9 " + 0 + " (" + product_details.get(position).getProduct_count() + " item )");
            }
        else {
                holder.txt_products_price.setText("\u20B9 " + 0 + " (" + product_details.get(position).getProduct_count() + " items )");

            }

        }
        if (product_details.get(position).getProduct_booked() != null) {
            holder.txt_bookedon.setText("Booked on:" + " " + product_details.get(position).getProduct_booked());

        }
        if (product_details.get(position).getProduct_image() != null && !product_details.get(position).getProduct_image().isEmpty()) {
            Glide.with(context)
                    .load(product_details.get(position).getProduct_image())
                    .into(holder.img_products_image);

        }
        else {
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_products_image);

        }

        holder.ch_confirm.setChecked(status);

        holder.txt_track_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VendorTrackOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("_id", product_details.get(position).getProduct_id());
                i.putExtra("orderid", orderid);
                i.putExtra("fromactivity", TAG);
                context.startActivity(i);


            }
        });
        holder.txt_cancell_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PetVendorCancelOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("_id", product_details.get(position).getProduct_id());
                i.putExtra("fromactivity", TAG);
                context.startActivity(i);

            }
        });

        holder.ch_confirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (holder.ch_confirm.isChecked()) {
                        holder.ch_reject.setChecked(false);
                        isCheckedConfirm = true;
                        isCheckedReject = false;
                        onItemCheckConfirmStatus.onItemCheckConfirmStatus(product_details.get(position).getProduct_id());

                    }

                }else if (!holder.ch_confirm.isChecked()){
                    holder.ch_reject.setChecked(true);
                    isCheckedConfirm = false;
                    isCheckedReject = true;
                    onItemCheckConfirmStatus.onItemUncheckConfirmStatus(product_details.get(position).getProduct_id());
                }



            }
        });
        holder.ch_reject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (holder.ch_reject.isChecked()) {
                        holder.ch_confirm.setChecked(false);
                        isCheckedReject = true;
                        isCheckedConfirm = false;
                        onItemCheckRejectStatus.onItemCheckRejectStatus(product_details.get(position).getProduct_id());

                    }

                }else if (!holder.ch_reject.isChecked()){
                    holder.ch_confirm.setChecked(true);
                    isCheckedReject = false;
                    isCheckedConfirm = true;
                    onItemCheckRejectStatus.onItemUncheckRejectStatus(product_details.get(position).getProduct_id());

                }

              /*  if(isChecked){
                    if (holder.ch_reject.isChecked()) {
                    }

                }else{

                }
*/
            }
        });
        holder.ch_dispatch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (holder.ch_dispatch.isChecked()) {
                        onItemCheckDispatchStatus.onItemCheckDispatchStatus(product_details.get(position).getProduct_id());
                    }else{
                        onItemCheckDispatchStatus.onItemUncheckDispatchStatus(product_details.get(position).getProduct_id());

                    }

                }
                else{
                    onItemCheckDispatchStatus.onItemUncheckDispatchStatus(product_details.get(position).getProduct_id());

                }



            }
        });


    }

    @Override
    public int getItemCount() {
        return product_details.size();

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_orderid, txt_producttitle, txt_products_price, txt_bookedon, txt_track_order, txt_cancell_order,txt_product_status;
        public ImageView img_products_image;
        public CheckBox ch_confirm,ch_reject,ch_dispatch;
        public LinearLayout ll_confirm_reject,ll_dispatch;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_products_image = itemView.findViewById(R.id.img_products_image);
            txt_orderid = itemView.findViewById(R.id.txt_orderid);
            txt_producttitle = itemView.findViewById(R.id.txt_producttitle);
            txt_products_price = itemView.findViewById(R.id.txt_products_price);
            txt_bookedon = itemView.findViewById(R.id.txt_bookedon);
            txt_track_order = itemView.findViewById(R.id.txt_track_order);
            txt_cancell_order = itemView.findViewById(R.id.txt_cancell_order);
            ch_confirm = itemView.findViewById(R.id.ch_confirm);
            ch_reject = itemView.findViewById(R.id.ch_reject);
            ch_dispatch = itemView.findViewById(R.id.ch_dispatch);
            txt_cancell_order.setVisibility(View.GONE);
            txt_product_status = itemView.findViewById(R.id.txt_product_status);
            ll_confirm_reject = itemView.findViewById(R.id.ll_confirm_reject);
            ll_dispatch = itemView.findViewById(R.id.ll_dispatch);


        }


    }

}