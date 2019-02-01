package com.example.rplrus021.loginwithsharedpreference;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.Holder> {
    private ArrayList<data> data;
    private data data_contact;
    Context context;

    public adapter(ArrayList<data>data,Context context){
        this.data =data;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new adapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final int pos = position;
        data_contact = data.get(pos);
        Glide.with(context)
                .load(data_contact.getImage())
                .into(holder.imageView);
        holder.textView.setText(data_contact.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView imageView;
        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            imageView = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.image_view);
            textView = (TextView) itemView.findViewById(R.id.text_view_name);
        }
    }
}
