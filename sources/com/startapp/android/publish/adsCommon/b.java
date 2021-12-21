package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.mopub.mobileads.resource.DrawableConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.a.e;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.common.metaData.MetaDataStyle;
import com.startapp.common.c.f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class b implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public static final Integer f217a = 18;
    public static final Integer b = -1;
    public static final Set<String> c = new HashSet(Arrays.asList(new String[]{"BOLD"}));
    public static final Integer d = Integer.valueOf(DrawableConstants.CtaButton.BACKGROUND_COLOR);
    public static final Integer e = -14803426;
    public static final Integer f = -1;
    private static transient Object g = new Object();
    private static transient b h = new b();
    private static final long serialVersionUID = 1;
    private String acMetadataUpdateVersion = AdsConstants.h;
    @f(a = true)
    private e adRules = new e();
    private boolean appPresence = true;
    private boolean autoInterstitialEnabled = true;
    private Integer backgroundGradientBottom = -14606047;
    private Integer backgroundGradientTop = -14606047;
    private int defaultActivitiesBetweenAds = 1;
    private int defaultSecondsBetweenAds = 0;
    private boolean disableInAppStore = false;
    private boolean disableReturnAd = false;
    private boolean disableSplashAd = false;
    private boolean disableTwoClicks = false;
    private boolean enableForceExternalBrowser = true;
    private boolean enableSmartRedirect = true;
    private boolean enforceForeground = false;
    private int forceExternalBrowserDaysInterval = 7;
    private Integer fullpageOfferWallProbability = 100;
    private Integer fullpageOverlayProbability = 0;
    private Integer homeProbability3D = 80;
    private Integer itemDescriptionTextColor = MetaDataStyle.DEFAULT_ITEM_DESC_TEXT_COLOR;
    @f(b = HashSet.class)
    private Set<String> itemDescriptionTextDecoration = MetaDataStyle.DEFAULT_ITEM_DESC_TEXT_DECORATION;
    private Integer itemDescriptionTextSize = MetaDataStyle.DEFAULT_ITEM_DESC_TEXT_SIZE;
    private Integer itemGradientBottom = Integer.valueOf(MetaDataStyle.DEFAULT_ITEM_BOTTOM);
    private Integer itemGradientTop = Integer.valueOf(MetaDataStyle.DEFAULT_ITEM_TOP);
    private Integer itemTitleTextColor = MetaDataStyle.DEFAULT_ITEM_TITLE_TEXT_COLOR;
    @f(b = HashSet.class)
    private Set<String> itemTitleTextDecoration = MetaDataStyle.DEFAULT_ITEM_TITLE_TEXT_DECORATION;
    private Integer itemTitleTextSize = MetaDataStyle.DEFAULT_ITEM_TITLE_TEXT_SIZE;
    private Integer maxAds = 10;
    private Integer poweredByBackgroundColor = e;
    private Integer poweredByTextColor = f;
    private Integer probability3D = 0;
    private long returnAdMinBackgroundTime = 300;
    private long smartRedirectLoadedTimeout = 1000;
    private int smartRedirectTimeout = 5;
    @f(b = HashMap.class, c = MetaDataStyle.class)
    private HashMap<String, MetaDataStyle> templates = new HashMap<>();
    private Integer titleBackgroundColor = -14803426;
    private String titleContent = "Recommended for you";
    private Integer titleLineColor = d;
    private Integer titleTextColor = b;
    @f(b = HashSet.class)
    private Set<String> titleTextDecoration = c;
    private Integer titleTextSize = f217a;
    @f(a = true)
    private n video = new n();

    public static b a() {
        return h;
    }

    public static void a(Context context) {
        b bVar = (b) com.startapp.common.a.e.a(context, "StartappAdsMetadata", b.class);
        b bVar2 = new b();
        if (bVar != null) {
            boolean a2 = i.a(bVar, bVar2);
            if (!bVar.O() && a2) {
                com.startapp.android.publish.adsCommon.f.f.a(context, d.METADATA_NULL, "AdsCommonMetaData", "", "");
            }
            bVar.P();
            h = bVar;
            return;
        }
        h = bVar2;
    }

    private boolean O() {
        return !AdsConstants.h.equals(this.acMetadataUpdateVersion);
    }

    private void P() {
        this.adRules.b();
    }

    public int b() {
        return this.fullpageOfferWallProbability.intValue();
    }

    public int c() {
        return this.fullpageOverlayProbability.intValue();
    }

    public int d() {
        return this.probability3D.intValue();
    }

    public int e() {
        return this.backgroundGradientTop.intValue();
    }

    public MetaDataStyle a(String str) {
        return this.templates.get(str);
    }

    public int f() {
        return this.backgroundGradientBottom.intValue();
    }

    public int g() {
        return this.maxAds.intValue();
    }

    public Integer h() {
        return this.titleBackgroundColor;
    }

    public String i() {
        return this.titleContent;
    }

    public Integer j() {
        return this.titleTextSize;
    }

    public Integer k() {
        return this.titleTextColor;
    }

    public Set<String> l() {
        return this.titleTextDecoration;
    }

    public Integer m() {
        return this.titleLineColor;
    }

    public int n() {
        return this.itemGradientTop.intValue();
    }

    public int o() {
        return this.itemGradientBottom.intValue();
    }

    public Integer p() {
        return this.itemTitleTextSize;
    }

    public Integer q() {
        return this.itemTitleTextColor;
    }

    public Set<String> r() {
        return this.itemTitleTextDecoration;
    }

    public Integer s() {
        return this.itemDescriptionTextSize;
    }

    public Integer t() {
        return this.itemDescriptionTextColor;
    }

    public Set<String> u() {
        return this.itemDescriptionTextDecoration;
    }

    public Integer v() {
        return this.poweredByBackgroundColor;
    }

    public Integer w() {
        return this.poweredByTextColor;
    }

    public long x() {
        return TimeUnit.SECONDS.toMillis(this.returnAdMinBackgroundTime);
    }

    public boolean y() {
        return this.disableReturnAd;
    }

    public boolean z() {
        return this.disableSplashAd;
    }

    public long A() {
        return TimeUnit.SECONDS.toMillis((long) this.smartRedirectTimeout);
    }

    public long B() {
        return this.smartRedirectLoadedTimeout;
    }

    public boolean C() {
        return this.enableSmartRedirect;
    }

    public boolean D() {
        return this.disableTwoClicks;
    }

    public boolean E() {
        return this.appPresence;
    }

    public e F() {
        return this.adRules;
    }

    public boolean G() {
        return this.disableInAppStore;
    }

    public n H() {
        return this.video;
    }

    public boolean I() {
        return this.autoInterstitialEnabled;
    }

    public int J() {
        return this.defaultActivitiesBetweenAds;
    }

    public int K() {
        return this.defaultSecondsBetweenAds;
    }

    public int L() {
        return this.forceExternalBrowserDaysInterval;
    }

    public boolean M() {
        return this.enableForceExternalBrowser;
    }

    public boolean N() {
        return this.enforceForeground;
    }

    public static void a(Context context, b bVar) {
        synchronized (g) {
            bVar.acMetadataUpdateVersion = AdsConstants.h;
            h = bVar;
            com.startapp.common.a.e.a(context, "StartappAdsMetadata", (Serializable) bVar);
        }
    }
}
