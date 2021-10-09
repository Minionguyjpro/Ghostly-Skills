package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Base64;
import com.yandex.metrica.impl.utils.g;
import com.yandex.metrica.impl.utils.i;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final long f759a = TimeUnit.SECONDS.toMillis(15);
    private final Context b;
    private long c = 0;

    public c(Context context) {
        this.b = context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00de, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00df, code lost:
        r2 = null;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00eb, code lost:
        r0 = null;
        r2 = null;
        r3 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x003c */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073 A[Catch:{ Exception -> 0x00ce, all -> 0x00c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0076 A[Catch:{ Exception -> 0x00ce, all -> 0x00c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00de A[Catch:{ Exception -> 0x00ce, all -> 0x00c9 }, ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x000e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.HashMap<java.lang.String, java.lang.String> a() {
        /*
            r12 = this;
            monitor-enter(r12)
            android.content.Context r0 = r12.b     // Catch:{ all -> 0x00fa }
            java.lang.String r1 = "b_meta.dat"
            java.io.File r0 = r0.getFileStreamPath(r1)     // Catch:{ all -> 0x00fa }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x00fa }
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00eb, all -> 0x00de }
            r2.<init>(r0)     // Catch:{ Exception -> 0x00eb, all -> 0x00de }
            boolean r3 = r2.exists()     // Catch:{ Exception -> 0x00eb, all -> 0x00de }
            r4 = 0
            r5 = 1
            if (r3 != 0) goto L_0x003c
            r2.createNewFile()     // Catch:{ Exception -> 0x00eb, all -> 0x00de }
            r2.setReadable(r5, r4)     // Catch:{ Exception -> 0x00eb, all -> 0x00de }
            android.content.Context r3 = r12.b     // Catch:{ Exception -> 0x003c, all -> 0x00de }
            java.lang.String r6 = "browsers.dat"
            java.io.File r3 = r3.getFileStreamPath(r6)     // Catch:{ Exception -> 0x003c, all -> 0x00de }
            java.io.File r3 = r3.getAbsoluteFile()     // Catch:{ Exception -> 0x003c, all -> 0x00de }
            boolean r6 = r3.exists()     // Catch:{ Exception -> 0x003c, all -> 0x00de }
            if (r6 == 0) goto L_0x003c
            boolean r6 = r3.canWrite()     // Catch:{ Exception -> 0x003c, all -> 0x00de }
            if (r6 == 0) goto L_0x003c
            r3.delete()     // Catch:{ Exception -> 0x003c, all -> 0x00de }
        L_0x003c:
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x00eb, all -> 0x00de }
            java.lang.String r6 = "rw"
            r3.<init>(r0, r6)     // Catch:{ Exception -> 0x00eb, all -> 0x00de }
            java.nio.channels.FileChannel r0 = r3.getChannel()     // Catch:{ Exception -> 0x00db, all -> 0x00d8 }
            java.nio.channels.FileLock r6 = r0.lock()     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
            long r7 = r2.length()     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            int r2 = (int) r7     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r2)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r0.read(r2)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r2.flip()     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            byte[] r2 = r2.array()     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            java.lang.String r2 = r12.a((byte[]) r2)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            java.util.HashMap r1 = r12.b((java.lang.String) r2)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            long r9 = r12.c     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            long r7 = r7 - r9
            long r9 = f759a     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 <= 0) goto L_0x0074
            r4 = 1
        L_0x0074:
            if (r4 == 0) goto L_0x00bf
            r12.a((java.util.HashMap<java.lang.String, java.lang.String>) r1)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r12.c = r7     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r2.<init>()     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            java.lang.String r4 = "browser_open_times"
            java.lang.String r7 = com.yandex.metrica.impl.utils.g.a((java.util.Map) r1)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r2.putOpt(r4, r7)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            java.lang.String r4 = "last_sync_time"
            long r7 = r12.c     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r2.putOpt(r4, r7)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            java.lang.String r2 = r12.a((java.lang.String) r2)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            java.lang.String r4 = "UTF-8"
            byte[] r2 = r2.getBytes(r4)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            int r4 = r2.length     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r4)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r4.put(r2)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r4.flip()     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r7 = 0
            r0.position(r7)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r0.truncate(r7)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r0.write(r4)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
            r0.force(r5)     // Catch:{ Exception -> 0x00ce, all -> 0x00c9 }
        L_0x00bf:
            com.yandex.metrica.impl.r.a((java.nio.channels.FileLock) r6)     // Catch:{ all -> 0x00fa }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r3)     // Catch:{ all -> 0x00fa }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r0)     // Catch:{ all -> 0x00fa }
            goto L_0x00f8
        L_0x00c9:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r6
            goto L_0x00e1
        L_0x00ce:
            r2 = r1
            r1 = r6
            goto L_0x00ee
        L_0x00d1:
            r2 = move-exception
            r11 = r2
            r2 = r0
            r0 = r11
            goto L_0x00e1
        L_0x00d6:
            r2 = r1
            goto L_0x00ee
        L_0x00d8:
            r0 = move-exception
            r2 = r1
            goto L_0x00e1
        L_0x00db:
            r0 = r1
            r2 = r0
            goto L_0x00ee
        L_0x00de:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L_0x00e1:
            com.yandex.metrica.impl.r.a((java.nio.channels.FileLock) r1)     // Catch:{ all -> 0x00fa }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r3)     // Catch:{ all -> 0x00fa }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r2)     // Catch:{ all -> 0x00fa }
            throw r0     // Catch:{ all -> 0x00fa }
        L_0x00eb:
            r0 = r1
            r2 = r0
            r3 = r2
        L_0x00ee:
            com.yandex.metrica.impl.r.a((java.nio.channels.FileLock) r1)     // Catch:{ all -> 0x00fa }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r3)     // Catch:{ all -> 0x00fa }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r0)     // Catch:{ all -> 0x00fa }
            r1 = r2
        L_0x00f8:
            monitor-exit(r12)
            return r1
        L_0x00fa:
            r0 = move-exception
            monitor-exit(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.c.a():java.util.HashMap");
    }

    private HashMap<String, String> b(String str) {
        HashMap<String, String> hashMap = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = g.a(jSONObject.optString("browser_open_times"));
                this.c = jSONObject.optLong("last_sync_time", 0);
            }
        } catch (JSONException unused) {
        }
        if (hashMap != null) {
            return hashMap;
        }
        return new HashMap<>();
    }

    private void a(HashMap<String, String> hashMap) {
        for (String file : b()) {
            a(hashMap, a(new File(file)));
        }
    }

    private HashMap<String, String> a(File file) {
        byte[] b2;
        try {
            if (file.exists() && (b2 = r.b(this.b, file)) != null) {
                String a2 = a(b2);
                file.getName();
                return b(a2);
            }
        } catch (UnsupportedEncodingException unused) {
        }
        return new HashMap<>(0);
    }

    /* access modifiers changed from: package-private */
    public List<String> b() {
        Context context = this.b;
        List<ResolveInfo> a2 = be.a(context, be.a(context));
        ArrayList arrayList = new ArrayList();
        String packageName = this.b.getPackageName();
        for (ResolveInfo next : a2) {
            String str = next.serviceInfo.applicationInfo.packageName;
            if (!packageName.equals(str) && be.a((PackageItemInfo) next.serviceInfo) >= 47) {
                try {
                    arrayList.add(this.b.getFileStreamPath("b_meta.dat").getAbsolutePath().replace(this.b.getApplicationInfo().dataDir, this.b.getPackageManager().getApplicationInfo(str, 8192).dataDir));
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
        }
        return arrayList;
    }

    private static boolean a(Map<String, String> map, Map<String, String> map2) {
        boolean z = false;
        for (Map.Entry next : map2.entrySet()) {
            String str = (String) next.getKey();
            long a2 = i.a((String) next.getValue(), 0);
            long a3 = i.a(map.get(str), 0);
            if (a2 > 0 && a3 < a2) {
                map.put(str, String.valueOf(a2));
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public String a(String str) throws UnsupportedEncodingException {
        return Base64.encodeToString(b(r.b(str).getBytes("UTF-8")), 0);
    }

    /* access modifiers changed from: package-private */
    public String a(byte[] bArr) throws UnsupportedEncodingException {
        return r.c(new String(b(Base64.decode(bArr, 0)), "UTF-8"));
    }

    private byte[] b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(this.b.getPackageName().getBytes("UTF-8"));
            byte[] digest = instance.digest();
            byte[] bArr2 = new byte[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                bArr2[i] = (byte) (bArr[i] ^ digest[i % digest.length]);
            }
            return bArr2;
        } catch (Exception unused) {
            return null;
        }
    }
}
