package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbbp extends zzbab<Integer> implements zzbbt<Integer>, RandomAccess {
    private static final zzbbp zzduo;
    private int size;
    private int[] zzdup;

    static {
        zzbbp zzbbp = new zzbbp();
        zzduo = zzbbp;
        zzbbp.zzaaz();
    }

    zzbbp() {
        this(new int[10], 0);
    }

    private zzbbp(int[] iArr, int i) {
        this.zzdup = iArr;
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

    private final void zzy(int i, int i2) {
        int i3;
        zzaba();
        if (i < 0 || i > (i3 = this.size)) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
        int[] iArr = this.zzdup;
        if (i3 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i3 - i);
        } else {
            int[] iArr2 = new int[(((i3 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.zzdup, i, iArr2, i + 1, this.size - i);
            this.zzdup = iArr2;
        }
        this.zzdup[i] = i2;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzy(i, ((Integer) obj).intValue());
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbbp)) {
            return super.addAll(collection);
        }
        zzbbp zzbbp = (zzbbp) collection;
        int i = zzbbp.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.zzdup;
            if (i3 > iArr.length) {
                this.zzdup = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(zzbbp.zzdup, 0, this.zzdup, this.size, zzbbp.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbbp)) {
            return super.equals(obj);
        }
        zzbbp zzbbp = (zzbbp) obj;
        if (this.size != zzbbp.size) {
            return false;
        }
        int[] iArr = zzbbp.zzdup;
        for (int i = 0; i < this.size; i++) {
            if (this.zzdup[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    public final int getInt(int i) {
        zzbk(i);
        return this.zzdup[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzdup[i2];
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzaba();
        zzbk(i);
        int[] iArr = this.zzdup;
        int i2 = iArr[i];
        int i3 = this.size;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, i3 - i);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final boolean remove(Object obj) {
        zzaba();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzdup[i]))) {
                int[] iArr = this.zzdup;
                System.arraycopy(iArr, i + 1, iArr, i, this.size - i);
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
            int[] iArr = this.zzdup;
            System.arraycopy(iArr, i2, iArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzaba();
        zzbk(i);
        int[] iArr = this.zzdup;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i >= this.size) {
            return new zzbbp(Arrays.copyOf(this.zzdup, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzco(int i) {
        zzy(this.size, i);
    }
}
