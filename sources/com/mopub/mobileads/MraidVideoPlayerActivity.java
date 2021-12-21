package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import com.mopub.common.AdType;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.FullAdType;
import com.mopub.common.IntentActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Intents;
import com.mopub.common.util.Reflection;
import com.mopub.mobileads.BaseVideoViewController;
import com.mopub.mraid.MraidVideoViewController;
import java.io.Serializable;

public class MraidVideoPlayerActivity extends BaseVideoPlayerActivity implements BaseVideoViewController.BaseVideoViewControllerListener {
    private static final String NATIVE_VIDEO_VIEW_CONTROLLER = "com.mopub.nativeads.NativeVideoViewController";
    private BaseVideoViewController mBaseVideoController;
    private long mBroadcastIdentifier;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBroadcastIdentifier = getBroadcastIdentifierFromIntent(getIntent());
        try {
            this.mBaseVideoController = createVideoViewController(bundle);
            Serializable serializableExtra = getIntent().getSerializableExtra(DataKeys.CREATIVE_ORIENTATION_KEY);
            CreativeOrientation creativeOrientation = CreativeOrientation.DEVICE;
            if (serializableExtra instanceof CreativeOrientation) {
                creativeOrientation = (CreativeOrientation) serializableExtra;
            }
            DeviceUtils.lockOrientation(this, creativeOrientation);
            this.mBaseVideoController.onCreate();
        } catch (IllegalStateException unused) {
            BaseBroadcastReceiver.broadcastAction(this, this.mBroadcastIdentifier, IntentActions.ACTION_INTERSTITIAL_FAIL);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onPause();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onDestroy();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onSaveInstanceState(bundle);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onConfigurationChanged(configuration);
        }
    }

    public void onBackPressed() {
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null && baseVideoViewController.backButtonEnabled()) {
            super.onBackPressed();
            this.mBaseVideoController.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onActivityResult(i, i2, intent);
        }
    }

    private BaseVideoViewController createVideoViewController(Bundle bundle) throws IllegalStateException {
        String stringExtra = getIntent().getStringExtra("video_view_class_name");
        if (FullAdType.VAST.equals(stringExtra)) {
            return new VastVideoViewController(this, getIntent().getExtras(), bundle, this.mBroadcastIdentifier, this);
        } else if ("vast_new".equals(stringExtra)) {
            return new VastVideoViewControllerTwo(this, getIntent().getExtras(), bundle, this.mBroadcastIdentifier, this);
        } else if (AdType.MRAID.equals(stringExtra)) {
            return new MraidVideoViewController(this, getIntent().getExtras(), bundle, this);
        } else {
            if ("native".equals(stringExtra)) {
                Class[] clsArr = {Context.class, Bundle.class, Bundle.class, BaseVideoViewController.BaseVideoViewControllerListener.class};
                Object[] objArr = {this, getIntent().getExtras(), bundle, this};
                if (Reflection.classFound(NATIVE_VIDEO_VIEW_CONTROLLER)) {
                    try {
                        return (BaseVideoViewController) Reflection.instantiateClassWithConstructor(NATIVE_VIDEO_VIEW_CONTROLLER, BaseVideoViewController.class, clsArr, objArr);
                    } catch (Exception unused) {
                        throw new IllegalStateException("Missing native video module");
                    }
                } else {
                    throw new IllegalStateException("Missing native video module");
                }
            } else {
                throw new IllegalStateException("Unsupported video type: " + stringExtra);
            }
        }
    }

    public void onSetContentView(View view) {
        setContentView(view);
    }

    public void onSetRequestedOrientation(int i) {
        setRequestedOrientation(i);
    }

    public void onFinish() {
        finish();
    }

    public void onStartActivityForResult(Class<? extends Activity> cls, int i, Bundle bundle) {
        if (cls != null) {
            try {
                startActivityForResult(Intents.getStartActivityIntent(this, cls, bundle), i);
            } catch (ActivityNotFoundException unused) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "Activity " + cls.getName() + " not found. Did you declare it in your AndroidManifest.xml?");
            }
        }
    }

    protected static long getBroadcastIdentifierFromIntent(Intent intent) {
        return intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public BaseVideoViewController getBaseVideoViewController() {
        return this.mBaseVideoController;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setBaseVideoViewController(BaseVideoViewController baseVideoViewController) {
        this.mBaseVideoController = baseVideoViewController;
    }
}
