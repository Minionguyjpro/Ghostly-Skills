package androidx.media2.player;

public class TimedMetaData {
    private byte[] mMetaData;
    private long mTimestampUs;

    public TimedMetaData(long j, byte[] bArr) {
        this.mTimestampUs = j;
        this.mMetaData = bArr;
    }
}
