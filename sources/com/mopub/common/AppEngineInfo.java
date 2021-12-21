package com.mopub.common;

public final class AppEngineInfo {
    final String mName;
    final String mVersion;

    public AppEngineInfo(String str, String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.mName = str;
        this.mVersion = str2;
    }
}
