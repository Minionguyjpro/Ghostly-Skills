package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzafm {
    public final boolean zzcgv;
    public final zzafy zzcgw = null;
    public final zzhn zzcgx;
    public final zzajc zzcgy;
    public final zzmz zzcgz;
    public final zzagh zzcha;
    public final zzwu zzchb;
    public final zzagi zzchc;
    public final zzagj zzchd;
    public final zzaav zzche;
    public final zzajg zzchf;
    public final zzafr zzchg;

    private zzafm(zzafy zzafy, zzhn zzhn, zzajc zzajc, zzmz zzmz, zzagh zzagh, zzwu zzwu, zzagi zzagi, zzagj zzagj, zzaav zzaav, zzajg zzajg, boolean z, zzafr zzafr) {
        this.zzcgx = zzhn;
        this.zzcgy = zzajc;
        this.zzcgz = zzmz;
        this.zzcha = zzagh;
        this.zzchb = zzwu;
        this.zzchc = zzagi;
        this.zzchd = zzagj;
        this.zzche = zzaav;
        this.zzchf = zzajg;
        this.zzcgv = true;
        this.zzchg = zzafr;
    }

    public static zzafm zzm(Context context) {
        zzbv.zzfi().initialize(context);
        zzagn zzagn = new zzagn(context);
        return new zzafm((zzafy) null, new zzhq(), new zzajd(), new zzmy(), new zzagf(context, zzagn.zzog()), new zzwv(), new zzagl(), new zzagm(), new zzaau(), new zzaje(), true, zzagn);
    }
}
