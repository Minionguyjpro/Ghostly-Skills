package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzbdw implements Comparable<zzbdw>, Map.Entry<K, V> {
    private V value;
    private final /* synthetic */ zzbdp zzdyq;
    private final K zzdyt;

    zzbdw(zzbdp zzbdp, K k, V v) {
        this.zzdyq = zzbdp;
        this.zzdyt = k;
        this.value = v;
    }

    zzbdw(zzbdp zzbdp, Map.Entry<K, V> entry) {
        this(zzbdp, (Comparable) entry.getKey(), entry.getValue());
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zzbdw) obj).getKey());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return equals(this.zzdyt, entry.getKey()) && equals(this.value, entry.getValue());
    }

    public final /* synthetic */ Object getKey() {
        return this.zzdyt;
    }

    public final V getValue() {
        return this.value;
    }

    public final int hashCode() {
        K k = this.zzdyt;
        int i = 0;
        int hashCode = k == null ? 0 : k.hashCode();
        V v = this.value;
        if (v != null) {
            i = v.hashCode();
        }
        return hashCode ^ i;
    }

    public final V setValue(V v) {
        this.zzdyq.zzafv();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzdyt);
        String valueOf2 = String.valueOf(this.value);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        return sb.toString();
    }
}
