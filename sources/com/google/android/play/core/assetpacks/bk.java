package com.google.android.play.core.assetpacks;

final class bk extends RuntimeException {

    /* renamed from: a  reason: collision with root package name */
    final int f1049a;

    bk(String str) {
        super(str);
        this.f1049a = -1;
    }

    bk(String str, int i) {
        super(str);
        this.f1049a = i;
    }

    bk(String str, Exception exc) {
        super(str, exc);
        this.f1049a = -1;
    }

    bk(String str, Exception exc, int i) {
        super(str, exc);
        this.f1049a = i;
    }
}
