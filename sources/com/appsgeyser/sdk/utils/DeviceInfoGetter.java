package com.appsgeyser.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public abstract class DeviceInfoGetter {
    public static String getDeviceInfo(Context context) {
        try {
            return "&dpi=" + URLEncoder.encode(String.valueOf(getScreenSizeDpi(context)), "utf-8") + "&" + "screenresolution" + "=" + URLEncoder.encode(getScreenResolution(context), "utf-8") + "&" + "androidversion" + "=" + URLEncoder.encode(String.valueOf(getAndroidOsVersionInt()), "utf-8") + "&" + "istablet" + "=" + URLEncoder.encode(String.valueOf(isTablet(context)), "utf-8") + "&" + "manufacturer" + "=" + URLEncoder.encode(getManufacturer(), "utf-8") + "&" + "devicename" + "=" + URLEncoder.encode(getDeviceName(), "utf-8") + "&" + "connectiontype" + "=" + URLEncoder.encode(getConnectType(context), "utf-8") + "&" + "operator" + "=" + URLEncoder.encode(getOperatorName(context), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static HashMap<String, String> getDeviceInfoMap(Context context) {
        HashMap<String, String> hashMap = new HashMap<>(9);
        try {
            hashMap.put("dpi", URLEncoder.encode(String.valueOf(getScreenSizeDpi(context)), "utf-8"));
            hashMap.put("screenresolution", URLEncoder.encode(getScreenResolution(context), "utf-8"));
            hashMap.put("androidversion", URLEncoder.encode(String.valueOf(getAndroidOsVersionInt()), "utf-8"));
            hashMap.put("istablet", URLEncoder.encode(String.valueOf(isTablet(context)), "utf-8"));
            hashMap.put("manufacturer", URLEncoder.encode(getManufacturer(), "utf-8"));
            hashMap.put("devicename", URLEncoder.encode(getDeviceName(), "utf-8"));
            hashMap.put("connectiontype", URLEncoder.encode(getConnectType(context), "utf-8"));
            hashMap.put("operator", URLEncoder.encode(getOperatorName(context), "utf-8"));
            return hashMap;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getScreenResolution(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return String.format("%sx%s", new Object[]{Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels)});
    }

    private static int getScreenSizeDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    private static boolean isTablet(Context context) {
        boolean z = (context.getResources().getConfiguration().screenLayout & 15) == 4;
        boolean z2 = (context.getResources().getConfiguration().screenLayout & 15) == 3;
        if (z || z2) {
            return true;
        }
        return false;
    }

    private static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    private static String getDeviceName() {
        return Build.DEVICE;
    }

    private static int getAndroidOsVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    private static String getConnectType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                return "wifi";
            }
            if (type == 0) {
                return activeNetworkInfo.getSubtypeName();
            }
        }
        return "unknowntype";
    }

    private static String getOperatorName(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
    }
}
