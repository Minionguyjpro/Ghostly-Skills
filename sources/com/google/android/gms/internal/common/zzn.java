package com.google.android.gms.internal.common;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public final class zzn {
    public static <T> zzo<T> zza(zzo<T> zzo) {
        if ((zzo instanceof zzp) || (zzo instanceof zzq)) {
            return zzo;
        }
        if (zzo instanceof Serializable) {
            return new zzq(zzo);
        }
        return new zzp(zzo);
    }
}
