package com.salveo.mysalveo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salveo.mysalveo.R;
import com.salveo.mysalveo.requestpojo.DocBusInfoUploadRequest;

import java.util.List;

public class EditVendorGovtIdPdfAdapter extends RecyclerView.Adapter<EditVendorGovtIdPdfAdapter.AddImageListHolder> {
    Context context;
    List<  DocBusInfoUploadRequest.GovtIdPicBean> govtIdPicBeans;
    View view;

    public EditVendorGovtIdPdfAdapter(Context context, List<  DocBusInfoUploadRequest.GovtIdPicBean> govtIdPicBeans) {
        this.context = context;
        this.govtIdPicBeans = govtIdPicBeans;

    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pdf_upload, parent, false);
        return new AddImageListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final   DocBusInfoUploadRequest.GovtIdPicBean govtIdPicBeanse = govtIdPicBeans.get(position);
        if (govtIdPicBeanse.getGovt_id_pic()!= null) {


        }

        holder.removeImg.setOnClickListener(view -> {
            govtIdPicBeans.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return govtIdPicBeans.size();
    }

    public static class AddImageListHolder extends RecyclerView.ViewHolder {
        ImageView removeImg,pdf_file;
        public AddImageListHolder(View itemView) {
            super(itemView);
            pdf_file = itemView.findViewById(R.id.pdf_file);
            removeImg = itemView.findViewById(R.id.close);
        }
    }


}