package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzbcc extends zzbab<String> implements zzbcd, RandomAccess {
    private static final zzbcc zzdvn;
    private static final zzbcd zzdvo = zzdvn;
    private final List<Object> zzdvp;

    static {
        zzbcc zzbcc = new zzbcc();
        zzdvn = zzbcc;
        zzbcc.zzaaz();
    }

    public zzbcc() {
        this(10);
    }

    public zzbcc(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zzbcc(ArrayList<Object> arrayList) {
        this.zzdvp = arrayList;
    }

    private static String zzq(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzbah ? ((zzbah) obj).zzabd() : zzbbq.zzt((byte[]) obj);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzaba();
        this.zzdvp.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzaba();
        if (collection instanceof zzbcd) {
            collection = ((zzbcd) collection).zzadw();
        }
        boolean addAll = this.zzdvp.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final void clear() {
        zzaba();
        this.zzdvp.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzdvp.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbah) {
            zzbah zzbah = (zzbah) obj;
            String zzabd = zzbah.zzabd();
            if (zzbah.zzabe()) {
                this.zzdvp.set(i, zzabd);
            }
            return zzabd;
        }
        byte[] bArr = (byte[]) obj;
        String zzt = zzbbq.zzt(bArr);
        if (zzbbq.zzs(bArr)) {
            this.zzdvp.set(i, zzt);
        }
        return zzt;
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* synthetic */ Object remove(int i) {
        zzaba();
        Object remove = this.zzdvp.remove(i);
        this.modCount++;
        return zzq(remove);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        zzaba();
        return zzq(this.zzdvp.set(i, (String) obj));
    }

    public final int size() {
        return this.zzdvp.size();
    }

    public final /* bridge */ /* synthetic */ boolean zzaay() {
        return super.zzaay();
    }

    public final List<?> zzadw() {
        return Collections.unmodifiableList(this.zzdvp);
    }

    public final zzbcd zzadx() {
        return zzaay() ? new zzbeh(this) : this;
    }

    public final void zzap(zzbah zzbah) {
        zzaba();
        this.zzdvp.add(zzbah);
        this.modCount++;
    }

    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzdvp);
            return new zzbcc((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final Object zzcp(int i) {
        return this.zzdvp.get(i);
    }
}
