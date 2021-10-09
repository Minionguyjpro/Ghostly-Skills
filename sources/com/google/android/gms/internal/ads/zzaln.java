package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;
import java.util.regex.Pattern;

@zzadh
public final class zzaln extends zzaj {
    private final Context mContext;

    private zzaln(Context context, zzar zzar) {
        super(zzar);
        this.mContext = context;
    }

    public static zzv zzba(Context context) {
        zzv zzv = new zzv(new zzam(new File(context.getCacheDir(), "admob_volley")), new zzaln(context, new zzas()));
        zzv.start();
        return zzv;
    }

    public final zzp zzc(zzr<?> zzr) throws zzae {
        if (zzr.zzh() && zzr.getMethod() == 0) {
            if (Pattern.matches((String) zzkb.zzik().zzd(zznk.zzbdw), zzr.getUrl())) {
                zzkb.zzif();
                if (zzamu.zzbe(this.mContext)) {
                    zzp zzc = new zzsm(this.mContext).zzc(zzr);
                    if (zzc != null) {
                        String valueOf = String.valueOf(zzr.getUrl());
                        zzakb.v(valueOf.length() != 0 ? "Got gmscore asset response: ".concat(valueOf) : new String("Got gmscore asset response: "));
                        return zzc;
                    }
                    String valueOf2 = String.valueOf(zzr.getUrl());
                    zzakb.v(valueOf2.length() != 0 ? "Failed to get gmscore asset response: ".concat(valueOf2) : new String("Failed to get gmscore asset response: "));
                }
            }
        }
        return super.zzc(zzr);
    }
}
