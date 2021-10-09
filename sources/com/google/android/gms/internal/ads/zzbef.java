package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;
import java.io.IOException;
import java.util.Arrays;

public final class zzbef {
    private static final zzbef zzdyx = new zzbef(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzdpi;
    private int zzdtu;
    private Object[] zzdwh;
    private int[] zzdyy;

    private zzbef() {
        this(0, new int[8], new Object[8], true);
    }

    private zzbef(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzdtu = -1;
        this.count = i;
        this.zzdyy = iArr;
        this.zzdwh = objArr;
        this.zzdpi = z;
    }

    static zzbef zza(zzbef zzbef, zzbef zzbef2) {
        int i = zzbef.count + zzbef2.count;
        int[] copyOf = Arrays.copyOf(zzbef.zzdyy, i);
        System.arraycopy(zzbef2.zzdyy, 0, copyOf, zzbef.count, zzbef2.count);
        Object[] copyOf2 = Arrays.copyOf(zzbef.zzdwh, i);
        System.arraycopy(zzbef2.zzdwh, 0, copyOf2, zzbef.count, zzbef2.count);
        return new zzbef(i, copyOf, copyOf2, true);
    }

    public static zzbef zzagc() {
        return zzdyx;
    }

    static zzbef zzagd() {
        return new zzbef();
    }

    private static void zzb(int i, Object obj, zzbey zzbey) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzbey.zzi(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            zzbey.zzc(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            zzbey.zza(i2, (zzbah) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                zzbey.zzp(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzbbu.zzadq());
        } else if (zzbey.zzacn() == zzbbo.zze.zzdul) {
            zzbey.zzcm(i2);
            ((zzbef) obj).zzb(zzbey);
            zzbey.zzcn(i2);
        } else {
            zzbey.zzcn(i2);
            ((zzbef) obj).zzb(zzbey);
            zzbey.zzcm(i2);
        }
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzbef)) {
            return false;
        }
        zzbef zzbef = (zzbef) obj;
        int i = this.count;
        if (i == zzbef.count) {
            int[] iArr = this.zzdyy;
            int[] iArr2 = zzbef.zzdyy;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.zzdwh;
                Object[] objArr2 = zzbef.zzdwh;
                int i3 = this.count;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                return z2;
            }
        }
    }

    public final int hashCode() {
        int i = this.count;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzdyy;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzdwh;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbey zzbey) throws IOException {
        if (zzbey.zzacn() == zzbbo.zze.zzdum) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzbey.zza(this.zzdyy[i] >>> 3, this.zzdwh[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzbey.zza(this.zzdyy[i2] >>> 3, this.zzdwh[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzbcx.zza(sb, i, String.valueOf(this.zzdyy[i2] >>> 3), this.zzdwh[i2]);
        }
    }

    public final void zzaaz() {
        this.zzdpi = false;
    }

    public final int zzacw() {
        int i;
        int i2 = this.zzdtu;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.count; i4++) {
            int i5 = this.zzdyy[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 == 0) {
                i = zzbav.zze(i6, ((Long) this.zzdwh[i4]).longValue());
            } else if (i7 == 1) {
                i = zzbav.zzg(i6, ((Long) this.zzdwh[i4]).longValue());
            } else if (i7 == 2) {
                i = zzbav.zzc(i6, (zzbah) this.zzdwh[i4]);
            } else if (i7 == 3) {
                i = (zzbav.zzcd(i6) << 1) + ((zzbef) this.zzdwh[i4]).zzacw();
            } else if (i7 == 5) {
                i = zzbav.zzt(i6, ((Integer) this.zzdwh[i4]).intValue());
            } else {
                throw new IllegalStateException(zzbbu.zzadq());
            }
            i3 += i;
        }
        this.zzdtu = i3;
        return i3;
    }

    public final int zzage() {
        int i = this.zzdtu;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzbav.zzd(this.zzdyy[i3] >>> 3, (zzbah) this.zzdwh[i3]);
        }
        this.zzdtu = i2;
        return i2;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(int i, Object obj) {
        if (this.zzdpi) {
            int i2 = this.count;
            if (i2 == this.zzdyy.length) {
                int i3 = this.count + (i2 < 4 ? 8 : i2 >> 1);
                this.zzdyy = Arrays.copyOf(this.zzdyy, i3);
                this.zzdwh = Arrays.copyOf(this.zzdwh, i3);
            }
            int[] iArr = this.zzdyy;
            int i4 = this.count;
            iArr[i4] = i;
            this.zzdwh[i4] = obj;
            this.count = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzb(zzbey zzbey) throws IOException {
        if (this.count != 0) {
            if (zzbey.zzacn() == zzbbo.zze.zzdul) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zzdyy[i], this.zzdwh[i], zzbey);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zzdyy[i2], this.zzdwh[i2], zzbey);
            }
        }
    }
}
