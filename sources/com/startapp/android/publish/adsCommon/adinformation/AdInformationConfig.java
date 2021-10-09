package com.startapp.android.publish.adsCommon.adinformation;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adinformation.AdInformationPositions;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.c.f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: StartAppSDK */
public class AdInformationConfig implements Serializable {
    private static final long serialVersionUID = 1;
    @f(b = ArrayList.class, c = e.class)
    private List<e> ImageResources = new ArrayList();
    @f(b = HashMap.class, c = AdInformationPositions.Position.class, d = AdPreferences.Placement.class)
    protected HashMap<AdPreferences.Placement, AdInformationPositions.Position> Positions = new HashMap<>();

    /* renamed from: a  reason: collision with root package name */
    private transient EnumMap<ImageResourceType, e> f201a = new EnumMap<>(ImageResourceType.class);
    private String dialogUrlSecured = "https://d1byvlfiet2h9q.cloudfront.net/InApp/resources/adInformationDialog3.html";
    private boolean enabled = true;
    private String eulaUrlSecured = "https://www.com.startapp.com/policy/sdk-policy/";
    private float fatFingersFactor = 200.0f;

    /* compiled from: StartAppSDK */
    public enum ImageResourceType {
        INFO_S(17, 14),
        INFO_EX_S(88, 14),
        INFO_L(25, 21),
        INFO_EX_L(130, 21);
        
        private int height;
        private int width;

        private ImageResourceType(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public int getDefaultWidth() {
            return this.width;
        }

        public int getDefaultHeight() {
            return this.height;
        }

        public static ImageResourceType getByName(String str) {
            ImageResourceType imageResourceType = INFO_S;
            ImageResourceType[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    imageResourceType = values[i];
                }
            }
            return imageResourceType;
        }
    }

    private AdInformationConfig() {
    }

    public static AdInformationConfig a() {
        AdInformationConfig adInformationConfig = new AdInformationConfig();
        a(adInformationConfig);
        return adInformationConfig;
    }

    public static void a(AdInformationConfig adInformationConfig) {
        adInformationConfig.i();
        adInformationConfig.h();
    }

    public String b() {
        String str = this.eulaUrlSecured;
        return (str == null || str.equals("")) ? "https://www.com.startapp.com/policy/sdk-policy/" : this.eulaUrlSecured;
    }

    public String c() {
        return (!this.f201a.containsKey(ImageResourceType.INFO_L) || this.f201a.get(ImageResourceType.INFO_L).d().equals("")) ? "https://info.startappservice.com/InApp/resources/info_l.png" : this.f201a.get(ImageResourceType.INFO_L).d();
    }

    public boolean d() {
        return this.enabled;
    }

    public boolean a(Context context) {
        return !k.a(context, "userDisabledAdInformation", (Boolean) false).booleanValue() && d();
    }

    public void a(Context context, boolean z) {
        k.b(context, "userDisabledAdInformation", Boolean.valueOf(!z));
    }

    public float e() {
        return this.fatFingersFactor / 100.0f;
    }

    /* access modifiers changed from: protected */
    public void a(ImageResourceType imageResourceType, e eVar) {
        j().put(imageResourceType, eVar);
    }

    public String f() {
        String str = this.dialogUrlSecured;
        return str != null ? str : "https://d1byvlfiet2h9q.cloudfront.net/InApp/resources/adInformationDialog3.html";
    }

    public AdInformationPositions.Position a(AdPreferences.Placement placement) {
        AdInformationPositions.Position position = this.Positions.get(placement);
        if (position != null) {
            return position;
        }
        AdInformationPositions.Position position2 = AdInformationPositions.Position.BOTTOM_LEFT;
        this.Positions.put(placement, position2);
        return position2;
    }

    public e a(ImageResourceType imageResourceType) {
        return j().get(imageResourceType);
    }

    public void g() {
        for (e next : this.ImageResources) {
            a(ImageResourceType.getByName(next.a()), next);
            next.e();
        }
    }

    /* access modifiers changed from: protected */
    public void h() {
        ImageResourceType[] values = ImageResourceType.values();
        int length = values.length;
        int i = 0;
        while (i < length) {
            ImageResourceType imageResourceType = values[i];
            if (j().get(imageResourceType) != null) {
                i++;
            } else {
                throw new IllegalArgumentException("AdInformation error in ImageResource [" + imageResourceType + "] cannot be found in MetaData");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void i() {
        for (ImageResourceType imageResourceType : ImageResourceType.values()) {
            e eVar = j().get(imageResourceType);
            Boolean bool = true;
            if (eVar == null) {
                eVar = e.c(imageResourceType.name());
                Iterator<e> it = this.ImageResources.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (ImageResourceType.getByName(it.next().a()).equals(imageResourceType)) {
                            bool = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                j().put(imageResourceType, eVar);
                if (bool.booleanValue()) {
                    this.ImageResources.add(eVar);
                }
            }
            eVar.a(imageResourceType.getDefaultWidth());
            eVar.b(imageResourceType.getDefaultHeight());
            eVar.a(imageResourceType.name().toLowerCase() + ".png");
        }
    }

    public EnumMap<ImageResourceType, e> j() {
        return this.f201a;
    }

    public void k() {
        this.f201a = new EnumMap<>(ImageResourceType.class);
    }
}
