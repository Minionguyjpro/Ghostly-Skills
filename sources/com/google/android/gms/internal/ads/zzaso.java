package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzaso {
    public static zzaqw zza(Context context, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) throws zzarg {
        try {
            return (zzaqw) zzaml.zzb(new zzasp(context, zzasi, str, z, z2, zzci, zzang, zznx, zzbo, zzw, zzhs));
        } catch (Throwable th) {
            zzbv.zzeo().zza(th, "AdWebViewFactory.newAdWebView2");
            throw new zzarg("Webview initialization failed.", th);
        }
    }
}
