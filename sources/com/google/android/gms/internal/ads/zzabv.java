package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzabv implements Callable<zzajh> {
    private static long zzbzx = 10;
    private final Context mContext;
    private int mErrorCode;
    private final Object mLock = new Object();
    private final zzacm zzaad;
    private final zzci zzbjc;
    private final zzaji zzbze;
    private final zzalt zzbzy;
    /* access modifiers changed from: private */
    public final zzbc zzbzz;
    private boolean zzcaa;
    private List<String> zzcab;
    private JSONObject zzcac;
    private String zzcad;
    private String zzcae;
    private final zznx zzvr;

    public zzabv(Context context, zzbc zzbc, zzalt zzalt, zzci zzci, zzaji zzaji, zznx zznx) {
        this.mContext = context;
        this.zzbzz = zzbc;
        this.zzbzy = zzalt;
        this.zzbze = zzaji;
        this.zzbjc = zzci;
        this.zzvr = zznx;
        this.zzaad = zzbc.zzdr();
        this.zzcaa = false;
        this.mErrorCode = -2;
        this.zzcab = null;
        this.zzcad = null;
        this.zzcae = null;
    }

    private final zzajh zza(zzpb zzpb, boolean z) {
        int i;
        synchronized (this.mLock) {
            i = (zzpb == null && this.mErrorCode == -2) ? 0 : this.mErrorCode;
        }
        return new zzajh(this.zzbze.zzcgs.zzccv, (zzaqw) null, this.zzbze.zzcos.zzbsn, i, this.zzbze.zzcos.zzbso, this.zzcab, this.zzbze.zzcos.orientation, this.zzbze.zzcos.zzbsu, this.zzbze.zzcgs.zzccy, false, (zzwx) null, (zzxq) null, (String) null, (zzwy) null, (zzxa) null, 0, this.zzbze.zzacv, this.zzbze.zzcos.zzcep, this.zzbze.zzcoh, this.zzbze.zzcoi, this.zzbze.zzcos.zzcev, this.zzcac, i != -2 ? null : zzpb, (zzaig) null, (List<String>) null, (List<String>) null, this.zzbze.zzcos.zzcfh, this.zzbze.zzcos.zzcfi, (String) null, this.zzbze.zzcos.zzbsr, this.zzcad, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, z, this.zzbze.zzcos.zzbsp, this.zzbze.zzcos.zzzm, this.zzcae);
    }

    private final zzanz<zzon> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString("url") : jSONObject.optString("url");
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (!TextUtils.isEmpty(string)) {
            return z2 ? zzano.zzi(new zzon((Drawable) null, Uri.parse(string), optDouble)) : this.zzbzy.zza(string, new zzacb(this, z, optDouble, optBoolean, string));
        }
        zzd(0, z);
        return zzano.zzi(null);
    }

    private final void zzab(int i) {
        synchronized (this.mLock) {
            this.zzcaa = true;
            this.mErrorCode = i;
        }
    }

    private static zzaqw zzb(zzanz<zzaqw> zzanz) {
        try {
            return (zzaqw) zzanz.get((long) ((Integer) zzkb.zzik().zzd(zznk.zzbby)).intValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zzane.zzc("", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzane.zzc("", e2);
            return null;
        }
    }

    private static Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException unused) {
            return null;
        }
    }

    static zzaqw zzc(zzanz<zzaqw> zzanz) {
        try {
            return (zzaqw) zzanz.get((long) ((Integer) zzkb.zzik().zzd(zznk.zzbbx)).intValue(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            zzakb.zzc("InterruptedException occurred while waiting for video to load", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzakb.zzc("Exception occurred while waiting for video to load", e2);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final void zzc(zzqs zzqs, String str) {
        try {
            zzrc zzr = this.zzbzz.zzr(zzqs.getCustomTemplateId());
            if (zzr != null) {
                zzr.zzb(zzqs, str);
            }
        } catch (RemoteException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40);
            sb.append("Failed to call onCustomClick for asset ");
            sb.append(str);
            sb.append(".");
            zzakb.zzc(sb.toString(), e);
        }
    }

    /* access modifiers changed from: private */
    public static <V> List<V> zzk(List<zzanz<V>> list) throws ExecutionException, InterruptedException {
        ArrayList arrayList = new ArrayList();
        for (zzanz<V> zzanz : list) {
            Object obj = zzanz.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
        if (r4.length() != 0) goto L_0x0046;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a9 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0152 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0153 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01bb A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01f0  */
    /* renamed from: zznw */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzajh call() {
        /*
            r15 = this;
            java.lang.String r0 = "custom_template_id"
            r1 = 0
            r2 = 0
            com.google.android.gms.ads.internal.zzbc r3 = r15.zzbzz     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r12 = r3.getUuid()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            boolean r3 = r15.zznx()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r3 != 0) goto L_0x006e
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaji r4 = r15.zzbze     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaej r4 = r4.zzcos     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r4 = r4.zzceo     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r3.<init>(r4)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaji r5 = r15.zzbze     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaej r5 = r5.zzcos     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r5 = r5.zzceo     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r4.<init>(r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            int r5 = r4.length()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r6 = "ads"
            if (r5 == 0) goto L_0x0042
            org.json.JSONArray r4 = r4.optJSONArray(r6)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r4 == 0) goto L_0x0039
            org.json.JSONObject r4 = r4.optJSONObject(r1)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            goto L_0x003a
        L_0x0039:
            r4 = r2
        L_0x003a:
            if (r4 == 0) goto L_0x0042
            int r4 = r4.length()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r4 != 0) goto L_0x0046
        L_0x0042:
            r4 = 3
            r15.zzab(r4)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
        L_0x0046:
            com.google.android.gms.internal.ads.zzacm r4 = r15.zzaad     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzanz r3 = r4.zzh(r3)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            long r4 = zzbzx     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.Object r3 = r3.get(r4, r7)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r4 = "success"
            boolean r4 = r3.optBoolean(r4, r1)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r4 == 0) goto L_0x006e
            java.lang.String r4 = "json"
            org.json.JSONObject r3 = r3.getJSONObject(r4)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            org.json.JSONArray r3 = r3.optJSONArray(r6)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            org.json.JSONObject r3 = r3.getJSONObject(r1)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r9 = r3
            goto L_0x006f
        L_0x006e:
            r9 = r2
        L_0x006f:
            java.lang.String r3 = "enable_omid"
            boolean r3 = r9.optBoolean(r3)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r4 = "omid_settings"
            if (r3 != 0) goto L_0x007f
        L_0x0079:
            com.google.android.gms.internal.ads.zzany r5 = com.google.android.gms.internal.ads.zzano.zzi(r2)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r13 = r5
            goto L_0x00a3
        L_0x007f:
            org.json.JSONObject r5 = r9.optJSONObject(r4)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r5 != 0) goto L_0x0086
            goto L_0x0079
        L_0x0086:
            java.lang.String r6 = "omid_html"
            java.lang.String r5 = r5.optString(r6)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r6 == 0) goto L_0x0093
            goto L_0x0079
        L_0x0093:
            com.google.android.gms.internal.ads.zzaoj r6 = new com.google.android.gms.internal.ads.zzaoj     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r6.<init>()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.util.concurrent.Executor r7 = com.google.android.gms.internal.ads.zzaoe.zzcvy     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzabw r8 = new com.google.android.gms.internal.ads.zzabw     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r8.<init>(r15, r6, r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r7.execute(r8)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r13 = r6
        L_0x00a3:
            boolean r5 = r15.zznx()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r5 != 0) goto L_0x0147
            if (r9 != 0) goto L_0x00ad
            goto L_0x0147
        L_0x00ad:
            java.lang.String r5 = "template_id"
            java.lang.String r5 = r9.getString(r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaji r6 = r15.zzbze     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaef r6 = r6.zzcgs     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzpl r6 = r6.zzadj     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r6 == 0) goto L_0x00c4
            com.google.android.gms.internal.ads.zzaji r6 = r15.zzbze     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaef r6 = r6.zzcgs     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzpl r6 = r6.zzadj     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            boolean r6 = r6.zzbjn     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            goto L_0x00c5
        L_0x00c4:
            r6 = 0
        L_0x00c5:
            com.google.android.gms.internal.ads.zzaji r7 = r15.zzbze     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaef r7 = r7.zzcgs     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzpl r7 = r7.zzadj     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r7 == 0) goto L_0x00d6
            com.google.android.gms.internal.ads.zzaji r7 = r15.zzbze     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaef r7 = r7.zzcgs     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzpl r7 = r7.zzadj     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            boolean r7 = r7.zzbjp     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            goto L_0x00d7
        L_0x00d6:
            r7 = 0
        L_0x00d7:
            java.lang.String r8 = "2"
            boolean r8 = r8.equals(r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r8 == 0) goto L_0x00e9
            com.google.android.gms.internal.ads.zzacn r0 = new com.google.android.gms.internal.ads.zzacn     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaji r5 = r15.zzbze     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            boolean r5 = r5.zzcor     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r0.<init>(r6, r7, r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            goto L_0x0148
        L_0x00e9:
            java.lang.String r8 = "1"
            boolean r8 = r8.equals(r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r8 == 0) goto L_0x00fb
            com.google.android.gms.internal.ads.zzaco r0 = new com.google.android.gms.internal.ads.zzaco     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaji r5 = r15.zzbze     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            boolean r5 = r5.zzcor     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r0.<init>(r6, r7, r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            goto L_0x0148
        L_0x00fb:
            java.lang.String r7 = "3"
            boolean r5 = r7.equals(r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r5 == 0) goto L_0x0144
            java.lang.String r5 = r9.getString(r0)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaoj r7 = new com.google.android.gms.internal.ads.zzaoj     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r7.<init>()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            android.os.Handler r8 = com.google.android.gms.internal.ads.zzakk.zzcrm     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaby r10 = new com.google.android.gms.internal.ads.zzaby     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r10.<init>(r15, r7, r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r8.post(r10)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            long r10 = zzbzx     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.Object r5 = r7.get(r10, r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r5 == 0) goto L_0x0126
            com.google.android.gms.internal.ads.zzacp r0 = new com.google.android.gms.internal.ads.zzacp     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r0.<init>(r6)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            goto L_0x0148
        L_0x0126:
            java.lang.String r5 = "No handler for custom template: "
            java.lang.String r0 = r9.getString(r0)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            int r6 = r0.length()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r6 == 0) goto L_0x013b
            java.lang.String r0 = r5.concat(r0)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            goto L_0x0140
        L_0x013b:
            java.lang.String r0 = new java.lang.String     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r0.<init>(r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
        L_0x0140:
            com.google.android.gms.internal.ads.zzakb.e(r0)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            goto L_0x0147
        L_0x0144:
            r15.zzab(r1)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
        L_0x0147:
            r0 = r2
        L_0x0148:
            boolean r5 = r15.zznx()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r5 != 0) goto L_0x01b6
            if (r0 == 0) goto L_0x01b6
            if (r9 != 0) goto L_0x0153
            goto L_0x01b6
        L_0x0153:
            java.lang.String r5 = "tracking_urls_and_actions"
            org.json.JSONObject r5 = r9.getJSONObject(r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r6 = "impression_tracking_urls"
            org.json.JSONArray r6 = r5.optJSONArray(r6)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r6 != 0) goto L_0x0163
            r7 = r2
            goto L_0x0179
        L_0x0163:
            int r7 = r6.length()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r8 = 0
        L_0x016a:
            int r10 = r6.length()     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r8 >= r10) goto L_0x0179
            java.lang.String r10 = r6.getString(r8)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r7[r8] = r10     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            int r8 = r8 + 1
            goto L_0x016a
        L_0x0179:
            if (r7 != 0) goto L_0x017d
            r6 = r2
            goto L_0x0181
        L_0x017d:
            java.util.List r6 = java.util.Arrays.asList(r7)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
        L_0x0181:
            r15.zzcab = r6     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r6 = "active_view"
            org.json.JSONObject r5 = r5.optJSONObject(r6)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r15.zzcac = r5     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r5 = "debug_signals"
            java.lang.String r5 = r9.optString(r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r15.zzcad = r5     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r4 = r9.optString(r4)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r15.zzcae = r4     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzpb r0 = r0.zza(r15, r9)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzpd r14 = new com.google.android.gms.internal.ads.zzpd     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            android.content.Context r5 = r15.mContext     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.ads.internal.zzbc r6 = r15.zzbzz     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzacm r7 = r15.zzaad     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzci r8 = r15.zzbjc     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaji r4 = r15.zzbze     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaef r4 = r4.zzcgs     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzang r11 = r4.zzacr     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r4 = r14
            r10 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r0.zzb(r14)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            goto L_0x01b7
        L_0x01b6:
            r0 = r2
        L_0x01b7:
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzos     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            if (r4 == 0) goto L_0x01ca
            r4 = r0
            com.google.android.gms.internal.ads.zzos r4 = (com.google.android.gms.internal.ads.zzos) r4     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzabz r5 = new com.google.android.gms.internal.ads.zzabz     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r5.<init>(r15, r4)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzacm r4 = r15.zzaad     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            java.lang.String r6 = "/nativeAdCustomClick"
            r4.zza((java.lang.String) r6, r5)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
        L_0x01ca:
            com.google.android.gms.internal.ads.zzajh r0 = r15.zza((com.google.android.gms.internal.ads.zzpb) r0, (boolean) r3)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.ads.internal.zzbc r3 = r15.zzbzz     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            com.google.android.gms.internal.ads.zzaqw r4 = zzb(r13)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            r3.zzg(r4)     // Catch:{ CancellationException -> 0x01e6, ExecutionException -> 0x01e4, InterruptedException -> 0x01e2, JSONException -> 0x01e0, TimeoutException -> 0x01dc, Exception -> 0x01d8 }
            return r0
        L_0x01d8:
            r0 = move-exception
            java.lang.String r3 = "Error occured while doing native ads initialization."
            goto L_0x01e9
        L_0x01dc:
            r0 = move-exception
            java.lang.String r3 = "Timeout when loading native ad."
            goto L_0x01e9
        L_0x01e0:
            r0 = move-exception
            goto L_0x01e7
        L_0x01e2:
            r0 = move-exception
            goto L_0x01e7
        L_0x01e4:
            r0 = move-exception
            goto L_0x01e7
        L_0x01e6:
            r0 = move-exception
        L_0x01e7:
            java.lang.String r3 = "Malformed native JSON response."
        L_0x01e9:
            com.google.android.gms.internal.ads.zzakb.zzc(r3, r0)
            boolean r0 = r15.zzcaa
            if (r0 != 0) goto L_0x01f3
            r15.zzab(r1)
        L_0x01f3:
            com.google.android.gms.internal.ads.zzajh r0 = r15.zza((com.google.android.gms.internal.ads.zzpb) r2, (boolean) r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabv.call():com.google.android.gms.internal.ads.zzajh");
    }

    private final boolean zznx() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcaa;
        }
        return z;
    }

    public final zzanz<zzon> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public final List<zzanz<zzon>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            zzd(0, false);
            return arrayList;
        }
        int length = z3 ? optJSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, false, z2));
        }
        return arrayList;
    }

    public final Future<zzon> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzaoj zzaoj, String str) {
        try {
            zzbv.zzel();
            zzaqw zza = zzarc.zza(this.mContext, zzasi.zzvq(), "native-omid", false, false, this.zzbjc, this.zzbze.zzcgs.zzacr, this.zzvr, (zzbo) null, this.zzbzz.zzbi(), this.zzbze.zzcoq);
            zza.zzuf().zza((zzasd) new zzabx(zzaoj, zza));
            zza.loadData(str, "text/html", "UTF-8");
        } catch (Exception e) {
            zzaoj.set(null);
            zzane.zzc("", e);
        }
    }

    public final zzanz<zzaqw> zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return zzano.zzi(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            zzakb.zzdk("Required field 'vast_xml' is missing");
            return zzano.zzi(null);
        }
        zzace zzace = new zzace(this.mContext, this.zzbjc, this.zzbze, this.zzvr, this.zzbzz);
        zzaoj zzaoj = new zzaoj();
        zzaoe.zzcvy.execute(new zzacf(zzace, optJSONObject, zzaoj));
        return zzaoj;
    }

    public final void zzd(int i, boolean z) {
        if (z) {
            zzab(i);
        }
    }

    public final zzanz<zzoj> zzg(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return zzano.zzi(null);
        }
        String optString = optJSONObject.optString("text");
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        int i = (this.zzbze.zzcgs.zzadj == null || this.zzbze.zzcgs.zzadj.versionCode < 2) ? 1 : this.zzbze.zzcgs.zzadj.zzbjq;
        boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List<zzanz> arrayList = new ArrayList<>();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, "image", false, false));
        }
        zzaoj zzaoj = new zzaoj();
        int size = arrayList.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzanz zza : arrayList) {
            zza.zza(new zzacc(atomicInteger, size, zzaoj, arrayList), zzaki.zzcrj);
            arrayList = arrayList;
        }
        return zzano.zza(zzaoj, new zzaca(this, optString, zzb2, zzb, optInt, optInt3, optInt2, i, optBoolean), (Executor) zzaki.zzcrj);
    }
}
