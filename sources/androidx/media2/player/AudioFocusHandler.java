package androidx.media2.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;
import androidx.media.AudioAttributesCompat;

public class AudioFocusHandler {
    private final AudioFocusHandlerImpl mImpl;

    interface AudioFocusHandlerImpl {
        void close();

        void onPause();

        boolean onPlay();

        void onReset();
    }

    AudioFocusHandler(Context context, MediaPlayer mediaPlayer) {
        this.mImpl = new AudioFocusHandlerImplBase(context, mediaPlayer);
    }

    public boolean onPlay() {
        return this.mImpl.onPlay();
    }

    public void onPause() {
        this.mImpl.onPause();
    }

    public void onReset() {
        this.mImpl.onReset();
    }

    public void close() {
        this.mImpl.close();
    }

    private static class AudioFocusHandlerImplBase implements AudioFocusHandlerImpl {
        AudioAttributesCompat mAudioAttributes;
        private final AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioFocusListener();
        private final AudioManager mAudioManager;
        private final BroadcastReceiver mBecomingNoisyReceiver = new BecomingNoisyReceiver();
        boolean mBecomingNoisyReceiverRegistered;
        private final Context mContext;
        private int mCurrentFocusGainType;
        private final IntentFilter mIntentFilter = new IntentFilter("android.media.AUDIO_BECOMING_NOISY");
        final Object mLock = new Object();
        final MediaPlayer mPlayer;
        boolean mResumeWhenAudioFocusGain;

        AudioFocusHandlerImplBase(Context context, MediaPlayer mediaPlayer) {
            this.mContext = context;
            this.mPlayer = mediaPlayer;
            this.mAudioManager = (AudioManager) context.getSystemService("audio");
        }

        public boolean onPlay() {
            boolean z;
            AudioAttributesCompat audioAttributes = this.mPlayer.getAudioAttributes();
            synchronized (this.mLock) {
                this.mAudioAttributes = audioAttributes;
                if (audioAttributes == null) {
                    abandonAudioFocusLocked();
                    unregisterBecomingNoisyReceiverLocked();
                    z = true;
                } else {
                    z = requestAudioFocusLocked();
                    if (z) {
                        registerBecomingNoisyReceiverLocked();
                    }
                }
            }
            return z;
        }

        public void onPause() {
            synchronized (this.mLock) {
                this.mResumeWhenAudioFocusGain = false;
                unregisterBecomingNoisyReceiverLocked();
            }
        }

        public void onReset() {
            synchronized (this.mLock) {
                abandonAudioFocusLocked();
                unregisterBecomingNoisyReceiverLocked();
            }
        }

        public void close() {
            synchronized (this.mLock) {
                unregisterBecomingNoisyReceiverLocked();
                abandonAudioFocusLocked();
            }
        }

        private boolean requestAudioFocusLocked() {
            int convertAudioAttributesToFocusGain = convertAudioAttributesToFocusGain(this.mAudioAttributes);
            if (convertAudioAttributesToFocusGain == 0) {
                if (this.mAudioAttributes == null) {
                    Log.e("AudioFocusHandler", "requestAudioFocusLocked() shouldn't be called when AudioAttributes is null");
                }
                return true;
            }
            int requestAudioFocus = this.mAudioManager.requestAudioFocus(this.mAudioFocusListener, this.mAudioAttributes.getVolumeControlStream(), convertAudioAttributesToFocusGain);
            if (requestAudioFocus == 1) {
                this.mCurrentFocusGainType = convertAudioAttributesToFocusGain;
            } else {
                Log.w("AudioFocusHandler", "requestAudioFocus(" + convertAudioAttributesToFocusGain + ") failed (return=" + requestAudioFocus + ") playback wouldn't start.");
                this.mCurrentFocusGainType = 0;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("requestAudioFocus(");
            sb.append(convertAudioAttributesToFocusGain);
            sb.append("), result=");
            sb.append(requestAudioFocus == 1);
            Log.d("AudioFocusHandler", sb.toString());
            this.mResumeWhenAudioFocusGain = false;
            if (this.mCurrentFocusGainType != 0) {
                return true;
            }
            return false;
        }

        private void abandonAudioFocusLocked() {
            if (this.mCurrentFocusGainType != 0) {
                Log.d("AudioFocusHandler", "abandoningAudioFocusLocked, currently=" + this.mCurrentFocusGainType);
                this.mAudioManager.abandonAudioFocus(this.mAudioFocusListener);
                this.mCurrentFocusGainType = 0;
                this.mResumeWhenAudioFocusGain = false;
            }
        }

        private void registerBecomingNoisyReceiverLocked() {
            if (!this.mBecomingNoisyReceiverRegistered) {
                Log.d("AudioFocusHandler", "registering becoming noisy receiver");
                this.mContext.registerReceiver(this.mBecomingNoisyReceiver, this.mIntentFilter);
                this.mBecomingNoisyReceiverRegistered = true;
            }
        }

        private void unregisterBecomingNoisyReceiverLocked() {
            if (this.mBecomingNoisyReceiverRegistered) {
                Log.d("AudioFocusHandler", "unregistering becoming noisy receiver");
                this.mContext.unregisterReceiver(this.mBecomingNoisyReceiver);
                this.mBecomingNoisyReceiverRegistered = false;
            }
        }

        private static int convertAudioAttributesToFocusGain(AudioAttributesCompat audioAttributesCompat) {
            if (audioAttributesCompat == null) {
                return 0;
            }
            switch (audioAttributesCompat.getUsage()) {
                case 0:
                    Log.w("AudioFocusHandler", "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
                    return 1;
                case 1:
                case 14:
                    return 1;
                case 2:
                case 4:
                    return 2;
                case 3:
                    return 0;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 12:
                case 13:
                    break;
                case 11:
                    if (audioAttributesCompat.getContentType() == 1) {
                        return 2;
                    }
                    break;
                case 16:
                    return 4;
                default:
                    Log.w("AudioFocusHandler", "Unidentified AudioAttribute " + audioAttributesCompat);
                    return 0;
            }
            return 3;
        }

        private class BecomingNoisyReceiver extends BroadcastReceiver {
            BecomingNoisyReceiver() {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
                if (r5 == 1) goto L_0x0073;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x005b, code lost:
                if (r5 == 14) goto L_0x005e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x005e, code lost:
                r3.this$0.mPlayer.setPlayerVolume(r3.this$0.mPlayer.getPlayerVolume() * 0.2f);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:0x0073, code lost:
                r3.this$0.mPlayer.pause();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onReceive(android.content.Context r4, android.content.Intent r5) {
                /*
                    r3 = this;
                    java.lang.String r4 = "android.media.AUDIO_BECOMING_NOISY"
                    java.lang.String r0 = r5.getAction()
                    boolean r4 = r4.equals(r0)
                    if (r4 != 0) goto L_0x000d
                    return
                L_0x000d:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r4 = r4.mLock
                    monitor-enter(r4)
                    java.lang.String r0 = "AudioFocusHandler"
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x007d }
                    r1.<init>()     // Catch:{ all -> 0x007d }
                    java.lang.String r2 = "Received noisy intent, intent="
                    r1.append(r2)     // Catch:{ all -> 0x007d }
                    r1.append(r5)     // Catch:{ all -> 0x007d }
                    java.lang.String r5 = ", registered="
                    r1.append(r5)     // Catch:{ all -> 0x007d }
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r5 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x007d }
                    boolean r5 = r5.mBecomingNoisyReceiverRegistered     // Catch:{ all -> 0x007d }
                    r1.append(r5)     // Catch:{ all -> 0x007d }
                    java.lang.String r5 = ", attr="
                    r1.append(r5)     // Catch:{ all -> 0x007d }
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r5 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x007d }
                    androidx.media.AudioAttributesCompat r5 = r5.mAudioAttributes     // Catch:{ all -> 0x007d }
                    r1.append(r5)     // Catch:{ all -> 0x007d }
                    java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x007d }
                    android.util.Log.d(r0, r5)     // Catch:{ all -> 0x007d }
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r5 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x007d }
                    boolean r5 = r5.mBecomingNoisyReceiverRegistered     // Catch:{ all -> 0x007d }
                    if (r5 == 0) goto L_0x007b
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r5 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x007d }
                    androidx.media.AudioAttributesCompat r5 = r5.mAudioAttributes     // Catch:{ all -> 0x007d }
                    if (r5 != 0) goto L_0x004d
                    goto L_0x007b
                L_0x004d:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r5 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x007d }
                    androidx.media.AudioAttributesCompat r5 = r5.mAudioAttributes     // Catch:{ all -> 0x007d }
                    int r5 = r5.getUsage()     // Catch:{ all -> 0x007d }
                    monitor-exit(r4)     // Catch:{ all -> 0x007d }
                    r4 = 1
                    if (r5 == r4) goto L_0x0073
                    r4 = 14
                    if (r5 == r4) goto L_0x005e
                    goto L_0x007a
                L_0x005e:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r4 = r4.mPlayer
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r5 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r5 = r5.mPlayer
                    float r5 = r5.getPlayerVolume()
                    r0 = 1045220557(0x3e4ccccd, float:0.2)
                    float r5 = r5 * r0
                    r4.setPlayerVolume(r5)
                    goto L_0x007a
                L_0x0073:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r4 = r4.mPlayer
                    r4.pause()
                L_0x007a:
                    return
                L_0x007b:
                    monitor-exit(r4)     // Catch:{ all -> 0x007d }
                    return
                L_0x007d:
                    r5 = move-exception
                    monitor-exit(r4)     // Catch:{ all -> 0x007d }
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.BecomingNoisyReceiver.onReceive(android.content.Context, android.content.Intent):void");
            }
        }

        private class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
            private float mPlayerDuckingVolume;
            private float mPlayerVolumeBeforeDucking;

            AudioFocusListener() {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:65:0x009b, code lost:
                if (r1 == false) goto L_0x00a5;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:66:0x009d, code lost:
                r3.this$0.mPlayer.pause();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:67:0x00a5, code lost:
                r4 = r3.this$0.mPlayer.getPlayerVolume();
                r0 = 0.2f * r4;
                r1 = r3.this$0.mLock;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:68:0x00b6, code lost:
                monitor-enter(r1);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
                r3.mPlayerVolumeBeforeDucking = r4;
                r3.mPlayerDuckingVolume = r0;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:71:0x00bb, code lost:
                monitor-exit(r1);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:72:0x00bc, code lost:
                r3.this$0.mPlayer.setPlayerVolume(r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onAudioFocusChange(int r4) {
                /*
                    r3 = this;
                    r0 = -3
                    r1 = 0
                    r2 = 1
                    if (r4 == r0) goto L_0x0082
                    r0 = -2
                    if (r4 == r0) goto L_0x006d
                    r0 = -1
                    if (r4 == r0) goto L_0x0058
                    if (r4 == r2) goto L_0x000f
                    goto L_0x00c3
                L_0x000f:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r4 = r4.mPlayer
                    int r4 = r4.getPlayerState()
                    if (r4 != r2) goto L_0x0034
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r4 = r4.mLock
                    monitor-enter(r4)
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x0031 }
                    boolean r0 = r0.mResumeWhenAudioFocusGain     // Catch:{ all -> 0x0031 }
                    if (r0 != 0) goto L_0x0027
                    monitor-exit(r4)     // Catch:{ all -> 0x0031 }
                    goto L_0x00c3
                L_0x0027:
                    monitor-exit(r4)     // Catch:{ all -> 0x0031 }
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r4 = r4.mPlayer
                    r4.play()
                    goto L_0x00c3
                L_0x0031:
                    r0 = move-exception
                    monitor-exit(r4)     // Catch:{ all -> 0x0031 }
                    throw r0
                L_0x0034:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r4 = r4.mPlayer
                    float r4 = r4.getPlayerVolume()
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r0 = r0.mLock
                    monitor-enter(r0)
                    float r1 = r3.mPlayerDuckingVolume     // Catch:{ all -> 0x0055 }
                    int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
                    if (r4 == 0) goto L_0x004a
                    monitor-exit(r0)     // Catch:{ all -> 0x0055 }
                    goto L_0x00c3
                L_0x004a:
                    float r4 = r3.mPlayerVolumeBeforeDucking     // Catch:{ all -> 0x0055 }
                    monitor-exit(r0)     // Catch:{ all -> 0x0055 }
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r0 = r0.mPlayer
                    r0.setPlayerVolume(r4)
                    goto L_0x00c3
                L_0x0055:
                    r4 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x0055 }
                    throw r4
                L_0x0058:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r4 = r4.mPlayer
                    r4.pause()
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r4 = r4.mLock
                    monitor-enter(r4)
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x006a }
                    r0.mResumeWhenAudioFocusGain = r1     // Catch:{ all -> 0x006a }
                    monitor-exit(r4)     // Catch:{ all -> 0x006a }
                    goto L_0x00c3
                L_0x006a:
                    r0 = move-exception
                    monitor-exit(r4)     // Catch:{ all -> 0x006a }
                    throw r0
                L_0x006d:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r4 = r4.mPlayer
                    r4.pause()
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r4 = r4.mLock
                    monitor-enter(r4)
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x007f }
                    r0.mResumeWhenAudioFocusGain = r2     // Catch:{ all -> 0x007f }
                    monitor-exit(r4)     // Catch:{ all -> 0x007f }
                    goto L_0x00c3
                L_0x007f:
                    r0 = move-exception
                    monitor-exit(r4)     // Catch:{ all -> 0x007f }
                    throw r0
                L_0x0082:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r4 = r4.mLock
                    monitor-enter(r4)
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x00c7 }
                    androidx.media.AudioAttributesCompat r0 = r0.mAudioAttributes     // Catch:{ all -> 0x00c7 }
                    if (r0 != 0) goto L_0x008f
                    monitor-exit(r4)     // Catch:{ all -> 0x00c7 }
                    goto L_0x00c3
                L_0x008f:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x00c7 }
                    androidx.media.AudioAttributesCompat r0 = r0.mAudioAttributes     // Catch:{ all -> 0x00c7 }
                    int r0 = r0.getContentType()     // Catch:{ all -> 0x00c7 }
                    if (r0 != r2) goto L_0x009a
                    r1 = 1
                L_0x009a:
                    monitor-exit(r4)     // Catch:{ all -> 0x00c7 }
                    if (r1 == 0) goto L_0x00a5
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r4 = r4.mPlayer
                    r4.pause()
                    goto L_0x00c3
                L_0x00a5:
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r4 = r4.mPlayer
                    float r4 = r4.getPlayerVolume()
                    r0 = 1045220557(0x3e4ccccd, float:0.2)
                    float r0 = r0 * r4
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r1 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r1 = r1.mLock
                    monitor-enter(r1)
                    r3.mPlayerVolumeBeforeDucking = r4     // Catch:{ all -> 0x00c4 }
                    r3.mPlayerDuckingVolume = r0     // Catch:{ all -> 0x00c4 }
                    monitor-exit(r1)     // Catch:{ all -> 0x00c4 }
                    androidx.media2.player.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.player.MediaPlayer r4 = r4.mPlayer
                    r4.setPlayerVolume(r0)
                L_0x00c3:
                    return
                L_0x00c4:
                    r4 = move-exception
                    monitor-exit(r1)     // Catch:{ all -> 0x00c4 }
                    throw r4
                L_0x00c7:
                    r0 = move-exception
                    monitor-exit(r4)     // Catch:{ all -> 0x00c7 }
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImplBase.AudioFocusListener.onAudioFocusChange(int):void");
            }
        }
    }
}
