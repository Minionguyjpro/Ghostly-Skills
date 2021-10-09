package com.onesignal;

import com.onesignal.LocationController;
import com.onesignal.OneSignal;
import com.onesignal.UserStateSynchronizer;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

class OneSignalStateSynchronizer {
    private static HashMap<UserStateSynchronizerType, UserStateSynchronizer> userStateSynchronizers = new HashMap<>();

    enum UserStateSynchronizerType {
        PUSH,
        EMAIL
    }

    static UserStatePushSynchronizer getPushStateSynchronizer() {
        if (!userStateSynchronizers.containsKey(UserStateSynchronizerType.PUSH) || userStateSynchronizers.get(UserStateSynchronizerType.PUSH) == null) {
            userStateSynchronizers.put(UserStateSynchronizerType.PUSH, new UserStatePushSynchronizer());
        }
        return (UserStatePushSynchronizer) userStateSynchronizers.get(UserStateSynchronizerType.PUSH);
    }

    static UserStateEmailSynchronizer getEmailStateSynchronizer() {
        if (!userStateSynchronizers.containsKey(UserStateSynchronizerType.EMAIL) || userStateSynchronizers.get(UserStateSynchronizerType.EMAIL) == null) {
            userStateSynchronizers.put(UserStateSynchronizerType.EMAIL, new UserStateEmailSynchronizer());
        }
        return (UserStateEmailSynchronizer) userStateSynchronizers.get(UserStateSynchronizerType.EMAIL);
    }

    static boolean persist() {
        boolean persist = getPushStateSynchronizer().persist();
        boolean persist2 = getEmailStateSynchronizer().persist();
        if (persist2) {
            persist2 = getEmailStateSynchronizer().getRegistrationId() != null;
        }
        if (persist || persist2) {
            return true;
        }
        return false;
    }

    static void initUserState() {
        getPushStateSynchronizer().initUserState();
        getEmailStateSynchronizer().initUserState();
    }

    static void syncUserState(boolean z) {
        getPushStateSynchronizer().syncUserState(z);
        getEmailStateSynchronizer().syncUserState(z);
    }

    static void sendTags(JSONObject jSONObject, OneSignal.ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        try {
            JSONObject put = new JSONObject().put("tags", jSONObject);
            getPushStateSynchronizer().sendTags(put, changeTagsUpdateHandler);
            getEmailStateSynchronizer().sendTags(put, changeTagsUpdateHandler);
        } catch (JSONException e) {
            if (changeTagsUpdateHandler != null) {
                changeTagsUpdateHandler.onFailure(new OneSignal.SendTagsError(-1, "Encountered an error attempting to serialize your tags into JSON: " + e.getMessage() + "\n" + e.getStackTrace()));
            }
            e.printStackTrace();
        }
    }

    static boolean getUserSubscribePreference() {
        return getPushStateSynchronizer().getUserSubscribePreference();
    }

    static void setPermission(boolean z) {
        getPushStateSynchronizer().setPermission(z);
    }

    static void updateLocation(LocationController.LocationPoint locationPoint) {
        getPushStateSynchronizer().updateLocation(locationPoint);
        getEmailStateSynchronizer().updateLocation(locationPoint);
    }

    static boolean getSubscribed() {
        return getPushStateSynchronizer().getSubscribed();
    }

    static String getRegistrationId() {
        return getPushStateSynchronizer().getRegistrationId();
    }

    static UserStateSynchronizer.GetTagsResult getTags(boolean z) {
        return getPushStateSynchronizer().getTags(z);
    }

    static void resetCurrentState() {
        getPushStateSynchronizer().resetCurrentState();
        getEmailStateSynchronizer().resetCurrentState();
        OneSignal.saveUserId((String) null);
        OneSignal.saveEmailId((String) null);
        OneSignal.setLastSessionTime(-3660);
    }

    static void updateDeviceInfo(JSONObject jSONObject) {
        getPushStateSynchronizer().updateDeviceInfo(jSONObject);
        getEmailStateSynchronizer().updateDeviceInfo(jSONObject);
    }

    static void updatePushState(JSONObject jSONObject) {
        getPushStateSynchronizer().updateState(jSONObject);
    }

    static void refreshEmailState() {
        getEmailStateSynchronizer().refresh();
    }

    static void setNewSession() {
        getPushStateSynchronizer().setNewSession();
        getEmailStateSynchronizer().setNewSession();
    }

    static boolean getSyncAsNewSession() {
        return getPushStateSynchronizer().getSyncAsNewSession() || getEmailStateSynchronizer().getSyncAsNewSession();
    }

    static void setNewSessionForEmail() {
        getEmailStateSynchronizer().setNewSession();
    }

    static void readyToUpdate(boolean z) {
        getPushStateSynchronizer().readyToUpdate(z);
        getEmailStateSynchronizer().readyToUpdate(z);
    }
}
