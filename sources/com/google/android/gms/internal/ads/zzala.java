package com.google.android.gms.internal.ads;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import com.google.android.gms.ads.internal.zzbv;
import java.io.InputStream;
import java.util.Map;

public final class zzala extends zzakz {
    public final WebResourceResponse zza(String str, String str2, int i, String str3, Map<String, String> map, InputStream inputStream) {
        return new WebResourceResponse(str, str2, i, str3, map, inputStream);
    }

    public final zzaqx zza(zzaqw zzaqw, boolean z) {
        return new zzarv(zzaqw, z);
    }

    public final CookieManager zzax(Context context) {
        if (zzrp()) {
            return null;
        }
        try {
            return CookieManager.getInstance();
        } catch (Throwable th) {
            zzakb.zzb("Failed to obtain CookieManager.", th);
            zzbv.zzeo().zza(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    public final int zzrq() {
        return 16974374;
    }
}
