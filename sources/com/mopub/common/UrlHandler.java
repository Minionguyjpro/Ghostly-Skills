package com.mopub.common;

import android.content.Context;
import android.text.TextUtils;
import com.mopub.common.UrlResolutionTask;
import com.mopub.common.logging.MoPubLog;
import java.util.EnumSet;

public class UrlHandler {
    /* access modifiers changed from: private */
    public static final ResultActions EMPTY_CLICK_LISTENER = new ResultActions() {
        public void urlHandlingFailed(String str, UrlAction urlAction) {
        }

        public void urlHandlingSucceeded(String str, UrlAction urlAction) {
        }
    };
    /* access modifiers changed from: private */
    public static final MoPubSchemeListener EMPTY_MOPUB_SCHEME_LISTENER = new MoPubSchemeListener() {
        public void onClose() {
        }

        public void onCrash() {
        }

        public void onFailLoad() {
        }

        public void onFinishLoad() {
        }
    };
    private boolean mAlreadySucceeded;
    private String mDspCreativeId;
    private MoPubSchemeListener mMoPubSchemeListener;
    private ResultActions mResultActions;
    private boolean mSkipShowMoPubBrowser;
    private EnumSet<UrlAction> mSupportedUrlActions;
    /* access modifiers changed from: private */
    public boolean mTaskPending;

    public interface MoPubSchemeListener {
        void onClose();

        void onCrash();

        void onFailLoad();

        void onFinishLoad();
    }

    public interface ResultActions {
        void urlHandlingFailed(String str, UrlAction urlAction);

        void urlHandlingSucceeded(String str, UrlAction urlAction);
    }

    public static class Builder {
        private String creativeId;
        private MoPubSchemeListener moPubSchemeListener = UrlHandler.EMPTY_MOPUB_SCHEME_LISTENER;
        private ResultActions resultActions = UrlHandler.EMPTY_CLICK_LISTENER;
        private boolean skipShowMoPubBrowser = false;
        private EnumSet<UrlAction> supportedUrlActions = EnumSet.of(UrlAction.NOOP);

        public Builder withSupportedUrlActions(UrlAction urlAction, UrlAction... urlActionArr) {
            this.supportedUrlActions = EnumSet.of(urlAction, urlActionArr);
            return this;
        }

        public Builder withSupportedUrlActions(EnumSet<UrlAction> enumSet) {
            this.supportedUrlActions = EnumSet.copyOf(enumSet);
            return this;
        }

        public Builder withResultActions(ResultActions resultActions2) {
            this.resultActions = resultActions2;
            return this;
        }

        public Builder withMoPubSchemeListener(MoPubSchemeListener moPubSchemeListener2) {
            this.moPubSchemeListener = moPubSchemeListener2;
            return this;
        }

        public Builder withoutMoPubBrowser() {
            this.skipShowMoPubBrowser = true;
            return this;
        }

        public Builder withDspCreativeId(String str) {
            this.creativeId = str;
            return this;
        }

        public UrlHandler build() {
            return new UrlHandler(this.supportedUrlActions, this.resultActions, this.moPubSchemeListener, this.skipShowMoPubBrowser, this.creativeId);
        }
    }

    private UrlHandler(EnumSet<UrlAction> enumSet, ResultActions resultActions, MoPubSchemeListener moPubSchemeListener, boolean z, String str) {
        this.mSupportedUrlActions = EnumSet.copyOf(enumSet);
        this.mResultActions = resultActions;
        this.mMoPubSchemeListener = moPubSchemeListener;
        this.mSkipShowMoPubBrowser = z;
        this.mDspCreativeId = str;
        this.mAlreadySucceeded = false;
        this.mTaskPending = false;
    }

    /* access modifiers changed from: package-private */
    public EnumSet<UrlAction> getSupportedUrlActions() {
        return EnumSet.copyOf(this.mSupportedUrlActions);
    }

    /* access modifiers changed from: package-private */
    public ResultActions getResultActions() {
        return this.mResultActions;
    }

    /* access modifiers changed from: package-private */
    public MoPubSchemeListener getMoPubSchemeListener() {
        return this.mMoPubSchemeListener;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldSkipShowMoPubBrowser() {
        return this.mSkipShowMoPubBrowser;
    }

    public void handleUrl(Context context, String str) {
        Preconditions.checkNotNull(context);
        handleUrl(context, str, true);
    }

    public void handleUrl(Context context, String str, boolean z) {
        Preconditions.checkNotNull(context);
        handleUrl(context, str, z, (Iterable<String>) null);
    }

    public void handleUrl(Context context, String str, boolean z, Iterable<String> iterable) {
        Preconditions.checkNotNull(context);
        if (TextUtils.isEmpty(str)) {
            failUrlHandling(str, (UrlAction) null, "Attempted to handle empty url.", (Throwable) null);
            return;
        }
        final Context context2 = context;
        final boolean z2 = z;
        final Iterable<String> iterable2 = iterable;
        final String str2 = str;
        UrlResolutionTask.getResolvedUrl(str, new UrlResolutionTask.UrlResolutionListener() {
            public void onSuccess(String str) {
                boolean unused = UrlHandler.this.mTaskPending = false;
                UrlHandler.this.handleResolvedUrl(context2, str, z2, iterable2);
            }

            public void onFailure(String str, Throwable th) {
                boolean unused = UrlHandler.this.mTaskPending = false;
                UrlHandler.this.failUrlHandling(str2, (UrlAction) null, str, th);
            }
        });
        this.mTaskPending = true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: com.mopub.common.UrlAction} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handleResolvedUrl(android.content.Context r16, java.lang.String r17, boolean r18, java.lang.Iterable<java.lang.String> r19) {
        /*
            r15 = this;
            r7 = r15
            r8 = r17
            boolean r0 = android.text.TextUtils.isEmpty(r17)
            r9 = 0
            r10 = 0
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = "Attempted to handle empty url."
            r15.failUrlHandling(r8, r10, r0, r10)
            return r9
        L_0x0011:
            com.mopub.common.UrlAction r0 = com.mopub.common.UrlAction.NOOP
            android.net.Uri r11 = android.net.Uri.parse(r17)
            java.util.EnumSet<com.mopub.common.UrlAction> r1 = r7.mSupportedUrlActions
            java.util.Iterator r12 = r1.iterator()
        L_0x001d:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x0087
            java.lang.Object r1 = r12.next()
            r13 = r1
            com.mopub.common.UrlAction r13 = (com.mopub.common.UrlAction) r13
            boolean r1 = r13.shouldTryHandlingUrl(r11)
            if (r1 == 0) goto L_0x0082
            r14 = 1
            java.lang.String r6 = r7.mDspCreativeId     // Catch:{ IntentNotResolvableException -> 0x006b }
            r1 = r13
            r2 = r15
            r3 = r16
            r4 = r11
            r5 = r18
            r1.handleUrl(r2, r3, r4, r5, r6)     // Catch:{ IntentNotResolvableException -> 0x006b }
            boolean r0 = r7.mAlreadySucceeded     // Catch:{ IntentNotResolvableException -> 0x006b }
            if (r0 != 0) goto L_0x006a
            boolean r0 = r7.mTaskPending     // Catch:{ IntentNotResolvableException -> 0x006b }
            if (r0 != 0) goto L_0x006a
            com.mopub.common.UrlAction r0 = com.mopub.common.UrlAction.IGNORE_ABOUT_SCHEME     // Catch:{ IntentNotResolvableException -> 0x006b }
            boolean r0 = r0.equals(r13)     // Catch:{ IntentNotResolvableException -> 0x006b }
            if (r0 != 0) goto L_0x006a
            com.mopub.common.UrlAction r0 = com.mopub.common.UrlAction.HANDLE_MOPUB_SCHEME     // Catch:{ IntentNotResolvableException -> 0x006b }
            boolean r0 = r0.equals(r13)     // Catch:{ IntentNotResolvableException -> 0x006b }
            if (r0 != 0) goto L_0x006a
            r1 = r16
            r2 = r19
            com.mopub.network.TrackingRequest.makeTrackingHttpRequest((java.lang.Iterable<java.lang.String>) r2, (android.content.Context) r1)     // Catch:{ IntentNotResolvableException -> 0x0068 }
            com.mopub.common.UrlHandler$ResultActions r0 = r7.mResultActions     // Catch:{ IntentNotResolvableException -> 0x0068 }
            java.lang.String r3 = r11.toString()     // Catch:{ IntentNotResolvableException -> 0x0068 }
            r0.urlHandlingSucceeded(r3, r13)     // Catch:{ IntentNotResolvableException -> 0x0068 }
            r7.mAlreadySucceeded = r14     // Catch:{ IntentNotResolvableException -> 0x0068 }
            goto L_0x006a
        L_0x0068:
            r0 = move-exception
            goto L_0x0070
        L_0x006a:
            return r14
        L_0x006b:
            r0 = move-exception
            r1 = r16
            r2 = r19
        L_0x0070:
            com.mopub.common.logging.MoPubLog$SdkLogEvent r3 = com.mopub.common.logging.MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = r0.getMessage()
            r4[r9] = r5
            r4[r14] = r0
            com.mopub.common.logging.MoPubLog.log(r3, r4)
            r0 = r13
            goto L_0x001d
        L_0x0082:
            r1 = r16
            r2 = r19
            goto L_0x001d
        L_0x0087:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Link ignored. Unable to handle url: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            r15.failUrlHandling(r8, r0, r1, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.UrlHandler.handleResolvedUrl(android.content.Context, java.lang.String, boolean, java.lang.Iterable):boolean");
    }

    /* access modifiers changed from: private */
    public void failUrlHandling(String str, UrlAction urlAction, String str2, Throwable th) {
        Preconditions.checkNotNull(str2);
        if (urlAction == null) {
            urlAction = UrlAction.NOOP;
        }
        MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, str2, th);
        this.mResultActions.urlHandlingFailed(str, urlAction);
    }
}
