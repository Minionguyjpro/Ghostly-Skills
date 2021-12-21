package com.startapp.android.mediation.admob;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.customevent.CustomEventBanner;
import com.google.ads.mediation.customevent.CustomEventBannerListener;
import com.google.ads.mediation.customevent.CustomEventInterstitial;
import com.google.ads.mediation.customevent.CustomEventInterstitialListener;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerBase;
import com.startapp.android.publish.ads.banner.BannerListener;
import com.startapp.android.publish.ads.banner.Mrec;
import com.startapp.android.publish.ads.banner.banner3d.Banner3D;
import com.startapp.android.publish.ads.banner.bannerstandard.BannerStandard;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.SDKAdPreferences;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import org.json.JSONException;
import org.json.JSONObject;

public class StartAppCustomEvent implements CustomEventBanner, CustomEventInterstitial {
    private StartAppAd ad;
    /* access modifiers changed from: private */
    public CustomEventInterstitialListener interstitialListener;

    public void destroy() {
    }

    public void requestInterstitialAd(final CustomEventInterstitialListener customEventInterstitialListener, Activity activity, String str, String str2, MediationAdRequest mediationAdRequest, Object obj) {
        this.interstitialListener = customEventInterstitialListener;
        StartAppAd startAppAd = new StartAppAd(activity);
        this.ad = startAppAd;
        startAppAd.loadAd(getAdMode(str2, obj), extractAdPrefs(activity, mediationAdRequest, obj), new AdEventListener() {
            public void onReceiveAd(Ad ad) {
                customEventInterstitialListener.onReceivedAd();
            }

            public void onFailedToReceiveAd(Ad ad) {
                customEventInterstitialListener.onFailedToReceiveAd();
            }
        });
    }

    private StartAppAd.AdMode getAdMode(String str, Object obj) {
        StartAppAd.AdMode adMode;
        if (str != null) {
            if (str.equalsIgnoreCase("AdMode.FULLPAGE")) {
                return StartAppAd.AdMode.FULLPAGE;
            }
            if (str.equalsIgnoreCase("AdMode.OVERLAY")) {
                return StartAppAd.AdMode.OVERLAY;
            }
            if (str.equalsIgnoreCase("AdMode.OFFERWALL")) {
                return StartAppAd.AdMode.OFFERWALL;
            }
            if (str.equalsIgnoreCase("AdMode.AUTOMATIC")) {
                return StartAppAd.AdMode.AUTOMATIC;
            }
        }
        if (!(obj instanceof StartAppInterstitialExtras) || (adMode = ((StartAppInterstitialExtras) obj).getAdMode()) == null) {
            return StartAppAd.AdMode.AUTOMATIC;
        }
        return adMode;
    }

    public void showInterstitial() {
        this.ad.showAd((AdDisplayListener) new AdDisplayListener() {
            public void adNotDisplayed(Ad ad) {
            }

            public void adHidden(Ad ad) {
                StartAppCustomEvent.this.interstitialListener.onDismissScreen();
            }

            public void adDisplayed(Ad ad) {
                StartAppCustomEvent.this.interstitialListener.onPresentScreen();
            }

            public void adClicked(Ad ad) {
                StartAppCustomEvent.this.interstitialListener.onLeaveApplication();
            }
        });
    }

    public void requestBannerAd(final CustomEventBannerListener customEventBannerListener, Activity activity, String str, String str2, AdSize adSize, MediationAdRequest mediationAdRequest, Object obj) {
        AdPreferences extractAdPrefs = extractAdPrefs(activity, mediationAdRequest, obj);
        final FrameLayout frameLayout = new FrameLayout(activity);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(getBanner(str2, obj, activity, extractAdPrefs, new BannerListener() {
            public void onReceiveAd(View view) {
                customEventBannerListener.onReceivedAd(frameLayout);
            }

            public void onFailedToReceiveAd(View view) {
                customEventBannerListener.onFailedToReceiveAd();
            }

            public void onClick(View view) {
                customEventBannerListener.onClick();
                customEventBannerListener.onPresentScreen();
                customEventBannerListener.onLeaveApplication();
            }
        }, adSize), new FrameLayout.LayoutParams(adSize.getWidthInPixels(activity), adSize.getHeightInPixels(activity), 17));
    }

    public View getBanner(String str, Object obj, Activity activity, AdPreferences adPreferences, BannerListener bannerListener, AdSize adSize) {
        BannerBase mrec = (adSize.getWidth() == AdSize.IAB_MRECT.getWidth() && adSize.getHeight() == AdSize.IAB_MRECT.getHeight()) ? new Mrec(activity, adPreferences, bannerListener) : null;
        if (mrec == null && str != null) {
            if (str.equalsIgnoreCase("BannerMode.STANDARD")) {
                mrec = new BannerStandard(activity, adPreferences, bannerListener);
            } else if (str.equalsIgnoreCase("BannerMode.THREED")) {
                mrec = new Banner3D(activity, adPreferences, bannerListener);
            } else if (str.equalsIgnoreCase("BannerMode.AUTO")) {
                mrec = new Banner(activity, adPreferences, bannerListener);
            }
        }
        if (mrec == null && (obj instanceof StartAppBannerExtras)) {
            int i = AnonymousClass4.$SwitchMap$com$startapp$android$mediation$admob$StartAppBannerExtras$BannerMode[((StartAppBannerExtras) obj).getBannerMode().ordinal()];
            if (i == 1) {
                mrec = new Banner(activity, adPreferences, bannerListener);
            } else if (i == 2) {
                mrec = new BannerStandard(activity, adPreferences, bannerListener);
            } else if (i == 3) {
                mrec = new Banner3D(activity, adPreferences, bannerListener);
            }
        }
        if (mrec == null) {
            mrec = new Banner(activity, adPreferences, bannerListener);
        }
        String adTag = getAdTag(str);
        if (!TextUtils.isEmpty(adTag)) {
            mrec.setAdTag(adTag);
        }
        return mrec;
    }

    /* renamed from: com.startapp.android.mediation.admob.StartAppCustomEvent$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$startapp$android$mediation$admob$StartAppBannerExtras$BannerMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.startapp.android.mediation.admob.StartAppBannerExtras$BannerMode[] r0 = com.startapp.android.mediation.admob.StartAppBannerExtras.BannerMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$startapp$android$mediation$admob$StartAppBannerExtras$BannerMode = r0
                com.startapp.android.mediation.admob.StartAppBannerExtras$BannerMode r1 = com.startapp.android.mediation.admob.StartAppBannerExtras.BannerMode.AUTO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$startapp$android$mediation$admob$StartAppBannerExtras$BannerMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.mediation.admob.StartAppBannerExtras$BannerMode r1 = com.startapp.android.mediation.admob.StartAppBannerExtras.BannerMode.STANDARD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$startapp$android$mediation$admob$StartAppBannerExtras$BannerMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.mediation.admob.StartAppBannerExtras$BannerMode r1 = com.startapp.android.mediation.admob.StartAppBannerExtras.BannerMode.THREED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.mediation.admob.StartAppCustomEvent.AnonymousClass4.<clinit>():void");
        }
    }

    private String getAdTag(String str) {
        if (str == null) {
            return null;
        }
        try {
            if (str.toLowerCase().contains("adtag")) {
                return new JSONObject(str).getString("adTag");
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }

    private AdPreferences extractAdPrefs(Activity activity, MediationAdRequest mediationAdRequest, Object obj) {
        AdPreferences adPreferences = new AdPreferences();
        setGender(adPreferences, mediationAdRequest);
        setAge(adPreferences, mediationAdRequest);
        setKeywords(adPreferences, mediationAdRequest);
        setLocation(adPreferences, mediationAdRequest);
        return adPreferences;
    }

    private void setKeywords(AdPreferences adPreferences, MediationAdRequest mediationAdRequest) {
        if (mediationAdRequest.getKeywords() != null) {
            StringBuilder sb = new StringBuilder();
            for (String str : mediationAdRequest.getKeywords()) {
                sb.append(str + ",");
            }
            adPreferences.setKeywords(sb.substring(0, sb.length() - 1));
        }
    }

    private void setLocation(AdPreferences adPreferences, MediationAdRequest mediationAdRequest) {
        if (mediationAdRequest.getLocation() != null) {
            adPreferences.setLongitude(mediationAdRequest.getLocation().getLongitude());
            adPreferences.setLatitude(mediationAdRequest.getLocation().getLatitude());
        }
    }

    private void setGender(AdPreferences adPreferences, MediationAdRequest mediationAdRequest) {
        if (mediationAdRequest.getGender() == null) {
            return;
        }
        if (mediationAdRequest.getGender() == AdRequest.Gender.MALE) {
            adPreferences.setGender(SDKAdPreferences.Gender.MALE);
        } else if (mediationAdRequest.getGender() == AdRequest.Gender.FEMALE) {
            adPreferences.setGender(SDKAdPreferences.Gender.FEMALE);
        }
    }

    private void setAge(AdPreferences adPreferences, MediationAdRequest mediationAdRequest) {
        if (mediationAdRequest.getAgeInYears() != null) {
            adPreferences.setAge(mediationAdRequest.getAgeInYears());
        }
    }
}
