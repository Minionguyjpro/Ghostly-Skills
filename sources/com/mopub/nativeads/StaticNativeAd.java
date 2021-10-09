package com.mopub.nativeads;

import android.view.View;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.util.HashMap;
import java.util.Map;

public abstract class StaticNativeAd extends BaseNativeAd implements ClickInterface, ImpressionInterface {
    private static final int DEFAULT_IMPRESSION_MIN_PERCENTAGE_VIEWED = 50;
    private static final int DEFAULT_IMPRESSION_MIN_TIME_VIEWED_MS = 1000;
    static final double MAX_STAR_RATING = 5.0d;
    static final double MIN_STAR_RATING = 0.0d;
    private String mCallToAction;
    private String mClickDestinationUrl;
    private final Map<String, Object> mExtras = new HashMap();
    private String mIconImageUrl;
    private int mImpressionMinPercentageViewed = 50;
    private int mImpressionMinTimeViewed = 1000;
    private Integer mImpressionMinVisiblePx = null;
    private boolean mImpressionRecorded;
    private String mMainImageUrl;
    private String mPrivacyInformationIconClickThroughUrl;
    private String mPrivacyInformationIconImageUrl;
    private String mSponsored;
    private Double mStarRating;
    private String mText;
    private String mTitle;

    public void clear(View view) {
    }

    public void handleClick(View view) {
    }

    public void prepare(View view) {
    }

    public void recordImpression(View view) {
    }

    public final String getTitle() {
        return this.mTitle;
    }

    public final String getText() {
        return this.mText;
    }

    public final String getMainImageUrl() {
        return this.mMainImageUrl;
    }

    public final String getIconImageUrl() {
        return this.mIconImageUrl;
    }

    public final String getCallToAction() {
        return this.mCallToAction;
    }

    public final Double getStarRating() {
        return this.mStarRating;
    }

    public final String getPrivacyInformationIconClickThroughUrl() {
        return this.mPrivacyInformationIconClickThroughUrl;
    }

    public String getPrivacyInformationIconImageUrl() {
        return this.mPrivacyInformationIconImageUrl;
    }

    public String getSponsored() {
        return this.mSponsored;
    }

    public final Object getExtra(String str) {
        if (!Preconditions.NoThrow.checkNotNull(str, "getExtra key is not allowed to be null")) {
            return null;
        }
        return this.mExtras.get(str);
    }

    public final Map<String, Object> getExtras() {
        return new HashMap(this.mExtras);
    }

    public final String getClickDestinationUrl() {
        return this.mClickDestinationUrl;
    }

    public final void setMainImageUrl(String str) {
        this.mMainImageUrl = str;
    }

    public final void setIconImageUrl(String str) {
        this.mIconImageUrl = str;
    }

    public final void setClickDestinationUrl(String str) {
        this.mClickDestinationUrl = str;
    }

    public final void setCallToAction(String str) {
        this.mCallToAction = str;
    }

    public final void setTitle(String str) {
        this.mTitle = str;
    }

    public final void setText(String str) {
        this.mText = str;
    }

    public final void setStarRating(Double d) {
        if (d == null) {
            this.mStarRating = null;
        } else if (d.doubleValue() < 0.0d || d.doubleValue() > MAX_STAR_RATING) {
            MoPubLog.AdLogEvent adLogEvent = MoPubLog.AdLogEvent.CUSTOM;
            MoPubLog.log(adLogEvent, "Ignoring attempt to set invalid star rating (" + d + "). Must be between " + 0.0d + " and " + MAX_STAR_RATING + ".");
        } else {
            this.mStarRating = d;
        }
    }

    public final void setPrivacyInformationIconClickThroughUrl(String str) {
        this.mPrivacyInformationIconClickThroughUrl = str;
    }

    public final void setPrivacyInformationIconImageUrl(String str) {
        this.mPrivacyInformationIconImageUrl = str;
    }

    public final void setSponsored(String str) {
        this.mSponsored = str;
    }

    public final void addExtra(String str, Object obj) {
        if (Preconditions.NoThrow.checkNotNull(str, "addExtra key is not allowed to be null")) {
            this.mExtras.put(str, obj);
        }
    }

    public final void setImpressionMinTimeViewed(int i) {
        if (i > 0) {
            this.mImpressionMinTimeViewed = i;
            return;
        }
        MoPubLog.AdLogEvent adLogEvent = MoPubLog.AdLogEvent.CUSTOM;
        MoPubLog.log(adLogEvent, "Ignoring non-positive impressionMinTimeViewed: " + i);
    }

    public final void setImpressionMinPercentageViewed(int i) {
        if (i < 0 || i > 100) {
            MoPubLog.AdLogEvent adLogEvent = MoPubLog.AdLogEvent.CUSTOM;
            MoPubLog.log(adLogEvent, "Ignoring impressionMinTimeViewed that's not a percent [0, 100]: " + i);
            return;
        }
        this.mImpressionMinPercentageViewed = i;
    }

    public final void setImpressionMinVisiblePx(Integer num) {
        if (num == null || num.intValue() <= 0) {
            MoPubLog.AdLogEvent adLogEvent = MoPubLog.AdLogEvent.CUSTOM;
            MoPubLog.log(adLogEvent, "Ignoring null or non-positive impressionMinVisiblePx: " + num);
            return;
        }
        this.mImpressionMinVisiblePx = num;
    }

    public void destroy() {
        invalidate();
    }

    public final int getImpressionMinPercentageViewed() {
        return this.mImpressionMinPercentageViewed;
    }

    public final int getImpressionMinTimeViewed() {
        return this.mImpressionMinTimeViewed;
    }

    public final Integer getImpressionMinVisiblePx() {
        return this.mImpressionMinVisiblePx;
    }

    public final boolean isImpressionRecorded() {
        return this.mImpressionRecorded;
    }

    public final void setImpressionRecorded() {
        this.mImpressionRecorded = true;
    }
}
