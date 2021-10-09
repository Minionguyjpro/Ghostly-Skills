package com.appnext.core;

import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class p {
    protected static final String hD = "https://cdn.appnext.com/tools/sdk/confign";
    protected HashMap<String, String> aR = null;
    protected HashMap<String, Object> hE = null;
    private ArrayList<a> hF;
    /* access modifiers changed from: private */
    public int state = 0;

    public interface a {
        void b(HashMap<String, Object> hashMap);

        void error(String str);
    }

    /* access modifiers changed from: protected */
    public abstract String getUrl();

    /* access modifiers changed from: protected */
    public abstract HashMap<String, String> n();

    /* access modifiers changed from: protected */
    public abstract HashMap<String, String> o();

    public final synchronized void a(a aVar) {
        a((Context) null, aVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0061, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(android.content.Context r6, com.appnext.core.p.a r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.ArrayList<com.appnext.core.p$a> r0 = r5.hF     // Catch:{ all -> 0x0062 }
            if (r0 != 0) goto L_0x000c
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0062 }
            r0.<init>()     // Catch:{ all -> 0x0062 }
            r5.hF = r0     // Catch:{ all -> 0x0062 }
        L_0x000c:
            int r0 = r5.state     // Catch:{ all -> 0x0062 }
            r1 = 2
            if (r0 != r1) goto L_0x001a
            if (r7 == 0) goto L_0x0060
            java.util.HashMap<java.lang.String, java.lang.Object> r6 = r5.hE     // Catch:{ all -> 0x0062 }
            r7.b(r6)     // Catch:{ all -> 0x0062 }
            monitor-exit(r5)
            return
        L_0x001a:
            int r0 = r5.state     // Catch:{ all -> 0x0062 }
            if (r0 != 0) goto L_0x0059
            r0 = 1
            r5.state = r0     // Catch:{ all -> 0x0062 }
            java.lang.String r2 = r5.getUrl()     // Catch:{ all -> 0x0062 }
            if (r6 == 0) goto L_0x0030
            java.lang.String r3 = "pck"
            java.lang.String r6 = r6.getPackageName()     // Catch:{ all -> 0x0062 }
            r5.q(r3, r6)     // Catch:{ all -> 0x0062 }
        L_0x0030:
            java.lang.String r6 = "vid"
            java.lang.String r3 = com.appnext.core.f.bi()     // Catch:{ all -> 0x0062 }
            r5.q(r6, r3)     // Catch:{ all -> 0x0062 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0062 }
            java.lang.String r3 = "start loading config from "
            r6.<init>(r3)     // Catch:{ all -> 0x0062 }
            r6.append(r2)     // Catch:{ all -> 0x0062 }
            com.appnext.core.p$b r6 = new com.appnext.core.p$b     // Catch:{ all -> 0x0062 }
            r3 = 0
            r6.<init>()     // Catch:{ all -> 0x0062 }
            java.util.concurrent.Executor r3 = android.os.AsyncTask.THREAD_POOL_EXECUTOR     // Catch:{ all -> 0x0062 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0062 }
            r4 = 0
            r1[r4] = r2     // Catch:{ all -> 0x0062 }
            java.util.HashMap r2 = r5.n()     // Catch:{ all -> 0x0062 }
            r1[r0] = r2     // Catch:{ all -> 0x0062 }
            r6.executeOnExecutor(r3, r1)     // Catch:{ all -> 0x0062 }
        L_0x0059:
            if (r7 == 0) goto L_0x0060
            java.util.ArrayList<com.appnext.core.p$a> r6 = r5.hF     // Catch:{ all -> 0x0062 }
            r6.add(r7)     // Catch:{ all -> 0x0062 }
        L_0x0060:
            monitor-exit(r5)
            return
        L_0x0062:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.p.a(android.content.Context, com.appnext.core.p$a):void");
    }

    private String ad(String str) {
        HashMap<String, String> hashMap = this.aR;
        if (hashMap == null) {
            return str;
        }
        for (String next : hashMap.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = "?";
            if (str.contains(str2)) {
                str2 = "&";
            }
            sb.append(str2);
            sb.append(next);
            sb.append("=");
            sb.append(this.aR.get(next));
            str = sb.toString();
        }
        return str;
    }

    public void a(HashMap<String, String> hashMap) {
        this.aR = hashMap;
    }

    public final void q(String str, String str2) {
        if (this.aR == null) {
            this.aR = new HashMap<>();
        }
        this.aR.put(str, str2);
    }

    public final void r(String str, String str2) {
        if (this.aR == null) {
            this.aR = new HashMap<>();
        }
        if (!this.aR.containsKey(str)) {
            this.aR.put(str, str2);
        }
    }

    public final HashMap<String, Object> bn() {
        return this.hE;
    }

    public final void s(String str, String str2) {
        if (this.hE == null) {
            this.hE = new HashMap<>();
        }
        this.hE.put(str, str2);
    }

    public final boolean isLoaded() {
        return this.state == 2;
    }

    public final String get(String str) {
        String value = getValue(str);
        if (value != null) {
            return value;
        }
        if (o().containsKey(str)) {
            return o().get(str);
        }
        return null;
    }

    public final String get(String str, String str2) {
        return getValue(str) == null ? str2 : getValue(str);
    }

    private class b extends AsyncTask<Object, Void, String> {
        private b() {
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a(objArr);
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            super.onPostExecute(str);
            if (str == null) {
                int unused = p.this.state = 0;
                p.a(p.this, "unknown error");
            } else if (str.startsWith("error:")) {
                int unused2 = p.this.state = 0;
                p.a(p.this, str.substring(7));
            } else {
                try {
                    HashMap<String, Object> ae = p.ae(str);
                    if (p.this.hE == null) {
                        p.this.hE = ae;
                    } else {
                        p.this.hE.putAll(ae);
                    }
                    int unused3 = p.this.state = 2;
                    p.a(p.this, (HashMap) p.this.hE);
                } catch (Throwable th) {
                    new StringBuilder("error ").append(th.getMessage());
                    int unused4 = p.this.state = 0;
                    p.a(p.this, "parsing error");
                }
            }
        }

        protected static String a(Object... objArr) {
            try {
                return f.a(objArr[0], (HashMap<String, String>) objArr[1]);
            } catch (HttpRetryException e) {
                return "error: " + e.getReason();
            } catch (IOException unused) {
                return "error: network problem";
            } catch (Throwable unused2) {
                return "error: Internal error";
            }
        }

        /* access modifiers changed from: protected */
        public final void ag(String str) {
            super.onPostExecute(str);
            if (str == null) {
                int unused = p.this.state = 0;
                p.a(p.this, "unknown error");
            } else if (str.startsWith("error:")) {
                int unused2 = p.this.state = 0;
                p.a(p.this, str.substring(7));
            } else {
                try {
                    HashMap<String, Object> ae = p.ae(str);
                    if (p.this.hE == null) {
                        p.this.hE = ae;
                    } else {
                        p.this.hE.putAll(ae);
                    }
                    int unused3 = p.this.state = 2;
                    p.a(p.this, (HashMap) p.this.hE);
                } catch (Throwable th) {
                    new StringBuilder("error ").append(th.getMessage());
                    int unused4 = p.this.state = 0;
                    p.a(p.this, "parsing error");
                }
            }
        }
    }

    protected static HashMap<String, Object> ae(String str) throws JSONException {
        HashMap<String, Object> hashMap = new HashMap<>();
        JSONObject jSONObject = new JSONObject(str);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String string = jSONObject.getString(next);
            try {
                JSONObject jSONObject2 = new JSONObject(string);
                Iterator<String> keys2 = jSONObject2.keys();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    String string2 = jSONObject2.getString(next2);
                    hashMap.put(next + "_" + next2, string2);
                }
            } catch (Throwable unused) {
                hashMap.put(next, string);
            }
        }
        return hashMap;
    }

    private void af(String str) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(this.hF).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar != null) {
                    aVar.error(str);
                }
            }
            this.hF.clear();
        }
    }

    private void e(HashMap<String, Object> hashMap) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(this.hF).iterator();
            while (it.hasNext()) {
                ((a) it.next()).b(hashMap);
            }
            this.hF.clear();
        }
    }

    public final String getValue(String str) {
        HashMap<String, Object> hashMap = this.hE;
        if (hashMap != null && hashMap.containsKey(str)) {
            return (String) this.hE.get(str);
        }
        return null;
    }

    static /* synthetic */ void a(p pVar, String str) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(pVar.hF).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar != null) {
                    aVar.error(str);
                }
            }
            pVar.hF.clear();
        }
    }

    static /* synthetic */ void a(p pVar, HashMap hashMap) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(pVar.hF).iterator();
            while (it.hasNext()) {
                ((a) it.next()).b(hashMap);
            }
            pVar.hF.clear();
        }
    }
}
