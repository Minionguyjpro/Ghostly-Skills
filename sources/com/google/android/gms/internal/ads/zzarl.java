package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.HashMap;
import java.util.Map;

@zzadh
public final class zzarl extends zzlp {
    private final Object lock = new Object();
    private boolean zzato = true;
    private boolean zzatp;
    private boolean zzatq;
    private zzlr zzbuq;
    private final zzapw zzcyg;
    private final boolean zzded;
    private final boolean zzdee;
    private final float zzdef;
    private int zzdeg;
    private boolean zzdeh;
    private boolean zzdei = true;
    private float zzdej;
    private float zzdek;

    public zzarl(zzapw zzapw, float f, boolean z, boolean z2) {
        this.zzcyg = zzapw;
        this.zzdef = f;
        this.zzded = z;
        this.zzdee = z2;
    }

    private final void zzf(String str, Map<String, String> map) {
        HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put("action", str);
        zzaoe.zzcvy.execute(new zzarm(this, hashMap));
    }

    public final float getAspectRatio() {
        float f;
        synchronized (this.lock) {
            f = this.zzdek;
        }
        return f;
    }

    public final int getPlaybackState() {
        int i;
        synchronized (this.lock) {
            i = this.zzdeg;
        }
        return i;
    }

    public final boolean isClickToExpandEnabled() {
        boolean z;
        boolean isCustomControlsEnabled = isCustomControlsEnabled();
        synchronized (this.lock) {
            if (!isCustomControlsEnabled) {
                try {
                    if (this.zzatq && this.zzdee) {
                        z = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            z = false;
        }
        return z;
    }

    public final boolean isCustomControlsEnabled() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzded && this.zzatp;
        }
        return z;
    }

    public final boolean isMuted() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzdei;
        }
        return z;
    }

    public final void mute(boolean z) {
        zzf(z ? "mute" : "unmute", (Map<String, String>) null);
    }

    public final void pause() {
        zzf("pause", (Map<String, String>) null);
    }

    public final void play() {
        zzf("play", (Map<String, String>) null);
    }

    public final void zza(float f, int i, boolean z, float f2) {
        boolean z2;
        int i2;
        synchronized (this.lock) {
            this.zzdej = f;
            z2 = this.zzdei;
            this.zzdei = z;
            i2 = this.zzdeg;
            this.zzdeg = i;
            float f3 = this.zzdek;
            this.zzdek = f2;
            if (Math.abs(f2 - f3) > 1.0E-4f) {
                this.zzcyg.getView().invalidate();
            }
        }
        zzaoe.zzcvy.execute(new zzarn(this, i2, i, z2, z));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, int i2, boolean z, boolean z2) {
        synchronized (this.lock) {
            boolean z3 = false;
            boolean z4 = i != i2;
            boolean z5 = !this.zzdeh && i2 == 1;
            boolean z6 = z4 && i2 == 1;
            boolean z7 = z4 && i2 == 2;
            boolean z8 = z4 && i2 == 3;
            boolean z9 = z != z2;
            if (this.zzdeh || z5) {
                z3 = true;
            }
            this.zzdeh = z3;
            if (this.zzbuq != null) {
                if (z5) {
                    try {
                        this.zzbuq.onVideoStart();
                    } catch (RemoteException e) {
                        zzakb.zzc("Unable to call onVideoStart()", e);
                    }
                }
                if (z6) {
                    try {
                        this.zzbuq.onVideoPlay();
                    } catch (RemoteException e2) {
                        zzakb.zzc("Unable to call onVideoPlay()", e2);
                    }
                }
                if (z7) {
                    try {
                        this.zzbuq.onVideoPause();
                    } catch (RemoteException e3) {
                        zzakb.zzc("Unable to call onVideoPause()", e3);
                    }
                }
                if (z8) {
                    try {
                        this.zzbuq.onVideoEnd();
                    } catch (RemoteException e4) {
                        zzakb.zzc("Unable to call onVideoEnd()", e4);
                    }
                }
                if (z9) {
                    try {
                        this.zzbuq.onVideoMute(z2);
                    } catch (RemoteException e5) {
                        zzakb.zzc("Unable to call onVideoMute()", e5);
                    }
                }
            } else {
                return;
            }
        }
        return;
    }

    public final void zza(zzlr zzlr) {
        synchronized (this.lock) {
            this.zzbuq = zzlr;
        }
    }

    public final void zzb(zzmu zzmu) {
        synchronized (this.lock) {
            this.zzato = zzmu.zzato;
            this.zzatp = zzmu.zzatp;
            this.zzatq = zzmu.zzatq;
        }
        zzf("initialState", CollectionUtils.mapOf("muteStart", zzmu.zzato ? "1" : "0", "customControlsRequested", zzmu.zzatp ? "1" : "0", "clickToExpandRequested", zzmu.zzatq ? "1" : "0"));
    }

    public final float zzim() {
        return this.zzdef;
    }

    public final float zzin() {
        float f;
        synchronized (this.lock) {
            f = this.zzdej;
        }
        return f;
    }

    public final zzlr zzio() throws RemoteException {
        zzlr zzlr;
        synchronized (this.lock) {
            zzlr = this.zzbuq;
        }
        return zzlr;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzo(Map map) {
        this.zzcyg.zza("pubVideoCmd", (Map<String, ?>) map);
    }
}
