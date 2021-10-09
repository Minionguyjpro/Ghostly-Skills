package com.google.android.gms.internal.ads;

final class zzws implements zzaom {
    private final /* synthetic */ zzvs zzbrl;
    private final /* synthetic */ zzaoj zzbrn;

    zzws(zzwq zzwq, zzaoj zzaoj, zzvs zzvs) {
        this.zzbrn = zzaoj;
        this.zzbrl = zzvs;
    }

    public final void run() {
        this.zzbrn.setException(new zzwe("Unable to obtain a JavascriptEngine."));
        this.zzbrl.release();
    }
}
