package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

@zzadh
public final class zzapu extends Thread implements SurfaceTexture.OnFrameAvailableListener, zzapt {
    private static final float[] zzcyv = {-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    private final float[] zzcys = new float[9];
    private final zzapr zzcyw;
    private final float[] zzcyx = new float[9];
    private final float[] zzcyy = new float[9];
    private final float[] zzcyz = new float[9];
    private final float[] zzcza = new float[9];
    private final float[] zzczb = new float[9];
    private final float[] zzczc = new float[9];
    private float zzczd = Float.NaN;
    private float zzcze;
    private float zzczf;
    private SurfaceTexture zzczg;
    private SurfaceTexture zzczh;
    private int zzczi;
    private int zzczj;
    private int zzczk;
    private FloatBuffer zzczl;
    private final CountDownLatch zzczm;
    private final Object zzczn;
    private EGL10 zzczo;
    private EGLDisplay zzczp;
    private EGLContext zzczq;
    private EGLSurface zzczr;
    private volatile boolean zzczs;
    private volatile boolean zzczt;
    private int zzuq;
    private int zzur;

    public zzapu(Context context) {
        super("SphericalVideoProcessor");
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(zzcyv.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.zzczl = asFloatBuffer;
        asFloatBuffer.put(zzcyv).position(0);
        zzapr zzapr = new zzapr(context);
        this.zzcyw = zzapr;
        zzapr.zza((zzapt) this);
        this.zzczm = new CountDownLatch(1);
        this.zzczn = new Object();
    }

    private static void zza(float[] fArr, float f) {
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        double d = (double) f;
        fArr[4] = (float) Math.cos(d);
        fArr[5] = (float) (-Math.sin(d));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin(d);
        fArr[8] = (float) Math.cos(d);
    }

    private static void zza(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = (fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[3]) + (fArr2[2] * fArr3[6]);
        fArr[1] = (fArr2[0] * fArr3[1]) + (fArr2[1] * fArr3[4]) + (fArr2[2] * fArr3[7]);
        fArr[2] = (fArr2[0] * fArr3[2]) + (fArr2[1] * fArr3[5]) + (fArr2[2] * fArr3[8]);
        fArr[3] = (fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[3]) + (fArr2[5] * fArr3[6]);
        fArr[4] = (fArr2[3] * fArr3[1]) + (fArr2[4] * fArr3[4]) + (fArr2[5] * fArr3[7]);
        fArr[5] = (fArr2[3] * fArr3[2]) + (fArr2[4] * fArr3[5]) + (fArr2[5] * fArr3[8]);
        fArr[6] = (fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[3]) + (fArr2[8] * fArr3[6]);
        fArr[7] = (fArr2[6] * fArr3[1]) + (fArr2[7] * fArr3[4]) + (fArr2[8] * fArr3[7]);
        fArr[8] = (fArr2[6] * fArr3[2]) + (fArr2[7] * fArr3[5]) + (fArr2[8] * fArr3[8]);
    }

    private static void zzb(float[] fArr, float f) {
        double d = (double) f;
        fArr[0] = (float) Math.cos(d);
        fArr[1] = (float) (-Math.sin(d));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin(d);
        fArr[4] = (float) Math.cos(d);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    private static int zzd(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        zzdo("createShader");
        if (glCreateShader == 0) {
            return glCreateShader;
        }
        GLES20.glShaderSource(glCreateShader, str);
        zzdo("shaderSource");
        GLES20.glCompileShader(glCreateShader);
        zzdo("compileShader");
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        zzdo("getShaderiv");
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Could not compile shader ");
        sb.append(i);
        sb.append(":");
        Log.e("SphericalVideoRenderer", sb.toString());
        Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        zzdo("deleteShader");
        return 0;
    }

    private static void zzdo(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21);
            sb.append(str);
            sb.append(": glError ");
            sb.append(glGetError);
            Log.e("SphericalVideoRenderer", sb.toString());
        }
    }

    private final boolean zztk() {
        EGLSurface eGLSurface = this.zzczr;
        boolean z = false;
        if (!(eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE)) {
            z = this.zzczo.eglDestroySurface(this.zzczp, this.zzczr) | this.zzczo.eglMakeCurrent(this.zzczp, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | false;
            this.zzczr = null;
        }
        EGLContext eGLContext = this.zzczq;
        if (eGLContext != null) {
            z |= this.zzczo.eglDestroyContext(this.zzczp, eGLContext);
            this.zzczq = null;
        }
        EGLDisplay eGLDisplay = this.zzczp;
        if (eGLDisplay == null) {
            return z;
        }
        boolean eglTerminate = z | this.zzczo.eglTerminate(eGLDisplay);
        this.zzczp = null;
        return eglTerminate;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzczk++;
        synchronized (this.zzczn) {
            this.zzczn.notifyAll();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:0x03a6  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x03ab  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01d3 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r14 = this;
            android.graphics.SurfaceTexture r0 = r14.zzczh
            if (r0 != 0) goto L_0x000f
            java.lang.String r0 = "SphericalVideoProcessor started with no output texture."
            com.google.android.gms.internal.ads.zzakb.e(r0)
            java.util.concurrent.CountDownLatch r0 = r14.zzczm
            r0.countDown()
            return
        L_0x000f:
            javax.microedition.khronos.egl.EGL r0 = javax.microedition.khronos.egl.EGLContext.getEGL()
            javax.microedition.khronos.egl.EGL10 r0 = (javax.microedition.khronos.egl.EGL10) r0
            r14.zzczo = r0
            java.lang.Object r1 = javax.microedition.khronos.egl.EGL10.EGL_DEFAULT_DISPLAY
            javax.microedition.khronos.egl.EGLDisplay r0 = r0.eglGetDisplay(r1)
            r14.zzczp = r0
            javax.microedition.khronos.egl.EGLDisplay r1 = javax.microedition.khronos.egl.EGL10.EGL_NO_DISPLAY
            r2 = 3
            r3 = 2
            r4 = 0
            r5 = 1
            r6 = 0
            if (r0 != r1) goto L_0x002b
        L_0x0028:
            r0 = 0
            goto L_0x0096
        L_0x002b:
            int[] r0 = new int[r3]
            javax.microedition.khronos.egl.EGL10 r1 = r14.zzczo
            javax.microedition.khronos.egl.EGLDisplay r7 = r14.zzczp
            boolean r0 = r1.eglInitialize(r7, r0)
            if (r0 != 0) goto L_0x0038
            goto L_0x0028
        L_0x0038:
            int[] r0 = new int[r5]
            javax.microedition.khronos.egl.EGLConfig[] r1 = new javax.microedition.khronos.egl.EGLConfig[r5]
            r7 = 11
            int[] r9 = new int[r7]
            r9 = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344} // fill-array
            javax.microedition.khronos.egl.EGL10 r7 = r14.zzczo
            javax.microedition.khronos.egl.EGLDisplay r8 = r14.zzczp
            r11 = 1
            r10 = r1
            r12 = r0
            boolean r7 = r7.eglChooseConfig(r8, r9, r10, r11, r12)
            if (r7 == 0) goto L_0x0057
            r0 = r0[r6]
            if (r0 <= 0) goto L_0x0057
            r0 = r1[r6]
            goto L_0x0058
        L_0x0057:
            r0 = r4
        L_0x0058:
            if (r0 != 0) goto L_0x005b
            goto L_0x0028
        L_0x005b:
            int[] r1 = new int[r2]
            r1 = {12440, 2, 12344} // fill-array
            javax.microedition.khronos.egl.EGL10 r7 = r14.zzczo
            javax.microedition.khronos.egl.EGLDisplay r8 = r14.zzczp
            javax.microedition.khronos.egl.EGLContext r9 = javax.microedition.khronos.egl.EGL10.EGL_NO_CONTEXT
            javax.microedition.khronos.egl.EGLContext r1 = r7.eglCreateContext(r8, r0, r9, r1)
            r14.zzczq = r1
            if (r1 == 0) goto L_0x0028
            javax.microedition.khronos.egl.EGLContext r7 = javax.microedition.khronos.egl.EGL10.EGL_NO_CONTEXT
            if (r1 != r7) goto L_0x0073
            goto L_0x0028
        L_0x0073:
            javax.microedition.khronos.egl.EGL10 r1 = r14.zzczo
            javax.microedition.khronos.egl.EGLDisplay r7 = r14.zzczp
            android.graphics.SurfaceTexture r8 = r14.zzczh
            javax.microedition.khronos.egl.EGLSurface r0 = r1.eglCreateWindowSurface(r7, r0, r8, r4)
            r14.zzczr = r0
            if (r0 == 0) goto L_0x0028
            javax.microedition.khronos.egl.EGLSurface r1 = javax.microedition.khronos.egl.EGL10.EGL_NO_SURFACE
            if (r0 != r1) goto L_0x0086
            goto L_0x0028
        L_0x0086:
            javax.microedition.khronos.egl.EGL10 r0 = r14.zzczo
            javax.microedition.khronos.egl.EGLDisplay r1 = r14.zzczp
            javax.microedition.khronos.egl.EGLSurface r7 = r14.zzczr
            javax.microedition.khronos.egl.EGLContext r8 = r14.zzczq
            boolean r0 = r0.eglMakeCurrent(r1, r7, r7, r8)
            if (r0 != 0) goto L_0x0095
            goto L_0x0028
        L_0x0095:
            r0 = 1
        L_0x0096:
            r1 = 35633(0x8b31, float:4.9932E-41)
            com.google.android.gms.internal.ads.zzna<java.lang.String> r7 = com.google.android.gms.internal.ads.zznk.zzazp
            com.google.android.gms.internal.ads.zzni r8 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r8 = r8.zzd(r7)
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r9 = r7.zzja()
            boolean r8 = r8.equals(r9)
            if (r8 != 0) goto L_0x00ba
            com.google.android.gms.internal.ads.zzni r8 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r7 = r8.zzd(r7)
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x00bc
        L_0x00ba:
            java.lang.String r7 = "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}"
        L_0x00bc:
            int r1 = zzd(r1, r7)
            if (r1 != 0) goto L_0x00c5
        L_0x00c2:
            r8 = 0
            goto L_0x0147
        L_0x00c5:
            r7 = 35632(0x8b30, float:4.9931E-41)
            com.google.android.gms.internal.ads.zzna<java.lang.String> r8 = com.google.android.gms.internal.ads.zznk.zzazq
            com.google.android.gms.internal.ads.zzni r9 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r9 = r9.zzd(r8)
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r10 = r8.zzja()
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x00e9
            com.google.android.gms.internal.ads.zzni r9 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r8 = r9.zzd(r8)
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x00eb
        L_0x00e9:
            java.lang.String r8 = "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}"
        L_0x00eb:
            int r7 = zzd(r7, r8)
            if (r7 != 0) goto L_0x00f2
            goto L_0x00c2
        L_0x00f2:
            int r8 = android.opengl.GLES20.glCreateProgram()
            java.lang.String r9 = "createProgram"
            zzdo(r9)
            if (r8 == 0) goto L_0x0147
            android.opengl.GLES20.glAttachShader(r8, r1)
            java.lang.String r1 = "attachShader"
            zzdo(r1)
            android.opengl.GLES20.glAttachShader(r8, r7)
            java.lang.String r1 = "attachShader"
            zzdo(r1)
            android.opengl.GLES20.glLinkProgram(r8)
            java.lang.String r1 = "linkProgram"
            zzdo(r1)
            int[] r1 = new int[r5]
            r7 = 35714(0x8b82, float:5.0046E-41)
            android.opengl.GLES20.glGetProgramiv(r8, r7, r1, r6)
            java.lang.String r7 = "getProgramiv"
            zzdo(r7)
            r1 = r1[r6]
            if (r1 == r5) goto L_0x013f
            java.lang.String r1 = "SphericalVideoRenderer"
            java.lang.String r7 = "Could not link program: "
            android.util.Log.e(r1, r7)
            java.lang.String r1 = "SphericalVideoRenderer"
            java.lang.String r7 = android.opengl.GLES20.glGetProgramInfoLog(r8)
            android.util.Log.e(r1, r7)
            android.opengl.GLES20.glDeleteProgram(r8)
            java.lang.String r1 = "deleteProgram"
            zzdo(r1)
            goto L_0x00c2
        L_0x013f:
            android.opengl.GLES20.glValidateProgram(r8)
            java.lang.String r1 = "validateProgram"
            zzdo(r1)
        L_0x0147:
            r14.zzczi = r8
            android.opengl.GLES20.glUseProgram(r8)
            java.lang.String r1 = "useProgram"
            zzdo(r1)
            int r1 = r14.zzczi
            java.lang.String r7 = "aPosition"
            int r1 = android.opengl.GLES20.glGetAttribLocation(r1, r7)
            r9 = 3
            r10 = 5126(0x1406, float:7.183E-42)
            r11 = 0
            r12 = 12
            java.nio.FloatBuffer r13 = r14.zzczl
            r8 = r1
            android.opengl.GLES20.glVertexAttribPointer(r8, r9, r10, r11, r12, r13)
            java.lang.String r7 = "vertexAttribPointer"
            zzdo(r7)
            android.opengl.GLES20.glEnableVertexAttribArray(r1)
            java.lang.String r1 = "enableVertexAttribArray"
            zzdo(r1)
            int[] r1 = new int[r5]
            android.opengl.GLES20.glGenTextures(r5, r1, r6)
            java.lang.String r7 = "genTextures"
            zzdo(r7)
            r1 = r1[r6]
            r7 = 36197(0x8d65, float:5.0723E-41)
            android.opengl.GLES20.glBindTexture(r7, r1)
            java.lang.String r8 = "bindTextures"
            zzdo(r8)
            r8 = 10240(0x2800, float:1.4349E-41)
            r9 = 9729(0x2601, float:1.3633E-41)
            android.opengl.GLES20.glTexParameteri(r7, r8, r9)
            java.lang.String r8 = "texParameteri"
            zzdo(r8)
            r8 = 10241(0x2801, float:1.435E-41)
            android.opengl.GLES20.glTexParameteri(r7, r8, r9)
            java.lang.String r8 = "texParameteri"
            zzdo(r8)
            r8 = 10242(0x2802, float:1.4352E-41)
            r9 = 33071(0x812f, float:4.6342E-41)
            android.opengl.GLES20.glTexParameteri(r7, r8, r9)
            java.lang.String r8 = "texParameteri"
            zzdo(r8)
            r8 = 10243(0x2803, float:1.4354E-41)
            android.opengl.GLES20.glTexParameteri(r7, r8, r9)
            java.lang.String r7 = "texParameteri"
            zzdo(r7)
            int r7 = r14.zzczi
            java.lang.String r8 = "uVMat"
            int r7 = android.opengl.GLES20.glGetUniformLocation(r7, r8)
            r14.zzczj = r7
            r8 = 9
            float[] r8 = new float[r8]
            r8 = {1065353216, 0, 0, 0, 1065353216, 0, 0, 0, 1065353216} // fill-array
            android.opengl.GLES20.glUniformMatrix3fv(r7, r5, r6, r8, r6)
            int r7 = r14.zzczi
            if (r7 == 0) goto L_0x01d0
            r7 = 1
            goto L_0x01d1
        L_0x01d0:
            r7 = 0
        L_0x01d1:
            if (r0 == 0) goto L_0x0390
            if (r7 != 0) goto L_0x01d7
            goto L_0x0390
        L_0x01d7:
            android.graphics.SurfaceTexture r0 = new android.graphics.SurfaceTexture
            r0.<init>(r1)
            r14.zzczg = r0
            r0.setOnFrameAvailableListener(r14)
            java.util.concurrent.CountDownLatch r0 = r14.zzczm
            r0.countDown()
            com.google.android.gms.internal.ads.zzapr r0 = r14.zzcyw
            r0.start()
            r14.zzczs = r5     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
        L_0x01ed:
            boolean r0 = r14.zzczt     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            if (r0 != 0) goto L_0x033b
        L_0x01f1:
            int r0 = r14.zzczk     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            if (r0 <= 0) goto L_0x0200
            android.graphics.SurfaceTexture r0 = r14.zzczg     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r0.updateTexImage()     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            int r0 = r14.zzczk     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            int r0 = r0 - r5
            r14.zzczk = r0     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            goto L_0x01f1
        L_0x0200:
            com.google.android.gms.internal.ads.zzapr r0 = r14.zzcyw     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r1 = r14.zzcys     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            boolean r0 = r0.zza((float[]) r1)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r1 = 5
            r7 = 4
            r8 = 1070141403(0x3fc90fdb, float:1.5707964)
            if (r0 == 0) goto L_0x0286
            float r0 = r14.zzczd     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            boolean r0 = java.lang.Float.isNaN(r0)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            if (r0 == 0) goto L_0x027b
            float[] r0 = r14.zzcys     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r9 = new float[r2]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r10 = 0
            r9[r6] = r10     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r11 = 1065353216(0x3f800000, float:1.0)
            r9[r5] = r11     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r9[r3] = r10     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r10 = new float[r2]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r11 = r0[r6]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r12 = r9[r6]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r11 = r11 * r12
            r12 = r0[r5]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r13 = r9[r5]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r12 = r12 * r13
            float r11 = r11 + r12
            r12 = r0[r3]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r13 = r9[r3]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r12 = r12 * r13
            float r11 = r11 + r12
            r10[r6] = r11     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r11 = r0[r2]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r12 = r9[r6]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r11 = r11 * r12
            r12 = r0[r7]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r13 = r9[r5]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r12 = r12 * r13
            float r11 = r11 + r12
            r12 = r0[r1]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r13 = r9[r3]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r12 = r12 * r13
            float r11 = r11 + r12
            r10[r5] = r11     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r11 = 6
            r11 = r0[r11]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r12 = r9[r6]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r11 = r11 * r12
            r12 = 7
            r12 = r0[r12]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r13 = r9[r5]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r12 = r12 * r13
            float r11 = r11 + r12
            r12 = 8
            r0 = r0[r12]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r9 = r9[r3]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r0 = r0 * r9
            float r11 = r11 + r0
            r10[r3] = r11     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r0 = r10[r5]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            double r11 = (double) r0     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r0 = r10[r6]     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            double r9 = (double) r0     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            double r9 = java.lang.Math.atan2(r11, r9)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r0 = (float) r9     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r0 = r0 - r8
            float r0 = -r0
            r14.zzczd = r0     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
        L_0x027b:
            float[] r0 = r14.zzczb     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r9 = r14.zzczd     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r10 = r14.zzcze     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r9 = r9 + r10
            zzb((float[]) r0, (float) r9)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            goto L_0x0295
        L_0x0286:
            float[] r0 = r14.zzcys     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r9 = -1077342245(0xffffffffbfc90fdb, float:-1.5707964)
            zza(r0, r9)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r0 = r14.zzczb     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r9 = r14.zzcze     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            zzb((float[]) r0, (float) r9)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
        L_0x0295:
            float[] r0 = r14.zzcyx     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            zza(r0, r8)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r0 = r14.zzcyy     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r8 = r14.zzczb     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r9 = r14.zzcyx     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            zza((float[]) r0, (float[]) r8, (float[]) r9)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r0 = r14.zzcyz     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r8 = r14.zzcys     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r9 = r14.zzcyy     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            zza((float[]) r0, (float[]) r8, (float[]) r9)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r0 = r14.zzcza     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r8 = r14.zzczf     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            zza(r0, r8)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r0 = r14.zzczc     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r8 = r14.zzcza     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r9 = r14.zzcyz     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            zza((float[]) r0, (float[]) r8, (float[]) r9)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            int r0 = r14.zzczj     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float[] r8 = r14.zzczc     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            android.opengl.GLES20.glUniformMatrix3fv(r0, r5, r6, r8, r6)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            android.opengl.GLES20.glDrawArrays(r1, r6, r7)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            java.lang.String r0 = "drawArrays"
            zzdo(r0)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            android.opengl.GLES20.glFinish()     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            javax.microedition.khronos.egl.EGL10 r0 = r14.zzczo     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            javax.microedition.khronos.egl.EGLDisplay r1 = r14.zzczp     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            javax.microedition.khronos.egl.EGLSurface r7 = r14.zzczr     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r0.eglSwapBuffers(r1, r7)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            boolean r0 = r14.zzczs     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            if (r0 == 0) goto L_0x0321
            int r0 = r14.zzuq     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            int r1 = r14.zzur     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            android.opengl.GLES20.glViewport(r6, r6, r0, r1)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            java.lang.String r0 = "viewport"
            zzdo(r0)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            int r0 = r14.zzczi     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            java.lang.String r1 = "uFOVx"
            int r0 = android.opengl.GLES20.glGetUniformLocation(r0, r1)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            int r1 = r14.zzczi     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            java.lang.String r7 = "uFOVy"
            int r1 = android.opengl.GLES20.glGetUniformLocation(r1, r7)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            int r7 = r14.zzuq     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            int r8 = r14.zzur     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            r9 = 1063216883(0x3f5f66f3, float:0.87266463)
            if (r7 <= r8) goto L_0x0310
            android.opengl.GLES20.glUniform1f(r0, r9)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            int r0 = r14.zzur     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r0 = (float) r0     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r0 = r0 * r9
            int r7 = r14.zzuq     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r7 = (float) r7     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r0 = r0 / r7
            android.opengl.GLES20.glUniform1f(r1, r0)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            goto L_0x031f
        L_0x0310:
            int r7 = r14.zzuq     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r7 = (float) r7     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r7 = r7 * r9
            int r8 = r14.zzur     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r8 = (float) r8     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            float r7 = r7 / r8
            android.opengl.GLES20.glUniform1f(r0, r7)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
            android.opengl.GLES20.glUniform1f(r1, r9)     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
        L_0x031f:
            r14.zzczs = r6     // Catch:{ IllegalStateException -> 0x036a, all -> 0x034b }
        L_0x0321:
            java.lang.Object r0 = r14.zzczn     // Catch:{ InterruptedException -> 0x01ed }
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x01ed }
            boolean r1 = r14.zzczt     // Catch:{ all -> 0x0338 }
            if (r1 != 0) goto L_0x0335
            boolean r1 = r14.zzczs     // Catch:{ all -> 0x0338 }
            if (r1 != 0) goto L_0x0335
            int r1 = r14.zzczk     // Catch:{ all -> 0x0338 }
            if (r1 != 0) goto L_0x0335
            java.lang.Object r1 = r14.zzczn     // Catch:{ all -> 0x0338 }
            r1.wait()     // Catch:{ all -> 0x0338 }
        L_0x0335:
            monitor-exit(r0)     // Catch:{ all -> 0x0338 }
            goto L_0x01ed
        L_0x0338:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0338 }
            throw r1     // Catch:{ InterruptedException -> 0x01ed }
        L_0x033b:
            com.google.android.gms.internal.ads.zzapr r0 = r14.zzcyw
            r0.stop()
            android.graphics.SurfaceTexture r0 = r14.zzczg
            r0.setOnFrameAvailableListener(r4)
            r14.zzczg = r4
            r14.zztk()
            return
        L_0x034b:
            r0 = move-exception
            java.lang.String r1 = "SphericalVideoProcessor died."
            com.google.android.gms.internal.ads.zzakb.zzb(r1, r0)     // Catch:{ all -> 0x037f }
            com.google.android.gms.internal.ads.zzajm r1 = com.google.android.gms.ads.internal.zzbv.zzeo()     // Catch:{ all -> 0x037f }
            java.lang.String r2 = "SphericalVideoProcessor.run.2"
            r1.zza(r0, r2)     // Catch:{ all -> 0x037f }
            com.google.android.gms.internal.ads.zzapr r0 = r14.zzcyw
            r0.stop()
            android.graphics.SurfaceTexture r0 = r14.zzczg
            r0.setOnFrameAvailableListener(r4)
            r14.zzczg = r4
            r14.zztk()
            return
        L_0x036a:
            java.lang.String r0 = "SphericalVideoProcessor halted unexpectedly."
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)     // Catch:{ all -> 0x037f }
            com.google.android.gms.internal.ads.zzapr r0 = r14.zzcyw
            r0.stop()
            android.graphics.SurfaceTexture r0 = r14.zzczg
            r0.setOnFrameAvailableListener(r4)
            r14.zzczg = r4
            r14.zztk()
            return
        L_0x037f:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzapr r1 = r14.zzcyw
            r1.stop()
            android.graphics.SurfaceTexture r1 = r14.zzczg
            r1.setOnFrameAvailableListener(r4)
            r14.zzczg = r4
            r14.zztk()
            throw r0
        L_0x0390:
            javax.microedition.khronos.egl.EGL10 r0 = r14.zzczo
            int r0 = r0.eglGetError()
            java.lang.String r0 = android.opengl.GLUtils.getEGLErrorString(r0)
            java.lang.String r1 = "EGL initialization failed: "
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r0.length()
            if (r2 == 0) goto L_0x03ab
            java.lang.String r0 = r1.concat(r0)
            goto L_0x03b0
        L_0x03ab:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r1)
        L_0x03b0:
            com.google.android.gms.internal.ads.zzakb.e(r0)
            com.google.android.gms.internal.ads.zzajm r1 = com.google.android.gms.ads.internal.zzbv.zzeo()
            java.lang.Throwable r2 = new java.lang.Throwable
            r2.<init>(r0)
            java.lang.String r0 = "SphericalVideoProcessor.run.1"
            r1.zza(r2, r0)
            r14.zztk()
            java.util.concurrent.CountDownLatch r0 = r14.zzczm
            r0.countDown()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapu.run():void");
    }

    public final void zza(SurfaceTexture surfaceTexture, int i, int i2) {
        this.zzuq = i;
        this.zzur = i2;
        this.zzczh = surfaceTexture;
    }

    public final void zzb(float f, float f2) {
        float f3;
        float f4;
        float f5;
        int i = this.zzuq;
        int i2 = this.zzur;
        float f6 = f * 1.7453293f;
        if (i > i2) {
            f4 = f6 / ((float) i);
            f3 = f2 * 1.7453293f;
            f5 = (float) i;
        } else {
            f4 = f6 / ((float) i2);
            f3 = f2 * 1.7453293f;
            f5 = (float) i2;
        }
        this.zzcze -= f4;
        float f7 = this.zzczf - (f3 / f5);
        this.zzczf = f7;
        if (f7 < -1.5707964f) {
            this.zzczf = -1.5707964f;
        }
        if (this.zzczf > 1.5707964f) {
            this.zzczf = 1.5707964f;
        }
    }

    public final void zzh(int i, int i2) {
        synchronized (this.zzczn) {
            this.zzuq = i;
            this.zzur = i2;
            this.zzczs = true;
            this.zzczn.notifyAll();
        }
    }

    public final void zznn() {
        synchronized (this.zzczn) {
            this.zzczn.notifyAll();
        }
    }

    public final void zzti() {
        synchronized (this.zzczn) {
            this.zzczt = true;
            this.zzczh = null;
            this.zzczn.notifyAll();
        }
    }

    public final SurfaceTexture zztj() {
        if (this.zzczh == null) {
            return null;
        }
        try {
            this.zzczm.await();
        } catch (InterruptedException unused) {
        }
        return this.zzczg;
    }
}
