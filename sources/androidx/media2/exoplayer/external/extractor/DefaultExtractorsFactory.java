package androidx.media2.exoplayer.external.extractor;

import androidx.media2.exoplayer.external.extractor.amr.AmrExtractor;
import androidx.media2.exoplayer.external.extractor.flv.FlvExtractor;
import androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor;
import androidx.media2.exoplayer.external.extractor.mp3.Mp3Extractor;
import androidx.media2.exoplayer.external.extractor.mp4.FragmentedMp4Extractor;
import androidx.media2.exoplayer.external.extractor.mp4.Mp4Extractor;
import androidx.media2.exoplayer.external.extractor.ogg.OggExtractor;
import androidx.media2.exoplayer.external.extractor.ts.Ac3Extractor;
import androidx.media2.exoplayer.external.extractor.ts.Ac4Extractor;
import androidx.media2.exoplayer.external.extractor.ts.AdtsExtractor;
import androidx.media2.exoplayer.external.extractor.ts.PsExtractor;
import androidx.media2.exoplayer.external.extractor.ts.TsExtractor;
import androidx.media2.exoplayer.external.extractor.wav.WavExtractor;
import java.lang.reflect.Constructor;

public final class DefaultExtractorsFactory implements ExtractorsFactory {
    private static final Constructor<? extends Extractor> FLAC_EXTRACTOR_CONSTRUCTOR;
    private int adtsFlags;
    private int amrFlags;
    private boolean constantBitrateSeekingEnabled;
    private int fragmentedMp4Flags;
    private int matroskaFlags;
    private int mp3Flags;
    private int mp4Flags;
    private int tsFlags;
    private int tsMode = 1;

    static {
        Constructor<? extends U> constructor;
        try {
            constructor = Class.forName("androidx.media2.exoplayer.external.ext.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor(new Class[0]);
        } catch (ClassNotFoundException unused) {
            constructor = null;
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating FLAC extension", e);
        }
        FLAC_EXTRACTOR_CONSTRUCTOR = constructor;
    }

    public synchronized DefaultExtractorsFactory setAdtsExtractorFlags(int i) {
        this.adtsFlags = i;
        return this;
    }

    public synchronized Extractor[] createExtractors() {
        Extractor[] extractorArr;
        extractorArr = new Extractor[(FLAC_EXTRACTOR_CONSTRUCTOR == null ? 13 : 14)];
        extractorArr[0] = new MatroskaExtractor(this.matroskaFlags);
        int i = 1;
        extractorArr[1] = new FragmentedMp4Extractor(this.fragmentedMp4Flags);
        extractorArr[2] = new Mp4Extractor(this.mp4Flags);
        extractorArr[3] = new Mp3Extractor(this.mp3Flags | (this.constantBitrateSeekingEnabled ? 1 : 0));
        extractorArr[4] = new AdtsExtractor(0, this.adtsFlags | (this.constantBitrateSeekingEnabled ? 1 : 0));
        extractorArr[5] = new Ac3Extractor();
        extractorArr[6] = new TsExtractor(this.tsMode, this.tsFlags);
        extractorArr[7] = new FlvExtractor();
        extractorArr[8] = new OggExtractor();
        extractorArr[9] = new PsExtractor();
        extractorArr[10] = new WavExtractor();
        int i2 = this.amrFlags;
        if (!this.constantBitrateSeekingEnabled) {
            i = 0;
        }
        extractorArr[11] = new AmrExtractor(i | i2);
        extractorArr[12] = new Ac4Extractor();
        if (FLAC_EXTRACTOR_CONSTRUCTOR != null) {
            try {
                extractorArr[13] = (Extractor) FLAC_EXTRACTOR_CONSTRUCTOR.newInstance(new Object[0]);
            } catch (Exception e) {
                throw new IllegalStateException("Unexpected error creating FLAC extractor", e);
            }
        }
        return extractorArr;
    }
}
