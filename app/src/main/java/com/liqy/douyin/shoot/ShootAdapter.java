package com.liqy.douyin.shoot;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liqy.douyin.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by liqy on 2018/2/6.
 */

public class ShootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> list;

    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;

    public ShootAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO{

        if (viewType == TYPE_2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoot_gawu, parent, false);
            return new GaWuViewHolder(view);
        } else if (viewType==TYPE_3){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoot_tab, parent, false);
            return new TabViewHolder(view);
        }

        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoot_type, parent, false);
            return new TypeViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    /**
     * 多条目加载
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 8) {
            return TYPE_2;
        }else  if (position==9){
            return TYPE_3;
        }else  if (position==10){
            return TYPE_3;
        }else  if (position==11){
            return TYPE_3;
        }
        return TYPE_1;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class TypeViewHolder extends RecyclerView.ViewHolder {

        public TypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class GaWuViewHolder extends RecyclerView.ViewHolder {

        public GaWuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class TabViewHolder extends RecyclerView.ViewHolder{
        public TabViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
