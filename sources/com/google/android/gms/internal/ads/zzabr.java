package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzadh
public final class zzabr extends zzabh {
    /* access modifiers changed from: private */
    public final zzaqw zzbnd;
    private zzwy zzbtj;
    private zzww zzbzq;
    protected zzxe zzbzr;
    /* access modifiers changed from: private */
    public boolean zzbzs;
    private final zznx zzvr;
    private zzxn zzwh;

    zzabr(Context context, zzaji zzaji, zzxn zzxn, zzabm zzabm, zznx zznx, zzaqw zzaqw) {
        super(context, zzaji, zzabm);
        this.zzwh = zzxn;
        this.zzbtj = zzaji.zzcod;
        this.zzvr = zznx;
        this.zzbnd = zzaqw;
    }

    public final void onStop() {
        synchronized (this.zzbzh) {
            super.onStop();
            if (this.zzbzq != null) {
                this.zzbzq.cancel();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final zzajh zzaa(int i) {
        String str;
        boolean z;
        zzwy zzwy;
        long j;
        zzael zzael;
        String str2;
        String str3;
        boolean z2;
        zzwy zzwy2;
        long j2;
        Iterator<zzxe> it;
        String str4;
        int i2;
        zzaef zzaef = this.zzbze.zzcgs;
        zzjj zzjj = zzaef.zzccv;
        zzaqw zzaqw = this.zzbnd;
        List<String> list = this.zzbzf.zzbsn;
        List<String> list2 = this.zzbzf.zzbso;
        List<String> list3 = this.zzbzf.zzces;
        int i3 = this.zzbzf.orientation;
        long j3 = this.zzbzf.zzbsu;
        String str5 = zzaef.zzccy;
        boolean z3 = this.zzbzf.zzceq;
        zzxe zzxe = this.zzbzr;
        zzwx zzwx = zzxe != null ? zzxe.zzbtw : null;
        zzxe zzxe2 = this.zzbzr;
        zzxq zzxq = zzxe2 != null ? zzxe2.zzbtx : null;
        zzxe zzxe3 = this.zzbzr;
        String name = zzxe3 != null ? zzxe3.zzbty : AdMobAdapter.class.getName();
        zzwy zzwy3 = this.zzbtj;
        zzxe zzxe4 = this.zzbzr;
        zzxa zzxa = zzxe4 != null ? zzxe4.zzbtz : null;
        zzwx zzwx2 = zzwx;
        zzxq zzxq2 = zzxq;
        long j4 = this.zzbzf.zzcer;
        zzjn zzjn = this.zzbze.zzacv;
        long j5 = j4;
        long j6 = this.zzbzf.zzcep;
        long j7 = this.zzbze.zzcoh;
        long j8 = this.zzbzf.zzceu;
        String str6 = this.zzbzf.zzcev;
        JSONObject jSONObject = this.zzbze.zzcob;
        zzaig zzaig = this.zzbzf.zzcfe;
        List<String> list4 = this.zzbzf.zzcff;
        List<String> list5 = this.zzbzf.zzcfg;
        zzwy zzwy4 = this.zzbtj;
        zzjn zzjn2 = zzjn;
        boolean z4 = zzwy4 != null ? zzwy4.zzbsz : false;
        zzael zzael2 = this.zzbzf.zzcfi;
        zzww zzww = this.zzbzq;
        if (zzww != null) {
            List<zzxe> zzme = zzww.zzme();
            if (zzme == null) {
                zzwy = zzwy3;
                zzael = zzael2;
                str = str5;
                z = z3;
                j = j8;
                str2 = "";
            } else {
                Iterator<zzxe> it2 = zzme.iterator();
                String str7 = "";
                while (true) {
                    zzael = zzael2;
                    if (!it2.hasNext()) {
                        break;
                    }
                    zzxe next = it2.next();
                    if (next != null) {
                        it = it2;
                        if (next.zzbtw == null || TextUtils.isEmpty(next.zzbtw.zzbru)) {
                            zzwy2 = zzwy3;
                        } else {
                            String valueOf = String.valueOf(str7);
                            j2 = j8;
                            String str8 = next.zzbtw.zzbru;
                            int i4 = next.zzbtv;
                            zzwy2 = zzwy3;
                            if (i4 != -1) {
                                if (i4 == 0) {
                                    str4 = str5;
                                    z2 = z3;
                                    i2 = 0;
                                } else if (i4 == 1) {
                                    str4 = str5;
                                    z2 = z3;
                                    i2 = 1;
                                } else if (i4 == 3) {
                                    i2 = 2;
                                } else if (i4 != 4) {
                                    i2 = 5;
                                    if (i4 != 5) {
                                        i2 = 6;
                                    }
                                } else {
                                    str4 = str5;
                                    z2 = z3;
                                    i2 = 3;
                                }
                                long j9 = next.zzbub;
                                str3 = str4;
                                StringBuilder sb = new StringBuilder(String.valueOf(str8).length() + 33);
                                sb.append(str8);
                                sb.append(".");
                                sb.append(i2);
                                sb.append(".");
                                sb.append(j9);
                                String sb2 = sb.toString();
                                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(sb2).length());
                                sb3.append(valueOf);
                                sb3.append(sb2);
                                sb3.append("_");
                                str7 = sb3.toString();
                                it2 = it;
                                zzael2 = zzael;
                                j8 = j2;
                                zzwy3 = zzwy2;
                                z3 = z2;
                                str5 = str3;
                            } else {
                                i2 = 4;
                            }
                            str4 = str5;
                            z2 = z3;
                            long j92 = next.zzbub;
                            str3 = str4;
                            StringBuilder sb4 = new StringBuilder(String.valueOf(str8).length() + 33);
                            sb4.append(str8);
                            sb4.append(".");
                            sb4.append(i2);
                            sb4.append(".");
                            sb4.append(j92);
                            String sb22 = sb4.toString();
                            StringBuilder sb32 = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(sb22).length());
                            sb32.append(valueOf);
                            sb32.append(sb22);
                            sb32.append("_");
                            str7 = sb32.toString();
                            it2 = it;
                            zzael2 = zzael;
                            j8 = j2;
                            zzwy3 = zzwy2;
                            z3 = z2;
                            str5 = str3;
                        }
                    } else {
                        zzwy2 = zzwy3;
                        it = it2;
                    }
                    str3 = str5;
                    z2 = z3;
                    j2 = j8;
                    it2 = it;
                    zzael2 = zzael;
                    j8 = j2;
                    zzwy3 = zzwy2;
                    z3 = z2;
                    str5 = str3;
                }
                zzwy = zzwy3;
                str = str5;
                z = z3;
                j = j8;
                str2 = str7.substring(0, Math.max(0, str7.length() - 1));
            }
        } else {
            zzwy = zzwy3;
            zzael = zzael2;
            str = str5;
            z = z3;
            j = j8;
            str2 = null;
        }
        return new zzajh(zzjj, zzaqw, list, i, list2, list3, i3, j3, str, z, zzwx2, zzxq2, name, zzwy, zzxa, j5, zzjn2, j6, j7, j, str6, jSONObject, (zzpb) null, zzaig, list4, list5, z4, zzael, str2, this.zzbzf.zzbsr, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbzf.zzzl, this.zzbze.zzcor, this.zzbzf.zzcfp, this.zzbzf.zzbsp, this.zzbzf.zzzm, this.zzbzf.zzcfq);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.internal.ads.zzww] */
    /* JADX WARNING: type inference failed for: r17v2, types: [com.google.android.gms.internal.ads.zzxk] */
    /* JADX WARNING: type inference failed for: r4v5, types: [com.google.android.gms.internal.ads.zzxh] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x00a3, code lost:
        r2 = r2.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(long r32) throws com.google.android.gms.internal.ads.zzabk {
        /*
            r31 = this;
            r1 = r31
            java.lang.Object r2 = r1.zzbzh
            monitor-enter(r2)
            com.google.android.gms.internal.ads.zzwy r0 = r1.zzbtj     // Catch:{ all -> 0x0173 }
            int r0 = r0.zzbsx     // Catch:{ all -> 0x0173 }
            r3 = -1
            if (r0 == r3) goto L_0x0043
            com.google.android.gms.internal.ads.zzxh r0 = new com.google.android.gms.internal.ads.zzxh     // Catch:{ all -> 0x0173 }
            android.content.Context r5 = r1.mContext     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaji r3 = r1.zzbze     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaef r6 = r3.zzcgs     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzxn r7 = r1.zzwh     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzwy r8 = r1.zzbtj     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaej r3 = r1.zzbzf     // Catch:{ all -> 0x0173 }
            boolean r9 = r3.zzare     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaej r3 = r1.zzbzf     // Catch:{ all -> 0x0173 }
            boolean r10 = r3.zzarg     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaej r3 = r1.zzbzf     // Catch:{ all -> 0x0173 }
            java.lang.String r11 = r3.zzcfj     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzna<java.lang.Long> r3 = com.google.android.gms.internal.ads.zznk.zzbao     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzni r4 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0173 }
            java.lang.Object r3 = r4.zzd(r3)     // Catch:{ all -> 0x0173 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x0173 }
            long r14 = r3.longValue()     // Catch:{ all -> 0x0173 }
            r16 = 2
            com.google.android.gms.internal.ads.zzaji r3 = r1.zzbze     // Catch:{ all -> 0x0173 }
            boolean r3 = r3.zzcor     // Catch:{ all -> 0x0173 }
            r4 = r0
            r12 = r32
            r17 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r14, r16, r17)     // Catch:{ all -> 0x0173 }
            goto L_0x008a
        L_0x0043:
            com.google.android.gms.internal.ads.zzxk r0 = new com.google.android.gms.internal.ads.zzxk     // Catch:{ all -> 0x0173 }
            android.content.Context r3 = r1.mContext     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaji r4 = r1.zzbze     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaef r4 = r4.zzcgs     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzxn r5 = r1.zzwh     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzwy r6 = r1.zzbtj     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaej r7 = r1.zzbzf     // Catch:{ all -> 0x0173 }
            boolean r7 = r7.zzare     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaej r8 = r1.zzbzf     // Catch:{ all -> 0x0173 }
            boolean r8 = r8.zzarg     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaej r9 = r1.zzbzf     // Catch:{ all -> 0x0173 }
            java.lang.String r9 = r9.zzcfj     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzna<java.lang.Long> r10 = com.google.android.gms.internal.ads.zznk.zzbao     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzni r11 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0173 }
            java.lang.Object r10 = r11.zzd(r10)     // Catch:{ all -> 0x0173 }
            java.lang.Long r10 = (java.lang.Long) r10     // Catch:{ all -> 0x0173 }
            long r27 = r10.longValue()     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zznx r10 = r1.zzvr     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.ads.zzaji r11 = r1.zzbze     // Catch:{ all -> 0x0173 }
            boolean r11 = r11.zzcor     // Catch:{ all -> 0x0173 }
            r17 = r0
            r18 = r3
            r19 = r4
            r20 = r5
            r21 = r6
            r22 = r7
            r23 = r8
            r24 = r9
            r25 = r32
            r29 = r10
            r30 = r11
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r27, r29, r30)     // Catch:{ all -> 0x0173 }
        L_0x008a:
            r1.zzbzq = r0     // Catch:{ all -> 0x0173 }
            monitor-exit(r2)     // Catch:{ all -> 0x0173 }
            java.util.ArrayList r0 = new java.util.ArrayList
            com.google.android.gms.internal.ads.zzwy r2 = r1.zzbtj
            java.util.List<com.google.android.gms.internal.ads.zzwx> r2 = r2.zzbsm
            r0.<init>(r2)
            com.google.android.gms.internal.ads.zzaji r2 = r1.zzbze
            com.google.android.gms.internal.ads.zzaef r2 = r2.zzcgs
            com.google.android.gms.internal.ads.zzjj r2 = r2.zzccv
            android.os.Bundle r2 = r2.zzaqg
            java.lang.String r3 = "com.google.ads.mediation.admob.AdMobAdapter"
            r4 = 0
            if (r2 == 0) goto L_0x00b0
            android.os.Bundle r2 = r2.getBundle(r3)
            if (r2 == 0) goto L_0x00b0
            java.lang.String r5 = "_skipMediation"
            boolean r2 = r2.getBoolean(r5)
            goto L_0x00b1
        L_0x00b0:
            r2 = 0
        L_0x00b1:
            if (r2 == 0) goto L_0x00cf
            java.util.ListIterator r2 = r0.listIterator()
        L_0x00b7:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x00cf
            java.lang.Object r5 = r2.next()
            com.google.android.gms.internal.ads.zzwx r5 = (com.google.android.gms.internal.ads.zzwx) r5
            java.util.List<java.lang.String> r5 = r5.zzbrt
            boolean r5 = r5.contains(r3)
            if (r5 != 0) goto L_0x00b7
            r2.remove()
            goto L_0x00b7
        L_0x00cf:
            com.google.android.gms.internal.ads.zzww r2 = r1.zzbzq
            com.google.android.gms.internal.ads.zzxe r0 = r2.zzh(r0)
            r1.zzbzr = r0
            int r0 = r0.zzbtv
            r2 = 1
            if (r0 == 0) goto L_0x0104
            if (r0 == r2) goto L_0x00fb
            com.google.android.gms.internal.ads.zzabk r0 = new com.google.android.gms.internal.ads.zzabk
            com.google.android.gms.internal.ads.zzxe r2 = r1.zzbzr
            int r2 = r2.zzbtv
            r3 = 40
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            java.lang.String r3 = "Unexpected mediation result: "
            r5.append(r3)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r0.<init>(r2, r4)
            throw r0
        L_0x00fb:
            com.google.android.gms.internal.ads.zzabk r0 = new com.google.android.gms.internal.ads.zzabk
            java.lang.String r2 = "No fill from any mediation ad networks."
            r3 = 3
            r0.<init>(r2, r3)
            throw r0
        L_0x0104:
            com.google.android.gms.internal.ads.zzxe r0 = r1.zzbzr
            com.google.android.gms.internal.ads.zzwx r0 = r0.zzbtw
            if (r0 == 0) goto L_0x0172
            com.google.android.gms.internal.ads.zzxe r0 = r1.zzbzr
            com.google.android.gms.internal.ads.zzwx r0 = r0.zzbtw
            java.lang.String r0 = r0.zzbsf
            if (r0 == 0) goto L_0x0172
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch
            r0.<init>(r2)
            android.os.Handler r2 = com.google.android.gms.internal.ads.zzakk.zzcrm
            com.google.android.gms.internal.ads.zzabs r3 = new com.google.android.gms.internal.ads.zzabs
            r3.<init>(r1, r0)
            r2.post(r3)
            r2 = 10
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x014c }
            r0.await(r2, r5)     // Catch:{ InterruptedException -> 0x014c }
            java.lang.Object r2 = r1.zzbzh
            monitor-enter(r2)
            boolean r0 = r1.zzbzs     // Catch:{ all -> 0x0149 }
            if (r0 == 0) goto L_0x0141
            com.google.android.gms.internal.ads.zzaqw r0 = r1.zzbnd     // Catch:{ all -> 0x0149 }
            boolean r0 = r0.isDestroyed()     // Catch:{ all -> 0x0149 }
            if (r0 != 0) goto L_0x0139
            monitor-exit(r2)     // Catch:{ all -> 0x0149 }
            return
        L_0x0139:
            com.google.android.gms.internal.ads.zzabk r0 = new com.google.android.gms.internal.ads.zzabk     // Catch:{ all -> 0x0149 }
            java.lang.String r3 = "Assets not loaded, web view is destroyed"
            r0.<init>(r3, r4)     // Catch:{ all -> 0x0149 }
            throw r0     // Catch:{ all -> 0x0149 }
        L_0x0141:
            com.google.android.gms.internal.ads.zzabk r0 = new com.google.android.gms.internal.ads.zzabk     // Catch:{ all -> 0x0149 }
            java.lang.String r3 = "View could not be prepared"
            r0.<init>(r3, r4)     // Catch:{ all -> 0x0149 }
            throw r0     // Catch:{ all -> 0x0149 }
        L_0x0149:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0149 }
            throw r0
        L_0x014c:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzabk r2 = new com.google.android.gms.internal.ads.zzabk
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r3 = java.lang.String.valueOf(r0)
            int r3 = r3.length()
            int r3 = r3 + 38
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            java.lang.String r3 = "Interrupted while waiting for latch : "
            r5.append(r3)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r2.<init>(r0, r4)
            throw r2
        L_0x0172:
            return
        L_0x0173:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0173 }
            goto L_0x0177
        L_0x0176:
            throw r0
        L_0x0177:
            goto L_0x0176
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabr.zze(long):void");
    }
}
