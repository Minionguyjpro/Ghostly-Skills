package androidx.media2.player;

import android.os.Build;

public final class PlaybackParams {
    private Integer mAudioFallbackMode;
    private Float mPitch;
    private android.media.PlaybackParams mPlaybackParams;
    private Float mSpeed;

    PlaybackParams(Integer num, Float f, Float f2) {
        this.mAudioFallbackMode = num;
        this.mPitch = f;
        this.mSpeed = f2;
    }

    PlaybackParams(android.media.PlaybackParams playbackParams) {
        this.mPlaybackParams = playbackParams;
    }

    public Integer getAudioFallbackMode() {
        if (Build.VERSION.SDK_INT < 23) {
            return this.mAudioFallbackMode;
        }
        try {
            return Integer.valueOf(this.mPlaybackParams.getAudioFallbackMode());
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public Float getPitch() {
        if (Build.VERSION.SDK_INT < 23) {
            return this.mPitch;
        }
        try {
            return Float.valueOf(this.mPlaybackParams.getPitch());
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public Float getSpeed() {
        if (Build.VERSION.SDK_INT < 23) {
            return this.mSpeed;
        }
        try {
            return Float.valueOf(this.mPlaybackParams.getSpeed());
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public android.media.PlaybackParams getPlaybackParams() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.mPlaybackParams;
        }
        return null;
    }

    public static final class Builder {
        private Integer mAudioFallbackMode;
        private Float mPitch;
        private android.media.PlaybackParams mPlaybackParams;
        private Float mSpeed;

        public Builder() {
            if (Build.VERSION.SDK_INT >= 23) {
                this.mPlaybackParams = new android.media.PlaybackParams();
            }
        }

        public Builder(PlaybackParams playbackParams) {
            if (playbackParams == null) {
                throw new NullPointerException("playbakcParams shouldn't be null");
            } else if (Build.VERSION.SDK_INT >= 23) {
                this.mPlaybackParams = playbackParams.getPlaybackParams();
            } else {
                this.mAudioFallbackMode = playbackParams.getAudioFallbackMode();
                this.mPitch = playbackParams.getPitch();
                this.mSpeed = playbackParams.getSpeed();
            }
        }

        public Builder setAudioFallbackMode(int i) {
            if (Build.VERSION.SDK_INT >= 23) {
                this.mPlaybackParams.setAudioFallbackMode(i);
            } else {
                this.mAudioFallbackMode = Integer.valueOf(i);
            }
            return this;
        }

        public Builder setPitch(float f) {
            if (f == 0.0f) {
                throw new IllegalArgumentException("0 pitch is not allowed");
            } else if (f >= 0.0f) {
                if (Build.VERSION.SDK_INT >= 23) {
                    this.mPlaybackParams.setPitch(f);
                } else {
                    this.mPitch = Float.valueOf(f);
                }
                return this;
            } else {
                throw new IllegalArgumentException("pitch must not be negative");
            }
        }

        public Builder setSpeed(float f) {
            if (f == 0.0f) {
                throw new IllegalArgumentException("0 speed is not allowed");
            } else if (f >= 0.0f) {
                if (Build.VERSION.SDK_INT >= 23) {
                    this.mPlaybackParams.setSpeed(f);
                } else {
                    this.mSpeed = Float.valueOf(f);
                }
                return this;
            } else {
                throw new IllegalArgumentException("negative speed is not supported");
            }
        }

        public PlaybackParams build() {
            if (Build.VERSION.SDK_INT >= 23) {
                return new PlaybackParams(this.mPlaybackParams);
            }
            return new PlaybackParams(this.mAudioFallbackMode, this.mPitch, this.mSpeed);
        }
    }
}
