package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbaf extends zzbab<Boolean> implements zzbbt<Boolean>, RandomAccess {
    private static final zzbaf zzdpo;
    private int size;
    private boolean[] zzdpp;

    static {
        zzbaf zzbaf = new zzbaf();
        zzdpo = zzbaf;
        zzbaf.zzaaz();
    }

    zzbaf() {
        this(new boolean[10], 0);
    }

    private zzbaf(boolean[] zArr, int i) {
        this.zzdpp = zArr;
        this.size = i;
    }

    private final void zzbk(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
    }

    private final String zzbl(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    private final void zze(int i, boolean z) {
        int i2;
        zzaba();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
        boolean[] zArr = this.zzdpp;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.zzdpp, i, zArr2, i + 1, this.size - i);
            this.zzdpp = zArr2;
        }
        this.zzdpp[i] = z;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zze(i, ((Boolean) obj).booleanValue());
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbaf)) {
            return super.addAll(collection);
        }
        zzbaf zzbaf = (zzbaf) collection;
        int i = zzbaf.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zzdpp;
            if (i3 > zArr.length) {
                this.zzdpp = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zzbaf.zzdpp, 0, this.zzdpp, this.size, zzbaf.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final void addBoolean(boolean z) {
        zze(this.size, z);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbaf)) {
            return super.equals(obj);
        }
        zzbaf zzbaf = (zzbaf) obj;
        if (this.size != zzbaf.size) {
            return false;
        }
        boolean[] zArr = zzbaf.zzdpp;
        for (int i = 0; i < this.size; i++) {
            if (this.zzdpp[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzbk(i);
        return Boolean.valueOf(this.zzdpp[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbbq.zzar(this.zzdpp[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzaba();
        zzbk(i);
        boolean[] zArr = this.zzdpp;
        boolean z = zArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, i2 - i);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final boolean remove(Object obj) {
        zzaba();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzdpp[i]))) {
                boolean[] zArr = this.zzdpp;
                System.arraycopy(zArr, i + 1, zArr, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzaba();
        if (i2 >= i) {
            boolean[] zArr = this.zzdpp;
            System.arraycopy(zArr, i2, zArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzaba();
        zzbk(i);
        boolean[] zArr = this.zzdpp;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i >= this.size) {
            return new zzbaf(Arrays.copyOf(this.zzdpp, i), this.size);
        }
        throw new IllegalArgumentException();
    }
}
