package com.startapp.common;

/* compiled from: StartAppSDK */
public class e extends Exception {
    private boolean b;
    private int c;

    public e(String str, Throwable th, boolean z, int i) {
        super(str, th);
        this.b = false;
        this.c = 0;
        this.b = z;
        this.c = i;
    }

    public e(String str, Throwable th, int i) {
        this(str, th, false, i);
    }

    public int a() {
        return this.c;
    }

    public e() {
        this.b = false;
        this.c = 0;
    }

    public e(String str, Throwable th) {
        this(str, th, false);
    }

    public e(String str, Throwable th, boolean z) {
        this(str, th, z, 0);
    }

    public boolean b() {
        return this.b;
    }
}
