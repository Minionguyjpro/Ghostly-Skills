package com.startapp.a.a.c;

import java.io.Serializable;
import java.io.Writer;

/* compiled from: StartAppSDK */
public class e extends Writer implements Serializable {
    private final StringBuilder b;

    public void close() {
    }

    public void flush() {
    }

    public e() {
        this.b = new StringBuilder();
    }

    public e(int i) {
        this.b = new StringBuilder(i);
    }

    public Writer append(char c) {
        this.b.append(c);
        return this;
    }

    public Writer append(CharSequence charSequence) {
        this.b.append(charSequence);
        return this;
    }

    public Writer append(CharSequence charSequence, int i, int i2) {
        this.b.append(charSequence, i, i2);
        return this;
    }

    public void write(String str) {
        if (str != null) {
            this.b.append(str);
        }
    }

    public void write(char[] cArr, int i, int i2) {
        if (cArr != null) {
            this.b.append(cArr, i, i2);
        }
    }

    public String toString() {
        return this.b.toString();
    }
}
