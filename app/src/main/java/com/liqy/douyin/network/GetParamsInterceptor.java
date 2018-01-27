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
        paramsMap.put("iid", "24446056580");
        paramsMap.put("channel", "xiaomi");
        paramsMap.put("aid", "1128");
        paramsMap.put("uuid", Utils.getDeviceUUID(context));
        paramsMap.put("openudid", "b39d9675ee6af5b2");
        paramsMap.put("app_name", "aweme");
        paramsMap.put("version_code", "159");
        paramsMap.put("version_name", "1.5.9");
        paramsMap.put("ssmix", "a");
        paramsMap.put("manifest_version_code", "159");
        paramsMap.put("device_type", Utils.getDeviceName());
        paramsMap.put("device_brand", Utils.getDeviceFactory());
        paramsMap.put("os_api", Utils.getOSSDK());
        paramsMap.put("os_version", Utils.getOSRelease());
        paramsMap.put("resolution", Utils.getDeviceWidth(context) + "*" + Utils.getDeviceHeight(context));
        paramsMap.put("dpi", Utils.getDeviceDpi(context) + "");
        paramsMap.put("device_id", "42386607829");
//		params.put("ac", NetworkUtil.getNetworkType(GlobalContext.getContext()).toLowerCase());
        paramsMap.put("ac", "wifi");
        paramsMap.put("device_platform", "android");
        paramsMap.put("update_version_code", "1592");
        paramsMap.put("app_type", "normal");

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
