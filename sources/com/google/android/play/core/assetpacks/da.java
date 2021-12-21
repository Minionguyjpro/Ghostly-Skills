package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FilenameFilter;

final /* synthetic */ class da implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    static final FilenameFilter f1087a = new da();

    private da() {
    }

    public final boolean accept(File file, String str) {
        return db.f1088a.matcher(str).matches();
    }
}
