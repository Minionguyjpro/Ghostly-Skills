package com.mopub.common.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import com.appnext.core.Ad;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection;
import java.io.File;
import java.net.SocketException;

public class DeviceUtils {
    private static final int MAX_DISK_CACHE_SIZE = 104857600;
    private static final int MAX_MEMORY_CACHE_SIZE = 31457280;
    private static final int MIN_DISK_CACHE_SIZE = 31457280;

    @Deprecated
    public enum IP {
        IPv4,
        IPv6
    }

    @Deprecated
    public static String getHashedUdid(Context context) {
        return null;
    }

    @Deprecated
    public static String getIpAddress(IP ip) throws SocketException {
        return null;
    }

    private DeviceUtils() {
    }

    public enum ForceOrientation {
        FORCE_PORTRAIT(Ad.ORIENTATION_PORTRAIT),
        FORCE_LANDSCAPE(Ad.ORIENTATION_LANDSCAPE),
        DEVICE_ORIENTATION("device"),
        UNDEFINED("");
        
        private final String mKey;

        private ForceOrientation(String str) {
            this.mKey = str;
        }

        public static ForceOrientation getForceOrientation(String str) {
            for (ForceOrientation forceOrientation : values()) {
                if (forceOrientation.mKey.equalsIgnoreCase(str)) {
                    return forceOrientation;
                }
            }
            return UNDEFINED;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null || !isPermissionGranted(context, "android.permission.INTERNET")) {
            return false;
        }
        if (!isPermissionGranted(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().isConnected();
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public static int memoryCacheSizeBytes(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        long memoryClass = (long) activityManager.getMemoryClass();
        try {
            if (Utils.bitMaskContainsFlag(context.getApplicationInfo().flags, ApplicationInfo.class.getDeclaredField("FLAG_LARGE_HEAP").getInt((Object) null))) {
                memoryClass = (long) ((Integer) new Reflection.MethodBuilder(activityManager, "getLargeMemoryClass").execute()).intValue();
            }
        } catch (Exception unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unable to reflectively determine large heap size.");
        }
        return (int) Math.min(31457280, (memoryClass / 8) * 1024 * 1024);
    }

    public static long diskCacheSizeBytes(File file, long j) {
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            j = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 50;
        } catch (IllegalArgumentException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unable to calculate 2% of available disk space, defaulting to minimum");
        }
        return Math.max(Math.min(j, 104857600), 31457280);
    }

    public static long diskCacheSizeBytes(File file) {
        return diskCacheSizeBytes(file, 31457280);
    }

    public static int getScreenOrientation(Activity activity) {
        return getScreenOrientationFromRotationAndOrientation(activity.getWindowManager().getDefaultDisplay().getRotation(), activity.getResources().getConfiguration().orientation);
    }

    static int getScreenOrientationFromRotationAndOrientation(int i, int i2) {
        if (1 == i2) {
            return (i == 1 || i == 2) ? 9 : 1;
        }
        if (2 == i2) {
            return (i == 2 || i == 3) ? 8 : 0;
        }
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unknown screen orientation. Defaulting to portrait.");
        return 9;
    }

    public static void lockOrientation(Activity activity, CreativeOrientation creativeOrientation) {
        if (Preconditions.NoThrow.checkNotNull(creativeOrientation) && Preconditions.NoThrow.checkNotNull(activity)) {
            int screenOrientationFromRotationAndOrientation = getScreenOrientationFromRotationAndOrientation(((WindowManager) activity.getSystemService("window")).getDefaultDisplay().getRotation(), activity.getResources().getConfiguration().orientation);
            int i = 8;
            if (CreativeOrientation.PORTRAIT == creativeOrientation) {
                i = 9 == screenOrientationFromRotationAndOrientation ? 9 : 1;
            } else if (CreativeOrientation.LANDSCAPE != creativeOrientation) {
                return;
            } else {
                if (8 != screenOrientationFromRotationAndOrientation) {
                    i = 0;
                }
            }
            activity.setRequestedOrientation(i);
        }
    }

    public static Point getDeviceDimensions(Context context) {
        Integer num;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Integer num2 = null;
        if (windowManager != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            Integer valueOf = Integer.valueOf(point.x);
            Integer valueOf2 = Integer.valueOf(point.y);
            num2 = valueOf;
            num = valueOf2;
        } else {
            num = null;
        }
        if (num2 == null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            num2 = Integer.valueOf(displayMetrics.widthPixels);
            num = Integer.valueOf(displayMetrics.heightPixels);
        }
        return new Point(num2.intValue(), num.intValue());
    }

    public static boolean isPermissionGranted(Context context, String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        try {
            if (ContextCompat.checkSelfPermission(context, str) == 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
