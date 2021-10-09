package com.mopub.nativeads;

import android.view.View;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

public abstract class BaseNativeAd {
    private final Set<String> mClickTrackers = new HashSet();
    private final Set<String> mImpressionTrackers = new HashSet();
    private boolean mInvalidated = false;
    private NativeEventListener mNativeEventListener;

    public interface NativeEventListener {
        void onAdClicked();

        void onAdImpressed();
    }

    public abstract void clear(View view);

    public abstract void destroy();

    public abstract void prepare(View view);

    protected BaseNativeAd() {
    }

    public void setNativeEventListener(NativeEventListener nativeEventListener) {
        this.mNativeEventListener = nativeEventListener;
    }

    /* access modifiers changed from: protected */
    public final void notifyAdImpressed() {
        NativeEventListener nativeEventListener = this.mNativeEventListener;
        if (nativeEventListener != null) {
            nativeEventListener.onAdImpressed();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyAdClicked() {
        NativeEventListener nativeEventListener = this.mNativeEventListener;
        if (nativeEventListener != null) {
            nativeEventListener.onAdClicked();
        }
    }

    /* access modifiers changed from: protected */
    public final void addImpressionTrackers(Object obj) throws ClassCastException {
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    addImpressionTracker(jSONArray.getString(i));
                } catch (JSONException unused) {
                    MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Unable to parse impression trackers.");
                }
            }
            return;
        }
        throw new ClassCastException("Expected impression trackers of type JSONArray.");
    }

    /* access modifiers changed from: protected */
    public final void addClickTrackers(Object obj) throws ClassCastException {
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    addClickTracker(jSONArray.getString(i));
                } catch (JSONException unused) {
                    MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Unable to parse click trackers.");
                }
            }
            return;
        }
        throw new ClassCastException("Expected click trackers of type JSONArray.");
    }

    public final void addImpressionTracker(String str) {
        if (Preconditions.NoThrow.checkNotNull(str, "impressionTracker url is not allowed to be null")) {
            this.mImpressionTrackers.add(str);
        }
    }

    public final void addClickTracker(String str) {
        if (Preconditions.NoThrow.checkNotNull(str, "clickTracker url is not allowed to be null")) {
            this.mClickTrackers.add(str);
        }
    }

    /* access modifiers changed from: package-private */
    public Set<String> getImpressionTrackers() {
        return new HashSet(this.mImpressionTrackers);
    }

    /* access modifiers changed from: package-private */
    public Set<String> getClickTrackers() {
        return new HashSet(this.mClickTrackers);
    }

    public void invalidate() {
        this.mInvalidated = true;
    }

    public boolean isInvalidated() {
        return this.mInvalidated;
    }
}
