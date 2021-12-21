package androidx.media2.exoplayer.external;

import android.content.Context;
import android.os.Looper;
import androidx.media2.exoplayer.external.analytics.AnalyticsCollector;
import androidx.media2.exoplayer.external.drm.DrmSessionManager;
import androidx.media2.exoplayer.external.drm.FrameworkMediaCrypto;
import androidx.media2.exoplayer.external.trackselection.TrackSelector;
import androidx.media2.exoplayer.external.upstream.BandwidthMeter;

public final class ExoPlayerFactory {
    public static SimpleExoPlayer newSimpleInstance(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, BandwidthMeter bandwidthMeter, AnalyticsCollector.Factory factory, Looper looper) {
        return new SimpleExoPlayer(context, renderersFactory, trackSelector, loadControl, drmSessionManager, bandwidthMeter, factory, looper);
    }
}
