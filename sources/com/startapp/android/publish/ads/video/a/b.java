package com.startapp.android.publish.ads.video.a;

import android.text.TextUtils;
import com.startapp.android.publish.ads.video.c.a.a;
import com.startapp.android.publish.ads.video.tracking.VideoTrackingLink;
import com.startapp.android.publish.ads.video.tracking.VideoTrackingParams;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.common.a.g;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private VideoTrackingLink[] f107a;
    private VideoTrackingParams b;
    private String c;
    private int d;
    private String e = "";
    private a f;

    public b(VideoTrackingLink[] videoTrackingLinkArr, VideoTrackingParams videoTrackingParams, String str, int i) {
        this.f107a = videoTrackingLinkArr;
        this.b = videoTrackingParams;
        this.c = str;
        this.d = i;
    }

    public b a(a aVar) {
        this.f = aVar;
        return this;
    }

    public b a(String str) {
        this.e = str;
        return this;
    }

    public a a() {
        if (!b()) {
            g.a("VideoEventBuilder", 3, "Some fields have illegal values");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (VideoTrackingLink videoTrackingLink : this.f107a) {
            if (videoTrackingLink.getTrackingUrl() == null) {
                g.a("VideoEventBuilder", 5, "Ignoring tracking link - tracking url is null: tracking link = " + videoTrackingLink);
            } else if (this.b.getOffset() <= 0 || videoTrackingLink.shouldAppendReplay()) {
                arrayList.add(a(videoTrackingLink));
            } else {
                g.a("VideoEventBuilder", 3, "Ignoring tracking link - external replay event: tracking link = " + videoTrackingLink);
            }
        }
        return new a(arrayList, this.e);
    }

    private boolean b() {
        return (this.f107a == null || this.b == null) ? false : true;
    }

    private String a(VideoTrackingLink videoTrackingLink) {
        StringBuilder sb = new StringBuilder();
        VideoTrackingParams b2 = b(videoTrackingLink);
        String trackingUrl = videoTrackingLink.getTrackingUrl();
        sb.append(b(trackingUrl));
        sb.append(b2.getQueryString());
        if (b2.getInternalTrackingParamsIndicator()) {
            sb.append(com.startapp.common.a.a.a(c.e(trackingUrl)));
        }
        return sb.toString();
    }

    private VideoTrackingParams b(VideoTrackingLink videoTrackingLink) {
        VideoTrackingLink.TrackingSource trackingSource = videoTrackingLink.getTrackingSource();
        return this.b.setInternalTrackingParamsIndicator(trackingSource != null && trackingSource == VideoTrackingLink.TrackingSource.STARTAPP).setShouldAppendOffset(videoTrackingLink.shouldAppendReplay()).setReplayParameter(videoTrackingLink.getReplayParameter());
    }

    private String b(String str) {
        String str2 = this.c;
        String replace = str.replace("[ASSETURI]", str2 != null ? TextUtils.htmlEncode(str2) : "").replace("[CONTENTPLAYHEAD]", TextUtils.htmlEncode(a(this.d))).replace("[CACHEBUSTING]", TextUtils.htmlEncode(c())).replace("[TIMESTAMP]", TextUtils.htmlEncode(d()));
        a aVar = this.f;
        return aVar != null ? replace.replace("[ERRORCODE]", String.valueOf(aVar.a())) : replace;
    }

    private static String c() {
        return String.valueOf(new SecureRandom().nextInt(90000000) + 10000000);
    }

    private static String a(int i) {
        long convert = TimeUnit.SECONDS.convert((long) i, TimeUnit.MILLISECONDS);
        long j = convert / 3600;
        long j2 = (long) (i % 1000);
        return String.format(Locale.US, "%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(j), Long.valueOf((convert % 3600) / 60), Long.valueOf(convert % 60), Long.valueOf(j2)});
    }

    private static String d() {
        String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(new Date());
        int length = format.length() - 2;
        return format.substring(0, length) + ":" + format.substring(length);
    }
}
