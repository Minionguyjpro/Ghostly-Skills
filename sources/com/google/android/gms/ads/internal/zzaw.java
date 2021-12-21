package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class zzaw implements zzv<zzaqw> {
    private final /* synthetic */ CountDownLatch zzwd;

    zzaw(CountDownLatch countDownLatch) {
        this.zzwd = countDownLatch;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzakb.zzdk("Adapter returned an ad, but assets substitution failed");
        this.zzwd.countDown();
        ((zzaqw) obj).destroy();
    }
}
