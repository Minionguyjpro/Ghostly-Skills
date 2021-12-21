package com.startapp.android.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.appnext.base.b.d;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f22a = {"15555215554", "15555215556", "15555215558", "15555215560", "15555215562", "15555215564", "15555215566", "15555215568", "15555215570", "15555215572", "15555215574", "15555215576", "15555215578", "15555215580", "15555215582", "15555215584"};
    private static final String[] b = {"000000000000000", "e21833235b6eef10", "012345678912345"};
    private static final String[] c = {"310260000000000"};
    private static final String[] d = {"/dev/socket/genyd", "/dev/socket/baseband_genyd"};
    private static final String[] e = {"goldfish"};
    private static final String[] f = {"/dev/socket/qemud", "/dev/qemu_pipe"};
    private static final String[] g = {"ueventd.android_x86.rc", "x86.prop", "ueventd.ttVM_x86.rc", "init.ttVM_x86.rc", "fstab.ttVM_x86", "fstab.vbox86", "init.vbox86.rc", "ueventd.vbox86.rc"};
    private static final String[] h = {"fstab.andy", "ueventd.andy.rc"};
    private static final String[] i = {"fstab.nox", "init.nox.rc", "ueventd.nox.rc", "/BigNoxGameHD", "/YSLauncher"};
    private static final b[] j = {new b("init.svc.qemud", (String) null), new b("init.svc.qemu-props", (String) null), new b("qemu.hw.mainkeys", (String) null), new b("qemu.sf.fake_camera", (String) null), new b("qemu.sf.lcd_density", (String) null), new b("ro.bootloader", "unknown"), new b("ro.bootmode", "unknown"), new b("ro.hardware", "goldfish"), new b("ro.kernel.android.qemud", (String) null), new b("ro.kernel.qemu.gles", (String) null), new b("ro.kernel.qemu", "1"), new b("ro.product.device", "generic"), new b("ro.product.model", "sdk"), new b("ro.product.name", "sdk"), new b("ro.serialno", (String) null), new b("ro.build.description", "72656C656173652D6B657973"), new b("ro.build.fingerprint", "3A757365722F72656C656173652D6B657973"), new b("net.eth0.dns1", (String) null), new b("rild.libpath", "2F73797374656D2F6C69622F6C69627265666572656E63652D72696C2E736F"), new b("ro.radio.use-ppp", (String) null), new b("gsm.version.baseband", (String) null), new b("ro.build.tags", "72656C656173652D6B65"), new b("ro.build.display.id", "746573742D"), new b("init.svc.console", (String) null)};
    private static a o;
    private static Boolean p;
    private final Context k;
    private boolean l = false;
    private boolean m = true;
    private List<String> n;

    public static boolean a(Context context) {
        if (p == null) {
            p = Boolean.valueOf(b(context).a());
        }
        return p.booleanValue();
    }

    private static a b(Context context) {
        if (context != null) {
            if (o == null) {
                o = new a(context.getApplicationContext());
            }
            return o;
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    private a(Context context) {
        ArrayList arrayList = new ArrayList();
        this.n = arrayList;
        this.k = context;
        arrayList.add("com.google.android.launcher.layouts.genymotion");
        this.n.add("com.bluestacks");
        this.n.add("com.bignox.app");
        this.n.add("com.vphone.launcher");
    }

    private boolean a() {
        boolean b2 = b();
        if (!b2) {
            b2 = c();
        }
        return !b2 ? d() : b2;
    }

    private boolean b() {
        return Build.FINGERPRINT.startsWith("generic") || Build.MODEL.contains("google_sdk") || Build.MODEL.toLowerCase().contains("droid4x") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for") || Build.MANUFACTURER.contains("Genymotion") || Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("vbox86") || Build.PRODUCT.equals("sdk") || Build.PRODUCT.equals("google_sdk") || Build.PRODUCT.equals("sdk_x86") || Build.PRODUCT.equals("vbox86p") || Build.BOARD.toLowerCase().contains("nox") || Build.BOOTLOADER.toLowerCase().contains("nox") || Build.HARDWARE.toLowerCase().contains("nox") || Build.PRODUCT.toLowerCase().contains("nox") || Build.SERIAL.toLowerCase().contains("nox") || Build.FINGERPRINT.startsWith("unknown") || Build.FINGERPRINT.contains("Andy") || Build.FINGERPRINT.contains("ttVM_Hdragon") || Build.FINGERPRINT.contains("vbox86p") || Build.HARDWARE.contains("ttVM_x86") || Build.MODEL.equals("sdk") || Build.MODEL.contains("Droid4X") || Build.MODEL.contains("TiantianVM") || Build.MODEL.contains("Andy") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"));
    }

    private boolean c() {
        return e() || a(d, "Geny") || a(h, "Andy") || a(i, "Nox") || j() || a(f, "Pipes") || l() || (k() && a(g, "X86"));
    }

    private boolean d() {
        if (this.m && !this.n.isEmpty()) {
            PackageManager packageManager = this.k.getPackageManager();
            for (String launchIntentForPackage : this.n) {
                Intent launchIntentForPackage2 = packageManager.getLaunchIntentForPackage(launchIntentForPackage);
                if (launchIntentForPackage2 != null && !packageManager.queryIntentActivities(launchIntentForPackage2, 65536).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean e() {
        if (!b(this.k, "android.permission.READ_PHONE_STATE") || !this.l || !m()) {
            return false;
        }
        if (f() || g() || h() || i()) {
            return true;
        }
        return false;
    }

    private boolean f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.k.getSystemService("phone");
        if (telephonyManager != null) {
            String line1Number = telephonyManager.getLine1Number();
            for (String equalsIgnoreCase : f22a) {
                if (equalsIgnoreCase.equalsIgnoreCase(line1Number)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean g() {
        TelephonyManager telephonyManager = (TelephonyManager) this.k.getSystemService("phone");
        if (telephonyManager != null) {
            String deviceId = telephonyManager.getDeviceId();
            for (String equalsIgnoreCase : b) {
                if (equalsIgnoreCase.equalsIgnoreCase(deviceId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean h() {
        TelephonyManager telephonyManager = (TelephonyManager) this.k.getSystemService("phone");
        if (telephonyManager != null) {
            String subscriberId = telephonyManager.getSubscriberId();
            for (String equalsIgnoreCase : c) {
                if (equalsIgnoreCase.equalsIgnoreCase(subscriberId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean i() {
        TelephonyManager telephonyManager = (TelephonyManager) this.k.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName().equalsIgnoreCase("android");
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006d A[SYNTHETIC, Splitter:B:28:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0074 A[SYNTHETIC, Splitter:B:34:0x0074] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean j() {
        /*
            r12 = this;
            r0 = 2
            java.io.File[] r1 = new java.io.File[r0]
            java.io.File r2 = new java.io.File
            java.lang.String r3 = "/proc/tty/drivers"
            r2.<init>(r3)
            r3 = 0
            r1[r3] = r2
            java.io.File r2 = new java.io.File
            java.lang.String r4 = "/proc/cpuinfo"
            r2.<init>(r4)
            r4 = 1
            r1[r4] = r2
            r2 = 0
        L_0x0018:
            if (r2 >= r0) goto L_0x007b
            r5 = r1[r2]
            boolean r6 = r5.exists()
            if (r6 == 0) goto L_0x0078
            boolean r6 = r5.canRead()
            if (r6 == 0) goto L_0x0078
            r6 = 1024(0x400, float:1.435E-42)
            char[] r6 = new char[r6]
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r8 = 0
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0071, all -> 0x006a }
            java.io.InputStreamReader r10 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0071, all -> 0x006a }
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0071, all -> 0x006a }
            r11.<init>(r5)     // Catch:{ Exception -> 0x0071, all -> 0x006a }
            r10.<init>(r11)     // Catch:{ Exception -> 0x0071, all -> 0x006a }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0071, all -> 0x006a }
        L_0x0041:
            int r5 = r9.read(r6)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r8 = -1
            if (r5 == r8) goto L_0x004c
            r7.append(r6, r3, r5)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            goto L_0x0041
        L_0x004c:
            r9.close()     // Catch:{ IOException -> 0x004f }
        L_0x004f:
            java.lang.String r5 = r7.toString()
            java.lang.String[] r6 = e
            int r7 = r6.length
            r8 = 0
        L_0x0057:
            if (r8 >= r7) goto L_0x0078
            r9 = r6[r8]
            boolean r9 = r5.contains(r9)
            if (r9 == 0) goto L_0x0062
            return r4
        L_0x0062:
            int r8 = r8 + 1
            goto L_0x0057
        L_0x0065:
            r0 = move-exception
            r8 = r9
            goto L_0x006b
        L_0x0068:
            r8 = r9
            goto L_0x0072
        L_0x006a:
            r0 = move-exception
        L_0x006b:
            if (r8 == 0) goto L_0x0070
            r8.close()     // Catch:{ IOException -> 0x0070 }
        L_0x0070:
            throw r0
        L_0x0071:
        L_0x0072:
            if (r8 == 0) goto L_0x0077
            r8.close()     // Catch:{ IOException -> 0x0077 }
        L_0x0077:
            return r3
        L_0x0078:
            int r2 = r2 + 1
            goto L_0x0018
        L_0x007b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.a.a.j():boolean");
    }

    private boolean a(String[] strArr, String str) {
        File file;
        for (String str2 : strArr) {
            if (!b(this.k, "android.permission.READ_EXTERNAL_STORAGE") || !str2.contains("/") || !str.equals("Nox")) {
                file = new File(str2);
            } else {
                file = new File(Environment.getExternalStorageDirectory() + str2);
            }
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }

    private boolean k() {
        int i2 = 0;
        for (b bVar : j) {
            String a2 = a(this.k, bVar.f23a);
            if (bVar.b == null && a2 != null) {
                i2++;
            }
            if (!(bVar.b == null || a2 == null || !a2.contains(bVar.b))) {
                i2++;
            }
        }
        if (i2 >= 5) {
            return true;
        }
        return false;
    }

    private boolean l() {
        if (!b(this.k, "android.permission.INTERNET")) {
            return false;
        }
        String[] strArr = {"/system/bin/netcfg"};
        StringBuilder sb = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(strArr);
            processBuilder.directory(new File("/system/bin/"));
            processBuilder.redirectErrorStream(true);
            InputStream inputStream = processBuilder.start().getInputStream();
            byte[] bArr = new byte[d.fb];
            while (inputStream.read(bArr) != -1) {
                sb.append(new String(bArr));
            }
            inputStream.close();
        } catch (Exception unused) {
        }
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            return false;
        }
        for (String str : sb2.split("\n")) {
            if ((str.contains("wlan0") || str.contains("tunl0") || str.contains("eth0")) && str.contains("10.0.2.15")) {
                return true;
            }
        }
        return false;
    }

    private String a(Context context, String str) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) loadClass.getMethod("get", new Class[]{String.class}).invoke(loadClass, new Object[]{str});
        } catch (Exception unused) {
            return null;
        }
    }

    private boolean m() {
        return this.k.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean b(Context context, String str) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                if (context.checkSelfPermission(str) == 0) {
                    return true;
                }
                return false;
            } else if (context.checkCallingOrSelfPermission(str) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
