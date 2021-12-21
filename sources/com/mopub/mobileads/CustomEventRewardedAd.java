package com.mopub.mobileads;

import android.app.Activity;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MoPubLifecycleManager;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;

public abstract class CustomEventRewardedAd {
    /* access modifiers changed from: protected */
    public abstract boolean checkAndInitializeSdk(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception;

    /* access modifiers changed from: protected */
    public abstract String getAdNetworkId();

    /* access modifiers changed from: protected */
    public abstract LifecycleListener getLifecycleListener();

    /* access modifiers changed from: protected */
    public abstract boolean isReady();

    /* access modifiers changed from: protected */
    public abstract void loadWithSdkInitialized(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void onInvalidate();

    /* access modifiers changed from: protected */
    public abstract void show();

    /* access modifiers changed from: package-private */
    public final void loadCustomEvent(Activity activity, Map<String, Object> map, Map<String, String> map2) {
        try {
            if (checkAndInitializeSdk(activity, map, map2)) {
                MoPubLifecycleManager.getInstance(activity).addLifecycleListener(getLifecycleListener());
            }
            loadWithSdkInitialized(activity, map, map2);
        } catch (Exception e) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, e.getMessage());
        }
    }
}
