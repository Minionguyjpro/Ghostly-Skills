package androidx.media2.exoplayer.external.upstream;

import android.net.Uri;
import android.util.Base64;
import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.util.Util;
import com.appnext.base.b.c;
import java.io.IOException;
import java.net.URLDecoder;

public final class DataSchemeDataSource extends BaseDataSource {
    private int bytesRead;
    private byte[] data;
    private int dataLength;
    private DataSpec dataSpec;

    public DataSchemeDataSource() {
        super(false);
    }

    public long open(DataSpec dataSpec2) throws IOException {
        transferInitializing(dataSpec2);
        this.dataSpec = dataSpec2;
        Uri uri = dataSpec2.uri;
        String scheme = uri.getScheme();
        if (!c.DATA.equals(scheme)) {
            String valueOf = String.valueOf(scheme);
            throw new ParserException(valueOf.length() != 0 ? "Unsupported scheme: ".concat(valueOf) : new String("Unsupported scheme: "));
        }
        String[] split = Util.split(uri.getSchemeSpecificPart(), ",");
        if (split.length == 2) {
            String str = split[1];
            if (split[0].contains(";base64")) {
                try {
                    byte[] decode = Base64.decode(str, 0);
                    this.data = decode;
                    this.dataLength = decode.length;
                } catch (IllegalArgumentException e) {
                    String valueOf2 = String.valueOf(str);
                    throw new ParserException(valueOf2.length() != 0 ? "Error while parsing Base64 encoded string: ".concat(valueOf2) : new String("Error while parsing Base64 encoded string: "), e);
                }
            } else {
                byte[] utf8Bytes = Util.getUtf8Bytes(URLDecoder.decode(str, "US-ASCII"));
                this.data = utf8Bytes;
                this.dataLength = utf8Bytes.length;
            }
            transferStarted(dataSpec2);
            return (long) this.dataLength;
        }
        String valueOf3 = String.valueOf(uri);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf3).length() + 23);
        sb.append("Unexpected URI format: ");
        sb.append(valueOf3);
        throw new ParserException(sb.toString());
    }

    public int read(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.dataLength - this.bytesRead;
        if (i3 == 0) {
            return -1;
        }
        int min = Math.min(i2, i3);
        System.arraycopy(Util.castNonNull(this.data), this.bytesRead, bArr, i, min);
        this.bytesRead += min;
        bytesTransferred(min);
        return min;
    }

    public Uri getUri() {
        DataSpec dataSpec2 = this.dataSpec;
        if (dataSpec2 != null) {
            return dataSpec2.uri;
        }
        return null;
    }

    public void close() throws IOException {
        if (this.data != null) {
            this.data = null;
            transferEnded();
        }
        this.dataSpec = null;
    }
}
