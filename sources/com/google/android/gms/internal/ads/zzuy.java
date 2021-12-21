package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.common.util.Predicate;

final /* synthetic */ class zzuy implements Predicate {
    private final zzv zzbps;

    zzuy(zzv zzv) {
        this.zzbps = zzv;
    }

    public final boolean apply(Object obj) {
        zzv zzv = (zzv) obj;
        return (zzv instanceof zzvd) && zzvd.zza((zzvd) zzv).equals(this.zzbps);
    }
}
