package com.startapp.android.publish.adsCommon.adListeners;

import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.adsCommon.Ad;

/* compiled from: StartAppSDK */
public class a implements AdDisplayListener {

    /* renamed from: a  reason: collision with root package name */
    AdDisplayListener f193a;

    public a(AdDisplayListener adDisplayListener) {
        this.f193a = adDisplayListener;
    }

    public void adHidden(final Ad ad) {
        if (this.f193a != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    a.this.f193a.adHidden(ad);
                }
            });
        }
    }

    public void adDisplayed(final Ad ad) {
        if (this.f193a != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    a.this.f193a.adDisplayed(ad);
                }
            });
        }
    }

    public void adClicked(final Ad ad) {
        if (this.f193a != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    a.this.f193a.adClicked(ad);
                }
            });
        }
    }

    public void adNotDisplayed(final Ad ad) {
        if (this.f193a != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    a.this.f193a.adNotDisplayed(ad);
                }
            });
        }
    }
}
