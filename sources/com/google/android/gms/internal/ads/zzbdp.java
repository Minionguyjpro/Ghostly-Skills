package com.google.android.gms.internal.ads;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzbdp<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzdqy;
    private final int zzdyj;
    /* access modifiers changed from: private */
    public List<zzbdw> zzdyk;
    /* access modifiers changed from: private */
    public Map<K, V> zzdyl;
    private volatile zzbdy zzdym;
    /* access modifiers changed from: private */
    public Map<K, V> zzdyn;
    private volatile zzbds zzdyo;

    private zzbdp(int i) {
        this.zzdyj = i;
        this.zzdyk = Collections.emptyList();
        this.zzdyl = Collections.emptyMap();
        this.zzdyn = Collections.emptyMap();
    }

    /* synthetic */ zzbdp(int i, zzbdq zzbdq) {
        this(i);
    }

    private final int zza(K k) {
        int size = this.zzdyk.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzdyk.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) this.zzdyk.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    /* access modifiers changed from: private */
    public final void zzafv() {
        if (this.zzdqy) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzafw() {
        zzafv();
        if (this.zzdyl.isEmpty() && !(this.zzdyl instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzdyl = treeMap;
            this.zzdyn = treeMap.descendingMap();
        }
        return (SortedMap) this.zzdyl;
    }

    static <FieldDescriptorType extends zzbbi<FieldDescriptorType>> zzbdp<FieldDescriptorType, Object> zzcx(int i) {
        return new zzbdq(i);
    }

    /* access modifiers changed from: private */
    public final V zzcz(int i) {
        zzafv();
        V value = this.zzdyk.remove(i).getValue();
        if (!this.zzdyl.isEmpty()) {
            Iterator it = zzafw().entrySet().iterator();
            this.zzdyk.add(new zzbdw(this, (Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    public void clear() {
        zzafv();
        if (!this.zzdyk.isEmpty()) {
            this.zzdyk.clear();
        }
        if (!this.zzdyl.isEmpty()) {
            this.zzdyl.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzdyl.containsKey(comparable);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzdym == null) {
            this.zzdym = new zzbdy(this, (zzbdq) null);
        }
        return this.zzdym;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbdp)) {
            return super.equals(obj);
        }
        zzbdp zzbdp = (zzbdp) obj;
        int size = size();
        if (size != zzbdp.size()) {
            return false;
        }
        int zzafs = zzafs();
        if (zzafs != zzbdp.zzafs()) {
            return entrySet().equals(zzbdp.entrySet());
        }
        for (int i = 0; i < zzafs; i++) {
            if (!zzcy(i).equals(zzbdp.zzcy(i))) {
                return false;
            }
        }
        if (zzafs != size) {
            return this.zzdyl.equals(zzbdp.zzdyl);
        }
        return true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        return zza >= 0 ? this.zzdyk.get(zza).getValue() : this.zzdyl.get(comparable);
    }

    public int hashCode() {
        int zzafs = zzafs();
        int i = 0;
        for (int i2 = 0; i2 < zzafs; i2++) {
            i += this.zzdyk.get(i2).hashCode();
        }
        return this.zzdyl.size() > 0 ? i + this.zzdyl.hashCode() : i;
    }

    public final boolean isImmutable() {
        return this.zzdqy;
    }

    public V remove(Object obj) {
        zzafv();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return zzcz(zza);
        }
        if (this.zzdyl.isEmpty()) {
            return null;
        }
        return this.zzdyl.remove(comparable);
    }

    public int size() {
        return this.zzdyk.size() + this.zzdyl.size();
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzafv();
        int zza = zza(k);
        if (zza >= 0) {
            return this.zzdyk.get(zza).setValue(v);
        }
        zzafv();
        if (this.zzdyk.isEmpty() && !(this.zzdyk instanceof ArrayList)) {
            this.zzdyk = new ArrayList(this.zzdyj);
        }
        int i = -(zza + 1);
        if (i >= this.zzdyj) {
            return zzafw().put(k, v);
        }
        int size = this.zzdyk.size();
        int i2 = this.zzdyj;
        if (size == i2) {
            zzbdw remove = this.zzdyk.remove(i2 - 1);
            zzafw().put((Comparable) remove.getKey(), remove.getValue());
        }
        this.zzdyk.add(i, new zzbdw(this, k, v));
        return null;
    }

    public void zzaaz() {
        if (!this.zzdqy) {
            this.zzdyl = this.zzdyl.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzdyl);
            this.zzdyn = this.zzdyn.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzdyn);
            this.zzdqy = true;
        }
    }

    public final int zzafs() {
        return this.zzdyk.size();
    }

    public final Iterable<Map.Entry<K, V>> zzaft() {
        return this.zzdyl.isEmpty() ? zzbdt.zzafy() : this.zzdyl.entrySet();
    }

    /* access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzafu() {
        if (this.zzdyo == null) {
            this.zzdyo = new zzbds(this, (zzbdq) null);
        }
        return this.zzdyo;
    }

    public final Map.Entry<K, V> zzcy(int i) {
        return this.zzdyk.get(i);
    }
}
