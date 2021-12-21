package com.mopub.nativeads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.nativeads.BaseNativeAd;
import com.mopub.network.AdResponse;
import com.mopub.network.ImpressionData;
import com.mopub.network.SingleImpression;
import com.mopub.network.TrackingRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NativeAd {
    private final String mAdUnitId;
    private final BaseNativeAd mBaseNativeAd;
    private final Set<String> mClickTrackers;
    private final Context mContext;
    private ImpressionData mImpressionData;
    private final Set<String> mImpressionTrackers;
    private boolean mIsClicked;
    private boolean mIsDestroyed;
    private final MoPubAdRenderer mMoPubAdRenderer;
    private MoPubNativeEventListener mMoPubNativeEventListener;
    private boolean mRecordedImpression;

    public interface MoPubNativeEventListener {
        void onClick(View view);

        void onImpression(View view);
    }

    public NativeAd(Context context, List<String> list, String str, String str2, BaseNativeAd baseNativeAd, MoPubAdRenderer moPubAdRenderer) {
        this.mContext = context.getApplicationContext();
        this.mAdUnitId = str2;
        this.mImpressionData = null;
        HashSet hashSet = new HashSet();
        this.mImpressionTrackers = hashSet;
        hashSet.addAll(list);
        this.mImpressionTrackers.addAll(baseNativeAd.getImpressionTrackers());
        HashSet hashSet2 = new HashSet();
        this.mClickTrackers = hashSet2;
        hashSet2.add(str);
        this.mClickTrackers.addAll(baseNativeAd.getClickTrackers());
        this.mBaseNativeAd = baseNativeAd;
        baseNativeAd.setNativeEventListener(new BaseNativeAd.NativeEventListener() {
            public void onAdImpressed() {
                NativeAd.this.recordImpression((View) null);
            }

            public void onAdClicked() {
                NativeAd.this.handleClick((View) null);
            }
        });
        this.mMoPubAdRenderer = moPubAdRenderer;
    }

    NativeAd(Context context, AdResponse adResponse, String str, BaseNativeAd baseNativeAd, MoPubAdRenderer moPubAdRenderer) {
        this(context, adResponse.getImpressionTrackingUrls(), adResponse.getClickTrackingUrl(), str, baseNativeAd, moPubAdRenderer);
        this.mImpressionData = adResponse.getImpressionData();
    }

    public String toString() {
        return "\n" + "impressionTrackers" + ":" + this.mImpressionTrackers + "\n" + "clickTrackers" + ":" + this.mClickTrackers + "\n" + "recordedImpression" + ":" + this.mRecordedImpression + "\n" + "isClicked" + ":" + this.mIsClicked + "\n" + "isDestroyed" + ":" + this.mIsDestroyed + "\n";
    }

    public void setMoPubNativeEventListener(MoPubNativeEventListener moPubNativeEventListener) {
        this.mMoPubNativeEventListener = moPubNativeEventListener;
    }

    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    public boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    public BaseNativeAd getBaseNativeAd() {
        return this.mBaseNativeAd;
    }

    public View createAdView(Context context, ViewGroup viewGroup) {
        return this.mMoPubAdRenderer.createAdView(context, viewGroup);
    }

    public void renderAdView(View view) {
        this.mMoPubAdRenderer.renderAdView(view, this.mBaseNativeAd);
    }

    public MoPubAdRenderer getMoPubAdRenderer() {
        return this.mMoPubAdRenderer;
    }

    public void prepare(View view) {
        if (!this.mIsDestroyed) {
            this.mBaseNativeAd.prepare(view);
        }
    }

    public void clear(View view) {
        if (!this.mIsDestroyed) {
            this.mBaseNativeAd.clear(view);
        }
    }

    public void destroy() {
        if (!this.mIsDestroyed) {
            this.mBaseNativeAd.destroy();
            this.mIsDestroyed = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void recordImpression(View view) {
        if (!this.mRecordedImpression && !this.mIsDestroyed) {
            this.mRecordedImpression = true;
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) this.mImpressionTrackers, this.mContext);
            MoPubNativeEventListener moPubNativeEventListener = this.mMoPubNativeEventListener;
            if (moPubNativeEventListener != null) {
                moPubNativeEventListener.onImpression(view);
            }
            new SingleImpression(this.mAdUnitId, this.mImpressionData).sendImpression();
        }
    }

    /* access modifiers changed from: package-private */
    public void handleClick(View view) {
        if (!this.mIsClicked && !this.mIsDestroyed) {
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) this.mClickTrackers, this.mContext);
            MoPubNativeEventListener moPubNativeEventListener = this.mMoPubNativeEventListener;
            if (moPubNativeEventListener != null) {
                moPubNativeEventListener.onClick(view);
            }
            this.mIsClicked = true;
        }
    }
}
