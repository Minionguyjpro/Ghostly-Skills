package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zzcf implements zzce {
    protected static volatile zzcz zzps;
    protected MotionEvent zzpy;
    protected LinkedList<MotionEvent> zzpz = new LinkedList<>();
    protected long zzqa = 0;
    protected long zzqb = 0;
    protected long zzqc = 0;
    protected long zzqd = 0;
    protected long zzqe = 0;
    protected long zzqf = 0;
    protected long zzqg = 0;
    protected double zzqh;
    private double zzqi;
    private double zzqj;
    protected float zzqk;
    protected float zzql;
    protected float zzqm;
    protected float zzqn;
    private boolean zzqo = false;
    protected boolean zzqp = false;
    protected DisplayMetrics zzqq;

    protected zzcf(Context context) {
        try {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbay)).booleanValue()) {
                zzbk.zzv();
            } else {
                zzde.zzb(zzps);
            }
            this.zzqq = context.getResources().getDisplayMetrics();
        } catch (Throwable unused) {
        }
    }

    private final String zza(Context context, String str, boolean z, View view, Activity activity, byte[] bArr) {
        zzba zzba;
        int i;
        if (z) {
            try {
                zzba = zza(context, view, activity);
                this.zzqo = true;
            } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
                i = 7;
                return Integer.toString(i);
            } catch (Throwable unused2) {
                i = 3;
                return Integer.toString(i);
            }
        } else {
            zzba = zza(context, (zzax) null);
        }
        if (zzba != null) {
            if (zzba.zzacw() != 0) {
                return zzbk.zza(zzba, str);
            }
        }
        return Integer.toString(5);
    }

    /* access modifiers changed from: protected */
    public abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzcw;

    /* access modifiers changed from: protected */
    public abstract zzba zza(Context context, View view, Activity activity);

    /* access modifiers changed from: protected */
    public abstract zzba zza(Context context, zzax zzax);

    public final String zza(Context context) {
        if (zzdg.isMainThread()) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbba)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return zza(context, (String) null, false, (View) null, (Activity) null, (byte[]) null);
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, (Activity) null);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        return zza(context, str, true, view, activity, (byte[]) null);
    }

    public final void zza(int i, int i2, int i3) {
        MotionEvent motionEvent;
        MotionEvent motionEvent2 = this.zzpy;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        DisplayMetrics displayMetrics = this.zzqq;
        if (displayMetrics != null) {
            motionEvent = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * displayMetrics.density, this.zzqq.density * ((float) i2), 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            motionEvent = null;
        }
        this.zzpy = motionEvent;
        this.zzqp = false;
    }

    public final void zza(MotionEvent motionEvent) {
        boolean z = false;
        if (this.zzqo) {
            this.zzqd = 0;
            this.zzqc = 0;
            this.zzqb = 0;
            this.zzqa = 0;
            this.zzqe = 0;
            this.zzqg = 0;
            this.zzqf = 0;
            Iterator it = this.zzpz.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            this.zzpz.clear();
            this.zzpy = null;
            this.zzqo = false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.zzqh = 0.0d;
            this.zzqi = (double) motionEvent.getRawX();
            this.zzqj = (double) motionEvent.getRawY();
        } else if (action == 1 || action == 2) {
            double rawX = (double) motionEvent.getRawX();
            double rawY = (double) motionEvent.getRawY();
            double d = this.zzqi;
            Double.isNaN(rawX);
            double d2 = rawX - d;
            double d3 = this.zzqj;
            Double.isNaN(rawY);
            double d4 = rawY - d3;
            this.zzqh += Math.sqrt((d2 * d2) + (d4 * d4));
            this.zzqi = rawX;
            this.zzqj = rawY;
        }
        int action2 = motionEvent.getAction();
        if (action2 == 0) {
            this.zzqk = motionEvent.getX();
            this.zzql = motionEvent.getY();
            this.zzqm = motionEvent.getRawX();
            this.zzqn = motionEvent.getRawY();
            this.zzqa++;
        } else if (action2 == 1) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            this.zzpy = obtain;
            this.zzpz.add(obtain);
            if (this.zzpz.size() > 6) {
                this.zzpz.remove().recycle();
            }
            this.zzqc++;
            this.zzqe = zza(new Throwable().getStackTrace());
        } else if (action2 == 2) {
            this.zzqb += (long) (motionEvent.getHistorySize() + 1);
            try {
                zzdf zzb = zzb(motionEvent);
                if ((zzb == null || zzb.zzfr == null || zzb.zzst == null) ? false : true) {
                    this.zzqf += zzb.zzfr.longValue() + zzb.zzst.longValue();
                }
                if (!(this.zzqq == null || zzb == null || zzb.zzfp == null || zzb.zzsu == null)) {
                    z = true;
                }
                if (z) {
                    this.zzqg += zzb.zzfp.longValue() + zzb.zzsu.longValue();
                }
            } catch (zzcw unused) {
            }
        } else if (action2 == 3) {
            this.zzqd++;
        }
        this.zzqp = true;
    }

    /* access modifiers changed from: protected */
    public abstract zzdf zzb(MotionEvent motionEvent) throws zzcw;

    public void zzb(View view) {
    }
}
