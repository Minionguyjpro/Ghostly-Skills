package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzauh {
    private zzaxr zzdhi;

    private zzauh(zzaxr zzaxr) {
        this.zzdhi = zzaxr;
    }

    static final zzauh zza(zzaxr zzaxr) throws GeneralSecurityException {
        if (zzaxr != null && zzaxr.zzzm() > 0) {
            return new zzauh(zzaxr);
        }
        throw new GeneralSecurityException("empty keyset");
    }

    public final String toString() {
        return zzaup.zzb(this.zzdhi).toString();
    }

    /* access modifiers changed from: package-private */
    public final zzaxr zzwg() {
        return this.zzdhi;
    }
}
