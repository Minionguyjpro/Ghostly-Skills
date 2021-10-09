package com.github.barteksc.pdfviewer.model;

import android.graphics.Bitmap;
import android.graphics.RectF;

public class PagePart {
    private int cacheOrder;
    private float height;
    private int page;
    private RectF pageRelativeBounds;
    private Bitmap renderedBitmap;
    private boolean thumbnail;
    private int userPage;
    private float width;

    public PagePart(int i, int i2, Bitmap bitmap, float f, float f2, RectF rectF, boolean z, int i3) {
        this.userPage = i;
        this.page = i2;
        this.renderedBitmap = bitmap;
        this.pageRelativeBounds = rectF;
        this.thumbnail = z;
        this.cacheOrder = i3;
    }

    public int getCacheOrder() {
        return this.cacheOrder;
    }

    public int getPage() {
        return this.page;
    }

    public int getUserPage() {
        return this.userPage;
    }

    public Bitmap getRenderedBitmap() {
        return this.renderedBitmap;
    }

    public RectF getPageRelativeBounds() {
        return this.pageRelativeBounds;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public boolean isThumbnail() {
        return this.thumbnail;
    }

    public void setCacheOrder(int i) {
        this.cacheOrder = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PagePart)) {
            return false;
        }
        PagePart pagePart = (PagePart) obj;
        if (pagePart.getPage() == this.page && pagePart.getUserPage() == this.userPage && pagePart.getWidth() == this.width && pagePart.getHeight() == this.height && pagePart.getPageRelativeBounds().left == this.pageRelativeBounds.left && pagePart.getPageRelativeBounds().right == this.pageRelativeBounds.right && pagePart.getPageRelativeBounds().top == this.pageRelativeBounds.top && pagePart.getPageRelativeBounds().bottom == this.pageRelativeBounds.bottom) {
            return true;
        }
        return false;
    }
}
