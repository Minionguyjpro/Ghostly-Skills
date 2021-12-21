package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

final class zzan {
    final String zza;
    final long zzb;
    final long zzc;
    long zzca;
    final String zzcb;
    final long zzd;
    final long zze;
    final List<zzl> zzg;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzan(java.lang.String r16, com.google.android.gms.internal.ads.zzc r17) {
        /*
            r15 = this;
            r0 = r17
            java.lang.String r3 = r0.zza
            long r4 = r0.zzb
            long r6 = r0.zzc
            long r8 = r0.zzd
            long r10 = r0.zze
            java.util.List<com.google.android.gms.internal.ads.zzl> r1 = r0.zzg
            if (r1 == 0) goto L_0x0014
            java.util.List<com.google.android.gms.internal.ads.zzl> r1 = r0.zzg
            r12 = r1
            goto L_0x0049
        L_0x0014:
            java.util.Map<java.lang.String, java.lang.String> r1 = r0.zzf
            java.util.ArrayList r2 = new java.util.ArrayList
            int r12 = r1.size()
            r2.<init>(r12)
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0027:
            boolean r12 = r1.hasNext()
            if (r12 == 0) goto L_0x0048
            java.lang.Object r12 = r1.next()
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12
            com.google.android.gms.internal.ads.zzl r13 = new com.google.android.gms.internal.ads.zzl
            java.lang.Object r14 = r12.getKey()
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r12 = r12.getValue()
            java.lang.String r12 = (java.lang.String) r12
            r13.<init>(r14, r12)
            r2.add(r13)
            goto L_0x0027
        L_0x0048:
            r12 = r2
        L_0x0049:
            r1 = r15
            r2 = r16
            r1.<init>(r2, r3, r4, r6, r8, r10, r12)
            byte[] r0 = r0.data
            int r0 = r0.length
            long r0 = (long) r0
            r2 = r15
            r2.zzca = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzan.<init>(java.lang.String, com.google.android.gms.internal.ads.zzc):void");
    }

    private zzan(String str, String str2, long j, long j2, long j3, long j4, List<zzl> list) {
        this.zzcb = str;
        this.zza = "".equals(str2) ? null : str2;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = j3;
        this.zze = j4;
        this.zzg = list;
    }

    static zzan zzc(zzao zzao) throws IOException {
        if (zzam.zzb((InputStream) zzao) == 538247942) {
            return new zzan(zzam.zza(zzao), zzam.zza(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzb(zzao));
        }
        throw new IOException();
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(OutputStream outputStream) {
        try {
            zzam.zza(outputStream, 538247942);
            zzam.zza(outputStream, this.zzcb);
            zzam.zza(outputStream, this.zza == null ? "" : this.zza);
            zzam.zza(outputStream, this.zzb);
            zzam.zza(outputStream, this.zzc);
            zzam.zza(outputStream, this.zzd);
            zzam.zza(outputStream, this.zze);
            List<zzl> list = this.zzg;
            if (list != null) {
                zzam.zza(outputStream, list.size());
                for (zzl next : list) {
                    zzam.zza(outputStream, next.getName());
                    zzam.zza(outputStream, next.getValue());
                }
            } else {
                zzam.zza(outputStream, 0);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            zzaf.d("%s", e.toString());
            return false;
        }
    }
}
