package com.tappx.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class w2 {
    private static SharedPreferences a(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void b(Context context, String str, String str2) {
        SharedPreferences.Editor edit = a(context).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static String a(Context context, String str, String str2) {
        return a(context).getString(str, str2).trim();
    }
}
