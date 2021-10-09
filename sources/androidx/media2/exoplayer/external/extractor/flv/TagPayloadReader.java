package androidx.media2.exoplayer.external.extractor.flv;

import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.extractor.TrackOutput;
import androidx.media2.exoplayer.external.util.ParsableByteArray;

abstract class TagPayloadReader {
    protected final TrackOutput output;

    /* access modifiers changed from: protected */
    public abstract boolean parseHeader(ParsableByteArray parsableByteArray) throws ParserException;

    /* access modifiers changed from: protected */
    public abstract void parsePayload(ParsableByteArray parsableByteArray, long j) throws ParserException;

    public static final class UnsupportedFormatException extends ParserException {
        public UnsupportedFormatException(String str) {
            super(str);
        }
    }

    protected TagPayloadReader(TrackOutput trackOutput) {
        this.output = trackOutput;
    }

    public final void consume(ParsableByteArray parsableByteArray, long j) throws ParserException {
        if (parseHeader(parsableByteArray)) {
            parsePayload(parsableByteArray, j);
        }
    }
}
