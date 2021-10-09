package com.startapp.android.publish.ads.video;

import android.content.Context;
import com.mopub.common.AdType;
import com.startapp.android.publish.ads.video.c;
import com.startapp.android.publish.ads.video.c.a.d;
import com.startapp.android.publish.ads.video.g;
import com.startapp.android.publish.ads.video.h;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.e;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.cache.c;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.android.publish.html.a;
import com.startapp.common.a.h;

/* compiled from: StartAppSDK */
public class b extends a {
    private long i = System.currentTimeMillis();
    private volatile c j;
    private com.startapp.android.publish.ads.video.c.a.c k;
    private int l = 0;

    public b(Context context, Ad ad, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, ad, adPreferences, adEventListener, AdPreferences.Placement.INAPP_OVERLAY, true);
        com.startapp.android.publish.ads.video.c.a.c cVar;
        if (com.startapp.android.publish.adsCommon.b.a().H().r() == 0) {
            cVar = new com.startapp.android.publish.ads.video.c.a.c(context);
        } else {
            cVar = new d(context, com.startapp.android.publish.adsCommon.b.a().H().s());
        }
        this.k = cVar;
    }

    /* access modifiers changed from: protected */
    public boolean a(Object obj) {
        h.a aVar = (h.a) obj;
        String str = null;
        if (aVar == null || !aVar.b().toLowerCase().contains(AdType.STATIC_NATIVE)) {
            if (aVar != null) {
                str = aVar.a();
            }
            if (com.startapp.android.publish.adsCommon.b.a().H().h() && b(str)) {
                b(false);
            }
            return super.a(obj);
        }
        if (com.startapp.android.publish.adsCommon.b.a().H().h() && !this.h.hasCampaignExclude()) {
            b(true);
        }
        try {
            VASTJson vASTJson = (VASTJson) com.startapp.common.c.b.a(aVar.a(), VASTJson.class);
            if (vASTJson == null || vASTJson.getVastTag() == null) {
                return a("no VAST wrapper in json", (Throwable) null, true);
            }
            com.startapp.android.publish.ads.video.c.b.b bVar = new com.startapp.android.publish.ads.video.c.b.b(com.startapp.android.publish.adsCommon.b.a().H().n(), com.startapp.android.publish.adsCommon.b.a().H().o());
            com.startapp.android.publish.ads.video.c.a.a a2 = bVar.a(this.f230a, vASTJson.getVastTag(), this.k);
            ((e) this.b).a(bVar.a(), this.b.getType() != Ad.AdType.REWARDED_VIDEO);
            if (vASTJson.getTtlSec() != null) {
                ((e) this.b).d(vASTJson.getTtlSec());
            }
            if (a2 == com.startapp.android.publish.ads.video.c.a.a.ErrorNone) {
                a(com.startapp.android.publish.ads.video.c.a.a.SAProcessSuccess);
                aVar.a(vASTJson.getAdmTag());
                aVar.b("text/html");
                return super.a((Object) aVar);
            }
            a(a2);
            if (vASTJson.getCampaignId() != null) {
                this.g.add(vASTJson.getCampaignId());
            }
            this.l++;
            ((e) this.b).e();
            if (System.currentTimeMillis() - this.i >= ((long) com.startapp.android.publish.adsCommon.b.a().H().p())) {
                return a("VAST retry timeout", (Throwable) null, false);
            }
            if (this.l > com.startapp.android.publish.adsCommon.b.a().H().q()) {
                return a("VAST too many excludes", (Throwable) null, false);
            }
            return d().booleanValue();
        } catch (Exception e) {
            return a("VAST json parsing", e, true);
        }
    }

    /* access modifiers changed from: protected */
    public void a(final Boolean bool) {
        super.a(bool);
        if (!bool.booleanValue() || !h()) {
            a(bool.booleanValue());
            return;
        }
        if (com.startapp.android.publish.adsCommon.b.a().H().i()) {
            super.b(bool);
        }
        b().setVideoMuted(this.c.isVideoMuted());
        d.a().a(this.f230a.getApplicationContext(), b().getVideoUrl(), new g.a() {
            public void a(String str) {
                if (str != null) {
                    if (!str.equals("downloadInterrupted")) {
                        b.super.b(bool);
                        b.this.b().setLocalVideoPath(str);
                    }
                    b.this.a(bool.booleanValue());
                    return;
                }
                b.this.a(false);
                b.this.d.onFailedToReceiveAd(b.this.b);
                b.this.a(com.startapp.android.publish.ads.video.c.a.a.FileNotFound);
            }
        }, new c.a() {
            public void a(String str) {
                b.this.b().setLocalVideoPath(str);
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean a(GetAdRequest getAdRequest) {
        h.a a2;
        if (!super.a(getAdRequest)) {
            return false;
        }
        if (!getAdRequest.isAdTypeVideo() || (a2 = h.a(this.f230a)) == h.a.ELIGIBLE) {
            return true;
        }
        this.f = a2.a();
        return false;
    }

    /* access modifiers changed from: protected */
    public GetAdRequest a() {
        return b((GetAdRequest) new a());
    }

    private boolean h() {
        return b() != null;
    }

    /* access modifiers changed from: protected */
    public void b(Boolean bool) {
        if (!h()) {
            super.b(bool);
        }
    }

    /* access modifiers changed from: package-private */
    public VideoAdDetails b() {
        return ((e) this.b).d();
    }

    private void b(boolean z) {
        AdPreferences adPreferences;
        if ((this.b.getType() != Ad.AdType.REWARDED_VIDEO && this.b.getType() != Ad.AdType.VIDEO) || z) {
            if (this.c == null) {
                adPreferences = new AdPreferences();
            } else {
                adPreferences = new AdPreferences(this.c);
            }
            AdPreferences adPreferences2 = adPreferences;
            adPreferences2.setType((this.b.getType() == Ad.AdType.REWARDED_VIDEO || this.b.getType() == Ad.AdType.VIDEO) ? Ad.AdType.VIDEO_NO_VAST : Ad.AdType.NON_VIDEO);
            com.startapp.android.publish.cache.c a2 = com.startapp.android.publish.cache.a.a().a(this.f230a, (StartAppAd) null, this.e, adPreferences2, (AdEventListener) null);
            if (z) {
                this.j = a2;
            }
        }
    }

    private boolean b(String str) {
        return i.a(str, "@videoJson@", "@videoJson@") != null;
    }

    /* JADX WARNING: type inference failed for: r2v9, types: [com.startapp.android.publish.ads.video.tracking.VideoTrackingLink[]] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.startapp.android.publish.ads.video.c.a.a r10) {
        /*
            r9 = this;
            java.lang.String r0 = ""
            r1 = 0
            com.startapp.android.publish.ads.video.VideoAdDetails r2 = r9.b()     // Catch:{ Exception -> 0x00bb }
            if (r2 == 0) goto L_0x001f
            com.startapp.android.publish.ads.video.VideoAdDetails r2 = r9.b()     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.tracking.VideoTrackingDetails r2 = r2.getVideoTrackingDetails()     // Catch:{ Exception -> 0x00bb }
            if (r2 == 0) goto L_0x001f
            com.startapp.android.publish.ads.video.VideoAdDetails r1 = r9.b()     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.tracking.VideoTrackingDetails r1 = r1.getVideoTrackingDetails()     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.tracking.ActionTrackingLink[] r1 = r1.getInlineErrorTrackingUrls()     // Catch:{ Exception -> 0x00bb }
        L_0x001f:
            if (r1 == 0) goto L_0x00c9
            int r2 = r1.length     // Catch:{ Exception -> 0x00bb }
            if (r2 <= 0) goto L_0x00c9
            com.startapp.android.publish.ads.video.c.a.a r2 = com.startapp.android.publish.ads.video.c.a.a.SAShowBeforeVast     // Catch:{ Exception -> 0x00bb }
            r3 = 0
            if (r10 == r2) goto L_0x002d
            com.startapp.android.publish.ads.video.c.a.a r2 = com.startapp.android.publish.ads.video.c.a.a.SAProcessSuccess     // Catch:{ Exception -> 0x00bb }
            if (r10 != r2) goto L_0x0093
        L_0x002d:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x0085 }
            r2.<init>()     // Catch:{ Exception -> 0x0085 }
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x0085 }
            com.startapp.android.publish.common.metaData.MetaData r5 = com.startapp.android.publish.common.metaData.MetaData.getInstance()     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = r5.getAdPlatformHost()     // Catch:{ Exception -> 0x0085 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r4 = r4.getHost()     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = "\\."
            java.lang.String[] r4 = r4.split(r5)     // Catch:{ Exception -> 0x0085 }
            r5 = 1
            r4 = r4[r5]     // Catch:{ Exception -> 0x0085 }
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ Exception -> 0x0085 }
            int r5 = r1.length     // Catch:{ Exception -> 0x0085 }
            r6 = 0
        L_0x0052:
            if (r6 >= r5) goto L_0x0070
            r7 = r1[r6]     // Catch:{ Exception -> 0x0085 }
            java.lang.String r8 = r7.getTrackingUrl()     // Catch:{ Exception -> 0x0085 }
            if (r8 == 0) goto L_0x006d
            java.lang.String r8 = r7.getTrackingUrl()     // Catch:{ Exception -> 0x0085 }
            java.lang.String r8 = r8.toLowerCase()     // Catch:{ Exception -> 0x0085 }
            boolean r8 = r8.contains(r4)     // Catch:{ Exception -> 0x0085 }
            if (r8 == 0) goto L_0x006d
            r2.add(r7)     // Catch:{ Exception -> 0x0085 }
        L_0x006d:
            int r6 = r6 + 1
            goto L_0x0052
        L_0x0070:
            int r4 = r2.size()     // Catch:{ Exception -> 0x0085 }
            if (r4 <= 0) goto L_0x0084
            int r4 = r2.size()     // Catch:{ Exception -> 0x0085 }
            com.startapp.android.publish.ads.video.tracking.VideoTrackingLink[] r4 = new com.startapp.android.publish.ads.video.tracking.VideoTrackingLink[r4]     // Catch:{ Exception -> 0x0085 }
            java.lang.Object[] r2 = r2.toArray(r4)     // Catch:{ Exception -> 0x0085 }
            com.startapp.android.publish.ads.video.tracking.VideoTrackingLink[] r2 = (com.startapp.android.publish.ads.video.tracking.VideoTrackingLink[]) r2     // Catch:{ Exception -> 0x0085 }
            r1 = r2
            goto L_0x0093
        L_0x0084:
            return
        L_0x0085:
            r2 = move-exception
            android.content.Context r4 = r9.f230a     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.adsCommon.f.d r5 = com.startapp.android.publish.adsCommon.f.d.EXCEPTION     // Catch:{ Exception -> 0x00bb }
            java.lang.String r6 = "GetVideoEnabledService.sendVideoErrorEvent filter sa links"
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.adsCommon.f.f.a(r4, r5, r6, r2, r0)     // Catch:{ Exception -> 0x00bb }
        L_0x0093:
            com.startapp.android.publish.ads.video.tracking.VideoTrackingParams r2 = new com.startapp.android.publish.ads.video.tracking.VideoTrackingParams     // Catch:{ Exception -> 0x00bb }
            java.lang.String r4 = "1"
            r2.<init>(r0, r3, r3, r4)     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.a.b r4 = new com.startapp.android.publish.ads.video.a.b     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.VideoAdDetails r5 = r9.b()     // Catch:{ Exception -> 0x00bb }
            java.lang.String r5 = r5.getVideoUrl()     // Catch:{ Exception -> 0x00bb }
            r4.<init>(r1, r2, r5, r3)     // Catch:{ Exception -> 0x00bb }
            java.lang.String r1 = "error"
            com.startapp.android.publish.ads.video.a.b r1 = r4.a((java.lang.String) r1)     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.a.b r10 = r1.a((com.startapp.android.publish.ads.video.c.a.a) r10)     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.a.a r10 = r10.a()     // Catch:{ Exception -> 0x00bb }
            android.content.Context r1 = r9.f230a     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.h.a((android.content.Context) r1, (com.startapp.android.publish.ads.video.a.a) r10)     // Catch:{ Exception -> 0x00bb }
            goto L_0x00c9
        L_0x00bb:
            r10 = move-exception
            android.content.Context r1 = r9.f230a
            com.startapp.android.publish.adsCommon.f.d r2 = com.startapp.android.publish.adsCommon.f.d.EXCEPTION
            java.lang.String r10 = r10.getMessage()
            java.lang.String r3 = "GetVideoEnabledService.sendVideoErrorEvent"
            com.startapp.android.publish.adsCommon.f.f.a(r1, r2, r3, r10, r0)
        L_0x00c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.video.b.a(com.startapp.android.publish.ads.video.c.a.a):void");
    }

    private boolean a(String str, Throwable th, boolean z) {
        com.startapp.common.a.g.a("GetVideoEnabledService", 6, str, th);
        if (z) {
            f.a(this.f230a, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, str, th != null ? th.getMessage() : "", "");
        }
        com.startapp.android.publish.adsCommon.g a2 = com.startapp.android.publish.cache.a.a().a(this.j);
        if (a2 instanceof e) {
            h.a aVar = new h.a();
            aVar.b("text/html");
            aVar.a(((e) a2).f());
            return super.a((Object) aVar);
        }
        this.b.setErrorMessage(this.f);
        return false;
    }
}
