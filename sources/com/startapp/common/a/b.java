package com.startapp.common.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import com.mopub.common.GpsHelper;
import com.startapp.common.g;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: StartAppSDK */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static volatile c f331a;

    /* compiled from: StartAppSDK */
    private static class e {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final b f337a = new b();
    }

    public static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static b a() {
        return e.f337a;
    }

    public synchronized c a(final Context context) {
        if (f331a == null) {
            f331a = new c();
            try {
                a(b(context));
            } catch (Exception e2) {
                for (int i = 0; i < e2.getStackTrace().length; i++) {
                }
                f331a.a("0");
            }
        } else {
            g.a(g.a.HIGH, (Runnable) new Runnable() {
                public void run() {
                    a aVar;
                    try {
                        aVar = b.b(context);
                    } catch (Exception unused) {
                        aVar = new a("0", false, "");
                    }
                    b.this.a(aVar);
                }
            });
        }
        return f331a;
    }

    /* access modifiers changed from: protected */
    public synchronized void a(a aVar) {
        f331a.a(aVar.a());
        f331a.a(aVar.b());
        f331a.b(aVar.c());
    }

    /* compiled from: StartAppSDK */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        private String f335a = "";
        private String b = "";
        private boolean c = false;

        public synchronized String a() {
            return this.f335a;
        }

        public synchronized void a(String str) {
            this.f335a = str;
        }

        public synchronized boolean b() {
            return this.c;
        }

        public synchronized void a(boolean z) {
            this.c = z;
        }

        public synchronized String c() {
            return this.b;
        }

        public synchronized void b(String str) {
            this.b = str;
        }
    }

    /* compiled from: StartAppSDK */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final String f333a;
        private final boolean b;
        private final String c;

        a(String str, boolean z, String str2) {
            this.f333a = str;
            this.b = z;
            this.c = str2;
        }

        public String a() {
            return this.f333a;
        }

        public boolean b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }
    }

    public static a b(Context context) {
        if (b()) {
            return c(context);
        }
        return d(context);
    }

    private static a c(Context context) {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{context.getApplicationContext()});
            return new a((String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]), ((Boolean) invoke.getClass().getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0])).booleanValue(), "APP");
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    private static a d(Context context) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            try {
                context.getPackageManager().getPackageInfo("com.android.vending", 0);
                C0010b bVar = new C0010b();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (context.getApplicationContext().bindService(intent, bVar, 1)) {
                    try {
                        d dVar = new d(bVar.a());
                        a aVar = new a(dVar.a(), dVar.a(true), "DEVICE");
                        context.getApplicationContext().unbindService(bVar);
                        return aVar;
                    } catch (Exception e2) {
                        throw e2;
                    } catch (Throwable th) {
                        context.getApplicationContext().unbindService(bVar);
                        throw th;
                    }
                } else {
                    throw new IOException("Google Play connection failed");
                }
            } catch (Exception e3) {
                throw e3;
            }
        } else {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
    }

    public static boolean b() {
        return a("com.google.android.gms.ads.identifier.AdvertisingIdClient");
    }

    /* renamed from: com.startapp.common.a.b$b  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    protected static final class C0010b implements ServiceConnection {

        /* renamed from: a  reason: collision with root package name */
        boolean f334a = false;
        private final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);

        public void onServiceDisconnected(ComponentName componentName) {
        }

        protected C0010b() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public IBinder a() {
            if (!this.f334a) {
                this.f334a = true;
                return this.b.take();
            }
            throw new IllegalStateException();
        }
    }

    /* compiled from: StartAppSDK */
    private static final class d implements IInterface {

        /* renamed from: a  reason: collision with root package name */
        private IBinder f336a;

        public d(IBinder iBinder) {
            this.f336a = iBinder;
        }

        public IBinder asBinder() {
            return this.f336a;
        }

        public String a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f336a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean a(boolean z) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z2 = true;
                obtain.writeInt(z ? 1 : 0);
                this.f336a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z2 = false;
                }
                return z2;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
