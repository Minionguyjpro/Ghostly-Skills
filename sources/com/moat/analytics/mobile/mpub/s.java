package com.moat.analytics.mobile.mpub;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.mopub.common.GpsHelper;
import java.lang.ref.WeakReference;

class s {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static String f1182a;
    private static a b;
    private static b c;

    static class a {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public boolean f1184a;
        private String b;
        private String c;
        private String d;

        private a() {
            this.f1184a = false;
            this.b = "_unknown_";
            this.c = "_unknown_";
            this.d = "_unknown_";
            try {
                Context b2 = s.b();
                if (b2 != null) {
                    this.f1184a = true;
                    PackageManager packageManager = b2.getPackageManager();
                    this.c = b2.getPackageName();
                    this.b = packageManager.getApplicationLabel(b2.getApplicationInfo()).toString();
                    this.d = packageManager.getInstallerPackageName(this.c);
                    return;
                }
                p.a(3, "Util", (Object) this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                n.a(e);
            }
        }

        /* access modifiers changed from: package-private */
        public String a() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public String b() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public String c() {
            String str = this.d;
            return str != null ? str : "_unknown_";
        }
    }

    static class b {

        /* renamed from: a  reason: collision with root package name */
        String f1185a;
        String b;
        Integer c;
        boolean d;
        boolean e;
        boolean f;

        private b() {
            this.f1185a = "_unknown_";
            this.b = "_unknown_";
            this.c = -1;
            this.d = false;
            this.e = false;
            this.f = false;
            try {
                Context b2 = s.b();
                if (b2 != null) {
                    this.f = true;
                    TelephonyManager telephonyManager = (TelephonyManager) b2.getSystemService("phone");
                    this.f1185a = telephonyManager.getSimOperatorName();
                    this.b = telephonyManager.getNetworkOperatorName();
                    this.c = Integer.valueOf(telephonyManager.getPhoneType());
                    this.d = s.g();
                    this.e = s.b(b2);
                }
            } catch (Exception e2) {
                n.a(e2);
            }
        }
    }

    s() {
    }

    static String a() {
        return f1182a;
    }

    static void a(final Context context) {
        try {
            AsyncTask.execute(new Runnable() {
                public void run() {
                    String str;
                    String str2;
                    try {
                        Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                        Class<?> cls2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                        Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
                        if (!((Boolean) cls2.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0])).booleanValue()) {
                            String unused = s.f1182a = (String) cls2.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
                            str2 = "Retrieved Advertising ID = " + s.f1182a;
                        } else {
                            str2 = "User has limited ad tracking";
                        }
                        p.a(3, "Util", (Object) this, str2);
                    } catch (ClassNotFoundException e) {
                        e = e;
                        str = "ClassNotFoundException while retrieving Advertising ID";
                        p.a("Util", (Object) this, str, e);
                    } catch (NoSuchMethodException e2) {
                        e = e2;
                        str = "NoSuchMethodException while retrieving Advertising ID";
                        p.a("Util", (Object) this, str, e);
                    } catch (Exception e3) {
                        n.a(e3);
                    }
                }
            });
        } catch (Exception e) {
            n.a(e);
        }
    }

    static Context b() {
        WeakReference<Context> weakReference = ((k) MoatAnalytics.getInstance()).d;
        if (weakReference != null) {
            return (Context) weakReference.get();
        }
        return null;
    }

    static boolean b(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    static a c() {
        a aVar = b;
        if (aVar == null || !aVar.f1184a) {
            b = new a();
        }
        return b;
    }

    static b d() {
        b bVar = c;
        if (bVar == null || !bVar.f) {
            c = new b();
        }
        return c;
    }

    /* access modifiers changed from: private */
    public static boolean g() {
        int i;
        Context b2 = b();
        if (b2 != null) {
            int i2 = Build.VERSION.SDK_INT;
            ContentResolver contentResolver = b2.getContentResolver();
            i = i2 >= 17 ? Settings.Global.getInt(contentResolver, "adb_enabled", 0) : Settings.Secure.getInt(contentResolver, "adb_enabled", 0);
        } else {
            i = 0;
        }
        return i == 1;
    }
}
