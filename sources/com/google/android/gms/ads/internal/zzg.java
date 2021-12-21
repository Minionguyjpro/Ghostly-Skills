package com.google.android.gms.ads.internal;

import java.util.concurrent.Callable;

final class zzg implements Callable<String> {
    private final /* synthetic */ zzd zzwk;

    zzg(zzd zzd) {
        this.zzwk = zzd;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0012, code lost:
        r0 = com.google.android.gms.ads.internal.zzbv.zzem().zzax(r2.zzwk.zzvw.zzrt);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object call() throws java.lang.Exception {
        /*
            r2 = this;
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbdj
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0029
            com.google.android.gms.internal.ads.zzakq r0 = com.google.android.gms.ads.internal.zzbv.zzem()
            com.google.android.gms.ads.internal.zzd r1 = r2.zzwk
            com.google.android.gms.ads.internal.zzbw r1 = r1.zzvw
            android.content.Context r1 = r1.zzrt
            android.webkit.CookieManager r0 = r0.zzax(r1)
            if (r0 == 0) goto L_0x0029
            java.lang.String r1 = "googleads.g.doubleclick.net"
            java.lang.String r0 = r0.getCookie(r1)
            goto L_0x002b
        L_0x0029:
            java.lang.String r0 = ""
        L_0x002b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzg.call():java.lang.Object");
    }
}
