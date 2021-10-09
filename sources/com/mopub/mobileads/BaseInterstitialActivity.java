package com.mopub.mobileads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.DataKeys;
import com.mopub.common.util.Utils;

abstract class BaseInterstitialActivity extends Activity {
    protected AdReport mAdReport;
    private Long mBroadcastIdentifier;
    private CloseableLayout mCloseableLayout;

    public abstract View getAdView();

    BaseInterstitialActivity() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.mBroadcastIdentifier = getBroadcastIdentifierFromIntent(intent);
        this.mAdReport = getAdReportFromIntent(intent);
        View adView = getAdView();
        this.mCloseableLayout = new CloseableLayout(this);
        this.mCloseableLayout.setBackgroundColor(getResources().getColor(17170444));
        this.mCloseableLayout.setOnCloseListener(new CloseableLayout.OnCloseListener() {
            public void onClose() {
                BaseInterstitialActivity.this.finish();
            }
        });
        this.mCloseableLayout.addView(adView, new FrameLayout.LayoutParams(-1, -1));
        setContentView(this.mCloseableLayout);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Utils.hideNavigationBar(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        CloseableLayout closeableLayout = this.mCloseableLayout;
        if (closeableLayout != null) {
            closeableLayout.removeAllViews();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public CloseableLayout getCloseableLayout() {
        return this.mCloseableLayout;
    }

    /* access modifiers changed from: package-private */
    public Long getBroadcastIdentifier() {
        return this.mBroadcastIdentifier;
    }

    /* access modifiers changed from: package-private */
    public String getResponseString() {
        return getResponseString(this.mAdReport);
    }

    /* access modifiers changed from: protected */
    public void showInterstitialCloseButton() {
        CloseableLayout closeableLayout = this.mCloseableLayout;
        if (closeableLayout != null) {
            closeableLayout.setCloseVisible(true);
        }
    }

    /* access modifiers changed from: protected */
    public void hideInterstitialCloseButton() {
        CloseableLayout closeableLayout = this.mCloseableLayout;
        if (closeableLayout != null) {
            closeableLayout.setCloseVisible(false);
        }
    }

    protected static Long getBroadcastIdentifierFromIntent(Intent intent) {
        if (intent.hasExtra(DataKeys.BROADCAST_IDENTIFIER_KEY)) {
            return Long.valueOf(intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1));
        }
        return null;
    }

    protected static AdReport getAdReportFromIntent(Intent intent) {
        try {
            return (AdReport) intent.getSerializableExtra(DataKeys.AD_REPORT_KEY);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    static String getResponseString(AdReport adReport) {
        if (adReport != null) {
            return adReport.getResponseString();
        }
        return null;
    }
}
