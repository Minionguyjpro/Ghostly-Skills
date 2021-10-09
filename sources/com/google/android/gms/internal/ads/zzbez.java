package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbez {
    private final byte[] buffer;
    private int zzdpx;
    private int zzdpy = 64;
    private int zzdpz = 67108864;
    private int zzdqe;
    private int zzdqg;
    private int zzdqh = Integer.MAX_VALUE;
    private final int zzebf;
    private final int zzebg;
    private int zzebh;
    private int zzebi;

    private zzbez(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzebf = i;
        int i3 = i2 + i;
        this.zzebh = i3;
        this.zzebg = i3;
        this.zzebi = i;
    }

    private final void zzacg() {
        int i = this.zzebh + this.zzdqe;
        this.zzebh = i;
        int i2 = this.zzdqh;
        if (i > i2) {
            int i3 = i - i2;
            this.zzdqe = i3;
            this.zzebh = i - i3;
            return;
        }
        this.zzdqe = 0;
    }

    private final byte zzach() throws IOException {
        int i = this.zzebi;
        if (i != this.zzebh) {
            byte[] bArr = this.buffer;
            this.zzebi = i + 1;
            return bArr[i];
        }
        throw zzbfh.zzagq();
    }

    private final void zzbt(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.zzebi;
            int i3 = i2 + i;
            int i4 = this.zzdqh;
            if (i3 > i4) {
                zzbt(i4 - i2);
                throw zzbfh.zzagq();
            } else if (i <= this.zzebh - i2) {
                this.zzebi = i2 + i;
            } else {
                throw zzbfh.zzagq();
            }
        } else {
            throw zzbfh.zzagr();
        }
    }

    public static zzbez zzi(byte[] bArr, int i, int i2) {
        return new zzbez(bArr, 0, i2);
    }

    public final int getPosition() {
        return this.zzebi - this.zzebf;
    }

    public final byte[] readBytes() throws IOException {
        int zzacc = zzacc();
        if (zzacc < 0) {
            throw zzbfh.zzagr();
        } else if (zzacc == 0) {
            return zzbfl.zzecf;
        } else {
            int i = this.zzebh;
            int i2 = this.zzebi;
            if (zzacc <= i - i2) {
                byte[] bArr = new byte[zzacc];
                System.arraycopy(this.buffer, i2, bArr, 0, zzacc);
                this.zzebi += zzacc;
                return bArr;
            }
            throw zzbfh.zzagq();
        }
    }

    public final String readString() throws IOException {
        int zzacc = zzacc();
        if (zzacc < 0) {
            throw zzbfh.zzagr();
        } else if (zzacc <= this.zzebh - this.zzebi) {
            String str = new String(this.buffer, this.zzebi, zzacc, zzbfg.UTF_8);
            this.zzebi += zzacc;
            return str;
        } else {
            throw zzbfh.zzagq();
        }
    }

    public final void zza(zzbfi zzbfi) throws IOException {
        int zzacc = zzacc();
        if (this.zzdpx < this.zzdpy) {
            int zzbr = zzbr(zzacc);
            this.zzdpx++;
            zzbfi.zza(this);
            zzbp(0);
            this.zzdpx--;
            zzbs(zzbr);
            return;
        }
        throw new zzbfh("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    public final byte[] zzab(int i, int i2) {
        if (i2 == 0) {
            return zzbfl.zzecf;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzebf + i, bArr, 0, i2);
        return bArr;
    }

    public final int zzabk() throws IOException {
        if (this.zzebi == this.zzebh) {
            this.zzdqg = 0;
            return 0;
        }
        int zzacc = zzacc();
        this.zzdqg = zzacc;
        if (zzacc != 0) {
            return zzacc;
        }
        throw new zzbfh("Protocol message contained an invalid tag (zero).");
    }

    public final long zzabm() throws IOException {
        return zzacd();
    }

    public final int zzabn() throws IOException {
        return zzacc();
    }

    public final boolean zzabq() throws IOException {
        return zzacc() != 0;
    }

    /* access modifiers changed from: package-private */
    public final void zzac(int i, int i2) {
        int i3 = this.zzebi;
        int i4 = this.zzebf;
        if (i > i3 - i4) {
            StringBuilder sb = new StringBuilder(50);
            sb.append("Position ");
            sb.append(i);
            sb.append(" is beyond current ");
            sb.append(this.zzebi - this.zzebf);
            throw new IllegalArgumentException(sb.toString());
        } else if (i >= 0) {
            this.zzebi = i4 + i;
            this.zzdqg = i2;
        } else {
            StringBuilder sb2 = new StringBuilder(24);
            sb2.append("Bad position ");
            sb2.append(i);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public final int zzacc() throws IOException {
        int i;
        byte zzach = zzach();
        if (zzach >= 0) {
            return zzach;
        }
        byte b = zzach & Byte.MAX_VALUE;
        byte zzach2 = zzach();
        if (zzach2 >= 0) {
            i = zzach2 << 7;
        } else {
            b |= (zzach2 & Byte.MAX_VALUE) << 7;
            byte zzach3 = zzach();
            if (zzach3 >= 0) {
                i = zzach3 << 14;
            } else {
                b |= (zzach3 & Byte.MAX_VALUE) << 14;
                byte zzach4 = zzach();
                if (zzach4 >= 0) {
                    i = zzach4 << 21;
                } else {
                    byte b2 = b | ((zzach4 & Byte.MAX_VALUE) << 21);
                    byte zzach5 = zzach();
                    byte b3 = b2 | (zzach5 << 28);
                    if (zzach5 >= 0) {
                        return b3;
                    }
                    for (int i2 = 0; i2 < 5; i2++) {
                        if (zzach() >= 0) {
                            return b3;
                        }
                    }
                    throw zzbfh.zzags();
                }
            }
        }
        return b | i;
    }

    public final long zzacd() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzach = zzach();
            j |= ((long) (zzach & Byte.MAX_VALUE)) << i;
            if ((zzach & 128) == 0) {
                return j;
            }
        }
        throw zzbfh.zzags();
    }

    public final int zzagn() {
        int i = this.zzdqh;
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i - this.zzebi;
    }

    public final void zzbp(int i) throws zzbfh {
        if (this.zzdqg != i) {
            throw new zzbfh("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzbq(int i) throws IOException {
        int zzabk;
        int i2 = i & 7;
        if (i2 == 0) {
            zzacc();
            return true;
        } else if (i2 == 1) {
            zzach();
            zzach();
            zzach();
            zzach();
            zzach();
            zzach();
            zzach();
            zzach();
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
                zzach();
                zzach();
                zzach();
                zzach();
                return true;
            }
            throw new zzbfh("Protocol message tag had invalid wire type.");
        }
    }

    public final int zzbr(int i) throws zzbfh {
        if (i >= 0) {
            int i2 = i + this.zzebi;
            int i3 = this.zzdqh;
            if (i2 <= i3) {
                this.zzdqh = i2;
                zzacg();
                return i3;
            }
            throw zzbfh.zzagq();
        }
        throw zzbfh.zzagr();
    }

    public final void zzbs(int i) {
        this.zzdqh = i;
        zzacg();
    }

    public final void zzdc(int i) {
        zzac(i, this.zzdqg);
    }
}
