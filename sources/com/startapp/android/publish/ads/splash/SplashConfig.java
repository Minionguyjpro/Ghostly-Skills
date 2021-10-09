package com.startapp.android.publish.ads.splash;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.AdError;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.common.c.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class SplashConfig implements Serializable {
    private static long DEFAULT_MAX_LOAD = 7500;
    private static final int INT_EMPTY_VALUE = -1;
    private static final String STRING_EMPTY_VALUE = "";
    private static final String VALUE_DEFAULT_HTML_BG_COLOR = "#066CAA";
    private static final String VALUE_DEFAULT_HTML_FONT_COLOR = "ffffff";
    private static final String VALUE_DEFAULT_HTML_LOADING_TYPE = "LoadingDots";
    private static final boolean VALUE_DEFAULT_HTML_SPLASH = true;
    private static final MaxAdDisplayTime VALUE_DEFAULT_MAXADDISPLAY = MaxAdDisplayTime.FOR_EVER;
    private static final long VALUE_DEFAULT_MAXLOAD = DEFAULT_MAX_LOAD;
    private static final MinSplashTime VALUE_DEFAULT_MINSPLASHTIME = MinSplashTime.REGULAR;
    private static final Orientation VALUE_DEFAULT_ORIENTATION = Orientation.AUTO;
    private static final Theme VALUE_DEFAULT_THEME = Theme.OCEAN;
    private static final long serialVersionUID = 1;
    private String appName = "";
    private int customScreen = -1;
    @f(b = MaxAdDisplayTime.class)
    private MaxAdDisplayTime defaultMaxAdDisplayTime = VALUE_DEFAULT_MAXADDISPLAY;
    private Long defaultMaxLoadTime = Long.valueOf(VALUE_DEFAULT_MAXLOAD);
    @f(b = MinSplashTime.class)
    private MinSplashTime defaultMinSplashTime = VALUE_DEFAULT_MINSPLASHTIME;
    @f(b = Orientation.class)
    private Orientation defaultOrientation = VALUE_DEFAULT_ORIENTATION;
    @f(b = Theme.class)
    private Theme defaultTheme = VALUE_DEFAULT_THEME;
    private transient String errMsg = "";
    private boolean forceNative = false;
    private boolean htmlSplash = true;
    private transient Drawable logo = null;
    private byte[] logoByteArray = null;
    private int logoRes = -1;
    private String splashBgColor = VALUE_DEFAULT_HTML_BG_COLOR;
    private String splashFontColor = VALUE_DEFAULT_HTML_FONT_COLOR;
    private String splashLoadingType = VALUE_DEFAULT_HTML_LOADING_TYPE;

    /* compiled from: StartAppSDK */
    public enum MaxAdDisplayTime {
        SHORT(5000),
        LONG(10000),
        FOR_EVER(86400000);
        
        private long index;

        private MaxAdDisplayTime(long j) {
            this.index = j;
        }

        public long getIndex() {
            return this.index;
        }

        public static MaxAdDisplayTime getByIndex(long j) {
            MaxAdDisplayTime maxAdDisplayTime = SHORT;
            MaxAdDisplayTime[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].getIndex() == j) {
                    maxAdDisplayTime = values[i];
                }
            }
            return maxAdDisplayTime;
        }

        public static MaxAdDisplayTime getByName(String str) {
            MaxAdDisplayTime maxAdDisplayTime = FOR_EVER;
            MaxAdDisplayTime[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    maxAdDisplayTime = values[i];
                }
            }
            return maxAdDisplayTime;
        }
    }

    /* compiled from: StartAppSDK */
    public enum MinSplashTime {
        REGULAR(3000),
        SHORT(AdError.SERVER_ERROR_CODE),
        LONG(5000);
        
        private long index;

        private MinSplashTime(int i) {
            this.index = (long) i;
        }

        public long getIndex() {
            return this.index;
        }

        public static MinSplashTime getByIndex(long j) {
            MinSplashTime minSplashTime = SHORT;
            MinSplashTime[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].getIndex() == j) {
                    minSplashTime = values[i];
                }
            }
            return minSplashTime;
        }

        public static MinSplashTime getByName(String str) {
            MinSplashTime minSplashTime = LONG;
            MinSplashTime[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    minSplashTime = values[i];
                }
            }
            return minSplashTime;
        }
    }

    /* compiled from: StartAppSDK */
    public enum Orientation {
        PORTRAIT(1),
        LANDSCAPE(2),
        AUTO(3);
        
        private int index;

        private Orientation(int i) {
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        public static Orientation getByIndex(int i) {
            Orientation orientation = PORTRAIT;
            Orientation[] values = values();
            for (int i2 = 0; i2 < values.length; i2++) {
                if (values[i2].getIndex() == i) {
                    orientation = values[i2];
                }
            }
            return orientation;
        }

        public static Orientation getByName(String str) {
            Orientation orientation = AUTO;
            Orientation[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    orientation = values[i];
                }
            }
            return orientation;
        }
    }

    /* compiled from: StartAppSDK */
    public enum Theme {
        DEEP_BLUE(1),
        SKY(2),
        ASHEN_SKY(3),
        BLAZE(4),
        GLOOMY(5),
        OCEAN(6),
        USER_DEFINED(0);
        
        private int index;

        private Theme(int i) {
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        public static Theme getByIndex(int i) {
            Theme theme = DEEP_BLUE;
            Theme[] values = values();
            for (int i2 = 0; i2 < values.length; i2++) {
                if (values[i2].getIndex() == i) {
                    theme = values[i2];
                }
            }
            return theme;
        }

        public static Theme getByName(String str) {
            Theme theme = DEEP_BLUE;
            Theme[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    theme = values[i];
                }
            }
            return theme;
        }
    }

    public static SplashConfig getDefaultSplashConfig() {
        SplashConfig splashConfig = new SplashConfig();
        splashConfig.setTheme(VALUE_DEFAULT_THEME).setMinSplashTime(VALUE_DEFAULT_MINSPLASHTIME).setMaxLoadAdTimeout(VALUE_DEFAULT_MAXLOAD).setMaxAdDisplayTime(VALUE_DEFAULT_MAXADDISPLAY).setOrientation(VALUE_DEFAULT_ORIENTATION).setLoadingType(VALUE_DEFAULT_HTML_LOADING_TYPE).setAppName("");
        return splashConfig;
    }

    private static void applyDefaultSplashConfig(SplashConfig splashConfig, Context context) {
        SplashConfig defaultSplashConfig = getDefaultSplashConfig();
        if (splashConfig.getTheme() == null) {
            splashConfig.setTheme(defaultSplashConfig.getTheme());
        }
        if (splashConfig.getMinSplashTime() == null) {
            splashConfig.setMinSplashTime(defaultSplashConfig.getMinSplashTime());
        }
        if (splashConfig.getMaxLoadAdTimeout() == null) {
            splashConfig.setMaxLoadAdTimeout(defaultSplashConfig.getMaxLoadAdTimeout().longValue());
        }
        if (splashConfig.getMaxAdDisplayTime() == null) {
            splashConfig.setMaxAdDisplayTime(defaultSplashConfig.getMaxAdDisplayTime());
        }
        if (splashConfig.getOrientation() == null) {
            splashConfig.setOrientation(defaultSplashConfig.getOrientation());
        }
        if (splashConfig.getLoadingType() == null) {
            splashConfig.setLoadingType(defaultSplashConfig.getLoadingType());
        }
        if (splashConfig.getAppName().equals("")) {
            splashConfig.setAppName(c.a(context, "Welcome!"));
        }
    }

    public SplashConfig setTheme(Theme theme) {
        this.defaultTheme = theme;
        return this;
    }

    /* renamed from: com.startapp.android.publish.ads.splash.SplashConfig$1  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.startapp.android.publish.ads.splash.SplashConfig$Theme[] r0 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme = r0
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.DEEP_BLUE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.SKY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.ASHEN_SKY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.BLAZE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.GLOOMY     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.OCEAN     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.USER_DEFINED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.splash.SplashConfig.AnonymousClass1.<clinit>():void");
        }
    }

    private void setSplashColorsByTheme(Theme theme) {
        int i = AnonymousClass1.$SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme[theme.ordinal()];
        String str = "#333333";
        String str2 = VALUE_DEFAULT_HTML_BG_COLOR;
        switch (i) {
            case 1:
                break;
            case 2:
                str2 = "#a3d4e5";
                break;
            case 3:
                str2 = "#E3E3E3";
                break;
            case 4:
                str2 = "#FF6600";
                break;
            case 5:
                str = "#33B5E5";
                str2 = "#2F353F";
                break;
            case 6:
                str = "#063D51";
                str2 = "#237C9A";
                break;
            default:
                str = VALUE_DEFAULT_HTML_FONT_COLOR;
                break;
        }
        str = "#FFFFFF";
        this.splashBgColor = str2;
        this.splashFontColor = str;
    }

    public SplashConfig setCustomScreen(int i) {
        this.customScreen = i;
        return this;
    }

    public SplashConfig setAppName(String str) {
        this.appName = str;
        return this;
    }

    public SplashConfig setLogo(int i) {
        this.logoRes = i;
        return this;
    }

    public SplashConfig setLogo(byte[] bArr) {
        this.logoByteArray = bArr;
        return this;
    }

    private SplashConfig setLogo(Drawable drawable) {
        this.logo = drawable;
        return this;
    }

    /* access modifiers changed from: protected */
    public SplashConfig setMaxLoadAdTimeout(long j) {
        this.defaultMaxLoadTime = Long.valueOf(j);
        return this;
    }

    public SplashConfig setOrientation(Orientation orientation) {
        this.defaultOrientation = orientation;
        return this;
    }

    public SplashConfig setMinSplashTime(MinSplashTime minSplashTime) {
        this.defaultMinSplashTime = minSplashTime;
        return this;
    }

    public SplashConfig setMaxAdDisplayTime(MaxAdDisplayTime maxAdDisplayTime) {
        this.defaultMaxAdDisplayTime = maxAdDisplayTime;
        return this;
    }

    /* access modifiers changed from: protected */
    public SplashConfig setHtmlSplash(boolean z) {
        this.htmlSplash = z;
        return this;
    }

    private void setErrorMsg(String str) {
        this.errMsg = str;
    }

    public int getCustomScreen() {
        return this.customScreen;
    }

    public String getAppName() {
        return this.appName;
    }

    public Drawable getLogo() {
        return this.logo;
    }

    public int getLogoRes() {
        return this.logoRes;
    }

    public byte[] getLogoByteArray() {
        return this.logoByteArray;
    }

    /* access modifiers changed from: protected */
    public Long getMaxLoadAdTimeout() {
        return this.defaultMaxLoadTime;
    }

    public String getErrorMessage() {
        return this.errMsg;
    }

    /* access modifiers changed from: protected */
    public Theme getTheme() {
        return this.defaultTheme;
    }

    public Orientation getOrientation() {
        return this.defaultOrientation;
    }

    public MinSplashTime getMinSplashTime() {
        return this.defaultMinSplashTime;
    }

    public MaxAdDisplayTime getMaxAdDisplayTime() {
        return this.defaultMaxAdDisplayTime;
    }

    public boolean isHtmlSplash() {
        if (this.forceNative) {
            return false;
        }
        return this.htmlSplash;
    }

    public String getBgColor() {
        return this.splashBgColor;
    }

    public String getFontColor() {
        return this.splashFontColor;
    }

    public String getLoadingType() {
        return this.splashLoadingType;
    }

    public SplashConfig setLoadingType(String str) {
        this.splashLoadingType = str;
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean validate(Context context) {
        if (AnonymousClass1.$SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme[getTheme().ordinal()] != 7) {
            if (getAppName().equals("")) {
                setAppName(c.a(context, "Welcome!"));
            }
            if (getLogo() == null && getLogoByteArray() == null) {
                if (getLogoRes() == -1) {
                    setLogo(context.getApplicationInfo().icon);
                    setLogo(context.getResources().getDrawable(context.getApplicationInfo().icon));
                } else {
                    setLogo(context.getResources().getDrawable(getLogoRes()));
                }
            }
        } else if (getCustomScreen() == -1) {
            setErrorMsg("StartApp: Exception getting custom screen resource id, make sure it is set");
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public View getLayout(Context context) {
        if (AnonymousClass1.$SwitchMap$com$startapp$android$publish$ads$splash$SplashConfig$Theme[getTheme().ordinal()] != 7) {
            return i.a(context, this);
        }
        try {
            return ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(getCustomScreen(), (ViewGroup) null);
        } catch (Resources.NotFoundException unused) {
            throw new Resources.NotFoundException("StartApp: Can't find Custom layout resource");
        } catch (InflateException unused2) {
            throw new InflateException("StartApp: Can't inflate layout in Custom mode, Are you sure layout resource is valid?");
        } catch (Exception e) {
            com.startapp.android.publish.adsCommon.f.f.a(context, d.EXCEPTION, "SplashConfig.getLayout - System service failed", e.getMessage(), "");
            return null;
        }
    }

    public void setDefaults(Context context) {
        SplashConfig a2 = f.b().a();
        if (a2 == null) {
            a2 = getDefaultSplashConfig();
        } else {
            setHtmlSplash(a2.isHtmlSplash());
        }
        applyDefaultSplashConfig(a2, context);
        if (getMaxAdDisplayTime() == null) {
            setMaxAdDisplayTime(a2.getMaxAdDisplayTime());
        }
        if (getMaxLoadAdTimeout() == null) {
            setMaxLoadAdTimeout(a2.getMaxLoadAdTimeout().longValue());
        }
        if (getMinSplashTime() == null) {
            setMinSplashTime(a2.getMinSplashTime());
        }
        if (getOrientation() == null) {
            setOrientation(a2.getOrientation());
        }
        if (getTheme() == null) {
            setTheme(a2.getTheme());
        }
        if (getLogoRes() == -1) {
            setLogo(context.getApplicationInfo().icon);
        }
        if (getAppName() == "") {
            setAppName(a2.getAppName());
        }
    }

    /* access modifiers changed from: protected */
    public void initSplashLogo(Activity activity) {
        if (getLogo() == null && getLogoRes() == -1 && getLogoByteArray() != null) {
            byte[] logoByteArray2 = getLogoByteArray();
            setLogo((Drawable) new BitmapDrawable(activity.getResources(), BitmapFactory.decodeByteArray(logoByteArray2, 0, logoByteArray2.length)));
        }
    }

    /* access modifiers changed from: protected */
    public d initSplashHtml(Activity activity) {
        setSplashColorsByTheme(getTheme());
        d dVar = new d(activity);
        dVar.b(this);
        dVar.a(this);
        return dVar;
    }

    /* access modifiers changed from: package-private */
    public boolean isUserDefinedSplash() {
        return getTheme() == Theme.USER_DEFINED || getCustomScreen() != -1;
    }
}
