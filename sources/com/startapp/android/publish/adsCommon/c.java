package com.startapp.android.publish.adsCommon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.base.b.d;
import com.facebook.ads.AdError;
import com.mopub.common.AdType;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.activities.OverlayActivity;
import com.startapp.android.publish.adsCommon.d.b;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.Constants;
import com.startapp.common.a.g;
import com.startapp.common.e;
import com.startapp.common.g;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static ProgressDialog f220a;

    public static Ad.AdType a(AdPreferences adPreferences, String str) {
        Object a2 = i.a((Class) adPreferences.getClass(), str, (Object) adPreferences);
        if (a2 == null || !(a2 instanceof Ad.AdType)) {
            return null;
        }
        return (Ad.AdType) a2;
    }

    public static void a(AdPreferences adPreferences, String str, Ad.AdType adType) {
        i.a((Class) adPreferences.getClass(), str, (Object) adPreferences, (Object) adType);
    }

    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.CharSequence] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r3, java.lang.String r4) {
        /*
            android.content.res.Resources r0 = r3.getResources()     // Catch:{ NotFoundException -> 0x000f }
            android.content.pm.ApplicationInfo r1 = r3.getApplicationInfo()     // Catch:{ NotFoundException -> 0x000f }
            int r1 = r1.labelRes     // Catch:{ NotFoundException -> 0x000f }
            java.lang.String r3 = r0.getString(r1)     // Catch:{ NotFoundException -> 0x000f }
            return r3
        L_0x000f:
            android.content.pm.PackageManager r0 = r3.getPackageManager()
            r1 = 0
            android.content.pm.ApplicationInfo r3 = r3.getApplicationInfo()     // Catch:{ NameNotFoundException -> 0x0020 }
            java.lang.String r3 = r3.packageName     // Catch:{ NameNotFoundException -> 0x0020 }
            r2 = 0
            android.content.pm.ApplicationInfo r1 = r0.getApplicationInfo(r3, r2)     // Catch:{ NameNotFoundException -> 0x0020 }
            goto L_0x0021
        L_0x0020:
        L_0x0021:
            if (r1 == 0) goto L_0x0027
            java.lang.CharSequence r4 = r0.getApplicationLabel(r1)
        L_0x0027:
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r4 = (java.lang.String) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.c.a(android.content.Context, java.lang.String):java.lang.String");
    }

    public static boolean a(Activity activity) {
        boolean z = activity.getTheme().obtainStyledAttributes(new int[]{16843277}).getBoolean(0, false);
        if ((activity.getWindow().getAttributes().flags & d.fb) != 0) {
            return true;
        }
        return z;
    }

    public static int a(String str) {
        String[] split = str.split("&");
        return Integer.parseInt(split[split.length - 1].split("=")[1]);
    }

    public static void a(Context context, String[] strArr, String str, String str2) {
        a(context, strArr, str, 0, str2);
    }

    public static void a(Context context, String[] strArr, String str, int i, String str2) {
        b nonImpressionReason = new b(str).setOffset(i).setNonImpressionReason(str2);
        if (strArr == null || strArr.length == 0) {
            f.a(context, com.startapp.android.publish.adsCommon.f.d.NON_IMPRESSION_NO_DPARAM, str2, nonImpressionReason.getProfileId(), "");
            return;
        }
        for (String str3 : strArr) {
            if (str3 != null && !str3.equalsIgnoreCase("")) {
                g.a(3, "Sending Impression: [" + str3 + "]");
                a(context, str3, nonImpressionReason, false);
            }
        }
    }

    public static void a(Context context, String str, b bVar) {
        if (str != null && !str.equalsIgnoreCase("")) {
            g.a(3, "Sending Impression: [" + str + "]");
            if (bVar != null) {
                bVar.setLocation(context);
            }
            a(context, str, bVar, true);
        }
    }

    public static void a(Context context, String[] strArr, b bVar) {
        if (strArr != null) {
            for (String a2 : strArr) {
                a(context, a2, bVar);
            }
        }
    }

    public static List<String> a(List<String> list, String str, String str2) {
        String str3;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 5;
            List<String> subList = list.subList(i, Math.min(i2, list.size()));
            StringBuilder sb = new StringBuilder();
            sb.append(AdsConstants.f);
            sb.append("?");
            sb.append(TextUtils.join("&", subList));
            sb.append("&isShown=");
            sb.append(str);
            if (str2 != null) {
                str3 = "&appPresence=" + str2;
            } else {
                str3 = "";
            }
            sb.append(str3);
            arrayList.add(sb.toString());
            i = i2;
        }
        g.a(3, "newUrlList size = " + arrayList.size());
        return arrayList;
    }

    public static final void a(Context context, String str, String str2, b bVar, boolean z, boolean z2) {
        if (!TextUtils.isEmpty(str2)) {
            b(context, str2, bVar);
        }
        m.a().b();
        String str3 = null;
        if (!z2) {
            try {
                str3 = a(str, str2);
            } catch (Exception e) {
                com.startapp.android.publish.adsCommon.f.d dVar = com.startapp.android.publish.adsCommon.f.d.FAILED_EXTRACTING_DPARAMS;
                f.a(context, dVar, "Util.clickWithoutSmartRedirect(): Couldn't extract dparams with clickUrl " + str + " and tacking click url " + str2, e.getMessage(), (String) null);
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot start activity to handle url: [");
                sb.append(str);
                sb.append("]");
                g.a(6, sb.toString());
            }
        }
        try {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(f(str2) ? com.startapp.common.a.a.a(str3) : "");
            String sb3 = sb2.toString();
            if (MetaData.getInstance().isInAppBrowser() && z) {
                b(context, sb3, str3);
            } else if (!TextUtils.isEmpty(str2) || !f(context)) {
                c(context, sb3);
            } else {
                b(context);
                c(context, g(sb3));
                g.a(6, "forceExternal - click without - External");
                g.a(6, "forceExternal - click without - trackingClickUrl : " + str2);
            }
        } catch (Exception e2) {
            com.startapp.android.publish.adsCommon.f.d dVar2 = com.startapp.android.publish.adsCommon.f.d.EXCEPTION;
            f.a(context, dVar2, "Util.clickWithoutSmartRedirect - Couldn't start activity for " + "InAppBrowser", e2.getMessage(), str3);
            g.a(6, "Cannot start activity to handle url: [" + str + "]");
        }
    }

    private static boolean f(String str) {
        return b.a().D() || TextUtils.isEmpty(str);
    }

    private static String g(String str) {
        return str + "&cki=1";
    }

    private static void b(Context context) {
        k.b(context, "shared_prefs_CookieFeatureTS", Long.valueOf(System.currentTimeMillis()));
        g.a(6, "forceExternal - write to sp - TS : " + SimpleDateFormat.getDateInstance().format(new Date()));
    }

    public static final void a(Context context, String str, String str2, String str3, b bVar, long j, long j2, boolean z, Boolean bool, boolean z2) {
        a(context, str, str2, str3, bVar, j, j2, z, bool, z2, (Runnable) null);
    }

    public static final void a(Context context, String str, String str2, String str3, b bVar, long j, long j2, boolean z, Boolean bool, boolean z2, Runnable runnable) {
        Context context2 = context;
        String str4 = str;
        String str5 = str2;
        if (b.a().C()) {
            m.a().b();
            String str6 = null;
            if (!z2) {
                try {
                    str6 = a(str, str2);
                } catch (Exception e) {
                    Exception exc = e;
                    com.startapp.android.publish.adsCommon.f.d dVar = com.startapp.android.publish.adsCommon.f.d.FAILED_EXTRACTING_DPARAMS;
                    f.a(context, dVar, "Util.clickWithSmartRedirect(): Couldn't extract dparams with clickUrl " + str + " and tacking click url " + str2, exc.getMessage(), (String) null);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Cannot start activity to handle url: [");
                    sb.append(str);
                    sb.append("]");
                    g.a(6, sb.toString());
                }
            }
            String str7 = "";
            if (str5 != null && !str2.equals(str7)) {
                b(context, str2, bVar);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            if (f(str2)) {
                str7 = com.startapp.common.a.a.a(str6);
            }
            sb2.append(str7);
            a(context, sb2.toString(), str3, str6, j, j2, z, bool, runnable);
            return;
        }
        b bVar2 = bVar;
        a(context, str, str2, bVar, z, z2);
    }

    public static void b(Context context, String str, b bVar) {
        a(context, str, bVar, true);
    }

    public static void a(final Context context, final String str, final b bVar, final boolean z) {
        if (!str.equals("")) {
            com.startapp.common.g.a(g.a.HIGH, (Runnable) new Runnable() {
                public void run() {
                    try {
                        if (z) {
                            Context context = context;
                            com.startapp.android.publish.adsCommon.k.a.a(context, str + com.startapp.common.a.a.a(c.e(str)) + bVar.getQueryString(), (Map<String, String>) null);
                            return;
                        }
                        Context context2 = context;
                        com.startapp.android.publish.adsCommon.k.a.a(context2, str + bVar.getQueryString(), (Map<String, String>) null);
                    } catch (e e) {
                        f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "Util.sendTrackingMessage - Error sending tracking message", e.getMessage(), c.e(str));
                        com.startapp.common.a.g.a(6, "Error sending tracking message", (Throwable) e);
                    }
                }
            });
        }
    }

    public static void b(final Context context, final String str) {
        com.startapp.common.g.a(g.a.HIGH, (Runnable) new Runnable() {
            public void run() {
                try {
                    com.startapp.android.publish.adsCommon.k.a.a(context, str, (Map<String, String>) null);
                } catch (e e) {
                    f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "Util.sendTrackingMessage - Error sending tracking message", e.getMessage(), "");
                    com.startapp.common.a.g.a(6, "Error sending tracking message", (Throwable) e);
                }
            }
        });
    }

    private static final void a(Context context, String str, String str2, String str3, long j, long j2, boolean z, Boolean bool, Runnable runnable) {
        Context context2 = context;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        com.startapp.common.b.a(context).a(new Intent("com.startapp.android.OnClickCallback"));
        if (b(str)) {
            if (str5 != null && !str5.equals("") && !str.toLowerCase().contains(str2.toLowerCase())) {
                com.startapp.android.publish.adsCommon.f.d dVar = com.startapp.android.publish.adsCommon.f.d.WRONG_PACKAGE_REACHED;
                f.a(context2, dVar, "Wrong package name reached", "Expected: " + str5 + " Link: " + str4, str6);
            }
            a(context2, str4, str6);
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        if (context2 instanceof Activity) {
            i.a((Activity) context2, true);
        }
        try {
            final WebView webView = new WebView(context2);
            if (f220a == null) {
                if (Build.VERSION.SDK_INT >= 22) {
                    f220a = new ProgressDialog(context2, 16974545);
                } else {
                    f220a = new ProgressDialog(context2);
                }
                f220a.setTitle((CharSequence) null);
                f220a.setMessage("Loading....");
                f220a.setIndeterminate(false);
                f220a.setCancelable(false);
                f220a.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        webView.stopLoading();
                    }
                });
                if ((context2 instanceof Activity) && !((Activity) context2).isFinishing()) {
                    f220a.show();
                } else if (!(context2 instanceof Activity) && c(context) && f220a.getWindow() != null) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        f220a.getWindow().setType(2038);
                    } else {
                        f220a.getWindow().setType(AdError.INTERNAL_ERROR_2003);
                    }
                    f220a.show();
                }
            }
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
            a aVar = r2;
            WebView webView2 = webView;
            a aVar2 = new a(j, j2, z, bool, f220a, str, str2, str3, runnable);
            webView2.setWebViewClient(aVar);
            webView2.loadUrl(str4);
        } catch (Exception e) {
            Context context3 = context;
            f.a(context3, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "Util.smartRedirect - Webview failed", e.getMessage(), str6);
            a(context3, str4, str6);
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    private static boolean c(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(context);
        }
        return com.startapp.common.a.c.a(context, "android.permission.SYSTEM_ALERT_WINDOW");
    }

    public static boolean b(String str) {
        return str.startsWith("market") || str.startsWith("http://play.google.com") || str.startsWith("https://play.google.com");
    }

    public static boolean c(String str) {
        return str.startsWith("intent://");
    }

    public static boolean d(String str) {
        return str != null && (str.startsWith("http://") || str.startsWith("https://"));
    }

    public static final void a(Context context) {
        if (context != null && (context instanceof Activity)) {
            i.a((Activity) context, false);
        }
        d(context);
    }

    private static void d(Context context) {
        ProgressDialog progressDialog = f220a;
        if (progressDialog != null) {
            synchronized (progressDialog) {
                if (f220a != null && f220a.isShowing()) {
                    try {
                        f220a.cancel();
                    } catch (Exception e) {
                        com.startapp.common.a.g.a(6, "Error while cancelling progress", (Throwable) e);
                        f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "AdsCommonUtils.cancelProgress - progress.cancel() failed", e.getMessage(), "");
                    }
                    f220a = null;
                }
            }
        }
    }

    /* compiled from: StartAppSDK */
    private static class a extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        protected String f224a = "";
        protected String b;
        protected boolean c = false;
        protected boolean d = false;
        protected long e;
        protected boolean f = true;
        protected Boolean g = null;
        protected String h;
        protected ProgressDialog i;
        protected Runnable j;
        protected boolean k = false;
        protected boolean l = false;
        private long m;
        private LinkedHashMap<String, Float> n = new LinkedHashMap<>();
        private long o;
        private Timer p;

        public a(long j2, long j3, boolean z, Boolean bool, ProgressDialog progressDialog, String str, String str2, String str3, Runnable runnable) {
            this.e = j2;
            this.m = j3;
            this.f = z;
            this.g = bool;
            this.i = progressDialog;
            this.f224a = str;
            this.h = str2;
            this.b = str3;
            this.j = runnable;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            com.startapp.common.a.g.a(2, "MyWebViewClientSmartRedirect::onPageStarted - [" + str + "]");
            super.onPageStarted(webView, str, bitmap);
            if (!this.d) {
                this.o = System.currentTimeMillis();
                this.n.put(str, Float.valueOf(-1.0f));
                a(webView.getContext());
                this.d = true;
            }
            this.l = false;
            b();
        }

        /* JADX WARNING: Removed duplicated region for block: B:29:0x00e9 A[Catch:{ Exception -> 0x013c }] */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x00f6 A[Catch:{ Exception -> 0x013c }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean shouldOverrideUrlLoading(android.webkit.WebView r9, java.lang.String r10) {
            /*
                r8 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "MyWebViewClientSmartRedirect::shouldOverrideUrlLoading - ["
                r0.append(r1)
                r0.append(r10)
                java.lang.String r1 = "]"
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r1 = 2
                com.startapp.common.a.g.a(r1, r0)
                r0 = 1
                long r1 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x013c }
                long r3 = r8.o     // Catch:{ Exception -> 0x013c }
                long r3 = r1 - r3
                float r3 = (float) r3     // Catch:{ Exception -> 0x013c }
                r4 = 1148846080(0x447a0000, float:1000.0)
                float r3 = r3 / r4
                java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{ Exception -> 0x013c }
                r8.o = r1     // Catch:{ Exception -> 0x013c }
                java.util.LinkedHashMap<java.lang.String, java.lang.Float> r1 = r8.n     // Catch:{ Exception -> 0x013c }
                java.lang.String r2 = r8.f224a     // Catch:{ Exception -> 0x013c }
                r1.put(r2, r3)     // Catch:{ Exception -> 0x013c }
                java.util.LinkedHashMap<java.lang.String, java.lang.Float> r1 = r8.n     // Catch:{ Exception -> 0x013c }
                r2 = -1082130432(0xffffffffbf800000, float:-1.0)
                java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x013c }
                r1.put(r10, r2)     // Catch:{ Exception -> 0x013c }
                r8.f224a = r10     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = r10.toLowerCase()     // Catch:{ Exception -> 0x013c }
                boolean r2 = com.startapp.android.publish.adsCommon.c.b((java.lang.String) r1)     // Catch:{ Exception -> 0x013c }
                r3 = 0
                if (r2 != 0) goto L_0x0053
                boolean r2 = com.startapp.android.publish.adsCommon.c.c((java.lang.String) r1)     // Catch:{ Exception -> 0x013c }
                if (r2 != 0) goto L_0x0053
                return r3
            L_0x0053:
                boolean r2 = r8.k     // Catch:{ Exception -> 0x013c }
                if (r2 != 0) goto L_0x013c
                r8.c = r0     // Catch:{ Exception -> 0x013c }
                android.content.Context r2 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.c.a((android.content.Context) r2)     // Catch:{ Exception -> 0x013c }
                r8.b()     // Catch:{ Exception -> 0x013c }
                android.content.Context r2 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                boolean r1 = com.startapp.android.publish.adsCommon.c.c((java.lang.String) r1)     // Catch:{ Exception -> 0x013c }
                if (r1 == 0) goto L_0x0071
                java.lang.String r10 = r9.getUrl()     // Catch:{ Exception -> 0x013c }
            L_0x0071:
                com.startapp.android.publish.adsCommon.c.c(r2, r10)     // Catch:{ Exception -> 0x013c }
                java.lang.String r10 = r8.h     // Catch:{ Exception -> 0x013c }
                if (r10 == 0) goto L_0x00c0
                java.lang.String r10 = r8.h     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = ""
                boolean r10 = r10.equals(r1)     // Catch:{ Exception -> 0x013c }
                if (r10 != 0) goto L_0x00c0
                java.lang.String r10 = r8.f224a     // Catch:{ Exception -> 0x013c }
                java.lang.String r10 = r10.toLowerCase()     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = r8.h     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = r1.toLowerCase()     // Catch:{ Exception -> 0x013c }
                boolean r10 = r10.contains(r1)     // Catch:{ Exception -> 0x013c }
                if (r10 != 0) goto L_0x00c0
                android.content.Context r9 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.f.d r10 = com.startapp.android.publish.adsCommon.f.d.WRONG_PACKAGE_REACHED     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = "Wrong package name reached"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013c }
                r2.<init>()     // Catch:{ Exception -> 0x013c }
                java.lang.String r3 = "Expected: "
                r2.append(r3)     // Catch:{ Exception -> 0x013c }
                java.lang.String r3 = r8.h     // Catch:{ Exception -> 0x013c }
                r2.append(r3)     // Catch:{ Exception -> 0x013c }
                java.lang.String r3 = " Link: "
                r2.append(r3)     // Catch:{ Exception -> 0x013c }
                java.lang.String r3 = r8.f224a     // Catch:{ Exception -> 0x013c }
                r2.append(r3)     // Catch:{ Exception -> 0x013c }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x013c }
                java.lang.String r3 = r8.b     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.f.f.a(r9, r10, r1, r2, r3)     // Catch:{ Exception -> 0x013c }
                goto L_0x0133
            L_0x00c0:
                com.startapp.android.publish.common.metaData.MetaData r10 = com.startapp.android.publish.common.metaData.MetaData.getInstance()     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.f.a r10 = r10.getAnalyticsConfig()     // Catch:{ Exception -> 0x013c }
                boolean r10 = r10.g()     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = "firstSucceededSmartRedirect"
                if (r10 == 0) goto L_0x00e4
                android.content.Context r10 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)     // Catch:{ Exception -> 0x013c }
                java.lang.Boolean r10 = com.startapp.android.publish.adsCommon.k.a((android.content.Context) r10, (java.lang.String) r1, (java.lang.Boolean) r2)     // Catch:{ Exception -> 0x013c }
                boolean r10 = r10.booleanValue()     // Catch:{ Exception -> 0x013c }
                if (r10 == 0) goto L_0x00e4
                r10 = 1
                goto L_0x00e5
            L_0x00e4:
                r10 = 0
            L_0x00e5:
                java.lang.Boolean r2 = r8.g     // Catch:{ Exception -> 0x013c }
                if (r2 != 0) goto L_0x00f6
                com.startapp.android.publish.common.metaData.MetaData r2 = com.startapp.android.publish.common.metaData.MetaData.getInstance()     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.f.a r2 = r2.getAnalyticsConfig()     // Catch:{ Exception -> 0x013c }
                float r2 = r2.f()     // Catch:{ Exception -> 0x013c }
                goto L_0x0102
            L_0x00f6:
                java.lang.Boolean r2 = r8.g     // Catch:{ Exception -> 0x013c }
                boolean r2 = r2.booleanValue()     // Catch:{ Exception -> 0x013c }
                if (r2 == 0) goto L_0x0101
                r2 = 1120403456(0x42c80000, float:100.0)
                goto L_0x0102
            L_0x0101:
                r2 = 0
            L_0x0102:
                if (r10 != 0) goto L_0x0111
                double r4 = java.lang.Math.random()     // Catch:{ Exception -> 0x013c }
                r6 = 4636737291354636288(0x4059000000000000, double:100.0)
                double r4 = r4 * r6
                double r6 = (double) r2     // Catch:{ Exception -> 0x013c }
                int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r10 >= 0) goto L_0x0133
            L_0x0111:
                com.startapp.android.publish.adsCommon.f.e r10 = new com.startapp.android.publish.adsCommon.f.e     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.f.d r2 = com.startapp.android.publish.adsCommon.f.d.SUCCESS_SMART_REDIRECT_HOP_INFO     // Catch:{ Exception -> 0x013c }
                r10.<init>(r2)     // Catch:{ Exception -> 0x013c }
                org.json.JSONArray r2 = r8.a()     // Catch:{ Exception -> 0x013c }
                r10.a((org.json.JSONArray) r2)     // Catch:{ Exception -> 0x013c }
                android.content.Context r2 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                java.lang.String r4 = r8.b     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.f.f.a(r2, r10, r4)     // Catch:{ Exception -> 0x013c }
                android.content.Context r9 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                java.lang.Boolean r10 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.k.b((android.content.Context) r9, (java.lang.String) r1, (java.lang.Boolean) r10)     // Catch:{ Exception -> 0x013c }
            L_0x0133:
                java.lang.Runnable r9 = r8.j     // Catch:{ Exception -> 0x013c }
                if (r9 == 0) goto L_0x013c
                java.lang.Runnable r9 = r8.j     // Catch:{ Exception -> 0x013c }
                r9.run()     // Catch:{ Exception -> 0x013c }
            L_0x013c:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.c.a.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
        }

        public void onPageFinished(WebView webView, String str) {
            com.startapp.common.a.g.a(2, "MyWebViewClientSmartRedirect::onPageFinished - [" + str + "]");
            if (!this.c && !this.k && this.f224a.equals(str) && str != null && !c.b(str) && (str.startsWith("http://") || str.startsWith("https://"))) {
                this.l = true;
                try {
                    a(str);
                } catch (Exception unused) {
                }
                b(webView.getContext());
            }
            super.onPageFinished(webView, str);
        }

        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            com.startapp.common.a.g.a(2, "MyWebViewClientSmartRedirect::onReceivedError - [" + str + "], [" + str2 + "]");
            b();
            if (str2 != null && !c.b(str2) && c.d(str2)) {
                f.a(webView.getContext(), com.startapp.android.publish.adsCommon.f.d.FAILED_SMART_REDIRECT, Integer.toString(i2), str2, this.b);
            }
            super.onReceivedError(webView, i2, str, str2);
        }

        private void a(final Context context) {
            com.startapp.common.g.a((Runnable) new Runnable() {
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0030 */
                /* JADX WARNING: Removed duplicated region for block: B:13:0x004f A[Catch:{ Exception -> 0x0078 }] */
                /* JADX WARNING: Removed duplicated region for block: B:14:0x005d A[Catch:{ Exception -> 0x0078 }] */
                /* JADX WARNING: Removed duplicated region for block: B:17:0x0070 A[Catch:{ Exception -> 0x0078 }] */
                /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r5 = this;
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.c.a.this
                        boolean r0 = r0.c
                        if (r0 != 0) goto L_0x008a
                        com.startapp.android.publish.adsCommon.f.e r0 = new com.startapp.android.publish.adsCommon.f.e     // Catch:{ Exception -> 0x0030 }
                        com.startapp.android.publish.adsCommon.f.d r1 = com.startapp.android.publish.adsCommon.f.d.FAILED_SMART_REDIRECT_HOP_INFO     // Catch:{ Exception -> 0x0030 }
                        r0.<init>(r1)     // Catch:{ Exception -> 0x0030 }
                        com.startapp.android.publish.adsCommon.c$a r1 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0030 }
                        org.json.JSONArray r1 = r1.a()     // Catch:{ Exception -> 0x0030 }
                        r0.a((org.json.JSONArray) r1)     // Catch:{ Exception -> 0x0030 }
                        com.startapp.android.publish.adsCommon.c$a r1 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0030 }
                        boolean r1 = r1.l     // Catch:{ Exception -> 0x0030 }
                        if (r1 == 0) goto L_0x0022
                        java.lang.String r1 = "Page Finished"
                        r0.d(r1)     // Catch:{ Exception -> 0x0030 }
                        goto L_0x0027
                    L_0x0022:
                        java.lang.String r1 = "Timeout"
                        r0.d(r1)     // Catch:{ Exception -> 0x0030 }
                    L_0x0027:
                        android.content.Context r1 = r4     // Catch:{ Exception -> 0x0030 }
                        com.startapp.android.publish.adsCommon.c$a r2 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0030 }
                        java.lang.String r2 = r2.b     // Catch:{ Exception -> 0x0030 }
                        com.startapp.android.publish.adsCommon.f.f.a(r1, r0, r2)     // Catch:{ Exception -> 0x0030 }
                    L_0x0030:
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0078 }
                        r1 = 1
                        r0.k = r1     // Catch:{ Exception -> 0x0078 }
                        android.content.Context r0 = r4     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c.a((android.content.Context) r0)     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0078 }
                        r0.b()     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0078 }
                        boolean r0 = r0.f     // Catch:{ Exception -> 0x0078 }
                        if (r0 == 0) goto L_0x005d
                        com.startapp.android.publish.common.metaData.MetaData r0 = com.startapp.android.publish.common.metaData.MetaData.getInstance()     // Catch:{ Exception -> 0x0078 }
                        boolean r0 = r0.isInAppBrowser()     // Catch:{ Exception -> 0x0078 }
                        if (r0 == 0) goto L_0x005d
                        android.content.Context r0 = r4     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r1 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r1 = r1.f224a     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r2 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r2 = r2.b     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c.b((android.content.Context) r0, (java.lang.String) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0078 }
                        goto L_0x006a
                    L_0x005d:
                        android.content.Context r0 = r4     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r1 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r1 = r1.f224a     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r2 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r2 = r2.b     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c.a((android.content.Context) r0, (java.lang.String) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0078 }
                    L_0x006a:
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.Runnable r0 = r0.j     // Catch:{ Exception -> 0x0078 }
                        if (r0 == 0) goto L_0x008a
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.c.a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.Runnable r0 = r0.j     // Catch:{ Exception -> 0x0078 }
                        r0.run()     // Catch:{ Exception -> 0x0078 }
                        goto L_0x008a
                    L_0x0078:
                        r0 = move-exception
                        android.content.Context r1 = r4
                        com.startapp.android.publish.adsCommon.f.d r2 = com.startapp.android.publish.adsCommon.f.d.EXCEPTION
                        java.lang.String r0 = r0.getMessage()
                        com.startapp.android.publish.adsCommon.c$a r3 = com.startapp.android.publish.adsCommon.c.a.this
                        java.lang.String r3 = r3.b
                        java.lang.String r4 = "AdsCommonUtils.startTimeout - error after time elapsed"
                        com.startapp.android.publish.adsCommon.f.f.a(r1, r2, r4, r0, r3)
                    L_0x008a:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.c.a.AnonymousClass1.run():void");
                }
            }, this.e);
        }

        private void b(final Context context) {
            b();
            try {
                Timer timer = new Timer();
                this.p = timer;
                timer.schedule(new TimerTask() {
                    public void run() {
                        if (!a.this.k && !a.this.c) {
                            try {
                                a.this.c = true;
                                c.a(context);
                                if (!a.this.f || !MetaData.getInstance().isInAppBrowser()) {
                                    c.a(context, a.this.f224a, a.this.b);
                                } else {
                                    c.b(context, a.this.f224a, a.this.b);
                                }
                                if (a.this.j != null) {
                                    a.this.j.run();
                                }
                            } catch (Exception e) {
                                f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "AdsCommonUtils.startLoadedTimer - error after time elapsed", e.getMessage(), a.this.b);
                            }
                        }
                    }
                }, this.m);
            } catch (Exception e2) {
                this.p = null;
                com.startapp.common.a.g.a("AdsCommonUtils", 6, "startLoadedTimer", e2);
            }
        }

        /* access modifiers changed from: private */
        public void b() {
            Timer timer = this.p;
            if (timer != null) {
                timer.cancel();
                this.p = null;
            }
        }

        private void a(String str) {
            if (this.n.get(str).floatValue() < 0.0f) {
                this.n.put(str, Float.valueOf(((float) (System.currentTimeMillis() - this.o)) / 1000.0f));
            }
        }

        /* access modifiers changed from: protected */
        public JSONArray a() {
            JSONArray jSONArray = new JSONArray();
            for (String next : this.n.keySet()) {
                JSONObject jSONObject = new JSONObject();
                try {
                    a(next);
                    jSONObject.put(d.fl, this.n.get(next).toString());
                    jSONObject.put("url", next);
                    jSONArray.put(jSONObject);
                } catch (JSONException unused) {
                    com.startapp.common.a.g.a(6, "error puting url into json [" + next + "]");
                }
            }
            return jSONArray;
        }
    }

    public static void c(Context context, String str) {
        a(context, str, (String) null);
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, d(str));
    }

    public static void a(Context context, String str, String str2, boolean z) {
        if (context != null) {
            int i = 76021760;
            if (b.a().G() || !(context instanceof Activity)) {
                i = 344457216;
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(i);
            boolean a2 = a(context, intent);
            if (!a2) {
                try {
                    if (Build.VERSION.SDK_INT >= 18 && MetaData.getInstance().getChromeCustomeTabsExternal() && e(context)) {
                        d(context, str);
                        return;
                    }
                } catch (Exception unused) {
                    a(context, str, str2, i);
                    return;
                }
            }
            if (z && !a2) {
                a(context, intent, i);
            }
            context.startActivity(intent);
        }
    }

    private static void a(Context context, Intent intent, int i) {
        String[] strArr = {"com.android.chrome", "com.android.browser", "com.opera.mini.native", "org.mozilla.firefox", "com.opera.browser"};
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, i);
            if (queryIntentActivities != null && queryIntentActivities.size() > 1) {
                for (int i2 = 0; i2 < 5; i2++) {
                    String str = strArr[i2];
                    if (com.startapp.common.a.c.a(context, str, 0)) {
                        intent.setPackage(str);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "AdsCommonUtils.chooseDefaultBrowser", e.getMessage(), "");
        }
    }

    private static void a(Context context, String str, String str2, int i) {
        try {
            Intent parseUri = Intent.parseUri(str, i);
            a(context, parseUri);
            if (!(context instanceof Activity)) {
                parseUri.addFlags(268435456);
            }
            context.startActivity(parseUri);
        } catch (Exception e) {
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "Util.openUrlExternally - Couldn't start activity", e.getMessage(), str2);
            com.startapp.common.a.g.a(6, "Cannot find activity to handle url: [" + str + "]");
        }
    }

    public static void b(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "Util.OpenAsInAppBrowser - Couldn't start activity", "Parameter clickUrl is null", str2);
            com.startapp.common.a.g.a(6, "Cannot start activity, because url is null");
        } else if (b(str) || !i.a(256)) {
            a(context, str, str2);
        } else {
            try {
                if (Build.VERSION.SDK_INT >= 18 && MetaData.getInstance().getChromeCustomeTabsInternal() && e(context)) {
                    d(context, str);
                    return;
                }
            } catch (Exception e) {
                f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "Util.OpenAsInAppBrowser - Couldn't openUrlChromeTabs", e.getMessage(), str2);
            }
            Intent intent = new Intent(context, OverlayActivity.class);
            if (Build.VERSION.SDK_INT >= 21) {
                intent.addFlags(524288);
            }
            if (Build.VERSION.SDK_INT >= 11) {
                intent.addFlags(32768);
            }
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setData(Uri.parse(str));
            intent.putExtra("placement", AdPreferences.Placement.INAPP_BROWSER.getIndex());
            intent.putExtra("activityShouldLockOrientation", false);
            try {
                context.startActivity(intent);
            } catch (Exception e2) {
                f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "Util.OpenAsInAppBrowser - Couldn't start activity", e2.getMessage(), str2);
                com.startapp.common.a.g.a(6, "Cannot find activity to handle url: [" + str + "]");
            }
        }
    }

    private static void d(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        Bundle bundle = new Bundle();
        bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private static boolean e(Context context) {
        return k.a(context, "chromeTabs", (Boolean) false).booleanValue();
    }

    public static void a(String str, String str2, String str3, Context context, b bVar) {
        a(context, str3, bVar, true);
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (str2 != null) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    launchIntentForPackage.putExtra(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (JSONException e) {
                com.startapp.common.a.g.a(6, "Couldn't parse intent details json!", (Throwable) e);
            }
        }
        try {
            context.startActivity(launchIntentForPackage);
        } catch (Exception e2) {
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "Util.handleCPEClick - Couldn't start activity", e2.getMessage(), a(str3, (String) null));
            com.startapp.common.a.g.a(6, "Cannot find activity to handle url: [" + str3 + "]");
        }
    }

    public static String e(String str) {
        return a(str, (String) null);
    }

    public static String a(String str, String str2) {
        if (str2 != null) {
            try {
                if (!str2.equals("")) {
                    str = str2;
                }
            } catch (Exception unused) {
                return "";
            }
        }
        String[] split = str.split("[?&]d=");
        if (split.length >= 2) {
            return split[1].split("[?&]")[0];
        }
        return "";
    }

    public static boolean a(Context context, Intent intent) {
        for (ResolveInfo next : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (next.activityInfo.packageName.equalsIgnoreCase(Constants.f326a)) {
                intent.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
                return true;
            }
        }
        return false;
    }

    public static String a() {
        return "&position=" + b();
    }

    public static String b() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int i = 0;
        while (i < 8) {
            if (stackTrace[i].getMethodName().compareTo("doHome") == 0) {
                return "home";
            }
            if (stackTrace[i].getMethodName().compareTo("onBackPressed") != 0) {
                i++;
            } else if (!m.a().f() && !m.p()) {
                return AdType.INTERSTITIAL;
            } else {
                m.a().m();
                return "back";
            }
        }
        return AdType.INTERSTITIAL;
    }

    public static String[] a(g gVar) {
        if (gVar instanceof e) {
            return ((e) gVar).l();
        }
        return gVar instanceof h ? a(((h) gVar).d()) : new String[0];
    }

    public static String[] a(List<AdDetails> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (AdDetails trackingUrl : list) {
                arrayList.add(trackingUrl.getTrackingUrl());
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static boolean a(Context context, AdPreferences.Placement placement) {
        com.startapp.common.a.g.a("AdsCommonUtils", 6, "forceExternal - check -placement is : " + placement);
        if (placement.equals(AdPreferences.Placement.INAPP_SPLASH) || !b.a().M()) {
            return false;
        }
        return f(context);
    }

    private static boolean f(Context context) {
        if (com.startapp.common.a.b.a().a(context).b() || !a(k.a(context, "shared_prefs_CookieFeatureTS", (Long) 0L).longValue(), System.currentTimeMillis())) {
            return false;
        }
        com.startapp.common.a.g.a("AdsCommonUtils", 6, "forceExternal - check - true ");
        return true;
    }

    private static boolean a(long j, long j2) {
        return j == 0 || j + (((long) b.a().L()) * 86400000) <= j2;
    }
}
