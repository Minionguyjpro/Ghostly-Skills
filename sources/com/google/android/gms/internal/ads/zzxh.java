package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzadh
public final class zzxh implements zzww {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public final long mStartTime;
    private final boolean zzael;
    private final zzwy zzbtj;
    private final boolean zzbtn;
    private final boolean zzbto;
    private final zzaef zzbuc;
    /* access modifiers changed from: private */
    public final long zzbud;
    private final int zzbue;
    /* access modifiers changed from: private */
    public boolean zzbuf = false;
    /* access modifiers changed from: private */
    public final Map<zzanz<zzxe>, zzxb> zzbug = new HashMap();
    private final String zzbuh;
    private List<zzxe> zzbui = new ArrayList();
    private final zzxn zzwh;

    public zzxh(Context context, zzaef zzaef, zzxn zzxn, zzwy zzwy, boolean z, boolean z2, String str, long j, long j2, int i, boolean z3) {
        this.mContext = context;
        this.zzbuc = zzaef;
        this.zzwh = zzxn;
        this.zzbtj = zzwy;
        this.zzael = z;
        this.zzbtn = z2;
        this.zzbuh = str;
        this.mStartTime = j;
        this.zzbud = j2;
        this.zzbue = 2;
        this.zzbto = z3;
    }

    private final void zza(zzanz<zzxe> zzanz) {
        zzakk.zzcrm.post(new zzxj(this, zzanz));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r4.hasNext() == false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = (com.google.android.gms.internal.ads.zzxe) r0.get();
        r3.zzbui.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r1 == null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        if (r1.zzbtv != 0) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0038, code lost:
        com.google.android.gms.internal.ads.zzakb.zzc("Exception while processing an adapter; continuing with other adapters", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0048, code lost:
        return new com.google.android.gms.internal.ads.zzxe(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r4 = r4.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzxe zzi(java.util.List<com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>> r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.zzbuf     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x000f
            com.google.android.gms.internal.ads.zzxe r4 = new com.google.android.gms.internal.ads.zzxe     // Catch:{ all -> 0x0049 }
            r1 = -1
            r4.<init>(r1)     // Catch:{ all -> 0x0049 }
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            return r4
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            java.util.Iterator r4 = r4.iterator()
        L_0x0014:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x003e
            java.lang.Object r0 = r4.next()
            com.google.android.gms.internal.ads.zzanz r0 = (com.google.android.gms.internal.ads.zzanz) r0
            java.lang.Object r1 = r0.get()     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            com.google.android.gms.internal.ads.zzxe r1 = (com.google.android.gms.internal.ads.zzxe) r1     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            java.util.List<com.google.android.gms.internal.ads.zzxe> r2 = r3.zzbui     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            r2.add(r1)     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            if (r1 == 0) goto L_0x0014
            int r2 = r1.zzbtv     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            if (r2 != 0) goto L_0x0014
            r3.zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) r0)     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            return r1
        L_0x0035:
            r0 = move-exception
            goto L_0x0038
        L_0x0037:
            r0 = move-exception
        L_0x0038:
            java.lang.String r1 = "Exception while processing an adapter; continuing with other adapters"
            com.google.android.gms.internal.ads.zzakb.zzc(r1, r0)
            goto L_0x0014
        L_0x003e:
            r4 = 0
            r3.zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) r4)
            com.google.android.gms.internal.ads.zzxe r4 = new com.google.android.gms.internal.ads.zzxe
            r0 = 1
            r4.<init>(r0)
            return r4
        L_0x0049:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            goto L_0x004d
        L_0x004c:
            throw r4
        L_0x004d:
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxh.zzi(java.util.List):com.google.android.gms.internal.ads.zzxe");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r13.zzbtj.zzbsy == -1) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r0 = r13.zzbtj.zzbsy;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r0 = 10000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r14 = r14.iterator();
        r3 = null;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r14.hasNext() == false) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r5 = r14.next();
        r6 = com.google.android.gms.ads.internal.zzbv.zzer().currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        if (r0 != 0) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        if (r5.isDone() == false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        r10 = r5.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        r10 = (com.google.android.gms.internal.ads.zzxe) r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        r10 = r5.get(r0, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0055, code lost:
        r13.zzbui.add(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        if (r10 == null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005e, code lost:
        if (r10.zzbtv != 0) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0060, code lost:
        r11 = r10.zzbua;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0062, code lost:
        if (r11 == null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0068, code lost:
        if (r11.zzmm() <= r2) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006a, code lost:
        r2 = r11.zzmm();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006e, code lost:
        r3 = r5;
        r4 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0071, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0073, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        com.google.android.gms.internal.ads.zzakb.zzc("Exception while processing an adapter; continuing with other adapters", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008e, code lost:
        java.lang.Math.max(r0 - (com.google.android.gms.ads.internal.zzbv.zzer().currentTimeMillis() - r6), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009b, code lost:
        throw r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009c, code lost:
        zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009f, code lost:
        if (r4 != null) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a7, code lost:
        return new com.google.android.gms.internal.ads.zzxe(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a8, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzxe zzj(java.util.List<com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>> r14) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.mLock
            monitor-enter(r0)
            boolean r1 = r13.zzbuf     // Catch:{ all -> 0x00a9 }
            r2 = -1
            if (r1 == 0) goto L_0x000f
            com.google.android.gms.internal.ads.zzxe r14 = new com.google.android.gms.internal.ads.zzxe     // Catch:{ all -> 0x00a9 }
            r14.<init>(r2)     // Catch:{ all -> 0x00a9 }
            monitor-exit(r0)     // Catch:{ all -> 0x00a9 }
            return r14
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x00a9 }
            com.google.android.gms.internal.ads.zzwy r0 = r13.zzbtj
            long r0 = r0.zzbsy
            r3 = -1
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x001f
            com.google.android.gms.internal.ads.zzwy r0 = r13.zzbtj
            long r0 = r0.zzbsy
            goto L_0x0021
        L_0x001f:
            r0 = 10000(0x2710, double:4.9407E-320)
        L_0x0021:
            java.util.Iterator r14 = r14.iterator()
            r3 = 0
            r4 = r3
        L_0x0027:
            boolean r5 = r14.hasNext()
            if (r5 == 0) goto L_0x009c
            java.lang.Object r5 = r14.next()
            com.google.android.gms.internal.ads.zzanz r5 = (com.google.android.gms.internal.ads.zzanz) r5
            com.google.android.gms.common.util.Clock r6 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r6 = r6.currentTimeMillis()
            r8 = 0
            int r10 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x004e
            boolean r10 = r5.isDone()     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
            if (r10 == 0) goto L_0x004e
            java.lang.Object r10 = r5.get()     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
        L_0x004b:
            com.google.android.gms.internal.ads.zzxe r10 = (com.google.android.gms.internal.ads.zzxe) r10     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
            goto L_0x0055
        L_0x004e:
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
            java.lang.Object r10 = r5.get(r0, r10)     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
            goto L_0x004b
        L_0x0055:
            java.util.List<com.google.android.gms.internal.ads.zzxe> r11 = r13.zzbui     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
            r11.add(r10)     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
            if (r10 == 0) goto L_0x007f
            int r11 = r10.zzbtv     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
            if (r11 != 0) goto L_0x007f
            com.google.android.gms.internal.ads.zzxw r11 = r10.zzbua     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
            if (r11 == 0) goto L_0x007f
            int r12 = r11.zzmm()     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
            if (r12 <= r2) goto L_0x007f
            int r2 = r11.zzmm()     // Catch:{ InterruptedException -> 0x0079, ExecutionException -> 0x0077, RemoteException -> 0x0075, TimeoutException -> 0x0073 }
            r3 = r5
            r4 = r10
            goto L_0x007f
        L_0x0071:
            r14 = move-exception
            goto L_0x008e
        L_0x0073:
            r5 = move-exception
            goto L_0x007a
        L_0x0075:
            r5 = move-exception
            goto L_0x007a
        L_0x0077:
            r5 = move-exception
            goto L_0x007a
        L_0x0079:
            r5 = move-exception
        L_0x007a:
            java.lang.String r10 = "Exception while processing an adapter; continuing with other adapters"
            com.google.android.gms.internal.ads.zzakb.zzc(r10, r5)     // Catch:{ all -> 0x0071 }
        L_0x007f:
            com.google.android.gms.common.util.Clock r5 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r10 = r5.currentTimeMillis()
            long r10 = r10 - r6
            long r0 = r0 - r10
            long r0 = java.lang.Math.max(r0, r8)
            goto L_0x0027
        L_0x008e:
            com.google.android.gms.common.util.Clock r2 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r2 = r2.currentTimeMillis()
            long r2 = r2 - r6
            long r0 = r0 - r2
            java.lang.Math.max(r0, r8)
            throw r14
        L_0x009c:
            r13.zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) r3)
            if (r4 != 0) goto L_0x00a8
            com.google.android.gms.internal.ads.zzxe r14 = new com.google.android.gms.internal.ads.zzxe
            r0 = 1
            r14.<init>(r0)
            return r14
        L_0x00a8:
            return r4
        L_0x00a9:
            r14 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00a9 }
            goto L_0x00ad
        L_0x00ac:
            throw r14
        L_0x00ad:
            goto L_0x00ac
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxh.zzj(java.util.List):com.google.android.gms.internal.ads.zzxe");
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzbuf = true;
            for (zzxb cancel : this.zzbug.values()) {
                cancel.cancel();
            }
        }
    }

    public final zzxe zzh(List<zzwx> list) {
        zzakb.zzck("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzjn zzjn = this.zzbuc.zzacv;
        int[] iArr = new int[2];
        if (zzjn.zzard != null) {
            zzbv.zzfd();
            if (zzxg.zza(this.zzbuh, iArr)) {
                int i = 0;
                int i2 = iArr[0];
                int i3 = iArr[1];
                zzjn[] zzjnArr = zzjn.zzard;
                int length = zzjnArr.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    zzjn zzjn2 = zzjnArr[i];
                    if (i2 == zzjn2.width && i3 == zzjn2.height) {
                        zzjn = zzjn2;
                        break;
                    }
                    i++;
                }
            }
        }
        Iterator<zzwx> it = list.iterator();
        while (it.hasNext()) {
            zzwx next = it.next();
            String valueOf = String.valueOf(next.zzbrs);
            zzakb.zzdj(valueOf.length() != 0 ? "Trying mediation network: ".concat(valueOf) : new String("Trying mediation network: "));
            for (Iterator<String> it2 = next.zzbrt.iterator(); it2.hasNext(); it2 = it2) {
                Context context = this.mContext;
                zzxn zzxn = this.zzwh;
                zzwy zzwy = this.zzbtj;
                zzjj zzjj = this.zzbuc.zzccv;
                zzang zzang = this.zzbuc.zzacr;
                boolean z = this.zzael;
                boolean z2 = this.zzbtn;
                Iterator<zzwx> it3 = it;
                ArrayList arrayList2 = arrayList;
                boolean z3 = z2;
                boolean z4 = z;
                zzxb zzxb = new zzxb(context, it2.next(), zzxn, zzwy, next, zzjj, zzjn, zzang, z4, z3, this.zzbuc.zzadj, this.zzbuc.zzads, this.zzbuc.zzcdk, this.zzbuc.zzcef, this.zzbto);
                zzanz zza = zzaki.zza(new zzxi(this, zzxb));
                this.zzbug.put(zza, zzxb);
                ArrayList arrayList3 = arrayList2;
                arrayList3.add(zza);
                it = it3;
                arrayList = arrayList3;
            }
        }
        ArrayList arrayList4 = arrayList;
        return this.zzbue != 2 ? zzi(arrayList4) : zzj(arrayList4);
    }

    public final List<zzxe> zzme() {
        return this.zzbui;
    }
}
