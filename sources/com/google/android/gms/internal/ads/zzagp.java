package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

@zzadh
public final class zzagp extends zzagv {
    private final String zzclb;
    private final int zzclc;

    public zzagp(String str, int i) {
        this.zzclb = str;
        this.zzclc = i;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzagp)) {
            zzagp zzagp = (zzagp) obj;
            return Objects.equal(this.zzclb, zzagp.zzclb) && Objects.equal(Integer.valueOf(this.zzclc), Integer.valueOf(zzagp.zzclc));
        }
    }

    public final int getAmount() {
        return this.zzclc;
    }

    public final String getType() {
        return this.zzclb;
    }
}
