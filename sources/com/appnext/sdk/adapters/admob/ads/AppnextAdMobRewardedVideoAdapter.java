package com.appnext.sdk.adapters.admob.ads;

import android.content.Context;
import android.os.Bundle;
import com.appnext.ads.fullscreen.RewardedConfig;
import com.appnext.ads.fullscreen.RewardedServerSidePostback;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.core.AppnextAdCreativeType;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.appnext.core.callbacks.OnVideoEnded;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import java.io.Serializable;

public class AppnextAdMobRewardedVideoAdapter implements MediationRewardedVideoAdAdapter {
    /* access modifiers changed from: private */
    public MediationRewardedVideoAdListener _mediationRewardedVideoAdListener;
    AppnextRewardedVideoAd ad;

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        String str2;
        Serializable serializable;
        this._mediationRewardedVideoAdListener = mediationRewardedVideoAdListener;
        Serializable serializable2 = null;
        if (bundle != null) {
            try {
                serializable2 = bundle.getSerializable("AppnextConfiguration");
                serializable = bundle.getSerializable("AppnextRewardPostback");
                str2 = bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD);
            } catch (Throwable th) {
                new StringBuilder("AppnextAdMobRewardedVideoAdapter createAd: ").append(th.getMessage());
                MediationRewardedVideoAdListener mediationRewardedVideoAdListener2 = this._mediationRewardedVideoAdListener;
                if (mediationRewardedVideoAdListener2 != null) {
                    mediationRewardedVideoAdListener2.onInitializationFailed(this, 0);
                    return;
                }
                return;
            }
        } else {
            str2 = str;
            serializable = null;
        }
        if (bundle2 != null) {
            if (bundle2.containsKey("AppnextConfiguration")) {
                serializable2 = bundle2.getSerializable("AppnextConfiguration");
            }
            if (bundle2.containsKey("AppnextRewardPostback")) {
                serializable = bundle2.getSerializable("AppnextRewardPostback");
            }
            if (bundle2.containsKey(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD)) {
                str2 = bundle2.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD);
            }
        }
        if (serializable2 == null || !(serializable2 instanceof RewardedConfig)) {
            this.ad = new AppnextRewardedVideoAd(context, str2);
        } else {
            this.ad = new AppnextRewardedVideoAd(context, str2, (RewardedConfig) serializable2);
        }
        if (serializable != null && (serializable instanceof RewardedServerSidePostback)) {
            RewardedServerSidePostback rewardedServerSidePostback = (RewardedServerSidePostback) serializable;
            this.ad.setRewardedServerSidePostback(rewardedServerSidePostback.getRewardsTransactionId(), rewardedServerSidePostback.getRewardsUserId(), rewardedServerSidePostback.getRewardsRewardTypeCurrency(), rewardedServerSidePostback.getRewardsAmountRewarded(), rewardedServerSidePostback.getRewardsCustomParameter());
        }
        this.ad.setOnAdErrorCallback(new OnAdError() {
            public void adError(String str) {
                if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                    AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onAdFailedToLoad(AppnextAdMobRewardedVideoAdapter.this, 0);
                }
            }
        });
        this.ad.setOnAdClickedCallback(new OnAdClicked() {
            public void adClicked() {
                if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                    AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onAdClicked(AppnextAdMobRewardedVideoAdapter.this);
                }
            }
        });
        this.ad.setOnAdClosedCallback(new OnAdClosed() {
            public void onAdClosed() {
                if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                    AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onAdClosed(AppnextAdMobRewardedVideoAdapter.this);
                }
            }
        });
        this.ad.setOnAdLoadedCallback(new OnAdLoaded() {
            public void adLoaded(String str, AppnextAdCreativeType appnextAdCreativeType) {
                if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                    AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onAdLoaded(AppnextAdMobRewardedVideoAdapter.this);
                }
            }
        });
        this.ad.setOnAdOpenedCallback(new OnAdOpened() {
            public void adOpened() {
                if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                    AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onAdOpened(AppnextAdMobRewardedVideoAdapter.this);
                }
            }
        });
        this.ad.setOnVideoEndedCallback(new OnVideoEnded() {
            public void videoEnded() {
                if (AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener != null) {
                    AppnextAdMobRewardedVideoAdapter.this._mediationRewardedVideoAdListener.onRewarded(AppnextAdMobRewardedVideoAdapter.this, new RewardItem() {
                        public int getAmount() {
                            return 1;
                        }

                        public String getType() {
                            return "";
                        }
                    });
                }
            }
        });
        if (this._mediationRewardedVideoAdListener != null) {
            this._mediationRewardedVideoAdListener.onInitializationSucceeded(this);
        }
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        AppnextRewardedVideoAd appnextRewardedVideoAd = this.ad;
        if (appnextRewardedVideoAd != null) {
            appnextRewardedVideoAd.loadAd();
        }
        Serializable serializable = null;
        if (bundle2 != null) {
            serializable = bundle2.getSerializable("AppnextRewardPostback");
        } else if (bundle != null) {
            serializable = bundle.getSerializable("AppnextRewardPostback");
        }
        if (serializable != null && (serializable instanceof RewardedServerSidePostback)) {
            RewardedServerSidePostback rewardedServerSidePostback = (RewardedServerSidePostback) serializable;
            this.ad.setRewardedServerSidePostback(rewardedServerSidePostback.getRewardsTransactionId(), rewardedServerSidePostback.getRewardsUserId(), rewardedServerSidePostback.getRewardsRewardTypeCurrency(), rewardedServerSidePostback.getRewardsAmountRewarded(), rewardedServerSidePostback.getRewardsCustomParameter());
        }
    }

    public void showVideo() {
        AppnextRewardedVideoAd appnextRewardedVideoAd = this.ad;
        if (appnextRewardedVideoAd != null) {
            appnextRewardedVideoAd.showAd();
        }
    }

    public boolean isInitialized() {
        return this.ad != null;
    }

    private class AppnextRewardedVideoAd extends RewardedVideo {
        protected static final String TID = "321";

        public String getTID() {
            return TID;
        }

        public AppnextRewardedVideoAd(Context context, String str) {
            super(context, str);
        }

        public AppnextRewardedVideoAd(Context context, String str, RewardedConfig rewardedConfig) {
            super(context, str, rewardedConfig);
        }
    }
}
