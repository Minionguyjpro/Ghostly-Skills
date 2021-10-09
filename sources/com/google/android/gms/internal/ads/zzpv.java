package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzpv extends NativeAd.AdChoicesInfo {
    private final List<NativeAd.Image> zzbhf = new ArrayList();
    private final zzps zzbkk;
    private String zzbkl;

    public zzpv(zzps zzps) {
        zzpw zzpw;
        IBinder iBinder;
        this.zzbkk = zzps;
        try {
            this.zzbkl = zzps.getText();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            this.zzbkl = "";
        }
        try {
            for (zzpw next : zzps.zzjr()) {
                if (!(next instanceof IBinder) || (iBinder = (IBinder) next) == null) {
                    zzpw = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    zzpw = queryLocalInterface instanceof zzpw ? (zzpw) queryLocalInterface : new zzpy(iBinder);
                }
                if (zzpw != null) {
                    this.zzbhf.add(new zzpz(zzpw));
                }
            }
        } catch (RemoteException e2) {
            zzane.zzb("", e2);
        }
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzbhf;
    }

    public final CharSequence getText() {
        return this.zzbkl;
    }
}
