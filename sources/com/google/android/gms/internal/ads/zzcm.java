package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzcm extends zzbh<Integer, Long> {
    public long zzri;
    public long zzrj;

    public zzcm() {
        this.zzri = -1;
        this.zzrj = -1;
    }

    public zzcm(String str) {
        this();
        zzj(str);
    }

    /* access modifiers changed from: protected */
    public final void zzj(String str) {
        HashMap zzk = zzk(str);
        if (zzk != null) {
            this.zzri = ((Long) zzk.get(0)).longValue();
            this.zzrj = ((Long) zzk.get(1)).longValue();
        }
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Long> zzu() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, Long.valueOf(this.zzri));
        hashMap.put(1, Long.valueOf(this.zzrj));
        return hashMap;
    }
}
