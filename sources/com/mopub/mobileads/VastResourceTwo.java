package com.mopub.mobileads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Mockable
/* compiled from: VastResourceTwo.kt */
public class VastResourceTwo implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final List<String> VALID_APPLICATION_TYPES = CollectionsKt.listOf("application/x-javascript");
    /* access modifiers changed from: private */
    public static final List<String> VALID_IMAGE_TYPES = CollectionsKt.listOf("image/jpeg", "image/png", "image/bmp", "image/gif");
    private static final long serialVersionUID = 1;
    @SerializedName("creative_type")
    @Expose
    private final CreativeType creativeType;
    @SerializedName("height")
    @Expose
    private final int height;
    @SerializedName("resource")
    @Expose
    private final String resource;
    @SerializedName("type")
    @Expose
    private final Type type;
    @SerializedName("width")
    @Expose
    private final int width;

    /* compiled from: VastResourceTwo.kt */
    public enum CreativeType {
        NONE,
        IMAGE,
        JAVASCRIPT
    }

    /* compiled from: VastResourceTwo.kt */
    public enum Type {
        STATIC_RESOURCE,
        HTML_RESOURCE,
        IFRAME_RESOURCE
    }

    public static final VastResourceTwo fromVastResourceXmlManager(VastResourceXmlManager vastResourceXmlManager, int i, int i2) {
        return Companion.fromVastResourceXmlManager(vastResourceXmlManager, i, i2);
    }

    public static final VastResourceTwo fromVastResourceXmlManager(VastResourceXmlManager vastResourceXmlManager, Type type2, int i, int i2) {
        return Companion.fromVastResourceXmlManager(vastResourceXmlManager, type2, i, i2);
    }

    public VastResourceTwo(String str, Type type2, CreativeType creativeType2, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(str, Constants.VAST_RESOURCE);
        Intrinsics.checkParameterIsNotNull(type2, "type");
        Intrinsics.checkParameterIsNotNull(creativeType2, VastResourceXmlManager.CREATIVE_TYPE);
        this.resource = str;
        this.type = type2;
        this.creativeType = creativeType2;
        this.width = i;
        this.height = i2;
    }

    public String getResource() {
        return this.resource;
    }

    public Type getType() {
        return this.type;
    }

    public CreativeType getCreativeType() {
        return this.creativeType;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void initializeWebView(VastWebView vastWebView) {
        String str;
        Intrinsics.checkParameterIsNotNull(vastWebView, "webView");
        if (getType() == Type.HTML_RESOURCE) {
            str = getResource();
        } else if (getType() == Type.IFRAME_RESOURCE) {
            str = "<iframe frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" style=\"border: 0px; margin: 0px;\"" + " width=\"" + getWidth() + '\"' + " height=\"" + getHeight() + '\"' + " src=\"" + getResource() + "\"></iframe>";
        } else if (getType() == Type.STATIC_RESOURCE && getCreativeType() == CreativeType.IMAGE) {
            str = "<html>" + "<head></head><body style=\"margin:0;padding:0\"><img src=\"" + getResource() + '\"' + " width=\"100%\" style=\"max-width:100%;max-height:100%;\" />" + "</body></html>";
        } else if (getType() == Type.STATIC_RESOURCE && getCreativeType() == CreativeType.JAVASCRIPT) {
            str = "<script src=\"" + getResource() + "\"></script>";
        } else {
            str = null;
        }
        if (str != null) {
            vastWebView.loadData(str);
        }
    }

    public String getCorrectClickThroughUrl(String str, String str2) {
        if (!(getType() == Type.HTML_RESOURCE || getType() == Type.IFRAME_RESOURCE)) {
            if (getType() == Type.STATIC_RESOURCE && getCreativeType() == CreativeType.IMAGE) {
                return str;
            }
            if (!(getType() == Type.STATIC_RESOURCE && getCreativeType() == CreativeType.JAVASCRIPT)) {
                return null;
            }
        }
        return str2;
    }

    /* compiled from: VastResourceTwo.kt */
    public static final class Companion {

        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Type.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[Type.STATIC_RESOURCE.ordinal()] = 1;
                $EnumSwitchMapping$0[Type.HTML_RESOURCE.ordinal()] = 2;
                $EnumSwitchMapping$0[Type.IFRAME_RESOURCE.ordinal()] = 3;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VastResourceTwo fromVastResourceXmlManager(VastResourceXmlManager vastResourceXmlManager, int i, int i2) {
            Intrinsics.checkParameterIsNotNull(vastResourceXmlManager, "resourceXmlManager");
            Type[] values = Type.values();
            Collection arrayList = new ArrayList();
            for (Type fromVastResourceXmlManager : values) {
                VastResourceTwo fromVastResourceXmlManager2 = VastResourceTwo.Companion.fromVastResourceXmlManager(vastResourceXmlManager, fromVastResourceXmlManager, i, i2);
                if (fromVastResourceXmlManager2 != null) {
                    arrayList.add(fromVastResourceXmlManager2);
                }
            }
            return (VastResourceTwo) CollectionsKt.firstOrNull((List) arrayList);
        }

        public final VastResourceTwo fromVastResourceXmlManager(VastResourceXmlManager vastResourceXmlManager, Type type, int i, int i2) {
            Intrinsics.checkParameterIsNotNull(vastResourceXmlManager, "resourceXmlManager");
            Intrinsics.checkParameterIsNotNull(type, "type");
            String staticResourceType = vastResourceXmlManager.getStaticResourceType();
            String str = null;
            CreativeType creativeType = CreativeType.NONE;
            int i3 = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
            boolean z = true;
            if (i3 == 1) {
                String staticResource = vastResourceXmlManager.getStaticResource();
                if (!CollectionsKt.contains(VastResourceTwo.VALID_IMAGE_TYPES, staticResourceType) && !CollectionsKt.contains(VastResourceTwo.VALID_APPLICATION_TYPES, staticResourceType)) {
                    z = false;
                }
                str = z ? staticResource : null;
                CreativeType creativeType2 = CreativeType.IMAGE;
                if (!CollectionsKt.contains(VastResourceTwo.VALID_IMAGE_TYPES, staticResourceType)) {
                    creativeType2 = null;
                }
                if (creativeType2 == null) {
                    creativeType2 = CreativeType.JAVASCRIPT;
                }
                creativeType = creativeType2;
            } else if (i3 == 2) {
                str = vastResourceXmlManager.getHTMLResource();
            } else if (i3 == 3) {
                str = vastResourceXmlManager.getIFrameResource();
            }
            String str2 = str;
            CreativeType creativeType3 = creativeType;
            if (str2 != null) {
                return new VastResourceTwo(str2, type, creativeType3, i, i2);
            }
            return null;
        }
    }
}
