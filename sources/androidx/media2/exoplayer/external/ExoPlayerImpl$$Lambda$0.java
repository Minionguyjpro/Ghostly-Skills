package androidx.media2.exoplayer.external;

import androidx.media2.exoplayer.external.BasePlayer;
import androidx.media2.exoplayer.external.Player;

final /* synthetic */ class ExoPlayerImpl$$Lambda$0 implements BasePlayer.ListenerInvocation {
    private final boolean arg$1;
    private final int arg$2;

    ExoPlayerImpl$$Lambda$0(boolean z, int i) {
        this.arg$1 = z;
        this.arg$2 = i;
    }

    public void invokeListener(Player.EventListener eventListener) {
        eventListener.onPlayerStateChanged(this.arg$1, this.arg$2);
    }
}
