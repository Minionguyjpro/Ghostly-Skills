package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;

@zzadh
public final class zzamm {
    public static boolean zzo(zzjj zzjj) {
        Bundle bundle = zzjj.zzaqg != null ? zzjj.zzaqg : new Bundle();
        return (bundle.getBundle(AdMobAdapter.class.getName()) != null ? bundle.getBundle(AdMobAdapter.class.getName()) : new Bundle()).getBoolean("render_test_label", false);
    }
}
