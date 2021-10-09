package androidx.media2.widget;

import android.view.View;

interface VideoViewInterface {

    public interface SurfaceListener {
        void onSurfaceChanged(View view, int i, int i2);

        void onSurfaceCreated(View view, int i, int i2);

        void onSurfaceDestroyed(View view);

        void onSurfaceTakeOverDone(VideoViewInterface videoViewInterface);
    }

    boolean assignSurfaceToPlayerWrapper(PlayerWrapper playerWrapper);

    int getViewType();
}
