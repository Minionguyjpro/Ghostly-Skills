package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

final class zzbdx implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzdyp;
    private final /* synthetic */ zzbdp zzdyq;
    private boolean zzdyu;

    private zzbdx(zzbdp zzbdp) {
        this.zzdyq = zzbdp;
        this.pos = -1;
    }

    /* synthetic */ zzbdx(zzbdp zzbdp, zzbdq zzbdq) {
        this(zzbdp);
    }

    private final Iterator<Map.Entry<K, V>> zzafx() {
        if (this.zzdyp == null) {
            this.zzdyp = this.zzdyq.zzdyl.entrySet().iterator();
        }
        return this.zzdyp;
    }

    public final boolean hasNext() {
        return this.pos + 1 < this.zzdyq.zzdyk.size() || (!this.zzdyq.zzdyl.isEmpty() && zzafx().hasNext());
    }

    public final /* synthetic */ Object next() {
        this.zzdyu = true;
        int i = this.pos + 1;
        this.pos = i;
        return (Map.Entry) (i < this.zzdyq.zzdyk.size() ? this.zzdyq.zzdyk.get(this.pos) : zzafx().next());
    }

    public final void remove() {
        if (this.zzdyu) {
            this.zzdyu = false;
            this.zzdyq.zzafv();
            if (this.pos < this.zzdyq.zzdyk.size()) {
                zzbdp zzbdp = this.zzdyq;
                int i = this.pos;
                this.pos = i - 1;
                Object unused = zzbdp.zzcz(i);
                return;
            }
            zzafx().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
