package com.liqy.douyin;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liqy.douyin.common.BaseActivity;
import com.liqy.douyin.follow.FollowFragment;
import com.liqy.douyin.home.HomeFragment;
import com.liqy.douyin.message.MessageFragment;
import com.liqy.douyin.mine.MineFragment;
import com.liqy.douyin.network.Constants;
import com.liqy.douyin.shoot.TextDragLayout;
import com.umeng.analytics.MobclickAgent;
import com.yanxuwen.mydrawer.BaseDragLayout;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;

/**
 * 运行时权限：https://github.com/tbruyelle/RxPermissions
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.bottom_layout)
    LinearLayout bottom_layout;

    @BindView(R.id.tv_home)
    TextView tv_home;

    @BindView(R.id.iv_refresh)
    ImageView iv_refresh;

    @BindView(R.id.tv_follow)
    TextView tv_follow;

    @BindView(R.id.tv_mesage)
    TextView tv_mesage;

    @BindView(R.id.tv_mine)
    TextView tv_mine;

    @BindColor(R.color.text_color_write)
    int text_color_write;
    @BindColor(R.color.text_color_gray)
    int text_color_gray;

    @BindColor(R.color.tab_bottom_transparent)
    int tab_bottom_transparent;

    @BindColor(R.color.tab_bottom_bg)
    int tab_bottom_bg;

    @BindString(R.string.main_tab_home_text)
    String tab_home_text;
    @BindString(R.string.main_tab_follow_text)
    String tab_follow_text;
    @BindString(R.string.main_tab_message_text)
    String tab_message_text;
    @BindString(R.string.main_tab_mine_text)
    String tab_mine_text;

    @BindView(R.id.iv_shoot)
    ImageView iv_shoot;


    @BindView(R.id.td_layout)
    TextDragLayout bottom_drag_layout;

    HomeFragment homeFragment;
    FollowFragment followFragment;
    MessageFragment messageFragment;
    MineFragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //当用户使用自有账号登录时，可以这样统计：
        MobclickAgent.onProfileSignIn(Constants.DEVICE_ID);
        initView();

    }

    /**
     * 初始化视图
     */
    private void initView() {
        tv_home.setOnClickListener(this);
        tv_follow.setOnClickListener(this);
        tv_mine.setOnClickListener(this);
        tv_mesage.setOnClickListener(this);
        iv_shoot.setOnClickListener(this);

        //设置默认
        setSelected(tv_home);
        switchFragment(tv_home.getId());

        bottom_drag_layout.setOnDragViewStatusListener(new BaseDragLayout.OnDragViewStatusListener() {
            @Override
            public void onDragViewStatus(boolean isOpen) {
                Log.e("xxxx", "底边抽屉是否打开" + isOpen);
            }
        });

        bottom_drag_layout.setOnDragViewOffsetListener(new BaseDragLayout.OnDragViewOffsetListener() {
            @Override
            public void onDragViewOffset(float Offset) {
                Log.e("xxxx", "底边抽屉偏移量" + Offset);
            }
        });
    }

    /**
     * 选择处理
     *
     * @param view
     */
    private void setSelected(TextView view) {
        int pos = 0;
        tv_home.setTextColor(text_color_gray);
        tv_follow.setTextColor(text_color_gray);
        tv_mesage.setTextColor(text_color_gray);
        tv_mine.setTextColor(text_color_gray);
        if (view.getId() == R.id.tv_home) {
            pos = 0;
            bottom_layout.setBackgroundColor(tab_bottom_transparent);
            tv_home.setVisibility(View.GONE);
            iv_refresh.setVisibility(View.VISIBLE);

        } else if (view.getId() == R.id.tv_follow) {
            pos = 1;
            bottom_layout.setBackgroundColor(tab_bottom_bg);
            tv_follow.setTextColor(text_color_write);
            tv_home.setVisibility(View.VISIBLE);
            iv_refresh.setVisibility(View.GONE);

        } else if (view.getId() == R.id.tv_mesage) {
            pos = 2;
            bottom_layout.setBackgroundColor(tab_bottom_bg);
            tv_mesage.setTextColor(text_color_write);
            tv_home.setVisibility(View.VISIBLE);
            iv_refresh.setVisibility(View.GONE);

        } else if (view.getId() == R.id.tv_mine) {
            pos = 3;
            bottom_layout.setBackgroundColor(tab_bottom_bg);
            tv_mine.setTextColor(text_color_write);
            tv_home.setVisibility(View.VISIBLE);
            iv_refresh.setVisibility(View.GONE);

        }
    }

    /**
     * switch the fragment accordting to id
     *
     * @param id
     */
    private void switchFragment(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.id.tv_home:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance("", "");
                }
                transaction.replace(R.id.sub_content, homeFragment);
                break;
            case R.id.tv_follow:
                if (followFragment == null) {
                    followFragment = FollowFragment.newInstance("", "");
                }
                transaction.replace(R.id.sub_content, followFragment);
                break;
            case R.id.tv_mesage:
                if (messageFragment == null) {
                    messageFragment = MessageFragment.newInstance("", "");
                }
                transaction.replace(R.id.sub_content, messageFragment);
                break;
            case R.id.tv_mine:
                if (mineFragment == null) {
                    mineFragment = MineFragment.newInstance("", "");
                }
                transaction.replace(R.id.sub_content, mineFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public String getPageStartName() {
        return getLocalClassName();
    }

    @Override
    public String getPageEndName() {
        return getLocalClassName();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            TextView textView = (TextView) v;
            setSelected(textView);
        }
        switch (v.getId()) {
            case R.id.tv_home:
                switchFragment(v.getId());
                break;
            case R.id.tv_follow:
                switchFragment(v.getId());
                break;
            case R.id.tv_mesage:
                switchFragment(v.getId());
                break;
            case R.id.tv_mine:
                switchFragment(v.getId());
                break;
            case R.id.iv_shoot:
                bottom_drag_layout.open();
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bottom_drag_layout != null && bottom_drag_layout.isOpen()) {
                bottom_drag_layout.close();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
