package com.google.android.gms.internal.ads;

final class zzafd implements zzaoo<zzwb> {
    private final /* synthetic */ zzafc zzcgm;

    zzafd(zzafc zzafc) {
        this.zzcgm = zzafc;
    }

    public final /* synthetic */ void zze(Object obj) {
        try {
            ((zzwb) obj).zzb("AFMA_getAdapterLessMediationAd", this.zzcgm.zzcgk);
        } catch (Exception e) {
            zzakb.zzb("Error requesting an ad url", e);
            zzafa.zzcgg.zzat(this.zzcgm.zzcgl);
        }
    }
}
