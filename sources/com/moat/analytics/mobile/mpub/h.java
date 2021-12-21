package com.moat.analytics.mobile.mpub;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;
import org.json.JSONObject;

abstract class h extends c {
    int l = RecyclerView.UNDEFINED_DURATION;
    private a m = a.UNINITIALIZED;
    private int n = RecyclerView.UNDEFINED_DURATION;
    private double o = Double.NaN;
    private int p = RecyclerView.UNDEFINED_DURATION;
    private int q = 0;

    enum a {
        UNINITIALIZED,
        PAUSED,
        PLAYING,
        STOPPED,
        COMPLETED
    }

    h(String str) {
        super(str);
    }

    private void t() {
        this.i.postDelayed(new Runnable() {
            public void run() {
                h hVar;
                try {
                    if (!h.this.n() || h.this.m()) {
                        hVar = h.this;
                    } else if (Boolean.valueOf(h.this.s()).booleanValue()) {
                        h.this.i.postDelayed(this, 200);
                        return;
                    } else {
                        hVar = h.this;
                    }
                    hVar.l();
                } catch (Exception e) {
                    h.this.l();
                    n.a(e);
                }
            }
        }, 200);
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        Integer num;
        int i;
        if (!moatAdEvent.b.equals(MoatAdEvent.f1146a)) {
            num = moatAdEvent.b;
        } else {
            try {
                num = o();
            } catch (Exception unused) {
                num = Integer.valueOf(this.n);
            }
            moatAdEvent.b = num;
        }
        if (moatAdEvent.b.intValue() < 0 || (moatAdEvent.b.intValue() == 0 && moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE && this.n > 0)) {
            num = Integer.valueOf(this.n);
            moatAdEvent.b = num;
        }
        if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE) {
            if (num.intValue() == Integer.MIN_VALUE || (i = this.l) == Integer.MIN_VALUE || !a(num, Integer.valueOf(i))) {
                this.m = a.STOPPED;
                moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.m = a.COMPLETED;
            }
        }
        return super.a(moatAdEvent);
    }

    public boolean a(Map<String, String> map, View view) {
        try {
            boolean a2 = super.a(map, view);
            if (!a2 || !p()) {
                return a2;
            }
            t();
            return a2;
        } catch (Exception e) {
            p.a(3, "IntervalVideoTracker", (Object) this, "Problem with video loop");
            a("trackVideoAd", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract boolean n();

    /* access modifiers changed from: package-private */
    public abstract Integer o();

    /* access modifiers changed from: protected */
    public boolean p() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean q();

    /* access modifiers changed from: package-private */
    public abstract Integer r();

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x009d A[Catch:{ Exception -> 0x00d4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009f A[Catch:{ Exception -> 0x00d4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00bf A[Catch:{ Exception -> 0x00d4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean s() {
        /*
            r12 = this;
            boolean r0 = r12.n()
            r1 = 0
            if (r0 == 0) goto L_0x00df
            boolean r0 = r12.m()
            if (r0 == 0) goto L_0x000f
            goto L_0x00df
        L_0x000f:
            r0 = 1
            java.lang.Integer r2 = r12.o()     // Catch:{ Exception -> 0x00d4 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x00d4 }
            int r3 = r12.n     // Catch:{ Exception -> 0x00d4 }
            if (r3 < 0) goto L_0x001f
            if (r2 >= 0) goto L_0x001f
            return r1
        L_0x001f:
            r12.n = r2     // Catch:{ Exception -> 0x00d4 }
            if (r2 != 0) goto L_0x0024
            return r0
        L_0x0024:
            java.lang.Integer r3 = r12.r()     // Catch:{ Exception -> 0x00d4 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x00d4 }
            boolean r4 = r12.q()     // Catch:{ Exception -> 0x00d4 }
            double r5 = (double) r3
            r7 = 4616189618054758400(0x4010000000000000, double:4.0)
            java.lang.Double.isNaN(r5)
            double r5 = r5 / r7
            java.lang.Double r7 = r12.j()     // Catch:{ Exception -> 0x00d4 }
            double r7 = r7.doubleValue()     // Catch:{ Exception -> 0x00d4 }
            r9 = 0
            int r10 = r12.p     // Catch:{ Exception -> 0x00d4 }
            if (r2 <= r10) goto L_0x0046
            r12.p = r2     // Catch:{ Exception -> 0x00d4 }
        L_0x0046:
            int r10 = r12.l     // Catch:{ Exception -> 0x00d4 }
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r10 != r11) goto L_0x004e
            r12.l = r3     // Catch:{ Exception -> 0x00d4 }
        L_0x004e:
            if (r4 == 0) goto L_0x0090
            com.moat.analytics.mobile.mpub.h$a r3 = r12.m     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r4 = com.moat.analytics.mobile.mpub.h.a.UNINITIALIZED     // Catch:{ Exception -> 0x00d4 }
            if (r3 != r4) goto L_0x005d
            com.moat.analytics.mobile.mpub.MoatAdEventType r9 = com.moat.analytics.mobile.mpub.MoatAdEventType.AD_EVT_START     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r3 = com.moat.analytics.mobile.mpub.h.a.PLAYING     // Catch:{ Exception -> 0x00d4 }
        L_0x005a:
            r12.m = r3     // Catch:{ Exception -> 0x00d4 }
            goto L_0x009b
        L_0x005d:
            com.moat.analytics.mobile.mpub.h$a r3 = r12.m     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r4 = com.moat.analytics.mobile.mpub.h.a.PAUSED     // Catch:{ Exception -> 0x00d4 }
            if (r3 != r4) goto L_0x0068
            com.moat.analytics.mobile.mpub.MoatAdEventType r9 = com.moat.analytics.mobile.mpub.MoatAdEventType.AD_EVT_PLAYING     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r3 = com.moat.analytics.mobile.mpub.h.a.PLAYING     // Catch:{ Exception -> 0x00d4 }
            goto L_0x005a
        L_0x0068:
            double r3 = (double) r2
            java.lang.Double.isNaN(r3)
            double r3 = r3 / r5
            double r3 = java.lang.Math.floor(r3)     // Catch:{ Exception -> 0x00d4 }
            int r3 = (int) r3     // Catch:{ Exception -> 0x00d4 }
            int r3 = r3 - r0
            r4 = -1
            if (r3 <= r4) goto L_0x009b
            r4 = 3
            if (r3 >= r4) goto L_0x009b
            com.moat.analytics.mobile.mpub.MoatAdEventType[] r4 = g     // Catch:{ Exception -> 0x00d4 }
            r3 = r4[r3]     // Catch:{ Exception -> 0x00d4 }
            java.util.Map r4 = r12.h     // Catch:{ Exception -> 0x00d4 }
            boolean r4 = r4.containsKey(r3)     // Catch:{ Exception -> 0x00d4 }
            if (r4 != 0) goto L_0x009b
            java.util.Map r4 = r12.h     // Catch:{ Exception -> 0x00d4 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x00d4 }
            r4.put(r3, r5)     // Catch:{ Exception -> 0x00d4 }
            r9 = r3
            goto L_0x009b
        L_0x0090:
            com.moat.analytics.mobile.mpub.h$a r3 = r12.m     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r4 = com.moat.analytics.mobile.mpub.h.a.PAUSED     // Catch:{ Exception -> 0x00d4 }
            if (r3 == r4) goto L_0x009b
            com.moat.analytics.mobile.mpub.MoatAdEventType r9 = com.moat.analytics.mobile.mpub.MoatAdEventType.AD_EVT_PAUSED     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r3 = com.moat.analytics.mobile.mpub.h.a.PAUSED     // Catch:{ Exception -> 0x00d4 }
            goto L_0x005a
        L_0x009b:
            if (r9 == 0) goto L_0x009f
            r3 = 1
            goto L_0x00a0
        L_0x009f:
            r3 = 0
        L_0x00a0:
            if (r3 != 0) goto L_0x00bd
            double r4 = r12.o     // Catch:{ Exception -> 0x00d4 }
            boolean r4 = java.lang.Double.isNaN(r4)     // Catch:{ Exception -> 0x00d4 }
            if (r4 != 0) goto L_0x00bd
            double r4 = r12.o     // Catch:{ Exception -> 0x00d4 }
            double r4 = r4 - r7
            double r4 = java.lang.Math.abs(r4)     // Catch:{ Exception -> 0x00d4 }
            r10 = 4587366580439587226(0x3fa999999999999a, double:0.05)
            int r6 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x00bd
            com.moat.analytics.mobile.mpub.MoatAdEventType r9 = com.moat.analytics.mobile.mpub.MoatAdEventType.AD_EVT_VOLUME_CHANGE     // Catch:{ Exception -> 0x00d4 }
            r3 = 1
        L_0x00bd:
            if (r3 == 0) goto L_0x00cf
            com.moat.analytics.mobile.mpub.MoatAdEvent r3 = new com.moat.analytics.mobile.mpub.MoatAdEvent     // Catch:{ Exception -> 0x00d4 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x00d4 }
            java.lang.Double r4 = r12.k()     // Catch:{ Exception -> 0x00d4 }
            r3.<init>(r9, r2, r4)     // Catch:{ Exception -> 0x00d4 }
            r12.dispatchEvent(r3)     // Catch:{ Exception -> 0x00d4 }
        L_0x00cf:
            r12.o = r7     // Catch:{ Exception -> 0x00d4 }
            r12.q = r1     // Catch:{ Exception -> 0x00d4 }
            return r0
        L_0x00d4:
            int r2 = r12.q
            int r3 = r2 + 1
            r12.q = r3
            r3 = 5
            if (r2 >= r3) goto L_0x00df
            r1 = 1
        L_0x00df:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.h.s():boolean");
    }

    public void setPlayerVolume(Double d) {
        super.setPlayerVolume(d);
        this.o = j().doubleValue();
    }

    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            super.stopTracking();
        } catch (Exception e) {
            n.a(e);
        }
    }
}
