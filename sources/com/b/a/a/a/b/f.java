package com.b.a.a.a.b;

public enum f {
    NATIVE("native"),
    JAVASCRIPT("javascript"),
    NONE("none");
    
    private final String owner;

    private f(String str) {
        this.owner = str;
    }

    public String toString() {
        return this.owner;
    }
}
