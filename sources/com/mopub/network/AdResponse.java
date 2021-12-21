package com.mopub.network;

import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.util.DateAndTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class AdResponse implements Serializable {
    private static final long serialVersionUID = 1;
    private final String mAdGroupId;
    private final Integer mAdTimeoutDelayMillis;
    private final String mAdType;
    private final String mAdUnitId;
    private final List<String> mAfterLoadFailUrls;
    private final List<String> mAfterLoadSuccessUrls;
    private final List<String> mAfterLoadUrls;
    private final boolean mAllowCustomClose;
    private final String mBeforeLoadUrl;
    private final MoPub.BrowserAgent mBrowserAgent;
    private final String mClickTrackingUrl;
    private final String mCustomEventClassName;
    private final String mDspCreativeId;
    private final String mFailoverUrl;
    private final String mFullAdType;
    private final Integer mHeight;
    private final ImpressionData mImpressionData;
    private final List<String> mImpressionTrackingUrls;
    private final JSONObject mJsonBody;
    private final String mNetworkType;
    private final Integer mRefreshTimeMillis;
    private final String mRequestId;
    private final String mResponseBody;
    private final String mRewardedCurrencies;
    private final Integer mRewardedDuration;
    private final String mRewardedVideoCompletionUrl;
    private final String mRewardedVideoCurrencyAmount;
    private final String mRewardedVideoCurrencyName;
    private final Map<String, String> mServerExtras;
    private final boolean mShouldRewardOnClick;
    private final long mTimestamp;
    private final Integer mWidth;

    private AdResponse(Builder builder) {
        this.mAdType = builder.adType;
        this.mAdGroupId = builder.adGroupId;
        this.mAdUnitId = builder.adUnitId;
        this.mFullAdType = builder.fullAdType;
        this.mNetworkType = builder.networkType;
        this.mRewardedVideoCurrencyName = builder.rewardedVideoCurrencyName;
        this.mRewardedVideoCurrencyAmount = builder.rewardedVideoCurrencyAmount;
        this.mRewardedCurrencies = builder.rewardedCurrencies;
        this.mRewardedVideoCompletionUrl = builder.rewardedVideoCompletionUrl;
        this.mRewardedDuration = builder.rewardedDuration;
        this.mShouldRewardOnClick = builder.shouldRewardOnClick;
        this.mImpressionData = builder.impressionData;
        this.mClickTrackingUrl = builder.clickTrackingUrl;
        this.mImpressionTrackingUrls = builder.impressionTrackingUrls;
        this.mFailoverUrl = builder.failoverUrl;
        this.mBeforeLoadUrl = builder.beforeLoadUrl;
        this.mAfterLoadUrls = builder.afterLoadUrls;
        this.mAfterLoadSuccessUrls = builder.afterLoadSuccessUrls;
        this.mAfterLoadFailUrls = builder.afterLoadFailUrls;
        this.mRequestId = builder.requestId;
        this.mWidth = builder.width;
        this.mHeight = builder.height;
        this.mAdTimeoutDelayMillis = builder.adTimeoutDelayMillis;
        this.mRefreshTimeMillis = builder.refreshTimeMillis;
        this.mDspCreativeId = builder.dspCreativeId;
        this.mResponseBody = builder.responseBody;
        this.mJsonBody = builder.jsonBody;
        this.mCustomEventClassName = builder.customEventClassName;
        this.mBrowserAgent = builder.browserAgent;
        this.mServerExtras = builder.serverExtras;
        this.mTimestamp = DateAndTime.now().getTime();
        this.mAllowCustomClose = builder.allowCustomClose;
    }

    public boolean hasJson() {
        return this.mJsonBody != null;
    }

    public JSONObject getJsonBody() {
        return this.mJsonBody;
    }

    public String getStringBody() {
        return this.mResponseBody;
    }

    public String getAdType() {
        return this.mAdType;
    }

    public String getAdGroupId() {
        return this.mAdGroupId;
    }

    public String getFullAdType() {
        return this.mFullAdType;
    }

    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    public String getNetworkType() {
        return this.mNetworkType;
    }

    public String getRewardedVideoCurrencyName() {
        return this.mRewardedVideoCurrencyName;
    }

    public String getRewardedVideoCurrencyAmount() {
        return this.mRewardedVideoCurrencyAmount;
    }

    public String getRewardedCurrencies() {
        return this.mRewardedCurrencies;
    }

    public String getRewardedVideoCompletionUrl() {
        return this.mRewardedVideoCompletionUrl;
    }

    public Integer getRewardedDuration() {
        return this.mRewardedDuration;
    }

    public boolean shouldRewardOnClick() {
        return this.mShouldRewardOnClick;
    }

    public ImpressionData getImpressionData() {
        return this.mImpressionData;
    }

    public String getClickTrackingUrl() {
        return this.mClickTrackingUrl;
    }

    public List<String> getImpressionTrackingUrls() {
        return this.mImpressionTrackingUrls;
    }

    @Deprecated
    public String getFailoverUrl() {
        return this.mFailoverUrl;
    }

    public String getBeforeLoadUrl() {
        return this.mBeforeLoadUrl;
    }

    public List<String> getAfterLoadUrls() {
        return this.mAfterLoadUrls;
    }

    public List<String> getAfterLoadSuccessUrls() {
        return this.mAfterLoadSuccessUrls;
    }

    public List<String> getAfterLoadFailUrls() {
        return this.mAfterLoadFailUrls;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public Integer getWidth() {
        return this.mWidth;
    }

    public Integer getHeight() {
        return this.mHeight;
    }

    public Integer getAdTimeoutMillis(int i) {
        Integer num = this.mAdTimeoutDelayMillis;
        if (num == null || num.intValue() < 1000) {
            return Integer.valueOf(i);
        }
        return this.mAdTimeoutDelayMillis;
    }

    public Integer getRefreshTimeMillis() {
        return this.mRefreshTimeMillis;
    }

    public String getDspCreativeId() {
        return this.mDspCreativeId;
    }

    public String getCustomEventClassName() {
        return this.mCustomEventClassName;
    }

    public MoPub.BrowserAgent getBrowserAgent() {
        return this.mBrowserAgent;
    }

    public Map<String, String> getServerExtras() {
        return new TreeMap(this.mServerExtras);
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public boolean allowCustomClose() {
        return this.mAllowCustomClose;
    }

    public Builder toBuilder() {
        return new Builder().setAdType(this.mAdType).setAdGroupId(this.mAdGroupId).setNetworkType(this.mNetworkType).setRewardedVideoCurrencyName(this.mRewardedVideoCurrencyName).setRewardedVideoCurrencyAmount(this.mRewardedVideoCurrencyAmount).setRewardedCurrencies(this.mRewardedCurrencies).setRewardedVideoCompletionUrl(this.mRewardedVideoCompletionUrl).setRewardedDuration(this.mRewardedDuration).setShouldRewardOnClick(this.mShouldRewardOnClick).setImpressionData(this.mImpressionData).setClickTrackingUrl(this.mClickTrackingUrl).setImpressionTrackingUrls(this.mImpressionTrackingUrls).setFailoverUrl(this.mFailoverUrl).setBeforeLoadUrl(this.mBeforeLoadUrl).setAfterLoadUrls(this.mAfterLoadUrls).setAfterLoadSuccessUrls(this.mAfterLoadSuccessUrls).setAfterLoadFailUrls(this.mAfterLoadFailUrls).setDimensions(this.mWidth, this.mHeight).setAdTimeoutDelayMilliseconds(this.mAdTimeoutDelayMillis).setRefreshTimeMilliseconds(this.mRefreshTimeMillis).setDspCreativeId(this.mDspCreativeId).setResponseBody(this.mResponseBody).setJsonBody(this.mJsonBody).setCustomEventClassName(this.mCustomEventClassName).setBrowserAgent(this.mBrowserAgent).setAllowCustomClose(this.mAllowCustomClose).setServerExtras(this.mServerExtras);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String adGroupId;
        /* access modifiers changed from: private */
        public Integer adTimeoutDelayMillis;
        /* access modifiers changed from: private */
        public String adType;
        /* access modifiers changed from: private */
        public String adUnitId;
        /* access modifiers changed from: private */
        public List<String> afterLoadFailUrls = new ArrayList();
        /* access modifiers changed from: private */
        public List<String> afterLoadSuccessUrls = new ArrayList();
        /* access modifiers changed from: private */
        public List<String> afterLoadUrls = new ArrayList();
        /* access modifiers changed from: private */
        public boolean allowCustomClose = false;
        /* access modifiers changed from: private */
        public String beforeLoadUrl;
        /* access modifiers changed from: private */
        public MoPub.BrowserAgent browserAgent;
        /* access modifiers changed from: private */
        public String clickTrackingUrl;
        /* access modifiers changed from: private */
        public String customEventClassName;
        /* access modifiers changed from: private */
        public String dspCreativeId;
        /* access modifiers changed from: private */
        public String failoverUrl;
        /* access modifiers changed from: private */
        public String fullAdType;
        /* access modifiers changed from: private */
        public Integer height;
        /* access modifiers changed from: private */
        public ImpressionData impressionData;
        /* access modifiers changed from: private */
        public List<String> impressionTrackingUrls = new ArrayList();
        /* access modifiers changed from: private */
        public JSONObject jsonBody;
        /* access modifiers changed from: private */
        public String networkType;
        /* access modifiers changed from: private */
        public Integer refreshTimeMillis;
        /* access modifiers changed from: private */
        public String requestId;
        /* access modifiers changed from: private */
        public String responseBody;
        /* access modifiers changed from: private */
        public String rewardedCurrencies;
        /* access modifiers changed from: private */
        public Integer rewardedDuration;
        /* access modifiers changed from: private */
        public String rewardedVideoCompletionUrl;
        /* access modifiers changed from: private */
        public String rewardedVideoCurrencyAmount;
        /* access modifiers changed from: private */
        public String rewardedVideoCurrencyName;
        /* access modifiers changed from: private */
        public Map<String, String> serverExtras = new TreeMap();
        /* access modifiers changed from: private */
        public boolean shouldRewardOnClick;
        /* access modifiers changed from: private */
        public Integer width;

        public Builder setAdType(String str) {
            this.adType = str;
            return this;
        }

        public Builder setAdGroupId(String str) {
            this.adGroupId = str;
            return this;
        }

        public Builder setAdUnitId(String str) {
            this.adUnitId = str;
            return this;
        }

        public Builder setFullAdType(String str) {
            this.fullAdType = str;
            return this;
        }

        public Builder setNetworkType(String str) {
            this.networkType = str;
            return this;
        }

        public Builder setRewardedVideoCurrencyName(String str) {
            this.rewardedVideoCurrencyName = str;
            return this;
        }

        public Builder setRewardedVideoCurrencyAmount(String str) {
            this.rewardedVideoCurrencyAmount = str;
            return this;
        }

        public Builder setRewardedCurrencies(String str) {
            this.rewardedCurrencies = str;
            return this;
        }

        public Builder setRewardedVideoCompletionUrl(String str) {
            this.rewardedVideoCompletionUrl = str;
            return this;
        }

        public Builder setRewardedDuration(Integer num) {
            this.rewardedDuration = num;
            return this;
        }

        public Builder setShouldRewardOnClick(boolean z) {
            this.shouldRewardOnClick = z;
            return this;
        }

        public Builder setImpressionData(ImpressionData impressionData2) {
            this.impressionData = impressionData2;
            return this;
        }

        public Builder setClickTrackingUrl(String str) {
            this.clickTrackingUrl = str;
            return this;
        }

        public Builder setImpressionTrackingUrls(List<String> list) {
            Preconditions.checkNotNull(list);
            this.impressionTrackingUrls = list;
            return this;
        }

        public Builder setFailoverUrl(String str) {
            this.failoverUrl = str;
            return this;
        }

        public Builder setBeforeLoadUrl(String str) {
            this.beforeLoadUrl = str;
            return this;
        }

        public Builder setAfterLoadUrls(List<String> list) {
            Preconditions.checkNotNull(list);
            this.afterLoadUrls = list;
            return this;
        }

        public Builder setAfterLoadSuccessUrls(List<String> list) {
            Preconditions.checkNotNull(list);
            this.afterLoadSuccessUrls = list;
            return this;
        }

        public Builder setAfterLoadFailUrls(List<String> list) {
            Preconditions.checkNotNull(list);
            this.afterLoadFailUrls = list;
            return this;
        }

        public Builder setRequestId(String str) {
            this.requestId = str;
            return this;
        }

        public Builder setDimensions(Integer num, Integer num2) {
            this.width = num;
            this.height = num2;
            return this;
        }

        public Builder setAdTimeoutDelayMilliseconds(Integer num) {
            this.adTimeoutDelayMillis = num;
            return this;
        }

        public Builder setRefreshTimeMilliseconds(Integer num) {
            this.refreshTimeMillis = num;
            return this;
        }

        public Builder setDspCreativeId(String str) {
            this.dspCreativeId = str;
            return this;
        }

        public Builder setResponseBody(String str) {
            this.responseBody = str;
            return this;
        }

        public Builder setJsonBody(JSONObject jSONObject) {
            this.jsonBody = jSONObject;
            return this;
        }

        public Builder setCustomEventClassName(String str) {
            this.customEventClassName = str;
            return this;
        }

        public Builder setBrowserAgent(MoPub.BrowserAgent browserAgent2) {
            this.browserAgent = browserAgent2;
            return this;
        }

        public Builder setServerExtras(Map<String, String> map) {
            if (map == null) {
                this.serverExtras = new TreeMap();
            } else {
                this.serverExtras = new TreeMap(map);
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAllowCustomClose(boolean z) {
            this.allowCustomClose = z;
            return this;
        }

        public AdResponse build() {
            return new AdResponse(this);
        }
    }
}
