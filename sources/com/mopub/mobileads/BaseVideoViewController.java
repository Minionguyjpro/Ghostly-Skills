package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.mopub.common.IntentActions;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;

public abstract class BaseVideoViewController {
    private final BaseVideoViewControllerListener mBaseVideoViewControllerListener;
    private Long mBroadcastIdentifier;
    private final Context mContext;
    private final RelativeLayout mLayout = new RelativeLayout(this.mContext);

    public interface BaseVideoViewControllerListener {
        void onFinish();

        void onSetContentView(View view);

        void onSetRequestedOrientation(int i);

        void onStartActivityForResult(Class<? extends Activity> cls, int i, Bundle bundle);
    }

    public boolean backButtonEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract View getVideoView();

    /* access modifiers changed from: package-private */
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    /* access modifiers changed from: protected */
    public abstract void onBackPressed();

    /* access modifiers changed from: protected */
    public abstract void onConfigurationChanged(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract void onDestroy();

    /* access modifiers changed from: protected */
    public abstract void onPause();

    /* access modifiers changed from: protected */
    public abstract void onResume();

    /* access modifiers changed from: protected */
    public abstract void onSaveInstanceState(Bundle bundle);

    protected BaseVideoViewController(Context context, Long l, BaseVideoViewControllerListener baseVideoViewControllerListener) {
        Preconditions.checkNotNull(baseVideoViewControllerListener);
        this.mContext = context;
        this.mBroadcastIdentifier = l;
        this.mBaseVideoViewControllerListener = baseVideoViewControllerListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.mLayout.addView(getVideoView(), 0, layoutParams);
        this.mBaseVideoViewControllerListener.onSetContentView(this.mLayout);
    }

    /* access modifiers changed from: protected */
    public BaseVideoViewControllerListener getBaseVideoViewControllerListener() {
        return this.mBaseVideoViewControllerListener;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    public ViewGroup getLayout() {
        return this.mLayout;
    }

    /* access modifiers changed from: protected */
    public void videoError(boolean z) {
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Video cannot be played.");
        broadcastAction(IntentActions.ACTION_INTERSTITIAL_FAIL);
        if (z) {
            this.mBaseVideoViewControllerListener.onFinish();
        }
    }

    /* access modifiers changed from: protected */
    public void videoCompleted(boolean z) {
        if (z) {
            this.mBaseVideoViewControllerListener.onFinish();
        }
    }

    /* access modifiers changed from: package-private */
    public void broadcastAction(String str) {
        Long l = this.mBroadcastIdentifier;
        if (l != null) {
            BaseBroadcastReceiver.broadcastAction(this.mContext, l.longValue(), str);
            return;
        }
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Tried to broadcast a video event without a broadcast identifier to send to.");
    }
}
