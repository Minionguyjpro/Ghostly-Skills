package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
final class zza implements Runnable {
    private final /* synthetic */ LifecycleCallback zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzb zzc;

    zza(zzb zzb2, LifecycleCallback lifecycleCallback, String str) {
        this.zzc = zzb2;
        this.zza = lifecycleCallback;
        this.zzb = str;
    }

    public final void run() {
        if (this.zzc.zzc > 0) {
            this.zza.onCreate(this.zzc.zzd != null ? this.zzc.zzd.getBundle(this.zzb) : null);
        }
        if (this.zzc.zzc >= 2) {
            this.zza.onStart();
        }
        if (this.zzc.zzc >= 3) {
            this.zza.onResume();
        }
        if (this.zzc.zzc >= 4) {
            this.zza.onStop();
        }
        if (this.zzc.zzc >= 5) {
            this.zza.onDestroy();
        }
    }
}
