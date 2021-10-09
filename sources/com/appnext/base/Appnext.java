package com.appnext.base;

import android.content.Context;
import com.appnext.base.b.d;
import com.appnext.base.b.e;
import com.appnext.base.b.g;
import com.appnext.base.b.i;
import com.appnext.base.b.j;
import com.appnext.base.services.OperationService;
import com.appnext.core.f;

public class Appnext {
    private static final Appnext dp = new Appnext();
    /* access modifiers changed from: private */

    /* renamed from: do  reason: not valid java name */
    public Context f0do = null;
    /* access modifiers changed from: private */
    public boolean dq = false;

    private Appnext() {
    }

    protected static Appnext T() {
        return dp;
    }

    private void b(Context context) throws ExceptionInInitializerError {
        if (context != null) {
            try {
                if (!this.dq || this.f0do == null) {
                    this.dq = true;
                    e.init(context);
                    this.f0do = context.getApplicationContext();
                    if (j.a(OperationService.class)) {
                        g.aN().b(new Runnable() {
                            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public final void run() {
                                /*
                                    r14 = this;
                                    android.content.Context r0 = com.appnext.base.b.e.getContext()     // Catch:{ all -> 0x00a6 }
                                    r1 = 1
                                    java.lang.String r0 = com.appnext.core.f.b((android.content.Context) r0, (boolean) r1)     // Catch:{ all -> 0x00a6 }
                                    java.lang.String r2 = "aidForSend"
                                    com.appnext.base.b.d$a r3 = com.appnext.base.b.d.a.String     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.j.a((java.lang.String) r2, (java.lang.String) r0, (com.appnext.base.b.d.a) r3)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.b.i.aR()     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r2 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r2 = r2.f0do     // Catch:{ all -> 0x00a6 }
                                    r0.init(r2)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f0do     // Catch:{ all -> 0x00a6 }
                                    boolean r0 = com.appnext.base.b.j.i(r0)     // Catch:{ all -> 0x00a6 }
                                    if (r0 == 0) goto L_0x0039
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    r2 = 0
                                    boolean unused = r0.dq = false     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.b.i.aR()     // Catch:{ all -> 0x00a6 }
                                    java.lang.String r2 = "lat"
                                    r0.putBoolean(r2, r1)     // Catch:{ all -> 0x00a6 }
                                    return
                                L_0x0039:
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f0do     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.e.init(r0)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.b.i.aR()     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r2 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r2 = r2.f0do     // Catch:{ all -> 0x00a6 }
                                    r0.init(r2)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext.b((com.appnext.base.Appnext) r0)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f0do     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.a.a r2 = com.appnext.base.a.a.X()     // Catch:{  }
                                    com.appnext.base.a.c.c r2 = r2.ab()     // Catch:{  }
                                    java.util.List r2 = r2.as()     // Catch:{  }
                                    if (r2 == 0) goto L_0x00a6
                                    int r2 = r2.size()     // Catch:{  }
                                    if (r2 != 0) goto L_0x00a6
                                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{  }
                                    java.lang.String r3 = "cdm"
                                    r2.<init>(r3)     // Catch:{  }
                                    long r3 = java.lang.System.currentTimeMillis()     // Catch:{  }
                                    r2.append(r3)     // Catch:{  }
                                    java.lang.String r12 = r2.toString()     // Catch:{  }
                                    com.appnext.base.a.b.c r2 = new com.appnext.base.a.b.c     // Catch:{  }
                                    java.lang.String r6 = "on"
                                    java.lang.String r7 = "1"
                                    java.lang.String r8 = "hour"
                                    java.lang.String r9 = "1"
                                    java.lang.String r10 = "interval"
                                    java.lang.String r11 = "cdm"
                                    r13 = 0
                                    r5 = r2
                                    r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{  }
                                    com.appnext.base.a.a r3 = com.appnext.base.a.a.X()     // Catch:{  }
                                    com.appnext.base.a.c.c r3 = r3.ab()     // Catch:{  }
                                    r3.a((com.appnext.base.a.b.c) r2)     // Catch:{  }
                                    com.appnext.base.services.b.a r0 = com.appnext.base.services.b.a.d(r0)     // Catch:{  }
                                    r0.a(r2, r1)     // Catch:{  }
                                L_0x00a6:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.Appnext.AnonymousClass1.run():void");
                            }
                        });
                        return;
                    }
                    return;
                }
                this.f0do = context.getApplicationContext();
            } catch (Throwable unused) {
            }
        } else {
            throw new ExceptionInInitializerError("Cannot init Appnext with null context");
        }
    }

    public static void setParam(String str, String str2) {
        try {
            if (e.getContext() != null && str.hashCode() == 951500826) {
                j.a(d.fo, str2, d.a.Boolean);
            }
        } catch (Throwable unused) {
        }
    }

    private void U() {
        try {
            String b = f.b(this.f0do, true);
            if (!b.equals(i.aR().getString(i.fB, ""))) {
                i.aR().clear();
                i.aR().putString(i.fB, b);
            }
        } catch (Throwable unused) {
        }
    }

    public static void init(Context context) {
        Appnext appnext = dp;
        if (context != null) {
            try {
                if (!appnext.dq || appnext.f0do == null) {
                    appnext.dq = true;
                    e.init(context);
                    appnext.f0do = context.getApplicationContext();
                    if (j.a(OperationService.class)) {
                        g.aN().b(new Runnable() {
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public final void run() {
                                /*
                                    r14 = this;
                                    android.content.Context r0 = com.appnext.base.b.e.getContext()     // Catch:{ all -> 0x00a6 }
                                    r1 = 1
                                    java.lang.String r0 = com.appnext.core.f.b((android.content.Context) r0, (boolean) r1)     // Catch:{ all -> 0x00a6 }
                                    java.lang.String r2 = "aidForSend"
                                    com.appnext.base.b.d$a r3 = com.appnext.base.b.d.a.String     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.j.a((java.lang.String) r2, (java.lang.String) r0, (com.appnext.base.b.d.a) r3)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.b.i.aR()     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r2 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r2 = r2.f0do     // Catch:{ all -> 0x00a6 }
                                    r0.init(r2)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f0do     // Catch:{ all -> 0x00a6 }
                                    boolean r0 = com.appnext.base.b.j.i(r0)     // Catch:{ all -> 0x00a6 }
                                    if (r0 == 0) goto L_0x0039
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    r2 = 0
                                    boolean unused = r0.dq = false     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.b.i.aR()     // Catch:{ all -> 0x00a6 }
                                    java.lang.String r2 = "lat"
                                    r0.putBoolean(r2, r1)     // Catch:{ all -> 0x00a6 }
                                    return
                                L_0x0039:
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f0do     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.e.init(r0)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.b.i.aR()     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r2 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r2 = r2.f0do     // Catch:{ all -> 0x00a6 }
                                    r0.init(r2)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext.b((com.appnext.base.Appnext) r0)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f0do     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.a.a r2 = com.appnext.base.a.a.X()     // Catch:{  }
                                    com.appnext.base.a.c.c r2 = r2.ab()     // Catch:{  }
                                    java.util.List r2 = r2.as()     // Catch:{  }
                                    if (r2 == 0) goto L_0x00a6
                                    int r2 = r2.size()     // Catch:{  }
                                    if (r2 != 0) goto L_0x00a6
                                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{  }
                                    java.lang.String r3 = "cdm"
                                    r2.<init>(r3)     // Catch:{  }
                                    long r3 = java.lang.System.currentTimeMillis()     // Catch:{  }
                                    r2.append(r3)     // Catch:{  }
                                    java.lang.String r12 = r2.toString()     // Catch:{  }
                                    com.appnext.base.a.b.c r2 = new com.appnext.base.a.b.c     // Catch:{  }
                                    java.lang.String r6 = "on"
                                    java.lang.String r7 = "1"
                                    java.lang.String r8 = "hour"
                                    java.lang.String r9 = "1"
                                    java.lang.String r10 = "interval"
                                    java.lang.String r11 = "cdm"
                                    r13 = 0
                                    r5 = r2
                                    r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{  }
                                    com.appnext.base.a.a r3 = com.appnext.base.a.a.X()     // Catch:{  }
                                    com.appnext.base.a.c.c r3 = r3.ab()     // Catch:{  }
                                    r3.a((com.appnext.base.a.b.c) r2)     // Catch:{  }
                                    com.appnext.base.services.b.a r0 = com.appnext.base.services.b.a.d(r0)     // Catch:{  }
                                    r0.a(r2, r1)     // Catch:{  }
                                L_0x00a6:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.Appnext.AnonymousClass1.run():void");
                            }
                        });
                        return;
                    }
                    return;
                }
                appnext.f0do = context.getApplicationContext();
            } catch (Throwable unused) {
            }
        } else {
            throw new ExceptionInInitializerError("Cannot init Appnext with null context");
        }
    }

    static /* synthetic */ void b(Appnext appnext) {
        try {
            String b = f.b(appnext.f0do, true);
            if (!b.equals(i.aR().getString(i.fB, ""))) {
                i.aR().clear();
                i.aR().putString(i.fB, b);
            }
        } catch (Throwable unused) {
        }
    }
}
