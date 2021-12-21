package com.mopub.common.privacy;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.appnext.base.b.d;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.ConsentDialogLayout;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.mobileads.MoPubErrorCode;

public class ConsentDialogActivity extends Activity {
    private static final int CLOSE_BUTTON_DELAY_MS = 10000;
    private static final String KEY_HTML_PAGE = "html-page-content";
    Handler mCloseButtonHandler;
    ConsentStatus mConsentStatus;
    private Runnable mEnableCloseButtonRunnable;
    ConsentDialogLayout mView;

    static void start(Context context, String str) {
        Preconditions.checkNotNull(context);
        if (TextUtils.isEmpty(str)) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "ConsentDialogActivity htmlData can't be empty string.");
            MoPubLog.log(MoPubLog.ConsentLogEvent.SHOW_FAILED, Integer.valueOf(MoPubErrorCode.INTERNAL_ERROR.getIntCode()), MoPubErrorCode.INTERNAL_ERROR);
            return;
        }
        try {
            Intents.startActivity(context, createIntent(context, str));
        } catch (ActivityNotFoundException | IntentNotResolvableException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "ConsentDialogActivity not found - did you declare it in AndroidManifest.xml?");
            MoPubLog.log(MoPubLog.ConsentLogEvent.SHOW_FAILED, Integer.valueOf(MoPubErrorCode.INTERNAL_ERROR.getIntCode()), MoPubErrorCode.INTERNAL_ERROR);
        }
    }

    static Intent createIntent(Context context, String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_HTML_PAGE, str);
        return Intents.getStartActivityIntent(context, ConsentDialogActivity.class, bundle);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra(KEY_HTML_PAGE);
        if (TextUtils.isEmpty(stringExtra)) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Web page for ConsentDialogActivity is empty");
            MoPubLog.log(MoPubLog.ConsentLogEvent.SHOW_FAILED, Integer.valueOf(MoPubErrorCode.INTERNAL_ERROR.getIntCode()), MoPubErrorCode.INTERNAL_ERROR);
            finish();
            return;
        }
        requestWindowFeature(1);
        getWindow().addFlags(d.fb);
        try {
            ConsentDialogLayout consentDialogLayout = new ConsentDialogLayout(this);
            this.mView = consentDialogLayout;
            consentDialogLayout.setConsentClickListener(new ConsentDialogLayout.ConsentListener() {
                public void onConsentClick(ConsentStatus consentStatus) {
                    ConsentDialogActivity.this.saveConsentStatus(consentStatus);
                    ConsentDialogActivity.this.setCloseButtonVisibility(false);
                }

                public void onCloseClick() {
                    ConsentDialogActivity.this.finish();
                }
            });
            this.mEnableCloseButtonRunnable = new Runnable() {
                public void run() {
                    ConsentDialogActivity.this.setCloseButtonVisibility(true);
                }
            };
            setContentView(this.mView);
            this.mView.startLoading(stringExtra, new ConsentDialogLayout.PageLoadListener() {
                public void onLoadProgress(int i) {
                    int i2 = ConsentDialogLayout.FINISHED_LOADING;
                }
            });
        } catch (RuntimeException e) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM_WITH_THROWABLE, "Unable to create WebView", e);
            MoPubLog.log(MoPubLog.ConsentLogEvent.SHOW_FAILED, Integer.valueOf(MoPubErrorCode.INTERNAL_ERROR.getIntCode()), MoPubErrorCode.INTERNAL_ERROR);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Handler handler = new Handler();
        this.mCloseButtonHandler = handler;
        handler.postDelayed(this.mEnableCloseButtonRunnable, 10000);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MoPubLog.log(MoPubLog.ConsentLogEvent.SHOW_SUCCESS, new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        setCloseButtonVisibility(true);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ConsentStatus consentStatus;
        PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
        if (!(personalInformationManager == null || (consentStatus = this.mConsentStatus) == null)) {
            personalInformationManager.changeConsentStateFromDialog(consentStatus);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: package-private */
    public void setCloseButtonVisibility(boolean z) {
        Handler handler = this.mCloseButtonHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mEnableCloseButtonRunnable);
        }
        ConsentDialogLayout consentDialogLayout = this.mView;
        if (consentDialogLayout != null) {
            consentDialogLayout.setCloseVisible(z);
        }
    }

    /* access modifiers changed from: private */
    public void saveConsentStatus(ConsentStatus consentStatus) {
        Preconditions.checkNotNull(consentStatus);
        this.mConsentStatus = consentStatus;
    }
}
