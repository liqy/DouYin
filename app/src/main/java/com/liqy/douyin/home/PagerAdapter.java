package com.liqy.douyin.home;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liqy.douyin.R;

import java.util.List;

public class PagerAdapter extends android.support.v4.view.PagerAdapter {

    public List<String> list;
    public Activity activity;

    public PagerAdapter() {
    }

    public PagerAdapter(Activity activity,List<String> list) {
        this.list = list;
        this.activity=activity;
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
        ImageView iv_head=(ImageView)view.findViewById(R.id.iv_head);
        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity,UserInfoActivity.class));
            }
        });
        view.setId(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container.findViewById(position));
    }
}