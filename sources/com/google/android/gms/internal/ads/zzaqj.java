package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;

final class zzaqj implements Runnable {
    private final /* synthetic */ String zzcce;
    private final /* synthetic */ String zzdba;
    private final /* synthetic */ int zzdbc;
    private final /* synthetic */ zzaqh zzdbe;

    zzaqj(zzaqh zzaqh, String str, String str2, int i) {
        this.zzdbe = zzaqh;
        this.zzcce = str;
        this.zzdba = str2;
        this.zzdbc = i;
    }

    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "precacheComplete");
        hashMap.put("src", this.zzcce);
        hashMap.put("cachedSrc", this.zzdba);
        hashMap.put("totalBytes", Integer.toString(this.zzdbc));
        this.zzdbe.zza("onPrecacheEvent", (Map<String, String>) hashMap);
    }
}
