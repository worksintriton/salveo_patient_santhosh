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
import com.salveo.mysalveo.doctor.DoctorOrderDetailsActivity;
import com.salveo.mysalveo.interfaces.AddandReviewListener;
import com.salveo.mysalveo.petlover.PetLoverVendorOrderDetailsActivity;
import com.salveo.mysalveo.responsepojo.PetLoverVendorOrderListResponse;
import com.salveo.mysalveo.serviceprovider.shop.SPOrderDetailsActivity;

import java.util.List;


public class PetLoverVendorOrdersAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "PetLoverVendorOrdersAdapter";
    List<PetLoverVendorOrderListResponse.DataBean> orderResponseListAll;
    private final Context context;
    PetLoverVendorOrderListResponse.DataBean currentItem;
    String fromactivity;
    private AddandReviewListener addReviewListener;




    public PetLoverVendorOrdersAdapter(Context context, List<PetLoverVendorOrderListResponse.DataBean> orderResponseListAll, String fromactivity,AddandReviewListener addReviewListener) {
        this.context = context;
        this.orderResponseListAll = orderResponseListAll;
        this.fromactivity = fromactivity;
        this.addReviewListener = addReviewListener;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petlover_new_orders, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber", "LongLogTag"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        Log.w(TAG,"fromactivity : "+fromactivity);
        currentItem = orderResponseListAll.get(position);
        if (orderResponseListAll.get(position).getP_order_id() != null) {
            holder.txt_orderid.setText(orderResponseListAll.get(position).getP_order_id());
        }
        if (orderResponseListAll.get(position).getP_order_text() != null) {
            holder.txt_producttitle.setText(orderResponseListAll.get(position).getP_order_text());
        }
        if (orderResponseListAll.get(position).getP_order_price() != 0 && orderResponseListAll.get(position).getP_order_product_count() != 0) {
            if (orderResponseListAll.get(position).getP_order_product_count() == 1) {
                holder.txt_products_price.setText("\u20B9 " + orderResponseListAll.get(position).getP_order_price() + " (" + orderResponseListAll.get(position).getP_order_product_count() + " product )");
            } else {
                holder.txt_products_price.setText("\u20B9 " + orderResponseListAll.get(position).getP_order_price() + " (" + orderResponseListAll.get(position).getP_order_product_count() + " products )");

            }
        }
        else { if (orderResponseListAll.get(position).getP_order_product_count() == 1) {
                holder.txt_products_price.setText("\u20B9 " + 0 + " (" + orderResponseListAll.get(position).getP_order_product_count() + " item )");
            } else { holder.txt_products_price.setText("\u20B9 " + 0 + " (" + orderResponseListAll.get(position).getP_order_product_count() + " items )"); } }



        if(fromactivity != null){
            if(fromactivity.equalsIgnoreCase("FragmentSPNewOrders") || fromactivity.equalsIgnoreCase("FragmentDoctorNewOrders") || fromactivity.equalsIgnoreCase("FragmentPetLoverNewOrders")){
                if (orderResponseListAll.get(position).getP_order_booked_on() != null) {
                    holder.txt_bookedon.setText("Booked on:" + " " + orderResponseListAll.get(position).getP_order_booked_on());

                }
            }
            else if(fromactivity.equalsIgnoreCase("FragmentSPCompletedOrders") || fromactivity.equalsIgnoreCase("FragmentDoctorCompletedOrders") || fromactivity.equalsIgnoreCase("FragmentPetLoverCompletedOrders")){
                if (orderResponseListAll.get(position).getP_completed_date() != null) {
                    holder.txt_bookedon.setText("Delivered on:" + " " + orderResponseListAll.get(position).getP_completed_date());

                }
                if(orderResponseListAll.get(position).getP_user_rate() == 0){
                    holder.btn_add_review.setVisibility(View.VISIBLE);
                }else{
                    holder.btn_add_review.setVisibility(View.GONE);

                }
                holder.btn_add_review.setOnClickListener(v -> addReviewListener.addReviewListener(orderResponseListAll.get(position).getP_order_id(),orderResponseListAll.get(position).getP_user_rate(),orderResponseListAll.get(position).getP_user_feedback()));

            }
            else if(fromactivity.equalsIgnoreCase("FragmentSPCancelledOrders") || fromactivity.equalsIgnoreCase("FragmentDoctorCancelledOrders") || fromactivity.equalsIgnoreCase("FragmentPetLoverCancelledOrders")){
                if (orderResponseListAll.get(position).getP_cancelled_date() != null) {
                    holder.txt_bookedon.setText("Cancelled on:" + " " + orderResponseListAll.get(position).getP_cancelled_date());

                }
            }

        }


        if (orderResponseListAll.get(position).getP_order_image() != null && !orderResponseListAll.get(position).getP_order_image().isEmpty()) {
            Glide.with(context)
                    .load(orderResponseListAll.get(position).getP_order_image())
                    .into(holder.img_products_image);

        }
        else {
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_products_image);

        }
        holder.txt_order_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fromactivity != null) {
                    if (fromactivity.equalsIgnoreCase("FragmentDoctorNewOrders") || fromactivity.equalsIgnoreCase("FragmentDoctorCompletedOrders") || fromactivity.equalsIgnoreCase("FragmentDoctorCancelledOrders")) {
                        Intent i = new Intent(context, DoctorOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("_id", orderResponseListAll.get(position).getP_order_id());
                        i.putExtra("fromactivity", fromactivity);
                        context.startActivity(i);
                    } else if (fromactivity.equalsIgnoreCase("FragmentSPNewOrders") || fromactivity.equalsIgnoreCase("FragmentSPCompletedOrders") || fromactivity.equalsIgnoreCase("FragmentSPCancelledOrders")) {
                        Intent i = new Intent(context, SPOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("_id", orderResponseListAll.get(position).getP_order_id());
                        i.putExtra("fromactivity", fromactivity);
                        context.startActivity(i);
                    } else{
                        Intent i = new Intent(context, PetLoverVendorOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("_id", orderResponseListAll.get(position).getP_order_id());
                        i.putExtra("fromactivity", fromactivity);
                        context.startActivity(i);
                    }
                }else{
                    Intent i = new Intent(context, PetLoverVendorOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("_id", orderResponseListAll.get(position).getP_order_id());
                    i.putExtra("fromactivity", fromactivity);
                    context.startActivity(i);
                }


            }
        });
        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fromactivity != null) {
                    if (fromactivity.equalsIgnoreCase("FragmentDoctorNewOrders") || fromactivity.equalsIgnoreCase("FragmentDoctorCompletedOrders") || fromactivity.equalsIgnoreCase("FragmentDoctorCancelledOrders")) {
                        Intent i = new Intent(context, DoctorOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("_id", orderResponseListAll.get(position).getP_order_id());
                        i.putExtra("fromactivity", fromactivity);
                        context.startActivity(i);
                    }else if (fromactivity.equalsIgnoreCase("FragmentSPNewOrders") || fromactivity.equalsIgnoreCase("FragmentSPCompletedOrders") || fromactivity.equalsIgnoreCase("FragmentSPCancelledOrders")) {
                        Intent i = new Intent(context, SPOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("_id", orderResponseListAll.get(position).getP_order_id());
                        i.putExtra("fromactivity", fromactivity);
                        context.startActivity(i);
                    } else{
                        Intent i = new Intent(context, PetLoverVendorOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("_id", orderResponseListAll.get(position).getP_order_id());
                        i.putExtra("fromactivity", fromactivity);
                        context.startActivity(i);
                    }
                }else{
                    Intent i = new Intent(context, PetLoverVendorOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("_id", orderResponseListAll.get(position).getP_order_id());
                    i.putExtra("fromactivity", fromactivity);
                    context.startActivity(i);
                }


            }
        });
       /* holder.txt_cancell_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PetVendorCancelOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("_id", newOrderResponseList.get(position).get_id());
                i.putExtra("fromactivity", TAG);
                context.startActivity(i);

            }
        });*/

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
        public TextView txt_orderid, txt_producttitle, txt_products_price, txt_bookedon, txt_order_details;
        public ImageView img_products_image;
        public Button btn_add_review;
        public LinearLayout ll_root;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_products_image = itemView.findViewById(R.id.img_products_image);
            txt_orderid = itemView.findViewById(R.id.txt_orderid);
            txt_producttitle = itemView.findViewById(R.id.txt_producttitle);
            txt_products_price = itemView.findViewById(R.id.txt_products_price);
            txt_bookedon = itemView.findViewById(R.id.txt_bookedon);
            txt_order_details = itemView.findViewById(R.id.txt_order_details);
            btn_add_review = itemView.findViewById(R.id.btn_add_review);
            ll_root = itemView.findViewById(R.id.ll_root);
            btn_add_review.setVisibility(View.GONE);

        }


    }

}