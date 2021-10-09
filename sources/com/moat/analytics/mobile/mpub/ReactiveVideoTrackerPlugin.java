package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.mpub.x;
import java.util.Map;

public class ReactiveVideoTrackerPlugin implements MoatPlugin<ReactiveVideoTracker> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final String f1149a;

    static class a implements ReactiveVideoTracker {
        a() {
        }

        public void changeTargetView(View view) {
        }

        public void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        public void removeListener() {
        }

        public void removeVideoListener() {
        }

        public void setActivity(Activity activity) {
        }

        public void setListener(TrackerListener trackerListener) {
        }

        public void setPlayerVolume(Double d) {
        }

        public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        }

        public void stopTracking() {
        }

        public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
            return false;
        }
    }

    public ReactiveVideoTrackerPlugin(String str) {
        this.f1149a = str;
    }

    /* renamed from: c */
    public ReactiveVideoTracker a() {
        return (ReactiveVideoTracker) x.a(new x.a<ReactiveVideoTracker>() {
            public com.moat.analytics.mobile.mpub.a.b.a<ReactiveVideoTracker> a() {
                p.a("[INFO] ", "Attempting to create ReactiveVideoTracker");
                return com.moat.analytics.mobile.mpub.a.b.a.a(new y(ReactiveVideoTrackerPlugin.this.f1149a));
            }
        }, ReactiveVideoTracker.class);
    }

    /* renamed from: d */
    public ReactiveVideoTracker b() {
        return new a();
    }
}
