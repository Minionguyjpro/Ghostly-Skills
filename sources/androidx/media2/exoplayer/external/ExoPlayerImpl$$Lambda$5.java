package androidx.media2.exoplayer.external;

import androidx.media2.exoplayer.external.BasePlayer;
import androidx.media2.exoplayer.external.Player;

final /* synthetic */ class ExoPlayerImpl$$Lambda$5 implements BasePlayer.ListenerInvocation {
    private final ExoPlaybackException arg$1;

    ExoPlayerImpl$$Lambda$5(ExoPlaybackException exoPlaybackException) {
        this.arg$1 = exoPlaybackException;
    }

    public void invokeListener(Player.EventListener eventListener) {
        eventListener.onPlayerError(this.arg$1);
    }
}
