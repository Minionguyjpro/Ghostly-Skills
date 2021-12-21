package androidx.media2.widget;

import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.accessibility.CaptioningManager;
import java.util.ArrayList;
import java.util.Arrays;

class Cea608CCParser {
    private static final boolean DEBUG = Log.isLoggable("Cea608CCParser", 3);
    private CCMemory mDisplay = new CCMemory();
    private final DisplayListener mListener;
    private int mMode = 1;
    private CCMemory mNonDisplay = new CCMemory();
    private int mPrevCtrlCode = -1;
    private int mRollUpSize = 4;
    private CCMemory mTextMem = new CCMemory();

    interface DisplayListener {
        CaptioningManager.CaptionStyle getCaptionStyle();

        void onDisplayChanged(SpannableStringBuilder[] spannableStringBuilderArr);
    }

    Cea608CCParser(DisplayListener displayListener) {
        this.mListener = displayListener;
    }

    public void parse(byte[] bArr) {
        CCData[] fromByteArray = CCData.fromByteArray(bArr);
        for (int i = 0; i < fromByteArray.length; i++) {
            if (DEBUG) {
                Log.d("Cea608CCParser", fromByteArray[i].toString());
            }
            if (!handleCtrlCode(fromByteArray[i]) && !handleTabOffsets(fromByteArray[i]) && !handlePACCode(fromByteArray[i]) && !handleMidRowCode(fromByteArray[i])) {
                handleDisplayableChars(fromByteArray[i]);
            }
        }
    }

    private CCMemory getMemory() {
        int i = this.mMode;
        if (i == 1 || i == 2) {
            return this.mDisplay;
        }
        if (i == 3) {
            return this.mNonDisplay;
        }
        if (i == 4) {
            return this.mTextMem;
        }
        Log.w("Cea608CCParser", "unrecoginized mode: " + this.mMode);
        return this.mDisplay;
    }

    private boolean handleDisplayableChars(CCData cCData) {
        if (!cCData.isDisplayableChar()) {
            return false;
        }
        if (cCData.isExtendedChar()) {
            getMemory().bs();
        }
        getMemory().writeText(cCData.getDisplayText());
        int i = this.mMode;
        if (i == 1 || i == 2) {
            updateDisplay();
        }
        return true;
    }

    private boolean handleMidRowCode(CCData cCData) {
        StyleCode midRow = cCData.getMidRow();
        if (midRow == null) {
            return false;
        }
        getMemory().writeMidRowCode(midRow);
        return true;
    }

    private boolean handlePACCode(CCData cCData) {
        PAC pac = cCData.getPAC();
        if (pac == null) {
            return false;
        }
        if (this.mMode == 2) {
            getMemory().moveBaselineTo(pac.getRow(), this.mRollUpSize);
        }
        getMemory().writePAC(pac);
        return true;
    }

    private boolean handleTabOffsets(CCData cCData) {
        int tabOffset = cCData.getTabOffset();
        if (tabOffset <= 0) {
            return false;
        }
        getMemory().tab(tabOffset);
        return true;
    }

    private boolean handleCtrlCode(CCData cCData) {
        int ctrlCode = cCData.getCtrlCode();
        int i = this.mPrevCtrlCode;
        if (i == -1 || i != ctrlCode) {
            switch (ctrlCode) {
                case 32:
                    this.mMode = 3;
                    break;
                case 33:
                    getMemory().bs();
                    break;
                case 36:
                    getMemory().der();
                    break;
                case 37:
                case 38:
                case 39:
                    this.mRollUpSize = ctrlCode - 35;
                    if (this.mMode != 2) {
                        this.mDisplay.erase();
                        this.mNonDisplay.erase();
                    }
                    this.mMode = 2;
                    break;
                case 40:
                    Log.i("Cea608CCParser", "Flash On");
                    break;
                case 41:
                    this.mMode = 1;
                    break;
                case 42:
                    this.mMode = 4;
                    this.mTextMem.erase();
                    break;
                case 43:
                    this.mMode = 4;
                    break;
                case 44:
                    this.mDisplay.erase();
                    updateDisplay();
                    break;
                case 45:
                    if (this.mMode == 2) {
                        getMemory().rollUp(this.mRollUpSize);
                    } else {
                        getMemory().cr();
                    }
                    if (this.mMode == 2) {
                        updateDisplay();
                        break;
                    }
                    break;
                case 46:
                    this.mNonDisplay.erase();
                    break;
                case 47:
                    swapMemory();
                    this.mMode = 3;
                    updateDisplay();
                    break;
                default:
                    this.mPrevCtrlCode = -1;
                    return false;
            }
            this.mPrevCtrlCode = ctrlCode;
            return true;
        }
        this.mPrevCtrlCode = -1;
        return true;
    }

    private void updateDisplay() {
        DisplayListener displayListener = this.mListener;
        if (displayListener != null) {
            this.mListener.onDisplayChanged(this.mDisplay.getStyledText(displayListener.getCaptionStyle()));
        }
    }

    private void swapMemory() {
        CCMemory cCMemory = this.mDisplay;
        this.mDisplay = this.mNonDisplay;
        this.mNonDisplay = cCMemory;
    }

    private static class StyleCode {
        static final String[] sColorMap = {"WHITE", "GREEN", "BLUE", "CYAN", "RED", "YELLOW", "MAGENTA", "INVALID"};
        final int mColor;
        final int mStyle;

        static StyleCode fromByte(byte b) {
            int i = (b >> 1) & 7;
            int i2 = (b & 1) != 0 ? 2 : 0;
            if (i == 7) {
                i2 |= 1;
                i = 0;
            }
            return new StyleCode(i2, i);
        }

        StyleCode(int i, int i2) {
            this.mStyle = i;
            this.mColor = i2;
        }

        /* access modifiers changed from: package-private */
        public boolean isItalics() {
            return (this.mStyle & 1) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isUnderline() {
            return (this.mStyle & 2) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append(sColorMap[this.mColor]);
            if ((this.mStyle & 1) != 0) {
                sb.append(", ITALICS");
            }
            if ((this.mStyle & 2) != 0) {
                sb.append(", UNDERLINE");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    private static class PAC extends StyleCode {
        final int mCol;
        final int mRow;

        static PAC fromBytes(byte b, byte b2) {
            int i = new int[]{11, 1, 3, 12, 14, 5, 7, 9}[b & 7] + ((b2 & 32) >> 5);
            int i2 = 0;
            int i3 = (b2 & 1) != 0 ? 2 : 0;
            if ((b2 & 16) != 0) {
                return new PAC(i, ((b2 >> 1) & 7) * 4, i3, 0);
            }
            int i4 = (b2 >> 1) & 7;
            if (i4 == 7) {
                i3 |= 1;
            } else {
                i2 = i4;
            }
            return new PAC(i, -1, i3, i2);
        }

        PAC(int i, int i2, int i3, int i4) {
            super(i3, i4);
            this.mRow = i;
            this.mCol = i2;
        }

        /* access modifiers changed from: package-private */
        public boolean isIndentPAC() {
            return this.mCol >= 0;
        }

        /* access modifiers changed from: package-private */
        public int getRow() {
            return this.mRow;
        }

        /* access modifiers changed from: package-private */
        public int getCol() {
            return this.mCol;
        }

        public String toString() {
            return String.format("{%d, %d}, %s", new Object[]{Integer.valueOf(this.mRow), Integer.valueOf(this.mCol), super.toString()});
        }
    }

    public static class MutableBackgroundColorSpan extends CharacterStyle implements UpdateAppearance {
        private int mColor;

        MutableBackgroundColorSpan(int i) {
            this.mColor = i;
        }

        public void setBackgroundColor(int i) {
            this.mColor = i;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.bgColor = this.mColor;
        }
    }

    private static class CCLineBuilder {
        private final StringBuilder mDisplayChars;
        private final StyleCode[] mMidRowStyles;
        private final StyleCode[] mPACStyles = new StyleCode[this.mDisplayChars.length()];

        CCLineBuilder(String str) {
            StringBuilder sb = new StringBuilder(str);
            this.mDisplayChars = sb;
            this.mMidRowStyles = new StyleCode[sb.length()];
        }

        /* access modifiers changed from: package-private */
        public void setCharAt(int i, char c) {
            this.mDisplayChars.setCharAt(i, c);
            this.mMidRowStyles[i] = null;
        }

        /* access modifiers changed from: package-private */
        public void setMidRowAt(int i, StyleCode styleCode) {
            this.mDisplayChars.setCharAt(i, ' ');
            this.mMidRowStyles[i] = styleCode;
        }

        /* access modifiers changed from: package-private */
        public void setPACAt(int i, PAC pac) {
            this.mPACStyles[i] = pac;
        }

        /* access modifiers changed from: package-private */
        public char charAt(int i) {
            return this.mDisplayChars.charAt(i);
        }

        /* access modifiers changed from: package-private */
        public int length() {
            return this.mDisplayChars.length();
        }

        /* access modifiers changed from: package-private */
        public void applyStyleSpan(SpannableStringBuilder spannableStringBuilder, StyleCode styleCode, int i, int i2) {
            if (styleCode.isItalics()) {
                spannableStringBuilder.setSpan(new StyleSpan(2), i, i2, 33);
            }
            if (styleCode.isUnderline()) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
            }
        }

        /* access modifiers changed from: package-private */
        public SpannableStringBuilder getStyledText(CaptioningManager.CaptionStyle captionStyle) {
            StyleCode styleCode;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.mDisplayChars);
            StyleCode styleCode2 = null;
            int i = -1;
            int i2 = -1;
            for (int i3 = 0; i3 < this.mDisplayChars.length(); i3++) {
                StyleCode[] styleCodeArr = this.mMidRowStyles;
                if (styleCodeArr[i3] != null) {
                    styleCode = styleCodeArr[i3];
                } else {
                    styleCode = (this.mPACStyles[i3] == null || (i >= 0 && i2 >= 0)) ? null : this.mPACStyles[i3];
                }
                if (styleCode != null) {
                    if (i >= 0 && i2 >= 0) {
                        applyStyleSpan(spannableStringBuilder, styleCode, i, i3);
                    }
                    i = i3;
                    styleCode2 = styleCode;
                }
                if (this.mDisplayChars.charAt(i3) != 160) {
                    if (i2 < 0) {
                        i2 = i3;
                    }
                } else if (i2 >= 0) {
                    if (this.mDisplayChars.charAt(i2) != ' ') {
                        i2--;
                    }
                    int i4 = this.mDisplayChars.charAt(i3 + -1) == ' ' ? i3 : i3 + 1;
                    spannableStringBuilder.setSpan(new MutableBackgroundColorSpan(captionStyle.backgroundColor), i2, i4, 33);
                    if (i >= 0) {
                        applyStyleSpan(spannableStringBuilder, styleCode2, i, i4);
                    }
                    i2 = -1;
                }
            }
            return spannableStringBuilder;
        }
    }

    private static class CCMemory {
        private final String mBlankLine;
        private int mCol;
        private final CCLineBuilder[] mLines = new CCLineBuilder[17];
        private int mRow;

        private static int clamp(int i, int i2, int i3) {
            return i < i2 ? i2 : i > i3 ? i3 : i;
        }

        CCMemory() {
            char[] cArr = new char[34];
            Arrays.fill(cArr, 160);
            this.mBlankLine = new String(cArr);
        }

        /* access modifiers changed from: package-private */
        public void erase() {
            int i = 0;
            while (true) {
                CCLineBuilder[] cCLineBuilderArr = this.mLines;
                if (i < cCLineBuilderArr.length) {
                    cCLineBuilderArr[i] = null;
                    i++;
                } else {
                    this.mRow = 15;
                    this.mCol = 1;
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void der() {
            if (this.mLines[this.mRow] != null) {
                for (int i = 0; i < this.mCol; i++) {
                    if (this.mLines[this.mRow].charAt(i) != 160) {
                        for (int i2 = this.mCol; i2 < this.mLines[this.mRow].length(); i2++) {
                            this.mLines[i2].setCharAt(i2, 160);
                        }
                        return;
                    }
                }
                this.mLines[this.mRow] = null;
            }
        }

        /* access modifiers changed from: package-private */
        public void tab(int i) {
            moveCursorByCol(i);
        }

        /* access modifiers changed from: package-private */
        public void bs() {
            moveCursorByCol(-1);
            CCLineBuilder[] cCLineBuilderArr = this.mLines;
            int i = this.mRow;
            if (cCLineBuilderArr[i] != null) {
                cCLineBuilderArr[i].setCharAt(this.mCol, 160);
                if (this.mCol == 31) {
                    this.mLines[this.mRow].setCharAt(32, 160);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void cr() {
            moveCursorTo(this.mRow + 1, 1);
        }

        /* access modifiers changed from: package-private */
        public void rollUp(int i) {
            int i2;
            int i3;
            int i4 = 0;
            while (true) {
                i2 = this.mRow;
                if (i4 > i2 - i) {
                    break;
                }
                this.mLines[i4] = null;
                i4++;
            }
            int i5 = (i2 - i) + 1;
            if (i5 < 1) {
                i5 = 1;
            }
            while (true) {
                i3 = this.mRow;
                if (i5 >= i3) {
                    break;
                }
                CCLineBuilder[] cCLineBuilderArr = this.mLines;
                int i6 = i5 + 1;
                cCLineBuilderArr[i5] = cCLineBuilderArr[i6];
                i5 = i6;
            }
            while (true) {
                CCLineBuilder[] cCLineBuilderArr2 = this.mLines;
                if (i3 < cCLineBuilderArr2.length) {
                    cCLineBuilderArr2[i3] = null;
                    i3++;
                } else {
                    this.mCol = 1;
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void writeText(String str) {
            for (int i = 0; i < str.length(); i++) {
                getLineBuffer(this.mRow).setCharAt(this.mCol, str.charAt(i));
                moveCursorByCol(1);
            }
        }

        /* access modifiers changed from: package-private */
        public void writeMidRowCode(StyleCode styleCode) {
            getLineBuffer(this.mRow).setMidRowAt(this.mCol, styleCode);
            moveCursorByCol(1);
        }

        /* access modifiers changed from: package-private */
        public void writePAC(PAC pac) {
            if (pac.isIndentPAC()) {
                moveCursorTo(pac.getRow(), pac.getCol());
            } else {
                moveCursorTo(pac.getRow(), 1);
            }
            getLineBuffer(this.mRow).setPACAt(this.mCol, pac);
        }

        /* access modifiers changed from: package-private */
        public SpannableStringBuilder[] getStyledText(CaptioningManager.CaptionStyle captionStyle) {
            ArrayList arrayList = new ArrayList(15);
            for (int i = 1; i <= 15; i++) {
                CCLineBuilder[] cCLineBuilderArr = this.mLines;
                arrayList.add(cCLineBuilderArr[i] != null ? cCLineBuilderArr[i].getStyledText(captionStyle) : null);
            }
            return (SpannableStringBuilder[]) arrayList.toArray(new SpannableStringBuilder[15]);
        }

        private void moveCursorTo(int i, int i2) {
            this.mRow = clamp(i, 1, 15);
            this.mCol = clamp(i2, 1, 32);
        }

        private void moveCursorByCol(int i) {
            this.mCol = clamp(this.mCol + i, 1, 32);
        }

        /* access modifiers changed from: package-private */
        public void moveBaselineTo(int i, int i2) {
            if (this.mRow != i) {
                int i3 = i < i2 ? i : i2;
                int i4 = this.mRow;
                if (i4 < i3) {
                    i3 = i4;
                }
                if (i < this.mRow) {
                    for (int i5 = i3 - 1; i5 >= 0; i5--) {
                        CCLineBuilder[] cCLineBuilderArr = this.mLines;
                        cCLineBuilderArr[i - i5] = cCLineBuilderArr[this.mRow - i5];
                    }
                } else {
                    for (int i6 = 0; i6 < i3; i6++) {
                        CCLineBuilder[] cCLineBuilderArr2 = this.mLines;
                        cCLineBuilderArr2[i - i6] = cCLineBuilderArr2[this.mRow - i6];
                    }
                }
                for (int i7 = 0; i7 <= i - i2; i7++) {
                    this.mLines[i7] = null;
                }
                while (true) {
                    i++;
                    CCLineBuilder[] cCLineBuilderArr3 = this.mLines;
                    if (i < cCLineBuilderArr3.length) {
                        cCLineBuilderArr3[i] = null;
                    } else {
                        return;
                    }
                }
            }
        }

        private CCLineBuilder getLineBuffer(int i) {
            CCLineBuilder[] cCLineBuilderArr = this.mLines;
            if (cCLineBuilderArr[i] == null) {
                cCLineBuilderArr[i] = new CCLineBuilder(this.mBlankLine);
            }
            return this.mLines[i];
        }
    }

    private static class CCData {
        private static final String[] sCtrlCodeMap = {"RCL", "BS", "AOF", "AON", "DER", "RU2", "RU3", "RU4", "FON", "RDC", "TR", "RTD", "EDM", "CR", "ENM", "EOC"};
        private static final String[] sProtugueseCharMap = {"Ã", "ã", "Í", "Ì", "ì", "Ò", "ò", "Õ", "õ", "{", "}", "\\", "^", "_", "|", "~", "Ä", "ä", "Ö", "ö", "ß", "¥", "¤", "│", "Å", "å", "Ø", "ø", "┌", "┐", "└", "┘"};
        private static final String[] sSpanishCharMap = {"Á", "É", "Ó", "Ú", "Ü", "ü", "‘", "¡", "*", "'", "—", "©", "℠", "•", "“", "”", "À", "Â", "Ç", "È", "Ê", "Ë", "ë", "Î", "Ï", "ï", "Ô", "Ù", "ù", "Û", "«", "»"};
        private static final String[] sSpecialCharMap = {"®", "°", "½", "¿", "™", "¢", "£", "♪", "à", " ", "è", "â", "ê", "î", "ô", "û"};
        private final byte mData1;
        private final byte mData2;
        private final byte mType;

        private char getBasicChar(byte b) {
            if (b == 42) {
                return 225;
            }
            if (b == 92) {
                return 233;
            }
            switch (b) {
                case 94:
                    return 237;
                case 95:
                    return 243;
                case 96:
                    return 250;
                default:
                    switch (b) {
                        case 123:
                            return 231;
                        case 124:
                            return 247;
                        case 125:
                            return 209;
                        case 126:
                            return 241;
                        case Byte.MAX_VALUE:
                            return 9608;
                        default:
                            return (char) b;
                    }
            }
        }

        static CCData[] fromByteArray(byte[] bArr) {
            int length = bArr.length / 3;
            CCData[] cCDataArr = new CCData[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 3;
                cCDataArr[i] = new CCData(bArr[i2], bArr[i2 + 1], bArr[i2 + 2]);
            }
            return cCDataArr;
        }

        CCData(byte b, byte b2, byte b3) {
            this.mType = b;
            this.mData1 = b2;
            this.mData2 = b3;
        }

        /* access modifiers changed from: package-private */
        public int getCtrlCode() {
            byte b;
            byte b2 = this.mData1;
            if ((b2 == 20 || b2 == 28) && (b = this.mData2) >= 32 && b <= 47) {
                return b;
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public StyleCode getMidRow() {
            byte b;
            byte b2 = this.mData1;
            if ((b2 == 17 || b2 == 25) && (b = this.mData2) >= 32 && b <= 47) {
                return StyleCode.fromByte(b);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public PAC getPAC() {
            byte b = this.mData1;
            if ((b & 112) != 16) {
                return null;
            }
            byte b2 = this.mData2;
            if ((b2 & 64) != 64) {
                return null;
            }
            if ((b & 7) != 0 || (b2 & 32) == 0) {
                return PAC.fromBytes(this.mData1, this.mData2);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public int getTabOffset() {
            byte b;
            byte b2 = this.mData1;
            if ((b2 == 23 || b2 == 31) && (b = this.mData2) >= 33 && b <= 35) {
                return b & 3;
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isDisplayableChar() {
            return isBasicChar() || isSpecialChar() || isExtendedChar();
        }

        /* access modifiers changed from: package-private */
        public String getDisplayText() {
            String basicChars = getBasicChars();
            if (basicChars != null) {
                return basicChars;
            }
            String specialChar = getSpecialChar();
            return specialChar == null ? getExtendedChar() : specialChar;
        }

        private String ctrlCodeToString(int i) {
            return sCtrlCodeMap[i - 32];
        }

        private boolean isBasicChar() {
            byte b = this.mData1;
            return b >= 32 && b <= Byte.MAX_VALUE;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
            r0 = r2.mData2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean isSpecialChar() {
            /*
                r2 = this;
                byte r0 = r2.mData1
                r1 = 17
                if (r0 == r1) goto L_0x000a
                r1 = 25
                if (r0 != r1) goto L_0x0016
            L_0x000a:
                byte r0 = r2.mData2
                r1 = 48
                if (r0 < r1) goto L_0x0016
                r1 = 63
                if (r0 > r1) goto L_0x0016
                r0 = 1
                goto L_0x0017
            L_0x0016:
                r0 = 0
            L_0x0017:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.widget.Cea608CCParser.CCData.isSpecialChar():boolean");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
            r0 = r2.mData2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isExtendedChar() {
            /*
                r2 = this;
                byte r0 = r2.mData1
                r1 = 18
                if (r0 == r1) goto L_0x0012
                r1 = 26
                if (r0 == r1) goto L_0x0012
                r1 = 19
                if (r0 == r1) goto L_0x0012
                r1 = 27
                if (r0 != r1) goto L_0x001e
            L_0x0012:
                byte r0 = r2.mData2
                r1 = 32
                if (r0 < r1) goto L_0x001e
                r1 = 63
                if (r0 > r1) goto L_0x001e
                r0 = 1
                goto L_0x001f
            L_0x001e:
                r0 = 0
            L_0x001f:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.widget.Cea608CCParser.CCData.isExtendedChar():boolean");
        }

        private String getBasicChars() {
            byte b = this.mData1;
            if (b < 32 || b > Byte.MAX_VALUE) {
                return null;
            }
            StringBuilder sb = new StringBuilder(2);
            sb.append(getBasicChar(this.mData1));
            byte b2 = this.mData2;
            if (b2 >= 32 && b2 <= Byte.MAX_VALUE) {
                sb.append(getBasicChar(b2));
            }
            return sb.toString();
        }

        private String getSpecialChar() {
            byte b;
            byte b2 = this.mData1;
            if ((b2 == 17 || b2 == 25) && (b = this.mData2) >= 48 && b <= 63) {
                return sSpecialCharMap[b - 48];
            }
            return null;
        }

        private String getExtendedChar() {
            byte b;
            byte b2;
            byte b3 = this.mData1;
            if ((b3 == 18 || b3 == 26) && (b2 = this.mData2) >= 32 && b2 <= 63) {
                return sSpanishCharMap[b2 - 32];
            }
            byte b4 = this.mData1;
            if ((b4 == 19 || b4 == 27) && (b = this.mData2) >= 32 && b <= 63) {
                return sProtugueseCharMap[b - 32];
            }
            return null;
        }

        public String toString() {
            if (this.mData1 >= 16 || this.mData2 >= 16) {
                int ctrlCode = getCtrlCode();
                if (ctrlCode != -1) {
                    return String.format("[%d]%s", new Object[]{Byte.valueOf(this.mType), ctrlCodeToString(ctrlCode)});
                }
                int tabOffset = getTabOffset();
                if (tabOffset > 0) {
                    return String.format("[%d]Tab%d", new Object[]{Byte.valueOf(this.mType), Integer.valueOf(tabOffset)});
                }
                PAC pac = getPAC();
                if (pac != null) {
                    return String.format("[%d]PAC: %s", new Object[]{Byte.valueOf(this.mType), pac.toString()});
                }
                StyleCode midRow = getMidRow();
                if (midRow != null) {
                    return String.format("[%d]Mid-row: %s", new Object[]{Byte.valueOf(this.mType), midRow.toString()});
                } else if (isDisplayableChar()) {
                    return String.format("[%d]Displayable: %s (%02x %02x)", new Object[]{Byte.valueOf(this.mType), getDisplayText(), Byte.valueOf(this.mData1), Byte.valueOf(this.mData2)});
                } else {
                    return String.format("[%d]Invalid: %02x %02x", new Object[]{Byte.valueOf(this.mType), Byte.valueOf(this.mData1), Byte.valueOf(this.mData2)});
                }
            } else {
                return String.format("[%d]Null: %02x %02x", new Object[]{Byte.valueOf(this.mType), Byte.valueOf(this.mData1), Byte.valueOf(this.mData2)});
            }
        }
    }
}
