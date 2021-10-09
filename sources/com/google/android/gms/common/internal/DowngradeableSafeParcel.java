package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zza = new Object();
    private static ClassLoader zzb = null;
    private static Integer zzc = null;
    private boolean zzd = false;

    /* access modifiers changed from: protected */
    public abstract boolean prepareForClientVersion(int i);

    private static ClassLoader zza() {
        synchronized (zza) {
        }
        return null;
    }

    protected static Integer getUnparcelClientVersion() {
        synchronized (zza) {
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean shouldDowngrade() {
        return this.zzd;
    }

    public void setShouldDowngrade(boolean z) {
        this.zzd = z;
    }

    protected static boolean canUnparcelSafely(String str) {
        zza();
        return true;
    }
}
