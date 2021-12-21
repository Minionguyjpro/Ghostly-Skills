package androidx.media2.widget;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class Cea708CCParser {
    private static final String MUSIC_NOTE_CHAR = new String("♫".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    private final StringBuilder mBuilder = new StringBuilder();
    private int mCommand = 0;
    private DisplayListener mListener = new DisplayListener() {
        public void emitEvent(CaptionEvent captionEvent) {
        }
    };

    interface DisplayListener {
        void emitEvent(CaptionEvent captionEvent);
    }

    private int parseG2(byte[] bArr, int i) {
        return i;
    }

    private int parseG3(byte[] bArr, int i) {
        return i;
    }

    Cea708CCParser(DisplayListener displayListener) {
        if (displayListener != null) {
            this.mListener = displayListener;
        }
    }

    private void emitCaptionEvent(CaptionEvent captionEvent) {
        emitCaptionBuffer();
        this.mListener.emitEvent(captionEvent);
    }

    private void emitCaptionBuffer() {
        if (this.mBuilder.length() > 0) {
            this.mListener.emitEvent(new CaptionEvent(1, this.mBuilder.toString()));
            this.mBuilder.setLength(0);
        }
    }

    public void parse(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            i = parseServiceBlockData(bArr, i);
        }
        emitCaptionBuffer();
    }

    private int parseServiceBlockData(byte[] bArr, int i) {
        byte b = bArr[i] & 255;
        this.mCommand = b;
        int i2 = i + 1;
        if (b == 16) {
            return parseExt1(bArr, i2);
        }
        if (b >= 0 && b <= 31) {
            return parseC0(bArr, i2);
        }
        int i3 = this.mCommand;
        if (i3 >= 128 && i3 <= 159) {
            return parseC1(bArr, i2);
        }
        int i4 = this.mCommand;
        if (i4 >= 32 && i4 <= 127) {
            return parseG0(bArr, i2);
        }
        int i5 = this.mCommand;
        return (i5 < 160 || i5 > 255) ? i2 : parseG1(bArr, i2);
    }

    private int parseC0(byte[] bArr, int i) {
        int i2 = this.mCommand;
        if (i2 < 24 || i2 > 31) {
            int i3 = this.mCommand;
            if (i3 >= 16 && i3 <= 23) {
                return i + 1;
            }
            int i4 = this.mCommand;
            if (i4 == 3) {
                emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) i4)));
                return i;
            } else if (i4 != 8) {
                switch (i4) {
                    case 12:
                        emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) i4)));
                        return i;
                    case 13:
                        this.mBuilder.append(10);
                        return i;
                    case 14:
                        emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) i4)));
                        return i;
                    default:
                        return i;
                }
            } else {
                emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) i4)));
                return i;
            }
        } else {
            if (i2 == 24) {
                try {
                    if (bArr[i] == 0) {
                        this.mBuilder.append((char) bArr[i + 1]);
                    } else {
                        this.mBuilder.append(new String(Arrays.copyOfRange(bArr, i, i + 2), "EUC-KR"));
                    }
                } catch (UnsupportedEncodingException e) {
                    Log.e("Cea708CCParser", "P16 Code - Could not find supported encoding", e);
                }
            }
            return i + 2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseC1(byte[] r28, int r29) {
        /*
            r27 = this;
            r0 = r27
            int r1 = r0.mCommand
            r2 = 0
            r3 = 8
            r4 = 15
            r5 = 5
            r6 = 7
            r7 = 6
            r8 = 0
            r9 = 12
            r10 = 4
            r11 = 3
            r12 = 1
            switch(r1) {
                case 128: goto L_0x023c;
                case 129: goto L_0x023c;
                case 130: goto L_0x023c;
                case 131: goto L_0x023c;
                case 132: goto L_0x023c;
                case 133: goto L_0x023c;
                case 134: goto L_0x023c;
                case 135: goto L_0x023c;
                case 136: goto L_0x0229;
                case 137: goto L_0x0216;
                case 138: goto L_0x0203;
                case 139: goto L_0x01f0;
                case 140: goto L_0x01dd;
                case 141: goto L_0x01c8;
                case 142: goto L_0x01bc;
                case 143: goto L_0x01b0;
                case 144: goto L_0x016e;
                case 145: goto L_0x0118;
                case 146: goto L_0x00fb;
                case 147: goto L_0x0015;
                case 148: goto L_0x0015;
                case 149: goto L_0x0015;
                case 150: goto L_0x0015;
                case 151: goto L_0x0085;
                case 152: goto L_0x0017;
                case 153: goto L_0x0017;
                case 154: goto L_0x0017;
                case 155: goto L_0x0017;
                case 156: goto L_0x0017;
                case 157: goto L_0x0017;
                case 158: goto L_0x0017;
                case 159: goto L_0x0017;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x024a
        L_0x0017:
            int r14 = r1 + -152
            byte r1 = r28[r29]
            r1 = r1 & 32
            if (r1 == 0) goto L_0x0021
            r15 = 1
            goto L_0x0022
        L_0x0021:
            r15 = 0
        L_0x0022:
            byte r1 = r28[r29]
            r2 = 16
            r1 = r1 & r2
            if (r1 == 0) goto L_0x002c
            r16 = 1
            goto L_0x002e
        L_0x002c:
            r16 = 0
        L_0x002e:
            byte r1 = r28[r29]
            r1 = r1 & r3
            if (r1 == 0) goto L_0x0036
            r17 = 1
            goto L_0x0038
        L_0x0036:
            r17 = 0
        L_0x0038:
            byte r1 = r28[r29]
            r18 = r1 & 7
            int r1 = r29 + 1
            byte r3 = r28[r1]
            r3 = r3 & 128(0x80, float:1.794E-43)
            if (r3 == 0) goto L_0x0047
            r19 = 1
            goto L_0x0049
        L_0x0047:
            r19 = 0
        L_0x0049:
            byte r1 = r28[r1]
            r20 = r1 & 127(0x7f, float:1.78E-43)
            int r1 = r29 + 2
            byte r1 = r28[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r3 = r29 + 3
            byte r5 = r28[r3]
            r5 = r5 & 240(0xf0, float:3.36E-43)
            int r22 = r5 >> 4
            byte r3 = r28[r3]
            r23 = r3 & 15
            int r3 = r29 + 4
            byte r3 = r28[r3]
            r24 = r3 & 63
            int r3 = r29 + 5
            byte r4 = r28[r3]
            r4 = r4 & 56
            int r26 = r4 >> 3
            byte r3 = r28[r3]
            r25 = r3 & 7
            int r3 = r29 + 6
            androidx.media2.widget.Cea708CCParser$CaptionEvent r4 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            androidx.media2.widget.Cea708CCParser$CaptionWindow r5 = new androidx.media2.widget.Cea708CCParser$CaptionWindow
            r13 = r5
            r21 = r1
            r13.<init>(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            r4.<init>(r2, r5)
            r0.emitCaptionEvent(r4)
            goto L_0x0115
        L_0x0085:
            byte r1 = r28[r29]
            r1 = r1 & 192(0xc0, float:2.69E-43)
            int r1 = r1 >> r7
            byte r2 = r28[r29]
            r2 = r2 & 48
            int r2 = r2 >> r10
            byte r3 = r28[r29]
            r3 = r3 & r9
            int r3 = r3 >> 2
            byte r6 = r28[r29]
            r6 = r6 & r11
            androidx.media2.widget.Cea708CCParser$CaptionColor r14 = new androidx.media2.widget.Cea708CCParser$CaptionColor
            r14.<init>(r1, r2, r3, r6)
            int r1 = r29 + 1
            byte r2 = r28[r1]
            r2 = r2 & 192(0xc0, float:2.69E-43)
            int r2 = r2 >> r7
            int r3 = r29 + 2
            byte r6 = r28[r3]
            r6 = r6 & 128(0x80, float:1.794E-43)
            int r5 = r6 >> 5
            r16 = r2 | r5
            byte r2 = r28[r1]
            r2 = r2 & 48
            int r2 = r2 >> r10
            byte r5 = r28[r1]
            r5 = r5 & r9
            int r5 = r5 >> 2
            byte r1 = r28[r1]
            r1 = r1 & r11
            androidx.media2.widget.Cea708CCParser$CaptionColor r15 = new androidx.media2.widget.Cea708CCParser$CaptionColor
            r15.<init>(r8, r2, r5, r1)
            byte r1 = r28[r3]
            r1 = r1 & 64
            if (r1 == 0) goto L_0x00c8
            r17 = 1
            goto L_0x00ca
        L_0x00c8:
            r17 = 0
        L_0x00ca:
            byte r1 = r28[r3]
            r1 = r1 & 48
            int r18 = r1 >> 4
            byte r1 = r28[r3]
            r1 = r1 & r9
            int r19 = r1 >> 2
            byte r1 = r28[r3]
            r20 = r1 & 3
            int r1 = r29 + 3
            byte r2 = r28[r1]
            r2 = r2 & 240(0xf0, float:3.36E-43)
            int r22 = r2 >> 4
            byte r2 = r28[r1]
            r2 = r2 & r9
            int r21 = r2 >> 2
            byte r1 = r28[r1]
            r23 = r1 & 3
            int r1 = r29 + 4
            androidx.media2.widget.Cea708CCParser$CaptionEvent r2 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            androidx.media2.widget.Cea708CCParser$CaptionWindowAttr r3 = new androidx.media2.widget.Cea708CCParser$CaptionWindowAttr
            r13 = r3
            r13.<init>(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            r2.<init>(r4, r3)
            r0.emitCaptionEvent(r2)
            goto L_0x016b
        L_0x00fb:
            byte r1 = r28[r29]
            r1 = r1 & r4
            int r2 = r29 + 1
            byte r2 = r28[r2]
            r2 = r2 & 63
            int r3 = r29 + 2
            androidx.media2.widget.Cea708CCParser$CaptionEvent r4 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            r5 = 14
            androidx.media2.widget.Cea708CCParser$CaptionPenLocation r6 = new androidx.media2.widget.Cea708CCParser$CaptionPenLocation
            r6.<init>(r1, r2)
            r4.<init>(r5, r6)
            r0.emitCaptionEvent(r4)
        L_0x0115:
            r2 = r3
            goto L_0x024c
        L_0x0118:
            byte r1 = r28[r29]
            r1 = r1 & 192(0xc0, float:2.69E-43)
            int r1 = r1 >> r7
            byte r2 = r28[r29]
            r2 = r2 & 48
            int r2 = r2 >> r10
            byte r3 = r28[r29]
            r3 = r3 & r9
            int r3 = r3 >> 2
            byte r4 = r28[r29]
            r4 = r4 & r11
            androidx.media2.widget.Cea708CCParser$CaptionColor r5 = new androidx.media2.widget.Cea708CCParser$CaptionColor
            r5.<init>(r1, r2, r3, r4)
            int r1 = r29 + 1
            byte r2 = r28[r1]
            r2 = r2 & 192(0xc0, float:2.69E-43)
            int r2 = r2 >> r7
            byte r3 = r28[r1]
            r3 = r3 & 48
            int r3 = r3 >> r10
            byte r4 = r28[r1]
            r4 = r4 & r9
            int r4 = r4 >> 2
            byte r6 = r28[r1]
            r6 = r6 & r11
            androidx.media2.widget.Cea708CCParser$CaptionColor r7 = new androidx.media2.widget.Cea708CCParser$CaptionColor
            r7.<init>(r2, r3, r4, r6)
            int r1 = r1 + r12
            byte r2 = r28[r1]
            r2 = r2 & 48
            int r2 = r2 >> r10
            byte r3 = r28[r1]
            r3 = r3 & r9
            int r3 = r3 >> 2
            byte r4 = r28[r1]
            r4 = r4 & r11
            androidx.media2.widget.Cea708CCParser$CaptionColor r6 = new androidx.media2.widget.Cea708CCParser$CaptionColor
            r6.<init>(r8, r2, r3, r4)
            int r1 = r1 + r12
            androidx.media2.widget.Cea708CCParser$CaptionEvent r2 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            r3 = 13
            androidx.media2.widget.Cea708CCParser$CaptionPenColor r4 = new androidx.media2.widget.Cea708CCParser$CaptionPenColor
            r4.<init>(r5, r7, r6)
            r2.<init>(r3, r4)
            r0.emitCaptionEvent(r2)
        L_0x016b:
            r2 = r1
            goto L_0x024c
        L_0x016e:
            byte r1 = r28[r29]
            r1 = r1 & 240(0xf0, float:3.36E-43)
            int r16 = r1 >> 4
            byte r1 = r28[r29]
            r14 = r1 & 3
            byte r1 = r28[r29]
            r1 = r1 & r9
            int r15 = r1 >> 2
            int r1 = r29 + 1
            byte r2 = r28[r1]
            r2 = r2 & 128(0x80, float:1.794E-43)
            if (r2 == 0) goto L_0x0188
            r20 = 1
            goto L_0x018a
        L_0x0188:
            r20 = 0
        L_0x018a:
            byte r2 = r28[r1]
            r2 = r2 & 64
            if (r2 == 0) goto L_0x0193
            r19 = 1
            goto L_0x0195
        L_0x0193:
            r19 = 0
        L_0x0195:
            byte r2 = r28[r1]
            r2 = r2 & 56
            int r18 = r2 >> 3
            byte r1 = r28[r1]
            r17 = r1 & 7
            int r1 = r29 + 2
            androidx.media2.widget.Cea708CCParser$CaptionEvent r2 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            androidx.media2.widget.Cea708CCParser$CaptionPenAttr r3 = new androidx.media2.widget.Cea708CCParser$CaptionPenAttr
            r13 = r3
            r13.<init>(r14, r15, r16, r17, r18, r19, r20)
            r2.<init>(r9, r3)
            r0.emitCaptionEvent(r2)
            goto L_0x016b
        L_0x01b0:
            androidx.media2.widget.Cea708CCParser$CaptionEvent r1 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            r3 = 11
            r1.<init>(r3, r2)
            r0.emitCaptionEvent(r1)
            goto L_0x024a
        L_0x01bc:
            androidx.media2.widget.Cea708CCParser$CaptionEvent r1 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            r3 = 10
            r1.<init>(r3, r2)
            r0.emitCaptionEvent(r1)
            goto L_0x024a
        L_0x01c8:
            byte r1 = r28[r29]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r2 = r29 + 1
            androidx.media2.widget.Cea708CCParser$CaptionEvent r3 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            r4 = 9
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.<init>(r4, r1)
            r0.emitCaptionEvent(r3)
            goto L_0x024c
        L_0x01dd:
            byte r1 = r28[r29]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r2 = r29 + 1
            androidx.media2.widget.Cea708CCParser$CaptionEvent r4 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r4.<init>(r3, r1)
            r0.emitCaptionEvent(r4)
            goto L_0x024c
        L_0x01f0:
            byte r1 = r28[r29]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r2 = r29 + 1
            androidx.media2.widget.Cea708CCParser$CaptionEvent r3 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.<init>(r6, r1)
            r0.emitCaptionEvent(r3)
            goto L_0x024c
        L_0x0203:
            byte r1 = r28[r29]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r2 = r29 + 1
            androidx.media2.widget.Cea708CCParser$CaptionEvent r3 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.<init>(r7, r1)
            r0.emitCaptionEvent(r3)
            goto L_0x024c
        L_0x0216:
            byte r1 = r28[r29]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r2 = r29 + 1
            androidx.media2.widget.Cea708CCParser$CaptionEvent r3 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.<init>(r5, r1)
            r0.emitCaptionEvent(r3)
            goto L_0x024c
        L_0x0229:
            byte r1 = r28[r29]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r2 = r29 + 1
            androidx.media2.widget.Cea708CCParser$CaptionEvent r3 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.<init>(r10, r1)
            r0.emitCaptionEvent(r3)
            goto L_0x024c
        L_0x023c:
            int r1 = r1 + -128
            androidx.media2.widget.Cea708CCParser$CaptionEvent r2 = new androidx.media2.widget.Cea708CCParser$CaptionEvent
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.<init>(r11, r1)
            r0.emitCaptionEvent(r2)
        L_0x024a:
            r2 = r29
        L_0x024c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.widget.Cea708CCParser.parseC1(byte[], int):int");
    }

    private int parseG0(byte[] bArr, int i) {
        int i2 = this.mCommand;
        if (i2 == 127) {
            this.mBuilder.append(MUSIC_NOTE_CHAR);
        } else {
            this.mBuilder.append((char) i2);
        }
        return i;
    }

    private int parseG1(byte[] bArr, int i) {
        this.mBuilder.append((char) this.mCommand);
        return i;
    }

    private int parseExt1(byte[] bArr, int i) {
        byte b = bArr[i] & 255;
        this.mCommand = b;
        int i2 = i + 1;
        if (b >= 0 && b <= 31) {
            return parseC2(bArr, i2);
        }
        int i3 = this.mCommand;
        if (i3 >= 128 && i3 <= 159) {
            return parseC3(bArr, i2);
        }
        int i4 = this.mCommand;
        if (i4 >= 32 && i4 <= 127) {
            return parseG2(bArr, i2);
        }
        int i5 = this.mCommand;
        return (i5 < 160 || i5 > 255) ? i2 : parseG3(bArr, i2);
    }

    private int parseC2(byte[] bArr, int i) {
        int i2 = this.mCommand;
        if (i2 >= 0 && i2 <= 7) {
            return i;
        }
        int i3 = this.mCommand;
        if (i3 >= 8 && i3 <= 15) {
            return i + 1;
        }
        int i4 = this.mCommand;
        if (i4 >= 16 && i4 <= 23) {
            return i + 2;
        }
        int i5 = this.mCommand;
        return (i5 < 24 || i5 > 31) ? i : i + 3;
    }

    private int parseC3(byte[] bArr, int i) {
        int i2 = this.mCommand;
        if (i2 >= 128 && i2 <= 135) {
            return i + 4;
        }
        int i3 = this.mCommand;
        return (i3 < 136 || i3 > 143) ? i : i + 5;
    }

    public static class CaptionColor {
        private static final int[] COLOR_MAP = {0, 15, 240, 255};
        private static final int[] OPACITY_MAP = {255, 254, 128, 0};
        public final int blue;
        public final int green;
        public final int opacity;
        public final int red;

        CaptionColor(int i, int i2, int i3, int i4) {
            this.opacity = i;
            this.red = i2;
            this.green = i3;
            this.blue = i4;
        }
    }

    public static class CaptionEvent {
        public final Object obj;
        public final int type;

        CaptionEvent(int i, Object obj2) {
            this.type = i;
            this.obj = obj2;
        }
    }

    public static class CaptionPenAttr {
        public final int edgeType;
        public final int fontTag;
        public final boolean italic;
        public final int penOffset;
        public final int penSize;
        public final int textTag;
        public final boolean underline;

        CaptionPenAttr(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            this.penSize = i;
            this.penOffset = i2;
            this.textTag = i3;
            this.fontTag = i4;
            this.edgeType = i5;
            this.underline = z;
            this.italic = z2;
        }
    }

    public static class CaptionPenColor {
        public final CaptionColor backgroundColor;
        public final CaptionColor edgeColor;
        public final CaptionColor foregroundColor;

        CaptionPenColor(CaptionColor captionColor, CaptionColor captionColor2, CaptionColor captionColor3) {
            this.foregroundColor = captionColor;
            this.backgroundColor = captionColor2;
            this.edgeColor = captionColor3;
        }
    }

    public static class CaptionPenLocation {
        public final int column;
        public final int row;

        CaptionPenLocation(int i, int i2) {
            this.row = i;
            this.column = i2;
        }
    }

    public static class CaptionWindowAttr {
        public final CaptionColor borderColor;
        public final int borderType;
        public final int displayEffect;
        public final int effectDirection;
        public final int effectSpeed;
        public final CaptionColor fillColor;
        public final int justify;
        public final int printDirection;
        public final int scrollDirection;
        public final boolean wordWrap;

        CaptionWindowAttr(CaptionColor captionColor, CaptionColor captionColor2, int i, boolean z, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.fillColor = captionColor;
            this.borderColor = captionColor2;
            this.borderType = i;
            this.wordWrap = z;
            this.printDirection = i2;
            this.scrollDirection = i3;
            this.justify = i4;
            this.effectDirection = i5;
            this.effectSpeed = i6;
            this.displayEffect = i7;
        }
    }

    public static class CaptionWindow {
        public final int anchorHorizontal;
        public final int anchorId;
        public final int anchorVertical;
        public final int columnCount;
        public final boolean columnLock;
        public final int id;
        public final int penStyle;
        public final int priority;
        public final boolean relativePositioning;
        public final int rowCount;
        public final boolean rowLock;
        public final boolean visible;
        public final int windowStyle;

        CaptionWindow(int i, boolean z, boolean z2, boolean z3, int i2, boolean z4, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.id = i;
            this.visible = z;
            this.rowLock = z2;
            this.columnLock = z3;
            this.priority = i2;
            this.relativePositioning = z4;
            this.anchorVertical = i3;
            this.anchorHorizontal = i4;
            this.anchorId = i5;
            this.rowCount = i6;
            this.columnCount = i7;
            this.penStyle = i8;
            this.windowStyle = i9;
        }
    }
}
