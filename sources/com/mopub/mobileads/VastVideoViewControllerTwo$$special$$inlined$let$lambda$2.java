package com.mopub.mobileads;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VastVideoViewControllerTwo.kt */
public final class VastVideoViewControllerTwo$$special$$inlined$let$lambda$2 extends WebViewClient {
    final /* synthetic */ VastIconConfigTwo $iconConfig$inlined;
    final /* synthetic */ VastVideoViewControllerTwo this$0;

    VastVideoViewControllerTwo$$special$$inlined$let$lambda$2(VastIconConfigTwo vastIconConfigTwo, VastVideoViewControllerTwo vastVideoViewControllerTwo) {
        this.$iconConfig$inlined = vastIconConfigTwo;
        this.this$0 = vastVideoViewControllerTwo;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Intrinsics.checkParameterIsNotNull(webView, "view");
        Intrinsics.checkParameterIsNotNull(str, "url");
        VastIconConfigTwo vastIconConfig = this.this$0.getVastIconConfig();
        if (vastIconConfig == null) {
            return true;
        }
        Context context = this.this$0.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        vastIconConfig.handleClick(context, str, this.this$0.getVastVideoConfig().getDspCreativeId());
        return true;
    }
}
