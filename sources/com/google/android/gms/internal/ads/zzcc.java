package com.google.android.gms.internal.ads;

import android.os.Build;
import android.os.ConditionVariable;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class zzcc {
    /* access modifiers changed from: private */
    public static final ConditionVariable zzpt = new ConditionVariable();
    protected static volatile zzhx zzpu = null;
    private static volatile Random zzpw = null;
    /* access modifiers changed from: private */
    public zzcz zzps;
    protected volatile Boolean zzpv;

    public zzcc(zzcz zzcz) {
        this.zzps = zzcz;
        zzcz.zzab().execute(new zzcd(this));
    }

    public static int zzx() {
        try {
            return Build.VERSION.SDK_INT >= 21 ? ThreadLocalRandom.current().nextInt() : zzy().nextInt();
        } catch (RuntimeException unused) {
            return zzy().nextInt();
        }
    }

    private static Random zzy() {
        if (zzpw == null) {
            synchronized (zzcc.class) {
                if (zzpw == null) {
                    zzpw = new Random();
                }
            }
        }
        return zzpw;
    }

    public final void zza(int i, int i2, long j) throws IOException {
        try {
            zzpt.block();
            if (this.zzpv.booleanValue() && zzpu != null) {
                zzaw zzaw = new zzaw();
                zzaw.zzco = this.zzps.zzrt.getPackageName();
                zzaw.zzcp = Long.valueOf(j);
                zzhz zzd = zzpu.zzd(zzbfi.zzb(zzaw));
                zzd.zzr(i2);
                zzd.zzs(i);
                zzd.zzbd();
            }
        } catch (Exception unused) {
        }
    }
}
