package com.example.rplrus021.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class costum_adapter extends BaseAdapter{
    ArrayList<chatting_model> user_models = new ArrayList<>();
    Context context;
    private chatting_model user_model;
    public String url;
    public costum_adapter(ArrayList<chatting_model> user_models, Context context) {
        this.user_models = user_models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.user_models.size();
    }

    @Override
    public Object getItem(int i) {
        return this.user_models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        user_model = user_models.get(pos);
        url = user_model.getImage();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        view = layoutInflater.inflate(R.layout.list_item, null);
        de.hdodenhof.circleimageview.CircleImageView icon_profil_chatting = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.image_profil_chatting);
        TextView name_profil_chatting = (TextView) view.findViewById(R.id.name_profil_chatting);
        Glide.with(view.getContext())
                .load(url)
                .into(icon_profil_chatting);
        name_profil_chatting.setText(user_model.getUser());
        return view;
    }
}
