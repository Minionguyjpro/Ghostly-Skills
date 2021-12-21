package com.startapp.android.publish.ads.video.c.a;

import android.content.Context;
import com.startapp.android.publish.ads.video.c.a.a.b;
import java.util.Comparator;

/* compiled from: StartAppSDK */
public class d extends c {
    /* access modifiers changed from: private */
    public final double c;
    /* access modifiers changed from: private */
    public final int d = (this.f126a * this.b);
    /* access modifiers changed from: private */
    public final int e;

    /* access modifiers changed from: private */
    public static int b(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public d(Context context, int i) {
        super(context);
        this.e = i;
        double d2 = (double) this.f126a;
        double d3 = (double) this.b;
        Double.isNaN(d2);
        Double.isNaN(d3);
        this.c = d2 / d3;
    }

    /* access modifiers changed from: protected */
    public Comparator<b> a() {
        return new Comparator<b>() {
            /* renamed from: a */
            public int compare(b bVar, b bVar2) {
                double doubleValue = d.this.a(bVar.d().intValue(), bVar.e().intValue(), d.this.c, d.this.d).doubleValue();
                double doubleValue2 = d.this.a(bVar2.d().intValue(), bVar2.e().intValue(), d.this.c, d.this.d).doubleValue();
                if (doubleValue < doubleValue2) {
                    return -1;
                }
                if (doubleValue > doubleValue2) {
                    return 1;
                }
                Integer c = bVar.c();
                Integer c2 = bVar2.c();
                if (c == null && c2 == null) {
                    return 0;
                }
                if (c == null) {
                    return 1;
                }
                if (c2 == null) {
                    return -1;
                }
                return d.b(Integer.valueOf(Math.abs(d.this.e - c.intValue())).intValue(), Integer.valueOf(Math.abs(d.this.e - c2.intValue())).intValue());
            }
        };
    }

    /* access modifiers changed from: private */
    public Double a(int i, int i2, double d2, int i3) {
        double d3 = (double) i;
        double d4 = (double) i2;
        Double.isNaN(d3);
        Double.isNaN(d4);
        double d5 = (d3 / d4) / d2;
        double d6 = (double) (i * i2);
        double d7 = (double) i3;
        Double.isNaN(d6);
        Double.isNaN(d7);
        return Double.valueOf((Math.abs(Math.log(d5)) * 40.0d) + (Math.abs(Math.log(d6 / d7)) * 60.0d));
    }
}
