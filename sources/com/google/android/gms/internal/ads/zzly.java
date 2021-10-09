package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

@zzadh
public final class zzly {
    private zzjd zzapt;
    private AdListener zzapu;
    private AdSize[] zzarh;
    private final zzxm zzast;
    private final AtomicBoolean zzasu;
    /* access modifiers changed from: private */
    public final VideoController zzasv;
    private final zzkd zzasw;
    private Correlator zzasx;
    private zzks zzasy;
    private OnCustomRenderedAdLoadedListener zzasz;
    private ViewGroup zzata;
    private int zzatb;
    private final zzjm zzuk;
    private VideoOptions zzvg;
    private boolean zzvm;
    private AppEventListener zzvo;
    private String zzye;

    public zzly(ViewGroup viewGroup) {
        this(viewGroup, (AttributeSet) null, false, zzjm.zzara, 0);
    }

    public zzly(ViewGroup viewGroup, int i) {
        this(viewGroup, (AttributeSet) null, false, zzjm.zzara, i);
    }

    public zzly(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, zzjm.zzara, 0);
    }

    public zzly(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, int i) {
        this(viewGroup, attributeSet, false, zzjm.zzara, i);
    }

    private zzly(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzjm zzjm, int i) {
        this(viewGroup, attributeSet, z, zzjm, (zzks) null, i);
    }

    private zzly(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzjm zzjm, zzks zzks, int i) {
        this.zzast = new zzxm();
        this.zzasv = new VideoController();
        this.zzasw = new zzlz(this);
        this.zzata = viewGroup;
        this.zzuk = zzjm;
        this.zzasy = null;
        this.zzasu = new AtomicBoolean(false);
        this.zzatb = i;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                zzjq zzjq = new zzjq(context, attributeSet);
                this.zzarh = zzjq.zzi(z);
                this.zzye = zzjq.getAdUnitId();
                if (viewGroup.isInEditMode()) {
                    zzamu zzif = zzkb.zzif();
                    AdSize adSize = this.zzarh[0];
                    int i2 = this.zzatb;
                    zzjn zzjn = new zzjn(context, adSize);
                    zzjn.zzarg = zzu(i2);
                    zzif.zza(viewGroup, zzjn, "Ads by Google");
                }
            } catch (IllegalArgumentException e) {
                zzkb.zzif().zza(viewGroup, new zzjn(context, AdSize.BANNER), e.getMessage(), e.getMessage());
            }
        }
    }

    private static zzjn zza(Context context, AdSize[] adSizeArr, int i) {
        zzjn zzjn = new zzjn(context, adSizeArr);
        zzjn.zzarg = zzu(i);
        return zzjn;
    }

    private static boolean zzu(int i) {
        return i == 1;
    }

    public final void destroy() {
        try {
            if (this.zzasy != null) {
                this.zzasy.destroy();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final AdListener getAdListener() {
        return this.zzapu;
    }

    public final AdSize getAdSize() {
        zzjn zzbk;
        try {
            if (!(this.zzasy == null || (zzbk = this.zzasy.zzbk()) == null)) {
                return zzbk.zzhy();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        AdSize[] adSizeArr = this.zzarh;
        if (adSizeArr != null) {
            return adSizeArr[0];
        }
        return null;
    }

    public final AdSize[] getAdSizes() {
        return this.zzarh;
    }

    public final String getAdUnitId() {
        zzks zzks;
        if (this.zzye == null && (zzks = this.zzasy) != null) {
            try {
                this.zzye = zzks.getAdUnitId();
            } catch (RemoteException e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
        return this.zzye;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzvo;
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzasy != null) {
                return this.zzasy.zzck();
            }
            return null;
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzasz;
    }

    public final VideoController getVideoController() {
        return this.zzasv;
    }

    public final VideoOptions getVideoOptions() {
        return this.zzvg;
    }

    public final boolean isLoading() {
        try {
            if (this.zzasy != null) {
                return this.zzasy.isLoading();
            }
            return false;
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            return false;
        }
    }

    public final void pause() {
        try {
            if (this.zzasy != null) {
                this.zzasy.pause();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void recordManualImpression() {
        if (!this.zzasu.getAndSet(true)) {
            try {
                if (this.zzasy != null) {
                    this.zzasy.zzbm();
                }
            } catch (RemoteException e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void resume() {
        try {
            if (this.zzasy != null) {
                this.zzasy.resume();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setAdListener(AdListener adListener) {
        this.zzapu = adListener;
        this.zzasw.zza(adListener);
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (this.zzarh == null) {
            zza(adSizeArr);
            return;
        }
        throw new IllegalStateException("The ad size can only be set once on AdView.");
    }

    public final void setAdUnitId(String str) {
        if (this.zzye == null) {
            this.zzye = str;
            return;
        }
        throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzvo = appEventListener;
            if (this.zzasy != null) {
                this.zzasy.zza((zzla) appEventListener != null ? new zzjp(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setCorrelator(Correlator correlator) {
        this.zzasx = correlator;
        try {
            if (this.zzasy != null) {
                this.zzasy.zza((zzlg) correlator == null ? null : correlator.zzaz());
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzvm = z;
        try {
            if (this.zzasy != null) {
                this.zzasy.setManualImpressionsEnabled(z);
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzasz = onCustomRenderedAdLoadedListener;
        try {
            if (this.zzasy != null) {
                this.zzasy.zza((zzod) onCustomRenderedAdLoadedListener != null ? new zzog(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        this.zzvg = videoOptions;
        try {
            if (this.zzasy != null) {
                this.zzasy.zza(videoOptions == null ? null : new zzmu(videoOptions));
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zza(zzjd zzjd) {
        try {
            this.zzapt = zzjd;
            if (this.zzasy != null) {
                this.zzasy.zza((zzke) zzjd != null ? new zzje(zzjd) : null);
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zza(zzlw zzlw) {
        Object zza;
        try {
            if (this.zzasy == null) {
                if ((this.zzarh == null || this.zzye == null) && this.zzasy == null) {
                    throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
                }
                Context context = this.zzata.getContext();
                zzjn zza2 = zza(context, this.zzarh, this.zzatb);
                if ("search_v2".equals(zza2.zzarb)) {
                    zza = zzjr.zza(context, false, new zzjt(zzkb.zzig(), context, zza2, this.zzye));
                } else {
                    zza = zzjr.zza(context, false, new zzjs(zzkb.zzig(), context, zza2, this.zzye, this.zzast));
                }
                zzks zzks = (zzks) zza;
                this.zzasy = zzks;
                zzks.zza((zzkh) new zzjf(this.zzasw));
                if (this.zzapt != null) {
                    this.zzasy.zza((zzke) new zzje(this.zzapt));
                }
                if (this.zzvo != null) {
                    this.zzasy.zza((zzla) new zzjp(this.zzvo));
                }
                if (this.zzasz != null) {
                    this.zzasy.zza((zzod) new zzog(this.zzasz));
                }
                if (this.zzasx != null) {
                    this.zzasy.zza((zzlg) this.zzasx.zzaz());
                }
                if (this.zzvg != null) {
                    this.zzasy.zza(new zzmu(this.zzvg));
                }
                this.zzasy.setManualImpressionsEnabled(this.zzvm);
                try {
                    IObjectWrapper zzbj = this.zzasy.zzbj();
                    if (zzbj != null) {
                        this.zzata.addView((View) ObjectWrapper.unwrap(zzbj));
                    }
                } catch (RemoteException e) {
                    zzane.zzd("#007 Could not call remote method.", e);
                }
            }
            if (this.zzasy.zzb(zzjm.zza(this.zzata.getContext(), zzlw))) {
                this.zzast.zzj(zzlw.zzir());
            }
        } catch (RemoteException e2) {
            zzane.zzd("#007 Could not call remote method.", e2);
        }
    }

    public final void zza(AdSize... adSizeArr) {
        this.zzarh = adSizeArr;
        try {
            if (this.zzasy != null) {
                this.zzasy.zza(zza(this.zzata.getContext(), this.zzarh, this.zzatb));
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        this.zzata.requestLayout();
    }

    public final boolean zza(zzks zzks) {
        if (zzks == null) {
            return false;
        }
        try {
            IObjectWrapper zzbj = zzks.zzbj();
            if (zzbj == null || ((View) ObjectWrapper.unwrap(zzbj)).getParent() != null) {
                return false;
            }
            this.zzata.addView((View) ObjectWrapper.unwrap(zzbj));
            this.zzasy = zzks;
            return true;
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            return false;
        }
    }

    public final zzlo zzbc() {
        zzks zzks = this.zzasy;
        if (zzks == null) {
            return null;
        }
        try {
            return zzks.getVideoController();
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            return null;
        }
    }
}
