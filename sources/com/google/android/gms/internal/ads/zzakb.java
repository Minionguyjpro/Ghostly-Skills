package com.google.android.gms.internal.ads;

import android.util.Log;

@zzadh
public final class zzakb extends zzane {
    public static void v(String str) {
        if (zzqp()) {
            Log.v("Ads", str);
        }
    }

    public static boolean zzqp() {
        if (!isLoggable(2)) {
            return false;
        }
        return ((Boolean) zzkb.zzik().zzd(zznk.zzazr)).booleanValue();
    }
}
