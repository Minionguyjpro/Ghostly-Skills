package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import com.yandex.metrica.MetricaEventHandler;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.YandexMetrica;
import java.util.List;

public class v {
    public static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static boolean a(Context context, Intent intent) {
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        String name = MetricaEventHandler.class.getName();
        for (ResolveInfo next : queryBroadcastReceivers) {
            if (next.activityInfo.packageName.equals(context.getPackageName()) && next.activityInfo.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    static class a extends RuntimeException {
        public a(String str) {
            super(str);
        }

        public a(String str, String str2) {
            super("\nPlease check " + str + " in AndroidManifest file.\n" + str2);
        }
    }

    static class c extends a {
        public c(String str) {
            super(str, "Attribute metrica:api:level should be equal to " + YandexMetrica.getLibraryApiLevel() + ".\n");
        }
    }

    static class b extends a {
        public b(String str, String str2) {
            super(str, "It should not include intent-filter with action " + str2 + "\n");
        }
    }

    public static void a(Context context) {
        if (a("com.yandex.metrica.CounterConfiguration")) {
            boolean z = true;
            boolean z2 = false;
            if ((context.getApplicationInfo().flags & 2) != 0) {
                try {
                    Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, MetricaService.class), 640).metaData;
                    if (bundle != null && bundle.containsKey("metrica:api:level")) {
                        if (bundle.getInt("metrica:api:level") != YandexMetrica.getLibraryApiLevel()) {
                            z = false;
                        }
                        z2 = z;
                    }
                } catch (Exception unused) {
                }
                if (z2) {
                    String str = MetricaEventHandler.class.getName() + " receiver";
                    Intent intent = new Intent("com.yandex.metrica.intent.action.SYNC");
                    Intent intent2 = new Intent((String) null, Uri.parse("package://fake.data"));
                    if (a(context, intent)) {
                        throw new b(str, "com.yandex.metrica.intent.action.SYNC");
                    } else if (a(context, intent2.setAction("android.intent.action.PACKAGE_DATA_CLEARED"))) {
                        throw new b(str, "android.intent.action.PACKAGE_DATA_CLEARED");
                    } else if (a(context, intent2.setAction("android.intent.action.PACKAGE_ADDED"))) {
                        throw new b(str, "android.intent.action.PACKAGE_ADDED");
                    }
                } else {
                    throw new c(MetricaService.class.getName());
                }
            }
        } else {
            throw new a("\nClass com.yandex.metrica.CounterConfiguration isn't found.\nPerhaps this is due to obfuscation.\nIf you build your application with ProGuard,\nyou need to keep the Metrica for Apps.\nPlease try to use the following lines of code:\n##########################################\n-keep class com.yandex.metrica.** { *; }\n-dontwarn com.yandex.metrica.**\n##########################################");
        }
    }
}
