package com.google.android.gms.internal.ads;

@zzadh
public final class zzaco implements zzacd<zzoq> {
    private final boolean zzbto;
    private final boolean zzcbk;
    private final boolean zzcbl;

    public zzaco(boolean z, boolean z2, boolean z3) {
        this.zzcbk = z;
        this.zzcbl = z2;
        this.zzbto = z3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.gms.internal.ads.zzpb zza(com.google.android.gms.internal.ads.zzabv r19, org.json.JSONObject r20) throws org.json.JSONException, java.lang.InterruptedException, java.util.concurrent.ExecutionException {
        /*
            r18 = this;
            r0 = r18
            r7 = r19
            r8 = r20
            boolean r5 = r0.zzcbk
            boolean r6 = r0.zzcbl
            java.lang.String r3 = "images"
            r4 = 0
            r1 = r19
            r2 = r20
            java.util.List r1 = r1.zza(r2, r3, r4, r5, r6)
            boolean r2 = r0.zzcbk
            java.lang.String r3 = "secondary_image"
            com.google.android.gms.internal.ads.zzanz r2 = r7.zza(r8, r3, r4, r2)
            com.google.android.gms.internal.ads.zzanz r3 = r19.zzg(r20)
            java.lang.String r4 = "video"
            com.google.android.gms.internal.ads.zzanz r4 = r7.zzc((org.json.JSONObject) r8, (java.lang.String) r4)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0030:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x0046
            java.lang.Object r5 = r1.next()
            com.google.android.gms.internal.ads.zzanz r5 = (com.google.android.gms.internal.ads.zzanz) r5
            java.lang.Object r5 = r5.get()
            com.google.android.gms.internal.ads.zzon r5 = (com.google.android.gms.internal.ads.zzon) r5
            r7.add(r5)
            goto L_0x0030
        L_0x0046:
            com.google.android.gms.internal.ads.zzaqw r1 = com.google.android.gms.internal.ads.zzabv.zzc(r4)
            com.google.android.gms.internal.ads.zzoq r4 = new com.google.android.gms.internal.ads.zzoq
            java.lang.String r5 = "headline"
            java.lang.String r5 = r8.getString(r5)
            boolean r6 = r0.zzbto
            if (r6 == 0) goto L_0x00a4
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r6 = com.google.android.gms.internal.ads.zznk.zzbfr
            com.google.android.gms.internal.ads.zzni r9 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r6 = r9.zzd(r6)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x00a4
            com.google.android.gms.internal.ads.zzajm r6 = com.google.android.gms.ads.internal.zzbv.zzeo()
            android.content.res.Resources r6 = r6.getResources()
            if (r6 == 0) goto L_0x0079
            int r9 = com.google.android.gms.ads.impl.R.string.s7
            java.lang.String r6 = r6.getString(r9)
            goto L_0x007b
        L_0x0079:
            java.lang.String r6 = "Test Ad"
        L_0x007b:
            if (r5 == 0) goto L_0x00a5
            java.lang.String r9 = java.lang.String.valueOf(r6)
            int r9 = r9.length()
            int r9 = r9 + 3
            java.lang.String r10 = java.lang.String.valueOf(r5)
            int r10 = r10.length()
            int r9 = r9 + r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r9)
            r10.append(r6)
            java.lang.String r6 = " : "
            r10.append(r6)
            r10.append(r5)
            java.lang.String r5 = r10.toString()
        L_0x00a4:
            r6 = r5
        L_0x00a5:
            java.lang.String r5 = "body"
            java.lang.String r9 = r8.getString(r5)
            java.lang.Object r2 = r2.get()
            com.google.android.gms.internal.ads.zzpw r2 = (com.google.android.gms.internal.ads.zzpw) r2
            java.lang.String r5 = "call_to_action"
            java.lang.String r10 = r8.getString(r5)
            java.lang.String r5 = "advertiser"
            java.lang.String r11 = r8.getString(r5)
            java.lang.Object r3 = r3.get()
            r12 = r3
            com.google.android.gms.internal.ads.zzoj r12 = (com.google.android.gms.internal.ads.zzoj) r12
            android.os.Bundle r13 = new android.os.Bundle
            r13.<init>()
            r3 = 0
            if (r1 == 0) goto L_0x00d2
            com.google.android.gms.internal.ads.zzarl r5 = r1.zztm()
            r14 = r5
            goto L_0x00d3
        L_0x00d2:
            r14 = r3
        L_0x00d3:
            if (r1 == 0) goto L_0x00db
            android.view.View r1 = r1.getView()
            r15 = r1
            goto L_0x00dc
        L_0x00db:
            r15 = r3
        L_0x00dc:
            r16 = 0
            r17 = 0
            r5 = r4
            r8 = r9
            r9 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaco.zza(com.google.android.gms.internal.ads.zzabv, org.json.JSONObject):com.google.android.gms.internal.ads.zzpb");
    }
}
