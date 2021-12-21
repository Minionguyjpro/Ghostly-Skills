package com.mopub.mraid;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.AdViewController;
import com.mopub.mobileads.CustomEventBanner;
import com.mopub.mobileads.InternalCustomEventBannerListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.factories.MraidControllerFactory;
import com.mopub.mraid.MraidBridge;
import com.mopub.mraid.MraidController;
import java.util.Map;

class MraidBanner extends CustomEventBanner {
    public static final String ADAPTER_NAME = MraidBanner.class.getSimpleName();
    /* access modifiers changed from: private */
    public InternalCustomEventBannerListener mBannerListener;
    private MraidWebViewDebugListener mDebugListener;
    /* access modifiers changed from: private */
    public ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    private MraidController mMraidController;

    MraidBanner() {
    }

    /* access modifiers changed from: protected */
    public void loadBanner(final Context context, CustomEventBanner.CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2) {
        try {
            this.mBannerListener = (InternalCustomEventBannerListener) customEventBannerListener;
            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
            if (extrasAreValid(map2)) {
                String str = map2.get(DataKeys.HTML_RESPONSE_BODY_KEY);
                try {
                    MraidController create = MraidControllerFactory.create(context, (AdReport) map.get(DataKeys.AD_REPORT_KEY), PlacementType.INLINE);
                    this.mMraidController = create;
                    create.setDebugListener(this.mDebugListener);
                    this.mMraidController.setMraidListener(new MraidController.MraidListener() {
                        public void onLoaded(View view) {
                            AdViewController.setShouldHonorServerDimensions(view);
                            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_SUCCESS, MraidBanner.ADAPTER_NAME);
                            MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_ATTEMPTED, MraidBanner.ADAPTER_NAME);
                            MraidBanner.this.mBannerListener.onBannerLoaded(view);
                        }

                        public void onFailedToLoad() {
                            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, MraidBanner.ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.MRAID_LOAD_ERROR.getIntCode()), MoPubErrorCode.MRAID_LOAD_ERROR);
                            MraidBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
                        }

                        public void onRenderProcessGone(MoPubErrorCode moPubErrorCode) {
                            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, MraidBanner.ADAPTER_NAME, Integer.valueOf(moPubErrorCode.getIntCode()), moPubErrorCode);
                            MraidBanner.this.mBannerListener.onBannerFailed(moPubErrorCode);
                        }

                        public void onExpand() {
                            MraidBanner.this.mBannerListener.onBannerExpanded();
                            MraidBanner.this.mBannerListener.onBannerClicked();
                        }

                        public void onResize(boolean z) {
                            if (z) {
                                MraidBanner.this.mBannerListener.onResumeAutoRefresh();
                            } else {
                                MraidBanner.this.mBannerListener.onPauseAutoRefresh();
                            }
                        }

                        public void onOpen() {
                            MoPubLog.log(MoPubLog.AdapterLogEvent.CLICKED, MraidBanner.ADAPTER_NAME);
                            MraidBanner.this.mBannerListener.onBannerClicked();
                        }

                        public void onClose() {
                            MraidBanner.this.mBannerListener.onBannerCollapsed();
                        }
                    });
                    this.mMraidController.fillContent(str, new MraidController.MraidWebViewCacheListener() {
                        public void onReady(MraidBridge.MraidWebView mraidWebView, ExternalViewabilitySessionManager externalViewabilitySessionManager) {
                            mraidWebView.getSettings().setJavaScriptEnabled(true);
                            Context context = context;
                            if (context instanceof Activity) {
                                ExternalViewabilitySessionManager unused = MraidBanner.this.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(context);
                                MraidBanner.this.mExternalViewabilitySessionManager.createDisplaySession(context, mraidWebView);
                            }
                        }
                    });
                } catch (ClassCastException unused) {
                    MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.MRAID_LOAD_ERROR.getIntCode()), MoPubErrorCode.MRAID_LOAD_ERROR);
                    this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
                }
            } else {
                MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.MRAID_LOAD_ERROR.getIntCode()), MoPubErrorCode.MRAID_LOAD_ERROR);
                this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
            }
        } catch (ClassCastException unused2) {
            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.MRAID_LOAD_ERROR.getIntCode()), MoPubErrorCode.MRAID_LOAD_ERROR);
            customEventBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.setMraidListener((MraidController.MraidListener) null);
            this.mMraidController.destroy();
        }
    }

    /* access modifiers changed from: protected */
    public void trackMpxAndThirdPartyImpressions() {
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
            if (this.mExternalViewabilitySessionManager != null) {
                Activity activity = (Activity) this.mMraidController.getWeakActivity().get();
                if (activity != null) {
                    this.mExternalViewabilitySessionManager.startDeferredDisplaySession(activity);
                    return;
                }
                MoPubLog.log(MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Lost the activity for deferred Viewability tracking. Dropping session.");
            }
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        return map.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
    }

    public void setDebugListener(MraidWebViewDebugListener mraidWebViewDebugListener) {
        this.mDebugListener = mraidWebViewDebugListener;
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.setDebugListener(mraidWebViewDebugListener);
        }
    }
}
