package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.ads.internal.zzbv;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzadh
public final class zzsm implements zzm {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public zzsf zzbnl;
    /* access modifiers changed from: private */
    public boolean zzbnm;

    public zzsm(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzbnl != null) {
                this.zzbnl.disconnect();
                this.zzbnl = null;
                Binder.flushPendingCommands();
            }
        }
    }

    private final Future<ParcelFileDescriptor> zzb(zzsg zzsg) {
        zzsn zzsn = new zzsn(this);
        zzso zzso = new zzso(this, zzsn, zzsg);
        zzsr zzsr = new zzsr(this, zzsn);
        synchronized (this.mLock) {
            zzsf zzsf = new zzsf(this.mContext, zzbv.zzez().zzsa(), zzso, zzsr);
            this.zzbnl = zzsf;
            zzsf.checkAvailabilityAndConnect();
        }
        return zzsn;
    }

    /* JADX INFO: finally extract failed */
    public final zzp zzc(zzr<?> zzr) throws zzae {
        zzp zzp;
        zzsg zzh = zzsg.zzh(zzr);
        long intValue = (long) ((Integer) zzkb.zzik().zzd(zznk.zzbdx)).intValue();
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        try {
            zzsi zzsi = (zzsi) new zzaev(zzb(zzh).get(intValue, TimeUnit.MILLISECONDS)).zza(zzsi.CREATOR);
            if (!zzsi.zzbnj) {
                if (zzsi.zzbnh.length != zzsi.zzbni.length) {
                    zzp = null;
                } else {
                    HashMap hashMap = new HashMap();
                    for (int i = 0; i < zzsi.zzbnh.length; i++) {
                        hashMap.put(zzsi.zzbnh[i], zzsi.zzbni[i]);
                    }
                    zzp = new zzp(zzsi.statusCode, zzsi.data, (Map<String, String>) hashMap, zzsi.zzac, zzsi.zzad);
                }
                StringBuilder sb = new StringBuilder(52);
                sb.append("Http assets remote cache took ");
                sb.append(zzbv.zzer().elapsedRealtime() - elapsedRealtime);
                sb.append("ms");
                zzakb.v(sb.toString());
                return zzp;
            }
            throw new zzae(zzsi.zzbnk);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            StringBuilder sb2 = new StringBuilder(52);
            sb2.append("Http assets remote cache took ");
            sb2.append(zzbv.zzer().elapsedRealtime() - elapsedRealtime);
            sb2.append("ms");
            zzakb.v(sb2.toString());
            return null;
        } catch (Throwable th) {
            StringBuilder sb3 = new StringBuilder(52);
            sb3.append("Http assets remote cache took ");
            sb3.append(zzbv.zzer().elapsedRealtime() - elapsedRealtime);
            sb3.append("ms");
            zzakb.v(sb3.toString());
            throw th;
        }
    }
}
