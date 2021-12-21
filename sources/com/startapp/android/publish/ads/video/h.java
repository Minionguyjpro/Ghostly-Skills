package com.startapp.android.publish.ads.video;

import android.app.Activity;
import android.content.Context;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.activities.FullScreenActivity;
import com.startapp.android.publish.adsCommon.b;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.cache.g;

/* compiled from: StartAppSDK */
public class h {

    /* compiled from: StartAppSDK */
    public enum a {
        ELIGIBLE(""),
        INELIGIBLE_NO_STORAGE("Not enough storage for video"),
        INELIGIBLE_MISSING_ACTIVITY("FullScreenActivity not declared in AndroidManifest.xml"),
        INELIGIBLE_ERRORS_THRESHOLD_REACHED("Video errors threshold reached.");
        
        private String desctiption;

        private a(String str) {
            this.desctiption = str;
        }

        public String a() {
            return this.desctiption;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v11, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v13, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v14, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v15, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v16, resolved type: java.io.DataInputStream} */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.io.DataInputStream, java.io.FileOutputStream, java.lang.String, java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r11, java.net.URL r12, java.lang.String r13) {
        /*
            java.lang.String r0 = ".temp"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Downloading video from "
            r1.append(r2)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "StartAppWall.VideoUtil"
            r3 = 3
            com.startapp.common.a.g.a((java.lang.String) r2, (int) r3, (java.lang.String) r1)
            r1 = 0
            java.lang.String r3 = a((android.content.Context) r11, (java.lang.String) r13)     // Catch:{ Exception -> 0x0097, all -> 0x0093 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0097, all -> 0x0093 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0097, all -> 0x0093 }
            boolean r5 = r4.exists()     // Catch:{ Exception -> 0x0097, all -> 0x0093 }
            if (r5 == 0) goto L_0x0033
            r1.close()     // Catch:{ Exception -> 0x0032 }
            r1.close()     // Catch:{ Exception -> 0x0032 }
            r1.close()     // Catch:{ Exception -> 0x0032 }
        L_0x0032:
            return r3
        L_0x0033:
            java.io.InputStream r5 = r12.openStream()     // Catch:{ Exception -> 0x0097, all -> 0x0093 }
            java.io.DataInputStream r6 = new java.io.DataInputStream     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r6.<init>(r5)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r7 = 4096(0x1000, float:5.74E-42)
            byte[] r7 = new byte[r7]     // Catch:{ Exception -> 0x0089, all -> 0x0086 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0089, all -> 0x0086 }
            r8.<init>()     // Catch:{ Exception -> 0x0089, all -> 0x0086 }
            r8.append(r13)     // Catch:{ Exception -> 0x0089, all -> 0x0086 }
            r8.append(r0)     // Catch:{ Exception -> 0x0089, all -> 0x0086 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0089, all -> 0x0086 }
            r9 = 0
            java.io.FileOutputStream r8 = r11.openFileOutput(r8, r9)     // Catch:{ Exception -> 0x0089, all -> 0x0086 }
        L_0x0054:
            int r10 = r6.read(r7)     // Catch:{ Exception -> 0x0084 }
            if (r10 <= 0) goto L_0x005e
            r8.write(r7, r9, r10)     // Catch:{ Exception -> 0x0084 }
            goto L_0x0054
        L_0x005e:
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x0084 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0084 }
            r9.<init>()     // Catch:{ Exception -> 0x0084 }
            r9.append(r13)     // Catch:{ Exception -> 0x0084 }
            r9.append(r0)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0084 }
            java.lang.String r9 = a((android.content.Context) r11, (java.lang.String) r9)     // Catch:{ Exception -> 0x0084 }
            r7.<init>(r9)     // Catch:{ Exception -> 0x0084 }
            r7.renameTo(r4)     // Catch:{ Exception -> 0x0084 }
            r5.close()     // Catch:{ Exception -> 0x0082 }
            r6.close()     // Catch:{ Exception -> 0x0082 }
            r8.close()     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            r1 = r3
            goto L_0x00d3
        L_0x0084:
            r3 = move-exception
            goto L_0x009b
        L_0x0086:
            r11 = move-exception
            r8 = r1
            goto L_0x00d5
        L_0x0089:
            r3 = move-exception
            r8 = r1
            goto L_0x009b
        L_0x008c:
            r11 = move-exception
            r6 = r1
            r8 = r6
            goto L_0x00d5
        L_0x0090:
            r3 = move-exception
            r6 = r1
            goto L_0x009a
        L_0x0093:
            r11 = move-exception
            r6 = r1
            r8 = r6
            goto L_0x00d6
        L_0x0097:
            r3 = move-exception
            r5 = r1
            r6 = r5
        L_0x009a:
            r8 = r6
        L_0x009b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d4 }
            r4.<init>()     // Catch:{ all -> 0x00d4 }
            java.lang.String r7 = "Error downloading video from "
            r4.append(r7)     // Catch:{ all -> 0x00d4 }
            r4.append(r12)     // Catch:{ all -> 0x00d4 }
            java.lang.String r12 = r4.toString()     // Catch:{ all -> 0x00d4 }
            android.util.Log.e(r2, r12, r3)     // Catch:{ all -> 0x00d4 }
            java.io.File r12 = new java.io.File     // Catch:{ all -> 0x00d4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d4 }
            r2.<init>()     // Catch:{ all -> 0x00d4 }
            r2.append(r13)     // Catch:{ all -> 0x00d4 }
            r2.append(r0)     // Catch:{ all -> 0x00d4 }
            java.lang.String r13 = r2.toString()     // Catch:{ all -> 0x00d4 }
            java.lang.String r11 = a((android.content.Context) r11, (java.lang.String) r13)     // Catch:{ all -> 0x00d4 }
            r12.<init>(r11)     // Catch:{ all -> 0x00d4 }
            r12.delete()     // Catch:{ all -> 0x00d4 }
            r5.close()     // Catch:{ Exception -> 0x00d3 }
            r6.close()     // Catch:{ Exception -> 0x00d3 }
            r8.close()     // Catch:{ Exception -> 0x00d3 }
        L_0x00d3:
            return r1
        L_0x00d4:
            r11 = move-exception
        L_0x00d5:
            r1 = r5
        L_0x00d6:
            r1.close()     // Catch:{ Exception -> 0x00df }
            r6.close()     // Catch:{ Exception -> 0x00df }
            r8.close()     // Catch:{ Exception -> 0x00df }
        L_0x00df:
            goto L_0x00e1
        L_0x00e0:
            throw r11
        L_0x00e1:
            goto L_0x00e0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.video.h.a(android.content.Context, java.net.URL, java.lang.String):java.lang.String");
    }

    public static a a(Context context) {
        a aVar = a.ELIGIBLE;
        if (c(context)) {
            aVar = a.INELIGIBLE_ERRORS_THRESHOLD_REACHED;
        }
        if (!i.a(context, (Class<? extends Activity>) FullScreenActivity.class)) {
            aVar = a.INELIGIBLE_MISSING_ACTIVITY;
        }
        return !d(context) ? a.INELIGIBLE_NO_STORAGE : aVar;
    }

    public static void b(Context context) {
        k.b(context, "videoErrorsCount", Integer.valueOf(k.a(context, "videoErrorsCount", (Integer) 0).intValue() + 1));
    }

    public static void a(Context context, com.startapp.android.publish.ads.video.a.a aVar) {
        if (aVar != null) {
            for (String b : aVar.a()) {
                c.b(context, b);
            }
        }
    }

    public static boolean a(String str) {
        for (g next : com.startapp.android.publish.cache.a.a().d()) {
            if (next.b() instanceof e) {
                e eVar = (e) next.b();
                if (!(eVar.d() == null || eVar.d().getLocalVideoPath() == null || !eVar.d().getLocalVideoPath().equals(str))) {
                    return true;
                }
            }
        }
        return false;
    }

    static String a(Context context, String str) {
        return context.getFilesDir() + "/" + str;
    }

    private static boolean c(Context context) {
        if (b.a().H().e() >= 0 && k.a(context, "videoErrorsCount", (Integer) 0).intValue() >= b.a().H().e()) {
            return true;
        }
        return false;
    }

    private static boolean d(Context context) {
        long a2 = i.a(context.getFilesDir(), -1);
        if (a2 >= 0 && a2 / 1024 > b.a().H().c() * 1024) {
            return true;
        }
        return false;
    }
}
