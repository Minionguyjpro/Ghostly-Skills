package com.google.android.gms.internal.ads;

public final class zzgy {
    final long value;
    final String zzajf;
    final int zzajg;

    zzgy(long j, String str, int i) {
        this.value = j;
        this.zzajf = str;
        this.zzajg = i;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzgy)) {
            zzgy zzgy = (zzgy) obj;
            return zzgy.value == this.value && zzgy.zzajg == this.zzajg;
        }
    }

    public final int hashCode() {
        return (int) this.value;
    }
}
