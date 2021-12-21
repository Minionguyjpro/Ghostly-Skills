package com.startapp.android.publish.ads.banner;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.mobileads.VastIconXmlManager;
import com.mopub.mobileads.resource.DrawableConstants;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.a.b;
import com.startapp.android.publish.adsCommon.a.f;
import com.startapp.android.publish.adsCommon.o;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.Constants;
import com.startapp.common.a.c;
import com.startapp.common.a.g;
import com.startapp.common.a.i;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: StartAppSDK */
public abstract class BannerBase extends RelativeLayout {
    private static final String TAG = "BannerLayout";
    protected AdPreferences adPreferences;
    protected f adRulesResult;
    protected String adTag;
    private boolean attachedToWindow;
    private boolean clicked;
    /* access modifiers changed from: protected */
    public boolean drawn;
    private String error;
    private boolean firstLoad;
    protected int innerBanner3dId;
    protected int innerBannerStandardId;
    protected int offset;
    private boolean shouldReloadBanner;
    private a task;
    private Timer timer;
    private o viewabilityChecker;
    /* access modifiers changed from: private */
    public Handler visibilityHandler;

    /* access modifiers changed from: protected */
    public abstract int getBannerId();

    /* access modifiers changed from: protected */
    public abstract String getBannerName();

    /* access modifiers changed from: protected */
    public abstract int getHeightInDp();

    /* access modifiers changed from: protected */
    public abstract int getOffset();

    /* access modifiers changed from: protected */
    public abstract int getRefreshRate();

    /* access modifiers changed from: protected */
    public abstract int getWidthInDp();

    /* access modifiers changed from: protected */
    public abstract void initRuntime();

    /* access modifiers changed from: protected */
    public abstract void reload();

    public abstract void setAdTag(String str);

    /* access modifiers changed from: protected */
    public abstract void setBannerId(int i);

    /* compiled from: StartAppSDK */
    class a extends TimerTask {
        a() {
        }

        public void run() {
            BannerBase.this.post(new Runnable() {
                public void run() {
                    if (BannerBase.this.isShown() || (BannerBase.this.adRulesResult != null && !BannerBase.this.adRulesResult.a())) {
                        g.a(BannerBase.TAG, 3, "REFRESH");
                        BannerBase.this.load();
                    }
                }
            });
        }
    }

    public BannerBase(Context context) {
        super(context);
        this.attachedToWindow = false;
        this.offset = 0;
        this.firstLoad = true;
        this.drawn = false;
        int nextInt = new Random().nextInt(100000) + 159868227;
        this.innerBanner3dId = nextInt;
        this.innerBannerStandardId = nextInt + 1;
        this.adTag = null;
        this.visibilityHandler = new Handler();
        this.viewabilityChecker = new o();
        this.clicked = false;
        this.shouldReloadBanner = false;
        this.timer = new Timer();
        this.task = new a();
    }

    public BannerBase(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.attachedToWindow = false;
        this.offset = 0;
        this.firstLoad = true;
        this.drawn = false;
        int nextInt = new Random().nextInt(100000) + 159868227;
        this.innerBanner3dId = nextInt;
        this.innerBannerStandardId = nextInt + 1;
        this.adTag = null;
        this.visibilityHandler = new Handler();
        this.viewabilityChecker = new o();
        this.clicked = false;
        this.shouldReloadBanner = false;
        this.timer = new Timer();
        this.task = new a();
        setBannerAttrs(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void init() {
        if (!isInEditMode()) {
            initRuntime();
        } else {
            initDebug();
        }
    }

    private void initDebug() {
        setMinimumWidth(h.a(getContext(), getWidthInDp()));
        setMinimumHeight(h.a(getContext(), getHeightInDp()));
        setBackgroundColor(Color.rgb(169, 169, 169));
        TextView textView = new TextView(getContext());
        textView.setText(getBannerName());
        textView.setTextColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(textView, layoutParams);
    }

    /* access modifiers changed from: protected */
    public String getAdTag() {
        return this.adTag;
    }

    /* access modifiers changed from: protected */
    public void loadBanner() {
        scheduleReloadTask();
        load();
    }

    /* access modifiers changed from: protected */
    public void load() {
        clearVisibilityHandler();
        if (this.adRulesResult == null || com.startapp.android.publish.adsCommon.a.g.a().b().a()) {
            f a2 = com.startapp.android.publish.adsCommon.a.g.a().b().a(AdPreferences.Placement.INAPP_BANNER, getAdTag());
            this.adRulesResult = a2;
            if (a2.a()) {
                reload();
                return;
            }
            setVisibility(4);
            if (Constants.a().booleanValue()) {
                i.a().a(getContext(), this.adRulesResult.b());
            }
        } else if (this.adRulesResult.a()) {
            reload();
        }
    }

    /* access modifiers changed from: private */
    public void clearVisibilityHandler() {
        try {
            this.visibilityHandler.removeCallbacksAndMessages((Object) null);
        } catch (Exception e) {
            g.a(TAG, 6, "BannerBase.reload - removeCallbacksAndMessages failed " + e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldSendImpression(com.startapp.android.publish.adsCommon.i iVar) {
        return iVar != null && !iVar.c();
    }

    /* access modifiers changed from: protected */
    public int getMinViewabilityPercentage() {
        return c.a().b().q();
    }

    /* access modifiers changed from: protected */
    public boolean isVisible() {
        return this.viewabilityChecker.a(this, getMinViewabilityPercentage());
    }

    /* access modifiers changed from: protected */
    public void startVisibilityRunnable(final com.startapp.android.publish.adsCommon.i iVar) {
        if (shouldSendImpression(iVar)) {
            g.a(TAG, 3, "BannerBase.startVisibilityRunnable - run visibility check");
            new Runnable() {
                private boolean c = true;

                public void run() {
                    try {
                        if (BannerBase.this.shouldSendImpression(iVar)) {
                            boolean isVisible = BannerBase.this.isVisible();
                            if (isVisible && this.c) {
                                this.c = false;
                                iVar.a();
                            } else if (!isVisible && !this.c) {
                                this.c = true;
                                iVar.b();
                            }
                            BannerBase.this.visibilityHandler.postDelayed(this, 100);
                            return;
                        }
                        BannerBase.this.clearVisibilityHandler();
                    } catch (Exception e) {
                        g.a(BannerBase.TAG, 6, "BannerBase.startVisibilityRunnable.run - runnable error " + e.getMessage());
                        BannerBase.this.clearVisibilityHandler();
                    }
                }
            }.run();
        }
    }

    private void setBannerAttrs(Context context, AttributeSet attributeSet) {
        setAdTag(new b(context, attributeSet).a());
    }

    private void scheduleReloadTask() {
        if (this.attachedToWindow && !isInEditMode()) {
            a aVar = this.task;
            if (aVar != null) {
                aVar.cancel();
            }
            Timer timer2 = this.timer;
            if (timer2 != null) {
                timer2.cancel();
            }
            this.task = new a();
            Timer timer3 = new Timer();
            this.timer = timer3;
            timer3.scheduleAtFixedRate(this.task, (long) getRefreshRate(), (long) getRefreshRate());
        }
    }

    private void cancelReloadTask() {
        if (!isInEditMode()) {
            a aVar = this.task;
            if (aVar != null) {
                aVar.cancel();
            }
            Timer timer2 = this.timer;
            if (timer2 != null) {
                timer2.cancel();
            }
            this.task = null;
            this.timer = null;
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        if (isClicked()) {
            setClicked(false);
            this.shouldReloadBanner = true;
        }
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putInt("bannerId", getBannerId());
        bundle.putParcelable("upperState", onSaveInstanceState);
        bundle.putSerializable("adRulesResult", this.adRulesResult);
        bundle.putSerializable("adPreferences", this.adPreferences);
        bundle.putInt(VastIconXmlManager.OFFSET, this.offset);
        bundle.putBoolean("firstLoad", this.firstLoad);
        bundle.putBoolean("shouldReloadBanner", this.shouldReloadBanner);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        setBannerId(bundle.getInt("bannerId"));
        this.adRulesResult = (f) bundle.getSerializable("adRulesResult");
        this.adPreferences = (AdPreferences) bundle.getSerializable("adPreferences");
        this.offset = bundle.getInt(VastIconXmlManager.OFFSET);
        this.firstLoad = bundle.getBoolean("firstLoad");
        this.shouldReloadBanner = bundle.getBoolean("shouldReloadBanner");
        super.onRestoreInstanceState(bundle.getParcelable("upperState"));
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        g.a(TAG, 3, "onAttachedToWindow");
        this.attachedToWindow = true;
        scheduleReloadTask();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        g.a(TAG, 3, "onDetachedFromWindow");
        this.attachedToWindow = false;
        cancelReloadTask();
        clearVisibilityHandler();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        g.a(TAG, 3, "onWindowFocusChanged");
        if (z) {
            if (this.shouldReloadBanner) {
                this.shouldReloadBanner = false;
                load();
            }
            this.attachedToWindow = true;
            scheduleReloadTask();
            return;
        }
        this.attachedToWindow = false;
        cancelReloadTask();
    }

    public boolean isFirstLoad() {
        return this.firstLoad;
    }

    public void setFirstLoad(boolean z) {
        this.firstLoad = z;
    }

    /* access modifiers changed from: protected */
    public void addDisplayEventOnLoad() {
        if (isFirstLoad() || com.startapp.android.publish.adsCommon.a.g.a().b().a()) {
            setFirstLoad(false);
            b.a().a(new com.startapp.android.publish.adsCommon.a.a(AdPreferences.Placement.INAPP_BANNER, getAdTag()));
        }
    }

    /* access modifiers changed from: protected */
    public void setHardwareAcceleration(AdPreferences adPreferences2) {
        com.startapp.android.publish.adsCommon.Utils.i.a(adPreferences2, "hardwareAccelerated", c.a((View) this, this.attachedToWindow));
    }

    public boolean isClicked() {
        return this.clicked;
    }

    public void setClicked(boolean z) {
        this.clicked = z;
    }

    public void setErrorMessage(String str) {
        this.error = str;
    }

    public String getErrorMessage() {
        return this.error;
    }
}
