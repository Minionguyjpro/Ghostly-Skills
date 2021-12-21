package com.mopub.mobileads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Preconditions;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

class VastResource implements Serializable {
    private static final List<String> VALID_APPLICATION_TYPES = Arrays.asList(new String[]{"application/x-javascript"});
    private static final List<String> VALID_IMAGE_TYPES = Arrays.asList(new String[]{"image/jpeg", "image/png", "image/bmp", "image/gif"});
    private static final long serialVersionUID = 0;
    @SerializedName("creative_type")
    @Expose
    private CreativeType mCreativeType;
    @SerializedName("height")
    @Expose
    private int mHeight;
    @SerializedName("resource")
    @Expose
    private String mResource;
    @SerializedName("type")
    @Expose
    private Type mType;
    @SerializedName("width")
    @Expose
    private int mWidth;

    enum CreativeType {
        NONE,
        IMAGE,
        JAVASCRIPT
    }

    enum Type {
        STATIC_RESOURCE,
        HTML_RESOURCE,
        IFRAME_RESOURCE
    }

    static VastResource fromVastResourceXmlManager(VastResourceXmlManager vastResourceXmlManager, int i, int i2) {
        for (Type fromVastResourceXmlManager : Type.values()) {
            VastResource fromVastResourceXmlManager2 = fromVastResourceXmlManager(vastResourceXmlManager, fromVastResourceXmlManager, i, i2);
            if (fromVastResourceXmlManager2 != null) {
                return fromVastResourceXmlManager2;
            }
        }
        return null;
    }

    static VastResource fromVastResourceXmlManager(VastResourceXmlManager vastResourceXmlManager, Type type, int i, int i2) {
        CreativeType creativeType;
        CreativeType creativeType2;
        Preconditions.checkNotNull(vastResourceXmlManager);
        Preconditions.checkNotNull(type);
        String iFrameResource = vastResourceXmlManager.getIFrameResource();
        String hTMLResource = vastResourceXmlManager.getHTMLResource();
        String staticResource = vastResourceXmlManager.getStaticResource();
        String staticResourceType = vastResourceXmlManager.getStaticResourceType();
        if (type == Type.STATIC_RESOURCE && staticResource != null && staticResourceType != null && (VALID_IMAGE_TYPES.contains(staticResourceType) || VALID_APPLICATION_TYPES.contains(staticResourceType))) {
            if (VALID_IMAGE_TYPES.contains(staticResourceType)) {
                creativeType2 = CreativeType.IMAGE;
            } else {
                creativeType2 = CreativeType.JAVASCRIPT;
            }
            creativeType = creativeType2;
        } else if (type == Type.HTML_RESOURCE && hTMLResource != null) {
            creativeType = CreativeType.NONE;
            staticResource = hTMLResource;
        } else if (type != Type.IFRAME_RESOURCE || iFrameResource == null) {
            return null;
        } else {
            creativeType = CreativeType.NONE;
            staticResource = iFrameResource;
        }
        return new VastResource(staticResource, type, creativeType, i, i2);
    }

    VastResource(String str, Type type, CreativeType creativeType, int i, int i2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(creativeType);
        this.mResource = str;
        this.mType = type;
        this.mCreativeType = creativeType;
        this.mWidth = i;
        this.mHeight = i2;
    }

    public String getResource() {
        return this.mResource;
    }

    public Type getType() {
        return this.mType;
    }

    public CreativeType getCreativeType() {
        return this.mCreativeType;
    }

    public void initializeWebView(VastWebView vastWebView) {
        Preconditions.checkNotNull(vastWebView);
        if (this.mType == Type.IFRAME_RESOURCE) {
            vastWebView.loadData("<iframe frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" style=\"border: 0px; margin: 0px;\" width=\"" + this.mWidth + "\" height=\"" + this.mHeight + "\" src=\"" + this.mResource + "\"></iframe>");
        } else if (this.mType == Type.HTML_RESOURCE) {
            vastWebView.loadData(this.mResource);
        } else if (this.mType != Type.STATIC_RESOURCE) {
        } else {
            if (this.mCreativeType == CreativeType.IMAGE) {
                vastWebView.loadData("<html><head></head><body style=\"margin:0;padding:0\"><img src=\"" + this.mResource + "\" width=\"100%\" style=\"max-width:100%;max-height:100%;\" /></body></html>");
            } else if (this.mCreativeType == CreativeType.JAVASCRIPT) {
                vastWebView.loadData("<script src=\"" + this.mResource + "\"></script>");
            }
        }
    }

    /* renamed from: com.mopub.mobileads.VastResource$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mobileads$VastResource$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.mopub.mobileads.VastResource$Type[] r0 = com.mopub.mobileads.VastResource.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$mobileads$VastResource$Type = r0
                com.mopub.mobileads.VastResource$Type r1 = com.mopub.mobileads.VastResource.Type.STATIC_RESOURCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$mobileads$VastResource$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.mobileads.VastResource$Type r1 = com.mopub.mobileads.VastResource.Type.HTML_RESOURCE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$mobileads$VastResource$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.mobileads.VastResource$Type r1 = com.mopub.mobileads.VastResource.Type.IFRAME_RESOURCE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VastResource.AnonymousClass1.<clinit>():void");
        }
    }

    public String getCorrectClickThroughUrl(String str, String str2) {
        int i = AnonymousClass1.$SwitchMap$com$mopub$mobileads$VastResource$Type[this.mType.ordinal()];
        if (i != 1) {
            if (i == 2 || i == 3) {
                return str2;
            }
            return null;
        } else if (CreativeType.IMAGE == this.mCreativeType) {
            return str;
        } else {
            if (CreativeType.JAVASCRIPT == this.mCreativeType) {
                return str2;
            }
            return null;
        }
    }
}
