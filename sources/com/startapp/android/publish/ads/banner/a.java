package com.startapp.android.publish.ads.banner;

import com.startapp.android.publish.adsCommon.Utils.d;
import com.startapp.android.publish.adsCommon.Utils.e;
import com.startapp.android.publish.common.model.GetAdRequest;

/* compiled from: StartAppSDK */
public class a extends GetAdRequest {

    /* renamed from: a  reason: collision with root package name */
    private boolean f47a;
    private int b;

    public void a(boolean z) {
        this.f47a = z;
    }

    public boolean a() {
        return this.f47a;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public e getNameValueMap() {
        e nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new d();
        }
        a(nameValueMap);
        return nameValueMap;
    }

    private void a(e eVar) {
        eVar.a("fixedSize", (Object) Boolean.valueOf(a()), false);
        eVar.a("bnrt", (Object) Integer.valueOf(b()), false);
    }
}
