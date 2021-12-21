package androidx.media2.exoplayer.external.trackselection;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import androidx.media2.exoplayer.external.ExoPlaybackException;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.RendererConfiguration;
import androidx.media2.exoplayer.external.source.TrackGroup;
import androidx.media2.exoplayer.external.source.TrackGroupArray;
import androidx.media2.exoplayer.external.trackselection.AdaptiveTrackSelection;
import androidx.media2.exoplayer.external.trackselection.MappingTrackSelector;
import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.trackselection.TrackSelectionParameters;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultTrackSelector extends MappingTrackSelector {
    private static final int[] NO_TRACKS = new int[0];
    private boolean allowMultipleAdaptiveSelections;
    private final AtomicReference<Parameters> parametersReference;
    private final TrackSelection.Factory trackSelectionFactory;

    /* access modifiers changed from: private */
    public static int compareFormatValues(int i, int i2) {
        if (i == -1) {
            return i2 == -1 ? 0 : -1;
        }
        if (i2 == -1) {
            return 1;
        }
        return i - i2;
    }

    /* access modifiers changed from: private */
    public static int compareInts(int i, int i2) {
        if (i > i2) {
            return 1;
        }
        return i2 > i ? -1 : 0;
    }

    protected static boolean isSupported(int i, boolean z) {
        int i2 = i & 7;
        return i2 == 4 || (z && i2 == 3);
    }

    public static final class ParametersBuilder extends TrackSelectionParameters.Builder {
        private boolean allowAudioMixedMimeTypeAdaptiveness;
        private boolean allowAudioMixedSampleRateAdaptiveness;
        private boolean allowVideoMixedMimeTypeAdaptiveness;
        private boolean allowVideoNonSeamlessAdaptiveness;
        private boolean exceedAudioConstraintsIfNecessary;
        private boolean exceedRendererCapabilitiesIfNecessary;
        private boolean exceedVideoConstraintsIfNecessary;
        private boolean forceHighestSupportedBitrate;
        private boolean forceLowestBitrate;
        private int maxAudioBitrate;
        private int maxAudioChannelCount;
        private int maxVideoBitrate;
        private int maxVideoFrameRate;
        private int maxVideoHeight;
        private int maxVideoWidth;
        private final SparseBooleanArray rendererDisabledFlags;
        private final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
        private int tunnelingAudioSessionId;
        private int viewportHeight;
        private boolean viewportOrientationMayChange;
        private int viewportWidth;

        public ParametersBuilder() {
            this(Parameters.DEFAULT);
        }

        private ParametersBuilder(Parameters parameters) {
            super(parameters);
            this.maxVideoWidth = parameters.maxVideoWidth;
            this.maxVideoHeight = parameters.maxVideoHeight;
            this.maxVideoFrameRate = parameters.maxVideoFrameRate;
            this.maxVideoBitrate = parameters.maxVideoBitrate;
            this.exceedVideoConstraintsIfNecessary = parameters.exceedVideoConstraintsIfNecessary;
            this.allowVideoMixedMimeTypeAdaptiveness = parameters.allowVideoMixedMimeTypeAdaptiveness;
            this.allowVideoNonSeamlessAdaptiveness = parameters.allowVideoNonSeamlessAdaptiveness;
            this.viewportWidth = parameters.viewportWidth;
            this.viewportHeight = parameters.viewportHeight;
            this.viewportOrientationMayChange = parameters.viewportOrientationMayChange;
            this.maxAudioChannelCount = parameters.maxAudioChannelCount;
            this.maxAudioBitrate = parameters.maxAudioBitrate;
            this.exceedAudioConstraintsIfNecessary = parameters.exceedAudioConstraintsIfNecessary;
            this.allowAudioMixedMimeTypeAdaptiveness = parameters.allowAudioMixedMimeTypeAdaptiveness;
            this.allowAudioMixedSampleRateAdaptiveness = parameters.allowAudioMixedSampleRateAdaptiveness;
            this.forceLowestBitrate = parameters.forceLowestBitrate;
            this.forceHighestSupportedBitrate = parameters.forceHighestSupportedBitrate;
            this.exceedRendererCapabilitiesIfNecessary = parameters.exceedRendererCapabilitiesIfNecessary;
            this.tunnelingAudioSessionId = parameters.tunnelingAudioSessionId;
            this.selectionOverrides = cloneSelectionOverrides(parameters.selectionOverrides);
            this.rendererDisabledFlags = parameters.rendererDisabledFlags.clone();
        }

        public ParametersBuilder setSelectUndeterminedTextLanguage(boolean z) {
            super.setSelectUndeterminedTextLanguage(z);
            return this;
        }

        public final ParametersBuilder setRendererDisabled(int i, boolean z) {
            if (this.rendererDisabledFlags.get(i) == z) {
                return this;
            }
            if (z) {
                this.rendererDisabledFlags.put(i, true);
            } else {
                this.rendererDisabledFlags.delete(i);
            }
            return this;
        }

        public final ParametersBuilder setSelectionOverride(int i, TrackGroupArray trackGroupArray, SelectionOverride selectionOverride) {
            Map map = this.selectionOverrides.get(i);
            if (map == null) {
                map = new HashMap();
                this.selectionOverrides.put(i, map);
            }
            if (map.containsKey(trackGroupArray) && Util.areEqual(map.get(trackGroupArray), selectionOverride)) {
                return this;
            }
            map.put(trackGroupArray, selectionOverride);
            return this;
        }

        public final ParametersBuilder clearSelectionOverrides() {
            if (this.selectionOverrides.size() == 0) {
                return this;
            }
            this.selectionOverrides.clear();
            return this;
        }

        public Parameters build() {
            return new Parameters(this.maxVideoWidth, this.maxVideoHeight, this.maxVideoFrameRate, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, this.allowVideoMixedMimeTypeAdaptiveness, this.allowVideoNonSeamlessAdaptiveness, this.viewportWidth, this.viewportHeight, this.viewportOrientationMayChange, this.preferredAudioLanguage, this.maxAudioChannelCount, this.maxAudioBitrate, this.exceedAudioConstraintsIfNecessary, this.allowAudioMixedMimeTypeAdaptiveness, this.allowAudioMixedSampleRateAdaptiveness, this.preferredTextLanguage, this.selectUndeterminedTextLanguage, this.disabledTextTrackSelectionFlags, this.forceLowestBitrate, this.forceHighestSupportedBitrate, this.exceedRendererCapabilitiesIfNecessary, this.tunnelingAudioSessionId, this.selectionOverrides, this.rendererDisabledFlags);
        }

        private static SparseArray<Map<TrackGroupArray, SelectionOverride>> cloneSelectionOverrides(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray) {
            SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray2 = new SparseArray<>();
            for (int i = 0; i < sparseArray.size(); i++) {
                sparseArray2.put(sparseArray.keyAt(i), new HashMap(sparseArray.valueAt(i)));
            }
            return sparseArray2;
        }
    }

    public static final class Parameters extends TrackSelectionParameters {
        public static final Parcelable.Creator<Parameters> CREATOR = new Parcelable.Creator<Parameters>() {
            public Parameters createFromParcel(Parcel parcel) {
                return new Parameters(parcel);
            }

            public Parameters[] newArray(int i) {
                return new Parameters[i];
            }
        };
        public static final Parameters DEFAULT = new Parameters();
        public final boolean allowAudioMixedMimeTypeAdaptiveness;
        public final boolean allowAudioMixedSampleRateAdaptiveness;
        @Deprecated
        public final boolean allowMixedMimeAdaptiveness;
        @Deprecated
        public final boolean allowNonSeamlessAdaptiveness;
        public final boolean allowVideoMixedMimeTypeAdaptiveness;
        public final boolean allowVideoNonSeamlessAdaptiveness;
        public final boolean exceedAudioConstraintsIfNecessary;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        public final boolean forceHighestSupportedBitrate;
        public final boolean forceLowestBitrate;
        public final int maxAudioBitrate;
        public final int maxAudioChannelCount;
        public final int maxVideoBitrate;
        public final int maxVideoFrameRate;
        public final int maxVideoHeight;
        public final int maxVideoWidth;
        /* access modifiers changed from: private */
        public final SparseBooleanArray rendererDisabledFlags;
        /* access modifiers changed from: private */
        public final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
        public final int tunnelingAudioSessionId;
        public final int viewportHeight;
        public final boolean viewportOrientationMayChange;
        public final int viewportWidth;

        public int describeContents() {
            return 0;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Parameters() {
            /*
                r26 = this;
                r0 = r26
                androidx.media2.exoplayer.external.trackselection.TrackSelectionParameters r1 = androidx.media2.exoplayer.external.trackselection.TrackSelectionParameters.DEFAULT
                java.lang.String r11 = r1.preferredAudioLanguage
                androidx.media2.exoplayer.external.trackselection.TrackSelectionParameters r1 = androidx.media2.exoplayer.external.trackselection.TrackSelectionParameters.DEFAULT
                java.lang.String r1 = r1.preferredTextLanguage
                r17 = r1
                androidx.media2.exoplayer.external.trackselection.TrackSelectionParameters r1 = androidx.media2.exoplayer.external.trackselection.TrackSelectionParameters.DEFAULT
                boolean r1 = r1.selectUndeterminedTextLanguage
                r18 = r1
                androidx.media2.exoplayer.external.trackselection.TrackSelectionParameters r1 = androidx.media2.exoplayer.external.trackselection.TrackSelectionParameters.DEFAULT
                int r1 = r1.disabledTextTrackSelectionFlags
                r19 = r1
                android.util.SparseArray r1 = new android.util.SparseArray
                r24 = r1
                r1.<init>()
                android.util.SparseBooleanArray r1 = new android.util.SparseBooleanArray
                r25 = r1
                r1.<init>()
                r1 = 2147483647(0x7fffffff, float:NaN)
                r2 = 2147483647(0x7fffffff, float:NaN)
                r3 = 2147483647(0x7fffffff, float:NaN)
                r4 = 2147483647(0x7fffffff, float:NaN)
                r5 = 1
                r6 = 0
                r7 = 1
                r8 = 2147483647(0x7fffffff, float:NaN)
                r9 = 2147483647(0x7fffffff, float:NaN)
                r10 = 1
                r12 = 2147483647(0x7fffffff, float:NaN)
                r13 = 2147483647(0x7fffffff, float:NaN)
                r14 = 1
                r15 = 0
                r16 = 0
                r20 = 0
                r21 = 0
                r22 = 1
                r23 = 0
                r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.Parameters.<init>():void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        Parameters(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, int i5, int i6, boolean z4, String str, int i7, int i8, boolean z5, boolean z6, boolean z7, String str2, boolean z8, int i9, boolean z9, boolean z10, boolean z11, int i10, SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray, SparseBooleanArray sparseBooleanArray) {
            super(str, str2, z8, i9);
            boolean z12 = z2;
            boolean z13 = z3;
            this.maxVideoWidth = i;
            this.maxVideoHeight = i2;
            this.maxVideoFrameRate = i3;
            this.maxVideoBitrate = i4;
            this.exceedVideoConstraintsIfNecessary = z;
            this.allowVideoMixedMimeTypeAdaptiveness = z12;
            this.allowVideoNonSeamlessAdaptiveness = z13;
            this.viewportWidth = i5;
            this.viewportHeight = i6;
            this.viewportOrientationMayChange = z4;
            this.maxAudioChannelCount = i7;
            this.maxAudioBitrate = i8;
            this.exceedAudioConstraintsIfNecessary = z5;
            this.allowAudioMixedMimeTypeAdaptiveness = z6;
            this.allowAudioMixedSampleRateAdaptiveness = z7;
            this.forceLowestBitrate = z9;
            this.forceHighestSupportedBitrate = z10;
            this.exceedRendererCapabilitiesIfNecessary = z11;
            this.tunnelingAudioSessionId = i10;
            this.allowMixedMimeAdaptiveness = z12;
            this.allowNonSeamlessAdaptiveness = z13;
            this.selectionOverrides = sparseArray;
            this.rendererDisabledFlags = sparseBooleanArray;
        }

        Parameters(Parcel parcel) {
            super(parcel);
            this.maxVideoWidth = parcel.readInt();
            this.maxVideoHeight = parcel.readInt();
            this.maxVideoFrameRate = parcel.readInt();
            this.maxVideoBitrate = parcel.readInt();
            this.exceedVideoConstraintsIfNecessary = Util.readBoolean(parcel);
            this.allowVideoMixedMimeTypeAdaptiveness = Util.readBoolean(parcel);
            this.allowVideoNonSeamlessAdaptiveness = Util.readBoolean(parcel);
            this.viewportWidth = parcel.readInt();
            this.viewportHeight = parcel.readInt();
            this.viewportOrientationMayChange = Util.readBoolean(parcel);
            this.maxAudioChannelCount = parcel.readInt();
            this.maxAudioBitrate = parcel.readInt();
            this.exceedAudioConstraintsIfNecessary = Util.readBoolean(parcel);
            this.allowAudioMixedMimeTypeAdaptiveness = Util.readBoolean(parcel);
            this.allowAudioMixedSampleRateAdaptiveness = Util.readBoolean(parcel);
            this.forceLowestBitrate = Util.readBoolean(parcel);
            this.forceHighestSupportedBitrate = Util.readBoolean(parcel);
            this.exceedRendererCapabilitiesIfNecessary = Util.readBoolean(parcel);
            this.tunnelingAudioSessionId = parcel.readInt();
            this.selectionOverrides = readSelectionOverrides(parcel);
            this.rendererDisabledFlags = (SparseBooleanArray) Util.castNonNull(parcel.readSparseBooleanArray());
            this.allowMixedMimeAdaptiveness = this.allowVideoMixedMimeTypeAdaptiveness;
            this.allowNonSeamlessAdaptiveness = this.allowVideoNonSeamlessAdaptiveness;
        }

        public final boolean getRendererDisabled(int i) {
            return this.rendererDisabledFlags.get(i);
        }

        public final boolean hasSelectionOverride(int i, TrackGroupArray trackGroupArray) {
            Map map = this.selectionOverrides.get(i);
            return map != null && map.containsKey(trackGroupArray);
        }

        public final SelectionOverride getSelectionOverride(int i, TrackGroupArray trackGroupArray) {
            Map map = this.selectionOverrides.get(i);
            if (map != null) {
                return (SelectionOverride) map.get(trackGroupArray);
            }
            return null;
        }

        public ParametersBuilder buildUpon() {
            return new ParametersBuilder(this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Parameters parameters = (Parameters) obj;
            if (super.equals(obj) && this.maxVideoWidth == parameters.maxVideoWidth && this.maxVideoHeight == parameters.maxVideoHeight && this.maxVideoFrameRate == parameters.maxVideoFrameRate && this.maxVideoBitrate == parameters.maxVideoBitrate && this.exceedVideoConstraintsIfNecessary == parameters.exceedVideoConstraintsIfNecessary && this.allowVideoMixedMimeTypeAdaptiveness == parameters.allowVideoMixedMimeTypeAdaptiveness && this.allowVideoNonSeamlessAdaptiveness == parameters.allowVideoNonSeamlessAdaptiveness && this.viewportOrientationMayChange == parameters.viewportOrientationMayChange && this.viewportWidth == parameters.viewportWidth && this.viewportHeight == parameters.viewportHeight && this.maxAudioChannelCount == parameters.maxAudioChannelCount && this.maxAudioBitrate == parameters.maxAudioBitrate && this.exceedAudioConstraintsIfNecessary == parameters.exceedAudioConstraintsIfNecessary && this.allowAudioMixedMimeTypeAdaptiveness == parameters.allowAudioMixedMimeTypeAdaptiveness && this.allowAudioMixedSampleRateAdaptiveness == parameters.allowAudioMixedSampleRateAdaptiveness && this.forceLowestBitrate == parameters.forceLowestBitrate && this.forceHighestSupportedBitrate == parameters.forceHighestSupportedBitrate && this.exceedRendererCapabilitiesIfNecessary == parameters.exceedRendererCapabilitiesIfNecessary && this.tunnelingAudioSessionId == parameters.tunnelingAudioSessionId && areRendererDisabledFlagsEqual(this.rendererDisabledFlags, parameters.rendererDisabledFlags) && areSelectionOverridesEqual(this.selectionOverrides, parameters.selectionOverrides)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((((((((super.hashCode() * 31) + this.maxVideoWidth) * 31) + this.maxVideoHeight) * 31) + this.maxVideoFrameRate) * 31) + this.maxVideoBitrate) * 31) + (this.exceedVideoConstraintsIfNecessary ? 1 : 0)) * 31) + (this.allowVideoMixedMimeTypeAdaptiveness ? 1 : 0)) * 31) + (this.allowVideoNonSeamlessAdaptiveness ? 1 : 0)) * 31) + (this.viewportOrientationMayChange ? 1 : 0)) * 31) + this.viewportWidth) * 31) + this.viewportHeight) * 31) + this.maxAudioChannelCount) * 31) + this.maxAudioBitrate) * 31) + (this.exceedAudioConstraintsIfNecessary ? 1 : 0)) * 31) + (this.allowAudioMixedMimeTypeAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedSampleRateAdaptiveness ? 1 : 0)) * 31) + (this.forceLowestBitrate ? 1 : 0)) * 31) + (this.forceHighestSupportedBitrate ? 1 : 0)) * 31) + (this.exceedRendererCapabilitiesIfNecessary ? 1 : 0)) * 31) + this.tunnelingAudioSessionId;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.maxVideoWidth);
            parcel.writeInt(this.maxVideoHeight);
            parcel.writeInt(this.maxVideoFrameRate);
            parcel.writeInt(this.maxVideoBitrate);
            Util.writeBoolean(parcel, this.exceedVideoConstraintsIfNecessary);
            Util.writeBoolean(parcel, this.allowVideoMixedMimeTypeAdaptiveness);
            Util.writeBoolean(parcel, this.allowVideoNonSeamlessAdaptiveness);
            parcel.writeInt(this.viewportWidth);
            parcel.writeInt(this.viewportHeight);
            Util.writeBoolean(parcel, this.viewportOrientationMayChange);
            parcel.writeInt(this.maxAudioChannelCount);
            parcel.writeInt(this.maxAudioBitrate);
            Util.writeBoolean(parcel, this.exceedAudioConstraintsIfNecessary);
            Util.writeBoolean(parcel, this.allowAudioMixedMimeTypeAdaptiveness);
            Util.writeBoolean(parcel, this.allowAudioMixedSampleRateAdaptiveness);
            Util.writeBoolean(parcel, this.forceLowestBitrate);
            Util.writeBoolean(parcel, this.forceHighestSupportedBitrate);
            Util.writeBoolean(parcel, this.exceedRendererCapabilitiesIfNecessary);
            parcel.writeInt(this.tunnelingAudioSessionId);
            writeSelectionOverridesToParcel(parcel, this.selectionOverrides);
            parcel.writeSparseBooleanArray(this.rendererDisabledFlags);
        }

        private static SparseArray<Map<TrackGroupArray, SelectionOverride>> readSelectionOverrides(Parcel parcel) {
            int readInt = parcel.readInt();
            SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray = new SparseArray<>(readInt);
            for (int i = 0; i < readInt; i++) {
                int readInt2 = parcel.readInt();
                int readInt3 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt3);
                for (int i2 = 0; i2 < readInt3; i2++) {
                    hashMap.put((TrackGroupArray) parcel.readParcelable(TrackGroupArray.class.getClassLoader()), (SelectionOverride) parcel.readParcelable(SelectionOverride.class.getClassLoader()));
                }
                sparseArray.put(readInt2, hashMap);
            }
            return sparseArray;
        }

        private static void writeSelectionOverridesToParcel(Parcel parcel, SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray) {
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; i++) {
                int keyAt = sparseArray.keyAt(i);
                Map valueAt = sparseArray.valueAt(i);
                int size2 = valueAt.size();
                parcel.writeInt(keyAt);
                parcel.writeInt(size2);
                for (Map.Entry entry : valueAt.entrySet()) {
                    parcel.writeParcelable((Parcelable) entry.getKey(), 0);
                    parcel.writeParcelable((Parcelable) entry.getValue(), 0);
                }
            }
        }

        private static boolean areRendererDisabledFlagsEqual(SparseBooleanArray sparseBooleanArray, SparseBooleanArray sparseBooleanArray2) {
            int size = sparseBooleanArray.size();
            if (sparseBooleanArray2.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (sparseBooleanArray2.indexOfKey(sparseBooleanArray.keyAt(i)) < 0) {
                    return false;
                }
            }
            return true;
        }

        private static boolean areSelectionOverridesEqual(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray, SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray2) {
            int size = sparseArray.size();
            if (sparseArray2.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                int indexOfKey = sparseArray2.indexOfKey(sparseArray.keyAt(i));
                if (indexOfKey < 0 || !areSelectionOverridesEqual(sparseArray.valueAt(i), sparseArray2.valueAt(indexOfKey))) {
                    return false;
                }
            }
            return true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:6:0x001a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static boolean areSelectionOverridesEqual(java.util.Map<androidx.media2.exoplayer.external.source.TrackGroupArray, androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.SelectionOverride> r4, java.util.Map<androidx.media2.exoplayer.external.source.TrackGroupArray, androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.SelectionOverride> r5) {
            /*
                int r0 = r4.size()
                int r1 = r5.size()
                r2 = 0
                if (r1 == r0) goto L_0x000c
                return r2
            L_0x000c:
                java.util.Set r4 = r4.entrySet()
                java.util.Iterator r4 = r4.iterator()
            L_0x0014:
                boolean r0 = r4.hasNext()
                if (r0 == 0) goto L_0x003b
                java.lang.Object r0 = r4.next()
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0
                java.lang.Object r1 = r0.getKey()
                androidx.media2.exoplayer.external.source.TrackGroupArray r1 = (androidx.media2.exoplayer.external.source.TrackGroupArray) r1
                boolean r3 = r5.containsKey(r1)
                if (r3 == 0) goto L_0x003a
                java.lang.Object r0 = r0.getValue()
                java.lang.Object r1 = r5.get(r1)
                boolean r0 = androidx.media2.exoplayer.external.util.Util.areEqual(r0, r1)
                if (r0 != 0) goto L_0x0014
            L_0x003a:
                return r2
            L_0x003b:
                r4 = 1
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.Parameters.areSelectionOverridesEqual(java.util.Map, java.util.Map):boolean");
        }
    }

    public static final class SelectionOverride implements Parcelable {
        public static final Parcelable.Creator<SelectionOverride> CREATOR = new Parcelable.Creator<SelectionOverride>() {
            public SelectionOverride createFromParcel(Parcel parcel) {
                return new SelectionOverride(parcel);
            }

            public SelectionOverride[] newArray(int i) {
                return new SelectionOverride[i];
            }
        };
        public final int data;
        public final int groupIndex;
        public final int length;
        public final int reason;
        public final int[] tracks;

        public int describeContents() {
            return 0;
        }

        public SelectionOverride(int i, int... iArr) {
            this(i, iArr, 2, 0);
        }

        public SelectionOverride(int i, int[] iArr, int i2, int i3) {
            this.groupIndex = i;
            int[] copyOf = Arrays.copyOf(iArr, iArr.length);
            this.tracks = copyOf;
            this.length = iArr.length;
            this.reason = i2;
            this.data = i3;
            Arrays.sort(copyOf);
        }

        SelectionOverride(Parcel parcel) {
            this.groupIndex = parcel.readInt();
            int readByte = parcel.readByte();
            this.length = readByte;
            int[] iArr = new int[readByte];
            this.tracks = iArr;
            parcel.readIntArray(iArr);
            this.reason = parcel.readInt();
            this.data = parcel.readInt();
        }

        public int hashCode() {
            return (((((this.groupIndex * 31) + Arrays.hashCode(this.tracks)) * 31) + this.reason) * 31) + this.data;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SelectionOverride selectionOverride = (SelectionOverride) obj;
            if (this.groupIndex == selectionOverride.groupIndex && Arrays.equals(this.tracks, selectionOverride.tracks) && this.reason == selectionOverride.reason && this.data == selectionOverride.data) {
                return true;
            }
            return false;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.groupIndex);
            parcel.writeInt(this.tracks.length);
            parcel.writeIntArray(this.tracks);
            parcel.writeInt(this.reason);
            parcel.writeInt(this.data);
        }
    }

    public DefaultTrackSelector() {
        this(new AdaptiveTrackSelection.Factory());
    }

    public DefaultTrackSelector(TrackSelection.Factory factory) {
        this.trackSelectionFactory = factory;
        this.parametersReference = new AtomicReference<>(Parameters.DEFAULT);
    }

    public void setParameters(Parameters parameters) {
        Assertions.checkNotNull(parameters);
        if (!this.parametersReference.getAndSet(parameters).equals(parameters)) {
            invalidate();
        }
    }

    public void setParameters(ParametersBuilder parametersBuilder) {
        setParameters(parametersBuilder.build());
    }

    public Parameters getParameters() {
        return this.parametersReference.get();
    }

    public ParametersBuilder buildUponParameters() {
        return getParameters().buildUpon();
    }

    /* access modifiers changed from: protected */
    public final Pair<RendererConfiguration[], TrackSelection[]> selectTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2) throws ExoPlaybackException {
        Parameters parameters = this.parametersReference.get();
        int rendererCount = mappedTrackInfo.getRendererCount();
        TrackSelection.Definition[] selectAllTracks = selectAllTracks(mappedTrackInfo, iArr, iArr2, parameters);
        int i = 0;
        while (true) {
            TrackSelection.Definition definition = null;
            if (i >= rendererCount) {
                break;
            }
            if (parameters.getRendererDisabled(i)) {
                selectAllTracks[i] = null;
            } else {
                TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i);
                if (parameters.hasSelectionOverride(i, trackGroups)) {
                    SelectionOverride selectionOverride = parameters.getSelectionOverride(i, trackGroups);
                    if (selectionOverride != null) {
                        definition = new TrackSelection.Definition(trackGroups.get(selectionOverride.groupIndex), selectionOverride.tracks, selectionOverride.reason, Integer.valueOf(selectionOverride.data));
                    }
                    selectAllTracks[i] = definition;
                }
            }
            i++;
        }
        TrackSelection[] createTrackSelections = this.trackSelectionFactory.createTrackSelections(selectAllTracks, getBandwidthMeter());
        RendererConfiguration[] rendererConfigurationArr = new RendererConfiguration[rendererCount];
        for (int i2 = 0; i2 < rendererCount; i2++) {
            rendererConfigurationArr[i2] = !parameters.getRendererDisabled(i2) && (mappedTrackInfo.getRendererType(i2) == 6 || createTrackSelections[i2] != null) ? RendererConfiguration.DEFAULT : null;
        }
        maybeConfigureRenderersForTunneling(mappedTrackInfo, iArr, rendererConfigurationArr, createTrackSelections, parameters.tunnelingAudioSessionId);
        return Pair.create(rendererConfigurationArr, createTrackSelections);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector$AudioTrackScore} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media2.exoplayer.external.trackselection.TrackSelection.Definition[] selectAllTracks(androidx.media2.exoplayer.external.trackselection.MappingTrackSelector.MappedTrackInfo r22, int[][][] r23, int[] r24, androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.Parameters r25) throws androidx.media2.exoplayer.external.ExoPlaybackException {
        /*
            r21 = this;
            r6 = r21
            r7 = r22
            r8 = r25
            int r9 = r22.getRendererCount()
            androidx.media2.exoplayer.external.trackselection.TrackSelection$Definition[] r10 = new androidx.media2.exoplayer.external.trackselection.TrackSelection.Definition[r9]
            r11 = 0
            r0 = 0
            r12 = 0
            r13 = 0
        L_0x0010:
            r14 = 2
            r15 = 1
            if (r12 >= r9) goto L_0x0044
            int r1 = r7.getRendererType(r12)
            if (r14 != r1) goto L_0x0041
            if (r0 != 0) goto L_0x0036
            androidx.media2.exoplayer.external.source.TrackGroupArray r1 = r7.getTrackGroups(r12)
            r2 = r23[r12]
            r3 = r24[r12]
            r5 = 1
            r0 = r21
            r4 = r25
            androidx.media2.exoplayer.external.trackselection.TrackSelection$Definition r0 = r0.selectVideoTrack(r1, r2, r3, r4, r5)
            r10[r12] = r0
            r0 = r10[r12]
            if (r0 == 0) goto L_0x0035
            r0 = 1
            goto L_0x0036
        L_0x0035:
            r0 = 0
        L_0x0036:
            androidx.media2.exoplayer.external.source.TrackGroupArray r1 = r7.getTrackGroups(r12)
            int r1 = r1.length
            if (r1 <= 0) goto L_0x003f
            goto L_0x0040
        L_0x003f:
            r15 = 0
        L_0x0040:
            r13 = r13 | r15
        L_0x0041:
            int r12 = r12 + 1
            goto L_0x0010
        L_0x0044:
            r12 = -1
            r16 = 0
            r3 = r16
            r4 = r3
            r2 = -1
            r5 = 0
        L_0x004c:
            if (r5 >= r9) goto L_0x00b7
            int r0 = r7.getRendererType(r5)
            if (r15 != r0) goto L_0x00a8
            boolean r0 = r6.allowMultipleAdaptiveSelections
            if (r0 != 0) goto L_0x005e
            if (r13 != 0) goto L_0x005b
            goto L_0x005e
        L_0x005b:
            r17 = 0
            goto L_0x0060
        L_0x005e:
            r17 = 1
        L_0x0060:
            androidx.media2.exoplayer.external.source.TrackGroupArray r1 = r7.getTrackGroups(r5)
            r18 = r23[r5]
            r19 = r24[r5]
            r0 = r21
            r14 = r2
            r2 = r18
            r15 = r3
            r3 = r19
            r20 = r4
            r4 = r25
            r19 = r5
            r5 = r17
            android.util.Pair r0 = r0.selectAudioTrack(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x00ae
            if (r15 == 0) goto L_0x008a
            java.lang.Object r1 = r0.second
            androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector$AudioTrackScore r1 = (androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.AudioTrackScore) r1
            int r1 = r1.compareTo((androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.AudioTrackScore) r15)
            if (r1 <= 0) goto L_0x00ae
        L_0x008a:
            if (r14 == r12) goto L_0x008e
            r10[r14] = r16
        L_0x008e:
            java.lang.Object r1 = r0.first
            androidx.media2.exoplayer.external.trackselection.TrackSelection$Definition r1 = (androidx.media2.exoplayer.external.trackselection.TrackSelection.Definition) r1
            r10[r19] = r1
            androidx.media2.exoplayer.external.source.TrackGroup r2 = r1.group
            int[] r1 = r1.tracks
            r1 = r1[r11]
            androidx.media2.exoplayer.external.Format r1 = r2.getFormat(r1)
            java.lang.String r4 = r1.language
            java.lang.Object r0 = r0.second
            r3 = r0
            androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector$AudioTrackScore r3 = (androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.AudioTrackScore) r3
            r2 = r19
            goto L_0x00b2
        L_0x00a8:
            r14 = r2
            r15 = r3
            r20 = r4
            r19 = r5
        L_0x00ae:
            r2 = r14
            r3 = r15
            r4 = r20
        L_0x00b2:
            int r5 = r19 + 1
            r14 = 2
            r15 = 1
            goto L_0x004c
        L_0x00b7:
            r20 = r4
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = -1
        L_0x00bc:
            if (r11 >= r9) goto L_0x010f
            int r2 = r7.getRendererType(r11)
            r3 = 1
            if (r2 == r3) goto L_0x0107
            r4 = 2
            if (r2 == r4) goto L_0x0104
            r5 = 3
            if (r2 == r5) goto L_0x00d8
            androidx.media2.exoplayer.external.source.TrackGroupArray r5 = r7.getTrackGroups(r11)
            r13 = r23[r11]
            androidx.media2.exoplayer.external.trackselection.TrackSelection$Definition r2 = r6.selectOtherTrack(r2, r5, r13, r8)
            r10[r11] = r2
            goto L_0x0104
        L_0x00d8:
            androidx.media2.exoplayer.external.source.TrackGroupArray r2 = r7.getTrackGroups(r11)
            r5 = r23[r11]
            r13 = r20
            android.util.Pair r2 = r6.selectTextTrack(r2, r5, r8, r13)
            if (r2 == 0) goto L_0x010a
            java.lang.Object r5 = r2.second
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            if (r5 <= r0) goto L_0x010a
            if (r1 == r12) goto L_0x00f4
            r10[r1] = r16
        L_0x00f4:
            java.lang.Object r0 = r2.first
            androidx.media2.exoplayer.external.trackselection.TrackSelection$Definition r0 = (androidx.media2.exoplayer.external.trackselection.TrackSelection.Definition) r0
            r10[r11] = r0
            java.lang.Object r0 = r2.second
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r1 = r11
            goto L_0x010a
        L_0x0104:
            r13 = r20
            goto L_0x010a
        L_0x0107:
            r13 = r20
            r4 = 2
        L_0x010a:
            int r11 = r11 + 1
            r20 = r13
            goto L_0x00bc
        L_0x010f:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.selectAllTracks(androidx.media2.exoplayer.external.trackselection.MappingTrackSelector$MappedTrackInfo, int[][][], int[], androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector$Parameters):androidx.media2.exoplayer.external.trackselection.TrackSelection$Definition[]");
    }

    /* access modifiers changed from: protected */
    public TrackSelection.Definition selectVideoTrack(TrackGroupArray trackGroupArray, int[][] iArr, int i, Parameters parameters, boolean z) throws ExoPlaybackException {
        TrackSelection.Definition selectAdaptiveVideoTrack = (parameters.forceHighestSupportedBitrate || parameters.forceLowestBitrate || !z) ? null : selectAdaptiveVideoTrack(trackGroupArray, iArr, i, parameters);
        return selectAdaptiveVideoTrack == null ? selectFixedVideoTrack(trackGroupArray, iArr, parameters) : selectAdaptiveVideoTrack;
    }

    private static TrackSelection.Definition selectAdaptiveVideoTrack(TrackGroupArray trackGroupArray, int[][] iArr, int i, Parameters parameters) {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        int i2 = parameters2.allowVideoNonSeamlessAdaptiveness ? 24 : 16;
        boolean z = parameters2.allowVideoMixedMimeTypeAdaptiveness && (i & i2) != 0;
        int i3 = 0;
        while (i3 < trackGroupArray2.length) {
            TrackGroup trackGroup = trackGroupArray2.get(i3);
            TrackGroup trackGroup2 = trackGroup;
            int[] adaptiveVideoTracksForGroup = getAdaptiveVideoTracksForGroup(trackGroup, iArr[i3], z, i2, parameters2.maxVideoWidth, parameters2.maxVideoHeight, parameters2.maxVideoFrameRate, parameters2.maxVideoBitrate, parameters2.viewportWidth, parameters2.viewportHeight, parameters2.viewportOrientationMayChange);
            if (adaptiveVideoTracksForGroup.length > 0) {
                return new TrackSelection.Definition(trackGroup2, adaptiveVideoTracksForGroup);
            }
            i3++;
            trackGroupArray2 = trackGroupArray;
        }
        return null;
    }

    private static int[] getAdaptiveVideoTracksForGroup(TrackGroup trackGroup, int[] iArr, boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2) {
        String str;
        TrackGroup trackGroup2 = trackGroup;
        if (trackGroup2.length < 2) {
            return NO_TRACKS;
        }
        List<Integer> viewportFilteredTrackIndices = getViewportFilteredTrackIndices(trackGroup2, i6, i7, z2);
        if (viewportFilteredTrackIndices.size() < 2) {
            return NO_TRACKS;
        }
        if (!z) {
            HashSet hashSet = new HashSet();
            String str2 = null;
            int i8 = 0;
            for (int i9 = 0; i9 < viewportFilteredTrackIndices.size(); i9++) {
                String str3 = trackGroup2.getFormat(viewportFilteredTrackIndices.get(i9).intValue()).sampleMimeType;
                if (hashSet.add(str3)) {
                    String str4 = str3;
                    int adaptiveVideoTrackCountForMimeType = getAdaptiveVideoTrackCountForMimeType(trackGroup, iArr, i, str3, i2, i3, i4, i5, viewportFilteredTrackIndices);
                    if (adaptiveVideoTrackCountForMimeType > i8) {
                        i8 = adaptiveVideoTrackCountForMimeType;
                        str2 = str4;
                    }
                }
            }
            str = str2;
        } else {
            str = null;
        }
        filterAdaptiveVideoTrackCountForMimeType(trackGroup, iArr, i, str, i2, i3, i4, i5, viewportFilteredTrackIndices);
        return viewportFilteredTrackIndices.size() < 2 ? NO_TRACKS : Util.toArray(viewportFilteredTrackIndices);
    }

    private static int getAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, String str, int i2, int i3, int i4, int i5, List<Integer> list) {
        int i6 = 0;
        for (int i7 = 0; i7 < list.size(); i7++) {
            int intValue = list.get(i7).intValue();
            TrackGroup trackGroup2 = trackGroup;
            if (isSupportedAdaptiveVideoTrack(trackGroup.getFormat(intValue), str, iArr[intValue], i, i2, i3, i4, i5)) {
                i6++;
            }
        }
        return i6;
    }

    private static void filterAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, String str, int i2, int i3, int i4, int i5, List<Integer> list) {
        List<Integer> list2 = list;
        for (int size = list.size() - 1; size >= 0; size--) {
            int intValue = list2.get(size).intValue();
            TrackGroup trackGroup2 = trackGroup;
            if (!isSupportedAdaptiveVideoTrack(trackGroup.getFormat(intValue), str, iArr[intValue], i, i2, i3, i4, i5)) {
                list2.remove(size);
            }
        }
    }

    private static boolean isSupportedAdaptiveVideoTrack(Format format, String str, int i, int i2, int i3, int i4, int i5, int i6) {
        if (!isSupported(i, false) || (i & i2) == 0) {
            return false;
        }
        if (str != null && !Util.areEqual(format.sampleMimeType, str)) {
            return false;
        }
        if (format.width != -1 && format.width > i3) {
            return false;
        }
        if (format.height != -1 && format.height > i4) {
            return false;
        }
        if (format.frameRate != -1.0f && format.frameRate > ((float) i5)) {
            return false;
        }
        if (format.bitrate == -1 || format.bitrate <= i6) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009b, code lost:
        if (r0 < 0) goto L_0x009d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media2.exoplayer.external.trackselection.TrackSelection.Definition selectFixedVideoTrack(androidx.media2.exoplayer.external.source.TrackGroupArray r17, int[][] r18, androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.Parameters r19) {
        /*
            r0 = r17
            r1 = r19
            r3 = -1
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = -1
            r10 = -1
        L_0x000b:
            int r11 = r0.length
            if (r5 >= r11) goto L_0x00e0
            androidx.media2.exoplayer.external.source.TrackGroup r11 = r0.get(r5)
            int r13 = r1.viewportWidth
            int r14 = r1.viewportHeight
            boolean r15 = r1.viewportOrientationMayChange
            java.util.List r13 = getViewportFilteredTrackIndices(r11, r13, r14, r15)
            r14 = r18[r5]
            r15 = 0
        L_0x0020:
            int r2 = r11.length
            if (r15 >= r2) goto L_0x00d7
            r2 = r14[r15]
            boolean r12 = r1.exceedRendererCapabilitiesIfNecessary
            boolean r2 = isSupported(r2, r12)
            if (r2 == 0) goto L_0x00cc
            androidx.media2.exoplayer.external.Format r2 = r11.getFormat(r15)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r15)
            boolean r12 = r13.contains(r12)
            if (r12 == 0) goto L_0x006d
            int r12 = r2.width
            if (r12 == r3) goto L_0x0046
            int r12 = r2.width
            int r4 = r1.maxVideoWidth
            if (r12 > r4) goto L_0x006d
        L_0x0046:
            int r4 = r2.height
            if (r4 == r3) goto L_0x0050
            int r4 = r2.height
            int r12 = r1.maxVideoHeight
            if (r4 > r12) goto L_0x006d
        L_0x0050:
            float r4 = r2.frameRate
            r12 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r4 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r4 == 0) goto L_0x0061
            float r4 = r2.frameRate
            int r12 = r1.maxVideoFrameRate
            float r12 = (float) r12
            int r4 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r4 > 0) goto L_0x006d
        L_0x0061:
            int r4 = r2.bitrate
            if (r4 == r3) goto L_0x006b
            int r4 = r2.bitrate
            int r12 = r1.maxVideoBitrate
            if (r4 > r12) goto L_0x006d
        L_0x006b:
            r4 = 1
            goto L_0x006e
        L_0x006d:
            r4 = 0
        L_0x006e:
            if (r4 != 0) goto L_0x0076
            boolean r12 = r1.exceedVideoConstraintsIfNecessary
            if (r12 != 0) goto L_0x0076
            goto L_0x00cc
        L_0x0076:
            if (r4 == 0) goto L_0x007a
            r12 = 2
            goto L_0x007b
        L_0x007a:
            r12 = 1
        L_0x007b:
            r3 = r14[r15]
            r0 = 0
            boolean r3 = isSupported(r3, r0)
            if (r3 == 0) goto L_0x0086
            int r12 = r12 + 1000
        L_0x0086:
            if (r12 <= r8) goto L_0x008a
            r0 = 1
            goto L_0x008b
        L_0x008a:
            r0 = 0
        L_0x008b:
            if (r12 != r8) goto L_0x00bc
            int r0 = r2.bitrate
            int r0 = compareFormatValues(r0, r9)
            r16 = r6
            boolean r6 = r1.forceLowestBitrate
            if (r6 == 0) goto L_0x00a1
            if (r0 == 0) goto L_0x00a1
            if (r0 >= 0) goto L_0x009f
        L_0x009d:
            r0 = 1
            goto L_0x00be
        L_0x009f:
            r0 = 0
            goto L_0x00be
        L_0x00a1:
            int r0 = r2.getPixelCount()
            if (r0 == r10) goto L_0x00ac
            int r0 = compareFormatValues(r0, r10)
            goto L_0x00b2
        L_0x00ac:
            int r0 = r2.bitrate
            int r0 = compareFormatValues(r0, r9)
        L_0x00b2:
            if (r3 == 0) goto L_0x00b9
            if (r4 == 0) goto L_0x00b9
            if (r0 <= 0) goto L_0x009f
            goto L_0x009d
        L_0x00b9:
            if (r0 >= 0) goto L_0x009f
            goto L_0x009d
        L_0x00bc:
            r16 = r6
        L_0x00be:
            if (r0 == 0) goto L_0x00ce
            int r0 = r2.bitrate
            int r2 = r2.getPixelCount()
            r9 = r0
            r10 = r2
            r6 = r11
            r8 = r12
            r7 = r15
            goto L_0x00d0
        L_0x00cc:
            r16 = r6
        L_0x00ce:
            r6 = r16
        L_0x00d0:
            int r15 = r15 + 1
            r0 = r17
            r3 = -1
            goto L_0x0020
        L_0x00d7:
            r16 = r6
            int r5 = r5 + 1
            r0 = r17
            r3 = -1
            goto L_0x000b
        L_0x00e0:
            if (r6 != 0) goto L_0x00e4
            r2 = 0
            goto L_0x00ef
        L_0x00e4:
            androidx.media2.exoplayer.external.trackselection.TrackSelection$Definition r2 = new androidx.media2.exoplayer.external.trackselection.TrackSelection$Definition
            r0 = 1
            int[] r0 = new int[r0]
            r1 = 0
            r0[r1] = r7
            r2.<init>(r6, r0)
        L_0x00ef:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.selectFixedVideoTrack(androidx.media2.exoplayer.external.source.TrackGroupArray, int[][], androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector$Parameters):androidx.media2.exoplayer.external.trackselection.TrackSelection$Definition");
    }

    /* access modifiers changed from: protected */
    public Pair<TrackSelection.Definition, AudioTrackScore> selectAudioTrack(TrackGroupArray trackGroupArray, int[][] iArr, int i, Parameters parameters, boolean z) throws ExoPlaybackException {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        TrackSelection.Definition definition = null;
        AudioTrackScore audioTrackScore = null;
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < trackGroupArray2.length; i4++) {
            TrackGroup trackGroup = trackGroupArray2.get(i4);
            int[] iArr2 = iArr[i4];
            for (int i5 = 0; i5 < trackGroup.length; i5++) {
                if (isSupported(iArr2[i5], parameters2.exceedRendererCapabilitiesIfNecessary)) {
                    AudioTrackScore audioTrackScore2 = new AudioTrackScore(trackGroup.getFormat(i5), parameters2, iArr2[i5]);
                    if ((audioTrackScore2.isWithinConstraints || parameters2.exceedAudioConstraintsIfNecessary) && (audioTrackScore == null || audioTrackScore2.compareTo(audioTrackScore) > 0)) {
                        i2 = i4;
                        i3 = i5;
                        audioTrackScore = audioTrackScore2;
                    }
                }
            }
        }
        if (i2 == -1) {
            return null;
        }
        TrackGroup trackGroup2 = trackGroupArray2.get(i2);
        if (!parameters2.forceHighestSupportedBitrate && !parameters2.forceLowestBitrate && z) {
            int[] adaptiveAudioTracks = getAdaptiveAudioTracks(trackGroup2, iArr[i2], parameters2.allowAudioMixedMimeTypeAdaptiveness, parameters2.allowAudioMixedSampleRateAdaptiveness);
            if (adaptiveAudioTracks.length > 0) {
                definition = new TrackSelection.Definition(trackGroup2, adaptiveAudioTracks);
            }
        }
        if (definition == null) {
            definition = new TrackSelection.Definition(trackGroup2, i3);
        }
        return Pair.create(definition, (AudioTrackScore) Assertions.checkNotNull(audioTrackScore));
    }

    private static int[] getAdaptiveAudioTracks(TrackGroup trackGroup, int[] iArr, boolean z, boolean z2) {
        int adaptiveAudioTrackCount;
        HashSet hashSet = new HashSet();
        AudioConfigurationTuple audioConfigurationTuple = null;
        int i = 0;
        for (int i2 = 0; i2 < trackGroup.length; i2++) {
            Format format = trackGroup.getFormat(i2);
            AudioConfigurationTuple audioConfigurationTuple2 = new AudioConfigurationTuple(format.channelCount, format.sampleRate, format.sampleMimeType);
            if (hashSet.add(audioConfigurationTuple2) && (adaptiveAudioTrackCount = getAdaptiveAudioTrackCount(trackGroup, iArr, audioConfigurationTuple2, z, z2)) > i) {
                i = adaptiveAudioTrackCount;
                audioConfigurationTuple = audioConfigurationTuple2;
            }
        }
        if (i <= 1) {
            return NO_TRACKS;
        }
        int[] iArr2 = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < trackGroup.length; i4++) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i4), iArr[i4], (AudioConfigurationTuple) Assertions.checkNotNull(audioConfigurationTuple), z, z2)) {
                iArr2[i3] = i4;
                i3++;
            }
        }
        return iArr2;
    }

    private static int getAdaptiveAudioTrackCount(TrackGroup trackGroup, int[] iArr, AudioConfigurationTuple audioConfigurationTuple, boolean z, boolean z2) {
        int i = 0;
        for (int i2 = 0; i2 < trackGroup.length; i2++) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i2), iArr[i2], audioConfigurationTuple, z, z2)) {
                i++;
            }
        }
        return i;
    }

    private static boolean isSupportedAdaptiveAudioTrack(Format format, int i, AudioConfigurationTuple audioConfigurationTuple, boolean z, boolean z2) {
        if (!isSupported(i, false) || format.channelCount == -1 || format.channelCount != audioConfigurationTuple.channelCount) {
            return false;
        }
        if (!z && (format.sampleMimeType == null || !TextUtils.equals(format.sampleMimeType, audioConfigurationTuple.mimeType))) {
            return false;
        }
        if (z2 || (format.sampleRate != -1 && format.sampleRate == audioConfigurationTuple.sampleRate)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public Pair<TrackSelection.Definition, Integer> selectTextTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, String str) throws ExoPlaybackException {
        int i;
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        TrackGroup trackGroup = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < trackGroupArray2.length; i4++) {
            TrackGroup trackGroup2 = trackGroupArray2.get(i4);
            int[] iArr2 = iArr[i4];
            for (int i5 = 0; i5 < trackGroup2.length; i5++) {
                if (isSupported(iArr2[i5], parameters2.exceedRendererCapabilitiesIfNecessary)) {
                    Format format = trackGroup2.getFormat(i5);
                    int i6 = format.selectionFlags & (parameters2.disabledTextTrackSelectionFlags ^ -1);
                    boolean z = (i6 & 1) != 0;
                    boolean z2 = (i6 & 2) != 0;
                    int formatLanguageScore = getFormatLanguageScore(format, parameters2.preferredTextLanguage);
                    boolean formatHasNoLanguage = formatHasNoLanguage(format);
                    if (formatLanguageScore > 0 || (parameters2.selectUndeterminedTextLanguage && formatHasNoLanguage)) {
                        String str2 = str;
                        i = (z ? 11 : !z2 ? 7 : 3) + formatLanguageScore;
                    } else if (z) {
                        String str3 = str;
                        i = 2;
                    } else if (z2) {
                        if (getFormatLanguageScore(format, str) > 0 || (formatHasNoLanguage && stringDefinesNoLanguage(str))) {
                            i = 1;
                        }
                    }
                    if (isSupported(iArr2[i5], false)) {
                        i += 1000;
                    }
                    if (i > i3) {
                        trackGroup = trackGroup2;
                        i2 = i5;
                        i3 = i;
                    }
                }
                String str4 = str;
            }
            String str5 = str;
        }
        if (trackGroup == null) {
            return null;
        }
        return Pair.create(new TrackSelection.Definition(trackGroup, i2), Integer.valueOf(i3));
    }

    /* access modifiers changed from: protected */
    public TrackSelection.Definition selectOtherTrack(int i, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) throws ExoPlaybackException {
        TrackGroup trackGroup = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < trackGroupArray.length; i4++) {
            TrackGroup trackGroup2 = trackGroupArray.get(i4);
            int[] iArr2 = iArr[i4];
            for (int i5 = 0; i5 < trackGroup2.length; i5++) {
                if (isSupported(iArr2[i5], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    int i6 = (trackGroup2.getFormat(i5).selectionFlags & 1) != 0 ? 2 : 1;
                    if (isSupported(iArr2[i5], false)) {
                        i6 += 1000;
                    }
                    if (i6 > i3) {
                        trackGroup = trackGroup2;
                        i2 = i5;
                        i3 = i6;
                    }
                }
            }
        }
        if (trackGroup == null) {
            return null;
        }
        return new TrackSelection.Definition(trackGroup, i2);
    }

    private static void maybeConfigureRenderersForTunneling(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, TrackSelection[] trackSelectionArr, int i) {
        boolean z;
        if (i != 0) {
            boolean z2 = false;
            int i2 = 0;
            int i3 = -1;
            int i4 = -1;
            while (true) {
                if (i2 >= mappedTrackInfo.getRendererCount()) {
                    z = true;
                    break;
                }
                int rendererType = mappedTrackInfo.getRendererType(i2);
                TrackSelection trackSelection = trackSelectionArr[i2];
                if ((rendererType == 1 || rendererType == 2) && trackSelection != null && rendererSupportsTunneling(iArr[i2], mappedTrackInfo.getTrackGroups(i2), trackSelection)) {
                    if (rendererType == 1) {
                        if (i4 != -1) {
                            break;
                        }
                        i4 = i2;
                    } else if (i3 != -1) {
                        break;
                    } else {
                        i3 = i2;
                    }
                }
                i2++;
            }
            z = false;
            if (!(i4 == -1 || i3 == -1)) {
                z2 = true;
            }
            if (z && z2) {
                RendererConfiguration rendererConfiguration = new RendererConfiguration(i);
                rendererConfigurationArr[i4] = rendererConfiguration;
                rendererConfigurationArr[i3] = rendererConfiguration;
            }
        }
    }

    private static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, TrackSelection trackSelection) {
        if (trackSelection == null) {
            return false;
        }
        int indexOf = trackGroupArray.indexOf(trackSelection.getTrackGroup());
        for (int i = 0; i < trackSelection.length(); i++) {
            if ((iArr[indexOf][trackSelection.getIndexInTrackGroup(i)] & 32) != 32) {
                return false;
            }
        }
        return true;
    }

    protected static boolean formatHasNoLanguage(Format format) {
        return stringDefinesNoLanguage(format.language);
    }

    protected static boolean stringDefinesNoLanguage(String str) {
        return TextUtils.isEmpty(str) || TextUtils.equals(str, "und");
    }

    protected static int getFormatLanguageScore(Format format, String str) {
        if (format.language == null || str == null) {
            return 0;
        }
        if (TextUtils.equals(format.language, str)) {
            return 3;
        }
        if (format.language.startsWith(str) || str.startsWith(format.language)) {
            return 2;
        }
        if (format.language.length() < 3 || str.length() < 3 || !format.language.substring(0, 3).equals(str.substring(0, 3))) {
            return 0;
        }
        return 1;
    }

    private static List<Integer> getViewportFilteredTrackIndices(TrackGroup trackGroup, int i, int i2, boolean z) {
        ArrayList arrayList = new ArrayList(trackGroup.length);
        for (int i3 = 0; i3 < trackGroup.length; i3++) {
            arrayList.add(Integer.valueOf(i3));
        }
        if (!(i == Integer.MAX_VALUE || i2 == Integer.MAX_VALUE)) {
            int i4 = Integer.MAX_VALUE;
            for (int i5 = 0; i5 < trackGroup.length; i5++) {
                Format format = trackGroup.getFormat(i5);
                if (format.width > 0 && format.height > 0) {
                    Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z, i, i2, format.width, format.height);
                    int i6 = format.width * format.height;
                    if (format.width >= ((int) (((float) maxVideoSizeInViewport.x) * 0.98f)) && format.height >= ((int) (((float) maxVideoSizeInViewport.y) * 0.98f)) && i6 < i4) {
                        i4 = i6;
                    }
                }
            }
            if (i4 != Integer.MAX_VALUE) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    int pixelCount = trackGroup.getFormat(((Integer) arrayList.get(size)).intValue()).getPixelCount();
                    if (pixelCount == -1 || pixelCount > i4) {
                        arrayList.remove(size);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000d, code lost:
        if (r1 != r3) goto L_0x0013;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto L_0x0010
            r3 = 1
            r0 = 0
            if (r6 <= r7) goto L_0x0008
            r1 = 1
            goto L_0x0009
        L_0x0008:
            r1 = 0
        L_0x0009:
            if (r4 <= r5) goto L_0x000c
            goto L_0x000d
        L_0x000c:
            r3 = 0
        L_0x000d:
            if (r1 == r3) goto L_0x0010
            goto L_0x0013
        L_0x0010:
            r2 = r5
            r5 = r4
            r4 = r2
        L_0x0013:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L_0x0023
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = androidx.media2.exoplayer.external.util.Util.ceilDivide(r0, r6)
            r3.<init>(r5, r4)
            return r3
        L_0x0023:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = androidx.media2.exoplayer.external.util.Util.ceilDivide(r3, r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }

    protected static final class AudioTrackScore implements Comparable<AudioTrackScore> {
        private final int bitrate;
        private final int channelCount;
        private final boolean isDefaultSelectionFlag;
        public final boolean isWithinConstraints;
        private final boolean isWithinRendererCapabilities;
        private final int localeLanguageMatchIndex;
        private final int localeLanguageScore;
        private final Parameters parameters;
        private final int preferredLanguageScore;
        private final int sampleRate;

        public AudioTrackScore(Format format, Parameters parameters2, int i) {
            this.parameters = parameters2;
            int i2 = 0;
            this.isWithinRendererCapabilities = DefaultTrackSelector.isSupported(i, false);
            this.preferredLanguageScore = DefaultTrackSelector.getFormatLanguageScore(format, parameters2.preferredAudioLanguage);
            boolean z = true;
            this.isDefaultSelectionFlag = (format.selectionFlags & 1) != 0;
            this.channelCount = format.channelCount;
            this.sampleRate = format.sampleRate;
            this.bitrate = format.bitrate;
            if ((format.bitrate != -1 && format.bitrate > parameters2.maxAudioBitrate) || (format.channelCount != -1 && format.channelCount > parameters2.maxAudioChannelCount)) {
                z = false;
            }
            this.isWithinConstraints = z;
            String[] systemLanguageCodes = Util.getSystemLanguageCodes();
            int i3 = Integer.MAX_VALUE;
            int i4 = 0;
            while (true) {
                if (i4 >= systemLanguageCodes.length) {
                    break;
                }
                int formatLanguageScore = DefaultTrackSelector.getFormatLanguageScore(format, systemLanguageCodes[i4]);
                if (formatLanguageScore > 0) {
                    i3 = i4;
                    i2 = formatLanguageScore;
                    break;
                }
                i4++;
            }
            this.localeLanguageMatchIndex = i3;
            this.localeLanguageScore = i2;
        }

        public int compareTo(AudioTrackScore audioTrackScore) {
            int access$300;
            int access$400;
            boolean z = this.isWithinRendererCapabilities;
            int i = 1;
            if (z != audioTrackScore.isWithinRendererCapabilities) {
                return z ? 1 : -1;
            }
            int i2 = this.preferredLanguageScore;
            int i3 = audioTrackScore.preferredLanguageScore;
            if (i2 != i3) {
                return DefaultTrackSelector.compareInts(i2, i3);
            }
            boolean z2 = this.isWithinConstraints;
            if (z2 != audioTrackScore.isWithinConstraints) {
                if (z2) {
                    return 1;
                }
                return -1;
            } else if (!this.parameters.forceLowestBitrate || (access$400 = DefaultTrackSelector.compareFormatValues(this.bitrate, audioTrackScore.bitrate)) == 0) {
                boolean z3 = this.isDefaultSelectionFlag;
                if (z3 == audioTrackScore.isDefaultSelectionFlag) {
                    int i4 = this.localeLanguageMatchIndex;
                    int i5 = audioTrackScore.localeLanguageMatchIndex;
                    if (i4 != i5) {
                        return -DefaultTrackSelector.compareInts(i4, i5);
                    }
                    int i6 = this.localeLanguageScore;
                    int i7 = audioTrackScore.localeLanguageScore;
                    if (i6 != i7) {
                        return DefaultTrackSelector.compareInts(i6, i7);
                    }
                    if (!this.isWithinConstraints || !this.isWithinRendererCapabilities) {
                        i = -1;
                    }
                    int i8 = this.channelCount;
                    int i9 = audioTrackScore.channelCount;
                    if (i8 != i9) {
                        access$300 = DefaultTrackSelector.compareInts(i8, i9);
                    } else {
                        int i10 = this.sampleRate;
                        int i11 = audioTrackScore.sampleRate;
                        if (i10 != i11) {
                            access$300 = DefaultTrackSelector.compareInts(i10, i11);
                        } else {
                            access$300 = DefaultTrackSelector.compareInts(this.bitrate, audioTrackScore.bitrate);
                        }
                    }
                    return i * access$300;
                } else if (z3) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (access$400 > 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private static final class AudioConfigurationTuple {
        public final int channelCount;
        public final String mimeType;
        public final int sampleRate;

        public AudioConfigurationTuple(int i, int i2, String str) {
            this.channelCount = i;
            this.sampleRate = i2;
            this.mimeType = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AudioConfigurationTuple audioConfigurationTuple = (AudioConfigurationTuple) obj;
            if (this.channelCount == audioConfigurationTuple.channelCount && this.sampleRate == audioConfigurationTuple.sampleRate && TextUtils.equals(this.mimeType, audioConfigurationTuple.mimeType)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = ((this.channelCount * 31) + this.sampleRate) * 31;
            String str = this.mimeType;
            return i + (str != null ? str.hashCode() : 0);
        }
    }
}
