package androidx.media2.player.exoplayer;

import android.net.Uri;
import androidx.core.util.Preconditions;
import androidx.media2.exoplayer.external.upstream.BaseDataSource;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.DataSpec;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class FileDescriptorDataSource extends BaseDataSource {
    private long mBytesRemaining;
    private final FileDescriptor mFileDescriptor;
    private InputStream mInputStream;
    private final long mLength;
    private final Object mLock;
    private final long mOffset;
    private boolean mOpened;
    private long mPosition;
    private Uri mUri;

    static DataSource.Factory getFactory(FileDescriptor fileDescriptor, long j, long j2, Object obj) {
        final FileDescriptor fileDescriptor2 = fileDescriptor;
        final long j3 = j;
        final long j4 = j2;
        final Object obj2 = obj;
        return new DataSource.Factory() {
            public DataSource createDataSource() {
                return new FileDescriptorDataSource(fileDescriptor2, j3, j4, obj2);
            }
        };
    }

    FileDescriptorDataSource(FileDescriptor fileDescriptor, long j, long j2, Object obj) {
        super(false);
        this.mFileDescriptor = fileDescriptor;
        this.mOffset = j;
        this.mLength = j2;
        this.mLock = obj;
    }

    public long open(DataSpec dataSpec) {
        this.mUri = dataSpec.uri;
        transferInitializing(dataSpec);
        this.mInputStream = new FileInputStream(this.mFileDescriptor);
        if (dataSpec.length != -1) {
            this.mBytesRemaining = dataSpec.length;
        } else {
            long j = this.mLength;
            if (j != -1) {
                this.mBytesRemaining = j - dataSpec.position;
            } else {
                this.mBytesRemaining = -1;
            }
        }
        this.mPosition = this.mOffset + dataSpec.position;
        this.mOpened = true;
        transferStarted(dataSpec);
        return this.mBytesRemaining;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        r9 = r7.mBytesRemaining;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        if (r9 == -1) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        r7.mBytesRemaining = r9 - r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        bytesTransferred(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0054, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r8, int r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            if (r10 != 0) goto L_0x0004
            r8 = 0
            return r8
        L_0x0004:
            long r0 = r7.mBytesRemaining
            r2 = 0
            r4 = -1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 != 0) goto L_0x000e
            return r4
        L_0x000e:
            r2 = -1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 != 0) goto L_0x0015
            goto L_0x001b
        L_0x0015:
            long r5 = (long) r10
            long r0 = java.lang.Math.min(r0, r5)
            int r10 = (int) r0
        L_0x001b:
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            java.io.FileDescriptor r1 = r7.mFileDescriptor     // Catch:{ all -> 0x0055 }
            long r5 = r7.mPosition     // Catch:{ all -> 0x0055 }
            androidx.media2.player.exoplayer.FileDescriptorUtil.seek(r1, r5)     // Catch:{ all -> 0x0055 }
            java.io.InputStream r1 = r7.mInputStream     // Catch:{ all -> 0x0055 }
            java.lang.Object r1 = androidx.core.util.Preconditions.checkNotNull(r1)     // Catch:{ all -> 0x0055 }
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch:{ all -> 0x0055 }
            int r8 = r1.read(r8, r9, r10)     // Catch:{ all -> 0x0055 }
            if (r8 != r4) goto L_0x0041
            long r8 = r7.mBytesRemaining     // Catch:{ all -> 0x0055 }
            int r10 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r10 != 0) goto L_0x003b
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return r4
        L_0x003b:
            java.io.EOFException r8 = new java.io.EOFException     // Catch:{ all -> 0x0055 }
            r8.<init>()     // Catch:{ all -> 0x0055 }
            throw r8     // Catch:{ all -> 0x0055 }
        L_0x0041:
            long r9 = r7.mPosition     // Catch:{ all -> 0x0055 }
            long r4 = (long) r8     // Catch:{ all -> 0x0055 }
            long r9 = r9 + r4
            r7.mPosition = r9     // Catch:{ all -> 0x0055 }
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            long r9 = r7.mBytesRemaining
            int r0 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x0051
            long r9 = r9 - r4
            r7.mBytesRemaining = r9
        L_0x0051:
            r7.bytesTransferred(r8)
            return r8
        L_0x0055:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.exoplayer.FileDescriptorDataSource.read(byte[], int, int):int");
    }

    public Uri getUri() {
        return (Uri) Preconditions.checkNotNull(this.mUri);
    }

    public void close() throws IOException {
        this.mUri = null;
        try {
            if (this.mInputStream != null) {
                this.mInputStream.close();
            }
        } finally {
            this.mInputStream = null;
            if (this.mOpened) {
                this.mOpened = false;
                transferEnded();
            }
        }
    }
}
