package com.google.android.gms.internal.ads;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

class zzbdy extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzbdp zzdyq;

    private zzbdy(zzbdp zzbdp) {
        this.zzdyq = zzbdp;
    }

    /* synthetic */ zzbdy(zzbdp zzbdp, zzbdq zzbdq) {
        this(zzbdp);
    }

    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzdyq.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public void clear() {
        this.zzdyq.clear();
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zzdyq.get(entry.getKey());
        Object value = entry.getValue();
        if (obj2 != value) {
            return obj2 != null && obj2.equals(value);
        }
        return true;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new zzbdx(this.zzdyq, (zzbdq) null);
    }

    public boolean remove(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzdyq.remove(entry.getKey());
        return true;
    }

    public int size() {
        return this.zzdyq.size();
    }
}
