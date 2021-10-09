package com.google.android.gms.internal.ads;

import java.util.Arrays;

final class zzbfk {
    final int tag;
    final byte[] zzdpw;

    zzbfk(int i, byte[] bArr) {
        this.tag = i;
        this.zzdpw = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbfk)) {
            return false;
        }
        zzbfk zzbfk = (zzbfk) obj;
        return this.tag == zzbfk.tag && Arrays.equals(this.zzdpw, zzbfk.zzdpw);
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzdpw);
    }
}
