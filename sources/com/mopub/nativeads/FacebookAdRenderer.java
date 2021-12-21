package com.mopub.nativeads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.MediaView;
import com.mopub.common.Preconditions;
import com.mopub.nativeads.FacebookNative;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class FacebookAdRenderer implements MoPubAdRenderer<FacebookNative.FacebookNativeAd> {
    private final FacebookViewBinder mViewBinder;
    final WeakHashMap<View, FacebookNativeViewHolder> mViewHolderMap = new WeakHashMap<>();

    public FacebookAdRenderer(FacebookViewBinder facebookViewBinder) {
        this.mViewBinder = facebookViewBinder;
    }

    public View createAdView(Context context, ViewGroup viewGroup) {
        Preconditions.checkNotNull(context);
        return LayoutInflater.from(context).inflate(this.mViewBinder.layoutId, viewGroup, false);
    }

    public void renderAdView(View view, FacebookNative.FacebookNativeAd facebookNativeAd) {
        Preconditions.checkNotNull(facebookNativeAd);
        Preconditions.checkNotNull(view);
        FacebookNativeViewHolder facebookNativeViewHolder = this.mViewHolderMap.get(view);
        if (facebookNativeViewHolder == null) {
            facebookNativeViewHolder = FacebookNativeViewHolder.fromViewBinder(view, this.mViewBinder);
            this.mViewHolderMap.put(view, facebookNativeViewHolder);
        }
        update(facebookNativeViewHolder, facebookNativeAd);
        NativeRendererHelper.updateExtras(facebookNativeViewHolder.getMainView(), this.mViewBinder.extras, facebookNativeAd.getExtras());
    }

    public boolean supports(BaseNativeAd baseNativeAd) {
        Preconditions.checkNotNull(baseNativeAd);
        return baseNativeAd instanceof FacebookNative.FacebookNativeAd;
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void update(com.mopub.nativeads.FacebookAdRenderer.FacebookNativeViewHolder r5, com.mopub.nativeads.FacebookNative.FacebookNativeAd r6) {
        /*
            r4 = this;
            android.widget.TextView r0 = r5.getTitleView()
            java.lang.String r1 = r6.getTitle()
            com.mopub.nativeads.NativeRendererHelper.addTextView(r0, r1)
            android.widget.TextView r0 = r5.getTextView()
            java.lang.String r1 = r6.getText()
            com.mopub.nativeads.NativeRendererHelper.addTextView(r0, r1)
            android.widget.TextView r0 = r5.getCallToActionView()
            java.lang.String r1 = r6.getCallToAction()
            com.mopub.nativeads.NativeRendererHelper.addTextView(r0, r1)
            android.widget.TextView r0 = r5.getAdvertiserNameView()
            java.lang.String r1 = r6.getAdvertiserName()
            com.mopub.nativeads.NativeRendererHelper.addTextView(r0, r1)
            android.widget.TextView r0 = r5.getSponsoredLabelView()
            java.lang.String r1 = r6.getSponsoredName()
            com.mopub.nativeads.NativeRendererHelper.addTextView(r0, r1)
            android.widget.RelativeLayout r0 = r5.getAdChoicesContainer()
            android.view.View r1 = r5.getMainView()
            com.facebook.ads.MediaView r2 = r5.getMediaView()
            com.facebook.ads.MediaView r3 = r5.getAdIconView()
            r6.registerChildViewsForInteraction(r1, r2, r3)
            if (r0 == 0) goto L_0x008c
            r0.removeAllViews()
            r1 = 0
            android.view.View r2 = r5.mainView
            boolean r2 = r2 instanceof com.facebook.ads.NativeAdLayout
            if (r2 == 0) goto L_0x005f
            android.view.View r5 = r5.mainView
            r1 = r5
            com.facebook.ads.NativeAdLayout r1 = (com.facebook.ads.NativeAdLayout) r1
        L_0x005f:
            com.facebook.ads.AdOptionsView r5 = new com.facebook.ads.AdOptionsView
            android.content.Context r2 = r0.getContext()
            com.facebook.ads.NativeAdBase r6 = r6.getFacebookNativeAd()
            r5.<init>(r2, r6, r1)
            android.view.ViewGroup$LayoutParams r6 = r5.getLayoutParams()
            boolean r1 = r6 instanceof android.widget.RelativeLayout.LayoutParams
            if (r1 == 0) goto L_0x0089
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 17
            if (r1 < r2) goto L_0x0082
            android.widget.RelativeLayout$LayoutParams r6 = (android.widget.RelativeLayout.LayoutParams) r6
            r1 = 21
            r6.addRule(r1)
            goto L_0x0089
        L_0x0082:
            android.widget.RelativeLayout$LayoutParams r6 = (android.widget.RelativeLayout.LayoutParams) r6
            r1 = 11
            r6.addRule(r1)
        L_0x0089:
            r0.addView(r5)
        L_0x008c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.FacebookAdRenderer.update(com.mopub.nativeads.FacebookAdRenderer$FacebookNativeViewHolder, com.mopub.nativeads.FacebookNative$FacebookNativeAd):void");
    }

    static class FacebookNativeViewHolder {
        private RelativeLayout adChoicesContainer;
        private MediaView adIconView;
        private TextView advertiserNameView;
        private TextView callToActionView;
        /* access modifiers changed from: private */
        public View mainView;
        private MediaView mediaView;
        private TextView sponsoredLabelView;
        private TextView textView;
        private TextView titleView;

        private FacebookNativeViewHolder() {
        }

        static FacebookNativeViewHolder fromViewBinder(View view, FacebookViewBinder facebookViewBinder) {
            if (view == null || facebookViewBinder == null) {
                return new FacebookNativeViewHolder();
            }
            FacebookNativeViewHolder facebookNativeViewHolder = new FacebookNativeViewHolder();
            facebookNativeViewHolder.mainView = view;
            facebookNativeViewHolder.titleView = (TextView) view.findViewById(facebookViewBinder.titleId);
            facebookNativeViewHolder.textView = (TextView) view.findViewById(facebookViewBinder.textId);
            facebookNativeViewHolder.callToActionView = (TextView) view.findViewById(facebookViewBinder.callToActionId);
            facebookNativeViewHolder.adChoicesContainer = (RelativeLayout) view.findViewById(facebookViewBinder.adChoicesRelativeLayoutId);
            facebookNativeViewHolder.mediaView = (MediaView) view.findViewById(facebookViewBinder.mediaViewId);
            facebookNativeViewHolder.adIconView = (MediaView) view.findViewById(facebookViewBinder.adIconViewId);
            facebookNativeViewHolder.advertiserNameView = (TextView) view.findViewById(facebookViewBinder.advertiserNameId);
            facebookNativeViewHolder.sponsoredLabelView = (TextView) view.findViewById(facebookViewBinder.sponsoredLabelId);
            return facebookNativeViewHolder;
        }

        public View getMainView() {
            return this.mainView;
        }

        public TextView getTitleView() {
            return this.titleView;
        }

        public TextView getTextView() {
            return this.textView;
        }

        public TextView getCallToActionView() {
            return this.callToActionView;
        }

        public RelativeLayout getAdChoicesContainer() {
            return this.adChoicesContainer;
        }

        public MediaView getAdIconView() {
            return this.adIconView;
        }

        public MediaView getMediaView() {
            return this.mediaView;
        }

        public TextView getAdvertiserNameView() {
            return this.advertiserNameView;
        }

        public TextView getSponsoredLabelView() {
            return this.sponsoredLabelView;
        }
    }

    public static class FacebookViewBinder {
        final int adChoicesRelativeLayoutId;
        final int adIconViewId;
        final int advertiserNameId;
        final int callToActionId;
        final Map<String, Integer> extras;
        final int layoutId;
        final int mediaViewId;
        final int sponsoredLabelId;
        final int textId;
        final int titleId;

        private FacebookViewBinder(Builder builder) {
            Preconditions.checkNotNull(builder);
            this.layoutId = builder.layoutId;
            this.titleId = builder.titleId;
            this.textId = builder.textId;
            this.callToActionId = builder.callToActionId;
            this.adChoicesRelativeLayoutId = builder.adChoicesRelativeLayoutId;
            this.extras = builder.extras;
            this.mediaViewId = builder.mediaViewId;
            this.adIconViewId = builder.adIconViewId;
            this.advertiserNameId = builder.advertiserNameId;
            this.sponsoredLabelId = builder.sponsoredLabelId;
        }

        public static class Builder {
            /* access modifiers changed from: private */
            public int adChoicesRelativeLayoutId;
            /* access modifiers changed from: private */
            public int adIconViewId;
            /* access modifiers changed from: private */
            public int advertiserNameId;
            /* access modifiers changed from: private */
            public int callToActionId;
            /* access modifiers changed from: private */
            public Map<String, Integer> extras = Collections.emptyMap();
            /* access modifiers changed from: private */
            public final int layoutId;
            /* access modifiers changed from: private */
            public int mediaViewId;
            /* access modifiers changed from: private */
            public int sponsoredLabelId;
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

            public final Builder adChoicesRelativeLayoutId(int i) {
                this.adChoicesRelativeLayoutId = i;
                return this;
            }

            public final Builder extras(Map<String, Integer> map) {
                this.extras = new HashMap(map);
                return this;
            }

            public final Builder addExtra(String str, int i) {
                this.extras.put(str, Integer.valueOf(i));
                return this;
            }

            public Builder mediaViewId(int i) {
                this.mediaViewId = i;
                return this;
            }

            public Builder adIconViewId(int i) {
                this.adIconViewId = i;
                return this;
            }

            public Builder advertiserNameId(int i) {
                this.advertiserNameId = i;
                return this;
            }

            public Builder sponsoredNameId(int i) {
                this.sponsoredLabelId = i;
                return this;
            }

            public FacebookViewBinder build() {
                return new FacebookViewBinder(this);
            }
        }
    }
}
