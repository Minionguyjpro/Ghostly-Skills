package com.mopub.mobileads;

import android.os.Handler;
import com.mopub.common.ExternalViewabilitySession;
import com.mopub.mobileads.VastFractionalProgressTrackerTwo;
import com.mopub.mobileads.VastTrackerTwo;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

@Mockable
/* compiled from: VastVideoViewProgressRunnableTwo.kt */
public class VastVideoViewProgressRunnableTwo extends RepeatingHandlerRunnable {
    private final VastVideoConfigTwo vastVideoConfig;
    private final VastVideoViewControllerTwo videoViewController;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VastTrackerTwo.MessageType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[VastTrackerTwo.MessageType.TRACKING_URL.ordinal()] = 1;
            $EnumSwitchMapping$0[VastTrackerTwo.MessageType.QUARTILE_EVENT.ordinal()] = 2;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VastVideoViewProgressRunnableTwo(VastVideoViewControllerTwo vastVideoViewControllerTwo, VastVideoConfigTwo vastVideoConfigTwo, Handler handler) {
        super(handler);
        Intrinsics.checkParameterIsNotNull(vastVideoViewControllerTwo, "videoViewController");
        Intrinsics.checkParameterIsNotNull(vastVideoConfigTwo, "vastVideoConfig");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.videoViewController = vastVideoViewControllerTwo;
        this.vastVideoConfig = vastVideoConfigTwo;
        List arrayList = new ArrayList();
        arrayList.add(new VastFractionalProgressTrackerTwo.Builder(ExternalViewabilitySession.VideoEvent.AD_STARTED.name(), 0.0f).messageType(VastTrackerTwo.MessageType.QUARTILE_EVENT).build());
        arrayList.add(new VastFractionalProgressTrackerTwo.Builder(ExternalViewabilitySession.VideoEvent.AD_IMPRESSED.name(), 0.0f).messageType(VastTrackerTwo.MessageType.QUARTILE_EVENT).build());
        arrayList.add(new VastFractionalProgressTrackerTwo.Builder(ExternalViewabilitySession.VideoEvent.AD_VIDEO_FIRST_QUARTILE.name(), 0.25f).messageType(VastTrackerTwo.MessageType.QUARTILE_EVENT).build());
        arrayList.add(new VastFractionalProgressTrackerTwo.Builder(ExternalViewabilitySession.VideoEvent.AD_VIDEO_MIDPOINT.name(), 0.5f).messageType(VastTrackerTwo.MessageType.QUARTILE_EVENT).build());
        arrayList.add(new VastFractionalProgressTrackerTwo.Builder(ExternalViewabilitySession.VideoEvent.AD_VIDEO_THIRD_QUARTILE.name(), 0.75f).messageType(VastTrackerTwo.MessageType.QUARTILE_EVENT).build());
        this.vastVideoConfig.addFractionalTrackers(arrayList);
    }

    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doWork() {
        /*
            r8 = this;
            com.mopub.mobileads.VastVideoViewControllerTwo r0 = r8.videoViewController
            int r0 = r0.getDuration()
            com.mopub.mobileads.VastVideoViewControllerTwo r1 = r8.videoViewController
            int r1 = r1.getCurrentPosition()
            com.mopub.mobileads.VastVideoViewControllerTwo r2 = r8.videoViewController
            r2.updateProgressBar()
            if (r0 > 0) goto L_0x0014
            return
        L_0x0014:
            com.mopub.mobileads.VastVideoConfigTwo r2 = r8.vastVideoConfig
            java.util.List r0 = r2.getUntriggeredTrackersBefore(r1, r0)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r0 = r0.iterator()
        L_0x0027:
            boolean r3 = r0.hasNext()
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x0063
            java.lang.Object r3 = r0.next()
            com.mopub.mobileads.VastTrackerTwo r3 = (com.mopub.mobileads.VastTrackerTwo) r3
            r3.setTracked()
            com.mopub.mobileads.VastTrackerTwo$MessageType r6 = r3.getMessageType()
            int[] r7 = com.mopub.mobileads.VastVideoViewProgressRunnableTwo.WhenMappings.$EnumSwitchMapping$0
            int r6 = r6.ordinal()
            r6 = r7[r6]
            if (r6 == r5) goto L_0x0059
            r5 = 2
            if (r6 != r5) goto L_0x0053
            com.mopub.mobileads.VastVideoViewControllerTwo r5 = r8.videoViewController
            java.lang.String r3 = r3.getContent()
            r5.handleViewabilityQuartileEvent$mopub_sdk_base_release(r3)
            goto L_0x005d
        L_0x0053:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L_0x0059:
            java.lang.String r4 = r3.getContent()
        L_0x005d:
            if (r4 == 0) goto L_0x0027
            r2.add(r4)
            goto L_0x0027
        L_0x0063:
            java.util.List r2 = (java.util.List) r2
            r0 = r2
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ r5
            if (r0 == 0) goto L_0x0070
            r4 = r2
        L_0x0070:
            if (r4 == 0) goto L_0x009d
            com.mopub.mobileads.VastMacroHelper r0 = new com.mopub.mobileads.VastMacroHelper
            r0.<init>(r4)
            com.mopub.mobileads.VastVideoViewControllerTwo r2 = r8.videoViewController
            java.lang.String r2 = r2.getNetworkMediaFileUrl()
            com.mopub.mobileads.VastMacroHelper r0 = r0.withAssetUri(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            com.mopub.mobileads.VastMacroHelper r0 = r0.withContentPlayHead(r2)
            java.lang.String r2 = "VastMacroHelper(it)\n    …PlayHead(currentPosition)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            java.util.List r0 = r0.getUris()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            com.mopub.mobileads.VastVideoViewControllerTwo r2 = r8.videoViewController
            android.content.Context r2 = r2.getContext()
            com.mopub.network.TrackingRequest.makeTrackingHttpRequest((java.lang.Iterable<java.lang.String>) r0, (android.content.Context) r2)
        L_0x009d:
            com.mopub.mobileads.VastVideoViewControllerTwo r0 = r8.videoViewController
            r0.handleIconDisplay(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VastVideoViewProgressRunnableTwo.doWork():void");
    }
}
