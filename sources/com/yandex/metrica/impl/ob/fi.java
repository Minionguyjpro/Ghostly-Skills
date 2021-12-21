package com.yandex.metrica.impl.ob;

import android.net.Uri;
import android.os.Build;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

class fi extends fo {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, String> f905a;

    public fi(String str, Map<String, String> map) {
        super(0, str, (JSONObject) null);
        this.f905a = map;
    }

    public String a() {
        String a2 = super.a();
        Map<String, String> map = this.f905a;
        Uri.Builder buildUpon = Uri.parse(a2).buildUpon();
        for (Map.Entry next : map.entrySet()) {
            buildUpon.appendQueryParameter((String) next.getKey(), (String) next.getValue());
        }
        return buildUpon.build().toString();
    }

    public Map<String, String> b() {
        HashMap hashMap = new HashMap();
        String format = String.format(Locale.US, "%s.%s.%s", new Object[]{2, 12, 20});
        String str = Build.DEVICE;
        String str2 = Build.VERSION.RELEASE;
        hashMap.put("User-agent", String.format(Locale.US, "com.yandex.mobile.pinning/%s (%s; Android %s)", new Object[]{format, str, str2}));
        return hashMap;
    }
}
