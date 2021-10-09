package androidx.media2.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.media2.common.BaseResult;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.VideoSize;
import androidx.media2.session.MediaController;
import androidx.media2.widget.PlayerWrapper;
import androidx.media2.widget.SelectiveLayout;
import androidx.media2.widget.SubtitleController;
import androidx.media2.widget.VideoViewInterface;
import androidx.palette.graphics.Palette;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class VideoView extends SelectiveLayout {
    static final boolean DEBUG = Log.isLoggable("VideoView", 3);
    int mAudioTrackCount;
    VideoViewInterface mCurrentView;
    MediaControlView mMediaControlView;
    MusicView mMusicView;
    PlayerWrapper mPlayer;
    SessionPlayer.TrackInfo mSelectedSubtitleTrackInfo;
    SelectiveLayout.LayoutParams mSelectiveLayoutParams;
    SubtitleAnchorView mSubtitleAnchorView;
    SubtitleController mSubtitleController;
    Map<SessionPlayer.TrackInfo, SubtitleTrack> mSubtitleTracks;
    private final VideoViewInterface.SurfaceListener mSurfaceListener;
    VideoSurfaceView mSurfaceView;
    VideoViewInterface mTargetView;
    VideoTextureView mTextureView;
    int mVideoTrackCount;
    OnViewTypeChangedListener mViewTypeChangedListener;

    public interface OnViewTypeChangedListener {
        void onViewTypeChanged(View view, int i);
    }

    public CharSequence getAccessibilityClassName() {
        return "androidx.media2.widget.VideoView";
    }

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
    }

    public /* bridge */ /* synthetic */ boolean shouldDelayChildPressedState() {
        return super.shouldDelayChildPressedState();
    }

    public VideoView(Context context) {
        this(context, (AttributeSet) null);
    }

    public VideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSurfaceListener = new VideoViewInterface.SurfaceListener() {
            public void onSurfaceCreated(View view, int i, int i2) {
                if (VideoView.DEBUG) {
                    Log.d("VideoView", "onSurfaceCreated(), width/height: " + i + "/" + i2 + ", " + view.toString());
                }
                if (view == VideoView.this.mTargetView && VideoView.this.isAggregatedVisible()) {
                    VideoView.this.mTargetView.assignSurfaceToPlayerWrapper(VideoView.this.mPlayer);
                }
            }

            public void onSurfaceDestroyed(View view) {
                if (VideoView.DEBUG) {
                    Log.d("VideoView", "onSurfaceDestroyed(). " + view.toString());
                }
            }

            public void onSurfaceChanged(View view, int i, int i2) {
                if (VideoView.DEBUG) {
                    Log.d("VideoView", "onSurfaceChanged(). width/height: " + i + "/" + i2 + ", " + view.toString());
                }
            }

            public void onSurfaceTakeOverDone(VideoViewInterface videoViewInterface) {
                if (videoViewInterface != VideoView.this.mTargetView) {
                    Log.w("VideoView", "onSurfaceTakeOverDone(). view is not targetView. ignore.: " + videoViewInterface);
                    return;
                }
                if (VideoView.DEBUG) {
                    Log.d("VideoView", "onSurfaceTakeOverDone(). Now current view is: " + videoViewInterface);
                }
                if (videoViewInterface != VideoView.this.mCurrentView) {
                    ((View) VideoView.this.mCurrentView).setVisibility(8);
                    VideoView.this.mCurrentView = videoViewInterface;
                    if (VideoView.this.mViewTypeChangedListener != null) {
                        VideoView.this.mViewTypeChangedListener.onViewTypeChanged(VideoView.this, videoViewInterface.getViewType());
                    }
                }
            }
        };
        initialize(context, attributeSet);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        int i;
        this.mSelectedSubtitleTrackInfo = null;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mTextureView = new VideoTextureView(context);
        this.mSurfaceView = new VideoSurfaceView(context);
        this.mTextureView.setSurfaceListener(this.mSurfaceListener);
        this.mSurfaceView.setSurfaceListener(this.mSurfaceListener);
        addView(this.mTextureView);
        addView(this.mSurfaceView);
        SelectiveLayout.LayoutParams layoutParams = new SelectiveLayout.LayoutParams();
        this.mSelectiveLayoutParams = layoutParams;
        layoutParams.forceMatchParent = true;
        SubtitleAnchorView subtitleAnchorView = new SubtitleAnchorView(context);
        this.mSubtitleAnchorView = subtitleAnchorView;
        subtitleAnchorView.setBackgroundColor(0);
        addView(this.mSubtitleAnchorView, this.mSelectiveLayoutParams);
        SubtitleController subtitleController = new SubtitleController(context, (MediaTimeProvider) null, new SubtitleController.Listener() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: androidx.media2.common.SessionPlayer$TrackInfo} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSubtitleTrackSelected(androidx.media2.widget.SubtitleTrack r5) {
                /*
                    r4 = this;
                    r0 = 0
                    if (r5 != 0) goto L_0x0011
                    androidx.media2.widget.VideoView r5 = androidx.media2.widget.VideoView.this
                    r5.mSelectedSubtitleTrackInfo = r0
                    androidx.media2.widget.VideoView r5 = androidx.media2.widget.VideoView.this
                    androidx.media2.widget.SubtitleAnchorView r5 = r5.mSubtitleAnchorView
                    r0 = 8
                    r5.setVisibility(r0)
                    return
                L_0x0011:
                    androidx.media2.widget.VideoView r1 = androidx.media2.widget.VideoView.this
                    java.util.Map<androidx.media2.common.SessionPlayer$TrackInfo, androidx.media2.widget.SubtitleTrack> r1 = r1.mSubtitleTracks
                    java.util.Set r1 = r1.entrySet()
                    java.util.Iterator r1 = r1.iterator()
                L_0x001d:
                    boolean r2 = r1.hasNext()
                    if (r2 == 0) goto L_0x0036
                    java.lang.Object r2 = r1.next()
                    java.util.Map$Entry r2 = (java.util.Map.Entry) r2
                    java.lang.Object r3 = r2.getValue()
                    if (r3 != r5) goto L_0x001d
                    java.lang.Object r5 = r2.getKey()
                    r0 = r5
                    androidx.media2.common.SessionPlayer$TrackInfo r0 = (androidx.media2.common.SessionPlayer.TrackInfo) r0
                L_0x0036:
                    if (r0 == 0) goto L_0x0044
                    androidx.media2.widget.VideoView r5 = androidx.media2.widget.VideoView.this
                    r5.mSelectedSubtitleTrackInfo = r0
                    androidx.media2.widget.VideoView r5 = androidx.media2.widget.VideoView.this
                    androidx.media2.widget.SubtitleAnchorView r5 = r5.mSubtitleAnchorView
                    r0 = 0
                    r5.setVisibility(r0)
                L_0x0044:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.media2.widget.VideoView.AnonymousClass2.onSubtitleTrackSelected(androidx.media2.widget.SubtitleTrack):void");
            }
        });
        this.mSubtitleController = subtitleController;
        subtitleController.registerRenderer(new Cea608CaptionRenderer(context));
        this.mSubtitleController.registerRenderer(new Cea708CaptionRenderer(context));
        this.mSubtitleController.setAnchor(this.mSubtitleAnchorView);
        MusicView musicView = new MusicView(context);
        this.mMusicView = musicView;
        musicView.setVisibility(8);
        addView(this.mMusicView, this.mSelectiveLayoutParams);
        if (attributeSet == null || attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res-auto", "enableControlView", true)) {
            MediaControlView mediaControlView = new MediaControlView(context);
            this.mMediaControlView = mediaControlView;
            mediaControlView.setAttachedToVideoView(true);
            addView(this.mMediaControlView, this.mSelectiveLayoutParams);
        }
        if (attributeSet == null) {
            i = 0;
        } else {
            i = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "viewType", 0);
        }
        if (i == 0) {
            if (DEBUG) {
                Log.d("VideoView", "viewType attribute is surfaceView.");
            }
            this.mTextureView.setVisibility(8);
            this.mSurfaceView.setVisibility(0);
            this.mCurrentView = this.mSurfaceView;
        } else if (i == 1) {
            if (DEBUG) {
                Log.d("VideoView", "viewType attribute is textureView.");
            }
            this.mTextureView.setVisibility(0);
            this.mSurfaceView.setVisibility(8);
            this.mCurrentView = this.mTextureView;
        }
        this.mTargetView = this.mCurrentView;
    }

    public void setMediaController(MediaController mediaController) {
        if (mediaController != null) {
            PlayerWrapper playerWrapper = this.mPlayer;
            if (playerWrapper != null) {
                playerWrapper.detachCallback();
            }
            this.mPlayer = new PlayerWrapper(mediaController, ContextCompat.getMainExecutor(getContext()), (PlayerWrapper.PlayerCallback) new PlayerCallback());
            if (isAttachedToWindow()) {
                this.mPlayer.attachCallback();
            }
            if (isAggregatedVisible()) {
                this.mTargetView.assignSurfaceToPlayerWrapper(this.mPlayer);
            } else {
                resetPlayerSurfaceWithNullAsync();
            }
            MediaControlView mediaControlView = this.mMediaControlView;
            if (mediaControlView != null) {
                mediaControlView.setMediaControllerInternal(mediaController);
                return;
            }
            return;
        }
        throw new NullPointerException("controller must not be null");
    }

    public void setPlayer(SessionPlayer sessionPlayer) {
        if (sessionPlayer != null) {
            PlayerWrapper playerWrapper = this.mPlayer;
            if (playerWrapper != null) {
                playerWrapper.detachCallback();
            }
            this.mPlayer = new PlayerWrapper(sessionPlayer, ContextCompat.getMainExecutor(getContext()), (PlayerWrapper.PlayerCallback) new PlayerCallback());
            if (isAttachedToWindow()) {
                this.mPlayer.attachCallback();
            }
            if (isAggregatedVisible()) {
                this.mTargetView.assignSurfaceToPlayerWrapper(this.mPlayer);
            } else {
                resetPlayerSurfaceWithNullAsync();
            }
            MediaControlView mediaControlView = this.mMediaControlView;
            if (mediaControlView != null) {
                mediaControlView.setPlayerInternal(sessionPlayer);
                return;
            }
            return;
        }
        throw new NullPointerException("player must not be null");
    }

    public MediaControlView getMediaControlView() {
        return this.mMediaControlView;
    }

    public void setViewType(int i) {
        VideoViewInterface videoViewInterface;
        if (i == this.mTargetView.getViewType()) {
            Log.d("VideoView", "setViewType with the same type (" + i + ") is ignored.");
            return;
        }
        if (i == 1) {
            Log.d("VideoView", "switching to TextureView");
            videoViewInterface = this.mTextureView;
        } else if (i == 0) {
            Log.d("VideoView", "switching to SurfaceView");
            videoViewInterface = this.mSurfaceView;
        } else {
            throw new IllegalArgumentException("Unknown view type: " + i);
        }
        this.mTargetView = videoViewInterface;
        if (isAggregatedVisible()) {
            videoViewInterface.assignSurfaceToPlayerWrapper(this.mPlayer);
        }
        ((View) videoViewInterface).setVisibility(0);
        requestLayout();
    }

    public int getViewType() {
        return this.mCurrentView.getViewType();
    }

    public void setOnViewTypeChangedListener(OnViewTypeChangedListener onViewTypeChangedListener) {
        this.mViewTypeChangedListener = onViewTypeChangedListener;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        PlayerWrapper playerWrapper = this.mPlayer;
        if (playerWrapper != null) {
            playerWrapper.attachCallback();
        }
    }

    /* access modifiers changed from: package-private */
    public void onVisibilityAggregatedCompat(boolean z) {
        super.onVisibilityAggregatedCompat(z);
        PlayerWrapper playerWrapper = this.mPlayer;
        if (playerWrapper != null) {
            if (z) {
                this.mTargetView.assignSurfaceToPlayerWrapper(playerWrapper);
            } else if (playerWrapper == null || playerWrapper.hasDisconnectedController()) {
                Log.w("VideoView", "Surface is being destroyed, but player will not be informed as the associated media controller is disconnected.");
            } else {
                resetPlayerSurfaceWithNull();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        PlayerWrapper playerWrapper = this.mPlayer;
        if (playerWrapper != null) {
            playerWrapper.detachCallback();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isMediaPrepared() {
        PlayerWrapper playerWrapper = this.mPlayer;
        return (playerWrapper == null || playerWrapper.getPlayerState() == 3 || this.mPlayer.getPlayerState() == 0) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public boolean hasActualVideo() {
        if (this.mVideoTrackCount > 0) {
            return true;
        }
        VideoSize videoSize = this.mPlayer.getVideoSize();
        if (videoSize.getHeight() <= 0 || videoSize.getWidth() <= 0) {
            return false;
        }
        Log.w("VideoView", "video track count is zero, but it renders video. size: " + videoSize.getWidth() + "/" + videoSize.getHeight());
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isCurrentItemMusic() {
        return !hasActualVideo() && this.mAudioTrackCount > 0;
    }

    /* access modifiers changed from: package-private */
    public void updateTracks(PlayerWrapper playerWrapper, List<SessionPlayer.TrackInfo> list) {
        SubtitleTrack addTrack;
        this.mSubtitleTracks = new LinkedHashMap();
        this.mVideoTrackCount = 0;
        this.mAudioTrackCount = 0;
        for (int i = 0; i < list.size(); i++) {
            SessionPlayer.TrackInfo trackInfo = list.get(i);
            int trackType = list.get(i).getTrackType();
            if (trackType == 1) {
                this.mVideoTrackCount++;
            } else if (trackType == 2) {
                this.mAudioTrackCount++;
            } else if (trackType == 4 && (addTrack = this.mSubtitleController.addTrack(trackInfo.getFormat())) != null) {
                this.mSubtitleTracks.put(trackInfo, addTrack);
            }
        }
        this.mSelectedSubtitleTrackInfo = playerWrapper.getSelectedTrack(4);
    }

    /* access modifiers changed from: package-private */
    public void updateMusicView(MediaItem mediaItem) {
        if (mediaItem != null && isCurrentItemMusic()) {
            this.mMusicView.setVisibility(0);
            MediaMetadata metadata = mediaItem.getMetadata();
            Resources resources = getResources();
            Drawable albumArt = getAlbumArt(metadata, resources.getDrawable(R.drawable.ic_default_album_image));
            String string = getString(metadata, "android.media.metadata.TITLE", resources.getString(R.string.mcv2_music_title_unknown_text));
            String string2 = getString(metadata, "android.media.metadata.ARTIST", resources.getString(R.string.mcv2_music_artist_unknown_text));
            this.mMusicView.setAlbumDrawable(albumArt);
            this.mMusicView.setTitleText(string);
            this.mMusicView.setArtistText(string2);
            return;
        }
        this.mMusicView.setVisibility(8);
        this.mMusicView.setAlbumDrawable((Drawable) null);
        this.mMusicView.setTitleText((String) null);
        this.mMusicView.setArtistText((String) null);
    }

    /* access modifiers changed from: package-private */
    public void resetPlayerSurfaceWithNull() {
        try {
            int resultCode = ((BaseResult) this.mPlayer.setSurface((Surface) null).get(100, TimeUnit.MILLISECONDS)).getResultCode();
            if (resultCode != 0) {
                Log.e("VideoView", "calling setSurface(null) was not successful. ResultCode: " + resultCode);
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e("VideoView", "calling setSurface(null) was not successful.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public void resetPlayerSurfaceWithNullAsync() {
        final ListenableFuture<? extends BaseResult> surface = this.mPlayer.setSurface((Surface) null);
        surface.addListener(new Runnable() {
            public void run() {
                try {
                    int resultCode = ((BaseResult) surface.get()).getResultCode();
                    if (resultCode != 0) {
                        Log.e("VideoView", "calling setSurface(null) was not successful. ResultCode: " + resultCode);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    Log.e("VideoView", "calling setSurface(null) was not successful.", e);
                }
            }
        }, ContextCompat.getMainExecutor(getContext()));
    }

    private Drawable getAlbumArt(MediaMetadata mediaMetadata, Drawable drawable) {
        Bitmap bitmap = (mediaMetadata == null || !mediaMetadata.containsKey("android.media.metadata.ALBUM_ART")) ? null : mediaMetadata.getBitmap("android.media.metadata.ALBUM_ART");
        if (bitmap != null) {
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette palette) {
                    VideoView.this.mMusicView.setBackgroundColor(palette.getDominantColor(0));
                }
            });
            return new BitmapDrawable(getResources(), bitmap);
        }
        this.mMusicView.setBackgroundColor(getResources().getColor(R.color.music_view_default_background));
        return drawable;
    }

    private String getString(MediaMetadata mediaMetadata, String str, String str2) {
        String string = mediaMetadata == null ? str2 : mediaMetadata.getString(str);
        return string == null ? str2 : string;
    }

    class PlayerCallback extends PlayerWrapper.PlayerCallback {
        PlayerCallback() {
        }

        /* access modifiers changed from: package-private */
        public void onVideoSizeChanged(PlayerWrapper playerWrapper, MediaItem mediaItem, VideoSize videoSize) {
            List<SessionPlayer.TrackInfo> trackInfo;
            if (VideoView.DEBUG) {
                Log.d("VideoView", "onVideoSizeChanged(): size: " + videoSize);
            }
            if (!shouldIgnoreCallback(playerWrapper)) {
                if (VideoView.this.mVideoTrackCount == 0 && videoSize.getHeight() > 0 && videoSize.getWidth() > 0 && VideoView.this.isMediaPrepared() && (trackInfo = playerWrapper.getTrackInfo()) != null) {
                    VideoView.this.updateTracks(playerWrapper, trackInfo);
                }
                VideoView.this.mTextureView.forceLayout();
                VideoView.this.mSurfaceView.forceLayout();
                VideoView.this.requestLayout();
            }
        }

        /* access modifiers changed from: package-private */
        public void onSubtitleData(PlayerWrapper playerWrapper, MediaItem mediaItem, SessionPlayer.TrackInfo trackInfo, SubtitleData subtitleData) {
            SubtitleTrack subtitleTrack;
            if (VideoView.DEBUG) {
                Log.d("VideoView", "onSubtitleData(): TrackInfo: " + trackInfo + ", getCurrentPosition: " + playerWrapper.getCurrentPosition() + ", getStartTimeUs(): " + subtitleData.getStartTimeUs() + ", diff: " + ((subtitleData.getStartTimeUs() / 1000) - playerWrapper.getCurrentPosition()) + "ms, getDurationUs(): " + subtitleData.getDurationUs());
            }
            if (!shouldIgnoreCallback(playerWrapper) && trackInfo.equals(VideoView.this.mSelectedSubtitleTrackInfo) && (subtitleTrack = VideoView.this.mSubtitleTracks.get(trackInfo)) != null) {
                subtitleTrack.onData(subtitleData);
            }
        }

        /* access modifiers changed from: package-private */
        public void onPlayerStateChanged(PlayerWrapper playerWrapper, int i) {
            if (VideoView.DEBUG) {
                Log.d("VideoView", "onPlayerStateChanged(): state: " + i);
            }
            if (shouldIgnoreCallback(playerWrapper)) {
            }
        }

        /* access modifiers changed from: package-private */
        public void onCurrentMediaItemChanged(PlayerWrapper playerWrapper, MediaItem mediaItem) {
            if (VideoView.DEBUG) {
                Log.d("VideoView", "onCurrentMediaItemChanged(): MediaItem: " + mediaItem);
            }
            if (!shouldIgnoreCallback(playerWrapper)) {
                VideoView.this.updateMusicView(mediaItem);
            }
        }

        /* access modifiers changed from: package-private */
        public void onTrackInfoChanged(PlayerWrapper playerWrapper, List<SessionPlayer.TrackInfo> list) {
            if (VideoView.DEBUG) {
                Log.d("VideoView", "onTrackInfoChanged(): tracks: " + list);
            }
            if (!shouldIgnoreCallback(playerWrapper)) {
                VideoView.this.updateTracks(playerWrapper, list);
                VideoView.this.updateMusicView(playerWrapper.getCurrentMediaItem());
            }
        }

        /* access modifiers changed from: package-private */
        public void onTrackSelected(PlayerWrapper playerWrapper, SessionPlayer.TrackInfo trackInfo) {
            SubtitleTrack subtitleTrack;
            if (VideoView.DEBUG) {
                Log.d("VideoView", "onTrackSelected(): selected track: " + trackInfo);
            }
            if (!shouldIgnoreCallback(playerWrapper) && (subtitleTrack = VideoView.this.mSubtitleTracks.get(trackInfo)) != null) {
                VideoView.this.mSubtitleController.selectTrack(subtitleTrack);
            }
        }

        /* access modifiers changed from: package-private */
        public void onTrackDeselected(PlayerWrapper playerWrapper, SessionPlayer.TrackInfo trackInfo) {
            if (VideoView.DEBUG) {
                Log.d("VideoView", "onTrackDeselected(): deselected track: " + trackInfo);
            }
            if (!shouldIgnoreCallback(playerWrapper) && VideoView.this.mSubtitleTracks.get(trackInfo) != null) {
                VideoView.this.mSubtitleController.selectTrack((SubtitleTrack) null);
            }
        }

        private boolean shouldIgnoreCallback(PlayerWrapper playerWrapper) {
            if (playerWrapper == VideoView.this.mPlayer) {
                return false;
            }
            if (VideoView.DEBUG) {
                try {
                    String methodName = new Throwable().getStackTrace()[1].getMethodName();
                    Log.w("VideoView", methodName + " should be ignored. player is already gone.");
                } catch (IndexOutOfBoundsException unused) {
                    Log.w("VideoView", "A PlayerCallback should be ignored. player is already gone.");
                }
            }
            return true;
        }
    }
}
