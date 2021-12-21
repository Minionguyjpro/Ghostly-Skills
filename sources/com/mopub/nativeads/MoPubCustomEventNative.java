package com.mopub.nativeads;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.plus.PlusShare;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Numbers;
import com.mopub.nativeads.CustomEventNative;
import com.mopub.nativeads.NativeImageHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class MoPubCustomEventNative extends CustomEventNative {
    public static final String ADAPTER_NAME = MoPubCustomEventNative.class.getSimpleName();
    private MoPubStaticNativeAd moPubStaticNativeAd;

    /* access modifiers changed from: protected */
    public void loadNativeAd(Context context, CustomEventNative.CustomEventNativeListener customEventNativeListener, Map<String, Object> map, Map<String, String> map2) {
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
        MoPubStaticNativeAd moPubStaticNativeAd2 = this.moPubStaticNativeAd;
        if (moPubStaticNativeAd2 == null || moPubStaticNativeAd2.isInvalidated()) {
            Object obj = map.get(DataKeys.JSON_BODY_KEY);
            if (!(obj instanceof JSONObject)) {
                MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(NativeErrorCode.INVALID_RESPONSE.getIntCode()), NativeErrorCode.INVALID_RESPONSE);
                customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
                return;
            }
            this.moPubStaticNativeAd = new MoPubStaticNativeAd(context, (JSONObject) obj, new ImpressionTracker(context), new NativeClickHandler(context), customEventNativeListener);
            if (map2.containsKey(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT)) {
                try {
                    this.moPubStaticNativeAd.setImpressionMinPercentageViewed(Integer.parseInt(map2.get(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT)));
                } catch (NumberFormatException unused) {
                    MoPubLog.AdapterLogEvent adapterLogEvent = MoPubLog.AdapterLogEvent.CUSTOM;
                    MoPubLog.log(adapterLogEvent, ADAPTER_NAME, "Unable to format min visible percent: " + map2.get(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT));
                }
            }
            if (map2.containsKey(DataKeys.IMPRESSION_VISIBLE_MS)) {
                try {
                    this.moPubStaticNativeAd.setImpressionMinTimeViewed(Integer.parseInt(map2.get(DataKeys.IMPRESSION_VISIBLE_MS)));
                } catch (NumberFormatException unused2) {
                    MoPubLog.AdapterLogEvent adapterLogEvent2 = MoPubLog.AdapterLogEvent.CUSTOM;
                    MoPubLog.log(adapterLogEvent2, ADAPTER_NAME, "Unable to format min time: " + map2.get(DataKeys.IMPRESSION_VISIBLE_MS));
                }
            }
            if (map2.containsKey(DataKeys.IMPRESSION_MIN_VISIBLE_PX)) {
                try {
                    this.moPubStaticNativeAd.setImpressionMinVisiblePx(Integer.valueOf(Integer.parseInt(map2.get(DataKeys.IMPRESSION_MIN_VISIBLE_PX))));
                } catch (NumberFormatException unused3) {
                    MoPubLog.AdapterLogEvent adapterLogEvent3 = MoPubLog.AdapterLogEvent.CUSTOM;
                    MoPubLog.log(adapterLogEvent3, ADAPTER_NAME, "Unable to format min visible px: " + map2.get(DataKeys.IMPRESSION_MIN_VISIBLE_PX));
                }
            }
            try {
                this.moPubStaticNativeAd.loadAd();
                MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_SUCCESS, ADAPTER_NAME);
            } catch (IllegalArgumentException unused4) {
                MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(NativeErrorCode.UNSPECIFIED.getIntCode()), NativeErrorCode.UNSPECIFIED);
                customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        MoPubStaticNativeAd moPubStaticNativeAd2 = this.moPubStaticNativeAd;
        if (moPubStaticNativeAd2 != null) {
            moPubStaticNativeAd2.invalidate();
        }
    }

    static class MoPubStaticNativeAd extends StaticNativeAd {
        static final String PRIVACY_INFORMATION_CLICKTHROUGH_URL = "https://www.mopub.com/optout";
        private final Context mContext;
        /* access modifiers changed from: private */
        public final CustomEventNative.CustomEventNativeListener mCustomEventNativeListener;
        private final ImpressionTracker mImpressionTracker;
        private final JSONObject mJsonObject;
        private final NativeClickHandler mNativeClickHandler;

        enum Parameter {
            IMPRESSION_TRACKER("imptracker", true),
            CLICK_TRACKER("clktracker", true),
            TITLE(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, false),
            TEXT("text", false),
            MAIN_IMAGE("mainimage", false),
            ICON_IMAGE("iconimage", false),
            CLICK_DESTINATION("clk", false),
            FALLBACK("fallback", false),
            CALL_TO_ACTION("ctatext", false),
            STAR_RATING("starrating", false),
            PRIVACY_INFORMATION_ICON_IMAGE_URL("privacyicon", false),
            PRIVACY_INFORMATION_ICON_CLICKTHROUGH_URL("privacyclkurl", false),
            SPONSORED("sponsored", false);
            
            static final Set<String> requiredKeys = null;
            final String name;
            final boolean required;

            static {
                int i;
                requiredKeys = new HashSet();
                for (Parameter parameter : values()) {
                    if (parameter.required) {
                        requiredKeys.add(parameter.name);
                    }
                }
            }

            private Parameter(String str, boolean z) {
                this.name = str;
                this.required = z;
            }

            static Parameter from(String str) {
                for (Parameter parameter : values()) {
                    if (parameter.name.equals(str)) {
                        return parameter;
                    }
                }
                return null;
            }
        }

        MoPubStaticNativeAd(Context context, JSONObject jSONObject, ImpressionTracker impressionTracker, NativeClickHandler nativeClickHandler, CustomEventNative.CustomEventNativeListener customEventNativeListener) {
            this.mJsonObject = jSONObject;
            this.mContext = context.getApplicationContext();
            this.mImpressionTracker = impressionTracker;
            this.mNativeClickHandler = nativeClickHandler;
            this.mCustomEventNativeListener = customEventNativeListener;
        }

        /* access modifiers changed from: package-private */
        public void loadAd() throws IllegalArgumentException {
            if (containsRequiredKeys(this.mJsonObject)) {
                Iterator<String> keys = this.mJsonObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    Parameter from = Parameter.from(next);
                    if (from != null) {
                        try {
                            addInstanceVariable(from, this.mJsonObject.opt(next));
                        } catch (ClassCastException unused) {
                            throw new IllegalArgumentException("JSONObject key (" + next + ") contained unexpected value.");
                        }
                    } else {
                        addExtra(next, this.mJsonObject.opt(next));
                    }
                }
                if (TextUtils.isEmpty(getPrivacyInformationIconClickThroughUrl())) {
                    setPrivacyInformationIconClickThroughUrl(PRIVACY_INFORMATION_CLICKTHROUGH_URL);
                }
                NativeImageHelper.preCacheImages(this.mContext, getAllImageUrls(), new NativeImageHelper.ImageListener() {
                    public void onImagesCached() {
                        if (!MoPubStaticNativeAd.this.isInvalidated()) {
                            MoPubStaticNativeAd.this.mCustomEventNativeListener.onNativeAdLoaded(MoPubStaticNativeAd.this);
                        }
                    }

                    public void onImagesFailedToCache(NativeErrorCode nativeErrorCode) {
                        if (!MoPubStaticNativeAd.this.isInvalidated()) {
                            MoPubStaticNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(nativeErrorCode);
                        }
                    }
                });
                return;
            }
            throw new IllegalArgumentException("JSONObject did not contain required keys.");
        }

        private boolean containsRequiredKeys(JSONObject jSONObject) {
            HashSet hashSet = new HashSet();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                hashSet.add(keys.next());
            }
            return hashSet.containsAll(Parameter.requiredKeys);
        }

        private void addInstanceVariable(Parameter parameter, Object obj) throws ClassCastException {
            try {
                switch (AnonymousClass1.$SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[parameter.ordinal()]) {
                    case 1:
                        setMainImageUrl((String) obj);
                        return;
                    case 2:
                        setIconImageUrl((String) obj);
                        return;
                    case 3:
                        addImpressionTrackers(obj);
                        return;
                    case 4:
                        setClickDestinationUrl((String) obj);
                        return;
                    case 5:
                        parseClickTrackers(obj);
                        return;
                    case 6:
                        setCallToAction((String) obj);
                        return;
                    case 7:
                        setTitle((String) obj);
                        return;
                    case 8:
                        setText((String) obj);
                        return;
                    case 9:
                        setStarRating(Numbers.parseDouble(obj));
                        return;
                    case 10:
                        setPrivacyInformationIconImageUrl((String) obj);
                        return;
                    case 11:
                        setPrivacyInformationIconClickThroughUrl((String) obj);
                        return;
                    case 12:
                        setSponsored((String) obj);
                        break;
                }
                MoPubLog.AdapterLogEvent adapterLogEvent = MoPubLog.AdapterLogEvent.CUSTOM;
                MoPubLog.log(adapterLogEvent, MoPubCustomEventNative.ADAPTER_NAME, "Unable to add JSON key to internal mapping: " + parameter.name);
            } catch (ClassCastException e) {
                if (!parameter.required) {
                    MoPubLog.AdapterLogEvent adapterLogEvent2 = MoPubLog.AdapterLogEvent.CUSTOM;
                    MoPubLog.log(adapterLogEvent2, MoPubCustomEventNative.ADAPTER_NAME, "Ignoring class cast exception for optional key: " + parameter.name);
                    return;
                }
                throw e;
            }
        }

        private void parseClickTrackers(Object obj) {
            if (obj instanceof JSONArray) {
                addClickTrackers(obj);
            } else {
                addClickTracker((String) obj);
            }
        }

        private boolean isImageKey(String str) {
            return str != null && str.toLowerCase(Locale.US).endsWith("image");
        }

        /* access modifiers changed from: package-private */
        public List<String> getExtrasImageUrls() {
            ArrayList arrayList = new ArrayList(getExtras().size());
            for (Map.Entry next : getExtras().entrySet()) {
                if (isImageKey((String) next.getKey()) && (next.getValue() instanceof String)) {
                    arrayList.add((String) next.getValue());
                }
            }
            return arrayList;
        }

        /* access modifiers changed from: package-private */
        public List<String> getAllImageUrls() {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(getMainImageUrl())) {
                arrayList.add(getMainImageUrl());
            }
            if (!TextUtils.isEmpty(getIconImageUrl())) {
                arrayList.add(getIconImageUrl());
            }
            if (!TextUtils.isEmpty(getPrivacyInformationIconImageUrl())) {
                arrayList.add(getPrivacyInformationIconImageUrl());
            }
            arrayList.addAll(getExtrasImageUrls());
            return arrayList;
        }

        public void prepare(View view) {
            this.mImpressionTracker.addView(view, this);
            this.mNativeClickHandler.setOnClickListener(view, (ClickInterface) this);
        }

        public void clear(View view) {
            this.mImpressionTracker.removeView(view);
            this.mNativeClickHandler.clearOnClickListener(view);
        }

        public void destroy() {
            this.mImpressionTracker.destroy();
            super.destroy();
        }

        public void recordImpression(View view) {
            notifyAdImpressed();
        }

        public void handleClick(View view) {
            MoPubLog.log(MoPubLog.AdapterLogEvent.CLICKED, MoPubCustomEventNative.ADAPTER_NAME);
            notifyAdClicked();
            this.mNativeClickHandler.openClickDestinationUrl(getClickDestinationUrl(), view);
        }
    }

    /* renamed from: com.mopub.nativeads.MoPubCustomEventNative$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter[] r0 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter = r0
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.MAIN_IMAGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.ICON_IMAGE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.IMPRESSION_TRACKER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.CLICK_DESTINATION     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x003e }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.CLICK_TRACKER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.CALL_TO_ACTION     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.TITLE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.TEXT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x006c }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.STAR_RATING     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.PRIVACY_INFORMATION_ICON_IMAGE_URL     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.PRIVACY_INFORMATION_ICON_CLICKTHROUGH_URL     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventNative$MoPubStaticNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.mopub.nativeads.MoPubCustomEventNative$MoPubStaticNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventNative.MoPubStaticNativeAd.Parameter.SPONSORED     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.MoPubCustomEventNative.AnonymousClass1.<clinit>():void");
        }
    }
}
