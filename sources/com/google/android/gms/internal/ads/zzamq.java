package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Map;
import java.util.WeakHashMap;

@zzadh
public final class zzamq {
    private final BroadcastReceiver zzcuc = new zzamr(this);
    private final Map<BroadcastReceiver, IntentFilter> zzcud = new WeakHashMap();
    private boolean zzcue;
    private boolean zzsh = false;
    private Context zzsz;

    /* access modifiers changed from: private */
    public final synchronized void zzc(Context context, Intent intent) {
        for (Map.Entry next : this.zzcud.entrySet()) {
            if (((IntentFilter) next.getValue()).hasAction(intent.getAction())) {
                ((BroadcastReceiver) next.getKey()).onReceive(context, intent);
            }
        }
    }

    public final synchronized void initialize(Context context) {
        if (!this.zzsh) {
            Context applicationContext = context.getApplicationContext();
            this.zzsz = applicationContext;
            if (applicationContext == null) {
                this.zzsz = context;
            }
            zznk.initialize(this.zzsz);
            this.zzcue = ((Boolean) zzkb.zzik().zzd(zznk.zzbcc)).booleanValue();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzsz.registerReceiver(this.zzcuc, intentFilter);
            this.zzsh = true;
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver) {
        if (this.zzcue) {
            this.zzcud.remove(broadcastReceiver);
        } else {
            context.unregisterReceiver(broadcastReceiver);
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (this.zzcue) {
            this.zzcud.put(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }
}
