package com.google.android.gms.internal.ads;

import android.content.Context;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class zzaku implements Callable<Boolean> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ WebSettings zzcrw;

    zzaku(zzakt zzakt, Context context, WebSettings webSettings) {
        this.val$context = context;
        this.zzcrw = webSettings;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (this.val$context.getCacheDir() != null) {
            this.zzcrw.setAppCachePath(this.val$context.getCacheDir().getAbsolutePath());
            this.zzcrw.setAppCacheMaxSize(0);
            this.zzcrw.setAppCacheEnabled(true);
        }
        this.zzcrw.setDatabasePath(this.val$context.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
        this.zzcrw.setDatabaseEnabled(true);
        this.zzcrw.setDomStorageEnabled(true);
        this.zzcrw.setDisplayZoomControls(false);
        this.zzcrw.setBuiltInZoomControls(true);
        this.zzcrw.setSupportZoom(true);
        this.zzcrw.setAllowContentAccess(false);
        return true;
    }
}
