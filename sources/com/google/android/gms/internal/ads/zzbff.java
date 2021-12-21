package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzbff implements Cloneable {
    private Object value;
    private zzbfd<?, ?> zzebq;
    private List<zzbfk> zzebr = new ArrayList();

    zzbff() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzr()];
        zza(zzbfa.zzu(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzagp */
    public final zzbff clone() {
        Object clone;
        zzbff zzbff = new zzbff();
        try {
            zzbff.zzebq = this.zzebq;
            if (this.zzebr == null) {
                zzbff.zzebr = null;
            } else {
                zzbff.zzebr.addAll(this.zzebr);
            }
            if (this.value != null) {
                if (this.value instanceof zzbfi) {
                    clone = (zzbfi) ((zzbfi) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    clone = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzbff.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        clone = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        clone = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        clone = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        clone = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        clone = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzbfi[]) {
                        zzbfi[] zzbfiArr = (zzbfi[]) this.value;
                        zzbfi[] zzbfiArr2 = new zzbfi[zzbfiArr.length];
                        zzbff.value = zzbfiArr2;
                        while (i < zzbfiArr.length) {
                            zzbfiArr2[i] = (zzbfi) zzbfiArr[i].clone();
                            i++;
                        }
                    }
                }
                zzbff.value = clone;
            }
            return zzbff;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        List<zzbfk> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbff)) {
            return false;
        }
        zzbff zzbff = (zzbff) obj;
        if (this.value == null || zzbff.value == null) {
            List<zzbfk> list2 = this.zzebr;
            if (list2 != null && (list = zzbff.zzebr) != null) {
                return list2.equals(list);
            }
            try {
                return Arrays.equals(toByteArray(), zzbff.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            zzbfd<?, ?> zzbfd = this.zzebq;
            if (zzbfd != zzbff.zzebq) {
                return false;
            }
            if (!zzbfd.zzebl.isArray()) {
                return this.value.equals(zzbff.value);
            }
            Object obj2 = this.value;
            return obj2 instanceof byte[] ? Arrays.equals((byte[]) obj2, (byte[]) zzbff.value) : obj2 instanceof int[] ? Arrays.equals((int[]) obj2, (int[]) zzbff.value) : obj2 instanceof long[] ? Arrays.equals((long[]) obj2, (long[]) zzbff.value) : obj2 instanceof float[] ? Arrays.equals((float[]) obj2, (float[]) zzbff.value) : obj2 instanceof double[] ? Arrays.equals((double[]) obj2, (double[]) zzbff.value) : obj2 instanceof boolean[] ? Arrays.equals((boolean[]) obj2, (boolean[]) zzbff.value) : Arrays.deepEquals((Object[]) obj2, (Object[]) zzbff.value);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.value == null) {
            for (zzbfk next : this.zzebr) {
                zzbfa.zzde(next.tag);
                zzbfa.zzw(next.zzdpw);
            }
            return;
        }
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbfk zzbfk) throws IOException {
        NoSuchMethodError noSuchMethodError;
        List<zzbfk> list = this.zzebr;
        if (list != null) {
            list.add(zzbfk);
            return;
        }
        Object obj = this.value;
        if (obj instanceof zzbfi) {
            byte[] bArr = zzbfk.zzdpw;
            zzbez zzi = zzbez.zzi(bArr, 0, bArr.length);
            int zzacc = zzi.zzacc();
            if (zzacc == bArr.length - zzbfa.zzce(zzacc)) {
                zzbfi zza = ((zzbfi) this.value).zza(zzi);
                this.zzebq = this.zzebq;
                this.value = zza;
                this.zzebr = null;
                return;
            }
            throw zzbfh.zzagq();
        }
        boolean z = obj instanceof zzbfi[];
        Collections.singletonList(zzbfk);
        if (z) {
            throw noSuchMethodError;
        } else {
            noSuchMethodError = new NoSuchMethodError();
            throw noSuchMethodError;
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzr() {
        if (this.value == null) {
            int i = 0;
            for (zzbfk next : this.zzebr) {
                i += zzbfa.zzcl(next.tag) + 0 + next.zzdpw.length;
            }
            return i;
        }
        throw new NoSuchMethodError();
    }
}
