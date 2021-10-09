package com.appsgeyser.sdk.configuration;

import android.content.Context;
import android.text.TextUtils;
import com.appsgeyser.sdk.GuidGenerator;
import org.json.JSONException;
import org.json.JSONObject;

public final class Configuration {
    private static Configuration instance;
    private String appGuid = "";
    private String appName = "";
    private String applicationId = "";
    private String publisherName = "";
    private boolean registered = false;
    private PreferencesCoder settingsCoder;

    private Configuration() {
    }

    public static Configuration getInstance(Context context) {
        if (instance == null) {
            instance = new Configuration();
        }
        instance.setSettingsCoder(new PreferencesCoder(context));
        return instance;
    }

    public PreferencesCoder getSettingsCoder() {
        return this.settingsCoder;
    }

    private void setSettingsCoder(PreferencesCoder preferencesCoder) {
        this.settingsCoder = preferencesCoder;
    }

    public void loadConfiguration() {
        this.publisherName = "";
        this.applicationId = this.settingsCoder.getPrefString("ApplicationId", "");
        this.appGuid = this.settingsCoder.getPrefString("AppGuid", "");
        this.registered = this.settingsCoder.getPrefBoolean("Registered", false);
    }

    public boolean isRegistered() {
        return this.registered;
    }

    public String getAppGuid() {
        if (TextUtils.isEmpty(this.appGuid)) {
            String generateNewGuid = GuidGenerator.generateNewGuid();
            this.appGuid = generateNewGuid;
            PreferencesCoder preferencesCoder = this.settingsCoder;
            if (preferencesCoder != null) {
                preferencesCoder.savePrefString("AppGuid", generateNewGuid);
            }
        }
        return this.appGuid;
    }

    public String getPublisherName() {
        return this.publisherName;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String str) {
        this.applicationId = str;
        PreferencesCoder preferencesCoder = this.settingsCoder;
        if (preferencesCoder != null) {
            preferencesCoder.savePrefString("ApplicationId", str);
        }
    }

    public void setTemplateVersion(String str) {
        this.settingsCoder.savePrefString("TemplateVersion", str);
    }

    public String getTemplateVersion() {
        return this.settingsCoder.getPrefString("TemplateVersion", "");
    }

    public void clearApplicationSettings() {
        this.applicationId = "";
        this.appGuid = "";
        this.settingsCoder.savePrefString("ApplicationId", "");
        this.settingsCoder.savePrefString("AppGuid", "");
    }

    public void registerNew() {
        this.registered = true;
        this.settingsCoder.savePrefBoolean("Registered", true);
    }

    public void setMetricaOnStartEvent(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("templateVersion", str2);
            jSONObject.put("appsgeyserSdkVersion", "2.23.s");
            this.settingsCoder.savePrefString("metricaJsonLoad", jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMetricaOnStartEvent() {
        return this.settingsCoder.getPrefString("metricaJsonLoad", (String) null);
    }
}
