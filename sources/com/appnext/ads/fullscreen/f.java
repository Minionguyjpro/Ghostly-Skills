package com.appnext.ads.fullscreen;

import com.appnext.core.p;
import java.util.HashMap;

public final class f extends p {
    private static f bt;
    private String aQ = ("https://cdn.appnext.com/tools/sdk/confign/rewarded/" + com.appnext.core.f.bi() + "/rewarded_config.txt");
    private HashMap<String, String> aR = null;

    public static synchronized f q() {
        f fVar;
        synchronized (f.class) {
            if (bt == null) {
                bt = new f();
            }
            fVar = bt;
        }
        return fVar;
    }

    private f() {
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
        hashMap.put("can_close", "true");
        hashMap.put("show_close", "false");
        hashMap.put("video_length", "15");
        hashMap.put("mute", "false");
        hashMap.put("urlApp_protection", "false");
        hashMap.put("pview", "true");
        hashMap.put("min_internet_connection_video", "3g");
        hashMap.put("banner_expiration_time", "0");
        hashMap.put("default_mode", "normal");
        hashMap.put("postpone_vta_sec", "0");
        hashMap.put("postpone_impression_sec", "0");
        hashMap.put("resolve_timeout", "8");
        hashMap.put("num_saved_videos", "5");
        hashMap.put("caption_text_time", "3");
        hashMap.put("pre_title_string1", "Which Ad");
        hashMap.put("pre_title_string2", "Would you like to watch?");
        hashMap.put("pre_cta_string", "Play this ad");
        hashMap.put("ads_caching_time_minutes", "0");
        hashMap.put("gdpr", "false");
        hashMap.put("clickType_a", "0");
        hashMap.put("clickType_b", "0");
        hashMap.put("didPrivacy", "false");
        hashMap.put("stp_flag", "false");
        return hashMap;
    }
}
