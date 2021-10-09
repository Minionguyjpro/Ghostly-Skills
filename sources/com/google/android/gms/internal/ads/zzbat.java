package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;

final class zzbat implements zzbdl {
    private int tag;
    private final zzbaq zzdqi;
    private int zzdqj;
    private int zzdqk = 0;

    private zzbat(zzbaq zzbaq) {
        zzbaq zzbaq2 = (zzbaq) zzbbq.zza(zzbaq, "input");
        this.zzdqi = zzbaq2;
        zzbaq2.zzdqa = this;
    }

    public static zzbat zza(zzbaq zzbaq) {
        return zzbaq.zzdqa != null ? zzbaq.zzdqa : new zzbat(zzbaq);
    }

    private final Object zza(zzbes zzbes, Class<?> cls, zzbbb zzbbb) throws IOException {
        switch (zzbau.zzdql[zzbes.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzabq());
            case 2:
                return zzabs();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzabu());
            case 5:
                return Integer.valueOf(zzabp());
            case 6:
                return Long.valueOf(zzabo());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzabn());
            case 9:
                return Long.valueOf(zzabm());
            case 10:
                zzbv(2);
                return zzc(zzbdg.zzaeo().zze(cls), zzbbb);
            case 11:
                return Integer.valueOf(zzabv());
            case 12:
                return Long.valueOf(zzabw());
            case 13:
                return Integer.valueOf(zzabx());
            case 14:
                return Long.valueOf(zzaby());
            case 15:
                return zzabr();
            case 16:
                return Integer.valueOf(zzabt());
            case 17:
                return Long.valueOf(zzabl());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zzabk;
        int zzabk2;
        if ((this.tag & 7) != 2) {
            throw zzbbu.zzadq();
        } else if (!(list instanceof zzbcd) || z) {
            do {
                list.add(z ? zzabr() : readString());
                if (!this.zzdqi.zzaca()) {
                    zzabk = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk == this.tag);
            this.zzdqk = zzabk;
        } else {
            zzbcd zzbcd = (zzbcd) list;
            do {
                zzbcd.zzap(zzabs());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
        }
    }

    private final void zzbv(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzbbu.zzadq();
        }
    }

    private static void zzbw(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzbbu.zzadr();
        }
    }

    private static void zzbx(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzbbu.zzadr();
        }
    }

    private final void zzby(int i) throws IOException {
        if (this.zzdqi.zzacb() != i) {
            throw zzbbu.zzadl();
        }
    }

    private final <T> T zzc(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int zzabt = this.zzdqi.zzabt();
        if (this.zzdqi.zzdpx < this.zzdqi.zzdpy) {
            int zzbr = this.zzdqi.zzbr(zzabt);
            T newInstance = zzbdm.newInstance();
            this.zzdqi.zzdpx++;
            zzbdm.zza(newInstance, this, zzbbb);
            zzbdm.zzo(newInstance);
            this.zzdqi.zzbp(0);
            zzbaq zzbaq = this.zzdqi;
            zzbaq.zzdpx--;
            this.zzdqi.zzbs(zzbr);
            return newInstance;
        }
        throw new zzbbu("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final <T> T zzd(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int i = this.zzdqj;
        this.zzdqj = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzbdm.newInstance();
            zzbdm.zza(newInstance, this, zzbbb);
            zzbdm.zzo(newInstance);
            if (this.tag == this.zzdqj) {
                return newInstance;
            }
            throw zzbbu.zzadr();
        } finally {
            this.zzdqj = i;
        }
    }

    public final int getTag() {
        return this.tag;
    }

    public final double readDouble() throws IOException {
        zzbv(1);
        return this.zzdqi.readDouble();
    }

    public final float readFloat() throws IOException {
        zzbv(5);
        return this.zzdqi.readFloat();
    }

    public final String readString() throws IOException {
        zzbv(2);
        return this.zzdqi.readString();
    }

    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    public final <T> T zza(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        zzbv(2);
        return zzc(zzbdm, zzbbb);
    }

    public final <T> void zza(List<T> list, zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int zzabk;
        int i = this.tag;
        if ((i & 7) == 2) {
            do {
                list.add(zzc(zzbdm, zzbbb));
                if (!this.zzdqi.zzaca() && this.zzdqk == 0) {
                    zzabk = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk == i);
            this.zzdqk = zzabk;
            return;
        }
        throw zzbbu.zzadq();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (zzacj() != false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        throw new com.google.android.gms.internal.ads.zzbbu("Unable to parse map entry.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r8, com.google.android.gms.internal.ads.zzbcn<K, V> r9, com.google.android.gms.internal.ads.zzbbb r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.zzbv(r0)
            com.google.android.gms.internal.ads.zzbaq r1 = r7.zzdqi
            int r1 = r1.zzabt()
            com.google.android.gms.internal.ads.zzbaq r2 = r7.zzdqi
            int r1 = r2.zzbr(r1)
            K r2 = r9.zzdvz
            V r3 = r9.zzdwb
        L_0x0014:
            int r4 = r7.zzaci()     // Catch:{ all -> 0x0064 }
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x005b
            com.google.android.gms.internal.ads.zzbaq r5 = r7.zzdqi     // Catch:{ all -> 0x0064 }
            boolean r5 = r5.zzaca()     // Catch:{ all -> 0x0064 }
            if (r5 != 0) goto L_0x005b
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L_0x0046
            if (r4 == r0) goto L_0x0039
            boolean r4 = r7.zzacj()     // Catch:{ zzbbv -> 0x004e }
            if (r4 == 0) goto L_0x0033
            goto L_0x0014
        L_0x0033:
            com.google.android.gms.internal.ads.zzbbu r4 = new com.google.android.gms.internal.ads.zzbbu     // Catch:{ zzbbv -> 0x004e }
            r4.<init>(r6)     // Catch:{ zzbbv -> 0x004e }
            throw r4     // Catch:{ zzbbv -> 0x004e }
        L_0x0039:
            com.google.android.gms.internal.ads.zzbes r4 = r9.zzdwa     // Catch:{ zzbbv -> 0x004e }
            V r5 = r9.zzdwb     // Catch:{ zzbbv -> 0x004e }
            java.lang.Class r5 = r5.getClass()     // Catch:{ zzbbv -> 0x004e }
            java.lang.Object r3 = r7.zza((com.google.android.gms.internal.ads.zzbes) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.ads.zzbbb) r10)     // Catch:{ zzbbv -> 0x004e }
            goto L_0x0014
        L_0x0046:
            com.google.android.gms.internal.ads.zzbes r4 = r9.zzdvy     // Catch:{ zzbbv -> 0x004e }
            r5 = 0
            java.lang.Object r2 = r7.zza((com.google.android.gms.internal.ads.zzbes) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.ads.zzbbb) r5)     // Catch:{ zzbbv -> 0x004e }
            goto L_0x0014
        L_0x004e:
            boolean r4 = r7.zzacj()     // Catch:{ all -> 0x0064 }
            if (r4 == 0) goto L_0x0055
            goto L_0x0014
        L_0x0055:
            com.google.android.gms.internal.ads.zzbbu r8 = new com.google.android.gms.internal.ads.zzbbu     // Catch:{ all -> 0x0064 }
            r8.<init>(r6)     // Catch:{ all -> 0x0064 }
            throw r8     // Catch:{ all -> 0x0064 }
        L_0x005b:
            r8.put(r2, r3)     // Catch:{ all -> 0x0064 }
            com.google.android.gms.internal.ads.zzbaq r8 = r7.zzdqi
            r8.zzbs(r1)
            return
        L_0x0064:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzbaq r9 = r7.zzdqi
            r9.zzbs(r1)
            goto L_0x006c
        L_0x006b:
            throw r8
        L_0x006c:
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbat.zza(java.util.Map, com.google.android.gms.internal.ads.zzbcn, com.google.android.gms.internal.ads.zzbbb):void");
    }

    public final void zzaa(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabu());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbbp.zzco(this.zzdqi.zzabu());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabu()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabu()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final void zzab(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbx(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbbp.zzco(this.zzdqi.zzabv());
                } while (this.zzdqi.zzacb() < zzacb);
            } else if (i == 5) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabv());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbx(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabv()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabv()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final long zzabl() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabl();
    }

    public final long zzabm() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabm();
    }

    public final int zzabn() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabn();
    }

    public final long zzabo() throws IOException {
        zzbv(1);
        return this.zzdqi.zzabo();
    }

    public final int zzabp() throws IOException {
        zzbv(5);
        return this.zzdqi.zzabp();
    }

    public final boolean zzabq() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabq();
    }

    public final String zzabr() throws IOException {
        zzbv(2);
        return this.zzdqi.zzabr();
    }

    public final zzbah zzabs() throws IOException {
        zzbv(2);
        return this.zzdqi.zzabs();
    }

    public final int zzabt() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabt();
    }

    public final int zzabu() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabu();
    }

    public final int zzabv() throws IOException {
        zzbv(5);
        return this.zzdqi.zzabv();
    }

    public final long zzabw() throws IOException {
        zzbv(1);
        return this.zzdqi.zzabw();
    }

    public final int zzabx() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabx();
    }

    public final long zzaby() throws IOException {
        zzbv(0);
        return this.zzdqi.zzaby();
    }

    public final void zzac(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzbci.zzw(this.zzdqi.zzabw());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbw(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbci.zzw(this.zzdqi.zzabw());
                } while (this.zzdqi.zzacb() < zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabw()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbw(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabw()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final int zzaci() throws IOException {
        int i = this.zzdqk;
        if (i != 0) {
            this.tag = i;
            this.zzdqk = 0;
        } else {
            this.tag = this.zzdqi.zzabk();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzdqj) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public final boolean zzacj() throws IOException {
        int i;
        if (this.zzdqi.zzaca() || (i = this.tag) == this.zzdqj) {
            return false;
        }
        return this.zzdqi.zzbq(i);
    }

    public final void zzad(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabx());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbbp.zzco(this.zzdqi.zzabx());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabx()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabx()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final void zzae(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbci.zzw(this.zzdqi.zzaby());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbci.zzw(this.zzdqi.zzaby());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzdqi.zzaby()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzaby()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final <T> T zzb(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        zzbv(3);
        return zzd(zzbdm, zzbbb);
    }

    public final <T> void zzb(List<T> list, zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int zzabk;
        int i = this.tag;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzbdm, zzbbb));
                if (!this.zzdqi.zzaca() && this.zzdqk == 0) {
                    zzabk = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk == i);
            this.zzdqk = zzabk;
            return;
        }
        throw zzbbu.zzadq();
    }

    public final void zzp(List<Double> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbay) {
            zzbay zzbay = (zzbay) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzbay.zzd(this.zzdqi.readDouble());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbw(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbay.zzd(this.zzdqi.readDouble());
                } while (this.zzdqi.zzacb() < zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Double.valueOf(this.zzdqi.readDouble()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbw(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Double.valueOf(this.zzdqi.readDouble()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final void zzq(List<Float> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbm) {
            zzbbm zzbbm = (zzbbm) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbx(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbbm.zzd(this.zzdqi.readFloat());
                } while (this.zzdqi.zzacb() < zzacb);
            } else if (i == 5) {
                do {
                    zzbbm.zzd(this.zzdqi.readFloat());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbx(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Float.valueOf(this.zzdqi.readFloat()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zzdqi.readFloat()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final void zzr(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbci.zzw(this.zzdqi.zzabl());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbci.zzw(this.zzdqi.zzabl());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabl()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabl()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final void zzs(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbci.zzw(this.zzdqi.zzabm());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbci.zzw(this.zzdqi.zzabm());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabm()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabm()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final void zzt(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabn());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbbp.zzco(this.zzdqi.zzabn());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabn()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabn()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final void zzu(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzbci.zzw(this.zzdqi.zzabo());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbw(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbci.zzw(this.zzdqi.zzabo());
                } while (this.zzdqi.zzacb() < zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabo()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbw(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabo()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final void zzv(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbx(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbbp.zzco(this.zzdqi.zzabp());
                } while (this.zzdqi.zzacb() < zzacb);
            } else if (i == 5) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabp());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbx(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabp()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabp()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final void zzw(List<Boolean> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbaf) {
            zzbaf zzbaf = (zzbaf) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbaf.addBoolean(this.zzdqi.zzabq());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbaf.addBoolean(this.zzdqi.zzabq());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzdqi.zzabq()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Boolean.valueOf(this.zzdqi.zzabq()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    public final void zzx(List<String> list) throws IOException {
        zza(list, true);
    }

    public final void zzy(List<zzbah> list) throws IOException {
        int zzabk;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzabs());
                if (!this.zzdqi.zzaca()) {
                    zzabk = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk == this.tag);
            this.zzdqk = zzabk;
            return;
        }
        throw zzbbu.zzadq();
    }

    public final void zzz(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabt());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbbp.zzco(this.zzdqi.zzabt());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabt()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabt()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }
}
