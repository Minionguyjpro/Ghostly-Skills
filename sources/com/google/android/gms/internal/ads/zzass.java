package com.google.android.gms.internal.ads;

import android.graphics.Paint;
import android.os.Build;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.zzbv;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public class zzass extends WebView implements zzasx, zzasz, zzata, zzatb {
    private final List<zzasx> zzdew = new CopyOnWriteArrayList();
    private final List<zzatb> zzdex = new CopyOnWriteArrayList();
    private final List<zzasz> zzdey = new CopyOnWriteArrayList();
    private final List<zzata> zzdez = new CopyOnWriteArrayList();
    private final zzash zzdfa;
    protected final WebViewClient zzdfb;

    public zzass(zzash zzash) {
        super(zzash);
        this.zzdfa = zzash;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzbv.zzem().zza(getContext(), settings);
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        try {
            getSettings().setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            zzakb.zzb("Unable to enable Javascript.", e);
        }
        setLayerType(1, (Paint) null);
        zzast zzast = new zzast(this, this, this, this);
        this.zzdfb = zzast;
        super.setWebViewClient(zzast);
    }

    public void addJavascriptInterface(Object obj, String str) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.addJavascriptInterface(obj, str);
        } else {
            zzakb.v("Ignore addJavascriptInterface due to low Android version.");
        }
    }

    public void loadUrl(String str) {
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError e) {
            zzbv.zzeo().zza(e, "CoreWebView.loadUrl");
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }

    public void setWebViewClient(WebViewClient webViewClient) {
    }

    public final void zza(zzasx zzasx) {
        this.zzdew.add(zzasx);
    }

    public final void zza(zzasz zzasz) {
        this.zzdey.add(zzasz);
    }

    public final void zza(zzata zzata) {
        this.zzdez.add(zzata);
    }

    public final void zza(zzatb zzatb) {
        this.zzdex.add(zzatb);
    }

    public final boolean zza(zzasu zzasu) {
        for (zzasx zza : this.zzdew) {
            if (zza.zza(zzasu)) {
                return true;
            }
        }
        return false;
    }

    public final void zzb(zzasu zzasu) {
        for (zzasz zzb : this.zzdey) {
            zzb.zzb(zzasu);
        }
    }

    public void zzbe(String str) {
        zzasy.zza(this, str);
    }

    public void zzc(zzasu zzasu) {
        for (zzata zzc : this.zzdez) {
            zzc.zzc(zzasu);
        }
    }

    public final WebResourceResponse zzd(zzasu zzasu) {
        for (zzatb zzd : this.zzdex) {
            WebResourceResponse zzd2 = zzd.zzd(zzasu);
            if (zzd2 != null) {
                return zzd2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final zzash zzvv() {
        return this.zzdfa;
    }
}
