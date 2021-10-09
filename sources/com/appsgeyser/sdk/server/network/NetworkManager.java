package com.appsgeyser.sdk.server.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RedirectError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.appsgeyser.sdk.server.RequestQueueHolder;
import com.appsgeyser.sdk.server.implementation.OnRequestDoneListener;
import java.net.HttpURLConnection;
import java.util.Map;

public class NetworkManager {
    private static final NetworkManager INSTANCE = new NetworkManager();

    public enum RequestType {
        AFTERINSTALL,
        USAGE,
        UPDATE,
        CLICK,
        APPMODE,
        CONFIG_PHP,
        NATIVE_ADS,
        IMPRESSION,
        RMA,
        REFERRER
    }

    public static NetworkManager getInstance() {
        return INSTANCE;
    }

    public void sendRequestAsync(final String str, final Integer num, Context context, final OnRequestDoneListener onRequestDoneListener, final Response.ErrorListener errorListener) {
        sendRequestAsync(str, num, context, (Response.Listener<String>) new Response.Listener<String>() {
            public void onResponse(String str) {
                OnRequestDoneListener onRequestDoneListener = onRequestDoneListener;
                if (onRequestDoneListener != null) {
                    onRequestDoneListener.onRequestDone(str, num.intValue(), str);
                }
            }
        }, (Response.ErrorListener) new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Response.ErrorListener errorListener = errorListener;
                if (errorListener != null) {
                    errorListener.onErrorResponse(volleyError);
                }
            }
        });
    }

    public void sendRequestAsync(String str, Integer num, Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        HttpURLConnection.setFollowRedirects(true);
        StringRequest stringRequest = new StringRequest(str, listener, errorListener);
        stringRequest.setRetryPolicy(new HandleRedirectRetryPolicy(10000, 5, 1.0f));
        stringRequest.setTag(num);
        RequestQueueHolder.getInstance(context).getQueue().add(stringRequest);
    }

    public void sendRequestAsyncPost(final String str, final Integer num, Context context, final OnRequestDoneListener onRequestDoneListener, final Response.ErrorListener errorListener, Map<String, String> map) {
        sendRequestAsyncPost(str, num, context, (Response.Listener<String>) new Response.Listener<String>() {
            public void onResponse(String str) {
                OnRequestDoneListener onRequestDoneListener = onRequestDoneListener;
                if (onRequestDoneListener != null) {
                    onRequestDoneListener.onRequestDone(str, num.intValue(), str);
                }
            }
        }, (Response.ErrorListener) new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Response.ErrorListener errorListener = errorListener;
                if (errorListener != null) {
                    errorListener.onErrorResponse(volleyError);
                }
            }
        }, map);
    }

    public void sendRequestAsyncPost(String str, Integer num, Context context, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> map) {
        HttpURLConnection.setFollowRedirects(true);
        final Map<String, String> map2 = map;
        AnonymousClass5 r1 = new StringRequest(1, str, listener, errorListener) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                return map2;
            }
        };
        r1.setRetryPolicy(new HandleRedirectRetryPolicy(10000, 5, 1.0f));
        r1.setTag(num);
        RequestQueueHolder.getInstance(context).getQueue().add(r1);
    }

    private class HandleRedirectRetryPolicy extends DefaultRetryPolicy {
        HandleRedirectRetryPolicy(int i, int i2, float f) {
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

    public Response.ErrorListener getDefaultErrorListener(Integer num, Context context) {
        return new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError != null) {
                    volleyError.printStackTrace();
                    if (volleyError.getMessage() != null) {
                        System.err.println(volleyError.getMessage());
                    } else {
                        System.err.println("volley request error");
                    }
                }
            }
        };
    }

    public OnRequestDoneListener getEmptyRequestDoneListener(Context context) {
        return new OnRequestDoneListener() {
            public void onRequestDone(String str, int i, String str2) {
            }
        };
    }

    public static boolean isOnline(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
