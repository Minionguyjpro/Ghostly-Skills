package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class zzch extends zzcg {
    private zzch(Context context, String str, boolean z) {
        super(context, str, z);
    }

    public static zzch zza(String str, Context context, boolean z) {
        zza(context, z);
        return new zzch(context, str, z);
    }

    /* access modifiers changed from: protected */
    public final List<Callable<Void>> zza(zzcz zzcz, zzba zzba, zzax zzax) {
        if (zzcz.zzab() == null || !this.zzqt) {
            return super.zza(zzcz, zzba, zzax);
        }
        int zzx = zzcz.zzx();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(super.zza(zzcz, zzba, zzax));
        arrayList.add(new zzds(zzcz, "1QeH3Cf7T53ayw17Ebbo9YTdhU+IFx0X5nCtC5gZQym4uicOVPXxYWmMK9k58i8n", "bHJRpFJ+2R5LAbYQUBDMyfYpLd1oiGixlpIqMJOBQPY=", zzba, zzx, 24));
        return arrayList;
    }
}
