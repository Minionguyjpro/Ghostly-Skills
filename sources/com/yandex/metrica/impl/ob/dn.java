package com.yandex.metrica.impl.ob;

public class dn extends Cdo {
    private dm b;

    public dn(int i) {
        super(i);
        this.b = new dm(i);
    }

    public void a() {
        try {
            Thread.sleep((long) this.b.a());
        } catch (InterruptedException unused) {
        }
        super.a();
    }
}
