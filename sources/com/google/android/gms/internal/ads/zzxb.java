package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.appnext.core.Ad;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzxb implements zzxf {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private zzjj zzaao;
    private final zzjn zzaau;
    private final boolean zzael;
    /* access modifiers changed from: private */
    public final String zzbth;
    private final long zzbti;
    private final zzwy zzbtj;
    private final zzwx zzbtk;
    private final List<String> zzbtl;
    private final List<String> zzbtm;
    private final boolean zzbtn;
    private final boolean zzbto;
    /* access modifiers changed from: private */
    public zzxq zzbtp;
    /* access modifiers changed from: private */
    public int zzbtq = -2;
    private zzxw zzbtr;
    private final zzxn zzwh;
    private final zzpl zzyb;
    private final List<String> zzyc;
    private final zzang zzyf;

    public zzxb(Context context, String str, zzxn zzxn, zzwy zzwy, zzwx zzwx, zzjj zzjj, zzjn zzjn, zzang zzang, boolean z, boolean z2, zzpl zzpl, List<String> list, List<String> list2, List<String> list3, boolean z3) {
        String str2 = str;
        zzwy zzwy2 = zzwy;
        zzwx zzwx2 = zzwx;
        this.mContext = context;
        this.zzwh = zzxn;
        this.zzbtk = zzwx2;
        this.zzbth = "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str) ? zzmh() : str2;
        this.zzbtj = zzwy2;
        this.zzbti = zzwx2.zzbsl != -1 ? zzwx2.zzbsl : zzwy2.zzbsl != -1 ? zzwy2.zzbsl : 10000;
        this.zzaao = zzjj;
        this.zzaau = zzjn;
        this.zzyf = zzang;
        this.zzael = z;
        this.zzbtn = z2;
        this.zzyb = zzpl;
        this.zzyc = list;
        this.zzbtl = list2;
        this.zzbtm = list3;
        this.zzbto = z3;
    }

    private static zzxq zza(MediationAdapter mediationAdapter) {
        return new zzyk(mediationAdapter);
    }

    /* access modifiers changed from: private */
    public final void zza(zzxa zzxa) {
        String zzbk = zzbk(this.zzbtk.zzbsb);
        try {
            if (this.zzyf.zzcvf >= 4100000) {
                if (!this.zzael) {
                    if (!this.zzbtk.zzmg()) {
                        if (this.zzaau.zzarc) {
                            this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaao, zzbk, this.zzbtk.zzbrr, (zzxt) zzxa);
                            return;
                        } else if (!this.zzbtn) {
                            this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaau, this.zzaao, zzbk, this.zzbtk.zzbrr, zzxa);
                            return;
                        } else if (this.zzbtk.zzbsf != null) {
                            this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaao, zzbk, this.zzbtk.zzbrr, zzxa, new zzpl(zzbl(this.zzbtk.zzbsj)), this.zzbtk.zzbsi);
                            return;
                        } else {
                            this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaau, this.zzaao, zzbk, this.zzbtk.zzbrr, zzxa);
                            return;
                        }
                    }
                }
                ArrayList arrayList = new ArrayList(this.zzyc);
                if (this.zzbtl != null) {
                    for (String next : this.zzbtl) {
                        String str = ":false";
                        if (this.zzbtm != null && this.zzbtm.contains(next)) {
                            str = ":true";
                        }
                        StringBuilder sb = new StringBuilder(String.valueOf(next).length() + 7 + str.length());
                        sb.append("custom:");
                        sb.append(next);
                        sb.append(str);
                        arrayList.add(sb.toString());
                    }
                }
                this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaao, zzbk, this.zzbtk.zzbrr, zzxa, this.zzyb, arrayList);
            } else if (this.zzaau.zzarc) {
                this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaao, zzbk, zzxa);
            } else {
                this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaau, this.zzaao, zzbk, (zzxt) zzxa);
            }
        } catch (RemoteException e) {
            zzakb.zzc("Could not request ad from mediation adapter.", e);
            zzx(5);
        }
    }

    private final String zzbk(String str) {
        if (str != null && zzmk() && !zzy(2)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.remove("cpm_floor_cents");
                return jSONObject.toString();
            } catch (JSONException unused) {
                zzakb.zzdk("Could not remove field. Returning the original value");
            }
        }
        return str;
    }

    private static NativeAdOptions zzbl(String str) {
        NativeAdOptions.Builder builder = new NativeAdOptions.Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = 0;
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            String optString = jSONObject.optString("native_image_orientation", "any");
            if (Ad.ORIENTATION_LANDSCAPE.equals(optString)) {
                i = 2;
            } else if (Ad.ORIENTATION_PORTRAIT.equals(optString)) {
                i = 1;
            } else if (!"any".equals(optString)) {
                i = -1;
            }
            builder.setImageOrientation(i);
        } catch (JSONException e) {
            zzakb.zzc("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }

    private final String zzmh() {
        try {
            return (TextUtils.isEmpty(this.zzbtk.zzbrv) || !this.zzwh.zzbn(this.zzbtk.zzbrv)) ? "com.google.ads.mediation.customevent.CustomEventAdapter" : "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
        } catch (RemoteException unused) {
            zzakb.zzdk("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    private final zzxw zzmi() {
        if (this.zzbtq != 0 || !zzmk()) {
            return null;
        }
        try {
            if (!(!zzy(4) || this.zzbtr == null || this.zzbtr.zzmm() == 0)) {
                return this.zzbtr;
            }
        } catch (RemoteException unused) {
            zzakb.zzdk("Could not get cpm value from MediationResponseMetadata");
        }
        return new zzxd(zzml());
    }

    /* access modifiers changed from: private */
    public final zzxq zzmj() {
        String valueOf = String.valueOf(this.zzbth);
        zzakb.zzdj(valueOf.length() != 0 ? "Instantiating mediation adapter: ".concat(valueOf) : new String("Instantiating mediation adapter: "));
        if (!this.zzael && !this.zzbtk.zzmg()) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbai)).booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbth)) {
                return zza((MediationAdapter) new AdMobAdapter());
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbaj)).booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzbth)) {
                return zza((MediationAdapter) new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzbth)) {
                return new zzyk(new zzzv());
            }
        }
        try {
            return this.zzwh.zzbm(this.zzbth);
        } catch (RemoteException e) {
            String valueOf2 = String.valueOf(this.zzbth);
            zzakb.zza(valueOf2.length() != 0 ? "Could not instantiate mediation adapter: ".concat(valueOf2) : new String("Could not instantiate mediation adapter: "), e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzmk() {
        return this.zzbtj.zzbsx != -1;
    }

    private final int zzml() {
        if (this.zzbtk.zzbsb == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zzbtk.zzbsb);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbth)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = zzy(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            return optInt == 0 ? jSONObject.optInt("penalized_average_cpm_cents", 0) : optInt;
        } catch (JSONException unused) {
            zzakb.zzdk("Could not convert to json. Returning 0");
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzy(int i) {
        try {
            Bundle zzmr = this.zzael ? this.zzbtp.zzmr() : this.zzaau.zzarc ? this.zzbtp.getInterstitialAdapterInfo() : this.zzbtp.zzmq();
            return zzmr != null && (zzmr.getInt("capabilities", 0) & i) == i;
        } catch (RemoteException unused) {
            zzakb.zzdk("Could not get adapter info. Returning false");
            return false;
        }
    }

    public final void cancel() {
        synchronized (this.mLock) {
            try {
                if (this.zzbtp != null) {
                    this.zzbtp.destroy();
                }
            } catch (RemoteException e) {
                zzakb.zzc("Could not destroy mediation adapter.", e);
            }
            this.zzbtq = -1;
            this.mLock.notify();
        }
    }

    public final zzxe zza(long j, long j2) {
        zzxe zzxe;
        synchronized (this.mLock) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            zzxa zzxa = new zzxa();
            zzakk.zzcrm.post(new zzxc(this, zzxa));
            long j3 = this.zzbti;
            while (this.zzbtq == -2) {
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                long j4 = j3 - (elapsedRealtime2 - elapsedRealtime);
                long j5 = j2 - (elapsedRealtime2 - j);
                if (j4 <= 0 || j5 <= 0) {
                    zzakb.zzdj("Timed out waiting for adapter.");
                    this.zzbtq = 3;
                } else {
                    try {
                        this.mLock.wait(Math.min(j4, j5));
                    } catch (InterruptedException unused) {
                        this.zzbtq = 5;
                    }
                }
            }
            zzxe = new zzxe(this.zzbtk, this.zzbtp, this.zzbth, zzxa, this.zzbtq, zzmi(), zzbv.zzer().elapsedRealtime() - elapsedRealtime);
        }
        return zzxe;
    }

    public final void zza(int i, zzxw zzxw) {
        synchronized (this.mLock) {
            this.zzbtq = 0;
            this.zzbtr = zzxw;
            this.mLock.notify();
        }
    }

    public final void zzx(int i) {
        synchronized (this.mLock) {
            this.zzbtq = i;
            this.mLock.notify();
        }
    }
}
