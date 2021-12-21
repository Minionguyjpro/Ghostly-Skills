package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzch;
import com.google.android.gms.internal.ads.zzci;
import java.util.concurrent.Callable;

final class zzbs implements Callable<zzci> {
    private final /* synthetic */ zzbp zzaba;

    zzbs(zzbp zzbp) {
        this.zzaba = zzbp;
    }

    public final /* synthetic */ Object call() throws Exception {
        return new zzci(zzch.zza(this.zzaba.zzyf.zzcw, this.zzaba.mContext, false));
    }
}
