package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.yandex.metrica.impl.ob.u;
import org.json.JSONObject;

public final class k {

    /* renamed from: a  reason: collision with root package name */
    private Context f772a;
    /* access modifiers changed from: private */
    public ContentValues b;
    private u c;

    public k(Context context) {
        this.f772a = context;
    }

    public k a(ContentValues contentValues) {
        this.b = contentValues;
        return this;
    }

    public k a(u uVar) {
        this.c = uVar;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Location b() {
        if (!this.c.j().m()) {
            return null;
        }
        Location t = this.c.j().t();
        if (t != null) {
            return t;
        }
        Location c2 = y.a(this.f772a).c();
        return c2 == null ? y.a(this.f772a).d() : c2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x00a9 A[SYNTHETIC, Splitter:B:12:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0176  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.yandex.metrica.impl.h r7, com.yandex.metrica.impl.a.C0030a r8) {
        /*
            r6 = this;
            android.content.ContentValues r0 = r6.b
            java.lang.String r1 = r7.a()
            java.lang.String r2 = "name"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.b
            java.lang.String r1 = r7.b()
            java.lang.String r2 = "value"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.b
            int r1 = r7.c()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "type"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.b
            int r1 = r7.d()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "custom_type"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.b
            java.lang.String r1 = r7.i()
            java.lang.String r2 = "error_environment"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.b
            java.lang.String r1 = r7.k()
            java.lang.String r2 = "user_info"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.b
            int r1 = r7.o()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "truncated"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.b
            android.content.Context r1 = r6.f772a
            java.lang.String r2 = "connectivity"
            java.lang.Object r2 = r1.getSystemService(r2)
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2
            java.lang.String r3 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r1 = com.yandex.metrica.impl.al.a(r1, r3)
            r3 = 1
            if (r1 == 0) goto L_0x0083
            android.net.NetworkInfo r1 = r2.getActiveNetworkInfo()
            if (r1 == 0) goto L_0x0083
            int r2 = r1.getType()
            if (r2 != r3) goto L_0x007b
            goto L_0x0084
        L_0x007b:
            int r1 = r1.getType()
            if (r1 != 0) goto L_0x0083
            r3 = 0
            goto L_0x0084
        L_0x0083:
            r3 = 2
        L_0x0084:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            java.lang.String r2 = "connection_type"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.b
            java.lang.String r1 = r8.f702a
            java.lang.String r2 = "app_environment"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.b
            long r1 = r8.b
            java.lang.Long r8 = java.lang.Long.valueOf(r1)
            java.lang.String r1 = "app_environment_revision"
            r0.put(r1, r8)
            android.location.Location r8 = r6.b()
            if (r8 == 0) goto L_0x013c
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x013b }
            r0.<init>()     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "lat"
            double r2 = r8.getLatitude()     // Catch:{ Exception -> 0x013b }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "lon"
            double r2 = r8.getLongitude()     // Catch:{ Exception -> 0x013b }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "timestamp"
            long r2 = r8.getTime()     // Catch:{ Exception -> 0x013b }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Exception -> 0x013b }
            r0.putOpt(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "precision"
            boolean r2 = r8.hasAccuracy()     // Catch:{ Exception -> 0x013b }
            r3 = 0
            if (r2 == 0) goto L_0x00df
            float r2 = r8.getAccuracy()     // Catch:{ Exception -> 0x013b }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x013b }
            goto L_0x00e0
        L_0x00df:
            r2 = r3
        L_0x00e0:
            r0.putOpt(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "direction"
            boolean r2 = r8.hasBearing()     // Catch:{ Exception -> 0x013b }
            if (r2 == 0) goto L_0x00f4
            float r2 = r8.getBearing()     // Catch:{ Exception -> 0x013b }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x013b }
            goto L_0x00f5
        L_0x00f4:
            r2 = r3
        L_0x00f5:
            r0.putOpt(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "speed"
            boolean r2 = r8.hasSpeed()     // Catch:{ Exception -> 0x013b }
            if (r2 == 0) goto L_0x0109
            float r2 = r8.getSpeed()     // Catch:{ Exception -> 0x013b }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x013b }
            goto L_0x010a
        L_0x0109:
            r2 = r3
        L_0x010a:
            r0.putOpt(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "altitude"
            boolean r2 = r8.hasAltitude()     // Catch:{ Exception -> 0x013b }
            if (r2 == 0) goto L_0x011e
            double r4 = r8.getAltitude()     // Catch:{ Exception -> 0x013b }
            java.lang.Double r2 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x013b }
            goto L_0x011f
        L_0x011e:
            r2 = r3
        L_0x011f:
            r0.putOpt(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "provider"
            java.lang.String r8 = r8.getProvider()     // Catch:{ Exception -> 0x013b }
            java.lang.String r8 = com.yandex.metrica.impl.bi.c(r8, r3)     // Catch:{ Exception -> 0x013b }
            r0.putOpt(r1, r8)     // Catch:{ Exception -> 0x013b }
            android.content.ContentValues r8 = r6.b     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "location_info"
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x013b }
            r8.put(r1, r0)     // Catch:{ Exception -> 0x013b }
            goto L_0x013c
        L_0x013b:
        L_0x013c:
            android.content.Context r8 = r6.f772a
            com.yandex.metrica.impl.ob.ef r8 = com.yandex.metrica.impl.ob.ef.a((android.content.Context) r8)
            com.yandex.metrica.impl.k$2 r0 = new com.yandex.metrica.impl.k$2
            r0.<init>()
            r8.a((com.yandex.metrica.impl.ob.eh) r0)
            com.yandex.metrica.impl.k$1 r0 = new com.yandex.metrica.impl.k$1
            r0.<init>()
            r8.a((com.yandex.metrica.impl.ob.ea) r0)
            android.content.Context r8 = r6.f772a
            com.yandex.metrica.impl.bm r8 = com.yandex.metrica.impl.bm.a((android.content.Context) r8)
            org.json.JSONArray r7 = r7.g()
            org.json.JSONArray r0 = r8.a()
            int r1 = r0.length()
            int r2 = r7.length()
            java.lang.String r3 = "wifi_network_info"
            if (r1 <= r2) goto L_0x0176
            android.content.ContentValues r7 = r6.b
            java.lang.String r0 = r0.toString()
            r7.put(r3, r0)
            goto L_0x017f
        L_0x0176:
            android.content.ContentValues r0 = r6.b
            java.lang.String r7 = r7.toString()
            r0.put(r3, r7)
        L_0x017f:
            r6.a((com.yandex.metrica.impl.bm) r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.k.a(com.yandex.metrica.impl.h, com.yandex.metrica.impl.a$a):void");
    }

    /* access modifiers changed from: package-private */
    public void a(bm bmVar) {
        String b2 = bmVar.b(this.f772a);
        if (!TextUtils.isEmpty(b2)) {
            int c2 = bmVar.c(this.f772a);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ssid", b2);
                jSONObject.put("state", c2);
                this.b.put("wifi_access_point", jSONObject.toString());
            } catch (Exception unused) {
            }
        }
    }

    public void a() {
        ba h = this.c.h();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("dId", h.c());
            jSONObject.putOpt("uId", h.b());
            jSONObject.putOpt("appVer", h.x());
            jSONObject.putOpt("appBuild", h.z());
            jSONObject.putOpt("kitVer", h.h());
            jSONObject.putOpt("clientKitVer", h.i());
            jSONObject.putOpt("kitBuildNumber", h.k());
            jSONObject.putOpt("kitBuildType", h.l());
            jSONObject.putOpt("osVer", h.q());
            jSONObject.putOpt("osApiLev", Integer.valueOf(h.r()));
            jSONObject.putOpt("lang", h.w());
            jSONObject.putOpt("root", h.F());
            jSONObject.putOpt("app_debuggable", h.N());
        } catch (Exception unused) {
            jSONObject = new JSONObject();
        }
        this.b.put("report_request_parameters", jSONObject.toString());
    }
}
