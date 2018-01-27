package com.liqy.douyin.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String filterStrBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String formatNumber(long val) {
        if (val < 10000) {
            return val + "";
        }
        DecimalFormat df = new DecimalFormat("######0.0");
        double d = val / 10000.0;
        return df.format(d) + "万";
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatTimeStr(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String dateString = formatter.format(time);
        return dateString;
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatTimeDetailStr(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        String dateString = formatter.format(time);
        return dateString;
    }

    public static String getWifiMac(Context ctx) {
        WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String str = info.getMacAddress();
        if (str == null) {
            str = "";
        }
        return str;
    }

    @SuppressWarnings("deprecation")
    public static String getOSSDK() {
        return android.os.Build.VERSION.SDK;
    }

    public static String getOSRelease() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getDeviceName() {
        return android.os.Build.MODEL;
    }

    public static String getDeviceFactory() {
        return android.os.Build.BRAND;
    }

    public static int getDeviceWidth(Context act) {
        Resources resources = act.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getDeviceHeight(Context act) {
        Resources resources = act.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int getDeviceDpi(Context act) {
        Resources resources = act.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.densityDpi;
    }

    public static float getDeviceDensity(Activity act) {
        DisplayMetrics metric = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.density;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static String getDeviceId() {
        return android.os.Build.SERIAL;
    }

    public static String getDeviceUUID(Context ctx) {
        try {
            return UUID.nameUUIDFromBytes(getDeviceAndroidId(ctx).getBytes("utf8")).toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getDeviceAndroidId(Context ctx) {
        try {
            return Secure.getString(ctx.getContentResolver(), Secure.ANDROID_ID);
        } catch (Exception e) {
            return "";
        }
    }

}
