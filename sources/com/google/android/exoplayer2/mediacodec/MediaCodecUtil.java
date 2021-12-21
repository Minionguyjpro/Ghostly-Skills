package com.google.android.exoplayer2.mediacodec;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseIntArray;
import com.appnext.base.b.d;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.ColorInfo;
import com.google.android.gms.ads.AdRequest;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public final class MediaCodecUtil {
    private static final SparseIntArray AV1_LEVEL_NUMBER_TO_CONST;
    private static final SparseIntArray AVC_LEVEL_NUMBER_TO_CONST;
    private static final SparseIntArray AVC_PROFILE_NUMBER_TO_CONST;
    private static final Map<String, Integer> DOLBY_VISION_STRING_TO_LEVEL;
    private static final Map<String, Integer> DOLBY_VISION_STRING_TO_PROFILE;
    private static final Map<String, Integer> HEVC_CODEC_STRING_TO_PROFILE_LEVEL;
    private static final SparseIntArray MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE;
    private static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");
    private static final SparseIntArray VP9_LEVEL_NUMBER_TO_CONST;
    private static final SparseIntArray VP9_PROFILE_NUMBER_TO_CONST;
    private static final HashMap<CodecKey, List<MediaCodecInfo>> decoderInfosCache = new HashMap<>();
    private static int maxH264DecodableFrameSize = -1;

    private interface MediaCodecListCompat {
        int getCodecCount();

        MediaCodecInfo getCodecInfoAt(int i);

        boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    private interface ScoreProvider<T> {
        int getScore(T t);
    }

    private static int avcLevelToMaxFrameSize(int i) {
        if (i == 1 || i == 2) {
            return 25344;
        }
        switch (i) {
            case 8:
            case 16:
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
            case 256:
                return 414720;
            case AdRequest.MAX_CONTENT_URL_LENGTH /*512*/:
                return 921600;
            case d.fb:
                return 1310720;
            case 2048:
            case 4096:
                return 2097152;
            case 8192:
                return 2228224;
            case 16384:
                return 5652480;
            case 32768:
            case 65536:
                return 9437184;
            default:
                return -1;
        }
    }

    public static class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        AVC_PROFILE_NUMBER_TO_CONST = sparseIntArray;
        sparseIntArray.put(66, 1);
        AVC_PROFILE_NUMBER_TO_CONST.put(77, 2);
        AVC_PROFILE_NUMBER_TO_CONST.put(88, 4);
        AVC_PROFILE_NUMBER_TO_CONST.put(100, 8);
        AVC_PROFILE_NUMBER_TO_CONST.put(110, 16);
        AVC_PROFILE_NUMBER_TO_CONST.put(122, 32);
        AVC_PROFILE_NUMBER_TO_CONST.put(244, 64);
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        AVC_LEVEL_NUMBER_TO_CONST = sparseIntArray2;
        sparseIntArray2.put(10, 1);
        AVC_LEVEL_NUMBER_TO_CONST.put(11, 4);
        AVC_LEVEL_NUMBER_TO_CONST.put(12, 8);
        AVC_LEVEL_NUMBER_TO_CONST.put(13, 16);
        AVC_LEVEL_NUMBER_TO_CONST.put(20, 32);
        AVC_LEVEL_NUMBER_TO_CONST.put(21, 64);
        AVC_LEVEL_NUMBER_TO_CONST.put(22, 128);
        AVC_LEVEL_NUMBER_TO_CONST.put(30, 256);
        SparseIntArray sparseIntArray3 = AVC_LEVEL_NUMBER_TO_CONST;
        Integer valueOf = Integer.valueOf(AdRequest.MAX_CONTENT_URL_LENGTH);
        sparseIntArray3.put(31, AdRequest.MAX_CONTENT_URL_LENGTH);
        AVC_LEVEL_NUMBER_TO_CONST.put(32, d.fb);
        AVC_LEVEL_NUMBER_TO_CONST.put(40, 2048);
        AVC_LEVEL_NUMBER_TO_CONST.put(41, 4096);
        AVC_LEVEL_NUMBER_TO_CONST.put(42, 8192);
        AVC_LEVEL_NUMBER_TO_CONST.put(50, 16384);
        AVC_LEVEL_NUMBER_TO_CONST.put(51, 32768);
        AVC_LEVEL_NUMBER_TO_CONST.put(52, 65536);
        SparseIntArray sparseIntArray4 = new SparseIntArray();
        VP9_PROFILE_NUMBER_TO_CONST = sparseIntArray4;
        sparseIntArray4.put(0, 1);
        VP9_PROFILE_NUMBER_TO_CONST.put(1, 2);
        VP9_PROFILE_NUMBER_TO_CONST.put(2, 4);
        VP9_PROFILE_NUMBER_TO_CONST.put(3, 8);
        SparseIntArray sparseIntArray5 = new SparseIntArray();
        VP9_LEVEL_NUMBER_TO_CONST = sparseIntArray5;
        sparseIntArray5.put(10, 1);
        VP9_LEVEL_NUMBER_TO_CONST.put(11, 2);
        VP9_LEVEL_NUMBER_TO_CONST.put(20, 4);
        VP9_LEVEL_NUMBER_TO_CONST.put(21, 8);
        VP9_LEVEL_NUMBER_TO_CONST.put(30, 16);
        VP9_LEVEL_NUMBER_TO_CONST.put(31, 32);
        VP9_LEVEL_NUMBER_TO_CONST.put(40, 64);
        VP9_LEVEL_NUMBER_TO_CONST.put(41, 128);
        VP9_LEVEL_NUMBER_TO_CONST.put(50, 256);
        VP9_LEVEL_NUMBER_TO_CONST.put(51, AdRequest.MAX_CONTENT_URL_LENGTH);
        VP9_LEVEL_NUMBER_TO_CONST.put(60, 2048);
        VP9_LEVEL_NUMBER_TO_CONST.put(61, 4096);
        VP9_LEVEL_NUMBER_TO_CONST.put(62, 8192);
        HashMap hashMap = new HashMap();
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL = hashMap;
        hashMap.put("L30", 1);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L60", 4);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L63", 16);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L90", 64);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L93", 256);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L120", Integer.valueOf(d.fb));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L123", 4096);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L150", 16384);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L153", 65536);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L156", 262144);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L180", 1048576);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L183", 4194304);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L186", 16777216);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H30", 2);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H60", 8);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H63", 32);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H90", 128);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H93", valueOf);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H120", 2048);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H123", 8192);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H150", 32768);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H153", 131072);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H156", 524288);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H180", 2097152);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H183", 8388608);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H186", 33554432);
        HashMap hashMap2 = new HashMap();
        DOLBY_VISION_STRING_TO_PROFILE = hashMap2;
        hashMap2.put("00", 1);
        DOLBY_VISION_STRING_TO_PROFILE.put("01", 2);
        DOLBY_VISION_STRING_TO_PROFILE.put("02", 4);
        DOLBY_VISION_STRING_TO_PROFILE.put("03", 8);
        DOLBY_VISION_STRING_TO_PROFILE.put("04", 16);
        DOLBY_VISION_STRING_TO_PROFILE.put("05", 32);
        DOLBY_VISION_STRING_TO_PROFILE.put("06", 64);
        DOLBY_VISION_STRING_TO_PROFILE.put("07", 128);
        DOLBY_VISION_STRING_TO_PROFILE.put("08", 256);
        DOLBY_VISION_STRING_TO_PROFILE.put("09", valueOf);
        HashMap hashMap3 = new HashMap();
        DOLBY_VISION_STRING_TO_LEVEL = hashMap3;
        hashMap3.put("01", 1);
        DOLBY_VISION_STRING_TO_LEVEL.put("02", 2);
        DOLBY_VISION_STRING_TO_LEVEL.put("03", 4);
        DOLBY_VISION_STRING_TO_LEVEL.put("04", 8);
        DOLBY_VISION_STRING_TO_LEVEL.put("05", 16);
        DOLBY_VISION_STRING_TO_LEVEL.put("06", 32);
        DOLBY_VISION_STRING_TO_LEVEL.put("07", 64);
        DOLBY_VISION_STRING_TO_LEVEL.put("08", 128);
        DOLBY_VISION_STRING_TO_LEVEL.put("09", 256);
        SparseIntArray sparseIntArray6 = new SparseIntArray();
        AV1_LEVEL_NUMBER_TO_CONST = sparseIntArray6;
        sparseIntArray6.put(0, 1);
        AV1_LEVEL_NUMBER_TO_CONST.put(1, 2);
        AV1_LEVEL_NUMBER_TO_CONST.put(2, 4);
        AV1_LEVEL_NUMBER_TO_CONST.put(3, 8);
        AV1_LEVEL_NUMBER_TO_CONST.put(4, 16);
        AV1_LEVEL_NUMBER_TO_CONST.put(5, 32);
        AV1_LEVEL_NUMBER_TO_CONST.put(6, 64);
        AV1_LEVEL_NUMBER_TO_CONST.put(7, 128);
        AV1_LEVEL_NUMBER_TO_CONST.put(8, 256);
        AV1_LEVEL_NUMBER_TO_CONST.put(9, AdRequest.MAX_CONTENT_URL_LENGTH);
        AV1_LEVEL_NUMBER_TO_CONST.put(10, d.fb);
        AV1_LEVEL_NUMBER_TO_CONST.put(11, 2048);
        AV1_LEVEL_NUMBER_TO_CONST.put(12, 4096);
        AV1_LEVEL_NUMBER_TO_CONST.put(13, 8192);
        AV1_LEVEL_NUMBER_TO_CONST.put(14, 16384);
        AV1_LEVEL_NUMBER_TO_CONST.put(15, 32768);
        AV1_LEVEL_NUMBER_TO_CONST.put(16, 65536);
        AV1_LEVEL_NUMBER_TO_CONST.put(17, 131072);
        AV1_LEVEL_NUMBER_TO_CONST.put(18, 262144);
        AV1_LEVEL_NUMBER_TO_CONST.put(19, 524288);
        AV1_LEVEL_NUMBER_TO_CONST.put(20, 1048576);
        AV1_LEVEL_NUMBER_TO_CONST.put(21, 2097152);
        AV1_LEVEL_NUMBER_TO_CONST.put(22, 4194304);
        AV1_LEVEL_NUMBER_TO_CONST.put(23, 8388608);
        SparseIntArray sparseIntArray7 = new SparseIntArray();
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE = sparseIntArray7;
        sparseIntArray7.put(1, 1);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(2, 2);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(3, 3);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(4, 4);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(5, 5);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(6, 6);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(17, 17);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(20, 20);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(23, 23);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(29, 29);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(39, 39);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(42, 42);
    }

    public static MediaCodecInfo getPassthroughDecoderInfo() throws DecoderQueryException {
        MediaCodecInfo decoderInfo = getDecoderInfo("audio/raw", false, false);
        if (decoderInfo == null) {
            return null;
        }
        return MediaCodecInfo.newPassthroughInstance(decoderInfo.name);
    }

    public static MediaCodecInfo getDecoderInfo(String str, boolean z, boolean z2) throws DecoderQueryException {
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(str, z, z2);
        if (decoderInfos.isEmpty()) {
            return null;
        }
        return decoderInfos.get(0);
    }

    public static synchronized List<MediaCodecInfo> getDecoderInfos(String str, boolean z, boolean z2) throws DecoderQueryException {
        synchronized (MediaCodecUtil.class) {
            CodecKey codecKey = new CodecKey(str, z, z2);
            List<MediaCodecInfo> list = decoderInfosCache.get(codecKey);
            if (list != null) {
                return list;
            }
            ArrayList<MediaCodecInfo> decoderInfosInternal = getDecoderInfosInternal(codecKey, Util.SDK_INT >= 21 ? new MediaCodecListCompatV21(z, z2) : new MediaCodecListCompatV16());
            if (z && decoderInfosInternal.isEmpty() && 21 <= Util.SDK_INT && Util.SDK_INT <= 23) {
                decoderInfosInternal = getDecoderInfosInternal(codecKey, new MediaCodecListCompatV16());
                if (!decoderInfosInternal.isEmpty()) {
                    Log.w("MediaCodecUtil", "MediaCodecList API didn't list secure decoder for: " + str + ". Assuming: " + decoderInfosInternal.get(0).name);
                }
            }
            applyWorkarounds(str, decoderInfosInternal);
            List<MediaCodecInfo> unmodifiableList = Collections.unmodifiableList(decoderInfosInternal);
            decoderInfosCache.put(codecKey, unmodifiableList);
            return unmodifiableList;
        }
    }

    public static List<MediaCodecInfo> getDecoderInfosSortedByFormatSupport(List<MediaCodecInfo> list, Format format) {
        ArrayList arrayList = new ArrayList(list);
        sortByScore(arrayList, new ScoreProvider() {
            public final int getScore(Object obj) {
                return MediaCodecUtil.lambda$getDecoderInfosSortedByFormatSupport$0(Format.this, (MediaCodecInfo) obj);
            }
        });
        return arrayList;
    }

    static /* synthetic */ int lambda$getDecoderInfosSortedByFormatSupport$0(Format format, MediaCodecInfo mediaCodecInfo) {
        try {
            return mediaCodecInfo.isFormatSupported(format) ? 1 : 0;
        } catch (DecoderQueryException unused) {
            return -1;
        }
    }

    public static int maxH264DecodableFrameSize() throws DecoderQueryException {
        if (maxH264DecodableFrameSize == -1) {
            int i = 0;
            MediaCodecInfo decoderInfo = getDecoderInfo("video/avc", false, false);
            if (decoderInfo != null) {
                MediaCodecInfo.CodecProfileLevel[] profileLevels = decoderInfo.getProfileLevels();
                int length = profileLevels.length;
                int i2 = 0;
                while (i < length) {
                    i2 = Math.max(avcLevelToMaxFrameSize(profileLevels[i].level), i2);
                    i++;
                }
                i = Math.max(i2, Util.SDK_INT >= 21 ? 345600 : 172800);
            }
            maxH264DecodableFrameSize = i;
        }
        return maxH264DecodableFrameSize;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0063, code lost:
        if (r3.equals("avc1") != false) goto L_0x0071;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> getCodecProfileAndLevel(com.google.android.exoplayer2.Format r6) {
        /*
            java.lang.String r0 = r6.codecs
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r0 = r6.codecs
            java.lang.String r2 = "\\."
            java.lang.String[] r0 = r0.split(r2)
            java.lang.String r2 = r6.sampleMimeType
            java.lang.String r3 = "video/dolby-vision"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x001f
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getDolbyVisionProfileAndLevel(r6, r0)
            return r6
        L_0x001f:
            r2 = 0
            r3 = r0[r2]
            r4 = -1
            int r5 = r3.hashCode()
            switch(r5) {
                case 3004662: goto L_0x0066;
                case 3006243: goto L_0x005d;
                case 3006244: goto L_0x0053;
                case 3199032: goto L_0x0049;
                case 3214780: goto L_0x003f;
                case 3356560: goto L_0x0035;
                case 3624515: goto L_0x002b;
                default: goto L_0x002a;
            }
        L_0x002a:
            goto L_0x0070
        L_0x002b:
            java.lang.String r2 = "vp09"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0070
            r2 = 2
            goto L_0x0071
        L_0x0035:
            java.lang.String r2 = "mp4a"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0070
            r2 = 6
            goto L_0x0071
        L_0x003f:
            java.lang.String r2 = "hvc1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0070
            r2 = 4
            goto L_0x0071
        L_0x0049:
            java.lang.String r2 = "hev1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0070
            r2 = 3
            goto L_0x0071
        L_0x0053:
            java.lang.String r2 = "avc2"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0070
            r2 = 1
            goto L_0x0071
        L_0x005d:
            java.lang.String r5 = "avc1"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0070
            goto L_0x0071
        L_0x0066:
            java.lang.String r2 = "av01"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0070
            r2 = 5
            goto L_0x0071
        L_0x0070:
            r2 = -1
        L_0x0071:
            switch(r2) {
                case 0: goto L_0x0093;
                case 1: goto L_0x0093;
                case 2: goto L_0x008c;
                case 3: goto L_0x0085;
                case 4: goto L_0x0085;
                case 5: goto L_0x007c;
                case 6: goto L_0x0075;
                default: goto L_0x0074;
            }
        L_0x0074:
            return r1
        L_0x0075:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getAacCodecProfileAndLevel(r6, r0)
            return r6
        L_0x007c:
            java.lang.String r1 = r6.codecs
            com.google.android.exoplayer2.video.ColorInfo r6 = r6.colorInfo
            android.util.Pair r6 = getAv1ProfileAndLevel(r1, r0, r6)
            return r6
        L_0x0085:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getHevcProfileAndLevel(r6, r0)
            return r6
        L_0x008c:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getVp9ProfileAndLevel(r6, r0)
            return r6
        L_0x0093:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getAvcProfileAndLevel(r6, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.getCodecProfileAndLevel(com.google.android.exoplayer2.Format):android.util.Pair");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0079, code lost:
        if (r1.secure == false) goto L_0x007b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00fa A[SYNTHETIC, Splitter:B:57:0x00fa] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0123 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList<com.google.android.exoplayer2.mediacodec.MediaCodecInfo> getDecoderInfosInternal(com.google.android.exoplayer2.mediacodec.MediaCodecUtil.CodecKey r25, com.google.android.exoplayer2.mediacodec.MediaCodecUtil.MediaCodecListCompat r26) throws com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException {
        /*
            r1 = r25
            r2 = r26
            java.lang.String r3 = "secure-playback"
            java.lang.String r4 = "tunneled-playback"
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x0148 }
            r5.<init>()     // Catch:{ Exception -> 0x0148 }
            java.lang.String r15 = r1.mimeType     // Catch:{ Exception -> 0x0148 }
            int r14 = r26.getCodecCount()     // Catch:{ Exception -> 0x0148 }
            boolean r13 = r26.secureDecodersExplicit()     // Catch:{ Exception -> 0x0148 }
            r0 = 0
            r12 = 0
        L_0x0019:
            if (r12 >= r14) goto L_0x0147
            android.media.MediaCodecInfo r0 = r2.getCodecInfoAt(r12)     // Catch:{ Exception -> 0x0148 }
            java.lang.String r11 = r0.getName()     // Catch:{ Exception -> 0x0148 }
            java.lang.String r10 = getCodecMimeType(r0, r11, r13, r15)     // Catch:{ Exception -> 0x0148 }
            if (r10 != 0) goto L_0x0031
        L_0x0029:
            r22 = r12
            r23 = r13
            r24 = r14
            goto L_0x0119
        L_0x0031:
            android.media.MediaCodecInfo$CodecCapabilities r9 = r0.getCapabilitiesForType(r10)     // Catch:{ Exception -> 0x00e8 }
            boolean r6 = r2.isFeatureSupported(r4, r10, r9)     // Catch:{ Exception -> 0x00e8 }
            boolean r7 = r2.isFeatureRequired(r4, r10, r9)     // Catch:{ Exception -> 0x00e8 }
            boolean r8 = r1.tunneling     // Catch:{ Exception -> 0x00e8 }
            if (r8 != 0) goto L_0x0043
            if (r7 != 0) goto L_0x0029
        L_0x0043:
            boolean r7 = r1.tunneling     // Catch:{ Exception -> 0x00e8 }
            if (r7 == 0) goto L_0x004a
            if (r6 != 0) goto L_0x004a
            goto L_0x0029
        L_0x004a:
            boolean r6 = r2.isFeatureSupported(r3, r10, r9)     // Catch:{ Exception -> 0x00e8 }
            boolean r7 = r2.isFeatureRequired(r3, r10, r9)     // Catch:{ Exception -> 0x00e8 }
            boolean r8 = r1.secure     // Catch:{ Exception -> 0x00e8 }
            if (r8 != 0) goto L_0x0058
            if (r7 != 0) goto L_0x0029
        L_0x0058:
            boolean r7 = r1.secure     // Catch:{ Exception -> 0x00e8 }
            if (r7 == 0) goto L_0x005f
            if (r6 != 0) goto L_0x005f
            goto L_0x0029
        L_0x005f:
            boolean r16 = isHardwareAccelerated(r0)     // Catch:{ Exception -> 0x00e8 }
            boolean r17 = isSoftwareOnly(r0)     // Catch:{ Exception -> 0x00e8 }
            boolean r0 = isVendor(r0)     // Catch:{ Exception -> 0x00e8 }
            boolean r18 = codecNeedsDisableAdaptationWorkaround(r11)     // Catch:{ Exception -> 0x00e8 }
            if (r13 == 0) goto L_0x0075
            boolean r7 = r1.secure     // Catch:{ Exception -> 0x00e8 }
            if (r7 == r6) goto L_0x007b
        L_0x0075:
            if (r13 != 0) goto L_0x00ab
            boolean r7 = r1.secure     // Catch:{ Exception -> 0x00a0 }
            if (r7 != 0) goto L_0x00ab
        L_0x007b:
            r19 = 0
            r6 = r11
            r7 = r15
            r8 = r10
            r20 = r10
            r10 = r16
            r21 = r11
            r11 = r17
            r22 = r12
            r12 = r0
            r23 = r13
            r13 = r18
            r24 = r14
            r14 = r19
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = com.google.android.exoplayer2.mediacodec.MediaCodecInfo.newInstance(r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x009c }
            r5.add(r0)     // Catch:{ Exception -> 0x009c }
            goto L_0x0119
        L_0x009c:
            r0 = move-exception
            r1 = r21
            goto L_0x00f2
        L_0x00a0:
            r0 = move-exception
            r20 = r10
            r22 = r12
            r23 = r13
            r24 = r14
            r1 = r11
            goto L_0x00f2
        L_0x00ab:
            r20 = r10
            r21 = r11
            r22 = r12
            r23 = r13
            r24 = r14
            if (r23 != 0) goto L_0x0119
            if (r6 == 0) goto L_0x0119
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009c }
            r6.<init>()     // Catch:{ Exception -> 0x009c }
            r14 = r21
            r6.append(r14)     // Catch:{ Exception -> 0x00e5 }
            java.lang.String r7 = ".secure"
            r6.append(r7)     // Catch:{ Exception -> 0x00e5 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x00e5 }
            r19 = 1
            r7 = r15
            r8 = r20
            r10 = r16
            r11 = r17
            r12 = r0
            r13 = r18
            r1 = r14
            r14 = r19
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = com.google.android.exoplayer2.mediacodec.MediaCodecInfo.newInstance(r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x00e3 }
            r5.add(r0)     // Catch:{ Exception -> 0x00e3 }
            return r5
        L_0x00e3:
            r0 = move-exception
            goto L_0x00f2
        L_0x00e5:
            r0 = move-exception
            r1 = r14
            goto L_0x00f2
        L_0x00e8:
            r0 = move-exception
            r20 = r10
            r1 = r11
            r22 = r12
            r23 = r13
            r24 = r14
        L_0x00f2:
            int r6 = com.google.android.exoplayer2.util.Util.SDK_INT     // Catch:{ Exception -> 0x0148 }
            r7 = 23
            java.lang.String r8 = "MediaCodecUtil"
            if (r6 > r7) goto L_0x0123
            boolean r6 = r5.isEmpty()     // Catch:{ Exception -> 0x0148 }
            if (r6 != 0) goto L_0x0123
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0148 }
            r0.<init>()     // Catch:{ Exception -> 0x0148 }
            java.lang.String r6 = "Skipping codec "
            r0.append(r6)     // Catch:{ Exception -> 0x0148 }
            r0.append(r1)     // Catch:{ Exception -> 0x0148 }
            java.lang.String r1 = " (failed to query capabilities)"
            r0.append(r1)     // Catch:{ Exception -> 0x0148 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0148 }
            com.google.android.exoplayer2.util.Log.e(r8, r0)     // Catch:{ Exception -> 0x0148 }
        L_0x0119:
            int r12 = r22 + 1
            r1 = r25
            r13 = r23
            r14 = r24
            goto L_0x0019
        L_0x0123:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0148 }
            r2.<init>()     // Catch:{ Exception -> 0x0148 }
            java.lang.String r3 = "Failed to query codec "
            r2.append(r3)     // Catch:{ Exception -> 0x0148 }
            r2.append(r1)     // Catch:{ Exception -> 0x0148 }
            java.lang.String r1 = " ("
            r2.append(r1)     // Catch:{ Exception -> 0x0148 }
            r1 = r20
            r2.append(r1)     // Catch:{ Exception -> 0x0148 }
            java.lang.String r1 = ")"
            r2.append(r1)     // Catch:{ Exception -> 0x0148 }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0148 }
            com.google.android.exoplayer2.util.Log.e(r8, r1)     // Catch:{ Exception -> 0x0148 }
            throw r0     // Catch:{ Exception -> 0x0148 }
        L_0x0147:
            return r5
        L_0x0148:
            r0 = move-exception
            com.google.android.exoplayer2.mediacodec.MediaCodecUtil$DecoderQueryException r1 = new com.google.android.exoplayer2.mediacodec.MediaCodecUtil$DecoderQueryException
            r2 = 0
            r1.<init>(r0)
            goto L_0x0151
        L_0x0150:
            throw r1
        L_0x0151:
            goto L_0x0150
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.getDecoderInfosInternal(com.google.android.exoplayer2.mediacodec.MediaCodecUtil$CodecKey, com.google.android.exoplayer2.mediacodec.MediaCodecUtil$MediaCodecListCompat):java.util.ArrayList");
    }

    private static String getCodecMimeType(MediaCodecInfo mediaCodecInfo, String str, boolean z, String str2) {
        if (!isCodecUsableDecoder(mediaCodecInfo, str, z, str2)) {
            return null;
        }
        for (String str3 : mediaCodecInfo.getSupportedTypes()) {
            if (str3.equalsIgnoreCase(str2)) {
                return str3;
            }
        }
        if (str2.equals("video/dolby-vision")) {
            if ("OMX.MS.HEVCDV.Decoder".equals(str)) {
                return "video/hevcdv";
            }
            if ("OMX.RTK.video.decoder".equals(str) || "OMX.realtek.video.decoder.tunneled".equals(str)) {
                return "video/dv_hevc";
            }
        } else if (str2.equals("audio/alac") && "OMX.lge.alac.decoder".equals(str)) {
            return "audio/x-lg-alac";
        } else {
            if (!str2.equals("audio/flac") || !"OMX.lge.flac.decoder".equals(str)) {
                return null;
            }
            return "audio/x-lg-flac";
        }
        return null;
    }

    private static boolean isCodecUsableDecoder(MediaCodecInfo mediaCodecInfo, String str, boolean z, String str2) {
        if (mediaCodecInfo.isEncoder() || (!z && str.endsWith(".secure"))) {
            return false;
        }
        if (Util.SDK_INT < 21 && ("CIPAACDecoder".equals(str) || "CIPMP3Decoder".equals(str) || "CIPVorbisDecoder".equals(str) || "CIPAMRNBDecoder".equals(str) || "AACDecoder".equals(str) || "MP3Decoder".equals(str))) {
            return false;
        }
        if (Util.SDK_INT < 18 && "OMX.MTK.AUDIO.DECODER.AAC".equals(str) && ("a70".equals(Util.DEVICE) || ("Xiaomi".equals(Util.MANUFACTURER) && Util.DEVICE.startsWith("HM")))) {
            return false;
        }
        if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.mp3".equals(str) && ("dlxu".equals(Util.DEVICE) || "protou".equals(Util.DEVICE) || "ville".equals(Util.DEVICE) || "villeplus".equals(Util.DEVICE) || "villec2".equals(Util.DEVICE) || Util.DEVICE.startsWith("gee") || "C6602".equals(Util.DEVICE) || "C6603".equals(Util.DEVICE) || "C6606".equals(Util.DEVICE) || "C6616".equals(Util.DEVICE) || "L36h".equals(Util.DEVICE) || "SO-02E".equals(Util.DEVICE))) {
            return false;
        }
        if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.aac".equals(str) && ("C1504".equals(Util.DEVICE) || "C1505".equals(Util.DEVICE) || "C1604".equals(Util.DEVICE) || "C1605".equals(Util.DEVICE))) {
            return false;
        }
        if (Util.SDK_INT < 24 && (("OMX.SEC.aac.dec".equals(str) || "OMX.Exynos.AAC.Decoder".equals(str)) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("zeroflte") || Util.DEVICE.startsWith("zerolte") || Util.DEVICE.startsWith("zenlte") || "SC-05G".equals(Util.DEVICE) || "marinelteatt".equals(Util.DEVICE) || "404SC".equals(Util.DEVICE) || "SC-04G".equals(Util.DEVICE) || "SCV31".equals(Util.DEVICE)))) {
            return false;
        }
        if (Util.SDK_INT <= 19 && "OMX.SEC.vp8.dec".equals(str) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("d2") || Util.DEVICE.startsWith("serrano") || Util.DEVICE.startsWith("jflte") || Util.DEVICE.startsWith("santos") || Util.DEVICE.startsWith("t0"))) {
            return false;
        }
        if (Util.SDK_INT <= 19 && Util.DEVICE.startsWith("jflte") && "OMX.qcom.video.decoder.vp8".equals(str)) {
            return false;
        }
        if (!"audio/eac3-joc".equals(str2) || !"OMX.MTK.AUDIO.DECODER.DSPAC3".equals(str)) {
            return true;
        }
        return false;
    }

    private static void applyWorkarounds(String str, List<MediaCodecInfo> list) {
        if ("audio/raw".equals(str)) {
            if (Util.SDK_INT < 26 && Util.DEVICE.equals("R9") && list.size() == 1 && list.get(0).name.equals("OMX.MTK.AUDIO.DECODER.RAW")) {
                list.add(MediaCodecInfo.newInstance("OMX.google.raw.decoder", "audio/raw", "audio/raw", (MediaCodecInfo.CodecCapabilities) null, false, true, false, false, false));
            }
            sortByScore(list, $$Lambda$MediaCodecUtil$cCWO3tN34TxRUMGlkaLU13g9pw.INSTANCE);
        } else if (Util.SDK_INT < 21 && list.size() > 1) {
            String str2 = list.get(0).name;
            if ("OMX.SEC.mp3.dec".equals(str2) || "OMX.SEC.MP3.Decoder".equals(str2) || "OMX.brcm.audio.mp3.decoder".equals(str2)) {
                sortByScore(list, $$Lambda$MediaCodecUtil$5ZWFpP5Ck4Hyp9KyuAYDjY5c2U.INSTANCE);
            }
        }
    }

    static /* synthetic */ int lambda$applyWorkarounds$1(MediaCodecInfo mediaCodecInfo) {
        String str = mediaCodecInfo.name;
        if (str.startsWith("OMX.google") || str.startsWith("c2.android")) {
            return 1;
        }
        return (Util.SDK_INT >= 26 || !str.equals("OMX.MTK.AUDIO.DECODER.RAW")) ? 0 : -1;
    }

    static /* synthetic */ int lambda$applyWorkarounds$2(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.name.startsWith("OMX.google") ? 1 : 0;
    }

    private static boolean isHardwareAccelerated(MediaCodecInfo mediaCodecInfo) {
        if (Util.SDK_INT >= 29) {
            return isHardwareAcceleratedV29(mediaCodecInfo);
        }
        return !isSoftwareOnly(mediaCodecInfo);
    }

    private static boolean isHardwareAcceleratedV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isHardwareAccelerated();
    }

    private static boolean isSoftwareOnly(MediaCodecInfo mediaCodecInfo) {
        if (Util.SDK_INT >= 29) {
            return isSoftwareOnlyV29(mediaCodecInfo);
        }
        String lowerInvariant = Util.toLowerInvariant(mediaCodecInfo.getName());
        if (lowerInvariant.startsWith("arc.")) {
            return false;
        }
        if (lowerInvariant.startsWith("omx.google.") || lowerInvariant.startsWith("omx.ffmpeg.") || ((lowerInvariant.startsWith("omx.sec.") && lowerInvariant.contains(".sw.")) || lowerInvariant.equals("omx.qcom.video.decoder.hevcswvdec") || lowerInvariant.startsWith("c2.android.") || lowerInvariant.startsWith("c2.google.") || (!lowerInvariant.startsWith("omx.") && !lowerInvariant.startsWith("c2.")))) {
            return true;
        }
        return false;
    }

    private static boolean isSoftwareOnlyV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isSoftwareOnly();
    }

    private static boolean isVendor(MediaCodecInfo mediaCodecInfo) {
        if (Util.SDK_INT >= 29) {
            return isVendorV29(mediaCodecInfo);
        }
        String lowerInvariant = Util.toLowerInvariant(mediaCodecInfo.getName());
        return !lowerInvariant.startsWith("omx.google.") && !lowerInvariant.startsWith("c2.android.") && !lowerInvariant.startsWith("c2.google.");
    }

    private static boolean isVendorV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isVendor();
    }

    private static boolean codecNeedsDisableAdaptationWorkaround(String str) {
        return Util.SDK_INT <= 22 && ("ODROID-XU3".equals(Util.MODEL) || "Nexus 10".equals(Util.MODEL)) && ("OMX.Exynos.AVC.Decoder".equals(str) || "OMX.Exynos.AVC.Decoder.secure".equals(str));
    }

    private static Pair<Integer, Integer> getDolbyVisionProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 3) {
            Log.w("MediaCodecUtil", "Ignoring malformed Dolby Vision codec string: " + str);
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            Log.w("MediaCodecUtil", "Ignoring malformed Dolby Vision codec string: " + str);
            return null;
        }
        String group = matcher.group(1);
        Integer num = DOLBY_VISION_STRING_TO_PROFILE.get(group);
        if (num == null) {
            Log.w("MediaCodecUtil", "Unknown Dolby Vision profile string: " + group);
            return null;
        }
        String str2 = strArr[2];
        Integer num2 = DOLBY_VISION_STRING_TO_LEVEL.get(str2);
        if (num2 != null) {
            return new Pair<>(num, num2);
        }
        Log.w("MediaCodecUtil", "Unknown Dolby Vision level string: " + str2);
        return null;
    }

    private static Pair<Integer, Integer> getHevcProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 4) {
            Log.w("MediaCodecUtil", "Ignoring malformed HEVC codec string: " + str);
            return null;
        }
        int i = 1;
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            Log.w("MediaCodecUtil", "Ignoring malformed HEVC codec string: " + str);
            return null;
        }
        String group = matcher.group(1);
        if (!"1".equals(group)) {
            if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(group)) {
                i = 2;
            } else {
                Log.w("MediaCodecUtil", "Unknown HEVC profile string: " + group);
                return null;
            }
        }
        String str2 = strArr[3];
        Integer num = HEVC_CODEC_STRING_TO_PROFILE_LEVEL.get(str2);
        if (num != null) {
            return new Pair<>(Integer.valueOf(i), num);
        }
        Log.w("MediaCodecUtil", "Unknown HEVC level string: " + str2);
        return null;
    }

    private static Pair<Integer, Integer> getAvcProfileAndLevel(String str, String[] strArr) {
        int i;
        int i2;
        if (strArr.length < 2) {
            Log.w("MediaCodecUtil", "Ignoring malformed AVC codec string: " + str);
            return null;
        }
        try {
            if (strArr[1].length() == 6) {
                i2 = Integer.parseInt(strArr[1].substring(0, 2), 16);
                i = Integer.parseInt(strArr[1].substring(4), 16);
            } else if (strArr.length >= 3) {
                int parseInt = Integer.parseInt(strArr[1]);
                i = Integer.parseInt(strArr[2]);
                i2 = parseInt;
            } else {
                Log.w("MediaCodecUtil", "Ignoring malformed AVC codec string: " + str);
                return null;
            }
            int i3 = AVC_PROFILE_NUMBER_TO_CONST.get(i2, -1);
            if (i3 == -1) {
                Log.w("MediaCodecUtil", "Unknown AVC profile: " + i2);
                return null;
            }
            int i4 = AVC_LEVEL_NUMBER_TO_CONST.get(i, -1);
            if (i4 != -1) {
                return new Pair<>(Integer.valueOf(i3), Integer.valueOf(i4));
            }
            Log.w("MediaCodecUtil", "Unknown AVC level: " + i);
            return null;
        } catch (NumberFormatException unused) {
            Log.w("MediaCodecUtil", "Ignoring malformed AVC codec string: " + str);
            return null;
        }
    }

    private static Pair<Integer, Integer> getVp9ProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 3) {
            Log.w("MediaCodecUtil", "Ignoring malformed VP9 codec string: " + str);
            return null;
        }
        try {
            int parseInt = Integer.parseInt(strArr[1]);
            int parseInt2 = Integer.parseInt(strArr[2]);
            int i = VP9_PROFILE_NUMBER_TO_CONST.get(parseInt, -1);
            if (i == -1) {
                Log.w("MediaCodecUtil", "Unknown VP9 profile: " + parseInt);
                return null;
            }
            int i2 = VP9_LEVEL_NUMBER_TO_CONST.get(parseInt2, -1);
            if (i2 != -1) {
                return new Pair<>(Integer.valueOf(i), Integer.valueOf(i2));
            }
            Log.w("MediaCodecUtil", "Unknown VP9 level: " + parseInt2);
            return null;
        } catch (NumberFormatException unused) {
            Log.w("MediaCodecUtil", "Ignoring malformed VP9 codec string: " + str);
            return null;
        }
    }

    private static Pair<Integer, Integer> getAv1ProfileAndLevel(String str, String[] strArr, ColorInfo colorInfo) {
        if (strArr.length < 4) {
            Log.w("MediaCodecUtil", "Ignoring malformed AV1 codec string: " + str);
            return null;
        }
        int i = 1;
        try {
            int parseInt = Integer.parseInt(strArr[1]);
            int parseInt2 = Integer.parseInt(strArr[2].substring(0, 2));
            int parseInt3 = Integer.parseInt(strArr[3]);
            if (parseInt != 0) {
                Log.w("MediaCodecUtil", "Unknown AV1 profile: " + parseInt);
                return null;
            } else if (parseInt3 == 8 || parseInt3 == 10) {
                if (parseInt3 != 8) {
                    i = (colorInfo == null || !(colorInfo.hdrStaticInfo != null || colorInfo.colorTransfer == 7 || colorInfo.colorTransfer == 6)) ? 2 : 4096;
                }
                int i2 = AV1_LEVEL_NUMBER_TO_CONST.get(parseInt2, -1);
                if (i2 != -1) {
                    return new Pair<>(Integer.valueOf(i), Integer.valueOf(i2));
                }
                Log.w("MediaCodecUtil", "Unknown AV1 level: " + parseInt2);
                return null;
            } else {
                Log.w("MediaCodecUtil", "Unknown AV1 bit depth: " + parseInt3);
                return null;
            }
        } catch (NumberFormatException unused) {
            Log.w("MediaCodecUtil", "Ignoring malformed AV1 codec string: " + str);
            return null;
        }
    }

    private static Pair<Integer, Integer> getAacCodecProfileAndLevel(String str, String[] strArr) {
        int i;
        if (strArr.length != 3) {
            Log.w("MediaCodecUtil", "Ignoring malformed MP4A codec string: " + str);
            return null;
        }
        try {
            if ("audio/mp4a-latm".equals(MimeTypes.getMimeTypeFromMp4ObjectType(Integer.parseInt(strArr[1], 16))) && (i = MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.get(Integer.parseInt(strArr[2]), -1)) != -1) {
                return new Pair<>(Integer.valueOf(i), 0);
            }
        } catch (NumberFormatException unused) {
            Log.w("MediaCodecUtil", "Ignoring malformed MP4A codec string: " + str);
        }
        return null;
    }

    static /* synthetic */ int lambda$sortByScore$3(ScoreProvider scoreProvider, Object obj, Object obj2) {
        return scoreProvider.getScore(obj2) - scoreProvider.getScore(obj);
    }

    private static <T> void sortByScore(List<T> list, ScoreProvider<T> scoreProvider) {
        Collections.sort(list, new Comparator() {
            public final int compare(Object obj, Object obj2) {
                return MediaCodecUtil.lambda$sortByScore$3(MediaCodecUtil.ScoreProvider.this, obj, obj2);
            }
        });
    }

    private static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        private MediaCodecInfo[] mediaCodecInfos;

        public boolean secureDecodersExplicit() {
            return true;
        }

        public MediaCodecListCompatV21(boolean z, boolean z2) {
            this.codecKind = (z || z2) ? 1 : 0;
        }

        public int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        public MediaCodecInfo getCodecInfoAt(int i) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[i];
        }

        public boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported(str);
        }

        public boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureRequired(str);
        }

        @EnsuresNonNull({"mediaCodecInfos"})
        private void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }
    }

    private static final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        public boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return false;
        }

        public boolean secureDecodersExplicit() {
            return false;
        }

        private MediaCodecListCompatV16() {
        }

        public int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        public MediaCodecInfo getCodecInfoAt(int i) {
            return MediaCodecList.getCodecInfoAt(i);
        }

        public boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return "secure-playback".equals(str) && "video/avc".equals(str2);
        }
    }

    private static final class CodecKey {
        public final String mimeType;
        public final boolean secure;
        public final boolean tunneling;

        public CodecKey(String str, boolean z, boolean z2) {
            this.mimeType = str;
            this.secure = z;
            this.tunneling = z2;
        }

        public int hashCode() {
            int i = 1231;
            int hashCode = (((this.mimeType.hashCode() + 31) * 31) + (this.secure ? 1231 : 1237)) * 31;
            if (!this.tunneling) {
                i = 1237;
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != CodecKey.class) {
                return false;
            }
            CodecKey codecKey = (CodecKey) obj;
            if (TextUtils.equals(this.mimeType, codecKey.mimeType) && this.secure == codecKey.secure && this.tunneling == codecKey.tunneling) {
                return true;
            }
            return false;
        }
    }
}
