package com.mopub.common.privacy;

import android.content.Context;
import android.text.TextUtils;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubRequest;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Response;
import com.mopub.volley.toolbox.HttpHeaderParser;
import org.json.JSONException;
import org.json.JSONObject;

class ConsentDialogRequest extends MoPubRequest<ConsentDialogResponse> {
    private static final String HTML_KEY = "dialog_html";
    private Listener mListener;

    public interface Listener extends Response.ErrorListener {
        void onSuccess(ConsentDialogResponse consentDialogResponse);
    }

    ConsentDialogRequest(Context context, String str, Listener listener) {
        super(context, str, listener);
        this.mListener = listener;
        setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 1, 1.0f));
        setShouldCache(false);
    }

    /* access modifiers changed from: protected */
    public Response<ConsentDialogResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String string = new JSONObject(parseStringBody(networkResponse)).getString(HTML_KEY);
            if (!TextUtils.isEmpty(string)) {
                return Response.success(new ConsentDialogResponse(string), HttpHeaderParser.parseCacheHeaders(networkResponse));
            }
            throw new JSONException("Empty HTML body");
        } catch (JSONException unused) {
            return Response.error(new MoPubNetworkError("Unable to parse consent dialog request network response.", MoPubNetworkError.Reason.BAD_BODY, (Integer) null));
        }
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(ConsentDialogResponse consentDialogResponse) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onSuccess(consentDialogResponse);
        }
    }
}
