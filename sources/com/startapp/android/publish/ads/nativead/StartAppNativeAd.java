package com.startapp.android.publish.ads.nativead;

import android.content.Context;
import com.startapp.android.publish.ads.nativead.NativeAdDetails;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.a.f;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.Constants;
import com.startapp.common.a.g;
import com.startapp.common.a.i;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class StartAppNativeAd extends Ad implements NativeAdDetails.a {
    private static final String TAG = "StartAppNativeAd";
    private static final long serialVersionUID = 1;
    private a adEventDelegate;
    boolean isLoading = false;
    private List<NativeAdDetails> listNativeAds = new ArrayList();
    private b nativeAd;
    private NativeAdPreferences preferences;
    private int totalObjectsLoaded = 0;

    /* compiled from: StartAppSDK */
    public enum b {
        LAUNCH_APP,
        OPEN_MARKET
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
    }

    public StartAppNativeAd(Context context) {
        super(context, AdPreferences.Placement.INAPP_NATIVE);
    }

    private NativeAdPreferences getPreferences() {
        return this.preferences;
    }

    private void setPreferences(NativeAdPreferences nativeAdPreferences) {
        this.preferences = nativeAdPreferences;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n===== StartAppNativeAd =====\n");
        for (int i = 0; i < getNumberOfAds(); i++) {
            stringBuffer.append(this.listNativeAds.get(i));
        }
        stringBuffer.append("===== End StartAppNativeAd =====");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public void initNativeAdList() {
        this.totalObjectsLoaded = 0;
        if (this.listNativeAds == null) {
            this.listNativeAds = new ArrayList();
        }
        this.listNativeAds.clear();
        b bVar = this.nativeAd;
        if (bVar != null && bVar.d() != null) {
            for (int i = 0; i < this.nativeAd.d().size(); i++) {
                this.listNativeAds.add(new NativeAdDetails(this.nativeAd.d().get(i), getPreferences(), i, this));
            }
        }
    }

    public void onNativeAdDetailsLoaded(int i) {
        this.totalObjectsLoaded++;
        if (this.nativeAd.d() != null && this.totalObjectsLoaded == this.nativeAd.d().size()) {
            onNativeAdLoaded();
        }
    }

    private void onNativeAdLoaded() {
        g.a(TAG, 3, "Ad Loaded successfully");
        this.isLoading = false;
        setErrorMessage((String) null);
        if (this.adEventDelegate != null) {
            g.a(TAG, 3, "Calling original RecienedAd callback");
            AdEventListener a2 = this.adEventDelegate.a();
            if (a2 != null) {
                a2.onReceiveAd(this);
            }
        }
    }

    public int getNumberOfAds() {
        List<NativeAdDetails> list = this.listNativeAds;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public boolean isBelowMinCPM() {
        return this.nativeAd.isBelowMinCPM();
    }

    public boolean loadAd() {
        return loadAd(new NativeAdPreferences(), (AdEventListener) null);
    }

    public boolean loadAd(AdEventListener adEventListener) {
        return loadAd(new NativeAdPreferences(), adEventListener);
    }

    public boolean loadAd(NativeAdPreferences nativeAdPreferences) {
        return loadAd(nativeAdPreferences, (AdEventListener) null);
    }

    public boolean loadAd(NativeAdPreferences nativeAdPreferences, AdEventListener adEventListener) {
        g.a(TAG, 3, "Start loading StartAppNativeAd");
        this.adEventDelegate = new a(adEventListener);
        setPreferences(nativeAdPreferences);
        if (this.isLoading) {
            setErrorMessage("Ad is currently being loaded");
            return false;
        }
        this.isLoading = true;
        b bVar = new b(this.context, getPreferences());
        this.nativeAd = bVar;
        return bVar.load(nativeAdPreferences, this.adEventDelegate);
    }

    public ArrayList<NativeAdDetails> getNativeAds() {
        return getNativeAds((String) null);
    }

    public ArrayList<NativeAdDetails> getNativeAds(String str) {
        ArrayList<NativeAdDetails> arrayList = new ArrayList<>();
        f a2 = com.startapp.android.publish.adsCommon.a.g.a().b().a(AdPreferences.Placement.INAPP_NATIVE, str);
        if (a2.a()) {
            List<NativeAdDetails> list = this.listNativeAds;
            if (list != null) {
                for (NativeAdDetails next : list) {
                    next.a(str);
                    arrayList.add(next);
                }
                com.startapp.android.publish.adsCommon.a.b.a().a(new com.startapp.android.publish.adsCommon.a.a(AdPreferences.Placement.INAPP_NATIVE, str));
            }
        } else {
            c.a(this.context, c.a(getAdDetailsList()), str, a2.c());
            if (Constants.a().booleanValue()) {
                i.a().a(this.context, a2.b());
            }
        }
        return arrayList;
    }

    private List<AdDetails> getAdDetailsList() {
        ArrayList arrayList = new ArrayList();
        List<NativeAdDetails> list = this.listNativeAds;
        if (list != null) {
            for (NativeAdDetails b2 : list) {
                arrayList.add(b2.b());
            }
        }
        return arrayList;
    }

    public static String getPrivacyURL() {
        if (com.startapp.android.publish.adsCommon.adinformation.a.b().c() == null) {
            return "";
        }
        String c = com.startapp.android.publish.adsCommon.adinformation.a.b().c();
        if (c.contains("http://") || c.contains("https://")) {
            return com.startapp.android.publish.adsCommon.adinformation.a.b().c();
        }
        return "https://" + com.startapp.android.publish.adsCommon.adinformation.a.b().c();
    }

    public static String getPrivacyImageUrl() {
        return com.startapp.android.publish.adsCommon.adinformation.a.b().d();
    }

    /* compiled from: StartAppSDK */
    private class a implements AdEventListener {
        private AdEventListener b = null;

        public a(AdEventListener adEventListener) {
            this.b = new com.startapp.android.publish.adsCommon.adListeners.b(adEventListener);
        }

        public void onReceiveAd(Ad ad) {
            g.a(StartAppNativeAd.TAG, 3, "NativeAd Received");
            StartAppNativeAd.this.initNativeAdList();
        }

        public void onFailedToReceiveAd(Ad ad) {
            g.a(StartAppNativeAd.TAG, 3, "NativeAd Failed to load");
            StartAppNativeAd.this.setErrorMessage(ad.getErrorMessage());
            AdEventListener adEventListener = this.b;
            if (adEventListener != null) {
                adEventListener.onFailedToReceiveAd(StartAppNativeAd.this);
                this.b = null;
            }
            StartAppNativeAd.this.isLoading = false;
            StartAppNativeAd.this.initNativeAdList();
        }

        public AdEventListener a() {
            return this.b;
        }
    }
}
