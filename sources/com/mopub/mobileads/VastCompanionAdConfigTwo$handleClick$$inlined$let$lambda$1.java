package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Bundle;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VastCompanionAdConfigTwo.kt */
public final class VastCompanionAdConfigTwo$handleClick$$inlined$let$lambda$1 implements UrlHandler.ResultActions {
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ String $dspCreativeId$inlined;
    final /* synthetic */ int $requestCode$inlined;

    public void urlHandlingFailed(String str, UrlAction urlAction) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(urlAction, "lastFailedUrlAction");
    }

    VastCompanionAdConfigTwo$handleClick$$inlined$let$lambda$1(String str, Context context, int i) {
        this.$dspCreativeId$inlined = str;
        this.$context$inlined = context;
        this.$requestCode$inlined = i;
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
            Class<MoPubBrowser> cls = MoPubBrowser.class;
            try {
                ((Activity) this.$context$inlined).startActivityForResult(Intents.getStartActivityIntent(this.$context$inlined, cls, bundle), this.$requestCode$inlined);
            } catch (ActivityNotFoundException unused) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Activity " + cls.getName() + " not found. Did you " + "declare it in your AndroidManifest.xml?");
            }
        }
    }
}
