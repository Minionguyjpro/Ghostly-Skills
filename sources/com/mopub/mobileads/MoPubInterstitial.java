package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowInsets;
import com.mopub.common.AdFormat;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.CustomEventInterstitialAdapter;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.factories.CustomEventInterstitialAdapterFactory;
import java.util.Map;

public class MoPubInterstitial implements CustomEventInterstitialAdapter.CustomEventInterstitialAdapterListener {
    /* access modifiers changed from: private */
    public Activity mActivity;
    private final Runnable mAdExpiration = new Runnable() {
        public void run() {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Expiring unused Interstitial ad.");
            MoPubInterstitial.this.attemptStateTransition(InterstitialState.IDLE, true);
            if (!InterstitialState.SHOWING.equals(MoPubInterstitial.this.mCurrentInterstitialState) && !InterstitialState.DESTROYED.equals(MoPubInterstitial.this.mCurrentInterstitialState)) {
                MoPubInterstitial.this.mInterstitialView.adFailed(MoPubErrorCode.EXPIRED);
            }
        }
    };
    /* access modifiers changed from: private */
    public volatile InterstitialState mCurrentInterstitialState = InterstitialState.IDLE;
    /* access modifiers changed from: private */
    public CustomEventInterstitialAdapter mCustomEventInterstitialAdapter;
    private Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public InterstitialAdListener mInterstitialAdListener;
    /* access modifiers changed from: private */
    public MoPubInterstitialView mInterstitialView;

    public interface InterstitialAdListener {
        void onInterstitialClicked(MoPubInterstitial moPubInterstitial);

        void onInterstitialDismissed(MoPubInterstitial moPubInterstitial);

        void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode);

        void onInterstitialLoaded(MoPubInterstitial moPubInterstitial);

        void onInterstitialShown(MoPubInterstitial moPubInterstitial);
    }

    enum InterstitialState {
        IDLE,
        LOADING,
        READY,
        SHOWING,
        DESTROYED
    }

    public MoPubInterstitial(Activity activity, String str) {
        this.mActivity = activity;
        MoPubInterstitialView moPubInterstitialView = new MoPubInterstitialView(this.mActivity);
        this.mInterstitialView = moPubInterstitialView;
        moPubInterstitialView.setAdUnitId(str);
    }

    /* access modifiers changed from: private */
    public boolean attemptStateTransition(InterstitialState interstitialState) {
        return attemptStateTransition(interstitialState, false);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x010b, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0173, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0182, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0069, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00c1, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean attemptStateTransition(com.mopub.mobileads.MoPubInterstitial.InterstitialState r8, boolean r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            com.mopub.common.Preconditions.checkNotNull(r8)     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r0 = r7.mCurrentInterstitialState     // Catch:{ all -> 0x0183 }
            int[] r1 = com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ all -> 0x0183 }
            int r0 = r0.ordinal()     // Catch:{ all -> 0x0183 }
            r0 = r1[r0]     // Catch:{ all -> 0x0183 }
            r1 = 2
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 1
            r6 = 0
            if (r0 == r5) goto L_0x010c
            if (r0 == r1) goto L_0x00c2
            if (r0 == r4) goto L_0x0077
            if (r0 == r3) goto L_0x006a
            if (r0 == r2) goto L_0x0020
            monitor-exit(r7)
            return r6
        L_0x0020:
            int[] r0 = com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ all -> 0x0183 }
            int r8 = r8.ordinal()     // Catch:{ all -> 0x0183 }
            r8 = r0[r8]     // Catch:{ all -> 0x0183 }
            if (r8 == r5) goto L_0x0051
            if (r8 == r1) goto L_0x0044
            if (r8 == r4) goto L_0x0037
            if (r8 == r3) goto L_0x0032
            monitor-exit(r7)
            return r6
        L_0x0032:
            r7.setInterstitialStateDestroyed()     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r5
        L_0x0037:
            com.mopub.common.logging.MoPubLog$AdLogEvent r8 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM     // Catch:{ all -> 0x0183 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = "No interstitial loading or loaded."
            r9[r6] = r0     // Catch:{ all -> 0x0183 }
            com.mopub.common.logging.MoPubLog.log(r8, r9)     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r6
        L_0x0044:
            com.mopub.common.logging.MoPubLog$AdLogEvent r8 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM     // Catch:{ all -> 0x0183 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = "Attempted transition from IDLE to READY failed due to no known load call."
            r9[r6] = r0     // Catch:{ all -> 0x0183 }
            com.mopub.common.logging.MoPubLog.log(r8, r9)     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r6
        L_0x0051:
            r7.invalidateInterstitialAdapter()     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r8 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.LOADING     // Catch:{ all -> 0x0183 }
            r7.mCurrentInterstitialState = r8     // Catch:{ all -> 0x0183 }
            r7.updatedInsets()     // Catch:{ all -> 0x0183 }
            if (r9 == 0) goto L_0x0063
            com.mopub.mobileads.MoPubInterstitial$MoPubInterstitialView r8 = r7.mInterstitialView     // Catch:{ all -> 0x0183 }
            r8.forceRefresh()     // Catch:{ all -> 0x0183 }
            goto L_0x0068
        L_0x0063:
            com.mopub.mobileads.MoPubInterstitial$MoPubInterstitialView r8 = r7.mInterstitialView     // Catch:{ all -> 0x0183 }
            r8.loadAd()     // Catch:{ all -> 0x0183 }
        L_0x0068:
            monitor-exit(r7)
            return r5
        L_0x006a:
            com.mopub.common.logging.MoPubLog$AdLogEvent r8 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM     // Catch:{ all -> 0x0183 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = "MoPubInterstitial destroyed. Ignoring all requests."
            r9[r6] = r0     // Catch:{ all -> 0x0183 }
            com.mopub.common.logging.MoPubLog.log(r8, r9)     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r6
        L_0x0077:
            int[] r0 = com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ all -> 0x0183 }
            int r8 = r8.ordinal()     // Catch:{ all -> 0x0183 }
            r8 = r0[r8]     // Catch:{ all -> 0x0183 }
            if (r8 == r5) goto L_0x00b3
            if (r8 == r4) goto L_0x00a6
            if (r8 == r3) goto L_0x00a1
            if (r8 == r2) goto L_0x0089
            monitor-exit(r7)
            return r6
        L_0x0089:
            if (r9 == 0) goto L_0x0098
            com.mopub.common.logging.MoPubLog$AdLogEvent r8 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM     // Catch:{ all -> 0x0183 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = "Cannot force refresh while showing an interstitial."
            r9[r6] = r0     // Catch:{ all -> 0x0183 }
            com.mopub.common.logging.MoPubLog.log(r8, r9)     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r6
        L_0x0098:
            r7.invalidateInterstitialAdapter()     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r8 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.IDLE     // Catch:{ all -> 0x0183 }
            r7.mCurrentInterstitialState = r8     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r5
        L_0x00a1:
            r7.setInterstitialStateDestroyed()     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r5
        L_0x00a6:
            com.mopub.common.logging.MoPubLog$AdLogEvent r8 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM     // Catch:{ all -> 0x0183 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = "Already showing an interstitial. Cannot show it again."
            r9[r6] = r0     // Catch:{ all -> 0x0183 }
            com.mopub.common.logging.MoPubLog.log(r8, r9)     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r6
        L_0x00b3:
            if (r9 != 0) goto L_0x00c0
            com.mopub.common.logging.MoPubLog$AdLogEvent r8 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM     // Catch:{ all -> 0x0183 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = "Interstitial already showing. Not loading another."
            r9[r6] = r0     // Catch:{ all -> 0x0183 }
            com.mopub.common.logging.MoPubLog.log(r8, r9)     // Catch:{ all -> 0x0183 }
        L_0x00c0:
            monitor-exit(r7)
            return r6
        L_0x00c2:
            int[] r0 = com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ all -> 0x0183 }
            int r8 = r8.ordinal()     // Catch:{ all -> 0x0183 }
            r8 = r0[r8]     // Catch:{ all -> 0x0183 }
            if (r8 == r5) goto L_0x00f6
            if (r8 == r4) goto L_0x00e6
            if (r8 == r3) goto L_0x00e1
            if (r8 == r2) goto L_0x00d4
            monitor-exit(r7)
            return r6
        L_0x00d4:
            if (r9 == 0) goto L_0x00df
            r7.invalidateInterstitialAdapter()     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r8 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.IDLE     // Catch:{ all -> 0x0183 }
            r7.mCurrentInterstitialState = r8     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r5
        L_0x00df:
            monitor-exit(r7)
            return r6
        L_0x00e1:
            r7.setInterstitialStateDestroyed()     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r5
        L_0x00e6:
            r7.showCustomEventInterstitial()     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r8 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.SHOWING     // Catch:{ all -> 0x0183 }
            r7.mCurrentInterstitialState = r8     // Catch:{ all -> 0x0183 }
            android.os.Handler r8 = r7.mHandler     // Catch:{ all -> 0x0183 }
            java.lang.Runnable r9 = r7.mAdExpiration     // Catch:{ all -> 0x0183 }
            r8.removeCallbacks(r9)     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r5
        L_0x00f6:
            com.mopub.common.logging.MoPubLog$AdLogEvent r8 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM     // Catch:{ all -> 0x0183 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = "Interstitial already loaded. Not loading another."
            r9[r6] = r0     // Catch:{ all -> 0x0183 }
            com.mopub.common.logging.MoPubLog.log(r8, r9)     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialAdListener r8 = r7.mInterstitialAdListener     // Catch:{ all -> 0x0183 }
            if (r8 == 0) goto L_0x010a
            com.mopub.mobileads.MoPubInterstitial$InterstitialAdListener r8 = r7.mInterstitialAdListener     // Catch:{ all -> 0x0183 }
            r8.onInterstitialLoaded(r7)     // Catch:{ all -> 0x0183 }
        L_0x010a:
            monitor-exit(r7)
            return r6
        L_0x010c:
            int[] r0 = com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ all -> 0x0183 }
            int r8 = r8.ordinal()     // Catch:{ all -> 0x0183 }
            r8 = r0[r8]     // Catch:{ all -> 0x0183 }
            if (r8 == r5) goto L_0x0174
            if (r8 == r1) goto L_0x013b
            if (r8 == r4) goto L_0x012e
            if (r8 == r3) goto L_0x0129
            if (r8 == r2) goto L_0x0120
            monitor-exit(r7)
            return r6
        L_0x0120:
            r7.invalidateInterstitialAdapter()     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r8 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.IDLE     // Catch:{ all -> 0x0183 }
            r7.mCurrentInterstitialState = r8     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r5
        L_0x0129:
            r7.setInterstitialStateDestroyed()     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r5
        L_0x012e:
            com.mopub.common.logging.MoPubLog$AdLogEvent r8 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM     // Catch:{ all -> 0x0183 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = "Interstitial is not ready to be shown yet."
            r9[r6] = r0     // Catch:{ all -> 0x0183 }
            com.mopub.common.logging.MoPubLog.log(r8, r9)     // Catch:{ all -> 0x0183 }
            monitor-exit(r7)
            return r6
        L_0x013b:
            com.mopub.common.logging.MoPubLog$AdLogEvent r8 = com.mopub.common.logging.MoPubLog.AdLogEvent.LOAD_SUCCESS     // Catch:{ all -> 0x0183 }
            java.lang.Object[] r9 = new java.lang.Object[r6]     // Catch:{ all -> 0x0183 }
            com.mopub.common.logging.MoPubLog.log(r8, r9)     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r8 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.READY     // Catch:{ all -> 0x0183 }
            r7.mCurrentInterstitialState = r8     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.MoPubInterstitial$MoPubInterstitialView r8 = r7.mInterstitialView     // Catch:{ all -> 0x0183 }
            java.lang.String r8 = r8.getCustomEventClassName()     // Catch:{ all -> 0x0183 }
            boolean r8 = com.mopub.mobileads.AdTypeTranslator.CustomEventType.isMoPubSpecific(r8)     // Catch:{ all -> 0x0183 }
            if (r8 == 0) goto L_0x015c
            android.os.Handler r8 = r7.mHandler     // Catch:{ all -> 0x0183 }
            java.lang.Runnable r9 = r7.mAdExpiration     // Catch:{ all -> 0x0183 }
            r0 = 14400000(0xdbba00, double:7.1145453E-317)
            r8.postDelayed(r9, r0)     // Catch:{ all -> 0x0183 }
        L_0x015c:
            com.mopub.mobileads.MoPubInterstitial$MoPubInterstitialView r8 = r7.mInterstitialView     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.AdViewController r8 = r8.mAdViewController     // Catch:{ all -> 0x0183 }
            if (r8 == 0) goto L_0x0169
            com.mopub.mobileads.MoPubInterstitial$MoPubInterstitialView r8 = r7.mInterstitialView     // Catch:{ all -> 0x0183 }
            com.mopub.mobileads.AdViewController r8 = r8.mAdViewController     // Catch:{ all -> 0x0183 }
            r8.creativeDownloadSuccess()     // Catch:{ all -> 0x0183 }
        L_0x0169:
            com.mopub.mobileads.MoPubInterstitial$InterstitialAdListener r8 = r7.mInterstitialAdListener     // Catch:{ all -> 0x0183 }
            if (r8 == 0) goto L_0x0172
            com.mopub.mobileads.MoPubInterstitial$InterstitialAdListener r8 = r7.mInterstitialAdListener     // Catch:{ all -> 0x0183 }
            r8.onInterstitialLoaded(r7)     // Catch:{ all -> 0x0183 }
        L_0x0172:
            monitor-exit(r7)
            return r5
        L_0x0174:
            if (r9 != 0) goto L_0x0181
            com.mopub.common.logging.MoPubLog$AdLogEvent r8 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM     // Catch:{ all -> 0x0183 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = "Already loading an interstitial."
            r9[r6] = r0     // Catch:{ all -> 0x0183 }
            com.mopub.common.logging.MoPubLog.log(r8, r9)     // Catch:{ all -> 0x0183 }
        L_0x0181:
            monitor-exit(r7)
            return r6
        L_0x0183:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.MoPubInterstitial.attemptStateTransition(com.mopub.mobileads.MoPubInterstitial$InterstitialState, boolean):boolean");
    }

    /* renamed from: com.mopub.mobileads.MoPubInterstitial$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.mopub.mobileads.MoPubInterstitial$InterstitialState[] r0 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState = r0
                com.mopub.mobileads.MoPubInterstitial$InterstitialState r1 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.LOADING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.mobileads.MoPubInterstitial$InterstitialState r1 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.READY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.mobileads.MoPubInterstitial$InterstitialState r1 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.SHOWING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.mobileads.MoPubInterstitial$InterstitialState r1 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.DESTROYED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ NoSuchFieldError -> 0x003e }
                com.mopub.mobileads.MoPubInterstitial$InterstitialState r1 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.IDLE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.<clinit>():void");
        }
    }

    private void setInterstitialStateDestroyed() {
        invalidateInterstitialAdapter();
        this.mInterstitialAdListener = null;
        this.mInterstitialView.setBannerAdListener((MoPubView.BannerAdListener) null);
        this.mInterstitialView.destroy();
        this.mHandler.removeCallbacks(this.mAdExpiration);
        this.mCurrentInterstitialState = InterstitialState.DESTROYED;
    }

    private void updatedInsets() {
        Window window;
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT >= 28 && (window = this.mActivity.getWindow()) != null && (rootWindowInsets = window.getDecorView().getRootWindowInsets()) != null) {
            this.mInterstitialView.setWindowInsets(rootWindowInsets);
        }
    }

    public void load() {
        MoPubLog.log(MoPubLog.AdLogEvent.LOAD_ATTEMPTED, new Object[0]);
        attemptStateTransition(InterstitialState.LOADING);
    }

    public boolean show() {
        MoPubLog.log(MoPubLog.AdLogEvent.SHOW_ATTEMPTED, new Object[0]);
        return attemptStateTransition(InterstitialState.SHOWING);
    }

    public void forceRefresh() {
        attemptStateTransition(InterstitialState.IDLE, true);
        attemptStateTransition(InterstitialState.LOADING, true);
    }

    public boolean isReady() {
        return this.mCurrentInterstitialState == InterstitialState.READY;
    }

    /* access modifiers changed from: package-private */
    public boolean isDestroyed() {
        return this.mCurrentInterstitialState == InterstitialState.DESTROYED;
    }

    /* access modifiers changed from: package-private */
    public Integer getAdTimeoutDelay(int i) {
        return this.mInterstitialView.getAdTimeoutDelay(i);
    }

    /* access modifiers changed from: package-private */
    public MoPubInterstitialView getMoPubInterstitialView() {
        return this.mInterstitialView;
    }

    private void showCustomEventInterstitial() {
        CustomEventInterstitialAdapter customEventInterstitialAdapter = this.mCustomEventInterstitialAdapter;
        if (customEventInterstitialAdapter != null) {
            customEventInterstitialAdapter.showInterstitial();
        }
    }

    private void invalidateInterstitialAdapter() {
        CustomEventInterstitialAdapter customEventInterstitialAdapter = this.mCustomEventInterstitialAdapter;
        if (customEventInterstitialAdapter != null) {
            customEventInterstitialAdapter.invalidate();
            this.mCustomEventInterstitialAdapter = null;
        }
    }

    public void setKeywords(String str) {
        this.mInterstitialView.setKeywords(str);
    }

    public String getKeywords() {
        return this.mInterstitialView.getKeywords();
    }

    public void setUserDataKeywords(String str) {
        this.mInterstitialView.setUserDataKeywords(str);
    }

    public String getUserDataKeywords() {
        return this.mInterstitialView.getUserDataKeywords();
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public Location getLocation() {
        return this.mInterstitialView.getLocation();
    }

    public void destroy() {
        attemptStateTransition(InterstitialState.DESTROYED);
    }

    public void setInterstitialAdListener(InterstitialAdListener interstitialAdListener) {
        this.mInterstitialAdListener = interstitialAdListener;
    }

    public InterstitialAdListener getInterstitialAdListener() {
        return this.mInterstitialAdListener;
    }

    public void setTesting(boolean z) {
        this.mInterstitialView.setTesting(z);
    }

    public boolean getTesting() {
        return this.mInterstitialView.getTesting();
    }

    public void setLocalExtras(Map<String, Object> map) {
        this.mInterstitialView.setLocalExtras(map);
    }

    public Map<String, Object> getLocalExtras() {
        return this.mInterstitialView.getLocalExtras();
    }

    public void onCustomEventInterstitialLoaded() {
        if (!isDestroyed()) {
            attemptStateTransition(InterstitialState.READY);
        }
    }

    public void onCustomEventInterstitialFailed(MoPubErrorCode moPubErrorCode) {
        if (!isDestroyed()) {
            if (this.mCurrentInterstitialState == InterstitialState.LOADING) {
                MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, Integer.valueOf(moPubErrorCode.getIntCode()), moPubErrorCode);
            } else if (this.mCurrentInterstitialState == InterstitialState.SHOWING) {
                MoPubLog.log(MoPubLog.AdLogEvent.SHOW_FAILED, Integer.valueOf(moPubErrorCode.getIntCode()), moPubErrorCode);
            }
            if (!this.mInterstitialView.loadFailUrl(moPubErrorCode)) {
                attemptStateTransition(InterstitialState.IDLE);
            }
        }
    }

    public void onCustomEventInterstitialShown() {
        if (!isDestroyed()) {
            MoPubLog.log(MoPubLog.AdLogEvent.SHOW_SUCCESS, new Object[0]);
            CustomEventInterstitialAdapter customEventInterstitialAdapter = this.mCustomEventInterstitialAdapter;
            if (customEventInterstitialAdapter == null || customEventInterstitialAdapter.isAutomaticImpressionAndClickTrackingEnabled()) {
                this.mInterstitialView.trackImpression();
            }
            InterstitialAdListener interstitialAdListener = this.mInterstitialAdListener;
            if (interstitialAdListener != null) {
                interstitialAdListener.onInterstitialShown(this);
            }
        }
    }

    public void onCustomEventInterstitialClicked() {
        if (!isDestroyed()) {
            MoPubLog.log(MoPubLog.AdLogEvent.CLICKED, new Object[0]);
            this.mInterstitialView.registerClick();
            InterstitialAdListener interstitialAdListener = this.mInterstitialAdListener;
            if (interstitialAdListener != null) {
                interstitialAdListener.onInterstitialClicked(this);
            }
        }
    }

    public void onCustomEventInterstitialImpression() {
        CustomEventInterstitialAdapter customEventInterstitialAdapter;
        if (!isDestroyed() && (customEventInterstitialAdapter = this.mCustomEventInterstitialAdapter) != null && !customEventInterstitialAdapter.isAutomaticImpressionAndClickTrackingEnabled()) {
            this.mInterstitialView.trackImpression();
        }
    }

    public void onCustomEventInterstitialDismissed() {
        if (!isDestroyed()) {
            MoPubLog.log(MoPubLog.AdLogEvent.WILL_DISAPPEAR, new Object[0]);
            attemptStateTransition(InterstitialState.IDLE);
            InterstitialAdListener interstitialAdListener = this.mInterstitialAdListener;
            if (interstitialAdListener != null) {
                interstitialAdListener.onInterstitialDismissed(this);
            }
        }
    }

    public class MoPubInterstitialView extends MoPubView {
        public MoPubInterstitialView(Context context) {
            super(context);
            setAutorefreshEnabled(false);
        }

        /* access modifiers changed from: package-private */
        public String getCustomEventClassName() {
            return this.mAdViewController.getCustomEventClassName();
        }

        public AdFormat getAdFormat() {
            return AdFormat.INTERSTITIAL;
        }

        /* access modifiers changed from: protected */
        public void loadCustomEvent(String str, Map<String, String> map) {
            if (this.mAdViewController != null) {
                if (TextUtils.isEmpty(str)) {
                    MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Couldn't invoke custom event because the server did not specify one.");
                    loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
                    return;
                }
                if (MoPubInterstitial.this.mCustomEventInterstitialAdapter != null) {
                    MoPubInterstitial.this.mCustomEventInterstitialAdapter.invalidate();
                }
                MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Loading custom event interstitial adapter.");
                MoPubInterstitial moPubInterstitial = MoPubInterstitial.this;
                CustomEventInterstitialAdapter unused = moPubInterstitial.mCustomEventInterstitialAdapter = CustomEventInterstitialAdapterFactory.create(moPubInterstitial, str, map, this.mAdViewController.getBroadcastIdentifier(), this.mAdViewController.getAdReport());
                MoPubInterstitial.this.mCustomEventInterstitialAdapter.setAdapterListener(MoPubInterstitial.this);
                MoPubInterstitial.this.mCustomEventInterstitialAdapter.loadInterstitial();
            }
        }

        /* access modifiers changed from: protected */
        public void trackImpression() {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Tracking impression for interstitial.");
            if (this.mAdViewController != null) {
                this.mAdViewController.trackImpression();
            }
        }

        /* access modifiers changed from: protected */
        public void adFailed(MoPubErrorCode moPubErrorCode) {
            boolean unused = MoPubInterstitial.this.attemptStateTransition(InterstitialState.IDLE);
            if (MoPubInterstitial.this.mInterstitialAdListener != null) {
                MoPubInterstitial.this.mInterstitialAdListener.onInterstitialFailed(MoPubInterstitial.this, moPubErrorCode);
            }
        }

        /* access modifiers changed from: protected */
        public Point resolveAdSize() {
            return DeviceUtils.getDeviceDimensions(MoPubInterstitial.this.mActivity);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setInterstitialView(MoPubInterstitialView moPubInterstitialView) {
        this.mInterstitialView = moPubInterstitialView;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setCurrentInterstitialState(InterstitialState interstitialState) {
        this.mCurrentInterstitialState = interstitialState;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public InterstitialState getCurrentInterstitialState() {
        return this.mCurrentInterstitialState;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setCustomEventInterstitialAdapter(CustomEventInterstitialAdapter customEventInterstitialAdapter) {
        this.mCustomEventInterstitialAdapter = customEventInterstitialAdapter;
    }
}
