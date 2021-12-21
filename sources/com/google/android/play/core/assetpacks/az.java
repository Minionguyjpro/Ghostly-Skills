package com.google.android.play.core.assetpacks;

final class az extends AssetPackState {

    /* renamed from: a  reason: collision with root package name */
    private final String f1038a;
    private final int b;
    private final int c;
    private final long d;
    private final long e;
    private final int f;
    private final int g;
    private final String h;

    az(String str, int i, int i2, long j, long j2, int i3, int i4, String str2) {
        if (str != null) {
            this.f1038a = str;
            this.b = i;
            this.c = i2;
            this.d = j;
            this.e = j2;
            this.f = i3;
            this.g = i4;
            if (str2 != null) {
                this.h = str2;
                return;
            }
            throw new NullPointerException("Null versionTag");
        }
        throw new NullPointerException("Null name");
    }

    public final int a() {
        return this.g;
    }

    public final String b() {
        return this.h;
    }

    public final long bytesDownloaded() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackState) {
            AssetPackState assetPackState = (AssetPackState) obj;
            return this.f1038a.equals(assetPackState.name()) && this.b == assetPackState.status() && this.c == assetPackState.errorCode() && this.d == assetPackState.bytesDownloaded() && this.e == assetPackState.totalBytesToDownload() && this.f == assetPackState.transferProgressPercentage() && this.g == assetPackState.a() && this.h.equals(assetPackState.b());
        }
    }

    public final int errorCode() {
        return this.c;
    }

    public final int hashCode() {
        int hashCode = this.f1038a.hashCode();
        int i = this.b;
        int i2 = this.c;
        long j = this.d;
        long j2 = this.e;
        return ((((((((((((((hashCode ^ 1000003) * 1000003) ^ i) * 1000003) ^ i2) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.f) * 1000003) ^ this.g) * 1000003) ^ this.h.hashCode();
    }

    public final String name() {
        return this.f1038a;
    }

    public final int status() {
        return this.b;
    }

    public final String toString() {
        String str = this.f1038a;
        int i = this.b;
        int i2 = this.c;
        long j = this.d;
        long j2 = this.e;
        int i3 = this.f;
        int i4 = this.g;
        String str2 = this.h;
        StringBuilder sb = new StringBuilder(str.length() + 230 + str2.length());
        sb.append("AssetPackState{name=");
        sb.append(str);
        sb.append(", status=");
        sb.append(i);
        sb.append(", errorCode=");
        sb.append(i2);
        sb.append(", bytesDownloaded=");
        sb.append(j);
        sb.append(", totalBytesToDownload=");
        sb.append(j2);
        sb.append(", transferProgressPercentage=");
        sb.append(i3);
        sb.append(", updateAvailability=");
        sb.append(i4);
        sb.append(", versionTag=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }

    public final long totalBytesToDownload() {
        return this.e;
    }

    public final int transferProgressPercentage() {
        return this.f;
    }
}
