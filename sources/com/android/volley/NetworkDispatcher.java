package com.android.volley;

import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public class NetworkDispatcher extends Thread {
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue<Request<?>> mQueue;
    private volatile boolean mQuit = false;

    public NetworkDispatcher(BlockingQueue<Request<?>> blockingQueue, Network network, Cache cache, ResponseDelivery responseDelivery) {
        this.mQueue = blockingQueue;
        this.mNetwork = network;
        this.mCache = cache;
        this.mDelivery = responseDelivery;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    private void addTrafficStatsTag(Request<?> request) {
        if (Build.VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                Request take = this.mQueue.take();
                try {
                    take.addMarker("network-queue-take");
                    if (take.isCanceled()) {
                        take.finish("network-discard-cancelled");
                    } else {
                        addTrafficStatsTag(take);
                        NetworkResponse performRequest = this.mNetwork.performRequest(take);
                        take.addMarker("network-http-complete");
                        if (!performRequest.notModified || !take.hasHadResponseDelivered()) {
                            Response parseNetworkResponse = take.parseNetworkResponse(performRequest);
                            take.addMarker("network-parse-complete");
                            if (take.shouldCache() && parseNetworkResponse.cacheEntry != null) {
                                this.mCache.put(take.getCacheKey(), parseNetworkResponse.cacheEntry);
                                take.addMarker("network-cache-written");
                            }
                            take.markDelivered();
                            this.mDelivery.postResponse(take, parseNetworkResponse);
                        } else {
                            take.finish("not-modified");
                        }
                    }
                } catch (VolleyError e) {
                    e.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
                    parseAndDeliverNetworkError(take, e);
                } catch (Exception e2) {
                    VolleyLog.e(e2, "Unhandled exception %s", e2.toString());
                    VolleyError volleyError = new VolleyError((Throwable) e2);
                    volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.mDelivery.postError(take, volleyError);
                }
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    return;
                }
            }
        }
    }

    private void parseAndDeliverNetworkError(Request<?> request, VolleyError volleyError) {
        this.mDelivery.postError(request, request.parseNetworkError(volleyError));
    }
}
