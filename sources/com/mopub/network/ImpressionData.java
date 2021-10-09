package com.mopub.network;

import com.mopub.common.logging.MoPubLog;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ImpressionData implements Serializable {
    public static final String ADGROUP_ID = "adgroup_id";
    public static final String ADGROUP_NAME = "adgroup_name";
    public static final String ADGROUP_PRIORITY = "adgroup_priority";
    public static final String ADGROUP_TYPE = "adgroup_type";
    public static final String ADUNIT_FORMAT = "adunit_format";
    public static final String ADUNIT_ID = "adunit_id";
    public static final String ADUNIT_NAME = "adunit_name";
    public static final String APP_VERSION = "app_version";
    public static final String COUNTRY = "country";
    public static final String CURRENCY = "currency";
    public static final String IMPRESSION_ID = "id";
    public static final String NETWORK_NAME = "network_name";
    public static final String NETWORK_PLACEMENT_ID = "network_placement_id";
    public static final String PRECISION = "precision";
    public static final String PUBLISHER_REVENUE = "publisher_revenue";
    private static final long serialVersionUID = 79;
    private SerializableJson mJson;

    private ImpressionData(JSONObject jSONObject) throws JSONException {
        this.mJson = new SerializableJson(jSONObject);
    }

    static ImpressionData create(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return new ImpressionData(jSONObject);
        } catch (Exception e) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, e.toString());
            return null;
        }
    }

    public String getAppVersion() {
        return this.mJson.optString(APP_VERSION, (String) null);
    }

    public String getAdUnitId() {
        return this.mJson.optString(ADUNIT_ID, (String) null);
    }

    public String getAdUnitName() {
        return this.mJson.optString(ADUNIT_NAME, (String) null);
    }

    public String getAdUnitFormat() {
        return this.mJson.optString("adunit_format", (String) null);
    }

    public String getImpressionId() {
        return this.mJson.optString("id", (String) null);
    }

    public String getCurrency() {
        return this.mJson.optString(CURRENCY, (String) null);
    }

    public Double getPublisherRevenue() {
        try {
            return Double.valueOf(this.mJson.getDouble(PUBLISHER_REVENUE));
        } catch (Exception unused) {
            return null;
        }
    }

    public String getAdGroupId() {
        return this.mJson.optString(ADGROUP_ID, (String) null);
    }

    public String getAdGroupName() {
        return this.mJson.optString(ADGROUP_NAME, (String) null);
    }

    public String getAdGroupType() {
        return this.mJson.optString(ADGROUP_TYPE, (String) null);
    }

    public Integer getAdGroupPriority() {
        try {
            return Integer.valueOf(this.mJson.getInt(ADGROUP_PRIORITY));
        } catch (Exception unused) {
            return null;
        }
    }

    public String getCountry() {
        return this.mJson.optString(COUNTRY, (String) null);
    }

    public String getPrecision() {
        return this.mJson.optString(PRECISION, (String) null);
    }

    public String getNetworkName() {
        return this.mJson.optString(NETWORK_NAME, (String) null);
    }

    public String getNetworkPlacementId() {
        return this.mJson.optString(NETWORK_PLACEMENT_ID, (String) null);
    }

    public JSONObject getJsonRepresentation() {
        return this.mJson;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.mJson.toString());
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException, JSONException {
        objectInputStream.defaultReadObject();
        this.mJson = new SerializableJson(objectInputStream.readUTF());
    }

    private static class SerializableJson extends JSONObject implements Serializable {
        SerializableJson(JSONObject jSONObject) throws JSONException {
            super(jSONObject.toString());
        }

        SerializableJson(String str) throws JSONException {
            super(str);
        }
    }
}
