package com.mopub.mobileads;

import android.content.Context;
import com.mopub.network.MoPubRequest;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Response;
import com.mopub.volley.RetryPolicy;
import com.mopub.volley.toolbox.HttpHeaderParser;

public class RewardedVideoCompletionRequest extends MoPubRequest<Integer> {
    final RewardedVideoCompletionRequestListener mListener;

    public interface RewardedVideoCompletionRequestListener extends Response.ErrorListener {
        void onResponse(Integer num);
    }

    public RewardedVideoCompletionRequest(Context context, String str, RetryPolicy retryPolicy, RewardedVideoCompletionRequestListener rewardedVideoCompletionRequestListener) {
        super(context, str, rewardedVideoCompletionRequestListener);
        setShouldCache(false);
        setRetryPolicy(retryPolicy);
        this.mListener = rewardedVideoCompletionRequestListener;
    }

    /* access modifiers changed from: protected */
    public Response<Integer> parseNetworkResponse(NetworkResponse networkResponse) {
        return Response.success(Integer.valueOf(networkResponse.statusCode), HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(Integer num) {
        this.mListener.onResponse(num);
    }
}
