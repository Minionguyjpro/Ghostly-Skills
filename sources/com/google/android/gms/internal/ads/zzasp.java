package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzw;
import java.util.concurrent.Callable;

final /* synthetic */ class zzasp implements Callable {
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

    zzasp(Context context, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
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
        zzasq zzc = zzasq.zzc(context, zzasi, str, z, z2, this.zzdcr, this.zzdcs, this.zzdct, this.zzdcu, this.zzdcv, this.zzdcw);
        zzarh zzarh = new zzarh(zzc);
        zzasj zzasj = new zzasj(zzarh, z2);
        zzc.setWebChromeClient(new zzaqo(zzarh));
        zzc.zza((zzasx) zzasj);
        zzc.zza((zzatb) zzasj);
        zzc.zza((zzata) zzasj);
        zzc.zza((zzasz) zzasj);
        zzc.zza(zzasj);
        return zzarh;
    }
}
