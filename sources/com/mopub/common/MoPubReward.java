package com.mopub.common;

public final class MoPubReward {
    public static final int DEFAULT_REWARD_AMOUNT = 0;
    public static final int NO_REWARD_AMOUNT = -123;
    public static final String NO_REWARD_LABEL = "";
    private final int mAmount;
    private final String mLabel;
    private final boolean mSuccess;

    private MoPubReward(boolean z, String str, int i) {
        this.mSuccess = z;
        this.mLabel = str;
        this.mAmount = i;
    }

    public static MoPubReward failure() {
        return new MoPubReward(false, "", 0);
    }

    public static MoPubReward success(String str, int i) {
        return new MoPubReward(true, str, i);
    }

    public final boolean isSuccessful() {
        return this.mSuccess;
    }

    public final String getLabel() {
        return this.mLabel;
    }

    public final int getAmount() {
        return this.mAmount;
    }
}
