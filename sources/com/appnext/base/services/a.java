package com.appnext.base.services;

import com.appnext.base.operations.b;

public final class a {
    private static volatile a eA;
    private String dP;

    private a() {
    }

    public static a aK() {
        if (eA == null) {
            synchronized (b.class) {
                if (eA == null) {
                    eA = new a();
                }
            }
        }
        return eA;
    }

    public final synchronized void setKey(String str) {
        this.dP = str;
    }

    public final synchronized String getKey() {
        return this.dP;
    }
}
