package com.mopub.nativeads;

import android.view.View;
import com.mopub.common.Preconditions;
import com.mopub.nativeads.NativeVideoController;
import java.util.HashMap;
import java.util.Map;

public abstract class VideoNativeAd extends BaseNativeAd implements NativeVideoController.Listener {
    private String mCallToAction;
    private String mClickDestinationUrl;
    private final Map<String, Object> mExtras = new HashMap();
    private String mIconImageUrl;
    private String mMainImageUrl;
    private String mPrivacyInformationIconClickThroughUrl;
    private String mPrivacyInformationIconImageUrl;
    private String mSponsored;
    private String mText;
    private String mTitle;
    private String mVastVideo;

    public void clear(View view) {
    }

    public void destroy() {
    }

    public void prepare(View view) {
    }

    public void render(MediaLayout mediaLayout) {
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getText() {
        return this.mText;
    }

    public String getMainImageUrl() {
        return this.mMainImageUrl;
    }

    public String getIconImageUrl() {
        return this.mIconImageUrl;
    }

    public String getClickDestinationUrl() {
        return this.mClickDestinationUrl;
    }

    public String getVastVideo() {
        return this.mVastVideo;
    }

    public String getCallToAction() {
        return this.mCallToAction;
    }

    public String getPrivacyInformationIconClickThroughUrl() {
        return this.mPrivacyInformationIconClickThroughUrl;
    }

    public String getPrivacyInformationIconImageUrl() {
        return this.mPrivacyInformationIconImageUrl;
    }

    public String getSponsored() {
        return this.mSponsored;
    }

    public final Object getExtra(String str) {
        if (!Preconditions.NoThrow.checkNotNull(str, "getExtra key is not allowed to be null")) {
            return null;
        }
        return this.mExtras.get(str);
    }

    public final Map<String, Object> getExtras() {
        return this.mExtras;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setText(String str) {
        this.mText = str;
    }

    public void setMainImageUrl(String str) {
        this.mMainImageUrl = str;
    }

    public void setIconImageUrl(String str) {
        this.mIconImageUrl = str;
    }

    public void setClickDestinationUrl(String str) {
        this.mClickDestinationUrl = str;
    }

    public void setVastVideo(String str) {
        this.mVastVideo = str;
    }

    public void setCallToAction(String str) {
        this.mCallToAction = str;
    }

    public void setPrivacyInformationIconClickThroughUrl(String str) {
        this.mPrivacyInformationIconClickThroughUrl = str;
    }

    public void setPrivacyInformationIconImageUrl(String str) {
        this.mPrivacyInformationIconImageUrl = str;
    }

    public void setSponsored(String str) {
        this.mSponsored = str;
    }

    public final void addExtra(String str, Object obj) {
        if (Preconditions.NoThrow.checkNotNull(str, "addExtra key is not allowed to be null")) {
            this.mExtras.put(str, obj);
        }
    }
}
