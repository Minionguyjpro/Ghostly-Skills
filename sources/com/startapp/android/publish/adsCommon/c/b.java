package com.startapp.android.publish.adsCommon.c;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.startapp.common.a.c;
import com.startapp.common.a.g;
import com.startapp.common.d;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    protected Context f228a;
    protected d b;
    protected a c = new a();
    protected BluetoothAdapter d = d();
    protected BroadcastReceiver e;

    public b(Context context, d dVar) {
        this.f228a = context;
        this.b = dVar;
    }

    public void a(boolean z) {
        BluetoothAdapter bluetoothAdapter = this.d;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            this.b.a((Object) null);
            return;
        }
        this.c.a(c());
        if (!z || !c.a(this.f228a, "android.permission.BLUETOOTH_ADMIN")) {
            this.b.a(b());
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.FOUND");
        BroadcastReceiver e2 = e();
        this.e = e2;
        try {
            this.f228a.registerReceiver(e2, intentFilter);
            this.d.startDiscovery();
        } catch (Exception e3) {
            g.a(3, "BluetoothManager - start() " + e3.getMessage());
            this.d.cancelDiscovery();
            this.b.a(b());
        }
    }

    public void a() {
        BluetoothAdapter bluetoothAdapter;
        if (c.a(this.f228a, "android.permission.BLUETOOTH_ADMIN") && this.e != null && (bluetoothAdapter = this.d) != null) {
            bluetoothAdapter.cancelDiscovery();
            try {
                this.f228a.unregisterReceiver(this.e);
            } catch (Exception e2) {
                g.a(3, "BluetoothManager - stop() " + e2.getMessage());
            }
            this.e = null;
        }
    }

    private Set<BluetoothDevice> c() {
        HashSet hashSet = new HashSet();
        try {
            if (c.a(this.f228a, "android.permission.BLUETOOTH") && this.d.isEnabled()) {
                return this.d.getBondedDevices();
            }
        } catch (Exception e2) {
            g.a(6, "Unable to get devices " + e2.getMessage());
        }
        return hashSet;
    }

    private BluetoothAdapter d() {
        if (c.a(this.f228a, "android.permission.BLUETOOTH")) {
            return BluetoothAdapter.getDefaultAdapter();
        }
        return null;
    }

    private BroadcastReceiver e() {
        return new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.bluetooth.device.action.FOUND".equals(action)) {
                    b.this.c.a((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                    b.this.a();
                    b.this.b.a(b.this.b());
                }
            }
        };
    }

    public JSONObject b() {
        try {
            return this.c.a();
        } catch (Exception unused) {
            return null;
        }
    }
}
