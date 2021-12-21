package com.mopub.nativeads;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Drawables;
import java.util.Map;

public class NativeRendererHelper {
    public static void addTextView(TextView textView, String str) {
        if (textView == null) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Attempted to add text (" + str + ") to null TextView.");
            return;
        }
        textView.setText((CharSequence) null);
        if (str == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Attempted to set TextView contents to null.");
            return;
        }
        textView.setText(str);
    }

    public static void addPrivacyInformationIcon(ImageView imageView, String str, final String str2) {
        if (imageView != null) {
            if (str2 == null) {
                imageView.setImageDrawable((Drawable) null);
                imageView.setOnClickListener((View.OnClickListener) null);
                imageView.setVisibility(4);
                return;
            }
            final Context context = imageView.getContext();
            if (context != null) {
                if (str == null) {
                    imageView.setImageDrawable(Drawables.NATIVE_PRIVACY_INFORMATION_ICON.createDrawable(context));
                } else {
                    NativeImageHelper.loadImageView(str, imageView);
                }
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        new UrlHandler.Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).build().handleUrl(context, str2);
                    }
                });
                imageView.setVisibility(0);
            }
        }
    }

    public static void addCtaButton(TextView textView, final View view, String str) {
        addTextView(textView, str);
        if (textView != null && view != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    view.performClick();
                }
            });
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void addSponsoredView(java.lang.String r6, android.widget.TextView r7) {
        /*
            java.lang.String r0 = "Unable to format sponsored by String."
            if (r7 != 0) goto L_0x0005
            return
        L_0x0005:
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 == 0) goto L_0x0010
            r6 = 4
            r7.setVisibility(r6)
            return
        L_0x0010:
            r1 = 1
            r2 = 0
            android.content.Context r3 = r7.getContext()     // Catch:{ IllegalFormatException -> 0x002b, NotFoundException -> 0x0021 }
            int r4 = com.mopub.mobileads.native_static.R.string.com_mopub_nativeads_sponsored_by     // Catch:{ IllegalFormatException -> 0x002b, NotFoundException -> 0x0021 }
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ IllegalFormatException -> 0x002b, NotFoundException -> 0x0021 }
            r5[r2] = r6     // Catch:{ IllegalFormatException -> 0x002b, NotFoundException -> 0x0021 }
            java.lang.String r0 = r3.getString(r4, r5)     // Catch:{ IllegalFormatException -> 0x002b, NotFoundException -> 0x0021 }
            goto L_0x0035
        L_0x0021:
            com.mopub.common.logging.MoPubLog$SdkLogEvent r3 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM
            java.lang.Object[] r4 = new java.lang.Object[r1]
            r4[r2] = r0
            com.mopub.common.logging.MoPubLog.log(r3, r4)
            goto L_0x0034
        L_0x002b:
            com.mopub.common.logging.MoPubLog$SdkLogEvent r3 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM
            java.lang.Object[] r4 = new java.lang.Object[r1]
            r4[r2] = r0
            com.mopub.common.logging.MoPubLog.log(r3, r4)
        L_0x0034:
            r0 = r6
        L_0x0035:
            boolean r6 = r0.contains(r6)
            if (r6 != 0) goto L_0x0046
            com.mopub.common.logging.MoPubLog$SdkLogEvent r6 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = "The formatted sponsored String does not include the sponsor. Please include %s in the com_mopub_nativeads_sponsored_by translation."
            r1[r2] = r3
            com.mopub.common.logging.MoPubLog.log(r6, r1)
        L_0x0046:
            addTextView(r7, r0)
            r7.setVisibility(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.NativeRendererHelper.addSponsoredView(java.lang.String, android.widget.TextView):void");
    }

    public static void updateExtras(View view, Map<String, Integer> map, Map<String, Object> map2) {
        if (view == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Attempted to bind extras on a null main view.");
            return;
        }
        for (String next : map.keySet()) {
            View findViewById = view.findViewById(map.get(next).intValue());
            Object obj = map2.get(next);
            if (findViewById instanceof ImageView) {
                ImageView imageView = (ImageView) findViewById;
                imageView.setImageDrawable((Drawable) null);
                Object obj2 = map2.get(next);
                if (obj2 != null && (obj2 instanceof String)) {
                    NativeImageHelper.loadImageView((String) obj2, imageView);
                }
            } else if (findViewById instanceof TextView) {
                TextView textView = (TextView) findViewById;
                textView.setText((CharSequence) null);
                if (obj instanceof String) {
                    addTextView(textView, (String) obj);
                }
            } else {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "View bound to " + next + " should be an instance of TextView or ImageView.");
            }
        }
    }
}
