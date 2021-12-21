package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzadh
public final class zzyk extends zzxr {
    private final MediationAdapter zzbus;
    private zzyl zzbut;

    public zzyk(MediationAdapter mediationAdapter) {
        this.zzbus = mediationAdapter;
    }

    private final Bundle zza(String str, zzjj zzjj, String str2) throws RemoteException {
        String valueOf = String.valueOf(str);
        zzane.zzdk(valueOf.length() != 0 ? "Server parameters: ".concat(valueOf) : new String("Server parameters: "));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.zzbus instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zzjj != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zzjj.zzaqa);
                }
            }
            return bundle;
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    private static boolean zzm(zzjj zzjj) {
        if (zzjj.zzapz) {
            return true;
        }
        zzkb.zzif();
        return zzamu.zzsg();
    }

    public final void destroy() throws RemoteException {
        try {
            this.zzbus.onDestroy();
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        MediationAdapter mediationAdapter = this.zzbus;
        if (mediationAdapter instanceof zzatm) {
            return ((zzatm) mediationAdapter).getInterstitialAdapterInfo();
        }
        String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
        zzane.zzdk(valueOf.length() != 0 ? "Not a v2 MediationInterstitialAdapter: ".concat(valueOf) : new String("Not a v2 MediationInterstitialAdapter: "));
        return new Bundle();
    }

    public final zzlo getVideoController() {
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof zza)) {
            return null;
        }
        try {
            return ((zza) mediationAdapter).getVideoController();
        } catch (Throwable th) {
            zzane.zzb("", th);
            return null;
        }
    }

    public final IObjectWrapper getView() throws RemoteException {
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? "Not a MediationBannerAdapter: ".concat(valueOf) : new String("Not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        try {
            return ObjectWrapper.wrap(((MediationBannerAdapter) mediationAdapter).getBannerView());
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final boolean isInitialized() throws RemoteException {
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? "Not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("Not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzane.zzck("Check if adapter is initialized.");
        try {
            return ((MediationRewardedVideoAdAdapter) this.zzbus).isInitialized();
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void pause() throws RemoteException {
        try {
            this.zzbus.onPause();
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void resume() throws RemoteException {
        try {
            this.zzbus.onResume();
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof OnImmersiveModeUpdatedListener)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdj(valueOf.length() != 0 ? "Not an OnImmersiveModeUpdatedListener: ".concat(valueOf) : new String("Not an OnImmersiveModeUpdatedListener: "));
            return;
        }
        try {
            ((OnImmersiveModeUpdatedListener) mediationAdapter).onImmersiveModeUpdated(z);
        } catch (Throwable th) {
            zzane.zzb("", th);
        }
    }

    public final void showInterstitial() throws RemoteException {
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? "Not a MediationInterstitialAdapter: ".concat(valueOf) : new String("Not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzane.zzck("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzbus).showInterstitial();
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void showVideo() throws RemoteException {
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? "Not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("Not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzane.zzck("Show rewarded video ad from adapter.");
        try {
            ((MediationRewardedVideoAdAdapter) this.zzbus).showVideo();
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaic zzaic, List<String> list) throws RemoteException {
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof InitializableMediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? "Not an InitializableMediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("Not an InitializableMediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzane.zzck("Initialize rewarded video adapter.");
        try {
            InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter) this.zzbus;
            ArrayList arrayList = new ArrayList();
            for (String zza : list) {
                arrayList.add(zza(zza, (zzjj) null, (String) null));
            }
            initializableMediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzaif(zzaic), arrayList);
        } catch (Throwable th) {
            zzane.zzc("Could not initialize rewarded video adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, zzaic zzaic, String str2) throws RemoteException {
        Bundle bundle;
        zzyj zzyj;
        zzjj zzjj2 = zzjj;
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? "Not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("Not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzane.zzck("Initialize rewarded video adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzbus;
            Bundle zza = zza(str2, zzjj2, (String) null);
            if (zzjj2 != null) {
                zzyj zzyj2 = new zzyj(zzjj2.zzapw == -1 ? null : new Date(zzjj2.zzapw), zzjj2.zzapx, zzjj2.zzapy != null ? new HashSet(zzjj2.zzapy) : null, zzjj2.zzaqe, zzm(zzjj), zzjj2.zzaqa, zzjj2.zzaql);
                bundle = zzjj2.zzaqg != null ? zzjj2.zzaqg.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null;
                zzyj = zzyj2;
            } else {
                zzyj = null;
                bundle = null;
            }
            mediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), zzyj, str, new zzaif(zzaic), zza, bundle);
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, zzxt zzxt) throws RemoteException {
        zza(iObjectWrapper, zzjj, str, (String) null, zzxt);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, String str2, zzxt zzxt) throws RemoteException {
        zzjj zzjj2 = zzjj;
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? "Not a MediationInterstitialAdapter: ".concat(valueOf) : new String("Not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzane.zzck("Requesting interstitial ad from adapter.");
        try {
            MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzbus;
            Bundle bundle = null;
            zzyj zzyj = new zzyj(zzjj2.zzapw == -1 ? null : new Date(zzjj2.zzapw), zzjj2.zzapx, zzjj2.zzapy != null ? new HashSet(zzjj2.zzapy) : null, zzjj2.zzaqe, zzm(zzjj), zzjj2.zzaqa, zzjj2.zzaql);
            if (zzjj2.zzaqg != null) {
                bundle = zzjj2.zzaqg.getBundle(mediationInterstitialAdapter.getClass().getName());
            }
            mediationInterstitialAdapter.requestInterstitialAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzyl(zzxt), zza(str, zzjj2, str2), zzyj, bundle);
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, String str2, zzxt zzxt, zzpl zzpl, List<String> list) throws RemoteException {
        zzjj zzjj2 = zzjj;
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof MediationNativeAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? "Not a MediationNativeAdapter: ".concat(valueOf) : new String("Not a MediationNativeAdapter: "));
            throw new RemoteException();
        }
        try {
            MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) mediationAdapter;
            Bundle bundle = null;
            zzyo zzyo = new zzyo(zzjj2.zzapw == -1 ? null : new Date(zzjj2.zzapw), zzjj2.zzapx, zzjj2.zzapy != null ? new HashSet(zzjj2.zzapy) : null, zzjj2.zzaqe, zzm(zzjj), zzjj2.zzaqa, zzpl, list, zzjj2.zzaql);
            if (zzjj2.zzaqg != null) {
                bundle = zzjj2.zzaqg.getBundle(mediationNativeAdapter.getClass().getName());
            }
            this.zzbut = new zzyl(zzxt);
            mediationNativeAdapter.requestNativeAd((Context) ObjectWrapper.unwrap(iObjectWrapper), this.zzbut, zza(str, zzjj2, str2), zzyo, bundle);
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjn zzjn, zzjj zzjj, String str, zzxt zzxt) throws RemoteException {
        zza(iObjectWrapper, zzjn, zzjj, str, (String) null, zzxt);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjn zzjn, zzjj zzjj, String str, String str2, zzxt zzxt) throws RemoteException {
        zzjn zzjn2 = zzjn;
        zzjj zzjj2 = zzjj;
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? "Not a MediationBannerAdapter: ".concat(valueOf) : new String("Not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzane.zzck("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzbus;
            Bundle bundle = null;
            zzyj zzyj = new zzyj(zzjj2.zzapw == -1 ? null : new Date(zzjj2.zzapw), zzjj2.zzapx, zzjj2.zzapy != null ? new HashSet(zzjj2.zzapy) : null, zzjj2.zzaqe, zzm(zzjj), zzjj2.zzaqa, zzjj2.zzaql);
            if (zzjj2.zzaqg != null) {
                bundle = zzjj2.zzaqg.getBundle(mediationBannerAdapter.getClass().getName());
            }
            mediationBannerAdapter.requestBannerAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzyl(zzxt), zza(str, zzjj2, str2), zzb.zza(zzjn2.width, zzjn2.height, zzjn2.zzarb), zzyj, bundle);
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void zza(zzjj zzjj, String str, String str2) throws RemoteException {
        MediationAdapter mediationAdapter = this.zzbus;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? "Not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("Not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzane.zzck("Requesting rewarded video ad from adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzbus;
            Bundle bundle = null;
            zzyj zzyj = new zzyj(zzjj.zzapw == -1 ? null : new Date(zzjj.zzapw), zzjj.zzapx, zzjj.zzapy != null ? new HashSet(zzjj.zzapy) : null, zzjj.zzaqe, zzm(zzjj), zzjj.zzaqa, zzjj.zzaql);
            if (zzjj.zzaqg != null) {
                bundle = zzjj.zzaqg.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
            }
            mediationRewardedVideoAdAdapter.loadAd(zzyj, zza(str, zzjj, str2), bundle);
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void zzc(zzjj zzjj, String str) throws RemoteException {
        zza(zzjj, str, (String) null);
    }

    public final void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
        try {
            ((OnContextChangedListener) this.zzbus).onContextChanged((Context) ObjectWrapper.unwrap(iObjectWrapper));
        } catch (Throwable th) {
            zzane.zzc("Failed", th);
        }
    }

    public final zzxz zzmo() {
        NativeAdMapper zzmx = this.zzbut.zzmx();
        if (zzmx instanceof NativeAppInstallAdMapper) {
            return new zzym((NativeAppInstallAdMapper) zzmx);
        }
        return null;
    }

    public final zzyc zzmp() {
        NativeAdMapper zzmx = this.zzbut.zzmx();
        if (zzmx instanceof NativeContentAdMapper) {
            return new zzyn((NativeContentAdMapper) zzmx);
        }
        return null;
    }

    public final Bundle zzmq() {
        MediationAdapter mediationAdapter = this.zzbus;
        if (mediationAdapter instanceof zzatl) {
            return ((zzatl) mediationAdapter).zzmq();
        }
        String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
        zzane.zzdk(valueOf.length() != 0 ? "Not a v2 MediationBannerAdapter: ".concat(valueOf) : new String("Not a v2 MediationBannerAdapter: "));
        return new Bundle();
    }

    public final Bundle zzmr() {
        return new Bundle();
    }

    public final boolean zzms() {
        return this.zzbus instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    public final zzqs zzmt() {
        NativeCustomTemplateAd zzmz = this.zzbut.zzmz();
        if (zzmz instanceof zzqv) {
            return ((zzqv) zzmz).zzku();
        }
        return null;
    }

    public final zzyf zzmu() {
        UnifiedNativeAdMapper zzmy = this.zzbut.zzmy();
        if (zzmy != null) {
            return new zzze(zzmy);
        }
        return null;
    }
}
