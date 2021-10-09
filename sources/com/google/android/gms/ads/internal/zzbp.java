package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzabc;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzcj;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkt;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzod;
import java.util.Map;
import java.util.concurrent.Future;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbp extends zzkt {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final zzjn zzaau;
    /* access modifiers changed from: private */
    public final Future<zzci> zzaav = zzaki.zza(new zzbs(this));
    private final zzbu zzaaw;
    /* access modifiers changed from: private */
    public WebView zzaax = new WebView(this.mContext);
    /* access modifiers changed from: private */
    public zzci zzaay;
    private AsyncTask<Void, Void, String> zzaaz;
    /* access modifiers changed from: private */
    public zzkh zzxs;
    /* access modifiers changed from: private */
    public final zzang zzyf;

    public zzbp(Context context, zzjn zzjn, String str, zzang zzang) {
        this.mContext = context;
        this.zzyf = zzang;
        this.zzaau = zzjn;
        this.zzaaw = new zzbu(str);
        zzk(0);
        this.zzaax.setVerticalScrollBarEnabled(false);
        this.zzaax.getSettings().setJavaScriptEnabled(true);
        this.zzaax.setWebViewClient(new zzbq(this));
        this.zzaax.setOnTouchListener(new zzbr(this));
    }

    /* access modifiers changed from: private */
    public final String zzv(String str) {
        if (this.zzaay == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = this.zzaay.zza(parse, this.mContext, (View) null, (Activity) null);
        } catch (zzcj e) {
            zzakb.zzc("Unable to process ad data", e);
        }
        return parse.toString();
    }

    /* access modifiers changed from: private */
    public final void zzw(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.mContext.startActivity(intent);
    }

    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzaaz.cancel(true);
        this.zzaav.cancel(true);
        this.zzaax.destroy();
        this.zzaax = null;
    }

    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final zzlo getVideoController() {
        return null;
    }

    public final boolean isLoading() throws RemoteException {
        return false;
    }

    public final boolean isReady() throws RemoteException {
        return false;
    }

    public final void pause() throws RemoteException {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
    }

    public final void resume() throws RemoteException {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
    }

    public final void setImmersiveMode(boolean z) {
        throw new IllegalStateException("Unused method");
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
    }

    public final void setUserId(String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void stopLoading() throws RemoteException {
    }

    public final void zza(zzaaw zzaaw2) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzabc zzabc, String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzahe zzahe) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzjn zzjn) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    public final void zza(zzke zzke) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzkh zzkh) throws RemoteException {
        this.zzxs = zzkh;
    }

    public final void zza(zzkx zzkx) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzla zzla) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzlg zzlg) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzlu zzlu) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzmu zzmu) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzod zzod) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final boolean zzb(zzjj zzjj) throws RemoteException {
        Preconditions.checkNotNull(this.zzaax, "This Search Ad has already been torn down");
        this.zzaaw.zza(zzjj, this.zzyf);
        this.zzaaz = new zzbt(this, (zzbq) null).execute(new Void[0]);
        return true;
    }

    public final Bundle zzba() {
        throw new IllegalStateException("Unused method");
    }

    public final IObjectWrapper zzbj() throws RemoteException {
        Preconditions.checkMainThread("getAdFrame must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzaax);
    }

    public final zzjn zzbk() throws RemoteException {
        return this.zzaau;
    }

    public final void zzbm() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final zzla zzbw() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    public final zzkh zzbx() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    public final String zzck() throws RemoteException {
        return null;
    }

    /* access modifiers changed from: package-private */
    public final String zzea() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https://").appendEncodedPath((String) zzkb.zzik().zzd(zznk.zzbcz));
        builder.appendQueryParameter(SearchIntents.EXTRA_QUERY, this.zzaaw.getQuery());
        builder.appendQueryParameter("pubId", this.zzaaw.zzed());
        Map<String, String> zzee = this.zzaaw.zzee();
        for (String next : zzee.keySet()) {
            builder.appendQueryParameter(next, zzee.get(next));
        }
        Uri build = builder.build();
        zzci zzci = this.zzaay;
        if (zzci != null) {
            try {
                build = zzci.zza(build, this.mContext);
            } catch (zzcj e) {
                zzakb.zzc("Unable to process ad data", e);
            }
        }
        String zzeb = zzeb();
        String encodedQuery = build.getEncodedQuery();
        StringBuilder sb = new StringBuilder(String.valueOf(zzeb).length() + 1 + String.valueOf(encodedQuery).length());
        sb.append(zzeb);
        sb.append("#");
        sb.append(encodedQuery);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zzeb() {
        String zzec = this.zzaaw.zzec();
        if (TextUtils.isEmpty(zzec)) {
            zzec = "www.google.com";
        }
        String str = (String) zzkb.zzik().zzd(zznk.zzbcz);
        StringBuilder sb = new StringBuilder(String.valueOf(zzec).length() + 8 + String.valueOf(str).length());
        sb.append("https://");
        sb.append(zzec);
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final void zzk(int i) {
        if (this.zzaax != null) {
            this.zzaax.setLayoutParams(new ViewGroup.LayoutParams(-1, i));
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzu(String str) {
        String queryParameter = Uri.parse(str).getQueryParameter("height");
        if (TextUtils.isEmpty(queryParameter)) {
            return 0;
        }
        try {
            zzkb.zzif();
            return zzamu.zza(this.mContext, Integer.parseInt(queryParameter));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }
}
