package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzadk;
import com.google.android.gms.internal.ads.zzaeg;
import com.google.android.gms.internal.ads.zzafa;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzajj;
import com.google.android.gms.internal.ads.zzajl;
import com.google.android.gms.internal.ads.zzajx;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzano;
import com.google.android.gms.internal.ads.zzanz;
import com.google.android.gms.internal.ads.zzaoe;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzhu;
import com.google.android.gms.internal.ads.zzhx;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzua;
import com.google.android.gms.internal.ads.zzwz;
import com.google.android.gms.internal.ads.zzxg;
import com.google.android.gms.internal.ads.zzxn;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.mopub.network.ImpressionData;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zzd extends zza implements zzn, zzbo, zzwz {
    protected final zzxn zzwh;
    private transient boolean zzwi;

    public zzd(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, zzw zzw) {
        this(new zzbw(context, zzjn, str, zzang), zzxn, (zzbl) null, zzw);
    }

    private zzd(zzbw zzbw, zzxn zzxn, zzbl zzbl, zzw zzw) {
        super(zzbw, (zzbl) null, zzw);
        this.zzwh = zzxn;
        this.zzwi = false;
    }

    private final zzaeg zza(zzjj zzjj, Bundle bundle, zzajl zzajl, int i) {
        PackageInfo packageInfo;
        Bundle bundle2;
        String str;
        JSONArray optJSONArray;
        ApplicationInfo applicationInfo = this.zzvw.zzrt.getApplicationInfo();
        int i2 = 0;
        try {
            packageInfo = Wrappers.packageManager(this.zzvw.zzrt).getPackageInfo(applicationInfo.packageName, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        DisplayMetrics displayMetrics = this.zzvw.zzrt.getResources().getDisplayMetrics();
        if (this.zzvw.zzacs == null || this.zzvw.zzacs.getParent() == null) {
            bundle2 = null;
        } else {
            int[] iArr = new int[2];
            this.zzvw.zzacs.getLocationOnScreen(iArr);
            int i3 = iArr[0];
            int i4 = 1;
            int i5 = iArr[1];
            int width = this.zzvw.zzacs.getWidth();
            int height = this.zzvw.zzacs.getHeight();
            if (!this.zzvw.zzacs.isShown() || i3 + width <= 0 || i5 + height <= 0 || i3 > displayMetrics.widthPixels || i5 > displayMetrics.heightPixels) {
                i4 = 0;
            }
            Bundle bundle3 = new Bundle(5);
            bundle3.putInt(AvidJSONUtil.KEY_X, i3);
            bundle3.putInt(AvidJSONUtil.KEY_Y, i5);
            bundle3.putInt("width", width);
            bundle3.putInt("height", height);
            bundle3.putInt("visible", i4);
            bundle2 = bundle3;
        }
        String zzql = zzbv.zzeo().zzpx().zzql();
        this.zzvw.zzacy = new zzajj(zzql, this.zzvw.zzacp);
        this.zzvw.zzacy.zzn(zzjj);
        zzbv.zzek();
        String zza = zzakk.zza(this.zzvw.zzrt, (View) this.zzvw.zzacs, this.zzvw.zzacv);
        long j = 0;
        if (this.zzvw.zzadd != null) {
            try {
                j = this.zzvw.zzadd.getValue();
            } catch (RemoteException unused2) {
                zzakb.zzdk("Cannot get correlation id, default to 0.");
            }
        }
        long j2 = j;
        String uuid = UUID.randomUUID().toString();
        Bundle zza2 = zzbv.zzep().zza(this.zzvw.zzrt, this, zzql);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i6 = 0; i6 < this.zzvw.zzadi.size(); i6++) {
            String keyAt = this.zzvw.zzadi.keyAt(i6);
            arrayList.add(keyAt);
            if (this.zzvw.zzadh.containsKey(keyAt) && this.zzvw.zzadh.get(keyAt) != null) {
                arrayList2.add(keyAt);
            }
        }
        zzanz zza3 = zzaki.zza(new zzg(this));
        zzanz zza4 = zzaki.zza(new zzh(this));
        String zzpu = zzajl != null ? zzajl.zzpu() : null;
        if (this.zzvw.zzads != null && this.zzvw.zzads.size() > 0) {
            if (packageInfo != null) {
                i2 = packageInfo.versionCode;
            }
            if (i2 > zzbv.zzeo().zzqh().zzqz()) {
                zzbv.zzeo().zzqh().zzrf();
                zzbv.zzeo().zzqh().zzae(i2);
            } else {
                JSONObject zzre = zzbv.zzeo().zzqh().zzre();
                if (!(zzre == null || (optJSONArray = zzre.optJSONArray(this.zzvw.zzacp)) == null)) {
                    str = optJSONArray.toString();
                    zzjn zzjn = this.zzvw.zzacv;
                    String str2 = this.zzvw.zzacp;
                    String zzih = zzkb.zzih();
                    ArrayList arrayList3 = arrayList2;
                    zzang zzang = this.zzvw.zzacr;
                    ArrayList arrayList4 = arrayList;
                    List<String> list = this.zzvw.zzads;
                    boolean zzqt = zzbv.zzeo().zzqh().zzqt();
                    int i7 = displayMetrics.widthPixels;
                    int i8 = displayMetrics.heightPixels;
                    float f = displayMetrics.density;
                    List<String> zzjb = zznk.zzjb();
                    String str3 = this.zzvw.zzaco;
                    zzpl zzpl = this.zzvw.zzadj;
                    String zzfq = this.zzvw.zzfq();
                    float zzdo = zzbv.zzfj().zzdo();
                    boolean zzdp = zzbv.zzfj().zzdp();
                    zzbv.zzek();
                    int zzas = zzakk.zzas(this.zzvw.zzrt);
                    zzbv.zzek();
                    int zzx = zzakk.zzx(this.zzvw.zzacs);
                    boolean z = this.zzvw.zzrt instanceof Activity;
                    boolean zzqy = zzbv.zzeo().zzqh().zzqy();
                    boolean zzqa = zzbv.zzeo().zzqa();
                    int zztx = zzbv.zzff().zztx();
                    zzbv.zzek();
                    Bundle zzrk = zzakk.zzrk();
                    String zzrw = zzbv.zzeu().zzrw();
                    zzlu zzlu = this.zzvw.zzadl;
                    boolean zzrx = zzbv.zzeu().zzrx();
                    Bundle zzlt = zzua.zzlk().zzlt();
                    boolean zzcr = zzbv.zzeo().zzqh().zzcr(this.zzvw.zzacp);
                    List<Integer> list2 = this.zzvw.zzadn;
                    boolean isCallerInstantApp = Wrappers.packageManager(this.zzvw.zzrt).isCallerInstantApp();
                    boolean zzqb = zzbv.zzeo().zzqb();
                    zzbv.zzem();
                    zzaeg zzaeg = r2;
                    zzaeg zzaeg2 = new zzaeg(bundle2, zzjj, zzjn, str2, applicationInfo, packageInfo, zzql, zzih, zzang, zza2, list, arrayList4, bundle, zzqt, i7, i8, f, zza, j2, uuid, zzjb, str3, zzpl, zzfq, zzdo, zzdp, zzas, zzx, z, zzqy, zza3, zzpu, zzqa, zztx, zzrk, zzrw, zzlu, zzrx, zzlt, zzcr, zza4, list2, str, arrayList3, i, isCallerInstantApp, zzqb, zzakq.zzrp(), (ArrayList) zzano.zza(zzbv.zzeo().zzqi(), null, 1000, TimeUnit.MILLISECONDS));
                    return zzaeg;
                }
            }
        }
        str = null;
        zzjn zzjn2 = this.zzvw.zzacv;
        String str22 = this.zzvw.zzacp;
        String zzih2 = zzkb.zzih();
        ArrayList arrayList32 = arrayList2;
        zzang zzang2 = this.zzvw.zzacr;
        ArrayList arrayList42 = arrayList;
        List<String> list3 = this.zzvw.zzads;
        boolean zzqt2 = zzbv.zzeo().zzqh().zzqt();
        int i72 = displayMetrics.widthPixels;
        int i82 = displayMetrics.heightPixels;
        float f2 = displayMetrics.density;
        List<String> zzjb2 = zznk.zzjb();
        String str32 = this.zzvw.zzaco;
        zzpl zzpl2 = this.zzvw.zzadj;
        String zzfq2 = this.zzvw.zzfq();
        float zzdo2 = zzbv.zzfj().zzdo();
        boolean zzdp2 = zzbv.zzfj().zzdp();
        zzbv.zzek();
        int zzas2 = zzakk.zzas(this.zzvw.zzrt);
        zzbv.zzek();
        int zzx2 = zzakk.zzx(this.zzvw.zzacs);
        boolean z2 = this.zzvw.zzrt instanceof Activity;
        boolean zzqy2 = zzbv.zzeo().zzqh().zzqy();
        boolean zzqa2 = zzbv.zzeo().zzqa();
        int zztx2 = zzbv.zzff().zztx();
        zzbv.zzek();
        Bundle zzrk2 = zzakk.zzrk();
        String zzrw2 = zzbv.zzeu().zzrw();
        zzlu zzlu2 = this.zzvw.zzadl;
        boolean zzrx2 = zzbv.zzeu().zzrx();
        Bundle zzlt2 = zzua.zzlk().zzlt();
        boolean zzcr2 = zzbv.zzeo().zzqh().zzcr(this.zzvw.zzacp);
        List<Integer> list22 = this.zzvw.zzadn;
        boolean isCallerInstantApp2 = Wrappers.packageManager(this.zzvw.zzrt).isCallerInstantApp();
        boolean zzqb2 = zzbv.zzeo().zzqb();
        zzbv.zzem();
        zzaeg zzaeg3 = zzaeg2;
        zzaeg zzaeg22 = new zzaeg(bundle2, zzjj, zzjn2, str22, applicationInfo, packageInfo, zzql, zzih2, zzang2, zza2, list3, arrayList42, bundle, zzqt2, i72, i82, f2, zza, j2, uuid, zzjb2, str32, zzpl2, zzfq2, zzdo2, zzdp2, zzas2, zzx2, z2, zzqy2, zza3, zzpu, zzqa2, zztx2, zzrk2, zzrw2, zzlu2, zzrx2, zzlt2, zzcr2, zza4, list22, str, arrayList32, i, isCallerInstantApp2, zzqb2, zzakq.zzrp(), (ArrayList) zzano.zza(zzbv.zzeo().zzqi(), null, 1000, TimeUnit.MILLISECONDS));
        return zzaeg3;
    }

    static String zzc(zzajh zzajh) {
        if (zzajh == null) {
            return null;
        }
        String str = zzajh.zzbty;
        if (("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) && zzajh.zzbtw != null) {
            try {
                return new JSONObject(zzajh.zzbtw.zzbsb).getString("class_name");
            } catch (NullPointerException | JSONException unused) {
            }
        }
        return str;
    }

    public final String getMediationAdapterClassName() {
        if (this.zzvw.zzacw == null) {
            return null;
        }
        return this.zzvw.zzacw.zzbty;
    }

    public void onAdClicked() {
        if (this.zzvw.zzacw == null) {
            zzakb.zzdk("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzvw.zzacw.zzcod == null || this.zzvw.zzacw.zzcod.zzbsn == null)) {
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw, this.zzvw.zzacp, false, zzc(this.zzvw.zzacw.zzcod.zzbsn));
        }
        if (!(this.zzvw.zzacw.zzbtw == null || this.zzvw.zzacw.zzbtw.zzbrw == null)) {
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw, this.zzvw.zzacp, false, this.zzvw.zzacw.zzbtw.zzbrw);
        }
        super.onAdClicked();
    }

    public final void onPause() {
        this.zzvy.zzj(this.zzvw.zzacw);
    }

    public final void onResume() {
        this.zzvy.zzk(this.zzvw.zzacw);
    }

    public void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null || !this.zzvw.zzfo())) {
            zzbv.zzem();
            zzakq.zzi(this.zzvw.zzacw.zzbyo);
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbtx == null)) {
            try {
                this.zzvw.zzacw.zzbtx.pause();
            } catch (RemoteException unused) {
                zzakb.zzdk("Could not pause mediation adapter.");
            }
        }
        this.zzvy.zzj(this.zzvw.zzacw);
        this.zzvv.pause();
    }

    public final void recordImpression() {
        zza(this.zzvw.zzacw, false);
    }

    public void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        zzaqw zzaqw = (this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null) ? null : this.zzvw.zzacw.zzbyo;
        if (zzaqw != null && this.zzvw.zzfo()) {
            zzbv.zzem();
            zzakq.zzj(this.zzvw.zzacw.zzbyo);
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbtx == null)) {
            try {
                this.zzvw.zzacw.zzbtx.resume();
            } catch (RemoteException unused) {
                zzakb.zzdk("Could not resume mediation adapter.");
            }
        }
        if (zzaqw == null || !zzaqw.zzum()) {
            this.zzvv.resume();
        }
        this.zzvy.zzk(this.zzvw.zzacw);
    }

    public void showInterstitial() {
        zzakb.zzdk("showInterstitial is not supported for current ad type");
    }

    /* access modifiers changed from: protected */
    public void zza(zzajh zzajh, boolean z) {
        if (zzajh == null) {
            zzakb.zzdk("Ad state was null when trying to ping impression URLs.");
            return;
        }
        if (zzajh == null) {
            zzakb.zzdk("Ad state was null when trying to ping impression URLs.");
        } else {
            zzakb.zzck("Pinging Impression URLs.");
            if (this.zzvw.zzacy != null) {
                this.zzvw.zzacy.zzpm();
            }
            zzajh.zzcoq.zza(zzhu.zza.zzb.AD_IMPRESSION);
            if (zzajh.zzbso != null && !zzajh.zzcok) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzc(zzajh.zzbso));
                zzajh.zzcok = true;
            }
        }
        if (!zzajh.zzcom || z) {
            if (!(zzajh.zzcod == null || zzajh.zzcod.zzbso == null)) {
                zzbv.zzfd();
                zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, z, zzc(zzajh.zzcod.zzbso));
            }
            if (!(zzajh.zzbtw == null || zzajh.zzbtw.zzbrx == null)) {
                zzbv.zzfd();
                zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, z, zzajh.zzbtw.zzbrx);
            }
            zzajh.zzcom = true;
        }
    }

    public final void zza(zzqs zzqs, String str) {
        String str2;
        zzrc zzrc = null;
        if (zzqs != null) {
            try {
                str2 = zzqs.getCustomTemplateId();
            } catch (RemoteException e) {
                zzakb.zzc("Unable to call onCustomClick.", e);
                return;
            }
        } else {
            str2 = null;
        }
        if (!(this.zzvw.zzadh == null || str2 == null)) {
            zzrc = this.zzvw.zzadh.get(str2);
        }
        if (zzrc == null) {
            zzakb.zzdk("Mediation adapter invoked onCustomClick but no listeners were set.");
        } else {
            zzrc.zzb(zzqs, str);
        }
    }

    public final boolean zza(zzaeg zzaeg, zznx zznx) {
        this.zzvr = zznx;
        zznx.zze("seq_num", zzaeg.zzccy);
        zznx.zze("request_id", zzaeg.zzcdi);
        zznx.zze("session_id", zzaeg.zzccz);
        if (zzaeg.zzccw != null) {
            zznx.zze(ImpressionData.APP_VERSION, String.valueOf(zzaeg.zzccw.versionCode));
        }
        zzbw zzbw = this.zzvw;
        zzbv.zzeg();
        Context context = this.zzvw.zzrt;
        zzhx zzhx = this.zzwc.zzxb;
        zzajx zzafa = zzaeg.zzccv.extras.getBundle("sdk_less_server_data") != null ? new zzafa(context, zzaeg, this, zzhx) : new zzadk(context, zzaeg, this, zzhx);
        zzafa.zzqo();
        zzbw.zzact = zzafa;
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzajh zzajh) {
        zzjj zzjj;
        boolean z = false;
        if (this.zzvx != null) {
            zzjj = this.zzvx;
            this.zzvx = null;
        } else {
            zzjj = zzajh.zzccv;
            if (zzjj.extras != null) {
                z = zzjj.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(zzjj, zzajh, z);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzajh zzajh, zzajh zzajh2) {
        int i;
        if (!(zzajh == null || zzajh.zzbtz == null)) {
            zzajh.zzbtz.zza((zzwz) null);
        }
        if (zzajh2.zzbtz != null) {
            zzajh2.zzbtz.zza((zzwz) this);
        }
        int i2 = 0;
        if (zzajh2.zzcod != null) {
            i2 = zzajh2.zzcod.zzbtc;
            i = zzajh2.zzcod.zzbtd;
        } else {
            i = 0;
        }
        this.zzvw.zzadt.zze(i2, i);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzjj zzjj, zzajh zzajh, boolean z) {
        zzbl zzbl;
        long j;
        if (!z && this.zzvw.zzfo()) {
            if (zzajh.zzbsu > 0) {
                zzbl = this.zzvv;
                j = zzajh.zzbsu;
            } else if (zzajh.zzcod != null && zzajh.zzcod.zzbsu > 0) {
                zzbl = this.zzvv;
                j = zzajh.zzcod.zzbsu;
            } else if (!zzajh.zzceq && zzajh.errorCode == 2) {
                this.zzvv.zzg(zzjj);
            }
            zzbl.zza(zzjj, j);
        }
        return this.zzvv.zzdz();
    }

    public boolean zza(zzjj zzjj, zznx zznx) {
        return zza(zzjj, zznx, 1);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.google.android.gms.internal.ads.zzajl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: com.google.android.gms.internal.ads.zzajl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: com.google.android.gms.internal.ads.zzajl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.google.android.gms.internal.ads.zzajl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.google.android.gms.internal.ads.zzajl} */
    /* JADX WARNING: type inference failed for: r2v5, types: [java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(com.google.android.gms.internal.ads.zzjj r12, com.google.android.gms.internal.ads.zznx r13, int r14) {
        /*
            r11 = this;
            boolean r0 = r11.zzca()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            com.google.android.gms.ads.internal.zzbv.zzek()
            com.google.android.gms.ads.internal.zzbw r0 = r11.zzvw
            android.content.Context r0 = r0.zzrt
            com.google.android.gms.internal.ads.zzajm r2 = com.google.android.gms.ads.internal.zzbv.zzeo()
            com.google.android.gms.internal.ads.zzgk r0 = r2.zzaf(r0)
            r2 = 0
            if (r0 != 0) goto L_0x001c
            r0 = r2
            goto L_0x0020
        L_0x001c:
            android.os.Bundle r0 = com.google.android.gms.internal.ads.zzakk.zza((com.google.android.gms.internal.ads.zzgk) r0)
        L_0x0020:
            com.google.android.gms.ads.internal.zzbl r3 = r11.zzvv
            r3.cancel()
            com.google.android.gms.ads.internal.zzbw r3 = r11.zzvw
            r3.zzadv = r1
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zznk.zzbcs
            com.google.android.gms.internal.ads.zzni r3 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r1 = r3.zzd(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0065
            com.google.android.gms.internal.ads.zzajm r1 = com.google.android.gms.ads.internal.zzbv.zzeo()
            com.google.android.gms.internal.ads.zzakd r1 = r1.zzqh()
            com.google.android.gms.internal.ads.zzajl r1 = r1.zzra()
            com.google.android.gms.ads.internal.zzad r3 = com.google.android.gms.ads.internal.zzbv.zzes()
            com.google.android.gms.ads.internal.zzbw r4 = r11.zzvw
            android.content.Context r4 = r4.zzrt
            com.google.android.gms.ads.internal.zzbw r5 = r11.zzvw
            com.google.android.gms.internal.ads.zzang r5 = r5.zzacr
            com.google.android.gms.ads.internal.zzbw r6 = r11.zzvw
            java.lang.String r9 = r6.zzacp
            if (r1 == 0) goto L_0x005d
            java.lang.String r2 = r1.zzpv()
        L_0x005d:
            r8 = r2
            r6 = 0
            r10 = 0
            r7 = r1
            r3.zza(r4, r5, r6, r7, r8, r9, r10)
            r2 = r1
        L_0x0065:
            com.google.android.gms.internal.ads.zzaeg r12 = r11.zza(r12, r0, r2, r14)
            boolean r12 = r11.zza((com.google.android.gms.internal.ads.zzaeg) r12, (com.google.android.gms.internal.ads.zznx) r13)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzd.zza(com.google.android.gms.internal.ads.zzjj, com.google.android.gms.internal.ads.zznx, int):boolean");
    }

    public final void zzb(zzajh zzajh) {
        super.zzb(zzajh);
        if (zzajh.zzbtw != null) {
            zzakb.zzck("Disable the debug gesture detector on the mediation ad frame.");
            if (this.zzvw.zzacs != null) {
                this.zzvw.zzacs.zzfu();
            }
            zzakb.zzck("Pinging network fill URLs.");
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, false, zzajh.zzbtw.zzbsa);
            if (!(zzajh.zzcod == null || zzajh.zzcod.zzbsr == null || zzajh.zzcod.zzbsr.size() <= 0)) {
                zzakb.zzck("Pinging urls remotely");
                zzbv.zzek().zza(this.zzvw.zzrt, zzajh.zzcod.zzbsr);
            }
        } else {
            zzakb.zzck("Enable the debug gesture detector on the admob ad frame.");
            if (this.zzvw.zzacs != null) {
                this.zzvw.zzacs.zzft();
            }
        }
        if (zzajh.errorCode == 3 && zzajh.zzcod != null && zzajh.zzcod.zzbsq != null) {
            zzakb.zzck("Pinging no fill URLs.");
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, false, zzajh.zzcod.zzbsq);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzajh zzajh, boolean z) {
        if (zzajh != null) {
            if (!(zzajh == null || zzajh.zzbsp == null || zzajh.zzcol)) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzb(zzajh.zzbsp));
                zzajh.zzcol = true;
            }
            if (!zzajh.zzcon || z) {
                if (!(zzajh.zzcod == null || zzajh.zzcod.zzbsp == null)) {
                    zzbv.zzfd();
                    zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, z, zzb(zzajh.zzcod.zzbsp));
                }
                if (!(zzajh.zzbtw == null || zzajh.zzbtw.zzbry == null)) {
                    zzbv.zzfd();
                    zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, z, zzajh.zzbtw.zzbry);
                }
                zzajh.zzcon = true;
            }
        }
    }

    public final void zzb(String str, String str2) {
        onAppEvent(str, str2);
    }

    /* access modifiers changed from: protected */
    public final boolean zzc(zzjj zzjj) {
        return super.zzc(zzjj) && !this.zzwi;
    }

    /* access modifiers changed from: protected */
    public boolean zzca() {
        zzbv.zzek();
        if (zzakk.zzl(this.zzvw.zzrt, "android.permission.INTERNET")) {
            zzbv.zzek();
            return zzakk.zzaj(this.zzvw.zzrt);
        }
    }

    public void zzcb() {
        this.zzwi = false;
        zzbn();
        this.zzvw.zzacy.zzpo();
    }

    public void zzcc() {
        this.zzwi = true;
        zzbp();
    }

    public void zzcd() {
        zzakb.zzdk("Mediated ad does not support onVideoEnd callback");
    }

    public void zzce() {
        onAdClicked();
    }

    public final void zzcf() {
        zzcb();
    }

    public final void zzcg() {
        zzbo();
    }

    public final void zzch() {
        zzcc();
    }

    public final void zzci() {
        if (this.zzvw.zzacw != null) {
            String str = this.zzvw.zzacw.zzbty;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 74);
            sb.append("Mediation adapter ");
            sb.append(str);
            sb.append(" refreshed, but mediation adapters should never refresh.");
            zzakb.zzdk(sb.toString());
        }
        zza(this.zzvw.zzacw, true);
        zzb(this.zzvw.zzacw, true);
        zzbq();
    }

    public void zzcj() {
        recordImpression();
    }

    public final String zzck() {
        if (this.zzvw.zzacw == null) {
            return null;
        }
        return zzc(this.zzvw.zzacw);
    }

    public final void zzcl() {
        Executor executor = zzaoe.zzcvy;
        zzbl zzbl = this.zzvv;
        zzbl.getClass();
        executor.execute(zze.zza(zzbl));
    }

    public final void zzcm() {
        Executor executor = zzaoe.zzcvy;
        zzbl zzbl = this.zzvv;
        zzbl.getClass();
        executor.execute(zzf.zza(zzbl));
    }
}
