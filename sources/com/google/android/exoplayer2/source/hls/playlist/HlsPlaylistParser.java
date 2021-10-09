package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.appnext.base.b.d;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.ads.AdRequest;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

public final class HlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private static final Pattern REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern REGEX_AUDIO = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern REGEX_AUTOSELECT = compileBooleanAttrPattern("AUTOSELECT");
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern REGEX_CHANNELS = Pattern.compile("CHANNELS=\"(.+?)\"");
    private static final Pattern REGEX_CHARACTERISTICS = Pattern.compile("CHARACTERISTICS=\"(.+?)\"");
    private static final Pattern REGEX_CLOSED_CAPTIONS = Pattern.compile("CLOSED-CAPTIONS=\"(.+?)\"");
    private static final Pattern REGEX_CODECS = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern REGEX_DEFAULT = compileBooleanAttrPattern("DEFAULT");
    private static final Pattern REGEX_FORCED = compileBooleanAttrPattern("FORCED");
    private static final Pattern REGEX_FRAME_RATE = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern REGEX_GROUP_ID = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern REGEX_IMPORT = Pattern.compile("IMPORT=\"(.+?)\"");
    private static final Pattern REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern REGEX_IV = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern REGEX_KEYFORMATVERSIONS = Pattern.compile("KEYFORMATVERSIONS=\"(.+?)\"");
    private static final Pattern REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern REGEX_MEDIA_TITLE = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
    private static final Pattern REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
    private static final Pattern REGEX_NAME = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern REGEX_SUBTITLES = Pattern.compile("SUBTITLES=\"(.+?)\"");
    private static final Pattern REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern REGEX_URI = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern REGEX_VALUE = Pattern.compile("VALUE=\"(.+?)\"");
    private static final Pattern REGEX_VARIABLE_REFERENCE = Pattern.compile("\\{\\$([a-zA-Z0-9\\-_]+)\\}");
    private static final Pattern REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern REGEX_VIDEO = Pattern.compile("VIDEO=\"(.+?)\"");
    private final HlsMasterPlaylist masterPlaylist;

    public HlsPlaylistParser() {
        this(HlsMasterPlaylist.EMPTY);
    }

    public HlsPlaylistParser(HlsMasterPlaylist hlsMasterPlaylist) {
        this.masterPlaylist = hlsMasterPlaylist;
    }

    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        String trim;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayDeque arrayDeque = new ArrayDeque();
        try {
            if (checkPlaylistHeader(bufferedReader)) {
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        trim = readLine.trim();
                        if (!trim.isEmpty()) {
                            if (!trim.startsWith("#EXT-X-STREAM-INF")) {
                                if (trim.startsWith("#EXT-X-TARGETDURATION") || trim.startsWith("#EXT-X-MEDIA-SEQUENCE") || trim.startsWith("#EXTINF") || trim.startsWith("#EXT-X-KEY") || trim.startsWith("#EXT-X-BYTERANGE") || trim.equals("#EXT-X-DISCONTINUITY") || trim.equals("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                                    break;
                                } else if (trim.equals("#EXT-X-ENDLIST")) {
                                    break;
                                } else {
                                    arrayDeque.add(trim);
                                }
                            } else {
                                arrayDeque.add(trim);
                                HlsMasterPlaylist parseMasterPlaylist = parseMasterPlaylist(new LineIterator(arrayDeque, bufferedReader), uri.toString());
                                Util.closeQuietly((Closeable) bufferedReader);
                                return parseMasterPlaylist;
                            }
                        }
                    } else {
                        Util.closeQuietly((Closeable) bufferedReader);
                        throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                    }
                }
                arrayDeque.add(trim);
                return parseMediaPlaylist(this.masterPlaylist, new LineIterator(arrayDeque, bufferedReader), uri.toString());
            }
            throw new UnrecognizedInputFormatException("Input does not start with the #EXTM3U header.", uri);
        } finally {
            Util.closeQuietly((Closeable) bufferedReader);
        }
    }

    private static boolean checkPlaylistHeader(BufferedReader bufferedReader) throws IOException {
        int read = bufferedReader.read();
        if (read == 239) {
            if (bufferedReader.read() != 187 || bufferedReader.read() != 191) {
                return false;
            }
            read = bufferedReader.read();
        }
        int skipIgnorableWhitespace = skipIgnorableWhitespace(bufferedReader, true, read);
        for (int i = 0; i < 7; i++) {
            if (skipIgnorableWhitespace != "#EXTM3U".charAt(i)) {
                return false;
            }
            skipIgnorableWhitespace = bufferedReader.read();
        }
        return Util.isLinebreak(skipIgnorableWhitespace(bufferedReader, false, skipIgnorableWhitespace));
    }

    private static int skipIgnorableWhitespace(BufferedReader bufferedReader, boolean z, int i) throws IOException {
        while (i != -1 && Character.isWhitespace(i) && (z || !Util.isLinebreak(i))) {
            i = bufferedReader.read();
        }
        return i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist parseMasterPlaylist(com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.LineIterator r40, java.lang.String r41) throws java.io.IOException {
        /*
            r1 = r41
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r13 = 0
            r14 = 0
        L_0x0036:
            boolean r15 = r40.hasNext()
            r16 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r15 == 0) goto L_0x01be
            java.lang.String r15 = r40.next()
            java.lang.String r10 = "#EXT"
            boolean r10 = r15.startsWith(r10)
            if (r10 == 0) goto L_0x004d
            r8.add(r15)
        L_0x004d:
            java.lang.String r10 = "#EXT-X-DEFINE"
            boolean r10 = r15.startsWith(r10)
            if (r10 == 0) goto L_0x006e
            java.util.regex.Pattern r10 = REGEX_NAME
            java.lang.String r10 = parseStringAttr(r15, r10, r11)
            java.util.regex.Pattern r9 = REGEX_VALUE
            java.lang.String r9 = parseStringAttr(r15, r9, r11)
            r11.put(r10, r9)
        L_0x0064:
            r20 = r7
            r22 = r8
            r21 = r12
            r19 = r13
            goto L_0x01b4
        L_0x006e:
            java.lang.String r9 = "#EXT-X-INDEPENDENT-SEGMENTS"
            boolean r9 = r15.equals(r9)
            if (r9 == 0) goto L_0x0078
            r13 = 1
            goto L_0x0036
        L_0x0078:
            java.lang.String r9 = "#EXT-X-MEDIA"
            boolean r9 = r15.startsWith(r9)
            if (r9 == 0) goto L_0x0084
            r3.add(r15)
            goto L_0x0064
        L_0x0084:
            java.lang.String r9 = "#EXT-X-SESSION-KEY"
            boolean r9 = r15.startsWith(r9)
            if (r9 == 0) goto L_0x00be
            java.util.regex.Pattern r9 = REGEX_KEYFORMAT
            java.lang.String r10 = "identity"
            java.lang.String r9 = parseOptionalStringAttr(r15, r9, r10, r11)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r9 = parseDrmSchemeData(r15, r9, r11)
            if (r9 == 0) goto L_0x00b8
            java.util.regex.Pattern r10 = REGEX_METHOD
            java.lang.String r10 = parseStringAttr(r15, r10, r11)
            java.lang.String r10 = parseEncryptionScheme(r10)
            com.google.android.exoplayer2.drm.DrmInitData r15 = new com.google.android.exoplayer2.drm.DrmInitData
            r20 = r7
            r19 = r13
            r13 = 1
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r7 = new com.google.android.exoplayer2.drm.DrmInitData.SchemeData[r13]
            r13 = 0
            r7[r13] = r9
            r15.<init>((java.lang.String) r10, (com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r7)
            r12.add(r15)
            goto L_0x01b0
        L_0x00b8:
            r20 = r7
            r19 = r13
            goto L_0x01b0
        L_0x00be:
            r20 = r7
            r19 = r13
            java.lang.String r7 = "#EXT-X-STREAM-INF"
            boolean r7 = r15.startsWith(r7)
            if (r7 == 0) goto L_0x01b0
            java.lang.String r7 = "CLOSED-CAPTIONS=NONE"
            boolean r7 = r15.contains(r7)
            r14 = r14 | r7
            java.util.regex.Pattern r7 = REGEX_BANDWIDTH
            int r7 = parseIntAttr(r15, r7)
            java.util.regex.Pattern r9 = REGEX_AVERAGE_BANDWIDTH
            r13 = -1
            parseOptionalIntAttr(r15, r9, r13)
            java.util.regex.Pattern r9 = REGEX_CODECS
            java.lang.String r25 = parseOptionalStringAttr(r15, r9, r11)
            java.util.regex.Pattern r9 = REGEX_RESOLUTION
            java.lang.String r9 = parseOptionalStringAttr(r15, r9, r11)
            if (r9 == 0) goto L_0x010b
            java.lang.String r10 = "x"
            java.lang.String[] r9 = r9.split(r10)
            r10 = 0
            r18 = r9[r10]
            int r10 = java.lang.Integer.parseInt(r18)
            r18 = 1
            r9 = r9[r18]
            int r9 = java.lang.Integer.parseInt(r9)
            if (r10 <= 0) goto L_0x0104
            if (r9 > 0) goto L_0x0106
        L_0x0104:
            r9 = -1
            r10 = -1
        L_0x0106:
            r29 = r9
            r28 = r10
            goto L_0x010f
        L_0x010b:
            r28 = -1
            r29 = -1
        L_0x010f:
            java.util.regex.Pattern r9 = REGEX_FRAME_RATE
            java.lang.String r9 = parseOptionalStringAttr(r15, r9, r11)
            if (r9 == 0) goto L_0x011e
            float r16 = java.lang.Float.parseFloat(r9)
            r30 = r16
            goto L_0x0120
        L_0x011e:
            r30 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x0120:
            java.util.regex.Pattern r9 = REGEX_VIDEO
            java.lang.String r9 = parseOptionalStringAttr(r15, r9, r11)
            java.util.regex.Pattern r10 = REGEX_AUDIO
            java.lang.String r10 = parseOptionalStringAttr(r15, r10, r11)
            java.util.regex.Pattern r13 = REGEX_SUBTITLES
            java.lang.String r13 = parseOptionalStringAttr(r15, r13, r11)
            r18 = r14
            java.util.regex.Pattern r14 = REGEX_CLOSED_CAPTIONS
            java.lang.String r14 = parseOptionalStringAttr(r15, r14, r11)
            boolean r15 = r40.hasNext()
            if (r15 == 0) goto L_0x01a8
            java.lang.String r15 = r40.next()
            java.lang.String r15 = replaceVariableReferences(r15, r11)
            android.net.Uri r15 = com.google.android.exoplayer2.util.UriUtil.resolveToUri(r1, r15)
            int r16 = r2.size()
            java.lang.String r21 = java.lang.Integer.toString(r16)
            r22 = 0
            r24 = 0
            r26 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            java.lang.String r23 = "application/x-mpegURL"
            r27 = r7
            com.google.android.exoplayer2.Format r33 = com.google.android.exoplayer2.Format.createVideoContainerFormat(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33)
            r21 = r12
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r12 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant
            r31 = r12
            r32 = r15
            r34 = r9
            r35 = r10
            r36 = r13
            r37 = r14
            r31.<init>(r32, r33, r34, r35, r36, r37)
            r2.add(r12)
            java.lang.Object r12 = r0.get(r15)
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            if (r12 != 0) goto L_0x018e
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            r0.put(r15, r12)
        L_0x018e:
            com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry$VariantInfo r15 = new com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry$VariantInfo
            r22 = r8
            long r7 = (long) r7
            r31 = r15
            r32 = r7
            r34 = r9
            r35 = r10
            r36 = r13
            r37 = r14
            r31.<init>(r32, r34, r35, r36, r37)
            r12.add(r15)
            r14 = r18
            goto L_0x01b4
        L_0x01a8:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "#EXT-X-STREAM-INF tag must be followed by another line"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x01b0:
            r22 = r8
            r21 = r12
        L_0x01b4:
            r13 = r19
            r7 = r20
            r12 = r21
            r8 = r22
            goto L_0x0036
        L_0x01be:
            r20 = r7
            r22 = r8
            r21 = r12
            r19 = r13
            r13 = -1
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.HashSet r8 = new java.util.HashSet
            r8.<init>()
            r9 = 0
        L_0x01d2:
            int r10 = r2.size()
            r12 = 0
            if (r9 >= r10) goto L_0x022d
            java.lang.Object r10 = r2.get(r9)
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r10 = (com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.Variant) r10
            android.net.Uri r15 = r10.url
            boolean r15 = r8.add(r15)
            if (r15 == 0) goto L_0x0221
            com.google.android.exoplayer2.Format r15 = r10.format
            com.google.android.exoplayer2.metadata.Metadata r15 = r15.metadata
            if (r15 != 0) goto L_0x01ef
            r15 = 1
            goto L_0x01f0
        L_0x01ef:
            r15 = 0
        L_0x01f0:
            com.google.android.exoplayer2.util.Assertions.checkState(r15)
            com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry r15 = new com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry
            android.net.Uri r13 = r10.url
            java.lang.Object r13 = r0.get(r13)
            java.lang.Object r13 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r13)
            java.util.List r13 = (java.util.List) r13
            r15.<init>(r12, r12, r13)
            com.google.android.exoplayer2.Format r12 = r10.format
            com.google.android.exoplayer2.metadata.Metadata r13 = new com.google.android.exoplayer2.metadata.Metadata
            r23 = r0
            r40 = r8
            r0 = 1
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r8 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r0]
            r0 = 0
            r8[r0] = r15
            r13.<init>((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r8)
            com.google.android.exoplayer2.Format r0 = r12.copyWithMetadata(r13)
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r0 = r10.copyWithFormat(r0)
            r7.add(r0)
            goto L_0x0225
        L_0x0221:
            r23 = r0
            r40 = r8
        L_0x0225:
            int r9 = r9 + 1
            r8 = r40
            r0 = r23
            r13 = -1
            goto L_0x01d2
        L_0x022d:
            r0 = r12
            r8 = r0
            r13 = 0
        L_0x0230:
            int r9 = r3.size()
            if (r13 >= r9) goto L_0x041a
            java.lang.Object r9 = r3.get(r13)
            java.lang.String r9 = (java.lang.String) r9
            java.util.regex.Pattern r10 = REGEX_GROUP_ID
            java.lang.String r10 = parseStringAttr(r9, r10, r11)
            java.util.regex.Pattern r15 = REGEX_NAME
            java.lang.String r15 = parseStringAttr(r9, r15, r11)
            java.util.regex.Pattern r12 = REGEX_URI
            java.lang.String r12 = parseOptionalStringAttr(r9, r12, r11)
            if (r12 != 0) goto L_0x0252
            r12 = 0
            goto L_0x0256
        L_0x0252:
            android.net.Uri r12 = com.google.android.exoplayer2.util.UriUtil.resolveToUri(r1, r12)
        L_0x0256:
            java.util.regex.Pattern r1 = REGEX_LANGUAGE
            java.lang.String r35 = parseOptionalStringAttr(r9, r1, r11)
            int r34 = parseSelectionFlags(r9)
            int r1 = parseRoleFlags(r9, r11)
            r36 = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r10)
            r37 = r8
            java.lang.String r8 = ":"
            r3.append(r8)
            r3.append(r15)
            java.lang.String r23 = r3.toString()
            com.google.android.exoplayer2.metadata.Metadata r3 = new com.google.android.exoplayer2.metadata.Metadata
            r38 = r7
            r8 = 1
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r7 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r8]
            com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry r8 = new com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry
            r39 = r14
            java.util.List r14 = java.util.Collections.emptyList()
            r8.<init>(r10, r15, r14)
            r14 = 0
            r7[r14] = r8
            r3.<init>((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r7)
            java.util.regex.Pattern r7 = REGEX_TYPE
            java.lang.String r7 = parseStringAttr(r9, r7, r11)
            int r8 = r7.hashCode()
            r14 = 2
            switch(r8) {
                case -959297733: goto L_0x02c1;
                case -333210994: goto L_0x02b7;
                case 62628790: goto L_0x02ad;
                case 81665115: goto L_0x02a3;
                default: goto L_0x02a2;
            }
        L_0x02a2:
            goto L_0x02cb
        L_0x02a3:
            java.lang.String r8 = "VIDEO"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x02cb
            r7 = 0
            goto L_0x02cc
        L_0x02ad:
            java.lang.String r8 = "AUDIO"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x02cb
            r7 = 1
            goto L_0x02cc
        L_0x02b7:
            java.lang.String r8 = "CLOSED-CAPTIONS"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x02cb
            r7 = 3
            goto L_0x02cc
        L_0x02c1:
            java.lang.String r8 = "SUBTITLES"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x02cb
            r7 = 2
            goto L_0x02cc
        L_0x02cb:
            r7 = -1
        L_0x02cc:
            if (r7 == 0) goto L_0x03ba
            r8 = 1
            if (r7 == r8) goto L_0x0345
            if (r7 == r14) goto L_0x0324
            r8 = 3
            if (r7 == r8) goto L_0x02da
        L_0x02d6:
            r17 = 0
            goto L_0x040b
        L_0x02da:
            java.util.regex.Pattern r3 = REGEX_INSTREAM_ID
            java.lang.String r3 = parseStringAttr(r9, r3, r11)
            java.lang.String r7 = "CC"
            boolean r7 = r3.startsWith(r7)
            if (r7 == 0) goto L_0x02f3
            java.lang.String r3 = r3.substring(r14)
            int r3 = java.lang.Integer.parseInt(r3)
            java.lang.String r7 = "application/cea-608"
            goto L_0x02fe
        L_0x02f3:
            r7 = 7
            java.lang.String r3 = r3.substring(r7)
            int r3 = java.lang.Integer.parseInt(r3)
            java.lang.String r7 = "application/cea-708"
        L_0x02fe:
            r32 = r3
            r26 = r7
            if (r0 != 0) goto L_0x0309
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x0309:
            r25 = 0
            r27 = 0
            r28 = -1
            r24 = r15
            r29 = r34
            r30 = r1
            r31 = r35
            com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createTextContainerFormat(r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            r0.add(r1)
            r8 = r37
            r17 = 0
            goto L_0x040d
        L_0x0324:
            r27 = 0
            r28 = -1
            java.lang.String r25 = "application/x-mpegURL"
            java.lang.String r26 = "text/vtt"
            r24 = r15
            r29 = r34
            r30 = r1
            r31 = r35
            com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createTextContainerFormat(r23, r24, r25, r26, r27, r28, r29, r30, r31)
            com.google.android.exoplayer2.Format r1 = r1.copyWithMetadata(r3)
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition r3 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition
            r3.<init>(r12, r1, r10, r15)
            r6.add(r3)
            goto L_0x02d6
        L_0x0345:
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r7 = getVariantWithAudioGroup(r2, r10)
            if (r7 == 0) goto L_0x0357
            com.google.android.exoplayer2.Format r7 = r7.format
            java.lang.String r7 = r7.codecs
            r8 = 1
            java.lang.String r7 = com.google.android.exoplayer2.util.Util.getCodecsOfType(r7, r8)
            r27 = r7
            goto L_0x035a
        L_0x0357:
            r8 = 1
            r27 = 0
        L_0x035a:
            if (r27 == 0) goto L_0x0361
            java.lang.String r7 = com.google.android.exoplayer2.util.MimeTypes.getMediaMimeType(r27)
            goto L_0x0362
        L_0x0361:
            r7 = 0
        L_0x0362:
            java.util.regex.Pattern r14 = REGEX_CHANNELS
            java.lang.String r9 = parseOptionalStringAttr(r9, r14, r11)
            if (r9 == 0) goto L_0x038f
            java.lang.String r14 = "/"
            java.lang.String[] r14 = com.google.android.exoplayer2.util.Util.splitAtFirst(r9, r14)
            r17 = 0
            r14 = r14[r17]
            int r14 = java.lang.Integer.parseInt(r14)
            java.lang.String r8 = "audio/eac3"
            boolean r8 = r8.equals(r7)
            if (r8 == 0) goto L_0x038a
            java.lang.String r8 = "/JOC"
            boolean r8 = r9.endsWith(r8)
            if (r8 == 0) goto L_0x038a
            java.lang.String r7 = "audio/eac3-joc"
        L_0x038a:
            r26 = r7
            r30 = r14
            goto L_0x0395
        L_0x038f:
            r17 = 0
            r26 = r7
            r30 = -1
        L_0x0395:
            r28 = 0
            r29 = -1
            r31 = -1
            r32 = 0
            java.lang.String r25 = "application/x-mpegURL"
            r24 = r15
            r33 = r34
            r34 = r1
            com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createAudioContainerFormat(r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35)
            if (r12 != 0) goto L_0x03ad
            r8 = r1
            goto L_0x040d
        L_0x03ad:
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition r7 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition
            com.google.android.exoplayer2.Format r1 = r1.copyWithMetadata(r3)
            r7.<init>(r12, r1, r10, r15)
            r5.add(r7)
            goto L_0x040b
        L_0x03ba:
            r17 = 0
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r7 = getVariantWithVideoGroup(r2, r10)
            if (r7 == 0) goto L_0x03d9
            com.google.android.exoplayer2.Format r7 = r7.format
            java.lang.String r8 = r7.codecs
            java.lang.String r8 = com.google.android.exoplayer2.util.Util.getCodecsOfType(r8, r14)
            int r9 = r7.width
            int r14 = r7.height
            float r7 = r7.frameRate
            r32 = r7
            r27 = r8
            r30 = r9
            r31 = r14
            goto L_0x03e1
        L_0x03d9:
            r27 = 0
            r30 = -1
            r31 = -1
            r32 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x03e1:
            if (r27 == 0) goto L_0x03ea
            java.lang.String r7 = com.google.android.exoplayer2.util.MimeTypes.getMediaMimeType(r27)
            r26 = r7
            goto L_0x03ec
        L_0x03ea:
            r26 = 0
        L_0x03ec:
            r28 = 0
            r29 = -1
            r33 = 0
            java.lang.String r25 = "application/x-mpegURL"
            r24 = r15
            r35 = r1
            com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createVideoContainerFormat(r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35)
            com.google.android.exoplayer2.Format r1 = r1.copyWithMetadata(r3)
            if (r12 != 0) goto L_0x0403
            goto L_0x040b
        L_0x0403:
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition r3 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition
            r3.<init>(r12, r1, r10, r15)
            r4.add(r3)
        L_0x040b:
            r8 = r37
        L_0x040d:
            int r13 = r13 + 1
            r1 = r41
            r3 = r36
            r7 = r38
            r14 = r39
            r12 = 0
            goto L_0x0230
        L_0x041a:
            r38 = r7
            r37 = r8
            r39 = r14
            if (r39 == 0) goto L_0x0426
            java.util.List r0 = java.util.Collections.emptyList()
        L_0x0426:
            r9 = r0
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist r13 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist
            r0 = r13
            r1 = r41
            r2 = r22
            r3 = r38
            r7 = r20
            r8 = r37
            r10 = r19
            r12 = r21
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.parseMasterPlaylist(com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser$LineIterator, java.lang.String):com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist");
    }

    private static HlsMasterPlaylist.Variant getVariantWithAudioGroup(ArrayList<HlsMasterPlaylist.Variant> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMasterPlaylist.Variant variant = arrayList.get(i);
            if (str.equals(variant.audioGroupId)) {
                return variant;
            }
        }
        return null;
    }

    private static HlsMasterPlaylist.Variant getVariantWithVideoGroup(ArrayList<HlsMasterPlaylist.Variant> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMasterPlaylist.Variant variant = arrayList.get(i);
            if (str.equals(variant.videoGroupId)) {
                return variant;
            }
        }
        return null;
    }

    private static HlsMediaPlaylist parseMediaPlaylist(HlsMasterPlaylist hlsMasterPlaylist, LineIterator lineIterator, String str) throws IOException {
        String str2;
        TreeMap treeMap;
        DrmInitData drmInitData;
        long j;
        long j2;
        HlsMasterPlaylist hlsMasterPlaylist2 = hlsMasterPlaylist;
        boolean z = hlsMasterPlaylist2.hasIndependentSegments;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        TreeMap treeMap2 = new TreeMap();
        String str3 = "";
        char c = 0;
        int i = 1;
        boolean z2 = z;
        long j3 = -9223372036854775807L;
        long j4 = -9223372036854775807L;
        String str4 = str3;
        boolean z3 = false;
        int i2 = 0;
        String str5 = null;
        String str6 = null;
        long j5 = 0;
        int i3 = 0;
        long j6 = 0;
        int i4 = 1;
        boolean z4 = false;
        DrmInitData drmInitData2 = null;
        long j7 = 0;
        long j8 = 0;
        DrmInitData drmInitData3 = null;
        boolean z5 = false;
        String str7 = null;
        long j9 = -1;
        int i5 = 0;
        long j10 = 0;
        HlsMediaPlaylist.Segment segment = null;
        while (true) {
            long j11 = 0;
            while (lineIterator.hasNext()) {
                String next = lineIterator.next();
                if (next.startsWith("#EXT")) {
                    arrayList2.add(next);
                }
                if (next.startsWith("#EXT-X-PLAYLIST-TYPE")) {
                    String parseStringAttr = parseStringAttr(next, REGEX_PLAYLIST_TYPE, hashMap);
                    if ("VOD".equals(parseStringAttr)) {
                        i2 = 1;
                    } else if ("EVENT".equals(parseStringAttr)) {
                        i2 = 2;
                    }
                } else if (next.startsWith("#EXT-X-START")) {
                    j3 = (long) (parseDoubleAttr(next, REGEX_TIME_OFFSET) * 1000000.0d);
                } else if (next.startsWith("#EXT-X-MAP")) {
                    String parseStringAttr2 = parseStringAttr(next, REGEX_URI, hashMap);
                    String parseOptionalStringAttr = parseOptionalStringAttr(next, REGEX_ATTR_BYTERANGE, hashMap);
                    if (parseOptionalStringAttr != null) {
                        String[] split = parseOptionalStringAttr.split("@");
                        long parseLong = Long.parseLong(split[c]);
                        if (split.length > i) {
                            j7 = Long.parseLong(split[i]);
                        }
                        j = parseLong;
                        j2 = j7;
                    } else {
                        j2 = j7;
                        j = j9;
                    }
                    if (str5 == null || str7 != null) {
                        segment = new HlsMediaPlaylist.Segment(parseStringAttr2, j2, j, str5, str7);
                        c = 0;
                        j7 = 0;
                        j9 = -1;
                    } else {
                        throw new ParserException("The encryption IV attribute must be present when an initialization segment is encrypted with METHOD=AES-128.");
                    }
                } else {
                    if (next.startsWith("#EXT-X-TARGETDURATION")) {
                        j4 = ((long) parseIntAttr(next, REGEX_TARGET_DURATION)) * 1000000;
                    } else if (next.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                        j8 = parseLongAttr(next, REGEX_MEDIA_SEQUENCE);
                        j6 = j8;
                    } else if (next.startsWith("#EXT-X-VERSION")) {
                        i4 = parseIntAttr(next, REGEX_VERSION);
                    } else {
                        if (next.startsWith("#EXT-X-DEFINE")) {
                            String parseOptionalStringAttr2 = parseOptionalStringAttr(next, REGEX_IMPORT, hashMap);
                            if (parseOptionalStringAttr2 != null) {
                                String str8 = hlsMasterPlaylist2.variableDefinitions.get(parseOptionalStringAttr2);
                                if (str8 != null) {
                                    hashMap.put(parseOptionalStringAttr2, str8);
                                }
                            } else {
                                hashMap.put(parseStringAttr(next, REGEX_NAME, hashMap), parseStringAttr(next, REGEX_VALUE, hashMap));
                            }
                        } else if (next.startsWith("#EXTINF")) {
                            str4 = parseOptionalStringAttr(next, REGEX_MEDIA_TITLE, str3, hashMap);
                            j11 = (long) (parseDoubleAttr(next, REGEX_MEDIA_DURATION) * 1000000.0d);
                        } else if (next.startsWith("#EXT-X-KEY")) {
                            String parseStringAttr3 = parseStringAttr(next, REGEX_METHOD, hashMap);
                            String parseOptionalStringAttr3 = parseOptionalStringAttr(next, REGEX_KEYFORMAT, "identity", hashMap);
                            if ("NONE".equals(parseStringAttr3)) {
                                treeMap2.clear();
                                str5 = null;
                                drmInitData3 = null;
                                str7 = null;
                            } else {
                                String parseOptionalStringAttr4 = parseOptionalStringAttr(next, REGEX_IV, hashMap);
                                if (!"identity".equals(parseOptionalStringAttr3)) {
                                    if (str6 == null) {
                                        str6 = parseEncryptionScheme(parseStringAttr3);
                                    }
                                    DrmInitData.SchemeData parseDrmSchemeData = parseDrmSchemeData(next, parseOptionalStringAttr3, hashMap);
                                    if (parseDrmSchemeData != null) {
                                        treeMap2.put(parseOptionalStringAttr3, parseDrmSchemeData);
                                        str7 = parseOptionalStringAttr4;
                                        str5 = null;
                                        drmInitData3 = null;
                                    }
                                } else if ("AES-128".equals(parseStringAttr3)) {
                                    str5 = parseStringAttr(next, REGEX_URI, hashMap);
                                    str7 = parseOptionalStringAttr4;
                                }
                                str7 = parseOptionalStringAttr4;
                                str5 = null;
                            }
                        } else if (next.startsWith("#EXT-X-BYTERANGE")) {
                            String[] split2 = parseStringAttr(next, REGEX_BYTERANGE, hashMap).split("@");
                            j9 = Long.parseLong(split2[0]);
                            if (split2.length > i) {
                                j7 = Long.parseLong(split2[i]);
                            }
                        } else if (next.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                            i3 = Integer.parseInt(next.substring(next.indexOf(58) + i));
                            z3 = true;
                        } else if (next.equals("#EXT-X-DISCONTINUITY")) {
                            i5++;
                        } else if (next.startsWith("#EXT-X-PROGRAM-DATE-TIME")) {
                            if (j5 == 0) {
                                j5 = C.msToUs(Util.parseXsDateTime(next.substring(next.indexOf(58) + i))) - j10;
                            }
                        } else if (next.equals("#EXT-X-GAP")) {
                            c = 0;
                            z5 = true;
                        } else if (next.equals("#EXT-X-INDEPENDENT-SEGMENTS")) {
                            c = 0;
                            z2 = true;
                        } else if (next.equals("#EXT-X-ENDLIST")) {
                            c = 0;
                            z4 = true;
                        } else if (!next.startsWith("#")) {
                            String hexString = str5 == null ? null : str7 != null ? str7 : Long.toHexString(j8);
                            long j12 = j8 + 1;
                            long j13 = j9 == -1 ? 0 : j7;
                            if (drmInitData3 != null || treeMap2.isEmpty()) {
                                treeMap = treeMap2;
                                str2 = str3;
                                drmInitData = drmInitData3;
                            } else {
                                DrmInitData.SchemeData[] schemeDataArr = (DrmInitData.SchemeData[]) treeMap2.values().toArray(new DrmInitData.SchemeData[0]);
                                drmInitData = new DrmInitData(str6, schemeDataArr);
                                if (drmInitData2 == null) {
                                    DrmInitData.SchemeData[] schemeDataArr2 = new DrmInitData.SchemeData[schemeDataArr.length];
                                    treeMap = treeMap2;
                                    str2 = str3;
                                    int i6 = 0;
                                    while (i6 < schemeDataArr.length) {
                                        schemeDataArr2[i6] = schemeDataArr[i6].copyWithData((byte[]) null);
                                        i6++;
                                        schemeDataArr = schemeDataArr;
                                    }
                                    drmInitData2 = new DrmInitData(str6, schemeDataArr2);
                                } else {
                                    treeMap = treeMap2;
                                    str2 = str3;
                                }
                            }
                            arrayList.add(new HlsMediaPlaylist.Segment(replaceVariableReferences(next, hashMap), segment, str4, j11, i5, j10, drmInitData, str5, hexString, j13, j9, z5));
                            j10 += j11;
                            if (j9 != -1) {
                                j13 += j9;
                            }
                            j7 = j13;
                            hlsMasterPlaylist2 = hlsMasterPlaylist;
                            j9 = -1;
                            j8 = j12;
                            drmInitData3 = drmInitData;
                            treeMap2 = treeMap;
                            str3 = str2;
                            str4 = str3;
                            c = 0;
                            i = 1;
                            z5 = false;
                        }
                        hlsMasterPlaylist2 = hlsMasterPlaylist;
                        treeMap2 = treeMap2;
                        str3 = str3;
                        c = 0;
                        i = 1;
                    }
                    c = 0;
                }
            }
            return new HlsMediaPlaylist(i2, str, arrayList2, j3, j5, z3, i3, j6, i4, j4, z2, z4, j5 != 0, drmInitData2, arrayList);
        }
    }

    private static int parseSelectionFlags(String str) {
        int i = parseOptionalBooleanAttribute(str, REGEX_DEFAULT, false) ? 1 : 0;
        if (parseOptionalBooleanAttribute(str, REGEX_FORCED, false)) {
            i |= 2;
        }
        return parseOptionalBooleanAttribute(str, REGEX_AUTOSELECT, false) ? i | 4 : i;
    }

    private static int parseRoleFlags(String str, Map<String, String> map) {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, REGEX_CHARACTERISTICS, map);
        int i = 0;
        if (TextUtils.isEmpty(parseOptionalStringAttr)) {
            return 0;
        }
        String[] split = Util.split(parseOptionalStringAttr, ",");
        if (Util.contains(split, "public.accessibility.describes-video")) {
            i = AdRequest.MAX_CONTENT_URL_LENGTH;
        }
        if (Util.contains(split, "public.accessibility.transcribes-spoken-dialog")) {
            i |= 4096;
        }
        if (Util.contains(split, "public.accessibility.describes-music-and-sound")) {
            i |= d.fb;
        }
        return Util.contains(split, "public.easy-to-read") ? i | 8192 : i;
    }

    private static DrmInitData.SchemeData parseDrmSchemeData(String str, String str2, Map<String, String> map) throws ParserException {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, REGEX_KEYFORMATVERSIONS, "1", map);
        if ("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed".equals(str2)) {
            String parseStringAttr = parseStringAttr(str, REGEX_URI, map);
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "video/mp4", Base64.decode(parseStringAttr.substring(parseStringAttr.indexOf(44)), 0));
        } else if ("com.widevine".equals(str2)) {
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "hls", Util.getUtf8Bytes(str));
        } else {
            if (!"com.microsoft.playready".equals(str2) || !"1".equals(parseOptionalStringAttr)) {
                return null;
            }
            String parseStringAttr2 = parseStringAttr(str, REGEX_URI, map);
            return new DrmInitData.SchemeData(C.PLAYREADY_UUID, "video/mp4", PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(parseStringAttr2.substring(parseStringAttr2.indexOf(44)), 0)));
        }
    }

    private static String parseEncryptionScheme(String str) {
        return ("SAMPLE-AES-CENC".equals(str) || "SAMPLE-AES-CTR".equals(str)) ? "cenc" : "cbcs";
    }

    private static int parseIntAttr(String str, Pattern pattern) throws ParserException {
        return Integer.parseInt(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static int parseOptionalIntAttr(String str, Pattern pattern, int i) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : i;
    }

    private static long parseLongAttr(String str, Pattern pattern) throws ParserException {
        return Long.parseLong(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static double parseDoubleAttr(String str, Pattern pattern) throws ParserException {
        return Double.parseDouble(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static String parseStringAttr(String str, Pattern pattern, Map<String, String> map) throws ParserException {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, pattern, map);
        if (parseOptionalStringAttr != null) {
            return parseOptionalStringAttr;
        }
        throw new ParserException("Couldn't match " + pattern.pattern() + " in " + str);
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern, Map<String, String> map) {
        return parseOptionalStringAttr(str, pattern, (String) null, map);
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern, String str2, Map<String, String> map) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str2 = matcher.group(1);
        }
        return (map.isEmpty() || str2 == null) ? str2 : replaceVariableReferences(str2, map);
    }

    private static String replaceVariableReferences(String str, Map<String, String> map) {
        Matcher matcher = REGEX_VARIABLE_REFERENCE.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (map.containsKey(group)) {
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(map.get(group)));
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static boolean parseOptionalBooleanAttribute(String str, Pattern pattern, boolean z) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1).equals("YES") : z;
    }

    private static Pattern compileBooleanAttrPattern(String str) {
        return Pattern.compile(str + "=(" + "NO" + "|" + "YES" + ")");
    }

    private static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> queue, BufferedReader bufferedReader) {
            this.extraLines = queue;
            this.reader = bufferedReader;
        }

        @EnsuresNonNullIf(expression = {"next"}, result = true)
        public boolean hasNext() throws IOException {
            String trim;
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = (String) Assertions.checkNotNull(this.extraLines.poll());
                return true;
            }
            do {
                String readLine = this.reader.readLine();
                this.next = readLine;
                if (readLine == null) {
                    return false;
                }
                trim = readLine.trim();
                this.next = trim;
            } while (trim.isEmpty());
            return true;
        }

        public String next() throws IOException {
            if (hasNext()) {
                String str = this.next;
                this.next = null;
                return str;
            }
            throw new NoSuchElementException();
        }
    }
}
