package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzalt {
    private static zzv zzctf;
    private static final Object zzctg = new Object();
    @Deprecated
    private static final zzalz<Void> zzcth = new zzalu();

    public zzalt(Context context) {
        zzbb(context.getApplicationContext() != null ? context.getApplicationContext() : context);
    }

    private static zzv zzbb(Context context) {
        zzv zzv;
        zzv zzv2;
        synchronized (zzctg) {
            if (zzctf == null) {
                zznk.initialize(context);
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbdv)).booleanValue()) {
                    zzv2 = zzaln.zzba(context);
                } else {
                    zzv2 = new zzv(new zzam(new File(context.getCacheDir(), "volley")), new zzaj((zzai) new zzas()));
                    zzv2.start();
                }
                zzctf = zzv2;
            }
            zzv = zzctf;
        }
        return zzv;
    }

    public final zzanz<String> zza(int i, String str, Map<String, String> map, byte[] bArr) {
        String str2 = str;
        zzama zzama = new zzama((zzalu) null);
        zzalx zzalx = new zzalx(this, str2, zzama);
        zzamy zzamy = new zzamy((String) null);
        zzaly zzaly = new zzaly(this, i, str, zzama, zzalx, bArr, map, zzamy);
        if (zzamy.isEnabled()) {
            try {
                zzamy.zza(str2, "GET", zzaly.getHeaders(), zzaly.zzg());
            } catch (zza e) {
                zzakb.zzdk(e.getMessage());
            }
        }
        zzctf.zze(zzaly);
        return zzama;
    }

    @Deprecated
    public final <T> zzanz<T> zza(String str, zzalz<T> zzalz) {
        zzaoj zzaoj = new zzaoj();
        zzctf.zze(new zzamb(str, zzaoj));
        return zzano.zza(zzano.zza(zzaoj, new zzalw(this, zzalz), (Executor) zzaki.zzcrj), Throwable.class, new zzalv(this, zzalz), zzaoe.zzcvz);
    }

    public final zzanz<String> zzc(String str, Map<String, String> map) {
        return zza(0, str, map, (byte[]) null);
    }
}
