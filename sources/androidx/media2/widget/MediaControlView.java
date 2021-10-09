package androidx.media2.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.UriMediaItem;
import androidx.media2.common.VideoSize;
import androidx.media2.session.MediaController;
import androidx.media2.session.SessionCommandGroup;
import androidx.media2.widget.PlayerWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class MediaControlView extends MediaViewGroup {
    static final boolean DEBUG = Log.isLoggable("MediaControlView", 3);
    private AccessibilityManager mAccessibilityManager;
    private View mAdExternalLink;
    private TextView mAdRemainingView;
    private TextView mAdSkipView;
    List<String> mAudioTrackDescriptionList;
    List<SessionPlayer.TrackInfo> mAudioTracks;
    ViewGroup mBasicControls;
    private View mBottomBarBackground;
    private ViewGroup mBottomBarLeft;
    ViewGroup mCenterView;
    private View mCenterViewBackground;
    long mCurrentSeekPosition;
    TextView mCurrentTime;
    int mCustomPlaybackSpeedIndex;
    long mDelayedAnimationIntervalMs;
    boolean mDragging;
    long mDuration;
    private int mEmbeddedSettingsItemWidth;
    private View mEmbeddedTransportControls;
    private TextView mEndTime;
    ViewGroup mExtraControls;
    private final View.OnClickListener mFfwdListener;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    ImageButton mFullScreenButton;
    private final View.OnClickListener mFullScreenListener;
    private int mFullSettingsItemWidth;
    private View mFullTransportControls;
    private final Runnable mHideAllBars;
    AnimatorSet mHideAllBarsAnimator;
    Runnable mHideMainBars;
    AnimatorSet mHideMainBarsAnimator;
    final Runnable mHideProgressBar;
    AnimatorSet mHideProgressBarAnimator;
    boolean mIsAdvertisement;
    private boolean mIsAttachedToVideoView;
    boolean mIsFullScreen;
    boolean mIsShowingReplayButton;
    ImageButton mMinimalFullScreenButton;
    ViewGroup mMinimalFullScreenView;
    private View mMinimalTransportControls;
    boolean mNeedToHideBars;
    boolean mNeedToShowBars;
    private final View.OnClickListener mNextListener;
    long mNextSeekPosition;
    OnFullScreenListener mOnFullScreenListener;
    ValueAnimator mOverflowHideAnimator;
    private final View.OnClickListener mOverflowHideListener;
    boolean mOverflowIsShowing;
    ValueAnimator mOverflowShowAnimator;
    private final View.OnClickListener mOverflowShowListener;
    private final View.OnClickListener mPlayPauseListener;
    List<Integer> mPlaybackSpeedMultBy100List;
    List<String> mPlaybackSpeedTextList;
    PlayerWrapper mPlayer;
    private final View.OnClickListener mPrevListener;
    SeekBar mProgress;
    private ViewGroup mProgressBar;
    Resources mResources;
    private final View.OnClickListener mRewListener;
    boolean mSeekAvailable;
    private final SeekBar.OnSeekBarChangeListener mSeekListener;
    int mSelectedAudioTrackIndex;
    int mSelectedSpeedIndex;
    int mSelectedSubtitleTrackIndex;
    SettingsAdapter mSettingsAdapter;
    private final View.OnClickListener mSettingsButtonListener;
    private PopupWindow.OnDismissListener mSettingsDismissListener;
    private List<Integer> mSettingsIconIdsList;
    private final AdapterView.OnItemClickListener mSettingsItemClickListener;
    private int mSettingsItemHeight;
    private ListView mSettingsListView;
    private List<String> mSettingsMainTextsList;
    int mSettingsMode;
    List<String> mSettingsSubTextsList;
    private PopupWindow mSettingsWindow;
    private int mSettingsWindowMargin;
    final Runnable mShowAllBars;
    AnimatorSet mShowAllBarsAnimator;
    AnimatorSet mShowMainBarsAnimator;
    int mSizeType;
    SubSettingsAdapter mSubSettingsAdapter;
    ImageButton mSubtitleButton;
    List<String> mSubtitleDescriptionsList;
    private final View.OnClickListener mSubtitleListener;
    List<SessionPlayer.TrackInfo> mSubtitleTracks;
    private ViewGroup mTimeView;
    private View mTitleBar;
    private TextView mTitleView;
    private SparseArray<View> mTransportControlsMap;
    final Runnable mUpdateProgress;
    int mUxState;
    int mVideoTrackCount;
    boolean mWasPlaying;

    public interface OnFullScreenListener {
        void onFullScreen(View view, boolean z);
    }

    public CharSequence getAccessibilityClassName() {
        return "androidx.media2.widget.MediaControlView";
    }

    public /* bridge */ /* synthetic */ void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
    }

    public MediaControlView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MediaControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsAttachedToVideoView = false;
        this.mSizeType = -1;
        this.mTransportControlsMap = new SparseArray<>();
        this.mAudioTracks = new ArrayList();
        this.mSubtitleTracks = new ArrayList();
        this.mUpdateProgress = new Runnable() {
            public void run() {
                boolean z = MediaControlView.this.getVisibility() == 0;
                if (!MediaControlView.this.mDragging && z && MediaControlView.this.mPlayer != null && MediaControlView.this.mPlayer.isPlaying()) {
                    long progress = MediaControlView.this.setProgress();
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.postDelayedRunnable(mediaControlView.mUpdateProgress, 1000 - (progress % 1000));
                }
            }
        };
        this.mShowAllBars = new Runnable() {
            public void run() {
                int i = MediaControlView.this.mUxState;
                if (i == 1) {
                    MediaControlView.this.mShowMainBarsAnimator.start();
                } else if (i == 2) {
                    MediaControlView.this.mShowAllBarsAnimator.start();
                } else if (i == 3) {
                    MediaControlView.this.mNeedToShowBars = true;
                }
                if (MediaControlView.this.mPlayer.isPlaying()) {
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.postDelayedRunnable(mediaControlView.mHideMainBars, MediaControlView.this.mDelayedAnimationIntervalMs);
                }
            }
        };
        this.mHideAllBars = new Runnable() {
            public void run() {
                if (!MediaControlView.this.shouldNotHideBars()) {
                    MediaControlView.this.mHideAllBarsAnimator.start();
                }
            }
        };
        this.mHideMainBars = new Runnable() {
            public void run() {
                if (MediaControlView.this.mPlayer.isPlaying() && !MediaControlView.this.shouldNotHideBars()) {
                    MediaControlView.this.mHideMainBarsAnimator.start();
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.postDelayedRunnable(mediaControlView.mHideProgressBar, MediaControlView.this.mDelayedAnimationIntervalMs);
                }
            }
        };
        this.mHideProgressBar = new Runnable() {
            public void run() {
                if (MediaControlView.this.mPlayer.isPlaying() && !MediaControlView.this.shouldNotHideBars()) {
                    MediaControlView.this.mHideProgressBarAnimator.start();
                }
            }
        };
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (MediaControlView.this.mPlayer != null && MediaControlView.this.mSeekAvailable) {
                    MediaControlView.this.mDragging = true;
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.removeCallbacks(mediaControlView.mUpdateProgress);
                    MediaControlView mediaControlView2 = MediaControlView.this;
                    mediaControlView2.removeCallbacks(mediaControlView2.mHideMainBars);
                    MediaControlView mediaControlView3 = MediaControlView.this;
                    mediaControlView3.removeCallbacks(mediaControlView3.mHideProgressBar);
                    if (MediaControlView.this.mIsShowingReplayButton) {
                        MediaControlView.this.updateReplayButton(false);
                    }
                    if (MediaControlView.this.isCurrentMediaItemFromNetwork() && MediaControlView.this.mPlayer.isPlaying()) {
                        MediaControlView.this.mWasPlaying = true;
                        MediaControlView.this.mPlayer.pause();
                    }
                }
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (MediaControlView.this.mPlayer != null && MediaControlView.this.mSeekAvailable && z && MediaControlView.this.mDragging && MediaControlView.this.mDuration > 0) {
                    MediaControlView.this.seekTo((MediaControlView.this.mDuration * ((long) i)) / 1000, !MediaControlView.this.isCurrentMediaItemFromNetwork());
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (MediaControlView.this.mPlayer != null && MediaControlView.this.mSeekAvailable) {
                    MediaControlView.this.mDragging = false;
                    long latestSeekPosition = MediaControlView.this.getLatestSeekPosition();
                    if (MediaControlView.this.isCurrentMediaItemFromNetwork()) {
                        MediaControlView.this.mCurrentSeekPosition = -1;
                        MediaControlView.this.mNextSeekPosition = -1;
                    }
                    MediaControlView.this.seekTo(latestSeekPosition, true);
                    if (MediaControlView.this.mWasPlaying) {
                        MediaControlView.this.mWasPlaying = false;
                        MediaControlView.this.mPlayer.play();
                    }
                }
            }
        };
        this.mPlayPauseListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaControlView.this.mPlayer != null) {
                    MediaControlView.this.resetHideCallbacks();
                    MediaControlView.this.togglePausePlayState();
                }
            }
        };
        this.mRewListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaControlView.this.mPlayer != null) {
                    MediaControlView.this.resetHideCallbacks();
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.removeCallbacks(mediaControlView.mUpdateProgress);
                    boolean z = MediaControlView.this.mIsShowingReplayButton && MediaControlView.this.mDuration != 0;
                    MediaControlView mediaControlView2 = MediaControlView.this;
                    MediaControlView.this.seekTo(Math.max((z ? mediaControlView2.mDuration : mediaControlView2.getLatestSeekPosition()) - 10000, 0), true);
                    if (z) {
                        MediaControlView.this.updateReplayButton(false);
                    }
                }
            }
        };
        this.mFfwdListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaControlView.this.mPlayer != null) {
                    MediaControlView.this.resetHideCallbacks();
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.removeCallbacks(mediaControlView.mUpdateProgress);
                    long latestSeekPosition = MediaControlView.this.getLatestSeekPosition();
                    MediaControlView mediaControlView2 = MediaControlView.this;
                    long j = latestSeekPosition + 30000;
                    mediaControlView2.seekTo(Math.min(j, mediaControlView2.mDuration), true);
                    if (j >= MediaControlView.this.mDuration && !MediaControlView.this.mPlayer.isPlaying()) {
                        MediaControlView.this.updateReplayButton(true);
                    }
                }
            }
        };
        this.mNextListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaControlView.this.mPlayer != null) {
                    MediaControlView.this.resetHideCallbacks();
                    MediaControlView.this.mPlayer.skipToNextItem();
                }
            }
        };
        this.mPrevListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaControlView.this.mPlayer != null) {
                    MediaControlView.this.resetHideCallbacks();
                    MediaControlView.this.mPlayer.skipToPreviousItem();
                }
            }
        };
        this.mSubtitleListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaControlView.this.mPlayer != null) {
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.removeCallbacks(mediaControlView.mHideMainBars);
                    MediaControlView mediaControlView2 = MediaControlView.this;
                    mediaControlView2.removeCallbacks(mediaControlView2.mHideProgressBar);
                    MediaControlView.this.mSettingsMode = 2;
                    MediaControlView.this.mSubSettingsAdapter.setTexts(MediaControlView.this.mSubtitleDescriptionsList);
                    MediaControlView.this.mSubSettingsAdapter.setCheckPosition(MediaControlView.this.mSelectedSubtitleTrackIndex + 1);
                    MediaControlView mediaControlView3 = MediaControlView.this;
                    mediaControlView3.displaySettingsWindow(mediaControlView3.mSubSettingsAdapter);
                }
            }
        };
        this.mFullScreenListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaControlView.this.mOnFullScreenListener != null) {
                    boolean z = !MediaControlView.this.mIsFullScreen;
                    if (z) {
                        MediaControlView.this.mFullScreenButton.setImageDrawable(MediaControlView.this.mResources.getDrawable(R.drawable.ic_fullscreen_exit));
                        MediaControlView.this.mMinimalFullScreenButton.setImageDrawable(MediaControlView.this.mResources.getDrawable(R.drawable.ic_fullscreen_exit));
                    } else {
                        MediaControlView.this.mFullScreenButton.setImageDrawable(MediaControlView.this.mResources.getDrawable(R.drawable.ic_fullscreen));
                        MediaControlView.this.mMinimalFullScreenButton.setImageDrawable(MediaControlView.this.mResources.getDrawable(R.drawable.ic_fullscreen));
                    }
                    MediaControlView.this.mIsFullScreen = z;
                    OnFullScreenListener onFullScreenListener = MediaControlView.this.mOnFullScreenListener;
                    MediaControlView mediaControlView = MediaControlView.this;
                    onFullScreenListener.onFullScreen(mediaControlView, mediaControlView.mIsFullScreen);
                }
            }
        };
        this.mOverflowShowListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaControlView.this.mPlayer != null) {
                    MediaControlView.this.resetHideCallbacks();
                    MediaControlView.this.mOverflowIsShowing = true;
                    MediaControlView.this.mOverflowShowAnimator.start();
                }
            }
        };
        this.mOverflowHideListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaControlView.this.mPlayer != null) {
                    MediaControlView.this.resetHideCallbacks();
                    MediaControlView.this.mOverflowIsShowing = false;
                    MediaControlView.this.mOverflowHideAnimator.start();
                }
            }
        };
        this.mSettingsButtonListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaControlView.this.mPlayer != null) {
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.removeCallbacks(mediaControlView.mHideMainBars);
                    MediaControlView mediaControlView2 = MediaControlView.this;
                    mediaControlView2.removeCallbacks(mediaControlView2.mHideProgressBar);
                    MediaControlView.this.mSettingsMode = 3;
                    MediaControlView.this.mSettingsAdapter.setSubTexts(MediaControlView.this.mSettingsSubTextsList);
                    MediaControlView mediaControlView3 = MediaControlView.this;
                    mediaControlView3.displaySettingsWindow(mediaControlView3.mSettingsAdapter);
                }
            }
        };
        this.mSettingsItemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = MediaControlView.this.mSettingsMode;
                if (i2 == 0) {
                    if (i != MediaControlView.this.mSelectedAudioTrackIndex && MediaControlView.this.mAudioTracks.size() > 0) {
                        MediaControlView.this.mPlayer.selectTrack(MediaControlView.this.mAudioTracks.get(i));
                    }
                    MediaControlView.this.dismissSettingsWindow();
                } else if (i2 == 1) {
                    if (i != MediaControlView.this.mSelectedSpeedIndex) {
                        MediaControlView.this.mPlayer.setPlaybackSpeed(((float) MediaControlView.this.mPlaybackSpeedMultBy100List.get(i).intValue()) / 100.0f);
                    }
                    MediaControlView.this.dismissSettingsWindow();
                } else if (i2 == 2) {
                    if (i != MediaControlView.this.mSelectedSubtitleTrackIndex + 1) {
                        if (i > 0) {
                            MediaControlView.this.mPlayer.selectTrack(MediaControlView.this.mSubtitleTracks.get(i - 1));
                        } else {
                            MediaControlView.this.mPlayer.deselectTrack(MediaControlView.this.mSubtitleTracks.get(MediaControlView.this.mSelectedSubtitleTrackIndex));
                        }
                    }
                    MediaControlView.this.dismissSettingsWindow();
                } else if (i2 == 3) {
                    if (i == 0) {
                        MediaControlView.this.mSubSettingsAdapter.setTexts(MediaControlView.this.mAudioTrackDescriptionList);
                        MediaControlView.this.mSubSettingsAdapter.setCheckPosition(MediaControlView.this.mSelectedAudioTrackIndex);
                        MediaControlView.this.mSettingsMode = 0;
                    } else if (i == 1) {
                        MediaControlView.this.mSubSettingsAdapter.setTexts(MediaControlView.this.mPlaybackSpeedTextList);
                        MediaControlView.this.mSubSettingsAdapter.setCheckPosition(MediaControlView.this.mSelectedSpeedIndex);
                        MediaControlView.this.mSettingsMode = 1;
                    }
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.displaySettingsWindow(mediaControlView.mSubSettingsAdapter);
                }
            }
        };
        this.mSettingsDismissListener = new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                if (MediaControlView.this.mNeedToHideBars) {
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.postDelayedRunnable(mediaControlView.mHideMainBars, MediaControlView.this.mDelayedAnimationIntervalMs);
                }
            }
        };
        this.mResources = context.getResources();
        inflate(context, R.layout.media_controller, this);
        initControllerView();
        this.mDelayedAnimationIntervalMs = 2000;
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }

    public void setMediaController(MediaController mediaController) {
        if (mediaController == null) {
            throw new NullPointerException("controller must not be null");
        } else if (!this.mIsAttachedToVideoView) {
            setMediaControllerInternal(mediaController);
        } else {
            throw new IllegalStateException("It's attached to VideoView. Use VideoView's method.");
        }
    }

    /* access modifiers changed from: package-private */
    public void setMediaControllerInternal(MediaController mediaController) {
        PlayerWrapper playerWrapper = this.mPlayer;
        if (playerWrapper != null) {
            playerWrapper.detachCallback();
        }
        this.mPlayer = new PlayerWrapper(mediaController, ContextCompat.getMainExecutor(getContext()), (PlayerWrapper.PlayerCallback) new PlayerCallback());
        if (isAttachedToWindow()) {
            this.mPlayer.attachCallback();
        }
    }

    public void setPlayer(SessionPlayer sessionPlayer) {
        if (sessionPlayer == null) {
            throw new NullPointerException("player must not be null");
        } else if (!this.mIsAttachedToVideoView) {
            setPlayerInternal(sessionPlayer);
        } else {
            throw new IllegalStateException("It's attached to VideoView. Use VideoView's method.");
        }
    }

    /* access modifiers changed from: package-private */
    public void setPlayerInternal(SessionPlayer sessionPlayer) {
        PlayerWrapper playerWrapper = this.mPlayer;
        if (playerWrapper != null) {
            playerWrapper.detachCallback();
        }
        this.mPlayer = new PlayerWrapper(sessionPlayer, ContextCompat.getMainExecutor(getContext()), (PlayerWrapper.PlayerCallback) new PlayerCallback());
        if (isAttachedToWindow()) {
            this.mPlayer.attachCallback();
        }
    }

    public void setOnFullScreenListener(OnFullScreenListener onFullScreenListener) {
        if (onFullScreenListener == null) {
            this.mOnFullScreenListener = null;
            this.mFullScreenButton.setVisibility(8);
            return;
        }
        this.mOnFullScreenListener = onFullScreenListener;
        this.mFullScreenButton.setVisibility(0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mPlayer == null) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 1 && (!isCurrentItemMusic() || this.mSizeType != 1)) {
            if (this.mUxState == 0) {
                hideMediaControlView();
            } else {
                showMediaControlView();
            }
        }
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (this.mPlayer == null) {
            return super.onTrackballEvent(motionEvent);
        }
        if (motionEvent.getAction() == 1 && (!isCurrentItemMusic() || this.mSizeType != 1)) {
            if (this.mUxState == 0) {
                hideMediaControlView();
            } else {
                showMediaControlView();
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = i;
        int i8 = i2;
        int resolveSize = resolveSize(getSuggestedMinimumWidth(), i7);
        int resolveSize2 = resolveSize(getSuggestedMinimumHeight(), i8);
        int paddingLeft = (resolveSize - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (resolveSize2 - getPaddingTop()) - getPaddingBottom();
        if (paddingLeft < 0) {
            i4 = 16777216;
            i3 = 0;
        } else {
            i3 = paddingLeft;
            i4 = 0;
        }
        if (paddingTop < 0) {
            i4 |= 256;
            paddingTop = 0;
        }
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    i5 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
                } else if (layoutParams.width == -2) {
                    i5 = View.MeasureSpec.makeMeasureSpec(i3, 0);
                } else {
                    i5 = View.MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
                }
                if (layoutParams.height == -1) {
                    i6 = View.MeasureSpec.makeMeasureSpec(paddingTop, 1073741824);
                } else if (layoutParams.height == -2) {
                    i6 = View.MeasureSpec.makeMeasureSpec(paddingTop, 0);
                } else {
                    i6 = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
                }
                childAt.measure(i5, i6);
                i4 |= childAt.getMeasuredState();
            }
        }
        setMeasuredDimension(resolveSizeAndState(resolveSize, i7, i4), resolveSizeAndState(resolveSize2, i8, i4 << 16));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int paddingLeft = ((i3 - i) - getPaddingLeft()) - getPaddingRight();
        int paddingTop = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        int i6 = 0;
        int i7 = (this.mIsAdvertisement || ((this.mBottomBarLeft.getMeasuredWidth() + this.mTimeView.getMeasuredWidth()) + this.mBasicControls.getMeasuredWidth() <= paddingLeft && (this.mTitleBar.getMeasuredHeight() + this.mProgressBar.getMeasuredHeight()) + this.mBottomBarBackground.getMeasuredHeight() <= paddingTop)) ? 1 : (this.mTimeView.getMeasuredWidth() + this.mBasicControls.getMeasuredWidth() > paddingLeft || ((this.mTitleBar.getMeasuredHeight() + this.mEmbeddedTransportControls.getMeasuredHeight()) + this.mProgressBar.getMeasuredHeight()) + this.mBottomBarBackground.getMeasuredHeight() > paddingTop) ? 2 : 0;
        if (this.mSizeType != i7) {
            this.mSizeType = i7;
            updateLayoutForSizeChange(i7);
        }
        this.mTitleBar.setVisibility(i7 != 2 ? 0 : 4);
        this.mCenterViewBackground.setVisibility(i7 != 1 ? 0 : 4);
        this.mEmbeddedTransportControls.setVisibility(i7 == 0 ? 0 : 4);
        this.mMinimalTransportControls.setVisibility(i7 == 2 ? 0 : 4);
        this.mBottomBarBackground.setVisibility(i7 != 2 ? 0 : 4);
        this.mBottomBarLeft.setVisibility(i7 == 1 ? 0 : 4);
        this.mTimeView.setVisibility(i7 != 2 ? 0 : 4);
        this.mBasicControls.setVisibility(i7 != 2 ? 0 : 4);
        ImageButton imageButton = this.mMinimalFullScreenButton;
        if (i7 != 2) {
            i6 = 4;
        }
        imageButton.setVisibility(i6);
        int paddingLeft2 = getPaddingLeft();
        int i8 = paddingLeft + paddingLeft2;
        int paddingTop2 = getPaddingTop();
        int i9 = paddingTop + paddingTop2;
        layoutChild(this.mTitleBar, paddingLeft2, paddingTop2);
        layoutChild(this.mCenterView, paddingLeft2, paddingTop2);
        View view = this.mBottomBarBackground;
        layoutChild(view, paddingLeft2, i9 - view.getMeasuredHeight());
        ViewGroup viewGroup = this.mBottomBarLeft;
        layoutChild(viewGroup, paddingLeft2, i9 - viewGroup.getMeasuredHeight());
        layoutChild(this.mTimeView, i7 == 1 ? (i8 - this.mBasicControls.getMeasuredWidth()) - this.mTimeView.getMeasuredWidth() : paddingLeft2, i9 - this.mTimeView.getMeasuredHeight());
        ViewGroup viewGroup2 = this.mBasicControls;
        layoutChild(viewGroup2, i8 - viewGroup2.getMeasuredWidth(), i9 - this.mBasicControls.getMeasuredHeight());
        ViewGroup viewGroup3 = this.mExtraControls;
        layoutChild(viewGroup3, i8, i9 - viewGroup3.getMeasuredHeight());
        ViewGroup viewGroup4 = this.mProgressBar;
        if (i7 == 2) {
            i5 = i9 - viewGroup4.getMeasuredHeight();
        } else {
            i5 = (i9 - viewGroup4.getMeasuredHeight()) - this.mResources.getDimensionPixelSize(R.dimen.mcv2_custom_progress_margin_bottom);
        }
        layoutChild(viewGroup4, paddingLeft2, i5);
        ViewGroup viewGroup5 = this.mMinimalFullScreenView;
        layoutChild(viewGroup5, paddingLeft2, i9 - viewGroup5.getMeasuredHeight());
    }

    private void layoutChild(View view, int i, int i2) {
        view.layout(i, i2, view.getMeasuredWidth() + i, view.getMeasuredHeight() + i2);
    }

    /* access modifiers changed from: package-private */
    public void onVisibilityAggregatedCompat(boolean z) {
        super.onVisibilityAggregatedCompat(z);
        if (this.mPlayer != null) {
            if (z) {
                removeCallbacks(this.mUpdateProgress);
                post(this.mUpdateProgress);
                return;
            }
            removeCallbacks(this.mUpdateProgress);
        }
    }

    /* access modifiers changed from: package-private */
    public void setDelayedAnimationInterval(long j) {
        this.mDelayedAnimationIntervalMs = j;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        PlayerWrapper playerWrapper = this.mPlayer;
        if (playerWrapper != null) {
            playerWrapper.attachCallback();
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
    public void setAttachedToVideoView(boolean z) {
        this.mIsAttachedToVideoView = z;
    }

    static View inflateLayout(Context context, int i) {
        return ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(i, (ViewGroup) null);
    }

    private void initControllerView() {
        this.mTitleBar = findViewById(R.id.title_bar);
        this.mTitleView = (TextView) findViewById(R.id.title_text);
        this.mAdExternalLink = findViewById(R.id.ad_external_link);
        this.mCenterView = (ViewGroup) findViewById(R.id.center_view);
        this.mCenterViewBackground = findViewById(R.id.center_view_background);
        this.mEmbeddedTransportControls = initTransportControls(R.id.embedded_transport_controls);
        this.mMinimalTransportControls = initTransportControls(R.id.minimal_transport_controls);
        this.mMinimalFullScreenView = (ViewGroup) findViewById(R.id.minimal_fullscreen_view);
        ImageButton imageButton = (ImageButton) findViewById(R.id.minimal_fullscreen);
        this.mMinimalFullScreenButton = imageButton;
        imageButton.setOnClickListener(this.mFullScreenListener);
        this.mProgressBar = (ViewGroup) findViewById(R.id.progress_bar);
        SeekBar seekBar = (SeekBar) findViewById(R.id.progress);
        this.mProgress = seekBar;
        seekBar.setOnSeekBarChangeListener(this.mSeekListener);
        this.mProgress.setMax(1000);
        this.mCurrentSeekPosition = -1;
        this.mNextSeekPosition = -1;
        this.mBottomBarBackground = findViewById(R.id.bottom_bar_background);
        this.mBottomBarLeft = (ViewGroup) findViewById(R.id.bottom_bar_left);
        this.mFullTransportControls = initTransportControls(R.id.full_transport_controls);
        this.mTimeView = (ViewGroup) findViewById(R.id.time);
        this.mEndTime = (TextView) findViewById(R.id.time_end);
        this.mCurrentTime = (TextView) findViewById(R.id.time_current);
        this.mAdSkipView = (TextView) findViewById(R.id.ad_skip_time);
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        this.mBasicControls = (ViewGroup) findViewById(R.id.basic_controls);
        this.mExtraControls = (ViewGroup) findViewById(R.id.extra_controls);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.subtitle);
        this.mSubtitleButton = imageButton2;
        imageButton2.setOnClickListener(this.mSubtitleListener);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.fullscreen);
        this.mFullScreenButton = imageButton3;
        imageButton3.setOnClickListener(this.mFullScreenListener);
        ((ImageButton) findViewById(R.id.overflow_show)).setOnClickListener(this.mOverflowShowListener);
        ((ImageButton) findViewById(R.id.overflow_hide)).setOnClickListener(this.mOverflowHideListener);
        ((ImageButton) findViewById(R.id.settings)).setOnClickListener(this.mSettingsButtonListener);
        this.mAdRemainingView = (TextView) findViewById(R.id.ad_remaining);
        initializeSettingsLists();
        this.mSettingsListView = (ListView) inflateLayout(getContext(), R.layout.settings_list);
        this.mSettingsAdapter = new SettingsAdapter(this.mSettingsMainTextsList, this.mSettingsSubTextsList, this.mSettingsIconIdsList);
        this.mSubSettingsAdapter = new SubSettingsAdapter((List<String>) null, 0);
        this.mSettingsListView.setAdapter(this.mSettingsAdapter);
        this.mSettingsListView.setChoiceMode(1);
        this.mSettingsListView.setOnItemClickListener(this.mSettingsItemClickListener);
        this.mTransportControlsMap.append(0, this.mEmbeddedTransportControls);
        this.mTransportControlsMap.append(1, this.mFullTransportControls);
        this.mTransportControlsMap.append(2, this.mMinimalTransportControls);
        this.mEmbeddedSettingsItemWidth = this.mResources.getDimensionPixelSize(R.dimen.mcv2_embedded_settings_width);
        this.mFullSettingsItemWidth = this.mResources.getDimensionPixelSize(R.dimen.mcv2_full_settings_width);
        this.mSettingsItemHeight = this.mResources.getDimensionPixelSize(R.dimen.mcv2_settings_height);
        this.mSettingsWindowMargin = this.mResources.getDimensionPixelSize(R.dimen.mcv2_settings_offset) * -1;
        PopupWindow popupWindow = new PopupWindow(this.mSettingsListView, this.mEmbeddedSettingsItemWidth, -2, true);
        this.mSettingsWindow = popupWindow;
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        this.mSettingsWindow.setOnDismissListener(this.mSettingsDismissListener);
        float dimension = this.mResources.getDimension(R.dimen.mcv2_title_bar_height);
        float dimension2 = this.mResources.getDimension(R.dimen.mcv2_custom_progress_thumb_size);
        float dimension3 = this.mResources.getDimension(R.dimen.mcv2_bottom_bar_height);
        View[] viewArr = {this.mBottomBarBackground, this.mBottomBarLeft, this.mTimeView, this.mBasicControls, this.mExtraControls, this.mProgressBar};
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                MediaControlView.this.mProgress.getThumb().setLevel((int) (((float) (MediaControlView.this.mSizeType == 2 ? 0 : 10000)) * floatValue));
                MediaControlView.this.mCenterView.setAlpha(floatValue);
                MediaControlView.this.mMinimalFullScreenView.setAlpha(floatValue);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                MediaControlView.this.mCenterView.setVisibility(4);
                MediaControlView.this.mMinimalFullScreenView.setVisibility(4);
            }
        });
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat2.setInterpolator(new LinearInterpolator());
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                MediaControlView.this.mProgress.getThumb().setLevel((int) (((float) (MediaControlView.this.mSizeType == 2 ? 0 : 10000)) * floatValue));
                MediaControlView.this.mCenterView.setAlpha(floatValue);
                MediaControlView.this.mMinimalFullScreenView.setAlpha(floatValue);
            }
        });
        ofFloat2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                MediaControlView.this.mCenterView.setVisibility(0);
                MediaControlView.this.mMinimalFullScreenView.setVisibility(0);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        this.mHideMainBarsAnimator = animatorSet;
        float f = -dimension;
        animatorSet.play(ofFloat).with(AnimatorUtil.ofTranslationY(0.0f, f, this.mTitleBar)).with(AnimatorUtil.ofTranslationYTogether(0.0f, dimension3, viewArr));
        this.mHideMainBarsAnimator.setDuration(250);
        this.mHideMainBarsAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                MediaControlView.this.mUxState = 3;
            }

            public void onAnimationEnd(Animator animator) {
                MediaControlView.this.mUxState = 1;
                if (MediaControlView.this.mNeedToShowBars) {
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.post(mediaControlView.mShowAllBars);
                    MediaControlView.this.mNeedToShowBars = false;
                }
            }
        });
        float f2 = dimension2 + dimension3;
        AnimatorSet ofTranslationYTogether = AnimatorUtil.ofTranslationYTogether(dimension3, f2, viewArr);
        this.mHideProgressBarAnimator = ofTranslationYTogether;
        ofTranslationYTogether.setDuration(250);
        this.mHideProgressBarAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                MediaControlView.this.mUxState = 3;
            }

            public void onAnimationEnd(Animator animator) {
                MediaControlView.this.mUxState = 2;
                if (MediaControlView.this.mNeedToShowBars) {
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.post(mediaControlView.mShowAllBars);
                    MediaControlView.this.mNeedToShowBars = false;
                }
            }
        });
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mHideAllBarsAnimator = animatorSet2;
        animatorSet2.play(ofFloat).with(AnimatorUtil.ofTranslationY(0.0f, f, this.mTitleBar)).with(AnimatorUtil.ofTranslationYTogether(0.0f, f2, viewArr));
        this.mHideAllBarsAnimator.setDuration(250);
        this.mHideAllBarsAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                MediaControlView.this.mUxState = 3;
            }

            public void onAnimationEnd(Animator animator) {
                MediaControlView.this.mUxState = 2;
                if (MediaControlView.this.mNeedToShowBars) {
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.post(mediaControlView.mShowAllBars);
                    MediaControlView.this.mNeedToShowBars = false;
                }
            }
        });
        AnimatorSet animatorSet3 = new AnimatorSet();
        this.mShowMainBarsAnimator = animatorSet3;
        animatorSet3.play(ofFloat2).with(AnimatorUtil.ofTranslationY(f, 0.0f, this.mTitleBar)).with(AnimatorUtil.ofTranslationYTogether(dimension3, 0.0f, viewArr));
        this.mShowMainBarsAnimator.setDuration(250);
        this.mShowMainBarsAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                MediaControlView.this.mUxState = 3;
            }

            public void onAnimationEnd(Animator animator) {
                MediaControlView.this.mUxState = 0;
            }
        });
        AnimatorSet animatorSet4 = new AnimatorSet();
        this.mShowAllBarsAnimator = animatorSet4;
        animatorSet4.play(ofFloat2).with(AnimatorUtil.ofTranslationY(f, 0.0f, this.mTitleBar)).with(AnimatorUtil.ofTranslationYTogether(f2, 0.0f, viewArr));
        this.mShowAllBarsAnimator.setDuration(250);
        this.mShowAllBarsAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                MediaControlView.this.mUxState = 3;
            }

            public void onAnimationEnd(Animator animator) {
                MediaControlView.this.mUxState = 0;
            }
        });
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mOverflowShowAnimator = ofFloat3;
        ofFloat3.setDuration(250);
        this.mOverflowShowAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                MediaControlView.this.animateOverflow(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.mOverflowShowAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                MediaControlView.this.mExtraControls.setVisibility(0);
            }

            public void onAnimationEnd(Animator animator) {
                int i = 4;
                MediaControlView.this.mBasicControls.setVisibility(4);
                ImageButton findFullSizedControlButton = MediaControlView.this.findFullSizedControlButton(R.id.ffwd);
                if (MediaControlView.this.mPlayer == null || !MediaControlView.this.mPlayer.canSeekForward()) {
                    i = 8;
                }
                findFullSizedControlButton.setVisibility(i);
            }
        });
        ValueAnimator ofFloat4 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        this.mOverflowHideAnimator = ofFloat4;
        ofFloat4.setDuration(250);
        this.mOverflowHideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                MediaControlView.this.animateOverflow(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.mOverflowHideAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                int i = 0;
                MediaControlView.this.mBasicControls.setVisibility(0);
                ImageButton findFullSizedControlButton = MediaControlView.this.findFullSizedControlButton(R.id.ffwd);
                if (MediaControlView.this.mPlayer == null || !MediaControlView.this.mPlayer.canSeekForward()) {
                    i = 8;
                }
                findFullSizedControlButton.setVisibility(i);
            }

            public void onAnimationEnd(Animator animator) {
                MediaControlView.this.mExtraControls.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public String stringForTime(long j) {
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        this.mFormatBuilder.setLength(0);
        if (j5 > 0) {
            return this.mFormatter.format("%d:%02d:%02d", new Object[]{Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)}).toString();
        }
        return this.mFormatter.format("%02d:%02d", new Object[]{Long.valueOf(j4), Long.valueOf(j3)}).toString();
    }

    /* access modifiers changed from: package-private */
    public long setProgress() {
        ensurePlayerIsNotNull();
        long currentPosition = this.mPlayer.getCurrentPosition();
        long j = this.mDuration;
        if (currentPosition > j) {
            currentPosition = j;
        }
        long j2 = this.mDuration;
        long j3 = 0;
        int i = j2 > 0 ? (int) ((currentPosition * 1000) / j2) : 0;
        SeekBar seekBar = this.mProgress;
        if (!(seekBar == null || currentPosition == this.mDuration)) {
            seekBar.setProgress(i);
            if (this.mPlayer.getBufferPercentage() < 0) {
                this.mProgress.setSecondaryProgress(1000);
            } else {
                this.mProgress.setSecondaryProgress(((int) this.mPlayer.getBufferPercentage()) * 10);
            }
        }
        TextView textView = this.mEndTime;
        if (textView != null) {
            textView.setText(stringForTime(this.mDuration));
        }
        TextView textView2 = this.mCurrentTime;
        if (textView2 != null) {
            textView2.setText(stringForTime(currentPosition));
        }
        if (this.mIsAdvertisement) {
            TextView textView3 = this.mAdSkipView;
            if (textView3 != null) {
                if (currentPosition <= 5000) {
                    if (textView3.getVisibility() == 8) {
                        this.mAdSkipView.setVisibility(0);
                    }
                    this.mAdSkipView.setText(this.mResources.getString(R.string.MediaControlView_ad_skip_wait_time, new Object[]{Long.valueOf(((5000 - currentPosition) / 1000) + 1)}));
                } else if (textView3.getVisibility() == 0) {
                    this.mAdSkipView.setVisibility(8);
                    findFullSizedControlButton(R.id.next).setEnabled(true);
                    findFullSizedControlButton(R.id.next).clearColorFilter();
                }
            }
            if (this.mAdRemainingView != null) {
                long j4 = this.mDuration;
                if (j4 - currentPosition >= 0) {
                    j3 = j4 - currentPosition;
                }
                this.mAdRemainingView.setText(this.mResources.getString(R.string.MediaControlView_ad_remaining_time, new Object[]{stringForTime(j3)}));
            }
        }
        return currentPosition;
    }

    /* access modifiers changed from: package-private */
    public void togglePausePlayState() {
        ensurePlayerIsNotNull();
        if (this.mPlayer.isPlaying()) {
            this.mPlayer.pause();
            updatePlayButton(1);
            return;
        }
        if (this.mIsShowingReplayButton) {
            this.mPlayer.seekTo(0);
        }
        this.mPlayer.play();
        updatePlayButton(0);
    }

    private void showMediaControlView() {
        if (this.mUxState != 3) {
            removeCallbacks(this.mHideMainBars);
            removeCallbacks(this.mHideProgressBar);
            post(this.mShowAllBars);
        }
    }

    private void hideMediaControlView() {
        if (!shouldNotHideBars() && this.mUxState != 3) {
            removeCallbacks(this.mHideMainBars);
            removeCallbacks(this.mHideProgressBar);
            post(this.mHideAllBars);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateTimeViews(MediaItem mediaItem) {
        if (mediaItem == null) {
            this.mProgress.setProgress(0);
            this.mCurrentTime.setText(this.mResources.getString(R.string.MediaControlView_time_placeholder));
            this.mEndTime.setText(this.mResources.getString(R.string.MediaControlView_time_placeholder));
            return;
        }
        ensurePlayerIsNotNull();
        long durationMs = this.mPlayer.getDurationMs();
        if (durationMs > 0) {
            this.mDuration = durationMs;
            setProgress();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateTitleView(MediaItem mediaItem) {
        if (mediaItem == null) {
            this.mTitleView.setText((CharSequence) null);
        } else if (!isCurrentItemMusic()) {
            CharSequence title = this.mPlayer.getTitle();
            if (title == null) {
                title = this.mResources.getString(R.string.mcv2_non_music_title_unknown_text);
            }
            this.mTitleView.setText(title.toString());
        } else {
            CharSequence title2 = this.mPlayer.getTitle();
            if (title2 == null) {
                title2 = this.mResources.getString(R.string.mcv2_music_title_unknown_text);
            }
            CharSequence artistText = this.mPlayer.getArtistText();
            if (artistText == null) {
                artistText = this.mResources.getString(R.string.mcv2_music_artist_unknown_text);
            }
            TextView textView = this.mTitleView;
            textView.setText(title2.toString() + " - " + artistText.toString());
        }
    }

    private void updateLayoutForSizeChange(int i) {
        if (i == 0 || i == 1) {
            this.mProgress.getThumb().setLevel(10000);
        } else if (i == 2) {
            this.mProgress.getThumb().setLevel(0);
        }
        updateReplayButton(this.mIsShowingReplayButton);
    }

    private View initTransportControls(int i) {
        View findViewById = findViewById(i);
        ImageButton imageButton = (ImageButton) findViewById.findViewById(R.id.pause);
        if (imageButton != null) {
            imageButton.setOnClickListener(this.mPlayPauseListener);
        }
        ImageButton imageButton2 = (ImageButton) findViewById.findViewById(R.id.ffwd);
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(this.mFfwdListener);
        }
        ImageButton imageButton3 = (ImageButton) findViewById.findViewById(R.id.rew);
        if (imageButton3 != null) {
            imageButton3.setOnClickListener(this.mRewListener);
        }
        ImageButton imageButton4 = (ImageButton) findViewById.findViewById(R.id.next);
        if (imageButton4 != null) {
            imageButton4.setOnClickListener(this.mNextListener);
        }
        ImageButton imageButton5 = (ImageButton) findViewById.findViewById(R.id.prev);
        if (imageButton5 != null) {
            imageButton5.setOnClickListener(this.mPrevListener);
        }
        return findViewById;
    }

    private void initializeSettingsLists() {
        ArrayList arrayList = new ArrayList();
        this.mSettingsMainTextsList = arrayList;
        arrayList.add(this.mResources.getString(R.string.MediaControlView_audio_track_text));
        this.mSettingsMainTextsList.add(this.mResources.getString(R.string.MediaControlView_playback_speed_text));
        ArrayList arrayList2 = new ArrayList();
        this.mSettingsSubTextsList = arrayList2;
        arrayList2.add(this.mResources.getString(R.string.MediaControlView_audio_track_none_text));
        String string = this.mResources.getString(R.string.MediaControlView_playback_speed_normal);
        this.mSettingsSubTextsList.add(string);
        this.mSettingsSubTextsList.add("");
        ArrayList arrayList3 = new ArrayList();
        this.mSettingsIconIdsList = arrayList3;
        arrayList3.add(Integer.valueOf(R.drawable.ic_audiotrack));
        this.mSettingsIconIdsList.add(Integer.valueOf(R.drawable.ic_speed));
        ArrayList arrayList4 = new ArrayList();
        this.mAudioTrackDescriptionList = arrayList4;
        arrayList4.add(this.mResources.getString(R.string.MediaControlView_audio_track_none_text));
        ArrayList arrayList5 = new ArrayList(Arrays.asList(this.mResources.getStringArray(R.array.MediaControlView_playback_speeds)));
        this.mPlaybackSpeedTextList = arrayList5;
        arrayList5.add(3, string);
        this.mSelectedSpeedIndex = 3;
        this.mPlaybackSpeedMultBy100List = new ArrayList();
        int[] intArray = this.mResources.getIntArray(R.array.speed_multiplied_by_100);
        for (int valueOf : intArray) {
            this.mPlaybackSpeedMultBy100List.add(Integer.valueOf(valueOf));
        }
        this.mCustomPlaybackSpeedIndex = -1;
    }

    /* access modifiers changed from: package-private */
    public ImageButton findControlButton(int i, int i2) {
        View view = this.mTransportControlsMap.get(i);
        if (view == null) {
            return null;
        }
        return (ImageButton) view.findViewById(i2);
    }

    /* access modifiers changed from: package-private */
    public ImageButton findFullSizedControlButton(int i) {
        ImageButton findControlButton = findControlButton(1, i);
        if (findControlButton != null) {
            return findControlButton;
        }
        throw new IllegalArgumentException("Couldn't find a view that has the given id");
    }

    /* access modifiers changed from: package-private */
    public boolean isCurrentMediaItemFromNetwork() {
        ensurePlayerIsNotNull();
        MediaItem currentMediaItem = this.mPlayer.getCurrentMediaItem();
        if (!(currentMediaItem instanceof UriMediaItem)) {
            return false;
        }
        return UriUtil.isFromNetwork(((UriMediaItem) currentMediaItem).getUri());
    }

    /* access modifiers changed from: package-private */
    public void displaySettingsWindow(BaseAdapter baseAdapter) {
        this.mSettingsListView.setAdapter(baseAdapter);
        this.mSettingsWindow.setWidth(this.mSizeType == 0 ? this.mEmbeddedSettingsItemWidth : this.mFullSettingsItemWidth);
        int measuredHeight = getMeasuredHeight() + (this.mSettingsWindowMargin * 2);
        int count = baseAdapter.getCount() * this.mSettingsItemHeight;
        if (count < measuredHeight) {
            measuredHeight = count;
        }
        this.mSettingsWindow.setHeight(measuredHeight);
        this.mNeedToHideBars = false;
        this.mSettingsWindow.dismiss();
        if (measuredHeight > 0) {
            PopupWindow popupWindow = this.mSettingsWindow;
            int i = this.mSettingsWindowMargin;
            popupWindow.showAsDropDown(this, i, i - measuredHeight, 85);
            this.mNeedToHideBars = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void dismissSettingsWindow() {
        this.mNeedToHideBars = true;
        this.mSettingsWindow.dismiss();
    }

    /* access modifiers changed from: package-private */
    public void animateOverflow(float f) {
        this.mExtraControls.setTranslationX((float) (((int) (((float) this.mExtraControls.getWidth()) * f)) * -1));
        float f2 = 1.0f - f;
        this.mTimeView.setAlpha(f2);
        this.mBasicControls.setAlpha(f2);
        this.mFullTransportControls.setTranslationX((float) (((int) (((float) findFullSizedControlButton(R.id.pause).getLeft()) * f)) * -1));
        findFullSizedControlButton(R.id.ffwd).setAlpha(f2);
    }

    /* access modifiers changed from: package-private */
    public void resetHideCallbacks() {
        removeCallbacks(this.mHideMainBars);
        removeCallbacks(this.mHideProgressBar);
        postDelayedRunnable(this.mHideMainBars, this.mDelayedAnimationIntervalMs);
    }

    /* access modifiers changed from: package-private */
    public void updateAllowedCommands() {
        ensurePlayerIsNotNull();
        boolean canPause = this.mPlayer.canPause();
        boolean canSeekBackward = this.mPlayer.canSeekBackward();
        boolean canSeekForward = this.mPlayer.canSeekForward();
        boolean canSkipToPrevious = this.mPlayer.canSkipToPrevious();
        boolean canSkipToNext = this.mPlayer.canSkipToNext();
        int size = this.mTransportControlsMap.size();
        int i = 0;
        while (true) {
            int i2 = 8;
            if (i >= size) {
                break;
            }
            int keyAt = this.mTransportControlsMap.keyAt(i);
            ImageButton findControlButton = findControlButton(keyAt, R.id.pause);
            if (findControlButton != null) {
                findControlButton.setVisibility(canPause ? 0 : 8);
            }
            ImageButton findControlButton2 = findControlButton(keyAt, R.id.rew);
            if (findControlButton2 != null) {
                findControlButton2.setVisibility(canSeekBackward ? 0 : 8);
            }
            ImageButton findControlButton3 = findControlButton(keyAt, R.id.ffwd);
            if (findControlButton3 != null) {
                findControlButton3.setVisibility(canSeekForward ? 0 : 8);
            }
            ImageButton findControlButton4 = findControlButton(keyAt, R.id.prev);
            if (findControlButton4 != null) {
                findControlButton4.setVisibility(canSkipToPrevious ? 0 : 8);
            }
            ImageButton findControlButton5 = findControlButton(keyAt, R.id.next);
            if (findControlButton5 != null) {
                if (canSkipToNext) {
                    i2 = 0;
                }
                findControlButton5.setVisibility(i2);
            }
            i++;
        }
        if (this.mPlayer.canSeekTo()) {
            this.mSeekAvailable = true;
            this.mProgress.setEnabled(true);
        }
        if (this.mPlayer.canSelectDeselectTrack()) {
            this.mSubtitleButton.setVisibility(0);
        } else {
            this.mSubtitleButton.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void updatePrevNextButtons(int i, int i2) {
        int size = this.mTransportControlsMap.size();
        for (int i3 = 0; i3 < size; i3++) {
            int keyAt = this.mTransportControlsMap.keyAt(i3);
            ImageButton findControlButton = findControlButton(keyAt, R.id.prev);
            if (findControlButton != null) {
                if (i > -1) {
                    findControlButton.setAlpha(1.0f);
                    findControlButton.setEnabled(true);
                } else {
                    findControlButton.setAlpha(0.5f);
                    findControlButton.setEnabled(false);
                }
            }
            ImageButton findControlButton2 = findControlButton(keyAt, R.id.next);
            if (findControlButton2 != null) {
                if (i2 > -1) {
                    findControlButton2.setAlpha(1.0f);
                    findControlButton2.setEnabled(true);
                } else {
                    findControlButton2.setAlpha(0.5f);
                    findControlButton2.setEnabled(false);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldNotHideBars() {
        if ((!isCurrentItemMusic() || this.mSizeType != 1) && !this.mAccessibilityManager.isTouchExplorationEnabled() && this.mPlayer.getPlayerState() != 3 && this.mPlayer.getPlayerState() != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void seekTo(long j, boolean z) {
        ensurePlayerIsNotNull();
        long j2 = this.mDuration;
        this.mProgress.setProgress(j2 <= 0 ? 0 : (int) ((1000 * j) / j2));
        this.mCurrentTime.setText(stringForTime(j));
        if (this.mCurrentSeekPosition == -1) {
            this.mCurrentSeekPosition = j;
            if (z) {
                this.mPlayer.seekTo(j);
                return;
            }
            return;
        }
        this.mNextSeekPosition = j;
    }

    /* access modifiers changed from: package-private */
    public long getLatestSeekPosition() {
        ensurePlayerIsNotNull();
        long j = this.mNextSeekPosition;
        if (j != -1) {
            return j;
        }
        long j2 = this.mCurrentSeekPosition;
        if (j2 != -1) {
            return j2;
        }
        return this.mPlayer.getCurrentPosition();
    }

    /* access modifiers changed from: package-private */
    public void removeCustomSpeedFromList() {
        this.mPlaybackSpeedMultBy100List.remove(this.mCustomPlaybackSpeedIndex);
        this.mPlaybackSpeedTextList.remove(this.mCustomPlaybackSpeedIndex);
        this.mCustomPlaybackSpeedIndex = -1;
    }

    /* access modifiers changed from: package-private */
    public void updateSelectedSpeed(int i, String str) {
        this.mSelectedSpeedIndex = i;
        this.mSettingsSubTextsList.set(1, str);
        this.mSubSettingsAdapter.setTexts(this.mPlaybackSpeedTextList);
        this.mSubSettingsAdapter.setCheckPosition(this.mSelectedSpeedIndex);
    }

    /* access modifiers changed from: package-private */
    public void updateReplayButton(boolean z) {
        ImageButton findControlButton = findControlButton(this.mSizeType, R.id.ffwd);
        if (z) {
            this.mIsShowingReplayButton = true;
            updatePlayButton(2);
            if (findControlButton != null) {
                findControlButton.setAlpha(0.5f);
                findControlButton.setEnabled(false);
                return;
            }
            return;
        }
        this.mIsShowingReplayButton = false;
        PlayerWrapper playerWrapper = this.mPlayer;
        if (playerWrapper == null || !playerWrapper.isPlaying()) {
            updatePlayButton(1);
        } else {
            updatePlayButton(0);
        }
        if (findControlButton != null) {
            findControlButton.setAlpha(1.0f);
            findControlButton.setEnabled(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void updatePlayButton(int i) {
        Drawable drawable;
        String str;
        ImageButton findControlButton = findControlButton(this.mSizeType, R.id.pause);
        if (findControlButton != null) {
            if (i == 0) {
                drawable = this.mResources.getDrawable(R.drawable.ic_pause_circle_filled);
                str = this.mResources.getString(R.string.mcv2_pause_button_desc);
            } else if (i == 1) {
                drawable = this.mResources.getDrawable(R.drawable.ic_play_circle_filled);
                str = this.mResources.getString(R.string.mcv2_play_button_desc);
            } else if (i == 2) {
                drawable = this.mResources.getDrawable(R.drawable.ic_replay_circle_filled);
                str = this.mResources.getString(R.string.mcv2_replay_button_desc);
            } else {
                throw new IllegalArgumentException("unknown type " + i);
            }
            findControlButton.setImageDrawable(drawable);
            findControlButton.setContentDescription(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void postDelayedRunnable(Runnable runnable, long j) {
        if (j != -1) {
            postDelayed(runnable, j);
        }
    }

    /* access modifiers changed from: package-private */
    public void ensurePlayerIsNotNull() {
        if (this.mPlayer == null) {
            throw new IllegalStateException("mPlayer must not be null");
        }
    }

    /* access modifiers changed from: package-private */
    public void updateTracks(PlayerWrapper playerWrapper, List<SessionPlayer.TrackInfo> list) {
        String str;
        this.mVideoTrackCount = 0;
        this.mAudioTracks = new ArrayList();
        this.mSubtitleTracks = new ArrayList();
        this.mSelectedAudioTrackIndex = 0;
        this.mSelectedSubtitleTrackIndex = -1;
        SessionPlayer.TrackInfo selectedTrack = playerWrapper.getSelectedTrack(2);
        SessionPlayer.TrackInfo selectedTrack2 = playerWrapper.getSelectedTrack(4);
        for (int i = 0; i < list.size(); i++) {
            int trackType = list.get(i).getTrackType();
            if (trackType == 1) {
                this.mVideoTrackCount++;
            } else if (trackType == 2) {
                if (list.get(i).equals(selectedTrack)) {
                    this.mSelectedAudioTrackIndex = this.mAudioTracks.size();
                }
                this.mAudioTracks.add(list.get(i));
            } else if (trackType == 4) {
                if (list.get(i).equals(selectedTrack2)) {
                    this.mSelectedSubtitleTrackIndex = this.mSubtitleTracks.size();
                }
                this.mSubtitleTracks.add(list.get(i));
            }
        }
        this.mAudioTrackDescriptionList = new ArrayList();
        if (this.mAudioTracks.isEmpty()) {
            this.mAudioTrackDescriptionList.add(this.mResources.getString(R.string.MediaControlView_audio_track_none_text));
        } else {
            int i2 = 0;
            while (i2 < this.mAudioTracks.size()) {
                i2++;
                this.mAudioTrackDescriptionList.add(this.mResources.getString(R.string.MediaControlView_audio_track_number_text, new Object[]{Integer.valueOf(i2)}));
            }
        }
        this.mSettingsSubTextsList.set(0, this.mAudioTrackDescriptionList.get(this.mSelectedAudioTrackIndex));
        this.mSubtitleDescriptionsList = new ArrayList();
        if (!this.mSubtitleTracks.isEmpty()) {
            this.mSubtitleDescriptionsList.add(this.mResources.getString(R.string.MediaControlView_subtitle_off_text));
            for (int i3 = 0; i3 < this.mSubtitleTracks.size(); i3++) {
                String iSO3Language = this.mSubtitleTracks.get(i3).getLanguage().getISO3Language();
                if (iSO3Language.equals("und")) {
                    str = this.mResources.getString(R.string.MediaControlView_subtitle_track_number_text, new Object[]{Integer.valueOf(i3 + 1)});
                } else {
                    str = this.mResources.getString(R.string.MediaControlView_subtitle_track_number_and_lang_text, new Object[]{Integer.valueOf(i3 + 1), iSO3Language});
                }
                this.mSubtitleDescriptionsList.add(str);
            }
            this.mSubtitleButton.setVisibility(0);
            this.mSubtitleButton.setAlpha(1.0f);
            this.mSubtitleButton.setEnabled(true);
        } else if (isCurrentItemMusic()) {
            this.mSubtitleButton.setVisibility(8);
        } else {
            this.mSubtitleButton.setVisibility(0);
            this.mSubtitleButton.setAlpha(0.5f);
            this.mSubtitleButton.setEnabled(false);
        }
    }

    private boolean hasActualVideo() {
        if (this.mVideoTrackCount > 0) {
            return true;
        }
        VideoSize videoSize = this.mPlayer.getVideoSize();
        if (videoSize.getHeight() <= 0 || videoSize.getWidth() <= 0) {
            return false;
        }
        Log.w("MediaControlView", "video track count is zero, but it renders video. size: " + videoSize);
        return true;
    }

    private boolean isCurrentItemMusic() {
        return !hasActualVideo() && this.mAudioTracks.size() > 0;
    }

    private class SettingsAdapter extends BaseAdapter {
        private List<Integer> mIconIds;
        private List<String> mMainTexts;
        private List<String> mSubTexts;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        SettingsAdapter(List<String> list, List<String> list2, List<Integer> list3) {
            this.mMainTexts = list;
            this.mSubTexts = list2;
            this.mIconIds = list3;
        }

        public int getCount() {
            List<String> list = this.mMainTexts;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflateLayout = MediaControlView.inflateLayout(MediaControlView.this.getContext(), R.layout.settings_list_item);
            TextView textView = (TextView) inflateLayout.findViewById(R.id.sub_text);
            ImageView imageView = (ImageView) inflateLayout.findViewById(R.id.icon);
            ((TextView) inflateLayout.findViewById(R.id.main_text)).setText(this.mMainTexts.get(i));
            List<String> list = this.mSubTexts;
            if (list == null || "".equals(list.get(i))) {
                textView.setVisibility(8);
            } else {
                textView.setText(this.mSubTexts.get(i));
            }
            List<Integer> list2 = this.mIconIds;
            if (list2 == null || list2.get(i).intValue() == -1) {
                imageView.setVisibility(8);
            } else {
                imageView.setImageDrawable(MediaControlView.this.mResources.getDrawable(this.mIconIds.get(i).intValue()));
            }
            return inflateLayout;
        }

        public void setSubTexts(List<String> list) {
            this.mSubTexts = list;
        }
    }

    private class SubSettingsAdapter extends BaseAdapter {
        private int mCheckPosition;
        private List<String> mTexts;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        SubSettingsAdapter(List<String> list, int i) {
            this.mTexts = list;
            this.mCheckPosition = i;
        }

        public String getMainText(int i) {
            List<String> list = this.mTexts;
            return (list == null || i >= list.size()) ? "" : this.mTexts.get(i);
        }

        public int getCount() {
            List<String> list = this.mTexts;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflateLayout = MediaControlView.inflateLayout(MediaControlView.this.getContext(), R.layout.sub_settings_list_item);
            ImageView imageView = (ImageView) inflateLayout.findViewById(R.id.check);
            ((TextView) inflateLayout.findViewById(R.id.text)).setText(this.mTexts.get(i));
            if (i != this.mCheckPosition) {
                imageView.setVisibility(4);
            }
            return inflateLayout;
        }

        public void setTexts(List<String> list) {
            this.mTexts = list;
        }

        public void setCheckPosition(int i) {
            this.mCheckPosition = i;
        }
    }

    class PlayerCallback extends PlayerWrapper.PlayerCallback {
        PlayerCallback() {
        }

        public void onPlayerStateChanged(PlayerWrapper playerWrapper, int i) {
            if (playerWrapper == MediaControlView.this.mPlayer) {
                if (MediaControlView.DEBUG) {
                    Log.d("MediaControlView", "onPlayerStateChanged(state: " + i + ")");
                }
                MediaControlView.this.updateTimeViews(playerWrapper.getCurrentMediaItem());
                if (i == 1) {
                    MediaControlView.this.updatePlayButton(1);
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.removeCallbacks(mediaControlView.mUpdateProgress);
                    MediaControlView mediaControlView2 = MediaControlView.this;
                    mediaControlView2.removeCallbacks(mediaControlView2.mHideMainBars);
                    MediaControlView mediaControlView3 = MediaControlView.this;
                    mediaControlView3.removeCallbacks(mediaControlView3.mHideProgressBar);
                    MediaControlView mediaControlView4 = MediaControlView.this;
                    mediaControlView4.post(mediaControlView4.mShowAllBars);
                } else if (i == 2) {
                    MediaControlView mediaControlView5 = MediaControlView.this;
                    mediaControlView5.removeCallbacks(mediaControlView5.mUpdateProgress);
                    MediaControlView mediaControlView6 = MediaControlView.this;
                    mediaControlView6.post(mediaControlView6.mUpdateProgress);
                    MediaControlView.this.resetHideCallbacks();
                    MediaControlView.this.updateReplayButton(false);
                } else if (i == 3) {
                    MediaControlView.this.updatePlayButton(1);
                    MediaControlView mediaControlView7 = MediaControlView.this;
                    mediaControlView7.removeCallbacks(mediaControlView7.mUpdateProgress);
                    if (MediaControlView.this.getWindowToken() != null) {
                        new AlertDialog.Builder(MediaControlView.this.getContext()).setMessage(R.string.mcv2_playback_error_text).setPositiveButton(R.string.mcv2_error_dialog_button, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).setCancelable(true).show();
                    }
                }
            }
        }

        public void onSeekCompleted(PlayerWrapper playerWrapper, long j) {
            if (playerWrapper == MediaControlView.this.mPlayer) {
                if (MediaControlView.DEBUG) {
                    Log.d("MediaControlView", "onSeekCompleted(): " + j);
                }
                MediaControlView.this.mProgress.setProgress(MediaControlView.this.mDuration <= 0 ? 0 : (int) ((1000 * j) / MediaControlView.this.mDuration));
                MediaControlView.this.mCurrentTime.setText(MediaControlView.this.stringForTime(j));
                if (MediaControlView.this.mNextSeekPosition != -1) {
                    MediaControlView mediaControlView = MediaControlView.this;
                    mediaControlView.mCurrentSeekPosition = mediaControlView.mNextSeekPosition;
                    playerWrapper.seekTo(MediaControlView.this.mNextSeekPosition);
                    MediaControlView.this.mNextSeekPosition = -1;
                    return;
                }
                MediaControlView.this.mCurrentSeekPosition = -1;
                if (!MediaControlView.this.mDragging) {
                    MediaControlView mediaControlView2 = MediaControlView.this;
                    mediaControlView2.removeCallbacks(mediaControlView2.mUpdateProgress);
                    MediaControlView mediaControlView3 = MediaControlView.this;
                    mediaControlView3.removeCallbacks(mediaControlView3.mHideMainBars);
                    MediaControlView mediaControlView4 = MediaControlView.this;
                    mediaControlView4.post(mediaControlView4.mUpdateProgress);
                    MediaControlView mediaControlView5 = MediaControlView.this;
                    mediaControlView5.postDelayedRunnable(mediaControlView5.mHideMainBars, MediaControlView.this.mDelayedAnimationIntervalMs);
                }
            }
        }

        public void onCurrentMediaItemChanged(PlayerWrapper playerWrapper, MediaItem mediaItem) {
            if (playerWrapper == MediaControlView.this.mPlayer) {
                if (MediaControlView.DEBUG) {
                    Log.d("MediaControlView", "onCurrentMediaItemChanged(): " + mediaItem);
                }
                MediaControlView.this.updateTimeViews(mediaItem);
                MediaControlView.this.updateTitleView(mediaItem);
                MediaControlView.this.updatePrevNextButtons(playerWrapper.getPreviousMediaItemIndex(), playerWrapper.getNextMediaItemIndex());
            }
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistChanged(PlayerWrapper playerWrapper, List<MediaItem> list, MediaMetadata mediaMetadata) {
            if (playerWrapper == MediaControlView.this.mPlayer) {
                if (MediaControlView.DEBUG) {
                    Log.d("MediaControlView", "onPlaylistChanged(): list: " + list + ", metadata: " + mediaMetadata);
                }
                MediaControlView.this.updatePrevNextButtons(playerWrapper.getPreviousMediaItemIndex(), playerWrapper.getNextMediaItemIndex());
            }
        }

        public void onPlaybackCompleted(PlayerWrapper playerWrapper) {
            if (playerWrapper == MediaControlView.this.mPlayer) {
                if (MediaControlView.DEBUG) {
                    Log.d("MediaControlView", "onPlaybackCompleted()");
                }
                MediaControlView.this.updateReplayButton(true);
                MediaControlView.this.mProgress.setProgress(1000);
                TextView textView = MediaControlView.this.mCurrentTime;
                MediaControlView mediaControlView = MediaControlView.this;
                textView.setText(mediaControlView.stringForTime(mediaControlView.mDuration));
            }
        }

        public void onAllowedCommandsChanged(PlayerWrapper playerWrapper, SessionCommandGroup sessionCommandGroup) {
            if (playerWrapper == MediaControlView.this.mPlayer) {
                MediaControlView.this.updateAllowedCommands();
            }
        }

        public void onPlaybackSpeedChanged(PlayerWrapper playerWrapper, float f) {
            if (playerWrapper == MediaControlView.this.mPlayer) {
                int round = Math.round(f * 100.0f);
                if (MediaControlView.this.mCustomPlaybackSpeedIndex != -1) {
                    MediaControlView.this.removeCustomSpeedFromList();
                }
                int i = 0;
                if (MediaControlView.this.mPlaybackSpeedMultBy100List.contains(Integer.valueOf(round))) {
                    while (i < MediaControlView.this.mPlaybackSpeedMultBy100List.size()) {
                        if (round == MediaControlView.this.mPlaybackSpeedMultBy100List.get(i).intValue()) {
                            MediaControlView mediaControlView = MediaControlView.this;
                            mediaControlView.updateSelectedSpeed(i, mediaControlView.mPlaybackSpeedTextList.get(i));
                            return;
                        }
                        i++;
                    }
                    return;
                }
                String string = MediaControlView.this.mResources.getString(R.string.MediaControlView_custom_playback_speed_text, new Object[]{Float.valueOf(((float) round) / 100.0f)});
                while (true) {
                    if (i >= MediaControlView.this.mPlaybackSpeedMultBy100List.size()) {
                        break;
                    } else if (round < MediaControlView.this.mPlaybackSpeedMultBy100List.get(i).intValue()) {
                        MediaControlView.this.mPlaybackSpeedMultBy100List.add(i, Integer.valueOf(round));
                        MediaControlView.this.mPlaybackSpeedTextList.add(i, string);
                        MediaControlView.this.updateSelectedSpeed(i, string);
                        break;
                    } else {
                        if (i == MediaControlView.this.mPlaybackSpeedMultBy100List.size() - 1 && round > MediaControlView.this.mPlaybackSpeedMultBy100List.get(i).intValue()) {
                            MediaControlView.this.mPlaybackSpeedMultBy100List.add(Integer.valueOf(round));
                            MediaControlView.this.mPlaybackSpeedTextList.add(string);
                            MediaControlView.this.updateSelectedSpeed(i + 1, string);
                        }
                        i++;
                    }
                }
                MediaControlView mediaControlView2 = MediaControlView.this;
                mediaControlView2.mCustomPlaybackSpeedIndex = mediaControlView2.mSelectedSpeedIndex;
            }
        }

        /* access modifiers changed from: package-private */
        public void onTrackInfoChanged(PlayerWrapper playerWrapper, List<SessionPlayer.TrackInfo> list) {
            if (playerWrapper == MediaControlView.this.mPlayer) {
                if (MediaControlView.DEBUG) {
                    Log.d("MediaControlView", "onTrackInfoChanged(): " + list);
                }
                MediaControlView.this.updateTracks(playerWrapper, list);
                MediaControlView.this.updateTimeViews(playerWrapper.getCurrentMediaItem());
                MediaControlView.this.updateTitleView(playerWrapper.getCurrentMediaItem());
            }
        }

        /* access modifiers changed from: package-private */
        public void onTrackSelected(PlayerWrapper playerWrapper, SessionPlayer.TrackInfo trackInfo) {
            if (playerWrapper == MediaControlView.this.mPlayer) {
                if (MediaControlView.DEBUG) {
                    Log.d("MediaControlView", "onTrackSelected(): " + trackInfo);
                }
                if (trackInfo.getTrackType() == 4) {
                    for (int i = 0; i < MediaControlView.this.mSubtitleTracks.size(); i++) {
                        if (MediaControlView.this.mSubtitleTracks.get(i).equals(trackInfo)) {
                            MediaControlView.this.mSelectedSubtitleTrackIndex = i;
                            if (MediaControlView.this.mSettingsMode == 2) {
                                MediaControlView.this.mSubSettingsAdapter.setCheckPosition(MediaControlView.this.mSelectedSubtitleTrackIndex + 1);
                            }
                            MediaControlView.this.mSubtitleButton.setImageDrawable(MediaControlView.this.mResources.getDrawable(R.drawable.ic_subtitle_on));
                            MediaControlView.this.mSubtitleButton.setContentDescription(MediaControlView.this.mResources.getString(R.string.mcv2_cc_is_on));
                            return;
                        }
                    }
                } else if (trackInfo.getTrackType() == 2) {
                    for (int i2 = 0; i2 < MediaControlView.this.mAudioTracks.size(); i2++) {
                        if (MediaControlView.this.mAudioTracks.get(i2).equals(trackInfo)) {
                            MediaControlView.this.mSelectedAudioTrackIndex = i2;
                            MediaControlView.this.mSettingsSubTextsList.set(0, MediaControlView.this.mSubSettingsAdapter.getMainText(MediaControlView.this.mSelectedAudioTrackIndex));
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onTrackDeselected(PlayerWrapper playerWrapper, SessionPlayer.TrackInfo trackInfo) {
            if (playerWrapper == MediaControlView.this.mPlayer) {
                if (MediaControlView.DEBUG) {
                    Log.d("MediaControlView", "onTrackDeselected(): " + trackInfo);
                }
                if (trackInfo.getTrackType() == 4) {
                    for (int i = 0; i < MediaControlView.this.mSubtitleTracks.size(); i++) {
                        if (MediaControlView.this.mSubtitleTracks.get(i).equals(trackInfo)) {
                            MediaControlView.this.mSelectedSubtitleTrackIndex = -1;
                            if (MediaControlView.this.mSettingsMode == 2) {
                                MediaControlView.this.mSubSettingsAdapter.setCheckPosition(MediaControlView.this.mSelectedSubtitleTrackIndex + 1);
                            }
                            MediaControlView.this.mSubtitleButton.setImageDrawable(MediaControlView.this.mResources.getDrawable(R.drawable.ic_subtitle_off));
                            MediaControlView.this.mSubtitleButton.setContentDescription(MediaControlView.this.mResources.getString(R.string.mcv2_cc_is_off));
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onVideoSizeChanged(PlayerWrapper playerWrapper, MediaItem mediaItem, VideoSize videoSize) {
            List<SessionPlayer.TrackInfo> trackInfo;
            if (playerWrapper == MediaControlView.this.mPlayer) {
                if (MediaControlView.DEBUG) {
                    Log.d("MediaControlView", "onVideoSizeChanged(): " + videoSize);
                }
                if (MediaControlView.this.mVideoTrackCount == 0 && videoSize.getHeight() > 0 && videoSize.getWidth() > 0 && (trackInfo = playerWrapper.getTrackInfo()) != null) {
                    MediaControlView.this.updateTracks(playerWrapper, trackInfo);
                }
            }
        }
    }
}
