package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.responsepojo.PetLoverDashboardResponse;

import java.util.List;


public class PetLoverProductsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_LOADING = 5;
    private  String TAG = "PetLoverProductsAdapter";
    private Context context;
    List<PetLoverDashboardResponse.DataBean.DashboarddataBean.ProductsDetailsBean> productDetailsResponseList;
    PetLoverDashboardResponse.DataBean.DashboarddataBean.ProductsDetailsBean currentItem;

    int size;



    public PetLoverProductsAdapter(Context context, List<PetLoverDashboardResponse.DataBean.DashboarddataBean.ProductsDetailsBean> productDetailsResponseList, RecyclerView inbox_list, int size) {
        this.productDetailsResponseList = productDetailsResponseList;
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petloverproducts, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

          currentItem = productDetailsResponseList.get(position);

          /*if (currentItem.getImg_path() != null && !currentItem.getImg_path().isEmpty()) {

            Glide.with(context)
                    .load(currentItem.getImg_path())
                    //.load(R.drawable.logo)
                    .into(holder.img_products_image);

        }
          else{
            Glide.with(context)
                    .load(R.drawable.services)
                    .into(holder.img_products_image);

        }*/

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent(context, SubServicesActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("vehicletypeid",popularserviceBeanList.get(position).getVehicle_Type_id());
                intent.putExtra("serviceid",popularserviceBeanList.get(position).get_id());
                intent.putExtra("city",city);
                intent.putExtra("street",street);
                intent.putExtra("vehicleImage", vehicleImage);
                intent.putExtra("vehicleName", vehicleName);
                intent.putExtra("vehicleModelName", vehicleModelName);
                intent.putExtra("fuelType", fuelType);
                intent.putExtra("servicename", servicename);
                intent.putExtra("masterservicename", masterservicename);
                intent.putExtra("vehicletypename", vehicletypename);
                intent.putExtra("customervehicledatabeanlist", customerVehicleDataBeanList);
                intent.putExtra("twowheelervehicleid",twowheelervehicleid);
                intent.putExtra("fourwheelervehicleid",fourwheelervehicleid);
                intent.putExtra("masterserviceid",masterserviceid);
                intent.putExtra("selectedVehicleId",selectedVehicleId);
                intent.putExtra("selectedVehicleType",selectedVehicleType);
                Log.w(TAG,"vehicletypeid :"+popularserviceBeanList.get(position).getVehicle_Type_id()+" "+"serviceid : "+popularserviceBeanList.get(position).get_id());
                context.startActivity(intent);*/
                }




        });










    }












    @Override
    public int getItemCount() {
        return Math.min(productDetailsResponseList.size(), size);



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_products_title,txt_products_price,txt_products_offer,txt_star_rating,txt_review_count;
        public LinearLayout ll_root;
        public ImageView img_products_image;




        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_products_title = itemView.findViewById(R.id.txt_products_title);
            txt_products_price = itemView.findViewById(R.id.txt_products_price);
            txt_products_offer = itemView.findViewById(R.id.txt_products_offer);
            img_products_image = itemView.findViewById(R.id.img_products_image);
            ll_root = itemView.findViewById(R.id.ll_root);
            txt_star_rating = itemView.findViewById(R.id.txt_star_rating);
            txt_review_count = itemView.findViewById(R.id.txt_review_count);
            txt_review_count.setVisibility(View.GONE);



        }




    }










}
