package com.salveo.mysalveo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.interfaces.SpecTypeChckedListener;
import com.salveo.mysalveo.responsepojo.DropDownListResponse;

import java.util.List;


public class SpecTypesListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "SpecTypesListAdapter";
    private Context mcontext;
    private List<DropDownListResponse.DataBean.SpecialzationBean> spectypedataBeanList;
    DropDownListResponse.DataBean.SpecialzationBean currentItem;
    private SpecTypeChckedListener specTypeChckedListener;


    public SpecTypesListAdapter(Context context, List<DropDownListResponse.DataBean.SpecialzationBean> spectypedataBeanList, SpecTypeChckedListener specTypeChckedListener) {
        this.spectypedataBeanList = spectypedataBeanList;
        this.mcontext = context;
        this.specTypeChckedListener = specTypeChckedListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_specialization_list, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = spectypedataBeanList.get(position);

        holder.txt_spectypes.setText(currentItem.getSpecialzation());

        holder.chx_spectypes.setChecked(currentItem.isSelected());

        holder.chx_spectypes.setTag(position);

        holder.chx_spectypes.setOnClickListener(v -> {

            Integer pos = (Integer) holder.chx_spectypes.getTag();

            if (spectypedataBeanList.get(pos).isSelected())
            {
                spectypedataBeanList.get(pos).setSelected(false);

                specTypeChckedListener.onItemSpecUnCheck(pos,spectypedataBeanList.get(pos).getSpecialzation());

            }

            else
            {
                specTypeChckedListener.onItemSpecCheck(pos,spectypedataBeanList.get(pos).getSpecialzation(),spectypedataBeanList);

            }

        });

    }
    @Override
    public int getItemCount() {
        return spectypedataBeanList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public TextView txt_spectypes;
        public CheckBox chx_spectypes;


        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_spectypes = itemView.findViewById(R.id.spec_name);

            chx_spectypes = itemView.findViewById(R.id.checkbox_specialization_type);


        }

    }

}
