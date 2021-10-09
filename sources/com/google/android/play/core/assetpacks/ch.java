package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FilenameFilter;

final /* synthetic */ class ch implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    private final String f1068a;

    ch(String str) {
        this.f1068a = str;
    }

    public final boolean accept(File file, String str) {
        return str.startsWith(String.valueOf(this.f1068a).concat("-")) && str.endsWith(".apk");
    }
}
