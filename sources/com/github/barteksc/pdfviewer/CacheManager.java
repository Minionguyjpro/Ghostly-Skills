package com.github.barteksc.pdfviewer;

import android.graphics.Bitmap;
import android.graphics.RectF;
import com.github.barteksc.pdfviewer.model.PagePart;
import com.github.barteksc.pdfviewer.util.Constants;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

class CacheManager {
    private final PriorityQueue<PagePart> activeCache = new PriorityQueue<>(Constants.Cache.CACHE_SIZE, this.comparator);
    private final PagePartComparator comparator = new PagePartComparator();
    private final Object passiveActiveLock = new Object();
    private final PriorityQueue<PagePart> passiveCache = new PriorityQueue<>(Constants.Cache.CACHE_SIZE, this.comparator);
    private final List<PagePart> thumbnails = new ArrayList();

    public void cachePart(PagePart pagePart) {
        synchronized (this.passiveActiveLock) {
            makeAFreeSpace();
            this.activeCache.offer(pagePart);
        }
    }

    public void makeANewSet() {
        synchronized (this.passiveActiveLock) {
            this.passiveCache.addAll(this.activeCache);
            this.activeCache.clear();
        }
    }

    private void makeAFreeSpace() {
        synchronized (this.passiveActiveLock) {
            while (this.activeCache.size() + this.passiveCache.size() >= Constants.Cache.CACHE_SIZE && !this.passiveCache.isEmpty()) {
                this.passiveCache.poll().getRenderedBitmap().recycle();
            }
            while (this.activeCache.size() + this.passiveCache.size() >= Constants.Cache.CACHE_SIZE && !this.activeCache.isEmpty()) {
                this.activeCache.poll().getRenderedBitmap().recycle();
            }
        }
    }

    public void cacheThumbnail(PagePart pagePart) {
        synchronized (this.thumbnails) {
            if (this.thumbnails.size() >= 6) {
                this.thumbnails.remove(0).getRenderedBitmap().recycle();
            }
            this.thumbnails.add(pagePart);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean upPartIfContained(int r12, int r13, float r14, float r15, android.graphics.RectF r16, int r17) {
        /*
            r11 = this;
            r1 = r11
            com.github.barteksc.pdfviewer.model.PagePart r0 = new com.github.barteksc.pdfviewer.model.PagePart
            r5 = 0
            r9 = 0
            r10 = 0
            r2 = r0
            r3 = r12
            r4 = r13
            r6 = r14
            r7 = r15
            r8 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            java.lang.Object r2 = r1.passiveActiveLock
            monitor-enter(r2)
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r3 = r1.passiveCache     // Catch:{ all -> 0x0039 }
            com.github.barteksc.pdfviewer.model.PagePart r3 = find(r3, r0)     // Catch:{ all -> 0x0039 }
            r4 = 1
            if (r3 == 0) goto L_0x002d
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r0 = r1.passiveCache     // Catch:{ all -> 0x0039 }
            r0.remove(r3)     // Catch:{ all -> 0x0039 }
            r0 = r17
            r3.setCacheOrder(r0)     // Catch:{ all -> 0x0039 }
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r0 = r1.activeCache     // Catch:{ all -> 0x0039 }
            r0.offer(r3)     // Catch:{ all -> 0x0039 }
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
            return r4
        L_0x002d:
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r3 = r1.activeCache     // Catch:{ all -> 0x0039 }
            com.github.barteksc.pdfviewer.model.PagePart r0 = find(r3, r0)     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0036
            goto L_0x0037
        L_0x0036:
            r4 = 0
        L_0x0037:
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
            return r4
        L_0x0039:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.barteksc.pdfviewer.CacheManager.upPartIfContained(int, int, float, float, android.graphics.RectF, int):boolean");
    }

    public boolean containsThumbnail(int i, int i2, float f, float f2, RectF rectF) {
        PagePart pagePart = new PagePart(i, i2, (Bitmap) null, f, f2, rectF, true, 0);
        synchronized (this.thumbnails) {
            for (PagePart equals : this.thumbnails) {
                if (equals.equals(pagePart)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static PagePart find(PriorityQueue<PagePart> priorityQueue, PagePart pagePart) {
        Iterator<PagePart> it = priorityQueue.iterator();
        while (it.hasNext()) {
            PagePart next = it.next();
            if (next.equals(pagePart)) {
                return next;
            }
        }
        return null;
    }

    public List<PagePart> getPageParts() {
        ArrayList arrayList;
        synchronized (this.passiveActiveLock) {
            arrayList = new ArrayList(this.passiveCache);
            arrayList.addAll(this.activeCache);
        }
        return arrayList;
    }

    public List<PagePart> getThumbnails() {
        List<PagePart> list;
        synchronized (this.thumbnails) {
            list = this.thumbnails;
        }
        return list;
    }

    public void recycle() {
        synchronized (this.passiveActiveLock) {
            Iterator<PagePart> it = this.passiveCache.iterator();
            while (it.hasNext()) {
                it.next().getRenderedBitmap().recycle();
            }
            this.passiveCache.clear();
            Iterator<PagePart> it2 = this.activeCache.iterator();
            while (it2.hasNext()) {
                it2.next().getRenderedBitmap().recycle();
            }
            this.activeCache.clear();
        }
        synchronized (this.thumbnails) {
            for (PagePart renderedBitmap : this.thumbnails) {
                renderedBitmap.getRenderedBitmap().recycle();
            }
            this.thumbnails.clear();
        }
    }

    class PagePartComparator implements Comparator<PagePart> {
        PagePartComparator() {
        }

        public int compare(PagePart pagePart, PagePart pagePart2) {
            if (pagePart.getCacheOrder() == pagePart2.getCacheOrder()) {
                return 0;
            }
            return pagePart.getCacheOrder() > pagePart2.getCacheOrder() ? 1 : -1;
        }
    }
}
