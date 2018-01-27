package com.liqy.douyin;

import android.app.Application;
import android.util.Log;

import com.ss.android.common.applog.GlobalContext;
import com.ss.android.common.applog.UserInfo;

/**
 * Created by liqy on 2018/1/27.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalContext.setContext(getApplicationContext());
        loadSo();
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
