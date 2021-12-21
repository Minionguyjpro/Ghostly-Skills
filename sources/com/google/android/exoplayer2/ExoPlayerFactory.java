package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Looper;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;

@Deprecated
public final class ExoPlayerFactory {
    @Deprecated
    public static ExoPlayer newInstance(Context context, Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl) {
        return newInstance(context, rendererArr, trackSelector, loadControl, Util.getLooper());
    }

    @Deprecated
    public static ExoPlayer newInstance(Context context, Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl, Looper looper) {
        return newInstance(context, rendererArr, trackSelector, loadControl, DefaultBandwidthMeter.getSingletonInstance(context), looper);
    }

    @Deprecated
    public static ExoPlayer newInstance(Context context, Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl, BandwidthMeter bandwidthMeter, Looper looper) {
        return new ExoPlayerImpl(rendererArr, trackSelector, loadControl, bandwidthMeter, Clock.DEFAULT, looper);
    }
}
