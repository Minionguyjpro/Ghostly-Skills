package com.appnext.banners;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.appnext.core.AppnextAdCreativeType;
import com.appnext.core.AppnextError;
import com.appnext.core.callbacks.OnECPMLoaded;
import com.appnext.core.p;
import com.startapp.android.publish.common.model.AdPreferences;

public abstract class BaseBannerView extends ViewGroup {
    protected e bannerAdapter;
    private BannerListener bannerListener;
    private String language;
    ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {
        public final void onScrollChanged() {
            BaseBannerView.this.impression();
            BaseBannerView.this.onScrollChanged();
        }
    };

    /* access modifiers changed from: protected */
    public abstract e getBannerAdapter();

    public BaseBannerView(Context context) {
        super(context);
        if (context != null) {
            parseAttributeSet((AttributeSet) null);
            return;
        }
        throw new IllegalArgumentException("The context cannot be null.");
    }

    public BaseBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        parseAttributeSet(attributeSet);
    }

    public BaseBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        parseAttributeSet(attributeSet);
    }

    public BaseBannerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        parseAttributeSet(attributeSet);
    }

    private void parseAttributeSet(AttributeSet attributeSet) {
        if (!isInEditMode()) {
            getBannerAdapter().init(this);
            d.S().r("tid", "301");
            d.S().a(getContext(), (p.a) null);
            getViewTreeObserver().addOnScrollChangedListener(this.onScrollChangedListener);
            if (attributeSet != null) {
                TypedArray obtainAttributes = getResources().obtainAttributes(attributeSet, R.styleable.BannersAttrs);
                String string = obtainAttributes.getString(R.styleable.BannersAttrs_bannerSize);
                if (string != null) {
                    char c = 65535;
                    int hashCode = string.hashCode();
                    if (hashCode != -1966536496) {
                        if (hashCode != -96588539) {
                            if (hashCode == 1951953708 && string.equals(AdPreferences.TYPE_BANNER)) {
                                c = 0;
                            }
                        } else if (string.equals("MEDIUM_RECTANGLE")) {
                            c = 2;
                        }
                    } else if (string.equals("LARGE_BANNER")) {
                        c = 1;
                    }
                    if (c == 0) {
                        setBannerSize(BannerSize.BANNER);
                    } else if (c == 1) {
                        setBannerSize(BannerSize.LARGE_BANNER);
                    } else if (c == 2) {
                        setBannerSize(BannerSize.MEDIUM_RECTANGLE);
                    } else {
                        throw new IllegalArgumentException("Wrong banner size " + string);
                    }
                }
                String string2 = obtainAttributes.getString(R.styleable.BannersAttrs_placementId);
                if (string2 != null) {
                    setPlacementId(string2);
                }
                obtainAttributes.recycle();
                getBannerAdapter().setBannerListener(new BannerListener() {
                    public final void onAdLoaded(String str, AppnextAdCreativeType appnextAdCreativeType) {
                        if (BaseBannerView.this.getBannerListener() != null) {
                            BaseBannerView.this.getBannerListener().onAdLoaded(str, appnextAdCreativeType);
                        }
                        BaseBannerView.this.impression();
                    }

                    public final void onAdClicked() {
                        if (BaseBannerView.this.getBannerListener() != null) {
                            BaseBannerView.this.getBannerListener().onAdClicked();
                        }
                    }

                    public final void onError(AppnextError appnextError) {
                        if (BaseBannerView.this.getBannerListener() != null) {
                            BaseBannerView.this.getBannerListener().onError(appnextError);
                        }
                    }

                    public final void adImpression() {
                        if (BaseBannerView.this.getBannerListener() != null) {
                            BaseBannerView.this.getBannerListener().adImpression();
                        }
                    }
                });
                getBannerAdapter().parseAttributeSet(attributeSet);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = ((i3 - i) - measuredWidth) / 2;
            int i6 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            measureChild(childAt, i, i2);
            i4 = childAt.getMeasuredWidth();
            i3 = childAt.getMeasuredHeight();
        } else if (getBannerAdapter().getBannerSize() != null) {
            Context context = getContext();
            int width = (int) (((float) getBannerAdapter().getBannerSize().getWidth()) * context.getResources().getDisplayMetrics().scaledDensity);
            i3 = (int) (((float) getBannerAdapter().getBannerSize().getHeight()) * context.getResources().getDisplayMetrics().scaledDensity);
            i4 = width;
        } else {
            i3 = 0;
        }
        setMeasuredDimension(View.resolveSize(Math.max(i4, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }

    public void setPlacementId(String str) {
        getBannerAdapter().setPlacementId(str);
    }

    public void setBannerSize(BannerSize bannerSize) {
        getBannerAdapter().setBannerSize(bannerSize);
    }

    public void setBannerListener(BannerListener bannerListener2) {
        getBannerAdapter().setBannerListener(bannerListener2);
    }

    public void loadAd(BannerAdRequest bannerAdRequest) {
        getBannerAdapter().loadAd(bannerAdRequest);
    }

    /* access modifiers changed from: protected */
    public void getECPM(BannerAdRequest bannerAdRequest, OnECPMLoaded onECPMLoaded) {
        getBannerAdapter().getECPM(bannerAdRequest, onECPMLoaded);
    }

    /* access modifiers changed from: private */
    public void impression() {
        getBannerAdapter().impression();
    }

    /* access modifiers changed from: private */
    public void onScrollChanged() {
        getBannerAdapter().onScrollChanged(getVisiblePercent(this));
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        try {
            getBannerAdapter().onWindowVisibilityChanged(i);
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        impression();
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        impression();
    }

    private boolean isViewPartiallyVisible(View view) {
        try {
            if (getParent() == null) {
                return false;
            }
            Rect rect = new Rect();
            ((ViewGroup) getParent()).getHitRect(rect);
            return view.getLocalVisibleRect(rect);
        } catch (Throwable unused) {
            return false;
        }
    }

    public int getVisiblePercent(View view) {
        if (!isViewPartiallyVisible(this) || getWindowVisibility() == 8 || getWindowVisibility() == 4) {
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

    /* access modifiers changed from: package-private */
    public String getPlacementId() {
        return getBannerAdapter().getPlacementId();
    }

    /* access modifiers changed from: package-private */
    public BannerListener getBannerListener() {
        return this.bannerListener;
    }

    public void destroy() {
        getBannerAdapter().destroy();
        this.bannerAdapter = null;
        try {
            getViewTreeObserver().removeOnScrollChangedListener(this.onScrollChangedListener);
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        destroy();
    }

    private void play() {
        try {
            getBannerAdapter().play();
        } catch (Throwable unused) {
        }
    }

    private void pause() {
        try {
            getBannerAdapter().pause();
        } catch (Throwable unused) {
        }
    }

    public boolean isClickEnabled() {
        return getBannerAdapter().isClickEnabled();
    }

    public void setClickEnabled(boolean z) {
        getBannerAdapter().setClickEnabled(z);
    }

    public void setParams(String str, String str2) {
        d.S().s(str, str2);
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
        this.bannerAdapter.setLanguage(str);
    }
}
