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
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.activity.location.PlacesSearchActivity;
import com.salveo.mysalveo.interfaces.PlacesNameListener;
import com.salveo.mysalveo.responsepojo.PlacesResultsResponse;

import java.util.List;


public class PlacesResultsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PlacesResultsAdapter";
    private List<PlacesResultsResponse.PredictionsBean> predictionsBeanList;
    private Context context;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    PlacesResultsResponse.PredictionsBean currentItem;






    private PlacesNameListener placesNameListener;
    private String fromactivity;



    public PlacesResultsAdapter(Context context, List<PlacesResultsResponse.PredictionsBean> predictionsBeanList, PlacesSearchActivity placesSearchActivity,String fromactivity) {
        this.predictionsBeanList = predictionsBeanList;
        this.context = context;
        this.placesNameListener = (PlacesNameListener)placesSearchActivity;
        this.fromactivity = fromactivity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_placesresults_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

             currentItem = predictionsBeanList.get(position);

            if(predictionsBeanList.get(position).getStructured_formatting().getMain_text() != null){
                Log.w(TAG,"MainPlaces-->"+predictionsBeanList.get(position).getStructured_formatting().getMain_text());
                holder.txt_mainplaces.setText(predictionsBeanList.get(position).getStructured_formatting().getMain_text());
            }
            if(predictionsBeanList.get(position).getStructured_formatting().getSecondary_text() != null) {
                holder.txt_secondaryplaces.setText(predictionsBeanList.get(position).getStructured_formatting().getSecondary_text());
                Log.w(TAG,"SecondaryPlaces-->"+predictionsBeanList.get(position).getStructured_formatting().getSecondary_text());

            }


            holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placesNameListener.selectedPlacesName(predictionsBeanList.get(position).getDescription(),predictionsBeanList.get(position).getStructured_formatting().getMain_text(),fromactivity);
                }




        });


   }

    @Override
    public int getItemCount() {
        return predictionsBeanList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_mainplaces,txt_secondaryplaces;
        public LinearLayout ll_root;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_mainplaces = itemView.findViewById(R.id.txt_mainplaces);
            txt_secondaryplaces = itemView.findViewById(R.id.txt_secondaryplaces);
            ll_root = itemView.findViewById(R.id.ll_root);



        }




    }







}
