package com.liqy.douyin.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liqy.douyin.R;

import java.util.List;

public class PagerAdapter extends android.support.v4.view.PagerAdapter {

    public List<String> list;

    public PagerAdapter() {
    }

    public PagerAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_room_item, null);
        view.setId(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container.findViewById(position));
    }
}