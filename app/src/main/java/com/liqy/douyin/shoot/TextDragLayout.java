package com.liqy.douyin.shoot;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.liqy.douyin.R;
import com.yanxuwen.mydrawer.BaseDragLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：严旭文 on 2017/2/16 15:37
 * 邮箱：420255048@qq.com
 */
public class TextDragLayout extends BaseDragLayout {

    RecyclerView recycler;

    public TextDragLayout(Context context) {
        super(context);
    }

    public TextDragLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TextDragLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 活动状态
     *
     * @param isOpen
     */
    @Override
    public void onViewStatus(boolean isOpen) {

    }

    /**
     * 偏移量监听
     *
     * @param mOffset
     */
    @Override
    public void onViewOffset(float mOffset) {

    }

    /**
     * 初始化视图
     */
    @Override
    public void initView() {
        setContentView(findViewById(R.id.layout_drag));
        recycler = findViewById(R.id.recycler_view);
        setRecyclerView(recycler);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 12);


        //TODO
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position==8){
                    return 12;
                }else if (position==9){
                    return 4;
                }else if (position==10){
                    return 4;
                }else if (position==11){
                    return 4;
                }else if (position>11){
                    return 12;
                }
                return 3;
            }
        });

        recycler.setLayoutManager(manager);
        recycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add(""); list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        recycler.setAdapter(new ShootAdapter(list));

    }
}
