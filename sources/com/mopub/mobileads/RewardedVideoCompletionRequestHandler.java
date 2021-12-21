package com.mopub.mobileads;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.RewardedVideoCompletionRequest;
import com.mopub.network.Networking;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.VolleyError;

public class RewardedVideoCompletionRequestHandler implements RewardedVideoCompletionRequest.RewardedVideoCompletionRequestListener {
    private static final String API_VERSION_KEY = "&v=";
    private static final String CUSTOMER_ID_KEY = "&customer_id=";
    private static final String CUSTOM_DATA_KEY = "&rcd=";
    private static final String CUSTOM_EVENT_CLASS_NAME_KEY = "&cec=";
    static final int MAX_RETRIES = 17;
    static final int REQUEST_TIMEOUT_DELAY = 1000;
    static final int[] RETRY_TIMES = {5000, 10000, 20000, 40000, 60000};
    private static final String REWARD_AMOUNT_KEY = "&rca=";
    private static final String REWARD_NAME_KEY = "&rcn=";
    private static final String SDK_VERSION_KEY = "&nv=";
    private final Context mContext;
    private final Handler mHandler;
    private final RequestQueue mRequestQueue;
    private int mRetryCount;
    private volatile boolean mShouldStop;
    private final String mUrl;

    RewardedVideoCompletionRequestHandler(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        this(context, str, str2, str3, str4, str5, str6, new Handler());
    }

    RewardedVideoCompletionRequestHandler(Context context, String str, String str2, String str3, String str4, String str5, String str6, Handler handler) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str3);
        Preconditions.checkNotNull(str4);
        Preconditions.checkNotNull(handler);
        this.mUrl = appendParameters(str, str2, str3, str4, str5, str6);
        this.mRetryCount = 0;
        this.mHandler = handler;
        this.mRequestQueue = Networking.getRequestQueue(context);
        this.mContext = context.getApplicationContext();
    }

    /* access modifiers changed from: package-private */
    public void makeRewardedVideoCompletionRequest() {
        if (this.mShouldStop) {
            this.mRequestQueue.cancelAll((Object) this.mUrl);
            return;
        }
        RewardedVideoCompletionRequest rewardedVideoCompletionRequest = new RewardedVideoCompletionRequest(this.mContext, this.mUrl, new DefaultRetryPolicy(getTimeout(this.mRetryCount) - 1000, 0, 0.0f), this);
        rewardedVideoCompletionRequest.setTag(this.mUrl);
        this.mRequestQueue.add(rewardedVideoCompletionRequest);
        if (this.mRetryCount >= 17) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Exceeded number of retries for rewarded video completion request.");
            return;
        }
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                RewardedVideoCompletionRequestHandler.this.makeRewardedVideoCompletionRequest();
            }
        }, (long) getTimeout(this.mRetryCount));
        this.mRetryCount++;
    }

    public void onResponse(Integer num) {
        if (num == null) {
            return;
        }
        if (num.intValue() < 500 || num.intValue() >= 600) {
            this.mShouldStop = true;
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (volleyError != null && volleyError.networkResponse != null) {
            if (volleyError.networkResponse.statusCode < 500 || volleyError.networkResponse.statusCode >= 600) {
                this.mShouldStop = true;
            }
        }
    }

    public static void makeRewardedVideoCompletionRequest(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (context != null && !TextUtils.isEmpty(str) && str3 != null && str4 != null) {
            new RewardedVideoCompletionRequestHandler(context, str, str2, str3, str4, str5, str6).makeRewardedVideoCompletionRequest();
        }
    }

    static int getTimeout(int i) {
        if (i >= 0) {
            int[] iArr = RETRY_TIMES;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        int[] iArr2 = RETRY_TIMES;
        return iArr2[iArr2.length - 1];
    }

    private static String appendParameters(String str, String str2, String str3, String str4, String str5, String str6) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str3);
        Preconditions.checkNotNull(str4);
        StringBuilder sb = new StringBuilder(str);
        sb.append(CUSTOMER_ID_KEY);
        String str7 = "";
        sb.append(str2 == null ? str7 : Uri.encode(str2));
        sb.append(REWARD_NAME_KEY);
        sb.append(Uri.encode(str3));
        sb.append(REWARD_AMOUNT_KEY);
        sb.append(Uri.encode(str4));
        sb.append(SDK_VERSION_KEY);
        sb.append(Uri.encode("5.12.0"));
        sb.append(API_VERSION_KEY);
        sb.append(1);
        sb.append(CUSTOM_EVENT_CLASS_NAME_KEY);
        if (str5 != null) {
            str7 = Uri.encode(str5);
        }
        sb.append(str7);
        if (!TextUtils.isEmpty(str6)) {
            sb.append(CUSTOM_DATA_KEY);
            sb.append(Uri.encode(str6));
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean getShouldStop() {
        return this.mShouldStop;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public int getRetryCount() {
        return this.mRetryCount;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setRetryCount(int i) {
        this.mRetryCount = i;
    }
}
