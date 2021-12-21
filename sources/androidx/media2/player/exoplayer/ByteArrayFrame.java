package androidx.media2.player.exoplayer;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.util.Util;
import java.util.Arrays;

final class ByteArrayFrame implements Metadata.Entry {
    public static final Parcelable.Creator<ByteArrayFrame> CREATOR = new Parcelable.Creator<ByteArrayFrame>() {
        public ByteArrayFrame createFromParcel(Parcel parcel) {
            return new ByteArrayFrame(parcel);
        }

        public ByteArrayFrame[] newArray(int i) {
            return new ByteArrayFrame[i];
        }
    };
    public final byte[] mData;
    public final long mTimestamp;

    public int describeContents() {
        return 0;
    }

    ByteArrayFrame(long j, byte[] bArr) {
        this.mTimestamp = j;
        this.mData = bArr;
    }

    ByteArrayFrame(Parcel parcel) {
        this.mTimestamp = parcel.readLong();
        byte[] bArr = new byte[parcel.readInt()];
        parcel.readByteArray(bArr);
        this.mData = bArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ByteArrayFrame byteArrayFrame = (ByteArrayFrame) obj;
        if (!Util.areEqual(Long.valueOf(this.mTimestamp), Long.valueOf(byteArrayFrame.mTimestamp)) || !Arrays.equals(this.mData, byteArrayFrame.mData)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((527 + ((int) this.mTimestamp)) * 31) + Arrays.hashCode(this.mData);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mTimestamp);
        parcel.writeByteArray(this.mData);
    }
}
