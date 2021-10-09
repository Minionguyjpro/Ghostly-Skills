package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.impl.bk;
import java.util.HashMap;
import java.util.Map;

public class l {
    public static String a(Map<String, String> map) {
        if (bk.a((Map) map)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            if (!TextUtils.isEmpty((CharSequence) next.getKey())) {
                sb.append((String) next.getKey());
                sb.append(":");
                sb.append((String) next.getValue());
                sb.append(",");
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static Map<String, String> a(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(",")) {
                int indexOf = str2.indexOf(":");
                if (indexOf != -1) {
                    String substring = str2.substring(0, indexOf);
                    if (!TextUtils.isEmpty(substring)) {
                        hashMap.put(substring, str2.substring(indexOf + 1));
                    }
                }
            }
        }
        return hashMap;
    }
}
