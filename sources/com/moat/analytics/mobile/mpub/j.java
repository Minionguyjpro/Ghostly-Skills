package com.moat.analytics.mobile.mpub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.s;
import com.moat.analytics.mobile.mpub.w;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

class j {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f1168a = 0;
    private boolean b = false;
    private boolean c = false;
    private final AtomicBoolean d = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public boolean f = false;
    private boolean g = false;
    private final WeakReference<WebView> h;
    private final Map<b, String> i;
    private final LinkedList<String> j;
    /* access modifiers changed from: private */
    public final long k;
    private final String l;
    private final List<String> m;
    private final a n;
    private final BroadcastReceiver o = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.d();
            } catch (Exception e) {
                n.a(e);
            }
            if (System.currentTimeMillis() - j.this.k > 30000) {
                j.this.i();
            }
        }
    };
    private final BroadcastReceiver p = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.e();
            } catch (Exception e) {
                n.a(e);
            }
        }
    };

    enum a {
        WEBVIEW,
        NATIVE_DISPLAY,
        NATIVE_VIDEO
    }

    j(WebView webView, a aVar) {
        this.h = new WeakReference<>(webView);
        this.n = aVar;
        this.j = new LinkedList<>();
        this.m = new ArrayList();
        this.i = new WeakHashMap();
        this.k = System.currentTimeMillis();
        this.l = String.format("javascript:(function(d,k){function l(){function d(a,b){var c=ipkn[b]||ipkn[kuea];if(c){var h=function(b){var c=b.b;c.ts=b.i;c.ticks=b.g;c.buffered=!0;a(c)};h(c.first);c.a.forEach(function(a){h(a)})}}function e(a){var b=a.a,c=a.c,h=a.b;a=a.f;var d=[];if(c)b[c]&&d.push(b[c].fn[0]);else for(key in b)if(b[key])for(var g=0,e=b[key].fn.length;g<e;g++)d.push(b[key].fn[g]);g=0;for(e=d.length;g<e;g++){var f=d[g];if('function'===typeof f)try{h?f(h):f()}catch(k){}a&&delete b[c]}}function f(a,b,c){'function'===typeof a&& (b===kuea&&c[b]?c[b].fn.push(a):c[b]={ts:+new Date,fn:[a]},c===yhgt&&d(a,b))}kuea=+new Date;iymv={};briz=!1;ewat=+new Date;bnkr=[];bjmk={};dptk={};uqaj={};ryup={};yhgt={};ipkn={};csif={};this.h=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash;this.aqzx=a.aqzx;this.appId=a.appId;this.metadata=a};this.nvsj=function(a){briz||(f(a,ewat,iymv),briz=!0)};this.bpsy=function(a,b){var c=b||kuea; c!==kuea&&bjmk[c]||f(a,c,bjmk)};this.qmrv=function(a,b){var c=b||kuea;c!==kuea&&uqaj[c]||f(a,c,uqaj)};this.lgpr=function(a,b){f(a,b||kuea,yhgt)};this.hgen=function(a,b){f(a,b||kuea,csif)};this.xrnk=function(a){delete yhgt[a||kuea]};this.vgft=function(a){return dptk[a||kuea]||!1};this.lkpu=function(a){return ryup[a||kuea]||!1};this.crts=function(a){var b={a:iymv,b:a,c:ewat};briz?e(b):bnkr.push(a)};this.mqjh=function(a){var b=a||kuea;dptk[b]=!0;var c={a:bjmk,f:!0};b!==kuea&&(c.b=a,c.c=a);e(c)};this.egpw= function(a){var b=a||kuea;ryup[b]=!0;var c={a:uqaj,f:!0};b!==kuea&&(c.b=a,c.c=a);e(c)};this.sglu=function(a){var b=a.adKey||kuea,c={a:yhgt,b:a.event||a,g:1,i:+new Date,f:!1};b!==kuea&&(c.c=a.adKey);a=0<Object.keys(yhgt).length;if(!a||!this.isNative)if(ipkn[b]){var d=ipkn[b].a.slice(-1)[0]||ipkn[b].first;JSON.stringify(c.b)==JSON.stringify(d.b)?d.g+=1:(5<=ipkn[b].a.length&&ipkn[b].a.shift(),ipkn[b].a.push(c))}else ipkn[b]={first:c,a:[]};a&&e(c);return a};this.ucbx=function(a){e({c:a.adKey||kuea,a:csif, b:a.event,f:!1})}}'undefined'===typeof d.MoatMAK&&(d.MoatMAK=new l,d.MoatMAK.h(k),d.__zMoatInit__=!0)})(window,%s);", new Object[]{h()});
        if (d("Initialize")) {
            IntentFilter intentFilter = new IntentFilter("UPDATE_METADATA");
            IntentFilter intentFilter2 = new IntentFilter("UPDATE_VIEW_INFO");
            i.a().a(s.b(), this.o, intentFilter);
            i.a().a(s.b(), this.p, intentFilter2);
            d();
            i.a().a(s.b(), this);
            p.a(3, "JavaScriptBridge", (Object) this, "bridge initialization succeeded");
        }
    }

    private boolean a(WebView webView) {
        return webView.getSettings().getJavaScriptEnabled();
    }

    static /* synthetic */ int b(j jVar) {
        int i2 = jVar.f1168a;
        jVar.f1168a = i2 + 1;
        return i2;
    }

    private void c() {
        for (Map.Entry<b, String> key : this.i.entrySet()) {
            b bVar = (b) key.getKey();
            if (bVar.e()) {
                g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.e}));
            }
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        try {
            if (w.a().f1186a != w.d.OFF) {
                if (!this.c) {
                    p.a(3, "JavaScriptBridge", (Object) this, "Attempting to establish communication (setting environment variables).");
                    this.c = true;
                }
                g(this.l);
            }
        } catch (Exception e2) {
            p.a("JavaScriptBridge", (Object) this, "Attempt failed to establish communication (did not set environment variables).", (Throwable) e2);
        }
    }

    private void d(b bVar) {
        p.a(3, "JavaScriptBridge", (Object) this, "Stopping view update loop");
        if (bVar != null) {
            i.a().a(bVar);
        }
    }

    private boolean d(String str) {
        WebView g2 = g();
        if (g2 == null) {
            p.a(6, "JavaScriptBridge", (Object) this, "WebView is null. Can't " + str);
            throw new n("WebView is null");
        } else if (a(g2)) {
            return true;
        } else {
            p.a(6, "JavaScriptBridge", (Object) this, "JavaScript is not enabled in the given WebView. Can't " + str);
            throw new n("JavaScript is not enabled in the WebView");
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        try {
            if (w.a().f1186a != w.d.OFF) {
                if (this.g) {
                    p.a(3, "JavaScriptBridge", (Object) this, "Can't send info, already cleaned up");
                    return;
                }
                if (f()) {
                    if (!this.b || g().getUrl() != null) {
                        if (g().getUrl() != null) {
                            this.b = true;
                        }
                        for (Map.Entry<b, String> key : this.i.entrySet()) {
                            b bVar = (b) key.getKey();
                            if (bVar == null || bVar.f() == null) {
                                p.a(3, "JavaScriptBridge", (Object) this, "Tracker has no subject");
                                if (bVar != null) {
                                    if (!bVar.f) {
                                    }
                                }
                                c(bVar);
                            }
                            if (bVar.e()) {
                                if (!this.d.get()) {
                                    g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.e}));
                                }
                                String format = String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.sglu(%s);}", new Object[]{bVar.h()});
                                if (Build.VERSION.SDK_INT >= 19) {
                                    g().evaluateJavascript(format, new ValueCallback<String>() {
                                        /* renamed from: a */
                                        public void onReceiveValue(String str) {
                                            String str2 = "null";
                                            if (str == null || str.equalsIgnoreCase(str2) || str.equalsIgnoreCase("false")) {
                                                j jVar = j.this;
                                                StringBuilder sb = new StringBuilder();
                                                sb.append("Received value is:");
                                                if (str != null) {
                                                    str2 = "(String)" + str;
                                                }
                                                sb.append(str2);
                                                p.a(3, "JavaScriptBridge", (Object) jVar, sb.toString());
                                                if (j.this.f1168a >= 150) {
                                                    p.a(3, "JavaScriptBridge", (Object) j.this, "Giving up on finding ad");
                                                    j.this.b();
                                                }
                                                j.b(j.this);
                                                if (str != null && str.equalsIgnoreCase("false") && !j.this.e) {
                                                    boolean unused = j.this.e = true;
                                                    p.a(3, "JavaScriptBridge", (Object) j.this, "Bridge connection established");
                                                }
                                            } else if (str.equalsIgnoreCase("true")) {
                                                if (!j.this.f) {
                                                    boolean unused2 = j.this.f = true;
                                                    p.a(3, "JavaScriptBridge", (Object) j.this, "Javascript has found ad");
                                                    j.this.a();
                                                }
                                                int unused3 = j.this.f1168a = 0;
                                            } else {
                                                p.a(3, "JavaScriptBridge", (Object) j.this, "Received unusual value from Javascript:" + str);
                                            }
                                        }
                                    });
                                } else {
                                    g().loadUrl(format);
                                }
                            }
                        }
                        return;
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append("WebView became null");
                sb.append(g() == null ? "" : "based on null url");
                sb.append(", stopping tracking loop");
                p.a(3, "JavaScriptBridge", (Object) this, sb.toString());
                b();
            }
        } catch (Exception e2) {
            n.a(e2);
            b();
        }
    }

    private void e(String str) {
        if (this.m.size() >= 50) {
            this.m.subList(0, 25).clear();
        }
        this.m.add(str);
    }

    private void f(String str) {
        if (this.d.get()) {
            g(str);
        } else {
            e(str);
        }
    }

    private boolean f() {
        return g() != null;
    }

    private WebView g() {
        return (WebView) this.h.get();
    }

    private void g(String str) {
        if (this.g) {
            p.a(3, "JavaScriptBridge", (Object) this, "Can't send, already cleaned up");
        } else if (f()) {
            p.b(2, "JavaScriptBridge", this, str);
            if (Build.VERSION.SDK_INT >= 19) {
                g().evaluateJavascript(str, (ValueCallback) null);
            } else {
                g().loadUrl(str);
            }
        }
    }

    private String h() {
        try {
            s.a c2 = s.c();
            s.b d2 = s.d();
            HashMap hashMap = new HashMap();
            String a2 = c2.a();
            String b2 = c2.b();
            String c3 = c2.c();
            String num = Integer.toString(Build.VERSION.SDK_INT);
            String a3 = s.a();
            String str = "0";
            String str2 = this.n == a.WEBVIEW ? str : "1";
            String str3 = d2.e ? "1" : str;
            if (d2.d) {
                str = "1";
            }
            hashMap.put("versionHash", "422d7e65812d34458dfd0c5f14e8141470b6e2ae");
            hashMap.put("appName", a2);
            hashMap.put("namespace", "MPUB");
            hashMap.put("version", "2.6.6");
            hashMap.put("deviceOS", num);
            hashMap.put("isNative", str2);
            hashMap.put("appId", b2);
            hashMap.put("source", c3);
            hashMap.put("carrier", d2.b);
            hashMap.put("sim", d2.f1185a);
            hashMap.put("phone", String.valueOf(d2.c));
            hashMap.put("buildFp", Build.FINGERPRINT);
            hashMap.put("buildModel", Build.MODEL);
            hashMap.put("buildMfg", Build.MANUFACTURER);
            hashMap.put("buildBrand", Build.BRAND);
            hashMap.put("buildProduct", Build.PRODUCT);
            hashMap.put("buildTags", Build.TAGS);
            hashMap.put("f1", str);
            hashMap.put("f2", str3);
            if (a3 != null) {
                hashMap.put("aqzx", a3);
            }
            return new JSONObject(hashMap).toString();
        } catch (Exception unused) {
            return "{}";
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        p.a(3, "JavaScriptBridge", (Object) this, "Stopping metadata reporting loop");
        i.a().a(this);
        i.a().a(s.b(), this.o);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        p.a(3, "JavaScriptBridge", (Object) this, "webViewReady");
        if (this.d.compareAndSet(false, true)) {
            p.a(3, "JavaScriptBridge", (Object) this, "webViewReady first time");
            i();
            for (String g2 : this.m) {
                g(g2);
            }
            this.m.clear();
        }
        c();
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        if (bVar != null) {
            p.a(3, "JavaScriptBridge", (Object) this, "adding tracker" + bVar.e);
            this.i.put(bVar, "");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        f(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.crts(%s);}", new Object[]{str}));
    }

    /* access modifiers changed from: package-private */
    public void a(String str, JSONObject jSONObject) {
        if (this.g) {
            p.a(3, "JavaScriptBridge", (Object) this, "Can't dispatch, already cleaned up");
            return;
        }
        String jSONObject2 = jSONObject.toString();
        if (!this.d.get() || !f()) {
            this.j.add(jSONObject2);
            return;
        }
        g(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{str, jSONObject2}));
    }

    /* access modifiers changed from: package-private */
    public void b() {
        p.a(3, "JavaScriptBridge", (Object) this, "Cleaning up");
        this.g = true;
        i();
        for (Map.Entry<b, String> key : this.i.entrySet()) {
            d((b) key.getKey());
        }
        this.i.clear();
        i.a().a(s.b(), this.p);
    }

    /* access modifiers changed from: package-private */
    public void b(b bVar) {
        if (d("startTracking")) {
            p.a(3, "JavaScriptBridge", (Object) this, "Starting tracking on tracker" + bVar.e);
            g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.e}));
            i.a().a(s.b(), bVar);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        p.a(3, "JavaScriptBridge", (Object) this, "markUserInteractionEvent:" + str);
        f(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.ucbx(%s);}", new Object[]{str}));
    }

    /* access modifiers changed from: package-private */
    public void c(b bVar) {
        n nVar = null;
        if (!this.g) {
            try {
                if (d("stopTracking")) {
                    try {
                        p.a(3, "JavaScriptBridge", (Object) this, "Ending tracking on tracker" + bVar.e);
                        g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.egpw(\"%s\");}", new Object[]{bVar.e}));
                    } catch (Exception e2) {
                        p.a("JavaScriptBridge", (Object) this, "Failed to end impression.", (Throwable) e2);
                    }
                }
            } catch (n e3) {
                nVar = e3;
            }
            if (this.n == a.NATIVE_DISPLAY) {
                d(bVar);
            } else {
                b();
            }
            this.i.remove(bVar);
        }
        if (nVar != null) {
            throw nVar;
        }
    }

    /* access modifiers changed from: package-private */
    public void c(String str) {
        p.a(3, "JavaScriptBridge", (Object) this, "flushDispatchQueue");
        if (this.j.size() >= 200) {
            LinkedList linkedList = new LinkedList();
            for (int i2 = 0; i2 < 10; i2++) {
                linkedList.addFirst(this.j.removeFirst());
            }
            int min = Math.min(Math.min(this.j.size() / 200, 10) + 200, this.j.size());
            for (int i3 = 0; i3 < min; i3++) {
                this.j.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.j.addFirst((String) it.next());
            }
        }
        if (!this.j.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            String str2 = "";
            int i4 = 1;
            while (!this.j.isEmpty() && i4 < 200) {
                i4++;
                String removeFirst = this.j.removeFirst();
                if (sb.length() + removeFirst.length() > 2000) {
                    break;
                }
                sb.append(str2);
                sb.append(removeFirst);
                str2 = ",";
            }
            g(String.format("javascript:%s.dispatchMany([%s])", new Object[]{str, sb.toString()}));
        }
        this.j.clear();
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            super.finalize();
            p.a(3, "JavaScriptBridge", (Object) this, "finalize");
            b();
        } catch (Exception e2) {
            n.a(e2);
        }
    }
}
