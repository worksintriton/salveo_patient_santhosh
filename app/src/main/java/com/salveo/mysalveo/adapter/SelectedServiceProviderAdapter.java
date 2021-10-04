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
import com.salveo.mysalveo.petlover.Service_Details_Activity;
import com.salveo.mysalveo.responsepojo.SPSpecificServiceDetailsResponse;

import java.util.ArrayList;
import java.util.List;


public class SelectedServiceProviderAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  String TAG = "SelectedServiceProviderAdapter";
    private Context context;
    private String from;

    SPSpecificServiceDetailsResponse.DataBean.ServiceProviderBean currentItem;


    private int size;
    List<SPSpecificServiceDetailsResponse.DataBean.ServiceProviderBean> serviceProviderList;

    List<SPSpecificServiceDetailsResponse.DataBean.ServiceProviderBean.BusServiceListBean> busServiceListBeanList;
    private String catid;

    private int distance;
    private int reviewcount;
    private int Count_value_start;
    private int Count_value_end;

    // Create an ArrayList
    ArrayList<String> serv_list
            = new ArrayList<String>();



    public SelectedServiceProviderAdapter(Context context, List<SPSpecificServiceDetailsResponse.DataBean.ServiceProviderBean> serviceProviderList,String catid,String from,int distance,int reviewcount,int Count_value_start,int Count_value_end) {
        this.serviceProviderList = serviceProviderList;
        this.context = context;
        this.catid = catid;
        this.from = from;
        this.distance = distance;
        this.reviewcount = reviewcount;
        this.Count_value_start = Count_value_start;
        this.Count_value_end = Count_value_end;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nearby_service_providers, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {


        Log.w(TAG,"distance : "+distance);

        currentItem = serviceProviderList.get(position);

//        holder.txt_serv_offer.setVisibility(View.GONE);

        holder.btn_book.setText("View");

        if (serviceProviderList.get(position).getService_provider_name() != null&& !serviceProviderList.get(position).getService_provider_name().isEmpty()) {

            holder.txt_service_providers.setText(serviceProviderList.get(position).getService_provider_name());

        }

        else {

            holder.txt_service_providers.setText("");

        }

        if (serviceProviderList.get(position).getCity_name() != null&& !serviceProviderList.get(position).getCity_name().isEmpty()) {

            holder.txt_place.setText(serviceProviderList.get(position).getCity_name());

        }

        else {

            holder.txt_place.setVisibility(View.GONE);

            holder.view.setVisibility(View.GONE);

        }

        if(serviceProviderList.get(position).getService_price() != 0) {

            holder.txt_serv_price.setVisibility(View.VISIBLE);

            holder.txt_serv_price.setText("\u20B9 " + serviceProviderList.get(position).getService_price());

        }
        else
        {
            holder.txt_serv_price.setText("\u20B9 " + 0);
        }

        if(serviceProviderList.get(position).getBus_service_list()!=null&&serviceProviderList.get(position).getBus_service_list().size()>0) {

            busServiceListBeanList = serviceProviderList.get(position).getBus_service_list();

            if(busServiceListBeanList!= null&&busServiceListBeanList.size()>0) {

                serv_list.clear();

                for (int i=0;i<busServiceListBeanList.size();i++){

                    serv_list.add(busServiceListBeanList.get(i).getBus_service_list());
                }
                StringBuilder str = new StringBuilder("");

                if(serv_list!=null&&serv_list.size()>0){

                    // Traversing the ArrayList
                    for (String eachstring : serv_list) {

                        // Each element in ArrayList is appended
                        // followed by comma
                        str.append(eachstring).append(",");
                    }

                    // StringBuffer to String conversion
                    String commaseparatedlist = str.toString();

                    // By following condition you can remove the last
                    // comma
                    if (commaseparatedlist.length() > 0)
                        commaseparatedlist
                                = commaseparatedlist.substring(
                                0, commaseparatedlist.length() - 1);

                    if(commaseparatedlist!=null&&!commaseparatedlist.isEmpty()){

                        holder.txt_serv_list.setText(commaseparatedlist);
                    }

                }
            }

            else {

                holder.txt_serv_list.setText("");
            }

        }

        else {

            holder.txt_serv_list.setText("");
        }

        if(serviceProviderList.get(position).getDistance() != 0) {
            holder.txt_km.setText(serviceProviderList.get(position).getDistance() + " km");
        }else{
            holder.txt_km.setText("0 km away");
        }
        if(serviceProviderList.get(position).getRating_count() != 0) {
            holder.txt_star_rating.setText(serviceProviderList.get(position).getRating_count() + "");
        }
//        if(serviceProviderList.get(position).getComments_count() != 0) {
//            holder.txt_review_count.setText(serviceProviderList.get(position).getComments_count() + "");
//        }
//
//

           if (serviceProviderList.get(position).getImage() != null && !serviceProviderList.get(position).getImage().isEmpty()) {

                Glide.with(context)
                        .load(serviceProviderList.get(position).getThumbnail_image())
                        .into(holder.img_service);

            }
           else{
                Glide.with(context)
                        .load(APIClient.PROFILE_IMAGE_URL)
                        .into(holder.img_service);

            }

           String distances = String.valueOf(serviceProviderList.get(position).getDistance());


        holder.btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Service_Details_Activity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("spid",serviceProviderList.get(position).get_id());
                i.putExtra("catid",catid);
                i.putExtra("from",from);
                i.putExtra("distance",distances);
                i.putExtra("reviewcount",reviewcount);
                i.putExtra("Count_value_start",Count_value_start);
                i.putExtra("Count_value_end",Count_value_end);
                context.startActivity(i);

            }
        });

           holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Service_Details_Activity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("spid",serviceProviderList.get(position).get_id());
                i.putExtra("catid",catid);
                i.putExtra("from",from);
                i.putExtra("distance",distances);
                i.putExtra("reviewcount",reviewcount);
                i.putExtra("Count_value_start",Count_value_start);
                i.putExtra("Count_value_end",Count_value_end);
                context.startActivity(i);

            }
        });





    }


    @Override
    public int getItemCount() {
        return serviceProviderList.size();

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_service_providers,txt_serv_price,txt_serv_list,txt_place,txt_km,txt_star_rating,txt_review_count;
        public ImageView img_service;
        public Button btn_book;
        public LinearLayout ll_root;
        public View view;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_service = itemView.findViewById(R.id.img_service);
            txt_service_providers = itemView.findViewById(R.id.txt_service_providers);
            txt_serv_price = itemView.findViewById(R.id.txt_price);
            txt_serv_list = itemView.findViewById(R.id.txt_serv_list);
            txt_place = itemView.findViewById(R.id.txt_place);
            txt_km = itemView.findViewById(R.id.txt_dist);
            txt_star_rating = itemView.findViewById(R.id.txt_star_rating);
            txt_review_count = itemView.findViewById(R.id.txt_review_count);
            btn_book = itemView.findViewById(R.id.btn_book);
            ll_root = itemView.findViewById(R.id.ll_root);
            view = itemView.findViewById(R.id.view9);
            txt_review_count.setVisibility(View.GONE);




        }




    }








}
