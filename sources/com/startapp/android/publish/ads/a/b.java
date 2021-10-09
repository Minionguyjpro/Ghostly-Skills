package com.startapp.android.publish.ads.a;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import com.startapp.android.publish.ads.splash.g;
import com.startapp.android.publish.ads.video.f;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adinformation.b;
import com.startapp.android.publish.adsCommon.adinformation.c;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.inappbrowser.a;

/* compiled from: StartAppSDK */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    protected com.startapp.android.publish.adsCommon.adinformation.b f28a = null;
    protected AdPreferences.Placement b;
    protected boolean c = false;
    private Intent d;
    private Activity e;
    private BroadcastReceiver f = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            b.this.p();
        }
    };
    private String[] g;
    private boolean[] h;
    private boolean[] i = {true};
    private String j;
    private String[] k;
    private String[] l;
    private String[] m;
    private Ad n;
    private String o;
    private boolean p;
    private c q;
    private String r;
    private Long s;
    private Boolean[] t = null;

    public void a(Configuration configuration) {
    }

    public boolean a(int i2, KeyEvent keyEvent) {
        return false;
    }

    public void b(Bundle bundle) {
    }

    public void c(Bundle bundle) {
    }

    public boolean r() {
        return false;
    }

    public void t() {
    }

    public abstract void u();

    /* renamed from: com.startapp.android.publish.ads.a.b$3  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f31a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.startapp.android.publish.common.model.AdPreferences$Placement[] r0 = com.startapp.android.publish.common.model.AdPreferences.Placement.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f31a = r0
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_OFFER_WALL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f31a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_RETURN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f31a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_OVERLAY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f31a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_SPLASH     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f31a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_FULL_SCREEN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f31a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_BROWSER     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.a.b.AnonymousClass3.<clinit>():void");
        }
    }

    public static b a(Activity activity, Intent intent, AdPreferences.Placement placement) {
        b bVar = null;
        switch (AnonymousClass3.f31a[placement.ordinal()]) {
            case 1:
                if (i.a(128) || i.a(64)) {
                    bVar = new e();
                    break;
                }
            case 2:
            case 3:
                if (!i.a(4) || !intent.getBooleanExtra("videoAd", false)) {
                    if (!intent.getBooleanExtra("mraidAd", false)) {
                        bVar = new f();
                        break;
                    } else {
                        bVar = new d();
                        break;
                    }
                } else {
                    bVar = new f();
                    break;
                }
                break;
            case 4:
                if (i.a(8)) {
                    bVar = new g();
                    break;
                }
                break;
            case 5:
            case 6:
                if (i.a(256)) {
                    Uri data = intent.getData();
                    if (data != null) {
                        bVar = new a(data.toString());
                        break;
                    } else {
                        return null;
                    }
                }
                break;
            default:
                bVar = new a();
                break;
        }
        bVar.a(intent);
        bVar.a(activity);
        bVar.c(intent.getStringExtra("position"));
        bVar.b(intent.getStringArrayExtra("tracking"));
        bVar.c(intent.getStringArrayExtra("trackingClickUrl"));
        bVar.d(intent.getStringArrayExtra("packageNames"));
        bVar.a(intent.getStringArrayExtra("closingUrl"));
        bVar.a(intent.getBooleanArrayExtra("smartRedirect"));
        bVar.b(intent.getBooleanArrayExtra("browserEnabled"));
        String stringExtra = intent.getStringExtra("htmlUuid");
        if (stringExtra != null) {
            if (AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
                bVar.a(com.startapp.android.publish.cache.a.a().b(stringExtra));
            } else {
                bVar.a(com.startapp.android.publish.cache.a.a().c(stringExtra));
            }
        }
        bVar.a(intent.getBooleanExtra("isSplash", false));
        bVar.a((c) intent.getSerializableExtra("adInfoOverride"));
        bVar.b(intent.getStringExtra("adTag"));
        bVar.a(placement);
        bVar.a(intent.getStringArrayExtra("closingUrl"));
        if (bVar.d() == null) {
            bVar.a(new boolean[]{true});
        }
        if (bVar.e() == null) {
            bVar.b(new boolean[]{true});
        }
        bVar.a((Ad) intent.getSerializableExtra("ad"));
        long longExtra = intent.getLongExtra("delayImpressionSeconds", -1);
        if (longExtra != -1) {
            bVar.a(Long.valueOf(longExtra));
        }
        bVar.a((Boolean[]) intent.getSerializableExtra("sendRedirectHops"));
        com.startapp.common.a.g.a("GenericMode", 3, "Placement=[" + bVar.k() + "]");
        return bVar;
    }

    private void a(String[] strArr) {
        this.g = strArr;
    }

    private void a(Intent intent) {
        this.d = intent;
    }

    private void a(AdPreferences.Placement placement) {
        this.b = placement;
    }

    private void a(boolean z) {
        this.p = z;
    }

    private void b(String str) {
        this.r = str;
    }

    public Intent a() {
        return this.d;
    }

    private void a(Activity activity) {
        this.e = activity;
    }

    private void a(c cVar) {
        this.q = cVar;
    }

    public Activity b() {
        return this.e;
    }

    public void c() {
        this.c = true;
    }

    private void c(String str) {
        this.j = str;
    }

    private void b(String[] strArr) {
        this.k = strArr;
    }

    private void c(String[] strArr) {
        this.l = strArr;
    }

    private void d(String[] strArr) {
        this.m = strArr;
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.o = str;
    }

    /* access modifiers changed from: protected */
    public void a(boolean[] zArr) {
        this.h = zArr;
    }

    /* access modifiers changed from: protected */
    public boolean[] d() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public void b(boolean[] zArr) {
        this.i = zArr;
    }

    public boolean[] e() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i2) {
        boolean[] zArr = this.i;
        if (zArr == null || i2 < 0 || i2 >= zArr.length) {
            return true;
        }
        return zArr[i2];
    }

    /* access modifiers changed from: protected */
    public String f() {
        return this.o;
    }

    /* access modifiers changed from: protected */
    public String g() {
        return this.j;
    }

    /* access modifiers changed from: protected */
    public String[] h() {
        return this.k;
    }

    /* access modifiers changed from: protected */
    public String[] i() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    public String[] j() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public AdPreferences.Placement k() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public String[] l() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public c m() {
        return this.q;
    }

    /* access modifiers changed from: protected */
    public String n() {
        return this.r;
    }

    /* access modifiers changed from: protected */
    public void a(RelativeLayout relativeLayout) {
        com.startapp.android.publish.adsCommon.adinformation.b bVar = new com.startapp.android.publish.adsCommon.adinformation.b(b(), b.C0003b.LARGE, k(), m());
        this.f28a = bVar;
        bVar.a(relativeLayout);
    }

    public Long o() {
        return this.s;
    }

    private void a(Long l2) {
        this.s = l2;
    }

    public Boolean b(int i2) {
        Boolean[] boolArr = this.t;
        if (boolArr == null || i2 < 0 || i2 >= boolArr.length) {
            return null;
        }
        return boolArr[i2];
    }

    public void a(Boolean[] boolArr) {
        this.t = boolArr;
    }

    public void p() {
        b().runOnUiThread(new Runnable() {
            public void run() {
                b.this.b().finish();
            }
        });
    }

    public void q() {
        com.startapp.common.b.a((Context) b()).a(new Intent("com.startapp.android.HideDisplayBroadcastListener"));
    }

    public void a(Bundle bundle) {
        com.startapp.common.b.a((Context) b()).a(this.f, new IntentFilter("com.startapp.android.CloseAdActivity"));
    }

    public void s() {
        p();
    }

    public void v() {
        if (this.f != null) {
            com.startapp.common.b.a((Context) b()).a(this.f);
        }
        this.f = null;
    }

    public Ad w() {
        return this.n;
    }

    public void a(Ad ad) {
        this.n = ad;
    }
}
