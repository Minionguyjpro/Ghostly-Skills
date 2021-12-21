package com.integralads.avid.library.mopub;

import android.os.AsyncTask;
import android.text.TextUtils;

public class DownloadAvidTask extends AsyncTask<String, Void, String> {
    private DownloadAvidTaskListener listener;

    public interface DownloadAvidTaskListener {
        void failedToLoadAvid();

        void onLoadAvid(String str);
    }

    public DownloadAvidTaskListener getListener() {
        return this.listener;
    }

    public void setListener(DownloadAvidTaskListener downloadAvidTaskListener) {
        this.listener = downloadAvidTaskListener;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0078 */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006e A[SYNTHETIC, Splitter:B:26:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x009f A[SYNTHETIC, Splitter:B:43:0x009f] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0054=Splitter:B:23:0x0054, B:33:0x0078=Splitter:B:33:0x0078} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String doInBackground(java.lang.String... r9) {
        /*
            r8 = this;
            java.lang.String r0 = "AvidLoader: can not close Stream"
            r1 = 0
            r9 = r9[r1]
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            r3 = 0
            if (r2 == 0) goto L_0x0012
            java.lang.String r9 = "AvidLoader: URL is empty"
            com.integralads.avid.library.mopub.utils.AvidLogs.e(r9)
            return r3
        L_0x0012:
            java.net.URL r2 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0077, IOException -> 0x0052, all -> 0x004f }
            r2.<init>(r9)     // Catch:{ MalformedURLException -> 0x0077, IOException -> 0x0052, all -> 0x004f }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ MalformedURLException -> 0x0077, IOException -> 0x0052, all -> 0x004f }
            r2.connect()     // Catch:{ MalformedURLException -> 0x0077, IOException -> 0x0052, all -> 0x004f }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ MalformedURLException -> 0x0077, IOException -> 0x0052, all -> 0x004f }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ MalformedURLException -> 0x0077, IOException -> 0x0052, all -> 0x004f }
            r4.<init>(r2)     // Catch:{ MalformedURLException -> 0x0077, IOException -> 0x0052, all -> 0x004f }
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ MalformedURLException -> 0x0078, IOException -> 0x004d }
            r2.<init>()     // Catch:{ MalformedURLException -> 0x0078, IOException -> 0x004d }
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch:{ MalformedURLException -> 0x0078, IOException -> 0x004d }
        L_0x0030:
            int r6 = r4.read(r5)     // Catch:{ MalformedURLException -> 0x0078, IOException -> 0x004d }
            r7 = -1
            if (r6 == r7) goto L_0x0040
            java.lang.String r7 = new java.lang.String     // Catch:{ MalformedURLException -> 0x0078, IOException -> 0x004d }
            r7.<init>(r5, r1, r6)     // Catch:{ MalformedURLException -> 0x0078, IOException -> 0x004d }
            r2.write(r7)     // Catch:{ MalformedURLException -> 0x0078, IOException -> 0x004d }
            goto L_0x0030
        L_0x0040:
            java.lang.String r9 = r2.toString()     // Catch:{ MalformedURLException -> 0x0078, IOException -> 0x004d }
            r4.close()     // Catch:{ IOException -> 0x0048 }
            return r9
        L_0x0048:
            r9 = move-exception
            com.integralads.avid.library.mopub.utils.AvidLogs.e(r0, r9)
            return r3
        L_0x004d:
            r9 = move-exception
            goto L_0x0054
        L_0x004f:
            r9 = move-exception
            r4 = r3
            goto L_0x009d
        L_0x0052:
            r9 = move-exception
            r4 = r3
        L_0x0054:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x009c }
            r1.<init>()     // Catch:{ all -> 0x009c }
            java.lang.String r2 = "AvidLoader: IO error "
            r1.append(r2)     // Catch:{ all -> 0x009c }
            java.lang.String r9 = r9.getLocalizedMessage()     // Catch:{ all -> 0x009c }
            r1.append(r9)     // Catch:{ all -> 0x009c }
            java.lang.String r9 = r1.toString()     // Catch:{ all -> 0x009c }
            com.integralads.avid.library.mopub.utils.AvidLogs.e(r9)     // Catch:{ all -> 0x009c }
            if (r4 == 0) goto L_0x0076
            r4.close()     // Catch:{ IOException -> 0x0072 }
            goto L_0x0076
        L_0x0072:
            r9 = move-exception
            com.integralads.avid.library.mopub.utils.AvidLogs.e(r0, r9)
        L_0x0076:
            return r3
        L_0x0077:
            r4 = r3
        L_0x0078:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x009c }
            r1.<init>()     // Catch:{ all -> 0x009c }
            java.lang.String r2 = "AvidLoader: something is wrong with the URL '"
            r1.append(r2)     // Catch:{ all -> 0x009c }
            r1.append(r9)     // Catch:{ all -> 0x009c }
            java.lang.String r9 = "'"
            r1.append(r9)     // Catch:{ all -> 0x009c }
            java.lang.String r9 = r1.toString()     // Catch:{ all -> 0x009c }
            com.integralads.avid.library.mopub.utils.AvidLogs.e(r9)     // Catch:{ all -> 0x009c }
            if (r4 == 0) goto L_0x009b
            r4.close()     // Catch:{ IOException -> 0x0097 }
            goto L_0x009b
        L_0x0097:
            r9 = move-exception
            com.integralads.avid.library.mopub.utils.AvidLogs.e(r0, r9)
        L_0x009b:
            return r3
        L_0x009c:
            r9 = move-exception
        L_0x009d:
            if (r4 == 0) goto L_0x00a8
            r4.close()     // Catch:{ IOException -> 0x00a3 }
            goto L_0x00a8
        L_0x00a3:
            r9 = move-exception
            com.integralads.avid.library.mopub.utils.AvidLogs.e(r0, r9)
            return r3
        L_0x00a8:
            goto L_0x00aa
        L_0x00a9:
            throw r9
        L_0x00aa:
            goto L_0x00a9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.integralads.avid.library.mopub.DownloadAvidTask.doInBackground(java.lang.String[]):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        if (this.listener == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            this.listener.onLoadAvid(str);
        } else {
            this.listener.failedToLoadAvid();
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        DownloadAvidTaskListener downloadAvidTaskListener = this.listener;
        if (downloadAvidTaskListener != null) {
            downloadAvidTaskListener.failedToLoadAvid();
        }
    }
}
