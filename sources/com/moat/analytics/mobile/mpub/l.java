package com.moat.analytics.mobile.mpub;

import android.app.Application;
import android.media.AudioManager;

public class l {

    /* renamed from: a  reason: collision with root package name */
    private static final Long f1174a = 200L;
    private static final l b = new l();
    private AudioManager c;
    private double d = 0.0d;
    private Long e;

    private l() {
        c();
    }

    public static l a() {
        return b;
    }

    private void c() {
        Application a2 = a.a();
        if (a2 != null) {
            this.c = (AudioManager) a2.getSystemService("audio");
        }
    }

    private AudioManager d() {
        if (this.c == null) {
            c();
        }
        return this.c;
    }

    private void e() {
        try {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            if (this.e == null || valueOf.longValue() - this.e.longValue() > f1174a.longValue()) {
                this.e = valueOf;
                AudioManager d2 = d();
                if (d2 != null) {
                    double streamVolume = (double) d2.getStreamVolume(3);
                    double streamMaxVolume = (double) d2.getStreamMaxVolume(3);
                    Double.isNaN(streamVolume);
                    Double.isNaN(streamMaxVolume);
                    this.d = streamVolume / streamMaxVolume;
                }
            }
        } catch (Exception e2) {
            n.a(e2);
            this.d = 0.0d;
        }
    }

    /* access modifiers changed from: package-private */
    public double b() {
        try {
            e();
            return this.d;
        } catch (Exception e2) {
            n.a(e2);
            return 0.0d;
        }
    }
}
