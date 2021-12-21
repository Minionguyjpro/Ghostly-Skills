package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzbbz<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzbbx> zzdvi;

    private zzbbz(Map.Entry<K, zzbbx> entry) {
        this.zzdvi = entry;
    }

    public final K getKey() {
        return this.zzdvi.getKey();
    }

    public final Object getValue() {
        if (this.zzdvi.getValue() == null) {
            return null;
        }
        return zzbbx.zzadu();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzbcu) {
            return this.zzdvi.getValue().zzl((zzbcu) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzbbx zzadv() {
        return this.zzdvi.getValue();
    }
}
