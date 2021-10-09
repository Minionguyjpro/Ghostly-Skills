package com.google.android.exoplayer2.drm;

public final class UnsupportedDrmException extends Exception {
    public final int reason;

    public UnsupportedDrmException(int i) {
        this.reason = i;
    }
}
