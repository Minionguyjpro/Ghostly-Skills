package androidx.media2.exoplayer.external.metadata.scte35;

import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.metadata.MetadataDecoder;
import androidx.media2.exoplayer.external.metadata.MetadataInputBuffer;
import androidx.media2.exoplayer.external.util.ParsableBitArray;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.TimestampAdjuster;
import java.nio.ByteBuffer;

public final class SpliceInfoDecoder implements MetadataDecoder {
    private final ParsableByteArray sectionData = new ParsableByteArray();
    private final ParsableBitArray sectionHeader = new ParsableBitArray();
    private TimestampAdjuster timestampAdjuster;

    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        if (this.timestampAdjuster == null || metadataInputBuffer.subsampleOffsetUs != this.timestampAdjuster.getTimestampOffsetUs()) {
            TimestampAdjuster timestampAdjuster2 = new TimestampAdjuster(metadataInputBuffer.timeUs);
            this.timestampAdjuster = timestampAdjuster2;
            timestampAdjuster2.adjustSampleTimestamp(metadataInputBuffer.timeUs - metadataInputBuffer.subsampleOffsetUs);
        }
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        byte[] array = byteBuffer.array();
        int limit = byteBuffer.limit();
        this.sectionData.reset(array, limit);
        this.sectionHeader.reset(array, limit);
        this.sectionHeader.skipBits(39);
        long readBits = (((long) this.sectionHeader.readBits(1)) << 32) | ((long) this.sectionHeader.readBits(32));
        this.sectionHeader.skipBits(20);
        int readBits2 = this.sectionHeader.readBits(12);
        int readBits3 = this.sectionHeader.readBits(8);
        Metadata.Entry entry = null;
        this.sectionData.skipBytes(14);
        if (readBits3 == 0) {
            entry = new SpliceNullCommand();
        } else if (readBits3 == 255) {
            entry = PrivateCommand.parseFromSection(this.sectionData, readBits2, readBits);
        } else if (readBits3 == 4) {
            entry = SpliceScheduleCommand.parseFromSection(this.sectionData);
        } else if (readBits3 == 5) {
            entry = SpliceInsertCommand.parseFromSection(this.sectionData, readBits, this.timestampAdjuster);
        } else if (readBits3 == 6) {
            entry = TimeSignalCommand.parseFromSection(this.sectionData, readBits, this.timestampAdjuster);
        }
        if (entry == null) {
            return new Metadata(new Metadata.Entry[0]);
        }
        return new Metadata(entry);
    }
}
