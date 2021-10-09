package com.tappx.a;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

public final class p0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f543a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final int f;
    public final int g;
    public final int h;
    public final String i;
    public final String j;
    public final String k;

    public static class b {
        private static volatile b d;

        /* renamed from: a  reason: collision with root package name */
        private final Context f544a;
        private final d<String> b;
        private final a c;

        static class a {

            /* renamed from: a  reason: collision with root package name */
            private final Context f545a;

            /* renamed from: com.tappx.a.p0$b$a$a  reason: collision with other inner class name */
            private static final class C0017a {
                /* access modifiers changed from: private */

                /* renamed from: a  reason: collision with root package name */
                public String f546a;
                private final Context b;

                /* renamed from: com.tappx.a.p0$b$a$a$a  reason: collision with other inner class name */
                class C0018a implements Runnable {

                    /* renamed from: a  reason: collision with root package name */
                    final /* synthetic */ CountDownLatch f547a;

                    C0018a(CountDownLatch countDownLatch) {
                        this.f547a = countDownLatch;
                    }

                    public void run() {
                        C0017a aVar = C0017a.this;
                        String unused = aVar.f546a = aVar.b();
                        this.f547a.countDown();
                    }
                }

                /* access modifiers changed from: private */
                public String b() {
                    WebView webView = new WebView(this.b);
                    String userAgentString = webView.getSettings().getUserAgentString();
                    webView.destroy();
                    return userAgentString;
                }

                private C0017a(Context context) {
                    this.b = context;
                }

                public String a() {
                    if (g3.a()) {
                        return b();
                    }
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    g3.a(new C0018a(countDownLatch));
                    try {
                        countDownLatch.await();
                        return this.f546a;
                    } catch (InterruptedException unused) {
                        return null;
                    }
                }
            }

            public a(Context context) {
                this.f545a = context;
            }

            private String b() {
                return WebSettings.getDefaultUserAgent(this.f545a);
            }

            private String c() {
                return new C0017a(this.f545a).a();
            }

            private String d() {
                Constructor<WebSettings> declaredConstructor = WebSettings.class.getDeclaredConstructor(new Class[]{Context.class, WebView.class});
                boolean isAccessible = declaredConstructor.isAccessible();
                if (!isAccessible) {
                    declaredConstructor.setAccessible(true);
                }
                try {
                    return declaredConstructor.newInstance(new Object[]{this.f545a, null}).getUserAgentString();
                } finally {
                    if (!isAccessible) {
                        declaredConstructor.setAccessible(false);
                    }
                }
            }

            /* JADX WARNING: Can't wrap try/catch for region: R(5:0|(3:2|3|4)|5|6|7) */
            /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
                if (r0 == null) goto L_0x0016;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
                return java.lang.System.getProperty("http.agent");
             */
            /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
                return r0;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
                r0 = c();
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000b */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.String a() {
                /*
                    r2 = this;
                    int r0 = android.os.Build.VERSION.SDK_INT
                    r1 = 17
                    if (r0 < r1) goto L_0x000b
                    java.lang.String r0 = r2.b()     // Catch:{ Exception -> 0x000b }
                    return r0
                L_0x000b:
                    java.lang.String r0 = r2.d()     // Catch:{ Exception -> 0x0010 }
                    return r0
                L_0x0010:
                    java.lang.String r0 = r2.c()
                    if (r0 != 0) goto L_0x001c
                    java.lang.String r0 = "http.agent"
                    java.lang.String r0 = java.lang.System.getProperty(r0)
                L_0x001c:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.p0.b.a.a():java.lang.String");
            }
        }

        public b(Context context) {
            this(context, new a(context));
        }

        private int a(int i, int i2) {
            if (i == i2) {
                return 0;
            }
            return i < i2 ? 1 : 2;
        }

        public static final b a(Context context) {
            if (d == null) {
                synchronized (b.class) {
                    if (d == null) {
                        d = new b(context.getApplicationContext());
                    }
                }
            }
            return d;
        }

        private String b() {
            Locale locale = Locale.getDefault();
            return locale != null ? locale.getLanguage() : "en-us";
        }

        private String c() {
            String a2 = this.b.a();
            if (a2 != null) {
                return a2;
            }
            String a3 = this.c.a();
            this.b.a(a3);
            return a3;
        }

        b(Context context, a aVar) {
            this.b = new k();
            this.f544a = context;
            this.c = aVar;
        }

        public p0 a() {
            String b2 = b();
            String str = Build.MANUFACTURER;
            String str2 = Build.MODEL;
            String str3 = Build.PRODUCT;
            String str4 = Build.VERSION.RELEASE;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) this.f544a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            return new p0(b2, str, str2, str3, "android", str4, i, i2, a(i, i2), String.valueOf(displayMetrics.scaledDensity), c());
        }
    }

    public p0(String str, String str2, String str3, String str4, String str5, String str6, int i2, int i3, int i4, String str7, String str8) {
        this.k = str;
        this.f543a = str2;
        this.b = str3;
        this.c = str4;
        this.d = str5;
        this.e = str6;
        this.g = i2;
        this.h = i3;
        this.f = i4;
        this.i = str7;
        this.j = str8;
    }
}
