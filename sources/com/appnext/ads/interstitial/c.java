package com.appnext.ads.interstitial;

import com.appnext.core.f;
import com.appnext.core.p;
import java.util.HashMap;

public final class c extends p {
    private static c cn;
    private String aQ = ("https://cdn.appnext.com/tools/sdk/confign/interstitial/" + f.bi() + "/interstitial_config.txt");
    private HashMap<String, String> aR = null;

    public static synchronized c K() {
        c cVar;
        synchronized (c.class) {
            if (cn == null) {
                cn = new c();
            }
            cVar = cn;
        }
        return cVar;
    }

    private c() {
    }

    /* access modifiers changed from: protected */
    public final String getUrl() {
        return this.aQ;
    }

    /* access modifiers changed from: protected */
    public final HashMap<String, String> n() {
        return this.aR;
    }

    public final void setUrl(String str) {
        this.aQ = str;
    }

    public final void a(HashMap<String, String> hashMap) {
        this.aR = hashMap;
    }

    /* access modifiers changed from: protected */
    public final HashMap<String, String> o() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("creative", Interstitial.TYPE_MANAGED);
        hashMap.put("auto_play", "true");
        hashMap.put("mute", "false");
        hashMap.put("pview", "true");
        hashMap.put("min_internet_connection", "2g");
        hashMap.put("min_internet_connection_video", "3g");
        hashMap.put("urlApp_protection", "false");
        hashMap.put("can_close", "true");
        hashMap.put("video_length", "15");
        hashMap.put("button_text", "");
        hashMap.put("button_color", "");
        hashMap.put("skip_title", "");
        hashMap.put("remove_poster_on_auto_play", "true");
        hashMap.put("banner_expiration_time", "0");
        hashMap.put("show_rating", "true");
        hashMap.put("show_desc", "true");
        hashMap.put("new_button_text", "Install");
        hashMap.put("existing_button_text", "Open");
        hashMap.put("postpone_vta_sec", "0");
        hashMap.put("postpone_impression_sec", "0");
        hashMap.put("resolve_timeout", "8");
        hashMap.put("ads_caching_time_minutes", "0");
        hashMap.put("gdpr", "false");
        hashMap.put("clickType_A", "0");
        hashMap.put("didPrivacy", "false");
        hashMap.put("S1", "[{\"id\":\"multi\",\"p\":50},{\"id\":\"single\",\"p\":50}]");
        hashMap.put("stp_flag", "false");
        return hashMap;
    }
}
