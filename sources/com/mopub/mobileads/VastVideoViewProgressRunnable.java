package com.mopub.mobileads;

import android.os.Handler;
import com.mopub.common.ExternalViewabilitySession;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.VastTracker;
import com.mopub.network.TrackingRequest;
import java.util.ArrayList;
import java.util.List;

public class VastVideoViewProgressRunnable extends RepeatingHandlerRunnable {
    private final VastVideoConfig mVastVideoConfig;
    private final VastVideoViewController mVideoViewController;

    public VastVideoViewProgressRunnable(VastVideoViewController vastVideoViewController, VastVideoConfig vastVideoConfig, Handler handler) {
        super(handler);
        Preconditions.checkNotNull(vastVideoViewController);
        Preconditions.checkNotNull(vastVideoConfig);
        this.mVideoViewController = vastVideoViewController;
        this.mVastVideoConfig = vastVideoConfig;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new VastFractionalProgressTracker(VastTracker.MessageType.QUARTILE_EVENT, ExternalViewabilitySession.VideoEvent.AD_STARTED.name(), 0.0f));
        arrayList.add(new VastFractionalProgressTracker(VastTracker.MessageType.QUARTILE_EVENT, ExternalViewabilitySession.VideoEvent.AD_IMPRESSED.name(), 0.0f));
        arrayList.add(new VastFractionalProgressTracker(VastTracker.MessageType.QUARTILE_EVENT, ExternalViewabilitySession.VideoEvent.AD_VIDEO_FIRST_QUARTILE.name(), 0.25f));
        arrayList.add(new VastFractionalProgressTracker(VastTracker.MessageType.QUARTILE_EVENT, ExternalViewabilitySession.VideoEvent.AD_VIDEO_MIDPOINT.name(), 0.5f));
        arrayList.add(new VastFractionalProgressTracker(VastTracker.MessageType.QUARTILE_EVENT, ExternalViewabilitySession.VideoEvent.AD_VIDEO_THIRD_QUARTILE.name(), 0.75f));
        this.mVastVideoConfig.addFractionalTrackers(arrayList);
    }

    public void doWork() {
        int duration = this.mVideoViewController.getDuration();
        int currentPosition = this.mVideoViewController.getCurrentPosition();
        this.mVideoViewController.updateProgressBar();
        if (duration > 0) {
            List<VastTracker> untriggeredTrackersBefore = this.mVastVideoConfig.getUntriggeredTrackersBefore(currentPosition, duration);
            if (!untriggeredTrackersBefore.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (VastTracker next : untriggeredTrackersBefore) {
                    if (next.getMessageType() == VastTracker.MessageType.TRACKING_URL) {
                        arrayList.add(next.getContent());
                    } else if (next.getMessageType() == VastTracker.MessageType.QUARTILE_EVENT) {
                        this.mVideoViewController.handleViewabilityQuartileEvent(next.getContent());
                    }
                    next.setTracked();
                }
                TrackingRequest.makeTrackingHttpRequest((Iterable<String>) new VastMacroHelper(arrayList).withAssetUri(this.mVideoViewController.getNetworkMediaFileUrl()).withContentPlayHead(Integer.valueOf(currentPosition)).getUris(), this.mVideoViewController.getContext());
            }
            this.mVideoViewController.handleIconDisplay(currentPosition);
        }
    }
}
