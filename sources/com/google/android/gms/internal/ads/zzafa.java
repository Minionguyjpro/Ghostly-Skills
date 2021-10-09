package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.appnext.base.b.c;
import com.appnext.base.b.i;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.internal.gmsg.HttpClient;
import com.google.android.gms.ads.internal.gmsg.zzaa;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafa extends zzajx {
    private static final Object sLock = new Object();
    private static final long zzcgc = TimeUnit.SECONDS.toMillis(10);
    private static boolean zzcgd = false;
    /* access modifiers changed from: private */
    public static zzvf zzcge = null;
    private static HttpClient zzcgf = null;
    /* access modifiers changed from: private */
    public static zzaa zzcgg = null;
    private static zzv<Object> zzcgh = null;
    private final Context mContext;
    private final Object zzbzh = new Object();
    /* access modifiers changed from: private */
    public final zzadj zzccf;
    private final zzaeg zzccg;
    private zzhx zzcci;
    /* access modifiers changed from: private */
    public zzvs zzcgi;

    public zzafa(Context context, zzaeg zzaeg, zzadj zzadj, zzhx zzhx) {
        super(true);
        this.zzccf = zzadj;
        this.mContext = context;
        this.zzccg = zzaeg;
        this.zzcci = zzhx;
        synchronized (sLock) {
            if (!zzcgd) {
                zzcgg = new zzaa();
                zzcgf = new HttpClient(context.getApplicationContext(), zzaeg.zzacr);
                zzcgh = new zzafi();
                zzcge = new zzvf(this.mContext.getApplicationContext(), this.zzccg.zzacr, (String) zzkb.zzik().zzd(zznk.zzaub), new zzafh(), new zzafg());
                zzcgd = true;
            }
        }
    }

    private final JSONObject zza(zzaef zzaef, String str) {
        zzaga zzaga;
        AdvertisingIdClient.Info info;
        Bundle bundle = zzaef.zzccv.extras.getBundle("sdk_less_server_data");
        if (bundle == null) {
            return null;
        }
        try {
            zzaga = zzbv.zzev().zzq(this.mContext).get();
        } catch (Exception e) {
            zzakb.zzc("Error grabbing device info: ", e);
            zzaga = null;
        }
        Context context = this.mContext;
        zzafl zzafl = new zzafl();
        zzafl.zzcgs = zzaef;
        zzafl.zzcgt = zzaga;
        JSONObject zza = zzafs.zza(context, zzafl);
        if (zza == null) {
            return null;
        }
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e2) {
            zzakb.zzc("Cannot get advertising id info", e2);
            info = null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("request_id", str);
        hashMap.put("request_param", zza);
        hashMap.put(c.DATA, bundle);
        if (info != null) {
            hashMap.put("adid", info.getId());
            hashMap.put(i.fC, Integer.valueOf(info.isLimitAdTrackingEnabled() ? 1 : 0));
        }
        try {
            return zzbv.zzek().zzn(hashMap);
        } catch (JSONException unused) {
            return null;
        }
    }

    protected static void zzb(zzuu zzuu) {
        zzuu.zza("/loadAd", zzcgg);
        zzuu.zza("/fetchHttpRequest", zzcgf);
        zzuu.zza("/invalidRequest", zzcgh);
    }

    private final zzaej zzc(zzaef zzaef) {
        zzbv.zzek();
        String zzrh = zzakk.zzrh();
        JSONObject zza = zza(zzaef, zzrh);
        if (zza == null) {
            return new zzaej(0);
        }
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        Future<JSONObject> zzas = zzcgg.zzas(zzrh);
        zzamu.zzsy.post(new zzafc(this, zza, zzrh));
        try {
            JSONObject jSONObject = zzas.get(zzcgc - (zzbv.zzer().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new zzaej(-1);
            }
            zzaej zza2 = zzafs.zza(this.mContext, zzaef, jSONObject.toString());
            return (zza2.errorCode == -3 || !TextUtils.isEmpty(zza2.zzceo)) ? zza2 : new zzaej(3);
        } catch (InterruptedException | CancellationException unused) {
            return new zzaej(-1);
        } catch (TimeoutException unused2) {
            return new zzaej(2);
        } catch (ExecutionException unused3) {
            return new zzaej(0);
        }
    }

    protected static void zzc(zzuu zzuu) {
        zzuu.zzb("/loadAd", zzcgg);
        zzuu.zzb("/fetchHttpRequest", zzcgf);
        zzuu.zzb("/invalidRequest", zzcgh);
    }

    public final void onStop() {
        synchronized (this.zzbzh) {
            zzamu.zzsy.post(new zzaff(this));
        }
    }

    public final void zzdn() {
        zzakb.zzck("SdkLessAdLoaderBackgroundTask started.");
        String zzab = zzbv.zzfh().zzab(this.mContext);
        zzaef zzaef = new zzaef(this.zzccg, -1, zzbv.zzfh().zzz(this.mContext), zzbv.zzfh().zzaa(this.mContext), zzab);
        zzbv.zzfh().zzg(this.mContext, zzab);
        zzaej zzc = zzc(zzaef);
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        zzaef zzaef2 = zzaef;
        zzamu.zzsy.post(new zzafb(this, new zzaji(zzaef2, zzc, (zzwy) null, (zzjn) null, zzc.errorCode, elapsedRealtime, zzc.zzceu, (JSONObject) null, this.zzcci)));
    }
}
