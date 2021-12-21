package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzce;
import com.google.android.gms.internal.ads.zzch;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zzadh
public final class zzag implements zzce, Runnable {
    private Context zzrt;
    private final List<Object[]> zzxo;
    private final AtomicReference<zzce> zzxp;
    private zzang zzxq;
    private CountDownLatch zzxr;

    private zzag(Context context, zzang zzang) {
        this.zzxo = new Vector();
        this.zzxp = new AtomicReference<>();
        this.zzxr = new CountDownLatch(1);
        this.zzrt = context;
        this.zzxq = zzang;
        zzkb.zzif();
        if (zzamu.zzsh()) {
            zzaki.zzb(this);
        } else {
            run();
        }
    }

    public zzag(zzbw zzbw) {
        this(zzbw.zzrt, zzbw.zzacr);
    }

    private static Context zzd(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    private final boolean zzdc() {
        try {
            this.zzxr.await();
            return true;
        } catch (InterruptedException e) {
            zzakb.zzc("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    private final void zzdd() {
        if (!this.zzxo.isEmpty()) {
            for (Object[] next : this.zzxo) {
                if (next.length == 1) {
                    this.zzxp.get().zza((MotionEvent) next[0]);
                } else if (next.length == 3) {
                    this.zzxp.get().zza(((Integer) next[0]).intValue(), ((Integer) next[1]).intValue(), ((Integer) next[2]).intValue());
                }
            }
            this.zzxo.clear();
        }
    }

    public final void run() {
        boolean z = false;
        try {
            boolean z2 = this.zzxq.zzcvg;
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzayl)).booleanValue() && z2) {
                z = true;
            }
            this.zzxp.set(zzch.zza(this.zzxq.zzcw, zzd(this.zzrt), z));
        } finally {
            this.zzxr.countDown();
            this.zzrt = null;
            this.zzxq = null;
        }
    }

    public final String zza(Context context) {
        zzce zzce;
        if (!zzdc() || (zzce = this.zzxp.get()) == null) {
            return "";
        }
        zzdd();
        return zzce.zza(zzd(context));
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, (Activity) null);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        zzce zzce;
        if (!zzdc() || (zzce = this.zzxp.get()) == null) {
            return "";
        }
        zzdd();
        return zzce.zza(zzd(context), str, view, activity);
    }

    public final void zza(int i, int i2, int i3) {
        zzce zzce = this.zzxp.get();
        if (zzce != null) {
            zzdd();
            zzce.zza(i, i2, i3);
            return;
        }
        this.zzxo.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public final void zza(MotionEvent motionEvent) {
        zzce zzce = this.zzxp.get();
        if (zzce != null) {
            zzdd();
            zzce.zza(motionEvent);
            return;
        }
        this.zzxo.add(new Object[]{motionEvent});
    }

    public final void zzb(View view) {
        zzce zzce = this.zzxp.get();
        if (zzce != null) {
            zzce.zzb(view);
        }
    }
}
