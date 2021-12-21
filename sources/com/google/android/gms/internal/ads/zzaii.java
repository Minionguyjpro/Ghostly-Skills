package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzaii implements zzait {
    /* access modifiers changed from: private */
    public static List<Future<Void>> zzcml = Collections.synchronizedList(new ArrayList());
    private static ScheduledExecutorService zzcmm = Executors.newSingleThreadScheduledExecutor();
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final zzaiq zzciy;
    /* access modifiers changed from: private */
    public final zzbfm zzcmn;
    private final LinkedHashMap<String, zzbfu> zzcmo;
    private final List<String> zzcmp = new ArrayList();
    private final List<String> zzcmq = new ArrayList();
    private final zzaiv zzcmr;
    private boolean zzcms;
    private final zzaiw zzcmt;
    private HashSet<String> zzcmu = new HashSet<>();
    private boolean zzcmv = false;
    private boolean zzcmw = false;
    private boolean zzcmx = false;

    public zzaii(Context context, zzang zzang, zzaiq zzaiq, String str, zzaiv zzaiv) {
        Preconditions.checkNotNull(zzaiq, "SafeBrowsing config is not present.");
        this.mContext = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzcmo = new LinkedHashMap<>();
        this.zzcmr = zzaiv;
        this.zzciy = zzaiq;
        for (String lowerCase : zzaiq.zzcnh) {
            this.zzcmu.add(lowerCase.toLowerCase(Locale.ENGLISH));
        }
        this.zzcmu.remove("cookie".toLowerCase(Locale.ENGLISH));
        zzbfm zzbfm = new zzbfm();
        zzbfm.zzamf = 8;
        zzbfm.url = str;
        zzbfm.zzech = str;
        zzbfm.zzecj = new zzbfn();
        zzbfm.zzecj.zzcnd = this.zzciy.zzcnd;
        zzbfv zzbfv = new zzbfv();
        zzbfv.zzedv = zzang.zzcw;
        zzbfv.zzedx = Boolean.valueOf(Wrappers.packageManager(this.mContext).isCallerInstantApp());
        long apkVersion = (long) GoogleApiAvailabilityLight.getInstance().getApkVersion(this.mContext);
        if (apkVersion > 0) {
            zzbfv.zzedw = Long.valueOf(apkVersion);
        }
        zzbfm.zzect = zzbfv;
        this.zzcmn = zzbfm;
        this.zzcmt = new zzaiw(this.mContext, this.zzciy.zzcnk, this);
    }

    private final zzbfu zzci(String str) {
        zzbfu zzbfu;
        synchronized (this.mLock) {
            zzbfu = this.zzcmo.get(str);
        }
        return zzbfu;
    }

    static final /* synthetic */ Void zzcj(String str) {
        return null;
    }

    private final zzanz<Void> zzpk() {
        zzanz<Void> zza;
        if (!((this.zzcms && this.zzciy.zzcnj) || (this.zzcmx && this.zzciy.zzcni) || (!this.zzcms && this.zzciy.zzcng))) {
            return zzano.zzi(null);
        }
        synchronized (this.mLock) {
            this.zzcmn.zzeck = new zzbfu[this.zzcmo.size()];
            this.zzcmo.values().toArray(this.zzcmn.zzeck);
            this.zzcmn.zzecu = (String[]) this.zzcmp.toArray(new String[0]);
            this.zzcmn.zzecv = (String[]) this.zzcmq.toArray(new String[0]);
            if (zzais.isEnabled()) {
                String str = this.zzcmn.url;
                String str2 = this.zzcmn.zzecl;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 53 + String.valueOf(str2).length());
                sb.append("Sending SB report\n  url: ");
                sb.append(str);
                sb.append("\n  clickUrl: ");
                sb.append(str2);
                sb.append("\n  resources: \n");
                StringBuilder sb2 = new StringBuilder(sb.toString());
                for (zzbfu zzbfu : this.zzcmn.zzeck) {
                    sb2.append("    [");
                    sb2.append(zzbfu.zzedu.length);
                    sb2.append("] ");
                    sb2.append(zzbfu.url);
                }
                zzais.zzck(sb2.toString());
            }
            zzanz<String> zza2 = new zzalt(this.mContext).zza(1, this.zzciy.zzcne, (Map<String, String>) null, zzbfi.zzb(this.zzcmn));
            if (zzais.isEnabled()) {
                zza2.zza(new zzain(this), zzaki.zzcrj);
            }
            zza = zzano.zza(zza2, zzaik.zzcmz, zzaoe.zzcvz);
        }
        return zza;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8, int r9) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            r1 = 3
            if (r9 != r1) goto L_0x0009
            r2 = 1
            r6.zzcmx = r2     // Catch:{ all -> 0x00c6 }
        L_0x0009:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbfu> r2 = r6.zzcmo     // Catch:{ all -> 0x00c6 }
            boolean r2 = r2.containsKey(r7)     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x0023
            if (r9 != r1) goto L_0x0021
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbfu> r8 = r6.zzcmo     // Catch:{ all -> 0x00c6 }
            java.lang.Object r7 = r8.get(r7)     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.ads.zzbfu r7 = (com.google.android.gms.internal.ads.zzbfu) r7     // Catch:{ all -> 0x00c6 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00c6 }
            r7.zzedt = r8     // Catch:{ all -> 0x00c6 }
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
            return
        L_0x0023:
            com.google.android.gms.internal.ads.zzbfu r1 = new com.google.android.gms.internal.ads.zzbfu     // Catch:{ all -> 0x00c6 }
            r1.<init>()     // Catch:{ all -> 0x00c6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00c6 }
            r1.zzedt = r9     // Catch:{ all -> 0x00c6 }
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbfu> r9 = r6.zzcmo     // Catch:{ all -> 0x00c6 }
            int r9 = r9.size()     // Catch:{ all -> 0x00c6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00c6 }
            r1.zzedn = r9     // Catch:{ all -> 0x00c6 }
            r1.url = r7     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.ads.zzbfp r9 = new com.google.android.gms.internal.ads.zzbfp     // Catch:{ all -> 0x00c6 }
            r9.<init>()     // Catch:{ all -> 0x00c6 }
            r1.zzedo = r9     // Catch:{ all -> 0x00c6 }
            java.util.HashSet<java.lang.String> r9 = r6.zzcmu     // Catch:{ all -> 0x00c6 }
            int r9 = r9.size()     // Catch:{ all -> 0x00c6 }
            if (r9 <= 0) goto L_0x00bf
            if (r8 == 0) goto L_0x00bf
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x00c6 }
            r9.<init>()     // Catch:{ all -> 0x00c6 }
            java.util.Set r8 = r8.entrySet()     // Catch:{ all -> 0x00c6 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x00c6 }
        L_0x005a:
            boolean r2 = r8.hasNext()     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x00b2
            java.lang.Object r2 = r8.next()     // Catch:{ all -> 0x00c6 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x00c6 }
            java.lang.Object r3 = r2.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            if (r3 == 0) goto L_0x0073
            java.lang.Object r3 = r2.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            goto L_0x0075
        L_0x0073:
            java.lang.String r3 = ""
        L_0x0075:
            java.lang.Object r4 = r2.getValue()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            if (r4 == 0) goto L_0x0082
            java.lang.Object r2 = r2.getValue()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            goto L_0x0084
        L_0x0082:
            java.lang.String r2 = ""
        L_0x0084:
            java.util.Locale r4 = java.util.Locale.ENGLISH     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r4 = r3.toLowerCase(r4)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.util.HashSet<java.lang.String> r5 = r6.zzcmu     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            boolean r4 = r5.contains(r4)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            if (r4 != 0) goto L_0x0093
            goto L_0x005a
        L_0x0093:
            com.google.android.gms.internal.ads.zzbfo r4 = new com.google.android.gms.internal.ads.zzbfo     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r4.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r5 = "UTF-8"
            byte[] r3 = r3.getBytes(r5)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r4.zzecx = r3     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r3 = "UTF-8"
            byte[] r2 = r2.getBytes(r3)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r4.zzecy = r2     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r9.add(r4)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            goto L_0x005a
        L_0x00ac:
            java.lang.String r2 = "Cannot convert string to bytes, skip header."
            com.google.android.gms.internal.ads.zzais.zzck(r2)     // Catch:{ all -> 0x00c6 }
            goto L_0x005a
        L_0x00b2:
            int r8 = r9.size()     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.ads.zzbfo[] r8 = new com.google.android.gms.internal.ads.zzbfo[r8]     // Catch:{ all -> 0x00c6 }
            r9.toArray(r8)     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.ads.zzbfp r9 = r1.zzedo     // Catch:{ all -> 0x00c6 }
            r9.zzeda = r8     // Catch:{ all -> 0x00c6 }
        L_0x00bf:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbfu> r8 = r6.zzcmo     // Catch:{ all -> 0x00c6 }
            r8.put(r7, r1)     // Catch:{ all -> 0x00c6 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
            return
        L_0x00c6:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
            goto L_0x00ca
        L_0x00c9:
            throw r7
        L_0x00ca:
            goto L_0x00c9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaii.zza(java.lang.String, java.util.Map, int):void");
    }

    public final String[] zzb(String[] strArr) {
        return (String[]) this.zzcmt.zzc(strArr).toArray(new String[0]);
    }

    public final void zzcf(String str) {
        synchronized (this.mLock) {
            this.zzcmn.zzecl = str;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzcg(String str) {
        synchronized (this.mLock) {
            this.zzcmp.add(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzch(String str) {
        synchronized (this.mLock) {
            this.zzcmq.add(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzanz zzm(Map map) throws Exception {
        if (map != null) {
            try {
                for (String str : map.keySet()) {
                    JSONArray optJSONArray = new JSONObject((String) map.get(str)).optJSONArray("matches");
                    if (optJSONArray != null) {
                        synchronized (this.mLock) {
                            int length = optJSONArray.length();
                            zzbfu zzci = zzci(str);
                            if (zzci == null) {
                                String valueOf = String.valueOf(str);
                                zzais.zzck(valueOf.length() != 0 ? "Cannot find the corresponding resource object for ".concat(valueOf) : new String("Cannot find the corresponding resource object for "));
                            } else {
                                zzci.zzedu = new String[length];
                                boolean z = false;
                                for (int i = 0; i < length; i++) {
                                    zzci.zzedu[i] = optJSONArray.getJSONObject(i).getString("threat_type");
                                }
                                boolean z2 = this.zzcms;
                                if (length > 0) {
                                    z = true;
                                }
                                this.zzcms = z | z2;
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbdi)).booleanValue()) {
                    zzakb.zza("Failed to get SafeBrowsing metadata", e);
                }
                return zzano.zzd(new Exception("Safebrowsing report transmission failed."));
            }
        }
        if (this.zzcms) {
            synchronized (this.mLock) {
                this.zzcmn.zzamf = 9;
            }
        }
        return zzpk();
    }

    public final zzaiq zzpg() {
        return this.zzciy;
    }

    public final boolean zzph() {
        return PlatformVersion.isAtLeastKitKat() && this.zzciy.zzcnf && !this.zzcmw;
    }

    public final void zzpi() {
        this.zzcmv = true;
    }

    public final void zzpj() {
        synchronized (this.mLock) {
            zzanz<B> zza = zzano.zza(this.zzcmr.zza(this.mContext, this.zzcmo.keySet()), new zzaij(this), zzaoe.zzcvz);
            zzanz<V> zza2 = zzano.zza(zza, 10, TimeUnit.SECONDS, zzcmm);
            zzano.zza(zza, new zzaim(this, zza2), zzaoe.zzcvz);
            zzcml.add(zza2);
        }
    }

    public final void zzr(View view) {
        if (this.zzciy.zzcnf && !this.zzcmw) {
            zzbv.zzek();
            Bitmap zzt = zzakk.zzt(view);
            if (zzt == null) {
                zzais.zzck("Failed to capture the webview bitmap.");
                return;
            }
            this.zzcmw = true;
            zzakk.zzd(new zzail(this, zzt));
        }
    }
}
