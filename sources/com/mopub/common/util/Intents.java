package com.mopub.common.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.mopub.common.Constants;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.logging.MoPubLog;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Intents {
    private static final Map<String, String> STORE_SCHEME_TO_URL_MAP;

    @Deprecated
    public static boolean canHandleApplicationUrl(Context context, Uri uri) {
        return false;
    }

    @Deprecated
    public static boolean canHandleApplicationUrl(Context context, Uri uri, boolean z) {
        return false;
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("market", "https://play.google.com/store/apps/details?%s");
        hashMap.put("amzn", "http://www.amazon.com/gp/mas/dl/android?%s");
        STORE_SCHEME_TO_URL_MAP = Collections.unmodifiableMap(hashMap);
    }

    private Intents() {
    }

    public static void startActivity(Context context, Intent intent) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intent);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            throw new IntentNotResolvableException((Throwable) e);
        }
    }

    public static Intent getStartActivityIntent(Context context, Class cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }

    public static boolean deviceCanHandleIntent(Context context, Intent intent) {
        try {
            return !context.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public static Intent intentForNativeBrowserScheme(Uri uri) throws UrlParseException {
        Preconditions.checkNotNull(uri);
        if (!UrlAction.OPEN_NATIVE_BROWSER.shouldTryHandlingUrl(uri)) {
            String str = MoPub.getBrowserAgent() == MoPub.BrowserAgent.NATIVE ? "mopubnativebrowser://, http://, or https://" : "mopubnativebrowser://";
            throw new UrlParseException("URI does not have " + str + " scheme.");
        } else if ("mopubnativebrowser".equalsIgnoreCase(uri.getScheme())) {
            return new Intent("android.intent.action.VIEW", parseMoPubNativeBrowserUri(uri));
        } else {
            if (MoPub.getBrowserAgent() == MoPub.BrowserAgent.NATIVE) {
                return new Intent("android.intent.action.VIEW", uri);
            }
            throw new UrlParseException("Invalid URI: " + uri.toString());
        }
    }

    private static Uri parseMoPubNativeBrowserUri(Uri uri) throws UrlParseException {
        Preconditions.checkNotNull(uri);
        if ("navigate".equals(uri.getHost())) {
            try {
                String queryParameter = uri.getQueryParameter("url");
                if (queryParameter != null) {
                    return Uri.parse(queryParameter);
                }
                throw new UrlParseException("URL missing 'url' query parameter.");
            } catch (UnsupportedOperationException unused) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "Could not handle url: " + uri);
                throw new UrlParseException("Passed-in URL did not create a hierarchical URI.");
            }
        } else {
            throw new UrlParseException("URL missing 'navigate' host parameter.");
        }
    }

    public static Intent intentForShareTweet(Uri uri) throws UrlParseException {
        if (UrlAction.HANDLE_SHARE_TWEET.shouldTryHandlingUrl(uri)) {
            try {
                String queryParameter = uri.getQueryParameter("screen_name");
                String queryParameter2 = uri.getQueryParameter("tweet_id");
                if (TextUtils.isEmpty(queryParameter)) {
                    throw new UrlParseException("URL missing non-empty 'screen_name' query parameter.");
                } else if (!TextUtils.isEmpty(queryParameter2)) {
                    String format = String.format("Check out @%s's Tweet: %s", new Object[]{queryParameter, String.format("https://twitter.com/%s/status/%s", new Object[]{queryParameter, queryParameter2})});
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", format);
                    intent.putExtra("android.intent.extra.TEXT", format);
                    return intent;
                } else {
                    throw new UrlParseException("URL missing non-empty 'tweet_id' query parameter.");
                }
            } catch (UnsupportedOperationException unused) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "Could not handle url: " + uri);
                throw new UrlParseException("Passed-in URL did not create a hierarchical URI.");
            }
        } else {
            throw new UrlParseException("URL does not have mopubshare://tweet? format.");
        }
    }

    public static void showMoPubBrowserForUrl(Context context, Uri uri, String str) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
        MoPubLog.log(sdkLogEvent, "Final URI to show in browser: " + uri);
        Bundle bundle = new Bundle();
        bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, uri.toString());
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, str);
        }
        Intent startActivityIntent = getStartActivityIntent(context, MoPubBrowser.class, bundle);
        launchIntentForUserClick(context, startActivityIntent, "Could not show MoPubBrowser for url: " + uri + "\n\tPerhaps you forgot to declare com.mopub.common.MoPubBrowser in your Android manifest file.");
    }

    public static void launchIntentForUserClick(Context context, Intent intent, String str) throws IntentNotResolvableException {
        Preconditions.NoThrow.checkNotNull(context);
        Preconditions.NoThrow.checkNotNull(intent);
        try {
            startActivity(context, intent);
        } catch (IntentNotResolvableException e) {
            throw new IntentNotResolvableException(str + "\n" + e.getMessage());
        }
    }

    public static void launchApplicationUrl(Context context, Uri uri) throws IntentNotResolvableException {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        if (deviceCanHandleIntent(context, intent)) {
            launchApplicationIntent(context, intent);
        } else if (!STORE_SCHEME_TO_URL_MAP.containsKey(intent.getScheme()) || intent.getData() == null || TextUtils.isEmpty(intent.getData().getQuery())) {
            throw new IntentNotResolvableException("Could not handle application specific action: " + uri + "\n\tYou may be running in the emulator or another device which does not have the required application.");
        } else {
            launchApplicationIntent(context, new Intent("android.intent.action.VIEW", Uri.parse(String.format(STORE_SCHEME_TO_URL_MAP.get(intent.getScheme()), new Object[]{intent.getData().getQuery()}))));
        }
    }

    public static void launchApplicationIntent(Context context, Intent intent) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intent);
        if (deviceCanHandleIntent(context, intent)) {
            String str = "Unable to open intent: " + intent;
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            launchIntentForUserClick(context, intent, str);
            return;
        }
        String stringExtra = intent.getStringExtra("browser_fallback_url");
        if (!TextUtils.isEmpty(stringExtra)) {
            Uri parse = Uri.parse(stringExtra);
            String scheme = parse.getScheme();
            if (Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme)) {
                showMoPubBrowserForUrl(context, parse, (String) null);
            } else {
                launchApplicationUrl(context, parse);
            }
        } else if (!STORE_SCHEME_TO_URL_MAP.containsKey(intent.getScheme())) {
            launchApplicationUrl(context, getPlayStoreUri(intent));
        } else {
            throw new IntentNotResolvableException("Device could not handle neither intent nor market url.\nIntent: " + intent.toString());
        }
    }

    public static Uri getPlayStoreUri(Intent intent) {
        Preconditions.checkNotNull(intent);
        return Uri.parse("market://details?id=" + intent.getPackage());
    }

    public static void launchActionViewIntent(Context context, Uri uri, String str) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        launchIntentForUserClick(context, intent, str);
    }
}
