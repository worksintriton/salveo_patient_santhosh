package com.salveo.mysalveo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.interfaces.PetBreedTypeSelectListener;
import com.salveo.mysalveo.responsepojo.BreedTypeResponse;

import java.util.List;


public class PetBreedTypesListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetBreedTypesListAdapter";
    private Context context;
    BreedTypeResponse.DataBean currentItem;
    private List<BreedTypeResponse.DataBean> breedTypedataBeanList;
    private PetBreedTypeSelectListener petBreedTypeSelectListener;




    public PetBreedTypesListAdapter(Context context,  List<BreedTypeResponse.DataBean> breedTypedataBeanList, PetBreedTypeSelectListener petBreedTypeSelectListener ) {
        this.context = context;
        this.breedTypedataBeanList = breedTypedataBeanList;
        this.petBreedTypeSelectListener = petBreedTypeSelectListener;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_breedtype_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("LogNotTimber")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = breedTypedataBeanList.get(position);

        if(currentItem.getPet_breed() != null){
            holder.txt_breedtype.setText(currentItem.getPet_breed());

        }

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<breedTypedataBeanList.size();i++){
                    breedTypedataBeanList.get(i).setSelected(false);
                }
                breedTypedataBeanList.get(position).setSelected(true);
                notifyDataSetChanged();
                if(breedTypedataBeanList.get(position).getPet_breed() != null && breedTypedataBeanList.get(position).get_id() != null){
                    petBreedTypeSelectListener.petBreedTypeSelectListener(breedTypedataBeanList.get(position).getPet_breed(),breedTypedataBeanList.get(position).get_id());

                }
            }
        });



        if (breedTypedataBeanList.get(position).isSelected()) {
            Log.w(TAG, "IF isSelected--->");
            holder.ll_root.setBackgroundResource(R.drawable.selected_background);
            holder.txt_breedtype.setTextColor(ContextCompat.getColor(context, R.color.white));
        } else {
            Log.w(TAG, "ELSE isSelected--->");
            holder.ll_root.setBackgroundResource(R.drawable.user_bgm_trans);
            holder.txt_breedtype.setTextColor(ContextCompat.getColor(context, R.color.black));



        }



    }
    @Override
    public int getItemCount() {
        return breedTypedataBeanList.size();
    }

 /*   public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    breedTypedataBeanListFiltered = breedTypedataBeanList;
                } else {
                    List<BreedTypeResponse.DataBean> filteredList = new ArrayList<>();
                    for (BreedTypeResponse.DataBean row : breedTypedataBeanList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPet_breed().toLowerCase().contains(charString.toLowerCase()) || row.getPet_breed().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    breedTypedataBeanListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = breedTypedataBeanListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                breedTypedataBeanListFiltered = (ArrayList<BreedTypeResponse.DataBean>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }*/

    public void filterList(List<BreedTypeResponse.DataBean> breedTypedataBeanListFiltered) {
        breedTypedataBeanList = breedTypedataBeanListFiltered;
        Log.w(TAG,"breedTypedataBeanList : "+new Gson().toJson(breedTypedataBeanList));

        notifyDataSetChanged();
    }




    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_breedtype;
        public LinearLayout ll_root;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_breedtype = itemView.findViewById(R.id.txt_breedtype);
            ll_root = itemView.findViewById(R.id.ll_root);










        }




    }

















}
