package com.yandex.metrica.impl;

import android.text.TextUtils;
import com.yandex.metrica.PreloadInfo;
import com.yandex.metrica.impl.utils.j;
import org.json.JSONException;
import org.json.JSONObject;

public class an {

    /* renamed from: a  reason: collision with root package name */
    private PreloadInfo f723a;

    public an(PreloadInfo preloadInfo) {
        if (preloadInfo == null) {
            return;
        }
        if (TextUtils.isEmpty(preloadInfo.getTrackingId())) {
            j.f().c("Required field \"PreloadInfo.trackingId\" is empty!\nThis preload info will be skipped.");
        } else {
            this.f723a = preloadInfo;
        }
    }

    /* access modifiers changed from: package-private */
    public String a() {
        if (this.f723a == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("preloadInfo", b());
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public JSONObject b() {
        if (this.f723a == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackingId", this.f723a.getTrackingId());
            if (!this.f723a.getAdditionalParams().isEmpty()) {
                jSONObject.put("additionalParams", new JSONObject(this.f723a.getAdditionalParams()));
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
