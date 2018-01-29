package com.liqy.douyin.network;

import android.content.Context;

import com.liqy.douyin.utils.Utils;
import com.ss.android.common.applog.GlobalContext;
import com.ss.android.common.applog.UserInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liqy on 2018/1/27.
 */

public class GetParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        HttpUrl httpUrl = request.url();
        HttpUrl.Builder builder = httpUrl.newBuilder();
        Context context = GlobalContext.getContext();

        HashMap<String, String>
                paramsMap = new HashMap<String, String>();
        paramsMap.put("iid", Constants.IID);
        paramsMap.put("channel", Constants.CHANNEL);//下载渠道
        paramsMap.put("aid", Constants.AID);
        paramsMap.put("uuid", Constants.UUID); //设备唯一号 需要运行时权限
        paramsMap.put("openudid", Constants.OPEN_UDID); //更账户绑定
        paramsMap.put("app_name", Constants.APP_NAME); //应用名称
        paramsMap.put("version_code", Constants.V_CODE);//版本号
        paramsMap.put("version_name", Constants.V_NAME);//版本名称
        paramsMap.put("ssmix", "a");
        paramsMap.put("manifest_version_code", Constants.V_CODE);//版本号
        paramsMap.put("device_type", Utils.getDeviceName());//设备类型
        paramsMap.put("device_brand", Utils.getDeviceFactory());//手机品牌
        paramsMap.put("os_api", Utils.getOSSDK());//SDK 版本号
        paramsMap.put("os_version", Utils.getOSRelease());//手机系统版本号
        paramsMap.put("resolution", Utils.getDeviceWidth(context) + "*" + Utils.getDeviceHeight(context));//分辨率
        paramsMap.put("dpi", Utils.getDeviceDpi(context) + "");//屏幕密度
        paramsMap.put("device_id", Constants.DEVICE_ID);//设备ID
//		params.put("ac", NetworkUtil.getNetworkType(GlobalContext.getContext()).toLowerCase());
        paramsMap.put("ac", "wifi");//网络类型
        paramsMap.put("device_platform", "android");//平台
        paramsMap.put("update_version_code", "1592");//更新版本号
        paramsMap.put("app_type", "normal");//应用类型

        int time = (int) (System.currentTimeMillis() / 1000);

        String[] paramsAry = new String[paramsMap.size() * 2];
        int i = 0;
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            paramsAry[i] = entry.getKey();
            i++;
            paramsAry[i] = entry.getValue();
            i++;
        }

        // 处理可变参数
        Set<String> query = httpUrl.queryParameterNames();
        for (String name : query) {
            paramsMap.put(name, httpUrl.queryParameter(name));
        }

        // 添加时间戳
        paramsMap.put("retry_type", "no_retry");
        paramsMap.put("ts", "" + time);//时间戳

        StringBuilder paramsSb = new StringBuilder();

        for (String key : paramsMap.keySet()) {
            paramsSb.append(key + "=" + paramsMap.get(key) + "&");
        }

        String urlStr = paramsSb.toString();

        if (urlStr.endsWith("&")) {
            urlStr = urlStr.substring(0, urlStr.length() - 1);
        }

        String as_cp = UserInfo.getUserInfo(time, urlStr, paramsAry);

        String asStr = as_cp.substring(0, as_cp.length() / 2);
        String cpStr = as_cp.substring(as_cp.length() / 2, as_cp.length());

        String url = urlStr + "&as=" + asStr + "&cp=" + cpStr;
        //TODO 重写的URL
        Request newRequest = request.newBuilder()
                .url(builder.query(url).build())
                .build();
        return chain.proceed(newRequest);
    }

}
