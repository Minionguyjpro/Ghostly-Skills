package androidx.media2.exoplayer.external.extractor.mp4;

import android.util.Pair;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.GaplessInfoHolder;
import androidx.media2.exoplayer.external.extractor.mp4.Atom;
import androidx.media2.exoplayer.external.extractor.mp4.FixedSampleSizeRechunker;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.MimeTypes;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.Util;
import androidx.media2.exoplayer.external.video.AvcConfig;
import androidx.media2.exoplayer.external.video.ColorInfo;
import androidx.media2.exoplayer.external.video.DolbyVisionConfig;
import androidx.media2.exoplayer.external.video.HevcConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class AtomParsers {
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    private static int getTrackTypeForHdlr(int i) {
        if (i == 1936684398) {
            return 1;
        }
        if (i == 1986618469) {
            return 2;
        }
        if (i == 1952807028 || i == 1935832172 || i == 1937072756 || i == 1668047728) {
            return 3;
        }
        return i == 1835365473 ? 4 : -1;
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long j2;
        Atom.LeafAtom leafAtom2;
        long[] jArr;
        long[] jArr2;
        Atom.ContainerAtom containerAtom2 = containerAtom;
        Atom.ContainerAtom containerAtomOfType = containerAtom2.getContainerAtomOfType(1835297121);
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(containerAtomOfType.getLeafAtomOfType(1751411826).data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(containerAtom2.getLeafAtomOfType(1953196132).data);
        long j3 = -9223372036854775807L;
        if (j == -9223372036854775807L) {
            leafAtom2 = leafAtom;
            j2 = parseTkhd.duration;
        } else {
            leafAtom2 = leafAtom;
            j2 = j;
        }
        long parseMvhd = parseMvhd(leafAtom2.data);
        if (j2 != -9223372036854775807L) {
            j3 = Util.scaleLargeTimestamp(j2, 1000000, parseMvhd);
        }
        long j4 = j3;
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(1835626086).getContainerAtomOfType(1937007212);
        Pair<Long, String> parseMdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(1835296868).data);
        StsdData parseStsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(1937011556).data, parseTkhd.id, parseTkhd.rotationDegrees, (String) parseMdhd.second, drmInitData, z2);
        if (!z) {
            Pair<long[], long[]> parseEdts = parseEdts(containerAtom2.getContainerAtomOfType(1701082227));
            jArr = (long[]) parseEdts.second;
            jArr2 = (long[]) parseEdts.first;
        } else {
            jArr2 = null;
            jArr = null;
        }
        if (parseStsd.format == null) {
            return null;
        }
        return new Track(parseTkhd.id, trackTypeForHdlr, ((Long) parseMdhd.first).longValue(), parseMvhd, j4, parseStsd.format, parseStsd.requiredSampleTransformation, parseStsd.trackEncryptionBoxes, parseStsd.nalUnitLengthFieldLength, jArr2, jArr);
    }

    public static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox sampleSizeBox;
        boolean z;
        int i;
        int i2;
        int i3;
        long j;
        int[] iArr;
        long[] jArr;
        Track track2;
        int[] iArr2;
        long[] jArr2;
        int i4;
        int i5;
        int[] iArr3;
        int[] iArr4;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        boolean z2;
        int i12;
        int i13;
        int i14;
        Track track3 = track;
        Atom.ContainerAtom containerAtom2 = containerAtom;
        GaplessInfoHolder gaplessInfoHolder2 = gaplessInfoHolder;
        Atom.LeafAtom leafAtomOfType = containerAtom2.getLeafAtomOfType(1937011578);
        if (leafAtomOfType != null) {
            sampleSizeBox = new StszSampleSizeBox(leafAtomOfType);
        } else {
            Atom.LeafAtom leafAtomOfType2 = containerAtom2.getLeafAtomOfType(1937013298);
            if (leafAtomOfType2 != null) {
                sampleSizeBox = new Stz2SampleSizeBox(leafAtomOfType2);
            } else {
                throw new ParserException("Track has no sample table size information");
            }
        }
        int sampleCount = sampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(track, new long[0], new int[0], 0, new long[0], new int[0], -9223372036854775807L);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom2.getLeafAtomOfType(1937007471);
        if (leafAtomOfType3 == null) {
            leafAtomOfType3 = containerAtom2.getLeafAtomOfType(1668232756);
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType3.data;
        ParsableByteArray parsableByteArray2 = containerAtom2.getLeafAtomOfType(1937011555).data;
        ParsableByteArray parsableByteArray3 = containerAtom2.getLeafAtomOfType(1937011827).data;
        Atom.LeafAtom leafAtomOfType4 = containerAtom2.getLeafAtomOfType(1937011571);
        ParsableByteArray parsableByteArray4 = null;
        ParsableByteArray parsableByteArray5 = leafAtomOfType4 != null ? leafAtomOfType4.data : null;
        Atom.LeafAtom leafAtomOfType5 = containerAtom2.getLeafAtomOfType(1668576371);
        ParsableByteArray parsableByteArray6 = leafAtomOfType5 != null ? leafAtomOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int readUnsignedIntToInt = parsableByteArray3.readUnsignedIntToInt() - 1;
        int readUnsignedIntToInt2 = parsableByteArray3.readUnsignedIntToInt();
        int readUnsignedIntToInt3 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray6 != null) {
            parsableByteArray6.setPosition(12);
            i = parsableByteArray6.readUnsignedIntToInt();
        } else {
            i = 0;
        }
        int i15 = -1;
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            i2 = parsableByteArray5.readUnsignedIntToInt();
            if (i2 > 0) {
                i15 = parsableByteArray5.readUnsignedIntToInt() - 1;
                parsableByteArray4 = parsableByteArray5;
            }
        } else {
            parsableByteArray4 = parsableByteArray5;
            i2 = 0;
        }
        if (!(sampleSizeBox.isFixedSampleSize() && "audio/raw".equals(track3.format.sampleMimeType) && readUnsignedIntToInt == 0 && i == 0 && i2 == 0)) {
            long[] jArr3 = new long[sampleCount];
            int[] iArr5 = new int[sampleCount];
            long[] jArr4 = new long[sampleCount];
            int i16 = i2;
            iArr = new int[sampleCount];
            ParsableByteArray parsableByteArray7 = parsableByteArray3;
            int i17 = i15;
            long j2 = 0;
            long j3 = 0;
            int i18 = 0;
            i3 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = i;
            int i23 = readUnsignedIntToInt3;
            int i24 = readUnsignedIntToInt2;
            int i25 = readUnsignedIntToInt;
            int i26 = i16;
            while (true) {
                i8 = i25;
                if (i18 >= sampleCount) {
                    int i27 = sampleCount;
                    i9 = i24;
                    i10 = i19;
                    i11 = i20;
                    break;
                }
                long j4 = j3;
                int i28 = i20;
                boolean z3 = true;
                while (i28 == 0) {
                    z3 = chunkIterator.moveNext();
                    if (!z3) {
                        break;
                    }
                    int i29 = i24;
                    long j5 = chunkIterator.offset;
                    i28 = chunkIterator.numSamples;
                    j4 = j5;
                    i24 = i29;
                    i23 = i23;
                    sampleCount = sampleCount;
                }
                int i30 = sampleCount;
                i9 = i24;
                int i31 = i23;
                if (!z3) {
                    Log.w("AtomParsers", "Unexpected end of chunk data");
                    jArr3 = Arrays.copyOf(jArr3, i18);
                    iArr5 = Arrays.copyOf(iArr5, i18);
                    jArr4 = Arrays.copyOf(jArr4, i18);
                    iArr = Arrays.copyOf(iArr, i18);
                    sampleCount = i18;
                    i10 = i19;
                    i11 = i28;
                    break;
                }
                if (parsableByteArray6 != null) {
                    while (i21 == 0 && i22 > 0) {
                        i21 = parsableByteArray6.readUnsignedIntToInt();
                        i19 = parsableByteArray6.readInt();
                        i22--;
                    }
                    i21--;
                }
                int i32 = i19;
                jArr3[i18] = j4;
                iArr5[i18] = sampleSizeBox.readNextSampleSize();
                if (iArr5[i18] > i3) {
                    i3 = iArr5[i18];
                }
                jArr4[i18] = j2 + ((long) i32);
                iArr[i18] = parsableByteArray4 == null ? 1 : 0;
                if (i18 == i17) {
                    iArr[i18] = 1;
                    i26--;
                    if (i26 > 0) {
                        i17 = parsableByteArray4.readUnsignedIntToInt() - 1;
                    }
                }
                int i33 = i17;
                int i34 = i32;
                int i35 = i31;
                j2 += (long) i35;
                int i36 = i9 - 1;
                if (i36 != 0 || i8 <= 0) {
                    i14 = i35;
                    i13 = i8;
                } else {
                    i36 = parsableByteArray7.readUnsignedIntToInt();
                    i14 = parsableByteArray7.readInt();
                    i13 = i8 - 1;
                }
                int i37 = i36;
                i20 = i28 - 1;
                i18++;
                j3 = j4 + ((long) iArr5[i18]);
                i17 = i33;
                i23 = i14;
                sampleCount = i30;
                i19 = i34;
                i25 = i13;
                i24 = i37;
            }
            long j6 = j2 + ((long) i10);
            while (true) {
                if (i22 <= 0) {
                    z2 = true;
                    break;
                } else if (parsableByteArray6.readUnsignedIntToInt() != 0) {
                    z2 = false;
                    break;
                } else {
                    parsableByteArray6.readInt();
                    i22--;
                }
            }
            if (i26 == 0 && i9 == 0 && i11 == 0 && i8 == 0) {
                i12 = i21;
                if (i12 == 0 && z2) {
                    track2 = track;
                    i4 = sampleCount;
                    jArr2 = jArr3;
                    jArr = jArr4;
                    iArr2 = iArr5;
                    j = j6;
                }
            } else {
                i12 = i21;
            }
            track2 = track;
            int i38 = track2.id;
            String str = !z2 ? ", ctts invalid" : "";
            StringBuilder sb = new StringBuilder(str.length() + 262);
            sb.append("Inconsistent stbl box for track ");
            sb.append(i38);
            sb.append(": remainingSynchronizationSamples ");
            sb.append(i26);
            sb.append(", remainingSamplesAtTimestampDelta ");
            sb.append(i9);
            sb.append(", remainingSamplesInChunk ");
            sb.append(i11);
            sb.append(", remainingTimestampDeltaChanges ");
            sb.append(i8);
            sb.append(", remainingSamplesAtTimestampOffset ");
            sb.append(i12);
            sb.append(str);
            Log.w("AtomParsers", sb.toString());
            i4 = sampleCount;
            jArr2 = jArr3;
            jArr = jArr4;
            iArr2 = iArr5;
            j = j6;
        } else {
            track2 = track3;
            int i39 = sampleCount;
            long[] jArr5 = new long[chunkIterator.length];
            int[] iArr6 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr5[chunkIterator.index] = chunkIterator.offset;
                iArr6[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results rechunk = FixedSampleSizeRechunker.rechunk(Util.getPcmFrameSize(track2.format.pcmEncoding, track2.format.channelCount), jArr5, iArr6, (long) readUnsignedIntToInt3);
            long[] jArr6 = rechunk.offsets;
            int[] iArr7 = rechunk.sizes;
            int i40 = rechunk.maximumSize;
            jArr = rechunk.timestamps;
            iArr = rechunk.flags;
            j = rechunk.duration;
            i3 = i40;
            i4 = i39;
            iArr2 = iArr7;
            jArr2 = jArr6;
        }
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(j, 1000000, track2.timescale);
        if (track2.editListDurations == null || gaplessInfoHolder.hasGaplessInfo()) {
            Util.scaleLargeTimestampsInPlace(jArr, 1000000, track2.timescale);
            return new TrackSampleTable(track, jArr2, iArr2, i3, jArr, iArr, scaleLargeTimestamp);
        }
        if (track2.editListDurations.length == 1 && track2.type == 1 && jArr.length >= 2) {
            long j7 = track2.editListMediaTimes[0];
            i5 = i4;
            long scaleLargeTimestamp2 = Util.scaleLargeTimestamp(track2.editListDurations[0], track2.timescale, track2.movieTimescale) + j7;
            if (canApplyEditWithGaplessInfo(jArr, j, j7, scaleLargeTimestamp2)) {
                long j8 = j - scaleLargeTimestamp2;
                long scaleLargeTimestamp3 = Util.scaleLargeTimestamp(j7 - jArr[0], (long) track2.format.sampleRate, track2.timescale);
                long scaleLargeTimestamp4 = Util.scaleLargeTimestamp(j8, (long) track2.format.sampleRate, track2.timescale);
                if (!(scaleLargeTimestamp3 == 0 && scaleLargeTimestamp4 == 0) && scaleLargeTimestamp3 <= 2147483647L && scaleLargeTimestamp4 <= 2147483647L) {
                    int i41 = (int) scaleLargeTimestamp3;
                    GaplessInfoHolder gaplessInfoHolder3 = gaplessInfoHolder;
                    gaplessInfoHolder3.encoderDelay = i41;
                    gaplessInfoHolder3.encoderPadding = (int) scaleLargeTimestamp4;
                    Util.scaleLargeTimestampsInPlace(jArr, 1000000, track2.timescale);
                    return new TrackSampleTable(track, jArr2, iArr2, i3, jArr, iArr, Util.scaleLargeTimestamp(track2.editListDurations[0], 1000000, track2.movieTimescale));
                }
            }
        } else {
            i5 = i4;
        }
        if (track2.editListDurations.length == 1 && track2.editListDurations[0] == 0) {
            long j9 = track2.editListMediaTimes[0];
            for (int i42 = 0; i42 < jArr.length; i42++) {
                jArr[i42] = Util.scaleLargeTimestamp(jArr[i42] - j9, 1000000, track2.timescale);
            }
            return new TrackSampleTable(track, jArr2, iArr2, i3, jArr, iArr, Util.scaleLargeTimestamp(j - j9, 1000000, track2.timescale));
        }
        boolean z4 = track2.type == 1;
        int[] iArr8 = new int[track2.editListDurations.length];
        int[] iArr9 = new int[track2.editListDurations.length];
        int i43 = 0;
        boolean z5 = false;
        int i44 = 0;
        int i45 = 0;
        while (i43 < track2.editListDurations.length) {
            long[] jArr7 = jArr2;
            int[] iArr10 = iArr2;
            long j10 = track2.editListMediaTimes[i43];
            if (j10 != -1) {
                i7 = i5;
                i6 = i3;
                long scaleLargeTimestamp5 = Util.scaleLargeTimestamp(track2.editListDurations[i43], track2.timescale, track2.movieTimescale);
                iArr8[i43] = Util.binarySearchCeil(jArr, j10, true, true);
                iArr9[i43] = Util.binarySearchCeil(jArr, j10 + scaleLargeTimestamp5, z4, false);
                while (iArr8[i43] < iArr9[i43] && (iArr[iArr8[i43]] & 1) == 0) {
                    iArr8[i43] = iArr8[i43] + 1;
                }
                i44 += iArr9[i43] - iArr8[i43];
                z5 = (i45 != iArr8[i43]) | z5;
                i45 = iArr9[i43];
            } else {
                i7 = i5;
                i6 = i3;
            }
            i43++;
            jArr2 = jArr7;
            i5 = i7;
            iArr2 = iArr10;
            i3 = i6;
        }
        long[] jArr8 = jArr2;
        int[] iArr11 = iArr2;
        int i46 = i5;
        int i47 = i3;
        int i48 = 0;
        boolean z6 = true;
        if (i44 == i46) {
            z6 = false;
        }
        boolean z7 = z5 | z6;
        long[] jArr9 = z7 ? new long[i44] : jArr8;
        int[] iArr12 = z7 ? new int[i44] : iArr11;
        int i49 = z7 ? 0 : i47;
        int[] iArr13 = z7 ? new int[i44] : iArr;
        long[] jArr10 = new long[i44];
        long j11 = 0;
        int i50 = 0;
        while (i48 < track2.editListDurations.length) {
            long j12 = track2.editListMediaTimes[i48];
            int i51 = iArr8[i48];
            int i52 = iArr9[i48];
            if (z7) {
                int i53 = i52 - i51;
                iArr3 = iArr9;
                System.arraycopy(jArr8, i51, jArr9, i50, i53);
                iArr4 = iArr11;
                System.arraycopy(iArr4, i51, iArr12, i50, i53);
                System.arraycopy(iArr, i51, iArr13, i50, i53);
            } else {
                iArr3 = iArr9;
                iArr4 = iArr11;
            }
            while (i51 < i52) {
                int[] iArr14 = iArr8;
                long[] jArr11 = jArr9;
                int i54 = i51;
                int i55 = i52;
                jArr10[i50] = Util.scaleLargeTimestamp(j11, 1000000, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr[i54] - j12, 1000000, track2.timescale);
                if (z7 && iArr12[i50] > i49) {
                    i49 = iArr4[i54];
                }
                i50++;
                i51 = i54 + 1;
                iArr8 = iArr14;
                jArr9 = jArr11;
                i52 = i55;
            }
            long[] jArr12 = jArr9;
            j11 += track2.editListDurations[i48];
            i48++;
            iArr8 = iArr8;
            iArr11 = iArr4;
            iArr9 = iArr3;
        }
        long[] jArr13 = jArr9;
        return new TrackSampleTable(track, jArr9, iArr12, i49, jArr10, iArr13, Util.scaleLargeTimestamp(j11, 1000000, track2.movieTimescale));
    }

    public static Metadata parseUdta(Atom.LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1835365473) {
                parsableByteArray.setPosition(position);
                return parseUdtaMeta(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    public static Metadata parseMdtaFromMeta(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(1751411826);
        Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(1801812339);
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(1768715124);
        if (leafAtomOfType == null || leafAtomOfType2 == null || leafAtomOfType3 == null || parseHdlr(leafAtomOfType.data) != 1835299937) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType2.data;
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        String[] strArr = new String[readInt];
        for (int i = 0; i < readInt; i++) {
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i] = parsableByteArray.readString(readInt2 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafAtomOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int readInt3 = parsableByteArray2.readInt();
            int readInt4 = parsableByteArray2.readInt() - 1;
            if (readInt4 < 0 || readInt4 >= readInt) {
                StringBuilder sb = new StringBuilder(52);
                sb.append("Skipped metadata with unknown key index: ");
                sb.append(readInt4);
                Log.w("AtomParsers", sb.toString());
            } else {
                MdtaMetadataEntry parseMdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + readInt3, strArr[readInt4]);
                if (parseMdtaMetadataEntryFromIlst != null) {
                    arrayList.add(parseMdtaMetadataEntryFromIlst);
                }
            }
            parsableByteArray2.setPosition(position + readInt3);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1768715124) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i = 16;
        }
        parsableByteArray.skipBytes(i);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                z = true;
                break;
            } else if (parsableByteArray.data[position + i3] != -1) {
                z = false;
                break;
            } else {
                i3++;
            }
        }
        long j = -9223372036854775807L;
        if (z) {
            parsableByteArray.skipBytes(i);
        } else {
            long readUnsignedInt = parseFullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (readUnsignedInt != 0) {
                j = readUnsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && readInt4 == -65536 && readInt5 == 0) {
            i2 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && readInt4 == 65536 && readInt5 == 0) {
            i2 = 270;
        } else if (readInt2 == -65536 && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i2 = 180;
        }
        return new TkhdData(readInt, j, i2);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        parsableByteArray.skipBytes(i);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        StringBuilder sb = new StringBuilder(3);
        sb.append((char) (((readUnsignedShort >> 10) & 31) + 96));
        sb.append((char) (((readUnsignedShort >> 5) & 31) + 96));
        sb.append((char) ((readUnsignedShort & 31) + 96));
        return Pair.create(Long.valueOf(readUnsignedInt), sb.toString());
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i3 = 0; i3 < readInt; i3++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            Assertions.checkArgument(readInt2 > 0, "childAtomSize should be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == 1635148593 || readInt3 == 1635148595 || readInt3 == 1701733238 || readInt3 == 1836070006 || readInt3 == 1752589105 || readInt3 == 1751479857 || readInt3 == 1932670515 || readInt3 == 1987063864 || readInt3 == 1987063865 || readInt3 == 1635135537 || readInt3 == 1685479798 || readInt3 == 1685479729 || readInt3 == 1685481573 || readInt3 == 1685481521) {
                parseVideoSampleEntry(parsableByteArray, readInt3, position, readInt2, i, i2, drmInitData, stsdData, i3);
            } else if (readInt3 == 1836069985 || readInt3 == 1701733217 || readInt3 == 1633889587 || readInt3 == 1700998451 || readInt3 == 1633889588 || readInt3 == 1685353315 || readInt3 == 1685353317 || readInt3 == 1685353320 || readInt3 == 1685353324 || readInt3 == 1935764850 || readInt3 == 1935767394 || readInt3 == 1819304813 || readInt3 == 1936684916 || readInt3 == 778924083 || readInt3 == 1634492771 || readInt3 == 1634492791 || readInt3 == 1970037111 || readInt3 == 1332770163 || readInt3 == 1716281667) {
                parseAudioSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, z, drmInitData, stsdData, i3);
            } else if (readInt3 == 1414810956 || readInt3 == 1954034535 || readInt3 == 2004251764 || readInt3 == 1937010800 || readInt3 == 1664495672) {
                parseTextSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, stsdData);
            } else if (readInt3 == 1667329389) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), "application/x-camera-motion", (String) null, -1, (DrmInitData) null);
            }
            parsableByteArray2.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) throws ParserException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i5 = i;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition(i2 + 8 + 8);
        String str2 = "application/ttml+xml";
        List list = null;
        long j = Long.MAX_VALUE;
        if (i5 != 1414810956) {
            if (i5 == 1954034535) {
                int i6 = (i3 - 8) - 8;
                byte[] bArr = new byte[i6];
                parsableByteArray2.readBytes(bArr, 0, i6);
                list = Collections.singletonList(bArr);
                str2 = "application/x-quicktime-tx3g";
            } else if (i5 == 2004251764) {
                str2 = "application/x-mp4-vtt";
            } else if (i5 == 1937010800) {
                j = 0;
            } else if (i5 == 1664495672) {
                stsdData2.requiredSampleTransformation = 1;
                str2 = "application/x-mp4-cea-608";
            } else {
                throw new IllegalStateException();
            }
        }
        stsdData2.format = Format.createTextSampleFormat(Integer.toString(i4), str2, (String) null, -1, 0, str, -1, (DrmInitData) null, j, list);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, int i5, DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i7 = i2;
        int i8 = i3;
        DrmInitData drmInitData2 = drmInitData;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition(i7 + 8 + 8);
        parsableByteArray2.skipBytes(16);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray2.skipBytes(50);
        int position = parsableByteArray.getPosition();
        int i9 = i;
        if (i9 == 1701733238) {
            Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray2, i7, i8);
            if (parseSampleEntryEncryptionData != null) {
                i9 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData2 == null) {
                    drmInitData2 = null;
                } else {
                    drmInitData2 = drmInitData2.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData2.trackEncryptionBoxes[i6] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray2.setPosition(position);
        }
        DrmInitData drmInitData3 = drmInitData2;
        String str = null;
        String str2 = null;
        List<byte[]> list = null;
        byte[] bArr = null;
        boolean z = false;
        float f = 1.0f;
        int i10 = -1;
        while (position - i7 < i8) {
            parsableByteArray2.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (readInt == 0 && parsableByteArray.getPosition() - i7 == i8) {
                break;
            }
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1635148611) {
                Assertions.checkState(str == null);
                parsableByteArray2.setPosition(position2 + 8);
                AvcConfig parse = AvcConfig.parse(parsableByteArray);
                list = parse.initializationData;
                stsdData2.nalUnitLengthFieldLength = parse.nalUnitLengthFieldLength;
                if (!z) {
                    f = parse.pixelWidthAspectRatio;
                }
                str = "video/avc";
            } else if (readInt2 == 1752589123) {
                Assertions.checkState(str == null);
                parsableByteArray2.setPosition(position2 + 8);
                HevcConfig parse2 = HevcConfig.parse(parsableByteArray);
                list = parse2.initializationData;
                stsdData2.nalUnitLengthFieldLength = parse2.nalUnitLengthFieldLength;
                str = "video/hevc";
            } else if (readInt2 == 1685480259 || readInt2 == 1685485123) {
                DolbyVisionConfig parse3 = DolbyVisionConfig.parse(parsableByteArray);
                if (parse3 != null) {
                    str2 = parse3.codecs;
                    str = "video/dolby-vision";
                }
            } else if (readInt2 == 1987076931) {
                Assertions.checkState(str == null);
                str = i9 == 1987063864 ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
            } else if (readInt2 == 1635135811) {
                Assertions.checkState(str == null);
                str = "video/av01";
            } else if (readInt2 == 1681012275) {
                Assertions.checkState(str == null);
                str = "video/3gpp";
            } else if (readInt2 == 1702061171) {
                Assertions.checkState(str == null);
                Pair<String, byte[]> parseEsdsFromParent = parseEsdsFromParent(parsableByteArray2, position2);
                str = (String) parseEsdsFromParent.first;
                list = Collections.singletonList((byte[]) parseEsdsFromParent.second);
            } else if (readInt2 == 1885434736) {
                f = parsePaspFromParent(parsableByteArray2, position2);
                z = true;
            } else if (readInt2 == 1937126244) {
                bArr = parseProjFromParent(parsableByteArray2, position2, readInt);
            } else if (readInt2 == 1936995172) {
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray2.skipBytes(3);
                if (readUnsignedByte == 0) {
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (readUnsignedByte2 == 0) {
                        i10 = 0;
                    } else if (readUnsignedByte2 == 1) {
                        i10 = 1;
                    } else if (readUnsignedByte2 == 2) {
                        i10 = 2;
                    } else if (readUnsignedByte2 == 3) {
                        i10 = 3;
                    }
                }
            }
            position += readInt;
            i7 = i2;
        }
        if (str != null) {
            stsdData2.format = Format.createVideoSampleFormat(Integer.toString(i4), str, str2, -1, -1, readUnsignedShort, readUnsignedShort2, -1.0f, list, i5, f, bArr, i10, (ColorInfo) null, drmInitData3);
        }
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(1701606260)) == null) {
            return Pair.create((Object) null, (Object) null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[readUnsignedIntToInt];
        long[] jArr2 = new long[readUnsignedIntToInt];
        int i = 0;
        while (i < readUnsignedIntToInt) {
            jArr[i] = parseFullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = parseFullAtomVersion == 1 ? parsableByteArray.readLong() : (long) parsableByteArray.readInt();
            if (parsableByteArray.readShort() == 1) {
                parsableByteArray.skipBytes(2);
                i++;
            } else {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.String} */
    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0292: MOVE  (r8v4 java.lang.String) = (r25v0 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    /* JADX WARNING: Multi-variable type inference failed */
    private static void parseAudioSampleEntry(androidx.media2.exoplayer.external.util.ParsableByteArray r28, int r29, int r30, int r31, int r32, java.lang.String r33, boolean r34, androidx.media2.exoplayer.external.drm.DrmInitData r35, androidx.media2.exoplayer.external.extractor.mp4.AtomParsers.StsdData r36, int r37) throws androidx.media2.exoplayer.external.ParserException {
        /*
            r0 = r28
            r1 = r30
            r2 = r31
            r14 = r33
            r3 = r35
            r15 = r36
            int r4 = r1 + 8
            r5 = 8
            int r4 = r4 + r5
            r0.setPosition(r4)
            r4 = 6
            r13 = 0
            if (r34 == 0) goto L_0x0020
            int r5 = r28.readUnsignedShort()
            r0.skipBytes(r4)
            goto L_0x0024
        L_0x0020:
            r0.skipBytes(r5)
            r5 = 0
        L_0x0024:
            r12 = 2
            r6 = 16
            r11 = 1
            if (r5 == 0) goto L_0x0046
            if (r5 != r11) goto L_0x002d
            goto L_0x0046
        L_0x002d:
            if (r5 != r12) goto L_0x0045
            r0.skipBytes(r6)
            double r4 = r28.readDouble()
            long r4 = java.lang.Math.round(r4)
            int r5 = (int) r4
            int r4 = r28.readUnsignedIntToInt()
            r6 = 20
            r0.skipBytes(r6)
            goto L_0x0058
        L_0x0045:
            return
        L_0x0046:
            int r7 = r28.readUnsignedShort()
            r0.skipBytes(r4)
            int r4 = r28.readUnsignedFixedPoint1616()
            if (r5 != r11) goto L_0x0056
            r0.skipBytes(r6)
        L_0x0056:
            r5 = r4
            r4 = r7
        L_0x0058:
            int r6 = r28.getPosition()
            r7 = 1701733217(0x656e6361, float:7.0359778E22)
            r16 = 0
            r8 = r29
            if (r8 != r7) goto L_0x008d
            android.util.Pair r7 = parseSampleEntryEncryptionData(r0, r1, r2)
            if (r7 == 0) goto L_0x008a
            java.lang.Object r8 = r7.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            if (r3 != 0) goto L_0x0078
            r3 = r16
            goto L_0x0082
        L_0x0078:
            java.lang.Object r9 = r7.second
            androidx.media2.exoplayer.external.extractor.mp4.TrackEncryptionBox r9 = (androidx.media2.exoplayer.external.extractor.mp4.TrackEncryptionBox) r9
            java.lang.String r9 = r9.schemeType
            androidx.media2.exoplayer.external.drm.DrmInitData r3 = r3.copyWithSchemeType(r9)
        L_0x0082:
            androidx.media2.exoplayer.external.extractor.mp4.TrackEncryptionBox[] r9 = r15.trackEncryptionBoxes
            java.lang.Object r7 = r7.second
            androidx.media2.exoplayer.external.extractor.mp4.TrackEncryptionBox r7 = (androidx.media2.exoplayer.external.extractor.mp4.TrackEncryptionBox) r7
            r9[r37] = r7
        L_0x008a:
            r0.setPosition(r6)
        L_0x008d:
            r10 = r3
            r3 = 1633889587(0x61632d33, float:2.6191674E20)
            r9 = 1634492771(0x616c6163, float:2.7252807E20)
            java.lang.String r7 = "audio/raw"
            if (r8 != r3) goto L_0x009c
            java.lang.String r3 = "audio/ac3"
            goto L_0x0119
        L_0x009c:
            r3 = 1700998451(0x65632d33, float:6.7050686E22)
            if (r8 != r3) goto L_0x00a5
            java.lang.String r3 = "audio/eac3"
            goto L_0x0119
        L_0x00a5:
            r3 = 1633889588(0x61632d34, float:2.6191676E20)
            if (r8 != r3) goto L_0x00ae
            java.lang.String r3 = "audio/ac4"
            goto L_0x0119
        L_0x00ae:
            r3 = 1685353315(0x64747363, float:1.803728E22)
            if (r8 != r3) goto L_0x00b7
            java.lang.String r3 = "audio/vnd.dts"
            goto L_0x0119
        L_0x00b7:
            r3 = 1685353320(0x64747368, float:1.8037286E22)
            if (r8 == r3) goto L_0x0117
            r3 = 1685353324(0x6474736c, float:1.803729E22)
            if (r8 != r3) goto L_0x00c2
            goto L_0x0117
        L_0x00c2:
            r3 = 1685353317(0x64747365, float:1.8037282E22)
            if (r8 != r3) goto L_0x00ca
            java.lang.String r3 = "audio/vnd.dts.hd;profile=lbr"
            goto L_0x0119
        L_0x00ca:
            r3 = 1935764850(0x73616d72, float:1.7860208E31)
            if (r8 != r3) goto L_0x00d2
            java.lang.String r3 = "audio/3gpp"
            goto L_0x0119
        L_0x00d2:
            r3 = 1935767394(0x73617762, float:1.7863284E31)
            if (r8 != r3) goto L_0x00da
            java.lang.String r3 = "audio/amr-wb"
            goto L_0x0119
        L_0x00da:
            r3 = 1819304813(0x6c70636d, float:1.1624469E27)
            if (r8 == r3) goto L_0x0115
            r3 = 1936684916(0x736f7774, float:1.89725E31)
            if (r8 != r3) goto L_0x00e5
            goto L_0x0115
        L_0x00e5:
            r3 = 778924083(0x2e6d7033, float:5.3987214E-11)
            if (r8 != r3) goto L_0x00ed
            java.lang.String r3 = "audio/mpeg"
            goto L_0x0119
        L_0x00ed:
            if (r8 != r9) goto L_0x00f2
            java.lang.String r3 = "audio/alac"
            goto L_0x0119
        L_0x00f2:
            r3 = 1634492791(0x616c6177, float:2.7252842E20)
            if (r8 != r3) goto L_0x00fa
            java.lang.String r3 = "audio/g711-alaw"
            goto L_0x0119
        L_0x00fa:
            r3 = 1970037111(0x756c6177, float:2.9964816E32)
            if (r8 != r3) goto L_0x0102
            java.lang.String r3 = "audio/g711-mlaw"
            goto L_0x0119
        L_0x0102:
            r3 = 1332770163(0x4f707573, float:4.03422899E9)
            if (r8 != r3) goto L_0x010a
            java.lang.String r3 = "audio/opus"
            goto L_0x0119
        L_0x010a:
            r3 = 1716281667(0x664c6143, float:2.4128923E23)
            if (r8 != r3) goto L_0x0112
            java.lang.String r3 = "audio/flac"
            goto L_0x0119
        L_0x0112:
            r3 = r16
            goto L_0x0119
        L_0x0115:
            r3 = r7
            goto L_0x0119
        L_0x0117:
            java.lang.String r3 = "audio/vnd.dts.hd"
        L_0x0119:
            r8 = r3
            r17 = r4
            r18 = r5
            r19 = r16
        L_0x0120:
            int r3 = r6 - r1
            r4 = -1
            if (r3 >= r2) goto L_0x0286
            r0.setPosition(r6)
            int r5 = r28.readInt()
            if (r5 <= 0) goto L_0x0130
            r3 = 1
            goto L_0x0131
        L_0x0130:
            r3 = 0
        L_0x0131:
            java.lang.String r9 = "childAtomSize should be positive"
            androidx.media2.exoplayer.external.util.Assertions.checkArgument(r3, r9)
            int r3 = r28.readInt()
            r9 = 1702061171(0x65736473, float:7.183675E22)
            if (r3 == r9) goto L_0x0232
            if (r34 == 0) goto L_0x0148
            r11 = 2002876005(0x77617665, float:4.5729223E33)
            if (r3 != r11) goto L_0x0148
            goto L_0x0232
        L_0x0148:
            r4 = 1684103987(0x64616333, float:1.6630662E22)
            if (r3 != r4) goto L_0x016e
            int r3 = r6 + 8
            r0.setPosition(r3)
            java.lang.String r3 = java.lang.Integer.toString(r32)
            androidx.media2.exoplayer.external.Format r3 = androidx.media2.exoplayer.external.audio.Ac3Util.parseAc3AnnexFFormat(r0, r3, r14, r10)
            r15.format = r3
        L_0x015c:
            r27 = r7
            r25 = r8
            r21 = r10
            r1 = 0
            r20 = 1
            r22 = 2
            r7 = r6
            r6 = r5
        L_0x0169:
            r5 = 1634492771(0x616c6163, float:2.7252807E20)
            goto L_0x022f
        L_0x016e:
            r4 = 1684366131(0x64656333, float:1.692581E22)
            if (r3 != r4) goto L_0x0183
            int r3 = r6 + 8
            r0.setPosition(r3)
            java.lang.String r3 = java.lang.Integer.toString(r32)
            androidx.media2.exoplayer.external.Format r3 = androidx.media2.exoplayer.external.audio.Ac3Util.parseEAc3AnnexFFormat(r0, r3, r14, r10)
            r15.format = r3
            goto L_0x015c
        L_0x0183:
            r4 = 1684103988(0x64616334, float:1.6630663E22)
            if (r3 != r4) goto L_0x0198
            int r3 = r6 + 8
            r0.setPosition(r3)
            java.lang.String r3 = java.lang.Integer.toString(r32)
            androidx.media2.exoplayer.external.Format r3 = androidx.media2.exoplayer.external.audio.Ac4Util.parseAc4AnnexEFormat(r0, r3, r14, r10)
            r15.format = r3
            goto L_0x015c
        L_0x0198:
            r4 = 1684305011(0x64647473, float:1.6856995E22)
            if (r3 != r4) goto L_0x01d6
            java.lang.String r3 = java.lang.Integer.toString(r32)
            r9 = 0
            r11 = -1
            r21 = -1
            r22 = 0
            r23 = 0
            r4 = r8
            r24 = r5
            r5 = r9
            r9 = r6
            r6 = r11
            r11 = r7
            r7 = r21
            r25 = r8
            r8 = r17
            r26 = r9
            r9 = r18
            r21 = r10
            r10 = r22
            r27 = r11
            r20 = 1
            r11 = r21
            r22 = 2
            r12 = r23
            r1 = 0
            r13 = r33
            androidx.media2.exoplayer.external.Format r3 = androidx.media2.exoplayer.external.Format.createAudioSampleFormat(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r15.format = r3
            r6 = r24
            r7 = r26
            goto L_0x0169
        L_0x01d6:
            r24 = r5
            r26 = r6
            r27 = r7
            r25 = r8
            r21 = r10
            r1 = 0
            r5 = 1634492771(0x616c6163, float:2.7252807E20)
            r20 = 1
            r22 = 2
            if (r3 != r5) goto L_0x01f9
            r6 = r24
            byte[] r3 = new byte[r6]
            r7 = r26
            r0.setPosition(r7)
            r0.readBytes(r3, r1, r6)
            r19 = r3
            goto L_0x022f
        L_0x01f9:
            r6 = r24
            r7 = r26
            r4 = 1682927731(0x644f7073, float:1.5306315E22)
            if (r3 != r4) goto L_0x021c
            int r3 = r6 + -8
            byte[] r4 = opusMagic
            int r8 = r4.length
            int r8 = r8 + r3
            byte[] r8 = new byte[r8]
            int r9 = r4.length
            java.lang.System.arraycopy(r4, r1, r8, r1, r9)
            int r4 = r7 + 8
            r0.setPosition(r4)
            byte[] r4 = opusMagic
            int r4 = r4.length
            r0.readBytes(r8, r4, r3)
            r19 = r8
            goto L_0x022f
        L_0x021c:
            r3 = 1684425825(0x64664c61, float:1.6993019E22)
            if (r6 != r3) goto L_0x022f
            int r3 = r6 + -12
            byte[] r4 = new byte[r3]
            int r8 = r7 + 12
            r0.setPosition(r8)
            r0.readBytes(r4, r1, r3)
            r19 = r4
        L_0x022f:
            r8 = r25
            goto L_0x0277
        L_0x0232:
            r27 = r7
            r25 = r8
            r21 = r10
            r1 = 0
            r20 = 1
            r22 = 2
            r7 = r6
            r6 = r5
            r5 = 1634492771(0x616c6163, float:2.7252807E20)
            if (r3 != r9) goto L_0x0246
            r3 = r7
            goto L_0x024a
        L_0x0246:
            int r3 = findEsdsPosition(r0, r7, r6)
        L_0x024a:
            if (r3 == r4) goto L_0x022f
            android.util.Pair r3 = parseEsdsFromParent(r0, r3)
            java.lang.Object r4 = r3.first
            r8 = r4
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r3 = r3.second
            r19 = r3
            byte[] r19 = (byte[]) r19
            java.lang.String r3 = "audio/mp4a-latm"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x0277
            android.util.Pair r3 = androidx.media2.exoplayer.external.util.CodecSpecificDataUtil.parseAacAudioSpecificConfig(r19)
            java.lang.Object r4 = r3.first
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r18 = r4.intValue()
            java.lang.Object r3 = r3.second
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r17 = r3.intValue()
        L_0x0277:
            int r6 = r6 + r7
            r1 = r30
            r10 = r21
            r7 = r27
            r9 = 1634492771(0x616c6163, float:2.7252807E20)
            r11 = 1
            r12 = 2
            r13 = 0
            goto L_0x0120
        L_0x0286:
            r27 = r7
            r25 = r8
            r21 = r10
            r22 = 2
            androidx.media2.exoplayer.external.Format r0 = r15.format
            if (r0 != 0) goto L_0x02c3
            r8 = r25
            if (r8 == 0) goto L_0x02c3
            r0 = r27
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x02a0
            r7 = 2
            goto L_0x02a1
        L_0x02a0:
            r7 = -1
        L_0x02a1:
            java.lang.String r0 = java.lang.Integer.toString(r32)
            r2 = 0
            r3 = -1
            r4 = -1
            if (r19 != 0) goto L_0x02ab
            goto L_0x02b1
        L_0x02ab:
            java.util.List r1 = java.util.Collections.singletonList(r19)
            r16 = r1
        L_0x02b1:
            r10 = 0
            r1 = r8
            r5 = r17
            r6 = r18
            r8 = r16
            r9 = r21
            r11 = r33
            androidx.media2.exoplayer.external.Format r0 = androidx.media2.exoplayer.external.Format.createAudioSampleFormat(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r15.format = r0
        L_0x02c3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.extractor.mp4.AtomParsers.parseAudioSampleEntry(androidx.media2.exoplayer.external.util.ParsableByteArray, int, int, int, int, java.lang.String, boolean, androidx.media2.exoplayer.external.drm.DrmInitData, androidx.media2.exoplayer.external.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == 1702061171) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if ("audio/mpeg".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts.hd".equals(mimeTypeFromMp4ObjectType)) {
            return Pair.create(mimeTypeFromMp4ObjectType, (Object) null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        return Pair.create(mimeTypeFromMp4ObjectType, bArr);
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) {
        Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == 1936289382 && (parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt)) != null) {
                return parseCommonEncryptionSinfFromParent;
            }
            position += readInt;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        boolean z = false;
        String str = null;
        Integer num = null;
        int i4 = -1;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1718775137) {
                num = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == 1935894637) {
                parsableByteArray.skipBytes(4);
                str = parsableByteArray.readString(4);
            } else if (readInt2 == 1935894633) {
                i4 = i3;
                i5 = readInt;
            }
            i3 += readInt;
        }
        if (!"cenc".equals(str) && !"cbc1".equals(str) && !"cens".equals(str) && !"cbcs".equals(str)) {
            return null;
        }
        Assertions.checkArgument(num != null, "frma atom is mandatory");
        Assertions.checkArgument(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, str);
        if (parseSchiFromParent != null) {
            z = true;
        }
        Assertions.checkArgument(z, "tenc atom is mandatory");
        return Pair.create(num, parseSchiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1952804451) {
                int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (parseFullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = readUnsignedByte & 15;
                    i4 = (readUnsignedByte & 240) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z && readUnsignedByte2 == 0) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[readUnsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, readUnsignedByte3);
                }
                return new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += readInt;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1886547818) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, readInt + i3);
            }
            i3 += readInt;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        int constrainValue = Util.constrainValue(4, 0, length);
        int constrainValue2 = Util.constrainValue(jArr.length - 4, 0, length);
        if (jArr[0] > j2 || j2 >= jArr[constrainValue] || jArr[constrainValue2] >= j3 || j3 > j) {
            return false;
        }
        return true;
    }

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() != 1 ? false : true, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long j;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                j = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                j = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = j;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private static final class TkhdData {
        /* access modifiers changed from: private */
        public final long duration;
        /* access modifiers changed from: private */
        public final int id;
        /* access modifiers changed from: private */
        public final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    private static final class StsdData {
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize = this.data.readUnsignedIntToInt();
        private final int sampleCount = this.data.readUnsignedIntToInt();

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == 0 ? this.data.readUnsignedIntToInt() : i;
        }

        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize = (this.data.readUnsignedIntToInt() & 255);
        private final int sampleCount = this.data.readUnsignedIntToInt();
        private int sampleIndex;

        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 != 0) {
                return this.currentByte & 15;
            }
            int readUnsignedByte = this.data.readUnsignedByte();
            this.currentByte = readUnsignedByte;
            return (readUnsignedByte & 240) >> 4;
        }
    }
}
