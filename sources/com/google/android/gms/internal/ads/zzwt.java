package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzag;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

final class zzwt implements zzag {
    private final /* synthetic */ zzwq zzbro;
    private final zzvs zzbrp;
    private final zzaoj zzbrq;

    public zzwt(zzwq zzwq, zzvs zzvs, zzaoj zzaoj) {
        this.zzbro = zzwq;
        this.zzbrp = zzvs;
        this.zzbrq = zzaoj;
    }

    public final void zzau(@Nullable String str) {
        if (str == null) {
            try {
                this.zzbrq.setException(new zzwe());
            } catch (IllegalStateException unused) {
            } catch (Throwable th) {
                this.zzbrp.release();
                throw th;
            }
        } else {
            this.zzbrq.setException(new zzwe(str));
        }
        this.zzbrp.release();
    }

    public final void zzd(JSONObject jSONObject) {
        try {
            this.zzbrq.set(this.zzbro.zzbri.zze(jSONObject));
        } catch (IllegalStateException unused) {
        } catch (JSONException e) {
            this.zzbrq.set(e);
        } catch (Throwable th) {
            this.zzbrp.release();
            throw th;
        }
        this.zzbrp.release();
    }
}
