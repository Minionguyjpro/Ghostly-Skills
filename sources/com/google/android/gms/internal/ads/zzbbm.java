package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbbm extends zzbab<Float> implements zzbbt<Float>, RandomAccess {
    private static final zzbbm zzdtq;
    private int size;
    private float[] zzdtr;

    static {
        zzbbm zzbbm = new zzbbm();
        zzdtq = zzbbm;
        zzbbm.zzaaz();
    }

    zzbbm() {
        this(new float[10], 0);
    }

    private zzbbm(float[] fArr, int i) {
        this.zzdtr = fArr;
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

    private final void zzc(int i, float f) {
        int i2;
        zzaba();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
        float[] fArr = this.zzdtr;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[(((i2 * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.zzdtr, i, fArr2, i + 1, this.size - i);
            this.zzdtr = fArr2;
        }
        this.zzdtr[i] = f;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Float) obj).floatValue());
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbbm)) {
            return super.addAll(collection);
        }
        zzbbm zzbbm = (zzbbm) collection;
        int i = zzbbm.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.zzdtr;
            if (i3 > fArr.length) {
                this.zzdtr = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(zzbbm.zzdtr, 0, this.zzdtr, this.size, zzbbm.size);
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
        if (!(obj instanceof zzbbm)) {
            return super.equals(obj);
        }
        zzbbm zzbbm = (zzbbm) obj;
        if (this.size != zzbbm.size) {
            return false;
        }
        float[] fArr = zzbbm.zzdtr;
        for (int i = 0; i < this.size; i++) {
            if (this.zzdtr[i] != fArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzbk(i);
        return Float.valueOf(this.zzdtr[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzdtr[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzaba();
        zzbk(i);
        float[] fArr = this.zzdtr;
        float f = fArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, i2 - i);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final boolean remove(Object obj) {
        zzaba();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzdtr[i]))) {
                float[] fArr = this.zzdtr;
                System.arraycopy(fArr, i + 1, fArr, i, this.size - i);
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
            float[] fArr = this.zzdtr;
            System.arraycopy(fArr, i2, fArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzaba();
        zzbk(i);
        float[] fArr = this.zzdtr;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i >= this.size) {
            return new zzbbm(Arrays.copyOf(this.zzdtr, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzd(float f) {
        zzc(this.size, f);
    }
}
