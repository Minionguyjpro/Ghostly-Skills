package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzbw;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Map;

@zzadh
public final class zzago {
    private static final zzxm zzcku = new zzxm();
    private final zzxn zzckv;
    private final zzbw zzckw;
    private final Map<String, zzaib> zzckx = new HashMap();
    private final zzahu zzcky;
    private final zzb zzckz;
    private final zzabm zzcla;

    public zzago(zzbw zzbw, zzxn zzxn, zzahu zzahu, zzb zzb, zzabm zzabm) {
        this.zzckw = zzbw;
        this.zzckv = zzxn;
        this.zzcky = zzahu;
        this.zzckz = zzb;
        this.zzcla = zzabm;
    }

    public static boolean zza(zzajh zzajh, zzajh zzajh2) {
        return true;
    }

    public final void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib zzaib = this.zzckx.get(str);
                if (!(zzaib == null || zzaib.zzpe() == null)) {
                    zzaib.zzpe().destroy();
                }
            } catch (RemoteException e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onContextChanged(Context context) {
        for (zzaib zzpe : this.zzckx.values()) {
            try {
                zzpe.zzpe().zzi(ObjectWrapper.wrap(context));
            } catch (RemoteException e) {
                zzakb.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public final void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib zzaib = this.zzckx.get(str);
                if (!(zzaib == null || zzaib.zzpe() == null)) {
                    zzaib.zzpe().pause();
                }
            } catch (RemoteException e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib zzaib = this.zzckx.get(str);
                if (!(zzaib == null || zzaib.zzpe() == null)) {
                    zzaib.zzpe().resume();
                }
            } catch (RemoteException e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzaib zzca(java.lang.String r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, com.google.android.gms.internal.ads.zzaib> r0 = r4.zzckx
            java.lang.Object r0 = r0.get(r5)
            com.google.android.gms.internal.ads.zzaib r0 = (com.google.android.gms.internal.ads.zzaib) r0
            if (r0 != 0) goto L_0x0045
            com.google.android.gms.internal.ads.zzxn r1 = r4.zzckv     // Catch:{ Exception -> 0x002b }
            java.lang.String r2 = "com.google.ads.mediation.admob.AdMobAdapter"
            boolean r2 = r2.equals(r5)     // Catch:{ Exception -> 0x002b }
            if (r2 == 0) goto L_0x0016
            com.google.android.gms.internal.ads.zzxm r1 = zzcku     // Catch:{ Exception -> 0x002b }
        L_0x0016:
            com.google.android.gms.internal.ads.zzaib r2 = new com.google.android.gms.internal.ads.zzaib     // Catch:{ Exception -> 0x002b }
            com.google.android.gms.internal.ads.zzxq r1 = r1.zzbm(r5)     // Catch:{ Exception -> 0x002b }
            com.google.android.gms.internal.ads.zzahu r3 = r4.zzcky     // Catch:{ Exception -> 0x002b }
            r2.<init>(r1, r3)     // Catch:{ Exception -> 0x002b }
            java.util.Map<java.lang.String, com.google.android.gms.internal.ads.zzaib> r0 = r4.zzckx     // Catch:{ Exception -> 0x0028 }
            r0.put(r5, r2)     // Catch:{ Exception -> 0x0028 }
            r0 = r2
            goto L_0x0045
        L_0x0028:
            r1 = move-exception
            r0 = r2
            goto L_0x002c
        L_0x002b:
            r1 = move-exception
        L_0x002c:
            java.lang.String r2 = "Fail to instantiate adapter "
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r3 = r5.length()
            if (r3 == 0) goto L_0x003d
            java.lang.String r5 = r2.concat(r5)
            goto L_0x0042
        L_0x003d:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r2)
        L_0x0042:
            com.google.android.gms.internal.ads.zzakb.zzc(r5, r1)
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzago.zzca(java.lang.String):com.google.android.gms.internal.ads.zzaib");
    }

    public final zzaig zzd(zzaig zzaig) {
        if (!(this.zzckw.zzacw == null || this.zzckw.zzacw.zzcod == null || TextUtils.isEmpty(this.zzckw.zzacw.zzcod.zzbsv))) {
            zzaig = new zzaig(this.zzckw.zzacw.zzcod.zzbsv, this.zzckw.zzacw.zzcod.zzbsw);
        }
        if (!(this.zzckw.zzacw == null || this.zzckw.zzacw.zzbtw == null)) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw.zzbtw.zzbsd, this.zzckw.zzadr, zzaig);
        }
        return zzaig;
    }

    public final zzb zzos() {
        return this.zzckz;
    }

    public final zzabm zzot() {
        return this.zzcla;
    }

    public final void zzou() {
        this.zzckw.zzadv = 0;
        zzbw zzbw = this.zzckw;
        zzbv.zzej();
        zzahx zzahx = new zzahx(this.zzckw.zzrt, this.zzckw.zzacx, this);
        String valueOf = String.valueOf(zzahx.getClass().getName());
        zzakb.zzck(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
        zzahx.zznt();
        zzbw.zzacu = zzahx;
    }

    public final void zzov() {
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw, this.zzckw.zzacp, false, this.zzckw.zzacw.zzbtw.zzbsc);
        }
    }

    public final void zzow() {
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw, this.zzckw.zzacp, false, this.zzckw.zzacw.zzbtw.zzbse);
        }
    }

    public final void zzw(boolean z) {
        zzaib zzca = zzca(this.zzckw.zzacw.zzbty);
        if (zzca != null && zzca.zzpe() != null) {
            try {
                zzca.zzpe().setImmersiveMode(z);
                zzca.zzpe().showVideo();
            } catch (RemoteException e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }
}
