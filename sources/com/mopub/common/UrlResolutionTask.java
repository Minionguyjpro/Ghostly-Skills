package com.mopub.common;

import android.net.Uri;
import android.os.AsyncTask;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlResolutionTask extends AsyncTask<String, Void, String> {
    private static final int REDIRECT_LIMIT = 10;
    private final UrlResolutionListener mListener;

    interface UrlResolutionListener {
        void onFailure(String str, Throwable th);

        void onSuccess(String str);
    }

    public static void getResolvedUrl(String str, UrlResolutionListener urlResolutionListener) {
        try {
            AsyncTasks.safeExecuteOnExecutor(new UrlResolutionTask(urlResolutionListener), str);
        } catch (Exception e) {
            urlResolutionListener.onFailure("Failed to resolve url", e);
        }
    }

    UrlResolutionTask(UrlResolutionListener urlResolutionListener) {
        this.mListener = urlResolutionListener;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... strArr) {
        if (!(strArr == null || strArr.length == 0)) {
            int i = 0;
            try {
                String str = strArr[0];
                String str2 = null;
                while (str != null && i < 10) {
                    if (!UrlAction.OPEN_IN_APP_BROWSER.shouldTryHandlingUrl(Uri.parse(str)) || UrlAction.OPEN_NATIVE_BROWSER.shouldTryHandlingUrl(Uri.parse(str))) {
                        return str;
                    }
                    i++;
                    str2 = str;
                    str = getRedirectLocation(str);
                }
                return str2;
            } catch (IOException | NullPointerException | URISyntaxException unused) {
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getRedirectLocation(java.lang.String r6) throws java.io.IOException, java.net.URISyntaxException {
        /*
            r5 = this;
            java.lang.String r0 = "IOException when closing httpUrlConnection. Ignoring."
            java.net.URL r1 = new java.net.URL
            r1.<init>(r6)
            r2 = 1
            r3 = 0
            r4 = 0
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ all -> 0x0033 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ all -> 0x0033 }
            r1.setInstanceFollowRedirects(r3)     // Catch:{ all -> 0x0030 }
            java.lang.String r6 = resolveRedirectLocation(r6, r1)     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002f
            java.io.InputStream r4 = r1.getInputStream()
            if (r4 == 0) goto L_0x002c
            r4.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x002c
        L_0x0023:
            com.mopub.common.logging.MoPubLog$SdkLogEvent r4 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r3] = r0
            com.mopub.common.logging.MoPubLog.log(r4, r2)
        L_0x002c:
            r1.disconnect()
        L_0x002f:
            return r6
        L_0x0030:
            r6 = move-exception
            r4 = r1
            goto L_0x0034
        L_0x0033:
            r6 = move-exception
        L_0x0034:
            if (r4 == 0) goto L_0x004c
            java.io.InputStream r1 = r4.getInputStream()
            if (r1 == 0) goto L_0x0049
            r1.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0049
        L_0x0040:
            com.mopub.common.logging.MoPubLog$SdkLogEvent r1 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r3] = r0
            com.mopub.common.logging.MoPubLog.log(r1, r2)
        L_0x0049:
            r4.disconnect()
        L_0x004c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.UrlResolutionTask.getRedirectLocation(java.lang.String):java.lang.String");
    }

    static String resolveRedirectLocation(String str, HttpURLConnection httpURLConnection) throws IOException, URISyntaxException {
        URI uri = new URI(str);
        int responseCode = httpURLConnection.getResponseCode();
        String headerField = httpURLConnection.getHeaderField("location");
        if (responseCode < 300 || responseCode >= 400) {
            return null;
        }
        try {
            return uri.resolve(headerField).toString();
        } catch (IllegalArgumentException unused) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Invalid URL redirection. baseUrl=" + str + "\n redirectUrl=" + headerField);
            throw new URISyntaxException(headerField, "Unable to parse invalid URL");
        } catch (NullPointerException e) {
            MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent2, "Invalid URL redirection. baseUrl=" + str + "\n redirectUrl=" + headerField);
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        if (isCancelled() || str == null) {
            onCancelled();
        } else {
            this.mListener.onSuccess(str);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
        this.mListener.onFailure("Task for resolving url was cancelled", (Throwable) null);
    }
}
