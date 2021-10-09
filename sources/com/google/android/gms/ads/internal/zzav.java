package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.internal.ads.zzaqw;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class zzav implements zzv<zzaqw> {
    private final /* synthetic */ CountDownLatch zzwd;

    zzav(CountDownLatch countDownLatch) {
        this.zzwd = countDownLatch;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        this.zzwd.countDown();
        ((zzaqw) obj).getView().setVisibility(0);
    }
}
