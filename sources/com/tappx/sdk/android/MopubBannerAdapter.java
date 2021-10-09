package com.tappx.sdk.android;

import android.content.Context;
import android.util.Log;
import com.integralads.avid.library.mopub.BuildConfig;
import com.mopub.mobileads.CustomEventBanner;
import com.mopub.mobileads.MoPubErrorCode;
import com.tappx.sdk.android.TappxBanner;
import java.util.Map;

class MopubBannerAdapter extends CustomEventBanner {

    /* renamed from: a  reason: collision with root package name */
    private TappxBanner f644a;

    /* renamed from: com.tappx.sdk.android.MopubBannerAdapter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f645a;

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
                f645a = r0
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.DEVELOPER_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f645a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.INTERNAL_ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f645a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.SERVER_ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f645a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.sdk.android.MopubBannerAdapter.AnonymousClass1.<clinit>():void");
        }
    }

    private static final class a implements TappxBannerListener {

        /* renamed from: a  reason: collision with root package name */
        private final CustomEventBanner.CustomEventBannerListener f646a;

        private a(CustomEventBanner.CustomEventBannerListener customEventBannerListener) {
            this.f646a = customEventBannerListener;
        }

        /* synthetic */ a(CustomEventBanner.CustomEventBannerListener customEventBannerListener, AnonymousClass1 r2) {
            this(customEventBannerListener);
        }

        public void onBannerClicked(TappxBanner tappxBanner) {
            this.f646a.onBannerClicked();
        }

        public void onBannerCollapsed(TappxBanner tappxBanner) {
            this.f646a.onBannerCollapsed();
        }

        public void onBannerExpanded(TappxBanner tappxBanner) {
            this.f646a.onBannerExpanded();
        }

        public void onBannerLoadFailed(TappxBanner tappxBanner, TappxAdError tappxAdError) {
            Log.v("Tappx", "MoPub adapter: Banner load failed " + tappxAdError);
            this.f646a.onBannerFailed(MopubBannerAdapter.b(tappxAdError));
        }

        public void onBannerLoaded(TappxBanner tappxBanner) {
            Log.v("Tappx", "MoPub adapter: Banner loaded");
            this.f646a.onBannerLoaded(tappxBanner);
        }
    }

    MopubBannerAdapter() {
    }

    private int a(Map<String, String> map, String str, int i) {
        try {
            return Integer.parseInt(map.get(str));
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    private TappxBanner.AdSize a(int i, int i2) {
        return (i > TappxBanner.AdSize.BANNER_320x50.getWidth() || i2 > TappxBanner.AdSize.BANNER_320x50.getHeight()) ? (i > TappxBanner.AdSize.BANNER_300x250.getWidth() || i2 > TappxBanner.AdSize.BANNER_300x250.getHeight()) ? (i > TappxBanner.AdSize.BANNER_728x90.getWidth() || i2 > TappxBanner.AdSize.BANNER_728x90.getHeight()) ? TappxBanner.AdSize.SMART_BANNER : TappxBanner.AdSize.BANNER_728x90 : TappxBanner.AdSize.BANNER_300x250 : TappxBanner.AdSize.BANNER_320x50;
    }

    /* access modifiers changed from: private */
    public static MoPubErrorCode b(TappxAdError tappxAdError) {
        int i = AnonymousClass1.f645a[tappxAdError.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? MoPubErrorCode.NO_FILL : MoPubErrorCode.SERVER_ERROR : MoPubErrorCode.INTERNAL_ERROR : MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR;
    }

    /* access modifiers changed from: protected */
    public void loadBanner(Context context, CustomEventBanner.CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2) {
        String str = map2.get("appKey");
        int a2 = a(map2, "adWidth", -1);
        int a3 = a(map2, "adHeight", -1);
        if (str == null || str.isEmpty()) {
            Log.e("Tappx", "MoPub adapter: invalid app key as server parameter");
            customEventBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        TappxBanner.AdSize a4 = a(a2, a3);
        TappxBanner tappxBanner = new TappxBanner(context, str);
        this.f644a = tappxBanner;
        tappxBanner.setListener(new a(customEventBannerListener, (AnonymousClass1) null));
        this.f644a.setAdSize(a4);
        this.f644a.loadAd(new AdRequest().mediator(BuildConfig.SDK_NAME));
        Log.v("Tappx", "Loading " + getClass().getSimpleName());
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        TappxBanner tappxBanner = this.f644a;
        if (tappxBanner != null) {
            tappxBanner.setListener((TappxBannerListener) null);
            this.f644a.destroy();
        }
    }
}
