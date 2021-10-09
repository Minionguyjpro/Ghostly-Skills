package com.moat.analytics.mobile.mpub;

import android.graphics.Rect;
import android.view.View;
import com.moat.analytics.mobile.mpub.NativeDisplayTracker;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class t extends b implements NativeDisplayTracker {
    private final Map<String, String> g;
    private final Set<NativeDisplayTracker.MoatUserInteractionType> h = new HashSet();

    t(View view, Map<String, String> map) {
        super(view, true, false);
        n e;
        n nVar;
        p.a(3, "NativeDisplayTracker", (Object) this, "Initializing.");
        this.g = map;
        if (view == null) {
            p.a("[ERROR] ", 3, "NativeDisplayTracker", this, "NativeDisplayTracker initialization not successful, " + "Target view is null");
            nVar = new n("Target view is null");
        } else {
            if (map == null || map.isEmpty()) {
                p.a("[ERROR] ", 3, "NativeDisplayTracker", this, "NativeDisplayTracker initialization not successful, " + "AdIds is null or empty");
                e = new n("AdIds is null or empty");
            } else {
                g gVar = ((k) k.getInstance()).c;
                if (gVar == null) {
                    p.a("[ERROR] ", 3, "NativeDisplayTracker", this, "NativeDisplayTracker initialization not successful, " + "prepareNativeDisplayTracking was not called successfully");
                    nVar = new n("prepareNativeDisplayTracking was not called successfully");
                } else {
                    super.a(gVar.b);
                    try {
                        super.a(gVar.f1157a);
                        i();
                        p.a("[SUCCESS] ", a() + " created for " + g() + ", with adIds:" + map.toString());
                        return;
                    } catch (n e2) {
                        e = e2;
                    }
                }
            }
            this.f1154a = e;
            return;
        }
        this.f1154a = nVar;
    }

    private static String a(Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < 8; i++) {
            String str = "moatClientLevel" + i;
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        for (int i2 = 0; i2 < 8; i2++) {
            String str2 = "moatClientSlicer" + i2;
            if (map.containsKey(str2)) {
                linkedHashMap.put(str2, map.get(str2));
            }
        }
        for (String next : map.keySet()) {
            if (!linkedHashMap.containsKey(next)) {
                linkedHashMap.put(next, map.get(next));
            }
        }
        return new JSONObject(linkedHashMap).toString();
    }

    private void i() {
        if (this.c != null) {
            this.c.a(j());
        }
    }

    private String j() {
        try {
            String a2 = a(this.g);
            p.a(3, "NativeDisplayTracker", (Object) this, "Parsed ad ids = " + a2);
            return "{\"adIds\":" + a2 + ", \"adKey\":\"" + this.e + "\", \"adSize\":" + k() + "}";
        } catch (Exception e) {
            n.a(e);
            return "";
        }
    }

    private String k() {
        try {
            Rect a2 = z.a(super.f());
            int width = a2.width();
            int height = a2.height();
            HashMap hashMap = new HashMap();
            hashMap.put("width", Integer.toString(width));
            hashMap.put("height", Integer.toString(height));
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            n.a(e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "NativeDisplayTracker";
    }

    public void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        try {
            p.a(3, "NativeDisplayTracker", (Object) this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (!this.h.contains(moatUserInteractionType)) {
                this.h.add(moatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.e);
                jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
                if (this.c != null) {
                    this.c.b(jSONObject.toString());
                }
            }
        } catch (JSONException e) {
            e = e;
            p.b(2, "NativeDisplayTracker", this, "Got JSON exception");
            n.a(e);
        } catch (Exception e2) {
            e = e2;
            n.a(e);
        }
    }
}
