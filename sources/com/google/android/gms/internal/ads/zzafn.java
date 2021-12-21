package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.internal.zzbv;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafn extends zzaeo {
    private static final Object sLock = new Object();
    private static zzafn zzchh;
    private final Context mContext;
    private final zzafm zzchi;
    private final ScheduledExecutorService zzchj = Executors.newSingleThreadScheduledExecutor();

    private zzafn(Context context, zzafm zzafm) {
        this.mContext = context;
        this.zzchi = zzafm;
    }

    private static zzaej zza(Context context, zzafm zzafm, zzaef zzaef, ScheduledExecutorService scheduledExecutorService) {
        zznx zznx;
        String string;
        Context context2 = context;
        zzafm zzafm2 = zzafm;
        zzaef zzaef2 = zzaef;
        ScheduledExecutorService scheduledExecutorService2 = scheduledExecutorService;
        zzakb.zzck("Starting ad request from service using: google.afma.request.getAdDictionary");
        zznx zznx2 = new zznx(((Boolean) zzkb.zzik().zzd(zznk.zzawh)).booleanValue(), "load_ad", zzaef2.zzacv.zzarb);
        if (zzaef2.versionCode > 10 && zzaef2.zzcdl != -1) {
            zznx2.zza(zznx2.zzd(zzaef2.zzcdl), "cts");
        }
        zznv zzjj = zznx2.zzjj();
        zzanz<V> zza = zzano.zza(zzafm2.zzche.zzk(context2), ((Long) zzkb.zzik().zzd(zznk.zzbdf)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        zzanz<V> zza2 = zzano.zza(zzafm2.zzchd.zzr(context2), ((Long) zzkb.zzik().zzd(zznk.zzbah)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        zzanz<String> zzcl = zzafm2.zzcgy.zzcl(zzaef2.zzccw.packageName);
        zzanz<String> zzcm = zzafm2.zzcgy.zzcm(zzaef2.zzccw.packageName);
        zzanz<String> zza3 = zzafm2.zzchf.zza(zzaef2.zzccx, zzaef2.zzccw);
        Future<zzaga> zzq = zzbv.zzev().zzq(context2);
        zzanz zzi = zzano.zzi(null);
        Bundle bundle = zzaef2.zzccv.extras;
        boolean z = (bundle == null || bundle.getString("_ad") == null) ? false : true;
        if (zzaef2.zzcdr && !z) {
            zzi = zzafm2.zzchb.zza(zzaef2.applicationInfo);
        }
        zznx zznx3 = zznx2;
        zznv zznv = zzjj;
        zzanz zza4 = zzano.zza(zzi, ((Long) zzkb.zzik().zzd(zznk.zzbco)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        zzanz<V> zzi2 = zzano.zzi(null);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayj)).booleanValue()) {
            zznx = zznx3;
            zzi2 = zzano.zza(zzafm2.zzchf.zzae(context2), ((Long) zzkb.zzik().zzd(zznk.zzayk)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        } else {
            zznx = zznx3;
        }
        Bundle bundle2 = (zzaef2.versionCode < 4 || zzaef2.zzcdc == null) ? null : zzaef2.zzcdc;
        ((Boolean) zzkb.zzik().zzd(zznk.zzawx)).booleanValue();
        zzbv.zzek();
        if (zzakk.zzl(context2, "android.permission.ACCESS_NETWORK_STATE") && ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            zzakb.zzck("Device is offline.");
        }
        String uuid = zzaef2.versionCode >= 7 ? zzaef2.zzcdi : UUID.randomUUID().toString();
        new zzaft(context2, uuid, zzaef2.applicationInfo.packageName);
        if (zzaef2.zzccv.extras != null && (string = zzaef2.zzccv.extras.getString("_ad")) != null) {
            return zzafs.zza(context2, zzaef2, string);
        }
        List<String> zzf = zzafm2.zzcgz.zzf(zzaef2.zzcdj);
        String str = uuid;
        Bundle bundle3 = (Bundle) zzano.zza(zza, null, ((Long) zzkb.zzik().zzd(zznk.zzbdf)).longValue(), TimeUnit.MILLISECONDS);
        zzagk zzagk = (zzagk) zzano.zza(zza2, null);
        Location location = (Location) zzano.zza(zza4, null);
        AdvertisingIdClient.Info info = (AdvertisingIdClient.Info) zzano.zza(zzi2, null);
        String str2 = (String) zzano.zza(zza3, null);
        String str3 = (String) zzano.zza(zzcl, null);
        String str4 = (String) zzano.zza(zzcm, null);
        zzaga zzaga = (zzaga) zzano.zza(zzq, null);
        if (zzaga == null) {
            zzakb.zzdk("Error fetching device info. This is not recoverable.");
            return new zzaej(0);
        }
        zzafl zzafl = new zzafl();
        zzafl.zzcgs = zzaef2;
        zzafl.zzcgt = zzaga;
        zzafl.zzcgo = zzagk;
        zzafl.zzaqe = location;
        zzafl.zzcgn = bundle3;
        zzafl.zzccx = str2;
        zzafl.zzcgr = info;
        if (zzf == null) {
            zzafl.zzcdj.clear();
        }
        zzafl.zzcdj = zzf;
        zzafl.zzcdc = bundle2;
        zzafl.zzcgp = str3;
        zzafl.zzcgq = str4;
        Context context3 = context;
        zzafl.zzcgu = zzafm2.zzcgx.zze(context3);
        zzafl.zzcgv = zzafm2.zzcgv;
        JSONObject zza5 = zzafs.zza(context3, zzafl);
        if (zza5 == null) {
            return new zzaej(0);
        }
        if (zzaef2.versionCode < 7) {
            try {
                zza5.put("request_id", str);
            } catch (JSONException unused) {
            }
        }
        zznv zznv2 = zznv;
        zznx zznx4 = zznx;
        zznx4.zza(zznv2, "arc");
        zznx4.zzjj();
        ScheduledExecutorService scheduledExecutorService3 = scheduledExecutorService;
        zzanz<V> zza6 = zzano.zza(zzano.zza(zzafm2.zzchg.zzof().zzf(zza5), zzafo.zzxn, (Executor) scheduledExecutorService3), 10, TimeUnit.SECONDS, scheduledExecutorService3);
        zzanz<Void> zzop = zzafm2.zzcha.zzop();
        if (zzop != null) {
            zzanm.zza(zzop, "AdRequestServiceImpl.loadAd.flags");
        }
        zzafz zzafz = (zzafz) zzano.zza(zza6, null);
        if (zzafz == null) {
            return new zzaej(0);
        }
        if (zzafz.getErrorCode() != -2) {
            return new zzaej(zzafz.getErrorCode());
        }
        zznx4.zzjm();
        zzaej zza7 = !TextUtils.isEmpty(zzafz.zzom()) ? zzafs.zza(context3, zzaef2, zzafz.zzom()) : null;
        if (zza7 == null && !TextUtils.isEmpty(zzafz.getUrl())) {
            zza7 = zza(zzaef, context, zzaef2.zzacr.zzcw, zzafz.getUrl(), str3, str4, zzafz, zznx4, zzafm);
        }
        if (zza7 == null) {
            zza7 = new zzaej(0);
        }
        zznx4.zza(zznv2, "tts");
        zza7.zzcfd = zznx4.zzjk();
        return zza7;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e6, code lost:
        r0 = r6.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r6 = new java.io.InputStreamReader(r11.getInputStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        com.google.android.gms.ads.internal.zzbv.zzek();
        r10 = com.google.android.gms.internal.ads.zzakk.zza(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r6);
        r3.zzdg(r10);
        zza(r0, (java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r12, r10, r9);
        r5.zza(r0, r12, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0106, code lost:
        if (r1 == null) goto L_0x0111;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0108, code lost:
        r1.zza(r4, "ufe");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0111, code lost:
        r0 = r5.zza(r7, r23.zzon());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x011c, code lost:
        if (r2 == null) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x011e, code lost:
        r2.zzchc.zzor();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0123, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0124, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0125, code lost:
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0127, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0128, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x012c, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0147, code lost:
        com.google.android.gms.internal.ads.zzakb.zzdk("No location header to follow redirect.");
        r0 = new com.google.android.gms.internal.ads.zzaej(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0155, code lost:
        if (r2 == null) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0157, code lost:
        r2.zzchc.zzor();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x015c, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0176, code lost:
        com.google.android.gms.internal.ads.zzakb.zzdk("Too many redirects.");
        r0 = new com.google.android.gms.internal.ads.zzaej(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0184, code lost:
        if (r2 == null) goto L_0x018b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0186, code lost:
        r2.zzchc.zzor();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x018b, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008a A[Catch:{ all -> 0x00c0, all -> 0x01c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a1 A[Catch:{ all -> 0x00c0, all -> 0x01c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c8 A[Catch:{ all -> 0x00c0, all -> 0x01c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x013b A[Catch:{ all -> 0x00c0, all -> 0x01c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x019e A[EDGE_INSN: B:119:0x019e->B:99:0x019e ?: BREAK  
    EDGE_INSN: B:120:0x019e->B:99:0x019e ?: BREAK  ] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzaej zza(com.google.android.gms.internal.ads.zzaef r17, android.content.Context r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, com.google.android.gms.internal.ads.zzafz r23, com.google.android.gms.internal.ads.zznx r24, com.google.android.gms.internal.ads.zzafm r25) {
        /*
            r0 = r17
            r1 = r24
            r2 = r25
            if (r1 == 0) goto L_0x000d
            com.google.android.gms.internal.ads.zznv r4 = r24.zzjj()
            goto L_0x000e
        L_0x000d:
            r4 = 0
        L_0x000e:
            com.google.android.gms.internal.ads.zzafx r5 = new com.google.android.gms.internal.ads.zzafx     // Catch:{ IOException -> 0x01d1 }
            java.lang.String r6 = r23.zzoi()     // Catch:{ IOException -> 0x01d1 }
            r5.<init>(r0, r6)     // Catch:{ IOException -> 0x01d1 }
            java.lang.String r6 = "AdRequestServiceImpl: Sending request: "
            java.lang.String r7 = java.lang.String.valueOf(r20)     // Catch:{ IOException -> 0x01d1 }
            int r8 = r7.length()     // Catch:{ IOException -> 0x01d1 }
            if (r8 == 0) goto L_0x0028
            java.lang.String r6 = r6.concat(r7)     // Catch:{ IOException -> 0x01d1 }
            goto L_0x002e
        L_0x0028:
            java.lang.String r7 = new java.lang.String     // Catch:{ IOException -> 0x01d1 }
            r7.<init>(r6)     // Catch:{ IOException -> 0x01d1 }
            r6 = r7
        L_0x002e:
            com.google.android.gms.internal.ads.zzakb.zzck(r6)     // Catch:{ IOException -> 0x01d1 }
            java.net.URL r6 = new java.net.URL     // Catch:{ IOException -> 0x01d1 }
            r7 = r20
            r6.<init>(r7)     // Catch:{ IOException -> 0x01d1 }
            com.google.android.gms.common.util.Clock r7 = com.google.android.gms.ads.internal.zzbv.zzer()     // Catch:{ IOException -> 0x01d1 }
            long r7 = r7.elapsedRealtime()     // Catch:{ IOException -> 0x01d1 }
            r9 = 0
            r10 = 0
        L_0x0042:
            if (r2 == 0) goto L_0x0049
            com.google.android.gms.internal.ads.zzagi r11 = r2.zzchc     // Catch:{ IOException -> 0x01d1 }
            r11.zzoq()     // Catch:{ IOException -> 0x01d1 }
        L_0x0049:
            java.net.URLConnection r11 = r6.openConnection()     // Catch:{ IOException -> 0x01d1 }
            java.net.HttpURLConnection r11 = (java.net.HttpURLConnection) r11     // Catch:{ IOException -> 0x01d1 }
            com.google.android.gms.internal.ads.zzakk r12 = com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ all -> 0x01c5 }
            r13 = r18
            r14 = r19
            r12.zza((android.content.Context) r13, (java.lang.String) r14, (boolean) r9, (java.net.HttpURLConnection) r11)     // Catch:{ all -> 0x01c5 }
            boolean r12 = r23.zzok()     // Catch:{ all -> 0x01c5 }
            if (r12 == 0) goto L_0x007e
            boolean r12 = android.text.TextUtils.isEmpty(r21)     // Catch:{ all -> 0x01c5 }
            if (r12 != 0) goto L_0x006e
            java.lang.String r12 = "x-afma-drt-cookie"
            r15 = r21
            r11.addRequestProperty(r12, r15)     // Catch:{ all -> 0x01c5 }
            goto L_0x0070
        L_0x006e:
            r15 = r21
        L_0x0070:
            boolean r12 = android.text.TextUtils.isEmpty(r22)     // Catch:{ all -> 0x01c5 }
            if (r12 != 0) goto L_0x0080
            java.lang.String r12 = "x-afma-drt-v2-cookie"
            r9 = r22
            r11.addRequestProperty(r12, r9)     // Catch:{ all -> 0x01c5 }
            goto L_0x0082
        L_0x007e:
            r15 = r21
        L_0x0080:
            r9 = r22
        L_0x0082:
            java.lang.String r12 = r0.zzcds     // Catch:{ all -> 0x01c5 }
            boolean r16 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x01c5 }
            if (r16 != 0) goto L_0x0094
            java.lang.String r16 = "Sending webview cookie in ad request header."
            com.google.android.gms.internal.ads.zzakb.zzck(r16)     // Catch:{ all -> 0x01c5 }
            java.lang.String r3 = "Cookie"
            r11.addRequestProperty(r3, r12)     // Catch:{ all -> 0x01c5 }
        L_0x0094:
            r3 = 1
            if (r23 == 0) goto L_0x00c8
            java.lang.String r12 = r23.zzoj()     // Catch:{ all -> 0x01c5 }
            boolean r12 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x01c5 }
            if (r12 != 0) goto L_0x00c8
            r11.setDoOutput(r3)     // Catch:{ all -> 0x01c5 }
            java.lang.String r12 = r23.zzoj()     // Catch:{ all -> 0x01c5 }
            byte[] r12 = r12.getBytes()     // Catch:{ all -> 0x01c5 }
            int r3 = r12.length     // Catch:{ all -> 0x01c5 }
            r11.setFixedLengthStreamingMode(r3)     // Catch:{ all -> 0x01c5 }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00c2 }
            java.io.OutputStream r9 = r11.getOutputStream()     // Catch:{ all -> 0x00c2 }
            r3.<init>(r9)     // Catch:{ all -> 0x00c2 }
            r3.write(r12)     // Catch:{ all -> 0x00c0 }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3)     // Catch:{ all -> 0x01c5 }
            goto L_0x00c9
        L_0x00c0:
            r0 = move-exception
            goto L_0x00c4
        L_0x00c2:
            r0 = move-exception
            r3 = 0
        L_0x00c4:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3)     // Catch:{ all -> 0x01c5 }
            throw r0     // Catch:{ all -> 0x01c5 }
        L_0x00c8:
            r12 = 0
        L_0x00c9:
            com.google.android.gms.internal.ads.zzamy r3 = new com.google.android.gms.internal.ads.zzamy     // Catch:{ all -> 0x01c5 }
            java.lang.String r9 = r0.zzcdi     // Catch:{ all -> 0x01c5 }
            r3.<init>(r9)     // Catch:{ all -> 0x01c5 }
            r3.zza((java.net.HttpURLConnection) r11, (byte[]) r12)     // Catch:{ all -> 0x01c5 }
            int r9 = r11.getResponseCode()     // Catch:{ all -> 0x01c5 }
            java.util.Map r12 = r11.getHeaderFields()     // Catch:{ all -> 0x01c5 }
            r3.zza((java.net.HttpURLConnection) r11, (int) r9)     // Catch:{ all -> 0x01c5 }
            r0 = 200(0xc8, float:2.8E-43)
            r13 = 300(0x12c, float:4.2E-43)
            if (r9 < r0) goto L_0x012d
            if (r9 >= r13) goto L_0x012d
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x01c5 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ all -> 0x0127 }
            java.io.InputStream r10 = r11.getInputStream()     // Catch:{ all -> 0x0127 }
            r6.<init>(r10)     // Catch:{ all -> 0x0127 }
            com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ all -> 0x0124 }
            java.lang.String r10 = com.google.android.gms.internal.ads.zzakk.zza((java.io.InputStreamReader) r6)     // Catch:{ all -> 0x0124 }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r6)     // Catch:{ all -> 0x01c5 }
            r3.zzdg(r10)     // Catch:{ all -> 0x01c5 }
            zza((java.lang.String) r0, (java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r12, (java.lang.String) r10, (int) r9)     // Catch:{ all -> 0x01c5 }
            r5.zza(r0, r12, r10)     // Catch:{ all -> 0x01c5 }
            if (r1 == 0) goto L_0x0111
            java.lang.String r0 = "ufe"
            java.lang.String[] r0 = new java.lang.String[]{r0}     // Catch:{ all -> 0x01c5 }
            r1.zza(r4, r0)     // Catch:{ all -> 0x01c5 }
        L_0x0111:
            boolean r0 = r23.zzon()     // Catch:{ all -> 0x01c5 }
            com.google.android.gms.internal.ads.zzaej r0 = r5.zza((long) r7, (boolean) r0)     // Catch:{ all -> 0x01c5 }
            r11.disconnect()     // Catch:{ IOException -> 0x01d1 }
            if (r2 == 0) goto L_0x0123
            com.google.android.gms.internal.ads.zzagi r1 = r2.zzchc     // Catch:{ IOException -> 0x01d1 }
            r1.zzor()     // Catch:{ IOException -> 0x01d1 }
        L_0x0123:
            return r0
        L_0x0124:
            r0 = move-exception
            r3 = r6
            goto L_0x0129
        L_0x0127:
            r0 = move-exception
            r3 = 0
        L_0x0129:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3)     // Catch:{ all -> 0x01c5 }
            throw r0     // Catch:{ all -> 0x01c5 }
        L_0x012d:
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x01c5 }
            r3 = 0
            zza((java.lang.String) r0, (java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r12, (java.lang.String) r3, (int) r9)     // Catch:{ all -> 0x01c5 }
            if (r9 < r13) goto L_0x019e
            r0 = 400(0x190, float:5.6E-43)
            if (r9 >= r0) goto L_0x019e
            java.lang.String r0 = "Location"
            java.lang.String r0 = r11.getHeaderField(r0)     // Catch:{ all -> 0x01c5 }
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x01c5 }
            if (r6 == 0) goto L_0x015d
            java.lang.String r0 = "No location header to follow redirect."
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)     // Catch:{ all -> 0x01c5 }
            com.google.android.gms.internal.ads.zzaej r0 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ all -> 0x01c5 }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x01c5 }
            r11.disconnect()     // Catch:{ IOException -> 0x01d1 }
            if (r2 == 0) goto L_0x015c
            com.google.android.gms.internal.ads.zzagi r1 = r2.zzchc     // Catch:{ IOException -> 0x01d1 }
            r1.zzor()     // Catch:{ IOException -> 0x01d1 }
        L_0x015c:
            return r0
        L_0x015d:
            java.net.URL r6 = new java.net.URL     // Catch:{ all -> 0x01c5 }
            r6.<init>(r0)     // Catch:{ all -> 0x01c5 }
            r0 = 1
            int r10 = r10 + r0
            com.google.android.gms.internal.ads.zzna<java.lang.Integer> r0 = com.google.android.gms.internal.ads.zznk.zzbes     // Catch:{ all -> 0x01c5 }
            com.google.android.gms.internal.ads.zzni r9 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x01c5 }
            java.lang.Object r0 = r9.zzd(r0)     // Catch:{ all -> 0x01c5 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x01c5 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x01c5 }
            if (r10 <= r0) goto L_0x018c
            java.lang.String r0 = "Too many redirects."
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)     // Catch:{ all -> 0x01c5 }
            com.google.android.gms.internal.ads.zzaej r0 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ all -> 0x01c5 }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x01c5 }
            r11.disconnect()     // Catch:{ IOException -> 0x01d1 }
            if (r2 == 0) goto L_0x018b
            com.google.android.gms.internal.ads.zzagi r1 = r2.zzchc     // Catch:{ IOException -> 0x01d1 }
            r1.zzor()     // Catch:{ IOException -> 0x01d1 }
        L_0x018b:
            return r0
        L_0x018c:
            r5.zzl(r12)     // Catch:{ all -> 0x01c5 }
            r11.disconnect()     // Catch:{ IOException -> 0x01d1 }
            if (r2 == 0) goto L_0x0199
            com.google.android.gms.internal.ads.zzagi r0 = r2.zzchc     // Catch:{ IOException -> 0x01d1 }
            r0.zzor()     // Catch:{ IOException -> 0x01d1 }
        L_0x0199:
            r0 = r17
            r9 = 0
            goto L_0x0042
        L_0x019e:
            r0 = 46
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c5 }
            r1.<init>(r0)     // Catch:{ all -> 0x01c5 }
            java.lang.String r0 = "Received error HTTP response code: "
            r1.append(r0)     // Catch:{ all -> 0x01c5 }
            r1.append(r9)     // Catch:{ all -> 0x01c5 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x01c5 }
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)     // Catch:{ all -> 0x01c5 }
            com.google.android.gms.internal.ads.zzaej r0 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ all -> 0x01c5 }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x01c5 }
            r11.disconnect()     // Catch:{ IOException -> 0x01d1 }
            if (r2 == 0) goto L_0x01c4
            com.google.android.gms.internal.ads.zzagi r1 = r2.zzchc     // Catch:{ IOException -> 0x01d1 }
            r1.zzor()     // Catch:{ IOException -> 0x01d1 }
        L_0x01c4:
            return r0
        L_0x01c5:
            r0 = move-exception
            r11.disconnect()     // Catch:{ IOException -> 0x01d1 }
            if (r2 == 0) goto L_0x01d0
            com.google.android.gms.internal.ads.zzagi r1 = r2.zzchc     // Catch:{ IOException -> 0x01d1 }
            r1.zzor()     // Catch:{ IOException -> 0x01d1 }
        L_0x01d0:
            throw r0     // Catch:{ IOException -> 0x01d1 }
        L_0x01d1:
            r0 = move-exception
            java.lang.String r1 = "Error while connecting to ad server: "
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r0.length()
            if (r2 == 0) goto L_0x01e7
            java.lang.String r0 = r1.concat(r0)
            goto L_0x01ec
        L_0x01e7:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r1)
        L_0x01ec:
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)
            com.google.android.gms.internal.ads.zzaej r0 = new com.google.android.gms.internal.ads.zzaej
            r1 = 2
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafn.zza(com.google.android.gms.internal.ads.zzaef, android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.ads.zzafz, com.google.android.gms.internal.ads.zznx, com.google.android.gms.internal.ads.zzafm):com.google.android.gms.internal.ads.zzaej");
    }

    public static zzafn zza(Context context, zzafm zzafm) {
        zzafn zzafn;
        synchronized (sLock) {
            if (zzchh == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zznk.initialize(context);
                zzchh = new zzafn(context, zzafm);
                if (context.getApplicationContext() != null) {
                    zzbv.zzek().zzal(context);
                }
                zzajz.zzai(context);
            }
            zzafn = zzchh;
        }
        return zzafn;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzakb.isLoggable(2)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39);
            sb.append("Http Response: {\n  URL:\n    ");
            sb.append(str);
            sb.append("\n  Headers:");
            zzakb.v(sb.toString());
            if (map != null) {
                for (String next : map.keySet()) {
                    StringBuilder sb2 = new StringBuilder(String.valueOf(next).length() + 5);
                    sb2.append("    ");
                    sb2.append(next);
                    sb2.append(":");
                    zzakb.v(sb2.toString());
                    for (String valueOf : map.get(next)) {
                        String valueOf2 = String.valueOf(valueOf);
                        zzakb.v(valueOf2.length() != 0 ? "      ".concat(valueOf2) : new String("      "));
                    }
                }
            }
            zzakb.v("  Body:");
            if (str2 != null) {
                int i2 = 0;
                while (i2 < Math.min(str2.length(), 100000)) {
                    int i3 = i2 + 1000;
                    zzakb.v(str2.substring(i2, Math.min(str2.length(), i3)));
                    i2 = i3;
                }
            } else {
                zzakb.v("    null");
            }
            StringBuilder sb3 = new StringBuilder(34);
            sb3.append("  Response Code:\n    ");
            sb3.append(i);
            sb3.append("\n}");
            zzakb.v(sb3.toString());
        }
    }

    public final void zza(zzaef zzaef, zzaeq zzaeq) {
        zzbv.zzeo().zzd(this.mContext, zzaef.zzacr);
        zzanz<?> zzb = zzaki.zzb(new zzafp(this, zzaef, zzaeq));
        zzbv.zzez().zzsa();
        zzbv.zzez().getHandler().postDelayed(new zzafq(this, zzb), 60000);
    }

    public final void zza(zzaey zzaey, zzaet zzaet) {
        zzakb.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    public final zzaej zzb(zzaef zzaef) {
        return zza(this.mContext, this.zzchi, zzaef, this.zzchj);
    }

    public final void zzb(zzaey zzaey, zzaet zzaet) {
        zzakb.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }
}
