package com.google.android.exoplayer2.metadata.emsg;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class EventMessageDecoder {
    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(metadataInputBuffer.data);
        return new Metadata(decode(new ParsableByteArray(byteBuffer.array(), byteBuffer.limit())));
    }

    public EventMessage decode(ParsableByteArray parsableByteArray) {
        return new EventMessage((String) Assertions.checkNotNull(parsableByteArray.readNullTerminatedString()), (String) Assertions.checkNotNull(parsableByteArray.readNullTerminatedString()), parsableByteArray.readUnsignedInt(), parsableByteArray.readUnsignedInt(), Arrays.copyOfRange(parsableByteArray.data, parsableByteArray.getPosition(), parsableByteArray.limit()));
    }
}
