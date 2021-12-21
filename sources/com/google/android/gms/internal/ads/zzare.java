package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import java.util.concurrent.Callable;

final /* synthetic */ class zzare implements Callable {
    private final String zzcah;
    private final Context zzdck;
    private final zzasi zzdco;
    private final boolean zzdcp;
    private final boolean zzdcq;
    private final zzci zzdcr;
    private final zzang zzdcs;
    private final zznx zzdct;
    private final zzbo zzdcu;
    private final zzw zzdcv;
    private final zzhs zzdcw;

    zzare(Context context, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        this.zzdck = context;
        this.zzdco = zzasi;
        this.zzcah = str;
        this.zzdcp = z;
        this.zzdcq = z2;
        this.zzdcr = zzci;
        this.zzdcs = zzang;
        this.zzdct = zznx;
        this.zzdcu = zzbo;
        this.zzdcv = zzw;
        this.zzdcw = zzhs;
    }

    public final Object call() {
        Context context = this.zzdck;
        zzasi zzasi = this.zzdco;
        String str = this.zzcah;
        boolean z = this.zzdcp;
        boolean z2 = this.zzdcq;
        zzarh zzarh = new zzarh(zzari.zzb(context, zzasi, str, z, z2, this.zzdcr, this.zzdcs, this.zzdct, this.zzdcu, this.zzdcv, this.zzdcw));
        zzarh.setWebViewClient(zzbv.zzem().zza((zzaqw) zzarh, z2));
        zzarh.setWebChromeClient(new zzaqo(zzarh));
        return zzarh;
    }
}
