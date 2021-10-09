package androidx.media2.exoplayer.external.metadata.id3;

import androidx.media2.exoplayer.external.metadata.Metadata;

public abstract class Id3Frame implements Metadata.Entry {
    public final String id;

    public int describeContents() {
        return 0;
    }

    public Id3Frame(String str) {
        this.id = str;
    }

    public String toString() {
        return this.id;
    }
}
