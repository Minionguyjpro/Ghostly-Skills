package com.tappx.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.integralads.avid.library.mopub.BuildConfig;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.MoPubErrorCode;
import java.util.Map;

public class MopubInterstitialAdapter extends CustomEventInterstitial {

    /* renamed from: a  reason: collision with root package name */
    private TappxInterstitial f647a;

    /* renamed from: com.tappx.sdk.android.MopubInterstitialAdapter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f648a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.tappx.sdk.android.TappxAdError[] r0 = com.tappx.sdk.android.TappxAdError.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f648a = r0
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.DEVELOPER_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f648a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.INTERNAL_ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f648a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.SERVER_ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f648a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.sdk.android.MopubInterstitialAdapter.AnonymousClass1.<clinit>():void");
        }
    }

    private static final class a implements TappxInterstitialListener {

        /* renamed from: a  reason: collision with root package name */
        private final CustomEventInterstitial.CustomEventInterstitialListener f649a;

        private a(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener) {
            this.f649a = customEventInterstitialListener;
        }

        /* synthetic */ a(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, AnonymousClass1 r2) {
            this(customEventInterstitialListener);
        }

        public void onInterstitialClicked(TappxInterstitial tappxInterstitial) {
            this.f649a.onInterstitialClicked();
        }

        public void onInterstitialDismissed(TappxInterstitial tappxInterstitial) {
            this.f649a.onInterstitialDismissed();
        }

        public void onInterstitialLoadFailed(TappxInterstitial tappxInterstitial, TappxAdError tappxAdError) {
            Log.v("Tappx", "MoPub adapter: Interstitial load failed " + tappxAdError);
            this.f649a.onInterstitialFailed(MopubInterstitialAdapter.b(tappxAdError));
        }

        public void onInterstitialLoaded(TappxInterstitial tappxInterstitial) {
            Log.v("Tappx", "MoPub adapter: Interstitial loaded");
            this.f649a.onInterstitialLoaded();
        }

        public void onInterstitialShown(TappxInterstitial tappxInterstitial) {
            this.f649a.onInterstitialShown();
        }
    }

    /* access modifiers changed from: private */
    public static MoPubErrorCode b(TappxAdError tappxAdError) {
        int i = AnonymousClass1.f648a[tappxAdError.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? MoPubErrorCode.NO_FILL : MoPubErrorCode.SERVER_ERROR : MoPubErrorCode.INTERNAL_ERROR : MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR;
    }

    /* access modifiers changed from: protected */
    public void loadInterstitial(Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        String str = map2.get("appKey");
        boolean z = context instanceof Activity;
        if (str == null || str.isEmpty()) {
            Log.e("Tappx", "MoPub adapter: invalid app key as server parameter");
            customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        } else if (!z) {
            customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NO_FILL);
        } else {
            TappxInterstitial tappxInterstitial = new TappxInterstitial(context, str);
            this.f647a = tappxInterstitial;
            tappxInterstitial.setListener(new a(customEventInterstitialListener, (AnonymousClass1) null));
            this.f647a.loadAd(new AdRequest().mediator(BuildConfig.SDK_NAME));
            Log.v("Tappx", "Loading " + getClass().getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        TappxInterstitial tappxInterstitial = this.f647a;
        if (tappxInterstitial != null) {
            tappxInterstitial.destroy();
            this.f647a.setListener((TappxInterstitialListener) null);
            this.f647a = null;
        }
    }

    /* access modifiers changed from: protected */
    public void showInterstitial() {
        TappxInterstitial tappxInterstitial = this.f647a;
        if (tappxInterstitial != null) {
            tappxInterstitial.show();
        }
    }
}
