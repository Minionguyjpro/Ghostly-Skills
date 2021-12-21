package com.appnext.ads.fullscreen;

import android.content.Context;
import android.net.Uri;
import android.util.Pair;
import com.appnext.ads.a;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.d;
import com.appnext.core.f;
import com.appnext.core.g;
import com.appnext.core.p;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;

public final class b extends d {
    private static b aL;
    private final int aM = 30;
    private HashMap<Ad, String> aN = new HashMap<>();

    public static synchronized b j() {
        b bVar;
        synchronized (b.class) {
            if (aL == null) {
                aL = new b();
            }
            bVar = aL;
        }
        return bVar;
    }

    private b() {
    }

    /* access modifiers changed from: protected */
    public final String a(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        StringBuilder sb = new StringBuilder("&auid=");
        sb.append(ad != null ? ad.getAUID() : "700");
        sb.append("&vidmin=");
        Object obj = "";
        sb.append(ad == null ? obj : Integer.valueOf(ad.getMinVideoLength()));
        sb.append("&vidmax=");
        if (ad != null) {
            obj = Integer.valueOf(ad.getMaxVideoLength());
        }
        sb.append(obj);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final int a(Context context, g gVar) {
        int i;
        AppnextAd appnextAd = (AppnextAd) gVar;
        FullscreenAd fullscreenAd = new FullscreenAd(appnextAd);
        if (!fullscreenAd.getCampaignGoal().equals(com.appnext.core.a.b.hX) || !f.c(context, fullscreenAd.getAdPackage())) {
            i = (!fullscreenAd.getCampaignGoal().equals(com.appnext.core.a.b.hY) || f.c(context, fullscreenAd.getAdPackage())) ? 0 : 2;
        } else {
            i = 1;
        }
        int b = b(context, appnextAd);
        if (i == 0 && b == 0) {
            return 0;
        }
        return i != 0 ? i : b;
    }

    /* access modifiers changed from: protected */
    public final boolean a(Ad ad) {
        return super.a(ad) && e(ad);
    }

    /* access modifiers changed from: protected */
    public final boolean a(Context context, Ad ad, ArrayList<?> arrayList) {
        return a(context, ad, (ArrayList<AppnextAd>) arrayList, "") != null;
    }

    private void a(Context context, Ad ad, AppnextAd appnextAd) throws Exception {
        String str;
        if (!appnextAd.getImageURL().equals("")) {
            f.Y(appnextAd.getImageURL());
        }
        if (!appnextAd.getWideImageURL().equals("")) {
            f.Y(appnextAd.getWideImageURL());
        }
        Video video = (Video) ad;
        String videoUrl = getVideoUrl(appnextAd, video.getVideoLength());
        String c = c(videoUrl);
        if (Video.getCacheVideo()) {
            str = context.getFilesDir().getAbsolutePath() + "/data/appnext/videos/";
        } else {
            str = context.getFilesDir().getAbsolutePath() + "/data/appnext/videos/" + "tmp/vid" + video.rnd + "/";
        }
        File file = new File(str + c);
        if (file.exists()) {
            file.setLastModified(System.currentTimeMillis());
            StringBuilder sb = new StringBuilder();
            sb.append(file.getPath());
            sb.append(" exists");
            this.aN.put(ad, file.getAbsolutePath());
        } else if (!Video.isStreamingModeEnabled()) {
            new File(str).mkdirs();
            URL url = new URL(videoUrl);
            url.openConnection().connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream(), com.appnext.base.b.d.fb);
            FileOutputStream fileOutputStream = new FileOutputStream(str + c + com.appnext.base.b.d.eY);
            byte[] bArr = new byte[com.appnext.base.b.d.fb];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    StringBuilder sb2 = new StringBuilder("downloaded ");
                    sb2.append(str);
                    sb2.append(c);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    bufferedInputStream.close();
                    File file2 = new File(str + c + com.appnext.base.b.d.eY);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append(c);
                    file2.renameTo(new File(sb3.toString()));
                    file2.delete();
                    this.aN.put(ad, file.getAbsolutePath());
                    return;
                }
            }
        }
    }

    private void a(Context context, Ad ad) {
        int i;
        int i2;
        try {
            File[] listFiles = new File(context.getFilesDir().getAbsolutePath() + "/data/appnext/videos/").listFiles();
            Arrays.sort(listFiles, new Comparator<File>() {
                public final /* synthetic */ int compare(Object obj, Object obj2) {
                    return Long.valueOf(((File) obj).lastModified()).compareTo(Long.valueOf(((File) obj2).lastModified()));
                }

                public static int a(File file, File file2) {
                    return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
                }
            });
            if (Video.getCacheVideo()) {
                if (ad instanceof FullScreenVideo) {
                    i2 = Integer.parseInt(c.m().get("num_saved_videos"));
                } else {
                    i2 = Integer.parseInt(f.q().get("num_saved_videos"));
                }
                i = i2 - 1;
            } else {
                i = 0;
            }
            if (listFiles.length > i) {
                for (int i3 = 0; i3 < listFiles.length - i; i3++) {
                    listFiles[i3].delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public final void a(Ad ad, String str, String str2) {
        if (ad != null) {
            f.a(ad.getTID(), ad.getVID(), ad.getAUID(), str2, str, a.k, "sdk", "", "");
        } else {
            f.a("300", "2.5.1.472", "700", str2, str, a.k, "sdk", "", "");
        }
        new StringBuilder("error ").append(str);
    }

    /* access modifiers changed from: protected */
    public final <T> void a(String str, Ad ad, T t) {
        f.a(ad.getTID(), ad.getVID(), ad.getAUID(), str, ((Video) ad).getSessionId(), a.j, "sdk", "", "");
    }

    protected static String getVideoUrl(AppnextAd appnextAd, String str) {
        String str2;
        if (str.equals("30")) {
            str2 = appnextAd.getVideoUrlHigh30Sec();
            if (str2.equals("")) {
                str2 = appnextAd.getVideoUrlHigh();
            }
        } else {
            str2 = appnextAd.getVideoUrlHigh();
            if (str2.equals("")) {
                str2 = appnextAd.getVideoUrlHigh30Sec();
            }
        }
        StringBuilder sb = new StringBuilder("returning video url for: ");
        sb.append(appnextAd.getBannerID());
        sb.append(" with len: ");
        sb.append(str);
        sb.append(" url: ");
        sb.append(str2);
        return str2;
    }

    /* access modifiers changed from: protected */
    public final void a(String str, Ad ad) {
        super.a(str, ad);
        if (this.aN.containsKey(ad)) {
            this.aN.remove(ad);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean b(Ad ad) {
        try {
            if (!h(ad) || k(ad).aU().longValue() + j(ad) + 300000 < System.currentTimeMillis() || !e(ad)) {
                return false;
            }
            return true;
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public final p c(Ad ad) {
        return ad instanceof RewardedVideo ? f.q() : c.m();
    }

    /* access modifiers changed from: protected */
    public final boolean d(Ad ad) {
        try {
            return a(ad) && e(ad);
        } catch (Throwable unused) {
        }
    }

    private boolean e(Ad ad) {
        if (Video.isStreamingModeEnabled()) {
            return true;
        }
        if (!this.aN.containsKey(ad)) {
            return false;
        }
        return new File(this.aN.get(ad)).exists();
    }

    private static boolean b(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    /* access modifiers changed from: protected */
    public final AppnextAd b(Context context, Ad ad) {
        return a(context, ad, "");
    }

    /* access modifiers changed from: protected */
    public final AppnextAd a(Context context, Ad ad, String str) {
        ArrayList<?> ads;
        if (k(ad) == null || (ads = k(ad).getAds()) == null) {
            return null;
        }
        return a(context, ad, (ArrayList<AppnextAd>) ads, str);
    }

    /* access modifiers changed from: protected */
    public final AppnextAd a(Context context, Ad ad, ArrayList<AppnextAd> arrayList, String str) {
        Iterator<AppnextAd> it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd next = it.next();
            if (b(next) && !a(next.getBannerID(), ad.getPlacementID()) && !next.getBannerID().equals(str)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final ArrayList<AppnextAd> f(Ad ad) {
        return k(ad).getAds();
    }

    private static int a(Context context, AppnextAd appnextAd) {
        FullscreenAd fullscreenAd = new FullscreenAd(appnextAd);
        if (!fullscreenAd.getCampaignGoal().equals(com.appnext.core.a.b.hX) || !f.c(context, fullscreenAd.getAdPackage())) {
            return (!fullscreenAd.getCampaignGoal().equals(com.appnext.core.a.b.hY) || f.c(context, fullscreenAd.getAdPackage())) ? 0 : 2;
        }
        return 1;
    }

    private static int b(Context context, AppnextAd appnextAd) {
        FullscreenAd fullscreenAd = new FullscreenAd(appnextAd);
        if (!fullscreenAd.getCptList().equals("") && !fullscreenAd.getCptList().equals("[]")) {
            try {
                JSONArray jSONArray = new JSONArray(fullscreenAd.getCptList());
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (f.c(context, jSONArray.getString(i))) {
                        return 0;
                    }
                }
                return 3;
            } catch (JSONException unused) {
            }
        }
        return 0;
    }

    protected static String c(String str) {
        String substring = str.substring(str.lastIndexOf("/") + 1);
        if (substring.contains("?")) {
            substring = substring.substring(0, substring.indexOf("?"));
        }
        try {
            String queryParameter = Uri.parse(str).getQueryParameter("rnd");
            if (queryParameter == null || queryParameter.equals("")) {
                return substring;
            }
            return substring.substring(0, substring.lastIndexOf(46)) + "_" + queryParameter + substring.substring(substring.lastIndexOf(46));
        } catch (Throwable unused) {
            return substring;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(Context context, Ad ad, com.appnext.core.a aVar) throws Exception {
        AppnextAd a2;
        int i;
        int i2;
        try {
            File[] listFiles = new File(context.getFilesDir().getAbsolutePath() + "/data/appnext/videos/").listFiles();
            Arrays.sort(listFiles, new Comparator<File>() {
                public final /* synthetic */ int compare(Object obj, Object obj2) {
                    return Long.valueOf(((File) obj).lastModified()).compareTo(Long.valueOf(((File) obj2).lastModified()));
                }

                public static int a(File file, File file2) {
                    return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
                }
            });
            if (Video.getCacheVideo()) {
                if (ad instanceof FullScreenVideo) {
                    i2 = Integer.parseInt(c.m().get("num_saved_videos"));
                } else {
                    i2 = Integer.parseInt(f.q().get("num_saved_videos"));
                }
                i = i2 - 1;
            } else {
                i = 0;
            }
            if (listFiles.length > i) {
                for (int i3 = 0; i3 < listFiles.length - i; i3++) {
                    listFiles[i3].delete();
                }
            }
        } catch (Throwable unused) {
        }
        AppnextAd appnextAd = null;
        try {
            AppnextAd b = b(context, ad);
            if (b != null) {
                a(context, ad, b);
                if (ad instanceof RewardedVideo) {
                    String mode = ((RewardedVideo) ad).getMode();
                    if (mode.equals(RewardedVideo.VIDEO_MODE_DEFAULT)) {
                        mode = f.q().get("default_mode");
                    }
                    if (mode.equals("multi") && (a2 = a(context, ad, b.getBannerID())) != null) {
                        a(context, ad, a2);
                        return;
                    }
                    return;
                }
                return;
            }
            throw new Exception("No video ads");
        } catch (Throwable th) {
            if (appnextAd != null) {
                a(appnextAd.getBannerID(), ad);
            }
            throw th;
        }
    }
}
