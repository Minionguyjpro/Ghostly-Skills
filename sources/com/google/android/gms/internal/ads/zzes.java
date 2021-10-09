package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzes implements zzfa {
    private final Object mLock = new Object();
    private final WeakHashMap<zzajh, zzet> zzaem = new WeakHashMap<>();
    private final ArrayList<zzet> zzaen = new ArrayList<>();
    private final Context zzaeo;
    private final zzvf zzaep;
    private final zzang zzyf;

    public zzes(Context context, zzang zzang) {
        this.zzaeo = context.getApplicationContext();
        this.zzyf = zzang;
        this.zzaep = new zzvf(context.getApplicationContext(), zzang, (String) zzkb.zzik().zzd(zznk.zzaub));
    }

    private final boolean zzg(zzajh zzajh) {
        boolean z;
        synchronized (this.mLock) {
            zzet zzet = this.zzaem.get(zzajh);
            z = zzet != null && zzet.zzge();
        }
        return z;
    }

    public final void zza(zzet zzet) {
        synchronized (this.mLock) {
            if (!zzet.zzge()) {
                this.zzaen.remove(zzet);
                Iterator<Map.Entry<zzajh, zzet>> it = this.zzaem.entrySet().iterator();
                while (it.hasNext()) {
                    if (it.next().getValue() == zzet) {
                        it.remove();
                    }
                }
            }
        }
    }

    public final void zza(zzjn zzjn, zzajh zzajh) {
        zza(zzjn, zzajh, zzajh.zzbyo.getView());
    }

    public final void zza(zzjn zzjn, zzajh zzajh, View view) {
        zza(zzjn, zzajh, (zzgd) new zzez(view, zzajh), (zzaqw) null);
    }

    public final void zza(zzjn zzjn, zzajh zzajh, View view, zzaqw zzaqw) {
        zza(zzjn, zzajh, (zzgd) new zzez(view, zzajh), zzaqw);
    }

    public final void zza(zzjn zzjn, zzajh zzajh, zzgd zzgd, zzaqw zzaqw) {
        zzet zzet;
        synchronized (this.mLock) {
            if (zzg(zzajh)) {
                zzet = this.zzaem.get(zzajh);
            } else {
                zzet zzet2 = new zzet(this.zzaeo, zzjn, zzajh, this.zzyf, zzgd);
                zzet2.zza((zzfa) this);
                this.zzaem.put(zzajh, zzet2);
                this.zzaen.add(zzet2);
                zzet = zzet2;
            }
            zzet.zza(zzaqw != null ? new zzfb(zzet, zzaqw) : new zzff(zzet, this.zzaep, this.zzaeo));
        }
    }

    public final void zzh(zzajh zzajh) {
        synchronized (this.mLock) {
            zzet zzet = this.zzaem.get(zzajh);
            if (zzet != null) {
                zzet.zzgc();
            }
        }
    }

    public final void zzi(zzajh zzajh) {
        synchronized (this.mLock) {
            zzet zzet = this.zzaem.get(zzajh);
            if (zzet != null) {
                zzet.stop();
            }
        }
    }

    public final void zzj(zzajh zzajh) {
        synchronized (this.mLock) {
            zzet zzet = this.zzaem.get(zzajh);
            if (zzet != null) {
                zzet.pause();
            }
        }
    }

    public final void zzk(zzajh zzajh) {
        synchronized (this.mLock) {
            zzet zzet = this.zzaem.get(zzajh);
            if (zzet != null) {
                zzet.resume();
            }
        }
    }
}
