package com.mopub.mobileads;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VastVideoViewControllerTwo.kt */
public final class VastVideoViewControllerTwo$createWebView$$inlined$also$lambda$2 extends WebViewClient {
    final /* synthetic */ VastCompanionAdConfigTwo $this_createWebView$inlined;
    final /* synthetic */ VastVideoViewControllerTwo this$0;

    VastVideoViewControllerTwo$createWebView$$inlined$also$lambda$2(VastVideoViewControllerTwo vastVideoViewControllerTwo, VastCompanionAdConfigTwo vastCompanionAdConfigTwo) {
        this.this$0 = vastVideoViewControllerTwo;
        this.$this_createWebView$inlined = vastCompanionAdConfigTwo;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Intrinsics.checkParameterIsNotNull(webView, "view");
        Intrinsics.checkParameterIsNotNull(str, "url");
        VastCompanionAdConfigTwo access$getVastCompanionAdConfig$p = this.this$0.vastCompanionAdConfig;
        if (access$getVastCompanionAdConfig$p != null) {
            Context context = this.this$0.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            access$getVastCompanionAdConfig$p.handleClick(context, 1, str, this.this$0.getVastVideoConfig().getDspCreativeId());
        }
        return true;
    }
}
