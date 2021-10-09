package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import com.mopub.common.Constants;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public abstract class GmsClientSupervisor {
    private static int zza = 129;
    private static final Object zzb = new Object();
    private static GmsClientSupervisor zzc;

    /* access modifiers changed from: protected */
    public abstract boolean zza(zza zza2, ServiceConnection serviceConnection, String str);

    /* access modifiers changed from: protected */
    public abstract void zzb(zza zza2, ServiceConnection serviceConnection, String str);

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    protected static final class zza {
        private static final Uri zzf = new Uri.Builder().scheme(Constants.VAST_TRACKER_CONTENT).authority("com.google.android.gms.chimera").build();
        private final String zza;
        private final String zzb;
        private final ComponentName zzc;
        private final int zzd;
        private final boolean zze;

        public zza(String str, int i) {
            this(str, "com.google.android.gms", i);
        }

        private zza(String str, String str2, int i) {
            this(str, str2, i, false);
        }

        public zza(String str, String str2, int i, boolean z) {
            this.zza = Preconditions.checkNotEmpty(str);
            this.zzb = Preconditions.checkNotEmpty(str2);
            this.zzc = null;
            this.zzd = i;
            this.zze = z;
        }

        public zza(ComponentName componentName, int i) {
            this.zza = null;
            this.zzb = null;
            this.zzc = (ComponentName) Preconditions.checkNotNull(componentName);
            this.zzd = i;
            this.zze = false;
        }

        public final String toString() {
            String str = this.zza;
            if (str != null) {
                return str;
            }
            Preconditions.checkNotNull(this.zzc);
            return this.zzc.flattenToString();
        }

        public final String zza() {
            return this.zzb;
        }

        public final ComponentName zzb() {
            return this.zzc;
        }

        public final int zzc() {
            return this.zzd;
        }

        public final Intent zza(Context context) {
            if (this.zza == null) {
                return new Intent().setComponent(this.zzc);
            }
            Intent zzb2 = this.zze ? zzb(context) : null;
            if (zzb2 == null) {
                return new Intent(this.zza).setPackage(this.zzb);
            }
            return zzb2;
        }

        /* JADX WARNING: type inference failed for: r6v6, types: [android.os.Parcelable] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final android.content.Intent zzb(android.content.Context r6) {
            /*
                r5 = this;
                java.lang.String r0 = "ConnectionStatusConfig"
                android.os.Bundle r1 = new android.os.Bundle
                r1.<init>()
                java.lang.String r2 = r5.zza
                java.lang.String r3 = "serviceActionBundleKey"
                r1.putString(r3, r2)
                r2 = 0
                android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ IllegalArgumentException -> 0x001c }
                android.net.Uri r3 = zzf     // Catch:{ IllegalArgumentException -> 0x001c }
                java.lang.String r4 = "serviceIntentCall"
                android.os.Bundle r6 = r6.call(r3, r4, r2, r1)     // Catch:{ IllegalArgumentException -> 0x001c }
                goto L_0x0040
            L_0x001c:
                r6 = move-exception
                java.lang.String r6 = java.lang.String.valueOf(r6)
                java.lang.String r1 = java.lang.String.valueOf(r6)
                int r1 = r1.length()
                int r1 = r1 + 34
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>(r1)
                java.lang.String r1 = "Dynamic intent resolution failed: "
                r3.append(r1)
                r3.append(r6)
                java.lang.String r6 = r3.toString()
                android.util.Log.w(r0, r6)
                r6 = r2
            L_0x0040:
                if (r6 != 0) goto L_0x0043
                goto L_0x004c
            L_0x0043:
                java.lang.String r1 = "serviceResponseIntentKey"
                android.os.Parcelable r6 = r6.getParcelable(r1)
                r2 = r6
                android.content.Intent r2 = (android.content.Intent) r2
            L_0x004c:
                if (r2 != 0) goto L_0x006a
                java.lang.String r6 = "Dynamic lookup for intent failed for action: "
                java.lang.String r1 = r5.zza
                java.lang.String r1 = java.lang.String.valueOf(r1)
                int r3 = r1.length()
                if (r3 == 0) goto L_0x0061
                java.lang.String r6 = r6.concat(r1)
                goto L_0x0067
            L_0x0061:
                java.lang.String r1 = new java.lang.String
                r1.<init>(r6)
                r6 = r1
            L_0x0067:
                android.util.Log.w(r0, r6)
            L_0x006a:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClientSupervisor.zza.zzb(android.content.Context):android.content.Intent");
        }

        public final int hashCode() {
            return Objects.hashCode(this.zza, this.zzb, this.zzc, Integer.valueOf(this.zzd), Boolean.valueOf(this.zze));
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza2 = (zza) obj;
            return Objects.equal(this.zza, zza2.zza) && Objects.equal(this.zzb, zza2.zzb) && Objects.equal(this.zzc, zza2.zzc) && this.zzd == zza2.zzd && this.zze == zza2.zze;
        }
    }

    public static int getDefaultBindFlags() {
        return zza;
    }

    public static GmsClientSupervisor getInstance(Context context) {
        synchronized (zzb) {
            if (zzc == null) {
                zzc = new zzf(context.getApplicationContext());
            }
        }
        return zzc;
    }

    public boolean bindService(String str, ServiceConnection serviceConnection, String str2) {
        return zza(new zza(str, getDefaultBindFlags()), serviceConnection, str2);
    }

    public boolean bindService(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return zza(new zza(componentName, getDefaultBindFlags()), serviceConnection, str);
    }

    public void unbindService(String str, ServiceConnection serviceConnection, String str2) {
        zzb(new zza(str, getDefaultBindFlags()), serviceConnection, str2);
    }

    public final void zza(String str, String str2, int i, ServiceConnection serviceConnection, String str3, boolean z) {
        zzb(new zza(str, str2, i, z), serviceConnection, str3);
    }

    public void unbindService(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zzb(new zza(componentName, getDefaultBindFlags()), serviceConnection, str);
    }
}
