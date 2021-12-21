package com.mopub.mobileads;

@Deprecated
public interface MoPubError {
    public static final int ER_ADAPTER_NOT_FOUND = 1;
    public static final int ER_INVALID_DATA = 3;
    public static final int ER_SUCCESS = 0;
    public static final int ER_TIMEOUT = 2;
    public static final int ER_UNSPECIFIED = 10000;

    int getIntCode();
}
