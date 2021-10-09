package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

final class zzbds extends zzbdy {
    private final /* synthetic */ zzbdp zzdyq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzbds(zzbdp zzbdp) {
        super(zzbdp, (zzbdq) null);
        this.zzdyq = zzbdp;
    }

    /* synthetic */ zzbds(zzbdp zzbdp, zzbdq zzbdq) {
        this(zzbdp);
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzbdr(this.zzdyq, (zzbdq) null);
    }
}
