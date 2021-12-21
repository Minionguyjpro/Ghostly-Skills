package com.startapp.android.b;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final Context f24a;

    public a(Context context) {
        this.f24a = context;
    }

    public boolean a() {
        return c() || d() || a("su") || a("busybox") || f() || g() || b() || h() || e();
    }

    public boolean b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    public boolean c() {
        return a((String[]) null);
    }

    public boolean a(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(b.f25a));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return a((List<String>) arrayList);
    }

    public boolean d() {
        return b((String[]) null);
    }

    public boolean b(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(b.b));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return a((List<String>) arrayList);
    }

    public boolean e() {
        return a("magisk");
    }

    public boolean a(String str) {
        boolean z = false;
        for (String file : b.d) {
            if (new File(file, str).exists()) {
                z = true;
            }
        }
        return z;
    }

    private String[] i() {
        String[] strArr = new String[0];
        try {
            return new Scanner(Runtime.getRuntime().exec("getprop").getInputStream()).useDelimiter("\\A").next().split("\n");
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
            return strArr;
        }
    }

    private String[] j() {
        String[] strArr = new String[0];
        try {
            return new Scanner(Runtime.getRuntime().exec("mount").getInputStream()).useDelimiter("\\A").next().split("\n");
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
            return strArr;
        }
    }

    private boolean a(List<String> list) {
        PackageManager packageManager = this.f24a.getPackageManager();
        boolean z = false;
        for (String packageInfo : list) {
            try {
                packageManager.getPackageInfo(packageInfo, 0);
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return z;
    }

    public boolean f() {
        HashMap hashMap = new HashMap();
        hashMap.put("ro.debuggable", "1");
        hashMap.put("ro.secure", "0");
        boolean z = false;
        for (String str : i()) {
            for (String str2 : hashMap.keySet()) {
                if (str.contains(str2)) {
                    if (str.contains("[" + ((String) hashMap.get(str2)) + "]")) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public boolean g() {
        boolean z = false;
        for (String split : j()) {
            String[] split2 = split.split(" ");
            if (split2.length >= 4) {
                String str = split2[1];
                String str2 = split2[3];
                for (String equalsIgnoreCase : b.e) {
                    if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                        String[] split3 = str2.split(",");
                        int length = split3.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            } else if (split3[i].equalsIgnoreCase("rw")) {
                                z = true;
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                }
            }
        }
        return z;
    }

    public boolean h() {
        boolean z = false;
        Process process = null;
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"which", "su"});
            if (new BufferedReader(new InputStreamReader(exec.getInputStream())).readLine() != null) {
                z = true;
            }
            if (exec != null) {
                exec.destroy();
            }
            return z;
        } catch (Throwable unused) {
            if (process != null) {
                process.destroy();
            }
            return false;
        }
    }
}
