package com.appsgeyser.multiTabApp.server;

import android.content.Context;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RedirectError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import java.net.HttpURLConnection;

public class BaseServerClient {
    protected MainNavigationActivity _activity;
    protected WebWidgetConfiguration _config;
    protected Context _context;

    public interface OnRequestDoneListener {
        void onRequestDone(String str, int i, String str2);
    }

    public class HandleRedirectRetryPolicy extends DefaultRetryPolicy {
        public HandleRedirectRetryPolicy(int i, int i2, float f) {
            super(i, i2, f);
        }

        public void retry(VolleyError volleyError) throws VolleyError {
            if (volleyError instanceof RedirectError) {
                super.retry(volleyError);
                return;
            }
            throw volleyError;
        }
    }

    public BaseServerClient(MainNavigationActivity mainNavigationActivity) {
        this._activity = mainNavigationActivity;
        this._context = mainNavigationActivity;
        this._config = mainNavigationActivity.getConfig();
    }

    public void sendRequestAsync(final String str, final Integer num, final OnRequestDoneListener onRequestDoneListener) {
        HttpURLConnection.setFollowRedirects(true);
        StringRequest stringRequest = new StringRequest(str, new Response.Listener<String>() {
            public void onResponse(String str) {
                OnRequestDoneListener onRequestDoneListener = onRequestDoneListener;
                if (onRequestDoneListener != null) {
                    onRequestDoneListener.onRequestDone(str, num.intValue(), str);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        stringRequest.setRetryPolicy(new HandleRedirectRetryPolicy(10000, 5, 1.0f));
        stringRequest.setTag(num);
        RequestQueueHolder.getInstance(this._context).getQueue().add(stringRequest);
    }
}
