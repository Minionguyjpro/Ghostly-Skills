package com.mopub.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.integralads.avid.library.mopub.BuildConfig;
import com.mopub.common.MoPub;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import com.mopub.network.TrackingRequest;
import java.net.URISyntaxException;
import java.util.List;

public enum UrlAction {
    HANDLE_MOPUB_SCHEME(false) {
        public boolean shouldTryHandlingUrl(Uri uri) {
            return BuildConfig.SDK_NAME.equalsIgnoreCase(uri.getScheme());
        }

        /* access modifiers changed from: protected */
        public void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException {
            String host = uri.getHost();
            UrlHandler.MoPubSchemeListener moPubSchemeListener = urlHandler.getMoPubSchemeListener();
            if ("finishLoad".equalsIgnoreCase(host)) {
                moPubSchemeListener.onFinishLoad();
            } else if ("close".equalsIgnoreCase(host)) {
                moPubSchemeListener.onClose();
            } else if ("failLoad".equalsIgnoreCase(host)) {
                moPubSchemeListener.onFailLoad();
            } else if ("crash".equals(host)) {
                moPubSchemeListener.onCrash();
            } else {
                throw new IntentNotResolvableException("Could not handle MoPub Scheme url: " + uri);
            }
        }
    },
    IGNORE_ABOUT_SCHEME(false) {
        public boolean shouldTryHandlingUrl(Uri uri) {
            return "about".equalsIgnoreCase(uri.getScheme());
        }

        /* access modifiers changed from: protected */
        public void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Link to about page ignored.");
        }
    },
    HANDLE_PHONE_SCHEME(true) {
        public boolean shouldTryHandlingUrl(Uri uri) {
            String scheme = uri.getScheme();
            return "tel".equalsIgnoreCase(scheme) || "voicemail".equalsIgnoreCase(scheme) || "sms".equalsIgnoreCase(scheme) || "mailto".equalsIgnoreCase(scheme) || "geo".equalsIgnoreCase(scheme) || "google.streetview".equalsIgnoreCase(scheme);
        }

        /* access modifiers changed from: protected */
        public void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException {
            Intents.launchActionViewIntent(context, uri, "Could not handle intent with URI: " + uri + "\n\tIs this intent supported on your phone?");
        }
    },
    OPEN_NATIVE_BROWSER(true) {
        public boolean shouldTryHandlingUrl(Uri uri) {
            String scheme = uri.getScheme();
            if (Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme)) {
                return MoPub.getBrowserAgent() == MoPub.BrowserAgent.NATIVE;
            }
            return "mopubnativebrowser".equalsIgnoreCase(scheme);
        }

        /* access modifiers changed from: protected */
        public void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException {
            String str2 = "Unable to load mopub native browser url: " + uri;
            try {
                Intents.launchIntentForUserClick(context, Intents.intentForNativeBrowserScheme(uri), str2);
            } catch (UrlParseException e) {
                throw new IntentNotResolvableException(str2 + "\n\t" + e.getMessage());
            }
        }
    },
    OPEN_APP_MARKET(true) {
        public boolean shouldTryHandlingUrl(Uri uri) {
            String scheme = uri.getScheme();
            String host = uri.getHost();
            return "play.google.com".equalsIgnoreCase(host) || "market.android.com".equalsIgnoreCase(host) || "market".equalsIgnoreCase(scheme) || uri.toString().toLowerCase().startsWith("play.google.com/") || uri.toString().toLowerCase().startsWith("market.android.com/");
        }

        /* access modifiers changed from: protected */
        public void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException {
            Intents.launchApplicationUrl(context, uri);
        }
    },
    OPEN_IN_APP_BROWSER(true) {
        public boolean shouldTryHandlingUrl(Uri uri) {
            String scheme = uri.getScheme();
            return Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme);
        }

        /* access modifiers changed from: protected */
        public void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException {
            if (!urlHandler.shouldSkipShowMoPubBrowser()) {
                Intents.showMoPubBrowserForUrl(context, uri, str);
            }
        }
    },
    HANDLE_SHARE_TWEET(true) {
        public boolean shouldTryHandlingUrl(Uri uri) {
            Preconditions.checkNotNull(uri);
            return "mopubshare".equalsIgnoreCase(uri.getScheme()) && "tweet".equalsIgnoreCase(uri.getHost());
        }

        /* access modifiers changed from: protected */
        public void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(uri);
            String str2 = "Could not handle share tweet intent with URI " + uri;
            try {
                Intents.launchIntentForUserClick(context, Intent.createChooser(Intents.intentForShareTweet(uri), "Share via"), str2);
            } catch (UrlParseException e) {
                throw new IntentNotResolvableException(str2 + "\n\t" + e.getMessage());
            }
        }
    },
    FOLLOW_DEEP_LINK_WITH_FALLBACK(true) {
        public boolean shouldTryHandlingUrl(Uri uri) {
            return "deeplink+".equalsIgnoreCase(uri.getScheme());
        }

        /* access modifiers changed from: protected */
        public void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException {
            if ("navigate".equalsIgnoreCase(uri.getHost())) {
                try {
                    String queryParameter = uri.getQueryParameter("primaryUrl");
                    List<String> queryParameters = uri.getQueryParameters("primaryTrackingUrl");
                    String queryParameter2 = uri.getQueryParameter("fallbackUrl");
                    List<String> queryParameters2 = uri.getQueryParameters("fallbackTrackingUrl");
                    if (queryParameter != null) {
                        Uri parse = Uri.parse(queryParameter);
                        if (!shouldTryHandlingUrl(parse)) {
                            try {
                                Intents.launchApplicationUrl(context, parse);
                                TrackingRequest.makeTrackingHttpRequest((Iterable<String>) queryParameters, context);
                            } catch (IntentNotResolvableException unused) {
                                if (queryParameter2 == null) {
                                    throw new IntentNotResolvableException("Unable to handle 'primaryUrl' for Deeplink+ and 'fallbackUrl' was missing.");
                                } else if (!shouldTryHandlingUrl(Uri.parse(queryParameter2))) {
                                    urlHandler.handleUrl(context, queryParameter2, true, queryParameters2);
                                } else {
                                    throw new IntentNotResolvableException("Deeplink+ URL had another Deeplink+ URL as the 'fallbackUrl'.");
                                }
                            }
                        } else {
                            throw new IntentNotResolvableException("Deeplink+ had another Deeplink+ as the 'primaryUrl'.");
                        }
                    } else {
                        throw new IntentNotResolvableException("Deeplink+ did not have 'primaryUrl' query param.");
                    }
                } catch (UnsupportedOperationException unused2) {
                    throw new IntentNotResolvableException("Deeplink+ URL was not a hierarchical URI.");
                }
            } else {
                throw new IntentNotResolvableException("Deeplink+ URL did not have 'navigate' as the host.");
            }
        }
    },
    FOLLOW_DEEP_LINK(true) {
        public boolean shouldTryHandlingUrl(Uri uri) {
            return !TextUtils.isEmpty(uri.getScheme());
        }

        /* access modifiers changed from: protected */
        public void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException {
            if (Constants.INTENT_SCHEME.equalsIgnoreCase(uri.getScheme())) {
                try {
                    Intents.launchApplicationIntent(context, Intent.parseUri(uri.toString(), 1));
                } catch (URISyntaxException unused) {
                    throw new IntentNotResolvableException("Intent uri had invalid syntax: " + uri.toString());
                }
            } else {
                Intents.launchApplicationUrl(context, uri);
            }
        }
    },
    NOOP(false) {
        /* access modifiers changed from: protected */
        public void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException {
        }

        public boolean shouldTryHandlingUrl(Uri uri) {
            return false;
        }
    };
    
    private final boolean mRequiresUserInteraction;

    /* access modifiers changed from: protected */
    public abstract void performAction(Context context, Uri uri, UrlHandler urlHandler, String str) throws IntentNotResolvableException;

    public abstract boolean shouldTryHandlingUrl(Uri uri);

    public void handleUrl(UrlHandler urlHandler, Context context, Uri uri, boolean z, String str) throws IntentNotResolvableException {
        MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
        MoPubLog.log(sdkLogEvent, "Ad event URL: " + uri);
        if (!this.mRequiresUserInteraction || z) {
            performAction(context, uri, urlHandler, str);
            return;
        }
        throw new IntentNotResolvableException("Attempted to handle action without user interaction.");
    }

    private UrlAction(boolean z) {
        this.mRequiresUserInteraction = z;
    }
}
