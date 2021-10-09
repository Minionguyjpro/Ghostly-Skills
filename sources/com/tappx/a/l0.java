package com.tappx.a;

import android.content.Context;
import java.util.Locale;

public final class l0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f500a;
    public final String b;
    public final String c;

    public l0(String str, String str2, String str3) {
        this.b = str;
        this.f500a = str2;
        this.c = str3;
    }

    public static class a {
        private static volatile a b;

        /* renamed from: a  reason: collision with root package name */
        private final Context f501a;

        public a(Context context) {
            this.f501a = context;
        }

        public static final a a(Context context) {
            if (b == null) {
                synchronized (a.class) {
                    if (b == null) {
                        b = new a(context);
                    }
                }
            }
            return b;
        }

        private String b() {
            Locale locale = this.f501a.getResources().getConfiguration().locale;
            if (locale == null) {
                locale = Locale.getDefault();
            }
            return locale != null ? locale.getLanguage() : "en-us";
        }

        private String c() {
            return this.f501a.getApplicationInfo().loadLabel(this.f501a.getPackageManager()).toString();
        }

        public l0 a() {
            return new l0(c(), this.f501a.getPackageName(), b());
        }
    }
}
