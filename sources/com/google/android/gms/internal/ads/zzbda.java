package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

final class zzbda<T> implements zzbdm<T> {
    private final zzbcu zzdwl;
    private final boolean zzdwm;
    private final zzbee<?, ?> zzdwv;
    private final zzbbd<?> zzdww;

    private zzbda(zzbee<?, ?> zzbee, zzbbd<?> zzbbd, zzbcu zzbcu) {
        this.zzdwv = zzbee;
        this.zzdwm = zzbbd.zzh(zzbcu);
        this.zzdww = zzbbd;
        this.zzdwl = zzbcu;
    }

    static <T> zzbda<T> zza(zzbee<?, ?> zzbee, zzbbd<?> zzbbd, zzbcu zzbcu) {
        return new zzbda<>(zzbee, zzbbd, zzbcu);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzdwv.zzac(t).equals(this.zzdwv.zzac(t2))) {
            return false;
        }
        if (this.zzdwm) {
            return this.zzdww.zzm(t).equals(this.zzdww.zzm(t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzdwv.zzac(t).hashCode();
        return this.zzdwm ? (hashCode * 53) + this.zzdww.zzm(t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzdwl.zzadf().zzadj();
    }

    public final void zza(T t, zzbdl zzbdl, zzbbb zzbbb) throws IOException {
        boolean z;
        zzbee<?, ?> zzbee = this.zzdwv;
        zzbbd<?> zzbbd = this.zzdww;
        Object zzad = zzbee.zzad(t);
        zzbbg<?> zzn = zzbbd.zzn(t);
        do {
            try {
                if (zzbdl.zzaci() == Integer.MAX_VALUE) {
                    zzbee.zzf(t, zzad);
                    return;
                }
                int tag = zzbdl.getTag();
                if (tag == 11) {
                    int i = 0;
                    Object obj = null;
                    zzbah zzbah = null;
                    while (zzbdl.zzaci() != Integer.MAX_VALUE) {
                        int tag2 = zzbdl.getTag();
                        if (tag2 == 16) {
                            i = zzbdl.zzabt();
                            obj = zzbbd.zza(zzbbb, this.zzdwl, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzbbd.zza(zzbdl, obj, zzbbb, zzn);
                            } else {
                                zzbah = zzbdl.zzabs();
                            }
                        } else if (!zzbdl.zzacj()) {
                            break;
                        }
                    }
                    if (zzbdl.getTag() != 12) {
                        throw zzbbu.zzadp();
                    } else if (zzbah != null) {
                        if (obj != null) {
                            zzbbd.zza(zzbah, obj, zzbbb, zzn);
                        } else {
                            zzbee.zza(zzad, i, zzbah);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object zza = zzbbd.zza(zzbbb, this.zzdwl, tag >>> 3);
                    if (zza != null) {
                        zzbbd.zza(zzbdl, zza, zzbbb, zzn);
                    } else {
                        z = zzbee.zza(zzad, zzbdl);
                        continue;
                    }
                } else {
                    z = zzbdl.zzacj();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzbee.zzf(t, zzad);
            }
        } while (z);
    }

    public final void zza(T t, zzbey zzbey) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.zzdww.zzm(t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzbbi zzbbi = (zzbbi) next.getKey();
            if (zzbbi.zzacz() != zzbex.MESSAGE || zzbbi.zzada() || zzbbi.zzadb()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            zzbey.zza(zzbbi.zzhq(), next instanceof zzbbz ? ((zzbbz) next).zzadv().zzaav() : next.getValue());
        }
        zzbee<?, ?> zzbee = this.zzdwv;
        zzbee.zzc(zzbee.zzac(t), zzbey);
    }

    public final void zza(T t, byte[] bArr, int i, int i2, zzbae zzbae) throws IOException {
        zzbbo zzbbo = (zzbbo) t;
        zzbef zzbef = zzbbo.zzdtt;
        if (zzbef == zzbef.zzagc()) {
            zzbef = zzbef.zzagd();
            zzbbo.zzdtt = zzbef;
        }
        zzbef zzbef2 = zzbef;
        while (i < i2) {
            int zza = zzbad.zza(bArr, i, zzbae);
            int i3 = zzbae.zzdpl;
            if (i3 != 11) {
                i = (i3 & 7) == 2 ? zzbad.zza(i3, bArr, zza, i2, zzbef2, zzbae) : zzbad.zza(i3, bArr, zza, i2, zzbae);
            } else {
                int i4 = 0;
                zzbah zzbah = null;
                while (zza < i2) {
                    zza = zzbad.zza(bArr, zza, zzbae);
                    int i5 = zzbae.zzdpl;
                    int i6 = i5 >>> 3;
                    int i7 = i5 & 7;
                    if (i6 != 2) {
                        if (i6 == 3 && i7 == 2) {
                            zza = zzbad.zze(bArr, zza, zzbae);
                            zzbah = (zzbah) zzbae.zzdpn;
                        }
                    } else if (i7 == 0) {
                        zza = zzbad.zza(bArr, zza, zzbae);
                        i4 = zzbae.zzdpl;
                    }
                    if (i5 == 12) {
                        break;
                    }
                    zza = zzbad.zza(i5, bArr, zza, i2, zzbae);
                }
                if (zzbah != null) {
                    zzbef2.zzb((i4 << 3) | 2, zzbah);
                }
                i = zza;
            }
        }
        if (i != i2) {
            throw zzbbu.zzadr();
        }
    }

    public final boolean zzaa(T t) {
        return this.zzdww.zzm(t).isInitialized();
    }

    public final void zzc(T t, T t2) {
        zzbdo.zza(this.zzdwv, t, t2);
        if (this.zzdwm) {
            zzbdo.zza(this.zzdww, t, t2);
        }
    }

    public final void zzo(T t) {
        this.zzdwv.zzo(t);
        this.zzdww.zzo(t);
    }

    public final int zzy(T t) {
        zzbee<?, ?> zzbee = this.zzdwv;
        int zzae = zzbee.zzae(zzbee.zzac(t)) + 0;
        return this.zzdwm ? zzae + this.zzdww.zzm(t).zzacx() : zzae;
    }
}
