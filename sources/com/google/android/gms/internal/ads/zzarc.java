package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzw;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzarc {
    public static zzanz<zzaqw> zza(Context context, zzang zzang, String str, zzci zzci, zzw zzw) {
        return zzano.zza(zzano.zzi(null), new zzard(context, zzci, zzang, zzw, str), zzaoe.zzcvy);
    }

    public static zzaqw zza(Context context, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) throws zzarg {
        zznk.initialize(context);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxy)).booleanValue()) {
            return zzaso.zza(context, zzasi, str, z2, z, zzci, zzang, zznx, zzbo, zzw, zzhs);
        }
        try {
            return (zzaqw) zzaml.zzb(new zzare(context, zzasi, str, z, z2, zzci, zzang, zznx, zzbo, zzw, zzhs));
        } catch (Throwable th) {
            throw new zzarg("Webview initialization failed.", th);
        }
    }
}
