package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.mopub.mobileads.MoPubView;

public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, MoPubView.MoPubAdSizeInt.HEIGHT_250_INT, "300x250_as");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
    public static final AdSize zzup = new AdSize(50, 50, "50x50_mb");
    private final int zzuq;
    private final int zzur;
    private final String zzus;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AdSize(int r5, int r6) {
        /*
            r4 = this;
            r0 = -1
            if (r5 != r0) goto L_0x0006
            java.lang.String r0 = "FULL"
            goto L_0x000a
        L_0x0006:
            java.lang.String r0 = java.lang.String.valueOf(r5)
        L_0x000a:
            r1 = -2
            if (r6 != r1) goto L_0x0010
            java.lang.String r1 = "AUTO"
            goto L_0x0014
        L_0x0010:
            java.lang.String r1 = java.lang.String.valueOf(r6)
        L_0x0014:
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            int r2 = r2 + 4
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            r3.append(r0)
            java.lang.String r0 = "x"
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = "_as"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r4.<init>(r5, r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.AdSize.<init>(int, int):void");
    }

    AdSize(int i, int i2, String str) {
        if (i < 0 && i != -1 && i != -3) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Invalid width for AdSize: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 >= 0 || i2 == -2 || i2 == -4) {
            this.zzuq = i;
            this.zzur = i2;
            this.zzus = str;
        } else {
            StringBuilder sb2 = new StringBuilder(38);
            sb2.append("Invalid height for AdSize: ");
            sb2.append(i2);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.zzuq == adSize.zzuq && this.zzur == adSize.zzur && this.zzus.equals(adSize.zzus);
    }

    public final int getHeight() {
        return this.zzur;
    }

    public final int getHeightInPixels(Context context) {
        int i = this.zzur;
        if (i == -4 || i == -3) {
            return -1;
        }
        if (i == -2) {
            return zzjn.zzc(context.getResources().getDisplayMetrics());
        }
        zzkb.zzif();
        return zzamu.zza(context, this.zzur);
    }

    public final int getWidth() {
        return this.zzuq;
    }

    public final int getWidthInPixels(Context context) {
        int i = this.zzuq;
        if (i == -4 || i == -3) {
            return -1;
        }
        if (i == -1) {
            return zzjn.zzb(context.getResources().getDisplayMetrics());
        }
        zzkb.zzif();
        return zzamu.zza(context, this.zzuq);
    }

    public final int hashCode() {
        return this.zzus.hashCode();
    }

    public final boolean isAutoHeight() {
        return this.zzur == -2;
    }

    public final boolean isFluid() {
        return this.zzuq == -3 && this.zzur == -4;
    }

    public final boolean isFullWidth() {
        return this.zzuq == -1;
    }

    public final String toString() {
        return this.zzus;
    }
}
