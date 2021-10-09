package com.tappx.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import com.tappx.a.y0;
import com.tappx.sdk.android.BuildConfig;

public final class m1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f516a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final boolean f;

    public m1(String str, String str2, String str3, String str4, String str5, boolean z) {
        this.f516a = str;
        this.d = str2;
        this.b = str4;
        this.c = str5;
        this.e = str3;
        this.f = z;
    }

    public static class a {
        private static volatile a c;

        /* renamed from: a  reason: collision with root package name */
        private final Context f517a;
        private final y0 b;

        a(Context context, y0 y0Var) {
            this.f517a = context;
            this.b = y0Var;
        }

        public static final a a(Context context) {
            if (c == null) {
                synchronized (a.class) {
                    if (c == null) {
                        c = new a(context);
                    }
                }
            }
            return c;
        }

        private String c() {
            String string = Settings.Secure.getString(this.f517a.getContentResolver(), "android_id");
            if (string == null) {
                return null;
            }
            return e3.a(string);
        }

        private String d() {
            try {
                return String.valueOf(this.f517a.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        private String e() {
            int identifier = this.f517a.getResources().getIdentifier("google_play_services_version", "string", this.f517a.getPackageName());
            if (identifier == 0) {
                return null;
            }
            return String.valueOf(this.f517a.getResources().getInteger(identifier));
        }

        public String a() {
            return BuildConfig.SDK_VERSION;
        }

        public m1 b() {
            boolean z;
            String str;
            String a2 = a();
            y0.a a3 = this.b.a();
            if (a3 != null) {
                z = a3.b();
                str = a3.a();
            } else {
                j0.b(f.b("krJOYpdJwB0z9kroej+tvgvunIIlLf/GdGehIr+r2OSbd/1jAuDbW6Z7w8Rb+zP0p97z+Ss5rCSYnT4eKWDNHxv5azbxwwxG3XGQe+SC2+3s6Z9kUQ084l1qIWDEae3FGWLeg8k8luby4GoV6Q0RRg"), new Object[0]);
                str = null;
                z = false;
            }
            return new m1(a2, str, c(), d(), e(), z);
        }

        public a(Context context) {
            this(context, new y0(context));
        }
    }
}
