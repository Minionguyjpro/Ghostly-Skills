package com.appnext.core.webview;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.core.f;
import com.mopub.common.Constants;
import java.io.IOException;
import java.net.HttpRetryException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class AppnextWebView {
    public static final int il = 1;
    public static final int im = 2;
    /* access modifiers changed from: private */
    public static AppnextWebView in;
    /* access modifiers changed from: private */
    public WebAppInterface bX;
    /* access modifiers changed from: private */
    public final HashMap<String, b> io = new HashMap<>();
    private HashMap<String, WebView> ip;

    public interface c {
        void error(String str);

        void f(String str);
    }

    public final void a(WebAppInterface webAppInterface) {
        if (this.bX == webAppInterface) {
            this.bX = null;
        }
    }

    private class b {
        public String aQ;
        public ArrayList<c> hF;
        public String is;
        public int state;

        private b() {
            this.state = 0;
            this.is = "";
            this.hF = new ArrayList<>();
        }
    }

    public static AppnextWebView u(Context context) {
        if (in == null) {
            AppnextWebView appnextWebView = new AppnextWebView();
            in = appnextWebView;
            appnextWebView.ip = new HashMap<>();
        }
        return in;
    }

    private AppnextWebView() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(java.lang.String r8, com.appnext.core.webview.AppnextWebView.c r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            java.util.HashMap<java.lang.String, com.appnext.core.webview.AppnextWebView$b> r0 = r7.io     // Catch:{ all -> 0x004c }
            boolean r0 = r0.containsKey(r8)     // Catch:{ all -> 0x004c }
            r1 = 0
            if (r0 == 0) goto L_0x0013
            java.util.HashMap<java.lang.String, com.appnext.core.webview.AppnextWebView$b> r0 = r7.io     // Catch:{ all -> 0x004c }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x004c }
            com.appnext.core.webview.AppnextWebView$b r0 = (com.appnext.core.webview.AppnextWebView.b) r0     // Catch:{ all -> 0x004c }
            goto L_0x001a
        L_0x0013:
            com.appnext.core.webview.AppnextWebView$b r0 = new com.appnext.core.webview.AppnextWebView$b     // Catch:{ all -> 0x004c }
            r0.<init>()     // Catch:{ all -> 0x004c }
            r0.aQ = r8     // Catch:{ all -> 0x004c }
        L_0x001a:
            int r2 = r0.state     // Catch:{ all -> 0x004c }
            r3 = 2
            if (r2 != r3) goto L_0x0026
            if (r9 == 0) goto L_0x004a
            r9.f(r8)     // Catch:{ all -> 0x004c }
            monitor-exit(r7)
            return
        L_0x0026:
            int r2 = r0.state     // Catch:{ all -> 0x004c }
            if (r2 != 0) goto L_0x003e
            r2 = 1
            r0.state = r2     // Catch:{ all -> 0x004c }
            com.appnext.core.webview.AppnextWebView$a r4 = new com.appnext.core.webview.AppnextWebView$a     // Catch:{ all -> 0x004c }
            r4.<init>(r0)     // Catch:{ all -> 0x004c }
            java.util.concurrent.Executor r5 = android.os.AsyncTask.THREAD_POOL_EXECUTOR     // Catch:{ all -> 0x004c }
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ all -> 0x004c }
            r6 = 0
            r3[r6] = r8     // Catch:{ all -> 0x004c }
            r3[r2] = r1     // Catch:{ all -> 0x004c }
            r4.executeOnExecutor(r5, r3)     // Catch:{ all -> 0x004c }
        L_0x003e:
            if (r9 == 0) goto L_0x0045
            java.util.ArrayList<com.appnext.core.webview.AppnextWebView$c> r1 = r0.hF     // Catch:{ all -> 0x004c }
            r1.add(r9)     // Catch:{ all -> 0x004c }
        L_0x0045:
            java.util.HashMap<java.lang.String, com.appnext.core.webview.AppnextWebView$b> r9 = r7.io     // Catch:{ all -> 0x004c }
            r9.put(r8, r0)     // Catch:{ all -> 0x004c }
        L_0x004a:
            monitor-exit(r7)
            return
        L_0x004c:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.webview.AppnextWebView.a(java.lang.String, com.appnext.core.webview.AppnextWebView$c):void");
    }

    private class a extends AsyncTask<String, Void, String> {
        b ir;

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            super.onPostExecute(str);
            if (str.startsWith("error:")) {
                this.ir.state = 0;
                AppnextWebView.this.io.put(this.ir.aQ, this.ir);
                AppnextWebView.a(AppnextWebView.this, this.ir, str.substring(7));
                return;
            }
            this.ir.state = 2;
            AppnextWebView.this.io.put(this.ir.aQ, this.ir);
            AppnextWebView.b(AppnextWebView.this, this.ir, str);
        }

        public a(b bVar) {
            this.ir = bVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public final String doInBackground(String... strArr) {
            try {
                b bVar = (b) AppnextWebView.this.io.get(strArr[0]);
                bVar.is = f.a(strArr[0], (HashMap<String, String>) null, true, 10000);
                AppnextWebView.this.io.put(strArr[0], bVar);
                return strArr[0];
            } catch (HttpRetryException e) {
                return "error: " + e.getReason();
            } catch (IOException unused) {
                return "error: network problem";
            } catch (Throwable unused2) {
                return "error: unknown error";
            }
        }

        /* access modifiers changed from: protected */
        public final void ag(String str) {
            super.onPostExecute(str);
            if (str.startsWith("error:")) {
                this.ir.state = 0;
                AppnextWebView.this.io.put(this.ir.aQ, this.ir);
                AppnextWebView.a(AppnextWebView.this, this.ir, str.substring(7));
                return;
            }
            this.ir.state = 2;
            AppnextWebView.this.io.put(this.ir.aQ, this.ir);
            AppnextWebView.b(AppnextWebView.this, this.ir, str);
        }
    }

    private WebView v(Context context) {
        WebView webView = new WebView(context.getApplicationContext());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            webView.getSettings().setMixedContentMode(0);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null) {
                    return false;
                }
                if (!str.startsWith(Constants.HTTP)) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                webView.loadUrl(str);
                return true;
            }
        });
        return webView;
    }

    public final WebView a(Context context, String str, WebAppInterface webAppInterface, a aVar, String str2) {
        String str3;
        try {
            in.bX = webAppInterface;
            WebView webView = this.ip.get(str2);
            if (!(webView == null || webView.getParent() == null)) {
                ((ViewGroup) webView.getParent()).removeView(webView);
            }
            URL url = new URL(str);
            String str4 = url.getProtocol() + "://" + url.getHost();
            WebView webView2 = new WebView(context.getApplicationContext());
            webView2.getSettings().setJavaScriptEnabled(true);
            webView2.getSettings().setAppCacheEnabled(true);
            webView2.getSettings().setDomStorageEnabled(true);
            webView2.getSettings().setDatabaseEnabled(true);
            if (Build.VERSION.SDK_INT >= 21) {
                webView2.getSettings().setMixedContentMode(0);
            }
            if (Build.VERSION.SDK_INT >= 17) {
                webView2.getSettings().setMediaPlaybackRequiresUserGesture(false);
            }
            webView2.setWebChromeClient(new WebChromeClient());
            webView2.setWebViewClient(new WebViewClient() {
                public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (str == null) {
                        return false;
                    }
                    if (!str.startsWith(Constants.HTTP)) {
                        return super.shouldOverrideUrlLoading(webView, str);
                    }
                    webView.loadUrl(str);
                    return true;
                }
            });
            if (this.io.containsKey(str)) {
                if (this.io.get(str).state == 2 && !this.io.get(str).is.equals("")) {
                    str3 = "<script>" + this.io.get(str).is + "</script>";
                    webView2.loadDataWithBaseURL(str4, "<html><body>" + str3 + "</body></html>", (String) null, "UTF-8", (String) null);
                    this.ip.put(str2, webView2);
                    webView2.addJavascriptInterface(new WebInterface(), "Appnext");
                    return webView2;
                }
            }
            if (aVar != null) {
                str3 = "<script>" + aVar.J() + "</script>";
            } else {
                str3 = "<script src='" + str + "'></script>";
            }
            webView2.loadDataWithBaseURL(str4, "<html><body>" + str3 + "</body></html>", (String) null, "UTF-8", (String) null);
            this.ip.put(str2, webView2);
            webView2.addJavascriptInterface(new WebInterface(), "Appnext");
            return webView2;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final boolean ah(String str) {
        return this.ip.get(str) != null;
    }

    public final WebView ai(String str) {
        WebView webView = this.ip.get(str);
        if (!(webView == null || webView.getParent() == null)) {
            ((ViewGroup) webView.getParent()).removeView(webView);
        }
        return webView;
    }

    public final String aj(String str) {
        b bVar = this.io.get(str);
        if (bVar == null || bVar.state != 2) {
            return null;
        }
        return bVar.is;
    }

    public static void b(WebAppInterface webAppInterface) {
        in.bX = webAppInterface;
    }

    private void a(b bVar, String str) {
        new StringBuilder("webview error: ").append(str);
        synchronized (this.io) {
            Iterator<c> it = bVar.hF.iterator();
            while (it.hasNext()) {
                it.next().error(str);
            }
            bVar.hF.clear();
            this.io.remove(bVar.aQ);
        }
    }

    private void b(b bVar, String str) {
        new StringBuilder("downloaded ").append(str);
        synchronized (this.io) {
            Iterator<c> it = bVar.hF.iterator();
            while (it.hasNext()) {
                it.next().f(str);
            }
            bVar.hF.clear();
        }
    }

    private class WebInterface extends WebAppInterface {
        public WebInterface() {
        }

        @JavascriptInterface
        public void destroy(String str) {
            super.destroy(str);
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.destroy(str);
            }
        }

        @JavascriptInterface
        public void postView(String str) {
            super.postView(str);
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.postView(str);
            }
        }

        @JavascriptInterface
        public void openStore(String str) {
            super.openStore(str);
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.openStore(str);
            }
        }

        @JavascriptInterface
        public void play() {
            super.play();
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.play();
            }
        }

        @JavascriptInterface
        public String filterAds(String str) {
            super.filterAds(str);
            return AppnextWebView.in.bX != null ? AppnextWebView.in.bX.filterAds(str) : str;
        }

        @JavascriptInterface
        public String loadAds() {
            super.loadAds();
            return AppnextWebView.in.bX != null ? AppnextWebView.in.bX.loadAds() : "";
        }

        @JavascriptInterface
        public void openLink(String str) {
            super.openLink(str);
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.openLink(str);
            }
        }

        @JavascriptInterface
        public void gotoAppWall() {
            super.gotoAppWall();
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.gotoAppWall();
            }
        }

        @JavascriptInterface
        public void videoPlayed() {
            super.videoPlayed();
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.videoPlayed();
            }
        }

        @JavascriptInterface
        public void notifyImpression(String str) {
            super.notifyImpression(str);
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.notifyImpression(str);
            }
        }

        @JavascriptInterface
        public void jsError(String str) {
            super.jsError(str);
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.jsError(str);
            }
        }

        @JavascriptInterface
        public void openResultPage(String str) {
            super.openResultPage(str);
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.openResultPage(str);
            }
        }

        @JavascriptInterface
        public void logSTP(String str, String str2) {
            super.logSTP(str, str2);
            if (AppnextWebView.in.bX != null) {
                AppnextWebView.in.bX.logSTP(str, str2);
            }
        }
    }

    static /* synthetic */ void a(AppnextWebView appnextWebView, b bVar, String str) {
        new StringBuilder("webview error: ").append(str);
        synchronized (appnextWebView.io) {
            Iterator<c> it = bVar.hF.iterator();
            while (it.hasNext()) {
                it.next().error(str);
            }
            bVar.hF.clear();
            appnextWebView.io.remove(bVar.aQ);
        }
    }

    static /* synthetic */ void b(AppnextWebView appnextWebView, b bVar, String str) {
        new StringBuilder("downloaded ").append(str);
        synchronized (appnextWebView.io) {
            Iterator<c> it = bVar.hF.iterator();
            while (it.hasNext()) {
                it.next().f(str);
            }
            bVar.hF.clear();
        }
    }
}
