package com.truenet.android;

import a.a.b.b.h;
import a.a.b.b.i;
import a.a.b.b.k;
import a.a.b.b.l;
import a.a.b.b.m;
import a.a.b.b.n;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ a.a.d.e[] f670a;
    public static final a b;
    /* access modifiers changed from: private */
    public static final String n;
    /* access modifiers changed from: private */
    public static final a.a.e.b o = new a.a.e.b("^\\w+(://){1}.+$");
    private Bitmap c;
    private long d;
    private String e;
    private String f;
    private final List<C0021b> g = new ArrayList();
    private final a.a.c h = a.a.d.a(f.f675a);
    private final a.a.c i = a.a.d.a(new g(this));
    /* access modifiers changed from: private */
    public final Context j;
    private final String k;
    private final int l;
    private final long m;

    /* access modifiers changed from: private */
    public final SynchronousQueue<String> j() {
        a.a.c cVar = this.h;
        a.a.d.e eVar = f670a[0];
        return (SynchronousQueue) cVar.a();
    }

    /* access modifiers changed from: private */
    public final WebView k() {
        a.a.c cVar = this.i;
        a.a.d.e eVar = f670a[1];
        return (WebView) cVar.a();
    }

    public b(Context context, String str, int i2, long j2) {
        h.b(context, "context");
        h.b(str, "url");
        this.j = context;
        this.k = str;
        this.l = i2;
        this.m = j2;
        this.e = str;
    }

    public final Bitmap a() {
        return this.c;
    }

    public final int b() {
        return this.g.size();
    }

    public final long c() {
        return this.d;
    }

    public final List<C0021b> d() {
        return a.a.a.g.a(this.g);
    }

    public final String e() {
        return this.e;
    }

    public final String f() {
        return this.f;
    }

    /* compiled from: StartAppSDK */
    static final class f extends i implements a.a.b.a.a<SynchronousQueue<String>> {

        /* renamed from: a  reason: collision with root package name */
        public static final f f675a = new f();

        f() {
            super(0);
        }

        /* renamed from: b */
        public final SynchronousQueue<String> a() {
            return new SynchronousQueue<>();
        }
    }

    /* compiled from: StartAppSDK */
    static final class g extends i implements a.a.b.a.a<WebView> {
        final /* synthetic */ b this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        g(b bVar) {
            super(0);
            this.this$0 = bVar;
        }

        /* renamed from: b */
        public final WebView a() {
            try {
                WebView webView = new WebView(this.this$0.j);
                if (Build.VERSION.SDK_INT >= 11) {
                    webView.setLayerType(1, (Paint) null);
                }
                WebSettings settings = webView.getSettings();
                h.a((Object) settings, com.appnext.core.a.b.hW);
                settings.setJavaScriptEnabled(true);
                webView.setWebChromeClient(new WebChromeClient());
                webView.setWebViewClient(new c());
                return webView;
            } catch (Exception e) {
                Log.e(b.n, e.getMessage());
                return null;
            }
        }
    }

    /* compiled from: StartAppSDK */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final boolean a(String str) {
            return a.a.e.c.a(str, "http://play.google.com", false, 2, (Object) null) || a.a.e.c.a(str, "https://play.google.com", false, 2, (Object) null) || a.a.e.c.a(str, "http://itunes.apple.com", false, 2, (Object) null) || a.a.e.c.a(str, "https://itunes.apple.com", false, 2, (Object) null) || (!a.a.e.c.a(str, "http://", false, 2, (Object) null) && !a.a.e.c.a(str, "https://", false, 2, (Object) null) && b.o.a(str));
        }
    }

    static {
        Class<b> cls = b.class;
        f670a = new a.a.d.e[]{n.a((k) new l(n.a((Class) cls), "queue", "getQueue()Ljava/util/concurrent/SynchronousQueue;")), n.a((k) new l(n.a((Class) cls), "webView", "getWebView()Landroid/webkit/WebView;"))};
        a aVar = new a((e) null);
        b = aVar;
        n = aVar.getClass().getSimpleName();
    }

    /* renamed from: com.truenet.android.b$b  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    public static final class C0021b {

        /* renamed from: a  reason: collision with root package name */
        private final String f671a;
        private final long b;
        private final int c;
        private final List<String> d;
        private final String e;

        public static /* bridge */ /* synthetic */ C0021b a(C0021b bVar, String str, long j, int i, List<String> list, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = bVar.f671a;
            }
            if ((i2 & 2) != 0) {
                j = bVar.b;
            }
            long j2 = j;
            if ((i2 & 4) != 0) {
                i = bVar.c;
            }
            int i3 = i;
            if ((i2 & 8) != 0) {
                list = bVar.d;
            }
            List<String> list2 = list;
            if ((i2 & 16) != 0) {
                str2 = bVar.e;
            }
            return bVar.a(str, j2, i3, list2, str2);
        }

        public final C0021b a(String str, long j, int i, List<String> list, String str2) {
            h.b(str, "url");
            return new C0021b(str, j, i, list, str2);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof C0021b) {
                    C0021b bVar = (C0021b) obj;
                    if (h.a((Object) this.f671a, (Object) bVar.f671a)) {
                        if (this.b == bVar.b) {
                            if (!(this.c == bVar.c) || !h.a((Object) this.d, (Object) bVar.d) || !h.a((Object) this.e, (Object) bVar.e)) {
                                return false;
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            String str = this.f671a;
            int i = 0;
            int hashCode = str != null ? str.hashCode() : 0;
            long j = this.b;
            int i2 = ((((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.c) * 31;
            List<String> list = this.d;
            int hashCode2 = (i2 + (list != null ? list.hashCode() : 0)) * 31;
            String str2 = this.e;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            return "ConnectionInfo(url=" + this.f671a + ", loadTime=" + this.b + ", httpCode=" + this.c + ", cookie=" + this.d + ", redirectUrl=" + this.e + ")";
        }

        public C0021b(String str, long j, int i, List<String> list, String str2) {
            h.b(str, "url");
            this.f671a = str;
            this.b = j;
            this.c = i;
            this.d = list;
            this.e = str2;
        }

        public final String a() {
            return this.f671a;
        }

        public final long b() {
            return this.b;
        }

        public final int c() {
            return this.c;
        }

        public final List<String> d() {
            return this.d;
        }

        public final String e() {
            return this.e;
        }
    }

    static /* bridge */ /* synthetic */ C0021b a(b bVar, String str, List list, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            list = null;
        }
        return bVar.a(str, list);
    }

    private final C0021b a(String str, List<String> list) {
        String str2;
        String str3 = str;
        this.f = null;
        if (b.a(str3)) {
            return new C0021b(str, 0, 200, (List<String>) null, (String) null);
        }
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(str3);
            URLConnection openConnection = url.openConnection();
            if (openConnection != null) {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) openConnection;
                boolean z = false;
                httpURLConnection2.setInstanceFollowRedirects(false);
                httpURLConnection2.setConnectTimeout(((int) this.m) * 1000);
                httpURLConnection2.setReadTimeout(((int) this.m) * 1000);
                httpURLConnection2.addRequestProperty("User-Agent", com.truenet.android.a.i.f668a.a(this.j));
                if (list != null) {
                    Iterable<String> iterable = list;
                    Collection arrayList = new ArrayList(a.a.a.g.a(iterable, 10));
                    for (String parse : iterable) {
                        List<HttpCookie> parse2 = HttpCookie.parse(parse);
                        h.a((Object) parse2, "HttpCookie.parse(it)");
                        arrayList.add((HttpCookie) a.a.a.g.c(parse2));
                    }
                    httpURLConnection2.setRequestProperty("Cookie", TextUtils.join(r3, (List) arrayList));
                }
                long currentTimeMillis = System.currentTimeMillis();
                httpURLConnection2.connect();
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                String headerField = httpURLConnection2.getHeaderField("Location");
                if (headerField != null) {
                    a.a.e.b bVar = o;
                    h.a((Object) headerField, "nextUrl");
                    if (!bVar.a(headerField)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(url.getProtocol());
                        sb.append("://");
                        sb.append(url.getHost());
                        h.a((Object) headerField, "nextUrl");
                        if (!a.a.e.c.a(headerField, "/", false, 2, (Object) null)) {
                            headerField = '/' + headerField;
                        }
                        sb.append(headerField);
                        headerField = sb.toString();
                    }
                    str2 = headerField;
                } else {
                    str2 = null;
                }
                C0021b bVar2 = new C0021b(str, currentTimeMillis2, httpURLConnection2.getResponseCode(), (List) httpURLConnection2.getHeaderFields().get("Set-Cookie"), str2);
                int responseCode = httpURLConnection2.getResponseCode();
                if (200 <= responseCode) {
                    if (299 >= responseCode) {
                        InputStream inputStream = httpURLConnection2.getInputStream();
                        h.a((Object) inputStream, "inputStream");
                        this.f = a(inputStream);
                        long currentTimeMillis3 = System.currentTimeMillis() - currentTimeMillis;
                        new Handler(Looper.getMainLooper()).post(new e(httpURLConnection2, this, list, url, str));
                        String take = j().take();
                        h.a((Object) take, "jsRedirectUrl");
                        if (take.length() == 0) {
                            z = true;
                        }
                        if (z) {
                            return C0021b.a(bVar2, (String) null, currentTimeMillis3, 0, (List) null, (String) null, 29, (Object) null);
                        }
                        return C0021b.a(bVar2, (String) null, currentTimeMillis3, 0, (List) null, take, 13, (Object) null);
                    }
                }
                if (300 <= responseCode) {
                    if (399 >= responseCode) {
                        return bVar2;
                    }
                }
                return C0021b.a(bVar2, (String) null, 0, 0, (List) null, (String) null, 15, (Object) null);
            }
            throw new a.a.h("null cannot be cast to non-null type java.net.HttpURLConnection");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* compiled from: StartAppSDK */
    static final class e implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ HttpURLConnection f674a;
        final /* synthetic */ b b;
        final /* synthetic */ List c;
        final /* synthetic */ URL d;
        final /* synthetic */ String e;

        e(HttpURLConnection httpURLConnection, b bVar, List list, URL url, String str) {
            this.f674a = httpURLConnection;
            this.b = bVar;
            this.c = list;
            this.d = url;
            this.e = str;
        }

        public final void run() {
            WebView b2 = this.b.k();
            if (b2 != null) {
                b2.loadDataWithBaseURL(this.e, this.b.f(), this.f674a.getContentType(), this.f674a.getContentEncoding(), (String) null);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0076 A[Catch:{ all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b A[Catch:{ all -> 0x007f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String a(java.io.InputStream r8) {
        /*
            r7 = this;
            java.lang.String r0 = "stream closed with error!"
            r1 = 0
            r2 = r1
            java.io.BufferedInputStream r2 = (java.io.BufferedInputStream) r2
            a.a.b.b.m$a r3 = new a.a.b.b.m$a
            r3.<init>()
            r4 = r1
            java.io.BufferedReader r4 = (java.io.BufferedReader) r4
            r3.element = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r4.<init>()     // Catch:{ all -> 0x006f }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ all -> 0x006f }
            r5.<init>(r8)     // Catch:{ all -> 0x006f }
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch:{ all -> 0x006c }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x006c }
            r6 = r5
            java.io.InputStream r6 = (java.io.InputStream) r6     // Catch:{ all -> 0x006c }
            r2.<init>(r6)     // Catch:{ all -> 0x006c }
            java.io.Reader r2 = (java.io.Reader) r2     // Catch:{ all -> 0x006c }
            r8.<init>(r2)     // Catch:{ all -> 0x006c }
            r3.element = r8     // Catch:{ all -> 0x006c }
            a.a.b.b.m$a r8 = new a.a.b.b.m$a     // Catch:{ all -> 0x006c }
            r8.<init>()     // Catch:{ all -> 0x006c }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x006c }
            r8.element = r1     // Catch:{ all -> 0x006c }
        L_0x0034:
            com.truenet.android.b$d r1 = new com.truenet.android.b$d     // Catch:{ all -> 0x006c }
            r1.<init>(r8, r3)     // Catch:{ all -> 0x006c }
            a.a.b.a.a r1 = (a.a.b.a.a) r1     // Catch:{ all -> 0x006c }
            java.lang.Object r1 = r1.a()     // Catch:{ all -> 0x006c }
            if (r1 == 0) goto L_0x0049
            T r1 = r8.element     // Catch:{ all -> 0x006c }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x006c }
            r4.append(r1)     // Catch:{ all -> 0x006c }
            goto L_0x0034
        L_0x0049:
            java.lang.String r8 = r4.toString()     // Catch:{ all -> 0x006c }
            java.lang.String r1 = "result.toString()"
            a.a.b.b.h.a((java.lang.Object) r8, (java.lang.String) r1)     // Catch:{ all -> 0x006c }
            T r1 = r3.element     // Catch:{ all -> 0x005f }
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1     // Catch:{ all -> 0x005f }
            if (r1 == 0) goto L_0x005b
            r1.close()     // Catch:{ all -> 0x005f }
        L_0x005b:
            r5.close()     // Catch:{ all -> 0x005f }
            goto L_0x006b
        L_0x005f:
            r1 = move-exception
            java.lang.Class r2 = r7.getClass()
            java.lang.String r2 = r2.getCanonicalName()
            android.util.Log.e(r2, r0, r1)
        L_0x006b:
            return r8
        L_0x006c:
            r8 = move-exception
            r2 = r5
            goto L_0x0070
        L_0x006f:
            r8 = move-exception
        L_0x0070:
            T r1 = r3.element     // Catch:{ all -> 0x007f }
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1     // Catch:{ all -> 0x007f }
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ all -> 0x007f }
        L_0x0079:
            if (r2 == 0) goto L_0x008b
            r2.close()     // Catch:{ all -> 0x007f }
            goto L_0x008b
        L_0x007f:
            r1 = move-exception
            java.lang.Class r2 = r7.getClass()
            java.lang.String r2 = r2.getCanonicalName()
            android.util.Log.e(r2, r0, r1)
        L_0x008b:
            goto L_0x008d
        L_0x008c:
            throw r8
        L_0x008d:
            goto L_0x008c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.truenet.android.b.a(java.io.InputStream):java.lang.String");
    }

    /* compiled from: StartAppSDK */
    static final class d extends i implements a.a.b.a.a<String> {
        final /* synthetic */ m.a $line;
        final /* synthetic */ m.a $reader;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        d(m.a aVar, m.a aVar2) {
            super(0);
            this.$line = aVar;
            this.$reader = aVar2;
        }

        /* renamed from: b */
        public final String a() {
            this.$line.element = ((BufferedReader) this.$reader.element).readLine();
            return (String) this.$line.element;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void g() {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = r7.k
            r3 = 0
            r4 = 2
            com.truenet.android.b$b r2 = a(r7, r2, r3, r4, r3)
            if (r2 == 0) goto L_0x0087
            java.util.List<com.truenet.android.b$b> r4 = r7.g
            r4.add(r2)
        L_0x0013:
            if (r2 == 0) goto L_0x001a
            java.lang.String r4 = r2.e()
            goto L_0x001b
        L_0x001a:
            r4 = r3
        L_0x001b:
            if (r4 == 0) goto L_0x003c
            boolean r4 = r7.a((long) r0)
            if (r4 == 0) goto L_0x003c
            java.lang.String r4 = r2.e()
            if (r4 != 0) goto L_0x002c
            a.a.b.b.h.a()
        L_0x002c:
            java.util.List r2 = r2.d()
            com.truenet.android.b$b r2 = r7.a(r4, r2)
            if (r2 == 0) goto L_0x0013
            java.util.List<com.truenet.android.b$b> r4 = r7.g
            r4.add(r2)
            goto L_0x0013
        L_0x003c:
            if (r2 == 0) goto L_0x005b
            r4 = 299(0x12b, float:4.19E-43)
            r5 = 200(0xc8, float:2.8E-43)
            int r2 = r2.c()
            if (r5 <= r2) goto L_0x0049
            goto L_0x005b
        L_0x0049:
            if (r4 < r2) goto L_0x005b
            java.lang.String r2 = r7.f
            if (r2 == 0) goto L_0x005b
            android.webkit.WebView r2 = r7.k()
            if (r2 == 0) goto L_0x0059
            android.graphics.Bitmap r3 = com.truenet.android.a.j.a(r2)
        L_0x0059:
            r7.c = r3
        L_0x005b:
            java.util.List<com.truenet.android.b$b> r2 = r7.g
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            r3 = 0
            java.util.Iterator r2 = r2.iterator()
        L_0x0065:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0077
            java.lang.Object r5 = r2.next()
            com.truenet.android.b$b r5 = (com.truenet.android.b.C0021b) r5
            long r5 = r5.b()
            long r3 = r3 + r5
            goto L_0x0065
        L_0x0077:
            r7.d = r3
            java.util.List<com.truenet.android.b$b> r2 = r7.g
            java.lang.Object r2 = a.a.a.g.e(r2)
            com.truenet.android.b$b r2 = (com.truenet.android.b.C0021b) r2
            java.lang.String r2 = r2.a()
            r7.e = r2
        L_0x0087:
            java.util.List<com.truenet.android.b$b> r2 = r7.g
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0096
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r0
            r7.d = r2
        L_0x0096:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.truenet.android.b.g():void");
    }

    private final boolean a(long j2) {
        int size = this.g.size();
        int i2 = this.l;
        return (size < i2 || i2 == -1) && System.currentTimeMillis() - j2 < this.m * ((long) 1000);
    }

    /* compiled from: StartAppSDK */
    public final class c extends WebViewClient {
        private ScheduledExecutorService b = Executors.newScheduledThreadPool(1);
        private ScheduledFuture<?> c;

        public c() {
        }

        private final void a(WebView webView, String str) {
            a();
            if (str != null) {
                if (webView != null) {
                    webView.stopLoading();
                }
                b.this.j().offer(str);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            a(webView, String.valueOf(webResourceRequest != null ? webResourceRequest.getUrl() : null));
            return true;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            a(webView, str);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            a();
            this.c = this.b.schedule(new a(this), 1, TimeUnit.SECONDS);
            super.onPageFinished(webView, str);
        }

        /* compiled from: StartAppSDK */
        static final class a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ c f673a;

            a(c cVar) {
                this.f673a = cVar;
            }

            public final void run() {
                b.this.j().offer("");
            }
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            a();
            if (webView != null) {
                webView.stopLoading();
            }
            b.this.j().offer("");
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        private final void a() {
            ScheduledFuture<?> scheduledFuture = this.c;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.c = null;
        }
    }
}
