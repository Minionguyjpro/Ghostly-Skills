package com.yandex.metrica.impl.ob;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import com.appnext.base.a.c.d;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.bk;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;

public class dp implements Runnable {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final ServiceConnection f849a = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    };
    private final Handler b = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 100) {
                dp.this.e();
                try {
                    dp.this.d.unbindService(dp.this.f849a);
                } catch (Exception unused) {
                    YandexMetrica.getReporter(dp.this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_unbind_has_thrown_exception");
                }
            }
        }
    };
    private HashMap<String, c> c = new HashMap<String, c>() {
        {
            put("p", new c() {
                public b a(Uri uri, Socket socket) {
                    return new a(uri, socket);
                }
            });
        }
    };
    /* access modifiers changed from: private */
    public final Context d;
    private boolean e;
    private ServerSocket f;
    /* access modifiers changed from: private */
    public final dq g = new dq();
    /* access modifiers changed from: private */
    public ds h;
    private Thread i;

    abstract class b {
        Uri b;
        Socket c;

        public abstract void a();

        /* access modifiers changed from: protected */
        public void a(Throwable th) {
        }

        /* access modifiers changed from: protected */
        public void b() {
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [java.net.Socket, java.util.Map<java.lang.String, java.lang.String>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        b(android.net.Uri r1, java.util.Map<java.lang.String, java.lang.String> r2) {
            /*
                r0 = this;
                r0.<init>()
                r0.b = r1
                r0.c = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.dp.b.<init>(android.net.Uri, java.net.Socket):void");
        }

        private static void a(OutputStream outputStream) throws IOException {
            outputStream.write("\r\n".getBytes());
        }

        /* access modifiers changed from: package-private */
        public void a(String str, Map<String, String> map, byte[] bArr) {
            BufferedOutputStream bufferedOutputStream = null;
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(this.c.getOutputStream());
                try {
                    bufferedOutputStream2.write(str.getBytes());
                    a((OutputStream) bufferedOutputStream2);
                    for (Map.Entry next : map.entrySet()) {
                        a((OutputStream) bufferedOutputStream2, (String) next.getKey(), (String) next.getValue());
                    }
                    a((OutputStream) bufferedOutputStream2, "Content-Length", String.valueOf(bArr.length));
                    a((OutputStream) bufferedOutputStream2);
                    bufferedOutputStream2.write(bArr);
                    bufferedOutputStream2.flush();
                    b();
                    bk.a((Closeable) bufferedOutputStream2);
                } catch (IOException e) {
                    e = e;
                    bufferedOutputStream = bufferedOutputStream2;
                    try {
                        a((Throwable) e);
                        bk.a((Closeable) bufferedOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        bk.a((Closeable) bufferedOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedOutputStream = bufferedOutputStream2;
                    bk.a((Closeable) bufferedOutputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                a((Throwable) e);
                bk.a((Closeable) bufferedOutputStream);
            }
        }

        private static void a(OutputStream outputStream, String str, String str2) throws IOException {
            outputStream.write((str + ": " + str2).getBytes());
            a(outputStream);
        }
    }

    static abstract class c {
        public abstract b a(Uri uri, Socket socket);

        c() {
        }
    }

    class a extends b {
        /* JADX WARNING: type inference failed for: r3v0, types: [java.net.Socket, java.util.Map<java.lang.String, java.lang.String>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        a(android.net.Uri r2, java.util.Map<java.lang.String, java.lang.String> r3) {
            /*
                r0 = this;
                com.yandex.metrica.impl.ob.dp.this = r1
                r0.<init>(r2, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.dp.a.<init>(com.yandex.metrica.impl.ob.dp, android.net.Uri, java.net.Socket):void");
        }

        public void a() {
            if (dp.this.h.b().equals(this.b.getQueryParameter(d.COLUMN_TYPE))) {
                a("HTTP/1.1 200 OK", (Map<String, String>) new HashMap<String, String>() {
                    {
                        put("Content-Type", "text/plain; charset=utf-8");
                        put("Access-Control-Allow-Origin", "*");
                        put("Access-Control-Allow-Methods", "GET");
                    }
                }, c());
            } else {
                YandexMetrica.getReporter(dp.this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_request_with_wrong_token");
            }
        }

        /* access modifiers changed from: protected */
        public void b() {
            YandexMetrica.getReporter(dp.this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_sync_succeed", dp.c(this.c.getLocalPort()));
        }

        /* access modifiers changed from: protected */
        public void a(Throwable th) {
            YandexMetrica.getReporter(dp.this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("socket_io_exception_during_sync", th);
        }

        /* access modifiers changed from: protected */
        public byte[] c() {
            try {
                return Base64.encode(new com.yandex.metrica.impl.utils.b().a(dp.this.g.a().getBytes()), 0);
            } catch (JSONException unused) {
                return new byte[0];
            }
        }
    }

    dp(Context context) {
        this.d = context;
        g.a().a(this, p.class, k.a(new j<p>() {
            public void a(p pVar) {
                dp.this.g.a(pVar.f930a);
            }
        }).a(new h<p>() {
            public boolean a(p pVar) {
                return !dp.this.d.getPackageName().equals(pVar.b);
            }
        }).a());
        g.a().a(this, n.class, k.a(new j<n>() {
            public void a(n nVar) {
                dp.this.g.b(nVar.f928a);
            }
        }).a());
        g.a().a(this, l.class, k.a(new j<l>() {
            public void a(l lVar) {
                dp.this.g.c(lVar.f926a);
            }
        }).a());
        g.a().a(this, m.class, k.a(new j<m>() {
            public void a(m mVar) {
                dp.this.g.d(mVar.f927a);
            }
        }).a());
        g.a().a(this, o.class, k.a(new j<o>() {
            public void a(o oVar) {
                dp.this.a(oVar.f929a);
                dp.this.c();
            }
        }).a());
    }

    public void a() {
        if (this.e) {
            b();
            Handler handler = this.b;
            handler.sendMessageDelayed(handler.obtainMessage(100), this.h.a() * 1000);
        }
    }

    public void b() {
        this.b.removeMessages(100);
    }

    public synchronized void c() {
        if (!(this.e || this.h == null)) {
            this.e = true;
            d();
            Thread thread = new Thread(this);
            this.i = thread;
            thread.start();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ds dsVar) {
        this.h = dsVar;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        Intent intent = new Intent(this.d, MetricaService.class);
        intent.setAction("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER");
        try {
            if (!this.d.bindService(intent, this.f849a, 1)) {
                YandexMetrica.getReporter(this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_bind_has_failed");
            }
        } catch (Exception unused) {
            YandexMetrica.getReporter(this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_bind_has_thrown_exception");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0021, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void e() {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            r2.e = r0     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            java.lang.Thread r0 = r2.i     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r1 = 0
            if (r0 == 0) goto L_0x0010
            java.lang.Thread r0 = r2.i     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r0.interrupt()     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r2.i = r1     // Catch:{ IOException -> 0x0020, all -> 0x001d }
        L_0x0010:
            java.net.ServerSocket r0 = r2.f     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            if (r0 == 0) goto L_0x001b
            java.net.ServerSocket r0 = r2.f     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r0.close()     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r2.f = r1     // Catch:{ IOException -> 0x0020, all -> 0x001d }
        L_0x001b:
            monitor-exit(r2)
            return
        L_0x001d:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x0020:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.dp.e():void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.net.Socket] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ae A[SYNTHETIC, Splitter:B:34:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bc A[SYNTHETIC, Splitter:B:43:0x00bc] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c3 A[SYNTHETIC, Splitter:B:49:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0008 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r9 = this;
            java.net.ServerSocket r0 = r9.f()
            r9.f = r0
            if (r0 == 0) goto L_0x00c8
        L_0x0008:
            boolean r0 = r9.e
            if (r0 == 0) goto L_0x00c8
            r0 = 0
            java.net.ServerSocket r1 = r9.f     // Catch:{ IOException -> 0x00c0, all -> 0x00b6 }
            java.net.Socket r1 = r1.accept()     // Catch:{ IOException -> 0x00c0, all -> 0x00b6 }
            r2 = 1000(0x3e8, float:1.401E-42)
            r1.setSoTimeout(r2)     // Catch:{ all -> 0x00a9 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x00a9 }
            r2.<init>()     // Catch:{ all -> 0x00a9 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x00a9 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ all -> 0x00a9 }
            java.io.InputStream r5 = r1.getInputStream()     // Catch:{ all -> 0x00a9 }
            r4.<init>(r5)     // Catch:{ all -> 0x00a9 }
            r3.<init>(r4)     // Catch:{ all -> 0x00a9 }
            java.lang.String r4 = r3.readLine()     // Catch:{ all -> 0x00a7 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00a7 }
            if (r5 != 0) goto L_0x009a
            java.lang.String r5 = "GET /"
            boolean r5 = r4.startsWith(r5)     // Catch:{ all -> 0x00a7 }
            if (r5 == 0) goto L_0x004f
            r0 = 47
            int r0 = r4.indexOf(r0)     // Catch:{ all -> 0x00a7 }
            int r0 = r0 + 1
            r5 = 32
            int r5 = r4.indexOf(r5, r0)     // Catch:{ all -> 0x00a7 }
            java.lang.String r0 = r4.substring(r0, r5)     // Catch:{ all -> 0x00a7 }
        L_0x004f:
            android.net.Uri r4 = android.net.Uri.parse(r0)     // Catch:{ all -> 0x00a7 }
        L_0x0053:
            java.lang.String r5 = r3.readLine()     // Catch:{ all -> 0x00a7 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x00a7 }
            if (r6 != 0) goto L_0x0072
            java.lang.String r6 = ": "
            int r6 = r5.indexOf(r6)     // Catch:{ all -> 0x00a7 }
            r7 = 0
            java.lang.String r7 = r5.substring(r7, r6)     // Catch:{ all -> 0x00a7 }
            int r6 = r6 + 2
            java.lang.String r5 = r5.substring(r6)     // Catch:{ all -> 0x00a7 }
            r2.put(r7, r5)     // Catch:{ all -> 0x00a7 }
            goto L_0x0053
        L_0x0072:
            java.util.HashMap<java.lang.String, com.yandex.metrica.impl.ob.dp$c> r2 = r9.c     // Catch:{ all -> 0x00a7 }
            java.lang.String r5 = r4.getPath()     // Catch:{ all -> 0x00a7 }
            java.lang.Object r2 = r2.get(r5)     // Catch:{ all -> 0x00a7 }
            com.yandex.metrica.impl.ob.dp$c r2 = (com.yandex.metrica.impl.ob.dp.c) r2     // Catch:{ all -> 0x00a7 }
            if (r2 == 0) goto L_0x0088
            com.yandex.metrica.impl.ob.dp$b r0 = r2.a(r4, r1)     // Catch:{ all -> 0x00a7 }
            r0.a()     // Catch:{ all -> 0x00a7 }
            goto L_0x009a
        L_0x0088:
            android.content.Context r2 = r9.d     // Catch:{ all -> 0x00a7 }
            java.lang.String r4 = "20799a27-fa80-4b36-b2db-0f8141f24180"
            com.yandex.metrica.IReporter r2 = com.yandex.metrica.YandexMetrica.getReporter(r2, r4)     // Catch:{ all -> 0x00a7 }
            java.lang.String r4 = "socket_request_to_unknown_path"
            com.yandex.metrica.impl.ob.dp$2 r5 = new com.yandex.metrica.impl.ob.dp$2     // Catch:{ all -> 0x00a7 }
            r5.<init>(r0)     // Catch:{ all -> 0x00a7 }
            r2.reportEvent(r4, r5)     // Catch:{ all -> 0x00a7 }
        L_0x009a:
            r3.close()     // Catch:{ IOException -> 0x00b4, all -> 0x00b2 }
            if (r1 == 0) goto L_0x0008
            r1.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x0008
        L_0x00a4:
            goto L_0x0008
        L_0x00a7:
            r0 = move-exception
            goto L_0x00ac
        L_0x00a9:
            r2 = move-exception
            r3 = r0
            r0 = r2
        L_0x00ac:
            if (r3 == 0) goto L_0x00b1
            r3.close()     // Catch:{ IOException -> 0x00b4, all -> 0x00b2 }
        L_0x00b1:
            throw r0     // Catch:{ IOException -> 0x00b4, all -> 0x00b2 }
        L_0x00b2:
            r0 = move-exception
            goto L_0x00ba
        L_0x00b4:
            r0 = r1
            goto L_0x00c1
        L_0x00b6:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        L_0x00ba:
            if (r1 == 0) goto L_0x00bf
            r1.close()     // Catch:{ IOException -> 0x00bf }
        L_0x00bf:
            throw r0
        L_0x00c0:
        L_0x00c1:
            if (r0 == 0) goto L_0x0008
            r0.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x0008
        L_0x00c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.dp.run():void");
    }

    /* access modifiers changed from: package-private */
    public ServerSocket f() {
        Iterator<Integer> it = this.h.c().iterator();
        ServerSocket serverSocket = null;
        Integer num = null;
        while (serverSocket == null && it.hasNext()) {
            try {
                Integer next = it.next();
                if (next != null) {
                    try {
                        serverSocket = a(next.intValue());
                    } catch (SocketException unused) {
                        num = next;
                        YandexMetrica.getReporter(this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_port_already_in_use", c(num.intValue()));
                    } catch (IOException unused2) {
                    }
                }
                num = next;
            } catch (SocketException unused3) {
                YandexMetrica.getReporter(this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_port_already_in_use", c(num.intValue()));
            } catch (IOException unused4) {
            }
        }
        return serverSocket;
    }

    /* access modifiers changed from: package-private */
    public ServerSocket a(int i2) throws IOException {
        return new ServerSocket(i2);
    }

    /* access modifiers changed from: private */
    public static HashMap<String, Object> c(int i2) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("port", String.valueOf(i2));
        return hashMap;
    }
}
