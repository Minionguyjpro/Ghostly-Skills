package com.google.android.gms.internal.ads;

import android.net.http.SslError;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
final class zzast extends WebViewClient {
    private final zzasx zzdfc;
    private final zzatb zzdfd;
    private final zzasz zzdfe;
    private final zzata zzdff;
    private final zzatc zzdfg = new zzatc();

    zzast(zzasx zzasx, zzatb zzatb, zzasz zzasz, zzata zzata) {
        this.zzdfc = zzasx;
        this.zzdfd = zzatb;
        this.zzdfe = zzasz;
        this.zzdff = zzata;
    }

    private final boolean zzf(zzasu zzasu) {
        return this.zzdfc.zza(zzasu);
    }

    private final WebResourceResponse zzg(zzasu zzasu) {
        return this.zzdfd.zzd(zzasu);
    }

    public final void onLoadResource(WebView webView, String str) {
        if (str != null) {
            String valueOf = String.valueOf(str);
            zzakb.v(valueOf.length() != 0 ? "Loading resource: ".concat(valueOf) : new String("Loading resource: "));
            this.zzdfe.zzb(new zzasu(str));
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        if (str != null) {
            this.zzdff.zzc(new zzasu(str));
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.zzdfg.zze(i, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        this.zzdfg.zzb(sslError);
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        if (webResourceRequest == null || webResourceRequest.getUrl() == null) {
            return null;
        }
        return zzg(new zzasu(webResourceRequest));
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (str == null) {
            return null;
        }
        return zzg(new zzasu(str));
    }

    public final boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 79 || keyCode == 222) {
            return true;
        }
        switch (keyCode) {
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
                return true;
            default:
                switch (keyCode) {
                    case 126:
                    case 127:
                    case 128:
                    case 129:
                    case 130:
                        return true;
                    default:
                        return false;
                }
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        if (webResourceRequest == null || webResourceRequest.getUrl() == null) {
            return false;
        }
        return zzf(new zzasu(webResourceRequest));
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null) {
            return false;
        }
        return zzf(new zzasu(str));
    }
}
