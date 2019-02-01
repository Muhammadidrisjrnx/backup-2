package com.example.rplrus021.retrofitexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class adapter_row_item extends RecyclerView.Adapter<adapter_row_item.ViewHolder> {
    private ArrayList<Result> datalist;
    private Context context;

    public adapter_row_item(Context context, ArrayList<Result> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView_title.setText(datalist.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_title;
//        private Button button_details;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_title = itemView.findViewById(R.id.text_view_title);
//            button_details = itemView.findViewById(R.id.button_details);
        }
    }
}
