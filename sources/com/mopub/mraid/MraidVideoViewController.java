package com.mopub.mraid;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.mobileads.BaseVideoViewController;

public class MraidVideoViewController extends BaseVideoViewController {
    private static final float CLOSE_BUTTON_PADDING = 8.0f;
    private static final float CLOSE_BUTTON_SIZE = 50.0f;
    private int mButtonPadding;
    private int mButtonSize;
    /* access modifiers changed from: private */
    public ImageButton mCloseButton;
    private final VideoView mVideoView;

    /* access modifiers changed from: protected */
    public void onBackPressed() {
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
    }

    /* access modifiers changed from: protected */
    public void onPause() {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
    }

    public MraidVideoViewController(Context context, Bundle bundle, Bundle bundle2, BaseVideoViewController.BaseVideoViewControllerListener baseVideoViewControllerListener) {
        super(context, (Long) null, baseVideoViewControllerListener);
        VideoView videoView = new VideoView(context);
        this.mVideoView = videoView;
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVideoScalingMode(1);
            }
        });
        this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                MraidVideoViewController.this.mCloseButton.setVisibility(0);
                MraidVideoViewController.this.videoCompleted(true);
            }
        });
        this.mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                MraidVideoViewController.this.mCloseButton.setVisibility(0);
                MraidVideoViewController.this.videoError(false);
                return false;
            }
        });
        this.mVideoView.setVideoPath(bundle.getString("video_url"));
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        super.onCreate();
        this.mButtonSize = Dips.asIntPixels(CLOSE_BUTTON_SIZE, getContext());
        this.mButtonPadding = Dips.asIntPixels(8.0f, getContext());
        createInterstitialCloseButton();
        this.mCloseButton.setVisibility(8);
        this.mVideoView.start();
    }

    /* access modifiers changed from: protected */
    public VideoView getVideoView() {
        return this.mVideoView;
    }

    private void createInterstitialCloseButton() {
        this.mCloseButton = new ImageButton(getContext());
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842919}, Drawables.INTERSTITIAL_CLOSE_BUTTON_NORMAL.createDrawable(getContext()));
        stateListDrawable.addState(new int[]{16842919}, Drawables.INTERSTITIAL_CLOSE_BUTTON_PRESSED.createDrawable(getContext()));
        this.mCloseButton.setImageDrawable(stateListDrawable);
        this.mCloseButton.setBackgroundDrawable((Drawable) null);
        this.mCloseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MraidVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
            }
        });
        int i = this.mButtonSize;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(11);
        int i2 = this.mButtonPadding;
        layoutParams.setMargins(i2, 0, i2, 0);
        getLayout().addView(this.mCloseButton, layoutParams);
    }
}
