package androidx.media2.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

class MusicView extends ViewGroup {
    private MusicViewType mType = MusicViewType.WITHOUT_TITLE;
    private View mWithTitleLandscape;
    private View mWithTitlePortrait;
    private View mWithoutTitle;

    private enum MusicViewType {
        WITH_TITLE_LANDSCAPE,
        WITH_TITLE_PORTRAIT,
        WITHOUT_TITLE
    }

    MusicView(Context context) {
        super(context);
        inflateLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == 1073741824 && View.MeasureSpec.getMode(i2) == 1073741824) {
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            if (size > size2) {
                this.mType = MusicViewType.WITH_TITLE_LANDSCAPE;
                this.mWithTitleLandscape.measure(View.MeasureSpec.makeMeasureSpec(size, 0), View.MeasureSpec.makeMeasureSpec(size2, RecyclerView.UNDEFINED_DURATION));
                if (hasTooSmallMeasuredState(this.mWithTitleLandscape) || this.mWithTitleLandscape.getMeasuredWidth() > size) {
                    this.mType = MusicViewType.WITHOUT_TITLE;
                }
            } else {
                this.mType = MusicViewType.WITH_TITLE_PORTRAIT;
                this.mWithTitlePortrait.measure(View.MeasureSpec.makeMeasureSpec(size, RecyclerView.UNDEFINED_DURATION), View.MeasureSpec.makeMeasureSpec(size2, 0));
                if (hasTooSmallMeasuredState(this.mWithTitlePortrait) || this.mWithTitlePortrait.getMeasuredHeight() > size2) {
                    this.mType = MusicViewType.WITHOUT_TITLE;
                }
            }
            if (this.mType == MusicViewType.WITHOUT_TITLE) {
                this.mWithoutTitle.measure(View.MeasureSpec.makeMeasureSpec(size / 2, 1073741824), View.MeasureSpec.makeMeasureSpec(size2 / 2, 1073741824));
            }
            setMeasuredDimension(size, size2);
            return;
        }
        throw new AssertionError("MusicView should be measured in MeasureSpec.EXACTLY");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view;
        if (this.mType == MusicViewType.WITH_TITLE_LANDSCAPE) {
            view = this.mWithTitleLandscape;
        } else if (this.mType == MusicViewType.WITH_TITLE_PORTRAIT) {
            view = this.mWithTitlePortrait;
        } else {
            view = this.mWithoutTitle;
        }
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt == view) {
                childAt.setVisibility(0);
            } else {
                childAt.setVisibility(4);
            }
        }
        int i6 = i3 - i;
        int i7 = i4 - i2;
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i8 = (i6 - measuredWidth) / 2;
        int i9 = (i7 - measuredHeight) / 2;
        view.layout(i8, i9, measuredWidth + i8, measuredHeight + i9);
    }

    /* access modifiers changed from: package-private */
    public void setAlbumDrawable(Drawable drawable) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) getChildAt(i).findViewById(R.id.album);
            if (imageView != null) {
                imageView.setImageDrawable(drawable);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setTitleText(String str) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView textView = (TextView) getChildAt(i).findViewById(R.id.title);
            if (textView != null) {
                textView.setText(str);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setArtistText(String str) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView textView = (TextView) getChildAt(i).findViewById(R.id.artist);
            if (textView != null) {
                textView.setText(str);
            }
        }
    }

    private void inflateLayout() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        this.mWithTitleLandscape = layoutInflater.inflate(R.layout.music_with_title_landscape, (ViewGroup) null);
        this.mWithTitlePortrait = layoutInflater.inflate(R.layout.music_with_title_portrait, (ViewGroup) null);
        this.mWithoutTitle = layoutInflater.inflate(R.layout.music_without_title, (ViewGroup) null);
        addView(this.mWithTitleLandscape);
        addView(this.mWithTitlePortrait);
        addView(this.mWithoutTitle);
    }

    private static boolean hasTooSmallMeasuredState(View view) {
        return ((view.getMeasuredHeightAndState() & 16777216) | (view.getMeasuredWidthAndState() & 16777216)) != 0;
    }
}
