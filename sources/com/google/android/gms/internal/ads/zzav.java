package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;

public class zzav extends zzr<String> {
    private final Object mLock = new Object();
    private zzz<String> zzck;

    public zzav(int i, String str, zzz<String> zzz, zzy zzy) {
        super(i, str, zzy);
        this.zzck = zzz;
    }

    /* access modifiers changed from: protected */
    public final zzx<String> zza(zzp zzp) {
        String str;
        try {
            byte[] bArr = zzp.data;
            String str2 = "ISO-8859-1";
            String str3 = zzp.zzab.get("Content-Type");
            if (str3 != null) {
                String[] split = str3.split(";");
                int i = 1;
                while (true) {
                    if (i >= split.length) {
                        break;
                    }
                    String[] split2 = split[i].trim().split("=");
                    if (split2.length == 2 && split2[0].equals("charset")) {
                        str2 = split2[1];
                        break;
                    }
                    i++;
                }
            }
            str = new String(bArr, str2);
        } catch (UnsupportedEncodingException unused) {
            str = new String(zzp.data);
        }
        return zzx.zza(str, zzap.zzb(zzp));
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzh */
    public void zza(String str) {
        zzz<String> zzz;
        synchronized (this.mLock) {
            zzz = this.zzck;
        }
        if (zzz != null) {
            zzz.zzb(str);
        }
    }
}
