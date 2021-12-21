package com.appnext.base.a.b;

import java.util.Date;

public final class b extends d {
    private String dG;
    private String dH;
    private String dI;
    private Date dJ;
    private String mDataType;

    public b(String str, String str2, String str3) {
        this(str, str, str2, (Date) null, str3);
    }

    public b(String str, String str2, String str3, String str4) {
        this(str, str2, str3, (Date) null, str4);
    }

    public b(String str, String str2, String str3, Date date, String str4) {
        this.dG = str;
        this.dH = str2;
        this.dI = str3;
        this.dJ = date;
        this.mDataType = str4;
    }

    public final String ah() {
        return this.dG;
    }

    public final String getType() {
        return this.dH;
    }

    public final String ai() {
        return this.dI;
    }

    public final Date aj() {
        return this.dJ;
    }

    public final String getDataType() {
        return this.mDataType;
    }
}
