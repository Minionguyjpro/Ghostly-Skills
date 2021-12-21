package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzbad {
    static int zza(int i, byte[] bArr, int i2, int i3, zzbae zzbae) throws zzbbu {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzb(bArr, i2, zzbae);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zza(bArr, i2, zzbae) + zzbae.zzdpl;
            }
            if (i4 == 3) {
                int i5 = (i & -8) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = zza(bArr, i2, zzbae);
                    i6 = zzbae.zzdpl;
                    if (i6 == i5) {
                        break;
                    }
                    i2 = zza(i6, bArr, i2, i3, zzbae);
                }
                if (i2 <= i3 && i6 == i5) {
                    return i2;
                }
                throw zzbbu.zzadr();
            } else if (i4 == 5) {
                return i2 + 4;
            } else {
                throw zzbbu.zzado();
            }
        } else {
            throw zzbbu.zzado();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzbbt<?> zzbbt, zzbae zzbae) {
        zzbbp zzbbp = (zzbbp) zzbbt;
        int zza = zza(bArr, i2, zzbae);
        while (true) {
            zzbbp.zzco(zzbae.zzdpl);
            if (zza >= i3) {
                break;
            }
            int zza2 = zza(bArr, zza, zzbae);
            if (i != zzbae.zzdpl) {
                break;
            }
            zza = zza(bArr, zza2, zzbae);
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzbef zzbef, zzbae zzbae) throws IOException {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzb = zzb(bArr, i2, zzbae);
                zzbef.zzb(i, Long.valueOf(zzbae.zzdpm));
                return zzb;
            } else if (i4 == 1) {
                zzbef.zzb(i, Long.valueOf(zzf(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zza = zza(bArr, i2, zzbae);
                int i5 = zzbae.zzdpl;
                zzbef.zzb(i, i5 == 0 ? zzbah.zzdpq : zzbah.zzc(bArr, zza, i5));
                return zza + i5;
            } else if (i4 == 3) {
                zzbef zzagd = zzbef.zzagd();
                int i6 = (i & -8) | 4;
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zza2 = zza(bArr, i2, zzbae);
                    int i8 = zzbae.zzdpl;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zza2;
                        break;
                    }
                    int zza3 = zza(i7, bArr, zza2, i3, zzagd, zzbae);
                    i7 = i8;
                    i2 = zza3;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzbbu.zzadr();
                }
                zzbef.zzb(i, zzagd);
                return i2;
            } else if (i4 == 5) {
                zzbef.zzb(i, Integer.valueOf(zze(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzbbu.zzado();
            }
        } else {
            throw zzbbu.zzado();
        }
    }

    static int zza(int i, byte[] bArr, int i2, zzbae zzbae) {
        int i3;
        int i4;
        int i5 = i & 127;
        int i6 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            i4 = b << 7;
        } else {
            int i7 = i5 | ((b & Byte.MAX_VALUE) << 7);
            int i8 = i6 + 1;
            byte b2 = bArr[i6];
            if (b2 >= 0) {
                i3 = b2 << 14;
            } else {
                i5 = i7 | ((b2 & Byte.MAX_VALUE) << 14);
                i6 = i8 + 1;
                byte b3 = bArr[i8];
                if (b3 >= 0) {
                    i4 = b3 << 21;
                } else {
                    i7 = i5 | ((b3 & Byte.MAX_VALUE) << 21);
                    i8 = i6 + 1;
                    byte b4 = bArr[i6];
                    if (b4 >= 0) {
                        i3 = b4 << 28;
                    } else {
                        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << 28);
                        while (true) {
                            int i10 = i8 + 1;
                            if (bArr[i8] >= 0) {
                                zzbae.zzdpl = i9;
                                return i10;
                            }
                            i8 = i10;
                        }
                    }
                }
            }
            zzbae.zzdpl = i7 | i3;
            return i8;
        }
        zzbae.zzdpl = i5 | i4;
        return i6;
    }

    static int zza(byte[] bArr, int i, zzbae zzbae) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza((int) b, bArr, i2, zzbae);
        }
        zzbae.zzdpl = b;
        return i2;
    }

    static int zza(byte[] bArr, int i, zzbbt<?> zzbbt, zzbae zzbae) throws IOException {
        zzbbp zzbbp = (zzbbp) zzbbt;
        int zza = zza(bArr, i, zzbae);
        int i2 = zzbae.zzdpl + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzbae);
            zzbbp.zzco(zzbae.zzdpl);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzbbu.zzadl();
    }

    static int zzb(byte[] bArr, int i, zzbae zzbae) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzbae.zzdpm = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & Byte.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        zzbae.zzdpm = j2;
        return i3;
    }

    static int zzc(byte[] bArr, int i, zzbae zzbae) {
        int zza = zza(bArr, i, zzbae);
        int i2 = zzbae.zzdpl;
        if (i2 == 0) {
            zzbae.zzdpn = "";
            return zza;
        }
        zzbae.zzdpn = new String(bArr, zza, i2, zzbbq.UTF_8);
        return zza + i2;
    }

    static int zzd(byte[] bArr, int i, zzbae zzbae) throws IOException {
        int zza = zza(bArr, i, zzbae);
        int i2 = zzbae.zzdpl;
        if (i2 == 0) {
            zzbae.zzdpn = "";
            return zza;
        }
        int i3 = zza + i2;
        if (zzbem.zzf(bArr, zza, i3)) {
            zzbae.zzdpn = new String(bArr, zza, i2, zzbbq.UTF_8);
            return i3;
        }
        throw zzbbu.zzads();
    }

    static int zze(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    static int zze(byte[] bArr, int i, zzbae zzbae) {
        int zza = zza(bArr, i, zzbae);
        int i2 = zzbae.zzdpl;
        if (i2 == 0) {
            zzbae.zzdpn = zzbah.zzdpq;
            return zza;
        }
        zzbae.zzdpn = zzbah.zzc(bArr, zza, i2);
        return zza + i2;
    }

    static long zzf(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double zzg(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzf(bArr, i));
    }

    static float zzh(byte[] bArr, int i) {
        return Float.intBitsToFloat(zze(bArr, i));
    }
}
