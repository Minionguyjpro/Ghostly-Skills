package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzbeh extends AbstractList<String> implements zzbcd, RandomAccess {
    /* access modifiers changed from: private */
    public final zzbcd zzdyz;

    public zzbeh(zzbcd zzbcd) {
        this.zzdyz = zzbcd;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzdyz.get(i);
    }

    public final Iterator<String> iterator() {
        return new zzbej(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzbei(this, i);
    }

    public final int size() {
        return this.zzdyz.size();
    }

    public final List<?> zzadw() {
        return this.zzdyz.zzadw();
    }

    public final zzbcd zzadx() {
        return this;
    }

    public final void zzap(zzbah zzbah) {
        throw new UnsupportedOperationException();
    }

    public final Object zzcp(int i) {
        return this.zzdyz.zzcp(i);
    }
}
