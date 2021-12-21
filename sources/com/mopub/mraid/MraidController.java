package com.mopub.mraid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.widget.FrameLayout;
import com.appnext.base.b.d;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Dips;
import com.mopub.common.util.ManifestUtils;
import com.mopub.common.util.Utils;
import com.mopub.common.util.Views;
import com.mopub.mobileads.BaseWebView;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MraidVideoPlayerActivity;
import com.mopub.mobileads.util.WebViews;
import com.mopub.mraid.MraidBridge;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.util.EnumSet;

public class MraidController {
    private final AdReport mAdReport;
    private boolean mAllowOrientationChange;
    private final CloseableLayout mCloseableAdContainer;
    /* access modifiers changed from: private */
    public final Context mContext;
    private MraidWebViewDebugListener mDebugListener;
    private UrlHandler.MoPubSchemeListener mDebugSchemeListener;
    /* access modifiers changed from: private */
    public final FrameLayout mDefaultAdContainer;
    private MraidOrientation mForceOrientation;
    private boolean mIsPaused;
    /* access modifiers changed from: private */
    public final MraidBridge mMraidBridge;
    private final MraidBridge.MraidBridgeListener mMraidBridgeListener;
    /* access modifiers changed from: private */
    public MraidListener mMraidListener;
    /* access modifiers changed from: private */
    public final MraidNativeCommandHandler mMraidNativeCommandHandler;
    /* access modifiers changed from: private */
    public MraidBridge.MraidWebView mMraidWebView;
    private UseCustomCloseListener mOnCloseButtonListener;
    private OrientationBroadcastReceiver mOrientationBroadcastReceiver;
    private Integer mOriginalActivityOrientation;
    /* access modifiers changed from: private */
    public final PlacementType mPlacementType;
    private ViewGroup mRootView;
    /* access modifiers changed from: private */
    public final MraidScreenMetrics mScreenMetrics;
    private final ScreenMetricsWaiter mScreenMetricsWaiter;
    /* access modifiers changed from: private */
    public final MraidBridge mTwoPartBridge;
    private final MraidBridge.MraidBridgeListener mTwoPartBridgeListener;
    private MraidBridge.MraidWebView mTwoPartWebView;
    /* access modifiers changed from: private */
    public ViewState mViewState;
    private WeakReference<Activity> mWeakActivity;

    public interface MraidListener {
        void onClose();

        void onExpand();

        void onFailedToLoad();

        void onLoaded(View view);

        void onOpen();

        void onRenderProcessGone(MoPubErrorCode moPubErrorCode);

        void onResize(boolean z);
    }

    public interface MraidWebViewCacheListener {
        void onReady(MraidBridge.MraidWebView mraidWebView, ExternalViewabilitySessionManager externalViewabilitySessionManager);
    }

    public interface UseCustomCloseListener {
        void useCustomCloseChanged(boolean z);
    }

    public MraidController(Context context, AdReport adReport, PlacementType placementType) {
        this(context, adReport, placementType, new MraidBridge(adReport, placementType), new MraidBridge(adReport, PlacementType.INTERSTITIAL), new ScreenMetricsWaiter());
    }

    MraidController(Context context, AdReport adReport, PlacementType placementType, MraidBridge mraidBridge, MraidBridge mraidBridge2, ScreenMetricsWaiter screenMetricsWaiter) {
        this.mViewState = ViewState.LOADING;
        this.mOrientationBroadcastReceiver = new OrientationBroadcastReceiver();
        this.mDebugSchemeListener = new UrlHandler.MoPubSchemeListener() {
            public void onClose() {
            }

            public void onFailLoad() {
            }

            public void onFinishLoad() {
            }

            public void onCrash() {
                if (MraidController.this.mMraidWebView != null) {
                    MraidController.this.mMraidWebView.loadUrl("chrome://crash");
                }
            }
        };
        this.mAllowOrientationChange = true;
        this.mForceOrientation = MraidOrientation.NONE;
        this.mIsPaused = true;
        this.mMraidBridgeListener = new MraidBridge.MraidBridgeListener() {
            public void onPageLoaded() {
                MraidController.this.handlePageLoad();
                if (MraidController.this.mMraidListener != null) {
                    MraidController.this.mMraidListener.onLoaded(MraidController.this.mDefaultAdContainer);
                }
            }

            public void onPageFailedToLoad() {
                if (MraidController.this.mMraidListener != null) {
                    MraidController.this.mMraidListener.onFailedToLoad();
                }
            }

            public void onRenderProcessGone(MoPubErrorCode moPubErrorCode) {
                MraidController.this.handleRenderProcessGone(moPubErrorCode);
            }

            public void onVisibilityChanged(boolean z) {
                if (!MraidController.this.mTwoPartBridge.isAttached()) {
                    MraidController.this.mMraidBridge.notifyViewability(z);
                }
            }

            public boolean onJsAlert(String str, JsResult jsResult) {
                return MraidController.this.handleJsAlert(str, jsResult);
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return MraidController.this.handleConsoleMessage(consoleMessage);
            }

            public void onClose() {
                MraidController.this.handleClose();
            }

            public void onResize(int i, int i2, int i3, int i4, CloseableLayout.ClosePosition closePosition, boolean z) throws MraidCommandException {
                MraidController.this.handleResize(i, i2, i3, i4, closePosition, z);
            }

            public void onExpand(URI uri, boolean z) throws MraidCommandException {
                MraidController.this.handleExpand(uri, z);
            }

            public void onUseCustomClose(boolean z) {
                MraidController.this.handleCustomClose(z);
            }

            public void onSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) throws MraidCommandException {
                MraidController.this.handleSetOrientationProperties(z, mraidOrientation);
            }

            public void onOpen(URI uri) {
                MraidController.this.handleOpen(uri.toString());
            }

            public void onPlayVideo(URI uri) {
                MraidController.this.handleShowVideo(uri.toString());
            }
        };
        this.mTwoPartBridgeListener = new MraidBridge.MraidBridgeListener() {
            public void onExpand(URI uri, boolean z) {
            }

            public void onPageFailedToLoad() {
            }

            public void onPageLoaded() {
                MraidController.this.handleTwoPartPageLoad();
            }

            public void onRenderProcessGone(MoPubErrorCode moPubErrorCode) {
                MraidController.this.handleRenderProcessGone(moPubErrorCode);
            }

            public void onVisibilityChanged(boolean z) {
                MraidController.this.mMraidBridge.notifyViewability(z);
                MraidController.this.mTwoPartBridge.notifyViewability(z);
            }

            public boolean onJsAlert(String str, JsResult jsResult) {
                return MraidController.this.handleJsAlert(str, jsResult);
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return MraidController.this.handleConsoleMessage(consoleMessage);
            }

            public void onResize(int i, int i2, int i3, int i4, CloseableLayout.ClosePosition closePosition, boolean z) throws MraidCommandException {
                throw new MraidCommandException("Not allowed to resize from an expanded state");
            }

            public void onClose() {
                MraidController.this.handleClose();
            }

            public void onUseCustomClose(boolean z) {
                MraidController.this.handleCustomClose(z);
            }

            public void onSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) throws MraidCommandException {
                MraidController.this.handleSetOrientationProperties(z, mraidOrientation);
            }

            public void onOpen(URI uri) {
                MraidController.this.handleOpen(uri.toString());
            }

            public void onPlayVideo(URI uri) {
                MraidController.this.handleShowVideo(uri.toString());
            }
        };
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        Preconditions.checkNotNull(applicationContext);
        this.mAdReport = adReport;
        if (context instanceof Activity) {
            this.mWeakActivity = new WeakReference<>((Activity) context);
        } else {
            this.mWeakActivity = new WeakReference<>((Object) null);
        }
        this.mPlacementType = placementType;
        this.mMraidBridge = mraidBridge;
        this.mTwoPartBridge = mraidBridge2;
        this.mScreenMetricsWaiter = screenMetricsWaiter;
        this.mViewState = ViewState.LOADING;
        this.mScreenMetrics = new MraidScreenMetrics(this.mContext, this.mContext.getResources().getDisplayMetrics().density);
        this.mDefaultAdContainer = new FrameLayout(this.mContext);
        CloseableLayout closeableLayout = new CloseableLayout(this.mContext);
        this.mCloseableAdContainer = closeableLayout;
        closeableLayout.setOnCloseListener(new CloseableLayout.OnCloseListener() {
            public void onClose() {
                MraidController.this.handleClose();
            }
        });
        View view = new View(this.mContext);
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.mCloseableAdContainer.addView(view, new FrameLayout.LayoutParams(-1, -1));
        this.mOrientationBroadcastReceiver.register(this.mContext);
        this.mMraidBridge.setMraidBridgeListener(this.mMraidBridgeListener);
        this.mTwoPartBridge.setMraidBridgeListener(this.mTwoPartBridgeListener);
        this.mMraidNativeCommandHandler = new MraidNativeCommandHandler();
    }

    public void setMraidListener(MraidListener mraidListener) {
        this.mMraidListener = mraidListener;
    }

    public void setUseCustomCloseListener(UseCustomCloseListener useCustomCloseListener) {
        this.mOnCloseButtonListener = useCustomCloseListener;
    }

    public void setDebugListener(MraidWebViewDebugListener mraidWebViewDebugListener) {
        this.mDebugListener = mraidWebViewDebugListener;
    }

    public void fillContent(String str, MraidWebViewCacheListener mraidWebViewCacheListener) {
        Preconditions.checkNotNull(str, "htmlData cannot be null");
        MraidBridge.MraidWebView mraidWebView = new MraidBridge.MraidWebView(this.mContext);
        this.mMraidWebView = mraidWebView;
        if (mraidWebViewCacheListener != null) {
            mraidWebViewCacheListener.onReady(mraidWebView, (ExternalViewabilitySessionManager) null);
        }
        this.mMraidBridge.attachView(this.mMraidWebView);
        this.mDefaultAdContainer.addView(this.mMraidWebView, new FrameLayout.LayoutParams(-1, -1));
        this.mMraidBridge.setContentHtml(str);
    }

    public void onPreloadFinished(BaseWebView baseWebView) {
        MraidBridge.MraidWebView mraidWebView = (MraidBridge.MraidWebView) baseWebView;
        this.mMraidWebView = mraidWebView;
        this.mMraidBridge.attachView(mraidWebView);
        this.mDefaultAdContainer.addView(this.mMraidWebView, new FrameLayout.LayoutParams(-1, -1));
        handlePageLoad();
    }

    public void onShow(Activity activity) {
        this.mWeakActivity = new WeakReference<>(activity);
        UseCustomCloseListener useCustomCloseListener = this.mOnCloseButtonListener;
        if (useCustomCloseListener != null) {
            useCustomCloseListener.useCustomCloseChanged(isUsingCustomClose());
        }
        try {
            applyOrientation();
        } catch (MraidCommandException unused) {
            MoPubLog.d("Failed to apply orientation.");
        }
    }

    /* access modifiers changed from: private */
    public int getDisplayRotation() {
        return ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    /* access modifiers changed from: package-private */
    public boolean handleConsoleMessage(ConsoleMessage consoleMessage) {
        MraidWebViewDebugListener mraidWebViewDebugListener = this.mDebugListener;
        if (mraidWebViewDebugListener != null) {
            return mraidWebViewDebugListener.onConsoleMessage(consoleMessage);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean handleJsAlert(String str, JsResult jsResult) {
        MraidWebViewDebugListener mraidWebViewDebugListener = this.mDebugListener;
        if (mraidWebViewDebugListener != null) {
            return mraidWebViewDebugListener.onJsAlert(str, jsResult);
        }
        jsResult.confirm();
        return true;
    }

    static class ScreenMetricsWaiter {
        private final Handler mHandler = new Handler();
        private WaitRequest mLastWaitRequest;

        ScreenMetricsWaiter() {
        }

        static class WaitRequest {
            private final Handler mHandler;
            private Runnable mSuccessRunnable;
            /* access modifiers changed from: private */
            public final View[] mViews;
            int mWaitCount;
            private final Runnable mWaitingRunnable;

            private WaitRequest(Handler handler, View[] viewArr) {
                this.mWaitingRunnable = new Runnable() {
                    public void run() {
                        for (final View view : WaitRequest.this.mViews) {
                            if (view.getHeight() > 0 || view.getWidth() > 0) {
                                WaitRequest.this.countDown();
                            } else {
                                view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                    public boolean onPreDraw() {
                                        view.getViewTreeObserver().removeOnPreDrawListener(this);
                                        WaitRequest.this.countDown();
                                        return true;
                                    }
                                });
                            }
                        }
                    }
                };
                this.mHandler = handler;
                this.mViews = viewArr;
            }

            /* access modifiers changed from: private */
            public void countDown() {
                Runnable runnable;
                int i = this.mWaitCount - 1;
                this.mWaitCount = i;
                if (i == 0 && (runnable = this.mSuccessRunnable) != null) {
                    runnable.run();
                    this.mSuccessRunnable = null;
                }
            }

            /* access modifiers changed from: package-private */
            public void start(Runnable runnable) {
                this.mSuccessRunnable = runnable;
                this.mWaitCount = this.mViews.length;
                this.mHandler.post(this.mWaitingRunnable);
            }

            /* access modifiers changed from: package-private */
            public void cancel() {
                this.mHandler.removeCallbacks(this.mWaitingRunnable);
                this.mSuccessRunnable = null;
            }
        }

        /* access modifiers changed from: package-private */
        public WaitRequest waitFor(View... viewArr) {
            WaitRequest waitRequest = new WaitRequest(this.mHandler, viewArr);
            this.mLastWaitRequest = waitRequest;
            return waitRequest;
        }

        /* access modifiers changed from: package-private */
        public void cancelLastRequest() {
            WaitRequest waitRequest = this.mLastWaitRequest;
            if (waitRequest != null) {
                waitRequest.cancel();
                this.mLastWaitRequest = null;
            }
        }
    }

    public MraidBridge.MraidWebView getCurrentWebView() {
        return this.mTwoPartBridge.isAttached() ? this.mTwoPartWebView : this.mMraidWebView;
    }

    /* access modifiers changed from: package-private */
    public boolean isInlineVideoAvailable() {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null || getCurrentWebView() == null) {
            return false;
        }
        if (this.mPlacementType != PlacementType.INLINE) {
            return true;
        }
        return this.mMraidNativeCommandHandler.isInlineVideoAvailable(activity, getCurrentWebView());
    }

    /* access modifiers changed from: package-private */
    public void handlePageLoad() {
        this.mMraidBridge.notifySupports(this.mMraidNativeCommandHandler.isSmsAvailable(this.mContext), this.mMraidNativeCommandHandler.isTelAvailable(this.mContext), MraidNativeCommandHandler.isCalendarAvailable(this.mContext), MraidNativeCommandHandler.isStorePictureSupported(this.mContext), isInlineVideoAvailable());
        this.mMraidBridge.notifyPlacementType(this.mPlacementType);
        MraidBridge mraidBridge = this.mMraidBridge;
        mraidBridge.notifyViewability(mraidBridge.isViewable());
        this.mMraidBridge.notifyScreenMetrics(this.mScreenMetrics);
        setViewState(ViewState.DEFAULT);
        this.mMraidBridge.notifyReady();
    }

    /* access modifiers changed from: package-private */
    public void handleTwoPartPageLoad() {
        updateScreenMetricsAsync(new Runnable() {
            public void run() {
                MraidBridge access$300 = MraidController.this.mTwoPartBridge;
                boolean isSmsAvailable = MraidController.this.mMraidNativeCommandHandler.isSmsAvailable(MraidController.this.mContext);
                boolean isTelAvailable = MraidController.this.mMraidNativeCommandHandler.isTelAvailable(MraidController.this.mContext);
                MraidNativeCommandHandler unused = MraidController.this.mMraidNativeCommandHandler;
                boolean isCalendarAvailable = MraidNativeCommandHandler.isCalendarAvailable(MraidController.this.mContext);
                MraidNativeCommandHandler unused2 = MraidController.this.mMraidNativeCommandHandler;
                access$300.notifySupports(isSmsAvailable, isTelAvailable, isCalendarAvailable, MraidNativeCommandHandler.isStorePictureSupported(MraidController.this.mContext), MraidController.this.isInlineVideoAvailable());
                MraidController.this.mTwoPartBridge.notifyViewState(MraidController.this.mViewState);
                MraidController.this.mTwoPartBridge.notifyPlacementType(MraidController.this.mPlacementType);
                MraidController.this.mTwoPartBridge.notifyViewability(MraidController.this.mTwoPartBridge.isViewable());
                MraidController.this.mTwoPartBridge.notifyReady();
            }
        });
    }

    private void updateScreenMetricsAsync(final Runnable runnable) {
        this.mScreenMetricsWaiter.cancelLastRequest();
        final MraidBridge.MraidWebView currentWebView = getCurrentWebView();
        if (currentWebView != null) {
            this.mScreenMetricsWaiter.waitFor(this.mDefaultAdContainer, currentWebView).start(new Runnable() {
                public void run() {
                    DisplayMetrics displayMetrics = MraidController.this.mContext.getResources().getDisplayMetrics();
                    MraidController.this.mScreenMetrics.setScreenSize(displayMetrics.widthPixels, displayMetrics.heightPixels);
                    int[] iArr = new int[2];
                    ViewGroup access$1300 = MraidController.this.getRootView();
                    access$1300.getLocationOnScreen(iArr);
                    MraidController.this.mScreenMetrics.setRootViewPosition(iArr[0], iArr[1], access$1300.getWidth(), access$1300.getHeight());
                    MraidController.this.mDefaultAdContainer.getLocationOnScreen(iArr);
                    MraidController.this.mScreenMetrics.setDefaultAdPosition(iArr[0], iArr[1], MraidController.this.mDefaultAdContainer.getWidth(), MraidController.this.mDefaultAdContainer.getHeight());
                    currentWebView.getLocationOnScreen(iArr);
                    MraidController.this.mScreenMetrics.setCurrentAdPosition(iArr[0], iArr[1], currentWebView.getWidth(), currentWebView.getHeight());
                    MraidController.this.mMraidBridge.notifyScreenMetrics(MraidController.this.mScreenMetrics);
                    if (MraidController.this.mTwoPartBridge.isAttached()) {
                        MraidController.this.mTwoPartBridge.notifyScreenMetrics(MraidController.this.mScreenMetrics);
                    }
                    Runnable runnable = runnable;
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void handleOrientationChange(int i) {
        updateScreenMetricsAsync((Runnable) null);
    }

    public void pause(boolean z) {
        this.mIsPaused = true;
        MraidBridge.MraidWebView mraidWebView = this.mMraidWebView;
        if (mraidWebView != null) {
            WebViews.onPause(mraidWebView, z);
        }
        MraidBridge.MraidWebView mraidWebView2 = this.mTwoPartWebView;
        if (mraidWebView2 != null) {
            WebViews.onPause(mraidWebView2, z);
        }
    }

    public void resume() {
        this.mIsPaused = false;
        MraidBridge.MraidWebView mraidWebView = this.mMraidWebView;
        if (mraidWebView != null) {
            mraidWebView.onResume();
        }
        MraidBridge.MraidWebView mraidWebView2 = this.mTwoPartWebView;
        if (mraidWebView2 != null) {
            mraidWebView2.onResume();
        }
    }

    public void destroy() {
        this.mScreenMetricsWaiter.cancelLastRequest();
        try {
            this.mOrientationBroadcastReceiver.unregister();
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("Receiver not registered")) {
                throw e;
            }
        }
        if (!this.mIsPaused) {
            pause(true);
        }
        Views.removeFromParent(this.mCloseableAdContainer);
        detachMraidWebView();
        detachTwoPartWebView();
        unApplyOrientation();
    }

    private void detachMraidWebView() {
        this.mMraidBridge.detach();
        this.mMraidWebView = null;
    }

    private void detachTwoPartWebView() {
        this.mTwoPartBridge.detach();
        this.mTwoPartWebView = null;
    }

    private void setViewState(ViewState viewState) {
        MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
        MoPubLog.log(sdkLogEvent, "MRAID state set to " + viewState);
        ViewState viewState2 = this.mViewState;
        this.mViewState = viewState;
        this.mMraidBridge.notifyViewState(viewState);
        if (this.mTwoPartBridge.isLoaded()) {
            this.mTwoPartBridge.notifyViewState(viewState);
        }
        MraidListener mraidListener = this.mMraidListener;
        if (mraidListener != null) {
            callMraidListenerCallbacks(mraidListener, viewState2, viewState);
        }
        updateScreenMetricsAsync((Runnable) null);
    }

    static void callMraidListenerCallbacks(MraidListener mraidListener, ViewState viewState, ViewState viewState2) {
        Preconditions.checkNotNull(mraidListener);
        Preconditions.checkNotNull(viewState);
        Preconditions.checkNotNull(viewState2);
        if (viewState2 == ViewState.EXPANDED) {
            mraidListener.onExpand();
        } else if (viewState == ViewState.EXPANDED && viewState2 == ViewState.DEFAULT) {
            mraidListener.onClose();
        } else if (viewState2 == ViewState.HIDDEN) {
            mraidListener.onClose();
        } else if (viewState == ViewState.RESIZED && viewState2 == ViewState.DEFAULT) {
            mraidListener.onResize(true);
        } else if (viewState2 == ViewState.RESIZED) {
            mraidListener.onResize(false);
        }
    }

    /* access modifiers changed from: package-private */
    public int clampInt(int i, int i2, int i3) {
        return Math.max(i, Math.min(i2, i3));
    }

    /* access modifiers changed from: package-private */
    public void handleResize(int i, int i2, int i3, int i4, CloseableLayout.ClosePosition closePosition, boolean z) throws MraidCommandException {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        CloseableLayout.ClosePosition closePosition2 = closePosition;
        if (this.mMraidWebView == null) {
            throw new MraidCommandException("Unable to resize after the WebView is destroyed");
        } else if (this.mViewState != ViewState.LOADING && this.mViewState != ViewState.HIDDEN) {
            if (this.mViewState == ViewState.EXPANDED) {
                throw new MraidCommandException("Not allowed to resize from an already expanded ad");
            } else if (this.mPlacementType != PlacementType.INTERSTITIAL) {
                int dipsToIntPixels = Dips.dipsToIntPixels((float) i5, this.mContext);
                int dipsToIntPixels2 = Dips.dipsToIntPixels((float) i6, this.mContext);
                int dipsToIntPixels3 = Dips.dipsToIntPixels((float) i7, this.mContext);
                int dipsToIntPixels4 = Dips.dipsToIntPixels((float) i8, this.mContext);
                int i9 = this.mScreenMetrics.getDefaultAdRect().left + dipsToIntPixels3;
                int i10 = this.mScreenMetrics.getDefaultAdRect().top + dipsToIntPixels4;
                Rect rect = new Rect(i9, i10, dipsToIntPixels + i9, i10 + dipsToIntPixels2);
                if (!z) {
                    Rect rootViewRect = this.mScreenMetrics.getRootViewRect();
                    if (rect.width() > rootViewRect.width() || rect.height() > rootViewRect.height()) {
                        throw new MraidCommandException("resizeProperties specified a size (" + i5 + ", " + i6 + ") and offset (" + i7 + ", " + i8 + ") that doesn't allow the ad to appear within the max allowed size (" + this.mScreenMetrics.getRootViewRectDips().width() + ", " + this.mScreenMetrics.getRootViewRectDips().height() + ")");
                    }
                    rect.offsetTo(clampInt(rootViewRect.left, rect.left, rootViewRect.right - rect.width()), clampInt(rootViewRect.top, rect.top, rootViewRect.bottom - rect.height()));
                }
                Rect rect2 = new Rect();
                this.mCloseableAdContainer.applyCloseRegionBounds(closePosition2, rect, rect2);
                if (!this.mScreenMetrics.getRootViewRect().contains(rect2)) {
                    throw new MraidCommandException("resizeProperties specified a size (" + i5 + ", " + i6 + ") and offset (" + i7 + ", " + i8 + ") that doesn't allow the close region to appear within the max allowed size (" + this.mScreenMetrics.getRootViewRectDips().width() + ", " + this.mScreenMetrics.getRootViewRectDips().height() + ")");
                } else if (rect.contains(rect2)) {
                    this.mCloseableAdContainer.setClosePosition(closePosition2);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(rect.width(), rect.height());
                    layoutParams.leftMargin = rect.left - this.mScreenMetrics.getRootViewRect().left;
                    layoutParams.topMargin = rect.top - this.mScreenMetrics.getRootViewRect().top;
                    if (this.mViewState == ViewState.DEFAULT) {
                        this.mDefaultAdContainer.removeView(this.mMraidWebView);
                        this.mDefaultAdContainer.setVisibility(4);
                        this.mCloseableAdContainer.addView(this.mMraidWebView, new FrameLayout.LayoutParams(-1, -1));
                        getAndMemoizeRootView().addView(this.mCloseableAdContainer, layoutParams);
                    } else if (this.mViewState == ViewState.RESIZED) {
                        this.mCloseableAdContainer.setLayoutParams(layoutParams);
                    }
                    this.mCloseableAdContainer.setClosePosition(closePosition2);
                    setViewState(ViewState.RESIZED);
                } else {
                    throw new MraidCommandException("resizeProperties specified a size (" + i5 + ", " + dipsToIntPixels2 + ") and offset (" + i7 + ", " + i8 + ") that don't allow the close region to appear within the resized ad.");
                }
            } else {
                throw new MraidCommandException("Not allowed to resize from an interstitial ad");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handleExpand(URI uri, boolean z) throws MraidCommandException {
        if (this.mMraidWebView == null) {
            throw new MraidCommandException("Unable to expand after the WebView is destroyed");
        } else if (this.mPlacementType != PlacementType.INTERSTITIAL) {
            if (this.mViewState == ViewState.DEFAULT || this.mViewState == ViewState.RESIZED) {
                applyOrientation();
                boolean z2 = uri != null;
                if (z2) {
                    MraidBridge.MraidWebView mraidWebView = new MraidBridge.MraidWebView(this.mContext);
                    this.mTwoPartWebView = mraidWebView;
                    this.mTwoPartBridge.attachView(mraidWebView);
                    this.mTwoPartBridge.setContentUrl(uri.toString());
                }
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                if (this.mViewState == ViewState.DEFAULT) {
                    if (z2) {
                        this.mCloseableAdContainer.addView(this.mTwoPartWebView, layoutParams);
                    } else {
                        this.mDefaultAdContainer.removeView(this.mMraidWebView);
                        this.mDefaultAdContainer.setVisibility(4);
                        this.mCloseableAdContainer.addView(this.mMraidWebView, layoutParams);
                    }
                    getAndMemoizeRootView().addView(this.mCloseableAdContainer, new FrameLayout.LayoutParams(-1, -1));
                } else if (this.mViewState == ViewState.RESIZED && z2) {
                    this.mCloseableAdContainer.removeView(this.mMraidWebView);
                    this.mDefaultAdContainer.addView(this.mMraidWebView, layoutParams);
                    this.mDefaultAdContainer.setVisibility(4);
                    this.mCloseableAdContainer.addView(this.mTwoPartWebView, layoutParams);
                }
                this.mCloseableAdContainer.setLayoutParams(layoutParams);
                handleCustomClose(z);
                setViewState(ViewState.EXPANDED);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void handleClose() {
        MraidBridge.MraidWebView mraidWebView;
        if (this.mMraidWebView != null && this.mViewState != ViewState.LOADING && this.mViewState != ViewState.HIDDEN) {
            if (this.mViewState == ViewState.EXPANDED || this.mPlacementType == PlacementType.INTERSTITIAL) {
                unApplyOrientation();
            }
            if (this.mViewState == ViewState.RESIZED || this.mViewState == ViewState.EXPANDED) {
                if (!this.mTwoPartBridge.isAttached() || (mraidWebView = this.mTwoPartWebView) == null) {
                    this.mCloseableAdContainer.removeView(this.mMraidWebView);
                    this.mDefaultAdContainer.addView(this.mMraidWebView, new FrameLayout.LayoutParams(-1, -1));
                    this.mDefaultAdContainer.setVisibility(0);
                } else {
                    detachTwoPartWebView();
                    this.mCloseableAdContainer.removeView(mraidWebView);
                }
                Views.removeFromParent(this.mCloseableAdContainer);
                setViewState(ViewState.DEFAULT);
            } else if (this.mViewState == ViewState.DEFAULT) {
                this.mDefaultAdContainer.setVisibility(4);
                setViewState(ViewState.HIDDEN);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handleRenderProcessGone(MoPubErrorCode moPubErrorCode) {
        MraidListener mraidListener = this.mMraidListener;
        if (mraidListener != null) {
            mraidListener.onRenderProcessGone(moPubErrorCode);
        }
    }

    /* access modifiers changed from: private */
    public ViewGroup getRootView() {
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup != null) {
            return viewGroup;
        }
        View topmostView = Views.getTopmostView((Context) this.mWeakActivity.get(), this.mDefaultAdContainer);
        return topmostView instanceof ViewGroup ? (ViewGroup) topmostView : this.mDefaultAdContainer;
    }

    private ViewGroup getAndMemoizeRootView() {
        if (this.mRootView == null) {
            this.mRootView = getRootView();
        }
        return this.mRootView;
    }

    /* access modifiers changed from: package-private */
    public void handleShowVideo(String str) {
        MraidVideoPlayerActivity.startMraid(this.mContext, str);
    }

    /* access modifiers changed from: package-private */
    public void lockOrientation(int i) throws MraidCommandException {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null || !shouldAllowForceOrientation(this.mForceOrientation)) {
            throw new MraidCommandException("Attempted to lock orientation to unsupported value: " + this.mForceOrientation.name());
        }
        if (this.mOriginalActivityOrientation == null) {
            this.mOriginalActivityOrientation = Integer.valueOf(activity.getRequestedOrientation());
        }
        activity.setRequestedOrientation(i);
    }

    /* access modifiers changed from: package-private */
    public void applyOrientation() throws MraidCommandException {
        if (this.mForceOrientation != MraidOrientation.NONE) {
            lockOrientation(this.mForceOrientation.getActivityInfoOrientation());
        } else if (this.mAllowOrientationChange) {
            unApplyOrientation();
        } else {
            Activity activity = (Activity) this.mWeakActivity.get();
            if (activity != null) {
                lockOrientation(DeviceUtils.getScreenOrientation(activity));
                return;
            }
            throw new MraidCommandException("Unable to set MRAID expand orientation to 'none'; expected passed in Activity Context.");
        }
    }

    /* access modifiers changed from: package-private */
    public void unApplyOrientation() {
        Integer num;
        Activity activity = (Activity) this.mWeakActivity.get();
        if (!(activity == null || (num = this.mOriginalActivityOrientation) == null)) {
            activity.setRequestedOrientation(num.intValue());
        }
        this.mOriginalActivityOrientation = null;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAllowForceOrientation(MraidOrientation mraidOrientation) {
        if (mraidOrientation == MraidOrientation.NONE) {
            return true;
        }
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null) {
            return false;
        }
        try {
            ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(new ComponentName(activity, activity.getClass()), 0);
            int i = activityInfo.screenOrientation;
            if (i != -1) {
                if (i == mraidOrientation.getActivityInfoOrientation()) {
                    return true;
                }
                return false;
            } else if (!Utils.bitMaskContainsFlag(activityInfo.configChanges, 128) || !Utils.bitMaskContainsFlag(activityInfo.configChanges, d.fb)) {
                return false;
            } else {
                return true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void handleCustomClose(boolean z) {
        if (z != isUsingCustomClose()) {
            this.mCloseableAdContainer.setCloseVisible(!z);
            UseCustomCloseListener useCustomCloseListener = this.mOnCloseButtonListener;
            if (useCustomCloseListener != null) {
                useCustomCloseListener.useCustomCloseChanged(z);
            }
        }
    }

    private boolean isUsingCustomClose() {
        return !this.mCloseableAdContainer.isCloseVisible();
    }

    public FrameLayout getAdContainer() {
        return this.mDefaultAdContainer;
    }

    public void loadJavascript(String str) {
        this.mMraidBridge.injectJavaScript(str);
    }

    class OrientationBroadcastReceiver extends BroadcastReceiver {
        private Context mContext;
        private int mLastRotation = -1;

        OrientationBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            int access$1400;
            if (this.mContext != null && "android.intent.action.CONFIGURATION_CHANGED".equals(intent.getAction()) && (access$1400 = MraidController.this.getDisplayRotation()) != this.mLastRotation) {
                this.mLastRotation = access$1400;
                MraidController.this.handleOrientationChange(access$1400);
            }
        }

        public void register(Context context) {
            Preconditions.checkNotNull(context);
            Context applicationContext = context.getApplicationContext();
            this.mContext = applicationContext;
            if (applicationContext != null) {
                applicationContext.registerReceiver(this, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
            }
        }

        public void unregister() {
            Context context = this.mContext;
            if (context != null) {
                context.unregisterReceiver(this);
                this.mContext = null;
            }
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public WeakReference<Activity> getWeakActivity() {
        return this.mWeakActivity;
    }

    /* access modifiers changed from: package-private */
    public void handleSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) throws MraidCommandException {
        if (shouldAllowForceOrientation(mraidOrientation)) {
            this.mAllowOrientationChange = z;
            this.mForceOrientation = mraidOrientation;
            if (this.mViewState == ViewState.EXPANDED || (this.mPlacementType == PlacementType.INTERSTITIAL && !this.mIsPaused)) {
                applyOrientation();
                return;
            }
            return;
        }
        throw new MraidCommandException("Unable to force orientation to " + mraidOrientation);
    }

    /* access modifiers changed from: package-private */
    public void handleOpen(String str) {
        MraidListener mraidListener = this.mMraidListener;
        if (mraidListener != null) {
            mraidListener.onOpen();
        }
        Uri parse = Uri.parse(str);
        if (UrlAction.HANDLE_PHONE_SCHEME.shouldTryHandlingUrl(parse)) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM_WITH_THROWABLE, String.format("Uri scheme %s is not allowed.", new Object[]{parse.getScheme()}), new MraidCommandException("Unsupported MRAID Javascript command"));
            return;
        }
        UrlHandler.Builder builder = new UrlHandler.Builder();
        AdReport adReport = this.mAdReport;
        if (adReport != null) {
            builder.withDspCreativeId(adReport.getDspCreativeId());
        }
        EnumSet of = EnumSet.of(UrlAction.IGNORE_ABOUT_SCHEME, new UrlAction[]{UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK});
        if (ManifestUtils.isDebuggable(this.mContext)) {
            of.add(UrlAction.HANDLE_MOPUB_SCHEME);
            builder.withMoPubSchemeListener(this.mDebugSchemeListener);
        }
        builder.withSupportedUrlActions(of).build().handleUrl(this.mContext, str);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public ViewState getViewState() {
        return this.mViewState;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setViewStateForTesting(ViewState viewState) {
        this.mViewState = viewState;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public CloseableLayout getExpandedAdContainer() {
        return this.mCloseableAdContainer;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setRootView(FrameLayout frameLayout) {
        this.mRootView = frameLayout;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setRootViewSize(int i, int i2) {
        this.mScreenMetrics.setRootViewPosition(0, 0, i, i2);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public Integer getOriginalActivityOrientation() {
        return this.mOriginalActivityOrientation;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean getAllowOrientationChange() {
        return this.mAllowOrientationChange;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public MraidOrientation getForceOrientation() {
        return this.mForceOrientation;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setOrientationBroadcastReceiver(OrientationBroadcastReceiver orientationBroadcastReceiver) {
        this.mOrientationBroadcastReceiver = orientationBroadcastReceiver;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public MraidBridge.MraidWebView getMraidWebView() {
        return this.mMraidWebView;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public MraidBridge.MraidWebView getTwoPartWebView() {
        return this.mTwoPartWebView;
    }
}
