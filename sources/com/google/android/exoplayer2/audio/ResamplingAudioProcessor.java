package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;

final class ResamplingAudioProcessor extends BaseAudioProcessor {
    ResamplingAudioProcessor() {
    }

    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        int i = audioFormat.encoding;
        if (i == 3 || i == 2 || i == Integer.MIN_VALUE || i == 1073741824) {
            return i != 2 ? new AudioProcessor.AudioFormat(audioFormat.sampleRate, audioFormat.channelCount, 2) : AudioProcessor.AudioFormat.NOT_SET;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0065 A[LOOP:2: B:19:0x0065->B:20:0x0067, LOOP_START, PHI: r0 
      PHI: (r0v2 int) = (r0v0 int), (r0v3 int) binds: [B:10:0x002d, B:20:0x0067] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void queueInput(java.nio.ByteBuffer r8) {
        /*
            r7 = this;
            int r0 = r8.position()
            int r1 = r8.limit()
            int r2 = r1 - r0
            com.google.android.exoplayer2.audio.AudioProcessor$AudioFormat r3 = r7.inputAudioFormat
            int r3 = r3.encoding
            r4 = 1073741824(0x40000000, float:2.0)
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = 3
            if (r3 == r5) goto L_0x0022
            if (r3 == r6) goto L_0x0023
            if (r3 != r4) goto L_0x001c
            int r2 = r2 / 2
            goto L_0x0025
        L_0x001c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>()
            throw r8
        L_0x0022:
            int r2 = r2 / r6
        L_0x0023:
            int r2 = r2 * 2
        L_0x0025:
            java.nio.ByteBuffer r2 = r7.replaceOutputBuffer(r2)
            com.google.android.exoplayer2.audio.AudioProcessor$AudioFormat r3 = r7.inputAudioFormat
            int r3 = r3.encoding
            if (r3 == r5) goto L_0x0065
            if (r3 == r6) goto L_0x0050
            if (r3 != r4) goto L_0x004a
        L_0x0033:
            if (r0 >= r1) goto L_0x007c
            int r3 = r0 + 2
            byte r3 = r8.get(r3)
            r2.put(r3)
            int r3 = r0 + 3
            byte r3 = r8.get(r3)
            r2.put(r3)
            int r0 = r0 + 4
            goto L_0x0033
        L_0x004a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>()
            throw r8
        L_0x0050:
            if (r0 >= r1) goto L_0x007c
            r3 = 0
            r2.put(r3)
            byte r3 = r8.get(r0)
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 + -128
            byte r3 = (byte) r3
            r2.put(r3)
            int r0 = r0 + 1
            goto L_0x0050
        L_0x0065:
            if (r0 >= r1) goto L_0x007c
            int r3 = r0 + 1
            byte r3 = r8.get(r3)
            r2.put(r3)
            int r3 = r0 + 2
            byte r3 = r8.get(r3)
            r2.put(r3)
            int r0 = r0 + 3
            goto L_0x0065
        L_0x007c:
            int r0 = r8.limit()
            r8.position(r0)
            r2.flip()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.ResamplingAudioProcessor.queueInput(java.nio.ByteBuffer):void");
    }
}
