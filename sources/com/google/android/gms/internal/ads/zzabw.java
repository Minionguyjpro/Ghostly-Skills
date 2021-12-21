package com.google.android.gms.internal.ads;

final /* synthetic */ class zzabw implements Runnable {
    private final zzabv zzcaf;
    private final zzaoj zzcag;
    private final String zzcah;

    zzabw(zzabv zzabv, zzaoj zzaoj, String str) {
        this.zzcaf = zzabv;
        this.zzcag = zzaoj;
        this.zzcah = str;
    }

    public final void run() {
        this.zzcaf.zza(this.zzcag, this.zzcah);
    }
}
