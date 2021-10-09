package com.google.android.exoplayer2;

public interface RendererCapabilities {
    int getTrackType();

    int supportsFormat(Format format) throws ExoPlaybackException;

    int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException;

    /* renamed from: com.google.android.exoplayer2.RendererCapabilities$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static int create(int i, int i2, int i3) {
            return i | i2 | i3;
        }

        public static int getAdaptiveSupport(int i) {
            return i & 24;
        }

        public static int getFormatSupport(int i) {
            return i & 7;
        }

        public static int getTunnelingSupport(int i) {
            return i & 32;
        }

        public static int create(int i) {
            return create(i, 0, 0);
        }

        public static String getFormatSupportString(int i) {
            if (i == 0) {
                return "NO";
            }
            if (i == 1) {
                return "NO_UNSUPPORTED_TYPE";
            }
            if (i == 2) {
                return "NO_UNSUPPORTED_DRM";
            }
            if (i == 3) {
                return "NO_EXCEEDS_CAPABILITIES";
            }
            if (i == 4) {
                return "YES";
            }
            throw new IllegalStateException();
        }
    }
}
