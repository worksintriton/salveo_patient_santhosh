package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.api.APIClient;
import com.salveo.mysalveo.interfaces.FamilyMembersDeleteListener;
import com.salveo.mysalveo.interfaces.PetDeleteListener;
import com.salveo.mysalveo.petlover.AddYourFamilyMembersNewActivity;
import com.salveo.mysalveo.petlover.AddYourFamilyMembersOldActivity;
import com.salveo.mysalveo.petlover.EditYourPetProfileInfoActivity;
import com.salveo.mysalveo.requestpojo.FamilyMemberCreateRequest;
import com.salveo.mysalveo.responsepojo.FamilyMemberCreateResponse;
import com.salveo.mysalveo.responsepojo.FamilyMemberListResponse;
import com.salveo.mysalveo.responsepojo.PetListResponse;

import java.io.Serializable;
import java.util.List;


public class ManagePetListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<FamilyMemberListResponse.DataBean> dataBeanList;

    private final Context context;

    FamilyMemberListResponse.DataBean currentItem;

    FamilyMembersDeleteListener petDeleteListener;


    public static String id = "";

    private List<FamilyMemberListResponse.DataBean.PicBean> picBeanList;

    public ManagePetListAdapter(Context context, List<FamilyMemberListResponse.DataBean> dataBeanList, FamilyMembersDeleteListener petDeleteListener) {
        this.dataBeanList = dataBeanList;
        this.context = context;
        this.petDeleteListener = petDeleteListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petlist, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = dataBeanList.get(position);

        if (dataBeanList.get(position).getName() != null) {
            holder.txt_pet_name.setText(dataBeanList.get(position).getName());
        }



        if (dataBeanList != null && dataBeanList.size() > 0) {
            String TAG = "ManagePetListAdapter";
            Log.w(TAG,"dataBeanList : "+new Gson().toJson(dataBeanList));


            picBeanList =   dataBeanList.get(position).getPic();

            String petImagePath = null;


            Log.w(TAG,"picBeanList : "+new Gson().toJson(picBeanList));

            if(picBeanList != null && picBeanList.size() > 0) {

                for (int j=0;j<picBeanList.size();j++) {

                    petImagePath= picBeanList.get(j).getImage();

                }
            }




            Log.w(TAG,"petImagePath : "+petImagePath);

            if (petImagePath != null && !petImagePath.isEmpty()) {
                Glide.with(context)
                        .load(petImagePath)
                        .into(holder.img_pet_imge);
            }else {
                Glide.with(context)
                        .load(APIClient.PROFILE_IMAGE_URL)
                        .into(holder.img_pet_imge);

            }


        }


        holder.ll_add.setOnClickListener(v -> {
                Intent i = new Intent(context, AddYourFamilyMembersOldActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });


        holder.img_settings.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(context, v);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(item -> {
                String titleName = String.valueOf(item.getTitle());
                if(titleName.equalsIgnoreCase("Edit")){
                    Intent i = new Intent(context, EditYourPetProfileInfoActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("id",dataBeanList.get(position).get_id());
                    i.putExtra("userid",dataBeanList.get(position).getUser_id());
                    i.putExtra("petname",dataBeanList.get(position).getName());
                    i.putExtra("pettype",dataBeanList.get(position).getRelation());
                    i.putExtra("petgender",dataBeanList.get(position).getGender());
                    i.putExtra("petcolor",dataBeanList.get(position).getHeight());
                    i.putExtra("petweight",dataBeanList.get(position).getWeight());
                    i.putExtra("petdob",dataBeanList.get(position).getAge());
                    i.putExtra("vaccinatedstatus",dataBeanList.get(position).getAnymedicalinfo());

                    Bundle args = new Bundle();
                    //int list = dataBeanList.get(position).getPet_img().size();
                    args.putSerializable("PETLIST", (Serializable) dataBeanList.get(position).getPic());
                    i.putExtra("petimage",args);

                    context.startActivity(i);

                } else if(titleName.equalsIgnoreCase("Delete")){
                  petDeleteListener.familyMemberDeleteListener(dataBeanList.get(position).get_id());

                }
                return true;
            });

            popup.show();//showing popup menu
        });
        //closing the setOnClickListener method

        if(position == dataBeanList.size()-1){
            holder.ll_add.setVisibility(View.VISIBLE);
        }

       /* holder.cv_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, PetloverPetDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id",petListResponseList.get(position).get_id());
                i.putExtra("userid",petListResponseList.get(position).getUser_id());
                i.putExtra("petimage",petListResponseList.get(position).getPet_img().get(0).getPet_img());
                i.putExtra("petname",petListResponseList.get(position).getPet_name());
                i.putExtra("pettype",petListResponseList.get(position).getPet_type());
                i.putExtra("petbreed",petListResponseList.get(position).getPet_breed());
                i.putExtra("petgender",petListResponseList.get(position).getPet_gender());
                i.putExtra("petcolor",petListResponseList.get(position).getPet_color());
                i.putExtra("petweight",petListResponseList.get(position).getPet_weight());
                i.putExtra("petage",petListResponseList.get(position).getPet_age());
                i.putExtra("petdob",petListResponseList.get(position).getPet_dob());
                i.putExtra("vaccinatedstatus",petListResponseList.get(position).isVaccinated());
                i.putExtra("vaccinateddate",petListResponseList.get(position).getLast_vaccination_date());
                i.putExtra("defaultstatus",petListResponseList.get(position).isDefault_status());
                i.putExtra("pet_spayed",petListResponseList.get(position).isPet_spayed());
                i.putExtra("pet_purebred",petListResponseList.get(position).isPet_purebred());
                i.putExtra("pet_frnd_with_dog",petListResponseList.get(position).isPet_frnd_with_dog());
                i.putExtra("pet_frnd_with_cat",petListResponseList.get(position).isPet_frnd_with_cat());
                i.putExtra("pet_microchipped",petListResponseList.get(position).isPet_microchipped());
                i.putExtra("pet_tick_free",petListResponseList.get(position).isPet_tick_free());
                i.putExtra("pet_private_part",petListResponseList.get(position).isPet_private_part());
                i.putExtra("petbio",petListResponseList.get(position).getPetbio());

                Bundle args = new Bundle();
                //int list = petListResponseList.get(position).getPet_img().size();
                args.putSerializable("PETLIST", (Serializable) petListResponseList.get(position).getPet_img());
                i.putExtra("petimage",args);

                context.startActivity(i);

            }
        });*/
    }









    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_pet_name;
        public ImageView img_pet_imge,img_settings;
        public LinearLayout ll_add;
        public CardView cv_root;

        public ViewHolderOne(View itemView) {
            super(itemView);
            img_pet_imge = itemView.findViewById(R.id.img_pet_imge);
            img_settings = itemView.findViewById(R.id.img_settings);
            txt_pet_name = itemView.findViewById(R.id.txt_pet_name);
            ll_add = itemView.findViewById(R.id.ll_add);
            cv_root = itemView.findViewById(R.id.cv_root);
            ll_add.setVisibility(View.GONE);



        }




    }







}
