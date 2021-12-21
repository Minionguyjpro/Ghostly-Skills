package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.WebViewCacheService;
import com.mopub.mraid.MraidBridge;
import com.mopub.mraid.MraidController;
import com.mopub.mraid.MraidWebViewDebugListener;
import com.mopub.mraid.PlacementType;
import com.mopub.mraid.RewardedMraidController;

public class RewardedMraidActivity extends MraidActivity {
    private MraidWebViewDebugListener mDebugListener;
    /* access modifiers changed from: private */
    public RewardedMraidController mRewardedMraidController;

    public static void preRenderHtml(Interstitial interstitial, Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Long l, AdReport adReport, int i) {
        Preconditions.checkNotNull(interstitial);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(customEventInterstitialListener);
        Preconditions.checkNotNull(l);
        Context context2 = context;
        Interstitial interstitial2 = interstitial;
        CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener2 = customEventInterstitialListener;
        preRenderHtml(interstitial2, customEventInterstitialListener2, getResponseString(adReport), new MraidBridge.MraidWebView(context), l, new RewardedMraidController(context, adReport, PlacementType.INTERSTITIAL, i, l.longValue()));
    }

    public static void start(Context context, AdReport adReport, long j, int i, boolean z) {
        try {
            Intents.startActivity(context, createIntent(context, adReport, j, i, z));
        } catch (IntentNotResolvableException unused) {
            Log.d("RewardedMraidActivity", "RewardedMraidActivity.class not found. Did you declare RewardedMraidActivity in your manifest?");
        }
    }

    protected static Intent createIntent(Context context, AdReport adReport, long j, int i, boolean z) {
        Intent intent = new Intent(context, RewardedMraidActivity.class);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.putExtra(DataKeys.REWARDED_AD_DURATION_KEY, i);
        intent.putExtra(DataKeys.SHOULD_REWARD_ON_CLICK_KEY, z);
        return intent;
    }

    public View getAdView() {
        Intent intent = getIntent();
        String responseString = getResponseString();
        boolean z = true;
        if (TextUtils.isEmpty(responseString)) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "RewardedMraidActivity received a null HTML body. Finishing the activity.");
            finish();
            return new View(this);
        } else if (getBroadcastIdentifier() == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "RewardedMraidActivity received a null broadcast id. Finishing the activity.");
            finish();
            return new View(this);
        } else {
            int intExtra = intent.getIntExtra(DataKeys.REWARDED_AD_DURATION_KEY, 30);
            final boolean booleanExtra = intent.getBooleanExtra(DataKeys.SHOULD_REWARD_ON_CLICK_KEY, false);
            Long broadcastIdentifier = getBroadcastIdentifier();
            WebViewCacheService.Config config = null;
            if (broadcastIdentifier != null) {
                config = WebViewCacheService.popWebViewConfig(broadcastIdentifier);
            }
            if (config == null || !(config.getController() instanceof RewardedMraidController)) {
                this.mRewardedMraidController = new RewardedMraidController(this, this.mAdReport, PlacementType.INTERSTITIAL, intExtra, getBroadcastIdentifier().longValue());
                z = false;
            } else {
                this.mRewardedMraidController = (RewardedMraidController) config.getController();
            }
            this.mRewardedMraidController.setDebugListener(this.mDebugListener);
            this.mRewardedMraidController.setMraidListener(new MraidController.MraidListener() {
                public void onExpand() {
                }

                public void onResize(boolean z) {
                }

                public void onLoaded(View view) {
                    RewardedMraidActivity.this.mRewardedMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
                }

                public void onFailedToLoad() {
                    MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "RewardedMraidActivity failed to load. Finishing the activity");
                    RewardedMraidActivity rewardedMraidActivity = RewardedMraidActivity.this;
                    EventForwardingBroadcastReceiver.broadcastAction(rewardedMraidActivity, rewardedMraidActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_FAIL);
                    RewardedMraidActivity.this.finish();
                }

                public void onRenderProcessGone(MoPubErrorCode moPubErrorCode) {
                    MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                    MoPubLog.log(sdkLogEvent, "Finishing the activity due to a problem: " + moPubErrorCode);
                    RewardedMraidActivity.this.finish();
                }

                public void onClose() {
                    RewardedMraidActivity.this.mRewardedMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getJavascript());
                    RewardedMraidActivity.this.finish();
                }

                public void onOpen() {
                    if (booleanExtra) {
                        RewardedMraidActivity.this.mRewardedMraidController.showPlayableCloseButton();
                    }
                    RewardedMraidActivity rewardedMraidActivity = RewardedMraidActivity.this;
                    EventForwardingBroadcastReceiver.broadcastAction(rewardedMraidActivity, rewardedMraidActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_CLICK);
                }
            });
            if (z) {
                this.mExternalViewabilitySessionManager = config.getViewabilityManager();
            } else {
                this.mRewardedMraidController.fillContent(responseString, new MraidController.MraidWebViewCacheListener() {
                    public void onReady(MraidBridge.MraidWebView mraidWebView, ExternalViewabilitySessionManager externalViewabilitySessionManager) {
                        if (externalViewabilitySessionManager != null) {
                            RewardedMraidActivity.this.mExternalViewabilitySessionManager = externalViewabilitySessionManager;
                            return;
                        }
                        RewardedMraidActivity rewardedMraidActivity = RewardedMraidActivity.this;
                        rewardedMraidActivity.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(rewardedMraidActivity);
                        RewardedMraidActivity.this.mExternalViewabilitySessionManager.createDisplaySession(RewardedMraidActivity.this, mraidWebView);
                    }
                });
            }
            this.mRewardedMraidController.onShow(this);
            return this.mRewardedMraidController.getAdContainer();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController != null) {
            rewardedMraidController.create(this, getCloseableLayout());
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController != null) {
            rewardedMraidController.pause();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController != null) {
            rewardedMraidController.resume();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController != null) {
            rewardedMraidController.destroy();
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController == null || rewardedMraidController.backButtonEnabled()) {
            super.onBackPressed();
        }
    }

    public void setDebugListener(MraidWebViewDebugListener mraidWebViewDebugListener) {
        this.mDebugListener = mraidWebViewDebugListener;
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController != null) {
            rewardedMraidController.setDebugListener(mraidWebViewDebugListener);
        }
    }

    @Deprecated
    public RewardedMraidController getRewardedMraidController() {
        return this.mRewardedMraidController;
    }
}
