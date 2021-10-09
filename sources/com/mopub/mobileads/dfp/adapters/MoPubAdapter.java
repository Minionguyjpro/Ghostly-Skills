package com.mopub.mobileads.dfp.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.NativeAd;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.ViewBinder;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;

public class MoPubAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    public static final double DEFAULT_MOPUB_IMAGE_SCALE = 1.0d;
    private static final int DEFAULT_MOPUB_PRIVACY_ICON_SIZE_DP = 20;
    private static final int MAXIMUM_MOPUB_PRIVACY_ICON_SIZE_DP = 30;
    private static final int MINIMUM_MOPUB_PRIVACY_ICON_SIZE_DP = 10;
    private static final String MOPUB_AD_UNIT_KEY = "adUnitId";
    private static final String MOPUB_NATIVE_CEVENT_VERSION = "gmext";
    public static final String TAG = MoPubAdapter.class.getSimpleName();
    /* access modifiers changed from: private */
    public AdSize mAdSize;
    private MoPubInterstitial mMoPubInterstitial;
    /* access modifiers changed from: private */
    public NativeAd.MoPubNativeEventListener mMoPubNativeEventListener;
    private MoPubView mMoPubView;
    /* access modifiers changed from: private */
    public int mPrivacyIconSize;
    /* access modifiers changed from: private */
    public int privacyIconPlacement;

    public void onPause() {
    }

    public void onResume() {
    }

    public void onDestroy() {
        MoPubInterstitial moPubInterstitial = this.mMoPubInterstitial;
        if (moPubInterstitial != null) {
            moPubInterstitial.destroy();
            this.mMoPubInterstitial = null;
        }
        MoPubView moPubView = this.mMoPubView;
        if (moPubView != null) {
            moPubView.destroy();
            this.mMoPubView = null;
        }
    }

    public void requestNativeAd(final Context context, final MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        String string = bundle.getString(MOPUB_AD_UNIT_KEY);
        initializeMoPub(context, string);
        NativeAdOptions nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
        if (nativeAdOptions != null) {
            this.privacyIconPlacement = nativeAdOptions.getAdChoicesPlacement();
        } else {
            this.privacyIconPlacement = 1;
        }
        if (nativeMediationAdRequest.isAppInstallAdRequested() || !nativeMediationAdRequest.isContentAdRequested()) {
            if (bundle2 != null) {
                int i = bundle2.getInt("privacy_icon_size_dp");
                if (i < 10) {
                    this.mPrivacyIconSize = 10;
                } else if (i > 30) {
                    this.mPrivacyIconSize = 30;
                } else {
                    this.mPrivacyIconSize = i;
                }
            } else {
                this.mPrivacyIconSize = 20;
            }
            AnonymousClass1 r10 = new MoPubNative.MoPubNativeNetworkListener() {
                /* JADX WARNING: Can't wrap try/catch for region: R(9:3|4|5|6|7|8|9|10|15) */
                /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0036 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onNativeLoad(com.mopub.nativeads.NativeAd r6) {
                    /*
                        r5 = this;
                        com.mopub.mobileads.dfp.adapters.MoPubAdapter r0 = com.mopub.mobileads.dfp.adapters.MoPubAdapter.this
                        com.mopub.nativeads.NativeAd$MoPubNativeEventListener r0 = r0.mMoPubNativeEventListener
                        r6.setMoPubNativeEventListener(r0)
                        com.mopub.nativeads.BaseNativeAd r6 = r6.getBaseNativeAd()
                        boolean r0 = r6 instanceof com.mopub.nativeads.StaticNativeAd
                        if (r0 == 0) goto L_0x0065
                        com.mopub.nativeads.StaticNativeAd r6 = (com.mopub.nativeads.StaticNativeAd) r6
                        r0 = 0
                        java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Exception -> 0x0057 }
                        r1.<init>()     // Catch:{ Exception -> 0x0057 }
                        java.lang.String r2 = "icon_key"
                        java.net.URL r3 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0036 }
                        java.lang.String r4 = r6.getIconImageUrl()     // Catch:{ MalformedURLException -> 0x0036 }
                        r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x0036 }
                        r1.put(r2, r3)     // Catch:{ MalformedURLException -> 0x0036 }
                        java.lang.String r2 = "image_key"
                        java.net.URL r3 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0036 }
                        java.lang.String r4 = r6.getMainImageUrl()     // Catch:{ MalformedURLException -> 0x0036 }
                        r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x0036 }
                        r1.put(r2, r3)     // Catch:{ MalformedURLException -> 0x0036 }
                        goto L_0x0044
                    L_0x0036:
                        java.lang.String r2 = com.mopub.mobileads.dfp.adapters.MoPubAdapter.TAG     // Catch:{ Exception -> 0x0057 }
                        java.lang.String r3 = "Invalid ad response received from MoPub. Image URLs are invalid"
                        android.util.Log.d(r2, r3)     // Catch:{ Exception -> 0x0057 }
                        com.google.android.gms.ads.mediation.MediationNativeListener r2 = r7     // Catch:{ Exception -> 0x0057 }
                        com.mopub.mobileads.dfp.adapters.MoPubAdapter r3 = com.mopub.mobileads.dfp.adapters.MoPubAdapter.this     // Catch:{ Exception -> 0x0057 }
                        r2.onAdFailedToLoad(r3, r0)     // Catch:{ Exception -> 0x0057 }
                    L_0x0044:
                        com.mopub.mobileads.dfp.adapters.DownloadDrawablesAsync r2 = new com.mopub.mobileads.dfp.adapters.DownloadDrawablesAsync     // Catch:{ Exception -> 0x0057 }
                        com.mopub.mobileads.dfp.adapters.MoPubAdapter$1$1 r3 = new com.mopub.mobileads.dfp.adapters.MoPubAdapter$1$1     // Catch:{ Exception -> 0x0057 }
                        r3.<init>(r6)     // Catch:{ Exception -> 0x0057 }
                        r2.<init>(r3)     // Catch:{ Exception -> 0x0057 }
                        r6 = 1
                        java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x0057 }
                        r6[r0] = r1     // Catch:{ Exception -> 0x0057 }
                        r2.execute(r6)     // Catch:{ Exception -> 0x0057 }
                        goto L_0x0065
                    L_0x0057:
                        java.lang.String r6 = com.mopub.mobileads.dfp.adapters.MoPubAdapter.TAG
                        java.lang.String r1 = "Exception constructing the native ad"
                        android.util.Log.d(r6, r1)
                        com.google.android.gms.ads.mediation.MediationNativeListener r6 = r7
                        com.mopub.mobileads.dfp.adapters.MoPubAdapter r1 = com.mopub.mobileads.dfp.adapters.MoPubAdapter.this
                        r6.onAdFailedToLoad(r1, r0)
                    L_0x0065:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.dfp.adapters.MoPubAdapter.AnonymousClass1.onNativeLoad(com.mopub.nativeads.NativeAd):void");
                }

                public void onNativeFail(NativeErrorCode nativeErrorCode) {
                    int i = AnonymousClass4.$SwitchMap$com$mopub$nativeads$NativeErrorCode[nativeErrorCode.ordinal()];
                    if (i == 1) {
                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 3);
                    } else if (i == 2) {
                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 1);
                    } else if (i == 3) {
                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 1);
                    } else if (i != 4) {
                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                    } else {
                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                    }
                }
            };
            if (string == null) {
                Log.d(TAG, "Ad unit id is invalid. So failing the request.");
                mediationNativeListener.onAdFailedToLoad(this, 1);
                return;
            }
            MoPubNative moPubNative = new MoPubNative(context, string, r10);
            moPubNative.registerAdRenderer(new MoPubStaticNativeAdRenderer(new ViewBinder.Builder(0).build()));
            moPubNative.makeRequest(new RequestParameters.Builder().keywords(getKeywords(nativeMediationAdRequest, false)).userDataKeywords(getKeywords(nativeMediationAdRequest, true)).location(nativeMediationAdRequest.getLocation()).desiredAssets(EnumSet.of(RequestParameters.NativeAdAsset.TITLE, RequestParameters.NativeAdAsset.TEXT, RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT, RequestParameters.NativeAdAsset.MAIN_IMAGE, RequestParameters.NativeAdAsset.ICON_IMAGE)).build());
            this.mMoPubNativeEventListener = new NativeAd.MoPubNativeEventListener() {
                public void onImpression(View view) {
                    mediationNativeListener.onAdImpression(MoPubAdapter.this);
                    Log.d(MoPubAdapter.TAG, "onImpression");
                }

                public void onClick(View view) {
                    mediationNativeListener.onAdClicked(MoPubAdapter.this);
                    mediationNativeListener.onAdOpened(MoPubAdapter.this);
                    mediationNativeListener.onAdLeftApplication(MoPubAdapter.this);
                    Log.d(MoPubAdapter.TAG, "onClick");
                }
            };
            return;
        }
        Log.d(TAG, "Currently, MoPub only serves native app install ads. Apps requesting content ads alone will not receive ads from this adapter.");
        mediationNativeListener.onAdFailedToLoad(this, 1);
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        String string = bundle.getString(MOPUB_AD_UNIT_KEY);
        initializeMoPub(context, string);
        this.mAdSize = adSize;
        MoPubView moPubView = new MoPubView(context);
        this.mMoPubView = moPubView;
        moPubView.setBannerAdListener(new MBannerListener(mediationBannerListener));
        this.mMoPubView.setAdUnitId(string);
        if (mediationAdRequest.isTesting()) {
            this.mMoPubView.setTesting(true);
        }
        if (mediationAdRequest.getLocation() != null) {
            this.mMoPubView.setLocation(mediationAdRequest.getLocation());
        }
        this.mMoPubView.setKeywords(getKeywords(mediationAdRequest, false));
        this.mMoPubView.setUserDataKeywords(getKeywords(mediationAdRequest, true));
        this.mMoPubView.loadAd();
    }

    public View getBannerView() {
        return this.mMoPubView;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getKeywords(com.google.android.gms.ads.mediation.MediationAdRequest r6, boolean r7) {
        /*
            r5 = this;
            java.util.Date r0 = r6.getBirthday()
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x0022
            int r0 = getAge(r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "m_age:"
            r2.append(r3)
            java.lang.String r0 = java.lang.Integer.toString(r0)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            goto L_0x0023
        L_0x0022:
            r0 = r1
        L_0x0023:
            int r2 = r6.getGender()
            r3 = -1
            if (r2 == r3) goto L_0x0036
            r3 = 2
            if (r2 != r3) goto L_0x0030
            java.lang.String r2 = "m_gender:f"
            goto L_0x0037
        L_0x0030:
            r3 = 1
            if (r2 != r3) goto L_0x0036
            java.lang.String r2 = "m_gender:m"
            goto L_0x0037
        L_0x0036:
            r2 = r1
        L_0x0037:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "gmext"
            r3.append(r4)
            java.lang.String r4 = ","
            r3.append(r4)
            r3.append(r0)
            r3.append(r4)
            r3.append(r2)
            if (r7 == 0) goto L_0x005c
            boolean r6 = r5.keywordsContainPII(r6)
            if (r6 == 0) goto L_0x005b
            java.lang.String r1 = r3.toString()
        L_0x005b:
            return r1
        L_0x005c:
            boolean r6 = r5.keywordsContainPII(r6)
            if (r6 == 0) goto L_0x0063
            goto L_0x0067
        L_0x0063:
            java.lang.String r1 = r3.toString()
        L_0x0067:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.dfp.adapters.MoPubAdapter.getKeywords(com.google.android.gms.ads.mediation.MediationAdRequest, boolean):java.lang.String");
    }

    private boolean keywordsContainPII(MediationAdRequest mediationAdRequest) {
        return (mediationAdRequest.getBirthday() == null && mediationAdRequest.getGender() == -1 && mediationAdRequest.getLocation() == null) ? false : true;
    }

    private static int getAge(Date date) {
        return Calendar.getInstance().get(1) - Integer.parseInt((String) DateFormat.format("yyyy", date));
    }

    private class MBannerListener implements MoPubView.BannerAdListener {
        private MediationBannerListener mMediationBannerListener;

        public MBannerListener(MediationBannerListener mediationBannerListener) {
            this.mMediationBannerListener = mediationBannerListener;
        }

        public void onBannerClicked(MoPubView moPubView) {
            this.mMediationBannerListener.onAdClicked(MoPubAdapter.this);
        }

        public void onBannerCollapsed(MoPubView moPubView) {
            this.mMediationBannerListener.onAdClosed(MoPubAdapter.this);
        }

        public void onBannerExpanded(MoPubView moPubView) {
            this.mMediationBannerListener.onAdOpened(MoPubAdapter.this);
        }

        public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
            int i = AnonymousClass4.$SwitchMap$com$mopub$mobileads$MoPubErrorCode[moPubErrorCode.ordinal()];
            if (i == 1) {
                this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 3);
            } else if (i == 2) {
                this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 2);
            } else if (i != 3) {
                this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 0);
            } else {
                this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 1);
            }
        }

        public void onBannerLoaded(MoPubView moPubView) {
            if (!(MoPubAdapter.this.mAdSize.getWidth() == moPubView.getAdWidth() && MoPubAdapter.this.mAdSize.getHeight() == moPubView.getAdHeight())) {
                Log.w(MoPubAdapter.TAG, "The banner ad size loaded does not match the request size. Update the ad size on your MoPub UI to match the request size.");
            }
            this.mMediationBannerListener.onAdLoaded(MoPubAdapter.this);
        }
    }

    /* renamed from: com.mopub.mobileads.dfp.adapters.MoPubAdapter$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mobileads$MoPubErrorCode;
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$NativeErrorCode;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        static {
            /*
                com.mopub.mobileads.MoPubErrorCode[] r0 = com.mopub.mobileads.MoPubErrorCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$mobileads$MoPubErrorCode = r0
                r1 = 1
                com.mopub.mobileads.MoPubErrorCode r2 = com.mopub.mobileads.MoPubErrorCode.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$mopub$mobileads$MoPubErrorCode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.mobileads.MoPubErrorCode r3 = com.mopub.mobileads.MoPubErrorCode.NETWORK_TIMEOUT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$mopub$mobileads$MoPubErrorCode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.mobileads.MoPubErrorCode r4 = com.mopub.mobileads.MoPubErrorCode.SERVER_ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.mopub.nativeads.NativeErrorCode[] r3 = com.mopub.nativeads.NativeErrorCode.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$mopub$nativeads$NativeErrorCode = r3
                com.mopub.nativeads.NativeErrorCode r4 = com.mopub.nativeads.NativeErrorCode.EMPTY_AD_RESPONSE     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$com$mopub$nativeads$NativeErrorCode     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.mopub.nativeads.NativeErrorCode r3 = com.mopub.nativeads.NativeErrorCode.INVALID_REQUEST_URL     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeErrorCode     // Catch:{ NoSuchFieldError -> 0x004d }
                com.mopub.nativeads.NativeErrorCode r1 = com.mopub.nativeads.NativeErrorCode.CONNECTION_ERROR     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeErrorCode     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.mopub.nativeads.NativeErrorCode r1 = com.mopub.nativeads.NativeErrorCode.UNSPECIFIED     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.dfp.adapters.MoPubAdapter.AnonymousClass4.<clinit>():void");
        }
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        String string = bundle.getString(MOPUB_AD_UNIT_KEY);
        initializeMoPub(context, string);
        MoPubInterstitial moPubInterstitial = new MoPubInterstitial((Activity) context, string);
        this.mMoPubInterstitial = moPubInterstitial;
        moPubInterstitial.setInterstitialAdListener(new mMediationInterstitialListener(mediationInterstitialListener));
        if (mediationAdRequest.isTesting()) {
            this.mMoPubInterstitial.setTesting(true);
        }
        this.mMoPubInterstitial.setKeywords(getKeywords(mediationAdRequest, false));
        this.mMoPubInterstitial.setKeywords(getKeywords(mediationAdRequest, true));
        this.mMoPubInterstitial.load();
    }

    public void showInterstitial() {
        if (this.mMoPubInterstitial.isReady()) {
            this.mMoPubInterstitial.show();
        } else {
            MoPubLog.i("Interstitial was not ready. Unable to load the interstitial");
        }
    }

    private class mMediationInterstitialListener implements MoPubInterstitial.InterstitialAdListener {
        private MediationInterstitialListener mMediationInterstitialListener;

        public mMediationInterstitialListener(MediationInterstitialListener mediationInterstitialListener) {
            this.mMediationInterstitialListener = mediationInterstitialListener;
        }

        public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdClicked(MoPubAdapter.this);
        }

        public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdClosed(MoPubAdapter.this);
        }

        public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
            int i = AnonymousClass4.$SwitchMap$com$mopub$mobileads$MoPubErrorCode[moPubErrorCode.ordinal()];
            if (i == 1) {
                this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 3);
            } else if (i == 2) {
                this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 2);
            } else if (i != 3) {
                this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 0);
            } else {
                this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 1);
            }
        }

        public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdLoaded(MoPubAdapter.this);
        }

        public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdOpened(MoPubAdapter.this);
        }
    }

    private void initializeMoPub(Context context, String str) {
        if (!MoPub.isSdkInitialized()) {
            MoPub.initializeSdk(context, new SdkConfiguration.Builder(str).build(), initSdkListener());
        }
    }

    private SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {
            public void onInitializationFinished() {
                MoPubLog.d("MoPub SDK initialized.");
            }
        };
    }

    public static final class BundleBuilder {
        private static final String ARG_PRIVACY_ICON_SIZE_DP = "privacy_icon_size_dp";
        private int mPrivacyIconSizeDp;

        public BundleBuilder setPrivacyIconSize(int i) {
            this.mPrivacyIconSizeDp = i;
            return this;
        }

        public Bundle build() {
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_PRIVACY_ICON_SIZE_DP, this.mPrivacyIconSizeDp);
            return bundle;
        }
    }
}
