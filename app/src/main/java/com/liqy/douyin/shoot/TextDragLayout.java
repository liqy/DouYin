package com.liqy.douyin.shoot;

import android.content.Context;
import android.util.AttributeSet;

import com.liqy.douyin.R;
import com.yanxuwen.mydrawer.BaseDragLayout;

/**
 * 作者：严旭文 on 2017/2/16 15:37
 * 邮箱：420255048@qq.com
 */
public class TextDragLayout extends BaseDragLayout {

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
     * @param isOpen
     */
    @Override
    public void onViewStatus(boolean isOpen) {

    }

    /**
     * 偏移量监听
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
    }
}
