package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzadh
public final class zzahv extends zzaid {
    private volatile zzahw zzclm;
    private volatile zzaht zzcma;
    private volatile zzahu zzcmb;
    private volatile zzaia zzcmc;

    public zzahv(zzahu zzahu) {
        this.zzcmb = zzahu;
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaig zzaig) {
        if (this.zzcmb != null) {
            this.zzcmb.zzc(zzaig);
        }
    }

    public final void zza(zzaht zzaht) {
        this.zzcma = zzaht;
    }

    public final void zza(zzahw zzahw) {
        this.zzclm = zzahw;
    }

    public final void zza(zzaia zzaia) {
        this.zzcmc = zzaia;
    }

    public final void zzc(Bundle bundle) {
        if (this.zzcmc != null) {
            this.zzcmc.zzc(bundle);
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper, int i) {
        if (this.zzcma != null) {
            this.zzcma.zzac(i);
        }
    }

    public final void zzd(IObjectWrapper iObjectWrapper, int i) {
        if (this.zzclm != null) {
            this.zzclm.zza(ObjectWrapper.unwrap(iObjectWrapper).getClass().getName(), i);
        }
    }

    public final void zzq(IObjectWrapper iObjectWrapper) {
        if (this.zzcma != null) {
            this.zzcma.zzpc();
        }
    }

    public final void zzr(IObjectWrapper iObjectWrapper) {
        if (this.zzclm != null) {
            this.zzclm.zzcb(ObjectWrapper.unwrap(iObjectWrapper).getClass().getName());
        }
    }

    public final void zzs(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoAdOpened();
        }
    }

    public final void zzt(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoStarted();
        }
    }

    public final void zzu(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoAdClosed();
        }
    }

    public final void zzv(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.zzdm();
        }
    }

    public final void zzw(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoAdLeftApplication();
        }
    }

    public final void zzx(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoCompleted();
        }
    }
}
