package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface DataSource {

    public interface Factory {
        DataSource createDataSource();
    }

    void addTransferListener(TransferListener transferListener);

    void close() throws IOException;

    Map<String, List<String>> getResponseHeaders();

    Uri getUri();

    long open(DataSpec dataSpec) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    /* renamed from: com.google.android.exoplayer2.upstream.DataSource$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Map $default$getResponseHeaders(DataSource _this) {
            return Collections.emptyMap();
        }
    }
}
