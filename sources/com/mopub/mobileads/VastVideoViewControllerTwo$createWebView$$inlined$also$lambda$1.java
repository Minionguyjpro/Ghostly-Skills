package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.IntentActions;
import com.mopub.mobileads.VastWebView;
import com.mopub.network.TrackingRequest;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VastVideoViewControllerTwo.kt */
final class VastVideoViewControllerTwo$createWebView$$inlined$also$lambda$1 implements VastWebView.VastWebViewClickListener {
    final /* synthetic */ VastCompanionAdConfigTwo $this_createWebView$inlined;
    final /* synthetic */ VastVideoViewControllerTwo this$0;

    VastVideoViewControllerTwo$createWebView$$inlined$also$lambda$1(VastVideoViewControllerTwo vastVideoViewControllerTwo, VastCompanionAdConfigTwo vastCompanionAdConfigTwo) {
        this.this$0 = vastVideoViewControllerTwo;
        this.$this_createWebView$inlined = vastCompanionAdConfigTwo;
    }

    public final void onVastWebViewClick() {
        this.this$0.broadcastAction(IntentActions.ACTION_INTERSTITIAL_CLICK);
        this.this$0.setClosing(true);
        TrackingRequest.makeVastTrackingTwoHttpRequest(this.$this_createWebView$inlined.getClickTrackers(), (VastErrorCode) null, Integer.valueOf(this.this$0.getCurrentPosition()), (String) null, this.this$0.getContext());
        VastCompanionAdConfigTwo vastCompanionAdConfigTwo = this.$this_createWebView$inlined;
        Context context = this.this$0.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        vastCompanionAdConfigTwo.handleClick(context, 1, (String) null, this.this$0.getVastVideoConfig().getDspCreativeId());
    }
}
