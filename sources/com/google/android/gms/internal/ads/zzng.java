package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

@zzadh
public final class zzng {
    private final Collection<zzna<?>> zzats = new ArrayList();
    private final Collection<zzna<String>> zzatt = new ArrayList();
    private final Collection<zzna<String>> zzatu = new ArrayList();

    public final void zza(SharedPreferences.Editor editor, int i, JSONObject jSONObject) {
        for (zzna next : this.zzats) {
            if (next.getSource() == 1) {
                next.zza(editor, next.zzb(jSONObject));
            }
        }
    }

    public final void zza(zzna zzna) {
        this.zzats.add(zzna);
    }

    public final void zzb(zzna<String> zzna) {
        this.zzatt.add(zzna);
    }

    public final void zzc(zzna<String> zzna) {
        this.zzatu.add(zzna);
    }

    public final List<String> zzjb() {
        ArrayList arrayList = new ArrayList();
        for (zzna<String> zzd : this.zzatt) {
            String str = (String) zzkb.zzik().zzd(zzd);
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public final List<String> zzjc() {
        List<String> zzjb = zzjb();
        for (zzna<String> zzd : this.zzatu) {
            String str = (String) zzkb.zzik().zzd(zzd);
            if (str != null) {
                zzjb.add(str);
            }
        }
        return zzjb;
    }
}
