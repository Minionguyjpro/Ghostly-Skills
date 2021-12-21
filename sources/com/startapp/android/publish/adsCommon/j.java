package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Utils.d;
import com.startapp.android.publish.adsCommon.Utils.e;
import com.startapp.android.publish.adsCommon.i.a;
import com.startapp.android.publish.adsCommon.i.b;
import com.startapp.common.a.c;

/* compiled from: StartAppSDK */
public class j extends BaseRequest {

    /* renamed from: a  reason: collision with root package name */
    private b f262a;
    private String b;

    public j(Context context) {
        this.f262a = a.a(context);
        this.b = c.j(context);
    }

    public e getNameValueMap() {
        e nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new d();
        }
        nameValueMap.a("placement", (Object) "INAPP_DOWNLOAD", true);
        b bVar = this.f262a;
        if (bVar != null) {
            nameValueMap.a("install_referrer", (Object) bVar.a(), true);
            nameValueMap.a("referrer_click_timestamp_seconds", (Object) Long.valueOf(this.f262a.b()), true);
            nameValueMap.a("install_begin_timestamp_seconds", (Object) Long.valueOf(this.f262a.c()), true);
        }
        nameValueMap.a("apkSig", (Object) this.b, true);
        return nameValueMap;
    }
}
