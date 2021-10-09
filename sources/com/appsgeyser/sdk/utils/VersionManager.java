package com.appsgeyser.sdk.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import com.appsgeyser.sdk.configuration.PreferencesCoder;

public class VersionManager {
    public static int getPreviousVersion(Context context) {
        return new PreferencesCoder(context).getPrefInt("app_version_prefix", -1);
    }

    public static int getCurrentVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public static void updateVersion(Context context, int i) {
        new PreferencesCoder(context).savePrefInt("app_version_prefix", i);
    }
}
