package com.tappx.a;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.tappx.sdk.android.TrackInstallReceiver;
import java.util.Set;

public class a3 {
    private void b(Context context, Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("token");
            if (stringExtra == null) {
                return;
            }
            if (!stringExtra.isEmpty()) {
                char c = 65535;
                if (stringExtra.hashCode() == 149971738) {
                    if (stringExtra.equals("TAPPX_INSTALL_GETCLASS")) {
                        c = 0;
                    }
                }
                if (c == 0) {
                    j0.a("BroadcastReceiverStackTrace: Start", new Object[0]);
                    for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                        try {
                            if (stackTraceElement.getMethodName().equalsIgnoreCase("onReceive")) {
                                j0.a("BroadcastReceiverStackTrace: " + context.getPackageName() + "/" + stackTraceElement.getClassName() + " :: " + stackTraceElement.getMethodName(), new Object[0]);
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        } catch (Exception e) {
            j0.a("no string token", new Object[0]);
            j0.b("ERROR01: " + e.getMessage(), new Object[0]);
            e.printStackTrace();
        }
    }

    private void c(Context context, Intent intent) {
        Bundle bundle;
        Set<String> keySet;
        try {
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, TrackInstallReceiver.class.getName()), 128);
            if (receiverInfo != null && (bundle = receiverInfo.metaData) != null && (keySet = bundle.keySet()) != null) {
                for (String str : keySet) {
                    if (str != null) {
                        if (!str.isEmpty()) {
                            try {
                                ((BroadcastReceiver) Class.forName(bundle.getString(str)).newInstance()).onReceive(context, intent);
                            } catch (InstantiationException e) {
                                j0.b(e.getMessage(), new Object[0]);
                            } catch (IllegalAccessException e2) {
                                j0.b(e2.getMessage(), new Object[0]);
                            } catch (ClassNotFoundException e3) {
                                j0.b(e3.getMessage(), new Object[0]);
                            }
                        }
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e4) {
            j0.b(e4.getMessage(), new Object[0]);
        }
    }

    public void a(Context context, Intent intent) {
        b(context, intent);
        c.a(context).m().a(intent);
        c(context, intent);
    }
}
