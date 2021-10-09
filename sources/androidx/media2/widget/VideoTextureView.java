package androidx.media2.widget;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.media2.widget.VideoViewInterface;

class VideoTextureView extends TextureView implements TextureView.SurfaceTextureListener, VideoViewInterface {
    private static final boolean DEBUG = Log.isLoggable("VideoTextureView", 3);
    private PlayerWrapper mPlayer;
    private Surface mSurface;
    VideoViewInterface.SurfaceListener mSurfaceListener;

    public int getViewType() {
        return 1;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    VideoTextureView(Context context) {
        super(context, (AttributeSet) null);
        setSurfaceTextureListener(this);
    }

    public boolean assignSurfaceToPlayerWrapper(PlayerWrapper playerWrapper) {
        this.mPlayer = playerWrapper;
        if (playerWrapper == null || !hasAvailableSurface()) {
            return false;
        }
        playerWrapper.setSurface(this.mSurface).addListener(new Runnable() {
            public void run() {
                if (VideoTextureView.this.mSurfaceListener != null) {
                    VideoTextureView.this.mSurfaceListener.onSurfaceTakeOverDone(VideoTextureView.this);
                }
            }
        }, ContextCompat.getMainExecutor(getContext()));
        return true;
    }

    public void setSurfaceListener(VideoViewInterface.SurfaceListener surfaceListener) {
        this.mSurfaceListener = surfaceListener;
    }

    public boolean hasAvailableSurface() {
        Surface surface = this.mSurface;
        return surface != null && surface.isValid();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mSurface = new Surface(surfaceTexture);
        VideoViewInterface.SurfaceListener surfaceListener = this.mSurfaceListener;
        if (surfaceListener != null) {
            surfaceListener.onSurfaceCreated(this, i, i2);
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        VideoViewInterface.SurfaceListener surfaceListener = this.mSurfaceListener;
        if (surfaceListener != null) {
            surfaceListener.onSurfaceChanged(this, i, i2);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        VideoViewInterface.SurfaceListener surfaceListener = this.mSurfaceListener;
        if (surfaceListener != null) {
            surfaceListener.onSurfaceDestroyed(this);
        }
        this.mSurface = null;
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        PlayerWrapper playerWrapper = this.mPlayer;
        int i4 = 0;
        int width = playerWrapper != null ? playerWrapper.getVideoSize().getWidth() : 0;
        PlayerWrapper playerWrapper2 = this.mPlayer;
        if (playerWrapper2 != null) {
            i4 = playerWrapper2.getVideoSize().getHeight();
        }
        if (width == 0 || i4 == 0) {
            setMeasuredDimension(getDefaultSize(width, i), getDefaultSize(i4, i2));
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            int i5 = width * size2;
            int i6 = size * i4;
            if (i5 < i6) {
                size = i5 / i4;
            } else if (i5 > i6) {
                size2 = i6 / width;
            }
        } else if (mode == 1073741824) {
            int i7 = (i4 * size) / width;
            size2 = (mode2 != Integer.MIN_VALUE || i7 <= size2) ? i7 : size2 | 16777216;
        } else if (mode2 == 1073741824) {
            int i8 = (width * size2) / i4;
            size = (mode != Integer.MIN_VALUE || i8 <= size) ? i8 : size | 16777216;
        } else {
            if (mode2 != Integer.MIN_VALUE || i4 <= size2) {
                i3 = width;
                size2 = i4;
            } else {
                i3 = (size2 * width) / i4;
            }
            if (mode != Integer.MIN_VALUE || i3 <= size) {
                size = i3;
            } else {
                size2 = (i4 * size) / width;
            }
        }
        setMeasuredDimension(size, size2);
    }
}
