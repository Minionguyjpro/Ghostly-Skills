package com.yandex.metrica.impl.ob;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ds {

    /* renamed from: a  reason: collision with root package name */
    private final long f863a;
    private final String b;
    private final List<Integer> c;

    public ds(long j, String str, List<Integer> list) {
        this.f863a = j;
        this.b = str;
        this.c = list;
    }

    public ds(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.f863a = jSONObject.getLong("seconds_to_live");
        this.b = jSONObject.getString("token");
        this.c = a(jSONObject.getJSONArray("ports"));
    }

    private static ArrayList<Integer> a(JSONArray jSONArray) throws JSONException {
        ArrayList<Integer> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Integer.valueOf(jSONArray.getInt(i)));
        }
        return arrayList;
    }

    public long a() {
        return this.f863a;
    }

    public String b() {
        return this.b;
    }

    public List<Integer> c() {
        return this.c;
    }

    public String d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("seconds_to_live", this.f863a);
        jSONObject.put("token", this.b);
        jSONObject.put("ports", new JSONArray(this.c));
        return jSONObject.toString();
    }
}
