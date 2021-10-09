package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
final class zzty {
    private final LinkedList<zztz> zzbon = new LinkedList<>();
    /* access modifiers changed from: private */
    public zzjj zzboo;
    private final int zzbop;
    private boolean zzboq;
    /* access modifiers changed from: private */
    public final String zzye;

    zzty(zzjj zzjj, String str, int i) {
        Preconditions.checkNotNull(zzjj);
        Preconditions.checkNotNull(str);
        this.zzboo = zzjj;
        this.zzye = str;
        this.zzbop = i;
    }

    /* access modifiers changed from: package-private */
    public final String getAdUnitId() {
        return this.zzye;
    }

    /* access modifiers changed from: package-private */
    public final int getNetworkType() {
        return this.zzbop;
    }

    /* access modifiers changed from: package-private */
    public final int size() {
        return this.zzbon.size();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzss zzss, zzjj zzjj) {
        this.zzbon.add(new zztz(this, zzss, zzjj));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb(zzss zzss) {
        zztz zztz = new zztz(this, zzss);
        this.zzbon.add(zztz);
        return zztz.load();
    }

    /* access modifiers changed from: package-private */
    public final zztz zzl(zzjj zzjj) {
        if (zzjj != null) {
            this.zzboo = zzjj;
        }
        return this.zzbon.remove();
    }

    /* access modifiers changed from: package-private */
    public final zzjj zzlf() {
        return this.zzboo;
    }

    /* access modifiers changed from: package-private */
    public final int zzlg() {
        Iterator it = this.zzbon.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((zztz) it.next()).zzwa) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final int zzlh() {
        Iterator it = this.zzbon.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((zztz) it.next()).load()) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final void zzli() {
        this.zzboq = true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzlj() {
        return this.zzboq;
    }
}
