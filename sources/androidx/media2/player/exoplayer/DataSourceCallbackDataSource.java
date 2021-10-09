package androidx.media2.player.exoplayer;

import android.net.Uri;
import androidx.core.util.Preconditions;
import androidx.media2.common.DataSourceCallback;
import androidx.media2.exoplayer.external.upstream.BaseDataSource;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.DataSpec;
import java.io.EOFException;
import java.io.IOException;

public final class DataSourceCallbackDataSource extends BaseDataSource {
    private long mBytesRemaining;
    private final DataSourceCallback mDataSourceCallback;
    private long mOffset;
    private boolean mOpened;
    private Uri mUri;

    static DataSource.Factory getFactory(final DataSourceCallback dataSourceCallback) {
        return new DataSource.Factory() {
            public DataSource createDataSource() {
                return new DataSourceCallbackDataSource(dataSourceCallback);
            }
        };
    }

    public DataSourceCallbackDataSource(DataSourceCallback dataSourceCallback) {
        super(false);
        this.mDataSourceCallback = (DataSourceCallback) Preconditions.checkNotNull(dataSourceCallback);
    }

    public long open(DataSpec dataSpec) throws IOException {
        this.mUri = dataSpec.uri;
        this.mOffset = dataSpec.position;
        transferInitializing(dataSpec);
        long size = this.mDataSourceCallback.getSize();
        if (dataSpec.length != -1) {
            this.mBytesRemaining = dataSpec.length;
        } else if (size != -1) {
            this.mBytesRemaining = size - this.mOffset;
        } else {
            this.mBytesRemaining = -1;
        }
        this.mOpened = true;
        transferStarted(dataSpec);
        return this.mBytesRemaining;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.mBytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            i2 = (int) Math.min(j, (long) i2);
        }
        int readAt = this.mDataSourceCallback.readAt(this.mOffset, bArr, i, i2);
        if (readAt >= 0) {
            long j2 = (long) readAt;
            this.mOffset += j2;
            long j3 = this.mBytesRemaining;
            if (j3 != -1) {
                this.mBytesRemaining = j3 - j2;
            }
            bytesTransferred(readAt);
            return readAt;
        } else if (this.mBytesRemaining == -1) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    public Uri getUri() {
        return this.mUri;
    }

    public void close() {
        this.mUri = null;
        if (this.mOpened) {
            this.mOpened = false;
            transferEnded();
        }
    }
}
