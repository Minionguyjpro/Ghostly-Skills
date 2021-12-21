package com.tappx.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public final class i1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f466a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;

    public i1(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.f466a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
    }

    public static class a {
        private static volatile a b;

        /* renamed from: a  reason: collision with root package name */
        private final Context f467a;

        public a(Context context) {
            this.f467a = context;
        }

        public static final a a(Context context) {
            if (b == null) {
                synchronized (a.class) {
                    if (b == null) {
                        b = new a(context.getApplicationContext());
                    }
                }
            }
            return b;
        }

        private String b(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getNetworkOperator();
            } catch (Exception unused) {
                return null;
            }
        }

        private String c(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getNetworkOperatorName();
            } catch (Exception unused) {
                return null;
            }
        }

        private String d(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getSimCountryIso();
            } catch (Exception unused) {
                return null;
            }
        }

        private String e(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getSimOperator();
            } catch (Exception unused) {
                return null;
            }
        }

        private String f(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getSimOperatorName();
            } catch (Exception unused) {
                return null;
            }
        }

        public i1 a() {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            NetworkInfo activeNetworkInfo;
            if (!d3.a(this.f467a, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = ((ConnectivityManager) this.f467a.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
                str2 = null;
                str = null;
            } else {
                String typeName = activeNetworkInfo.getTypeName();
                str = activeNetworkInfo.getSubtypeName();
                str2 = typeName;
            }
            TelephonyManager telephonyManager = (TelephonyManager) this.f467a.getSystemService("phone");
            if (telephonyManager != null) {
                String a2 = a(e(telephonyManager));
                String a3 = a(f(telephonyManager));
                String a4 = a(d(telephonyManager));
                String a5 = a(b(telephonyManager));
                String a6 = a(c(telephonyManager));
                str3 = a(a(telephonyManager));
                str6 = a4;
                str5 = a5;
                str4 = a6;
                str8 = a2;
                str7 = a3;
            } else {
                str8 = null;
                str7 = null;
                str6 = null;
                str5 = null;
                str4 = null;
                str3 = null;
            }
            return new i1(str2, str, str8, str7, str6, str5, str4, str3);
        }

        private String a(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getNetworkCountryIso();
            } catch (Exception unused) {
                return null;
            }
        }

        private String a(String str) {
            if (str == null || !str.trim().isEmpty()) {
                return str;
            }
            return null;
        }
    }
}
