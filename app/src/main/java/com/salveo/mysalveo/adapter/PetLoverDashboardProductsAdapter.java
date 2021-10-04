package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.responsepojo.PetLoverDashboardResponse;

import java.util.List;


public class PetLoverDashboardProductsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetLoverDashboardProductsAdapter";
    private Context context;
    List<PetLoverDashboardResponse.DataBean.DashboarddataBean.ProductsDetailsBean> productDetailsResponseList;
    PetLoverDashboardResponse.DataBean.DashboarddataBean.ProductsDetailsBean currentItem;

    int size;
    private RecyclerView recyclerView;



    public PetLoverDashboardProductsAdapter(Context context, List<PetLoverDashboardResponse.DataBean.DashboarddataBean.ProductsDetailsBean> productDetailsResponseList, RecyclerView recyclerView, int size) {
        this.productDetailsResponseList = productDetailsResponseList;
        this.context = context;
        this.size = size;
        this.recyclerView = recyclerView;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petlover_dashboardproducts, parent, false);
        // recyclerView is your passed view.
        int width = recyclerView.getWidth();
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = (int)(width * 0.8);
        view.setLayoutParams(params);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

          currentItem = productDetailsResponseList.get(position);

          /*Log.w(TAG,"getImg_path :"+currentItem.getImg_path());

          if (currentItem.getImg_path() != null && !currentItem.getImg_path().isEmpty()) {

            Glide.with(context)
                    .load(currentItem.getImg_path())
                    //.load(R.drawable.logo)
                    .into(holder.img_products_image);

        }
          else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_products_image);

        }*/

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
        public LinearLayout ll_root;
        public ImageView img_products_image;




        public ViewHolderOne(View itemView) {
            super(itemView);

            img_products_image = itemView.findViewById(R.id.img_products_image);
            ll_root = itemView.findViewById(R.id.ll_root);




        }




    }










}
