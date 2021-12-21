package com.mopub.mobileads;

import android.os.AsyncTask;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;

public class VideoDownloader {
    private static final int MAX_VIDEO_SIZE = 26214400;
    /* access modifiers changed from: private */
    public static final Deque<WeakReference<VideoDownloaderTask>> sDownloaderTasks = new ArrayDeque();

    interface VideoDownloaderListener {
        void onComplete(boolean z);
    }

    private VideoDownloader() {
    }

    public static void cache(String str, VideoDownloaderListener videoDownloaderListener) {
        Preconditions.checkNotNull(videoDownloaderListener);
        if (str == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "VideoDownloader attempted to cache video with null url.");
            videoDownloaderListener.onComplete(false);
            return;
        }
        try {
            AsyncTasks.safeExecuteOnExecutor(new VideoDownloaderTask(videoDownloaderListener), str);
        } catch (Exception unused) {
            videoDownloaderListener.onComplete(false);
        }
    }

    public static void cancelAllDownloaderTasks() {
        for (WeakReference<VideoDownloaderTask> cancelOneTask : sDownloaderTasks) {
            cancelOneTask(cancelOneTask);
        }
        sDownloaderTasks.clear();
    }

    public static void cancelLastDownloadTask() {
        if (!sDownloaderTasks.isEmpty()) {
            cancelOneTask(sDownloaderTasks.peekLast());
            sDownloaderTasks.removeLast();
        }
    }

    private static boolean cancelOneTask(WeakReference<VideoDownloaderTask> weakReference) {
        VideoDownloaderTask videoDownloaderTask;
        if (weakReference == null || (videoDownloaderTask = (VideoDownloaderTask) weakReference.get()) == null) {
            return false;
        }
        return videoDownloaderTask.cancel(true);
    }

    static class VideoDownloaderTask extends AsyncTask<String, Void, Boolean> {
        private final VideoDownloaderListener mListener;
        private final WeakReference<VideoDownloaderTask> mWeakSelf = new WeakReference<>(this);

        VideoDownloaderTask(VideoDownloaderListener videoDownloaderListener) {
            this.mListener = videoDownloaderListener;
            VideoDownloader.sDownloaderTasks.add(this.mWeakSelf);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x00b2  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x00bb  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Boolean doInBackground(java.lang.String... r12) {
            /*
                r11 = this;
                r0 = 1
                r1 = 0
                java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
                if (r12 == 0) goto L_0x00bf
                int r3 = r12.length
                if (r3 == 0) goto L_0x00bf
                r3 = r12[r1]
                if (r3 != 0) goto L_0x0011
                goto L_0x00bf
            L_0x0011:
                r12 = r12[r1]
                r3 = 2
                r4 = 0
                java.net.HttpURLConnection r5 = com.mopub.common.MoPubHttpUrlConnection.getHttpUrlConnection(r12)     // Catch:{ Exception -> 0x009e, all -> 0x009b }
                java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0099 }
                java.io.InputStream r7 = r5.getInputStream()     // Catch:{ Exception -> 0x0099 }
                r6.<init>(r7)     // Catch:{ Exception -> 0x0099 }
                int r4 = r5.getResponseCode()     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                r7 = 200(0xc8, float:2.8E-43)
                if (r4 < r7) goto L_0x006e
                r7 = 300(0x12c, float:4.2E-43)
                if (r4 < r7) goto L_0x002f
                goto L_0x006e
            L_0x002f:
                int r4 = r5.getContentLength()     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                r7 = 26214400(0x1900000, float:5.2897246E-38)
                if (r4 <= r7) goto L_0x005d
                com.mopub.common.logging.MoPubLog$SdkLogEvent r12 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                java.lang.Object[] r8 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                java.lang.String r9 = "VideoDownloader encountered video larger than disk cap. (%d bytes / %d maximum)."
                java.lang.Object[] r10 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                r10[r1] = r4     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                java.lang.Integer r4 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                r10[r0] = r4     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                java.lang.String r4 = java.lang.String.format(r9, r10)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                r8[r1] = r4     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                com.mopub.common.logging.MoPubLog.log(r12, r8)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                com.mopub.common.util.Streams.closeStream(r6)
                if (r5 == 0) goto L_0x005c
                r5.disconnect()
            L_0x005c:
                return r2
            L_0x005d:
                boolean r12 = com.mopub.common.CacheService.putToDiskCache((java.lang.String) r12, (java.io.InputStream) r6)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                com.mopub.common.util.Streams.closeStream(r6)
                if (r5 == 0) goto L_0x006d
                r5.disconnect()
            L_0x006d:
                return r12
            L_0x006e:
                com.mopub.common.logging.MoPubLog$SdkLogEvent r12 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                java.lang.Object[] r7 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                r8.<init>()     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                java.lang.String r9 = "VideoDownloader encountered unexpected statusCode: "
                r8.append(r9)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                r8.append(r4)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                r7[r1] = r4     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                com.mopub.common.logging.MoPubLog.log(r12, r7)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
                com.mopub.common.util.Streams.closeStream(r6)
                if (r5 == 0) goto L_0x0090
                r5.disconnect()
            L_0x0090:
                return r2
            L_0x0091:
                r12 = move-exception
                r4 = r6
                goto L_0x00b6
            L_0x0094:
                r12 = move-exception
                r4 = r6
                goto L_0x00a0
            L_0x0097:
                r12 = move-exception
                goto L_0x00b6
            L_0x0099:
                r12 = move-exception
                goto L_0x00a0
            L_0x009b:
                r12 = move-exception
                r5 = r4
                goto L_0x00b6
            L_0x009e:
                r12 = move-exception
                r5 = r4
            L_0x00a0:
                com.mopub.common.logging.MoPubLog$SdkLogEvent r6 = com.mopub.common.logging.MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE     // Catch:{ all -> 0x0097 }
                java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0097 }
                java.lang.String r7 = "VideoDownloader task threw an internal exception."
                r3[r1] = r7     // Catch:{ all -> 0x0097 }
                r3[r0] = r12     // Catch:{ all -> 0x0097 }
                com.mopub.common.logging.MoPubLog.log(r6, r3)     // Catch:{ all -> 0x0097 }
                com.mopub.common.util.Streams.closeStream(r4)
                if (r5 == 0) goto L_0x00b5
                r5.disconnect()
            L_0x00b5:
                return r2
            L_0x00b6:
                com.mopub.common.util.Streams.closeStream(r4)
                if (r5 == 0) goto L_0x00be
                r5.disconnect()
            L_0x00be:
                throw r12
            L_0x00bf:
                com.mopub.common.logging.MoPubLog$SdkLogEvent r12 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM
                java.lang.Object[] r0 = new java.lang.Object[r0]
                java.lang.String r3 = "VideoDownloader task tried to execute null or empty url."
                r0[r1] = r3
                com.mopub.common.logging.MoPubLog.log(r12, r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VideoDownloader.VideoDownloaderTask.doInBackground(java.lang.String[]):java.lang.Boolean");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            if (isCancelled()) {
                onCancelled();
                return;
            }
            VideoDownloader.sDownloaderTasks.remove(this.mWeakSelf);
            if (bool == null) {
                this.mListener.onComplete(false);
            } else {
                this.mListener.onComplete(bool.booleanValue());
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled() {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "VideoDownloader task was cancelled.");
            VideoDownloader.sDownloaderTasks.remove(this.mWeakSelf);
            this.mListener.onComplete(false);
        }
    }

    @Deprecated
    public static Deque<WeakReference<VideoDownloaderTask>> getDownloaderTasks() {
        return sDownloaderTasks;
    }

    @Deprecated
    public static void clearDownloaderTasks() {
        sDownloaderTasks.clear();
    }
}
