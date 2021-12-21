package com.mopub.nativeads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ViewBinder {
    final int callToActionId;
    final Map<String, Integer> extras;
    final int iconImageId;
    final int layoutId;
    final int mainImageId;
    final int privacyInformationIconImageId;
    final int sponsoredTextId;
    final int textId;
    final int titleId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public int callToActionId;
        /* access modifiers changed from: private */
        public Map<String, Integer> extras = Collections.emptyMap();
        /* access modifiers changed from: private */
        public int iconImageId;
        /* access modifiers changed from: private */
        public final int layoutId;
        /* access modifiers changed from: private */
        public int mainImageId;
        /* access modifiers changed from: private */
        public int privacyInformationIconImageId;
        /* access modifiers changed from: private */
        public int sponsoredTextId;
        /* access modifiers changed from: private */
        public int textId;
        /* access modifiers changed from: private */
        public int titleId;

        public Builder(int i) {
            this.layoutId = i;
            this.extras = new HashMap();
        }

        public final Builder titleId(int i) {
            this.titleId = i;
            return this;
        }

        public final Builder textId(int i) {
            this.textId = i;
            return this;
        }

        public final Builder callToActionId(int i) {
            this.callToActionId = i;
            return this;
        }

        public final Builder mainImageId(int i) {
            this.mainImageId = i;
            return this;
        }

        public final Builder iconImageId(int i) {
            this.iconImageId = i;
            return this;
        }

        public final Builder privacyInformationIconImageId(int i) {
            this.privacyInformationIconImageId = i;
            return this;
        }

        public final Builder sponsoredTextId(int i) {
            this.sponsoredTextId = i;
            return this;
        }

        public final Builder addExtras(Map<String, Integer> map) {
            this.extras = new HashMap(map);
            return this;
        }

        public final Builder addExtra(String str, int i) {
            this.extras.put(str, Integer.valueOf(i));
            return this;
        }

        public final ViewBinder build() {
            return new ViewBinder(this);
        }
    }

    private ViewBinder(Builder builder) {
        this.layoutId = builder.layoutId;
        this.titleId = builder.titleId;
        this.textId = builder.textId;
        this.callToActionId = builder.callToActionId;
        this.mainImageId = builder.mainImageId;
        this.iconImageId = builder.iconImageId;
        this.privacyInformationIconImageId = builder.privacyInformationIconImageId;
        this.sponsoredTextId = builder.sponsoredTextId;
        this.extras = builder.extras;
    }
}
