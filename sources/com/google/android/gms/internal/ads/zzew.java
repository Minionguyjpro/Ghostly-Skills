package com.google.android.gms.internal.ads;

import android.database.ContentObserver;
import android.os.Handler;

final class zzew extends ContentObserver {
    private final /* synthetic */ zzet zzafk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzew(zzet zzet, Handler handler) {
        super(handler);
        this.zzafk = zzet;
    }

    public final void onChange(boolean z) {
        super.onChange(z);
        this.zzafk.zzgb();
    }
}
