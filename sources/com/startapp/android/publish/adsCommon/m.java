package com.startapp.android.publish.adsCommon;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.mopub.common.logging.MoPubLog;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.ads.splash.SplashHideListener;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.b;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.activities.FullScreenActivity;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.cache.c;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.MetaDataRequest;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.Constants;
import com.startapp.common.a.e;
import com.startapp.common.a.g;
import com.startapp.common.g;
import com.truenet.android.TrueNetSDK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class m {
    /* access modifiers changed from: private */
    public ServiceConnection A;

    /* renamed from: a  reason: collision with root package name */
    private SDKAdPreferences f272a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private long g;
    private Application h;
    private HashMap<Integer, Integer> i;
    private Object j;
    private Activity k;
    private boolean l;
    private String m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private Map<String, String> r;
    private Bundle s;
    private c t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private g y;
    /* access modifiers changed from: private */
    public boolean z;

    /* compiled from: StartAppSDK */
    private static class a {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final m f278a = new m();
    }

    public void b(Activity activity, Bundle bundle) {
    }

    private m() {
        this.b = i.a(512);
        this.d = false;
        this.e = false;
        this.f = false;
        this.h = null;
        this.i = new HashMap<>();
        this.l = false;
        this.n = false;
        this.o = true;
        this.p = false;
        this.q = false;
        this.s = null;
        this.t = null;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = null;
        this.z = false;
    }

    public static m a() {
        return a.f278a;
    }

    /* access modifiers changed from: protected */
    public void a(Context context, String str, String str2, SDKAdPreferences sDKAdPreferences, boolean z2) {
        try {
            if (i.a(2)) {
                if (!i.a(context, (Class<? extends Activity>) FullScreenActivity.class)) {
                    Log.w("StartAppSDKInternal", "FullScreenActivity is missing from AndroidManifest.xml");
                }
            }
            if (context instanceof Activity) {
                this.m = context.getClass().getName();
            }
            context = context.getApplicationContext();
            try {
                com.startapp.android.publish.adsCommon.e.a.a(context);
            } catch (Exception e2) {
                f.a(context, d.EXCEPTION, "init AdsExceptionHandler", e2.getMessage(), "");
            }
            b(!i.f(context));
            q(context);
            l.a(context);
            if (!this.l) {
                com.startapp.common.c.c(context);
                b.a(context);
                k(context);
                this.l = true;
                g.a("StartAppSDKInternal", 3, "Initialize StartAppSDK with DevID:[" + str + "], AppID:[" + str2 + "]");
                i.a(context, str, str2);
                this.f272a = sDKAdPreferences;
                e.b(context, "shared_prefs_sdk_ad_prefs", (Serializable) sDKAdPreferences);
                com.startapp.common.d.a.b(context);
                boolean booleanValue = k.a(context, "shared_prefs_first_init", (Boolean) true).booleanValue();
                g.a("StartAppSDKInternal", 3, "First Initialization: [" + booleanValue + "]");
                if (booleanValue) {
                    n(context);
                    k.b(context, "totalSessions", (Integer) 0);
                    k.b(context, "firstSessionTime", Long.valueOf(System.currentTimeMillis()));
                    k.b(context, "shared_prefs_first_init", (Boolean) false);
                }
                a(context, MetaDataRequest.a.LAUNCH);
                l(context);
                a(context, z2);
                if (this.b) {
                    m(context);
                }
                r(context);
            }
            j(context);
            f(context);
        } catch (Exception e3) {
            f.a(context, d.EXCEPTION, "StartAppSDKInternal.intialize - unexpected error occurd", e3.getMessage(), "");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context) {
        k.b(context, "periodicInfoEventPaused", (Boolean) true);
        b.a(786564404);
    }

    /* access modifiers changed from: package-private */
    public void b(Context context) {
        k.b(context, "periodicMetadataPaused", (Boolean) true);
        b.a(586482792);
    }

    /* access modifiers changed from: package-private */
    public void c(Context context) {
        k.b(context, "periodicInfoEventPaused", (Boolean) false);
        b.a(context, k.a(context, "periodicInfoEventTriggerTime", Long.valueOf(b.b(context))).longValue());
    }

    /* access modifiers changed from: package-private */
    public void d(Context context) {
        k.b(context, "periodicMetadataPaused", (Boolean) false);
        b.a(context, Long.valueOf(k.a(context, "periodicMetadataTriggerTime", Long.valueOf(b.a())).longValue()));
    }

    private void j(final Context context) {
        k.b(context, "periodicInfoEventPaused", (Boolean) false);
        k.b(context, "periodicMetadataPaused", (Boolean) false);
        AnonymousClass1 r0 = new com.startapp.android.publish.common.metaData.d() {
            public void a() {
                if (MetaData.getInstance().isUserAgentEnabled()) {
                    m.this.a(context, TimeUnit.SECONDS.toMillis(MetaData.getInstance().getUserAgentDelayInSeconds()));
                }
                b.c(context);
                b.d(context);
                m.this.e(context);
                if (Build.VERSION.SDK_INT <= 8) {
                    return;
                }
                if (MetaData.getInstance().getTrueNetEnabled()) {
                    if (!m.this.z) {
                        boolean unused = m.this.z = true;
                        Context context = context;
                        TrueNetSDK.with(context, k.a(context, "shared_prefs_appId", (String) null));
                    }
                    TrueNetSDK.enable(context, true);
                } else if (m.this.z) {
                    TrueNetSDK.enable(context, false);
                }
            }

            public void b() {
                g.a("StartAppSDKInternal", 3, "setPeriodicAlarms: onFailedLoadingMeta");
                if (MetaData.getInstance().isUserAgentEnabled()) {
                    m.this.a(context, TimeUnit.SECONDS.toMillis(10));
                }
            }
        };
        if (MetaData.getInstance().isReady()) {
            r0.a();
        } else {
            MetaData.getInstance().addMetaDataListener(r0);
        }
    }

    /* access modifiers changed from: package-private */
    public void e(Context context) {
        com.startapp.android.publish.adsCommon.f.c cVar = new com.startapp.android.publish.adsCommon.f.c(context, false);
        cVar.c().c(AdsConstants.j);
        cVar.a();
        if (e()) {
            f.a(context, d.GENERAL, "packagingType", AdsConstants.j, "");
        }
    }

    private void k(Context context) {
        MetaData.init(context);
        if (!i.a()) {
            b.a(context);
            if (i.a(16) || i.a(32)) {
                com.startapp.android.publish.ads.banner.c.a(context);
            }
            if (i.a(8)) {
                com.startapp.android.publish.ads.splash.f.a(context);
            }
            if (this.b) {
                com.startapp.android.publish.cache.d.a(context);
            }
            if (i.e()) {
                com.startapp.android.publish.adsCommon.adinformation.a.a(context);
            }
        }
    }

    private void l(Context context) {
        Integer a2 = k.a(context, "shared_prefs_app_version_id", (Integer) -1);
        int d2 = com.startapp.common.a.c.d(context);
        if (a2.intValue() > 0 && d2 > a2.intValue()) {
            this.q = true;
        }
        k.b(context, "shared_prefs_app_version_id", Integer.valueOf(d2));
    }

    private void m(Context context) {
        if (this.q || !com.startapp.android.publish.cache.d.a().b().isLocalCache()) {
            g.a("StartAppSDKInternal", 3, "App version changed or disabled in meta - deleting existing cache");
            com.startapp.android.publish.cache.a.a().b(context);
        } else if (this.c) {
            com.startapp.android.publish.cache.a.a().a(context);
        }
        p(context);
        com.startapp.android.publish.cache.a.a().c(context);
    }

    private void a(Context context, boolean z2) {
        g(false);
        f(false);
        if (!com.startapp.common.a.c.b() || !i.a(2)) {
            g.a("StartAppSDKInternal", 6, "Cannot activate return interstitials, cache to disk, ttl reload - api lower than 14");
            return;
        }
        g(z2);
        f(true);
        g.a("StartAppSDKInternal", 3, "Return Ads: [" + z2 + "]");
    }

    private void n(final Context context) {
        g.a("StartAppSDKInternal", 3, "Sending Download Event");
        com.startapp.common.g.a(g.a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                try {
                    j jVar = new j(context);
                    AdPreferences adPreferences = new AdPreferences();
                    i.a(context, adPreferences);
                    jVar.fillApplicationDetails(context, adPreferences);
                    com.startapp.android.publish.adsCommon.k.a.a(context, AdsConstants.a(AdsConstants.ServiceApiType.DOWNLOAD), jVar, (Map<String, String>) null);
                } catch (Exception e) {
                    com.startapp.common.a.g.a(6, "Error occured while sending download event", (Throwable) e);
                    f.a(context, d.EXCEPTION, "StartAppSDKInternal.sendDownloadEvent", e.getMessage(), "");
                }
            }
        });
    }

    public void f(Context context) {
        if (com.startapp.common.a.c.b()) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                this.k = activity;
                this.h = activity.getApplication();
            } else if (context.getApplicationContext() instanceof Application) {
                this.h = (Application) context.getApplicationContext();
            } else {
                com.startapp.common.a.g.a("StartAppSDKInternal", 6, "Cannot register activity life cycle callbacks - context is not an Activity");
                return;
            }
            try {
                if (!(this.j == null || this.h == null)) {
                    a(this.h, this.j);
                    com.startapp.common.a.g.a("StartAppSDKInternal", 3, "Unregistered LifeCycle Callbacks");
                }
            } catch (Exception unused) {
            }
            com.startapp.common.a.g.a("StartAppSDKInternal", 3, "Registring LifeCycle Callbacks");
            this.j = a(this.h);
            return;
        }
        com.startapp.common.a.g.a("StartAppSDKInternal", 6, "Cannot register activity life cycle callbacks - api lower than 14");
    }

    public void a(Activity activity, Bundle bundle) {
        if (bundle == null && this.m != null && activity.getClass().getName().equals(this.m)) {
            this.l = false;
        }
        this.s = bundle;
    }

    public void a(Activity activity) {
        com.startapp.common.a.g.a("StartAppSDKInternal", 3, "onActivityStarted [" + activity.getClass().getName() + "]");
        if (i.a(8) && !b.a().z() && !this.w && !b(MoPubLog.LOGTAG) && !b("AdMob") && !this.x && activity.getClass().getName().equals(this.m) && !i() && this.i.size() == 0) {
            StartAppAd.showSplash(activity, this.s, new SplashConfig(), new AdPreferences(), (SplashHideListener) null, false);
        }
        this.x = true;
        if (this.d) {
            g(activity);
        }
        this.f = false;
        this.d = false;
        if (this.i.get(Integer.valueOf(activity.hashCode())) == null) {
            this.i.put(Integer.valueOf(activity.hashCode()), Integer.valueOf(new Integer(0).intValue() + 1));
            com.startapp.common.a.g.a("StartAppSDKInternal", 3, "Activity Added:[" + activity.getClass().getName() + "]");
            return;
        }
        com.startapp.common.a.g.a("StartAppSDKInternal", 3, "Activity [" + activity.getClass().getName() + "] already exists");
    }

    public void b(Activity activity) {
        if (this.b && this.e) {
            this.e = false;
            com.startapp.android.publish.cache.a.a().b();
        }
        if (this.n) {
            this.n = false;
            l.c(activity.getApplicationContext());
        }
        this.k = activity;
    }

    public void b() {
        this.n = true;
        this.e = true;
    }

    public boolean c() {
        return this.p;
    }

    public void a(boolean z2) {
        this.p = z2;
    }

    public boolean d() {
        return this.o;
    }

    public void b(boolean z2) {
        this.o = z2;
    }

    public boolean e() {
        return this.q;
    }

    public void c(Activity activity) {
        this.g = System.currentTimeMillis();
        this.k = null;
    }

    public void d(Activity activity) {
        com.startapp.common.a.g.a("StartAppSDKInternal", 3, "onActivityStopped [" + activity.getClass().getName() + "]");
        Integer num = this.i.get(Integer.valueOf(activity.hashCode()));
        if (num != null) {
            Integer valueOf = Integer.valueOf(num.intValue() - 1);
            if (valueOf.intValue() == 0) {
                this.i.remove(Integer.valueOf(activity.hashCode()));
            } else {
                this.i.put(Integer.valueOf(activity.hashCode()), valueOf);
            }
            com.startapp.common.a.g.a("StartAppSDKInternal", 3, "Activity removed:[" + activity.getClass().getName() + "]");
            if (this.i.size() == 0) {
                if (!this.f) {
                    f(activity);
                }
                if (this.b) {
                    com.startapp.android.publish.cache.a.a().a(activity.getApplicationContext(), this.f);
                    this.e = true;
                    return;
                }
                return;
            }
            return;
        }
        com.startapp.common.a.g.a("StartAppSDKInternal", 3, "Activity hadn't been found:[" + activity.getClass().getName() + "]");
    }

    public void e(Activity activity) {
        if (activity.getClass().getName().equals(this.m)) {
            this.x = false;
        }
        if (this.i.size() == 0) {
            this.d = false;
        }
    }

    public boolean f() {
        Activity activity = this.k;
        if (activity != null) {
            return activity.isTaskRoot();
        }
        return true;
    }

    public String g() {
        return this.m;
    }

    public boolean h() {
        return this.u;
    }

    public boolean i() {
        return this.v;
    }

    public void c(boolean z2) {
        this.v = z2;
    }

    @Deprecated
    public void j() {
        d(false);
    }

    public void d(boolean z2) {
        this.w = !z2;
        if (!z2) {
            com.startapp.android.publish.cache.a.a().a(AdPreferences.Placement.INAPP_SPLASH);
        }
    }

    public boolean k() {
        return !this.w;
    }

    public boolean l() {
        return this.c && !this.d && !this.f;
    }

    public boolean a(AdPreferences.Placement placement) {
        if (!this.c || this.f) {
            return false;
        }
        if (!this.d) {
            return true;
        }
        if (placement != AdPreferences.Placement.INAPP_RETURN || !com.startapp.android.publish.cache.d.a().b().shouldReturnAdLoadInBg()) {
            return false;
        }
        return true;
    }

    private void f(boolean z2) {
        this.c = z2;
    }

    private void g(boolean z2) {
        this.u = z2;
    }

    public void m() {
        this.d = false;
        this.f = true;
    }

    public boolean n() {
        return this.c && this.d;
    }

    public void a(Context context, String str, String str2) {
        if (this.r == null) {
            this.r = new TreeMap();
        }
        this.r.put(str, str2);
        o(context);
    }

    private void o(Context context) {
        k.a(context, "sharedPrefsWrappers", this.r);
    }

    public String a(String str) {
        Map<String, String> map = this.r;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public Map<String, String> o() {
        return this.r;
    }

    public boolean b(String str) {
        return a(str) != null;
    }

    public SDKAdPreferences g(Context context) {
        if (this.f272a == null) {
            SDKAdPreferences sDKAdPreferences = (SDKAdPreferences) e.a(context, "shared_prefs_sdk_ad_prefs", SDKAdPreferences.class);
            if (sDKAdPreferences == null) {
                this.f272a = new SDKAdPreferences();
            } else {
                this.f272a = sDKAdPreferences;
            }
        }
        return this.f272a;
    }

    private void f(Activity activity) {
        this.d = true;
        p(activity);
        if (com.startapp.common.c.a() != null) {
            com.startapp.common.c.a().b(activity);
        }
    }

    private void g(Activity activity) {
        if (MetaData.getInstance().canShowAd() && h() && !b.a().y() && !i.a() && !c() && q()) {
            g a2 = com.startapp.android.publish.cache.a.a().a(this.t);
            this.y = a2;
            if (a2 != null && a2.isReady()) {
                com.startapp.android.publish.adsCommon.a.f a3 = b.a().F().a(AdPreferences.Placement.INAPP_RETURN, (String) null);
                if (!a3.a()) {
                    c.a((Context) activity, ((com.startapp.android.publish.ads.b.e) this.y).l(), (String) null, a3.c());
                    if (Constants.a().booleanValue()) {
                        com.startapp.common.a.i.a().a(activity, a3.b());
                    }
                } else if (this.y.a((String) null)) {
                    com.startapp.android.publish.adsCommon.a.b.a().a(new com.startapp.android.publish.adsCommon.a.a(AdPreferences.Placement.INAPP_RETURN, (String) null));
                }
            }
        }
        if (com.startapp.common.c.a() != null) {
            com.startapp.common.c.a().a(activity);
        }
        if (r()) {
            a((Context) activity, MetaDataRequest.a.APP_IDLE);
        }
    }

    private boolean q() {
        return System.currentTimeMillis() - this.g > b.a().x();
    }

    private boolean r() {
        return System.currentTimeMillis() - this.g > MetaData.getInstance().getSessionMaxBackgroundTime();
    }

    /* access modifiers changed from: protected */
    public void a(Context context, MetaDataRequest.a aVar) {
        com.startapp.android.publish.adsCommon.Utils.g.d().a(context, aVar);
    }

    /* access modifiers changed from: protected */
    public void e(boolean z2) {
        boolean z3 = z2 && com.startapp.common.a.c.b();
        g(z3);
        if (!z3) {
            com.startapp.android.publish.cache.a.a().a(AdPreferences.Placement.INAPP_RETURN);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Context context, String str, long j2, boolean z2, boolean z3) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            String str2 = "1";
            sb.append(z2 ? str2 : "0");
            sb.append(z3 ? "M" : "A");
            f.a(context, d.USER_CONSENT, str, sb.toString(), "");
            if (str.toLowerCase().equals("pas")) {
                if (!z2) {
                    str2 = "0";
                }
                k.b(context, "USER_CONSENT_PERSONALIZED_ADS_SERVING", str2);
                com.startapp.android.publish.adsCommon.Utils.g.d().a(context, MetaDataRequest.a.PAS);
                return;
            }
            return;
        }
        com.startapp.common.a.g.a("StartAppSDKInternal", 6, "setUserConsent: empty consentType");
    }

    public void h(Context context) {
        b(context, "android.permission.ACCESS_FINE_LOCATION", "USER_CONSENT_FINE_LOCATION");
        b(context, "android.permission.ACCESS_COARSE_LOCATION", "USER_CONSENT_COARSE_LOCATION");
    }

    private void b(Context context, String str, String str2) {
        boolean booleanValue = k.a(context, str2, (Boolean) false).booleanValue();
        boolean a2 = com.startapp.common.a.c.a(context, str);
        if (booleanValue != a2) {
            k.b(context, str2, Boolean.valueOf(a2));
            a(context, str, System.currentTimeMillis(), a2, false);
        }
    }

    private void p(Context context) {
        a(context, new AdPreferences());
    }

    private void a(Context context, AdPreferences adPreferences) {
        if (h() && !b.a().y()) {
            this.t = com.startapp.android.publish.cache.a.a().a(context, adPreferences);
        }
    }

    private static void q(Context context) {
        TreeMap treeMap = new TreeMap();
        if (u()) {
            treeMap.put("Cordova", i.b());
        }
        if (s()) {
            treeMap.put("AdMob", d("com.startapp.android.mediation.admob"));
        }
        if (t()) {
            treeMap.put(MoPubLog.LOGTAG, d("com.mopub.mobileads"));
        }
        if (v() && !a().o().containsKey("B4A")) {
            treeMap.put(MoPubLog.LOGTAG, "0");
        }
        if (!treeMap.isEmpty()) {
            k.a(context, "sharedPrefsWrappers", (Map<String, String>) treeMap);
        }
    }

    private static boolean s() {
        return c("com.startapp.android.mediation.admob.StartAppCustomEvent");
    }

    private static boolean t() {
        return c("com.mopub.mobileads.StartAppCustomEventInterstitial");
    }

    public static boolean p() {
        return a().a("Unity") != null;
    }

    private static boolean u() {
        return c("org.apache.cordova.CordovaPlugin");
    }

    private static boolean v() {
        return c("anywheresoftware.b4a.BA");
    }

    private static boolean c(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException | Exception unused) {
            return false;
        }
    }

    private static String d(String str) {
        try {
            return (String) Class.forName(str + ".StartAppConstants").getField("WRAPPER_VERSION").get((Object) null);
        } catch (Exception unused) {
            return "0";
        }
    }

    public static Object a(Application application) {
        AnonymousClass3 r0 = new Application.ActivityLifecycleCallbacks() {
            public void onActivityStopped(Activity activity) {
                com.startapp.common.a.g.a("StartAppSDKInternal", 3, "onActivityStopped [" + activity.getClass().getName() + "]");
                m.a().d(activity);
            }

            public void onActivityStarted(Activity activity) {
                com.startapp.common.a.g.a("StartAppSDKInternal", 3, "onActivityStarted [" + activity.getClass().getName() + "]");
                m.a().a(activity);
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                com.startapp.common.a.g.a("StartAppSDKInternal", 3, "onActivitySaveInstanceState [" + activity.getClass().getName() + "]");
                m.a().b(activity, bundle);
            }

            public void onActivityResumed(Activity activity) {
                com.startapp.common.a.g.a("StartAppSDKInternal", 3, "onActivityResumed [" + activity.getClass().getName() + "]");
                m.a().b(activity);
            }

            public void onActivityPaused(Activity activity) {
                com.startapp.common.a.g.a("StartAppSDKInternal", 3, "onActivityPaused [" + activity.getClass().getName() + "]");
                m.a().c(activity);
            }

            public void onActivityDestroyed(Activity activity) {
                com.startapp.common.a.g.a("StartAppSDKInternal", 3, "onActivityDestroyed [" + activity.getClass().getName() + "]");
                m.a().e(activity);
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                com.startapp.common.a.g.a("StartAppSDKInternal", 3, "onActivityCreated [" + activity.getClass().getName() + ", " + bundle + "]");
                m.a().a(activity, bundle);
                if (i.a(2)) {
                    f.a().a(activity, bundle);
                }
            }
        };
        application.registerActivityLifecycleCallbacks(r0);
        return r0;
    }

    public static void a(Application application, Object obj) {
        application.unregisterActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks) obj);
    }

    /* access modifiers changed from: private */
    public void a(final Context context, long j2) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                try {
                    k.b(context, "User-Agent", new WebView(context).getSettings().getUserAgentString());
                } catch (Exception e) {
                    f.a(context, d.EXCEPTION, "NetworkUtils.saveUserAgent - Webview failed", e.getMessage(), "");
                }
            }
        }, j2);
    }

    private void r(final Context context) {
        com.startapp.common.g.a(g.a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                String i;
                if (Build.VERSION.SDK_INT < 18 || (i = m.s(context)) == null) {
                    m.this.b(context, false);
                    return;
                }
                ServiceConnection unused = m.this.A = new ServiceConnection() {
                    public void onServiceDisconnected(ComponentName componentName) {
                    }

                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        m.this.b(context, true);
                        context.unbindService(m.this.A);
                    }
                };
                try {
                    Intent intent = new Intent("android.support.customtabs.action.CustomTabsService");
                    intent.setPackage(i);
                    if (!context.bindService(intent, m.this.A, 33)) {
                        m.this.b(context, false);
                    }
                } catch (Exception e) {
                    com.startapp.common.a.g.a("StartAppSDKInternal", 6, "failed to check checkChromeTabsSupport", e);
                    m.this.b(context, false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static String s(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            String str = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            ArrayList arrayList = new ArrayList();
            for (ResolveInfo next : queryIntentActivities) {
                Intent intent2 = new Intent();
                intent2.setAction("android.support.customtabs.action.CustomTabsService");
                intent2.setPackage(next.activityInfo.packageName);
                if (packageManager.resolveService(intent2, 0) != null) {
                    arrayList.add(next.activityInfo.packageName);
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            if (arrayList.size() == 1) {
                return (String) arrayList.get(0);
            }
            if (!TextUtils.isEmpty(str) && !a(context, intent) && arrayList.contains(str)) {
                return str;
            }
            if (arrayList.contains("com.android.chrome")) {
                return "com.android.chrome";
            }
            return null;
        } catch (Exception unused) {
            com.startapp.common.a.g.a("StartAppSDKInternal", 6, "Exception inside getPackageNameToUse");
            return null;
        }
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities != null) {
                if (queryIntentActivities.size() != 0) {
                    for (ResolveInfo next : queryIntentActivities) {
                        IntentFilter intentFilter = next.filter;
                        if (intentFilter != null) {
                            if (intentFilter.countDataAuthorities() == 0) {
                                continue;
                            } else if (intentFilter.countDataPaths() != 0) {
                                if (next.activityInfo != null) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (RuntimeException unused) {
            com.startapp.common.a.g.a("StartAppSDKInternal", 6, "Runtime exception while getting specialized handlers");
        }
    }

    /* access modifiers changed from: private */
    public void b(Context context, boolean z2) {
        k.b(context, "chromeTabs", Boolean.valueOf(z2));
    }
}
