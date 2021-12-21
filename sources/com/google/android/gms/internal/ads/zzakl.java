package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;

final class zzakl implements zzoi {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ List zzcrs;
    private final /* synthetic */ zzoh zzcrt;

    zzakl(zzakk zzakk, List list, zzoh zzoh, Context context) {
        this.zzcrs = list;
        this.zzcrt = zzoh;
        this.val$context = context;
    }

    public final void zzjp() {
        for (String str : this.zzcrs) {
            String valueOf = String.valueOf(str);
            zzakb.zzdj(valueOf.length() != 0 ? "Pinging url: ".concat(valueOf) : new String("Pinging url: "));
            this.zzcrt.mayLaunchUrl(Uri.parse(str), (Bundle) null, (List<Bundle>) null);
        }
        this.zzcrt.zzc((Activity) this.val$context);
    }

    public final void zzjq() {
    }
}
