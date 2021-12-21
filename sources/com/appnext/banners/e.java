package com.appnext.banners;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.appnext.core.callbacks.OnECPMLoaded;

public abstract class e {
    private BannerListener bannerListener;
    private BannerSize bannerSize;
    protected Context context;
    private String language;
    private String placementId;
    protected ViewGroup rootView;

    public abstract void click();

    public abstract void getECPM(BannerAdRequest bannerAdRequest, OnECPMLoaded onECPMLoaded);

    public abstract void impression();

    public boolean isClickEnabled() {
        return false;
    }

    public abstract void loadAd(BannerAdRequest bannerAdRequest);

    public void onScrollChanged(int i) {
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
    }

    public void parseAttributeSet(AttributeSet attributeSet) {
    }

    public void pause() {
    }

    public void play() {
    }

    public void setClickEnabled(boolean z) {
    }

    public void init(ViewGroup viewGroup) {
        this.rootView = viewGroup;
        this.context = viewGroup.getContext();
    }

    public void setPlacementId(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The placement id cannot be null.");
        } else if (this.placementId == null) {
            this.placementId = str;
        } else {
            throw new IllegalStateException("The placement id can only be set once.");
        }
    }

    public String getPlacementId() {
        return this.placementId;
    }

    public void setBannerSize(BannerSize bannerSize2) {
        if (bannerSize2 == null) {
            throw new IllegalArgumentException("The placement id cannot be null.");
        } else if (this.bannerSize == null) {
            this.bannerSize = bannerSize2;
        } else {
            throw new IllegalStateException("The banner size can only be set once.");
        }
    }

    public BannerSize getBannerSize() {
        return this.bannerSize;
    }

    public void setBannerListener(BannerListener bannerListener2) {
        this.bannerListener = bannerListener2;
    }

    public BannerListener getBannerListener() {
        return this.bannerListener;
    }

    public void destroy() {
        this.context = null;
    }

    private boolean isViewPartiallyVisible(View view) {
        try {
            if (this.rootView.getParent() == null) {
                return false;
            }
            Rect rect = new Rect();
            ((ViewGroup) this.rootView.getParent()).getHitRect(rect);
            return view.getLocalVisibleRect(rect);
        } catch (Throwable unused) {
            return false;
        }
    }

    public int getVisiblePercent(View view) {
        if (!isViewPartiallyVisible(this.rootView) || this.rootView.getWindowVisibility() == 8 || this.rootView.getWindowVisibility() == 4) {
            return 0;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        double width = (double) (rect.width() * rect.height());
        double width2 = (double) (view.getWidth() * view.getHeight());
        Double.isNaN(width);
        Double.isNaN(width2);
        return (int) ((width * 100.0d) / width2);
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    /* access modifiers changed from: protected */
    public void openLink(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }
}
