package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import com.mopub.common.AdType;
import com.mopub.common.Constants;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.FullAdType;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Utils;

public class BaseVideoPlayerActivity extends Activity {
    public static final String VIDEO_CLASS_EXTRAS_KEY = "video_view_class_name";
    public static final String VIDEO_URL = "video_url";

    public static void startMraid(Context context, String str) {
        try {
            context.startActivity(createIntentMraid(context, str));
        } catch (ActivityNotFoundException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentMraid(Context context, String str) {
        Intent intent = new Intent(context, MraidVideoPlayerActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("video_view_class_name", AdType.MRAID);
        intent.putExtra("video_url", str);
        return intent;
    }

    static void startVast(Context context, VastVideoConfig vastVideoConfig, long j, CreativeOrientation creativeOrientation) {
        try {
            context.startActivity(createIntentVast(context, vastVideoConfig, j, creativeOrientation));
        } catch (ActivityNotFoundException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Attempt to start with VastVideoConfig failed. Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static void startVast(Context context, VastVideoConfigTwo vastVideoConfigTwo, long j, CreativeOrientation creativeOrientation) {
        try {
            context.startActivity(createIntentVast(context, vastVideoConfigTwo, j, creativeOrientation));
        } catch (ActivityNotFoundException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Attempt to start with VastVideoConfigTwo failed. Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentVast(Context context, VastVideoConfig vastVideoConfig, long j, CreativeOrientation creativeOrientation) {
        Intent intent = new Intent(context, MraidVideoPlayerActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("video_view_class_name", FullAdType.VAST);
        intent.putExtra(VastVideoViewControllerTwo.VAST_VIDEO_CONFIG, vastVideoConfig);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        intent.putExtra(DataKeys.CREATIVE_ORIENTATION_KEY, creativeOrientation);
        return intent;
    }

    static Intent createIntentVast(Context context, VastVideoConfigTwo vastVideoConfigTwo, long j, CreativeOrientation creativeOrientation) {
        Intent intent = new Intent(context, MraidVideoPlayerActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("video_view_class_name", "vast_new");
        intent.putExtra(VastVideoViewControllerTwo.VAST_VIDEO_CONFIG, vastVideoConfigTwo);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        intent.putExtra(DataKeys.CREATIVE_ORIENTATION_KEY, creativeOrientation);
        return intent;
    }

    public static void startNativeVideo(Context context, long j, VastVideoConfig vastVideoConfig) {
        try {
            context.startActivity(createIntentNativeVideo(context, j, vastVideoConfig));
        } catch (ActivityNotFoundException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    public static Intent createIntentNativeVideo(Context context, long j, VastVideoConfig vastVideoConfig) {
        Intent intent = new Intent(context, MraidVideoPlayerActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("video_view_class_name", "native");
        intent.putExtra(Constants.NATIVE_VIDEO_ID, j);
        intent.putExtra(Constants.NATIVE_VAST_VIDEO_CONFIG, vastVideoConfig);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Utils.hideNavigationBar(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        if (audioManager != null) {
            audioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
        }
    }
}
