package androidx.media2.player.exoplayer;

import android.content.Context;
import android.media.MediaFormat;
import android.net.Uri;
import androidx.core.util.Preconditions;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.CallbackMediaItem;
import androidx.media2.common.FileMediaItem;
import androidx.media2.common.MediaItem;
import androidx.media2.common.UriMediaItem;
import androidx.media2.exoplayer.external.ExoPlaybackException;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.PlaybackParameters;
import androidx.media2.exoplayer.external.SeekParameters;
import androidx.media2.exoplayer.external.audio.AudioAttributes;
import androidx.media2.exoplayer.external.extractor.DefaultExtractorsFactory;
import androidx.media2.exoplayer.external.extractor.ExtractorsFactory;
import androidx.media2.exoplayer.external.mediacodec.MediaFormatUtil;
import androidx.media2.exoplayer.external.source.ExtractorMediaSource;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.source.hls.HlsMediaSource;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.HttpDataSource;
import androidx.media2.exoplayer.external.upstream.RawResourceDataSource;
import androidx.media2.exoplayer.external.util.MimeTypes;
import androidx.media2.exoplayer.external.util.Util;
import androidx.media2.player.PlaybackParams;
import java.io.IOException;
import java.net.SocketTimeoutException;

class ExoPlayerUtils {
    private static final ExtractorsFactory sExtractorsFactory = new DefaultExtractorsFactory().setAdtsExtractorFlags(1);

    public static MediaSource createUnclippedMediaSource(Context context, DataSource.Factory factory, MediaItem mediaItem) {
        int i;
        if (mediaItem instanceof UriMediaItem) {
            Uri uri = ((UriMediaItem) mediaItem).getUri();
            if (Util.inferContentType(uri) == 2) {
                return new HlsMediaSource.Factory(factory).setTag(mediaItem).createMediaSource(uri);
            }
            if ("android.resource".equals(uri.getScheme())) {
                String str = (String) Preconditions.checkNotNull(uri.getPath());
                boolean z = true;
                if (uri.getPathSegments().size() != 1 || !uri.getPathSegments().get(0).matches("\\d+")) {
                    String str2 = "";
                    String replaceAll = str.replaceAll("^/", str2);
                    String host = uri.getHost();
                    StringBuilder sb = new StringBuilder();
                    if (host != null) {
                        str2 = host + ":";
                    }
                    sb.append(str2);
                    sb.append(replaceAll);
                    i = context.getResources().getIdentifier(sb.toString(), "raw", context.getPackageName());
                } else {
                    i = Integer.parseInt(uri.getPathSegments().get(0));
                }
                if (i == 0) {
                    z = false;
                }
                Preconditions.checkState(z);
                uri = RawResourceDataSource.buildRawResourceUri(i);
            }
            return new ExtractorMediaSource.Factory(factory).setExtractorsFactory(sExtractorsFactory).setTag(mediaItem).createMediaSource(uri);
        } else if (mediaItem instanceof FileMediaItem) {
            return new ExtractorMediaSource.Factory(factory).setExtractorsFactory(sExtractorsFactory).setTag(mediaItem).createMediaSource(Uri.EMPTY);
        } else {
            if (mediaItem instanceof CallbackMediaItem) {
                return new ExtractorMediaSource.Factory(DataSourceCallbackDataSource.getFactory(((CallbackMediaItem) mediaItem).getDataSourceCallback())).setExtractorsFactory(sExtractorsFactory).setTag(mediaItem).createMediaSource(Uri.EMPTY);
            }
            throw new IllegalStateException();
        }
    }

    public static AudioAttributes getAudioAttributes(AudioAttributesCompat audioAttributesCompat) {
        return new AudioAttributes.Builder().setContentType(audioAttributesCompat.getContentType()).setFlags(audioAttributesCompat.getFlags()).setUsage(audioAttributesCompat.getUsage()).build();
    }

    public static AudioAttributesCompat getAudioAttributesCompat(AudioAttributes audioAttributes) {
        return new AudioAttributesCompat.Builder().setContentType(audioAttributes.contentType).setFlags(audioAttributes.flags).setUsage(audioAttributes.usage).build();
    }

    public static PlaybackParameters getPlaybackParameters(PlaybackParams playbackParams) {
        Float speed = playbackParams.getSpeed();
        Float pitch = playbackParams.getPitch();
        float f = 1.0f;
        float floatValue = speed != null ? speed.floatValue() : 1.0f;
        if (pitch != null) {
            f = pitch.floatValue();
        }
        return new PlaybackParameters(floatValue, f);
    }

    public static SeekParameters getSeekParameters(int i) {
        if (i == 0) {
            return SeekParameters.PREVIOUS_SYNC;
        }
        if (i == 1) {
            return SeekParameters.NEXT_SYNC;
        }
        if (i == 2) {
            return SeekParameters.CLOSEST_SYNC;
        }
        if (i == 3) {
            return SeekParameters.EXACT;
        }
        throw new IllegalArgumentException();
    }

    public static int getError(ExoPlaybackException exoPlaybackException) {
        if (exoPlaybackException.type != 0) {
            return 1;
        }
        IOException sourceException = exoPlaybackException.getSourceException();
        if (sourceException instanceof ParserException) {
            return -1007;
        }
        return (!(sourceException instanceof HttpDataSource.HttpDataSourceException) || !(sourceException.getCause() instanceof SocketTimeoutException)) ? -1004 : -110;
    }

    public static MediaFormat getMediaFormat(Format format) {
        MediaFormat mediaFormat = new MediaFormat();
        String str = format.sampleMimeType;
        mediaFormat.setString("mime", str);
        int trackType = MimeTypes.getTrackType(str);
        int i = 1;
        if (trackType == 1) {
            mediaFormat.setInteger("channel-count", format.channelCount);
            mediaFormat.setInteger("sample-rate", format.sampleRate);
            if (format.language != null) {
                mediaFormat.setString("language", format.language);
            }
        } else if (trackType == 2) {
            MediaFormatUtil.maybeSetInteger(mediaFormat, "width", format.width);
            MediaFormatUtil.maybeSetInteger(mediaFormat, "height", format.height);
            MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
            MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
            MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        } else if (trackType == 3) {
            int i2 = format.selectionFlags == 4 ? 1 : 0;
            int i3 = format.selectionFlags == 1 ? 1 : 0;
            if (format.selectionFlags != 2) {
                i = 0;
            }
            mediaFormat.setInteger("is-autoselect", i2);
            mediaFormat.setInteger("is-default", i3);
            mediaFormat.setInteger("is-forced-subtitle", i);
            if (format.language == null) {
                mediaFormat.setString("language", "und");
            } else {
                mediaFormat.setString("language", format.language);
            }
            if ("application/cea-608".equals(str)) {
                mediaFormat.setString("mime", "text/cea-608");
            } else if ("application/cea-708".equals(str)) {
                mediaFormat.setString("mime", "text/cea-708");
            }
        }
        return mediaFormat;
    }
}
