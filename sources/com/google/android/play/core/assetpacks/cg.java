package com.google.android.play.core.assetpacks;

final /* synthetic */ class cg implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final cj f1067a;
    private final int b;
    private final String c;

    cg(cj cjVar, int i, String str) {
        this.f1067a = cjVar;
        this.b = i;
        this.c = str;
    }

    public final void run() {
        this.f1067a.m(this.b, this.c);
    }
}
