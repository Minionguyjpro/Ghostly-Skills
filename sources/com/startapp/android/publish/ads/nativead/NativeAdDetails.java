package com.startapp.android.publish.ads.nativead;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import com.startapp.android.publish.ads.nativead.StartAppNativeAd;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.d.b;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public class NativeAdDetails implements NativeAdInterface {

    /* renamed from: a  reason: collision with root package name */
    int f78a;
    a b;
    private AdDetails c;
    private Bitmap d;
    private Bitmap e;
    private boolean f = false;
    private String g;

    /* compiled from: StartAppSDK */
    protected interface a {
        void onNativeAdDetailsLoaded(int i);
    }

    public NativeAdDetails(AdDetails adDetails, NativeAdPreferences nativeAdPreferences, int i, a aVar) {
        g.a("StartAppNativeAd", 3, "Initializiang SingleAd [" + i + "]");
        this.c = adDetails;
        this.f78a = i;
        this.b = aVar;
        if (nativeAdPreferences.isAutoBitmapDownload()) {
            new com.startapp.common.a(getImageUrl(), new a.C0009a() {
                public void a(Bitmap bitmap, int i) {
                    NativeAdDetails.this.a(bitmap);
                    new com.startapp.common.a(NativeAdDetails.this.getSecondaryImageUrl(), new a.C0009a() {
                        public void a(Bitmap bitmap, int i) {
                            NativeAdDetails.this.b(bitmap);
                            NativeAdDetails.this.a();
                        }
                    }, i).a();
                }
            }, i).a();
        } else {
            a();
        }
    }

    public String toString() {
        return "         Title: [" + getTitle() + "]\n" + "         Description: [" + getDescription().substring(0, 30) + "]...\n" + "         Rating: [" + getRating() + "]\n" + "         Installs: [" + getInstalls() + "]\n" + "         Category: [" + getCategory() + "]\n" + "         PackageName: [" + getPackacgeName() + "]\n" + "         CampaginAction: [" + getCampaignAction() + "]\n";
    }

    /* access modifiers changed from: package-private */
    public void a(Bitmap bitmap) {
        this.d = bitmap;
    }

    /* access modifiers changed from: package-private */
    public void b(Bitmap bitmap) {
        this.e = bitmap;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        new Handler().post(new Runnable() {
            public void run() {
                g.a("StartAppNativeAd", 3, "SingleAd [" + NativeAdDetails.this.f78a + "] Loaded");
                if (NativeAdDetails.this.b != null) {
                    NativeAdDetails.this.b.onNativeAdDetailsLoaded(NativeAdDetails.this.f78a);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.g = str;
    }

    public String getTitle() {
        AdDetails adDetails = this.c;
        return adDetails != null ? adDetails.getTitle() : "";
    }

    public String getDescription() {
        AdDetails adDetails = this.c;
        return adDetails != null ? adDetails.getDescription() : "";
    }

    public float getRating() {
        AdDetails adDetails = this.c;
        if (adDetails != null) {
            return adDetails.getRating();
        }
        return 5.0f;
    }

    public String getImageUrl() {
        AdDetails adDetails = this.c;
        return adDetails != null ? adDetails.getImageUrl() : "";
    }

    public String getSecondaryImageUrl() {
        AdDetails adDetails = this.c;
        return adDetails != null ? adDetails.getSecondaryImageUrl() : "";
    }

    public Bitmap getImageBitmap() {
        return this.d;
    }

    public Bitmap getSecondaryImageBitmap() {
        return this.e;
    }

    public String getInstalls() {
        AdDetails adDetails = this.c;
        return adDetails != null ? adDetails.getInstalls() : "";
    }

    public String getCategory() {
        AdDetails adDetails = this.c;
        return adDetails != null ? adDetails.getCategory() : "";
    }

    public String getPackacgeName() {
        AdDetails adDetails = this.c;
        return adDetails != null ? adDetails.getPackageName() : "";
    }

    public StartAppNativeAd.b getCampaignAction() {
        StartAppNativeAd.b bVar = StartAppNativeAd.b.OPEN_MARKET;
        AdDetails adDetails = this.c;
        return (adDetails == null || !adDetails.isCPE()) ? bVar : StartAppNativeAd.b.LAUNCH_APP;
    }

    public Boolean isApp() {
        AdDetails adDetails = this.c;
        if (adDetails != null) {
            return Boolean.valueOf(adDetails.isApp());
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public AdDetails b() {
        return this.c;
    }

    /* renamed from: com.startapp.android.publish.ads.nativead.NativeAdDetails$3  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f82a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.startapp.android.publish.ads.nativead.StartAppNativeAd$b[] r0 = com.startapp.android.publish.ads.nativead.StartAppNativeAd.b.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f82a = r0
                com.startapp.android.publish.ads.nativead.StartAppNativeAd$b r1 = com.startapp.android.publish.ads.nativead.StartAppNativeAd.b.OPEN_MARKET     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f82a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.ads.nativead.StartAppNativeAd$b r1 = com.startapp.android.publish.ads.nativead.StartAppNativeAd.b.LAUNCH_APP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.nativead.NativeAdDetails.AnonymousClass3.<clinit>():void");
        }
    }

    public void sendClick(Context context) {
        if (this.c != null) {
            int i = AnonymousClass3.f82a[getCampaignAction().ordinal()];
            if (i == 1) {
                boolean a2 = c.a(context, AdPreferences.Placement.INAPP_NATIVE);
                if (!this.c.isSmartRedirect() || a2) {
                    c.a(context, this.c.getClickUrl(), this.c.getTrackingClickUrl(), new b(this.g), this.c.isStartappBrowserEnabled() && !a2, false);
                } else {
                    c.a(context, this.c.getClickUrl(), this.c.getTrackingClickUrl(), this.c.getPackageName(), new b(this.g), com.startapp.android.publish.adsCommon.b.a().A(), com.startapp.android.publish.adsCommon.b.a().B(), this.c.isStartappBrowserEnabled(), this.c.shouldSendRedirectHops(), false);
                }
            } else if (i == 2) {
                c.a(getPackacgeName(), this.c.getIntentDetails(), this.c.getClickUrl(), context, new b(this.g));
            }
        }
    }

    public void sendImpression(Context context) {
        if (!this.f) {
            this.f = true;
            if (this.c != null) {
                g.a("StartAppNativeAd", 3, "Sending Impression for [" + this.f78a + "]");
                c.a(context, this.c.getTrackingUrl(), new b(this.g));
                return;
            }
            return;
        }
        g.a("StartAppNativeAd", 3, "Already sent impression for [" + this.f78a + "]");
    }
}
