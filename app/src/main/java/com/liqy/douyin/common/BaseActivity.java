package com.liqy.douyin.common;

import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by liqy on 2018/1/29.
 */

public abstract   class BaseActivity extends AppCompatActivity {

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart(getPageStartName()); //统计页面(仅有Activity的应用中SDK自动调用，不需
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getPageEndName()); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);

    }

    public abstract String getPageStartName();
    public abstract String getPageEndName();


}
