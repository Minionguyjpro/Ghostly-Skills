package androidx.media2.exoplayer.external.source.hls;

import android.net.Uri;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.util.TimestampAdjuster;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface HlsExtractorFactory {
    public static final HlsExtractorFactory DEFAULT = new DefaultHlsExtractorFactory();

    Result createExtractor(Extractor extractor, Uri uri, Format format, List<Format> list, DrmInitData drmInitData, TimestampAdjuster timestampAdjuster, Map<String, List<String>> map, ExtractorInput extractorInput) throws InterruptedException, IOException;

    public static final class Result {
        public final Extractor extractor;
        public final boolean isPackedAudioExtractor;
        public final boolean isReusable;

        public Result(Extractor extractor2, boolean z, boolean z2) {
            this.extractor = extractor2;
            this.isPackedAudioExtractor = z;
            this.isReusable = z2;
        }
    }
}
