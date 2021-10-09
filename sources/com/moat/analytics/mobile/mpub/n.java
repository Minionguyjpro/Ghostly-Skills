package com.moat.analytics.mobile.mpub;

import android.util.Log;

class n extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private static final Long f1176a = 60000L;
    private static Long b;
    private static Exception c = null;

    n(String str) {
        super(str);
    }

    static String a(String str, Exception exc) {
        if (exc instanceof n) {
            return str + " failed: " + exc.getMessage();
        }
        return str + " failed unexpectedly";
    }

    static void a() {
        Exception exc = c;
        if (exc != null) {
            b(exc);
            c = null;
        }
    }

    static void a(Exception exc) {
        if (w.a().b) {
            Log.e("MoatException", Log.getStackTraceString(exc));
        } else {
            b(exc);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:7|(4:9|10|11|(1:13))|14|(1:16)(1:17)|18|(5:19|20|21|(1:23)(2:24|25)|26)|27|(6:29|30|31|32|33|34)|40|41|(2:45|51)(1:50)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x00eb */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01a4 A[Catch:{ Exception -> 0x01b0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(java.lang.Exception r13) {
        /*
            java.lang.String r0 = ""
            com.moat.analytics.mobile.mpub.w r1 = com.moat.analytics.mobile.mpub.w.a()     // Catch:{ Exception -> 0x01b0 }
            com.moat.analytics.mobile.mpub.w$d r1 = r1.f1186a     // Catch:{ Exception -> 0x01b0 }
            com.moat.analytics.mobile.mpub.w$d r2 = com.moat.analytics.mobile.mpub.w.d.ON     // Catch:{ Exception -> 0x01b0 }
            if (r1 != r2) goto L_0x01ae
            com.moat.analytics.mobile.mpub.w r1 = com.moat.analytics.mobile.mpub.w.a()     // Catch:{ Exception -> 0x01b0 }
            int r1 = r1.e     // Catch:{ Exception -> 0x01b0 }
            if (r1 != 0) goto L_0x0015
            return
        L_0x0015:
            r2 = 100
            if (r1 >= r2) goto L_0x0029
            double r2 = (double) r1
            r4 = 4636737291354636288(0x4059000000000000, double:100.0)
            java.lang.Double.isNaN(r2)
            double r2 = r2 / r4
            double r4 = java.lang.Math.random()     // Catch:{ Exception -> 0x01b0 }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0029
            return
        L_0x0029:
            java.lang.String r2 = "https://px.moatads.com/pixel.gif?e=0&i=MOATSDK1&ac=1"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b0 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x01b0 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b0 }
            r2.<init>()     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r4 = "&zt="
            r2.append(r4)     // Catch:{ Exception -> 0x01b0 }
            boolean r4 = r13 instanceof com.moat.analytics.mobile.mpub.n     // Catch:{ Exception -> 0x01b0 }
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0042
            r4 = 1
            goto L_0x0043
        L_0x0042:
            r4 = 0
        L_0x0043:
            r2.append(r4)     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x01b0 }
            r3.append(r2)     // Catch:{ Exception -> 0x01b0 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b0 }
            r2.<init>()     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r4 = "&zr="
            r2.append(r4)     // Catch:{ Exception -> 0x01b0 }
            r2.append(r1)     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x01b0 }
            r3.append(r1)     // Catch:{ Exception -> 0x01b0 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b4 }
            r1.<init>()     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r2 = "&zm="
            r1.append(r2)     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r2 = r13.getMessage()     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r4 = "UTF-8"
            if (r2 != 0) goto L_0x0076
            java.lang.String r2 = "null"
            goto L_0x0086
        L_0x0076:
            java.lang.String r2 = r13.getMessage()     // Catch:{ Exception -> 0x00b4 }
            byte[] r2 = r2.getBytes(r4)     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r2 = android.util.Base64.encodeToString(r2, r6)     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r2 = java.net.URLEncoder.encode(r2, r4)     // Catch:{ Exception -> 0x00b4 }
        L_0x0086:
            r1.append(r2)     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00b4 }
            r3.append(r1)     // Catch:{ Exception -> 0x00b4 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b4 }
            r1.<init>()     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r2 = "&k="
            r1.append(r2)     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r13 = android.util.Log.getStackTraceString(r13)     // Catch:{ Exception -> 0x00b4 }
            byte[] r13 = r13.getBytes(r4)     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r13 = android.util.Base64.encodeToString(r13, r6)     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r13 = java.net.URLEncoder.encode(r13, r4)     // Catch:{ Exception -> 0x00b4 }
            r1.append(r13)     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r13 = r1.toString()     // Catch:{ Exception -> 0x00b4 }
            r3.append(r13)     // Catch:{ Exception -> 0x00b4 }
        L_0x00b4:
            java.lang.String r13 = "MPUB"
            java.lang.String r1 = "&zMoatMMAKv=422d7e65812d34458dfd0c5f14e8141470b6e2ae"
            r3.append(r1)     // Catch:{ Exception -> 0x00e6 }
            java.lang.String r1 = "2.6.6"
            com.moat.analytics.mobile.mpub.s$a r2 = com.moat.analytics.mobile.mpub.s.c()     // Catch:{ Exception -> 0x00e4 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e4 }
            r4.<init>()     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r7 = "&zMoatMMAKan="
            r4.append(r7)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r7 = r2.a()     // Catch:{ Exception -> 0x00e4 }
            r4.append(r7)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00e4 }
            r3.append(r4)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r2 = r2.b()     // Catch:{ Exception -> 0x00e4 }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00eb }
            java.lang.String r0 = java.lang.Integer.toString(r4)     // Catch:{ Exception -> 0x00eb }
            goto L_0x00eb
        L_0x00e4:
            r2 = r0
            goto L_0x00eb
        L_0x00e6:
            r1 = r0
            goto L_0x00ea
        L_0x00e8:
            r13 = r0
            r1 = r13
        L_0x00ea:
            r2 = r1
        L_0x00eb:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b0 }
            r4.<init>()     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r7 = "&d=Android:"
            r4.append(r7)     // Catch:{ Exception -> 0x01b0 }
            r4.append(r13)     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r13 = ":"
            r4.append(r13)     // Catch:{ Exception -> 0x01b0 }
            r4.append(r2)     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r13 = ":-"
            r4.append(r13)     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r13 = r4.toString()     // Catch:{ Exception -> 0x01b0 }
            r3.append(r13)     // Catch:{ Exception -> 0x01b0 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b0 }
            r13.<init>()     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r2 = "&bo="
            r13.append(r2)     // Catch:{ Exception -> 0x01b0 }
            r13.append(r1)     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x01b0 }
            r3.append(r13)     // Catch:{ Exception -> 0x01b0 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b0 }
            r13.<init>()     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r1 = "&bd="
            r13.append(r1)     // Catch:{ Exception -> 0x01b0 }
            r13.append(r0)     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x01b0 }
            r3.append(r13)     // Catch:{ Exception -> 0x01b0 }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x01b0 }
            java.lang.Long r13 = java.lang.Long.valueOf(r0)     // Catch:{ Exception -> 0x01b0 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b0 }
            r0.<init>()     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r1 = "&t="
            r0.append(r1)     // Catch:{ Exception -> 0x01b0 }
            r0.append(r13)     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01b0 }
            r3.append(r0)     // Catch:{ Exception -> 0x01b0 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b0 }
            r0.<init>()     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r1 = "&de="
            r0.append(r1)     // Catch:{ Exception -> 0x01b0 }
            java.util.Locale r1 = java.util.Locale.ROOT     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r2 = "%.0f"
            java.lang.Object[] r4 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x01b0 }
            double r7 = java.lang.Math.random()     // Catch:{ Exception -> 0x01b0 }
            r9 = 4621819117588971520(0x4024000000000000, double:10.0)
            r11 = 4622945017495814144(0x4028000000000000, double:12.0)
            double r9 = java.lang.Math.pow(r9, r11)     // Catch:{ Exception -> 0x01b0 }
            double r7 = r7 * r9
            double r7 = java.lang.Math.floor(r7)     // Catch:{ Exception -> 0x01b0 }
            java.lang.Double r5 = java.lang.Double.valueOf(r7)     // Catch:{ Exception -> 0x01b0 }
            r4[r6] = r5     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r1 = java.lang.String.format(r1, r2, r4)     // Catch:{ Exception -> 0x01b0 }
            r0.append(r1)     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01b0 }
            r3.append(r0)     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r0 = "&cs=0"
            r3.append(r0)     // Catch:{ Exception -> 0x01b0 }
            java.lang.Long r0 = b     // Catch:{ Exception -> 0x01b0 }
            if (r0 == 0) goto L_0x01a4
            long r0 = r13.longValue()     // Catch:{ Exception -> 0x01b0 }
            java.lang.Long r2 = b     // Catch:{ Exception -> 0x01b0 }
            long r4 = r2.longValue()     // Catch:{ Exception -> 0x01b0 }
            long r0 = r0 - r4
            java.lang.Long r2 = f1176a     // Catch:{ Exception -> 0x01b0 }
            long r4 = r2.longValue()     // Catch:{ Exception -> 0x01b0 }
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x01b0
        L_0x01a4:
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x01b0 }
            com.moat.analytics.mobile.mpub.q.b(r0)     // Catch:{ Exception -> 0x01b0 }
            b = r13     // Catch:{ Exception -> 0x01b0 }
            goto L_0x01b0
        L_0x01ae:
            c = r13     // Catch:{ Exception -> 0x01b0 }
        L_0x01b0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.n.b(java.lang.Exception):void");
    }
}
