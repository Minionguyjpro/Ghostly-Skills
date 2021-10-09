package com.google.android.play.core.assetpacks;

import java.util.HashMap;
import java.util.Map;

final class bo {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, Double> f1053a = new HashMap();

    bo() {
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(String str) {
        this.f1053a.put(str, Double.valueOf(0.0d));
    }

    /* access modifiers changed from: package-private */
    public final synchronized double b(String str) {
        Double d = this.f1053a.get(str);
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    /* access modifiers changed from: package-private */
    public final synchronized double c(String str, cc ccVar) {
        double d;
        double d2 = (double) ((bi) ccVar).e;
        Double.isNaN(d2);
        double d3 = d2 + 1.0d;
        double d4 = (double) ((bi) ccVar).f;
        Double.isNaN(d4);
        d = d3 / d4;
        this.f1053a.put(str, Double.valueOf(d));
        return d;
    }
}
