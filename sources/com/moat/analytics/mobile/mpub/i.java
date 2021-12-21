package com.moat.analytics.mobile.mpub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class i {

    /* renamed from: a  reason: collision with root package name */
    private static final i f1165a = new i();
    private Class<?> b = null;
    private Object c = null;
    /* access modifiers changed from: private */
    public final Map<j, String> d;
    /* access modifiers changed from: private */
    public final Map<b, String> e;
    private final ScheduledExecutorService f;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> g;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> h;

    private i() {
        c();
        this.f = Executors.newScheduledThreadPool(1);
        this.d = new WeakHashMap();
        this.e = new WeakHashMap();
    }

    static i a() {
        return f1165a;
    }

    private void a(Context context) {
        try {
            if (this.c != null) {
                return;
            }
            if (this.b != null) {
                this.c = this.b.getMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
            }
        } catch (NoSuchMethodException e2) {
            p.a("JSUpdateLooper", (Object) s.class, "NoSuchMethodException while getting LocalBroadcastManager instance", (Throwable) e2);
        } catch (Exception e3) {
            n.a(e3);
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, Intent intent) {
        try {
            a(context);
            if (this.b == null) {
                return;
            }
            if (this.c != null) {
                this.b.getMethod("sendBroadcast", new Class[]{Intent.class}).invoke(this.c, new Object[]{intent});
            }
        } catch (NoSuchMethodException e2) {
            p.a("JSUpdateLooper", (Object) s.class, "NoSuchMethodException calling LocalBroadcastManager sendBroadcast", (Throwable) e2);
        } catch (Exception e3) {
            n.a(e3);
        }
    }

    private void b(final Context context) {
        ScheduledFuture<?> scheduledFuture = this.h;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            p.a(3, "JSUpdateLooper", (Object) this, "Starting metadata reporting loop");
            this.h = this.f.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        i.this.a(context.getApplicationContext(), new Intent("UPDATE_METADATA"));
                        if (i.this.d.isEmpty()) {
                            i.this.h.cancel(true);
                        }
                    } catch (Exception e) {
                        n.a(e);
                    }
                }
            }, 0, 50, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        com.moat.analytics.mobile.mpub.p.a("JSUpdateLooper", (java.lang.Object) r4, "ClassNotFoundException while retrieving LocalBroadcastManager androidx class", (java.lang.Throwable) r0);
        com.moat.analytics.mobile.mpub.p.a("JSUpdateLooper", (java.lang.Object) r4, "No LocalBroadcastManager class was found.", (java.lang.Throwable) r1);
        com.moat.analytics.mobile.mpub.p.a("[ERROR] ", "No LocalBroadcastManager class was found.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0009 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:1:0x0002] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c() {
        /*
            r4 = this;
            java.lang.String r0 = "androidx.localbroadcastmanager.content.LocalBroadcastManager"
            java.lang.Class r1 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x000b, Exception -> 0x0009 }
            r4.b = r1     // Catch:{ ClassNotFoundException -> 0x000b, Exception -> 0x0009 }
            goto L_0x0030
        L_0x0009:
            r0 = move-exception
            goto L_0x000d
        L_0x000b:
            r1 = move-exception
            goto L_0x0011
        L_0x000d:
            com.moat.analytics.mobile.mpub.n.a(r0)
            goto L_0x0030
        L_0x0011:
            java.lang.String r2 = "JSUpdateLooper"
            java.lang.String r3 = "ClassNotFoundException while retrieving LocalBroadcastManager support class"
            com.moat.analytics.mobile.mpub.p.a((java.lang.String) r2, (java.lang.Object) r4, (java.lang.String) r3, (java.lang.Throwable) r1)
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x001f, Exception -> 0x0009 }
            r4.b = r0     // Catch:{ ClassNotFoundException -> 0x001f, Exception -> 0x0009 }
            goto L_0x0030
        L_0x001f:
            r0 = move-exception
            java.lang.String r3 = "ClassNotFoundException while retrieving LocalBroadcastManager androidx class"
            com.moat.analytics.mobile.mpub.p.a((java.lang.String) r2, (java.lang.Object) r4, (java.lang.String) r3, (java.lang.Throwable) r0)
            java.lang.String r3 = "No LocalBroadcastManager class was found."
            com.moat.analytics.mobile.mpub.p.a((java.lang.String) r2, (java.lang.Object) r4, (java.lang.String) r3, (java.lang.Throwable) r1)
            java.lang.String r1 = "[ERROR] "
            com.moat.analytics.mobile.mpub.p.a(r1, r3)
            goto L_0x000d
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.i.c():void");
    }

    private void c(final Context context) {
        ScheduledFuture<?> scheduledFuture = this.g;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            p.a(3, "JSUpdateLooper", (Object) this, "Starting view update loop");
            this.g = this.f.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        i.this.a(context.getApplicationContext(), new Intent("UPDATE_VIEW_INFO"));
                        if (i.this.e.isEmpty()) {
                            p.a(3, "JSUpdateLooper", (Object) i.this, "No more active trackers");
                            i.this.g.cancel(true);
                        }
                    } catch (Exception e) {
                        n.a(e);
                    }
                }
            }, 0, (long) w.a().d, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            a(context);
            if (this.b == null) {
                return;
            }
            if (this.c != null) {
                this.b.getMethod("unregisterReceiver", new Class[]{BroadcastReceiver.class}).invoke(this.c, new Object[]{broadcastReceiver});
            }
        } catch (NoSuchMethodException e2) {
            p.a("JSUpdateLooper", (Object) s.class, "NoSuchMethodException while calling LocalBroadcastManager unregisterReceiver", (Throwable) e2);
        } catch (Exception e3) {
            n.a(e3);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        try {
            a(context);
            if (this.b == null) {
                return;
            }
            if (this.c != null) {
                this.b.getMethod("registerReceiver", new Class[]{BroadcastReceiver.class, IntentFilter.class}).invoke(this.c, new Object[]{broadcastReceiver, intentFilter});
            }
        } catch (NoSuchMethodException e2) {
            p.a("JSUpdateLooper", (Object) s.class, "NoSuchMethodException while calling LocalBroadcastManager registerReceiver", (Throwable) e2);
        } catch (Exception e3) {
            n.a(e3);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, b bVar) {
        if (bVar != null) {
            p.a(3, "JSUpdateLooper", (Object) this, "addActiveTracker" + bVar.hashCode());
            Map<b, String> map = this.e;
            if (map != null && !map.containsKey(bVar)) {
                this.e.put(bVar, "");
                c(context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, j jVar) {
        Map<j, String> map = this.d;
        if (map != null && jVar != null) {
            map.put(jVar, "");
            b(context);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        if (bVar != null) {
            p.a(3, "JSUpdateLooper", (Object) this, "removeActiveTracker" + bVar.hashCode());
            Map<b, String> map = this.e;
            if (map != null) {
                map.remove(bVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        if (jVar != null) {
            p.a(3, "JSUpdateLooper", (Object) this, "removeSetupNeededBridge" + jVar.hashCode());
            Map<j, String> map = this.d;
            if (map != null) {
                map.remove(jVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.b != null;
    }
}
