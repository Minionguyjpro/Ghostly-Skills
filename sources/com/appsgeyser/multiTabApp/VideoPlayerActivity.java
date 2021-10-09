package com.appsgeyser.multiTabApp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;
import com.appsgeyser.multiTabApp.utils.ThemeUtils;
import com.wGhostlySkills_14510784.R;

public class VideoPlayerActivity extends Activity {
    /* access modifiers changed from: private */
    public VideoView mVideo = null;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Uri data = getIntent().getData();
        ThemeUtils.setCurrentThemeWithActionBar(this);
        setContentView(R.layout.video_layout);
        this.mVideo = (VideoView) findViewById(R.id.video);
        this.progressBar = (ProgressBar) findViewById(R.id.video_progress_bar);
        this.mVideo.setVideoURI(data);
        this.mVideo.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                VideoPlayerActivity.this.mVideo.setOnErrorListener((MediaPlayer.OnErrorListener) null);
                Intent intent = VideoPlayerActivity.this.getIntent();
                if (intent == null) {
                    return false;
                }
                Intent intent2 = new Intent("android.intent.action.VIEW", intent.getData());
                intent2.setFlags(268435456);
                VideoPlayerActivity.this.getApplicationContext().startActivity(intent2);
                VideoPlayerActivity.this.finish();
                return false;
            }
        });
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(this.mVideo);
        this.mVideo.setMediaController(mediaController);
        this.mVideo.start();
        this.progressBar.setVisibility(0);
        this.mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                VideoPlayerActivity.this.progressBar.setVisibility(8);
                mediaPlayer.start();
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                        VideoPlayerActivity.this.progressBar.setVisibility(8);
                        mediaPlayer.start();
                    }
                });
            }
        });
    }
}
