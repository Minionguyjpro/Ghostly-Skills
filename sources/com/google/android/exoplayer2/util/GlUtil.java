package com.google.android.exoplayer2.util;

import android.opengl.GLES20;
import android.opengl.GLU;

public final class GlUtil {
    public static void checkGlError() {
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError != 0) {
                Log.e("GlUtil", "glError " + GLU.gluErrorString(glGetError));
            } else {
                return;
            }
        }
    }
}
