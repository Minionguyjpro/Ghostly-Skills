package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzadh
public final class zzxk implements zzww {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final boolean zzael;
    private final zzwy zzbtj;
    private final boolean zzbtn;
    private final boolean zzbto;
    private final zzaef zzbuc;
    private final long zzbud;
    private boolean zzbuf = false;
    private final String zzbuh;
    private List<zzxe> zzbui = new ArrayList();
    private zzxb zzbum;
    private final zznx zzvr;
    private final zzxn zzwh;

    public zzxk(Context context, zzaef zzaef, zzxn zzxn, zzwy zzwy, boolean z, boolean z2, String str, long j, long j2, zznx zznx, boolean z3) {
        this.mContext = context;
        this.zzbuc = zzaef;
        this.zzwh = zzxn;
        this.zzbtj = zzwy;
        this.zzael = z;
        this.zzbtn = z2;
        this.zzbuh = str;
        this.mStartTime = j;
        this.zzbud = j2;
        this.zzvr = zznx;
        this.zzbto = z3;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzbuf = true;
            if (this.zzbum != null) {
                this.zzbum.cancel();
            }
        }
    }

    public final zzxe zzh(List<zzwx> list) {
        Object obj;
        zzxe zzxe;
        zzakb.zzck("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zznv zzjj = this.zzvr.zzjj();
        zzjn zzjn = this.zzbuc.zzacv;
        int[] iArr = new int[2];
        if (zzjn.zzard != null) {
            zzbv.zzfd();
            if (zzxg.zza(this.zzbuh, iArr)) {
                int i = 0;
                int i2 = iArr[0];
                int i3 = iArr[1];
                zzjn[] zzjnArr = zzjn.zzard;
                int length = zzjnArr.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    zzjn zzjn2 = zzjnArr[i];
                    if (i2 == zzjn2.width && i3 == zzjn2.height) {
                        zzjn = zzjn2;
                        break;
                    }
                    i++;
                }
            }
        }
        Iterator<zzwx> it = list.iterator();
        while (it.hasNext()) {
            zzwx next = it.next();
            String valueOf = String.valueOf(next.zzbrs);
            zzakb.zzdj(valueOf.length() != 0 ? "Trying mediation network: ".concat(valueOf) : new String("Trying mediation network: "));
            Iterator<String> it2 = next.zzbrt.iterator();
            while (it2.hasNext()) {
                String next2 = it2.next();
                zznv zzjj2 = this.zzvr.zzjj();
                Object obj2 = this.mLock;
                synchronized (obj2) {
                    try {
                        if (this.zzbuf) {
                            zzxe = new zzxe(-1);
                        } else {
                            Context context = this.mContext;
                            zzxn zzxn = this.zzwh;
                            zzwy zzwy = this.zzbtj;
                            zzjj zzjj3 = this.zzbuc.zzccv;
                            zzang zzang = this.zzbuc.zzacr;
                            Iterator<zzwx> it3 = it;
                            boolean z = this.zzael;
                            Iterator<String> it4 = it2;
                            boolean z2 = this.zzbtn;
                            zzjj zzjj4 = zzjj3;
                            zzpl zzpl = this.zzbuc.zzadj;
                            zznv zznv = zzjj;
                            zznv zznv2 = zzjj2;
                            zzxb zzxb = r10;
                            obj = obj2;
                            try {
                                zzxb zzxb2 = new zzxb(context, next2, zzxn, zzwy, next, zzjj4, zzjn, zzang, z, z2, zzpl, this.zzbuc.zzads, this.zzbuc.zzcdk, this.zzbuc.zzcef, this.zzbto);
                                this.zzbum = zzxb;
                                zzxe zza = zzxb.zza(this.mStartTime, this.zzbud);
                                this.zzbui.add(zza);
                                if (zza.zzbtv == 0) {
                                    zzakb.zzck("Adapter succeeded.");
                                    this.zzvr.zze("mediation_network_succeed", next2);
                                    if (!arrayList.isEmpty()) {
                                        this.zzvr.zze("mediation_networks_fail", TextUtils.join(",", arrayList));
                                    }
                                    this.zzvr.zza(zznv2, "mls");
                                    this.zzvr.zza(zznv, "ttm");
                                    return zza;
                                }
                                zznv zznv3 = zznv;
                                arrayList.add(next2);
                                this.zzvr.zza(zznv2, "mlf");
                                if (zza.zzbtx != null) {
                                    zzakk.zzcrm.post(new zzxl(this, zza));
                                }
                                it = it3;
                                zzjj = zznv3;
                                it2 = it4;
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        obj = obj2;
                        throw th;
                    }
                }
                return zzxe;
            }
        }
        if (!arrayList.isEmpty()) {
            this.zzvr.zze("mediation_networks_fail", TextUtils.join(",", arrayList));
        }
        return new zzxe(1);
    }

    public final List<zzxe> zzme() {
        return this.zzbui;
    }
}
