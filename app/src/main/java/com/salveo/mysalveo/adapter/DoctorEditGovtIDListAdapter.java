package com.salveo.mysalveo.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salveo.mysalveo.R;
import com.salveo.mysalveo.requestpojo.DoctorBusinessInfoUpdateRequest;
import com.salveo.mysalveo.responsepojo.DoctorDetailsByUserIdResponse;

import java.util.List;

public class DoctorEditGovtIDListAdapter extends RecyclerView.Adapter<DoctorEditGovtIDListAdapter.AddImageListHolder> {
    private String TAG = "DoctorEditGovtIDListAdapter";
    Context context;
    private List<DoctorDetailsByUserIdResponse.DataBean.GovtIdPicBean> govtIdPicBeansEdit;
    private List<DoctorBusinessInfoUpdateRequest.GovtIdPicBean> govt_id_pic;
    String extension;


    View view;

    public DoctorEditGovtIDListAdapter(Context context, List<DoctorDetailsByUserIdResponse.DataBean.GovtIdPicBean> govtIdPicBeansEdit, List<DoctorBusinessInfoUpdateRequest.GovtIdPicBean> govt_id_pic) {
        this.context = context;
        this.govtIdPicBeansEdit = govtIdPicBeansEdit;
        this.govt_id_pic = govt_id_pic;


    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pdf_upload, parent, false);

        return new AddImageListHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final DoctorDetailsByUserIdResponse.DataBean.GovtIdPicBean govtIdPicBean = govtIdPicBeansEdit.get(position);

        Log.w(TAG,"ImagePic : "+govtIdPicBean.getGovt_id_pic());

        if (govtIdPicBean.getGovt_id_pic()!= null) {

            String uri = govtIdPicBean.getGovt_id_pic();
            if(uri.contains(".")) {
                extension = uri.substring(uri.lastIndexOf("."));

                Log.w("extension",extension);
            }

        }

        if(extension != null && !extension.isEmpty()) {
            if (extension.equals(".png") || extension.equals(".jpg") || (extension.equals(".jpeg"))) {
                Glide.with(context)
                        .load(govtIdPicBean.getGovt_id_pic())
                        .into(holder.pdf_file);

            }
        } else {

            holder.pdf_file.setImageResource(R.drawable.pdf_icon);
        }


        holder.removeImg.setOnClickListener(view -> {
            govtIdPicBeansEdit.remove(position);
            govt_id_pic.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return govtIdPicBeansEdit.size();
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