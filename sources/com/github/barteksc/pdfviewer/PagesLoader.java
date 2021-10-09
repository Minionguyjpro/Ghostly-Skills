package com.github.barteksc.pdfviewer;

import android.graphics.RectF;
import android.util.Pair;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.Constants;
import com.github.barteksc.pdfviewer.util.MathUtils;

class PagesLoader {
    private int cacheOrder;
    private float colWidth;
    private Pair<Integer, Integer> colsRows;
    private float pageRelativePartHeight;
    private float pageRelativePartWidth;
    private float partRenderHeight;
    private float partRenderWidth;
    private PDFView pdfView;
    private float rowHeight;
    private float scaledHeight;
    private float scaledWidth;
    private int thumbnailHeight;
    private final RectF thumbnailRect = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
    private int thumbnailWidth;
    private float xOffset;
    private float yOffset;

    private class Holder {
        int col;
        int page;
        int row;

        private Holder() {
        }
    }

    public PagesLoader(PDFView pDFView) {
        this.pdfView = pDFView;
    }

    private Pair<Integer, Integer> getPageColsRows() {
        return new Pair<>(Integer.valueOf(MathUtils.ceil(1.0f / (((1.0f / this.pdfView.getOptimalPageWidth()) * 256.0f) / this.pdfView.getZoom()))), Integer.valueOf(MathUtils.ceil(1.0f / (((1.0f / this.pdfView.getOptimalPageHeight()) * 256.0f) / this.pdfView.getZoom()))));
    }

    private int documentPage(int i) {
        int i2;
        if (this.pdfView.getFilteredUserPages() == null) {
            i2 = i;
        } else if (i < 0 || i >= this.pdfView.getFilteredUserPages().length) {
            return -1;
        } else {
            i2 = this.pdfView.getFilteredUserPages()[i];
        }
        if (i2 < 0 || i >= this.pdfView.getDocumentPageCount()) {
            return -1;
        }
        return i2;
    }

    private Holder getPageAndCoordsByOffset(float f) {
        Holder holder = new Holder();
        float f2 = -MathUtils.max(f, 0.0f);
        if (this.pdfView.isSwipeVertical()) {
            holder.page = MathUtils.floor(f2 / this.scaledHeight);
            holder.row = MathUtils.floor(Math.abs(f2 - (this.scaledHeight * ((float) holder.page))) / this.rowHeight);
            holder.col = MathUtils.floor(this.xOffset / this.colWidth);
        } else {
            holder.page = MathUtils.floor(f2 / this.scaledWidth);
            holder.col = MathUtils.floor(Math.abs(f2 - (this.scaledWidth * ((float) holder.page))) / this.colWidth);
            holder.row = MathUtils.floor(this.yOffset / this.rowHeight);
        }
        return holder;
    }

    private void loadThumbnail(int i, int i2) {
        if (!this.pdfView.cacheManager.containsThumbnail(i, i2, (float) this.thumbnailWidth, (float) this.thumbnailHeight, this.thumbnailRect)) {
            this.pdfView.renderingAsyncTask.addRenderingTask(i, i2, (float) this.thumbnailWidth, (float) this.thumbnailHeight, this.thumbnailRect, true, 0, this.pdfView.isBestQuality(), this.pdfView.isAnnotationRendering());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0044 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int loadRelative(int r11, int r12, boolean r13) {
        /*
            r10 = this;
            com.github.barteksc.pdfviewer.PDFView r0 = r10.pdfView
            boolean r0 = r0.isSwipeVertical()
            r1 = 0
            if (r0 == 0) goto L_0x0020
            float r0 = r10.rowHeight
            float r11 = (float) r11
            float r0 = r0 * r11
            r11 = 1065353216(0x3f800000, float:1.0)
            float r0 = r0 + r11
            com.github.barteksc.pdfviewer.PDFView r11 = r10.pdfView
            float r11 = r11.getCurrentYOffset()
            if (r13 == 0) goto L_0x0034
            com.github.barteksc.pdfviewer.PDFView r13 = r10.pdfView
            int r13 = r13.getHeight()
            goto L_0x0035
        L_0x0020:
            float r0 = r10.colWidth
            float r11 = (float) r11
            float r0 = r0 * r11
            com.github.barteksc.pdfviewer.PDFView r11 = r10.pdfView
            float r11 = r11.getCurrentXOffset()
            if (r13 == 0) goto L_0x0034
            com.github.barteksc.pdfviewer.PDFView r13 = r10.pdfView
            int r13 = r13.getWidth()
            goto L_0x0035
        L_0x0034:
            r13 = 0
        L_0x0035:
            float r13 = (float) r13
            float r11 = r11 - r13
            float r11 = r11 - r0
            com.github.barteksc.pdfviewer.PagesLoader$Holder r11 = r10.getPageAndCoordsByOffset(r11)
            int r13 = r11.page
            int r13 = r10.documentPage(r13)
            if (r13 >= 0) goto L_0x0045
            return r1
        L_0x0045:
            int r0 = r11.page
            r10.loadThumbnail(r0, r13)
            com.github.barteksc.pdfviewer.PDFView r0 = r10.pdfView
            boolean r0 = r0.isSwipeVertical()
            if (r0 == 0) goto L_0x009d
            float r0 = r10.xOffset
            float r2 = r10.colWidth
            float r0 = r0 / r2
            int r0 = com.github.barteksc.pdfviewer.util.MathUtils.floor(r0)
            int r0 = r0 + -1
            int r0 = com.github.barteksc.pdfviewer.util.MathUtils.min(r0, r1)
            float r2 = r10.xOffset
            com.github.barteksc.pdfviewer.PDFView r3 = r10.pdfView
            int r3 = r3.getWidth()
            float r3 = (float) r3
            float r2 = r2 + r3
            float r3 = r10.colWidth
            float r2 = r2 / r3
            int r2 = com.github.barteksc.pdfviewer.util.MathUtils.ceil(r2)
            int r2 = r2 + 1
            android.util.Pair<java.lang.Integer, java.lang.Integer> r3 = r10.colsRows
            java.lang.Object r3 = r3.first
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            int r9 = com.github.barteksc.pdfviewer.util.MathUtils.max((int) r2, (int) r3)
        L_0x0082:
            if (r0 > r9) goto L_0x00e8
            int r3 = r11.page
            int r5 = r11.row
            float r7 = r10.pageRelativePartWidth
            float r8 = r10.pageRelativePartHeight
            r2 = r10
            r4 = r13
            r6 = r0
            boolean r2 = r2.loadCell(r3, r4, r5, r6, r7, r8)
            if (r2 == 0) goto L_0x0097
            int r1 = r1 + 1
        L_0x0097:
            if (r1 < r12) goto L_0x009a
            return r1
        L_0x009a:
            int r0 = r0 + 1
            goto L_0x0082
        L_0x009d:
            float r0 = r10.yOffset
            float r2 = r10.rowHeight
            float r0 = r0 / r2
            int r0 = com.github.barteksc.pdfviewer.util.MathUtils.floor(r0)
            int r0 = r0 + -1
            int r0 = com.github.barteksc.pdfviewer.util.MathUtils.min(r0, r1)
            float r2 = r10.yOffset
            com.github.barteksc.pdfviewer.PDFView r3 = r10.pdfView
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r2 = r2 + r3
            float r3 = r10.rowHeight
            float r2 = r2 / r3
            int r2 = com.github.barteksc.pdfviewer.util.MathUtils.ceil(r2)
            int r2 = r2 + 1
            android.util.Pair<java.lang.Integer, java.lang.Integer> r3 = r10.colsRows
            java.lang.Object r3 = r3.second
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            int r9 = com.github.barteksc.pdfviewer.util.MathUtils.max((int) r2, (int) r3)
        L_0x00cd:
            if (r0 > r9) goto L_0x00e8
            int r3 = r11.page
            int r6 = r11.col
            float r7 = r10.pageRelativePartWidth
            float r8 = r10.pageRelativePartHeight
            r2 = r10
            r4 = r13
            r5 = r0
            boolean r2 = r2.loadCell(r3, r4, r5, r6, r7, r8)
            if (r2 == 0) goto L_0x00e2
            int r1 = r1 + 1
        L_0x00e2:
            if (r1 < r12) goto L_0x00e5
            return r1
        L_0x00e5:
            int r0 = r0 + 1
            goto L_0x00cd
        L_0x00e8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.barteksc.pdfviewer.PagesLoader.loadRelative(int, int, boolean):int");
    }

    public int loadVisible() {
        int i;
        Holder holder;
        int i2;
        int i3;
        if (this.pdfView.isSwipeVertical()) {
            holder = getPageAndCoordsByOffset(this.pdfView.getCurrentYOffset());
            Holder pageAndCoordsByOffset = getPageAndCoordsByOffset((this.pdfView.getCurrentYOffset() - ((float) this.pdfView.getHeight())) + 1.0f);
            if (holder.page == pageAndCoordsByOffset.page) {
                i3 = (pageAndCoordsByOffset.row - holder.row) + 1;
            } else {
                int intValue = (((Integer) this.colsRows.second).intValue() - holder.row) + 0;
                int i4 = holder.page;
                while (true) {
                    i4++;
                    if (i4 >= pageAndCoordsByOffset.page) {
                        break;
                    }
                    intValue += ((Integer) this.colsRows.second).intValue();
                }
                i3 = pageAndCoordsByOffset.row + 1 + intValue;
            }
            i = 0;
            for (int i5 = 0; i5 < i3 && i < Constants.Cache.CACHE_SIZE; i5++) {
                i += loadRelative(i5, Constants.Cache.CACHE_SIZE - i, false);
            }
        } else {
            holder = getPageAndCoordsByOffset(this.pdfView.getCurrentXOffset());
            Holder pageAndCoordsByOffset2 = getPageAndCoordsByOffset((this.pdfView.getCurrentXOffset() - ((float) this.pdfView.getWidth())) + 1.0f);
            if (holder.page == pageAndCoordsByOffset2.page) {
                i2 = (pageAndCoordsByOffset2.col - holder.col) + 1;
            } else {
                int intValue2 = (((Integer) this.colsRows.first).intValue() - holder.col) + 0;
                int i6 = holder.page;
                while (true) {
                    i6++;
                    if (i6 >= pageAndCoordsByOffset2.page) {
                        break;
                    }
                    intValue2 += ((Integer) this.colsRows.first).intValue();
                }
                i2 = pageAndCoordsByOffset2.col + 1 + intValue2;
            }
            int i7 = 0;
            for (int i8 = 0; i8 < i2 && i < Constants.Cache.CACHE_SIZE; i8++) {
                i7 = i + loadRelative(i8, Constants.Cache.CACHE_SIZE - i, false);
            }
        }
        int documentPage = documentPage(holder.page - 1);
        if (documentPage >= 0) {
            loadThumbnail(holder.page - 1, documentPage);
        }
        int documentPage2 = documentPage(holder.page + 1);
        if (documentPage2 >= 0) {
            loadThumbnail(holder.page + 1, documentPage2);
        }
        return i;
    }

    private boolean loadCell(int i, int i2, int i3, int i4, float f, float f2) {
        float f3 = ((float) i4) * f;
        float f4 = ((float) i3) * f2;
        float f5 = this.partRenderWidth;
        float f6 = this.partRenderHeight;
        float f7 = f3 + f > 1.0f ? 1.0f - f3 : f;
        float f8 = f4 + f2 > 1.0f ? 1.0f - f4 : f2;
        float f9 = f5 * f7;
        float f10 = f6 * f8;
        RectF rectF = new RectF(f3, f4, f7 + f3, f8 + f4);
        if (f9 <= 0.0f || f10 <= 0.0f) {
            return false;
        }
        if (!this.pdfView.cacheManager.upPartIfContained(i, i2, f9, f10, rectF, this.cacheOrder)) {
            this.pdfView.renderingAsyncTask.addRenderingTask(i, i2, f9, f10, rectF, false, this.cacheOrder, this.pdfView.isBestQuality(), this.pdfView.isAnnotationRendering());
        }
        this.cacheOrder++;
        return true;
    }

    public void loadPages() {
        PDFView pDFView = this.pdfView;
        this.scaledHeight = pDFView.toCurrentScale(pDFView.getOptimalPageHeight());
        PDFView pDFView2 = this.pdfView;
        this.scaledWidth = pDFView2.toCurrentScale(pDFView2.getOptimalPageWidth());
        this.thumbnailWidth = (int) (this.pdfView.getOptimalPageWidth() * 0.3f);
        this.thumbnailHeight = (int) (this.pdfView.getOptimalPageHeight() * 0.3f);
        this.colsRows = getPageColsRows();
        this.xOffset = -MathUtils.max(this.pdfView.getCurrentXOffset(), 0.0f);
        this.yOffset = -MathUtils.max(this.pdfView.getCurrentYOffset(), 0.0f);
        this.rowHeight = this.scaledHeight / ((float) ((Integer) this.colsRows.second).intValue());
        this.colWidth = this.scaledWidth / ((float) ((Integer) this.colsRows.first).intValue());
        this.pageRelativePartWidth = 1.0f / ((float) ((Integer) this.colsRows.first).intValue());
        float intValue = 1.0f / ((float) ((Integer) this.colsRows.second).intValue());
        this.pageRelativePartHeight = intValue;
        this.partRenderWidth = 256.0f / this.pageRelativePartWidth;
        this.partRenderHeight = 256.0f / intValue;
        this.cacheOrder = 1;
        int loadVisible = loadVisible();
        if (this.pdfView.getScrollDir().equals(PDFView.ScrollDir.END)) {
            for (int i = 0; i < 7 && loadVisible < Constants.Cache.CACHE_SIZE; i++) {
                loadVisible += loadRelative(i, loadVisible, true);
            }
            return;
        }
        for (int i2 = 0; i2 > -7 && loadVisible < Constants.Cache.CACHE_SIZE; i2--) {
            loadVisible += loadRelative(i2, loadVisible, false);
        }
    }
}
