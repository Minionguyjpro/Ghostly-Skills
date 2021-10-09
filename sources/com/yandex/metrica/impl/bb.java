package com.yandex.metrica.impl;

import android.util.SparseArray;

public final class bb {

    /* renamed from: a  reason: collision with root package name */
    static final a f744a = new a("273", bc.a());
    static final SparseArray<a> b;

    static final class a {

        /* renamed from: a  reason: collision with root package name */
        String f745a;
        String b;

        public a(String str, String str2) {
            this.f745a = str;
            this.b = str2;
        }
    }

    static {
        SparseArray<a> sparseArray = new SparseArray<>();
        b = sparseArray;
        sparseArray.put(1, new a("100", "1.00"));
        b.put(2, new a("110", "1.10"));
        b.put(3, new a("111", "1.11"));
        b.put(4, new a("120", "1.20"));
        b.put(5, new a("121", "1.21"));
        b.put(6, new a("122", "1.22"));
        b.put(7, new a("123", "1.23"));
        b.put(8, new a("124", "1.24"));
        b.put(9, new a("126", "1.26"));
        b.put(10, new a("127", "1.27"));
        b.put(11, new a("140", "1.40"));
        b.put(12, new a("141", "1.41"));
        b.put(13, new a("142", "1.42"));
        b.put(14, new a("150", "1.50"));
        b.put(15, new a("151", "1.51"));
        b.put(16, new a("160", "1.60"));
        b.put(17, new a("161", "1.61"));
        b.put(18, new a("162", "1.62"));
        b.put(19, new a("163", "1.63"));
        b.put(20, new a("164", "1.64"));
        b.put(21, new a("165", "1.65"));
        b.put(22, new a("166", "1.66"));
        b.put(23, new a("167", "1.67"));
        b.put(24, new a("168", "1.68"));
        b.put(25, new a("169", "1.69"));
        b.put(26, new a("170", "1.70"));
        b.put(27, new a("171", "1.71"));
        b.put(28, new a("172", "1.72"));
        b.put(29, new a("180", "1.80"));
        b.put(30, new a("181", "1.81"));
        b.put(31, new a("182", "1.82"));
        b.put(32, new a("200", "2.00"));
        b.put(33, new a("210", "2.10"));
        b.put(34, new a("211", "2.11"));
        b.put(35, new a("220", "2.20"));
        b.put(36, new a("221", "2.21"));
        b.put(37, new a("222", "2.22"));
        b.put(38, new a("223", "2.23"));
        b.put(39, new a("230", "2.30"));
        b.put(40, new a("231", "2.31"));
        b.put(41, new a("232", "2.32"));
        b.put(42, new a("233", "2.33"));
        b.put(43, new a("240", "2.40"));
        b.put(44, new a("241", "2.41"));
        b.put(45, new a("242", "2.42"));
        b.put(46, new a("243", "2.43"));
        b.put(47, new a("250", "2.50"));
        b.put(48, new a("251", "2.51"));
        b.put(49, new a("252", "2.52"));
        b.put(50, new a("260", "2.60"));
        b.put(51, new a("261", "2.61"));
        b.put(52, new a("262", "2.62"));
        b.put(53, new a("263", "2.63"));
        b.put(54, new a("264", "2.64"));
        b.put(55, new a("270", "2.70"));
        b.put(56, new a("271", "2.71"));
        b.put(57, new a("272", "2.72"));
        b.put(58, new a("273", "2.73"));
    }

    static a a(int i) {
        return b.get(i);
    }
}
