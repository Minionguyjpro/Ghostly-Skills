package com.appnext.ads.interstitial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.ECPM;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnECPMLoaded;
import com.appnext.core.d;
import com.appnext.core.f;
import com.appnext.core.j;
import com.appnext.core.p;
import com.appnext.core.webview.AppnextWebView;
import com.appnext.core.webview.a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Interstitial extends Ad {
    private static final String JS_URL = "https://cdn.appnext.com/tools/sdk/interstitial/v75/script.min.js";
    protected static final String TID = "301";
    public static final String TYPE_MANAGED = "managed";
    public static final String TYPE_STATIC = "static";
    public static final String TYPE_VIDEO = "video";
    protected static final String VID = "2.5.1.472";
    protected static Interstitial currentAd;
    private boolean autoPlay;
    private String buttonColor;
    /* access modifiers changed from: private */
    public boolean calledShow;
    private Boolean canClose;
    /* access modifiers changed from: private */
    public boolean configLoaded;
    private String creativeType;
    private String language;
    private boolean setAutoPlay;
    private boolean setCanClose;
    private String skipText;
    private String titleText;
    /* access modifiers changed from: private */
    public OnAdError userOnAdError;

    public String getAUID() {
        return "600";
    }

    /* access modifiers changed from: protected */
    public String getPageUrl() {
        return JS_URL;
    }

    public String getTID() {
        return TID;
    }

    public String getVID() {
        return "2.5.1.472";
    }

    @Deprecated
    public void setOrientation(String str) {
    }

    public Interstitial(Context context, String str) {
        super(context, str);
        this.creativeType = TYPE_MANAGED;
        this.titleText = "";
        this.skipText = "";
        this.autoPlay = true;
        this.setAutoPlay = false;
        this.setCanClose = false;
        this.configLoaded = false;
        this.calledShow = false;
        this.buttonColor = "";
        this.language = "";
        init();
    }

    public Interstitial(Context context, String str, InterstitialConfig interstitialConfig) {
        super(context, str);
        this.creativeType = TYPE_MANAGED;
        this.titleText = "";
        this.skipText = "";
        boolean z = true;
        this.autoPlay = true;
        this.setAutoPlay = false;
        this.setCanClose = false;
        this.configLoaded = false;
        this.calledShow = false;
        this.buttonColor = "";
        this.language = "";
        init();
        if (interstitialConfig != null) {
            setPostback(interstitialConfig.getPostback());
            setCategories(interstitialConfig.getCategories());
            setButtonColor(interstitialConfig.getButtonColor());
            if (interstitialConfig.backButtonCanClose != null) {
                setBackButtonCanClose(interstitialConfig.isBackButtonCanClose());
            }
            setSkipText(interstitialConfig.getSkipText());
            if (interstitialConfig.autoPlay != null) {
                setAutoPlay(interstitialConfig.isAutoPlay());
            }
            setCreativeType(interstitialConfig.getCreativeType());
            setOrientation(interstitialConfig.getOrientation());
            if (interstitialConfig.mute == null ? false : z) {
                setMute(interstitialConfig.getMute());
            }
            setMinVideoLength(interstitialConfig.getMinVideoLength());
            setMaxVideoLength(interstitialConfig.getMaxVideoLength());
            setLanguage(interstitialConfig.getLanguage());
        }
    }

    protected Interstitial(Interstitial interstitial) {
        super(interstitial);
        this.creativeType = TYPE_MANAGED;
        this.titleText = "";
        this.skipText = "";
        this.autoPlay = true;
        this.setAutoPlay = false;
        this.setCanClose = false;
        this.configLoaded = false;
        this.calledShow = false;
        this.buttonColor = "";
        this.language = "";
        this.creativeType = interstitial.creativeType;
        this.titleText = interstitial.titleText;
        this.skipText = interstitial.skipText;
        this.autoPlay = interstitial.autoPlay;
        this.setAutoPlay = interstitial.setAutoPlay;
        this.setCanClose = interstitial.setCanClose;
        this.configLoaded = interstitial.configLoaded;
        this.calledShow = interstitial.calledShow;
        this.canClose = interstitial.canClose;
        this.buttonColor = interstitial.buttonColor;
        this.language = interstitial.language;
        this.userOnAdError = interstitial.userOnAdError;
    }

    private void init() {
        loadConfig();
        AppnextWebView.u(this.context).a(getPageUrl(), (AppnextWebView.c) null);
        super.setOnAdErrorCallback(new OnAdError() {
            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void adError(java.lang.String r11) {
                /*
                    r10 = this;
                    int r0 = r11.hashCode()
                    switch(r0) {
                        case -2026653947: goto L_0x0044;
                        case -1958363695: goto L_0x003a;
                        case -1477010874: goto L_0x0030;
                        case -507110949: goto L_0x0026;
                        case 297538105: goto L_0x001c;
                        case 350741825: goto L_0x0012;
                        case 844170097: goto L_0x0008;
                        default: goto L_0x0007;
                    }
                L_0x0007:
                    goto L_0x004e
                L_0x0008:
                    java.lang.String r0 = "Too Slow Connection"
                    boolean r0 = r11.equals(r0)
                    if (r0 == 0) goto L_0x004e
                    r0 = 4
                    goto L_0x004f
                L_0x0012:
                    java.lang.String r0 = "Timeout"
                    boolean r0 = r11.equals(r0)
                    if (r0 == 0) goto L_0x004e
                    r0 = 5
                    goto L_0x004f
                L_0x001c:
                    java.lang.String r0 = "Ad Not Ready"
                    boolean r0 = r11.equals(r0)
                    if (r0 == 0) goto L_0x004e
                    r0 = 6
                    goto L_0x004f
                L_0x0026:
                    java.lang.String r0 = "No market installed on the device"
                    boolean r0 = r11.equals(r0)
                    if (r0 == 0) goto L_0x004e
                    r0 = 3
                    goto L_0x004f
                L_0x0030:
                    java.lang.String r0 = "Connection Error"
                    boolean r0 = r11.equals(r0)
                    if (r0 == 0) goto L_0x004e
                    r0 = 0
                    goto L_0x004f
                L_0x003a:
                    java.lang.String r0 = "No Ads"
                    boolean r0 = r11.equals(r0)
                    if (r0 == 0) goto L_0x004e
                    r0 = 2
                    goto L_0x004f
                L_0x0044:
                    java.lang.String r0 = "Internal error"
                    boolean r0 = r11.equals(r0)
                    if (r0 == 0) goto L_0x004e
                    r0 = 1
                    goto L_0x004f
                L_0x004e:
                    r0 = -1
                L_0x004f:
                    switch(r0) {
                        case 0: goto L_0x0068;
                        case 1: goto L_0x0065;
                        case 2: goto L_0x0062;
                        case 3: goto L_0x005f;
                        case 4: goto L_0x005c;
                        case 5: goto L_0x0059;
                        case 6: goto L_0x0056;
                        default: goto L_0x0052;
                    }
                L_0x0052:
                    java.lang.String r0 = ""
                L_0x0054:
                    r6 = r0
                    goto L_0x006b
                L_0x0056:
                    java.lang.String r0 = "error_ad_not_ready"
                    goto L_0x0054
                L_0x0059:
                    java.lang.String r0 = "error_timeout"
                    goto L_0x0054
                L_0x005c:
                    java.lang.String r0 = "error_slow_connection"
                    goto L_0x0054
                L_0x005f:
                    java.lang.String r0 = "error_no_market"
                    goto L_0x0054
                L_0x0062:
                    java.lang.String r0 = "error_no_ads"
                    goto L_0x0054
                L_0x0065:
                    java.lang.String r0 = "error_internal_error"
                    goto L_0x0054
                L_0x0068:
                    java.lang.String r0 = "error_connection_error"
                    goto L_0x0054
                L_0x006b:
                    com.appnext.ads.interstitial.Interstitial r0 = com.appnext.ads.interstitial.Interstitial.this
                    java.lang.String r1 = r0.getTID()
                    com.appnext.ads.interstitial.Interstitial r0 = com.appnext.ads.interstitial.Interstitial.this
                    java.lang.String r2 = r0.getVID()
                    com.appnext.ads.interstitial.Interstitial r0 = com.appnext.ads.interstitial.Interstitial.this
                    java.lang.String r3 = r0.getAUID()
                    com.appnext.ads.interstitial.Interstitial r0 = com.appnext.ads.interstitial.Interstitial.this
                    java.lang.String r4 = r0.getPlacementID()
                    com.appnext.ads.interstitial.Interstitial r0 = com.appnext.ads.interstitial.Interstitial.this
                    java.lang.String r5 = r0.getSessionId()
                    java.lang.String r7 = "current_interstitial"
                    java.lang.String r8 = ""
                    java.lang.String r9 = ""
                    com.appnext.core.f.a((java.lang.String) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8, (java.lang.String) r9)
                    com.appnext.ads.interstitial.Interstitial r0 = com.appnext.ads.interstitial.Interstitial.this
                    com.appnext.core.callbacks.OnAdError r0 = r0.userOnAdError
                    if (r0 == 0) goto L_0x00a3
                    com.appnext.ads.interstitial.Interstitial r0 = com.appnext.ads.interstitial.Interstitial.this
                    com.appnext.core.callbacks.OnAdError r0 = r0.userOnAdError
                    r0.adError(r11)
                L_0x00a3:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.interstitial.Interstitial.AnonymousClass1.adError(java.lang.String):void");
            }
        });
    }

    public void loadAd() {
        if (getPlacementID().equals("")) {
            throw new IllegalArgumentException("Placement ID cannot be empty");
        } else if (f.a(this.context, "android.permission.INTERNET")) {
            new Thread(new Runnable() {
                public final void run() {
                    try {
                        if (Interstitial.this.context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
                            f.a("http://www.appnext.com/myid.html", (HashMap<String, String>) null);
                        } else {
                            NetworkInfo activeNetworkInfo = ((ConnectivityManager) Interstitial.this.context.getSystemService("connectivity")).getActiveNetworkInfo();
                            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                                throw new IOException();
                            }
                        }
                        Interstitial.this.getConfig().a(Interstitial.this.context, (p.a) new p.a() {
                            public final void b(HashMap<String, Object> hashMap) {
                                Interstitial.this.load();
                            }

                            public final void error(String str) {
                                Interstitial.this.load();
                            }
                        });
                    } catch (Throwable unused) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public final void run() {
                                if (Interstitial.this.getOnAdErrorCallback() != null) {
                                    Interstitial.this.getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
                                }
                            }
                        });
                    }
                }
            }).start();
        } else if (getOnAdErrorCallback() != null) {
            getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
        }
    }

    /* access modifiers changed from: private */
    public void load() {
        a.G().a(this.context, this, getPlacementID(), new d.a() {
            public final <T> void a(T t) {
                AppnextAd appnextAd;
                try {
                    appnextAd = a.G().a(Interstitial.this.context, (ArrayList<AppnextAd>) (ArrayList) t, Interstitial.this.getCreative(), (Ad) Interstitial.this);
                } catch (Throwable unused) {
                    if (Interstitial.this.getOnAdErrorCallback() != null) {
                        Interstitial.this.getOnAdErrorCallback().adError(AppnextError.NO_ADS);
                    }
                    appnextAd = null;
                }
                if (appnextAd != null) {
                    if (Interstitial.this.getOnAdLoadedCallback() != null) {
                        Interstitial.this.getOnAdLoadedCallback().adLoaded(appnextAd.getBannerID(), appnextAd.getCreativeType());
                    }
                } else if (Interstitial.this.getOnAdErrorCallback() != null) {
                    Interstitial.this.getOnAdErrorCallback().adError(AppnextError.NO_ADS);
                }
            }

            public final void error(String str) {
                if (Interstitial.this.getOnAdErrorCallback() != null) {
                    Interstitial.this.getOnAdErrorCallback().adError(str);
                }
            }
        }, getCreative());
    }

    public void showAd() {
        if (getPlacementID().equals("")) {
            throw new IllegalArgumentException("Placement ID cannot be empty");
        } else if (f.a(this.context, "android.permission.INTERNET")) {
            int Z = f.Z(getConfig().get("min_internet_connection"));
            int Z2 = f.Z(f.o(this.context));
            if (Z2 == -1) {
                if (getOnAdErrorCallback() != null) {
                    getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
                }
            } else if (Z2 >= Z) {
                currentAd = this;
                if (a.G().d(this)) {
                    startInterstitialActivity();
                    return;
                }
                a.G().a(this.context, this, getPlacementID(), new d.a() {
                    public final <T> void a(T t) {
                        Interstitial.this.startInterstitialActivity();
                    }

                    public final void error(final String str) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public final void run() {
                                if (Interstitial.this.getOnAdErrorCallback() != null) {
                                    Interstitial.this.getOnAdErrorCallback().adError(str);
                                }
                            }
                        });
                    }
                }, getCreative());
            } else if (getOnAdErrorCallback() != null) {
                getOnAdErrorCallback().adError(AppnextError.SLOW_CONNECTION);
            }
        } else if (getOnAdErrorCallback() != null) {
            getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
        }
    }

    /* access modifiers changed from: private */
    public void startInterstitialActivity() {
        Intent activityIntent = getActivityIntent();
        if (activityIntent != null) {
            this.context.startActivity(activityIntent);
            return;
        }
        throw new IllegalArgumentException("null intent");
    }

    public void getECPM(final OnECPMLoaded onECPMLoaded) {
        if (onECPMLoaded != null) {
            a.G().a(this.context, (Ad) this, getPlacementID(), (d.a) new d.a() {
                public final <T> void a(T t) {
                    AppnextAd a2 = a.G().a(Interstitial.this.context, (ArrayList<AppnextAd>) (ArrayList) t, Interstitial.this.getCreative(), (Ad) Interstitial.this);
                    if (a2 != null) {
                        onECPMLoaded.ecpm(new ECPM(a2.getECPM(), a2.getPPR(), a2.getBannerID()));
                    } else {
                        onECPMLoaded.error(AppnextError.NO_ADS);
                    }
                }

                public final void error(String str) {
                    onECPMLoaded.error(str);
                }
            }, false);
            return;
        }
        throw new IllegalArgumentException("Callback cannot be null");
    }

    /* access modifiers changed from: private */
    public String getCreative() {
        int Z = f.Z(getConfig().get("min_internet_connection"));
        int Z2 = f.Z(getConfig().get("min_internet_connection_video"));
        int Z3 = f.Z(f.o(this.context));
        if (Z3 < Z || Z3 >= Z2) {
            return getCreativeType();
        }
        return "static";
    }

    /* access modifiers changed from: protected */
    public Intent getActivityIntent() {
        Intent intent = new Intent(this.context, InterstitialActivity.class);
        intent.setFlags(268435456);
        intent.addFlags(67108864);
        intent.putExtra("id", getPlacementID());
        if (this.setAutoPlay) {
            intent.putExtra("auto_play", this.autoPlay);
        }
        if (this.setCanClose) {
            intent.putExtra("can_close", isBackButtonCanClose());
        }
        if (this.setMute) {
            intent.putExtra("mute", getMute());
        }
        intent.putExtra("cat", getCategories());
        intent.putExtra("pbk", getPostback());
        intent.putExtra("b_color", getButtonColor());
        intent.putExtra("skip_title", getSkipText());
        intent.putExtra("creative", getCreative());
        return intent;
    }

    public boolean isAdLoaded() {
        return !getPlacementID().equals("") && a.G().d(this);
    }

    /* access modifiers changed from: protected */
    public p getConfig() {
        return c.K();
    }

    private void loadConfig() {
        getConfig().q("tid", getTID());
        getConfig().a(this.context, (p.a) new p.a() {
            public final void b(HashMap<String, Object> hashMap) {
                boolean unused = Interstitial.this.configLoaded = true;
                j.bj().b(Integer.parseInt(Interstitial.this.getConfig().get("banner_expiration_time")));
                if (Interstitial.this.calledShow) {
                    Interstitial.this.showAd();
                }
            }

            public final void error(String str) {
                boolean unused = Interstitial.this.configLoaded = true;
                j.bj().b(Integer.parseInt(Interstitial.this.getConfig().get("banner_expiration_time")));
                if (Interstitial.this.calledShow) {
                    Interstitial.this.showAd();
                }
            }
        });
    }

    public void setOnAdErrorCallback(OnAdError onAdError) {
        this.userOnAdError = onAdError;
    }

    public OnAdError getOnAdErrorCallback() {
        return super.getOnAdErrorCallback();
    }

    public void setCreativeType(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Wrong creative type");
        } else if (str.equals(TYPE_MANAGED) || str.equals("static") || str.equals("video")) {
            this.creativeType = str;
        } else {
            throw new IllegalArgumentException("Wrong creative type");
        }
    }

    public String getCreativeType() {
        return this.creativeType;
    }

    @Deprecated
    public void setBackButtonCanClose(boolean z) {
        this.setCanClose = true;
        this.canClose = Boolean.valueOf(z);
    }

    @Deprecated
    public boolean isBackButtonCanClose() {
        Boolean bool = this.canClose;
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean isAutoPlay() {
        return this.autoPlay;
    }

    public void setAutoPlay(boolean z) {
        this.setAutoPlay = true;
        this.autoPlay = z;
    }

    public void setSkipText(String str) {
        if (str == null) {
            str = "";
        }
        this.skipText = str;
    }

    public String getSkipText() {
        return this.skipText;
    }

    public String getButtonColor() {
        return this.buttonColor;
    }

    public void setButtonColor(String str) {
        if (str == null || str.equals("")) {
            this.buttonColor = "";
            return;
        }
        if (!str.startsWith("#")) {
            str = "#" + str;
        }
        try {
            Color.parseColor(str);
            this.buttonColor = str;
        } catch (Throwable unused) {
            throw new IllegalArgumentException("Unknown color");
        }
    }

    private boolean hasVideo(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrl().equals("") || !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrl30Sec().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    public void setParams(String str, String str2) {
        getConfig().s(str, str2);
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    /* access modifiers changed from: protected */
    public String getSessionId() {
        return super.getSessionId();
    }

    /* access modifiers changed from: protected */
    public a getFallback() {
        return new b();
    }

    public void destroy() {
        super.destroy();
        currentAd = null;
    }
}
