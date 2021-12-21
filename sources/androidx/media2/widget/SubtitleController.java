package androidx.media2.widget;

import android.content.Context;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.accessibility.CaptioningManager;
import androidx.media2.widget.SubtitleTrack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

class SubtitleController {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Anchor mAnchor;
    private final Handler.Callback mCallback = new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                SubtitleController.this.doShow();
                return true;
            } else if (i == 2) {
                SubtitleController.this.doHide();
                return true;
            } else if (i == 3) {
                SubtitleController.this.doSelectTrack((SubtitleTrack) message.obj);
                return true;
            } else if (i != 4) {
                return false;
            } else {
                SubtitleController.this.doSelectDefaultTrack();
                return true;
            }
        }
    };
    private CaptioningManager.CaptioningChangeListener mCaptioningChangeListener = new CaptioningManager.CaptioningChangeListener() {
        public void onEnabledChanged(boolean z) {
            SubtitleController.this.selectDefaultTrack();
        }

        public void onLocaleChanged(Locale locale) {
            SubtitleController.this.selectDefaultTrack();
        }
    };
    private CaptioningManager mCaptioningManager;
    private Handler mHandler;
    private Listener mListener;
    private ArrayList<Renderer> mRenderers;
    private final Object mRenderersLock = new Object();
    private SubtitleTrack mSelectedTrack;
    private boolean mShowing;
    private MediaTimeProvider mTimeProvider;
    private boolean mTrackIsExplicit = false;
    private ArrayList<SubtitleTrack> mTracks;
    private final Object mTracksLock = new Object();
    private boolean mVisibilityIsExplicit = false;

    interface Anchor {
        Looper getSubtitleLooper();

        void setSubtitleWidget(SubtitleTrack.RenderingWidget renderingWidget);
    }

    interface Listener {
        void onSubtitleTrackSelected(SubtitleTrack subtitleTrack);
    }

    public static abstract class Renderer {
        public abstract SubtitleTrack createTrack(MediaFormat mediaFormat);

        public abstract boolean supports(MediaFormat mediaFormat);
    }

    private void checkAnchorLooper() {
    }

    SubtitleController(Context context, MediaTimeProvider mediaTimeProvider, Listener listener) {
        this.mTimeProvider = mediaTimeProvider;
        this.mListener = listener;
        this.mRenderers = new ArrayList<>();
        this.mShowing = false;
        this.mTracks = new ArrayList<>();
        this.mCaptioningManager = (CaptioningManager) context.getSystemService("captioning");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        this.mCaptioningManager.removeCaptioningChangeListener(this.mCaptioningChangeListener);
        super.finalize();
    }

    private SubtitleTrack.RenderingWidget getRenderingWidget() {
        SubtitleTrack subtitleTrack = this.mSelectedTrack;
        if (subtitleTrack == null) {
            return null;
        }
        return subtitleTrack.getRenderingWidget();
    }

    public boolean selectTrack(SubtitleTrack subtitleTrack) {
        if (subtitleTrack != null && !this.mTracks.contains(subtitleTrack)) {
            return false;
        }
        processOnAnchor(this.mHandler.obtainMessage(3, subtitleTrack));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void doSelectTrack(SubtitleTrack subtitleTrack) {
        this.mTrackIsExplicit = true;
        SubtitleTrack subtitleTrack2 = this.mSelectedTrack;
        if (subtitleTrack2 != subtitleTrack) {
            if (subtitleTrack2 != null) {
                subtitleTrack2.hide();
                this.mSelectedTrack.setTimeProvider((MediaTimeProvider) null);
            }
            this.mSelectedTrack = subtitleTrack;
            Anchor anchor = this.mAnchor;
            if (anchor != null) {
                anchor.setSubtitleWidget(getRenderingWidget());
            }
            SubtitleTrack subtitleTrack3 = this.mSelectedTrack;
            if (subtitleTrack3 != null) {
                subtitleTrack3.setTimeProvider(this.mTimeProvider);
                this.mSelectedTrack.show();
            }
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onSubtitleTrackSelected(subtitleTrack);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media2.widget.SubtitleTrack getDefaultTrack() {
        /*
            r16 = this;
            r1 = r16
            android.view.accessibility.CaptioningManager r0 = r1.mCaptioningManager
            java.util.Locale r0 = r0.getLocale()
            if (r0 != 0) goto L_0x000f
            java.util.Locale r2 = java.util.Locale.getDefault()
            goto L_0x0010
        L_0x000f:
            r2 = r0
        L_0x0010:
            android.view.accessibility.CaptioningManager r3 = r1.mCaptioningManager
            boolean r3 = r3.isEnabled()
            r4 = 1
            r3 = r3 ^ r4
            java.lang.Object r5 = r1.mTracksLock
            monitor-enter(r5)
            java.util.ArrayList<androidx.media2.widget.SubtitleTrack> r6 = r1.mTracks     // Catch:{ all -> 0x00ae }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x00ae }
            r7 = 0
            r8 = -1
        L_0x0023:
            boolean r9 = r6.hasNext()     // Catch:{ all -> 0x00ae }
            if (r9 == 0) goto L_0x00ac
            java.lang.Object r9 = r6.next()     // Catch:{ all -> 0x00ae }
            androidx.media2.widget.SubtitleTrack r9 = (androidx.media2.widget.SubtitleTrack) r9     // Catch:{ all -> 0x00ae }
            android.media.MediaFormat r10 = r9.getFormat()     // Catch:{ all -> 0x00ae }
            java.lang.String r11 = "language"
            java.lang.String r11 = r10.getString(r11)     // Catch:{ all -> 0x00ae }
            java.lang.String r12 = "is-forced-subtitle"
            r13 = 0
            int r12 = androidx.media2.widget.SubtitleController.MediaFormatUtil.getInteger(r10, r12, r13)     // Catch:{ all -> 0x00ae }
            if (r12 == 0) goto L_0x0044
            r12 = 1
            goto L_0x0045
        L_0x0044:
            r12 = 0
        L_0x0045:
            java.lang.String r14 = "is-autoselect"
            int r14 = androidx.media2.widget.SubtitleController.MediaFormatUtil.getInteger(r10, r14, r4)     // Catch:{ all -> 0x00ae }
            if (r14 == 0) goto L_0x004f
            r14 = 1
            goto L_0x0050
        L_0x004f:
            r14 = 0
        L_0x0050:
            java.lang.String r15 = "is-default"
            int r10 = androidx.media2.widget.SubtitleController.MediaFormatUtil.getInteger(r10, r15, r13)     // Catch:{ all -> 0x00ae }
            if (r10 == 0) goto L_0x005a
            r10 = 1
            goto L_0x005b
        L_0x005a:
            r10 = 0
        L_0x005b:
            if (r2 == 0) goto L_0x0080
            java.lang.String r15 = r2.getLanguage()     // Catch:{ all -> 0x00ae }
            java.lang.String r4 = ""
            boolean r4 = r15.equals(r4)     // Catch:{ all -> 0x00ae }
            if (r4 != 0) goto L_0x0080
            java.lang.String r4 = r2.getISO3Language()     // Catch:{ all -> 0x00ae }
            boolean r4 = r4.equals(r11)     // Catch:{ all -> 0x00ae }
            if (r4 != 0) goto L_0x0080
            java.lang.String r4 = r2.getLanguage()     // Catch:{ all -> 0x00ae }
            boolean r4 = r4.equals(r11)     // Catch:{ all -> 0x00ae }
            if (r4 == 0) goto L_0x007e
            goto L_0x0080
        L_0x007e:
            r4 = 0
            goto L_0x0081
        L_0x0080:
            r4 = 1
        L_0x0081:
            if (r12 == 0) goto L_0x0085
            r11 = 0
            goto L_0x0087
        L_0x0085:
            r11 = 8
        L_0x0087:
            if (r0 != 0) goto L_0x008d
            if (r10 == 0) goto L_0x008d
            r15 = 4
            goto L_0x008e
        L_0x008d:
            r15 = 0
        L_0x008e:
            int r11 = r11 + r15
            if (r14 == 0) goto L_0x0092
            goto L_0x0093
        L_0x0092:
            r13 = 2
        L_0x0093:
            int r11 = r11 + r13
            int r11 = r11 + r4
            if (r3 == 0) goto L_0x009b
            if (r12 != 0) goto L_0x009b
        L_0x0099:
            r4 = 1
            goto L_0x0023
        L_0x009b:
            if (r0 != 0) goto L_0x009f
            if (r10 != 0) goto L_0x00a7
        L_0x009f:
            if (r4 == 0) goto L_0x0099
            if (r14 != 0) goto L_0x00a7
            if (r12 != 0) goto L_0x00a7
            if (r0 == 0) goto L_0x0099
        L_0x00a7:
            if (r11 <= r8) goto L_0x0099
            r7 = r9
            r8 = r11
            goto L_0x0099
        L_0x00ac:
            monitor-exit(r5)     // Catch:{ all -> 0x00ae }
            return r7
        L_0x00ae:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00ae }
            goto L_0x00b2
        L_0x00b1:
            throw r0
        L_0x00b2:
            goto L_0x00b1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.widget.SubtitleController.getDefaultTrack():androidx.media2.widget.SubtitleTrack");
    }

    static class MediaFormatUtil {
        static int getInteger(MediaFormat mediaFormat, String str, int i) {
            try {
                return mediaFormat.getInteger(str);
            } catch (ClassCastException | NullPointerException unused) {
                return i;
            }
        }
    }

    public void selectDefaultTrack() {
        processOnAnchor(this.mHandler.obtainMessage(4));
    }

    /* access modifiers changed from: package-private */
    public void doSelectDefaultTrack() {
        SubtitleTrack subtitleTrack;
        if (this.mTrackIsExplicit) {
            if (!this.mVisibilityIsExplicit) {
                if (this.mCaptioningManager.isEnabled() || !((subtitleTrack = this.mSelectedTrack) == null || MediaFormatUtil.getInteger(subtitleTrack.getFormat(), "is-forced-subtitle", 0) == 0)) {
                    show();
                } else {
                    SubtitleTrack subtitleTrack2 = this.mSelectedTrack;
                    if (subtitleTrack2 != null && subtitleTrack2.getTrackType() == 4) {
                        hide();
                    }
                }
                this.mVisibilityIsExplicit = false;
            } else {
                return;
            }
        }
        SubtitleTrack defaultTrack = getDefaultTrack();
        if (defaultTrack != null) {
            selectTrack(defaultTrack);
            this.mTrackIsExplicit = false;
            if (!this.mVisibilityIsExplicit) {
                show();
                this.mVisibilityIsExplicit = false;
            }
        }
    }

    public SubtitleTrack addTrack(MediaFormat mediaFormat) {
        SubtitleTrack createTrack;
        synchronized (this.mRenderersLock) {
            Iterator<Renderer> it = this.mRenderers.iterator();
            while (it.hasNext()) {
                Renderer next = it.next();
                if (next.supports(mediaFormat) && (createTrack = next.createTrack(mediaFormat)) != null) {
                    synchronized (this.mTracksLock) {
                        if (this.mTracks.size() == 0) {
                            this.mCaptioningManager.addCaptioningChangeListener(this.mCaptioningChangeListener);
                        }
                        this.mTracks.add(createTrack);
                    }
                    return createTrack;
                }
            }
            return null;
        }
    }

    public void show() {
        processOnAnchor(this.mHandler.obtainMessage(1));
    }

    /* access modifiers changed from: package-private */
    public void doShow() {
        this.mShowing = true;
        this.mVisibilityIsExplicit = true;
        SubtitleTrack subtitleTrack = this.mSelectedTrack;
        if (subtitleTrack != null) {
            subtitleTrack.show();
        }
    }

    public void hide() {
        processOnAnchor(this.mHandler.obtainMessage(2));
    }

    /* access modifiers changed from: package-private */
    public void doHide() {
        this.mVisibilityIsExplicit = true;
        SubtitleTrack subtitleTrack = this.mSelectedTrack;
        if (subtitleTrack != null) {
            subtitleTrack.hide();
        }
        this.mShowing = false;
    }

    public void registerRenderer(Renderer renderer) {
        synchronized (this.mRenderersLock) {
            if (!this.mRenderers.contains(renderer)) {
                this.mRenderers.add(renderer);
            }
        }
    }

    public void setAnchor(Anchor anchor) {
        Anchor anchor2 = this.mAnchor;
        if (anchor2 != anchor) {
            if (anchor2 != null) {
                checkAnchorLooper();
                this.mAnchor.setSubtitleWidget((SubtitleTrack.RenderingWidget) null);
            }
            this.mAnchor = anchor;
            this.mHandler = null;
            if (anchor != null) {
                this.mHandler = new Handler(this.mAnchor.getSubtitleLooper(), this.mCallback);
                checkAnchorLooper();
                this.mAnchor.setSubtitleWidget(getRenderingWidget());
            }
        }
    }

    private void processOnAnchor(Message message) {
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            this.mHandler.dispatchMessage(message);
        } else {
            this.mHandler.sendMessage(message);
        }
    }
}
