package com.yandex.metrica.impl;

import android.os.Bundle;
import com.yandex.metrica.impl.utils.f;
import org.json.JSONException;
import org.json.JSONObject;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private JSONObject f701a = new JSONObject();
    private long b;
    private boolean c;
    private f.a d = f.a.d();
    private final f e = new f();

    /* renamed from: com.yandex.metrica.impl.a$a  reason: collision with other inner class name */
    public static final class C0030a {

        /* renamed from: a  reason: collision with root package name */
        public final String f702a;
        public final long b;

        public C0030a(String str, long j) {
            this.f702a = str;
            this.b = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                C0030a aVar = (C0030a) obj;
                if (this.b != aVar.b) {
                    return false;
                }
                String str = this.f702a;
                String str2 = aVar.f702a;
                return str == null ? str2 == null : str.equals(str2);
            }
        }

        public int hashCode() {
            String str = this.f702a;
            int hashCode = str != null ? str.hashCode() : 0;
            long j = this.b;
            return (hashCode * 31) + ((int) (j ^ (j >>> 32)));
        }
    }

    public a(String str, long j) {
        this.b = j;
        try {
            this.f701a = new JSONObject(str);
        } catch (JSONException unused) {
            this.f701a = new JSONObject();
            this.b = 0;
        }
    }

    public synchronized void a() {
        this.f701a = new JSONObject();
        this.b = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.yandex.metrica.impl.utils.f r0 = r3.e     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            com.yandex.metrica.impl.utils.f$a r1 = r3.d     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            int r1 = r1.b()     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            java.lang.String r2 = "App Environment"
            java.lang.String r4 = r0.a(r4, r1, r2)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            com.yandex.metrica.impl.utils.f r0 = r3.e     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            com.yandex.metrica.impl.utils.f$a r1 = r3.d     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            int r1 = r1.c()     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            java.lang.String r2 = "App Environment"
            java.lang.String r5 = r0.a(r5, r1, r2)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            org.json.JSONObject r0 = r3.f701a     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            boolean r0 = r0.has(r4)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            if (r0 == 0) goto L_0x0038
            org.json.JSONObject r0 = r3.f701a     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            java.lang.String r0 = r0.getString(r4)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            if (r5 == 0) goto L_0x0033
            boolean r0 = r5.equals(r0)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            if (r0 != 0) goto L_0x0036
        L_0x0033:
            r3.b(r4, r5)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
        L_0x0036:
            monitor-exit(r3)
            return
        L_0x0038:
            if (r5 == 0) goto L_0x003d
            r3.b(r4, r5)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
        L_0x003d:
            monitor-exit(r3)
            return
        L_0x003f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x0042:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.a.a(java.lang.String, java.lang.String):void");
    }

    public synchronized void a(Bundle bundle) {
        for (String str : bundle.keySet()) {
            a(str, bundle.getString(str));
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(String str, String str2) throws JSONException {
        if (this.f701a.length() >= this.d.a()) {
            if (this.d.a() != this.f701a.length() || !this.f701a.has(str)) {
                this.e.b(str, this.d.a(), "App Environment");
                return;
            }
        }
        this.f701a.put(str, str2);
        this.c = true;
    }

    public synchronized C0030a b() {
        if (this.c) {
            this.b++;
            this.c = false;
        }
        return new C0030a(this.f701a.toString(), this.b);
    }

    public synchronized String toString() {
        return "Map size " + this.f701a.length() + ". Is changed " + this.c + ". Current revision " + this.b;
    }
}
