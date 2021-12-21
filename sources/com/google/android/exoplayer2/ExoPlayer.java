package com.google.android.exoplayer2;

import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.source.MediaSource;

public interface ExoPlayer extends Player {
    PlayerMessage createMessage(PlayerMessage.Target target);

    void prepare(MediaSource mediaSource);
}
