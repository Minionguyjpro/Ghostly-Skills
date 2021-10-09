package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@zzadh
public final class zzano {
    public static <V> zzanz<V> zza(zzanz<V> zzanz, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzaoj zzaoj = new zzaoj();
        zza(zzaoj, zzanz);
        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new zzans(zzaoj), j, timeUnit);
        zza(zzanz, zzaoj);
        zzaoj.zza(new zzant(schedule), zzaoe.zzcvz);
        return zzaoj;
    }

    public static <A, B> zzanz<B> zza(zzanz<A> zzanz, zzanj<? super A, ? extends B> zzanj, Executor executor) {
        zzaoj zzaoj = new zzaoj();
        zzanz.zza(new zzanr(zzaoj, zzanj, zzanz), executor);
        zza(zzaoj, zzanz);
        return zzaoj;
    }

    public static <A, B> zzanz<B> zza(zzanz<A> zzanz, zzank<A, B> zzank, Executor executor) {
        zzaoj zzaoj = new zzaoj();
        zzanz.zza(new zzanq(zzaoj, zzank, zzanz), executor);
        zza(zzaoj, zzanz);
        return zzaoj;
    }

    public static <V, X extends Throwable> zzanz<V> zza(zzanz<? extends V> zzanz, Class<X> cls, zzanj<? super X, ? extends V> zzanj, Executor executor) {
        zzaoj zzaoj = new zzaoj();
        zza(zzaoj, zzanz);
        zzanz.zza(new zzanu(zzaoj, zzanz, cls, zzanj, executor), zzaoe.zzcvz);
        return zzaoj;
    }

    public static <T> T zza(Future<T> future, T t) {
        try {
            return future.get(((Long) zzkb.zzik().zzd(zznk.zzbam)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e = e;
            future.cancel(true);
            zzakb.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbv.zzeo().zzb(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e2) {
            e = e2;
            future.cancel(true);
            zzakb.zzb("Error waiting for future.", e);
            zzbv.zzeo().zzb(e, "Futures.resolveFuture");
            return t;
        }
    }

    public static <T> T zza(Future<T> future, T t, long j, TimeUnit timeUnit) {
        try {
            return future.get(j, timeUnit);
        } catch (InterruptedException e) {
            e = e;
            future.cancel(true);
            zzakb.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbv.zzeo().zza(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e2) {
            e = e2;
            future.cancel(true);
            zzakb.zzb("Error waiting for future.", e);
            zzbv.zzeo().zza(e, "Futures.resolveFuture");
            return t;
        }
    }

    public static <V> void zza(zzanz<V> zzanz, zzanl<V> zzanl, Executor executor) {
        zzanz.zza(new zzanp(zzanl, zzanz), executor);
    }

    private static <V> void zza(zzanz<? extends V> zzanz, zzaoj<V> zzaoj) {
        zza(zzaoj, zzanz);
        zzanz.zza(new zzanv(zzaoj, zzanz), zzaoe.zzcvz);
    }

    private static <A, B> void zza(zzanz<A> zzanz, Future<B> future) {
        zzanz.zza(new zzanw(zzanz, future), zzaoe.zzcvz);
    }

    static final /* synthetic */ void zza(zzaoj zzaoj, zzanj zzanj, zzanz zzanz) {
        if (!zzaoj.isCancelled()) {
            try {
                zza(zzanj.zzc(zzanz.get()), zzaoj);
            } catch (CancellationException unused) {
                zzaoj.cancel(true);
            } catch (ExecutionException e) {
                zzaoj.setException(e.getCause());
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                zzaoj.setException(e2);
            } catch (Exception e3) {
                zzaoj.setException(e3);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static final /* synthetic */ void zza(com.google.android.gms.internal.ads.zzaoj r1, com.google.android.gms.internal.ads.zzanz r2, java.lang.Class r3, com.google.android.gms.internal.ads.zzanj r4, java.util.concurrent.Executor r5) {
        /*
            java.lang.Object r2 = r2.get()     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000a, Exception -> 0x0008 }
            r1.set(r2)     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000a, Exception -> 0x0008 }
            return
        L_0x0008:
            r2 = move-exception
            goto L_0x0018
        L_0x000a:
            r2 = move-exception
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
            goto L_0x0018
        L_0x0013:
            r2 = move-exception
            java.lang.Throwable r2 = r2.getCause()
        L_0x0018:
            boolean r3 = r3.isInstance(r2)
            if (r3 == 0) goto L_0x002a
            com.google.android.gms.internal.ads.zzany r2 = zzi(r2)
            com.google.android.gms.internal.ads.zzanz r2 = zza(r2, r4, (java.util.concurrent.Executor) r5)
            zza(r2, r1)
            return
        L_0x002a:
            r1.setException(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzano.zza(com.google.android.gms.internal.ads.zzaoj, com.google.android.gms.internal.ads.zzanz, java.lang.Class, com.google.android.gms.internal.ads.zzanj, java.util.concurrent.Executor):void");
    }

    public static <T> zzanx<T> zzd(Throwable th) {
        return new zzanx<>(th);
    }

    public static <T> zzany<T> zzi(T t) {
        return new zzany<>(t);
    }
}
