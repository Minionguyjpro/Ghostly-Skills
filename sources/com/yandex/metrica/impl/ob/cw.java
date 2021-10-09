package com.yandex.metrica.impl.ob;

public class cw {

    /* renamed from: a  reason: collision with root package name */
    private final String f839a;
    private final boolean b;

    public cw(String str, boolean z) {
        this.f839a = str;
        this.b = z;
    }

    public boolean a() {
        return this.b;
    }

    public String b() {
        return this.f839a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        cw cwVar = (cw) obj;
        if (this.b != cwVar.b) {
            return false;
        }
        return this.f839a.equals(cwVar.f839a);
    }

    public int hashCode() {
        return (this.f839a.hashCode() * 31) + (this.b ? 1 : 0);
    }
}
