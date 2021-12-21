package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

@zzadh
public final class zzaql implements zzv<zzapw> {
    private static Integer zze(Map<String, String> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(map.get(str)));
        } catch (NumberFormatException unused) {
            String str2 = map.get(str);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39 + String.valueOf(str2).length());
            sb.append("Precache invalid numeric parameter '");
            sb.append(str);
            sb.append("': ");
            sb.append(str2);
            zzakb.zzdk(sb.toString());
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(java.lang.Object r6, java.util.Map r7) {
        /*
            r5 = this;
            com.google.android.gms.internal.ads.zzapw r6 = (com.google.android.gms.internal.ads.zzapw) r6
            com.google.android.gms.ads.internal.zzbv.zzff()
            java.lang.String r0 = "abort"
            boolean r0 = r7.containsKey(r0)
            if (r0 == 0) goto L_0x0019
            boolean r6 = com.google.android.gms.internal.ads.zzaqg.zzb((com.google.android.gms.internal.ads.zzapw) r6)
            if (r6 != 0) goto L_0x0018
            java.lang.String r6 = "Precache abort but no precache task running."
            com.google.android.gms.internal.ads.zzakb.zzdk(r6)
        L_0x0018:
            return
        L_0x0019:
            java.lang.String r0 = "src"
            java.lang.Object r0 = r7.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L_0x006a
            com.google.android.gms.internal.ads.zzaqe r1 = com.google.android.gms.internal.ads.zzaqg.zzc(r6)
            if (r1 == 0) goto L_0x002f
            java.lang.String r6 = "Precache task is already running."
        L_0x002b:
            com.google.android.gms.internal.ads.zzakb.zzdk(r6)
            return
        L_0x002f:
            com.google.android.gms.ads.internal.zzw r1 = r6.zzbi()
            if (r1 != 0) goto L_0x0038
            java.lang.String r6 = "Precache requires a dependency provider."
            goto L_0x002b
        L_0x0038:
            com.google.android.gms.internal.ads.zzapv r1 = new com.google.android.gms.internal.ads.zzapv
            java.lang.String r2 = "flags"
            java.lang.Object r2 = r7.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            r1.<init>(r2)
            java.lang.String r2 = "player"
            java.lang.Integer r2 = zze(r7, r2)
            if (r2 != 0) goto L_0x0052
            r2 = 0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
        L_0x0052:
            int r2 = r2.intValue()
            com.google.android.gms.ads.internal.zzw r3 = r6.zzbi()
            com.google.android.gms.internal.ads.zzaqm r3 = r3.zzwy
            r4 = 0
            com.google.android.gms.internal.ads.zzaqh r1 = r3.zza(r6, r2, r4, r1)
            com.google.android.gms.internal.ads.zzaqe r2 = new com.google.android.gms.internal.ads.zzaqe
            r2.<init>(r6, r1, r0)
            r2.zznt()
            goto L_0x0073
        L_0x006a:
            com.google.android.gms.internal.ads.zzaqe r6 = com.google.android.gms.internal.ads.zzaqg.zzc(r6)
            if (r6 != 0) goto L_0x0073
            java.lang.String r6 = "Precache must specify a source."
            goto L_0x002b
        L_0x0073:
            java.lang.String r6 = "minBufferMs"
            java.lang.Integer r6 = zze(r7, r6)
            if (r6 == 0) goto L_0x007e
            r6.intValue()
        L_0x007e:
            java.lang.String r6 = "maxBufferMs"
            java.lang.Integer r6 = zze(r7, r6)
            if (r6 == 0) goto L_0x0089
            r6.intValue()
        L_0x0089:
            java.lang.String r6 = "bufferForPlaybackMs"
            java.lang.Integer r6 = zze(r7, r6)
            if (r6 == 0) goto L_0x0094
            r6.intValue()
        L_0x0094:
            java.lang.String r6 = "bufferForPlaybackAfterRebufferMs"
            java.lang.Integer r6 = zze(r7, r6)
            if (r6 == 0) goto L_0x009f
            r6.intValue()
        L_0x009f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaql.zza(java.lang.Object, java.util.Map):void");
    }
}
