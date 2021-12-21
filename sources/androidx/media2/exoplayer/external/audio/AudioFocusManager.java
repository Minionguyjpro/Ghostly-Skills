package androidx.media2.exoplayer.external.audio;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.Util;

public final class AudioFocusManager {
    private AudioAttributes audioAttributes;
    private AudioFocusRequest audioFocusRequest;
    /* access modifiers changed from: private */
    public int audioFocusState;
    private final AudioManager audioManager;
    private int focusGain;
    private final AudioFocusListener focusListener;
    /* access modifiers changed from: private */
    public final PlayerControl playerControl;
    private boolean rebuildAudioFocusRequest;
    /* access modifiers changed from: private */
    public float volumeMultiplier = 1.0f;

    public interface PlayerControl {
        void executePlayerCommand(int i);

        void setVolumeMultiplier(float f);
    }

    private int handleIdle(boolean z) {
        return z ? 1 : -1;
    }

    public AudioFocusManager(Context context, PlayerControl playerControl2) {
        this.audioManager = (AudioManager) context.getApplicationContext().getSystemService("audio");
        this.playerControl = playerControl2;
        this.focusListener = new AudioFocusListener();
        this.audioFocusState = 0;
    }

    public float getVolumeMultiplier() {
        return this.volumeMultiplier;
    }

    public int setAudioAttributes(AudioAttributes audioAttributes2, boolean z, int i) {
        if (!Util.areEqual(this.audioAttributes, audioAttributes2)) {
            this.audioAttributes = audioAttributes2;
            int convertAudioAttributesToFocusGain = convertAudioAttributesToFocusGain(audioAttributes2);
            this.focusGain = convertAudioAttributesToFocusGain;
            Assertions.checkArgument(convertAudioAttributesToFocusGain == 1 || convertAudioAttributesToFocusGain == 0, "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
            if (z && (i == 2 || i == 3)) {
                return requestAudioFocus();
            }
        }
        if (i == 1) {
            return handleIdle(z);
        }
        return handlePrepare(z);
    }

    public int handlePrepare(boolean z) {
        if (z) {
            return requestAudioFocus();
        }
        return -1;
    }

    public int handleSetPlayWhenReady(boolean z, int i) {
        if (z) {
            return i == 1 ? handleIdle(z) : requestAudioFocus();
        }
        abandonAudioFocus();
        return -1;
    }

    public void handleStop() {
        abandonAudioFocus(true);
    }

    private int requestAudioFocus() {
        int i;
        if (this.focusGain == 0) {
            if (this.audioFocusState != 0) {
                abandonAudioFocus(true);
            }
            return 1;
        }
        if (this.audioFocusState == 0) {
            if (Util.SDK_INT >= 26) {
                i = requestAudioFocusV26();
            } else {
                i = requestAudioFocusDefault();
            }
            this.audioFocusState = i == 1 ? 1 : 0;
        }
        int i2 = this.audioFocusState;
        if (i2 == 0) {
            return -1;
        }
        if (i2 == 2) {
            return 0;
        }
        return 1;
    }

    private void abandonAudioFocus() {
        abandonAudioFocus(false);
    }

    /* access modifiers changed from: private */
    public void abandonAudioFocus(boolean z) {
        if (this.focusGain != 0 || this.audioFocusState != 0) {
            if (this.focusGain != 1 || this.audioFocusState == -1 || z) {
                if (Util.SDK_INT >= 26) {
                    abandonAudioFocusV26();
                } else {
                    abandonAudioFocusDefault();
                }
                this.audioFocusState = 0;
            }
        }
    }

    private int requestAudioFocusDefault() {
        return this.audioManager.requestAudioFocus(this.focusListener, Util.getStreamTypeForAudioUsage(((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).usage), this.focusGain);
    }

    private int requestAudioFocusV26() {
        AudioFocusRequest.Builder builder;
        if (this.audioFocusRequest == null || this.rebuildAudioFocusRequest) {
            if (this.audioFocusRequest == null) {
                builder = new AudioFocusRequest.Builder(this.focusGain);
            } else {
                builder = new AudioFocusRequest.Builder(this.audioFocusRequest);
            }
            this.audioFocusRequest = builder.setAudioAttributes(((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).getAudioAttributesV21()).setWillPauseWhenDucked(willPauseWhenDucked()).setOnAudioFocusChangeListener(this.focusListener).build();
            this.rebuildAudioFocusRequest = false;
        }
        return this.audioManager.requestAudioFocus(this.audioFocusRequest);
    }

    private void abandonAudioFocusDefault() {
        this.audioManager.abandonAudioFocus(this.focusListener);
    }

    private void abandonAudioFocusV26() {
        AudioFocusRequest audioFocusRequest2 = this.audioFocusRequest;
        if (audioFocusRequest2 != null) {
            this.audioManager.abandonAudioFocusRequest(audioFocusRequest2);
        }
    }

    /* access modifiers changed from: private */
    public boolean willPauseWhenDucked() {
        AudioAttributes audioAttributes2 = this.audioAttributes;
        return audioAttributes2 != null && audioAttributes2.contentType == 1;
    }

    private static int convertAudioAttributesToFocusGain(AudioAttributes audioAttributes2) {
        if (audioAttributes2 == null) {
            return 0;
        }
        switch (audioAttributes2.usage) {
            case 0:
                Log.w("AudioFocusManager", "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
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
                if (audioAttributes2.contentType == 1) {
                    return 2;
                }
                break;
            case 16:
                if (Util.SDK_INT >= 19) {
                    return 4;
                }
                return 2;
            default:
                int i = audioAttributes2.usage;
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unidentified audio usage: ");
                sb.append(i);
                Log.w("AudioFocusManager", sb.toString());
                return 0;
        }
        return 3;
    }

    private class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
        private AudioFocusListener() {
        }

        public void onAudioFocusChange(int i) {
            if (i != -3) {
                if (i == -2) {
                    int unused = AudioFocusManager.this.audioFocusState = 2;
                } else if (i == -1) {
                    int unused2 = AudioFocusManager.this.audioFocusState = -1;
                } else if (i != 1) {
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unknown focus change type: ");
                    sb.append(i);
                    Log.w("AudioFocusManager", sb.toString());
                    return;
                } else {
                    int unused3 = AudioFocusManager.this.audioFocusState = 1;
                }
            } else if (AudioFocusManager.this.willPauseWhenDucked()) {
                int unused4 = AudioFocusManager.this.audioFocusState = 2;
            } else {
                int unused5 = AudioFocusManager.this.audioFocusState = 3;
            }
            int access$100 = AudioFocusManager.this.audioFocusState;
            if (access$100 == -1) {
                AudioFocusManager.this.playerControl.executePlayerCommand(-1);
                AudioFocusManager.this.abandonAudioFocus(true);
            } else if (access$100 != 0) {
                if (access$100 == 1) {
                    AudioFocusManager.this.playerControl.executePlayerCommand(1);
                } else if (access$100 == 2) {
                    AudioFocusManager.this.playerControl.executePlayerCommand(0);
                } else if (access$100 != 3) {
                    int access$1002 = AudioFocusManager.this.audioFocusState;
                    StringBuilder sb2 = new StringBuilder(38);
                    sb2.append("Unknown audio focus state: ");
                    sb2.append(access$1002);
                    throw new IllegalStateException(sb2.toString());
                }
            }
            float f = AudioFocusManager.this.audioFocusState == 3 ? 0.2f : 1.0f;
            if (AudioFocusManager.this.volumeMultiplier != f) {
                float unused6 = AudioFocusManager.this.volumeMultiplier = f;
                AudioFocusManager.this.playerControl.setVolumeMultiplier(f);
            }
        }
    }
}
