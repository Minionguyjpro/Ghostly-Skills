package com.tappx.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class q5 {

    /* renamed from: a  reason: collision with root package name */
    public final int f563a;
    public final byte[] b;
    public final Map<String, String> c;
    public final List<m5> d;
    public final boolean e;
    public final long f;

    @Deprecated
    public q5(int i, byte[] bArr, Map<String, String> map, boolean z, long j) {
        this(i, bArr, map, a(map), z, j);
    }

    private static Map<String, String> a(List<m5> list) {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (m5 next : list) {
            treeMap.put(next.a(), next.b());
        }
        return treeMap;
    }

    public q5(int i, byte[] bArr, boolean z, long j, List<m5> list) {
        this(i, bArr, a(list), list, z, j);
    }

    @Deprecated
    public q5(byte[] bArr, Map<String, String> map) {
        this(200, bArr, map, false, 0);
    }

    private q5(int i, byte[] bArr, Map<String, String> map, List<m5> list, boolean z, long j) {
        this.f563a = i;
        this.b = bArr;
        this.c = map;
        if (list == null) {
            this.d = null;
        } else {
            this.d = Collections.unmodifiableList(list);
        }
        this.e = z;
        this.f = j;
    }

    private static List<m5> a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        if (map.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(new m5((String) next.getKey(), (String) next.getValue()));
        }
        return arrayList;
    }
}
