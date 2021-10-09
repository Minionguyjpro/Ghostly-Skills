package com.mopub.mobileads;

import android.content.Context;
import com.mopub.mobileads.VastWebView;
import com.mopub.network.TrackingRequest;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VastVideoViewControllerTwo.kt */
final class VastVideoViewControllerTwo$$special$$inlined$let$lambda$1 implements VastWebView.VastWebViewClickListener {
    final /* synthetic */ VastIconConfigTwo $iconConfig$inlined;
    final /* synthetic */ VastVideoViewControllerTwo this$0;

    VastVideoViewControllerTwo$$special$$inlined$let$lambda$1(VastIconConfigTwo vastIconConfigTwo, VastVideoViewControllerTwo vastVideoViewControllerTwo) {
        this.$iconConfig$inlined = vastIconConfigTwo;
        this.this$0 = vastVideoViewControllerTwo;
    }

    public final void onVastWebViewClick() {
        TrackingRequest.makeVastTrackingTwoHttpRequest(this.$iconConfig$inlined.getClickTrackingUris(), (VastErrorCode) null, Integer.valueOf(this.this$0.getCurrentPosition()), this.this$0.getNetworkMediaFileUrl(), this.this$0.getContext());
        VastIconConfigTwo vastIconConfig = this.this$0.getVastIconConfig();
        if (vastIconConfig != null) {
            Context context = this.this$0.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            vastIconConfig.handleClick(context, (String) null, this.this$0.getVastVideoConfig().getDspCreativeId());
        }
    }
}
