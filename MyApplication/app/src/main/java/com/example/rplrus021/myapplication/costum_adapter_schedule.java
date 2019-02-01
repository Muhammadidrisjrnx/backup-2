package com.example.rplrus021.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class costum_adapter_schedule extends BaseAdapter {
    private ArrayList<schedule_model2> arrayList = new ArrayList<schedule_model2>();
    Context context;
    private schedule_model2 model;

    public costum_adapter_schedule(ArrayList<schedule_model2>arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;
        model = arrayList.get(position);
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        view = layoutInflater.inflate(R.layout.schedule,viewGroup,false);
        TextView textView = (TextView)view.findViewById(R.id.text_view_text_schedule);
        TextView textView1 = (TextView)view.findViewById(R.id.text_view_date_schedule);
        textView.setText(model.getText());
        textView1.setText(model.getTanggal());
        return view;
    }
}
