package com.startapp.android.publish.adsCommon.j;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.mopub.mobileads.VastExtensionXmlManager;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<Integer, SensorEvent> f263a = new HashMap<>();

    public int a(SensorEvent sensorEvent) {
        int size;
        synchronized (this) {
            int type = sensorEvent.sensor.getType();
            SensorEvent sensorEvent2 = this.f263a.get(Integer.valueOf(type));
            if (sensorEvent2 == null || sensorEvent2.accuracy <= sensorEvent.accuracy) {
                this.f263a.put(Integer.valueOf(type), sensorEvent);
            }
            size = this.f263a.size();
        }
        return size;
    }

    public JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        for (SensorEvent next : this.f263a.values()) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            Sensor sensor = next.sensor;
            jSONObject2.put("name", sensor.getName());
            jSONObject2.put(VastExtensionXmlManager.VENDOR, sensor.getVendor());
            jSONObject2.put("version", sensor.getVersion());
            jSONObject2.put("maximum range", (double) sensor.getMaximumRange());
            jSONObject2.put("power", (double) sensor.getPower());
            jSONObject2.put("resolution", (double) sensor.getResolution());
            jSONObject2.put("accuracy", next.accuracy);
            jSONObject2.put(AvidJSONUtil.KEY_TIMESTAMP, next.timestamp);
            JSONArray jSONArray2 = new JSONArray();
            for (float f : next.values) {
                jSONArray2.put((double) f);
            }
            jSONObject2.put("values", jSONArray2);
            jSONObject.put(String.valueOf(sensor.getType()), jSONObject2);
            jSONArray.put(jSONObject);
        }
        if (jSONArray.length() > 0) {
            return jSONArray;
        }
        return null;
    }
}
