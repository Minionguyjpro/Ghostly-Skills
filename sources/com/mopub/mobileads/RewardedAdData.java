package com.mopub.mobileads;

import android.text.TextUtils;
import android.util.Pair;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class RewardedAdData {
    private final Map<String, Set<MoPubReward>> mAdUnitToAvailableRewardsMap = new TreeMap();
    private final Map<String, String> mAdUnitToCustomDataMap = new TreeMap();
    private final Map<String, CustomEventRewardedAd> mAdUnitToCustomEventMap = new TreeMap();
    private final Map<String, MoPubReward> mAdUnitToRewardMap = new TreeMap();
    private final Map<String, String> mAdUnitToServerCompletionUrlMap = new TreeMap();
    private String mCurrentlyShowingAdUnitId;
    private final Map<TwoPartKey, Set<String>> mCustomEventToMoPubIdMap = new HashMap();
    private final Map<Class<? extends CustomEventRewardedAd>, MoPubReward> mCustomEventToRewardMap = new HashMap();
    private String mCustomerId;

    RewardedAdData() {
    }

    /* access modifiers changed from: package-private */
    public CustomEventRewardedAd getCustomEvent(String str) {
        return this.mAdUnitToCustomEventMap.get(str);
    }

    /* access modifiers changed from: package-private */
    public MoPubReward getMoPubReward(String str) {
        return this.mAdUnitToRewardMap.get(str);
    }

    /* access modifiers changed from: package-private */
    public String getCustomData(String str) {
        return this.mAdUnitToCustomDataMap.get(str);
    }

    /* access modifiers changed from: package-private */
    public void addAvailableReward(String str, String str2, String str3) {
        Preconditions.checkNotNull(str);
        if (str2 == null || str3 == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Currency name and amount cannot be null: name = %s, amount = %s", new Object[]{str2, str3}));
            return;
        }
        try {
            int parseInt = Integer.parseInt(str3);
            if (parseInt < 0) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Currency amount cannot be negative: %s", new Object[]{str3}));
            } else if (this.mAdUnitToAvailableRewardsMap.containsKey(str)) {
                this.mAdUnitToAvailableRewardsMap.get(str).add(MoPubReward.success(str2, parseInt));
            } else {
                HashSet hashSet = new HashSet();
                hashSet.add(MoPubReward.success(str2, parseInt));
                this.mAdUnitToAvailableRewardsMap.put(str, hashSet);
            }
        } catch (NumberFormatException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Currency amount must be an integer: %s", new Object[]{str3}));
        }
    }

    /* access modifiers changed from: package-private */
    public Set<MoPubReward> getAvailableRewards(String str) {
        Preconditions.checkNotNull(str);
        Set<MoPubReward> set = this.mAdUnitToAvailableRewardsMap.get(str);
        return set == null ? Collections.emptySet() : set;
    }

    /* access modifiers changed from: package-private */
    public void selectReward(String str, MoPubReward moPubReward) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(moPubReward);
        Set set = this.mAdUnitToAvailableRewardsMap.get(str);
        if (set == null || set.isEmpty()) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "AdUnit %s does not have any rewards.", new Object[]{str}));
        } else if (!set.contains(moPubReward)) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Selected reward is invalid for AdUnit %s.", new Object[]{str}));
        } else {
            updateAdUnitRewardMapping(str, moPubReward.getLabel(), Integer.toString(moPubReward.getAmount()));
        }
    }

    /* access modifiers changed from: package-private */
    public void resetAvailableRewards(String str) {
        Preconditions.checkNotNull(str);
        Set set = this.mAdUnitToAvailableRewardsMap.get(str);
        if (set != null && !set.isEmpty()) {
            set.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void resetSelectedReward(String str) {
        Preconditions.checkNotNull(str);
        updateAdUnitRewardMapping(str, (String) null, (String) null);
    }

    /* access modifiers changed from: package-private */
    public String getServerCompletionUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.mAdUnitToServerCompletionUrlMap.get(str);
    }

    /* access modifiers changed from: package-private */
    public MoPubReward getLastShownMoPubReward(Class<? extends CustomEventRewardedAd> cls) {
        return this.mCustomEventToRewardMap.get(cls);
    }

    /* access modifiers changed from: package-private */
    public Set<String> getMoPubIdsForAdNetwork(Class<? extends CustomEventRewardedAd> cls, String str) {
        if (str == null) {
            HashSet hashSet = new HashSet();
            for (Map.Entry next : this.mCustomEventToMoPubIdMap.entrySet()) {
                if (cls == ((TwoPartKey) next.getKey()).customEventClass) {
                    hashSet.addAll((Collection) next.getValue());
                }
            }
            return hashSet;
        }
        TwoPartKey twoPartKey = new TwoPartKey(cls, str);
        if (this.mCustomEventToMoPubIdMap.containsKey(twoPartKey)) {
            return this.mCustomEventToMoPubIdMap.get(twoPartKey);
        }
        return Collections.emptySet();
    }

    /* access modifiers changed from: package-private */
    public void updateAdUnitCustomEventMapping(String str, CustomEventRewardedAd customEventRewardedAd, String str2) {
        this.mAdUnitToCustomEventMap.put(str, customEventRewardedAd);
        associateCustomEventWithMoPubId(customEventRewardedAd.getClass(), str2, str);
    }

    /* access modifiers changed from: package-private */
    public void updateAdUnitRewardMapping(String str, String str2, String str3) {
        Preconditions.checkNotNull(str);
        if (str2 == null || str3 == null) {
            this.mAdUnitToRewardMap.remove(str);
            return;
        }
        try {
            int parseInt = Integer.parseInt(str3);
            if (parseInt < 0) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Currency amount cannot be negative: %s", new Object[]{str3}));
                return;
            }
            this.mAdUnitToRewardMap.put(str, MoPubReward.success(str2, parseInt));
        } catch (NumberFormatException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Currency amount must be an integer: %s", new Object[]{str3}));
        }
    }

    /* access modifiers changed from: package-private */
    public void updateAdUnitToServerCompletionUrlMapping(String str, String str2) {
        Preconditions.checkNotNull(str);
        this.mAdUnitToServerCompletionUrlMap.put(str, str2);
    }

    /* access modifiers changed from: package-private */
    public void updateCustomEventLastShownRewardMapping(Class<? extends CustomEventRewardedAd> cls, MoPubReward moPubReward) {
        Preconditions.checkNotNull(cls);
        this.mCustomEventToRewardMap.put(cls, moPubReward);
    }

    /* access modifiers changed from: package-private */
    public void associateCustomEventWithMoPubId(Class<? extends CustomEventRewardedAd> cls, String str, String str2) {
        TwoPartKey twoPartKey = new TwoPartKey(cls, str);
        Iterator<Map.Entry<TwoPartKey, Set<String>>> it = this.mCustomEventToMoPubIdMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry next = it.next();
            if (!((TwoPartKey) next.getKey()).equals(twoPartKey) && ((Set) next.getValue()).contains(str2)) {
                ((Set) next.getValue()).remove(str2);
                if (((Set) next.getValue()).isEmpty()) {
                    it.remove();
                }
            }
        }
        Set set = this.mCustomEventToMoPubIdMap.get(twoPartKey);
        if (set == null) {
            set = new HashSet();
            this.mCustomEventToMoPubIdMap.put(twoPartKey, set);
        }
        set.add(str2);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentlyShowingAdUnitId(String str) {
        this.mCurrentlyShowingAdUnitId = str;
    }

    /* access modifiers changed from: package-private */
    public void updateAdUnitToCustomDataMapping(String str, String str2) {
        Preconditions.NoThrow.checkNotNull(str);
        this.mAdUnitToCustomDataMap.put(str, str2);
    }

    /* access modifiers changed from: package-private */
    public String getCurrentlyShowingAdUnitId() {
        return this.mCurrentlyShowingAdUnitId;
    }

    /* access modifiers changed from: package-private */
    public void setCustomerId(String str) {
        this.mCustomerId = str;
    }

    /* access modifiers changed from: package-private */
    public String getCustomerId() {
        return this.mCustomerId;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void clear() {
        this.mAdUnitToCustomEventMap.clear();
        this.mAdUnitToRewardMap.clear();
        this.mAdUnitToAvailableRewardsMap.clear();
        this.mAdUnitToServerCompletionUrlMap.clear();
        this.mAdUnitToCustomDataMap.clear();
        this.mCustomEventToRewardMap.clear();
        this.mCustomEventToMoPubIdMap.clear();
        this.mCurrentlyShowingAdUnitId = null;
        this.mCustomerId = null;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean existsInAvailableRewards(String str, String str2, int i) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        for (MoPubReward next : getAvailableRewards(str)) {
            if (next.getLabel().equals(str2) && next.getAmount() == i) {
                return true;
            }
        }
        return false;
    }

    private static class TwoPartKey extends Pair<Class<? extends CustomEventRewardedAd>, String> {
        final String adNetworkId;
        final Class<? extends CustomEventRewardedAd> customEventClass;

        public TwoPartKey(Class<? extends CustomEventRewardedAd> cls, String str) {
            super(cls, str);
            this.customEventClass = cls;
            this.adNetworkId = str;
        }
    }
}
