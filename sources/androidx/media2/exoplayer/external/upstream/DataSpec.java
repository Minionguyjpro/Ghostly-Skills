package androidx.media2.exoplayer.external.upstream;

import android.net.Uri;
import androidx.media2.exoplayer.external.util.Assertions;
import java.util.Arrays;

public final class DataSpec {
    public final long absoluteStreamPosition;
    public final int flags;
    public final byte[] httpBody;
    public final int httpMethod;
    public final String key;
    public final long length;
    public final long position;
    @Deprecated
    public final byte[] postBody;
    public final Uri uri;

    public DataSpec(Uri uri2, int i) {
        this(uri2, 0, -1, (String) null, i);
    }

    public DataSpec(Uri uri2, long j, long j2, String str) {
        this(uri2, j, j, j2, str, 0);
    }

    public DataSpec(Uri uri2, long j, long j2, String str, int i) {
        this(uri2, j, j, j2, str, i);
    }

    public DataSpec(Uri uri2, long j, long j2, long j3, String str, int i) {
        this(uri2, (byte[]) null, j, j2, j3, str, i);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DataSpec(Uri uri2, byte[] bArr, long j, long j2, long j3, String str, int i) {
        this(uri2, bArr != null ? 2 : 1, bArr, j, j2, j3, str, i);
    }

    public DataSpec(Uri uri2, int i, byte[] bArr, long j, long j2, long j3, String str, int i2) {
        byte[] bArr2 = bArr;
        long j4 = j;
        long j5 = j2;
        long j6 = j3;
        boolean z = true;
        Assertions.checkArgument(j4 >= 0);
        Assertions.checkArgument(j5 >= 0);
        if (j6 <= 0 && j6 != -1) {
            z = false;
        }
        Assertions.checkArgument(z);
        this.uri = uri2;
        this.httpMethod = i;
        bArr2 = (bArr2 == null || bArr2.length == 0) ? null : bArr2;
        this.httpBody = bArr2;
        this.postBody = bArr2;
        this.absoluteStreamPosition = j4;
        this.position = j5;
        this.length = j6;
        this.key = str;
        this.flags = i2;
    }

    public boolean isFlagSet(int i) {
        return (this.flags & i) == i;
    }

    public String toString() {
        String httpMethodString = getHttpMethodString();
        String valueOf = String.valueOf(this.uri);
        String arrays = Arrays.toString(this.httpBody);
        long j = this.absoluteStreamPosition;
        long j2 = this.position;
        long j3 = this.length;
        String str = this.key;
        int i = this.flags;
        StringBuilder sb = new StringBuilder(String.valueOf(httpMethodString).length() + 94 + String.valueOf(valueOf).length() + String.valueOf(arrays).length() + String.valueOf(str).length());
        sb.append("DataSpec[");
        sb.append(httpMethodString);
        sb.append(" ");
        sb.append(valueOf);
        sb.append(", ");
        sb.append(arrays);
        sb.append(", ");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(", ");
        sb.append(j3);
        sb.append(", ");
        sb.append(str);
        sb.append(", ");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }

    public final String getHttpMethodString() {
        return getStringForHttpMethod(this.httpMethod);
    }

    public static String getStringForHttpMethod(int i) {
        if (i == 1) {
            return "GET";
        }
        if (i == 2) {
            return "POST";
        }
        if (i == 3) {
            return "HEAD";
        }
        throw new AssertionError(i);
    }

    public DataSpec subrange(long j) {
        long j2 = this.length;
        long j3 = -1;
        if (j2 != -1) {
            j3 = j2 - j;
        }
        return subrange(j, j3);
    }

    public DataSpec subrange(long j, long j2) {
        if (j == 0 && this.length == j2) {
            return this;
        }
        return new DataSpec(this.uri, this.httpMethod, this.httpBody, this.absoluteStreamPosition + j, this.position + j, j2, this.key, this.flags);
    }
}
