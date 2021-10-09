package com.startapp.common;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: StartAppSDK */
public class c {
    private static c b;

    /* renamed from: a  reason: collision with root package name */
    protected String f356a = "e106";
    private Context c;
    private PhoneStateListener d = c();

    private c(Context context) {
        this.c = context.getApplicationContext();
    }

    public void a(Context context) {
        a(context, 256);
    }

    public void b(Context context) {
        a(context, 0);
    }

    private void a(Context context, int i) {
        try {
            ((TelephonyManager) context.getSystemService("phone")).listen(this.d, i);
        } catch (Exception unused) {
        }
    }

    public static void c(Context context) {
        if (b == null) {
            b = new c(context);
            a().a(context);
        }
    }

    public static c a() {
        return b;
    }

    public String b() {
        return this.f356a;
    }

    private PhoneStateListener c() {
        try {
            return new PhoneStateListener() {
                public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                    try {
                        Method method = SignalStrength.class.getMethod("getLevel", (Class[]) null);
                        c.this.f356a = Integer.toString(((Integer) method.invoke(signalStrength, (Object[]) null)).intValue());
                    } catch (NoSuchMethodException unused) {
                        c.this.f356a = "e104";
                    } catch (IllegalAccessException unused2) {
                        c.this.f356a = "e105";
                    } catch (IllegalArgumentException unused3) {
                        c.this.f356a = "e105";
                    } catch (InvocationTargetException unused4) {
                        c.this.f356a = "e105";
                    }
                }
            };
        } catch (Exception unused) {
            return null;
        }
    }
}
