package com.mopub.mobileads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import com.mopub.common.AdReport;
import com.mopub.common.Constants;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.WebViewCacheService;
import com.mopub.mraid.MraidBridge;
import com.mopub.mraid.MraidController;
import com.mopub.mraid.MraidWebViewClient;
import com.mopub.mraid.MraidWebViewDebugListener;
import com.mopub.mraid.PlacementType;
import com.mopub.network.Networking;
import java.io.Serializable;

public class MraidActivity extends BaseInterstitialActivity {
    private MraidWebViewDebugListener mDebugListener;
    protected ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    /* access modifiers changed from: private */
    public MraidController mMraidController;

    public static void preRenderHtml(Interstitial interstitial, Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Long l, AdReport adReport) {
        Preconditions.checkNotNull(interstitial);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(customEventInterstitialListener);
        Preconditions.checkNotNull(l);
        preRenderHtml(interstitial, customEventInterstitialListener, getResponseString(adReport), new MraidBridge.MraidWebView(context), l, new MraidController(context, adReport, PlacementType.INTERSTITIAL));
    }

    static void preRenderHtml(Interstitial interstitial, final CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, String str, final BaseWebView baseWebView, Long l, final MraidController mraidController) {
        MoPubLog.log(MoPubLog.AdLogEvent.LOAD_ATTEMPTED, new Object[0]);
        Preconditions.checkNotNull(interstitial);
        Preconditions.checkNotNull(customEventInterstitialListener);
        Preconditions.checkNotNull(baseWebView);
        Preconditions.checkNotNull(l);
        Preconditions.checkNotNull(mraidController);
        baseWebView.enableJavascriptCaching();
        Context context = baseWebView.getContext();
        baseWebView.setWebViewClient(new MraidWebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if ("mopub://failLoad".equals(str)) {
                    MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, Integer.valueOf(MoPubErrorCode.VIDEO_CACHE_ERROR.getIntCode()), MoPubErrorCode.VIDEO_CACHE_ERROR);
                    customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
                }
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                MoPubLog.log(MoPubLog.AdLogEvent.LOAD_SUCCESS, new Object[0]);
                customEventInterstitialListener.onInterstitialLoaded();
                mraidController.onPreloadFinished(baseWebView);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, Integer.valueOf(MoPubErrorCode.VIDEO_CACHE_ERROR.getIntCode()), MoPubErrorCode.VIDEO_CACHE_ERROR);
                customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
            }
        });
        ExternalViewabilitySessionManager externalViewabilitySessionManager = new ExternalViewabilitySessionManager(context);
        externalViewabilitySessionManager.createDisplaySession(context, baseWebView);
        baseWebView.loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + "/", str, "text/html", "UTF-8", (String) null);
        WebViewCacheService.storeWebViewConfig(l, interstitial, baseWebView, externalViewabilitySessionManager, mraidController);
    }

    public static void start(Context context, AdReport adReport, long j, CreativeOrientation creativeOrientation) {
        MoPubLog.log(MoPubLog.AdLogEvent.SHOW_ATTEMPTED, new Object[0]);
        try {
            context.startActivity(createIntent(context, adReport, j, creativeOrientation));
        } catch (ActivityNotFoundException unused) {
            MoPubLog.log(MoPubLog.AdLogEvent.SHOW_FAILED, Integer.valueOf(MoPubErrorCode.INTERNAL_ERROR.getIntCode()), MoPubErrorCode.INTERNAL_ERROR);
            Log.d("MraidInterstitial", "MraidActivity.class not found. Did you declare MraidActivity in your manifest?");
        }
    }

    protected static Intent createIntent(Context context, AdReport adReport, long j, CreativeOrientation creativeOrientation) {
        Intent intent = new Intent(context, MraidActivity.class);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.putExtra(DataKeys.CREATIVE_ORIENTATION_KEY, creativeOrientation);
        intent.addFlags(268435456);
        return intent;
    }

    public View getAdView() {
        String responseString = getResponseString();
        if (TextUtils.isEmpty(responseString)) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "MraidActivity received a null HTML body. Finishing the activity.");
            finish();
            return new View(this);
        }
        Long broadcastIdentifier = getBroadcastIdentifier();
        WebViewCacheService.Config config = null;
        if (broadcastIdentifier != null) {
            config = WebViewCacheService.popWebViewConfig(broadcastIdentifier);
        }
        if (config == null || config.getController() == null) {
            this.mMraidController = new MraidController(this, this.mAdReport, PlacementType.INTERSTITIAL);
        } else {
            this.mMraidController = config.getController();
        }
        this.mMraidController.setDebugListener(this.mDebugListener);
        this.mMraidController.setMraidListener(new MraidController.MraidListener() {
            public void onExpand() {
            }

            public void onResize(boolean z) {
            }

            public void onLoaded(View view) {
                MraidActivity.this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
            }

            public void onFailedToLoad() {
                MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "MraidActivity failed to load. Finishing the activity");
                if (MraidActivity.this.getBroadcastIdentifier() != null) {
                    MraidActivity mraidActivity = MraidActivity.this;
                    EventForwardingBroadcastReceiver.broadcastAction(mraidActivity, mraidActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_FAIL);
                }
                MraidActivity.this.finish();
            }

            public void onRenderProcessGone(MoPubErrorCode moPubErrorCode) {
                MoPubLog.AdLogEvent adLogEvent = MoPubLog.AdLogEvent.CUSTOM;
                MoPubLog.log(adLogEvent, "Finishing the activity due to a problem: " + moPubErrorCode);
                MraidActivity.this.finish();
            }

            public void onClose() {
                MoPubLog.log(MoPubLog.AdLogEvent.WILL_DISAPPEAR, new Object[0]);
                MraidActivity.this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getJavascript());
                MraidActivity.this.finish();
            }

            public void onOpen() {
                MoPubLog.log(MoPubLog.AdLogEvent.DID_APPEAR, new Object[0]);
                if (MraidActivity.this.getBroadcastIdentifier() != null) {
                    MraidActivity mraidActivity = MraidActivity.this;
                    EventForwardingBroadcastReceiver.broadcastAction(mraidActivity, mraidActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_CLICK);
                }
            }
        });
        this.mMraidController.setUseCustomCloseListener(new MraidController.UseCustomCloseListener() {
            public void useCustomCloseChanged(boolean z) {
                if (z) {
                    MraidActivity.this.hideInterstitialCloseButton();
                } else {
                    MraidActivity.this.showInterstitialCloseButton();
                }
            }
        });
        if (config != null) {
            this.mExternalViewabilitySessionManager = config.getViewabilityManager();
        } else {
            this.mMraidController.fillContent(responseString, new MraidController.MraidWebViewCacheListener() {
                public void onReady(MraidBridge.MraidWebView mraidWebView, ExternalViewabilitySessionManager externalViewabilitySessionManager) {
                    if (externalViewabilitySessionManager != null) {
                        MraidActivity.this.mExternalViewabilitySessionManager = externalViewabilitySessionManager;
                        return;
                    }
                    MraidActivity mraidActivity = MraidActivity.this;
                    mraidActivity.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(mraidActivity);
                    MraidActivity.this.mExternalViewabilitySessionManager.createDisplaySession(MraidActivity.this, mraidWebView);
                }
            });
        }
        return this.mMraidController.getAdContainer();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MoPubLog.log(MoPubLog.AdLogEvent.SHOW_SUCCESS, new Object[0]);
        Serializable serializableExtra = getIntent().getSerializableExtra(DataKeys.CREATIVE_ORIENTATION_KEY);
        CreativeOrientation creativeOrientation = CreativeOrientation.DEVICE;
        if (serializableExtra instanceof CreativeOrientation) {
            creativeOrientation = (CreativeOrientation) serializableExtra;
        }
        DeviceUtils.lockOrientation(this, creativeOrientation);
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.startDeferredDisplaySession(this);
        }
        if (getBroadcastIdentifier() != null) {
            EventForwardingBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_SHOW);
        }
        getWindow().setFlags(16777216, 16777216);
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.onShow(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.pause(isFinishing());
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.resume();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.destroy();
        }
        if (getBroadcastIdentifier() != null) {
            EventForwardingBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_DISMISS);
        }
        super.onDestroy();
    }

    public void setDebugListener(MraidWebViewDebugListener mraidWebViewDebugListener) {
        this.mDebugListener = mraidWebViewDebugListener;
        MraidController mraidController = this.mMraidController;
        if (mraidController != null) {
            mraidController.setDebugListener(mraidWebViewDebugListener);
        }
    }
}
