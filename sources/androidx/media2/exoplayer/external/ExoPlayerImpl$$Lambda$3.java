package androidx.media2.exoplayer.external;

import androidx.media2.exoplayer.external.BasePlayer;
import androidx.media2.exoplayer.external.Player;

final /* synthetic */ class ExoPlayerImpl$$Lambda$3 implements BasePlayer.ListenerInvocation {
    static final BasePlayer.ListenerInvocation $instance = new ExoPlayerImpl$$Lambda$3();

    private ExoPlayerImpl$$Lambda$3() {
    }

    public void invokeListener(Player.EventListener eventListener) {
        eventListener.onPositionDiscontinuity(1);
    }
}
