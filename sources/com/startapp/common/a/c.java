package com.startapp.common.a;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Paint;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.webkit.WebView;
import com.startapp.common.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: StartAppSDK */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static Object f338a;

    /* compiled from: StartAppSDK */
    public interface a {
        void a(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);
    }

    public static void a(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT < 9) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public static void a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 9) {
            a(activity, 7);
        } else {
            a(activity, 1);
        }
    }

    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 9) {
            a(activity, 6);
        } else {
            a(activity, 0);
        }
    }

    public static int a(int i, int i2, boolean z) {
        if (i != 1) {
            if (i != 2) {
                return 1;
            }
            if (Build.VERSION.SDK_INT > 8 && !z && i2 != 0 && i2 != 1) {
                return 8;
            }
            return 0;
        } else if (Build.VERSION.SDK_INT > 8 && !z && (i2 == 1 || i2 == 2)) {
            return 9;
        } else {
            return 1;
        }
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 12;
    }

    public static void a(View view, float f) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setAlpha(f);
        }
    }

    public static void a(View view, long j) {
        view.animate().alpha(1.0f).setDuration(j).setListener((Animator.AnimatorListener) null);
    }

    public static void a(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT >= 16) {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }

    public static boolean a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                if (Build.VERSION.SDK_INT < 21) {
                    if (Settings.Global.getInt(context.getContentResolver(), "install_non_market_apps") != 1) {
                        return false;
                    }
                    return true;
                }
            }
            if (Settings.Secure.getInt(context.getContentResolver(), "install_non_market_apps") != 1) {
                return false;
            }
            return true;
        } catch (Settings.SettingNotFoundException unused) {
            return false;
        }
    }

    public static void a(WebView webView) {
        if (Build.VERSION.SDK_INT >= 17) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
    }

    public static int a(Activity activity, int i, boolean z) {
        int a2 = a(i, activity.getWindowManager().getDefaultDisplay().getRotation(), z);
        a(activity, a2);
        return a2;
    }

    public static void a(Activity activity, int i) {
        try {
            activity.setRequestedOrientation(i);
        } catch (Exception e) {
            g.a(6, "Error to setRequestedOrientation ", (Throwable) e);
        }
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static void b(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.onPause();
            return;
        }
        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(webView, (Object[]) null);
        } catch (Exception unused) {
            g.a(6, "Error while calling webView.onPause()");
        }
    }

    public static void c(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.onResume();
            return;
        }
        try {
            Class.forName("android.webkit.WebView").getMethod("onResume", (Class[]) null).invoke(webView, (Object[]) null);
        } catch (Exception unused) {
            g.a(6, "Error while calling webView.onResume()");
        }
    }

    public static void a(WebView webView, Paint paint) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.setLayerType(1, paint);
        }
    }

    public static void a(View view, final a aVar) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    aVar.a(view, i, i2, i3, i4, i5, i6, i7, i8);
                }
            });
        }
    }

    public static Long a(ActivityManager.MemoryInfo memoryInfo) {
        if (Build.VERSION.SDK_INT >= 16) {
            return Long.valueOf(memoryInfo.totalMem);
        }
        return null;
    }

    public static Set<String> b(Context context) {
        HashSet hashSet = new HashSet();
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
                for (InputMethodInfo enabledInputMethodSubtypeList : inputMethodManager.getEnabledInputMethodList()) {
                    for (InputMethodSubtype next : inputMethodManager.getEnabledInputMethodSubtypeList(enabledInputMethodSubtypeList, true)) {
                        if (next.getMode().equals("keyboard")) {
                            hashSet.add(next.getLocale());
                        }
                    }
                }
            } catch (Exception e) {
                g.a("ApiUtil", 6, "Failed to retreive keyboard input langs: " + e.getLocalizedMessage());
            }
        }
        return hashSet;
    }

    public static boolean a(View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 11 && 1 != view.getLayerType() && z) {
            return view.isHardwareAccelerated();
        }
        return false;
    }

    public static long a(File file, long j) {
        long j2;
        long j3;
        if (file != null) {
            try {
                if (file.isDirectory()) {
                    if (Build.VERSION.SDK_INT >= 9) {
                        return file.getFreeSpace();
                    }
                    StatFs statFs = new StatFs(file.getPath());
                    if (Build.VERSION.SDK_INT >= 18) {
                        j3 = statFs.getBlockSizeLong();
                        j2 = statFs.getFreeBlocksLong();
                    } else {
                        j3 = (long) statFs.getBlockSize();
                        j2 = (long) statFs.getFreeBlocks();
                    }
                    return j3 * j2;
                }
            } catch (Exception e) {
                g.a("ApiUtil", 6, "Failed calculating free space with error: " + e.getMessage());
                return j;
            }
        }
        g.a("ApiUtil", 6, "Invalid filesDir argument - null or not a directory");
        return j;
    }

    public static boolean c(Context context) {
        if (Build.VERSION.SDK_INT >= 17) {
            if (Settings.Global.getInt(context.getContentResolver(), "auto_time", 0) > 0) {
                return true;
            }
        } else if (Settings.System.getInt(context.getContentResolver(), "auto_time", 0) > 0) {
            return true;
        }
        return false;
    }

    public static boolean a(Context context, String str) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                if (context.checkSelfPermission(str) == 0) {
                    return true;
                }
                return false;
            } else if (context.checkCallingOrSelfPermission(str) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("Exception while checking permission: ");
            sb.append(th);
            g.a(6, sb.toString() != null ? th.getMessage() : "");
            return false;
        }
    }

    public static List<ScanResult> a(Context context, WifiManager wifiManager) {
        if (!(context == null || wifiManager == null)) {
            boolean z = true;
            if (Build.VERSION.SDK_INT >= 23 && !a(context, "android.permission.ACCESS_FINE_LOCATION") && !a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                z = false;
            }
            if (z) {
                try {
                    return wifiManager.getScanResults();
                } catch (Exception e) {
                    g.a("ApiUtil", 6, "Failed to retreive WIFI scan results: " + e.getLocalizedMessage());
                    return null;
                }
            } else {
                g.a("ApiUtil", 3, "Unable to get WIFI scan results: API level >= 23 but no location permission granted");
            }
        }
        return null;
    }

    public static List<CellInfo> a(Context context, TelephonyManager telephonyManager) {
        if (context == null || telephonyManager == null || ((!a(context, "android.permission.ACCESS_FINE_LOCATION") && !a(context, "android.permission.ACCESS_COARSE_LOCATION")) || Build.VERSION.SDK_INT < 17)) {
            return null;
        }
        return telephonyManager.getAllCellInfo();
    }

    public static int a(int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            return i;
        }
        if (i == 16) {
            return 0;
        }
        if (i == 17) {
            return 1;
        }
        if (i == 20) {
            return 9;
        }
        if (i == 21) {
            return 11;
        }
        if (i != 8388611) {
            if (i == 8388613 && Build.VERSION.SDK_INT < 14) {
                return 5;
            }
            return i;
        } else if (Build.VERSION.SDK_INT < 14) {
            return 3;
        } else {
            return i;
        }
    }

    public static long a(ScanResult scanResult) {
        if (Build.VERSION.SDK_INT >= 17) {
            return (System.currentTimeMillis() - SystemClock.elapsedRealtime()) + (scanResult.timestamp / 1000);
        }
        return 0;
    }

    public static CharSequence b(ScanResult scanResult) {
        return Build.VERSION.SDK_INT >= 23 ? scanResult.venueName : "";
    }

    public static String b(Context context, TelephonyManager telephonyManager) {
        List<CellInfo> allCellInfo;
        if (Build.VERSION.SDK_INT >= 17 && ((a(context, "android.permission.ACCESS_FINE_LOCATION") || a(context, "android.permission.ACCESS_COARSE_LOCATION")) && (allCellInfo = telephonyManager.getAllCellInfo()) != null)) {
            for (CellInfo next : allCellInfo) {
                if (next.isRegistered()) {
                    try {
                        Object invoke = Class.forName(next.getClass().getName()).getMethod("getCellSignalStrength", (Class[]) null).invoke(next, (Object[]) null);
                        return Integer.toString(Integer.parseInt(Class.forName(invoke.getClass().getName()).getMethod("getTimingAdvance", (Class[]) null).invoke(invoke, (Object[]) null).toString()));
                    } catch (Exception unused) {
                        g.a(6, "Error while calling ApiUtil.getCellTimingAdv()");
                    }
                }
            }
        }
        return null;
    }

    public static int d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return 0;
        }
    }

    public static String e(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return null;
        }
    }

    public String a(String str, Context context) {
        String str2;
        try {
            str2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).sourceDir;
        } catch (PackageManager.NameNotFoundException unused) {
            str2 = null;
        }
        if (str2 != null) {
            return a((InputStream) new FileInputStream(str2), str);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        if (r6 != null) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (r6 == null) goto L_0x004a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.io.InputStream r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x0046, all -> 0x003f }
            java.security.MessageDigest r7 = java.security.MessageDigest.getInstance(r7)     // Catch:{ Exception -> 0x0046, all -> 0x003f }
        L_0x000d:
            int r2 = r6.read(r1)     // Catch:{ Exception -> 0x0046, all -> 0x003f }
            r3 = -1
            r4 = 0
            if (r2 == r3) goto L_0x001b
            if (r2 <= 0) goto L_0x000d
            r7.update(r1, r4, r2)     // Catch:{ Exception -> 0x0046, all -> 0x003f }
            goto L_0x000d
        L_0x001b:
            byte[] r7 = r7.digest()     // Catch:{ Exception -> 0x0046, all -> 0x003f }
        L_0x001f:
            int r1 = r7.length     // Catch:{ Exception -> 0x0046, all -> 0x003f }
            if (r4 >= r1) goto L_0x0039
            byte r1 = r7[r4]     // Catch:{ Exception -> 0x0046, all -> 0x003f }
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 + 256
            r2 = 16
            java.lang.String r1 = java.lang.Integer.toString(r1, r2)     // Catch:{ Exception -> 0x0046, all -> 0x003f }
            r2 = 1
            java.lang.String r1 = r1.substring(r2)     // Catch:{ Exception -> 0x0046, all -> 0x003f }
            r0.append(r1)     // Catch:{ Exception -> 0x0046, all -> 0x003f }
            int r4 = r4 + 1
            goto L_0x001f
        L_0x0039:
            if (r6 == 0) goto L_0x004a
        L_0x003b:
            r6.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x004a
        L_0x003f:
            r7 = move-exception
            if (r6 == 0) goto L_0x0045
            r6.close()     // Catch:{ IOException -> 0x0045 }
        L_0x0045:
            throw r7
        L_0x0046:
            if (r6 == 0) goto L_0x004a
            goto L_0x003b
        L_0x004a:
            java.lang.String r6 = r0.toString()
            java.lang.String r6 = r6.toUpperCase()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.common.a.c.a(java.io.InputStream, java.lang.String):java.lang.String");
    }

    public static int f(Context context) {
        g.a("ApiUtil", 3, "getPackageList entered");
        int i = 0;
        try {
            for (PackageInfo next : a(context.getPackageManager())) {
                if (!a(next) || next.packageName.equals(Constants.f326a)) {
                    i++;
                }
            }
        } catch (Exception e) {
            g.a("ApiUtil", 6, "Could not complete getPackagesList", e);
        }
        return i;
    }

    public static boolean a(PackageInfo packageInfo) {
        return ((packageInfo.applicationInfo.flags & 1) == 0 && (packageInfo.applicationInfo.flags & 128) == 0) ? false : true;
    }

    public static boolean a(Context context, String str, int i) {
        try {
            if (context.getPackageManager().getPackageInfo(str, 128).versionCode >= i) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return false;
        }
    }

    public static List<PackageInfo> a(PackageManager packageManager) {
        return (List) packageManager.getClass().getMethod("getInstalledPackages", new Class[]{Integer.TYPE}).invoke(packageManager, new Object[]{8192});
    }

    public static boolean g(Context context) {
        int i;
        try {
            if (Build.VERSION.SDK_INT < 17) {
                i = Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0);
            } else {
                i = Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0);
            }
            if (i != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            g.a("ApiUtil", 6, "getUsbDebug", e);
            return false;
        }
    }

    public static boolean h(Context context) {
        try {
            return com.startapp.android.b.c.a(context);
        } catch (Throwable th) {
            g.a("ApiUtil", 6, "isRooted", th);
            return false;
        }
    }

    public static boolean i(Context context) {
        try {
            return com.startapp.android.a.a.a(context);
        } catch (Throwable th) {
            g.a("ApiUtil", 6, "isSimulator", th);
            return false;
        }
    }

    public static String j(Context context) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            if (signatureArr == null || signatureArr.length <= 0) {
                g.a("ApiUtil", 6, "getApkSignature: empty signatures");
                return null;
            }
            if (signatureArr.length == 1) {
                return signatureArr[0].toCharsString();
            }
            Arrays.sort(signatureArr, new Comparator<Signature>() {
                /* renamed from: a */
                public int compare(Signature signature, Signature signature2) {
                    return signature.toCharsString().compareTo(signature2.toCharsString());
                }
            });
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < signatureArr.length; i++) {
                sb.append(signatureArr[i].toCharsString());
                if (i < signatureArr.length - 1) {
                    sb.append(';');
                }
            }
            return sb.toString();
        } catch (Exception e) {
            g.a("ApiUtil", 6, "getApkSignature", e);
            return null;
        }
    }
}
