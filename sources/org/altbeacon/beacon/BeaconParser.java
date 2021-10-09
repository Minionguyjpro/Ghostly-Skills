package org.altbeacon.beacon;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.bluetooth.BleAdvertisement;
import org.altbeacon.bluetooth.Pdu;

public class BeaconParser implements Serializable {
    private static final Pattern D_PATTERN = Pattern.compile("d\\:(\\d+)\\-(\\d+)([bl]*)?");
    private static final char[] HEX_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Pattern I_PATTERN = Pattern.compile("i\\:(\\d+)\\-(\\d+)([blv]*)?");
    private static final Pattern M_PATTERN = Pattern.compile("m\\:(\\d+)-(\\d+)\\=([0-9A-Fa-f]+)");
    private static final Pattern P_PATTERN = Pattern.compile("p\\:(\\d+)\\-(\\d+)\\:?([\\-\\d]+)?");
    private static final Pattern S_PATTERN = Pattern.compile("s\\:(\\d+)-(\\d+)\\=([0-9A-Fa-f]+)");
    private static final Pattern X_PATTERN = Pattern.compile(AvidJSONUtil.KEY_X);
    protected List<BeaconParser> extraParsers = new ArrayList();
    protected Boolean mAllowPduOverflow = true;
    protected String mBeaconLayout;
    protected Integer mDBmCorrection;
    protected final List<Integer> mDataEndOffsets = new ArrayList();
    protected final List<Boolean> mDataLittleEndianFlags = new ArrayList();
    protected final List<Integer> mDataStartOffsets = new ArrayList();
    protected Boolean mExtraFrame;
    protected int[] mHardwareAssistManufacturers = {76};
    protected String mIdentifier;
    protected final List<Integer> mIdentifierEndOffsets = new ArrayList();
    protected final List<Boolean> mIdentifierLittleEndianFlags = new ArrayList();
    protected final List<Integer> mIdentifierStartOffsets = new ArrayList();
    protected final List<Boolean> mIdentifierVariableLengthFlags = new ArrayList();
    protected Integer mLayoutSize;
    private Long mMatchingBeaconTypeCode;
    protected Integer mMatchingBeaconTypeCodeEndOffset;
    protected Integer mMatchingBeaconTypeCodeStartOffset;
    protected Integer mPowerEndOffset;
    protected Integer mPowerStartOffset;
    protected Long mServiceUuid;
    protected Integer mServiceUuidEndOffset;
    protected Integer mServiceUuidStartOffset;

    public BeaconParser setBeaconLayout(String str) {
        String str2 = str;
        this.mBeaconLayout = str2;
        Log.d("BeaconParser", "Parsing beacon layout: " + str2);
        String[] split = str2.split(",");
        this.mExtraFrame = false;
        int length = split.length;
        int i = 0;
        while (i < length) {
            String str3 = split[i];
            Matcher matcher = I_PATTERN.matcher(str3);
            boolean z = false;
            while (matcher.find()) {
                try {
                    int parseInt = Integer.parseInt(matcher.group(1));
                    int parseInt2 = Integer.parseInt(matcher.group(2));
                    this.mIdentifierLittleEndianFlags.add(Boolean.valueOf(matcher.group(3).contains("l")));
                    this.mIdentifierVariableLengthFlags.add(Boolean.valueOf(matcher.group(3).contains("v")));
                    this.mIdentifierStartOffsets.add(Integer.valueOf(parseInt));
                    this.mIdentifierEndOffsets.add(Integer.valueOf(parseInt2));
                    z = true;
                } catch (NumberFormatException unused) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + str3);
                }
            }
            Matcher matcher2 = D_PATTERN.matcher(str3);
            while (matcher2.find()) {
                try {
                    int parseInt3 = Integer.parseInt(matcher2.group(1));
                    int parseInt4 = Integer.parseInt(matcher2.group(2));
                    this.mDataLittleEndianFlags.add(Boolean.valueOf(matcher2.group(3).contains("l")));
                    this.mDataStartOffsets.add(Integer.valueOf(parseInt3));
                    this.mDataEndOffsets.add(Integer.valueOf(parseInt4));
                    z = true;
                } catch (NumberFormatException unused2) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + str3);
                }
            }
            Matcher matcher3 = P_PATTERN.matcher(str3);
            while (matcher3.find()) {
                try {
                    int parseInt5 = Integer.parseInt(matcher3.group(1));
                    int parseInt6 = Integer.parseInt(matcher3.group(2));
                    this.mDBmCorrection = Integer.valueOf(matcher3.group(3) != null ? Integer.parseInt(matcher3.group(3)) : 0);
                    this.mPowerStartOffset = Integer.valueOf(parseInt5);
                    this.mPowerEndOffset = Integer.valueOf(parseInt6);
                    z = true;
                } catch (NumberFormatException unused3) {
                    throw new BeaconLayoutException("Cannot parse integer power byte offset in term: " + str3);
                }
            }
            Matcher matcher4 = M_PATTERN.matcher(str3);
            while (matcher4.find()) {
                try {
                    int parseInt7 = Integer.parseInt(matcher4.group(1));
                    int parseInt8 = Integer.parseInt(matcher4.group(2));
                    this.mMatchingBeaconTypeCodeStartOffset = Integer.valueOf(parseInt7);
                    this.mMatchingBeaconTypeCodeEndOffset = Integer.valueOf(parseInt8);
                    String group = matcher4.group(3);
                    try {
                        this.mMatchingBeaconTypeCode = Long.decode("0x" + group);
                        z = true;
                    } catch (NumberFormatException unused4) {
                        throw new BeaconLayoutException("Cannot parse beacon type code: " + group + " in term: " + str3);
                    }
                } catch (NumberFormatException unused5) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + str3);
                }
            }
            Matcher matcher5 = S_PATTERN.matcher(str3);
            while (matcher5.find()) {
                try {
                    int parseInt9 = Integer.parseInt(matcher5.group(1));
                    int parseInt10 = Integer.parseInt(matcher5.group(2));
                    this.mServiceUuidStartOffset = Integer.valueOf(parseInt9);
                    this.mServiceUuidEndOffset = Integer.valueOf(parseInt10);
                    String group2 = matcher5.group(3);
                    try {
                        this.mServiceUuid = Long.decode("0x" + group2);
                        z = true;
                    } catch (NumberFormatException unused6) {
                        throw new BeaconLayoutException("Cannot parse serviceUuid: " + group2 + " in term: " + str3);
                    }
                } catch (NumberFormatException unused7) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + str3);
                }
            }
            Matcher matcher6 = X_PATTERN.matcher(str3);
            while (matcher6.find()) {
                this.mExtraFrame = true;
                z = true;
            }
            if (z) {
                i++;
            } else {
                LogManager.d("BeaconParser", "cannot parse term %s", str3);
                throw new BeaconLayoutException("Cannot parse beacon layout term: " + str3);
            }
        }
        if (!this.mExtraFrame.booleanValue()) {
            if (this.mIdentifierStartOffsets.size() == 0 || this.mIdentifierEndOffsets.size() == 0) {
                throw new BeaconLayoutException("You must supply at least one identifier offset with a prefix of 'i'");
            } else if (this.mPowerStartOffset == null || this.mPowerEndOffset == null) {
                throw new BeaconLayoutException("You must supply a power byte offset with a prefix of 'p'");
            }
        }
        if (this.mMatchingBeaconTypeCodeStartOffset == null || this.mMatchingBeaconTypeCodeEndOffset == null) {
            throw new BeaconLayoutException("You must supply a matching beacon type expression with a prefix of 'm'");
        }
        this.mLayoutSize = Integer.valueOf(calculateLayoutSize());
        return this;
    }

    public List<BeaconParser> getExtraDataParsers() {
        return new ArrayList(this.extraParsers);
    }

    public int[] getHardwareAssistManufacturers() {
        return this.mHardwareAssistManufacturers;
    }

    public Long getMatchingBeaconTypeCode() {
        return this.mMatchingBeaconTypeCode;
    }

    public int getMatchingBeaconTypeCodeStartOffset() {
        return this.mMatchingBeaconTypeCodeStartOffset.intValue();
    }

    public int getMatchingBeaconTypeCodeEndOffset() {
        return this.mMatchingBeaconTypeCodeEndOffset.intValue();
    }

    public Long getServiceUuid() {
        return this.mServiceUuid;
    }

    public Beacon fromScanData(byte[] bArr, int i, BluetoothDevice bluetoothDevice) {
        return fromScanData(bArr, i, bluetoothDevice, new Beacon());
    }

    /* access modifiers changed from: protected */
    public Beacon fromScanData(byte[] bArr, int i, BluetoothDevice bluetoothDevice, Beacon beacon) {
        int i2;
        Pdu pdu;
        int i3;
        boolean z;
        Beacon beacon2;
        String str;
        String str2;
        boolean z2;
        String str3;
        String str4;
        byte[] bArr2 = bArr;
        BleAdvertisement bleAdvertisement = new BleAdvertisement(bArr2);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<Pdu> it = bleAdvertisement.getPdus().iterator();
        while (true) {
            i2 = 0;
            if (!it.hasNext()) {
                pdu = null;
                break;
            }
            pdu = it.next();
            if (pdu.getType() != 22 && pdu.getType() != -1) {
                if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.d("BeaconParser", "Ignoring pdu type %02X", Byte.valueOf(pdu.getType()));
                }
            }
        }
        if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.d("BeaconParser", "Processing pdu type %02X: %s with startIndex: %d, endIndex: %d", Byte.valueOf(pdu.getType()), bytesToHex(bArr), Integer.valueOf(pdu.getStartIndex()), Integer.valueOf(pdu.getEndIndex()));
        }
        if (pdu == null) {
            if (LogManager.isVerboseLoggingEnabled()) {
                LogManager.d("BeaconParser", "No PDUs to process in this packet.", new Object[0]);
            }
            beacon2 = beacon;
            z = true;
            i3 = 0;
        } else {
            byte[] longToByteArray = longToByteArray(getMatchingBeaconTypeCode().longValue(), (this.mMatchingBeaconTypeCodeEndOffset.intValue() - this.mMatchingBeaconTypeCodeStartOffset.intValue()) + 1);
            byte[] longToByteArray2 = getServiceUuid() != null ? longToByteArray(getServiceUuid().longValue(), (this.mServiceUuidEndOffset.intValue() - this.mServiceUuidStartOffset.intValue()) + 1, false) : null;
            i3 = pdu.getStartIndex();
            if (getServiceUuid() != null ? !byteArraysMatch(bArr2, this.mServiceUuidStartOffset.intValue() + i3, longToByteArray2) || !byteArraysMatch(bArr2, this.mMatchingBeaconTypeCodeStartOffset.intValue() + i3, longToByteArray) : !byteArraysMatch(bArr2, this.mMatchingBeaconTypeCodeStartOffset.intValue() + i3, longToByteArray)) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (!z2) {
                if (getServiceUuid() == null) {
                    if (LogManager.isVerboseLoggingEnabled()) {
                        LogManager.d("BeaconParser", "This is not a matching Beacon advertisement. (Was expecting %s.  The bytes I see are: %s", byteArrayToString(longToByteArray), bytesToHex(bArr));
                    }
                } else if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.d("BeaconParser", "This is not a matching Beacon advertisement. Was expecting %s at offset %d and %s at offset %d.  The bytes I see are: %s", byteArrayToString(longToByteArray2), Integer.valueOf(this.mServiceUuidStartOffset.intValue() + i3), byteArrayToString(longToByteArray), Integer.valueOf(this.mMatchingBeaconTypeCodeStartOffset.intValue() + i3), bytesToHex(bArr));
                }
                beacon2 = null;
                z = true;
            } else {
                if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.d("BeaconParser", "This is a recognized beacon advertisement -- %s seen", byteArrayToString(longToByteArray));
                    LogManager.d("BeaconParser", "Bytes are: %s", bytesToHex(bArr));
                }
                beacon2 = beacon;
                z = false;
            }
            if (z2) {
                if (bArr2.length <= this.mLayoutSize.intValue() + i3 && this.mAllowPduOverflow.booleanValue()) {
                    if (LogManager.isVerboseLoggingEnabled()) {
                        LogManager.d("BeaconParser", "Expanding buffer because it is too short to parse: " + bArr2.length + ", needed: " + (this.mLayoutSize.intValue() + i3), new Object[0]);
                    }
                    bArr2 = ensureMaxSize(bArr2, this.mLayoutSize.intValue() + i3);
                }
                int i4 = 0;
                while (true) {
                    str3 = " PDU endIndex: ";
                    if (i4 >= this.mIdentifierEndOffsets.size()) {
                        break;
                    }
                    int intValue = this.mIdentifierEndOffsets.get(i4).intValue() + i3;
                    if (intValue > pdu.getEndIndex() && this.mIdentifierVariableLengthFlags.get(i4).booleanValue()) {
                        if (LogManager.isVerboseLoggingEnabled()) {
                            LogManager.d("BeaconParser", "Need to truncate identifier by " + (intValue - pdu.getEndIndex()), new Object[0]);
                        }
                        arrayList.add(Identifier.fromBytes(bArr2, this.mIdentifierStartOffsets.get(i4).intValue() + i3, pdu.getEndIndex() + 1, this.mIdentifierLittleEndianFlags.get(i4).booleanValue()));
                    } else if (intValue <= pdu.getEndIndex() || this.mAllowPduOverflow.booleanValue()) {
                        arrayList.add(Identifier.fromBytes(bArr2, this.mIdentifierStartOffsets.get(i4).intValue() + i3, intValue + 1, this.mIdentifierLittleEndianFlags.get(i4).booleanValue()));
                    } else {
                        if (LogManager.isVerboseLoggingEnabled()) {
                            LogManager.d("BeaconParser", "Cannot parse identifier " + i4 + " because PDU is too short.  endIndex: " + intValue + str3 + pdu.getEndIndex(), new Object[0]);
                        }
                        z = true;
                    }
                    i4++;
                }
                int i5 = 0;
                while (i5 < this.mDataEndOffsets.size()) {
                    int intValue2 = this.mDataEndOffsets.get(i5).intValue() + i3;
                    if (intValue2 <= pdu.getEndIndex() || this.mAllowPduOverflow.booleanValue()) {
                        str4 = str3;
                        arrayList2.add(Long.decode(byteArrayToFormattedString(bArr2, this.mDataStartOffsets.get(i5).intValue() + i3, intValue2, this.mDataLittleEndianFlags.get(i5).booleanValue())));
                    } else {
                        if (LogManager.isVerboseLoggingEnabled()) {
                            LogManager.d("BeaconParser", "Cannot parse data field " + i5 + " because PDU is too short.  endIndex: " + intValue2 + str3 + pdu.getEndIndex() + ".  Setting value to 0", new Object[i2]);
                        }
                        str4 = str3;
                        arrayList2.add(new Long(0));
                    }
                    i5++;
                    str3 = str4;
                    i2 = 0;
                }
                String str5 = str3;
                if (this.mPowerStartOffset != null) {
                    int intValue3 = this.mPowerEndOffset.intValue() + i3;
                    try {
                        if (intValue3 <= pdu.getEndIndex() || this.mAllowPduOverflow.booleanValue()) {
                            int parseInt = Integer.parseInt(byteArrayToFormattedString(bArr2, this.mPowerStartOffset.intValue() + i3, this.mPowerEndOffset.intValue() + i3, false)) + this.mDBmCorrection.intValue();
                            if (parseInt > 127) {
                                parseInt -= 256;
                            }
                            beacon2.mTxPower = parseInt;
                        } else {
                            try {
                                if (LogManager.isVerboseLoggingEnabled()) {
                                    LogManager.d("BeaconParser", "Cannot parse power field because PDU is too short.  endIndex: " + intValue3 + str5 + pdu.getEndIndex(), new Object[0]);
                                }
                            } catch (NullPointerException | NumberFormatException unused) {
                            }
                            z = true;
                        }
                    } catch (NullPointerException | NumberFormatException unused2) {
                    }
                }
            }
        }
        if (z) {
            return null;
        }
        int parseInt2 = Integer.parseInt(byteArrayToFormattedString(bArr2, this.mMatchingBeaconTypeCodeStartOffset.intValue() + i3, this.mMatchingBeaconTypeCodeEndOffset.intValue() + i3, false));
        int parseInt3 = Integer.parseInt(byteArrayToFormattedString(bArr2, i3, i3 + 1, true));
        if (bluetoothDevice != null) {
            str = bluetoothDevice.getAddress();
            str2 = bluetoothDevice.getName();
        } else {
            str2 = null;
            str = null;
        }
        beacon2.mIdentifiers = arrayList;
        beacon2.mDataFields = arrayList2;
        beacon2.mRssi = i;
        beacon2.mBeaconTypeCode = parseInt2;
        Long l = this.mServiceUuid;
        if (l != null) {
            beacon2.mServiceUuid = (int) l.longValue();
        } else {
            beacon2.mServiceUuid = -1;
        }
        beacon2.mBluetoothAddress = str;
        beacon2.mBluetoothName = str2;
        beacon2.mManufacturer = parseInt3;
        beacon2.mParserIdentifier = this.mIdentifier;
        beacon2.mMultiFrameBeacon = this.extraParsers.size() > 0 || this.mExtraFrame.booleanValue();
        return beacon2;
    }

    public String getLayout() {
        return this.mBeaconLayout;
    }

    protected static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i2] = cArr2[b >>> 4];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public static class BeaconLayoutException extends RuntimeException {
        public BeaconLayoutException(String str) {
            super(str);
        }
    }

    public static byte[] longToByteArray(long j, int i) {
        return longToByteArray(j, i, true);
    }

    public static byte[] longToByteArray(long j, int i, boolean z) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = ((i - (z ? i2 : (i - i2) - 1)) - 1) * 8;
            bArr[i2] = (byte) ((int) (((255 << i3) & j) >> ((int) ((long) i3))));
        }
        return bArr;
    }

    private int calculateLayoutSize() {
        List<Integer> list = this.mIdentifierEndOffsets;
        int i = 0;
        if (list != null) {
            for (Integer intValue : list) {
                int intValue2 = intValue.intValue();
                if (intValue2 > i) {
                    i = intValue2;
                }
            }
        }
        List<Integer> list2 = this.mDataEndOffsets;
        if (list2 != null) {
            for (Integer intValue3 : list2) {
                int intValue4 = intValue3.intValue();
                if (intValue4 > i) {
                    i = intValue4;
                }
            }
        }
        Integer num = this.mPowerEndOffset;
        if (num != null && num.intValue() > i) {
            i = this.mPowerEndOffset.intValue();
        }
        Integer num2 = this.mServiceUuidEndOffset;
        if (num2 != null && num2.intValue() > i) {
            i = this.mServiceUuidEndOffset.intValue();
        }
        return i + 1;
    }

    private boolean byteArraysMatch(byte[] bArr, int i, byte[] bArr2) {
        int length = bArr2.length;
        if (bArr.length - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (bArr[i + i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private String byteArrayToString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    private String byteArrayToFormattedString(byte[] bArr, int i, int i2, boolean z) {
        int i3 = i2 - i;
        int i4 = i3 + 1;
        byte[] bArr2 = new byte[i4];
        if (z) {
            for (int i5 = 0; i5 <= i3; i5++) {
                bArr2[i5] = bArr[((i + i4) - 1) - i5];
            }
        } else {
            for (int i6 = 0; i6 <= i3; i6++) {
                bArr2[i6] = bArr[i + i6];
            }
        }
        if (i4 < 5) {
            long j = 0;
            for (int i7 = 0; i7 < i4; i7++) {
                double d = (double) i7;
                Double.isNaN(d);
                j += ((long) (bArr2[(i4 - i7) - 1] & 255)) * ((long) Math.pow(256.0d, d * 1.0d));
            }
            return Long.toString(j);
        }
        String bytesToHex = bytesToHex(bArr2);
        if (i4 == 16) {
            return bytesToHex.substring(0, 8) + "-" + bytesToHex.substring(8, 12) + "-" + bytesToHex.substring(12, 16) + "-" + bytesToHex.substring(16, 20) + "-" + bytesToHex.substring(20, 32);
        }
        return "0x" + bytesToHex;
    }

    private byte[] ensureMaxSize(byte[] bArr, int i) {
        if (bArr.length >= i) {
            return bArr;
        }
        return Arrays.copyOf(bArr, i);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.mMatchingBeaconTypeCode, this.mIdentifierStartOffsets, this.mIdentifierEndOffsets, this.mIdentifierLittleEndianFlags, this.mDataStartOffsets, this.mDataEndOffsets, this.mDataLittleEndianFlags, this.mIdentifierVariableLengthFlags, this.mMatchingBeaconTypeCodeStartOffset, this.mMatchingBeaconTypeCodeEndOffset, this.mServiceUuidStartOffset, this.mServiceUuidEndOffset, this.mServiceUuid, this.mExtraFrame, this.mPowerStartOffset, this.mPowerEndOffset, this.mDBmCorrection, this.mLayoutSize, this.mAllowPduOverflow, this.mIdentifier, this.mHardwareAssistManufacturers, this.extraParsers});
    }

    public boolean equals(Object obj) {
        try {
            BeaconParser beaconParser = (BeaconParser) obj;
            return beaconParser.mBeaconLayout != null && beaconParser.mBeaconLayout.equals(this.mBeaconLayout) && beaconParser.mIdentifier != null && beaconParser.mIdentifier.equals(this.mIdentifier);
        } catch (ClassCastException unused) {
            return false;
        }
    }
}
