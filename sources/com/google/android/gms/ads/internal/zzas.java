package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzpw;
import com.google.android.gms.internal.ads.zzpx;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzas {
    static zzv<zzaqw> zza(zzxz zzxz, zzyc zzyc, zzac zzac) {
        return new zzax(zzxz, zzac, zzyc);
    }

    private static String zza(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzakb.zzdk("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String valueOf = String.valueOf(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
        return valueOf.length() != 0 ? "data:image/png;base64,".concat(valueOf) : new String("data:image/png;base64,");
    }

    private static String zza(zzpw zzpw) {
        if (zzpw == null) {
            zzakb.zzdk("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = zzpw.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException unused) {
            zzakb.zzdk("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzpw);
    }

    private static JSONObject zza(Bundle bundle, String str) throws JSONException {
        String str2;
        String str3;
        JSONObject jSONObject = new JSONObject();
        if (bundle != null && !TextUtils.isEmpty(str)) {
            JSONObject jSONObject2 = new JSONObject(str);
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (bundle.containsKey(next)) {
                    if ("image".equals(jSONObject2.getString(next))) {
                        Object obj = bundle.get(next);
                        if (obj instanceof Bitmap) {
                            str2 = zza((Bitmap) obj);
                            jSONObject.put(next, str2);
                        } else {
                            str3 = "Invalid type. An image type extra should return a bitmap";
                        }
                    } else if (bundle.get(next) instanceof Bitmap) {
                        str3 = "Invalid asset type. Bitmap should be returned only for image type";
                    } else {
                        str2 = String.valueOf(bundle.get(next));
                        jSONObject.put(next, str2);
                    }
                    zzakb.zzdk(str3);
                }
            }
        }
        return jSONObject;
    }

    static final /* synthetic */ void zza(zzoo zzoo, String str, zzaqw zzaqw, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzoo.getHeadline());
            jSONObject.put("body", zzoo.getBody());
            jSONObject.put("call_to_action", zzoo.getCallToAction());
            jSONObject.put("price", zzoo.getPrice());
            jSONObject.put("star_rating", String.valueOf(zzoo.getStarRating()));
            jSONObject.put("store", zzoo.getStore());
            jSONObject.put("icon", zza(zzoo.zzjz()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzoo.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzoo.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", InternalAvidAdSessionContext.AVID_API_LEVEL);
            zzaqw.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            zzakb.zzc("Exception occurred when loading assets", e);
        }
    }

    static final /* synthetic */ void zza(zzoq zzoq, String str, zzaqw zzaqw, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzoq.getHeadline());
            jSONObject.put("body", zzoq.getBody());
            jSONObject.put("call_to_action", zzoq.getCallToAction());
            jSONObject.put("advertiser", zzoq.getAdvertiser());
            jSONObject.put("logo", zza(zzoq.zzkg()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzoq.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzoq.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "1");
            zzaqw.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            zzakb.zzc("Exception occurred when loading assets", e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: android.view.View} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0134  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zza(com.google.android.gms.internal.ads.zzaqw r25, com.google.android.gms.internal.ads.zzxe r26, java.util.concurrent.CountDownLatch r27) {
        /*
            r0 = r25
            r1 = r26
            r7 = r27
            r8 = 0
            android.view.View r2 = r25.getView()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            if (r2 != 0) goto L_0x0014
            java.lang.String r0 = "AdWebView is null"
        L_0x000f:
            com.google.android.gms.internal.ads.zzakb.zzdk(r0)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            goto L_0x0132
        L_0x0014:
            r3 = 4
            r2.setVisibility(r3)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzwx r2 = r1.zzbtw     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.util.List<java.lang.String> r2 = r2.zzbsi     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            if (r2 == 0) goto L_0x0123
            boolean r3 = r2.isEmpty()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            if (r3 == 0) goto L_0x0026
            goto L_0x0123
        L_0x0026:
            java.lang.String r3 = "/nativeExpressAssetsLoaded"
            com.google.android.gms.ads.internal.zzav r4 = new com.google.android.gms.ads.internal.zzav     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r4.<init>(r7)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r0.zza((java.lang.String) r3, (com.google.android.gms.ads.internal.gmsg.zzv<? super com.google.android.gms.internal.ads.zzaqw>) r4)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r3 = "/nativeExpressAssetsLoadingFailed"
            com.google.android.gms.ads.internal.zzaw r4 = new com.google.android.gms.ads.internal.zzaw     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r4.<init>(r7)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r0.zza((java.lang.String) r3, (com.google.android.gms.ads.internal.gmsg.zzv<? super com.google.android.gms.internal.ads.zzaqw>) r4)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzxq r3 = r1.zzbtx     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzxz r3 = r3.zzmo()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzxq r4 = r1.zzbtx     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzyc r4 = r4.zzmp()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r5 = "2"
            boolean r5 = r2.contains(r5)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r6 = 0
            if (r5 == 0) goto L_0x00a9
            if (r3 == 0) goto L_0x00a9
            com.google.android.gms.internal.ads.zzoo r2 = new com.google.android.gms.internal.ads.zzoo     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r10 = r3.getHeadline()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.util.List r11 = r3.getImages()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r12 = r3.getBody()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzpw r13 = r3.zzjz()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r14 = r3.getCallToAction()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            double r15 = r3.getStarRating()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r17 = r3.getStore()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r18 = r3.getPrice()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r19 = 0
            android.os.Bundle r20 = r3.getExtras()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r21 = 0
            com.google.android.gms.dynamic.IObjectWrapper r4 = r3.zzmw()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            if (r4 == 0) goto L_0x008c
            com.google.android.gms.dynamic.IObjectWrapper r4 = r3.zzmw()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.Object r4 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r4)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r6 = r4
            android.view.View r6 = (android.view.View) r6     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
        L_0x008c:
            r22 = r6
            com.google.android.gms.dynamic.IObjectWrapper r23 = r3.zzke()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r24 = 0
            r9 = r2
            r9.<init>(r10, r11, r12, r13, r14, r15, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzwx r3 = r1.zzbtw     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r3 = r3.zzbsh     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzasc r4 = r25.zzuf()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.ads.internal.zzat r5 = new com.google.android.gms.ads.internal.zzat     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r5.<init>(r2, r3, r0)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
        L_0x00a5:
            r4.zza((com.google.android.gms.internal.ads.zzasd) r5)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            goto L_0x0100
        L_0x00a9:
            java.lang.String r3 = "1"
            boolean r2 = r2.contains(r3)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            if (r2 == 0) goto L_0x011f
            if (r4 == 0) goto L_0x011f
            com.google.android.gms.internal.ads.zzoq r2 = new com.google.android.gms.internal.ads.zzoq     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r10 = r4.getHeadline()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.util.List r11 = r4.getImages()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r12 = r4.getBody()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzpw r13 = r4.zzkg()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r14 = r4.getCallToAction()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r15 = r4.getAdvertiser()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r16 = 0
            android.os.Bundle r17 = r4.getExtras()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r18 = 0
            com.google.android.gms.dynamic.IObjectWrapper r3 = r4.zzmw()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            if (r3 == 0) goto L_0x00e6
            com.google.android.gms.dynamic.IObjectWrapper r3 = r4.zzmw()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.Object r3 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r3)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r6 = r3
            android.view.View r6 = (android.view.View) r6     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
        L_0x00e6:
            r19 = r6
            com.google.android.gms.dynamic.IObjectWrapper r20 = r4.zzke()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r21 = 0
            r9 = r2
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzwx r3 = r1.zzbtw     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r3 = r3.zzbsh     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzasc r4 = r25.zzuf()     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.ads.internal.zzau r5 = new com.google.android.gms.ads.internal.zzau     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            r5.<init>(r2, r3, r0)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            goto L_0x00a5
        L_0x0100:
            com.google.android.gms.internal.ads.zzwx r2 = r1.zzbtw     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r3 = r2.zzbsf     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            com.google.android.gms.internal.ads.zzwx r1 = r1.zzbtw     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            java.lang.String r2 = r1.zzbsg     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            if (r2 == 0) goto L_0x0115
            java.lang.String r4 = "text/html"
            java.lang.String r5 = "UTF-8"
            r6 = 0
            r1 = r25
            r1.loadDataWithBaseURL(r2, r3, r4, r5, r6)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
            goto L_0x011c
        L_0x0115:
            java.lang.String r1 = "text/html"
            java.lang.String r2 = "UTF-8"
            r0.loadData(r3, r1, r2)     // Catch:{ RemoteException -> 0x012c, RuntimeException -> 0x0127 }
        L_0x011c:
            r0 = 1
            r8 = 1
            goto L_0x0132
        L_0x011f:
            java.lang.String r0 = "No matching template id and mapper"
            goto L_0x000f
        L_0x0123:
            java.lang.String r0 = "No template ids present in mediation response"
            goto L_0x000f
        L_0x0127:
            r0 = move-exception
            r27.countDown()
            throw r0
        L_0x012c:
            r0 = move-exception
            java.lang.String r1 = "Unable to invoke load assets"
            com.google.android.gms.internal.ads.zzakb.zzc(r1, r0)
        L_0x0132:
            if (r8 != 0) goto L_0x0137
            r27.countDown()
        L_0x0137:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzas.zza(com.google.android.gms.internal.ads.zzaqw, com.google.android.gms.internal.ads.zzxe, java.util.concurrent.CountDownLatch):boolean");
    }

    private static String zzb(zzpw zzpw) {
        String str;
        try {
            IObjectWrapper zzjy = zzpw.zzjy();
            if (zzjy == null) {
                zzakb.zzdk("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzjy);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            str = "Drawable is not an instance of BitmapDrawable. Returning empty string";
            zzakb.zzdk(str);
            return "";
        } catch (RemoteException unused) {
            str = "Unable to get drawable. Returning empty string";
        }
    }

    private static zzpw zzd(Object obj) {
        if (obj instanceof IBinder) {
            return zzpx.zzh((IBinder) obj);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static void zzd(zzaqw zzaqw) {
        View.OnClickListener onClickListener = zzaqw.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(zzaqw.getView());
        }
    }

    public static View zze(zzajh zzajh) {
        if (zzajh == null) {
            zzakb.e("AdState is null");
            return null;
        } else if (zzf(zzajh) && zzajh.zzbyo != null) {
            return zzajh.zzbyo.getView();
        } else {
            try {
                IObjectWrapper view = zzajh.zzbtx != null ? zzajh.zzbtx.getView() : null;
                if (view != null) {
                    return (View) ObjectWrapper.unwrap(view);
                }
                zzakb.zzdk("View in mediation adapter is null.");
                return null;
            } catch (RemoteException e) {
                zzakb.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzf(zzajh zzajh) {
        return (zzajh == null || !zzajh.zzceq || zzajh.zzbtw == null || zzajh.zzbtw.zzbsf == null) ? false : true;
    }
}
