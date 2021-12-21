package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.b.a.a.a.b;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adinformation.AdInformationPositions;
import com.startapp.android.publish.adsCommon.b.a;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a.g;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public abstract class e extends Ad {

    /* renamed from: a  reason: collision with root package name */
    protected static String f233a = null;
    private static final long serialVersionUID = 1;
    private String adId = null;
    private List<a> apps;
    private String[] closingUrl = {""};
    private Long delayImpressionInSeconds;
    private int height;
    private String htmlUuid = "";
    public boolean[] inAppBrowserEnabled = {true};
    private boolean isMraidAd = false;
    private int orientation = 0;
    private String[] packageNames = {""};
    private Boolean[] sendRedirectHops = null;
    public boolean[] smartRedirect = {false};
    private String[] trackingClickUrls = {""};
    public String[] trackingUrls = {""};
    private int width;

    public String f() {
        return com.startapp.android.publish.cache.a.a().b(this.htmlUuid);
    }

    public String g() {
        return this.htmlUuid;
    }

    public void b(int i) {
        this.width = i;
    }

    public int h() {
        return this.width;
    }

    public void c(int i) {
        this.height = i;
    }

    public void c(String str) {
        this.closingUrl = str.split(",");
    }

    public String[] i() {
        return this.closingUrl;
    }

    public int j() {
        return this.height;
    }

    public void a(int i, int i2) {
        b(i);
        c(i2);
    }

    public e(Context context, AdPreferences.Placement placement) {
        super(context, placement);
        if (f233a == null) {
            a();
        }
    }

    private void a() {
        f233a = i.c(getContext());
    }

    private String f(String str) {
        try {
            return b.a(com.startapp.android.publish.omsdk.b.a(), str);
        } catch (Exception e) {
            g.a(6, e.getMessage());
            f.a(this.context, d.EXCEPTION, "OMSDK: Failed to inject js to html ad.", e.getMessage(), "");
            return str;
        }
    }

    public void b(String str) {
        if (MetaData.getInstance().isOmsdkEnabled()) {
            str = f(str);
        }
        if (i.a(512)) {
            this.htmlUuid = com.startapp.android.publish.cache.a.a().a(str);
        }
        String a2 = a(str, "@smartRedirect@");
        if (a2 != null) {
            i(a2);
        }
        String a3 = a(str, "@trackingClickUrl@");
        if (a3 != null) {
            k(a3);
        }
        String a4 = a(str, "@closeUrl@");
        if (a4 != null) {
            c(a4);
        }
        String a5 = a(str, "@tracking@");
        if (a5 != null) {
            j(a5);
        }
        String a6 = a(str, "@packageName@");
        if (a6 != null) {
            l(a6);
        }
        String a7 = a(str, "@startappBrowserEnabled@");
        if (a7 != null) {
            h(a7);
        }
        String a8 = a(str, "@orientation@");
        if (a8 != null && i.a(8)) {
            a(SplashConfig.Orientation.getByName(a8));
        }
        String a9 = a(str, "@adInfoEnable@");
        if (a9 != null) {
            m(a9);
        }
        String a10 = a(str, "@adInfoPosition@");
        if (a10 != null) {
            n(a10);
        }
        String a11 = a(str, "@ttl@");
        if (a11 != null) {
            d(a11);
        }
        String a12 = a(str, "@belowMinCPM@");
        if (a12 != null) {
            g(a12);
        }
        String a13 = a(str, "@delayImpressionInSeconds@");
        if (a13 != null) {
            o(a13);
        }
        String a14 = a(str, "@sendRedirectHops@");
        if (a14 != null) {
            e(a14);
        }
        if (this.smartRedirect.length < this.trackingUrls.length) {
            g.a(6, "Error in smartRedirect array in HTML");
            boolean[] zArr = new boolean[this.trackingUrls.length];
            int i = 0;
            while (true) {
                boolean[] zArr2 = this.smartRedirect;
                if (i >= zArr2.length) {
                    break;
                }
                zArr[i] = zArr2[i];
                i++;
            }
            while (i < this.trackingUrls.length) {
                zArr[i] = false;
                i++;
            }
            this.smartRedirect = zArr;
        }
        b(com.startapp.android.publish.adsCommon.g.d.a.b(str));
    }

    private void g(String str) {
        if (Arrays.asList(str.split(",")).contains("false")) {
            this.belowMinCPM = false;
        } else {
            this.belowMinCPM = true;
        }
    }

    private void h(String str) {
        String[] split = str.split(",");
        this.inAppBrowserEnabled = new boolean[split.length];
        for (int i = 0; i < split.length; i++) {
            if (split[i].compareTo("false") == 0) {
                this.inAppBrowserEnabled[i] = false;
            } else {
                this.inAppBrowserEnabled[i] = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public String a(String str, String str2) {
        return i.a(str, str2, str2);
    }

    /* access modifiers changed from: protected */
    public void a(SplashConfig.Orientation orientation2) {
        this.orientation = 0;
        boolean a2 = i.a(8);
        if (orientation2 == null) {
            return;
        }
        if (a2 && orientation2.equals(SplashConfig.Orientation.PORTRAIT)) {
            this.orientation = 1;
        } else if (a2 && orientation2.equals(SplashConfig.Orientation.LANDSCAPE)) {
            this.orientation = 2;
        }
    }

    private void i(String str) {
        String[] split = str.split(",");
        this.smartRedirect = new boolean[split.length];
        for (int i = 0; i < split.length; i++) {
            if (split[i].compareTo("true") == 0) {
                this.smartRedirect[i] = true;
            } else {
                this.smartRedirect[i] = false;
            }
        }
    }

    public boolean d(int i) {
        if (i < 0) {
            return false;
        }
        boolean[] zArr = this.smartRedirect;
        if (i >= zArr.length) {
            return false;
        }
        return zArr[i];
    }

    public boolean[] k() {
        return this.inAppBrowserEnabled;
    }

    public boolean e(int i) {
        boolean[] zArr = this.inAppBrowserEnabled;
        if (zArr == null || i < 0 || i >= zArr.length) {
            return true;
        }
        return zArr[i];
    }

    private void j(String str) {
        this.trackingUrls = str.split(",");
    }

    public String[] l() {
        return this.trackingUrls;
    }

    public String[] m() {
        return this.trackingClickUrls;
    }

    private void k(String str) {
        this.trackingClickUrls = str.split(",");
    }

    public int n() {
        return this.orientation;
    }

    private void l(String str) {
        this.packageNames = str.split(",");
    }

    public String[] o() {
        return this.packageNames;
    }

    public void a(List<a> list) {
        this.apps = list;
    }

    private void m(String str) {
        getAdInfoOverride().a(Boolean.parseBoolean(str));
    }

    private void n(String str) {
        getAdInfoOverride().a(AdInformationPositions.Position.getByName(str));
    }

    /* access modifiers changed from: protected */
    public String a_() {
        return f233a;
    }

    public void d(String str) {
        Long l = null;
        for (String str2 : str.split(",")) {
            if (!str2.equals("")) {
                try {
                    long parseLong = Long.parseLong(str2);
                    if (parseLong > 0 && (l == null || parseLong < l.longValue())) {
                        l = Long.valueOf(parseLong);
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        if (l != null) {
            this.adCacheTtl = Long.valueOf(TimeUnit.SECONDS.toMillis(l.longValue()));
        }
    }

    public Long p() {
        return this.delayImpressionInSeconds;
    }

    private void o(String str) {
        if (str != null && !str.equals("")) {
            try {
                this.delayImpressionInSeconds = Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException unused) {
            }
        }
    }

    public Boolean[] q() {
        return this.sendRedirectHops;
    }

    public Boolean f(int i) {
        Boolean[] boolArr = this.sendRedirectHops;
        if (boolArr == null || i < 0 || i >= boolArr.length) {
            return null;
        }
        return boolArr[i];
    }

    public void e(String str) {
        if (str != null && !str.equals("")) {
            String[] split = str.split(",");
            this.sendRedirectHops = new Boolean[split.length];
            for (int i = 0; i < split.length; i++) {
                if (split[i].compareTo("true") == 0) {
                    this.sendRedirectHops[i] = true;
                } else if (split[i].compareTo("false") == 0) {
                    this.sendRedirectHops[i] = false;
                } else {
                    this.sendRedirectHops[i] = null;
                }
            }
        }
    }

    public boolean r() {
        return this.isMraidAd;
    }

    public void b(boolean z) {
        this.isMraidAd = z;
    }
}
