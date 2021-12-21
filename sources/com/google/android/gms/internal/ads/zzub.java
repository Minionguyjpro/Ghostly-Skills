package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.dynamic.IObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzub extends zzkt {
    private final zzss zzbom;
    private zzal zzbor;
    private final zztt zzbpd;
    private final String zzye;
    private boolean zzyu;

    public zzub(Context context, String str, zzxn zzxn, zzang zzang, zzw zzw) {
        this(str, new zzss(context, zzxn, zzang, zzw));
    }

    private zzub(String str, zzss zzss) {
        this.zzye = str;
        this.zzbom = zzss;
        this.zzbpd = new zztt();
        zzbv.zzex().zza(zzss);
    }

    private final void abort() {
        if (this.zzbor == null) {
            zzal zzav = this.zzbom.zzav(this.zzye);
            this.zzbor = zzav;
            this.zzbpd.zzd(zzav);
        }
    }

    public final void destroy() throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            zzal.destroy();
        }
    }

    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            return zzal.getMediationAdapterClassName();
        }
        return null;
    }

    public final zzlo getVideoController() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public final boolean isLoading() throws RemoteException {
        zzal zzal = this.zzbor;
        return zzal != null && zzal.isLoading();
    }

    public final boolean isReady() throws RemoteException {
        zzal zzal = this.zzbor;
        return zzal != null && zzal.isReady();
    }

    public final void pause() throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            zzal.pause();
        }
    }

    public final void resume() throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            zzal.resume();
        }
    }

    public final void setImmersiveMode(boolean z) {
        this.zzyu = z;
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
        abort();
        zzal zzal = this.zzbor;
        if (zzal != null) {
            zzal.setManualImpressionsEnabled(z);
        }
    }

    public final void setUserId(String str) {
    }

    public final void showInterstitial() throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            zzal.setImmersiveMode(this.zzyu);
            this.zzbor.showInterstitial();
            return;
        }
        zzakb.zzdk("Interstitial ad must be loaded before showInterstitial().");
    }

    public final void stopLoading() throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            zzal.stopLoading();
        }
    }

    public final void zza(zzaaw zzaaw) throws RemoteException {
        zzakb.zzdk("setInAppPurchaseListener is deprecated and should not be called.");
    }

    public final void zza(zzabc zzabc, String str) throws RemoteException {
        zzakb.zzdk("setPlayStorePurchaseParams is deprecated and should not be called.");
    }

    public final void zza(zzahe zzahe) {
        this.zzbpd.zzboh = zzahe;
        zzal zzal = this.zzbor;
        if (zzal != null) {
            this.zzbpd.zzd(zzal);
        }
    }

    public final void zza(zzjn zzjn) throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            zzal.zza(zzjn);
        }
    }

    public final void zza(zzke zzke) throws RemoteException {
        this.zzbpd.zzbog = zzke;
        zzal zzal = this.zzbor;
        if (zzal != null) {
            this.zzbpd.zzd(zzal);
        }
    }

    public final void zza(zzkh zzkh) throws RemoteException {
        this.zzbpd.zzxs = zzkh;
        zzal zzal = this.zzbor;
        if (zzal != null) {
            this.zzbpd.zzd(zzal);
        }
    }

    public final void zza(zzkx zzkx) throws RemoteException {
        this.zzbpd.zzbod = zzkx;
        zzal zzal = this.zzbor;
        if (zzal != null) {
            this.zzbpd.zzd(zzal);
        }
    }

    public final void zza(zzla zzla) throws RemoteException {
        this.zzbpd.zzboe = zzla;
        zzal zzal = this.zzbor;
        if (zzal != null) {
            this.zzbpd.zzd(zzal);
        }
    }

    public final void zza(zzlg zzlg) throws RemoteException {
        abort();
        zzal zzal = this.zzbor;
        if (zzal != null) {
            zzal.zza(zzlg);
        }
    }

    public final void zza(zzlu zzlu) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzmu zzmu) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public final void zza(zzod zzod) throws RemoteException {
        this.zzbpd.zzbof = zzod;
        zzal zzal = this.zzbor;
        if (zzal != null) {
            this.zzbpd.zzd(zzal);
        }
    }

    public final boolean zzb(zzjj zzjj) throws RemoteException {
        if (!zztw.zzh(zzjj).contains("gw")) {
            abort();
        }
        if (zztw.zzh(zzjj).contains("_skipMediation")) {
            abort();
        }
        if (zzjj.zzaqd != null) {
            abort();
        }
        zzal zzal = this.zzbor;
        if (zzal != null) {
            return zzal.zzb(zzjj);
        }
        zztw zzex = zzbv.zzex();
        if (zztw.zzh(zzjj).contains("_ad")) {
            zzex.zzb(zzjj, this.zzye);
        }
        zztz zza = zzex.zza(zzjj, this.zzye);
        if (zza != null) {
            if (!zza.zzwa) {
                zza.load();
                zzua.zzlk().zzlo();
            } else {
                zzua.zzlk().zzln();
            }
            this.zzbor = zza.zzbor;
            zza.zzbot.zza(this.zzbpd);
            this.zzbpd.zzd(this.zzbor);
            return zza.zzbov;
        }
        abort();
        zzua.zzlk().zzlo();
        return this.zzbor.zzb(zzjj);
    }

    public final Bundle zzba() throws RemoteException {
        zzal zzal = this.zzbor;
        return zzal != null ? zzal.zzba() : new Bundle();
    }

    public final IObjectWrapper zzbj() throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            return zzal.zzbj();
        }
        return null;
    }

    public final zzjn zzbk() throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            return zzal.zzbk();
        }
        return null;
    }

    public final void zzbm() throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            zzal.zzbm();
        } else {
            zzakb.zzdk("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }

    public final zzla zzbw() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    public final zzkh zzbx() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    public final String zzck() throws RemoteException {
        zzal zzal = this.zzbor;
        if (zzal != null) {
            return zzal.zzck();
        }
        return null;
    }
}
