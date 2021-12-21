package com.tappx.a;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.core.Ad;
import com.mopub.common.AdType;
import com.mopub.mobileads.resource.DrawableConstants;
import com.tappx.a.k4;
import com.tappx.a.l3;
import com.tappx.a.o3;
import com.tappx.a.p3;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class c4 {

    /* renamed from: a  reason: collision with root package name */
    private final b4 f394a;
    private final k4 b;
    /* access modifiers changed from: private */
    public h c;
    private q4 d;
    private g4 e;
    /* access modifiers changed from: private */
    public boolean f;
    private boolean g;
    private final WebViewClient h;

    class a extends WebChromeClient {
        a() {
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (c4.this.c != null) {
                return c4.this.c.a(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            if (c4.this.c != null) {
                return c4.this.c.a(str2, jsResult);
            }
            return super.onJsAlert(webView, str, str2, jsResult);
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
        }
    }

    class b implements o3.a {
        b() {
        }

        public void a() {
            boolean unused = c4.this.f = true;
        }
    }

    class c implements View.OnTouchListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ o3 f397a;

        c(c4 c4Var, o3 o3Var) {
            this.f397a = o3Var;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.f397a.a(motionEvent);
            int action = motionEvent.getAction();
            if ((action != 0 && action != 1) || view.hasFocus()) {
                return false;
            }
            view.requestFocus();
            return false;
        }
    }

    class d implements l3.a {
        d() {
        }

        public void b(boolean z) {
            if (c4.this.c != null) {
                c4.this.c.b(z);
            }
        }
    }

    class e extends WebViewClient {
        e() {
        }

        public void onPageFinished(WebView webView, String str) {
            c4.this.f();
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            j4.a("Error: " + str);
            super.onReceivedError(webView, i, str, str2);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return c4.this.a(str);
        }
    }

    class f implements k4.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ h4 f400a;

        f(h4 h4Var) {
            this.f400a = h4Var;
        }

        public void a(f4 f4Var) {
            c4.this.a(this.f400a, f4Var.getMessage());
        }
    }

    static /* synthetic */ class g {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f401a;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.tappx.a.h4[] r0 = com.tappx.a.h4.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f401a = r0
                com.tappx.a.h4 r1 = com.tappx.a.h4.CLOSE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f401a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.h4 r1 = com.tappx.a.h4.RESIZE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f401a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.h4 r1 = com.tappx.a.h4.EXPAND     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f401a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.h4 r1 = com.tappx.a.h4.USE_CUSTOM_CLOSE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f401a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.tappx.a.h4 r1 = com.tappx.a.h4.SET_ORIENTATION_PROPERTIES     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f401a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.tappx.a.h4 r1 = com.tappx.a.h4.CREATE_CALENDAR_EVENT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f401a     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.tappx.a.h4 r1 = com.tappx.a.h4.OPEN     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = f401a     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.tappx.a.h4 r1 = com.tappx.a.h4.STORE_PICTURE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = f401a     // Catch:{ NoSuchFieldError -> 0x006c }
                com.tappx.a.h4 r1 = com.tappx.a.h4.PLAY_VIDEO     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = f401a     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.tappx.a.h4 r1 = com.tappx.a.h4.UNSPECIFIED     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.c4.g.<clinit>():void");
        }
    }

    public interface h {
        void a();

        void a(int i, int i2, int i3, int i4, p3.d dVar, boolean z);

        void a(URI uri);

        void a(URI uri, boolean z);

        void a(boolean z);

        void a(boolean z, l4 l4Var);

        boolean a(ConsoleMessage consoleMessage);

        boolean a(String str, JsResult jsResult);

        void b();

        void b(boolean z);

        void c();
    }

    c4(b4 b4Var) {
        this(b4Var, new k4());
    }

    private boolean e(String str) {
        if ("true".equals(str)) {
            return true;
        }
        if ("false".equals(str)) {
            return false;
        }
        throw new f4("Invalid boolean parameter: " + str);
    }

    /* access modifiers changed from: private */
    public void f() {
        if (!this.g) {
            this.g = true;
            h hVar = this.c;
            if (hVar != null) {
                hVar.c();
            }
        }
    }

    private int g(String str) {
        try {
            return Integer.parseInt(str, 10);
        } catch (NumberFormatException unused) {
            throw new f4("Invalid param: " + str);
        }
    }

    public void c(String str) {
        if (this.d == null) {
            j4.a("MRAID bridge called setContentHtml before WebView was attached");
            return;
        }
        g4 g4Var = this.e;
        if (g4Var != null) {
            str = g4Var.a(str);
        }
        this.g = false;
        this.d.loadDataWithBaseURL((String) null, str, "text/html", "UTF-8", (String) null);
    }

    public void d(String str) {
        q4 q4Var = this.d;
        if (q4Var == null) {
            j4.a("MRAID bridge called setContentHtml while WebView was not attached");
            return;
        }
        this.g = false;
        q4Var.loadUrl(str);
    }

    c4(b4 b4Var, k4 k4Var) {
        this.h = new e();
        this.f394a = b4Var;
        this.b = k4Var;
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        if (this.d == null) {
            j4.a("Attempted to inject Javascript into MRAID WebView while was not attached:\n\t" + str);
            return;
        }
        j4.c("Injecting Javascript into MRAID WebView:\t" + str);
        q4 q4Var = this.d;
        q4Var.loadUrl("javascript:" + str);
    }

    /* access modifiers changed from: package-private */
    public void a(h hVar) {
        this.c = hVar;
    }

    /* access modifiers changed from: package-private */
    public void a(q4 q4Var) {
        this.d = q4Var;
        this.e = new g4(q4Var.getContext());
        this.d.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 17 && (this.f394a == b4.INTERSTITIAL || o.b)) {
            q4Var.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.e.a((WebView) q4Var);
        this.d.setScrollContainer(false);
        this.d.setVerticalScrollBarEnabled(false);
        this.d.setHorizontalScrollBarEnabled(false);
        this.d.setBackgroundColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
        this.d.setWebViewClient(this.h);
        this.d.setWebChromeClient(new a());
        o3 o3Var = new o3();
        o3Var.a((o3.a) new b());
        this.d.setOnTouchListener(new c(this, o3Var));
        this.d.setVisibilityChangedListener(new d());
    }

    /* access modifiers changed from: package-private */
    public void e() {
        b("mraidbridge.notifyReadyEvent();");
    }

    private l4 f(String str) {
        if (Ad.ORIENTATION_PORTRAIT.equals(str)) {
            return l4.PORTRAIT;
        }
        if (Ad.ORIENTATION_LANDSCAPE.equals(str)) {
            return l4.LANDSCAPE;
        }
        if ("none".equals(str)) {
            return l4.NONE;
        }
        throw new f4("Invalid orientation '" + str + "'");
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        q4 q4Var = this.d;
        return q4Var != null && q4Var.b();
    }

    private String b(Rect rect) {
        return rect.width() + "," + rect.height();
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.d != null;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.d = null;
    }

    /* access modifiers changed from: private */
    public void a(h4 h4Var, String str) {
        b("window.mraidbridge.notifyErrorEvent(" + JSONObject.quote(h4Var.a()) + ", " + JSONObject.quote(str) + ")");
    }

    private void a(h4 h4Var) {
        b("window.mraidbridge.nativeCallComplete(" + JSONObject.quote(h4Var.a()) + ")");
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str) {
        try {
            new URI(str);
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            String host = parse.getHost();
            if ("tappx".equalsIgnoreCase(scheme)) {
                if ("loadFailed".equalsIgnoreCase(host)) {
                    this.c.b();
                }
                return true;
            } else if (AdType.MRAID.equals(scheme)) {
                h4 a2 = h4.a(host);
                try {
                    a(a2, a(parse));
                } catch (f4 e2) {
                    a(a2, e2.getMessage());
                }
                a(a2);
                return true;
            } else {
                if (this.f) {
                    this.f = false;
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    intent.addFlags(268435456);
                    try {
                        if (this.d == null) {
                            j4.a("WebView was detached. Unable to load a URL");
                            return true;
                        }
                        this.d.getContext().startActivity(intent);
                        this.c.a((URI) null);
                        return true;
                    } catch (ActivityNotFoundException unused) {
                        j4.a("No activity found to handle this URL " + str);
                    }
                }
                return false;
            }
        } catch (URISyntaxException unused2) {
            j4.d("Invalid MRAID URL: " + str);
            a(h4.UNSPECIFIED, "Mraid command sent an invalid URL");
            return true;
        }
    }

    private Map<String, String> a(Uri uri) {
        HashMap hashMap = new HashMap();
        for (String next : uri.getQueryParameterNames()) {
            hashMap.put(next, TextUtils.join(",", uri.getQueryParameters(next)));
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public void a(h4 h4Var, Map<String, String> map) {
        if (h4Var.a(this.f394a) && !this.f) {
            throw new f4("Click required");
        } else if (this.c == null) {
            throw new f4("Invalid state");
        } else if (this.d != null) {
            switch (g.f401a[h4Var.ordinal()]) {
                case 1:
                    this.c.a();
                    return;
                case 2:
                    this.c.a(a(g(map.get("width")), 0, 100000), a(g(map.get("height")), 0, 100000), a(g(map.get("offsetX")), -100000, 100000), a(g(map.get("offsetY")), -100000, 100000), a(map.get("customClosePosition"), p3.d.TOP_RIGHT), a(map.get("allowOffscreen"), true));
                    return;
                case 3:
                    this.c.a(m4.a(map.get("url"), (URI) null), a(map.get("shouldUseCustomClose"), false));
                    return;
                case 4:
                    this.c.a(a(map.get("shouldUseCustomClose"), false));
                    return;
                case 5:
                    this.c.a(e(map.get("allowOrientationChange")), f(map.get("forceOrientation")));
                    return;
                case 6:
                    this.b.a(this.d.getContext(), map);
                    return;
                case 7:
                    URI b2 = m4.b(map.get("url"));
                    this.b.a(this.d.getContext(), b2);
                    this.c.a(b2);
                    return;
                case 8:
                    this.b.b(this.d.getContext(), m4.b(map.get("uri")).toString(), new f(h4Var));
                    return;
                case 9:
                    this.b.a(this.d.getContext(), m4.b(map.get("uri")).toString());
                    return;
                case 10:
                    throw new f4("Unspecified command");
                default:
                    return;
            }
        } else {
            throw new f4("Destroyed");
        }
    }

    private p3.d a(String str, p3.d dVar) {
        if (TextUtils.isEmpty(str)) {
            return dVar;
        }
        if (str.equals("top-left")) {
            return p3.d.TOP_LEFT;
        }
        if (str.equals("top-right")) {
            return p3.d.TOP_RIGHT;
        }
        if (str.equals("center")) {
            return p3.d.CENTER;
        }
        if (str.equals("bottom-left")) {
            return p3.d.BOTTOM_LEFT;
        }
        if (str.equals("bottom-right")) {
            return p3.d.BOTTOM_RIGHT;
        }
        if (str.equals("top-center")) {
            return p3.d.TOP_CENTER;
        }
        if (str.equals("bottom-center")) {
            return p3.d.BOTTOM_CENTER;
        }
        throw new f4("Invalid position '" + str + "'");
    }

    private int a(int i, int i2, int i3) {
        if (i >= i2 && i <= i3) {
            return i;
        }
        throw new f4("param out of range: " + i);
    }

    private boolean a(String str, boolean z) {
        return str == null ? z : e(str);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        b("mraidbridge.setIsViewable(" + z + ")");
    }

    /* access modifiers changed from: package-private */
    public void a(b4 b4Var) {
        b("mraidbridge.setPlacementType(" + JSONObject.quote(b4Var.a()) + ")");
    }

    /* access modifiers changed from: package-private */
    public void a(v4 v4Var) {
        b("mraidbridge.setState(" + JSONObject.quote(v4Var.a()) + ")");
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        b("mraidbridge.setSupports(" + z + "," + z2 + "," + z3 + "," + z4 + "," + z5 + ")");
    }

    private String a(Rect rect) {
        return rect.left + "," + rect.top + "," + rect.width() + "," + rect.height();
    }

    public void a(n4 n4Var) {
        b("mraidbridge.setScreenSize(" + b(n4Var.g()) + ");mraidbridge.setMaxSize(" + b(n4Var.f()) + ");mraidbridge.setCurrentPosition(" + a(n4Var.b()) + ");mraidbridge.setDefaultPosition(" + a(n4Var.d()) + ")");
        StringBuilder sb = new StringBuilder();
        sb.append("mraidbridge.notifySizeChangeEvent(");
        sb.append(b(n4Var.a()));
        sb.append(")");
        b(sb.toString());
    }
}
