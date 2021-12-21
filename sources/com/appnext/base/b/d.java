package com.appnext.base.b;

import com.appnext.core.i;

public final class d {
    public static final String STATUS = "status";
    public static final String eS = "service_key";
    public static final String eT = "4.7.2";
    public static final String eU = "config.json";
    public static final String eV = "plist.json";
    public static final String eW = "/data/appnext/";
    public static final String eX = "videos/";
    public static final String eY = ".tmp";
    public static final String eZ = "http://cdn.appnext.com/tools/services/4.7.2/config.json";
    public static final String fa = "http://cdn.appnext.com/tools/services/4.7.2/plist.json";
    public static final int fb = 1024;
    public static final long fc = 1048576;
    public static final int fd = 15000;
    public static final String fe = "on";
    public static final String ff = "off";
    public static final String fg = "config_data_obj";
    public static final String fh = "second";
    public static final String fi = "minute";
    public static final String fj = "hour";
    public static final String fk = "day";
    public static final String fl = "time";
    public static final String fm = "once";
    public static final String fn = "interval";
    public static final String fo = "isAidDisabled";
    public static final String fp = "aidForSend";

    public static final String aL() {
        return i.hm;
    }

    public static final String aM() {
        return i.hn;
    }

    public enum a {
        String("String"),
        Long("Long"),
        Double("Double"),
        Integer("Integer"),
        HashMap("HashMap"),
        ArrayList("ArrayList"),
        Boolean("Boolean"),
        JSONArray("JSONArray"),
        JSONObject("JSONObject"),
        Set("Set");
        
        private String mDataType;

        private a(String str) {
            this.mDataType = str;
        }

        public final String getType() {
            return this.mDataType;
        }
    }
}
