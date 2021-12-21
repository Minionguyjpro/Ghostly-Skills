package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowInsets;
import com.mopub.common.AdReport;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.SharedPreferencesHelper;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Json;
import com.mopub.common.util.MoPubCollections;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Utils;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.volley.NoConnectionError;
import com.mopub.volley.VolleyError;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class MoPubRewardedVideoManager {
    public static final int API_VERSION = 1;
    private static final String CURRENCIES_JSON_REWARDS_MAP_KEY = "rewards";
    private static final String CURRENCIES_JSON_REWARD_AMOUNT_KEY = "amount";
    private static final String CURRENCIES_JSON_REWARD_NAME_KEY = "name";
    static final int CUSTOM_DATA_MAX_LENGTH_BYTES = 8192;
    private static final String CUSTOM_EVENT_PREF_NAME = "mopubCustomEventSettings";
    private static final int DEFAULT_LOAD_TIMEOUT = 30000;
    private static SharedPreferences sCustomEventSharedPrefs;
    /* access modifiers changed from: private */
    public static MoPubRewardedVideoManager sInstance;
    private final Handler mCallbackHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Handler mCustomEventTimeoutHandler;
    private final Set<MediationSettings> mGlobalMediationSettings;
    private final Map<String, Set<MediationSettings>> mInstanceMediationSettings;
    private WeakReference<Activity> mMainActivity;
    /* access modifiers changed from: private */
    public final RewardedAdData mRewardedAdData = new RewardedAdData();
    private final Map<String, Runnable> mTimeoutMap;
    /* access modifiers changed from: private */
    public MoPubRewardedVideoListener mVideoListener;
    /* access modifiers changed from: private */
    public final RewardedAdsLoaders rewardedAdsLoaders;

    public static final class RequestParameters {
        public final String mCustomerId;
        public final String mKeywords;
        public final Location mLocation;
        public final String mUserDataKeywords;

        public RequestParameters(String str) {
            this(str, (String) null);
        }

        public RequestParameters(String str, String str2) {
            this(str, str2, (Location) null);
        }

        public RequestParameters(String str, String str2, Location location) {
            this(str, str2, location, (String) null);
        }

        public RequestParameters(String str, String str2, Location location, String str3) {
            this.mKeywords = str;
            this.mCustomerId = str3;
            boolean canCollectPersonalInformation = MoPub.canCollectPersonalInformation();
            this.mUserDataKeywords = !canCollectPersonalInformation ? null : str2;
            this.mLocation = !canCollectPersonalInformation ? null : location;
        }
    }

    private MoPubRewardedVideoManager(Activity activity, MediationSettings... mediationSettingsArr) {
        this.mMainActivity = new WeakReference<>(activity);
        this.mContext = activity.getApplicationContext();
        HashSet hashSet = new HashSet();
        this.mGlobalMediationSettings = hashSet;
        MoPubCollections.addAllNonNull(hashSet, (T[]) mediationSettingsArr);
        this.mInstanceMediationSettings = new HashMap();
        this.mCustomEventTimeoutHandler = new Handler();
        this.mTimeoutMap = new HashMap();
        this.rewardedAdsLoaders = new RewardedAdsLoaders(this);
        sCustomEventSharedPrefs = SharedPreferencesHelper.getSharedPreferences(this.mContext, CUSTOM_EVENT_PREF_NAME);
    }

    public static synchronized void init(Activity activity, MediationSettings... mediationSettingsArr) {
        synchronized (MoPubRewardedVideoManager.class) {
            if (sInstance == null) {
                sInstance = new MoPubRewardedVideoManager(activity, mediationSettingsArr);
            } else {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Tried to call initializeRewardedVideo more than once. Only the first initialization call has any effect.");
            }
        }
    }

    public static void updateActivity(Activity activity) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            moPubRewardedVideoManager.mMainActivity = new WeakReference<>(activity);
        } else {
            logErrorNotInitialized();
        }
    }

    public static <T extends MediationSettings> T getGlobalMediationSettings(Class<T> cls) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager == null) {
            logErrorNotInitialized();
            return null;
        }
        for (MediationSettings next : moPubRewardedVideoManager.mGlobalMediationSettings) {
            if (cls.equals(next.getClass())) {
                return (MediationSettings) cls.cast(next);
            }
        }
        return null;
    }

    public static <T extends MediationSettings> T getInstanceMediationSettings(Class<T> cls, String str) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager == null) {
            logErrorNotInitialized();
            return null;
        }
        Set<MediationSettings> set = moPubRewardedVideoManager.mInstanceMediationSettings.get(str);
        if (set == null) {
            return null;
        }
        for (MediationSettings mediationSettings : set) {
            if (cls.equals(mediationSettings.getClass())) {
                return (MediationSettings) cls.cast(mediationSettings);
            }
        }
        return null;
    }

    public static void setVideoListener(MoPubRewardedVideoListener moPubRewardedVideoListener) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            moPubRewardedVideoManager.mVideoListener = moPubRewardedVideoListener;
        } else {
            logErrorNotInitialized();
        }
    }

    public static void loadVideo(final String str, RequestParameters requestParameters, MediationSettings... mediationSettingsArr) {
        String str2;
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager == null) {
            logErrorNotInitialized();
        } else if (str.equals(moPubRewardedVideoManager.mRewardedAdData.getCurrentlyShowingAdUnitId())) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Did not queue rewarded ad request for ad unit %s. The ad is already showing.", new Object[]{str}));
        } else if (sInstance.rewardedAdsLoaders.canPlay(str)) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Did not queue rewarded ad request for ad unit %s. This ad unit already finished loading and is ready to show.", new Object[]{str}));
            postToInstance(new Runnable() {
                public void run() {
                    if (MoPubRewardedVideoManager.sInstance.mVideoListener != null) {
                        MoPubRewardedVideoManager.sInstance.mVideoListener.onRewardedVideoLoadSuccess(str);
                    }
                }
            });
        } else {
            HashSet hashSet = new HashSet();
            MoPubCollections.addAllNonNull(hashSet, (T[]) mediationSettingsArr);
            sInstance.mInstanceMediationSettings.put(str, hashSet);
            if (requestParameters == null) {
                str2 = null;
            } else {
                str2 = requestParameters.mCustomerId;
            }
            if (!TextUtils.isEmpty(str2)) {
                sInstance.mRewardedAdData.setCustomerId(str2);
            }
            WebViewAdUrlGenerator webViewAdUrlGenerator = new WebViewAdUrlGenerator(sInstance.mContext);
            webViewAdUrlGenerator.withAdUnitId(str).withKeywords(requestParameters == null ? null : requestParameters.mKeywords).withUserDataKeywords((requestParameters == null || !MoPub.canCollectPersonalInformation()) ? null : requestParameters.mUserDataKeywords);
            setSafeAreaValues(webViewAdUrlGenerator);
            loadVideo(str, webViewAdUrlGenerator.generateUrlString(Constants.HOST), (MoPubErrorCode) null);
        }
    }

    private static void loadVideo(String str, String str2, MoPubErrorCode moPubErrorCode) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager == null) {
            logErrorNotInitialized();
        } else {
            moPubRewardedVideoManager.fetchAd(str, str2, moPubErrorCode);
        }
    }

    private void fetchAd(String str, String str2, MoPubErrorCode moPubErrorCode) {
        if (this.rewardedAdsLoaders.isLoading(str)) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Did not queue rewarded ad request for ad unit %s. A request is already pending.", new Object[]{str}));
            return;
        }
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Loading rewarded ad request for ad unit %s with URL %s", new Object[]{str, str2}));
        this.rewardedAdsLoaders.loadNextAd(this.mContext, str, str2, moPubErrorCode);
    }

    public static boolean hasVideo(String str) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            return isPlayable(str, moPubRewardedVideoManager.mRewardedAdData.getCustomEvent(str));
        }
        logErrorNotInitialized();
        return false;
    }

    public static void showVideo(String str) {
        showVideo(str, (String) null);
    }

    public static void showVideo(String str, String str2) {
        if (sInstance == null) {
            logErrorNotInitialized();
            return;
        }
        if (str2 != null && str2.length() > CUSTOM_DATA_MAX_LENGTH_BYTES) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Provided rewarded ad custom data parameter longer than supported(%d bytes, %d maximum)", new Object[]{Integer.valueOf(str2.length()), Integer.valueOf(CUSTOM_DATA_MAX_LENGTH_BYTES)}));
        }
        CustomEventRewardedAd customEvent = sInstance.mRewardedAdData.getCustomEvent(str);
        if (!isPlayable(str, customEvent)) {
            if (sInstance.rewardedAdsLoaders.isLoading(str)) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Rewarded ad is not ready to be shown yet.");
            } else {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "No rewarded ad loading or loaded.");
            }
            sInstance.failover(str, MoPubErrorCode.VIDEO_NOT_AVAILABLE);
        } else if (sInstance.mRewardedAdData.getAvailableRewards(str).isEmpty() || sInstance.mRewardedAdData.getMoPubReward(str) != null) {
            sInstance.mRewardedAdData.updateCustomEventLastShownRewardMapping(customEvent.getClass(), sInstance.mRewardedAdData.getMoPubReward(str));
            sInstance.mRewardedAdData.updateAdUnitToCustomDataMapping(str, str2);
            sInstance.mRewardedAdData.setCurrentlyShowingAdUnitId(str);
            customEvent.show();
        } else {
            sInstance.failover(str, MoPubErrorCode.REWARD_NOT_SELECTED);
        }
    }

    private static boolean isPlayable(String str, CustomEventRewardedAd customEventRewardedAd) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        return moPubRewardedVideoManager != null && moPubRewardedVideoManager.rewardedAdsLoaders.canPlay(str) && customEventRewardedAd != null && customEventRewardedAd.isReady();
    }

    public static Set<MoPubReward> getAvailableRewards(String str) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            return moPubRewardedVideoManager.mRewardedAdData.getAvailableRewards(str);
        }
        logErrorNotInitialized();
        return Collections.emptySet();
    }

    public static void selectReward(String str, MoPubReward moPubReward) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            moPubRewardedVideoManager.mRewardedAdData.selectReward(str, moPubReward);
        } else {
            logErrorNotInitialized();
        }
    }

    private static void setSafeAreaValues(AdUrlGenerator adUrlGenerator) {
        Window window;
        WindowInsets rootWindowInsets;
        Preconditions.checkNotNull(adUrlGenerator);
        adUrlGenerator.withRequestedAdSize(ClientMetadata.getInstance(sInstance.mContext).getDeviceDimensions());
        if (Build.VERSION.SDK_INT >= 28 && ((Activity) sInstance.mMainActivity.get()) != null && (window = ((Activity) sInstance.mMainActivity.get()).getWindow()) != null && (rootWindowInsets = window.getDecorView().getRootWindowInsets()) != null) {
            adUrlGenerator.withWindowInsets(rootWindowInsets);
        }
    }

    /* access modifiers changed from: package-private */
    public void onAdSuccess(AdResponse adResponse) {
        String adUnitId = adResponse.getAdUnitId();
        Integer adTimeoutMillis = adResponse.getAdTimeoutMillis(30000);
        String customEventClassName = adResponse.getCustomEventClassName();
        if (customEventClassName == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Couldn't create custom event, class name was null.");
            failover(adUnitId, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        CustomEventRewardedAd customEvent = this.mRewardedAdData.getCustomEvent(adUnitId);
        if (customEvent != null) {
            customEvent.onInvalidate();
        }
        try {
            final CustomEventRewardedAd customEventRewardedAd = (CustomEventRewardedAd) Reflection.instantiateClassWithEmptyConstructor(customEventClassName, CustomEventRewardedAd.class);
            TreeMap treeMap = new TreeMap();
            treeMap.put(DataKeys.AD_UNIT_ID_KEY, adUnitId);
            treeMap.put(DataKeys.REWARDED_AD_CURRENCY_NAME_KEY, adResponse.getRewardedVideoCurrencyName());
            treeMap.put(DataKeys.REWARDED_AD_CURRENCY_AMOUNT_STRING_KEY, adResponse.getRewardedVideoCurrencyAmount());
            treeMap.put(DataKeys.REWARDED_AD_DURATION_KEY, adResponse.getRewardedDuration());
            treeMap.put(DataKeys.SHOULD_REWARD_ON_CLICK_KEY, Boolean.valueOf(adResponse.shouldRewardOnClick()));
            treeMap.put(DataKeys.AD_REPORT_KEY, new AdReport(adUnitId, ClientMetadata.getInstance(this.mContext), adResponse));
            treeMap.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(Utils.generateUniqueId()));
            treeMap.put("rewarded-ad-customer-id", this.mRewardedAdData.getCustomerId());
            String rewardedCurrencies = adResponse.getRewardedCurrencies();
            this.mRewardedAdData.resetAvailableRewards(adUnitId);
            this.mRewardedAdData.resetSelectedReward(adUnitId);
            if (TextUtils.isEmpty(rewardedCurrencies)) {
                this.mRewardedAdData.updateAdUnitRewardMapping(adUnitId, adResponse.getRewardedVideoCurrencyName(), adResponse.getRewardedVideoCurrencyAmount());
            } else {
                try {
                    parseMultiCurrencyJson(adUnitId, rewardedCurrencies);
                } catch (Exception unused) {
                    MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                    MoPubLog.log(sdkLogEvent, "Error parsing rewarded currencies JSON header: " + rewardedCurrencies);
                    failover(adUnitId, MoPubErrorCode.REWARDED_CURRENCIES_PARSING_ERROR);
                    return;
                }
            }
            this.mRewardedAdData.updateAdUnitToServerCompletionUrlMapping(adUnitId, adResponse.getRewardedVideoCompletionUrl());
            Activity activity = (Activity) this.mMainActivity.get();
            if (activity == null) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Could not load custom event because Activity reference was null. Call MoPub#updateActivity before requesting more rewarded ads.");
                this.rewardedAdsLoaders.markFail(adUnitId);
                return;
            }
            AnonymousClass2 r8 = new Runnable() {
                public void run() {
                    MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Custom Event failed to load rewarded ad in a timely fashion.");
                    MoPubRewardedVideoManager.onRewardedVideoLoadFailure(customEventRewardedAd.getClass(), customEventRewardedAd.getAdNetworkId(), MoPubErrorCode.NETWORK_TIMEOUT);
                    customEventRewardedAd.onInvalidate();
                }
            };
            this.mCustomEventTimeoutHandler.postDelayed(r8, (long) adTimeoutMillis.intValue());
            this.mTimeoutMap.put(adUnitId, r8);
            Map<String, String> serverExtras = adResponse.getServerExtras();
            if (customEventRewardedAd instanceof CustomEventRewardedVideo) {
                String jSONObject = new JSONObject(serverExtras).toString();
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Updating init settings for custom event %s with params %s", new Object[]{customEventClassName, jSONObject}));
                sCustomEventSharedPrefs.edit().putString(customEventClassName, jSONObject).apply();
            }
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Loading custom event with class name %s", new Object[]{customEventClassName}));
            customEventRewardedAd.loadCustomEvent(activity, treeMap, serverExtras);
            this.mRewardedAdData.updateAdUnitCustomEventMapping(adUnitId, customEventRewardedAd, customEventRewardedAd.getAdNetworkId());
        } catch (Exception unused2) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Couldn't create custom event with class name %s", new Object[]{customEventClassName}));
            failover(adUnitId, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        }
    }

    /* access modifiers changed from: package-private */
    public void onAdError(VolleyError volleyError, String str) {
        MoPubErrorCode moPubErrorCode = MoPubErrorCode.INTERNAL_ERROR;
        if (volleyError instanceof MoPubNetworkError) {
            int i = AnonymousClass15.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()];
            if (i == 1 || i == 2) {
                moPubErrorCode = MoPubErrorCode.NO_FILL;
            } else {
                moPubErrorCode = MoPubErrorCode.INTERNAL_ERROR;
            }
        }
        if (volleyError instanceof NoConnectionError) {
            moPubErrorCode = MoPubErrorCode.NO_CONNECTION;
        }
        failover(str, moPubErrorCode);
    }

    /* renamed from: com.mopub.mobileads.MoPubRewardedVideoManager$15  reason: invalid class name */
    static /* synthetic */ class AnonymousClass15 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$network$MoPubNetworkError$Reason;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.mopub.network.MoPubNetworkError$Reason[] r0 = com.mopub.network.MoPubNetworkError.Reason.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason = r0
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$network$MoPubNetworkError$Reason     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.WARMING_UP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$network$MoPubNetworkError$Reason     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.BAD_BODY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$mopub$network$MoPubNetworkError$Reason     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.network.MoPubNetworkError$Reason r1 = com.mopub.network.MoPubNetworkError.Reason.BAD_HEADER_DATA     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.MoPubRewardedVideoManager.AnonymousClass15.<clinit>():void");
        }
    }

    private void parseMultiCurrencyJson(String str, String str2) throws JSONException {
        String[] jsonArrayToStringArray = Json.jsonArrayToStringArray(Json.jsonStringToMap(str2).get(CURRENCIES_JSON_REWARDS_MAP_KEY));
        if (jsonArrayToStringArray.length == 1) {
            Map<String, String> jsonStringToMap = Json.jsonStringToMap(jsonArrayToStringArray[0]);
            this.mRewardedAdData.updateAdUnitRewardMapping(str, jsonStringToMap.get(CURRENCIES_JSON_REWARD_NAME_KEY), jsonStringToMap.get(CURRENCIES_JSON_REWARD_AMOUNT_KEY));
        }
        for (String jsonStringToMap2 : jsonArrayToStringArray) {
            Map<String, String> jsonStringToMap3 = Json.jsonStringToMap(jsonStringToMap2);
            this.mRewardedAdData.addAvailableReward(str, jsonStringToMap3.get(CURRENCIES_JSON_REWARD_NAME_KEY), jsonStringToMap3.get(CURRENCIES_JSON_REWARD_AMOUNT_KEY));
        }
    }

    /* access modifiers changed from: private */
    public void failover(String str, MoPubErrorCode moPubErrorCode) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(moPubErrorCode);
        if (!this.rewardedAdsLoaders.hasMoreAds(str) || moPubErrorCode.equals(MoPubErrorCode.EXPIRED)) {
            MoPubRewardedVideoListener moPubRewardedVideoListener = sInstance.mVideoListener;
            if (moPubRewardedVideoListener != null) {
                moPubRewardedVideoListener.onRewardedVideoLoadFailure(str, moPubErrorCode);
                return;
            }
            return;
        }
        loadVideo(str, "", moPubErrorCode);
    }

    /* access modifiers changed from: private */
    public void cancelTimeouts(String str) {
        Runnable remove = this.mTimeoutMap.remove(str);
        if (remove != null) {
            this.mCustomEventTimeoutHandler.removeCallbacks(remove);
        }
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoLoadSuccess(Class<T> cls, String str) {
        postToInstance(new ForEachMoPubIdRunnable(cls, str) {
            /* access modifiers changed from: protected */
            public void forEach(String str) {
                MoPubRewardedVideoManager.sInstance.cancelTimeouts(str);
                MoPubRewardedVideoManager.sInstance.rewardedAdsLoaders.creativeDownloadSuccess(str);
                if (MoPubRewardedVideoManager.sInstance.mVideoListener != null) {
                    MoPubRewardedVideoManager.sInstance.mVideoListener.onRewardedVideoLoadSuccess(str);
                }
            }
        });
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoLoadFailure(Class<T> cls, String str, final MoPubErrorCode moPubErrorCode) {
        postToInstance(new ForEachMoPubIdRunnable(cls, str) {
            /* access modifiers changed from: protected */
            public void forEach(String str) {
                MoPubRewardedVideoManager.sInstance.cancelTimeouts(str);
                MoPubRewardedVideoManager.sInstance.failover(str, moPubErrorCode);
            }
        });
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoStarted(Class<T> cls, String str) {
        final String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new ForEachMoPubIdRunnable(cls, str) {
                /* access modifiers changed from: protected */
                public void forEach(String str) {
                    MoPubRewardedVideoManager.onRewardedVideoStartedAction(str);
                }
            });
        } else {
            postToInstance(new Runnable() {
                public void run() {
                    MoPubRewardedVideoManager.onRewardedVideoStartedAction(currentlyShowingAdUnitId);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void onRewardedVideoStartedAction(String str) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoListener moPubRewardedVideoListener = sInstance.mVideoListener;
        if (moPubRewardedVideoListener != null) {
            moPubRewardedVideoListener.onRewardedVideoStarted(str);
        }
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        moPubRewardedVideoManager.rewardedAdsLoaders.onRewardedVideoStarted(str, moPubRewardedVideoManager.mContext);
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoPlaybackError(Class<T> cls, String str, final MoPubErrorCode moPubErrorCode) {
        final String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new ForEachMoPubIdRunnable(cls, str) {
                /* access modifiers changed from: protected */
                public void forEach(String str) {
                    MoPubRewardedVideoManager.onRewardedVideoPlaybackErrorAction(str, moPubErrorCode);
                }
            });
        } else {
            postToInstance(new Runnable() {
                public void run() {
                    MoPubRewardedVideoManager.onRewardedVideoPlaybackErrorAction(currentlyShowingAdUnitId, moPubErrorCode);
                }
            });
        }
        sInstance.mRewardedAdData.setCurrentlyShowingAdUnitId((String) null);
    }

    /* access modifiers changed from: private */
    public static void onRewardedVideoPlaybackErrorAction(String str, MoPubErrorCode moPubErrorCode) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(moPubErrorCode);
        sInstance.rewardedAdsLoaders.markFail(str);
        MoPubRewardedVideoListener moPubRewardedVideoListener = sInstance.mVideoListener;
        if (moPubRewardedVideoListener != null) {
            moPubRewardedVideoListener.onRewardedVideoPlaybackError(str, moPubErrorCode);
        }
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoClicked(Class<T> cls, String str) {
        final String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new ForEachMoPubIdRunnable(cls, str) {
                /* access modifiers changed from: protected */
                public void forEach(String str) {
                    MoPubRewardedVideoManager.onRewardedVideoClickedAction(str);
                }
            });
        } else {
            postToInstance(new Runnable() {
                public void run() {
                    MoPubRewardedVideoManager.onRewardedVideoClickedAction(currentlyShowingAdUnitId);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void onRewardedVideoClickedAction(String str) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoListener moPubRewardedVideoListener = sInstance.mVideoListener;
        if (moPubRewardedVideoListener != null) {
            moPubRewardedVideoListener.onRewardedVideoClicked(str);
        }
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        moPubRewardedVideoManager.rewardedAdsLoaders.onRewardedVideoClicked(str, moPubRewardedVideoManager.mContext);
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoClosed(Class<T> cls, String str) {
        final String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new ForEachMoPubIdRunnable(cls, str) {
                /* access modifiers changed from: protected */
                public void forEach(String str) {
                    MoPubRewardedVideoManager.onRewardedVideoClosedAction(str);
                }
            });
        } else {
            postToInstance(new Runnable() {
                public void run() {
                    MoPubRewardedVideoManager.onRewardedVideoClosedAction(currentlyShowingAdUnitId);
                }
            });
        }
        sInstance.mRewardedAdData.setCurrentlyShowingAdUnitId((String) null);
    }

    /* access modifiers changed from: private */
    public static void onRewardedVideoClosedAction(String str) {
        Preconditions.checkNotNull(str);
        sInstance.rewardedAdsLoaders.markPlayed(str);
        MoPubRewardedVideoListener moPubRewardedVideoListener = sInstance.mVideoListener;
        if (moPubRewardedVideoListener != null) {
            moPubRewardedVideoListener.onRewardedVideoClosed(str);
        }
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoCompleted(Class<T> cls, String str, MoPubReward moPubReward) {
        String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        rewardOnClient(cls, str, moPubReward, currentlyShowingAdUnitId);
        rewardOnServer(currentlyShowingAdUnitId);
    }

    private static void rewardOnServer(final String str) {
        final String serverCompletionUrl = sInstance.mRewardedAdData.getServerCompletionUrl(str);
        if (!TextUtils.isEmpty(serverCompletionUrl)) {
            postToInstance(new Runnable() {
                public void run() {
                    String str;
                    String str2;
                    MoPubReward moPubReward = MoPubRewardedVideoManager.sInstance.mRewardedAdData.getMoPubReward(str);
                    if (moPubReward == null) {
                        str = "";
                    } else {
                        str = moPubReward.getLabel();
                    }
                    String str3 = str;
                    if (moPubReward == null) {
                        str2 = Integer.toString(0);
                    } else {
                        str2 = Integer.toString(moPubReward.getAmount());
                    }
                    String str4 = str2;
                    CustomEventRewardedAd customEvent = MoPubRewardedVideoManager.sInstance.mRewardedAdData.getCustomEvent(str);
                    RewardedVideoCompletionRequestHandler.makeRewardedVideoCompletionRequest(MoPubRewardedVideoManager.sInstance.mContext, serverCompletionUrl, MoPubRewardedVideoManager.sInstance.mRewardedAdData.getCustomerId(), str3, str4, (customEvent == null || customEvent.getClass() == null) ? null : customEvent.getClass().getName(), MoPubRewardedVideoManager.sInstance.mRewardedAdData.getCustomData(str));
                }
            });
        }
    }

    private static <T extends CustomEventRewardedAd> void rewardOnClient(final Class<T> cls, final String str, final MoPubReward moPubReward, final String str2) {
        postToInstance(new Runnable() {
            public void run() {
                MoPubReward chooseReward = MoPubRewardedVideoManager.chooseReward(MoPubRewardedVideoManager.sInstance.mRewardedAdData.getLastShownMoPubReward(cls), moPubReward);
                HashSet hashSet = new HashSet();
                if (TextUtils.isEmpty(str2)) {
                    hashSet.addAll(MoPubRewardedVideoManager.sInstance.mRewardedAdData.getMoPubIdsForAdNetwork(cls, str));
                } else {
                    hashSet.add(str2);
                }
                if (MoPubRewardedVideoManager.sInstance.mVideoListener != null) {
                    MoPubRewardedVideoManager.sInstance.mVideoListener.onRewardedVideoCompleted(hashSet, chooseReward);
                }
            }
        });
    }

    static MoPubReward chooseReward(MoPubReward moPubReward, MoPubReward moPubReward2) {
        if (!moPubReward2.isSuccessful()) {
            return moPubReward2;
        }
        return moPubReward != null ? moPubReward : moPubReward2;
    }

    private static void postToInstance(Runnable runnable) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            moPubRewardedVideoManager.mCallbackHandler.post(runnable);
        }
    }

    private static void logErrorNotInitialized() {
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "MoPub rewarded ad was not initialized. You must call MoPub.initializeSdk() with an Activity Context before loading or attempting to play rewarded ads.");
    }

    private static abstract class ForEachMoPubIdRunnable implements Runnable {
        private final Class<? extends CustomEventRewardedAd> mCustomEventClass;
        private final String mThirdPartyId;

        /* access modifiers changed from: protected */
        public abstract void forEach(String str);

        ForEachMoPubIdRunnable(Class<? extends CustomEventRewardedAd> cls, String str) {
            Preconditions.checkNotNull(cls);
            Preconditions.checkNotNull(str);
            this.mCustomEventClass = cls;
            this.mThirdPartyId = str;
        }

        public void run() {
            for (String forEach : MoPubRewardedVideoManager.sInstance.mRewardedAdData.getMoPubIdsForAdNetwork(this.mCustomEventClass, this.mThirdPartyId)) {
                forEach(forEach);
            }
        }
    }

    @Deprecated
    static RewardedAdData getRewardedAdData() {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            return moPubRewardedVideoManager.mRewardedAdData;
        }
        return null;
    }

    @Deprecated
    static RewardedAdsLoaders getAdRequestStatusMapping() {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            return moPubRewardedVideoManager.rewardedAdsLoaders;
        }
        return null;
    }

    @Deprecated
    static void setCustomEventSharedPrefs(SharedPreferences sharedPreferences) {
        Preconditions.checkNotNull(sharedPreferences);
        sCustomEventSharedPrefs = sharedPreferences;
    }
}
