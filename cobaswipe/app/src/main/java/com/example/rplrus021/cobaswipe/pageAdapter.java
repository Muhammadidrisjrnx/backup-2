package com.example.rplrus021.cobaswipe;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class pageAdapter extends PagerAdapter {

    public Object instantiateitem(View collection, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int res = 0;
        switch (position) {
            case 0:
                res = R.layout.activity_main2;
                break;
            case 1:
                res = R.layout.activity_main3;
                break;
        }
        View view = layoutInflater.inflate(res, null);
        ((ViewPager) collection).addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public void destroyItem(ViewGroup arg0,int arg1,Object arg2){
        ((ViewPager)arg0).removeView((View)arg2);
    }
    @Override
    public Parcelable saveState(){
        return null;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == ((View)arg1);
    }
}
