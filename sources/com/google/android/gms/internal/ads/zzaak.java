package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzadh
public final class zzaak extends zzaal implements zzv<zzaqw> {
    private final Context mContext;
    private final WindowManager zzaeu;
    private DisplayMetrics zzagj;
    private final zzaqw zzbnd;
    private final zzmw zzbww;
    private float zzbwx;
    private int zzbwy = -1;
    private int zzbwz = -1;
    private int zzbxa;
    private int zzbxb = -1;
    private int zzbxc = -1;
    private int zzbxd = -1;
    private int zzbxe = -1;

    public zzaak(zzaqw zzaqw, Context context, zzmw zzmw) {
        super(zzaqw);
        this.zzbnd = zzaqw;
        this.mContext = context;
        this.zzbww = zzmw;
        this.zzaeu = (WindowManager) context.getSystemService("window");
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        int i;
        this.zzagj = new DisplayMetrics();
        Display defaultDisplay = this.zzaeu.getDefaultDisplay();
        defaultDisplay.getMetrics(this.zzagj);
        this.zzbwx = this.zzagj.density;
        this.zzbxa = defaultDisplay.getRotation();
        zzkb.zzif();
        DisplayMetrics displayMetrics = this.zzagj;
        this.zzbwy = zzamu.zzb(displayMetrics, displayMetrics.widthPixels);
        zzkb.zzif();
        DisplayMetrics displayMetrics2 = this.zzagj;
        this.zzbwz = zzamu.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity zzto = this.zzbnd.zzto();
        if (zzto == null || zzto.getWindow() == null) {
            this.zzbxb = this.zzbwy;
            i = this.zzbwz;
        } else {
            zzbv.zzek();
            int[] zzf = zzakk.zzf(zzto);
            zzkb.zzif();
            this.zzbxb = zzamu.zzb(this.zzagj, zzf[0]);
            zzkb.zzif();
            i = zzamu.zzb(this.zzagj, zzf[1]);
        }
        this.zzbxc = i;
        if (this.zzbnd.zzud().zzvs()) {
            this.zzbxd = this.zzbwy;
            this.zzbxe = this.zzbwz;
        } else {
            this.zzbnd.measure(0, 0);
        }
        zza(this.zzbwy, this.zzbwz, this.zzbxb, this.zzbxc, this.zzbwx, this.zzbxa);
        this.zzbnd.zza("onDeviceFeaturesReceived", new zzaah(new zzaaj().zzo(this.zzbww.zziw()).zzn(this.zzbww.zzix()).zzp(this.zzbww.zziz()).zzq(this.zzbww.zziy()).zzr(true)).zzng());
        int[] iArr = new int[2];
        this.zzbnd.getLocationOnScreen(iArr);
        zzkb.zzif();
        int zzb = zzamu.zzb(this.mContext, iArr[0]);
        zzkb.zzif();
        zzc(zzb, zzamu.zzb(this.mContext, iArr[1]));
        if (zzakb.isLoggable(2)) {
            zzakb.zzdj("Dispatching Ready Event.");
        }
        zzbx(this.zzbnd.zztq().zzcw);
    }

    public final void zzc(int i, int i2) {
        int i3 = 0;
        if (this.mContext instanceof Activity) {
            i3 = zzbv.zzek().zzh((Activity) this.mContext)[0];
        }
        if (this.zzbnd.zzud() == null || !this.zzbnd.zzud().zzvs()) {
            zzkb.zzif();
            this.zzbxd = zzamu.zzb(this.mContext, this.zzbnd.getWidth());
            zzkb.zzif();
            this.zzbxe = zzamu.zzb(this.mContext, this.zzbnd.getHeight());
        }
        zzc(i, i2 - i3, this.zzbxd, this.zzbxe);
        this.zzbnd.zzuf().zzb(i, i2);
    }
}
