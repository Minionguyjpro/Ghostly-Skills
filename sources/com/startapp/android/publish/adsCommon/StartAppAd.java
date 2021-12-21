package com.startapp.android.publish.adsCommon;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.ads.splash.SplashHideListener;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.a.f;
import com.startapp.android.publish.adsCommon.activities.AppWallActivity;
import com.startapp.android.publish.adsCommon.activities.OverlayActivity;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.cache.a;
import com.startapp.android.publish.cache.c;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.Constants;
import com.startapp.common.a.g;
import com.startapp.common.a.i;
import com.startapp.common.b;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class StartAppAd extends Ad {
    private static final String TAG = "StartAppAd";
    private static final long serialVersionUID = 1;
    private static boolean testMode = false;
    g ad = null;
    private c adKey = null;
    private AdMode adMode = AdMode.AUTOMATIC;
    private AdPreferences adPreferences = null;
    AdDisplayListener callback = null;
    private BroadcastReceiver callbackBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.startapp.android.ShowFailedDisplayBroadcastListener")) {
                if (intent.getExtras().containsKey("showFailedReason")) {
                    StartAppAd.this.setNotDisplayedReason((AdDisplayListener.NotDisplayedReason) intent.getExtras().getSerializable("showFailedReason"));
                }
                if (StartAppAd.this.callback != null) {
                    StartAppAd.this.callback.adNotDisplayed(StartAppAd.this);
                }
                a(context);
            } else if (intent.getAction().equals("com.startapp.android.ShowDisplayBroadcastListener")) {
                if (StartAppAd.this.callback != null) {
                    StartAppAd.this.callback.adDisplayed(StartAppAd.this);
                }
            } else if (intent.getAction().equals("com.startapp.android.OnClickCallback")) {
                if (StartAppAd.this.callback != null) {
                    StartAppAd.this.callback.adClicked(StartAppAd.this);
                }
            } else if (!intent.getAction().equals("com.startapp.android.OnVideoCompleted")) {
                if (StartAppAd.this.callback != null) {
                    StartAppAd.this.callback.adHidden(StartAppAd.this);
                }
                a(context);
            } else if (StartAppAd.this.videoListener != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        StartAppAd.this.videoListener.onVideoCompleted();
                    }
                });
            }
            StartAppAd.this.ad = null;
        }

        private void a(Context context) {
            b.a(context).a((BroadcastReceiver) this);
        }
    };
    VideoListener videoListener = null;

    /* compiled from: StartAppSDK */
    public enum AdMode {
        AUTOMATIC,
        FULLPAGE,
        OFFERWALL,
        REWARDED_VIDEO,
        VIDEO,
        OVERLAY
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences2, AdEventListener adEventListener) {
    }

    public void onPause() {
    }

    public StartAppAd(Context context) {
        super(context, (AdPreferences.Placement) null);
    }

    public static void init(Context context, String str, String str2) {
        StartAppSDK.init(context, str, str2);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences2, AdEventListener adEventListener) {
        if (!canShowAd()) {
            if (adEventListener != null) {
                setErrorMessage("serving ads disabled");
                adEventListener.onFailedToReceiveAd(this);
            }
            return false;
        }
        c a2 = a.a().a(this.context, this, this.adMode, adPreferences2, adEventListener);
        this.adKey = a2;
        if (a2 != null) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x0168  */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean show(java.lang.String r10, com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener r11) {
        /*
            r9 = this;
            r0 = 0
            r9.setNotDisplayedReason(r0)
            com.startapp.android.publish.adsCommon.adListeners.a r1 = new com.startapp.android.publish.adsCommon.adListeners.a
            r1.<init>(r11)
            r9.callback = r1
            boolean r11 = r9.canShowAd()
            r1 = 0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            if (r11 != 0) goto L_0x0021
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r10 = com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener.NotDisplayedReason.SERVING_ADS_DISABLED
            r9.setNotDisplayedReason(r10)
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener r10 = r9.callback
            r10.adNotDisplayed(r9)
            return r1
        L_0x0021:
            com.startapp.android.publish.cache.c r11 = r9.adKey
            if (r11 != 0) goto L_0x0028
            r9.loadAd()
        L_0x0028:
            boolean r11 = r9.isAppOnForeground()
            if (r11 == 0) goto L_0x0142
            boolean r11 = r9.isNetworkAvailable()
            if (r11 == 0) goto L_0x013c
            boolean r11 = r9.isReady()
            r3 = 1
            if (r11 == 0) goto L_0x011f
            com.startapp.android.publish.common.model.AdPreferences$Placement r11 = r9.getPlacement()
            com.startapp.android.publish.adsCommon.a.f r4 = r9.shouldDisplayAd(r10, r11)
            boolean r5 = r4.a()
            if (r5 == 0) goto L_0x0100
            com.startapp.android.publish.cache.a r5 = com.startapp.android.publish.cache.a.a()
            com.startapp.android.publish.cache.c r6 = r9.adKey
            com.startapp.android.publish.adsCommon.g r5 = r5.a((com.startapp.android.publish.cache.c) r6)
            r9.ad = r5
            if (r5 == 0) goto L_0x011c
            com.startapp.android.publish.common.model.AdPreferences$Placement r5 = r9.placement
            com.startapp.android.publish.common.model.AdPreferences$Placement r6 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_SPLASH
            r7 = 3
            if (r5 != r6) goto L_0x0076
            com.startapp.android.publish.adsCommon.m r5 = com.startapp.android.publish.adsCommon.m.a()
            boolean r5 = r5.n()
            if (r5 == 0) goto L_0x0076
            java.lang.String r11 = "StartAppAd"
            java.lang.String r2 = "App in background, can't display splash"
            com.startapp.common.a.g.a((java.lang.String) r11, (int) r7, (java.lang.String) r2)
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r11 = com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener.NotDisplayedReason.APP_IN_BACKGROUND
            r9.setNotDisplayedReason(r11)
            goto L_0x011c
        L_0x0076:
            com.startapp.android.publish.adsCommon.g r5 = r9.ad
            boolean r5 = r5.a(r10)
            if (r5 == 0) goto L_0x00e9
            com.startapp.android.publish.adsCommon.a.b r6 = com.startapp.android.publish.adsCommon.a.b.a()
            com.startapp.android.publish.adsCommon.a.a r8 = new com.startapp.android.publish.adsCommon.a.a
            r8.<init>(r11, r10)
            r6.a((com.startapp.android.publish.adsCommon.a.a) r8)
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r11 = r9.adMode
            if (r11 == 0) goto L_0x00a4
            com.startapp.android.publish.common.model.AdPreferences$Placement r11 = r9.placement
            com.startapp.android.publish.common.model.AdPreferences$Placement r6 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_SPLASH
            if (r11 == r6) goto L_0x00a4
            com.startapp.android.publish.common.model.AdPreferences r11 = r9.adPreferences
            if (r11 == 0) goto L_0x00a5
            com.startapp.android.publish.common.model.AdPreferences r6 = new com.startapp.android.publish.common.model.AdPreferences
            r6.<init>()
            boolean r11 = r11.equals(r6)
            if (r11 == 0) goto L_0x00a4
            goto L_0x00a5
        L_0x00a4:
            r3 = 0
        L_0x00a5:
            if (r3 == 0) goto L_0x00f8
            com.startapp.android.publish.cache.a r11 = com.startapp.android.publish.cache.a.a()
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = r9.adMode
            java.lang.String r3 = r11.a((com.startapp.android.publish.adsCommon.StartAppAd.AdMode) r3)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "reset autoLoad after show "
            r6.append(r8)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            java.lang.String r8 = "preCache"
            com.startapp.common.a.g.a((java.lang.String) r8, (int) r7, (java.lang.String) r6)
            android.content.Context r6 = r9.context
            com.startapp.android.publish.adsCommon.k.b((android.content.Context) r6, (java.lang.String) r3, (java.lang.Integer) r2)
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = r9.adMode
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r6 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.AUTOMATIC
            if (r3 != r6) goto L_0x00f8
            android.content.Context r3 = r9.context
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r6 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.FULLPAGE
            java.lang.String r6 = r11.a((com.startapp.android.publish.adsCommon.StartAppAd.AdMode) r6)
            com.startapp.android.publish.adsCommon.k.b((android.content.Context) r3, (java.lang.String) r6, (java.lang.Integer) r2)
            android.content.Context r3 = r9.context
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r6 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.OFFERWALL
            java.lang.String r11 = r11.a((com.startapp.android.publish.adsCommon.StartAppAd.AdMode) r6)
            com.startapp.android.publish.adsCommon.k.b((android.content.Context) r3, (java.lang.String) r11, (java.lang.Integer) r2)
            goto L_0x00f8
        L_0x00e9:
            com.startapp.android.publish.adsCommon.g r11 = r9.ad
            boolean r2 = r11 instanceof com.startapp.android.publish.adsCommon.Ad
            if (r2 == 0) goto L_0x00f8
            com.startapp.android.publish.adsCommon.Ad r11 = (com.startapp.android.publish.adsCommon.Ad) r11
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r11 = r11.getNotDisplayedReason()
            r9.setNotDisplayedReason(r11)
        L_0x00f8:
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r11 = r9.adMode
            com.startapp.android.publish.common.model.AdPreferences r2 = r9.adPreferences
            r9.loadAd(r11, r2, r0)
            goto L_0x011d
        L_0x0100:
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r11 = com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener.NotDisplayedReason.AD_RULES
            r9.setNotDisplayedReason(r11)
            java.lang.Boolean r11 = com.startapp.common.Constants.a()
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x011c
            com.startapp.common.a.i r11 = com.startapp.common.a.i.a()
            android.content.Context r2 = r9.context
            java.lang.String r3 = r4.b()
            r11.a(r2, r3)
        L_0x011c:
            r5 = 0
        L_0x011d:
            r1 = r5
            goto L_0x0148
        L_0x011f:
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r11 = r9.adMode
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r2 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.REWARDED_VIDEO
            if (r11 == r2) goto L_0x0132
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r11 = r9.adMode
            com.startapp.android.publish.adsCommon.StartAppAd$AdMode r2 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.VIDEO
            if (r11 == r2) goto L_0x0132
            boolean r11 = r9.showPreparedVideoFallbackAd(r10)
            if (r11 == 0) goto L_0x0132
            goto L_0x0133
        L_0x0132:
            r3 = 0
        L_0x0133:
            if (r3 != 0) goto L_0x013a
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r11 = com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener.NotDisplayedReason.AD_NOT_READY
            r9.setNotDisplayedReason(r11)
        L_0x013a:
            r4 = r0
            goto L_0x0149
        L_0x013c:
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r11 = com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener.NotDisplayedReason.NETWORK_PROBLEM
            r9.setNotDisplayedReason(r11)
            goto L_0x0147
        L_0x0142:
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r11 = com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener.NotDisplayedReason.APP_IN_BACKGROUND
            r9.setNotDisplayedReason(r11)
        L_0x0147:
            r4 = r0
        L_0x0148:
            r3 = 0
        L_0x0149:
            if (r1 != 0) goto L_0x014d
            if (r3 == 0) goto L_0x0166
        L_0x014d:
            java.lang.String r11 = "com.startapp.android.HideDisplayBroadcastListener"
            r9.registerBroadcastReceiver(r11)
            java.lang.String r11 = "com.startapp.android.ShowDisplayBroadcastListener"
            r9.registerBroadcastReceiver(r11)
            java.lang.String r11 = "com.startapp.android.ShowFailedDisplayBroadcastListener"
            r9.registerBroadcastReceiver(r11)
            java.lang.String r11 = "com.startapp.android.OnClickCallback"
            r9.registerBroadcastReceiver(r11)
            java.lang.String r11 = "com.startapp.android.OnVideoCompleted"
            r9.registerBroadcastReceiver(r11)
        L_0x0166:
            if (r1 != 0) goto L_0x01e9
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r11 = r9.getNotDisplayedReason()
            if (r11 != 0) goto L_0x0173
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r11 = com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener.NotDisplayedReason.INTERNAL_ERROR
            r9.setNotDisplayedReason(r11)
        L_0x0173:
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r11 = r9.getNotDisplayedReason()
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r2 = com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener.NotDisplayedReason.NETWORK_PROBLEM
            if (r11 == r2) goto L_0x01de
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r11 = r9.getNotDisplayedReason()
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r2 = com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener.NotDisplayedReason.AD_RULES
            if (r11 == r2) goto L_0x01c5
            if (r3 == 0) goto L_0x01a4
            android.content.Context r11 = r9.context
            com.startapp.android.publish.adsCommon.g r2 = r9.ad
            if (r2 == 0) goto L_0x018c
            goto L_0x0196
        L_0x018c:
            com.startapp.android.publish.cache.a r2 = com.startapp.android.publish.cache.a.a()
            com.startapp.android.publish.cache.c r4 = r9.adKey
            com.startapp.android.publish.adsCommon.g r2 = r2.b((com.startapp.android.publish.cache.c) r4)
        L_0x0196:
            java.lang.String[] r2 = com.startapp.android.publish.adsCommon.c.a((com.startapp.android.publish.adsCommon.g) r2)
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r4 = com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener.NotDisplayedReason.AD_NOT_READY_VIDEO_FALLBACK
            java.lang.String r4 = r4.toString()
            com.startapp.android.publish.adsCommon.c.a((android.content.Context) r11, (java.lang.String[]) r2, (java.lang.String) r10, (java.lang.String) r4)
            goto L_0x01de
        L_0x01a4:
            android.content.Context r11 = r9.context
            com.startapp.android.publish.adsCommon.g r2 = r9.ad
            if (r2 == 0) goto L_0x01ab
            goto L_0x01b5
        L_0x01ab:
            com.startapp.android.publish.cache.a r2 = com.startapp.android.publish.cache.a.a()
            com.startapp.android.publish.cache.c r4 = r9.adKey
            com.startapp.android.publish.adsCommon.g r2 = r2.b((com.startapp.android.publish.cache.c) r4)
        L_0x01b5:
            java.lang.String[] r2 = com.startapp.android.publish.adsCommon.c.a((com.startapp.android.publish.adsCommon.g) r2)
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener$NotDisplayedReason r4 = r9.getNotDisplayedReason()
            java.lang.String r4 = r4.toString()
            com.startapp.android.publish.adsCommon.c.a((android.content.Context) r11, (java.lang.String[]) r2, (java.lang.String) r10, (java.lang.String) r4)
            goto L_0x01de
        L_0x01c5:
            if (r4 == 0) goto L_0x01de
            android.content.Context r11 = r9.context
            com.startapp.android.publish.cache.a r2 = com.startapp.android.publish.cache.a.a()
            com.startapp.android.publish.cache.c r5 = r9.adKey
            com.startapp.android.publish.adsCommon.g r2 = r2.b((com.startapp.android.publish.cache.c) r5)
            java.lang.String[] r2 = com.startapp.android.publish.adsCommon.c.a((com.startapp.android.publish.adsCommon.g) r2)
            java.lang.String r4 = r4.c()
            com.startapp.android.publish.adsCommon.c.a((android.content.Context) r11, (java.lang.String[]) r2, (java.lang.String) r10, (java.lang.String) r4)
        L_0x01de:
            r9.ad = r0
            if (r3 != 0) goto L_0x01e9
            com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener r10 = r9.callback
            if (r10 == 0) goto L_0x01e9
            r10.adNotDisplayed(r9)
        L_0x01e9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.StartAppAd.show(java.lang.String, com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener):boolean");
    }

    private boolean showPreparedVideoFallbackAd(String str) {
        if (canShowAd() && b.a().H().h()) {
            AdPreferences adPreferences2 = this.adPreferences;
            if (adPreferences2 == null) {
                adPreferences2 = new AdPreferences();
            }
            adPreferences2.setType(Ad.AdType.NON_VIDEO);
            AdPreferences.Placement placement = getPlacement();
            g b = a.a().b(new c(placement, adPreferences2));
            if (b != null && b.isReady() && shouldDisplayAd(str, placement).a()) {
                b.setVideoCancelCallBack(true);
                if (Constants.a().booleanValue()) {
                    i.a().a(this.context, "display Video fallback");
                }
                return b.a(str);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public f shouldDisplayAd(String str, AdPreferences.Placement placement) {
        return b.a().F().a(placement, str);
    }

    /* access modifiers changed from: protected */
    public AdPreferences.Placement getPlacement() {
        AdPreferences.Placement placement = super.getPlacement();
        return (placement != null || this.adKey == null || a.a().b(this.adKey) == null) ? placement : ((Ad) a.a().b(this.adKey)).getPlacement();
    }

    /* access modifiers changed from: protected */
    public String getAdHtml() {
        g b = a.a().b(this.adKey);
        if (b == null || !(b instanceof e)) {
            return null;
        }
        return ((e) b).f();
    }

    private void registerBroadcastReceiver(String str) {
        b.a(this.context).a(this.callbackBroadcastReceiver, new IntentFilter(str));
    }

    @Deprecated
    public boolean show() {
        return show((String) null, (AdDisplayListener) null);
    }

    private void setAdMode(AdMode adMode2) {
        this.adMode = adMode2;
    }

    private void setAdPrefs(AdPreferences adPreferences2) {
        this.adPreferences = adPreferences2;
    }

    public void loadAd() {
        loadAd(AdMode.AUTOMATIC, new AdPreferences(), (AdEventListener) null);
    }

    public void loadAd(AdPreferences adPreferences2) {
        loadAd(AdMode.AUTOMATIC, adPreferences2, (AdEventListener) null);
    }

    public void loadAd(AdEventListener adEventListener) {
        loadAd(AdMode.AUTOMATIC, new AdPreferences(), adEventListener);
    }

    public void loadAd(AdPreferences adPreferences2, AdEventListener adEventListener) {
        loadAd(AdMode.AUTOMATIC, adPreferences2, adEventListener);
    }

    public void loadAd(AdMode adMode2) {
        loadAd(adMode2, new AdPreferences(), (AdEventListener) null);
    }

    public void loadAd(AdMode adMode2, AdPreferences adPreferences2) {
        loadAd(adMode2, adPreferences2, (AdEventListener) null);
    }

    public void loadAd(AdMode adMode2, AdEventListener adEventListener) {
        loadAd(adMode2, new AdPreferences(), adEventListener);
    }

    public void loadAd(AdMode adMode2, AdPreferences adPreferences2, AdEventListener adEventListener) {
        setAdMode(adMode2);
        setAdPrefs(adPreferences2);
        try {
            load(adPreferences2, adEventListener);
        } catch (Exception e) {
            com.startapp.android.publish.adsCommon.f.f.a(this.context, d.EXCEPTION, "StartAppAd.loadAd - unexpected Error occurd", e.getMessage(), "");
            if (adEventListener != null) {
                adEventListener.onFailedToReceiveAd(this);
            }
        }
    }

    public boolean showAd() {
        return showAd((String) null, (AdDisplayListener) null);
    }

    public boolean showAd(String str) {
        return showAd(str, (AdDisplayListener) null);
    }

    public boolean showAd(AdDisplayListener adDisplayListener) {
        return showAd((String) null, adDisplayListener);
    }

    public boolean showAd(String str, AdDisplayListener adDisplayListener) {
        try {
            return show(str, adDisplayListener);
        } catch (Exception e) {
            com.startapp.android.publish.adsCommon.f.f.a(this.context, d.EXCEPTION, "StartAppAd.showAd - unexpected Error occurd", e.getMessage(), "");
            setNotDisplayedReason(AdDisplayListener.NotDisplayedReason.INTERNAL_ERROR);
            if (adDisplayListener == null) {
                return false;
            }
            adDisplayListener.adNotDisplayed((Ad) null);
            return false;
        }
    }

    public void setVideoListener(VideoListener videoListener2) {
        this.videoListener = videoListener2;
    }

    public void onResume() {
        if (!isReady()) {
            loadAd();
        }
    }

    public void onBackPressed() {
        if (!showAd("exit_ad")) {
            g.a(TAG, 3, "Could not display StartAppAd onBackPressed");
        }
        m.a().m();
    }

    /* renamed from: com.startapp.android.publish.adsCommon.StartAppAd$3  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f172a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode[] r0 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f172a = r0
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r1 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.FULLPAGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f172a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r1 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.OFFERWALL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f172a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r1 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.OVERLAY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f172a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r1 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.REWARDED_VIDEO     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.StartAppAd.AnonymousClass3.<clinit>():void");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        int i = AnonymousClass3.f172a[this.adMode.ordinal()];
        int i2 = 4;
        if (i == 1) {
            i2 = 1;
        } else if (i == 2) {
            i2 = 2;
        } else if (i == 3) {
            i2 = 3;
        } else if (i != 4) {
            i2 = 0;
        }
        AdPreferences adPreferences2 = this.adPreferences;
        if (adPreferences2 != null) {
            bundle.putSerializable("AdPrefs", adPreferences2);
        }
        bundle.putInt("AdMode", i2);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        int i = bundle.getInt("AdMode");
        this.adMode = AdMode.AUTOMATIC;
        if (i == 1) {
            this.adMode = AdMode.FULLPAGE;
        } else if (i == 2) {
            this.adMode = AdMode.OFFERWALL;
        } else if (i == 3) {
            this.adMode = AdMode.OVERLAY;
        } else if (i == 4) {
            this.adMode = AdMode.REWARDED_VIDEO;
        } else if (i == 5) {
            this.adMode = AdMode.VIDEO;
        }
        Serializable serializable = bundle.getSerializable("AdPrefs");
        if (serializable != null) {
            this.adPreferences = (AdPreferences) serializable;
        }
    }

    public void close() {
        if (this.callbackBroadcastReceiver != null) {
            b.a(this.context).a(this.callbackBroadcastReceiver);
        }
        b.a(this.context).a(new Intent("com.startapp.android.CloseAdActivity"));
    }

    public boolean isReady() {
        g b = a.a().b(this.adKey);
        if (b != null) {
            return b.isReady();
        }
        return false;
    }

    public boolean isNetworkAvailable() {
        return com.startapp.android.publish.adsCommon.Utils.i.a(this.context);
    }

    private boolean isAppOnForeground() {
        try {
            return !b.a().N() || com.startapp.android.publish.adsCommon.Utils.i.e(this.context);
        } catch (Exception unused) {
            return true;
        }
    }

    public static void disableSplash() {
        m.a().j();
    }

    public c loadSplash(AdPreferences adPreferences2, AdEventListener adEventListener) {
        c a2 = a.a().a(this.context, this, adPreferences2, adEventListener);
        this.adKey = a2;
        return a2;
    }

    public static void enableAutoInterstitial() {
        f.a().b();
    }

    public static void disableAutoInterstitial() {
        f.a().c();
    }

    public static void setAutoInterstitialPreferences(AutoInterstitialPreferences autoInterstitialPreferences) {
        f.a().a(autoInterstitialPreferences);
    }

    public static void showSplash(Activity activity, Bundle bundle) {
        showSplash(activity, bundle, new SplashConfig());
    }

    public static void showSplash(Activity activity, Bundle bundle, SplashConfig splashConfig) {
        showSplash(activity, bundle, splashConfig, new AdPreferences());
    }

    public static void showSplash(Activity activity, Bundle bundle, AdPreferences adPreferences2) {
        showSplash(activity, bundle, new SplashConfig(), adPreferences2);
    }

    public static void showSplash(Activity activity, Bundle bundle, SplashConfig splashConfig, AdPreferences adPreferences2) {
        showSplash(activity, bundle, splashConfig, adPreferences2, (SplashHideListener) null);
    }

    public static void showSplash(Activity activity, Bundle bundle, SplashConfig splashConfig, AdPreferences adPreferences2, SplashHideListener splashHideListener) {
        showSplash(activity, bundle, splashConfig, adPreferences2, splashHideListener, true);
    }

    static void showSplash(final Activity activity, Bundle bundle, SplashConfig splashConfig, AdPreferences adPreferences2, final SplashHideListener splashHideListener, boolean z) {
        if (bundle == null && MetaData.getInstance().canShowAd()) {
            try {
                m a2 = m.a();
                if (!a2.k() && z) {
                    a2.d(true);
                }
                a2.c(z);
                if (!z) {
                    if (adPreferences2 == null) {
                        adPreferences2 = new AdPreferences();
                    }
                    adPreferences2.setAs(true);
                }
                splashConfig.setDefaults(activity);
                com.startapp.android.publish.adsCommon.Utils.i.a(activity, true);
                Intent intent = new Intent(activity, com.startapp.android.publish.adsCommon.Utils.i.a((Context) activity, (Class<? extends Activity>) OverlayActivity.class, (Class<? extends Activity>) AppWallActivity.class));
                intent.putExtra("SplashConfig", splashConfig);
                intent.putExtra("AdPreference", adPreferences2);
                intent.putExtra("testMode", testMode);
                intent.putExtra("fullscreen", c.a(activity));
                intent.putExtra("placement", AdPreferences.Placement.INAPP_SPLASH.getIndex());
                intent.addFlags(1140883456);
                activity.startActivity(intent);
                b.a((Context) activity).a(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        com.startapp.android.publish.adsCommon.Utils.i.a(activity, false);
                        SplashHideListener splashHideListener = splashHideListener;
                        if (splashHideListener != null) {
                            splashHideListener.splashHidden();
                        }
                        b.a((Context) activity).a((BroadcastReceiver) this);
                    }
                }, new IntentFilter("com.startapp.android.splashHidden"));
            } catch (Exception e) {
                if (splashHideListener != null) {
                    splashHideListener.splashHidden();
                    com.startapp.android.publish.adsCommon.f.f.a(activity, d.EXCEPTION, "StartAppAd.showSplash - unexpected Error occurd", e.getMessage(), "");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getLauncherName() {
        g b = a.a().b(this.adKey);
        if (b != null) {
            return b.a_();
        }
        return com.startapp.android.publish.adsCommon.Utils.i.c(getContext());
    }

    public Ad.AdState getState() {
        g b = a.a().b(this.adKey);
        if (b != null) {
            return b.getState();
        }
        return Ad.AdState.UN_INITIALIZED;
    }

    public boolean isBelowMinCPM() {
        g b = a.a().b(this.adKey);
        if (b != null) {
            return b.isBelowMinCPM();
        }
        return false;
    }

    public static boolean showAd(Context context) {
        try {
            return new StartAppAd(context).showAd();
        } catch (Exception e) {
            com.startapp.android.publish.adsCommon.f.f.a(context, d.EXCEPTION, "StartAppAd.showAd(one line integration) - unexpected Error occurd", e.getMessage(), "");
            return false;
        }
    }

    public static void onBackPressed(Context context) {
        new StartAppAd(context).onBackPressed();
    }
}
