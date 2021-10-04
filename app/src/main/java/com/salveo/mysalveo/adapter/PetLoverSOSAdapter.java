package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.EditSoSActivity;
import com.salveo.mysalveo.interfaces.SoSCallListener;
import com.salveo.mysalveo.responsepojo.PetLoverDashboardResponse;
import com.salveo.mysalveo.responsepojo.SOSListResponse;

import java.util.List;


public class PetLoverSOSAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetLoverSOSAdapter";

    private Context context;

    private SoSCallListener soSCallListener;





    private List<SOSListResponse.DataBean> sosList;
    PetLoverDashboardResponse.DataBean.SOSBean currentItem;
    private int row_index;


    public PetLoverSOSAdapter(Context context, List<SOSListResponse.DataBean> sosList, SoSCallListener soSCallListener) {
        this.context = context;
        this.sosList = sosList;
        this.soSCallListener = soSCallListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sos_no_list, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
          holder.txt_contact.setText(sosList.get(position).getName()+"");
          holder.txt_phn_num.setText(sosList.get(position).getPhone()+"");


        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index=position;
                notifyDataSetChanged();
                }




        });




        if(row_index == position){
            Log.w(TAG,"phonenumber : "+sosList.get(position).getPhone());
            soSCallListener.soSCallListener(sosList.get(position).getPhone());
            holder.ll_root.setBackgroundResource(R.drawable.rounded_sos_bgm);
            holder.txt_phn_num.setTextColor(Color.parseColor("#ffffff"));
            holder.txt_contact.setTextColor(Color.parseColor("#ffffff"));//for both textviews
        }
        else {
            holder.ll_root.setBackgroundResource(R.drawable.user_bgm_trans);
            holder.txt_phn_num.setTextColor(Color.parseColor("#555555"));
            holder.txt_contact.setTextColor(Color.parseColor("#555555"));//for both textviews
        }

        if(sosList.get(position).isEdit_status()){
            holder.img_menu.setVisibility(View.VISIBLE);
        }else{
            holder.img_menu.setVisibility(View.INVISIBLE);
        }

        holder.img_menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(context, v);


                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_manageproducts_menu, popup.getMenu());

                    final Menu menu = popup.getMenu();
                    menu.removeItem(R.id.menu_delete);

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String titleName = String.valueOf(item.getTitle());
                        if(titleName.equalsIgnoreCase("Edit")){
                           Intent i = new Intent(context, EditSoSActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("id",sosList.get(position).get_id());
                            i.putExtra("name",sosList.get(position).getName());
                            i.putExtra("phone",sosList.get(position).getPhone());
                            context.startActivity(i);

                        }



                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });






    }












    @Override
    public int getItemCount() {
        return sosList.size();



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_phn_num,txt_contact;
        public LinearLayout ll_root;
        public ImageView img_menu;




        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_phn_num = itemView.findViewById(R.id.txt_phn_num);
            txt_contact = itemView.findViewById(R.id.txt_contact);
            ll_root = itemView.findViewById(R.id.ll_root);
            img_menu = itemView.findViewById(R.id.img_menu);




        }




    }










}
