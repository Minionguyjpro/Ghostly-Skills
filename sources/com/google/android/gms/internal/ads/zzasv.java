package com.google.android.gms.internal.ads;

import android.graphics.Canvas;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public class zzasv extends zzass {
    private boolean zzdfh;
    private boolean zzdfi;

    public zzasv(zzash zzash) {
        super(zzash);
        zzbv.zzeo().zzqe();
    }

    private final synchronized void zzqf() {
        if (!this.zzdfi) {
            this.zzdfi = true;
            zzbv.zzeo().zzqf();
        }
    }

    public synchronized void destroy() {
        if (!this.zzdfh) {
            this.zzdfh = true;
            zzam(false);
            zzakb.v("Initiating WebView self destruct sequence in 3...");
            zzakb.v("Loading blank page in WebView, 2...");
            try {
                super.loadUrl("about:blank");
            } catch (UnsatisfiedLinkError e) {
                zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrlUnsafe");
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void evaluateJavascript(java.lang.String r2, android.webkit.ValueCallback<java.lang.String> r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isDestroyed()     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0014
            java.lang.String r2 = "#004 The webview is destroyed. Ignoring action."
            com.google.android.gms.internal.ads.zzakb.zzdk(r2)     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x0012
            r2 = 0
            r3.onReceiveValue(r2)     // Catch:{ all -> 0x0019 }
        L_0x0012:
            monitor-exit(r1)
            return
        L_0x0014:
            super.evaluateJavascript(r2, r3)     // Catch:{ all -> 0x0019 }
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzasv.evaluateJavascript(java.lang.String, android.webkit.ValueCallback):void");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!isDestroyed()) {
                    zzam(true);
                }
                zzqf();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public final synchronized boolean isDestroyed() {
        return this.zzdfh;
    }

    public synchronized void loadData(String str, String str2, String str3) {
        if (!isDestroyed()) {
            super.loadData(str, str2, str3);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!isDestroyed()) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public synchronized void loadUrl(String str) {
        if (!isDestroyed()) {
            super.loadUrl(str);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            super.onDraw(canvas);
        }
    }

    public void onPause() {
        if (!isDestroyed()) {
            super.onPause();
        }
    }

    public void onResume() {
        if (!isDestroyed()) {
            super.onResume();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !isDestroyed() && super.onTouchEvent(motionEvent);
    }

    public void stopLoading() {
        if (!isDestroyed()) {
            super.stopLoading();
        }
    }

    /* access modifiers changed from: protected */
    public void zzam(boolean z) {
    }

    public final synchronized void zzc(zzasu zzasu) {
        if (isDestroyed()) {
            zzakb.v("Blank page loaded, 1...");
            zzuk();
            return;
        }
        super.zzc(zzasu);
    }

    public final synchronized void zzuk() {
        zzakb.v("Destroying WebView!");
        zzqf();
        zzaoe.zzcvy.execute(new zzasw(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzvw() {
        super.destroy();
    }
}
