package com.appsgeyser.multiTabApp.utils;

import android.content.Context;
import android.content.pm.PackageManager;

public class VersionManager {
    public static int getPreviousVersion(Context context) {
        try {
            return context.getSharedPreferences("AppsgeyserPrefs", 0).getInt("app_version_prefix", -1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getCurrentVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
