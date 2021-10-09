package com.startapp.android.publish.adsCommon.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.core.Ad;
import com.google.android.gms.ads.AdRequest;
import com.startapp.android.publish.GeneratedConstants;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a.c;
import com.startapp.common.a.g;
import com.startapp.common.c.b;
import com.startapp.common.e;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: StartAppSDK */
public class i {

    /* renamed from: a  reason: collision with root package name */
    protected static int f181a;
    private static Map<Activity, Integer> b = new WeakHashMap();
    private static boolean c = false;

    /* compiled from: StartAppSDK */
    public interface a {
        void a();

        void a(String str);
    }

    public static String d() {
        return GeneratedConstants.INAPP_PACKAGING;
    }

    public static boolean a() {
        return new BigInteger(AdsConstants.i, 10).intValue() == 0;
    }

    public static String b() {
        g.a(3, "SDK version: [" + GeneratedConstants.INAPP_VERSION + "]");
        return GeneratedConstants.INAPP_VERSION;
    }

    public static String c() {
        g.a(3, "SDK Flavor: [" + GeneratedConstants.INAPP_FLAVOR + "]");
        return GeneratedConstants.INAPP_FLAVOR;
    }

    public static boolean a(long j) {
        String str = AdsConstants.i;
        if (str.equals("${flavor}") || (j & new BigInteger(str, 2).longValue()) != 0) {
            return true;
        }
        return false;
    }

    public static boolean e() {
        return a(2) || a(16) || a(32) || a(4);
    }

    public static String a(Double d) {
        if (d == null) {
            return null;
        }
        return String.format(Locale.US, "%.2f", new Object[]{d});
    }

    public static boolean a(Context context) {
        if (AdsConstants.OVERRIDE_HOST != null || AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
            return true;
        }
        if (c.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                f.a(context, d.EXCEPTION, "Util.isNetworkAvailable - system service failed", e.getMessage(), "");
            }
        }
        return false;
    }

    public static void a(SharedPreferences.Editor editor) {
        c.a(editor);
    }

    public static String a(String str, String str2, String str3) {
        int indexOf;
        int indexOf2;
        if (str == null || str2 == null || str3 == null || (indexOf = str.indexOf(str2)) == -1 || (indexOf2 = str.indexOf(str3, str2.length() + indexOf)) == -1) {
            return null;
        }
        return str.substring(indexOf + str2.length(), indexOf2);
    }

    public static String b(Context context) {
        if (context.getResources().getConfiguration().orientation == 2) {
            return Ad.ORIENTATION_LANDSCAPE;
        }
        return context.getResources().getConfiguration().orientation == 1 ? Ad.ORIENTATION_PORTRAIT : "undefined";
    }

    public static int a(Activity activity, int i, boolean z) {
        if (z) {
            if (!b.containsKey(activity)) {
                b.put(activity, Integer.valueOf(activity.getRequestedOrientation()));
            }
            if (i == activity.getResources().getConfiguration().orientation) {
                return c.a(activity, i, false);
            }
            return c.a(activity, i, true);
        } else if (!b.containsKey(activity)) {
            return -1;
        } else {
            int intValue = b.get(activity).intValue();
            c.a(activity, intValue);
            b.remove(activity);
            return intValue;
        }
    }

    public static void a(Activity activity, boolean z) {
        a(activity, activity.getResources().getConfiguration().orientation, z);
    }

    private static List<Field> a(Class cls) {
        return a((List<Field>) new LinkedList(), (Class<?>) cls);
    }

    private static List<Field> a(List<Field> list, Class<?> cls) {
        list.addAll(Arrays.asList(cls.getDeclaredFields()));
        if (cls.getSuperclass() != null) {
            a(list, (Class<?>) cls.getSuperclass());
        }
        return list;
    }

    public static <T> boolean a(T t, T t2) {
        Object obj;
        boolean z = false;
        try {
            for (Field next : a((Class) t2.getClass())) {
                int modifiers = next.getModifiers();
                if (!Modifier.isTransient(modifiers)) {
                    if (!Modifier.isStatic(modifiers)) {
                        next.setAccessible(true);
                        if (next.get(t) == null && (obj = next.get(t2)) != null) {
                            next.set(t, obj);
                            z = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            g.a(3, "Util.mergeDefaultValues failed: " + e.getMessage());
        }
        return z;
    }

    public static void a(Context context, AdPreferences adPreferences) {
        String a2 = k.a(context, "shared_prefs_devId", (String) null);
        String a3 = k.a(context, "shared_prefs_appId", (String) null);
        if (adPreferences.getPublisherId() == null) {
            adPreferences.setPublisherId(a2);
        }
        if (adPreferences.getProductId() == null) {
            adPreferences.setProductId(a3);
        }
        if (adPreferences.getProductId() == null && !c) {
            c = true;
            Log.e("StartApp", "Integration Error - App ID is missing");
        }
    }

    public static void a(Context context, String str, String str2) {
        if (str != null) {
            k.b(context, "shared_prefs_devId", str.trim());
        } else {
            k.b(context, "shared_prefs_devId", (String) null);
        }
        k.b(context, "shared_prefs_appId", str2.trim());
    }

    public static void a(Context context, String str, final a aVar) {
        if ("true".equals(a(str, "@doNotRender@", "@doNotRender@"))) {
            aVar.a();
            return;
        }
        try {
            final WebView webView = new WebView(context);
            final Handler handler = new Handler();
            if (AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
                f181a = 25000;
                webView.getSettings().setBlockNetworkImage(false);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
            } else {
                f181a = 0;
            }
            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView webView, String str) {
                    super.onPageFinished(webView, str);
                    g.a("StartAppWall.Util", 4, "onPageFinished url=[" + str + "]");
                    handler.removeCallbacksAndMessages((Object) null);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            webView.destroy();
                            g.a("StartAppWall.Util", 4, "webview destroyed");
                            aVar.a();
                        }
                    }, (long) i.f181a);
                }

                public void onReceivedError(final WebView webView, int i, final String str, String str2) {
                    super.onReceivedError(webView, i, str, str2);
                    g.a("StartAppWall.Util", 6, "onReceivedError failingUrl=[" + str2 + "], description=[" + str + "]");
                    handler.removeCallbacksAndMessages((Object) null);
                    handler.post(new Runnable() {
                        public void run() {
                            webView.destroy();
                            aVar.a(str);
                        }
                    });
                }

                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    g.a("StartAppWall.Util", 4, "shouldOverrideUrlLoading url=[" + str + "]");
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            });
            a(context, webView, str);
            g.a("StartAppWall.Util", 4, "Data loaded to webview");
            handler.postDelayed(new Runnable() {
                public void run() {
                    webView.destroy();
                    aVar.a();
                    g.a("StartAppWall.Util", 4, "webview destroyed pos 2");
                }
            }, 25000);
        } catch (Exception e) {
            f.a(context, d.EXCEPTION, "Util.loadHtmlToCacheWebView - webview failed", e.getMessage(), "");
            aVar.a("WebView instantiation Error");
        }
    }

    public static void a(Context context, WebView webView, String str) {
        try {
            webView.loadDataWithBaseURL(MetaData.getInstance().getHostForWebview(), str, "text/html", "utf-8", (String) null);
        } catch (Exception e) {
            if (context != null) {
                f.a(context, d.EXCEPTION, "Util.loadDataToWebview failed", e.getMessage(), "");
            }
            g.a(6, "StartAppWall.UtilError while encoding html");
        }
    }

    public static String c(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null || resolveActivity.activityInfo == null) {
                return "";
            }
            String str = resolveActivity.activityInfo.packageName;
            return str != null ? str.toLowerCase() : str;
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean a(AdPreferences adPreferences, String str) {
        Object a2 = a((Class) adPreferences.getClass(), str, (Object) adPreferences);
        if (a2 == null || !(a2 instanceof Boolean)) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static String b(AdPreferences adPreferences, String str) {
        Object a2 = a((Class) adPreferences.getClass(), str, (Object) adPreferences);
        if (a2 == null || !(a2 instanceof String)) {
            return null;
        }
        return (String) a2;
    }

    public static void a(AdPreferences adPreferences, String str, boolean z) {
        a((Class) adPreferences.getClass(), str, (Object) adPreferences, (Object) Boolean.valueOf(z));
    }

    public static Object a(Class cls, String str, Object obj) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (NoSuchFieldException e) {
            g.a("StartAppWall.Util", 6, e.getLocalizedMessage());
            return null;
        } catch (IllegalArgumentException e2) {
            g.a("StartAppWall.Util", 6, e2.getLocalizedMessage());
            return null;
        } catch (IllegalAccessException e3) {
            g.a("StartAppWall.Util", 6, e3.getLocalizedMessage());
            return null;
        }
    }

    public static void a(Class cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public static String d(Context context) {
        return context.getPackageManager().getInstallerPackageName(context.getPackageName());
    }

    public static void a(WebView webView, String str, Object... objArr) {
        a(webView, true, str, objArr);
    }

    public static void a(WebView webView, boolean z, String str, Object... objArr) {
        if (webView != null) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("(");
                if (objArr != null) {
                    for (int i = 0; i < objArr.length; i++) {
                        if (!z || !(objArr[i] instanceof String)) {
                            sb.append(objArr[i]);
                        } else {
                            sb.append("\"");
                            sb.append(objArr[i]);
                            sb.append("\"");
                        }
                        if (i < objArr.length - 1) {
                            sb.append(",");
                        }
                    }
                }
                sb.append(")");
                g.a("StartAppWall.Util", 3, "runJavascript: " + sb.toString());
                webView.loadUrl("javascript:" + sb.toString());
            } catch (Exception e) {
                g.a("StartAppWall.Util", 6, "runJavascript Exception: " + e.getMessage());
            }
        }
    }

    public static Class<?> a(Context context, Class<? extends Activity> cls, Class<? extends Activity> cls2) {
        if (a(context, cls) || !a(context, cls2)) {
            return cls;
        }
        Log.w("StartAppWall.Util", "Expected activity " + cls.getName() + " is missing from AndroidManifest.xml");
        return cls2;
    }

    public static boolean a(Context context, Class<? extends Activity> cls) {
        try {
            for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities) {
                if (activityInfo.name.equals(cls.getName())) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static boolean e(Context context) {
        for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (next.importance == 100 && next.processName.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean f(Context context) {
        boolean z = false;
        try {
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
            boolean z2 = false;
            int i = 0;
            while (!z2) {
                try {
                    if (i >= activityInfoArr.length) {
                        return z2;
                    }
                    int i2 = i + 1;
                    ActivityInfo activityInfo = activityInfoArr[i];
                    if (activityInfo.name.equals("com.startapp.android.publish.AppWallActivity") || activityInfo.name.equals("com.startapp.android.publish.adsCommon.OverlayActivity") || activityInfo.name.equals("com.startapp.android.publish.FullScreenActivity")) {
                        z2 = (activityInfo.flags & AdRequest.MAX_CONTENT_URL_LENGTH) == 0;
                    }
                    i = i2;
                } catch (PackageManager.NameNotFoundException | Exception unused) {
                    z = z2;
                    return z;
                }
            }
            return z2;
        } catch (PackageManager.NameNotFoundException | Exception unused2) {
            return z;
        }
    }

    public String a(String str, Context context) {
        try {
            return new c().a(str, context);
        } catch (Exception e) {
            f.a(context, d.EXCEPTION, "Util.getApkHash - system service failed", e.getMessage(), "");
            return null;
        }
    }

    public static long a(File file, long j) {
        return c.a(file, j);
    }

    public static String a(Context context, int i) {
        try {
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            decodeResource.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        } catch (Exception unused) {
            return "";
        }
    }

    public static <T> T a(String str, Class<T> cls) {
        T a2 = b.a(str, cls);
        if (a2 != null) {
            return a2;
        }
        throw new e();
    }

    public static void a(Object obj, long j) {
        new Handler(Looper.getMainLooper()).postAtTime((Runnable) null, obj, SystemClock.uptimeMillis() + j);
    }
}
