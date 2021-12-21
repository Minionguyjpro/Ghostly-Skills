package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

final class zzbas extends zzbaq {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzdqd;
    private int zzdqe;
    private int zzdqf;
    private int zzdqg;
    private int zzdqh;

    private zzbas(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzdqh = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzdqf = i;
        this.zzdqd = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzacc() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L_0x006b
            byte[] r2 = r5.buffer
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0011
            r5.pos = r3
            return r0
        L_0x0011:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x006b
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0022
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x0068
        L_0x0022:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x002f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L_0x002d:
            r1 = r3
            goto L_0x0068
        L_0x002f:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x003d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0068
        L_0x003d:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x0068
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x0068
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r2 = r2[r3]
            if (r2 < 0) goto L_0x006b
        L_0x0068:
            r5.pos = r1
            return r0
        L_0x006b:
            long r0 = r5.zzabz()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbas.zzacc():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b0, code lost:
        if (((long) r2[r0]) >= 0) goto L_0x006e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzacd() throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r11.pos
            int r1 = r11.limit
            if (r1 == r0) goto L_0x00b8
            byte[] r2 = r11.buffer
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0012
            r11.pos = r3
            long r0 = (long) r0
            return r0
        L_0x0012:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x00b8
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0025
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
        L_0x0022:
            long r2 = (long) r0
            goto L_0x00b5
        L_0x0025:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0036
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r9 = r0
            r1 = r3
            r2 = r9
            goto L_0x00b5
        L_0x0036:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0044
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0022
        L_0x0044:
            long r3 = (long) r0
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r5 = (long) r1
            r1 = 28
            long r5 = r5 << r1
            long r3 = r3 ^ r5
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x005b
            r1 = 266354560(0xfe03f80, double:1.315966377E-315)
        L_0x0057:
            long r1 = r1 ^ r3
            r2 = r1
            r1 = r0
            goto L_0x00b5
        L_0x005b:
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            r0 = 35
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0070
            r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
        L_0x006d:
            long r3 = r3 ^ r5
        L_0x006e:
            r2 = r3
            goto L_0x00b5
        L_0x0070:
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r7 = (long) r1
            r1 = 42
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x0083
            r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L_0x0057
        L_0x0083:
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            r0 = 49
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0096
            r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L_0x006d
        L_0x0096:
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r7 = (long) r1
            r1 = 56
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x00b3
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 < 0) goto L_0x00b8
            goto L_0x006e
        L_0x00b3:
            r1 = r0
            goto L_0x006e
        L_0x00b5:
            r11.pos = r1
            return r2
        L_0x00b8:
            long r0 = r11.zzabz()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbas.zzacd():long");
    }

    private final int zzace() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 4) {
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }
        throw zzbbu.zzadl();
    }

    private final long zzacf() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 8) {
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzbbu.zzadl();
    }

    private final void zzacg() {
        int i = this.limit + this.zzdqe;
        this.limit = i;
        int i2 = i - this.zzdqf;
        int i3 = this.zzdqh;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzdqe = i4;
            this.limit = i - i4;
            return;
        }
        this.zzdqe = 0;
    }

    private final byte zzach() throws IOException {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzbbu.zzadl();
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzacf());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzace());
    }

    public final String readString() throws IOException {
        int zzacc = zzacc();
        if (zzacc > 0 && zzacc <= this.limit - this.pos) {
            String str = new String(this.buffer, this.pos, zzacc, zzbbq.UTF_8);
            this.pos += zzacc;
            return str;
        } else if (zzacc == 0) {
            return "";
        } else {
            if (zzacc < 0) {
                throw zzbbu.zzadm();
            }
            throw zzbbu.zzadl();
        }
    }

    public final int zzabk() throws IOException {
        if (zzaca()) {
            this.zzdqg = 0;
            return 0;
        }
        int zzacc = zzacc();
        this.zzdqg = zzacc;
        if ((zzacc >>> 3) != 0) {
            return zzacc;
        }
        throw zzbbu.zzado();
    }

    public final long zzabl() throws IOException {
        return zzacd();
    }

    public final long zzabm() throws IOException {
        return zzacd();
    }

    public final int zzabn() throws IOException {
        return zzacc();
    }

    public final long zzabo() throws IOException {
        return zzacf();
    }

    public final int zzabp() throws IOException {
        return zzace();
    }

    public final boolean zzabq() throws IOException {
        return zzacd() != 0;
    }

    public final String zzabr() throws IOException {
        int zzacc = zzacc();
        if (zzacc > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzacc <= i - i2) {
                if (zzbem.zzf(this.buffer, i2, i2 + zzacc)) {
                    int i3 = this.pos;
                    this.pos = i3 + zzacc;
                    return new String(this.buffer, i3, zzacc, zzbbq.UTF_8);
                }
                throw zzbbu.zzads();
            }
        }
        if (zzacc == 0) {
            return "";
        }
        if (zzacc <= 0) {
            throw zzbbu.zzadm();
        }
        throw zzbbu.zzadl();
    }

    public final zzbah zzabs() throws IOException {
        byte[] bArr;
        int zzacc = zzacc();
        if (zzacc > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzacc <= i - i2) {
                zzbah zzc = zzbah.zzc(this.buffer, i2, zzacc);
                this.pos += zzacc;
                return zzc;
            }
        }
        if (zzacc == 0) {
            return zzbah.zzdpq;
        }
        if (zzacc > 0) {
            int i3 = this.limit;
            int i4 = this.pos;
            if (zzacc <= i3 - i4) {
                int i5 = zzacc + i4;
                this.pos = i5;
                bArr = Arrays.copyOfRange(this.buffer, i4, i5);
                return zzbah.zzp(bArr);
            }
        }
        if (zzacc > 0) {
            throw zzbbu.zzadl();
        } else if (zzacc == 0) {
            bArr = zzbbq.zzduq;
            return zzbah.zzp(bArr);
        } else {
            throw zzbbu.zzadm();
        }
    }

    public final int zzabt() throws IOException {
        return zzacc();
    }

    public final int zzabu() throws IOException {
        return zzacc();
    }

    public final int zzabv() throws IOException {
        return zzace();
    }

    public final long zzabw() throws IOException {
        return zzacf();
    }

    public final int zzabx() throws IOException {
        return zzbu(zzacc());
    }

    public final long zzaby() throws IOException {
        return zzl(zzacd());
    }

    /* access modifiers changed from: package-private */
    public final long zzabz() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzach = zzach();
            j |= ((long) (zzach & Byte.MAX_VALUE)) << i;
            if ((zzach & 128) == 0) {
                return j;
            }
        }
        throw zzbbu.zzadn();
    }

    public final boolean zzaca() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzacb() {
        return this.pos - this.zzdqf;
    }

    public final void zzbp(int i) throws zzbbu {
        if (this.zzdqg != i) {
            throw zzbbu.zzadp();
        }
    }

    public final boolean zzbq(int i) throws IOException {
        int zzabk;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.limit - this.pos >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.buffer;
                    int i4 = this.pos;
                    this.pos = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzbbu.zzadn();
            }
            while (i3 < 10) {
                if (zzach() < 0) {
                    i3++;
                }
            }
            throw zzbbu.zzadn();
            return true;
        } else if (i2 == 1) {
            zzbt(8);
            return true;
        } else if (i2 == 2) {
            zzbt(zzacc());
            return true;
        } else if (i2 == 3) {
            do {
                zzabk = zzabk();
                if (zzabk == 0 || !zzbq(zzabk)) {
                    zzbp(((i >>> 3) << 3) | 4);
                }
                zzabk = zzabk();
                break;
            } while (!zzbq(zzabk));
            zzbp(((i >>> 3) << 3) | 4);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzbt(4);
                return true;
            }
            throw zzbbu.zzadq();
        }
    }

    public final int zzbr(int i) throws zzbbu {
        if (i >= 0) {
            int zzacb = i + zzacb();
            int i2 = this.zzdqh;
            if (zzacb <= i2) {
                this.zzdqh = zzacb;
                zzacg();
                return i2;
            }
            throw zzbbu.zzadl();
        }
        throw zzbbu.zzadm();
    }

    public final void zzbs(int i) {
        this.zzdqh = i;
        zzacg();
    }

    public final void zzbt(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.limit;
            int i3 = this.pos;
            if (i <= i2 - i3) {
                this.pos = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzbbu.zzadm();
        }
        throw zzbbu.zzadl();
    }
}
