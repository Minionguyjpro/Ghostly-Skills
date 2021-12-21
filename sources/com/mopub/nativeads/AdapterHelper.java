package com.mopub.nativeads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.lang.ref.WeakReference;

public final class AdapterHelper {
    private final Context mApplicationContext;
    private final WeakReference<Context> mContext;
    private final int mInterval;
    private final int mStart;

    public AdapterHelper(Context context, int i, int i2) {
        Preconditions.checkNotNull(context, "Context cannot be null.");
        boolean z = true;
        Preconditions.checkArgument(i >= 0, "start position must be non-negative");
        Preconditions.checkArgument(i2 < 2 ? false : z, "interval must be at least 2");
        this.mContext = new WeakReference<>(context);
        this.mApplicationContext = context.getApplicationContext();
        this.mStart = i;
        this.mInterval = i2;
    }

    public View getAdView(View view, ViewGroup viewGroup, NativeAd nativeAd, ViewBinder viewBinder) {
        Context context = (Context) this.mContext.get();
        if (context != null) {
            return NativeAdViewHelper.getAdView(view, viewGroup, context, nativeAd);
        }
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Weak reference to Context in AdapterHelper became null. Returning empty view.");
        return new View(this.mApplicationContext);
    }

    public View getAdView(View view, ViewGroup viewGroup, NativeAd nativeAd) {
        return getAdView(view, viewGroup, nativeAd, (ViewBinder) null);
    }

    public int shiftedCount(int i) {
        return i + numberOfAdsThatCouldFitWithContent(i);
    }

    public int shiftedPosition(int i) {
        return i - numberOfAdsSeenUpToPosition(i);
    }

    public boolean isAdPosition(int i) {
        int i2 = this.mStart;
        if (i >= i2 && (i - i2) % this.mInterval == 0) {
            return true;
        }
        return false;
    }

    private int numberOfAdsSeenUpToPosition(int i) {
        int i2 = this.mStart;
        if (i <= i2) {
            return 0;
        }
        double d = (double) (i - i2);
        double d2 = (double) this.mInterval;
        Double.isNaN(d);
        Double.isNaN(d2);
        return ((int) Math.floor(d / d2)) + 1;
    }

    private int numberOfAdsThatCouldFitWithContent(int i) {
        int i2 = this.mStart;
        if (i <= i2) {
            return 0;
        }
        int i3 = this.mInterval - 1;
        if ((i - i2) % i3 == 0) {
            return (i - i2) / i3;
        }
        double d = (double) (i - i2);
        double d2 = (double) i3;
        Double.isNaN(d);
        Double.isNaN(d2);
        return ((int) Math.floor(d / d2)) + 1;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void clearContext() {
        this.mContext.clear();
    }
}
