package com.integralads.avid.library.mopub.video;

import com.integralads.avid.library.mopub.base.AvidBaseListenerImpl;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidBridgeManager;
import org.json.JSONException;
import org.json.JSONObject;

public class AvidVideoPlaybackListenerImpl extends AvidBaseListenerImpl implements AvidVideoPlaybackListener {
    public static final String AD_CLICK_THRU = "AdClickThru";
    public static final String AD_DURATION = "adDuration";
    public static final String AD_DURATION_CHANGE = "AdDurationChange";
    public static final String AD_ENTERED_FULLSCREEN = "AdEnteredFullscreen";
    public static final String AD_ERROR = "AdError";
    public static final String AD_EXITED_FULLSCREEN = "AdExitedFullscreen";
    public static final String AD_EXPANDED_CHANGE = "AdExpandedChange";
    public static final String AD_IMPRESSION = "AdImpression";
    public static final String AD_LOADED = "AdLoaded";
    public static final String AD_PAUSED = "AdPaused";
    public static final String AD_PLAYING = "AdPlaying";
    public static final String AD_REMAINING_TIME = "adDuration";
    public static final String AD_SKIPPED = "AdSkipped";
    public static final String AD_STARTED = "AdStarted";
    public static final String AD_STOPPED = "AdStopped";
    public static final String AD_USER_ACCEPT_INVITATION = "AdUserAcceptInvitation";
    public static final String AD_USER_CLOSE = "AdUserClose";
    public static final String AD_USER_MINIMIZE = "AdUserMinimize";
    public static final String AD_VIDEO_COMPLETE = "AdVideoComplete";
    public static final String AD_VIDEO_FIRST_QUARTILE = "AdVideoFirstQuartile";
    public static final String AD_VIDEO_MIDPOINT = "AdVideoMidpoint";
    public static final String AD_VIDEO_START = "AdVideoStart";
    public static final String AD_VIDEO_THIRD_QUARTILE = "AdVideoThirdQuartile";
    public static final String AD_VOLUME_CHANGE = "AdVolumeChange";
    public static final String MESSAGE = "message";
    public static final String VOLUME = "volume";

    public AvidVideoPlaybackListenerImpl(InternalAvidAdSession internalAvidAdSession, AvidBridgeManager avidBridgeManager) {
        super(internalAvidAdSession, avidBridgeManager);
    }

    public void recordAdImpressionEvent() {
        publishVideoEvent(AD_IMPRESSION, (JSONObject) null);
    }

    public void recordAdStartedEvent() {
        publishVideoEvent(AD_STARTED, (JSONObject) null);
    }

    public void recordAdLoadedEvent() {
        publishVideoEvent(AD_LOADED, (JSONObject) null);
    }

    public void recordAdVideoStartEvent() {
        publishVideoEvent(AD_VIDEO_START, (JSONObject) null);
    }

    public void recordAdStoppedEvent() {
        publishVideoEvent(AD_STOPPED, (JSONObject) null);
    }

    public void recordAdCompleteEvent() {
        publishVideoEvent(AD_VIDEO_COMPLETE, (JSONObject) null);
    }

    public void recordAdClickThruEvent() {
        publishVideoEvent(AD_CLICK_THRU, (JSONObject) null);
    }

    public void recordAdVideoFirstQuartileEvent() {
        publishVideoEvent(AD_VIDEO_FIRST_QUARTILE, (JSONObject) null);
    }

    public void recordAdVideoMidpointEvent() {
        publishVideoEvent(AD_VIDEO_MIDPOINT, (JSONObject) null);
    }

    public void recordAdVideoThirdQuartileEvent() {
        publishVideoEvent(AD_VIDEO_THIRD_QUARTILE, (JSONObject) null);
    }

    public void recordAdPausedEvent() {
        publishVideoEvent(AD_PAUSED, (JSONObject) null);
    }

    public void recordAdPlayingEvent() {
        publishVideoEvent(AD_PLAYING, (JSONObject) null);
    }

    public void recordAdExpandedChangeEvent() {
        publishVideoEvent(AD_EXPANDED_CHANGE, (JSONObject) null);
    }

    public void recordAdUserMinimizeEvent() {
        publishVideoEvent(AD_USER_MINIMIZE, (JSONObject) null);
    }

    public void recordAdUserAcceptInvitationEvent() {
        publishVideoEvent(AD_USER_ACCEPT_INVITATION, (JSONObject) null);
    }

    public void recordAdUserCloseEvent() {
        publishVideoEvent(AD_USER_CLOSE, (JSONObject) null);
    }

    public void recordAdSkippedEvent() {
        publishVideoEvent(AD_SKIPPED, (JSONObject) null);
    }

    public void recordAdVolumeChangeEvent(Integer num) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(VOLUME, num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent(AD_VOLUME_CHANGE, jSONObject);
    }

    public void recordAdEnteredFullscreenEvent() {
        publishVideoEvent(AD_ENTERED_FULLSCREEN, (JSONObject) null);
    }

    public void recordAdExitedFullscreenEvent() {
        publishVideoEvent(AD_EXITED_FULLSCREEN, (JSONObject) null);
    }

    public void recordAdDurationChangeEvent(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("adDuration", str);
            jSONObject.put("adDuration", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent(AD_DURATION_CHANGE, jSONObject);
    }

    public void recordAdError(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MESSAGE, str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent(AD_ERROR, jSONObject);
    }

    private void publishVideoEvent(String str, JSONObject jSONObject) {
        assertSessionIsNotEnded();
        assertSessionIsReady();
        getAvidBridgeManager().publishVideoEvent(str, jSONObject);
    }

    private void assertSessionIsReady() {
        if (!getAvidAdSession().isReady()) {
            throw new IllegalStateException("The AVID ad session is not ready. Please ensure you have called recordReadyEvent for the deferred AVID ad session before recording any video event.");
        }
    }
}
