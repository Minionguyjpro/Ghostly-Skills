package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzadh
public final class zzmb {
    private static final Object sLock = new Object();
    private static zzmb zzate;
    private zzlj zzatf;
    private RewardedVideoAd zzatg;

    private zzmb() {
    }

    public static zzmb zziv() {
        zzmb zzmb;
        synchronized (sLock) {
            if (zzate == null) {
                zzate = new zzmb();
            }
            zzmb = zzate;
        }
        return zzmb;
    }

    public final RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        synchronized (sLock) {
            if (this.zzatg != null) {
                RewardedVideoAd rewardedVideoAd = this.zzatg;
                return rewardedVideoAd;
            }
            zzahm zzahm = new zzahm(context, (zzagz) zzjr.zza(context, false, new zzjz(zzkb.zzig(), context, new zzxm())));
            this.zzatg = zzahm;
            return zzahm;
        }
    }

    public final void openDebugMenu(Context context, String str) {
        Preconditions.checkState(this.zzatf != null, "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzatf.zzb(ObjectWrapper.wrap(context), str);
        } catch (RemoteException e) {
            zzane.zzb("Unable to open debug menu.", e);
        }
    }

    public final void setAppMuted(boolean z) {
        Preconditions.checkState(this.zzatf != null, "MobileAds.initialize() must be called prior to setting app muted state.");
        try {
            this.zzatf.setAppMuted(z);
        } catch (RemoteException e) {
            zzane.zzb("Unable to set app mute state.", e);
        }
    }

    public final void setAppVolume(float f) {
        boolean z = true;
        Preconditions.checkArgument(0.0f <= f && f <= 1.0f, "The app volume must be a value between 0 and 1 inclusive.");
        if (this.zzatf == null) {
            z = false;
        }
        Preconditions.checkState(z, "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzatf.setAppVolume(f);
        } catch (RemoteException e) {
            zzane.zzb("Unable to set app volume.", e);
        }
    }

    public final void zza(Context context, String str, zzmd zzmd) {
        synchronized (sLock) {
            if (this.zzatf == null) {
                if (context != null) {
                    try {
                        zzlj zzlj = (zzlj) zzjr.zza(context, false, new zzjw(zzkb.zzig(), context));
                        this.zzatf = zzlj;
                        zzlj.zza();
                        if (str != null) {
                            this.zzatf.zza(str, ObjectWrapper.wrap(new zzmc(this, context)));
                        }
                    } catch (RemoteException e) {
                        zzane.zzc("MobileAdsSettingManager initialization failed", e);
                    }
                } else {
                    throw new IllegalArgumentException("Context cannot be null.");
                }
            }
        }
    }

    public final float zzdo() {
        zzlj zzlj = this.zzatf;
        if (zzlj == null) {
            return 1.0f;
        }
        try {
            return zzlj.zzdo();
        } catch (RemoteException e) {
            zzane.zzb("Unable to get app volume.", e);
            return 1.0f;
        }
    }

    public final boolean zzdp() {
        zzlj zzlj = this.zzatf;
        if (zzlj == null) {
            return false;
        }
        try {
            return zzlj.zzdp();
        } catch (RemoteException e) {
            zzane.zzb("Unable to get app mute state.", e);
            return false;
        }
    }
}
