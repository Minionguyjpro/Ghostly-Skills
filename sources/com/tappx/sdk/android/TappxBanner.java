package com.tappx.sdk.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.mopub.mobileads.MoPubView;
import com.tappx.a.c3;
import com.tappx.a.e2;
import com.tappx.a.g2;

public final class TappxBanner extends FrameLayout implements c3.c, ITappxBanner {

    /* renamed from: a  reason: collision with root package name */
    private c3 f652a;
    private g2 b;
    private final e2 c;
    boolean d;
    boolean e;
    boolean f;
    private boolean g;

    public enum AdSize {
        SMART_BANNER(-1, -1),
        BANNER_320x50(320, 50),
        BANNER_728x90(728, 90),
        BANNER_300x250(300, MoPubView.MoPubAdSizeInt.HEIGHT_250_INT);
        

        /* renamed from: a  reason: collision with root package name */
        private final int f653a;
        private final int b;

        private AdSize(int i, int i2) {
            this.f653a = i;
            this.b = i2;
        }

        public int getHeight() {
            return this.b;
        }

        public int getWidth() {
            return this.f653a;
        }
    }

    public TappxBanner(Context context, String str) {
        super(context);
        this.c = new e2();
        c();
        setAppKey(str);
    }

    private void a(Context context, AttributeSet attributeSet) {
        boolean z = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TappxParams, 0, 0);
        try {
            String string = obtainStyledAttributes.getString(R.styleable.TappxParams_txAppKey);
            String string2 = obtainStyledAttributes.getString(R.styleable.TappxParams_txAdSize);
            if (obtainStyledAttributes.getBoolean(R.styleable.TappxParams_txAutoPrivacyDisclaimer, false)) {
                Tappx.getPrivacyManager(context).setAutoPrivacyDisclaimerEnabled(true);
            }
            int i = obtainStyledAttributes.getInt(R.styleable.TappxParams_txRefreshTime, -1);
            if (string != null) {
                setAppKey(string);
            }
            if (string2 != null) {
                setAdSizeFromString(string2);
            }
            if (i > 0) {
                setRefreshTimeSeconds(i);
            }
            if (string != null) {
                z = true;
            }
            this.d = z;
        } catch (Exception unused) {
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void b() {
        boolean z = true;
        boolean z2 = getVisibility() == 0;
        boolean z3 = getWindowVisibility() == 0;
        boolean b2 = this.f652a.b();
        boolean z4 = this.f && this.g && z2 && z3 && b2;
        if (!this.f || !z2 || !z3 || !b2) {
            z = false;
        }
        if (z4 != this.e) {
            this.e = z4;
            this.b.b(z4);
        }
        if (z) {
            a();
        }
    }

    private void c() {
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.b = g2.c.a().a(this);
        c3 a2 = c3.b.a().a(getContext());
        this.f652a = a2;
        a2.a((c3.c) this);
        setAdSize(AdSize.BANNER_320x50);
    }

    private void setAdSizeFromString(String str) {
        setAdSize(a(str));
    }

    public void destroy() {
        this.b.a();
        this.f652a.a();
    }

    public void loadAd(AdRequest adRequest) {
        this.d = false;
        this.b.a(adRequest);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setAttachedToWindow(true);
        b();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setAttachedToWindow(false);
        b();
    }

    public void onDeviceScreenStateChanged(boolean z) {
        b();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        e2.a a2 = this.c.a(this);
        this.b.a(a2.a());
        setVisibleOnScreen(a2.b());
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        b();
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        b();
    }

    public void setAdSize(AdSize adSize) {
        this.b.a(adSize);
    }

    public void setAppKey(String str) {
        this.b.b(str);
    }

    public void setAttachedToWindow(boolean z) {
        this.f = z;
    }

    public void setEnableAutoRefresh(boolean z) {
        this.b.a(z);
    }

    public void setListener(TappxBannerListener tappxBannerListener) {
        this.b.a(tappxBannerListener);
    }

    public void setRefreshTimeSeconds(int i) {
        this.b.b(i * 1000);
    }

    /* access modifiers changed from: package-private */
    public void setVisibleOnScreen(boolean z) {
        if (z != this.g) {
            this.g = z;
            b();
        }
    }

    public void loadAd() {
        loadAd((AdRequest) null);
    }

    public TappxBanner(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
        c();
        a(context, attributeSet);
    }

    public TappxBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new e2();
        c();
        a(context, attributeSet);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.tappx.sdk.android.TappxBanner.AdSize a(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r5 = r5.toLowerCase()
            int r0 = r5.hashCode()
            r1 = 1
            r2 = 2
            r3 = -559799608(0xffffffffdea222c8, float:-5.8415601E18)
            if (r0 == r3) goto L_0x003d
            r3 = 109549001(0x68795c9, float:5.1001445E-35)
            if (r0 == r3) goto L_0x0033
            r3 = 1507809730(0x59df59c2, float:7.8584512E15)
            if (r0 == r3) goto L_0x0029
            r3 = 1622564786(0x60b65fb2, float:1.0513134E20)
            if (r0 == r3) goto L_0x001f
            goto L_0x0047
        L_0x001f:
            java.lang.String r0 = "728x90"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0047
            r5 = 1
            goto L_0x0048
        L_0x0029:
            java.lang.String r0 = "320x50"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0047
            r5 = 0
            goto L_0x0048
        L_0x0033:
            java.lang.String r0 = "smart"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0047
            r5 = 3
            goto L_0x0048
        L_0x003d:
            java.lang.String r0 = "300x250"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0047
            r5 = 2
            goto L_0x0048
        L_0x0047:
            r5 = -1
        L_0x0048:
            if (r5 == 0) goto L_0x0057
            if (r5 == r1) goto L_0x0054
            if (r5 == r2) goto L_0x0051
            com.tappx.sdk.android.TappxBanner$AdSize r5 = com.tappx.sdk.android.TappxBanner.AdSize.SMART_BANNER
            return r5
        L_0x0051:
            com.tappx.sdk.android.TappxBanner$AdSize r5 = com.tappx.sdk.android.TappxBanner.AdSize.BANNER_300x250
            return r5
        L_0x0054:
            com.tappx.sdk.android.TappxBanner$AdSize r5 = com.tappx.sdk.android.TappxBanner.AdSize.BANNER_728x90
            return r5
        L_0x0057:
            com.tappx.sdk.android.TappxBanner$AdSize r5 = com.tappx.sdk.android.TappxBanner.AdSize.BANNER_320x50
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tappx.sdk.android.TappxBanner.a(java.lang.String):com.tappx.sdk.android.TappxBanner$AdSize");
    }

    private void a() {
        if (this.d) {
            this.d = false;
            loadAd();
        }
    }
}
