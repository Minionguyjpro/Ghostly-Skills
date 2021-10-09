package com.mopub.network;

import android.os.Handler;
import com.mopub.common.Preconditions;
import com.mopub.volley.Cache;
import com.mopub.volley.Network;
import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.ResponseDelivery;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MoPubRequestQueue extends RequestQueue {
    private static final int CAPACITY = 10;
    /* access modifiers changed from: private */
    public final Map<Request<?>, DelayedRequestHelper> mDelayedRequests = new HashMap(10);

    MoPubRequestQueue(Cache cache, Network network, int i, ResponseDelivery responseDelivery) {
        super(cache, network, i, responseDelivery);
    }

    MoPubRequestQueue(Cache cache, Network network, int i) {
        super(cache, network, i);
    }

    MoPubRequestQueue(Cache cache, Network network) {
        super(cache, network);
    }

    public void addDelayedRequest(Request<?> request, int i) {
        Preconditions.checkNotNull(request);
        addDelayedRequest(request, new DelayedRequestHelper(this, request, i));
    }

    /* access modifiers changed from: package-private */
    public void addDelayedRequest(Request<?> request, DelayedRequestHelper delayedRequestHelper) {
        Preconditions.checkNotNull(delayedRequestHelper);
        if (this.mDelayedRequests.containsKey(request)) {
            cancel(request);
        }
        delayedRequestHelper.start();
        this.mDelayedRequests.put(request, delayedRequestHelper);
    }

    public void cancelAll(RequestQueue.RequestFilter requestFilter) {
        Preconditions.checkNotNull(requestFilter);
        super.cancelAll(requestFilter);
        Iterator<Map.Entry<Request<?>, DelayedRequestHelper>> it = this.mDelayedRequests.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (requestFilter.apply((Request) next.getKey())) {
                ((Request) next.getKey()).cancel();
                ((DelayedRequestHelper) next.getValue()).cancel();
                it.remove();
            }
        }
    }

    public void cancelAll(final Object obj) {
        Preconditions.checkNotNull(obj);
        super.cancelAll(obj);
        cancelAll((RequestQueue.RequestFilter) new RequestQueue.RequestFilter() {
            public boolean apply(Request<?> request) {
                return request.getTag() == obj;
            }
        });
    }

    public void cancel(final Request<?> request) {
        Preconditions.checkNotNull(request);
        cancelAll((RequestQueue.RequestFilter) new RequestQueue.RequestFilter() {
            public boolean apply(Request<?> request) {
                return request == request;
            }
        });
    }

    class DelayedRequestHelper {
        final int mDelayMs;
        final Runnable mDelayedRunnable;
        final Handler mHandler;

        DelayedRequestHelper(MoPubRequestQueue moPubRequestQueue, Request<?> request, int i) {
            this(request, i, new Handler());
        }

        DelayedRequestHelper(final Request<?> request, int i, Handler handler) {
            this.mDelayMs = i;
            this.mHandler = handler;
            this.mDelayedRunnable = new Runnable(MoPubRequestQueue.this) {
                public void run() {
                    MoPubRequestQueue.this.mDelayedRequests.remove(request);
                    MoPubRequestQueue.this.add(request);
                }
            };
        }

        /* access modifiers changed from: package-private */
        public void start() {
            this.mHandler.postDelayed(this.mDelayedRunnable, (long) this.mDelayMs);
        }

        /* access modifiers changed from: package-private */
        public void cancel() {
            this.mHandler.removeCallbacks(this.mDelayedRunnable);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public Map<Request<?>, DelayedRequestHelper> getDelayedRequests() {
        return this.mDelayedRequests;
    }
}
