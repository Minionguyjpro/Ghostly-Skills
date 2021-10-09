package androidx.media2.exoplayer.external.util;

import android.opengl.GLES20;
import android.opengl.GLU;

public final class GlUtil {
    public static void checkGlError() {
        int i = 0;
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError != 0) {
                String valueOf = String.valueOf(GLU.gluErrorString(i));
                Log.e("GlUtil", valueOf.length() != 0 ? "glError ".concat(valueOf) : new String("glError "));
                i = glGetError;
            } else {
                return;
            }
        }
    }
}
