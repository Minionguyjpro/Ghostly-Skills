package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

final class zzbca<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzdvj;

    public zzbca(Iterator<Map.Entry<K, Object>> it) {
        this.zzdvj = it;
    }

    public final boolean hasNext() {
        return this.zzdvj.hasNext();
    }

    public final /* synthetic */ Object next() {
        Map.Entry next = this.zzdvj.next();
        return next.getValue() instanceof zzbbx ? new zzbbz(next) : next;
    }

    public final void remove() {
        this.zzdvj.remove();
    }
}
