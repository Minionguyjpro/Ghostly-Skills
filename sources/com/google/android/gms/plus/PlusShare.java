package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.plus.zzr;
import com.google.android.gms.plus.model.people.Person;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class PlusShare {
    @Deprecated
    public static final String EXTRA_CALL_TO_ACTION = "com.google.android.apps.plus.CALL_TO_ACTION";
    @Deprecated
    public static final String EXTRA_CONTENT_DEEP_LINK_ID = "com.google.android.apps.plus.CONTENT_DEEP_LINK_ID";
    @Deprecated
    public static final String EXTRA_CONTENT_DEEP_LINK_METADATA = "com.google.android.apps.plus.CONTENT_DEEP_LINK_METADATA";
    @Deprecated
    public static final String EXTRA_CONTENT_URL = "com.google.android.apps.plus.CONTENT_URL";
    @Deprecated
    public static final String EXTRA_IS_INTERACTIVE_POST = "com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST";
    @Deprecated
    public static final String EXTRA_SENDER_ID = "com.google.android.apps.plus.SENDER_ID";
    @Deprecated
    public static final String KEY_CALL_TO_ACTION_DEEP_LINK_ID = "deepLinkId";
    @Deprecated
    public static final String KEY_CALL_TO_ACTION_LABEL = "label";
    @Deprecated
    public static final String KEY_CALL_TO_ACTION_URL = "url";
    @Deprecated
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION = "description";
    @Deprecated
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL = "thumbnailUrl";
    @Deprecated
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_TITLE = "title";
    @Deprecated
    public static final String PARAM_CONTENT_DEEP_LINK_ID = "deep_link_id";

    @Deprecated
    public static class Builder {
        private final Context mContext;
        private final Intent mIntent;
        private boolean zzp;
        private ArrayList<Uri> zzq;

        @Deprecated
        public Builder(Activity activity) {
            this.mContext = activity;
            Intent action = new Intent().setAction("android.intent.action.SEND");
            this.mIntent = action;
            action.addFlags(524288);
            if (activity != null && activity.getComponentName() != null) {
                this.zzp = true;
            }
        }

        @Deprecated
        public Builder(Context context) {
            this.mContext = context;
            this.mIntent = new Intent().setAction("android.intent.action.SEND");
        }

        @Deprecated
        public Builder addCallToAction(String str, Uri uri, String str2) {
            Preconditions.checkState(this.zzp, "Must include the launching activity with PlusShare.Builder constructor before setting call-to-action");
            Preconditions.checkArgument(uri != null && !TextUtils.isEmpty(uri.toString()), "Must provide a call to action URL");
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str)) {
                bundle.putString(PlusShare.KEY_CALL_TO_ACTION_LABEL, str);
            }
            bundle.putString("url", uri.toString());
            if (!TextUtils.isEmpty(str2)) {
                Preconditions.checkState(PlusShare.zza(str2), "The specified deep-link ID was malformed.");
                bundle.putString(PlusShare.KEY_CALL_TO_ACTION_DEEP_LINK_ID, str2);
            }
            this.mIntent.putExtra(PlusShare.EXTRA_CALL_TO_ACTION, bundle);
            this.mIntent.putExtra(PlusShare.EXTRA_IS_INTERACTIVE_POST, true);
            this.mIntent.setType("text/plain");
            return this;
        }

        @Deprecated
        public Builder addStream(Uri uri) {
            Uri uri2 = (Uri) this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
            if (uri2 == null) {
                return setStream(uri);
            }
            if (this.zzq == null) {
                this.zzq = new ArrayList<>();
            }
            this.zzq.add(uri2);
            this.zzq.add(uri);
            return this;
        }

        @Deprecated
        public Intent getIntent() {
            ArrayList<Uri> arrayList = this.zzq;
            boolean z = true;
            boolean z2 = arrayList != null && arrayList.size() > 1;
            boolean equals = "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
            boolean booleanExtra = this.mIntent.getBooleanExtra(PlusShare.EXTRA_IS_INTERACTIVE_POST, false);
            Preconditions.checkState(!z2 || !booleanExtra, "Call-to-action buttons are only available for URLs.");
            Preconditions.checkState(!booleanExtra || this.mIntent.hasExtra(PlusShare.EXTRA_CONTENT_URL), "The content URL is required for interactive posts.");
            if (booleanExtra && !this.mIntent.hasExtra(PlusShare.EXTRA_CONTENT_URL) && !this.mIntent.hasExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_ID)) {
                z = false;
            }
            Preconditions.checkState(z, "Must set content URL or content deep-link ID to use a call-to-action button.");
            if (this.mIntent.hasExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_ID)) {
                Preconditions.checkState(PlusShare.zza(this.mIntent.getStringExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_ID)), "The specified deep-link ID was malformed.");
            }
            if (!z2 && equals) {
                this.mIntent.setAction("android.intent.action.SEND");
                ArrayList<Uri> arrayList2 = this.zzq;
                if (arrayList2 == null || arrayList2.isEmpty()) {
                    this.mIntent.removeExtra("android.intent.extra.STREAM");
                } else {
                    this.mIntent.putExtra("android.intent.extra.STREAM", this.zzq.get(0));
                }
                this.zzq = null;
            }
            if (z2 && !equals) {
                this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
                ArrayList<Uri> arrayList3 = this.zzq;
                if (arrayList3 == null || arrayList3.isEmpty()) {
                    this.mIntent.removeExtra("android.intent.extra.STREAM");
                } else {
                    this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.zzq);
                }
            }
            if (!"com.google.android.gms.plus.action.SHARE_INTERNAL_GOOGLE".equals(this.mIntent.getAction())) {
                if (!this.mIntent.hasExtra("android.intent.extra.STREAM")) {
                    this.mIntent.setAction("com.google.android.gms.plus.action.SHARE_GOOGLE");
                } else {
                    this.mIntent.setPackage("com.google.android.apps.plus");
                    return this.mIntent;
                }
            }
            this.mIntent.setPackage("com.google.android.gms");
            return this.mIntent;
        }

        @Deprecated
        public Builder setContentDeepLinkId(String str) {
            return setContentDeepLinkId(str, (String) null, (String) null, (Uri) null);
        }

        @Deprecated
        public Builder setContentDeepLinkId(String str, String str2, String str3, Uri uri) {
            Preconditions.checkArgument(this.zzp, "Must include the launching activity with PlusShare.Builder constructor before setting deep links");
            Preconditions.checkArgument(!TextUtils.isEmpty(str), "The deepLinkId parameter is required.");
            Bundle bundle = new Bundle();
            bundle.putString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, str2);
            bundle.putString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str3);
            if (uri != null) {
                bundle.putString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL, uri.toString());
            }
            this.mIntent.putExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_ID, str);
            this.mIntent.putExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_METADATA, bundle);
            this.mIntent.setType("text/plain");
            return this;
        }

        @Deprecated
        public Builder setContentUrl(Uri uri) {
            String uri2 = uri != null ? uri.toString() : null;
            if (TextUtils.isEmpty(uri2)) {
                this.mIntent.removeExtra(PlusShare.EXTRA_CONTENT_URL);
            } else {
                this.mIntent.putExtra(PlusShare.EXTRA_CONTENT_URL, uri2);
            }
            return this;
        }

        @Deprecated
        public Builder setRecipients(Person person, List<Person> list) {
            this.mIntent.putExtra(PlusShare.EXTRA_SENDER_ID, person != null ? person.getId() : "0");
            int size = list != null ? list.size() : 0;
            if (size == 0) {
                this.mIntent.removeExtra("com.google.android.apps.plus.RECIPIENT_IDS");
                this.mIntent.removeExtra("com.google.android.apps.plus.RECIPIENT_DISPLAY_NAMES");
                return this;
            }
            ArrayList arrayList = new ArrayList(size);
            ArrayList arrayList2 = new ArrayList(size);
            for (Person next : list) {
                arrayList.add(next.getId());
                arrayList2.add(next.getDisplayName());
            }
            this.mIntent.putStringArrayListExtra("com.google.android.apps.plus.RECIPIENT_IDS", arrayList);
            this.mIntent.putStringArrayListExtra("com.google.android.apps.plus.RECIPIENT_DISPLAY_NAMES", arrayList2);
            return this;
        }

        @Deprecated
        public Builder setStream(Uri uri) {
            this.zzq = null;
            this.mIntent.putExtra("android.intent.extra.STREAM", uri);
            return this;
        }

        @Deprecated
        public Builder setText(CharSequence charSequence) {
            this.mIntent.putExtra("android.intent.extra.TEXT", charSequence);
            return this;
        }

        @Deprecated
        public Builder setType(String str) {
            this.mIntent.setType(str);
            return this;
        }
    }

    @Deprecated
    protected PlusShare() {
        throw new AssertionError();
    }

    @Deprecated
    public static Person createPerson(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("MinimalPerson ID must not be empty.");
        } else if (!TextUtils.isEmpty(str2)) {
            return new zzr(str2, str, (zzr.zzc) null, 0, (String) null);
        } else {
            throw new IllegalArgumentException("Display name must not be empty.");
        }
    }

    @Deprecated
    public static String getDeepLinkId(Intent intent) {
        if (intent == null || intent.getData() == null) {
            return null;
        }
        return intent.getData().getQueryParameter(PARAM_CONTENT_DEEP_LINK_ID);
    }

    protected static boolean zza(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "The provided deep-link ID is empty.";
        } else if (!str.contains(" ")) {
            return true;
        } else {
            str2 = "Spaces are not allowed in deep-link IDs.";
        }
        Log.e("GooglePlusPlatform", str2);
        return false;
    }
}
