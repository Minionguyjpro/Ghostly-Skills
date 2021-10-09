package com.startapp.android.publish.adsCommon.i;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.a.a.a.a.a;
import com.startapp.common.a.g;
import java.util.concurrent.CountDownLatch;

/* compiled from: StartAppSDK */
public class a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static CountDownLatch f259a;
    /* access modifiers changed from: private */
    public static b b;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:17|18|19|20) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0071 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.startapp.android.publish.adsCommon.i.b a(android.content.Context r9) {
        /*
            java.lang.String r0 = "com.android.vending"
            java.lang.String r1 = "PlayReferrer"
            com.startapp.android.publish.adsCommon.i.b r2 = b
            if (r2 != 0) goto L_0x0081
            r2 = 5
            java.util.concurrent.CountDownLatch r3 = new java.util.concurrent.CountDownLatch     // Catch:{ all -> 0x007b }
            r4 = 1
            r3.<init>(r4)     // Catch:{ all -> 0x007b }
            f259a = r3     // Catch:{ all -> 0x007b }
            com.startapp.android.publish.adsCommon.i.a$a r3 = new com.startapp.android.publish.adsCommon.i.a$a     // Catch:{ all -> 0x007b }
            java.lang.String r5 = r9.getPackageName()     // Catch:{ all -> 0x007b }
            r6 = 0
            r3.<init>(r5)     // Catch:{ all -> 0x007b }
            android.content.Intent r5 = new android.content.Intent     // Catch:{ all -> 0x007b }
            java.lang.String r6 = "com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE"
            r5.<init>(r6)     // Catch:{ all -> 0x007b }
            android.content.ComponentName r6 = new android.content.ComponentName     // Catch:{ all -> 0x007b }
            java.lang.String r7 = "com.google.android.finsky.externalreferrer.GetInstallReferrerService"
            r6.<init>(r0, r7)     // Catch:{ all -> 0x007b }
            r5.setComponent(r6)     // Catch:{ all -> 0x007b }
            android.content.pm.PackageManager r6 = r9.getPackageManager()     // Catch:{ all -> 0x007b }
            r7 = 0
            java.util.List r6 = r6.queryIntentServices(r5, r7)     // Catch:{ all -> 0x007b }
            if (r6 == 0) goto L_0x0081
            boolean r8 = r6.isEmpty()     // Catch:{ all -> 0x007b }
            if (r8 != 0) goto L_0x0081
            java.lang.Object r6 = r6.get(r7)     // Catch:{ all -> 0x007b }
            android.content.pm.ResolveInfo r6 = (android.content.pm.ResolveInfo) r6     // Catch:{ all -> 0x007b }
            android.content.pm.ServiceInfo r7 = r6.serviceInfo     // Catch:{ all -> 0x007b }
            if (r7 == 0) goto L_0x0081
            android.content.pm.ServiceInfo r7 = r6.serviceInfo     // Catch:{ all -> 0x007b }
            java.lang.String r7 = r7.packageName     // Catch:{ all -> 0x007b }
            android.content.pm.ServiceInfo r6 = r6.serviceInfo     // Catch:{ all -> 0x007b }
            java.lang.String r6 = r6.name     // Catch:{ all -> 0x007b }
            boolean r0 = r0.equals(r7)     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x0081
            if (r6 == 0) goto L_0x0081
            boolean r0 = b(r9)     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x0081
            android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x007b }
            r0.<init>(r5)     // Catch:{ all -> 0x007b }
            boolean r0 = r9.bindService(r0, r3, r4)     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x0075
            java.util.concurrent.CountDownLatch r0 = f259a     // Catch:{ InterruptedException -> 0x0071 }
            r4 = 1
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0071 }
            r0.await(r4, r6)     // Catch:{ InterruptedException -> 0x0071 }
        L_0x0071:
            r9.unbindService(r3)     // Catch:{ all -> 0x007b }
            goto L_0x0081
        L_0x0075:
            java.lang.String r9 = "failed to connect to referrer service"
            com.startapp.common.a.g.a((java.lang.String) r1, (int) r2, (java.lang.String) r9)     // Catch:{ all -> 0x007b }
            goto L_0x0081
        L_0x007b:
            r9 = move-exception
            java.lang.String r0 = "getReferrerDetails"
            com.startapp.common.a.g.a(r1, r2, r0, r9)
        L_0x0081:
            com.startapp.android.publish.adsCommon.i.b r9 = b
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.i.a.a(android.content.Context):com.startapp.android.publish.adsCommon.i.b");
    }

    private static boolean b(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo("com.android.vending", 128).versionCode >= 80837300) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: com.startapp.android.publish.adsCommon.i.a$a  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    private static final class C0006a implements ServiceConnection {

        /* renamed from: a  reason: collision with root package name */
        private String f260a;

        private C0006a(String str) {
            this.f260a = str;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.a.a.a.a.a a2 = a.C0033a.a(iBinder);
            Bundle bundle = new Bundle();
            bundle.putString("package_name", this.f260a);
            try {
                b unused = a.b = new b(a2.a(bundle));
            } catch (RemoteException e) {
                g.a("PlayReferrer", 5, "InstallReferrerServiceConnection.onServiceConnected", e);
            }
            a.f259a.countDown();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            g.a("PlayReferrer", 5, "InstallReferrerServiceConnection.onServiceDisconnected");
            a.f259a.countDown();
        }
    }
}
