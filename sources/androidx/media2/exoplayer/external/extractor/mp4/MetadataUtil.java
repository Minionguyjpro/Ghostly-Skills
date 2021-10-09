package androidx.media2.exoplayer.external.extractor.mp4;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.extractor.GaplessInfoHolder;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.metadata.id3.ApicFrame;
import androidx.media2.exoplayer.external.metadata.id3.CommentFrame;
import androidx.media2.exoplayer.external.metadata.id3.Id3Frame;
import androidx.media2.exoplayer.external.metadata.id3.InternalFrame;
import androidx.media2.exoplayer.external.metadata.id3.TextInformationFrame;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.nio.ByteBuffer;

final class MetadataUtil {
    private static final String[] STANDARD_GENRES = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};

    public static Format getFormatWithMetadata(int i, Format format, Metadata metadata, Metadata metadata2, GaplessInfoHolder gaplessInfoHolder) {
        if (i == 1) {
            if (gaplessInfoHolder.hasGaplessInfo()) {
                format = format.copyWithGaplessInfo(gaplessInfoHolder.encoderDelay, gaplessInfoHolder.encoderPadding);
            }
            if (metadata != null) {
                return format.copyWithMetadata(metadata);
            }
            return format;
        } else if (i != 2 || metadata2 == null) {
            return format;
        } else {
            for (int i2 = 0; i2 < metadata2.length(); i2++) {
                Metadata.Entry entry = metadata2.get(i2);
                if (entry instanceof MdtaMetadataEntry) {
                    MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) entry;
                    if ("com.android.capture.fps".equals(mdtaMetadataEntry.key) && mdtaMetadataEntry.typeIndicator == 23) {
                        try {
                            format = format.copyWithFrameRate(ByteBuffer.wrap(mdtaMetadataEntry.value).asFloatBuffer().get()).copyWithMetadata(new Metadata(mdtaMetadataEntry));
                        } catch (NumberFormatException unused) {
                            Log.w("MetadataUtil", "Ignoring invalid framerate");
                        }
                    }
                }
            }
            return format;
        }
    }

    public static Metadata.Entry parseIlstElement(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition() + parsableByteArray.readInt();
        int readInt = parsableByteArray.readInt();
        int i = (readInt >> 24) & 255;
        if (i == 169 || i == 253) {
            int i2 = 16777215 & readInt;
            if (i2 == 6516084) {
                CommentFrame parseCommentAttribute = parseCommentAttribute(readInt, parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseCommentAttribute;
            } else if (i2 == 7233901 || i2 == 7631467) {
                TextInformationFrame parseTextAttribute = parseTextAttribute(readInt, "TIT2", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute;
            } else if (i2 == 6516589 || i2 == 7828084) {
                TextInformationFrame parseTextAttribute2 = parseTextAttribute(readInt, "TCOM", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute2;
            } else if (i2 == 6578553) {
                TextInformationFrame parseTextAttribute3 = parseTextAttribute(readInt, "TDRC", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute3;
            } else if (i2 == 4280916) {
                TextInformationFrame parseTextAttribute4 = parseTextAttribute(readInt, "TPE1", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute4;
            } else if (i2 == 7630703) {
                TextInformationFrame parseTextAttribute5 = parseTextAttribute(readInt, "TSSE", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute5;
            } else if (i2 == 6384738) {
                TextInformationFrame parseTextAttribute6 = parseTextAttribute(readInt, "TALB", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute6;
            } else if (i2 == 7108978) {
                TextInformationFrame parseTextAttribute7 = parseTextAttribute(readInt, "USLT", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute7;
            } else if (i2 == 6776174) {
                TextInformationFrame parseTextAttribute8 = parseTextAttribute(readInt, "TCON", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute8;
            } else if (i2 == 6779504) {
                TextInformationFrame parseTextAttribute9 = parseTextAttribute(readInt, "TIT1", parsableByteArray);
                parsableByteArray.setPosition(position);
                return parseTextAttribute9;
            }
        } else if (readInt == 1735291493) {
            try {
                return parseStandardGenreAttribute(parsableByteArray);
            } finally {
                parsableByteArray.setPosition(position);
            }
        } else if (readInt == 1684632427) {
            TextInformationFrame parseIndexAndCountAttribute = parseIndexAndCountAttribute(readInt, "TPOS", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseIndexAndCountAttribute;
        } else if (readInt == 1953655662) {
            TextInformationFrame parseIndexAndCountAttribute2 = parseIndexAndCountAttribute(readInt, "TRCK", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseIndexAndCountAttribute2;
        } else if (readInt == 1953329263) {
            Id3Frame parseUint8Attribute = parseUint8Attribute(readInt, "TBPM", parsableByteArray, true, false);
            parsableByteArray.setPosition(position);
            return parseUint8Attribute;
        } else if (readInt == 1668311404) {
            Id3Frame parseUint8Attribute2 = parseUint8Attribute(readInt, "TCMP", parsableByteArray, true, true);
            parsableByteArray.setPosition(position);
            return parseUint8Attribute2;
        } else if (readInt == 1668249202) {
            ApicFrame parseCoverArt = parseCoverArt(parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseCoverArt;
        } else if (readInt == 1631670868) {
            TextInformationFrame parseTextAttribute10 = parseTextAttribute(readInt, "TPE2", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseTextAttribute10;
        } else if (readInt == 1936682605) {
            TextInformationFrame parseTextAttribute11 = parseTextAttribute(readInt, "TSOT", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseTextAttribute11;
        } else if (readInt == 1936679276) {
            TextInformationFrame parseTextAttribute12 = parseTextAttribute(readInt, "TSO2", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseTextAttribute12;
        } else if (readInt == 1936679282) {
            TextInformationFrame parseTextAttribute13 = parseTextAttribute(readInt, "TSOA", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseTextAttribute13;
        } else if (readInt == 1936679265) {
            TextInformationFrame parseTextAttribute14 = parseTextAttribute(readInt, "TSOP", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseTextAttribute14;
        } else if (readInt == 1936679791) {
            TextInformationFrame parseTextAttribute15 = parseTextAttribute(readInt, "TSOC", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseTextAttribute15;
        } else if (readInt == 1920233063) {
            Id3Frame parseUint8Attribute3 = parseUint8Attribute(readInt, "ITUNESADVISORY", parsableByteArray, false, false);
            parsableByteArray.setPosition(position);
            return parseUint8Attribute3;
        } else if (readInt == 1885823344) {
            Id3Frame parseUint8Attribute4 = parseUint8Attribute(readInt, "ITUNESGAPLESS", parsableByteArray, false, true);
            parsableByteArray.setPosition(position);
            return parseUint8Attribute4;
        } else if (readInt == 1936683886) {
            TextInformationFrame parseTextAttribute16 = parseTextAttribute(readInt, "TVSHOWSORT", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseTextAttribute16;
        } else if (readInt == 1953919848) {
            TextInformationFrame parseTextAttribute17 = parseTextAttribute(readInt, "TVSHOW", parsableByteArray);
            parsableByteArray.setPosition(position);
            return parseTextAttribute17;
        } else if (readInt == 757935405) {
            Id3Frame parseInternalAttribute = parseInternalAttribute(parsableByteArray, position);
            parsableByteArray.setPosition(position);
            return parseInternalAttribute;
        }
        String valueOf = String.valueOf(Atom.getAtomTypeString(readInt));
        Log.d("MetadataUtil", valueOf.length() != 0 ? "Skipped unknown metadata entry: ".concat(valueOf) : new String("Skipped unknown metadata entry: "));
        parsableByteArray.setPosition(position);
        return null;
    }

    public static MdtaMetadataEntry parseMdtaMetadataEntryFromIlst(ParsableByteArray parsableByteArray, int i, String str) {
        while (true) {
            int position = parsableByteArray.getPosition();
            if (position >= i) {
                return null;
            }
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1684108385) {
                int readInt2 = parsableByteArray.readInt();
                int readInt3 = parsableByteArray.readInt();
                int i2 = readInt - 16;
                byte[] bArr = new byte[i2];
                parsableByteArray.readBytes(bArr, 0, i2);
                return new MdtaMetadataEntry(str, bArr, readInt3, readInt2);
            }
            parsableByteArray.setPosition(position + readInt);
        }
    }

    private static TextInformationFrame parseTextAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            return new TextInformationFrame(str, (String) null, parsableByteArray.readNullTerminatedString(readInt - 16));
        }
        String valueOf = String.valueOf(Atom.getAtomTypeString(i));
        Log.w("MetadataUtil", valueOf.length() != 0 ? "Failed to parse text attribute: ".concat(valueOf) : new String("Failed to parse text attribute: "));
        return null;
    }

    private static CommentFrame parseCommentAttribute(int i, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            String readNullTerminatedString = parsableByteArray.readNullTerminatedString(readInt - 16);
            return new CommentFrame("und", readNullTerminatedString, readNullTerminatedString);
        }
        String valueOf = String.valueOf(Atom.getAtomTypeString(i));
        Log.w("MetadataUtil", valueOf.length() != 0 ? "Failed to parse comment attribute: ".concat(valueOf) : new String("Failed to parse comment attribute: "));
        return null;
    }

    private static Id3Frame parseUint8Attribute(int i, String str, ParsableByteArray parsableByteArray, boolean z, boolean z2) {
        int parseUint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (z2) {
            parseUint8AttributeValue = Math.min(1, parseUint8AttributeValue);
        }
        if (parseUint8AttributeValue < 0) {
            String valueOf = String.valueOf(Atom.getAtomTypeString(i));
            Log.w("MetadataUtil", valueOf.length() != 0 ? "Failed to parse uint8 attribute: ".concat(valueOf) : new String("Failed to parse uint8 attribute: "));
            return null;
        } else if (z) {
            return new TextInformationFrame(str, (String) null, Integer.toString(parseUint8AttributeValue));
        } else {
            return new CommentFrame("und", str, Integer.toString(parseUint8AttributeValue));
        }
    }

    private static TextInformationFrame parseIndexAndCountAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385 && readInt >= 22) {
            parsableByteArray.skipBytes(10);
            int readUnsignedShort = parsableByteArray.readUnsignedShort();
            if (readUnsignedShort > 0) {
                StringBuilder sb = new StringBuilder(11);
                sb.append(readUnsignedShort);
                String sb2 = sb.toString();
                int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
                if (readUnsignedShort2 > 0) {
                    String valueOf = String.valueOf(sb2);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 12);
                    sb3.append(valueOf);
                    sb3.append("/");
                    sb3.append(readUnsignedShort2);
                    sb2 = sb3.toString();
                }
                return new TextInformationFrame(str, (String) null, sb2);
            }
        }
        String valueOf2 = String.valueOf(Atom.getAtomTypeString(i));
        Log.w("MetadataUtil", valueOf2.length() != 0 ? "Failed to parse index/count attribute: ".concat(valueOf2) : new String("Failed to parse index/count attribute: "));
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media2.exoplayer.external.metadata.id3.TextInformationFrame parseStandardGenreAttribute(androidx.media2.exoplayer.external.util.ParsableByteArray r3) {
        /*
            int r3 = parseUint8AttributeValue(r3)
            r0 = 0
            if (r3 <= 0) goto L_0x0011
            java.lang.String[] r1 = STANDARD_GENRES
            int r2 = r1.length
            if (r3 > r2) goto L_0x0011
            int r3 = r3 + -1
            r3 = r1[r3]
            goto L_0x0012
        L_0x0011:
            r3 = r0
        L_0x0012:
            if (r3 == 0) goto L_0x001c
            androidx.media2.exoplayer.external.metadata.id3.TextInformationFrame r1 = new androidx.media2.exoplayer.external.metadata.id3.TextInformationFrame
            java.lang.String r2 = "TCON"
            r1.<init>(r2, r0, r3)
            return r1
        L_0x001c:
            java.lang.String r3 = "MetadataUtil"
            java.lang.String r1 = "Failed to parse standard genre code"
            androidx.media2.exoplayer.external.util.Log.w(r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.extractor.mp4.MetadataUtil.parseStandardGenreAttribute(androidx.media2.exoplayer.external.util.ParsableByteArray):androidx.media2.exoplayer.external.metadata.id3.TextInformationFrame");
    }

    private static ApicFrame parseCoverArt(ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            int parseFullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
            String str = parseFullAtomFlags == 13 ? "image/jpeg" : parseFullAtomFlags == 14 ? "image/png" : null;
            if (str == null) {
                StringBuilder sb = new StringBuilder(41);
                sb.append("Unrecognized cover art flags: ");
                sb.append(parseFullAtomFlags);
                Log.w("MetadataUtil", sb.toString());
                return null;
            }
            parsableByteArray.skipBytes(4);
            int i = readInt - 16;
            byte[] bArr = new byte[i];
            parsableByteArray.readBytes(bArr, 0, i);
            return new ApicFrame(str, (String) null, 3, bArr);
        }
        Log.w("MetadataUtil", "Failed to parse cover art attribute");
        return null;
    }

    private static Id3Frame parseInternalAttribute(ParsableByteArray parsableByteArray, int i) {
        String str = null;
        String str2 = null;
        int i2 = -1;
        int i3 = -1;
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            if (readInt2 == 1835360622) {
                str = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else if (readInt2 == 1851878757) {
                str2 = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else {
                if (readInt2 == 1684108385) {
                    i2 = position;
                    i3 = readInt;
                }
                parsableByteArray.skipBytes(readInt - 12);
            }
        }
        if (str == null || str2 == null || i2 == -1) {
            return null;
        }
        parsableByteArray.setPosition(i2);
        parsableByteArray.skipBytes(16);
        return new InternalFrame(str, str2, parsableByteArray.readNullTerminatedString(i3 - 16));
    }

    private static int parseUint8AttributeValue(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            return parsableByteArray.readUnsignedByte();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }
}
