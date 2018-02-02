package com.liqy.douyin;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.facebook.stetho.Stetho;
import com.liqy.douyin.db.DBHelper;
import com.ss.android.common.applog.GlobalContext;
import com.ss.android.common.applog.UserInfo;
import com.umeng.commonsdk.UMConfigure;
import com.uuch.adlibrary.LApplication;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.Screen;

/**
 * Created by liqy on 2018/1/27.
 */

public class App extends LApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalContext.setContext(getApplicationContext());
        loadSo();
        initUmeng();

        DBHelper.initDB(this);

        Stetho.initializeWithDefaults(this);

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.mipmap.ic_launcher);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 处理悬浮窗口的点击事件


            }
        });

//        FloatWindow
//                .with(getApplicationContext())
//                .setView(imageView)
//                .setWidth(Screen.width,0.2f)
//                .setHeight(Screen.width,0.2f)
//                .setX(Screen.width,0.8f)
//                .setY(Screen.height,0.3f)
//                .setMoveType(MoveType.slide)
//                .setMoveStyle(500,new BounceInterpolator())
//                .setDesktopShow(true)
//
//                .build();
    }

    private void initUmeng() {
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "caa66eb05b47ca7615871522d618ea77");
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
    }


    /**
     * 加载抖音解密库
     */
    public void loadSo() {
        try {
            System.loadLibrary("userinfo");//抖音&火山
        } catch (Exception e) {
            Log.i("jw", "load so err:" + Log.getStackTraceString(e));
        }
        UserInfo.setAppId(2);
        int result = UserInfo.initUser("a3668f0afac72ca3f6c1697d29e0e1bb1fef4ab0285319b95ac39fa42c38d05f");
        Log.i("jw", "result:" + result);
    }

}
