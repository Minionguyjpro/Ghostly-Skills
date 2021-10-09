package com.mopub.nativeads;

import com.mopub.common.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MoPubNativeAdPositioning {

    public static class MoPubServerPositioning {
    }

    public static class MoPubClientPositioning {
        public static final int NO_REPEAT = Integer.MAX_VALUE;
        /* access modifiers changed from: private */
        public final ArrayList<Integer> mFixedPositions = new ArrayList<>();
        /* access modifiers changed from: private */
        public int mRepeatInterval = Integer.MAX_VALUE;

        public MoPubClientPositioning addFixedPosition(int i) {
            int binarySearch;
            if (Preconditions.NoThrow.checkArgument(i >= 0) && (binarySearch = Collections.binarySearch(this.mFixedPositions, Integer.valueOf(i))) < 0) {
                this.mFixedPositions.add(binarySearch ^ -1, Integer.valueOf(i));
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public List<Integer> getFixedPositions() {
            return this.mFixedPositions;
        }

        public MoPubClientPositioning enableRepeatingPositions(int i) {
            boolean z = true;
            if (i <= 1) {
                z = false;
            }
            if (!Preconditions.NoThrow.checkArgument(z, "Repeating interval must be greater than 1")) {
                this.mRepeatInterval = Integer.MAX_VALUE;
                return this;
            }
            this.mRepeatInterval = i;
            return this;
        }

        /* access modifiers changed from: package-private */
        public int getRepeatingInterval() {
            return this.mRepeatInterval;
        }
    }

    static MoPubClientPositioning clone(MoPubClientPositioning moPubClientPositioning) {
        Preconditions.checkNotNull(moPubClientPositioning);
        MoPubClientPositioning moPubClientPositioning2 = new MoPubClientPositioning();
        moPubClientPositioning2.mFixedPositions.addAll(moPubClientPositioning.mFixedPositions);
        int unused = moPubClientPositioning2.mRepeatInterval = moPubClientPositioning.mRepeatInterval;
        return moPubClientPositioning2;
    }

    public static MoPubClientPositioning clientPositioning() {
        return new MoPubClientPositioning();
    }

    public static MoPubServerPositioning serverPositioning() {
        return new MoPubServerPositioning();
    }
}
