package androidx.browser.customtabs;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.BundleCompat;
import java.util.ArrayList;

public final class CustomTabsIntent {
    public final Intent intent;
    public final Bundle startAnimationBundle;

    CustomTabsIntent(Intent intent2, Bundle bundle) {
        this.intent = intent2;
        this.startAnimationBundle = bundle;
    }

    public static final class Builder {
        private ArrayList<Bundle> mActionButtons;
        private boolean mInstantAppsEnabled;
        private final Intent mIntent;
        private ArrayList<Bundle> mMenuItems;
        private Bundle mStartAnimationBundle;

        public Builder() {
            this((CustomTabsSession) null);
        }

        public Builder(CustomTabsSession customTabsSession) {
            Intent intent = new Intent("android.intent.action.VIEW");
            this.mIntent = intent;
            IBinder iBinder = null;
            this.mMenuItems = null;
            this.mStartAnimationBundle = null;
            this.mActionButtons = null;
            this.mInstantAppsEnabled = true;
            if (customTabsSession != null) {
                intent.setPackage(customTabsSession.getComponentName().getPackageName());
            }
            Bundle bundle = new Bundle();
            BundleCompat.putBinder(bundle, "android.support.customtabs.extra.SESSION", customTabsSession != null ? customTabsSession.getBinder() : iBinder);
            this.mIntent.putExtras(bundle);
        }

        public CustomTabsIntent build() {
            ArrayList<Bundle> arrayList = this.mMenuItems;
            if (arrayList != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", arrayList);
            }
            ArrayList<Bundle> arrayList2 = this.mActionButtons;
            if (arrayList2 != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", arrayList2);
            }
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.mInstantAppsEnabled);
            return new CustomTabsIntent(this.mIntent, this.mStartAnimationBundle);
        }
    }
}
