package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzbai implements Iterator {
    private final int limit = this.zzdps.size();
    private int position = 0;
    private final /* synthetic */ zzbah zzdps;

    zzbai(zzbah zzbah) {
        this.zzdps = zzbah;
    }

    private final byte nextByte() {
        try {
            zzbah zzbah = this.zzdps;
            int i = this.position;
            this.position = i + 1;
            return zzbah.zzbn(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final /* synthetic */ Object next() {
        return Byte.valueOf(nextByte());
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
