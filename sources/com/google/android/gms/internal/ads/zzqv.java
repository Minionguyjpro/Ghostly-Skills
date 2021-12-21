package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import java.util.WeakHashMap;

@zzadh
public final class zzqv implements NativeCustomTemplateAd {
    private static WeakHashMap<IBinder, zzqv> zzbkt = new WeakHashMap<>();
    private final VideoController zzasv = new VideoController();
    private final zzqs zzbku;
    private final MediaView zzbkv;

    private zzqv(zzqs zzqs) {
        Context context;
        this.zzbku = zzqs;
        MediaView mediaView = null;
        try {
            context = (Context) ObjectWrapper.unwrap(zzqs.zzkh());
        } catch (RemoteException | NullPointerException e) {
            zzane.zzb("", e);
            context = null;
        }
        if (context != null) {
            MediaView mediaView2 = new MediaView(context);
            try {
                if (this.zzbku.zzh(ObjectWrapper.wrap(mediaView2))) {
                    mediaView = mediaView2;
                }
            } catch (RemoteException e2) {
                zzane.zzb("", e2);
            }
        }
        this.zzbkv = mediaView;
    }

    public static zzqv zza(zzqs zzqs) {
        synchronized (zzbkt) {
            zzqv zzqv = zzbkt.get(zzqs.asBinder());
            if (zzqv != null) {
                return zzqv;
            }
            zzqv zzqv2 = new zzqv(zzqs);
            zzbkt.put(zzqs.asBinder(), zzqv2);
            return zzqv2;
        }
    }

    public final void destroy() {
        try {
            this.zzbku.destroy();
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final List<String> getAvailableAssetNames() {
        try {
            return this.zzbku.getAvailableAssetNames();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getCustomTemplateId() {
        try {
            return this.zzbku.getCustomTemplateId();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final NativeAd.Image getImage(String str) {
        try {
            zzpw zzap = this.zzbku.zzap(str);
            if (zzap != null) {
                return new zzpz(zzap);
            }
            return null;
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final CharSequence getText(String str) {
        try {
            return this.zzbku.zzao(str);
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            zzlo videoController = this.zzbku.getVideoController();
            if (videoController != null) {
                this.zzasv.zza(videoController);
            }
        } catch (RemoteException e) {
            zzane.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzasv;
    }

    public final MediaView getVideoMediaView() {
        return this.zzbkv;
    }

    public final void performClick(String str) {
        try {
            this.zzbku.performClick(str);
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzbku.recordImpression();
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final zzqs zzku() {
        return this.zzbku;
    }
}
