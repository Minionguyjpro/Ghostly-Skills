package androidx.media2.exoplayer.external.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class BaseAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer = EMPTY_BUFFER;
    protected int channelCount = -1;
    protected int encoding = -1;
    private boolean inputEnded;
    private ByteBuffer outputBuffer = EMPTY_BUFFER;
    protected int sampleRateHz = -1;

    /* access modifiers changed from: protected */
    public void onFlush() {
    }

    /* access modifiers changed from: protected */
    public void onQueueEndOfStream() {
    }

    /* access modifiers changed from: protected */
    public void onReset() {
    }

    public boolean isActive() {
        return this.sampleRateHz != -1;
    }

    public int getOutputChannelCount() {
        return this.channelCount;
    }

    public int getOutputEncoding() {
        return this.encoding;
    }

    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    public final void queueEndOfStream() {
        this.inputEnded = true;
        onQueueEndOfStream();
    }

    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = EMPTY_BUFFER;
        return byteBuffer;
    }

    public boolean isEnded() {
        return this.inputEnded && this.outputBuffer == EMPTY_BUFFER;
    }

    public final void flush() {
        this.outputBuffer = EMPTY_BUFFER;
        this.inputEnded = false;
        onFlush();
    }

    public final void reset() {
        flush();
        this.buffer = EMPTY_BUFFER;
        this.sampleRateHz = -1;
        this.channelCount = -1;
        this.encoding = -1;
        onReset();
    }

    /* access modifiers changed from: protected */
    public final boolean setInputFormat(int i, int i2, int i3) {
        if (i == this.sampleRateHz && i2 == this.channelCount && i3 == this.encoding) {
            return false;
        }
        this.sampleRateHz = i;
        this.channelCount = i2;
        this.encoding = i3;
        return true;
    }

    /* access modifiers changed from: protected */
    public final ByteBuffer replaceOutputBuffer(int i) {
        if (this.buffer.capacity() < i) {
            this.buffer = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.buffer.clear();
        }
        ByteBuffer byteBuffer = this.buffer;
        this.outputBuffer = byteBuffer;
        return byteBuffer;
    }

    /* access modifiers changed from: protected */
    public final boolean hasPendingOutput() {
        return this.outputBuffer.hasRemaining();
    }
}
