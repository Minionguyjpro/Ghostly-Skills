package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public interface g {
    boolean a(String str);

    String a_();

    Long getAdCacheTtl();

    Long getLastLoadTime();

    Ad.AdState getState();

    boolean getVideoCancelCallBack();

    boolean hasAdCacheTtlPassed();

    boolean isBelowMinCPM();

    boolean isReady();

    boolean load(AdPreferences adPreferences, AdEventListener adEventListener);

    void setActivityExtra(a aVar);

    void setContext(Context context);

    void setVideoCancelCallBack(boolean z);
}
