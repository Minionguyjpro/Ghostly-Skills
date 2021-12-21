package com.yandex.metrica.impl;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.yandex.metrica.impl.d;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

class bm implements d {
    private static volatile bm b;
    private static final Object c = new Object();

    /* renamed from: a  reason: collision with root package name */
    private final WifiManager f755a;
    private d.a<JSONArray> d = new d.a<>();
    private d.a<List<a>> e = new d.a<>();

    private bm(Context context) {
        this.f755a = (WifiManager) context.getSystemService("wifi");
    }

    static bm a(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new bm(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    /* access modifiers changed from: package-private */
    public synchronized JSONArray a() {
        if (!d()) {
            return new JSONArray();
        }
        if (this.d.b() || this.d.c()) {
            this.d.a(c());
        }
        return this.d.a();
    }

    private JSONArray c() {
        try {
            List<ScanResult> scanResults = this.f755a.getScanResults();
            JSONArray jSONArray = new JSONArray();
            String str = null;
            WifiInfo connectionInfo = this.f755a.getConnectionInfo();
            if (connectionInfo != null) {
                str = connectionInfo.getBSSID();
            }
            for (ScanResult next : scanResults) {
                if (next != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("mac", next.BSSID.toUpperCase(Locale.US).replace(":", ""));
                    jSONObject.put("signal_strength", next.level);
                    jSONObject.put("ssid", next.SSID);
                    jSONObject.put("is_connected", next.BSSID.equals(str));
                    jSONArray.put(jSONObject);
                }
            }
            return jSONArray;
        } catch (Exception unused) {
            return new JSONArray();
        }
    }

    private boolean d() {
        try {
            return this.f755a.isWifiEnabled();
        } catch (Exception unused) {
            return false;
        }
    }

    public List<a> b() {
        if (this.e.b() || this.e.c()) {
            ArrayList arrayList = new ArrayList();
            a((List<a>) arrayList);
            this.e.a(arrayList);
        }
        return this.e.a();
    }

    private static void a(List<a> list) {
        StringBuilder sb = new StringBuilder();
        try {
            Iterator<T> it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress != null) {
                    for (byte valueOf : hardwareAddress) {
                        sb.append(String.format(Locale.US, "%02X:", new Object[]{Byte.valueOf(valueOf)}));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                        list.add(new a(networkInterface.getName(), sb.toString()));
                        sb.setLength(0);
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final String f756a;
        public final String b;

        public a(String str, String str2) {
            this.f756a = str;
            this.b = str2;
        }
    }

    public String b(Context context) {
        WifiConfiguration wifiConfiguration;
        try {
            if (al.a(context, "android.permission.ACCESS_WIFI_STATE") && (wifiConfiguration = (WifiConfiguration) this.f755a.getClass().getMethod("getWifiApConfiguration", new Class[0]).invoke(this.f755a, new Object[0])) != null) {
                return wifiConfiguration.SSID;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public int c(Context context) {
        try {
            if (!al.a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return -1;
            }
            int intValue = ((Integer) this.f755a.getClass().getMethod("getWifiApState", new Class[0]).invoke(this.f755a, new Object[0])).intValue();
            if (intValue > 10) {
                intValue -= 10;
            }
            return intValue;
        } catch (Throwable unused) {
            return -1;
        }
    }
}
