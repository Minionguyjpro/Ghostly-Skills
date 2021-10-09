package com.google.android.gms.internal.ads;

final class zzcd implements Runnable {
    private final /* synthetic */ zzcc zzpx;

    zzcd(zzcc zzcc) {
        this.zzpx = zzcc;
    }

    public final void run() {
        if (this.zzpx.zzpv == null) {
            synchronized (zzcc.zzpt) {
                if (this.zzpx.zzpv == null) {
                    boolean booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzbap)).booleanValue();
                    if (booleanValue) {
                        try {
                            zzcc.zzpu = new zzhx(this.zzpx.zzps.zzrt, "ADSHIELD", (String) null);
                        } catch (Throwable unused) {
                            booleanValue = false;
                        }
                    }
                    this.zzpx.zzpv = Boolean.valueOf(booleanValue);
                    zzcc.zzpt.open();
                }
            }
        }
    }
}
