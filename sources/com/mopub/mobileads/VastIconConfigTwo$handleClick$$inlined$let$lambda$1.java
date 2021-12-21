package com.mopub.mobileads;

import android.content.Context;
import android.os.Bundle;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VastIconConfigTwo.kt */
public final class VastIconConfigTwo$handleClick$$inlined$let$lambda$1 implements UrlHandler.ResultActions {
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ String $dspCreativeId$inlined;

    public void urlHandlingFailed(String str, UrlAction urlAction) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(urlAction, "lastFailedUrlAction");
    }

    VastIconConfigTwo$handleClick$$inlined$let$lambda$1(String str, Context context) {
        this.$dspCreativeId$inlined = str;
        this.$context$inlined = context;
    }

    public void urlHandlingSucceeded(String str, UrlAction urlAction) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(urlAction, "urlAction");
        if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
            Bundle bundle = new Bundle();
            bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, str);
            CharSequence charSequence = this.$dspCreativeId$inlined;
            if (!(charSequence == null || charSequence.length() == 0)) {
                bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, this.$dspCreativeId$inlined);
            }
            try {
                Intents.startActivity(this.$context$inlined, Intents.getStartActivityIntent(this.$context$inlined, MoPubBrowser.class, bundle));
            } catch (IntentNotResolvableException e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, e.getMessage());
            }
        }
    }
}
