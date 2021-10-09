package com.appnext.core;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class e {
    private static e gA = null;
    private static final long go = 8000;
    private static final long gp = 15000;
    private static final String gq = "com.android.vending";
    private static final String gr = "market://";
    private static final String gs = "https://play.google.com/store";
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public WebView gt;
    /* access modifiers changed from: private */
    public WebView gu;
    /* access modifiers changed from: private */
    public a gv = new a() {
        public final void onMarket(String str) {
            e.this.gz = 0;
            if (e.this.gy.size() != 0) {
                b bVar = (b) e.this.gy.get(0);
                if (bVar.gL != null) {
                    bVar.gL.onMarket(str);
                }
                try {
                    String str2 = "https://admin.appnext.com/tools/navtac.html?bid=" + ((b) e.this.gy.get(0)).gM + "&guid=" + f.m("admin.appnext.com", "applink") + "&url=" + URLEncoder.encode(str, "UTF-8");
                    if (e.this.gu == null) {
                        WebView unused = e.this.gu = new WebView(e.this.context);
                        e.this.gu.getSettings().setJavaScriptEnabled(true);
                        e.this.gu.getSettings().setDomStorageEnabled(true);
                        if (Build.VERSION.SDK_INT >= 21) {
                            e.this.gu.getSettings().setMixedContentMode(0);
                        }
                        e.this.gu.setWebViewClient(new WebViewClient() {
                            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                                if (str == null || str.contains("about:blank")) {
                                    return false;
                                }
                                webView.loadUrl(str);
                                return true;
                            }
                        });
                    }
                    e.this.gu.loadUrl("about:blank");
                    e.this.gu.loadUrl(str2);
                    new StringBuilder("store url: ").append(str2);
                    e.this.bc();
                    e.this.bb();
                } catch (UnsupportedEncodingException unused2) {
                    e.this.bb();
                }
            }
        }

        public final void error(String str) {
            e.this.gz = 0;
            if (e.this.gy.size() != 0) {
                b bVar = (b) e.this.gy.get(0);
                if (bVar.gL != null) {
                    bVar.gL.error(str);
                }
                e.this.bb();
            }
        }
    };
    private Runnable gw = new Runnable() {
        public final void run() {
            if (!(e.this.gv == null || e.this.gt == null)) {
                e.this.gv.error(e.this.gt.getUrl());
                e.this.gt.stopLoading();
            }
            e.this.bb();
        }
    };
    private List gx;
    /* access modifiers changed from: private */
    public final ArrayList<b> gy = new ArrayList<>();
    /* access modifiers changed from: private */
    public int gz = 0;
    private Handler handler = new Handler(Looper.getMainLooper());

    public interface a {
        void error(String str);

        void onMarket(String str);
    }

    private class b {
        String aQ;
        String gJ;
        String gK;
        a gL;
        String gM;
        long gN;

        b(String str, String str2, String str3, String str4, a aVar, long j) {
            this.gJ = str;
            this.gK = str2;
            this.aQ = str3;
            this.gL = aVar;
            this.gM = str4;
            this.gN = j;
        }

        public final void onMarket(String str) {
            a aVar = this.gL;
            if (aVar != null) {
                aVar.onMarket(str);
            }
        }

        public final void error(String str) {
            a aVar = this.gL;
            if (aVar != null) {
                aVar.error(str);
            }
        }
    }

    public static e k(Context context2) {
        if (gA == null) {
            synchronized (e.class) {
                if (gA == null) {
                    gA = new e(context2);
                }
            }
        }
        return gA;
    }

    private e(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public final void a(String str, String str2, String str3, String str4, a aVar) {
        a(str, str2, str3, str4, aVar, (long) go);
    }

    public final void a(String str, String str2, String str3, String str4, a aVar, long j) {
        String str5 = str3;
        if (this.context != null) {
            if (str5 != null) {
                Iterator<b> it = this.gy.iterator();
                while (it.hasNext()) {
                    if (it.next().aQ.equals(str5)) {
                        return;
                    }
                }
                if (str5.endsWith("&ox=0")) {
                    this.gy.add(new b(str, str2, str3, str4, aVar, j));
                    new StringBuilder("--ck-- in ").append(str5);
                } else {
                    this.gz = 0;
                    if (this.gy.size() > 0 && !this.gy.get(0).aQ.endsWith("&ox=0")) {
                        new StringBuilder("--ck-- out ").append(this.gy.get(0).aQ);
                        this.gy.get(0).gL = null;
                        this.gy.remove(0);
                        new StringBuilder("--ck-- size ").append(this.gy.size());
                    }
                    this.gy.add(0, new b(str, str2, str3, str4, aVar, j));
                    new StringBuilder("--ck-- in ").append(str5);
                }
            }
            if (this.gy.size() <= 0 || this.gz == 1) {
                StringBuilder sb = new StringBuilder("vta waiting -  ");
                sb.append(str4);
                sb.append(" - ");
                sb.append(str5);
                return;
            }
            this.gz = 1;
            StringBuilder sb2 = new StringBuilder("vta load -  ");
            sb2.append(this.gy.get(0).gM);
            sb2.append(" - ");
            sb2.append(this.gy.get(0).aQ);
            a(this.gy.get(0));
        }
    }

    private void setState(int i) {
        this.gz = i;
    }

    /* access modifiers changed from: private */
    public void bc() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
    }

    private boolean R(String str) {
        try {
            this.context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private void S(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        for (ResolveInfo next : this.context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (next.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                ActivityInfo activityInfo = next.activityInfo;
                ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                intent.addFlags(268435456);
                intent.addFlags(2097152);
                intent.addFlags(67108864);
                intent.setComponent(componentName);
                this.context.startActivity(intent);
                return;
            }
        }
    }

    private void T(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setFlags(268435456);
        this.context.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        if (r7.startsWith(gs) != false) goto L_0x0016;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0084 A[Catch:{ all -> 0x008a }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void openMarket(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "com.android.vending"
            java.lang.String r1 = "market://"
            boolean r1 = r7.startsWith(r1)     // Catch:{ all -> 0x008a }
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            java.lang.String r3 = "android.intent.action.VIEW"
            if (r1 != 0) goto L_0x0016
            java.lang.String r1 = "https://play.google.com/store"
            boolean r1 = r7.startsWith(r1)     // Catch:{ all -> 0x008a }
            if (r1 == 0) goto L_0x006f
        L_0x0016:
            boolean r1 = r6.R(r0)     // Catch:{ all -> 0x008a }
            if (r1 == 0) goto L_0x006f
            android.content.Intent r1 = new android.content.Intent     // Catch:{ all -> 0x008a }
            android.net.Uri r4 = android.net.Uri.parse(r7)     // Catch:{ all -> 0x008a }
            r1.<init>(r3, r4)     // Catch:{ all -> 0x008a }
            android.content.Context r3 = r6.context     // Catch:{ all -> 0x008a }
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ all -> 0x008a }
            r4 = 0
            java.util.List r3 = r3.queryIntentActivities(r1, r4)     // Catch:{ all -> 0x008a }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x008a }
        L_0x0034:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x008a }
            if (r4 == 0) goto L_0x0080
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x008a }
            android.content.pm.ResolveInfo r4 = (android.content.pm.ResolveInfo) r4     // Catch:{ all -> 0x008a }
            android.content.pm.ActivityInfo r5 = r4.activityInfo     // Catch:{ all -> 0x008a }
            android.content.pm.ApplicationInfo r5 = r5.applicationInfo     // Catch:{ all -> 0x008a }
            java.lang.String r5 = r5.packageName     // Catch:{ all -> 0x008a }
            boolean r5 = r5.equals(r0)     // Catch:{ all -> 0x008a }
            if (r5 == 0) goto L_0x0034
            android.content.pm.ActivityInfo r0 = r4.activityInfo     // Catch:{ all -> 0x008a }
            android.content.ComponentName r3 = new android.content.ComponentName     // Catch:{ all -> 0x008a }
            android.content.pm.ApplicationInfo r4 = r0.applicationInfo     // Catch:{ all -> 0x008a }
            java.lang.String r4 = r4.packageName     // Catch:{ all -> 0x008a }
            java.lang.String r0 = r0.name     // Catch:{ all -> 0x008a }
            r3.<init>(r4, r0)     // Catch:{ all -> 0x008a }
            r1.addFlags(r2)     // Catch:{ all -> 0x008a }
            r0 = 2097152(0x200000, float:2.938736E-39)
            r1.addFlags(r0)     // Catch:{ all -> 0x008a }
            r0 = 67108864(0x4000000, float:1.5046328E-36)
            r1.addFlags(r0)     // Catch:{ all -> 0x008a }
            r1.setComponent(r3)     // Catch:{ all -> 0x008a }
            android.content.Context r0 = r6.context     // Catch:{ all -> 0x008a }
            r0.startActivity(r1)     // Catch:{ all -> 0x008a }
            goto L_0x0080
        L_0x006f:
            android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x008a }
            android.net.Uri r1 = android.net.Uri.parse(r7)     // Catch:{ all -> 0x008a }
            r0.<init>(r3, r1)     // Catch:{ all -> 0x008a }
            r0.setFlags(r2)     // Catch:{ all -> 0x008a }
            android.content.Context r1 = r6.context     // Catch:{ all -> 0x008a }
            r1.startActivity(r0)     // Catch:{ all -> 0x008a }
        L_0x0080:
            com.appnext.core.e$a r0 = r6.gv     // Catch:{ all -> 0x008a }
            if (r0 == 0) goto L_0x0089
            com.appnext.core.e$a r0 = r6.gv     // Catch:{ all -> 0x008a }
            r0.onMarket(r7)     // Catch:{ all -> 0x008a }
        L_0x0089:
            return
        L_0x008a:
            com.appnext.core.e$a r0 = r6.gv
            if (r0 == 0) goto L_0x0092
            r0.error(r7)
        L_0x0092:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.e.openMarket(java.lang.String):void");
    }

    private void a(final b bVar) {
        try {
            new StringBuilder("ClickMarketUrl - ").append(bVar.gK);
            if (!TextUtils.isEmpty(bVar.gK)) {
                openMarket(bVar.gK);
                new Thread(new Runnable() {
                    public final void run() {
                        try {
                            f.a(bVar.gJ, (HashMap<String, String>) null);
                        } catch (Throwable unused) {
                        }
                    }
                }).start();
                return;
            }
            bc();
            if (this.gt == null) {
                WebView webView = new WebView(this.context);
                this.gt = webView;
                webView.getSettings().setJavaScriptEnabled(true);
                this.gt.getSettings().setDomStorageEnabled(true);
                if (Build.VERSION.SDK_INT >= 21) {
                    this.gt.getSettings().setMixedContentMode(0);
                }
                this.gt.setWebViewClient(new WebViewClient() {
                    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e7 A[Catch:{ all -> 0x0108 }] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final boolean shouldOverrideUrlLoading(android.webkit.WebView r7, java.lang.String r8) {
                        /*
                            r6 = this;
                            java.lang.String r0 = "browser_fallback_url"
                            java.lang.String r1 = "market_referrer"
                            r2 = 0
                            if (r8 != 0) goto L_0x0008
                            return r2
                        L_0x0008:
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            java.lang.String r4 = "redirect url: "
                            r3.<init>(r4)
                            r3.append(r8)
                            java.lang.String r3 = "https://play.google.com/store/apps/"
                            boolean r4 = r8.startsWith(r3)
                            if (r4 == 0) goto L_0x0020
                            java.lang.String r4 = "market://"
                            java.lang.String r8 = r8.replace(r3, r4)
                        L_0x0020:
                            java.lang.String r3 = "about:blank"
                            boolean r3 = r8.contains(r3)
                            if (r3 == 0) goto L_0x0029
                            return r2
                        L_0x0029:
                            java.lang.String r3 = "http://"
                            boolean r3 = r8.startsWith(r3)
                            r4 = 1
                            if (r3 != 0) goto L_0x014b
                            java.lang.String r3 = "https://"
                            boolean r3 = r8.startsWith(r3)
                            if (r3 != 0) goto L_0x014b
                            java.lang.String r3 = "intent://"
                            boolean r3 = r8.startsWith(r3)
                            if (r3 == 0) goto L_0x0109
                            android.content.Intent r7 = android.content.Intent.parseUri(r8, r4)     // Catch:{ all -> 0x0108 }
                            com.appnext.core.e r3 = com.appnext.core.e.this     // Catch:{ all -> 0x0108 }
                            android.content.Context r3 = r3.context     // Catch:{ all -> 0x0108 }
                            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ all -> 0x0108 }
                            r5 = 65536(0x10000, float:9.18355E-41)
                            android.content.pm.ResolveInfo r3 = r3.resolveActivity(r7, r5)     // Catch:{ all -> 0x0108 }
                            if (r3 == 0) goto L_0x0077
                            com.appnext.core.e r8 = com.appnext.core.e.this     // Catch:{ all -> 0x0108 }
                            r8.bc()     // Catch:{ all -> 0x0108 }
                            com.appnext.core.e r8 = com.appnext.core.e.this     // Catch:{ all -> 0x0108 }
                            com.appnext.core.e$a r8 = r8.gv     // Catch:{ all -> 0x0108 }
                            if (r8 == 0) goto L_0x0076
                            com.appnext.core.e r8 = com.appnext.core.e.this     // Catch:{ all -> 0x0108 }
                            com.appnext.core.e$a r8 = r8.gv     // Catch:{ all -> 0x0108 }
                            android.net.Uri r7 = r7.getData()     // Catch:{ all -> 0x0108 }
                            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0108 }
                            r8.onMarket(r7)     // Catch:{ all -> 0x0108 }
                        L_0x0076:
                            return r4
                        L_0x0077:
                            android.os.Bundle r3 = r7.getExtras()     // Catch:{ all -> 0x0108 }
                            java.lang.String r5 = ""
                            if (r3 == 0) goto L_0x00a0
                            android.os.Bundle r3 = r7.getExtras()     // Catch:{ all -> 0x0108 }
                            boolean r3 = r3.containsKey(r0)     // Catch:{ all -> 0x0108 }
                            if (r3 == 0) goto L_0x00a0
                            android.os.Bundle r3 = r7.getExtras()     // Catch:{ all -> 0x0108 }
                            java.lang.String r3 = r3.getString(r0)     // Catch:{ all -> 0x0108 }
                            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x0108 }
                            if (r3 != 0) goto L_0x00a0
                            android.os.Bundle r7 = r7.getExtras()     // Catch:{ all -> 0x0108 }
                            java.lang.String r7 = r7.getString(r0)     // Catch:{ all -> 0x0108 }
                            goto L_0x00da
                        L_0x00a0:
                            android.os.Bundle r0 = r7.getExtras()     // Catch:{ all -> 0x0108 }
                            boolean r0 = r0.containsKey(r1)     // Catch:{ all -> 0x0108 }
                            if (r0 == 0) goto L_0x00f1
                            android.os.Bundle r0 = r7.getExtras()     // Catch:{ all -> 0x0108 }
                            java.lang.String r0 = r0.getString(r1)     // Catch:{ all -> 0x0108 }
                            boolean r0 = r0.equals(r5)     // Catch:{ all -> 0x0108 }
                            if (r0 != 0) goto L_0x00f1
                            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0108 }
                            java.lang.String r0 = "market://details?id="
                            r8.<init>(r0)     // Catch:{ all -> 0x0108 }
                            java.lang.String r0 = r7.getPackage()     // Catch:{ all -> 0x0108 }
                            r8.append(r0)     // Catch:{ all -> 0x0108 }
                            java.lang.String r0 = "&referrer="
                            r8.append(r0)     // Catch:{ all -> 0x0108 }
                            android.os.Bundle r7 = r7.getExtras()     // Catch:{ all -> 0x0108 }
                            java.lang.String r7 = r7.getString(r1)     // Catch:{ all -> 0x0108 }
                            r8.append(r7)     // Catch:{ all -> 0x0108 }
                            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x0108 }
                        L_0x00da:
                            com.appnext.core.e r8 = com.appnext.core.e.this     // Catch:{ all -> 0x0108 }
                            r8.bc()     // Catch:{ all -> 0x0108 }
                            com.appnext.core.e r8 = com.appnext.core.e.this     // Catch:{ all -> 0x0108 }
                            com.appnext.core.e$a r8 = r8.gv     // Catch:{ all -> 0x0108 }
                            if (r8 == 0) goto L_0x00f0
                            com.appnext.core.e r8 = com.appnext.core.e.this     // Catch:{ all -> 0x0108 }
                            com.appnext.core.e$a r8 = r8.gv     // Catch:{ all -> 0x0108 }
                            r8.onMarket(r7)     // Catch:{ all -> 0x0108 }
                        L_0x00f0:
                            return r4
                        L_0x00f1:
                            com.appnext.core.e r7 = com.appnext.core.e.this     // Catch:{ all -> 0x0108 }
                            r7.bc()     // Catch:{ all -> 0x0108 }
                            com.appnext.core.e r7 = com.appnext.core.e.this     // Catch:{ all -> 0x0108 }
                            com.appnext.core.e$a r7 = r7.gv     // Catch:{ all -> 0x0108 }
                            if (r7 == 0) goto L_0x0107
                            com.appnext.core.e r7 = com.appnext.core.e.this     // Catch:{ all -> 0x0108 }
                            com.appnext.core.e$a r7 = r7.gv     // Catch:{ all -> 0x0108 }
                            r7.error(r8)     // Catch:{ all -> 0x0108 }
                        L_0x0107:
                            return r4
                        L_0x0108:
                            return r2
                        L_0x0109:
                            android.content.Intent r0 = new android.content.Intent
                            java.lang.String r1 = "android.intent.action.VIEW"
                            r0.<init>(r1)
                            android.net.Uri r1 = android.net.Uri.parse(r8)
                            r0.setData(r1)
                            com.appnext.core.e r1 = com.appnext.core.e.this     // Catch:{ all -> 0x014a }
                            android.content.Context r1 = r1.context     // Catch:{ all -> 0x014a }
                            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch:{ all -> 0x014a }
                            java.util.List r0 = r1.queryIntentActivities(r0, r2)     // Catch:{ all -> 0x014a }
                            int r0 = r0.size()     // Catch:{ all -> 0x014a }
                            if (r0 <= 0) goto L_0x0147
                            com.appnext.core.e r7 = com.appnext.core.e.this     // Catch:{ all -> 0x014a }
                            r7.bc()     // Catch:{ all -> 0x014a }
                            com.appnext.core.e r7 = com.appnext.core.e.this     // Catch:{ all -> 0x014a }
                            r7.openMarket(r8)     // Catch:{ all -> 0x014a }
                            com.appnext.core.e r7 = com.appnext.core.e.this     // Catch:{ all -> 0x014a }
                            com.appnext.core.e$a r7 = r7.gv     // Catch:{ all -> 0x014a }
                            if (r7 == 0) goto L_0x0146
                            com.appnext.core.e r7 = com.appnext.core.e.this     // Catch:{ all -> 0x014a }
                            com.appnext.core.e$a r7 = r7.gv     // Catch:{ all -> 0x014a }
                            r7.onMarket(r8)     // Catch:{ all -> 0x014a }
                        L_0x0146:
                            return r4
                        L_0x0147:
                            r7.loadUrl(r8)     // Catch:{ all -> 0x014a }
                        L_0x014a:
                            return r2
                        L_0x014b:
                            com.appnext.core.e r0 = com.appnext.core.e.this
                            android.content.Intent r1 = com.appnext.core.e.U(r8)
                            r2 = 0
                            android.content.Intent r1 = r1.setComponent(r2)
                            android.content.Intent r0 = r0.b((android.content.Intent) r1)
                            if (r0 == 0) goto L_0x017c
                            com.appnext.core.e r7 = com.appnext.core.e.this
                            r7.bc()
                            com.appnext.core.e r7 = com.appnext.core.e.this
                            com.appnext.core.e$a r7 = r7.gv
                            if (r7 == 0) goto L_0x0172
                            com.appnext.core.e r7 = com.appnext.core.e.this
                            com.appnext.core.e$a r7 = r7.gv
                            r7.onMarket(r8)
                        L_0x0172:
                            com.appnext.core.e r7 = com.appnext.core.e.this
                            android.content.Context r7 = r7.context
                            r7.startActivity(r0)
                            return r4
                        L_0x017c:
                            r7.loadUrl(r8)
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.e.AnonymousClass4.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
                    }
                });
            }
            this.gt.stopLoading();
            this.gt.loadUrl("about:blank");
            this.gx = a(this.context, U(bVar.aQ).setComponent((ComponentName) null));
            this.gt.loadUrl(bVar.aQ);
            new StringBuilder("appurl: ").append(bVar.aQ);
            this.handler.postDelayed(this.gw, bVar.aQ.endsWith("&ox=0") ? gp : bVar.gN);
        } catch (Throwable unused) {
            a aVar = this.gv;
            if (aVar != null) {
                aVar.error(bVar.aQ);
            }
            bb();
        }
    }

    public final void e(final AppnextAd appnextAd) {
        new Thread(new Runnable() {
            public final void run() {
                try {
                    f.a(appnextAd.getImpressionURL(), (HashMap<String, String>) null);
                } catch (Throwable unused) {
                }
            }
        }).start();
    }

    public final void f(final AppnextAd appnextAd) {
        new Thread(new Runnable() {
            public final void run() {
                try {
                    f.a(appnextAd.getImpressionURL() + "&device=" + f.be() + "&ox=0", (HashMap<String, String>) null);
                } catch (Throwable unused) {
                }
            }
        }).start();
    }

    public final void a(String str, String str2, String str3, String str4, String str5, String str6) {
        final String str7 = str;
        final String str8 = str2;
        final String str9 = str3;
        final String str10 = str4;
        final String str11 = str5;
        final String str12 = str6;
        new Thread(new Runnable() {
            public final void run() {
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("guid", str7);
                    hashMap.put("bannerId", str8);
                    hashMap.put("placementId", str9);
                    hashMap.put("vid", str10);
                    hashMap.put("url", str11);
                    f.a("https://admin.appnext.com/AdminService.asmx/" + str12, (HashMap<String, String>) hashMap);
                } catch (Throwable unused) {
                }
            }
        }).start();
    }

    private static List a(Context context2, Intent intent) {
        List<ResolveInfo> queryIntentActivities = context2.getPackageManager().queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : queryIntentActivities) {
            arrayList.add(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
        }
        return arrayList;
    }

    public final Intent b(Intent intent) {
        List<ComponentName> a2 = a(this.context, intent);
        new HashSet();
        for (ComponentName componentName : a2) {
            if (!this.gx.contains(componentName)) {
                Intent intent2 = new Intent();
                intent2.setComponent(componentName);
                return intent2;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static Intent U(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        return intent;
    }

    /* access modifiers changed from: private */
    public void bb() {
        this.gz = 0;
        if (this.gy.size() != 0) {
            new StringBuilder("--ck-- out ").append(this.gy.get(0).aQ);
            this.gy.get(0).gL = null;
            this.gy.remove(0);
            new StringBuilder("--ck-- size ").append(this.gy.size());
            a((String) null, (String) null, (String) null, (String) null, (a) null);
        }
    }
}
