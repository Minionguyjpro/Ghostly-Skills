package com.mopub.nativeads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import com.google.android.gms.plus.PlusShare;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibilityTracker;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.MraidVideoPlayerActivity;
import com.mopub.mobileads.VastErrorCode;
import com.mopub.mobileads.VastManager;
import com.mopub.mobileads.VastTracker;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.mobileads.VideoViewabilityTracker;
import com.mopub.mobileads.factories.VastManagerFactory;
import com.mopub.nativeads.CustomEventNative;
import com.mopub.nativeads.MediaLayout;
import com.mopub.nativeads.NativeImageHelper;
import com.mopub.nativeads.NativeVideoController;
import com.mopub.network.TrackingRequest;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MoPubCustomEventVideoNative extends CustomEventNative {
    public static final String ADAPTER_NAME = MoPubCustomEventVideoNative.class.getSimpleName();
    private MoPubVideoNativeAd videoNativeAd;

    /* access modifiers changed from: protected */
    public void loadNativeAd(Context context, CustomEventNative.CustomEventNativeListener customEventNativeListener, Map<String, Object> map, Map<String, String> map2) {
        CustomEventNative.CustomEventNativeListener customEventNativeListener2 = customEventNativeListener;
        Map<String, Object> map3 = map;
        Object obj = map.get(DataKeys.JSON_BODY_KEY);
        if (!(obj instanceof JSONObject)) {
            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(NativeErrorCode.INVALID_RESPONSE.getIntCode()), NativeErrorCode.INVALID_RESPONSE);
            customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
            return;
        }
        map.get(DataKeys.EVENT_DETAILS);
        VideoResponseHeaders videoResponseHeaders = new VideoResponseHeaders(map2);
        if (!videoResponseHeaders.hasValidHeaders()) {
            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(NativeErrorCode.INVALID_RESPONSE.getIntCode()), NativeErrorCode.INVALID_RESPONSE);
            customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
            return;
        }
        Object obj2 = map.get(DataKeys.CLICK_TRACKING_URL_KEY);
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (!TextUtils.isEmpty(str)) {
                MoPubVideoNativeAd moPubVideoNativeAd = new MoPubVideoNativeAd(context, (JSONObject) obj, customEventNativeListener, videoResponseHeaders, str);
                this.videoNativeAd = moPubVideoNativeAd;
                try {
                    moPubVideoNativeAd.loadAd();
                    return;
                } catch (IllegalArgumentException unused) {
                    MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(NativeErrorCode.UNSPECIFIED.getIntCode()), NativeErrorCode.UNSPECIFIED);
                    customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                    return;
                }
            }
        }
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(NativeErrorCode.UNSPECIFIED.getIntCode()), NativeErrorCode.UNSPECIFIED);
        customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        MoPubVideoNativeAd moPubVideoNativeAd = this.videoNativeAd;
        if (moPubVideoNativeAd != null) {
            moPubVideoNativeAd.invalidate();
        }
    }

    public static class MoPubVideoNativeAd extends VideoNativeAd implements AudioManager.OnAudioFocusChangeListener, VastManager.VastManagerListener, NativeVideoController.NativeVideoProgressRunnable.ProgressListener {
        static final String PRIVACY_INFORMATION_CLICKTHROUGH_URL = "https://www.mopub.com/optout/";
        /* access modifiers changed from: private */
        public final Context mContext;
        /* access modifiers changed from: private */
        public final CustomEventNative.CustomEventNativeListener mCustomEventNativeListener;
        /* access modifiers changed from: private */
        public boolean mEnded;
        private boolean mError;
        /* access modifiers changed from: private */
        public final long mId;
        private final JSONObject mJsonObject;
        /* access modifiers changed from: private */
        public int mLatestVideoControllerState;
        /* access modifiers changed from: private */
        public boolean mLatestVisibility;
        /* access modifiers changed from: private */
        public MediaLayout mMediaLayout;
        private final String mMoPubClickTrackingUrl;
        /* access modifiers changed from: private */
        public boolean mMuted;
        /* access modifiers changed from: private */
        public NativeVideoController mNativeVideoController;
        private final NativeVideoControllerFactory mNativeVideoControllerFactory;
        /* access modifiers changed from: private */
        public boolean mNeedsPrepare;
        /* access modifiers changed from: private */
        public boolean mNeedsSeek;
        private boolean mPauseCanBeTracked;
        private boolean mResumeCanBeTracked;
        private View mRootView;
        /* access modifiers changed from: private */
        public final VastManager mVastManager;
        VastVideoConfig mVastVideoConfig;
        private final VideoResponseHeaders mVideoResponseHeaders;
        /* access modifiers changed from: private */
        public VideoState mVideoState;
        private final VisibilityTracker mVideoVisibleTracking;

        public enum VideoState {
            CREATED,
            LOADING,
            BUFFERING,
            PAUSED,
            PLAYING,
            PLAYING_MUTED,
            ENDED,
            FAILED_LOAD
        }

        enum Parameter {
            IMPRESSION_TRACKER("imptracker", true),
            CLICK_TRACKER("clktracker", true),
            TITLE(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, false),
            TEXT("text", false),
            IMAGE_URL("mainimage", false),
            ICON_URL("iconimage", false),
            CLICK_DESTINATION("clk", false),
            FALLBACK("fallback", false),
            CALL_TO_ACTION("ctatext", false),
            VAST_VIDEO("video", false),
            PRIVACY_INFORMATION_ICON_IMAGE_URL("privacyicon", false),
            PRIVACY_INFORMATION_ICON_CLICKTHROUGH_URL("privacyclkurl", false),
            SPONSORED("sponsored", false);
            
            static final Set<String> requiredKeys = null;
            final String mName;
            final boolean mRequired;

            static {
                int i;
                requiredKeys = new HashSet();
                for (Parameter parameter : values()) {
                    if (parameter.mRequired) {
                        requiredKeys.add(parameter.mName);
                    }
                }
            }

            private Parameter(String str, boolean z) {
                Preconditions.checkNotNull(str);
                this.mName = str;
                this.mRequired = z;
            }

            static Parameter from(String str) {
                Preconditions.checkNotNull(str);
                for (Parameter parameter : values()) {
                    if (parameter.mName.equals(str)) {
                        return parameter;
                    }
                }
                return null;
            }
        }

        public MoPubVideoNativeAd(Context context, JSONObject jSONObject, CustomEventNative.CustomEventNativeListener customEventNativeListener, VideoResponseHeaders videoResponseHeaders, String str) {
            this(context, jSONObject, customEventNativeListener, videoResponseHeaders, new VisibilityTracker(context), new NativeVideoControllerFactory(), str, VastManagerFactory.create(context.getApplicationContext(), false));
        }

        MoPubVideoNativeAd(Context context, JSONObject jSONObject, CustomEventNative.CustomEventNativeListener customEventNativeListener, VideoResponseHeaders videoResponseHeaders, VisibilityTracker visibilityTracker, NativeVideoControllerFactory nativeVideoControllerFactory, String str, VastManager vastManager) {
            this.mPauseCanBeTracked = false;
            this.mResumeCanBeTracked = false;
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(jSONObject);
            Preconditions.checkNotNull(customEventNativeListener);
            Preconditions.checkNotNull(videoResponseHeaders);
            Preconditions.checkNotNull(visibilityTracker);
            Preconditions.checkNotNull(nativeVideoControllerFactory);
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(vastManager);
            this.mContext = context.getApplicationContext();
            this.mJsonObject = jSONObject;
            this.mCustomEventNativeListener = customEventNativeListener;
            this.mVideoResponseHeaders = videoResponseHeaders;
            this.mNativeVideoControllerFactory = nativeVideoControllerFactory;
            this.mMoPubClickTrackingUrl = str;
            this.mId = Utils.generateUniqueId();
            this.mNeedsSeek = true;
            this.mVideoState = VideoState.CREATED;
            this.mNeedsPrepare = true;
            this.mLatestVideoControllerState = 1;
            this.mMuted = true;
            this.mVideoVisibleTracking = visibilityTracker;
            visibilityTracker.setVisibilityTrackerListener(new VisibilityTracker.VisibilityTrackerListener() {
                public void onVisibilityChanged(List<View> list, List<View> list2) {
                    if (!list.isEmpty() && !MoPubVideoNativeAd.this.mLatestVisibility) {
                        boolean unused = MoPubVideoNativeAd.this.mLatestVisibility = true;
                        MoPubVideoNativeAd.this.maybeChangeState();
                    } else if (!list2.isEmpty() && MoPubVideoNativeAd.this.mLatestVisibility) {
                        boolean unused2 = MoPubVideoNativeAd.this.mLatestVisibility = false;
                        MoPubVideoNativeAd.this.maybeChangeState();
                    }
                }
            });
            this.mVastManager = vastManager;
        }

        /* access modifiers changed from: package-private */
        public void loadAd() throws IllegalArgumentException {
            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, MoPubCustomEventVideoNative.ADAPTER_NAME);
            if (containsRequiredKeys(this.mJsonObject)) {
                Iterator<String> keys = this.mJsonObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    Parameter from = Parameter.from(next);
                    if (from != null) {
                        try {
                            addInstanceVariable(from, this.mJsonObject.opt(next));
                        } catch (ClassCastException unused) {
                            throw new IllegalArgumentException("JSONObject key (" + next + ") contained unexpected value.");
                        }
                    } else {
                        addExtra(next, this.mJsonObject.opt(next));
                    }
                }
                if (TextUtils.isEmpty(getPrivacyInformationIconClickThroughUrl())) {
                    setPrivacyInformationIconClickThroughUrl(PRIVACY_INFORMATION_CLICKTHROUGH_URL);
                }
                NativeImageHelper.preCacheImages(this.mContext, getAllImageUrls(), new NativeImageHelper.ImageListener() {
                    public void onImagesCached() {
                        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_SUCCESS, MoPubCustomEventVideoNative.ADAPTER_NAME);
                        if (!MoPubVideoNativeAd.this.isInvalidated()) {
                            VastManager access$300 = MoPubVideoNativeAd.this.mVastManager;
                            String vastVideo = MoPubVideoNativeAd.this.getVastVideo();
                            MoPubVideoNativeAd moPubVideoNativeAd = MoPubVideoNativeAd.this;
                            access$300.prepareVastVideoConfiguration(vastVideo, moPubVideoNativeAd, (String) null, moPubVideoNativeAd.mContext);
                        }
                    }

                    public void onImagesFailedToCache(NativeErrorCode nativeErrorCode) {
                        if (!MoPubVideoNativeAd.this.isInvalidated()) {
                            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, MoPubCustomEventVideoNative.ADAPTER_NAME, Integer.valueOf(nativeErrorCode.getIntCode()), nativeErrorCode);
                            MoPubVideoNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(nativeErrorCode);
                        }
                    }
                });
                return;
            }
            throw new IllegalArgumentException("JSONObject did not contain required keys.");
        }

        public void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig) {
            if (vastVideoConfig == null) {
                MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, MoPubCustomEventVideoNative.ADAPTER_NAME, Integer.valueOf(NativeErrorCode.INVALID_RESPONSE.getIntCode()), NativeErrorCode.INVALID_RESPONSE);
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
                return;
            }
            ArrayList arrayList = new ArrayList();
            NativeVideoController.VisibilityTrackingEvent visibilityTrackingEvent = new NativeVideoController.VisibilityTrackingEvent();
            visibilityTrackingEvent.strategy = new HeaderVisibilityStrategy(this);
            visibilityTrackingEvent.minimumPercentageVisible = this.mVideoResponseHeaders.getImpressionMinVisiblePercent();
            visibilityTrackingEvent.totalRequiredPlayTimeMs = this.mVideoResponseHeaders.getImpressionVisibleMs();
            arrayList.add(visibilityTrackingEvent);
            visibilityTrackingEvent.minimumVisiblePx = this.mVideoResponseHeaders.getImpressionVisiblePx();
            for (VastTracker content : vastVideoConfig.getImpressionTrackers()) {
                NativeVideoController.VisibilityTrackingEvent visibilityTrackingEvent2 = new NativeVideoController.VisibilityTrackingEvent();
                visibilityTrackingEvent2.strategy = new PayloadVisibilityStrategy(this.mContext, content.getContent());
                visibilityTrackingEvent2.minimumPercentageVisible = this.mVideoResponseHeaders.getImpressionMinVisiblePercent();
                visibilityTrackingEvent2.totalRequiredPlayTimeMs = this.mVideoResponseHeaders.getImpressionVisibleMs();
                arrayList.add(visibilityTrackingEvent2);
                visibilityTrackingEvent2.minimumVisiblePx = this.mVideoResponseHeaders.getImpressionVisiblePx();
            }
            this.mVastVideoConfig = vastVideoConfig;
            VideoViewabilityTracker videoViewabilityTracker = vastVideoConfig.getVideoViewabilityTracker();
            if (videoViewabilityTracker != null) {
                NativeVideoController.VisibilityTrackingEvent visibilityTrackingEvent3 = new NativeVideoController.VisibilityTrackingEvent();
                visibilityTrackingEvent3.strategy = new PayloadVisibilityStrategy(this.mContext, videoViewabilityTracker.getContent());
                visibilityTrackingEvent3.minimumPercentageVisible = videoViewabilityTracker.getPercentViewable();
                visibilityTrackingEvent3.totalRequiredPlayTimeMs = videoViewabilityTracker.getViewablePlaytimeMS();
                arrayList.add(visibilityTrackingEvent3);
            }
            this.mVastVideoConfig.setPrivacyInformationIconImageUrl(getPrivacyInformationIconImageUrl());
            this.mVastVideoConfig.setPrivacyInformationIconClickthroughUrl(getPrivacyInformationIconClickThroughUrl());
            HashSet<String> hashSet = new HashSet<>();
            hashSet.add(this.mMoPubClickTrackingUrl);
            hashSet.addAll(getClickTrackers());
            ArrayList arrayList2 = new ArrayList();
            for (String vastTracker : hashSet) {
                arrayList2.add(new VastTracker(vastTracker, false));
            }
            this.mVastVideoConfig.addClickTrackers(arrayList2);
            this.mVastVideoConfig.setClickThroughUrl(getClickDestinationUrl());
            this.mNativeVideoController = this.mNativeVideoControllerFactory.createForId(this.mId, this.mContext, arrayList, this.mVastVideoConfig);
            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_SUCCESS, MoPubCustomEventVideoNative.ADAPTER_NAME);
            this.mCustomEventNativeListener.onNativeAdLoaded(this);
            JSONObject videoTrackers = this.mVideoResponseHeaders.getVideoTrackers();
            if (videoTrackers != null) {
                this.mVastVideoConfig.addVideoTrackers(videoTrackers);
            }
        }

        private boolean containsRequiredKeys(JSONObject jSONObject) {
            Preconditions.checkNotNull(jSONObject);
            HashSet hashSet = new HashSet();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                hashSet.add(keys.next());
            }
            return hashSet.containsAll(Parameter.requiredKeys);
        }

        private void addInstanceVariable(Parameter parameter, Object obj) throws ClassCastException {
            Preconditions.checkNotNull(parameter);
            Preconditions.checkNotNull(obj);
            try {
                switch (AnonymousClass1.$SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[parameter.ordinal()]) {
                    case 1:
                        addImpressionTrackers(obj);
                        return;
                    case 2:
                        setTitle((String) obj);
                        return;
                    case 3:
                        setText((String) obj);
                        return;
                    case 4:
                        setMainImageUrl((String) obj);
                        return;
                    case 5:
                        setIconImageUrl((String) obj);
                        return;
                    case 6:
                        setClickDestinationUrl((String) obj);
                        return;
                    case 7:
                        parseClickTrackers(obj);
                        return;
                    case 8:
                        setCallToAction((String) obj);
                        return;
                    case 9:
                        setVastVideo((String) obj);
                        return;
                    case 10:
                        setPrivacyInformationIconImageUrl((String) obj);
                        return;
                    case 11:
                        setPrivacyInformationIconClickThroughUrl((String) obj);
                        return;
                    case 12:
                        setSponsored((String) obj);
                        break;
                }
                MoPubLog.AdapterLogEvent adapterLogEvent = MoPubLog.AdapterLogEvent.CUSTOM;
                MoPubLog.log(adapterLogEvent, "Unable to add JSON key to internal mapping: " + parameter.mName);
            } catch (ClassCastException e) {
                if (!parameter.mRequired) {
                    MoPubLog.AdapterLogEvent adapterLogEvent2 = MoPubLog.AdapterLogEvent.CUSTOM;
                    MoPubLog.log(adapterLogEvent2, "Ignoring class cast exception for optional key: " + parameter.mName);
                    return;
                }
                throw e;
            }
        }

        private void parseClickTrackers(Object obj) {
            if (obj instanceof JSONArray) {
                addClickTrackers(obj);
            } else {
                addClickTracker((String) obj);
            }
        }

        public void render(MediaLayout mediaLayout) {
            MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_ATTEMPTED, MoPubCustomEventVideoNative.ADAPTER_NAME);
            Preconditions.checkNotNull(mediaLayout);
            this.mVideoVisibleTracking.addView(this.mRootView, mediaLayout, this.mVideoResponseHeaders.getPlayVisiblePercent(), this.mVideoResponseHeaders.getPauseVisiblePercent(), this.mVideoResponseHeaders.getImpressionVisiblePx());
            this.mMediaLayout = mediaLayout;
            mediaLayout.initForVideo();
            this.mMediaLayout.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                }

                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                }

                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                    MoPubVideoNativeAd.this.mNativeVideoController.setListener(MoPubVideoNativeAd.this);
                    MoPubVideoNativeAd.this.mNativeVideoController.setOnAudioFocusChangeListener(MoPubVideoNativeAd.this);
                    MoPubVideoNativeAd.this.mNativeVideoController.setProgressListener(MoPubVideoNativeAd.this);
                    MoPubVideoNativeAd.this.mNativeVideoController.setTextureView(MoPubVideoNativeAd.this.mMediaLayout.getTextureView());
                    MoPubVideoNativeAd.this.mMediaLayout.resetProgress();
                    long duration = MoPubVideoNativeAd.this.mNativeVideoController.getDuration();
                    long currentPosition = MoPubVideoNativeAd.this.mNativeVideoController.getCurrentPosition();
                    if (MoPubVideoNativeAd.this.mLatestVideoControllerState == 4 || (duration > 0 && duration - currentPosition < 750)) {
                        boolean unused = MoPubVideoNativeAd.this.mEnded = true;
                    }
                    if (MoPubVideoNativeAd.this.mNeedsPrepare) {
                        boolean unused2 = MoPubVideoNativeAd.this.mNeedsPrepare = false;
                        MoPubVideoNativeAd.this.mNativeVideoController.prepare(MoPubVideoNativeAd.this);
                    }
                    boolean unused3 = MoPubVideoNativeAd.this.mNeedsSeek = true;
                    MoPubVideoNativeAd.this.maybeChangeState();
                    if (MoPubVideoNativeAd.this.mVideoState == VideoState.PLAYING || MoPubVideoNativeAd.this.mVideoState == VideoState.PLAYING_MUTED) {
                        MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_SUCCESS, MoPubCustomEventVideoNative.ADAPTER_NAME);
                    }
                }

                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    boolean unused = MoPubVideoNativeAd.this.mNeedsPrepare = true;
                    MoPubVideoNativeAd.this.mNativeVideoController.release(MoPubVideoNativeAd.this);
                    MoPubVideoNativeAd.this.applyState(VideoState.PAUSED);
                    return true;
                }
            });
            this.mMediaLayout.setPlayButtonClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MoPubVideoNativeAd.this.mMediaLayout.resetProgress();
                    MoPubVideoNativeAd.this.mNativeVideoController.seekTo(0);
                    boolean unused = MoPubVideoNativeAd.this.mEnded = false;
                    boolean unused2 = MoPubVideoNativeAd.this.mNeedsSeek = false;
                }
            });
            this.mMediaLayout.setMuteControlClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MoPubVideoNativeAd moPubVideoNativeAd = MoPubVideoNativeAd.this;
                    boolean unused = moPubVideoNativeAd.mMuted = !moPubVideoNativeAd.mMuted;
                    MoPubVideoNativeAd.this.maybeChangeState();
                }
            });
            this.mMediaLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MoPubLog.log(MoPubLog.AdapterLogEvent.CLICKED, MoPubCustomEventVideoNative.ADAPTER_NAME);
                    MoPubVideoNativeAd.this.prepareToLeaveView();
                    MoPubVideoNativeAd.this.mNativeVideoController.triggerImpressionTrackers();
                    MraidVideoPlayerActivity.startNativeVideo(MoPubVideoNativeAd.this.mContext, MoPubVideoNativeAd.this.mId, MoPubVideoNativeAd.this.mVastVideoConfig);
                }
            });
            if (this.mNativeVideoController.getPlaybackState() == 5) {
                this.mNativeVideoController.prepare(this);
            }
            applyState(VideoState.PAUSED);
        }

        public void prepare(View view) {
            Preconditions.checkNotNull(view);
            this.mRootView = view;
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MoPubVideoNativeAd.this.prepareToLeaveView();
                    MoPubVideoNativeAd.this.mNativeVideoController.triggerImpressionTrackers();
                    MoPubVideoNativeAd.this.mNativeVideoController.handleCtaClick(MoPubVideoNativeAd.this.mContext);
                }
            });
        }

        public void clear(View view) {
            Preconditions.checkNotNull(view);
            this.mNativeVideoController.clear();
            cleanUpMediaLayout();
        }

        public void destroy() {
            invalidate();
            cleanUpMediaLayout();
            this.mNativeVideoController.setPlayWhenReady(false);
            this.mNativeVideoController.release(this);
            NativeVideoController.remove(this.mId);
            this.mVideoVisibleTracking.destroy();
        }

        public void onStateChanged(boolean z, int i) {
            this.mLatestVideoControllerState = i;
            maybeChangeState();
        }

        public void onError(Exception exc) {
            MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_FAILED, MoPubCustomEventVideoNative.ADAPTER_NAME, Integer.valueOf(NativeErrorCode.UNSPECIFIED.getIntCode()), NativeErrorCode.UNSPECIFIED);
            this.mError = true;
            maybeChangeState();
        }

        public void updateProgress(int i) {
            this.mMediaLayout.updateProgress(i);
        }

        public void onAudioFocusChange(int i) {
            if (i == -1 || i == -2) {
                this.mMuted = true;
                maybeChangeState();
            } else if (i == -3) {
                this.mNativeVideoController.setAudioVolume(0.3f);
            } else if (i == 1) {
                this.mNativeVideoController.setAudioVolume(1.0f);
                maybeChangeState();
            }
        }

        private void cleanUpMediaLayout() {
            MediaLayout mediaLayout = this.mMediaLayout;
            if (mediaLayout != null) {
                mediaLayout.setMode(MediaLayout.Mode.IMAGE);
                this.mMediaLayout.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
                this.mMediaLayout.setPlayButtonClickListener((View.OnClickListener) null);
                this.mMediaLayout.setMuteControlClickListener((View.OnClickListener) null);
                this.mMediaLayout.setOnClickListener((View.OnClickListener) null);
                this.mVideoVisibleTracking.removeView(this.mMediaLayout);
                this.mMediaLayout = null;
            }
        }

        /* access modifiers changed from: private */
        public void prepareToLeaveView() {
            MoPubLog.log(MoPubLog.AdapterLogEvent.WILL_LEAVE_APPLICATION, MoPubCustomEventVideoNative.ADAPTER_NAME);
            this.mNeedsSeek = true;
            this.mNeedsPrepare = true;
            this.mNativeVideoController.setListener((NativeVideoController.Listener) null);
            this.mNativeVideoController.setOnAudioFocusChangeListener((AudioManager.OnAudioFocusChangeListener) null);
            this.mNativeVideoController.setProgressListener((NativeVideoController.NativeVideoProgressRunnable.ProgressListener) null);
            this.mNativeVideoController.clear();
            applyState(VideoState.PAUSED, true);
        }

        /* access modifiers changed from: private */
        public void maybeChangeState() {
            VideoState videoState = this.mVideoState;
            if (this.mError) {
                videoState = VideoState.FAILED_LOAD;
            } else if (this.mEnded) {
                videoState = VideoState.ENDED;
            } else {
                int i = this.mLatestVideoControllerState;
                if (i == 1) {
                    videoState = VideoState.LOADING;
                } else if (i == 2) {
                    videoState = VideoState.BUFFERING;
                } else if (i == 4) {
                    this.mEnded = true;
                    videoState = VideoState.ENDED;
                } else if (i == 3) {
                    if (this.mLatestVisibility) {
                        videoState = this.mMuted ? VideoState.PLAYING_MUTED : VideoState.PLAYING;
                    } else {
                        videoState = VideoState.PAUSED;
                    }
                }
            }
            applyState(videoState);
        }

        /* access modifiers changed from: package-private */
        public void applyState(VideoState videoState) {
            applyState(videoState, false);
        }

        /* access modifiers changed from: package-private */
        public void applyState(VideoState videoState, boolean z) {
            VideoState videoState2;
            Preconditions.checkNotNull(videoState);
            if (this.mVastVideoConfig != null && this.mNativeVideoController != null && this.mMediaLayout != null && (videoState2 = this.mVideoState) != videoState) {
                this.mVideoState = videoState;
                switch (AnonymousClass1.$SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[videoState.ordinal()]) {
                    case 1:
                        this.mVastVideoConfig.handleError(this.mContext, (VastErrorCode) null, 0);
                        this.mNativeVideoController.setAppAudioEnabled(false);
                        this.mMediaLayout.setMode(MediaLayout.Mode.IMAGE);
                        return;
                    case 2:
                    case 3:
                        this.mNativeVideoController.setPlayWhenReady(true);
                        this.mMediaLayout.setMode(MediaLayout.Mode.LOADING);
                        return;
                    case 4:
                        this.mNativeVideoController.setPlayWhenReady(true);
                        this.mMediaLayout.setMode(MediaLayout.Mode.BUFFERING);
                        return;
                    case 5:
                        if (z) {
                            this.mResumeCanBeTracked = false;
                        }
                        if (!z) {
                            this.mNativeVideoController.setAppAudioEnabled(false);
                            if (this.mPauseCanBeTracked) {
                                TrackingRequest.makeVastTrackingHttpRequest(this.mVastVideoConfig.getPauseTrackers(), (VastErrorCode) null, Integer.valueOf((int) this.mNativeVideoController.getCurrentPosition()), (String) null, this.mContext);
                                this.mPauseCanBeTracked = false;
                                this.mResumeCanBeTracked = true;
                            }
                        }
                        this.mNativeVideoController.setPlayWhenReady(false);
                        this.mMediaLayout.setMode(MediaLayout.Mode.PAUSED);
                        return;
                    case 6:
                        handleResumeTrackersAndSeek(videoState2);
                        this.mNativeVideoController.setPlayWhenReady(true);
                        this.mNativeVideoController.setAudioEnabled(true);
                        this.mNativeVideoController.setAppAudioEnabled(true);
                        this.mMediaLayout.setMode(MediaLayout.Mode.PLAYING);
                        this.mMediaLayout.setMuteState(MediaLayout.MuteState.UNMUTED);
                        return;
                    case 7:
                        handleResumeTrackersAndSeek(videoState2);
                        this.mNativeVideoController.setPlayWhenReady(true);
                        this.mNativeVideoController.setAudioEnabled(false);
                        this.mNativeVideoController.setAppAudioEnabled(false);
                        this.mMediaLayout.setMode(MediaLayout.Mode.PLAYING);
                        this.mMediaLayout.setMuteState(MediaLayout.MuteState.MUTED);
                        return;
                    case 8:
                        if (this.mNativeVideoController.hasFinalFrame()) {
                            this.mMediaLayout.setMainImageDrawable(this.mNativeVideoController.getFinalFrame());
                        }
                        this.mPauseCanBeTracked = false;
                        this.mResumeCanBeTracked = false;
                        this.mVastVideoConfig.handleComplete(this.mContext, 0);
                        this.mNativeVideoController.setAppAudioEnabled(false);
                        this.mMediaLayout.setMode(MediaLayout.Mode.FINISHED);
                        this.mMediaLayout.updateProgress(1000);
                        return;
                    default:
                        return;
                }
            }
        }

        private void handleResumeTrackersAndSeek(VideoState videoState) {
            if (!(!this.mResumeCanBeTracked || videoState == VideoState.PLAYING || videoState == VideoState.PLAYING_MUTED)) {
                TrackingRequest.makeVastTrackingHttpRequest(this.mVastVideoConfig.getResumeTrackers(), (VastErrorCode) null, Integer.valueOf((int) this.mNativeVideoController.getCurrentPosition()), (String) null, this.mContext);
                this.mResumeCanBeTracked = false;
            }
            this.mPauseCanBeTracked = true;
            if (this.mNeedsSeek) {
                this.mNeedsSeek = false;
                NativeVideoController nativeVideoController = this.mNativeVideoController;
                nativeVideoController.seekTo(nativeVideoController.getCurrentPosition());
            }
        }

        private boolean isImageKey(String str) {
            return str != null && str.toLowerCase(Locale.US).endsWith("image");
        }

        private List<String> getExtrasImageUrls() {
            ArrayList arrayList = new ArrayList(getExtras().size());
            for (Map.Entry next : getExtras().entrySet()) {
                if (isImageKey((String) next.getKey()) && (next.getValue() instanceof String)) {
                    arrayList.add((String) next.getValue());
                }
            }
            return arrayList;
        }

        private List<String> getAllImageUrls() {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(getMainImageUrl())) {
                arrayList.add(getMainImageUrl());
            }
            if (!TextUtils.isEmpty(getIconImageUrl())) {
                arrayList.add(getIconImageUrl());
            }
            if (!TextUtils.isEmpty(getPrivacyInformationIconImageUrl())) {
                arrayList.add(getPrivacyInformationIconImageUrl());
            }
            arrayList.addAll(getExtrasImageUrls());
            return arrayList;
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public boolean needsPrepare() {
            return this.mNeedsPrepare;
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public boolean hasEnded() {
            return this.mEnded;
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public boolean needsSeek() {
            return this.mNeedsSeek;
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public boolean isMuted() {
            return this.mMuted;
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public long getId() {
            return this.mId;
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public VideoState getVideoState() {
            return this.mVideoState;
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public void setLatestVisibility(boolean z) {
            this.mLatestVisibility = z;
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public void setMuted(boolean z) {
            this.mMuted = z;
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public MediaLayout getMediaLayout() {
            return this.mMediaLayout;
        }
    }

    /* renamed from: com.mopub.nativeads.MoPubCustomEventVideoNative$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter;
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState;

        /* JADX WARNING: Can't wrap try/catch for region: R(44:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|58) */
        /* JADX WARNING: Can't wrap try/catch for region: R(47:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|(2:25|26)|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|58) */
        /* JADX WARNING: Can't wrap try/catch for region: R(50:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|58) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x008f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x0099 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00a3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00ad */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00b7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00c3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00cf */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00db */
        static {
            /*
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[] r0 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.VideoState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState = r0
                r1 = 1
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState r2 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.VideoState.FAILED_LOAD     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState r3 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.VideoState.CREATED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState r4 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.VideoState.LOADING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState r5 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.VideoState.BUFFERING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState     // Catch:{ NoSuchFieldError -> 0x003e }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState r6 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.VideoState.PAUSED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState r7 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.VideoState.PLAYING     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                r6 = 7
                int[] r7 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState r8 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.VideoState.PLAYING_MUTED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7[r8] = r6     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                r7 = 8
                int[] r8 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState r9 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.VideoState.ENDED     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r8[r9] = r7     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[] r8 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.values()
                int r8 = r8.length
                int[] r8 = new int[r8]
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter = r8
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r9 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.IMPRESSION_TRACKER     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r8[r9] = r1     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r1 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x007b }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r8 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.TITLE     // Catch:{ NoSuchFieldError -> 0x007b }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r1[r8] = r0     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.TEXT     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x008f }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.IMAGE_URL     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x0099 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.ICON_URL     // Catch:{ NoSuchFieldError -> 0x0099 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0099 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0099 }
            L_0x0099:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x00a3 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.CLICK_DESTINATION     // Catch:{ NoSuchFieldError -> 0x00a3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a3 }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x00a3 }
            L_0x00a3:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x00ad }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.CLICK_TRACKER     // Catch:{ NoSuchFieldError -> 0x00ad }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ad }
                r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x00ad }
            L_0x00ad:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x00b7 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.CALL_TO_ACTION     // Catch:{ NoSuchFieldError -> 0x00b7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b7 }
                r0[r1] = r7     // Catch:{ NoSuchFieldError -> 0x00b7 }
            L_0x00b7:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x00c3 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.VAST_VIDEO     // Catch:{ NoSuchFieldError -> 0x00c3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c3 }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c3 }
            L_0x00c3:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x00cf }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.PRIVACY_INFORMATION_ICON_IMAGE_URL     // Catch:{ NoSuchFieldError -> 0x00cf }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cf }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cf }
            L_0x00cf:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x00db }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.PRIVACY_INFORMATION_ICON_CLICKTHROUGH_URL     // Catch:{ NoSuchFieldError -> 0x00db }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00db }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00db }
            L_0x00db:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter     // Catch:{ NoSuchFieldError -> 0x00e7 }
                com.mopub.nativeads.MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter r1 = com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd.Parameter.SPONSORED     // Catch:{ NoSuchFieldError -> 0x00e7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e7 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e7 }
            L_0x00e7:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.MoPubCustomEventVideoNative.AnonymousClass1.<clinit>():void");
        }
    }

    static class HeaderVisibilityStrategy implements NativeVideoController.VisibilityTrackingEvent.OnTrackedStrategy {
        private final WeakReference<MoPubVideoNativeAd> mMoPubVideoNativeAd;

        HeaderVisibilityStrategy(MoPubVideoNativeAd moPubVideoNativeAd) {
            this.mMoPubVideoNativeAd = new WeakReference<>(moPubVideoNativeAd);
        }

        public void execute() {
            MoPubVideoNativeAd moPubVideoNativeAd = (MoPubVideoNativeAd) this.mMoPubVideoNativeAd.get();
            if (moPubVideoNativeAd != null) {
                moPubVideoNativeAd.notifyAdImpressed();
            }
        }
    }

    static class PayloadVisibilityStrategy implements NativeVideoController.VisibilityTrackingEvent.OnTrackedStrategy {
        private final Context mContext;
        private final String mUrl;

        PayloadVisibilityStrategy(Context context, String str) {
            this.mContext = context.getApplicationContext();
            this.mUrl = str;
        }

        public void execute() {
            TrackingRequest.makeTrackingHttpRequest(this.mUrl, this.mContext);
        }
    }

    static class NativeVideoControllerFactory {
        NativeVideoControllerFactory() {
        }

        public NativeVideoController createForId(long j, Context context, List<NativeVideoController.VisibilityTrackingEvent> list, VastVideoConfig vastVideoConfig) {
            return NativeVideoController.createForId(j, context, list, vastVideoConfig);
        }
    }

    static class VideoResponseHeaders {
        private boolean mHeadersAreValid;
        private int mImpressionMinVisiblePercent;
        private int mImpressionVisibleMs;
        private Integer mImpressionVisiblePx;
        private int mMaxBufferMs;
        private int mPauseVisiblePercent;
        private int mPlayVisiblePercent;
        private JSONObject mVideoTrackers;

        VideoResponseHeaders(Map<String, String> map) {
            try {
                this.mPlayVisiblePercent = Integer.parseInt(map.get(DataKeys.PLAY_VISIBLE_PERCENT));
                this.mPauseVisiblePercent = Integer.parseInt(map.get(DataKeys.PAUSE_VISIBLE_PERCENT));
                this.mImpressionVisibleMs = Integer.parseInt(map.get(DataKeys.IMPRESSION_VISIBLE_MS));
                this.mMaxBufferMs = Integer.parseInt(map.get(DataKeys.MAX_BUFFER_MS));
                this.mHeadersAreValid = true;
            } catch (NumberFormatException unused) {
                this.mHeadersAreValid = false;
            }
            String str = map.get(DataKeys.IMPRESSION_MIN_VISIBLE_PX);
            if (!TextUtils.isEmpty(str)) {
                try {
                    this.mImpressionVisiblePx = Integer.valueOf(Integer.parseInt(str));
                } catch (NumberFormatException unused2) {
                    MoPubLog.log(MoPubLog.AdapterLogEvent.CUSTOM, "Unable to parse impression min visible px from server extras.");
                }
            }
            try {
                this.mImpressionMinVisiblePercent = Integer.parseInt(map.get(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT));
            } catch (NumberFormatException unused3) {
                MoPubLog.log(MoPubLog.AdapterLogEvent.CUSTOM, "Unable to parse impression min visible percent from server extras.");
                Integer num = this.mImpressionVisiblePx;
                if (num == null || num.intValue() < 0) {
                    this.mHeadersAreValid = false;
                }
            }
            String str2 = map.get(DataKeys.VIDEO_TRACKERS_KEY);
            if (!TextUtils.isEmpty(str2)) {
                try {
                    this.mVideoTrackers = new JSONObject(str2);
                } catch (JSONException e) {
                    MoPubLog.AdapterLogEvent adapterLogEvent = MoPubLog.AdapterLogEvent.CUSTOM_WITH_THROWABLE;
                    MoPubLog.log(adapterLogEvent, "Failed to parse video trackers to JSON: " + str2, e);
                    this.mVideoTrackers = null;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean hasValidHeaders() {
            return this.mHeadersAreValid;
        }

        /* access modifiers changed from: package-private */
        public int getPlayVisiblePercent() {
            return this.mPlayVisiblePercent;
        }

        /* access modifiers changed from: package-private */
        public int getPauseVisiblePercent() {
            return this.mPauseVisiblePercent;
        }

        /* access modifiers changed from: package-private */
        public int getImpressionMinVisiblePercent() {
            return this.mImpressionMinVisiblePercent;
        }

        /* access modifiers changed from: package-private */
        public int getImpressionVisibleMs() {
            return this.mImpressionVisibleMs;
        }

        /* access modifiers changed from: package-private */
        public int getMaxBufferMs() {
            return this.mMaxBufferMs;
        }

        /* access modifiers changed from: package-private */
        public Integer getImpressionVisiblePx() {
            return this.mImpressionVisiblePx;
        }

        /* access modifiers changed from: package-private */
        public JSONObject getVideoTrackers() {
            return this.mVideoTrackers;
        }
    }
}
