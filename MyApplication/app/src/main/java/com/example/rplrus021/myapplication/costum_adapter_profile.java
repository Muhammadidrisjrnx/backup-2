package com.example.rplrus021.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class costum_adapter_profile extends BaseAdapter {
    private ArrayList<my_creation_model2> my_creation_models = new ArrayList<>();
    Context context;
    private my_creation_model2 model;

    public costum_adapter_profile(ArrayList<my_creation_model2> my_creation_models, Context context) {
        this.my_creation_models = my_creation_models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return my_creation_models.size();
    }

    @Override
    public Object getItem(int i) {
        return my_creation_models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;
        model = my_creation_models.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        view = layoutInflater.inflate(R.layout.list_item_creation_in_profile,null);
        TextView judul_text=(TextView)view.findViewById(R.id.judul_text);
        TextView text_creation = (TextView)view.findViewById(R.id.text_creation);
        judul_text.setText(model.getText());
        text_creation.setText(model.getJudul());
        return view;
    }
}
