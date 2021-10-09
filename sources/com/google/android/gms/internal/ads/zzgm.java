package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;
import android.webkit.WebView;

final class zzgm implements Runnable {
    final /* synthetic */ zzgk zzaik;
    private ValueCallback<String> zzail = new zzgn(this);
    final /* synthetic */ zzge zzaim;
    final /* synthetic */ WebView zzain;
    final /* synthetic */ boolean zzaio;

    zzgm(zzgk zzgk, zzge zzge, WebView webView, boolean z) {
        this.zzaik = zzgk;
        this.zzaim = zzge;
        this.zzain = webView;
        this.zzaio = z;
    }

    public final void run() {
        if (this.zzain.getSettings().getJavaScriptEnabled()) {
            try {
                this.zzain.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzail);
            } catch (Throwable unused) {
                this.zzail.onReceiveValue("");
            }
        }
    }
}
