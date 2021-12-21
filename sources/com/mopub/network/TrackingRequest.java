package com.mopub.network;

import android.content.Context;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastErrorCode;
import com.mopub.mobileads.VastMacroHelper;
import com.mopub.mobileads.VastTracker;
import com.mopub.mobileads.VastTrackerTwo;
import com.mopub.network.MoPubNetworkError;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Response;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackingRequest extends MoPubRequest<Void> {
    private static final int ZERO_RETRIES = 0;
    private final Listener mListener;

    public interface Listener extends Response.ErrorListener {
        void onResponse(String str);
    }

    private TrackingRequest(Context context, String str, Listener listener) {
        super(context, str, listener);
        this.mListener = listener;
        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1.0f));
    }

    /* access modifiers changed from: protected */
    public Response<Void> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse.statusCode == 200) {
            return Response.success(null, HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        return Response.error(new MoPubNetworkError("Failed to log tracking request. Response code: " + networkResponse.statusCode + " for url: " + getUrl(), MoPubNetworkError.Reason.TRACKING_FAILURE));
    }

    public void deliverResponse(Void voidR) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onResponse(getUrl());
        }
    }

    public static void makeVastTrackingHttpRequest(List<VastTracker> list, VastErrorCode vastErrorCode, Integer num, String str, Context context) {
        Preconditions.checkNotNull(list);
        ArrayList arrayList = new ArrayList(list.size());
        for (VastTracker next : list) {
            if (next != null && (!next.isTracked() || next.isRepeatable())) {
                arrayList.add(next.getContent());
                next.setTracked();
            }
        }
        makeTrackingHttpRequest((Iterable<String>) new VastMacroHelper(arrayList).withErrorCode(vastErrorCode).withContentPlayHead(num).withAssetUri(str).getUris(), context);
    }

    public static void makeVastTrackingTwoHttpRequest(List<VastTrackerTwo> list, VastErrorCode vastErrorCode, Integer num, String str, Context context) {
        Preconditions.checkNotNull(list);
        ArrayList arrayList = new ArrayList(list.size());
        for (VastTrackerTwo next : list) {
            if (next != null && (!next.isTracked() || next.isRepeatable())) {
                arrayList.add(next.getContent());
                next.setTracked();
            }
        }
        makeTrackingHttpRequest((Iterable<String>) new VastMacroHelper(arrayList).withErrorCode(vastErrorCode).withContentPlayHead(num).withAssetUri(str).getUris(), context);
    }

    public static void makeTrackingHttpRequest(Iterable<String> iterable, Context context, final Listener listener) {
        if (iterable != null && context != null) {
            MoPubRequestQueue requestQueue = Networking.getRequestQueue(context);
            for (final String next : iterable) {
                if (!TextUtils.isEmpty(next)) {
                    requestQueue.add(new TrackingRequest(context, next, new Listener() {
                        public void onResponse(String str) {
                            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                            MoPubLog.log(sdkLogEvent, "Successfully hit tracking endpoint: " + str);
                            Listener listener = listener;
                            if (listener != null) {
                                listener.onResponse(str);
                            }
                        }

                        public void onErrorResponse(VolleyError volleyError) {
                            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                            MoPubLog.log(sdkLogEvent, "Failed to hit tracking endpoint: " + next);
                            Listener listener = listener;
                            if (listener != null) {
                                listener.onErrorResponse(volleyError);
                            }
                        }
                    }));
                }
            }
        }
    }

    public static void makeTrackingHttpRequest(String str, Context context) {
        makeTrackingHttpRequest(str, context, (Listener) null);
    }

    public static void makeTrackingHttpRequest(String str, Context context, Listener listener) {
        if (!TextUtils.isEmpty(str)) {
            makeTrackingHttpRequest((Iterable<String>) Arrays.asList(new String[]{str}), context, listener);
        }
    }

    public static void makeTrackingHttpRequest(Iterable<String> iterable, Context context) {
        makeTrackingHttpRequest(iterable, context, (Listener) null);
    }
}
