package com.tappx.a;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.tappx.a.m0;

public class s0 implements q0 {
    private static final AdSize[] d = {AdSize.BANNER, AdSize.FULL_BANNER, AdSize.LARGE_BANNER, AdSize.LEADERBOARD, AdSize.MEDIUM_RECTANGLE, AdSize.WIDE_SKYSCRAPER};
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final AdView f572a;
    private boolean b = false;
    private m0.c c;

    class a extends AdListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ m0.c f573a;
        final /* synthetic */ Runnable b;

        a(m0.c cVar, Runnable runnable) {
            this.f573a = cVar;
            this.b = runnable;
        }

        public void onAdClosed() {
        }

        public void onAdFailedToLoad(int i) {
            j0.d("1HPYA2lkbaNURYCXsP4iRrPA2bcLu2GoZBfTi2x2iws", new Object[0]);
            m0.c cVar = this.f573a;
            if (cVar != null) {
                cVar.a(a2.NO_FILL);
            }
        }

        public void onAdLeftApplication() {
            m0.c cVar = this.f573a;
            if (cVar != null) {
                cVar.a();
            }
        }

        public void onAdLoaded() {
            j0.d("sQBMFfIvnZat9SH496KzHfKib626NzkhHKkXIfYGxxc", new Object[0]);
            this.b.run();
            m0.c cVar = this.f573a;
            if (cVar != null) {
                cVar.a((View) s0.this.f572a);
            }
        }

        public void onAdOpened() {
            m0.c cVar = this.f573a;
            if (cVar != null) {
                cVar.d();
            }
        }
    }

    public s0(Context context) {
        this.f572a = new AdView(context);
    }

    public void destroy() {
        AdView adView = this.f572a;
        if (adView != null) {
            adView.destroy();
        }
    }

    public void loadAd() {
        if (this.b) {
            m0.c cVar = this.c;
            if (cVar != null) {
                cVar.a(a2.INTERNAL_ERROR);
                return;
            }
            return;
        }
        try {
            this.f572a.loadAd(new AdRequest.Builder().build());
        } catch (Throwable unused) {
        }
    }

    public void a(m0.c cVar, Runnable runnable) {
        this.c = cVar;
        if (cVar == null) {
            try {
                this.f572a.setAdListener((AdListener) null);
            } catch (Throwable unused) {
                this.b = true;
            }
        } else {
            this.f572a.setAdListener(new a(cVar, runnable));
        }
    }

    public void a(String str, int i, int i2) {
        try {
            this.f572a.setAdSize(a(i, i2));
            this.f572a.setAdUnitId(str);
        } catch (Throwable unused) {
            this.b = true;
        }
    }

    private AdSize a(int i, int i2) {
        for (AdSize adSize : d) {
            if (adSize.getWidth() == i && adSize.getHeight() == i2) {
                return adSize;
            }
        }
        return AdSize.BANNER;
    }

    public View a() {
        return this.f572a;
    }
}
