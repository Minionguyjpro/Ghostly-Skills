package com.tappx.sdk.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;
import com.tappx.sdk.android.AdRequest;
import com.tappx.sdk.android.TappxAdError;
import com.tappx.sdk.android.TappxBanner;
import com.tappx.sdk.android.TappxBannerListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class AdmobBannerAdapter implements CustomEventBanner {
    private static final String TEST_REQUEST_SUFIX = "|1";
    private TappxBanner banner;

    /* renamed from: com.tappx.sdk.adapters.AdmobBannerAdapter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$tappx$sdk$android$TappxAdError;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.tappx.sdk.android.TappxAdError[] r0 = com.tappx.sdk.android.TappxAdError.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$tappx$sdk$android$TappxAdError = r0
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.NETWORK_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$tappx$sdk$android$TappxAdError     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.INTERNAL_ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$tappx$sdk$android$TappxAdError     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.sdk.adapters.AdmobBannerAdapter.AnonymousClass1.<clinit>():void");
        }
    }

    private static final class AdapterListener implements TappxBannerListener {
        final CustomEventBannerListener admobListener;

        /* synthetic */ AdapterListener(CustomEventBannerListener customEventBannerListener, AnonymousClass1 r2) {
            this(customEventBannerListener);
        }

        public void onBannerClicked(TappxBanner tappxBanner) {
            this.admobListener.onAdClicked();
        }

        public void onBannerCollapsed(TappxBanner tappxBanner) {
            this.admobListener.onAdClosed();
        }

        public void onBannerExpanded(TappxBanner tappxBanner) {
            this.admobListener.onAdOpened();
        }

        public void onBannerLoadFailed(TappxBanner tappxBanner, TappxAdError tappxAdError) {
            this.admobListener.onAdFailedToLoad(AdmobBannerAdapter.convertToAdmobReason(tappxAdError));
        }

        public void onBannerLoaded(TappxBanner tappxBanner) {
            Log.v("Tappx", "Admob adapter: banner loaded");
            this.admobListener.onAdLoaded(tappxBanner);
        }

        private AdapterListener(CustomEventBannerListener customEventBannerListener) {
            this.admobListener = customEventBannerListener;
        }
    }

    private TappxBanner.AdSize convertAdSize(AdSize adSize) {
        if (AdSize.BANNER.equals(adSize)) {
            return TappxBanner.AdSize.BANNER_320x50;
        }
        if (AdSize.MEDIUM_RECTANGLE.equals(adSize)) {
            return TappxBanner.AdSize.BANNER_300x250;
        }
        if (AdSize.LEADERBOARD.equals(adSize)) {
            return TappxBanner.AdSize.BANNER_728x90;
        }
        return TappxBanner.AdSize.SMART_BANNER;
    }

    /* access modifiers changed from: private */
    public static int convertToAdmobReason(TappxAdError tappxAdError) {
        int i = AnonymousClass1.$SwitchMap$com$tappx$sdk$android$TappxAdError[tappxAdError.ordinal()];
        if (i != 1) {
            return i != 2 ? 3 : 0;
        }
        return 2;
    }

    private AdRequest.Gender getGender(MediationAdRequest mediationAdRequest) {
        int gender = mediationAdRequest.getGender();
        if (gender == 1) {
            return AdRequest.Gender.MALE;
        }
        if (gender != 2) {
            return AdRequest.Gender.UNKNOWN;
        }
        return AdRequest.Gender.FEMALE;
    }

    private String getKeywords(MediationAdRequest mediationAdRequest) {
        Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords == null || keywords.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String next : keywords) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(next);
        }
        return sb.toString();
    }

    private void setAge(AdRequest adRequest, MediationAdRequest mediationAdRequest) {
        Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            int i = Calendar.getInstance().get(1);
            Calendar instance = Calendar.getInstance();
            instance.setTime(birthday);
            int i2 = instance.get(1);
            int i3 = i - i2;
            if (i3 >= 0 && i3 < 120) {
                adRequest.age(i2);
            }
            adRequest.yearOfBirth(i2);
        }
    }

    public void onDestroy() {
        TappxBanner tappxBanner = this.banner;
        if (tappxBanner != null) {
            tappxBanner.setListener((TappxBannerListener) null);
            this.banner.destroy();
            this.banner = null;
        }
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void requestBannerAd(Context context, CustomEventBannerListener customEventBannerListener, String str, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle) {
        if (str == null || str.isEmpty()) {
            Log.e("Tappx", "Admob adapter: invalid app key as server parameter");
            customEventBannerListener.onAdFailedToLoad(0);
            return;
        }
        AdRequest adRequest = new AdRequest();
        if (str.endsWith(TEST_REQUEST_SUFIX)) {
            str = str.substring(0, str.length() - 2);
            adRequest.useTestAds(true);
        }
        this.banner = new TappxBanner(context, str);
        this.banner.setAdSize(convertAdSize(adSize));
        this.banner.setListener(new AdapterListener(customEventBannerListener, (AnonymousClass1) null));
        adRequest.mediator("admob");
        if (mediationAdRequest != null) {
            adRequest.keywords(getKeywords(mediationAdRequest));
            adRequest.gender(getGender(mediationAdRequest));
            setAge(adRequest, mediationAdRequest);
        }
        this.banner.setEnableAutoRefresh(false);
        this.banner.loadAd(adRequest);
        Log.v("Tappx", "Loading " + AdmobBannerAdapter.class.getSimpleName());
    }
}
