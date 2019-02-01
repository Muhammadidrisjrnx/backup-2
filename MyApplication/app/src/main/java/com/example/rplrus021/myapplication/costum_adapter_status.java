package com.example.rplrus021.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class costum_adapter_status extends RecyclerView.Adapter<costum_adapter_status.ViewHolder> {
    private ArrayList<status_model> status_models;
    private Context context;

    public costum_adapter_status(Context context,ArrayList<status_model>status_models) {
        this.context = context;
        this.status_models = status_models;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_status, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final status_model status = status_models.get(position);
        holder.name_profil_status.setText(status.getName_profil_status());
        holder.text_status.setText(status.getText_status());
        Glide.with(context)
                .load(status.getIcon_profil_status())
                .into(holder.icon_profil_status);
    }

    @Override
    public int getItemCount() {
//        int arr = 0;
//
//        try{
//            if(status_models.size()==0){
//
//                arr = 0;
//
//            }
//            else{
//
//                arr=status_models.size();
//            }
//
//
//
//        }catch (Exception e){
//
//
//
//        }
//
//        return arr;
        return status_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView icon_profil_status;
        TextView name_profil_status, text_status;

        public ViewHolder(View itemView) {
            super(itemView);
            icon_profil_status = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.icon_profil_status);
            name_profil_status = (TextView) itemView.findViewById(R.id.name_profil_status);
            text_status = (TextView) itemView.findViewById(R.id.text_status);
        }
    }
}
