package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzjr;

final class zzjx extends zzjr.zza<zzqa> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzjr zzart;
    private final /* synthetic */ FrameLayout zzaru;
    private final /* synthetic */ FrameLayout zzarv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjx(zzjr zzjr, FrameLayout frameLayout, FrameLayout frameLayout2, Context context) {
        super();
        this.zzart = zzjr;
        this.zzaru = frameLayout;
        this.zzarv = frameLayout2;
        this.val$context = context;
    }

    public final /* synthetic */ Object zza(zzld zzld) throws RemoteException {
        return zzld.createNativeAdViewDelegate(ObjectWrapper.wrap(this.zzaru), ObjectWrapper.wrap(this.zzarv));
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzqa zzb = this.zzart.zzarm.zzb(this.val$context, this.zzaru, this.zzarv);
        if (zzb != null) {
            return zzb;
        }
        zzjr.zza(this.val$context, "native_ad_view_delegate");
        return new zzmm();
    }
}
