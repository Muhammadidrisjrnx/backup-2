package com.example.rplrus021.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class costum_adapter_my_creation extends RecyclerView.Adapter<costum_adapter_my_creation.ViewHolder> {
    private ArrayList<my_creation_model2> my_creation_models2;
    private Context context;

    public costum_adapter_my_creation(Context context, ArrayList<my_creation_model2> my_creation_models2) {
        this.context = context;
        this.my_creation_models2 = my_creation_models2;
    }

    @Override
    public costum_adapter_my_creation.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_status, parent, false);
        return new costum_adapter_my_creation.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(costum_adapter_my_creation.ViewHolder holder, final int position) {
        final my_creation_model2 my_creation_model2 = my_creation_models2.get(position);
        holder.image_view_status.setVisibility(View.VISIBLE);
        holder.button_share.setVisibility(View.VISIBLE);
        holder.name_profil_status.setText(my_creation_model2.getName());
        holder.text_status.setText(my_creation_model2.getJudul());
        Glide.with(context)
                .load("")
                .into(holder.icon_profil_status);
        Glide.with(context)
                .load(my_creation_model2.getImage())
                .into(holder.image_view_status);
        holder.button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final int pos = position;
                final String name = my_creation_model2.getName();
                final String judul = my_creation_model2.getJudul();
                AlertDialog.Builder alerBuilder = new AlertDialog.Builder(context);
                alerBuilder.setTitle("Are you want to share this creation "+judul);
                alerBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context.getApplicationContext(),main_menu.class);
//                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar snackbar = Snackbar.make(view,"",Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return my_creation_models2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView icon_profil_status;
        TextView name_profil_status, text_status;
        ImageView image_view_status;
        Button button_share;
        public ViewHolder(View itemView) {
            super(itemView);
            image_view_status = (ImageView) itemView.findViewById(R.id.image_view_status);
            icon_profil_status = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.icon_profil_status);
            name_profil_status = (TextView) itemView.findViewById(R.id.name_profil_status);
            text_status = (TextView) itemView.findViewById(R.id.text_status);
            button_share = (Button)itemView.findViewById(R.id.btn);
        }
    }
}
