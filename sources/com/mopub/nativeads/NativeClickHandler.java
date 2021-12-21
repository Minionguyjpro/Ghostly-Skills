package com.mopub.nativeads;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;

public class NativeClickHandler {
    /* access modifiers changed from: private */
    public boolean mClickInProgress;
    private final Context mContext;
    private final String mDspCreativeId;

    public NativeClickHandler(Context context) {
        this(context, (String) null);
    }

    public NativeClickHandler(Context context, String str) {
        Preconditions.checkNotNull(context);
        this.mContext = context.getApplicationContext();
        this.mDspCreativeId = str;
    }

    public void setOnClickListener(View view, final ClickInterface clickInterface) {
        if (Preconditions.NoThrow.checkNotNull(view, "Cannot set click listener on a null view") && Preconditions.NoThrow.checkNotNull(clickInterface, "Cannot set click listener with a null ClickInterface")) {
            setOnClickListener(view, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    clickInterface.handleClick(view);
                }
            });
        }
    }

    private void setOnClickListener(View view, View.OnClickListener onClickListener) {
        view.setOnClickListener(onClickListener);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                setOnClickListener(viewGroup.getChildAt(i), onClickListener);
            }
        }
    }

    public void clearOnClickListener(View view) {
        if (Preconditions.NoThrow.checkNotNull(view, "Cannot clear click listener from a null view")) {
            setOnClickListener(view, (View.OnClickListener) null);
        }
    }

    public void openClickDestinationUrl(String str, View view) {
        openClickDestinationUrl(str, view, new SpinningProgressView(this.mContext));
    }

    /* access modifiers changed from: package-private */
    public void openClickDestinationUrl(String str, final View view, final SpinningProgressView spinningProgressView) {
        if (Preconditions.NoThrow.checkNotNull(str, "Cannot open a null click destination url")) {
            Preconditions.checkNotNull(spinningProgressView);
            if (!this.mClickInProgress) {
                this.mClickInProgress = true;
                if (view != null) {
                    spinningProgressView.addToRoot(view);
                }
                UrlHandler.Builder builder = new UrlHandler.Builder();
                if (!TextUtils.isEmpty(this.mDspCreativeId)) {
                    builder.withDspCreativeId(this.mDspCreativeId);
                }
                builder.withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new UrlHandler.ResultActions() {
                    public void urlHandlingSucceeded(String str, UrlAction urlAction) {
                        removeSpinningProgressView();
                        boolean unused = NativeClickHandler.this.mClickInProgress = false;
                    }

                    public void urlHandlingFailed(String str, UrlAction urlAction) {
                        removeSpinningProgressView();
                        boolean unused = NativeClickHandler.this.mClickInProgress = false;
                    }

                    private void removeSpinningProgressView() {
                        if (view != null) {
                            spinningProgressView.removeFromRoot();
                        }
                    }
                }).build().handleUrl(this.mContext, str);
            }
        }
    }
}
