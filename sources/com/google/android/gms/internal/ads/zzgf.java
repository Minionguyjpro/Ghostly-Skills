package com.google.android.gms.internal.ads;

import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgf {
    private final Object mLock = new Object();
    private int zzahm;
    private List<zzge> zzahn = new LinkedList();

    public final boolean zza(zzge zzge) {
        synchronized (this.mLock) {
            return this.zzahn.contains(zzge);
        }
    }

    public final boolean zzb(zzge zzge) {
        synchronized (this.mLock) {
            Iterator<zzge> it = this.zzahn.iterator();
            while (it.hasNext()) {
                zzge next = it.next();
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzawq)).booleanValue() || zzbv.zzeo().zzqh().zzqu()) {
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzaws)).booleanValue() && !zzbv.zzeo().zzqh().zzqw() && zzge != next && next.zzgp().equals(zzge.zzgp())) {
                        it.remove();
                        return true;
                    }
                } else if (zzge != next && next.getSignature().equals(zzge.getSignature())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final void zzc(zzge zzge) {
        synchronized (this.mLock) {
            if (this.zzahn.size() >= 10) {
                int size = this.zzahn.size();
                StringBuilder sb = new StringBuilder(41);
                sb.append("Queue is full, current size = ");
                sb.append(size);
                zzakb.zzck(sb.toString());
                this.zzahn.remove(0);
            }
            int i = this.zzahm;
            this.zzahm = i + 1;
            zzge.zzo(i);
            this.zzahn.add(zzge);
        }
    }

    public final zzge zzgv() {
        synchronized (this.mLock) {
            zzge zzge = null;
            if (this.zzahn.size() == 0) {
                zzakb.zzck("Queue empty");
                return null;
            }
            int i = 0;
            if (this.zzahn.size() >= 2) {
                int i2 = RecyclerView.UNDEFINED_DURATION;
                int i3 = 0;
                for (zzge next : this.zzahn) {
                    int score = next.getScore();
                    if (score > i2) {
                        i = i3;
                        zzge = next;
                        i2 = score;
                    }
                    i3++;
                }
                this.zzahn.remove(i);
                return zzge;
            }
            zzge zzge2 = this.zzahn.get(0);
            zzge2.zzgq();
            return zzge2;
        }
    }
}
