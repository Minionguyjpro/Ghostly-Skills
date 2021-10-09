package com.google.android.gms.internal.ads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zzadh
public class zzaop<T> implements zzaol<T> {
    private final Object mLock = new Object();
    private int zzbqb = 0;
    private final BlockingQueue<zzaoq> zzcwi = new LinkedBlockingQueue();
    private T zzcwj;

    public final int getStatus() {
        return this.zzbqb;
    }

    public final void reject() {
        synchronized (this.mLock) {
            if (this.zzbqb == 0) {
                this.zzbqb = -1;
                for (zzaoq zzaoq : this.zzcwi) {
                    zzaoq.zzcwl.run();
                }
                this.zzcwi.clear();
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

    public final void zza(zzaoo<T> zzaoo, zzaom zzaom) {
        synchronized (this.mLock) {
            if (this.zzbqb == 1) {
                zzaoo.zze(this.zzcwj);
            } else if (this.zzbqb == -1) {
                zzaom.run();
            } else if (this.zzbqb == 0) {
                this.zzcwi.add(new zzaoq(this, zzaoo, zzaom));
            }
        }
    }

    public final void zzk(T t) {
        synchronized (this.mLock) {
            if (this.zzbqb == 0) {
                this.zzcwj = t;
                this.zzbqb = 1;
                for (zzaoq zzaoq : this.zzcwi) {
                    zzaoq.zzcwk.zze(t);
                }
                this.zzcwi.clear();
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }
}
