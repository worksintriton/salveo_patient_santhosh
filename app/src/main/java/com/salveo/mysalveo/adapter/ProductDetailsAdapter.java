package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.doctor.shop.DoctorCancelOrderActivity;
import com.salveo.mysalveo.doctor.shop.DoctorTrackOrderActivity;
import com.salveo.mysalveo.petlover.PetVendorCancelOrderActivity;

import com.salveo.mysalveo.petlover.TrackOrderActivity;
import com.salveo.mysalveo.responsepojo.PetLoverVendorOrderDetailsResponse;
import com.salveo.mysalveo.serviceprovider.shop.SPCancelOrderActivity;
import com.salveo.mysalveo.serviceprovider.shop.SPTrackOrderActivity;

import java.util.List;


public class ProductDetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "ProductDetailsAdapter";

    List<PetLoverVendorOrderDetailsResponse.DataBean.ProductDetailsBean> product_details;
    private final Context context;
    PetLoverVendorOrderDetailsResponse.DataBean.ProductDetailsBean currentItem;
    String orderid;
    String fromactivity;



    public ProductDetailsAdapter(Context context,  List<PetLoverVendorOrderDetailsResponse.DataBean.ProductDetailsBean> product_details,String orderid, String fromactivity) {
        this.context = context;
        this.product_details = product_details;
        this.orderid = orderid;
        this.fromactivity = fromactivity;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_details, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = product_details.get(position);
       /* if (product_details.get(position).getProduct_id() != null) {
            holder.txt_orderid.setText(product_details.get(position).getOrder_id());
        }*/

        Log.w(TAG,"fromactivity : "+fromactivity);

        if(fromactivity != null){
            if(fromactivity.equalsIgnoreCase("FragmentPetLoverCompletedOrders")){
                holder.txt_cancell_order.setVisibility(View.INVISIBLE);
            }
            else if(fromactivity.equalsIgnoreCase("FragmentPetLoverCancelledOrders")){
                holder.txt_cancell_order.setVisibility(View.INVISIBLE);
            }
            else if(fromactivity.equalsIgnoreCase("FragmentDoctorCompletedOrders")){
                holder.txt_cancell_order.setVisibility(View.INVISIBLE);
            }
            else if(fromactivity.equalsIgnoreCase("FragmentDoctorCancelledOrders")){
                holder.txt_cancell_order.setVisibility(View.INVISIBLE);
            }
            else if(fromactivity.equalsIgnoreCase("FragmentSPCompletedOrders")){
                holder.txt_cancell_order.setVisibility(View.INVISIBLE);
            }
            else if(fromactivity.equalsIgnoreCase("FragmentSPCancelledOrders")){
                holder.txt_cancell_order.setVisibility(View.INVISIBLE);
            }


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

        holder.txt_track_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fromactivity != null) {
                    if (fromactivity.equalsIgnoreCase("FragmentDoctorNewOrders") || fromactivity.equalsIgnoreCase("FragmentDoctorCompletedOrders") || fromactivity.equalsIgnoreCase("FragmentDoctorCancelledOrders")) {
                        Intent i = new Intent(context, DoctorTrackOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("_id", product_details.get(position).getProduct_id());
                        i.putExtra("orderid", orderid);
                        i.putExtra("fromactivity", TAG);
                        context.startActivity(i);
                    }
                    else if (fromactivity.equalsIgnoreCase("FragmentSPNewOrders") || fromactivity.equalsIgnoreCase("FragmentSPCompletedOrders") || fromactivity.equalsIgnoreCase("FragmentSPCancelledOrders")) {
                        Intent i = new Intent(context, SPTrackOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("_id", product_details.get(position).getProduct_id());
                        i.putExtra("orderid", orderid);
                        i.putExtra("fromactivity", TAG);
                        context.startActivity(i);
                    }
                    else {
                        Intent i = new Intent(context, TrackOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("_id", product_details.get(position).getProduct_id());
                        i.putExtra("orderid", orderid);
                        i.putExtra("fromactivity", TAG);
                        context.startActivity(i);
                    }
                }else{
                    Intent i = new Intent(context, TrackOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("_id", product_details.get(position).getProduct_id());
                    i.putExtra("orderid", orderid);
                    i.putExtra("fromactivity", TAG);
                    context.startActivity(i);
                }



            }
        });
        holder.txt_cancell_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fromactivity != null) {
                    if (fromactivity.equalsIgnoreCase("FragmentDoctorNewOrders")) {
                        Intent i = new Intent(context, DoctorCancelOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("product_id", product_details.get(position).getProduct_id());
                        i.putExtra("orderid", orderid);
                        i.putExtra("fromactivity", fromactivity);
                        context.startActivity(i);
                    }else if (fromactivity.equalsIgnoreCase("FragmentSPNewOrders")) {
                        Intent i = new Intent(context, SPCancelOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("product_id", product_details.get(position).getProduct_id());
                        i.putExtra("orderid", orderid);
                        i.putExtra("fromactivity", fromactivity);
                        context.startActivity(i);
                    }else{
                        Intent i = new Intent(context, PetVendorCancelOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("product_id", product_details.get(position).getProduct_id());
                        i.putExtra("orderid", orderid);
                        i.putExtra("fromactivity", TAG);
                        context.startActivity(i);
                    }
                }else{
                    Intent i = new Intent(context, PetVendorCancelOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("product_id", product_details.get(position).getProduct_id());
                    i.putExtra("orderid", orderid);
                    i.putExtra("fromactivity", TAG);
                    context.startActivity(i);
                }


            }
        });
        if(product_details.get(position).getProduct_stauts() != null ){
            Log.w(TAG,"Product_stauts : "+product_details.get(position).getProduct_stauts());

            if( product_details.get(position).getProduct_stauts().equalsIgnoreCase("Order Booked")){
                holder.txt_cancell_order.setVisibility(View.VISIBLE);
                holder.txt_product_status.setVisibility(View.GONE);
            } else if(product_details.get(position).getProduct_stauts().equalsIgnoreCase("Order Accept")){
                holder.txt_cancell_order.setVisibility(View.GONE);
                holder.txt_product_status.setVisibility(View.VISIBLE);
                holder.txt_product_status.setTextColor(context.getResources().getColor(R.color.new_green_btn));
                holder.txt_product_status.setText(product_details.get(position).getProduct_stauts());
            } else if(product_details.get(position).getProduct_stauts().equalsIgnoreCase("Vendor cancelled")){
                holder.txt_cancell_order.setVisibility(View.GONE);
                holder.txt_product_status.setVisibility(View.VISIBLE);
                holder.txt_product_status.setText(product_details.get(position).getProduct_stauts());
            }else if(product_details.get(position).getProduct_stauts().equalsIgnoreCase("Order Dispatch")){
                holder.txt_cancell_order.setVisibility(View.GONE);
                holder.txt_product_status.setVisibility(View.VISIBLE);
                holder.txt_product_status.setTextColor(context.getResources().getColor(R.color.new_green_btn));
                holder.txt_product_status.setText(product_details.get(position).getProduct_stauts());
            }else if(product_details.get(position).getProduct_stauts().equalsIgnoreCase("Order Cancelled")){
                holder.txt_cancell_order.setVisibility(View.GONE);
                holder.txt_product_status.setVisibility(View.VISIBLE);
                holder.txt_product_status.setText(product_details.get(position).getProduct_stauts());
            }


        }





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
        public ViewHolderOne(View itemView) {
            super(itemView);
            img_products_image = itemView.findViewById(R.id.img_products_image);
            txt_orderid = itemView.findViewById(R.id.txt_orderid);
            txt_producttitle = itemView.findViewById(R.id.txt_producttitle);
            txt_products_price = itemView.findViewById(R.id.txt_products_price);
            txt_bookedon = itemView.findViewById(R.id.txt_bookedon);
            txt_track_order = itemView.findViewById(R.id.txt_track_order);
            txt_cancell_order = itemView.findViewById(R.id.txt_cancell_order);
            txt_product_status = itemView.findViewById(R.id.txt_product_status);
            txt_product_status.setVisibility(View.GONE);


        }


    }

}