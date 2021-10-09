package com.moat.analytics.mobile.mpub;

import android.view.View;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

class y extends c implements ReactiveVideoTracker {
    private Integer l;

    public y(String str) {
        super(str);
        p.a(3, "ReactiveVideoTracker", (Object) this, "Initializing.");
        p.a("[SUCCESS] ", a() + " created");
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "ReactiveVideoTracker";
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE && !moatAdEvent.b.equals(MoatAdEvent.f1146a) && !a(moatAdEvent.b, this.l)) {
            moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.a(moatAdEvent);
    }

    /* access modifiers changed from: package-private */
    public void a(List<String> list) {
        if (this.l.intValue() >= 1000) {
            super.a(list);
            return;
        }
        throw new n(String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{this.l}));
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> i() {
        Integer num;
        HashMap hashMap = new HashMap();
        View view = (View) this.k.get();
        int i = 0;
        if (view != null) {
            i = Integer.valueOf(view.getWidth());
            num = Integer.valueOf(view.getHeight());
        } else {
            num = 0;
        }
        hashMap.put(VastIconXmlManager.DURATION, this.l);
        hashMap.put("width", i);
        hashMap.put("height", num);
        return hashMap;
    }

    public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        try {
            c();
            d();
            this.l = num;
            return super.a(map, view);
        } catch (Exception e) {
            a("trackVideoAd", e);
            return false;
        }
    }
}
