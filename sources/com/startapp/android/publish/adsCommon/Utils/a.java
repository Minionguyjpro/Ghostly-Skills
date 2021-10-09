package com.startapp.android.publish.adsCommon.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import com.startapp.common.g;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: StartAppSDK */
public class a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final Map<String, Bitmap> f173a = new ConcurrentHashMap();

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0059 A[SYNTHETIC, Splitter:B:20:0x0059] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0060 A[SYNTHETIC, Splitter:B:28:0x0060] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap a(android.content.Context r3, java.lang.String r4, boolean r5) {
        /*
            java.util.Map<java.lang.String, android.graphics.Bitmap> r0 = f173a
            java.lang.Object r0 = r0.get(r4)
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            if (r0 == 0) goto L_0x000b
            return r0
        L_0x000b:
            com.startapp.android.publish.adsCommon.Utils.f.a((android.content.Context) r3, (boolean) r5)
            r5 = 0
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x005d, all -> 0x0056 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005d, all -> 0x0056 }
            r1.<init>()     // Catch:{ Exception -> 0x005d, all -> 0x0056 }
            java.io.File r2 = r3.getFilesDir()     // Catch:{ Exception -> 0x005d, all -> 0x0056 }
            java.lang.String r2 = r2.getPath()     // Catch:{ Exception -> 0x005d, all -> 0x0056 }
            r1.append(r2)     // Catch:{ Exception -> 0x005d, all -> 0x0056 }
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch:{ Exception -> 0x005d, all -> 0x0056 }
            r1.append(r4)     // Catch:{ Exception -> 0x005d, all -> 0x0056 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x005d, all -> 0x0056 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x005d, all -> 0x0056 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r0)     // Catch:{ Exception -> 0x005e, all -> 0x0053 }
            android.content.res.Resources r2 = r3.getResources()     // Catch:{ Exception -> 0x005e, all -> 0x0053 }
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ Exception -> 0x005e, all -> 0x0053 }
            if (r3 == 0) goto L_0x0045
            android.util.DisplayMetrics r3 = r2.getDisplayMetrics()     // Catch:{ Exception -> 0x005e, all -> 0x0053 }
            int r3 = r3.densityDpi     // Catch:{ Exception -> 0x005e, all -> 0x0053 }
            goto L_0x0047
        L_0x0045:
            r3 = 160(0xa0, float:2.24E-43)
        L_0x0047:
            r1.setDensity(r3)     // Catch:{ Exception -> 0x005e, all -> 0x0053 }
            java.util.Map<java.lang.String, android.graphics.Bitmap> r3 = f173a     // Catch:{ Exception -> 0x005e, all -> 0x0053 }
            r3.put(r4, r1)     // Catch:{ Exception -> 0x005e, all -> 0x0053 }
            r0.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            return r1
        L_0x0053:
            r3 = move-exception
            r5 = r0
            goto L_0x0057
        L_0x0056:
            r3 = move-exception
        L_0x0057:
            if (r5 == 0) goto L_0x005c
            r5.close()     // Catch:{ IOException -> 0x005c }
        L_0x005c:
            throw r3
        L_0x005d:
            r0 = r5
        L_0x005e:
            if (r0 == 0) goto L_0x0063
            r0.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0063:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.Utils.a.a(android.content.Context, java.lang.String, boolean):android.graphics.Bitmap");
    }

    public static Bitmap a(Context context, String str) {
        Bitmap a2 = a(context, str, false);
        return a2 == null ? a(context, str, true) : a2;
    }

    public static void a(final Context context, final Bitmap bitmap, final String str, final String str2) {
        g.a(g.a.DEFAULT, (Runnable) new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:15:0x0064 A[SYNTHETIC, Splitter:B:15:0x0064] */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x0070 A[SYNTHETIC, Splitter:B:21:0x0070] */
            /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    java.util.Map r0 = com.startapp.android.publish.adsCommon.Utils.a.f173a
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = r4
                    r1.append(r2)
                    java.lang.String r2 = r5
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    android.graphics.Bitmap r2 = r3
                    r0.put(r1, r2)
                    r0 = 0
                    java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    r2.<init>()     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    android.content.Context r3 = r2     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    java.io.File r3 = r3.getFilesDir()     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    java.lang.String r3 = r3.getPath()     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    r2.append(r3)     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    java.lang.String r3 = "/"
                    r2.append(r3)     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    java.lang.String r3 = r4     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    r2.append(r3)     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    java.lang.String r3 = ".png"
                    r2.append(r3)     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    r1.<init>(r2)     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
                    android.graphics.Bitmap r0 = r3     // Catch:{ Exception -> 0x0054 }
                    android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Exception -> 0x0054 }
                    r3 = 100
                    r0.compress(r2, r3, r1)     // Catch:{ Exception -> 0x0054 }
                    r1.close()     // Catch:{ IOException -> 0x0068 }
                    goto L_0x006c
                L_0x0054:
                    r0 = move-exception
                    goto L_0x005f
                L_0x0056:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                    goto L_0x006e
                L_0x005b:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                L_0x005f:
                    r0.printStackTrace()     // Catch:{ all -> 0x006d }
                    if (r1 == 0) goto L_0x006c
                    r1.close()     // Catch:{ IOException -> 0x0068 }
                    goto L_0x006c
                L_0x0068:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x006c:
                    return
                L_0x006d:
                    r0 = move-exception
                L_0x006e:
                    if (r1 == 0) goto L_0x0078
                    r1.close()     // Catch:{ IOException -> 0x0074 }
                    goto L_0x0078
                L_0x0074:
                    r1 = move-exception
                    r1.printStackTrace()
                L_0x0078:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.Utils.a.AnonymousClass1.run():void");
            }
        });
    }

    public static boolean a(Context context, String str, String str2) {
        Map<String, Bitmap> map = f173a;
        if (!map.containsKey(str + str2)) {
            if (!new File(context.getFilesDir().getPath() + "/" + str).exists()) {
                return false;
            }
        }
        return true;
    }
}
