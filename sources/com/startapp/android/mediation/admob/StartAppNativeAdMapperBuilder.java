package com.startapp.android.mediation.admob;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.startapp.android.publish.ads.nativead.NativeAdDetails;
import java.util.ArrayList;
import java.util.List;

public class StartAppNativeAdMapperBuilder {

    interface IMapper {
        NativeAdMapper asMapper();

        void setBody(String str);

        void setCallToAction(String str);

        void setHeadline(String str);

        void setIcon(NativeAd.Image image);

        void setImages(List<NativeAd.Image> list);

        void setPrice(String str);

        void setStarRating(double d);

        void setStore(String str);
    }

    public static NativeAdMapper buildMapper(Context context, NativeAdDetails nativeAdDetails) {
        boolean equals = Boolean.TRUE.equals(nativeAdDetails.isApp());
        IMapper appInstallMapper = equals ? new AppInstallMapper(context, nativeAdDetails) : new ContentMapper(context, nativeAdDetails);
        appInstallMapper.setHeadline(nativeAdDetails.getTitle());
        appInstallMapper.setBody(nativeAdDetails.getDescription());
        appInstallMapper.setCallToAction(extractCallToAction(nativeAdDetails.getCampaignAction().name()));
        appInstallMapper.setStore("Google Play");
        appInstallMapper.setPrice("");
        appInstallMapper.setStarRating((double) nativeAdDetails.getRating());
        if (nativeAdDetails.getImageUrl() != null) {
            appInstallMapper.setIcon(new StartAppNativeAdImage(context, nativeAdDetails.getImageBitmap(), nativeAdDetails.getImageUrl()));
        }
        if (nativeAdDetails.getSecondaryImageUrl() != null && !nativeAdDetails.getSecondaryImageUrl().equals(nativeAdDetails.getImageUrl())) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(new StartAppNativeAdImage(context, nativeAdDetails.getSecondaryImageBitmap(), nativeAdDetails.getSecondaryImageUrl()));
            appInstallMapper.setImages(arrayList);
        }
        NativeAdMapper asMapper = appInstallMapper.asMapper();
        asMapper.setOverrideClickHandling(false);
        asMapper.setOverrideImpressionRecording(false);
        if (equals) {
            Bundle bundle = new Bundle();
            bundle.putString(StartAppNative.EXTRAS_INSTALLS, nativeAdDetails.getInstalls());
            bundle.putString(StartAppNative.EXTRAS_CATEGORY, nativeAdDetails.getCategory());
            asMapper.setExtras(bundle);
        }
        return asMapper;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String extractCallToAction(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = 253992913(0xf239fd1, float:8.0673E-30)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 1032794997(0x3d8f3375, float:0.069922365)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "LAUNCH_APP"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0024
            r3 = 1
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "OPEN_MARKET"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0024
            r3 = 0
            goto L_0x0025
        L_0x0024:
            r3 = -1
        L_0x0025:
            if (r3 == r2) goto L_0x002a
            java.lang.String r3 = "Install"
            return r3
        L_0x002a:
            java.lang.String r3 = "Launch app"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.mediation.admob.StartAppNativeAdMapperBuilder.extractCallToAction(java.lang.String):java.lang.String");
    }

    static class AppInstallMapper extends NativeAppInstallAdMapper implements IMapper {
        Context context;
        NativeAdDetails details;

        public NativeAdMapper asMapper() {
            return this;
        }

        public AppInstallMapper(Context context2, NativeAdDetails nativeAdDetails) {
            this.context = context2;
            this.details = nativeAdDetails;
        }

        public void recordImpression() {
            this.details.sendImpression(this.context);
        }

        public void handleClick(View view) {
            this.details.sendClick(this.context);
        }
    }

    static class ContentMapper extends NativeContentAdMapper implements IMapper {
        Context context;
        NativeAdDetails details;

        public NativeAdMapper asMapper() {
            return this;
        }

        public void setIcon(NativeAd.Image image) {
        }

        public void setPrice(String str) {
        }

        public void setStarRating(double d) {
        }

        public void setStore(String str) {
        }

        public ContentMapper(Context context2, NativeAdDetails nativeAdDetails) {
            this.context = context2;
            this.details = nativeAdDetails;
        }

        public void recordImpression() {
            this.details.sendImpression(this.context);
        }

        public void handleClick(View view) {
            this.details.sendClick(this.context);
        }
    }
}
