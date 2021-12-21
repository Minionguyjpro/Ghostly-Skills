package com.tappx.sdk.android;

import android.content.Context;
import com.tappx.a.h2;

public final class TappxInterstitial implements ITappxInterstitial {

    /* renamed from: a  reason: collision with root package name */
    private final Context f654a;
    private h2 b;

    public TappxInterstitial(Context context, String str) {
        this.f654a = context;
        h2 h2Var = new h2(this, context);
        this.b = h2Var;
        h2Var.b(str);
    }

    public void destroy() {
        this.b.a();
    }

    public Context getContext() {
        return this.f654a;
    }

    public boolean isReady() {
        return this.b.e();
    }

    public void loadAd() {
        loadAd((AdRequest) null);
    }

    public void setAutoShowWhenReady(boolean z) {
        this.b.a(z);
    }

    public void setListener(TappxInterstitialListener tappxInterstitialListener) {
        this.b.a(tappxInterstitialListener);
    }

    public void show() {
        this.b.f();
    }

    public void loadAd(AdRequest adRequest) {
        this.b.a(adRequest);
    }
}
