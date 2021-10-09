package com.google.android.play.core.install;

public abstract class InstallState {
    public static InstallState a(int i, long j, long j2, int i2, String str) {
        return new a(i, j, j2, i2, str);
    }

    public abstract long bytesDownloaded();

    public abstract int installErrorCode();

    public abstract int installStatus();

    public abstract String packageName();

    public abstract long totalBytesToDownload();
}
