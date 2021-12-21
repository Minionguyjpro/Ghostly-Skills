package com.startapp.android.publish.adsCommon.c;

import android.bluetooth.BluetoothDevice;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private Set<BluetoothDevice> f225a;
    private Set<BluetoothDevice> b;

    public void a(BluetoothDevice bluetoothDevice) {
        if (this.b == null) {
            this.b = new HashSet();
        }
        this.b.add(bluetoothDevice);
    }

    public void a(Set<BluetoothDevice> set) {
        this.f225a = set;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.f225a != null && this.f225a.size() > 0) {
                jSONObject.put("paired", b(this.f225a));
            }
            if (this.b != null && this.b.size() > 0) {
                jSONObject.put("available", b(this.b));
            }
        } catch (Exception unused) {
        }
        if (jSONObject.length() > 0) {
            return jSONObject;
        }
        return null;
    }

    private JSONArray b(Set<BluetoothDevice> set) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (BluetoothDevice next : set) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("bluetoothClass", next.getBluetoothClass().getDeviceClass());
                jSONObject.put("name", next.getName());
                jSONObject.put("mac", next.getAddress());
                jSONObject.put("bondState", next.getBondState());
                jSONArray.put(jSONObject);
            }
            return jSONArray;
        } catch (Exception unused) {
            return null;
        }
    }
}
