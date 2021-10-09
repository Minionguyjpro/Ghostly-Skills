package com.tappx.a;

import com.appnext.base.b.d;
import com.google.android.gms.plus.PlusShare;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class m4 {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f521a = {"yyyy-MM-dd'T'HH:mm:ssZZZZZ", "yyyy-MM-dd'T'HH:mmZZZZZ"};

    static Date a(String str) {
        String[] strArr = f521a;
        int length = strArr.length;
        Date date = null;
        int i = 0;
        while (i < length) {
            try {
                date = new SimpleDateFormat(strArr[i], Locale.US).parse(str);
                if (date != null) {
                    break;
                }
                i++;
            } catch (ParseException unused) {
            }
        }
        return date;
    }

    static String b(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (map.containsKey("frequency")) {
            String str = map.get("frequency");
            int parseInt = map.containsKey(d.fn) ? Integer.parseInt(map.get(d.fn)) : -1;
            if ("daily".equals(str)) {
                sb.append("FREQ=DAILY;");
                if (parseInt != -1) {
                    sb.append("INTERVAL=" + parseInt + ";");
                }
            } else if ("weekly".equals(str)) {
                sb.append("FREQ=WEEKLY;");
                if (parseInt != -1) {
                    sb.append("INTERVAL=" + parseInt + ";");
                }
                if (map.containsKey("daysInWeek")) {
                    String d = d(map.get("daysInWeek"));
                    if (d != null) {
                        sb.append("BYDAY=" + d + ";");
                    } else {
                        throw new IllegalArgumentException("invalid ");
                    }
                }
            } else if ("monthly".equals(str)) {
                sb.append("FREQ=MONTHLY;");
                if (parseInt != -1) {
                    sb.append("INTERVAL=" + parseInt + ";");
                }
                if (map.containsKey("daysInMonth")) {
                    String c = c(map.get("daysInMonth"));
                    if (c != null) {
                        sb.append("BYMONTHDAY=" + c + ";");
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            } else {
                throw new IllegalArgumentException("Invalid frequency");
            }
        }
        if (sb.toString().isEmpty()) {
            return null;
        }
        return sb.toString();
    }

    static String c(String str) {
        StringBuilder sb = new StringBuilder();
        boolean[] zArr = new boolean[63];
        String[] split = str.split(",");
        for (String parseInt : split) {
            int parseInt2 = Integer.parseInt(parseInt);
            int i = parseInt2 + 31;
            if (!zArr[i]) {
                sb.append(a(parseInt2) + ",");
                zArr[i] = true;
            }
        }
        if (split.length != 0) {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        throw new IllegalArgumentException();
    }

    static String d(String str) {
        StringBuilder sb = new StringBuilder();
        boolean[] zArr = new boolean[7];
        String[] split = str.split(",");
        for (String parseInt : split) {
            int parseInt2 = Integer.parseInt(parseInt);
            if (parseInt2 == 7) {
                parseInt2 = 0;
            }
            if (!zArr[parseInt2]) {
                sb.append(b(parseInt2) + ",");
                zArr[parseInt2] = true;
            }
        }
        if (split.length != 0) {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        throw new IllegalArgumentException();
    }

    static String a(int i) {
        if (i == 0 || i < -31 || i > 31) {
            throw new IllegalArgumentException("Invalid params " + i);
        }
        return "" + i;
    }

    static Map<String, Object> a(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (!map.containsKey(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION) || !map.containsKey("start")) {
            throw new IllegalArgumentException();
        }
        hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, map.get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION));
        if (!map.containsKey("start") || map.get("start") == null) {
            throw new IllegalArgumentException();
        }
        Date a2 = a(map.get("start"));
        if (a2 != null) {
            hashMap.put("beginTime", Long.valueOf(a2.getTime()));
            if (map.containsKey("end") && map.get("end") != null) {
                Date a3 = a(map.get("end"));
                if (a3 != null) {
                    hashMap.put("endTime", Long.valueOf(a3.getTime()));
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (map.containsKey("location")) {
                hashMap.put("eventLocation", map.get("location"));
            }
            if (map.containsKey("summary")) {
                hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, map.get("summary"));
            }
            if (map.containsKey("transparency")) {
                hashMap.put("availability", Integer.valueOf(map.get("transparency").equals("transparent") ? 1 : 0));
            }
            String b = b(map);
            if (b != null) {
                hashMap.put("rrule", b);
            }
            return hashMap;
        }
        throw new IllegalArgumentException();
    }

    static String b(int i) {
        switch (i) {
            case 0:
                return "SU";
            case 1:
                return "MO";
            case 2:
                return "TU";
            case 3:
                return "WE";
            case 4:
                return "TH";
            case 5:
                return "FR";
            case 6:
                return "SA";
            default:
                throw new IllegalArgumentException();
        }
    }

    static URI b(String str) {
        if (str != null) {
            try {
                return new URI(str);
            } catch (URISyntaxException unused) {
                throw new f4("Invalid param: " + str);
            }
        } else {
            throw new f4("Null param");
        }
    }

    static URI a(String str, URI uri) {
        return str == null ? uri : b(str);
    }
}
