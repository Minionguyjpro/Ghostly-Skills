package com.tappx.a;

import com.tappx.a.h5;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

public class f6 {
    public static h5.a a(q5 q5Var) {
        long j;
        long j2;
        boolean z;
        long j3;
        long j4;
        long j5;
        q5 q5Var2 = q5Var;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = q5Var2.c;
        String str = map.get("Date");
        long b = str != null ? b(str) : 0;
        String str2 = map.get("Cache-Control");
        int i = 0;
        if (str2 != null) {
            String[] split = str2.split(",", 0);
            int i2 = 0;
            j2 = 0;
            j = 0;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j2 = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    i2 = 1;
                }
                i++;
            }
            i = i2;
            z = true;
        } else {
            z = false;
            j2 = 0;
            j = 0;
        }
        String str3 = map.get("Expires");
        long b2 = str3 != null ? b(str3) : 0;
        String str4 = map.get("Last-Modified");
        long b3 = str4 != null ? b(str4) : 0;
        String str5 = map.get("ETag");
        if (z) {
            long j6 = currentTimeMillis + (j2 * 1000);
            if (i != 0) {
                j5 = j6;
            } else {
                Long.signum(j);
                j5 = (j * 1000) + j6;
            }
            j3 = j6;
            j4 = j5;
        } else {
            j3 = 0;
            if (b <= 0 || b2 < b) {
                j4 = 0;
            } else {
                j4 = currentTimeMillis + (b2 - b);
                j3 = j4;
            }
        }
        h5.a aVar = new h5.a();
        aVar.f460a = q5Var2.b;
        aVar.b = str5;
        aVar.f = j3;
        aVar.e = j4;
        aVar.c = b;
        aVar.d = b3;
        aVar.g = map;
        aVar.h = q5Var2.d;
        return aVar;
    }

    public static long b(String str) {
        try {
            return a("EEE, dd MMM yyyy HH:mm:ss zzz").parse(str).getTime();
        } catch (ParseException e) {
            if ("0".equals(str) || "-1".equals(str)) {
                a6.d("Unable to parse dateStr: %s, falling back to 0", str);
                return 0;
            }
            a6.a(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0;
        }
    }

    static String a(long j) {
        return a("EEE, dd MMM yyyy HH:mm:ss 'GMT'").format(new Date(j));
    }

    private static SimpleDateFormat a(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    static Map<String, String> a(List<m5> list) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (m5 next : list) {
            treeMap.put(next.a(), next.b());
        }
        return treeMap;
    }

    static List<m5> a(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(new m5((String) next.getKey(), (String) next.getValue()));
        }
        return arrayList;
    }
}
