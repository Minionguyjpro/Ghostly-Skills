package com.startapp.android.publish.ads.video;

import android.content.Context;
import android.util.Pair;
import com.startapp.android.publish.ads.video.h;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.Utils.d;
import com.startapp.android.publish.adsCommon.Utils.e;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;

/* compiled from: StartAppSDK */
public class a extends GetAdRequest {

    /* renamed from: a  reason: collision with root package name */
    private GetAdRequest.VideoRequestType f105a;
    private GetAdRequest.VideoRequestMode b = GetAdRequest.VideoRequestMode.INTERSTITIAL;

    public void fillAdPreferences(Context context, AdPreferences adPreferences, AdPreferences.Placement placement, Pair<String, String> pair) {
        super.fillAdPreferences(context, adPreferences, placement, pair);
        a(context);
        a();
    }

    private void a() {
        if (getType() == Ad.AdType.REWARDED_VIDEO) {
            this.b = GetAdRequest.VideoRequestMode.REWARDED;
        }
        if (getType() == Ad.AdType.VIDEO) {
            this.b = GetAdRequest.VideoRequestMode.INTERSTITIAL;
        }
    }

    private void a(Context context) {
        if (getType() != null) {
            if (getType() == Ad.AdType.NON_VIDEO) {
                this.f105a = GetAdRequest.VideoRequestType.DISABLED;
            } else if (getType() == Ad.AdType.VIDEO_NO_VAST) {
                this.f105a = GetAdRequest.VideoRequestType.FORCED_NONVAST;
            } else if (isAdTypeVideo()) {
                this.f105a = GetAdRequest.VideoRequestType.FORCED;
            }
        } else if (h.a(context) != h.a.ELIGIBLE) {
            this.f105a = GetAdRequest.VideoRequestType.DISABLED;
        } else if (!i.a(2)) {
            this.f105a = GetAdRequest.VideoRequestType.FORCED;
        } else {
            this.f105a = GetAdRequest.VideoRequestType.ENABLED;
        }
    }

    public e getNameValueMap() {
        e nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new d();
        }
        nameValueMap.a("video", (Object) this.f105a, false);
        nameValueMap.a("videoMode", (Object) this.b, false);
        return nameValueMap;
    }
}
