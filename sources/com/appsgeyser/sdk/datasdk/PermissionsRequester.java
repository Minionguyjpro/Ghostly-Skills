package com.appsgeyser.sdk.datasdk;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import java.util.HashSet;
import java.util.Set;

class PermissionsRequester {
    private static final String[] oneAudiencePermissions = {"android.permission.GET_ACCOUNTS"};
    private static final String[] predicioPermissions = {"android.permission.ACCESS_FINE_LOCATION"};

    static boolean isPermissionsRequired(ConfigPhp configPhp, Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        HashSet hashSet = new HashSet();
        PackageManager packageManager = context.getPackageManager();
        for (String next : getPermissionRequiredFromConfig(configPhp)) {
            if (packageManager.checkPermission(next, context.getPackageName()) != 0) {
                hashSet.add(next);
            }
        }
        if (hashSet.size() > 0) {
            return true;
        }
        return false;
    }

    static void requestAllActiveByDefaultPermissions(Activity activity, ConfigPhp configPhp, int i) {
        String[] needRequestPermissions = getNeedRequestPermissions(activity, new HashSet());
        if (needRequestPermissions != null) {
            activity.requestPermissions(needRequestPermissions, i);
        } else {
            activity.onRequestPermissionsResult(78, new String[0], new int[0]);
        }
    }

    static void requestAllActivePermissions(Activity activity, ConfigPhp configPhp, int i) {
        String[] needRequestPermissions = getNeedRequestPermissions(activity, getPermissionRequiredFromConfig(configPhp));
        if (needRequestPermissions != null) {
            activity.requestPermissions(needRequestPermissions, i);
        } else {
            activity.onRequestPermissionsResult(78, new String[0], new int[0]);
        }
    }

    private static String[] getNeedRequestPermissions(Activity activity, Set<String> set) {
        HashSet hashSet = new HashSet();
        for (String next : set) {
            if (ContextCompat.checkSelfPermission(activity, next) != 0) {
                hashSet.add(next);
            }
        }
        if (hashSet.size() > 0) {
            return (String[]) hashSet.toArray(new String[hashSet.size()]);
        }
        return null;
    }

    private static Set<String> getPermissionRequiredFromConfig(ConfigPhp configPhp) {
        return new HashSet();
    }
}
