package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

final class zzsc implements Runnable {
    private final /* synthetic */ PublisherAdView zzblg;
    private final /* synthetic */ zzks zzblh;
    private final /* synthetic */ zzsb zzbli;

    zzsc(zzsb zzsb, PublisherAdView publisherAdView, zzks zzks) {
        this.zzbli = zzsb;
        this.zzblg = publisherAdView;
        this.zzblh = zzks;
    }

    public final void run() {
        if (this.zzblg.zza(this.zzblh)) {
            this.zzbli.zzblf.onPublisherAdViewLoaded(this.zzblg);
        } else {
            zzane.zzdk("Could not bind.");
        }
    }
}
