package com.startapp.a.a.g;

/* compiled from: StartAppSDK */
public enum a {
    ZERO("0", 1, 720),
    THREE("3.0", 1, 720) {
    },
    FOUR("4", 3, 3500),
    FIVE("5", 3, 1000000);
    
    private final String e;
    private final int f;
    private final int g;

    private a(String str, int i, int i2) {
        this.e = str;
        this.f = i;
        this.g = i2;
    }

    public String a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public int c() {
        return this.g;
    }
}
