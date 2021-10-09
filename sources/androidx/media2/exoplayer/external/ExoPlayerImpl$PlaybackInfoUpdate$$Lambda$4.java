package androidx.media2.exoplayer.external;

import androidx.media2.exoplayer.external.BasePlayer;
import androidx.media2.exoplayer.external.ExoPlayerImpl;
import androidx.media2.exoplayer.external.Player;

final /* synthetic */ class ExoPlayerImpl$PlaybackInfoUpdate$$Lambda$4 implements BasePlayer.ListenerInvocation {
    private final ExoPlayerImpl.PlaybackInfoUpdate arg$1;

    ExoPlayerImpl$PlaybackInfoUpdate$$Lambda$4(ExoPlayerImpl.PlaybackInfoUpdate playbackInfoUpdate) {
        this.arg$1 = playbackInfoUpdate;
    }

    public void invokeListener(Player.EventListener eventListener) {
        this.arg$1.lambda$run$4$ExoPlayerImpl$PlaybackInfoUpdate(eventListener);
    }
}
