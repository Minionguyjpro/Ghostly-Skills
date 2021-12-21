package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

public final class zzalr {
    public final int count;
    public final String name;
    private final double zzcsz;
    private final double zzcta;
    public final double zzctb;

    public zzalr(String str, double d, double d2, double d3, int i) {
        this.name = str;
        this.zzcta = d;
        this.zzcsz = d2;
        this.zzctb = d3;
        this.count = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzalr)) {
            return false;
        }
        zzalr zzalr = (zzalr) obj;
        return Objects.equal(this.name, zzalr.name) && this.zzcsz == zzalr.zzcsz && this.zzcta == zzalr.zzcta && this.count == zzalr.count && Double.compare(this.zzctb, zzalr.zzctb) == 0;
    }

    public final int hashCode() {
        return Objects.hashCode(this.name, Double.valueOf(this.zzcsz), Double.valueOf(this.zzcta), Double.valueOf(this.zzctb), Integer.valueOf(this.count));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("minBound", Double.valueOf(this.zzcta)).add("maxBound", Double.valueOf(this.zzcsz)).add("percent", Double.valueOf(this.zzctb)).add("count", Integer.valueOf(this.count)).toString();
    }
}
