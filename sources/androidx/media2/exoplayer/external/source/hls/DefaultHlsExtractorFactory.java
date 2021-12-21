package androidx.media2.exoplayer.external.source.hls;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.extractor.mp3.Mp3Extractor;
import androidx.media2.exoplayer.external.extractor.mp4.FragmentedMp4Extractor;
import androidx.media2.exoplayer.external.extractor.mp4.Track;
import androidx.media2.exoplayer.external.extractor.ts.Ac3Extractor;
import androidx.media2.exoplayer.external.extractor.ts.Ac4Extractor;
import androidx.media2.exoplayer.external.extractor.ts.AdtsExtractor;
import androidx.media2.exoplayer.external.extractor.ts.DefaultTsPayloadReaderFactory;
import androidx.media2.exoplayer.external.extractor.ts.TsExtractor;
import androidx.media2.exoplayer.external.source.hls.HlsExtractorFactory;
import androidx.media2.exoplayer.external.util.MimeTypes;
import androidx.media2.exoplayer.external.util.TimestampAdjuster;
import java.io.EOFException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class DefaultHlsExtractorFactory implements HlsExtractorFactory {
    private final boolean exposeCea608WhenMissingDeclarations;
    private final int payloadReaderFactoryFlags;

    public DefaultHlsExtractorFactory() {
        this(0, true);
    }

    public DefaultHlsExtractorFactory(int i, boolean z) {
        this.payloadReaderFactoryFlags = i;
        this.exposeCea608WhenMissingDeclarations = z;
    }

    public HlsExtractorFactory.Result createExtractor(Extractor extractor, Uri uri, Format format, List<Format> list, DrmInitData drmInitData, TimestampAdjuster timestampAdjuster, Map<String, List<String>> map, ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (extractor != null) {
            if (isReusable(extractor)) {
                return buildResult(extractor);
            }
            if (buildResultForSameExtractorType(extractor, format, timestampAdjuster) == null) {
                String valueOf = String.valueOf(extractor.getClass().getSimpleName());
                throw new IllegalArgumentException(valueOf.length() != 0 ? "Unexpected previousExtractor type: ".concat(valueOf) : new String("Unexpected previousExtractor type: "));
            }
        }
        Extractor createExtractorByFileExtension = createExtractorByFileExtension(uri, format, list, drmInitData, timestampAdjuster);
        extractorInput.resetPeekPosition();
        if (sniffQuietly(createExtractorByFileExtension, extractorInput)) {
            return buildResult(createExtractorByFileExtension);
        }
        if (!(createExtractorByFileExtension instanceof WebvttExtractor)) {
            WebvttExtractor webvttExtractor = new WebvttExtractor(format.language, timestampAdjuster);
            if (sniffQuietly(webvttExtractor, extractorInput)) {
                return buildResult(webvttExtractor);
            }
        }
        if (!(createExtractorByFileExtension instanceof AdtsExtractor)) {
            AdtsExtractor adtsExtractor = new AdtsExtractor();
            if (sniffQuietly(adtsExtractor, extractorInput)) {
                return buildResult(adtsExtractor);
            }
        }
        if (!(createExtractorByFileExtension instanceof Ac3Extractor)) {
            Ac3Extractor ac3Extractor = new Ac3Extractor();
            if (sniffQuietly(ac3Extractor, extractorInput)) {
                return buildResult(ac3Extractor);
            }
        }
        if (!(createExtractorByFileExtension instanceof Ac4Extractor)) {
            Ac4Extractor ac4Extractor = new Ac4Extractor();
            if (sniffQuietly(ac4Extractor, extractorInput)) {
                return buildResult(ac4Extractor);
            }
        }
        if (!(createExtractorByFileExtension instanceof Mp3Extractor)) {
            Mp3Extractor mp3Extractor = new Mp3Extractor(0, 0);
            if (sniffQuietly(mp3Extractor, extractorInput)) {
                return buildResult(mp3Extractor);
            }
        }
        if (!(createExtractorByFileExtension instanceof FragmentedMp4Extractor)) {
            FragmentedMp4Extractor createFragmentedMp4Extractor = createFragmentedMp4Extractor(timestampAdjuster, drmInitData, list);
            if (sniffQuietly(createFragmentedMp4Extractor, extractorInput)) {
                return buildResult(createFragmentedMp4Extractor);
            }
        }
        if (!(createExtractorByFileExtension instanceof TsExtractor)) {
            TsExtractor createTsExtractor = createTsExtractor(this.payloadReaderFactoryFlags, this.exposeCea608WhenMissingDeclarations, format, list, timestampAdjuster);
            if (sniffQuietly(createTsExtractor, extractorInput)) {
                return buildResult(createTsExtractor);
            }
        }
        return buildResult(createExtractorByFileExtension);
    }

    private Extractor createExtractorByFileExtension(Uri uri, Format format, List<Format> list, DrmInitData drmInitData, TimestampAdjuster timestampAdjuster) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null) {
            lastPathSegment = "";
        }
        if ("text/vtt".equals(format.sampleMimeType) || lastPathSegment.endsWith(".webvtt") || lastPathSegment.endsWith(".vtt")) {
            return new WebvttExtractor(format.language, timestampAdjuster);
        }
        if (lastPathSegment.endsWith(".aac")) {
            return new AdtsExtractor();
        }
        if (lastPathSegment.endsWith(".ac3") || lastPathSegment.endsWith(".ec3")) {
            return new Ac3Extractor();
        }
        if (lastPathSegment.endsWith(".ac4")) {
            return new Ac4Extractor();
        }
        if (lastPathSegment.endsWith(".mp3")) {
            return new Mp3Extractor(0, 0);
        }
        if (lastPathSegment.endsWith(".mp4") || lastPathSegment.startsWith(".m4", lastPathSegment.length() - 4) || lastPathSegment.startsWith(".mp4", lastPathSegment.length() - 5) || lastPathSegment.startsWith(".cmf", lastPathSegment.length() - 5)) {
            return createFragmentedMp4Extractor(timestampAdjuster, drmInitData, list);
        }
        return createTsExtractor(this.payloadReaderFactoryFlags, this.exposeCea608WhenMissingDeclarations, format, list, timestampAdjuster);
    }

    private static TsExtractor createTsExtractor(int i, boolean z, Format format, List<Format> list, TimestampAdjuster timestampAdjuster) {
        int i2 = i | 16;
        if (list != null) {
            i2 |= 32;
        } else if (z) {
            list = Collections.singletonList(Format.createTextSampleFormat((String) null, "application/cea-608", 0, (String) null));
        } else {
            list = Collections.emptyList();
        }
        String str = format.codecs;
        if (!TextUtils.isEmpty(str)) {
            if (!"audio/mp4a-latm".equals(MimeTypes.getAudioMediaMimeType(str))) {
                i2 |= 2;
            }
            if (!"video/avc".equals(MimeTypes.getVideoMediaMimeType(str))) {
                i2 |= 4;
            }
        }
        return new TsExtractor(2, timestampAdjuster, new DefaultTsPayloadReaderFactory(i2, list));
    }

    private static FragmentedMp4Extractor createFragmentedMp4Extractor(TimestampAdjuster timestampAdjuster, DrmInitData drmInitData, List<Format> list) {
        if (list == null) {
            list = Collections.emptyList();
        }
        return new FragmentedMp4Extractor(0, timestampAdjuster, (Track) null, drmInitData, list);
    }

    private static HlsExtractorFactory.Result buildResultForSameExtractorType(Extractor extractor, Format format, TimestampAdjuster timestampAdjuster) {
        if (extractor instanceof WebvttExtractor) {
            return buildResult(new WebvttExtractor(format.language, timestampAdjuster));
        }
        if (extractor instanceof AdtsExtractor) {
            return buildResult(new AdtsExtractor());
        }
        if (extractor instanceof Ac3Extractor) {
            return buildResult(new Ac3Extractor());
        }
        if (extractor instanceof Ac4Extractor) {
            return buildResult(new Ac4Extractor());
        }
        if (extractor instanceof Mp3Extractor) {
            return buildResult(new Mp3Extractor());
        }
        return null;
    }

    private static HlsExtractorFactory.Result buildResult(Extractor extractor) {
        return new HlsExtractorFactory.Result(extractor, (extractor instanceof AdtsExtractor) || (extractor instanceof Ac3Extractor) || (extractor instanceof Ac4Extractor) || (extractor instanceof Mp3Extractor), isReusable(extractor));
    }

    /* JADX INFO: finally extract failed */
    private static boolean sniffQuietly(Extractor extractor, ExtractorInput extractorInput) throws InterruptedException, IOException {
        try {
            boolean sniff = extractor.sniff(extractorInput);
            extractorInput.resetPeekPosition();
            return sniff;
        } catch (EOFException unused) {
            extractorInput.resetPeekPosition();
            return false;
        } catch (Throwable th) {
            extractorInput.resetPeekPosition();
            throw th;
        }
    }

    private static boolean isReusable(Extractor extractor) {
        return (extractor instanceof TsExtractor) || (extractor instanceof FragmentedMp4Extractor);
    }
}
