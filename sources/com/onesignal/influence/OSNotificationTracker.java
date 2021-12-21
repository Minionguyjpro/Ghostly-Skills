package com.onesignal.influence;

import com.onesignal.OSLogger;
import com.onesignal.influence.model.OSInfluence;
import com.onesignal.influence.model.OSInfluenceChannel;
import com.onesignal.influence.model.OSInfluenceType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class OSNotificationTracker extends OSChannelTracker {
    public static final String TAG = OSNotificationTracker.class.getCanonicalName();

    public String getIdTag() {
        return "notification_id";
    }

    OSNotificationTracker(OSInfluenceDataRepository oSInfluenceDataRepository, OSLogger oSLogger) {
        super(oSInfluenceDataRepository, oSLogger);
    }

    /* access modifiers changed from: package-private */
    public JSONArray getLastChannelObjectsReceivedByNewId(String str) {
        try {
            return getLastChannelObjects();
        } catch (JSONException e) {
            this.logger.error("Generating Notification tracker getLastChannelObjects JSONObject ", e);
            return new JSONArray();
        }
    }

    /* access modifiers changed from: package-private */
    public JSONArray getLastChannelObjects() throws JSONException {
        return this.dataRepository.getLastNotificationsReceivedData();
    }

    /* access modifiers changed from: package-private */
    public OSInfluenceChannel getChannelType() {
        return OSInfluenceChannel.NOTIFICATION;
    }

    /* access modifiers changed from: package-private */
    public int getChannelLimit() {
        return this.dataRepository.getNotificationLimit();
    }

    /* access modifiers changed from: package-private */
    public int getIndirectAttributionWindow() {
        return this.dataRepository.getNotificationIndirectAttributionWindow();
    }

    /* access modifiers changed from: package-private */
    public void saveChannelObjects(JSONArray jSONArray) {
        this.dataRepository.saveNotifications(jSONArray);
    }

    /* access modifiers changed from: package-private */
    public void initInfluencedTypeFromCache() {
        OSInfluenceType notificationCachedInfluenceType = this.dataRepository.getNotificationCachedInfluenceType();
        setInfluenceType(notificationCachedInfluenceType);
        if (notificationCachedInfluenceType.isIndirect()) {
            setIndirectIds(getLastReceivedIds());
        } else if (notificationCachedInfluenceType.isDirect()) {
            setDirectId(this.dataRepository.getCachedNotificationOpenId());
        }
        OSLogger oSLogger = this.logger;
        oSLogger.debug("OneSignal NotificationTracker initInfluencedTypeFromCache: " + toString());
    }

    /* access modifiers changed from: package-private */
    public void addSessionData(JSONObject jSONObject, OSInfluence oSInfluence) {
        if (oSInfluence.getInfluenceType().isAttributed()) {
            try {
                jSONObject.put("direct", oSInfluence.getInfluenceType().isDirect());
                jSONObject.put("notification_ids", oSInfluence.getIds());
            } catch (JSONException e) {
                this.logger.error("Generating notification tracker addSessionData JSONObject ", e);
            }
        }
    }

    public void cacheState() {
        this.dataRepository.cacheNotificationInfluenceType(this.influenceType == null ? OSInfluenceType.UNATTRIBUTED : this.influenceType);
        this.dataRepository.cacheNotificationOpenId(this.directId);
    }
}
