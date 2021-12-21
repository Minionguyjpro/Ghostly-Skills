package com.google.android.exoplayer2.text;

import android.graphics.Bitmap;
import android.text.Layout;
import androidx.recyclerview.widget.RecyclerView;
import com.mopub.mobileads.resource.DrawableConstants;

public class Cue {
    public static final Cue EMPTY = new Cue("");
    public final Bitmap bitmap;
    public final float bitmapHeight;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final int positionAnchor;
    public final float size;
    public final CharSequence text;
    public final Layout.Alignment textAlignment;
    public final float textSize;
    public final int textSizeType;
    public final int windowColor;
    public final boolean windowColorSet;

    public Cue(CharSequence charSequence) {
        this(charSequence, (Layout.Alignment) null, -3.4028235E38f, RecyclerView.UNDEFINED_DURATION, RecyclerView.UNDEFINED_DURATION, -3.4028235E38f, RecyclerView.UNDEFINED_DURATION, -3.4028235E38f);
    }

    public Cue(CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3) {
        this(charSequence, alignment, f, i, i2, f2, i3, f3, false, DrawableConstants.CtaButton.BACKGROUND_COLOR);
    }

    public Cue(CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4) {
        this(charSequence, alignment, (Bitmap) null, f, i, i2, f2, i3, RecyclerView.UNDEFINED_DURATION, -3.4028235E38f, f3, -3.4028235E38f, z, i4);
    }

    private Cue(CharSequence charSequence, Layout.Alignment alignment, Bitmap bitmap2, float f, int i, int i2, float f2, int i3, int i4, float f3, float f4, float f5, boolean z, int i5) {
        this.text = charSequence;
        this.textAlignment = alignment;
        this.bitmap = bitmap2;
        this.line = f;
        this.lineType = i;
        this.lineAnchor = i2;
        this.position = f2;
        this.positionAnchor = i3;
        this.size = f4;
        this.bitmapHeight = f5;
        this.windowColorSet = z;
        this.windowColor = i5;
        this.textSizeType = i4;
        this.textSize = f3;
    }
}
