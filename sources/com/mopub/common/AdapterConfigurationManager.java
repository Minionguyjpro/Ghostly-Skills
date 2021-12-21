package com.mopub.common;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.Reflection;
import com.mopub.mobileads.MoPubErrorCode;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class AdapterConfigurationManager implements AdapterConfigurationsInitializationListener {
    private static final String TOKEN_KEY = "token";
    private volatile Map<String, AdapterConfiguration> mAdapterConfigurations;
    private SdkInitializationListener mSdkInitializationListener;

    AdapterConfigurationManager(SdkInitializationListener sdkInitializationListener) {
        this.mSdkInitializationListener = sdkInitializationListener;
    }

    public void initialize(Context context, Set<String> set, Map<String, Map<String, String>> map, Map<String, Map<String, String>> map2) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(set);
        Preconditions.checkNotNull(map);
        Preconditions.checkNotNull(map2);
        AsyncTasks.safeExecuteOnExecutor(new AdapterConfigurationsInitializationAsyncTask(context.getApplicationContext(), set, map, map2, this), new Void[0]);
    }

    public List<String> getAdvancedBidderNames() {
        Map<String, AdapterConfiguration> map = this.mAdapterConfigurations;
        if (map == null || map.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (AdapterConfiguration moPubNetworkName : map.values()) {
            arrayList.add(moPubNetworkName.getMoPubNetworkName());
        }
        return arrayList;
    }

    public List<String> getAdapterConfigurationInfo() {
        Map<String, AdapterConfiguration> map = this.mAdapterConfigurations;
        if (map == null || map.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            StringBuilder sb = new StringBuilder();
            String str = (String) next.getKey();
            sb.append(str.substring(str.lastIndexOf(".") + 1));
            sb.append(": Adapter version ");
            sb.append(((AdapterConfiguration) next.getValue()).getAdapterVersion());
            sb.append(", SDK version ");
            sb.append(((AdapterConfiguration) next.getValue()).getNetworkSdkVersion());
            arrayList.add(sb.toString());
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public String getTokensAsJsonString(Context context) {
        Preconditions.checkNotNull(context);
        JSONObject tokensAsJsonObject = getTokensAsJsonObject(context);
        if (tokensAsJsonObject == null) {
            return null;
        }
        return tokensAsJsonObject.toString();
    }

    private JSONObject getTokensAsJsonObject(Context context) {
        Preconditions.checkNotNull(context);
        Map<String, AdapterConfiguration> map = this.mAdapterConfigurations;
        JSONObject jSONObject = null;
        if (map != null && !map.isEmpty()) {
            for (AdapterConfiguration next : map.values()) {
                try {
                    String biddingToken = next.getBiddingToken(context);
                    if (!TextUtils.isEmpty(biddingToken)) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put(TOKEN_KEY, biddingToken);
                        if (jSONObject == null) {
                            jSONObject = new JSONObject();
                        }
                        jSONObject.put(next.getMoPubNetworkName(), jSONObject2);
                    }
                } catch (JSONException unused) {
                    MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.ERROR;
                    MoPubLog.log(sdkLogEvent, "JSON parsing failed for MoPub network name: " + next.getMoPubNetworkName());
                }
            }
        }
        return jSONObject;
    }

    /* access modifiers changed from: package-private */
    public AdapterConfiguration getAdapterConfiguration(Class<? extends AdapterConfiguration> cls) {
        Preconditions.checkNotNull(cls);
        Map<String, AdapterConfiguration> map = this.mAdapterConfigurations;
        if (map == null) {
            return null;
        }
        return map.get(cls.getName());
    }

    public void onAdapterConfigurationsInitialized(Map<String, AdapterConfiguration> map) {
        Preconditions.checkNotNull(map);
        this.mAdapterConfigurations = map;
        SdkInitializationListener sdkInitializationListener = this.mSdkInitializationListener;
        if (sdkInitializationListener != null) {
            sdkInitializationListener.onInitializationFinished();
            this.mSdkInitializationListener = null;
        }
    }

    public void onNetworkInitializationFinished(Class<? extends AdapterConfiguration> cls, MoPubErrorCode moPubErrorCode) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(moPubErrorCode);
        MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
        MoPubLog.log(sdkLogEvent, cls + " initialized with error code " + moPubErrorCode);
    }

    private static class AdapterConfigurationsInitializationAsyncTask extends AsyncTask<Void, Void, Map<String, AdapterConfiguration>> {
        private final Set<String> adapterConfigurationClasses;
        private final AdapterConfigurationsInitializationListener adapterConfigurationsInitializationListener;
        private final Map<String, Map<String, String>> moPubRequestOptions;
        private final Map<String, Map<String, String>> networkMediationConfigurations;
        private final WeakReference<Context> weakContext;

        AdapterConfigurationsInitializationAsyncTask(Context context, Set<String> set, Map<String, Map<String, String>> map, Map<String, Map<String, String>> map2, AdapterConfigurationsInitializationListener adapterConfigurationsInitializationListener2) {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(set);
            Preconditions.checkNotNull(map);
            Preconditions.checkNotNull(map2);
            Preconditions.checkNotNull(adapterConfigurationsInitializationListener2);
            this.weakContext = new WeakReference<>(context);
            this.adapterConfigurationClasses = set;
            this.networkMediationConfigurations = map;
            this.moPubRequestOptions = map2;
            this.adapterConfigurationsInitializationListener = adapterConfigurationsInitializationListener2;
        }

        /* access modifiers changed from: protected */
        public Map<String, AdapterConfiguration> doInBackground(Void... voidArr) {
            HashMap hashMap = new HashMap();
            for (String next : this.adapterConfigurationClasses) {
                try {
                    AdapterConfiguration adapterConfiguration = (AdapterConfiguration) Reflection.instantiateClassWithEmptyConstructor(next, AdapterConfiguration.class);
                    Context context = (Context) this.weakContext.get();
                    if (context == null) {
                        MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                        MoPubLog.log(sdkLogEvent, "Context null. Unable to initialize adapter configuration " + next);
                    } else {
                        Map map = this.networkMediationConfigurations.get(next);
                        HashMap hashMap2 = new HashMap(adapterConfiguration.getCachedInitializationParameters(context));
                        if (map != null) {
                            hashMap2.putAll(map);
                            adapterConfiguration.setCachedInitializationParameters(context, hashMap2);
                        }
                        Map map2 = this.moPubRequestOptions.get(next);
                        if (map2 != null) {
                            adapterConfiguration.setMoPubRequestOptions(map2);
                        }
                        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Initializing %s version %s with network sdk version %s and with params %s", new Object[]{next, adapterConfiguration.getAdapterVersion(), adapterConfiguration.getNetworkSdkVersion(), hashMap2}));
                        adapterConfiguration.initializeNetwork(context, hashMap2, this.adapterConfigurationsInitializationListener);
                        hashMap.put(next, adapterConfiguration);
                    }
                } catch (Exception e) {
                    MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM_WITH_THROWABLE;
                    MoPubLog.log(sdkLogEvent2, "Unable to find class " + next, e);
                }
            }
            return hashMap;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Map<String, AdapterConfiguration> map) {
            this.adapterConfigurationsInitializationListener.onAdapterConfigurationsInitialized(map);
        }
    }
}
