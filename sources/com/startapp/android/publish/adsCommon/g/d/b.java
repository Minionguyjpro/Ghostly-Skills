package com.startapp.android.publish.adsCommon.g.d;

import com.startapp.common.a.g;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: StartAppSDK */
public class b {
    public static Map<String, String> a(String str) {
        g.a("MraidParser", 3, "parseCommandUrl " + str);
        String substring = str.substring(8);
        HashMap hashMap = new HashMap();
        int indexOf = substring.indexOf(63);
        if (indexOf != -1) {
            String substring2 = substring.substring(0, indexOf);
            for (String str2 : substring.substring(indexOf + 1).split("&")) {
                int indexOf2 = str2.indexOf(61);
                hashMap.put(str2.substring(0, indexOf2), str2.substring(indexOf2 + 1));
            }
            substring = substring2;
        }
        if (!b(substring)) {
            g.a(5, "command " + substring + " is unknown");
            return null;
        } else if (!a(substring, hashMap)) {
            g.a(5, "command URL " + str + " is missing parameters");
            return null;
        } else {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("command", substring);
            hashMap2.putAll(hashMap);
            return hashMap2;
        }
    }

    public static boolean b(String str) {
        return Arrays.asList(new String[]{"close", "createCalendarEvent", "expand", "open", "playVideo", "resize", "setOrientationProperties", "setResizeProperties", "storePicture", "useCustomClose"}).contains(str);
    }

    private static boolean a(String str, Map<String, String> map) {
        if (str.equals("createCalendarEvent")) {
            return map.containsKey("eventJSON");
        }
        if (str.equals("open") || str.equals("playVideo") || str.equals("storePicture")) {
            return map.containsKey("url");
        }
        if (str.equals("setOrientationProperties")) {
            if (!map.containsKey("allowOrientationChange") || !map.containsKey("forceOrientation")) {
                return false;
            }
            return true;
        } else if (str.equals("setResizeProperties")) {
            if (!map.containsKey("width") || !map.containsKey("height") || !map.containsKey("offsetX") || !map.containsKey("offsetY") || !map.containsKey("customClosePosition") || !map.containsKey("allowOffscreen")) {
                return false;
            }
            return true;
        } else if (str.equals("useCustomClose")) {
            return map.containsKey("useCustomClose");
        } else {
            return true;
        }
    }
}
