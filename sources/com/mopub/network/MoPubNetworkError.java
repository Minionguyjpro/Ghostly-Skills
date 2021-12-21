package com.mopub.network;

import com.mopub.volley.NetworkResponse;
import com.mopub.volley.VolleyError;

public class MoPubNetworkError extends VolleyError {
    private final Reason mReason;
    private final Integer mRefreshTimeMillis;

    public enum Reason {
        WARMING_UP,
        NO_FILL,
        BAD_HEADER_DATA,
        BAD_BODY,
        TRACKING_FAILURE,
        UNSPECIFIED
    }

    public MoPubNetworkError(Reason reason) {
        this.mReason = reason;
        this.mRefreshTimeMillis = null;
    }

    public MoPubNetworkError(NetworkResponse networkResponse, Reason reason) {
        super(networkResponse);
        this.mReason = reason;
        this.mRefreshTimeMillis = null;
    }

    public MoPubNetworkError(Throwable th, Reason reason) {
        super(th);
        this.mReason = reason;
        this.mRefreshTimeMillis = null;
    }

    public MoPubNetworkError(String str, Reason reason) {
        this(str, reason, (Integer) null);
    }

    public MoPubNetworkError(String str, Throwable th, Reason reason) {
        super(str, th);
        this.mReason = reason;
        this.mRefreshTimeMillis = null;
    }

    public MoPubNetworkError(String str, Reason reason, Integer num) {
        super(str);
        this.mReason = reason;
        this.mRefreshTimeMillis = num;
    }

    public Reason getReason() {
        return this.mReason;
    }

    public Integer getRefreshTimeMillis() {
        return this.mRefreshTimeMillis;
    }
}
