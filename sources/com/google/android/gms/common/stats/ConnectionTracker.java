package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.internal.common.zzn;
import com.google.android.gms.internal.common.zzo;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public class ConnectionTracker {
    private static final Object zza = new Object();
    private static volatile ConnectionTracker zzb;
    private static boolean zzc = false;
    private static zzo<Boolean> zzd = zzn.zza(zza.zza);
    private ConcurrentHashMap<ServiceConnection, ServiceConnection> zze = new ConcurrentHashMap<>();

    public static ConnectionTracker getInstance() {
        if (zzb == null) {
            synchronized (zza) {
                if (zzb == null) {
                    zzb = new ConnectionTracker();
                }
            }
        }
        return zzb;
    }

    static final /* synthetic */ boolean zza() {
        return false;
    }

    private ConnectionTracker() {
    }

    public final boolean zza(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i) {
        return zza(context, str, intent, serviceConnection, i, true);
    }

    private final boolean zza(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i, boolean z) {
        boolean z2;
        ComponentName component = intent.getComponent();
        if (component == null) {
            z2 = false;
        } else {
            z2 = ClientLibraryUtils.zza(context, component.getPackageName());
        }
        if (z2) {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            return false;
        } else if (!zza(serviceConnection)) {
            return context.bindService(intent, serviceConnection, i);
        } else {
            ServiceConnection putIfAbsent = this.zze.putIfAbsent(serviceConnection, serviceConnection);
            if (!(putIfAbsent == null || serviceConnection == putIfAbsent)) {
                Log.w("ConnectionTracker", String.format("Duplicate binding with the same ServiceConnection: %s, %s, %s.", new Object[]{serviceConnection, str, intent.getAction()}));
            }
            try {
                boolean bindService = context.bindService(intent, serviceConnection, i);
                return !bindService ? bindService : bindService;
            } finally {
                this.zze.remove(serviceConnection, serviceConnection);
            }
        }
    }

    public boolean bindService(Context context, Intent intent, ServiceConnection serviceConnection, int i) {
        return zza(context, context.getClass().getName(), intent, serviceConnection, i);
    }

    public void unbindService(Context context, ServiceConnection serviceConnection) {
        if (!zza(serviceConnection) || !this.zze.containsKey(serviceConnection)) {
            zza(context, serviceConnection);
            return;
        }
        try {
            zza(context, this.zze.get(serviceConnection));
        } finally {
            this.zze.remove(serviceConnection);
        }
    }

    private static boolean zza(ServiceConnection serviceConnection) {
        return zzd.zza().booleanValue() && !(serviceConnection instanceof zzj);
    }

    private static void zza(Context context, ServiceConnection serviceConnection) {
        try {
            context.unbindService(serviceConnection);
        } catch (IllegalArgumentException | IllegalStateException e) {
            Log.w("ConnectionTracker", "Exception thrown while unbinding", e);
        }
    }

    public void unbindServiceSafe(Context context, ServiceConnection serviceConnection) {
        try {
            unbindService(context, serviceConnection);
        } catch (IllegalArgumentException e) {
            Log.w("ConnectionTracker", "Exception thrown while unbinding", e);
        }
    }
}
