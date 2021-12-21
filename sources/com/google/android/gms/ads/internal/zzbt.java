package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zzbt extends AsyncTask<Void, Void, String> {
    private final /* synthetic */ zzbp zzaba;

    private zzbt(zzbp zzbp) {
        this.zzaba = zzbp;
    }

    /* synthetic */ zzbt(zzbp zzbp, zzbq zzbq) {
        this(zzbp);
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final String doInBackground(Void... voidArr) {
        try {
            zzci unused = this.zzaba.zzaay = (zzci) this.zzaba.zzaav.get(((Long) zzkb.zzik().zzd(zznk.zzbdb)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzane.zzc("", e);
        }
        return this.zzaba.zzea();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        if (this.zzaba.zzaax != null && str != null) {
            this.zzaba.zzaax.loadUrl(str);
        }
    }
}
