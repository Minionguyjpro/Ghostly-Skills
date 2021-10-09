package com.appsgeyser.sdk;

import android.util.Log;

public class Logger {
    public static void DebugLog(String str) {
        Log.d("*** AppsgeyserSDK Debug", str);
    }

    static void ErrorLog(String str) {
        Log.e("AppsgeyserSDK", str);
    }
}
