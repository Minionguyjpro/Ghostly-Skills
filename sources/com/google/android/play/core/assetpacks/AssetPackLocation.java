package com.google.android.play.core.assetpacks;

public abstract class AssetPackLocation {

    /* renamed from: a  reason: collision with root package name */
    private static final AssetPackLocation f1021a = new ay(1, (String) null, (String) null);

    static AssetPackLocation b(String str, String str2) {
        return new ay(0, str, str2);
    }

    public abstract String assetsPath();

    public abstract int packStorageMethod();

    public abstract String path();
}
