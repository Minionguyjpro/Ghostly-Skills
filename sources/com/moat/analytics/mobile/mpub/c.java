package com.moat.analytics.mobile.mpub;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import com.moat.analytics.mobile.mpub.g;
import com.mopub.mobileads.VastIconXmlManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

abstract class c extends b {
    static final MoatAdEventType[] g = {MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    final Map<MoatAdEventType, Integer> h;
    final Handler i;
    Map<String, String> j;
    WeakReference<View> k;
    private final Set<MoatAdEventType> l;
    /* access modifiers changed from: private */
    public VideoTrackerListener m;
    private boolean n;
    private Double o;
    /* access modifiers changed from: private */
    public final g p;
    private final String q;

    c(String str) {
        super((View) null, false, true);
        p.a(3, "BaseVideoTracker", (Object) this, "Initializing.");
        this.q = str;
        g gVar = new g(a.a(), g.a.VIDEO);
        this.p = gVar;
        super.a(gVar.b);
        try {
            super.a(this.p.f1157a);
        } catch (n e) {
            this.f1154a = e;
        }
        this.h = new HashMap();
        this.l = new HashSet();
        this.i = new Handler();
        this.n = false;
        this.o = Double.valueOf(1.0d);
    }

    private static boolean a(MoatAdEventType moatAdEventType) {
        return moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED;
    }

    private void b(MoatAdEvent moatAdEvent) {
        JSONObject a2 = a(moatAdEvent);
        p.a(3, "BaseVideoTracker", (Object) this, String.format("Received event: %s", new Object[]{a2.toString()}));
        p.a("[SUCCESS] ", a() + String.format(" Received event: %s", new Object[]{a2.toString()}));
        if (e() && this.c != null) {
            this.c.a(this.p.c, a2);
            if (!this.l.contains(moatAdEvent.d)) {
                this.l.add(moatAdEvent.d);
                VideoTrackerListener videoTrackerListener = this.m;
                if (videoTrackerListener != null) {
                    videoTrackerListener.onVideoEventReported(moatAdEvent.d);
                }
            }
        }
        MoatAdEventType moatAdEventType = moatAdEvent.d;
        if (a(moatAdEventType)) {
            this.h.put(moatAdEventType, 1);
            if (this.c != null) {
                this.c.c((b) this);
            }
            l();
        }
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.c.doubleValue())) {
            moatAdEvent.c = this.o;
        }
        return new JSONObject(moatAdEvent.a());
    }

    /* access modifiers changed from: package-private */
    public void a(List<String> list) {
        if (this.j == null) {
            list.add("Null adIds object");
        }
        if (list.isEmpty()) {
            super.a(list);
            return;
        }
        throw new n(TextUtils.join(" and ", list));
    }

    /* access modifiers changed from: package-private */
    public boolean a(Integer num, Integer num2) {
        int abs = Math.abs(num2.intValue() - num.intValue());
        double intValue = (double) num2.intValue();
        Double.isNaN(intValue);
        return ((double) abs) <= Math.min(750.0d, intValue * 0.05d);
    }

    public boolean a(Map<String, String> map, View view) {
        try {
            c();
            d();
            if (view == null) {
                p.a(3, "BaseVideoTracker", (Object) this, "trackVideoAd received null video view instance");
            }
            this.j = map;
            this.k = new WeakReference<>(view);
            b();
            String format = String.format("trackVideoAd tracking ids: %s | view: %s", new Object[]{new JSONObject(map).toString(), p.a(view)});
            p.a(3, "BaseVideoTracker", (Object) this, format);
            p.a("[SUCCESS] ", a() + " " + format);
            if (this.d != null) {
                this.d.onTrackingStarted(g());
            }
            return true;
        } catch (Exception e) {
            a("trackVideoAd", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        super.changeTargetView((View) this.k.get());
        super.b();
        Map<String, Object> i2 = i();
        Integer num = (Integer) i2.get("width");
        Integer num2 = (Integer) i2.get("height");
        Integer num3 = (Integer) i2.get(VastIconXmlManager.DURATION);
        p.a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", new Object[]{num2, num, num3}));
        this.p.a(this.q, this.j, num, num2, num3);
    }

    public void changeTargetView(View view) {
        p.a(3, "BaseVideoTracker", (Object) this, "changing view to " + p.a(view));
        this.k = new WeakReference<>(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            n.a(e);
        }
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            b(moatAdEvent);
        } catch (Exception e) {
            n.a(e);
        }
    }

    /* access modifiers changed from: package-private */
    public abstract Map<String, Object> i();

    /* access modifiers changed from: package-private */
    public Double j() {
        return Double.valueOf(k().doubleValue() * l.a().b());
    }

    /* access modifiers changed from: package-private */
    public Double k() {
        return this.o;
    }

    /* access modifiers changed from: package-private */
    public void l() {
        if (!this.n) {
            this.n = true;
            this.i.postDelayed(new Runnable() {
                public void run() {
                    try {
                        p.a(3, "BaseVideoTracker", (Object) this, "Shutting down.");
                        c.this.p.a();
                        VideoTrackerListener unused = c.this.m = null;
                    } catch (Exception e) {
                        n.a(e);
                    }
                }
            }, 500);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean m() {
        return this.h.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.h.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.h.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    public void removeVideoListener() {
        this.m = null;
    }

    public void setPlayerVolume(Double d) {
        Double j2 = j();
        if (!d.equals(this.o)) {
            p.a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "player volume changed to %f ", new Object[]{d}));
            this.o = d;
            if (!j2.equals(j())) {
                dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.f1146a, this.o));
            }
        }
    }

    public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        this.m = videoTrackerListener;
    }

    public void stopTracking() {
        try {
            super.stopTracking();
            l();
            if (this.m != null) {
                this.m = null;
            }
        } catch (Exception e) {
            n.a(e);
        }
    }
}
