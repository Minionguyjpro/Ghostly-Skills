package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzarr;
import com.google.android.gms.internal.ads.zzarz;
import com.google.android.gms.internal.ads.zzasb;

@zzadh
public final class zzaro<WebViewT extends zzarr & zzarz & zzasb> {
    private final zzarq zzdem;
    private final WebViewT zzden;

    private zzaro(WebViewT webviewt, zzarq zzarq) {
        this.zzdem = zzarq;
        this.zzden = webviewt;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.gms.internal.ads.zzarq, com.google.android.gms.internal.ads.zzarp] */
    public static zzaro<zzaqw> zzk(zzaqw zzaqw) {
        return new zzaro<>(zzaqw, new zzarp(zzaqw));
    }
}
