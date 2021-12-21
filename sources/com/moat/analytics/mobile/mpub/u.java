package com.moat.analytics.mobile.mpub;

import android.media.MediaPlayer;
import android.view.View;
import com.mopub.mobileads.VastIconXmlManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class u extends h implements NativeVideoTracker {
    private WeakReference<MediaPlayer> m;

    u(String str) {
        super(str);
        p.a(3, "NativeVideoTracker", (Object) this, "In initialization method.");
        if (str == null || str.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("PartnerCode is ");
            sb.append(str == null ? "null" : "empty");
            String sb2 = sb.toString();
            p.a("[ERROR] ", 3, "NativeVideoTracker", this, "NativeDisplayTracker creation problem, " + sb2);
            this.f1154a = new n(sb2);
        }
        p.a("[SUCCESS] ", a() + " created");
    }

    private void a(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.getCurrentPosition();
            } catch (Exception unused) {
                throw new n("Playback has already completed");
            }
        } else {
            throw new n("Null player instance");
        }
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "NativeVideoTracker";
    }

    /* access modifiers changed from: package-private */
    public void a(List<String> list) {
        if (!n()) {
            list.add("Player is null");
        }
        super.a(list);
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> i() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.m.get();
        HashMap hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put(VastIconXmlManager.DURATION, Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public boolean n() {
        WeakReference<MediaPlayer> weakReference = this.m;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public Integer o() {
        return Integer.valueOf(((MediaPlayer) this.m.get()).getCurrentPosition());
    }

    /* access modifiers changed from: package-private */
    public boolean q() {
        return ((MediaPlayer) this.m.get()).isPlaying();
    }

    /* access modifiers changed from: package-private */
    public Integer r() {
        return Integer.valueOf(((MediaPlayer) this.m.get()).getDuration());
    }

    public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        try {
            c();
            d();
            a(mediaPlayer);
            this.m = new WeakReference<>(mediaPlayer);
            return super.a(map, view);
        } catch (Exception e) {
            n.a(e);
            String a2 = n.a("trackVideoAd", e);
            if (this.d != null) {
                this.d.onTrackingFailedToStart(a2);
            }
            p.a(3, "NativeVideoTracker", (Object) this, a2);
            p.a("[ERROR] ", a() + " " + a2);
            return false;
        }
    }
}
