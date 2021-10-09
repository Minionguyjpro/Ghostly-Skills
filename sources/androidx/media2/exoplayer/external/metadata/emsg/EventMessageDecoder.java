package androidx.media2.exoplayer.external.metadata.emsg;

import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.metadata.MetadataDecoder;
import androidx.media2.exoplayer.external.metadata.MetadataInputBuffer;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.Util;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class EventMessageDecoder implements MetadataDecoder {
    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        byte[] array = byteBuffer.array();
        int limit = byteBuffer.limit();
        ParsableByteArray parsableByteArray = new ParsableByteArray(array, limit);
        String str = (String) Assertions.checkNotNull(parsableByteArray.readNullTerminatedString());
        String str2 = (String) Assertions.checkNotNull(parsableByteArray.readNullTerminatedString());
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
        if (readUnsignedInt2 != 0) {
            StringBuilder sb = new StringBuilder(63);
            sb.append("Ignoring non-zero presentation_time_delta: ");
            sb.append(readUnsignedInt2);
            Log.w("EventMessageDecoder", sb.toString());
        }
        return new Metadata(new EventMessage(str, str2, Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), 1000, readUnsignedInt), parsableByteArray.readUnsignedInt(), Arrays.copyOfRange(array, parsableByteArray.getPosition(), limit)));
    }
}
