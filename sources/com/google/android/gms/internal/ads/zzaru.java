package com.google.android.gms.internal.ads;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzbv;
import java.io.File;
import java.util.Collections;
import java.util.Map;

@zzadh
public class zzaru extends zzaqx {
    public zzaru(zzaqw zzaqw, boolean z) {
        super(zzaqw, z);
    }

    /* access modifiers changed from: protected */
    public final WebResourceResponse zza(WebView webView, String str, Map<String, String> map) {
        if (!(webView instanceof zzaqw)) {
            zzakb.zzdk("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        zzaqw zzaqw = (zzaqw) webView;
        if (this.zzxd != null) {
            this.zzxd.zza(str, map, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
            if (map == null) {
                map = Collections.emptyMap();
            }
            return super.zzd(str, map);
        }
        if (zzaqw.zzuf() != null) {
            zzaqw.zzuf().zznk();
        }
        String str2 = (String) zzkb.zzik().zzd(zzaqw.zzud().zzvs() ? zznk.zzawe : zzaqw.zzuj() ? zznk.zzawd : zznk.zzawc);
        zzbv.zzek();
        return zzakk.zzf(zzaqw.getContext(), zzaqw.zztq().zzcw, str2);
    }
}
