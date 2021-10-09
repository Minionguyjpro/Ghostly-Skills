package com.appsgeyser.multiTabApp.server;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;

public class RequestQueueHolder {
    private static volatile boolean _ready = false;
    private static ArrayList<String> deferredUrls;
    private static RequestQueueHolder instance;
    private RequestQueue _queue = null;

    public static RequestQueueHolder getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQueueHolder(context);
        }
        return instance;
    }

    public RequestQueue getQueue() {
        return this._queue;
    }

    private RequestQueueHolder(Context context) {
        this._queue = Volley.newRequestQueue(context);
        _ready = true;
    }
}
