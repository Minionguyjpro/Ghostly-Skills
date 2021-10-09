package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzxi implements Callable<zzxe> {
    private final /* synthetic */ zzxb zzbuj;
    private final /* synthetic */ zzxh zzbuk;

    zzxi(zzxh zzxh, zzxb zzxb) {
        this.zzbuk = zzxh;
        this.zzbuj = zzxb;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzmn */
    public final zzxe call() throws Exception {
        synchronized (this.zzbuk.mLock) {
            if (this.zzbuk.zzbuf) {
                return null;
            }
            return this.zzbuj.zza(this.zzbuk.mStartTime, this.zzbuk.zzbud);
        }
    }
}
