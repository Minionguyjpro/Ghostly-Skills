package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;

@zzadh
public final class zzahh implements RewardItem {
    private final zzagu zzcli;

    public zzahh(zzagu zzagu) {
        this.zzcli = zzagu;
    }

    public final int getAmount() {
        zzagu zzagu = this.zzcli;
        if (zzagu == null) {
            return 0;
        }
        try {
            return zzagu.getAmount();
        } catch (RemoteException e) {
            zzane.zzc("Could not forward getAmount to RewardItem", e);
            return 0;
        }
    }

    public final String getType() {
        zzagu zzagu = this.zzcli;
        if (zzagu == null) {
            return null;
        }
        try {
            return zzagu.getType();
        } catch (RemoteException e) {
            zzane.zzc("Could not forward getType to RewardItem", e);
            return null;
        }
    }
}
