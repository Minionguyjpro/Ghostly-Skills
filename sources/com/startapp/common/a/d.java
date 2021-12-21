package com.startapp.common.a;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import java.io.FilterInputStream;
import java.io.InputStream;

/* compiled from: StartAppSDK */
public class d {
    public static Bitmap a(String str) {
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static Drawable a(Resources resources, String str) {
        return new BitmapDrawable(resources, a(str));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.net.HttpURLConnection} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap b(java.lang.String r5) {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x003d, all -> 0x0033 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x003d, all -> 0x0033 }
            java.net.URLConnection r5 = r1.openConnection()     // Catch:{ Exception -> 0x003d, all -> 0x0033 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ Exception -> 0x003d, all -> 0x0033 }
            r5.connect()     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            java.io.InputStream r1 = r5.getInputStream()     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            r2.<init>(r1)     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            com.startapp.common.a.d$a r3 = new com.startapp.common.a.d$a     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            r3.<init>(r1)     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r3)     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            r2.close()     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            r1.close()     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            if (r5 == 0) goto L_0x0044
            r5.disconnect()
            goto L_0x0044
        L_0x002d:
            r0 = move-exception
            goto L_0x0037
        L_0x002f:
            r4 = r0
            r0 = r5
            r5 = r4
            goto L_0x003e
        L_0x0033:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
        L_0x0037:
            if (r5 == 0) goto L_0x003c
            r5.disconnect()
        L_0x003c:
            throw r0
        L_0x003d:
            r5 = r0
        L_0x003e:
            if (r0 == 0) goto L_0x0043
            r0.disconnect()
        L_0x0043:
            r0 = r5
        L_0x0044:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.common.a.d.b(java.lang.String):android.graphics.Bitmap");
    }

    /* compiled from: StartAppSDK */
    static class a extends FilterInputStream {
        public a(InputStream inputStream) {
            super(inputStream);
        }

        public long skip(long j) {
            long j2 = 0;
            while (j2 < j) {
                long skip = this.in.skip(j - j2);
                if (skip == 0) {
                    if (read() < 0) {
                        break;
                    }
                    skip = 1;
                }
                j2 += skip;
            }
            return j2;
        }
    }
}
