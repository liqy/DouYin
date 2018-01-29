package com.liqy.douyin;

import android.app.Application;
import android.util.Log;

import com.ss.android.common.applog.GlobalContext;
import com.ss.android.common.applog.UserInfo;
import com.umeng.commonsdk.UMConfigure;

/**
 * Created by liqy on 2018/1/27.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalContext.setContext(getApplicationContext());
        loadSo();
        initUmeng();
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
