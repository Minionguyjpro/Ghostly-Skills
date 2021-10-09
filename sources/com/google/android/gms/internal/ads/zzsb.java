package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzsb extends zzrj {
    /* access modifiers changed from: private */
    public final OnPublisherAdViewLoadedListener zzblf;

    public zzsb(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener) {
        this.zzblf = onPublisherAdViewLoadedListener;
    }

    public final void zza(zzks zzks, IObjectWrapper iObjectWrapper) {
        if (zzks != null && iObjectWrapper != null) {
            PublisherAdView publisherAdView = new PublisherAdView((Context) ObjectWrapper.unwrap(iObjectWrapper));
            AppEventListener appEventListener = null;
            try {
                if (zzks.zzbx() instanceof zzjf) {
                    zzjf zzjf = (zzjf) zzks.zzbx();
                    publisherAdView.setAdListener(zzjf != null ? zzjf.getAdListener() : null);
                }
            } catch (RemoteException e) {
                zzane.zzb("", e);
            }
            try {
                if (zzks.zzbw() instanceof zzjp) {
                    zzjp zzjp = (zzjp) zzks.zzbw();
                    if (zzjp != null) {
                        appEventListener = zzjp.getAppEventListener();
                    }
                    publisherAdView.setAppEventListener(appEventListener);
                }
            } catch (RemoteException e2) {
                zzane.zzb("", e2);
            }
            zzamu.zzsy.post(new zzsc(this, publisherAdView, zzks));
        }
    }
}
