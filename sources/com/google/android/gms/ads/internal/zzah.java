package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkl;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzrl;
import com.google.android.gms.internal.ads.zzxn;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzah extends zzkl {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final zzw zzwc;
    private final zzxn zzwh;
    private final zzkh zzxs;
    private final zzqw zzxt;
    private final zzrl zzxu;
    private final zzqz zzxv;
    private final zzri zzxw;
    private final zzjn zzxx;
    private final PublisherAdViewOptions zzxy;
    private final SimpleArrayMap<String, zzrf> zzxz;
    private final SimpleArrayMap<String, zzrc> zzya;
    private final zzpl zzyb;
    private final List<String> zzyc;
    private final zzlg zzyd;
    private final String zzye;
    private final zzang zzyf;
    private WeakReference<zzd> zzyg;

    zzah(Context context, String str, zzxn zzxn, zzang zzang, zzkh zzkh, zzqw zzqw, zzrl zzrl, zzqz zzqz, SimpleArrayMap<String, zzrf> simpleArrayMap, SimpleArrayMap<String, zzrc> simpleArrayMap2, zzpl zzpl, zzlg zzlg, zzw zzw, zzri zzri, zzjn zzjn, PublisherAdViewOptions publisherAdViewOptions) {
        this.mContext = context;
        this.zzye = str;
        this.zzwh = zzxn;
        this.zzyf = zzang;
        this.zzxs = zzkh;
        this.zzxv = zzqz;
        this.zzxt = zzqw;
        this.zzxu = zzrl;
        this.zzxz = simpleArrayMap;
        this.zzya = simpleArrayMap2;
        this.zzyb = zzpl;
        this.zzyc = zzdg();
        this.zzyd = zzlg;
        this.zzwc = zzw;
        this.zzxw = zzri;
        this.zzxx = zzjn;
        this.zzxy = publisherAdViewOptions;
        zznk.initialize(this.mContext);
    }

    private static void runOnUiThread(Runnable runnable) {
        zzakk.zzcrm.post(runnable);
    }

    /* access modifiers changed from: private */
    public final void zzb(zzjj zzjj, int i) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbcg)).booleanValue() || this.zzxu == null) {
            Context context = this.mContext;
            zzbc zzbc = new zzbc(context, this.zzwc, zzjn.zzf(context), this.zzye, this.zzwh, this.zzyf);
            this.zzyg = new WeakReference<>(zzbc);
            zzqw zzqw = this.zzxt;
            Preconditions.checkMainThread("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
            zzbc.zzvw.zzade = zzqw;
            zzrl zzrl = this.zzxu;
            Preconditions.checkMainThread("setOnUnifiedNativeAdLoadedListener must be called on the main UI thread.");
            zzbc.zzvw.zzadg = zzrl;
            zzqz zzqz = this.zzxv;
            Preconditions.checkMainThread("setOnContentAdLoadedListener must be called on the main UI thread.");
            zzbc.zzvw.zzadf = zzqz;
            SimpleArrayMap<String, zzrf> simpleArrayMap = this.zzxz;
            Preconditions.checkMainThread("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
            zzbc.zzvw.zzadi = simpleArrayMap;
            zzbc.zza(this.zzxs);
            SimpleArrayMap<String, zzrc> simpleArrayMap2 = this.zzya;
            Preconditions.checkMainThread("setOnCustomClickListener must be called on the main UI thread.");
            zzbc.zzvw.zzadh = simpleArrayMap2;
            zzbc.zzd(zzdg());
            zzpl zzpl = this.zzyb;
            Preconditions.checkMainThread("setNativeAdOptions must be called on the main UI thread.");
            zzbc.zzvw.zzadj = zzpl;
            zzbc.zza(this.zzyd);
            zzbc.zzj(i);
            zzbc.zzb(zzjj);
            return;
        }
        zzi(0);
    }

    /* access modifiers changed from: private */
    public final boolean zzde() {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaym)).booleanValue() && this.zzxw != null;
    }

    private final boolean zzdf() {
        if (this.zzxt != null || this.zzxv != null || this.zzxu != null) {
            return true;
        }
        SimpleArrayMap<String, zzrf> simpleArrayMap = this.zzxz;
        return simpleArrayMap != null && simpleArrayMap.size() > 0;
    }

    private final List<String> zzdg() {
        ArrayList arrayList = new ArrayList();
        if (this.zzxv != null) {
            arrayList.add("1");
        }
        if (this.zzxt != null) {
            arrayList.add(InternalAvidAdSessionContext.AVID_API_LEVEL);
        }
        if (this.zzxu != null) {
            arrayList.add("6");
        }
        if (this.zzxz.size() > 0) {
            arrayList.add("3");
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final void zze(zzjj zzjj) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbcg)).booleanValue() || this.zzxu == null) {
            zzq zzq = new zzq(this.mContext, this.zzwc, this.zzxx, this.zzye, this.zzwh, this.zzyf);
            this.zzyg = new WeakReference<>(zzq);
            zzri zzri = this.zzxw;
            Preconditions.checkMainThread("setOnPublisherAdViewLoadedListener must be called on the main UI thread.");
            zzq.zzvw.zzadm = zzri;
            PublisherAdViewOptions publisherAdViewOptions = this.zzxy;
            if (publisherAdViewOptions != null) {
                if (publisherAdViewOptions.zzbg() != null) {
                    zzq.zza(this.zzxy.zzbg());
                }
                zzq.setManualImpressionsEnabled(this.zzxy.getManualImpressionsEnabled());
            }
            zzqw zzqw = this.zzxt;
            Preconditions.checkMainThread("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
            zzq.zzvw.zzade = zzqw;
            zzrl zzrl = this.zzxu;
            Preconditions.checkMainThread("setOnUnifiedNativeAdLoadedListener must be called on the main UI thread.");
            zzq.zzvw.zzadg = zzrl;
            zzqz zzqz = this.zzxv;
            Preconditions.checkMainThread("setOnContentAdLoadedListener must be called on the main UI thread.");
            zzq.zzvw.zzadf = zzqz;
            SimpleArrayMap<String, zzrf> simpleArrayMap = this.zzxz;
            Preconditions.checkMainThread("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
            zzq.zzvw.zzadi = simpleArrayMap;
            SimpleArrayMap<String, zzrc> simpleArrayMap2 = this.zzya;
            Preconditions.checkMainThread("setOnCustomClickListener must be called on the main UI thread.");
            zzq.zzvw.zzadh = simpleArrayMap2;
            zzpl zzpl = this.zzyb;
            Preconditions.checkMainThread("setNativeAdOptions must be called on the main UI thread.");
            zzq.zzvw.zzadj = zzpl;
            zzq.zzd(zzdg());
            zzq.zza(this.zzxs);
            zzq.zza(this.zzyd);
            ArrayList arrayList = new ArrayList();
            if (zzdf()) {
                arrayList.add(1);
            }
            if (this.zzxw != null) {
                arrayList.add(2);
            }
            zzq.zze(arrayList);
            if (zzdf()) {
                zzjj.extras.putBoolean("ina", true);
            }
            if (this.zzxw != null) {
                zzjj.extras.putBoolean("iba", true);
            }
            zzq.zzb(zzjj);
            return;
        }
        zzi(0);
    }

    private final void zzi(int i) {
        zzkh zzkh = this.zzxs;
        if (zzkh != null) {
            try {
                zzkh.onAdFailedToLoad(0);
            } catch (RemoteException e) {
                zzakb.zzc("Failed calling onAdFailedToLoad.", e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getMediationAdapterClassName() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzd> r1 = r3.zzyg     // Catch:{ all -> 0x001a }
            r2 = 0
            if (r1 == 0) goto L_0x0018
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzd> r1 = r3.zzyg     // Catch:{ all -> 0x001a }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x001a }
            com.google.android.gms.ads.internal.zzd r1 = (com.google.android.gms.ads.internal.zzd) r1     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x0016
            java.lang.String r2 = r1.getMediationAdapterClassName()     // Catch:{ all -> 0x001a }
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x001a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzah.getMediationAdapterClassName():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isLoading() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzd> r1 = r3.zzyg     // Catch:{ all -> 0x001a }
            r2 = 0
            if (r1 == 0) goto L_0x0018
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzd> r1 = r3.zzyg     // Catch:{ all -> 0x001a }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x001a }
            com.google.android.gms.ads.internal.zzd r1 = (com.google.android.gms.ads.internal.zzd) r1     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x0016
            boolean r2 = r1.isLoading()     // Catch:{ all -> 0x001a }
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x001a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzah.isLoading():boolean");
    }

    public final void zza(zzjj zzjj, int i) {
        if (i > 0) {
            runOnUiThread(new zzaj(this, zzjj, i));
            return;
        }
        throw new IllegalArgumentException("Number of ads has to be more than 0");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzck() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzd> r1 = r3.zzyg     // Catch:{ all -> 0x001a }
            r2 = 0
            if (r1 == 0) goto L_0x0018
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzd> r1 = r3.zzyg     // Catch:{ all -> 0x001a }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x001a }
            com.google.android.gms.ads.internal.zzd r1 = (com.google.android.gms.ads.internal.zzd) r1     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x0016
            java.lang.String r2 = r1.zzck()     // Catch:{ all -> 0x001a }
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x001a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzah.zzck():java.lang.String");
    }

    public final void zzd(zzjj zzjj) {
        runOnUiThread(new zzai(this, zzjj));
    }
}
